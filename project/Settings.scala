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

  /** Declare global dependency versions here to avoid mismatches in multi part dependencies */
  object versions {
    val scala = "2.11.11"
    val scalaDom = "0.9.1"
    val scalajsReact = "0.11.3"
    //    val scalaCSS = "0.5.0"
    //    val log4js = "1.4.10"
    //    val autowire = "0.2.5"
    //    val booPickle = "1.2.5"
    //    val diode = "1.1.0"
    //    val uTest = "0.4.4"
    val react = "15.3.2"
    val jQuery = "3.2.1"
    val bootstrap = "3.3.7"
    val compass = "1.0.2"
    val fontawesome = "4.3.0-1"
    //    val chartjs = "2.1.3"
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
    "com.vmunier" %% "scalajs-scripts" % versions.scalajsScripts,

    "org.webjars" % "font-awesome" % versions.fontawesome % Provided,
    "org.webjars.bower" % "bootstrap-sass" % versions.bootstrap % Provided,
    "org.webjars.bower" % "compass-mixins" % versions.compass % Provided,
    //    "com.lihaoyi" %% "utest" % versions.uTest % Test
    //    jdbc,
    cache,
    ws,
    specs2 % Test
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
        "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
        "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
//        "com.github.japgolly.scalacss" %%% "ext-react" % versions.scalaCSS,
    //    "me.chrons" %%% "diode" % versions.diode,
    //    "me.chrons" %%% "diode-react" % versions.diode,
        "org.scala-js" %%% "scalajs-dom" % versions.scalaDom //,
    //    "com.lihaoyi" %%% "utest" % versions.uTest % Test
  ))

  /** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
  val jsDependencies = Def.setting(Seq(
        "org.webjars" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
        "org.webjars" % "react" % versions.react / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
        "org.webjars" % "react" % versions.react / "react-dom-server.js" minified  "react-dom-server.min.js" dependsOn "react-dom.js" commonJSName "ReactDOMServer",

        "org.webjars" % "jquery" % versions.jQuery / "jquery.js" minified "jquery.min.js",
        "org.webjars" % "bootstrap-sass" % versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js"
    //    "org.webjars" % "chartjs" % versions.chartjs / "Chart.js" minified "Chart.min.js",
    //    "org.webjars" % "log4javascript" % versions.log4js / "js/log4javascript_uncompressed.js" minified "js/log4javascript.js"
  ))

  val npmDependencies: Seq[(String, String)] = Seq[(String, String)](

  )

  val npmDevDependencies: Seq[(String, String)] = Seq[(String, String)](

  )
}
