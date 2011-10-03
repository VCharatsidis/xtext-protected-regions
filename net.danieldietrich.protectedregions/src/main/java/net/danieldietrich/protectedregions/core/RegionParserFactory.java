package net.danieldietrich.protectedregions.core;


/**
 * @author Daniel Dietrich - Initial contribution and API
 */
public class RegionParserFactory {

  private RegionParserFactory() {
  }

  // Clojure
  public static IRegionParser createClojureParser() {
    return createClojureParser(null, false);
  }
  public static IRegionParser createClojureParser(boolean inverse) {
    return createClojureParser(null, inverse);
  }
  public static IRegionParser createClojureParser(IRegionOracle oracle, boolean inverse) {
    return new RegionParserBuilder().addComment(";")
        .setInverse(inverse).usingOracle(oracle).build();
  }

  // Java
  public static IRegionParser createJavaParser() {
    return createJavaParser(null, false);
  }
  public static IRegionParser createJavaParser(boolean inverse) {
    return createJavaParser(null, inverse);
  }
  public static IRegionParser createJavaParser(IRegionOracle oracle, boolean mergeStyle) {
    return new RegionParserBuilder().addComment("/*", "*/").addComment("//")
        .setInverse(mergeStyle).usingOracle(oracle).build();
  }

  // Ruby
  public static IRegionParser createRubyParser() {
    return createClojureParser(null, false);
  }
  public static IRegionParser createRubyParser(boolean inverse) {
    return createRubyParser(null, inverse);
  }
  public static IRegionParser createRubyParser(IRegionOracle oracle, boolean mergeStyle) {
    return new RegionParserBuilder().addComment("#")
        .setInverse(mergeStyle).usingOracle(oracle).build();
  }

  // Scala
  public static IRegionParser createScalaParser() {
    return createScalaParser(null, false);
  }
  public static IRegionParser createScalaParser(boolean inverse) {
    return createScalaParser(null, inverse);
  }
  public static IRegionParser createScalaParser(IRegionOracle oracle, boolean mergeStyle) {
    return new RegionParserBuilder().addNestableComment("/*", "*/").addComment("//")
        .setInverse(mergeStyle).usingOracle(oracle).build();
  }

  // Xml
  public static IRegionParser createXmlParser() {
    return createXmlParser(null, false);
  }
  public static IRegionParser createXmlParser(boolean inverse) {
    return createXmlParser(null, inverse);
  }
  public static IRegionParser createXmlParser(IRegionOracle oracle, boolean mergeStyle) {
    return new RegionParserBuilder().addComment("<!--", "-->")
        .setInverse(mergeStyle).usingOracle(oracle).build();
  }
  
  // Xtend2
  public static IRegionParser createXtend2Parser() {
    return createJavaParser(null, false);
  }
  public static IRegionParser createXtend2Parser(boolean inverse) {
    return createXtend2Parser(null, inverse);
  }
  public static IRegionParser createXtend2Parser(IRegionOracle oracle, boolean mergeStyle) {
    return new RegionParserBuilder().addComment("/*", "*/").addComment("//")
        .setInverse(mergeStyle).usingOracle(oracle).build();
  }

  // Xtext
  public static IRegionParser createXtextParser() {
    return createJavaParser(null, false);
  }
  public static IRegionParser createXtextParser(boolean inverse) {
    return createXtextParser(null, inverse);
  }
  public static IRegionParser createXtextParser(IRegionOracle oracle, boolean mergeStyle) {
    return new RegionParserBuilder().addComment("/*", "*/").addComment("//")
        .setInverse(mergeStyle).usingOracle(oracle).build();
  }
}
