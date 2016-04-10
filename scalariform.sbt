import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

SbtScalariform.scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignParameters, true)
  .setPreference(CompactStringConcatenation, false)
  .setPreference(IndentPackageBlocks, true)
  .setPreference(FormatXml, true)
  .setPreference(PreserveSpaceBeforeArguments, false)
  .setPreference(DoubleIndentClassDeclaration, false)
  .setPreference(RewriteArrowSymbols, false)
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 40)
  .setPreference(SpaceBeforeColon, false)
  .setPreference(SpaceInsideBrackets, false)
  .setPreference(SpaceInsideParentheses, false)
  .setPreference(PreserveDanglingCloseParenthesis, false)
  .setPreference(IndentSpaces, 2)
  .setPreference(IndentLocalDefs, false)
  .setPreference(SpacesWithinPatternBinders, true)
  .setPreference(SpacesAroundMultiImports, true)
