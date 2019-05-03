//import play.sbt.PlayImport.PlayKeys.playRunHooks

name := """scala-play-demo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
//  jdbc,
  cache,
  ws,
  specs2 % Test
//  "com.codeborne" % "phantomjsdriver" % "1.2.1"
)

//playRunHooks <+= (baseDirectory / "reactApp/node_modules/.bin/").map(Webpack.apply)

//excludeFilter in (Assets, JshintKeys.jshint) := "*.js"

//watchSources ~= { (ws: Seq[File]) =>
//  ws filterNot { path =>
//    path.getName.endsWith(".js") || path.getName == "build"
//  }
//}

//pipelineStages := Seq(digest)

//resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

fork in run := true

//herokuAppName in Compile := "lit-spire-71369"
//herokuJdkVersion in Compile := "1.8"
