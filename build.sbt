name := "reactivemongo-demo-app"

val buildVersion = "0.16.0"

version := buildVersion

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  "Sonatype Staging" at "https://oss.sonatype.org/content/repositories/staging/")

scalacOptions in Compile += "-target:jvm-1.8"

scalaVersion := "2.12.6"

libraryDependencies ++= {
  val (playVer, nativeVer) = buildVersion.span(_ != '-') match {
    case (major, "") =>
      s"${major}-play26" -> s"${major}-linux-x86-64"

    case (major, mod) =>
      s"${major}-play26${mod}" -> s"${major}-linux-x86-64${mod}"
  }

  Seq(
    guice,
    "com.typesafe.play" %% "play-iteratees" % "2.6.1",
    "com.typesafe.akka" %% "akka-slf4j" % "2.5.4",
    "org.reactivemongo" %% "play2-reactivemongo" % playVer,
    "org.reactivemongo" % "reactivemongo-shaded-native" % nativeVer
  )
}

routesGenerator := InjectedRoutesGenerator

fork in run := true

lazy val root = (project in file(".")).enablePlugins(PlayScala)
