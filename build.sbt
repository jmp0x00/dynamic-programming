ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val Benchmark = config("benchmark").extend(Test)

lazy val root = (project in file("."))
  .configs(Benchmark)
  .settings(
    name                          := "dynamic-programming",
    resolvers += "Sonatype OSS Releases".at("https://oss.sonatype.org/content/repositories/releases"),
    libraryDependencies ++= Seq(
      "dev.zio"           %% "zio"        % "2.0.9",
      "dev.zio"           %% "zio-test"   % "2.0.9"  % Test,
      "org.scalacheck"    %% "scalacheck" % "1.17.0" % Test,
      "com.storm-enroute" %% "scalameter" % "0.21"   % Benchmark,
    ),
    testFrameworks ++= Seq(
      new TestFramework("zio.test.sbt.ZTestFramework"),
      new TestFramework("org.scalameter.ScalaMeterFramework"),
    ),
    Benchmark / parallelExecution := false,
    logBuffered                   := false,
  )
  .settings(
    inConfig(Benchmark)(Defaults.testSettings),
  )
