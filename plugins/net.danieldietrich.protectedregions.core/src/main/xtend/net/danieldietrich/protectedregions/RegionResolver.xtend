package net.danieldietrich.protectedregions

import java.util.regex.Pattern
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * The prototype for resolvers of protected regions.
 * Write your own resolvers by implementing getId() and isEnabled().
 * Pass regular expressions for protected region start and end to the constructor.
 * The id has to be the first regex group (denoted by '(' and ')').
 * The enabled part has to be the second regex group.
 * If additional groups are necessary they have to be disabled using '(?:' and ')'.
 * Nested groups are allowed, e.g. '(?:xxx(xxx))*'.
 */
abstract class RegionResolver {
	
	@Accessors val Pattern start
	@Accessors val Pattern end
	
	new(String start, String end) {
		if (start.isNullOrEmpty) throw new IllegalArgumentException("Start cannot be empty")
		if (end.isNullOrEmpty) throw new IllegalArgumentException("End cannot be empty")
		this.start = Pattern::compile(start)
		this.end = Pattern::compile(end)
	}
	
	def boolean isStart(String region) {
		start.matcher(region).matches
	}
	
	def boolean isEnd(String region) {
		end.matcher(region).matches
	}

	def String getId(String regionStart) {
		getString(regionStart, 1)
	}

	def getEnabled(String regionStart) {
		getString(regionStart, 2)
	}
	
	/** To be implemented by custom resolvers. */
	def boolean isEnabled(String regionStart)
	
	def protected getString(String regionStart, int group) {
		val matcher = start.matcher(regionStart)
		val found = matcher.find()
		if (!found) null else matcher.group(group)
	}
	
}

// By default protected regions should be generated in enabled state (@see Github issue #31)
class DefaultProtectedRegionResolver extends RegionResolver {
	
	static val ID = "[\\p{L}\\p{N}\\.:_$]*"
	static val PR_START = "PROTECTED\\s+REGION\\s+ID\\s*\\(\\s*("+ ID +")\\s*\\)\\s+(?:(ENABLED)\\s+)?START"
	static val PR_END = "PROTECTED\\s+REGION\\s+END"
	
	new() { super(PR_START, PR_END) }
	
	override isEnabled(String regionStart) {
		"ENABLED".equals(getEnabled(regionStart))
	}
	
}

// By default generated regions should be generated in disabled state (@see Github issue #31)
class DefaultGeneratedRegionResolver extends RegionResolver {

	static val ID = "[\\p{L}\\p{N}\\.:_$]*"
	static val GR_START = "GENERATED\\s+REGION\\s+ID\\s*\\(\\s*("+ ID +")\\s*\\)\\s+(?:(DISABLED)\\s+)?START"
	static val GR_END = "GENERATED\\s+REGION\\s+END"
	
	new() { super(GR_START, GR_END) }
	
	override isEnabled(String regionStart) {
		!"DISABLED".equals(getEnabled(regionStart))
	}
	
}
