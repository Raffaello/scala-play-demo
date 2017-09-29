import play.sbt.PlayImport.{cache, jdbc, specs2, ws}
import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

/**
  * Application settings. Configure the build for your application here.
  * You normally don't have to touch the actual build definition after this.
  */
object Settings {
  /** The name of your application */
  val name = "scala-play-demo-scalajs"
  /** The version of your application */
  val version = "1.0.0"
  /** Options for the scala compiler */
  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  object Strings {
    val webjars = "org.webjars"
    val bower = "org.webjars.bower"
    val scalajsReact = "com.github.japgolly.scalajs-react"
    val reactWithAddon = "react-with-addons.js"
    val reactDom = "react-dom.js"
    val jQuery = "jquery.js"
    val react = "react"
    val bootstrapSass = "bootstrap-sass"
  }
  /** Declare global dependency versions here to avoid mismatches in multi part dependencies */
  object Versions {
    val scala = "2.11.11"
    val scalaDom = "0.9.1"
    val scalajsReact = "1.0.1"
    //    val log4js = "1.4.10"
    //    val autowire = "0.2.5"
    //    val booPickle = "1.2.5"
    //    val diode = "1.1.0"
    //    val uTest = "0.4.4"
    val react = "15.3.2" // 15.5.4 // 15.6.0
    val jQuery = "3.2.1"
    val bootstrap = "3.3.7"
    val compass = "1.0.2"
    val fontawesome = "4.3.0-1"
    val scalajsScripts = "1.0.0"
  }

  /**
    * These dependencies are shared between JS and JVM projects
    * the special %%% function selects the correct version for each project
    */
  val sharedDependencies = Def.setting(Seq(
    //    "com.lihaoyi" %%% "autowire" % versions.autowire,
    //    "me.chrons" %%% "boopickle" % versions.booPickle
  ))

  /** Dependencies only used by the JVM project */
  val playDependencies = Def.setting(Seq(
    "com.vmunier" %% "scalajs-scripts" % Versions.scalajsScripts,
    Strings.webjars % "font-awesome" % Versions.fontawesome % Provided,
    Strings.bower % Strings.bootstrapSass % Versions.bootstrap % Provided,
    Strings.bower % "compass-mixins" % Versions.compass % Provided,
    //    "com.lihaoyi" %% "utest" % versions.uTest % Test
    cache,
    ws,
    specs2 % Test,
    "org.specs2" %% "specs2-matcher-extra" % "3.8" % "test"
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
    Strings.scalajsReact %%% "core" % Versions.scalajsReact,
    Strings.scalajsReact %%% "extra" % Versions.scalajsReact,
    //    "me.chrons" %%% "diode" % versions.diode,
    //    "me.chrons" %%% "diode-react" % versions.diode,
    "org.scala-js" %%% "scalajs-dom" % Versions.scalaDom //,
    //    "com.lihaoyi" %%% "utest" % versions.uTest % Test
  ))

  /** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
  val jsDependencies = Def.setting(Seq(
    Strings.webjars % Strings.react % Versions.react / Strings.reactWithAddon minified "react-with-addons.min.js" commonJSName "React",
    Strings.webjars % Strings.react % Versions.react / Strings.reactDom minified "react-dom.min.js" dependsOn Strings.reactWithAddon commonJSName "ReactDOM",
    Strings.webjars % Strings.react % Versions.react / "react-dom-server.js" minified "react-dom-server.min.js" dependsOn Strings.reactDom commonJSName "ReactDOMServer",

    Strings.webjars % "jquery" % Versions.jQuery / Strings.jQuery minified "jquery.min.js",
    Strings.webjars % Strings.bootstrapSass % Versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn Strings.jQuery
    //    Strings.webjars % "log4javascript" % versions.log4js / "js/log4javascript_uncompressed.js" minified "js/log4javascript.js"
  ))

  //  val npmDependencies = Seq(
  //    "react" -> Versions.react,
  //    "react-dom" -> Versions.react,
  //    "react-dom-server" -> Versions.react
  //  )
}
