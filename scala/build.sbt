name := "katas"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats-effect"  % "2.0.0",
  "org.scalactic"              %% "scalactic"    % "3.0.8",
  "org.scalatest"              %% "scalatest"    % "3.0.8" % "test",
  "eu.timepit"                 %% "refined"      % "0.9.10",
  "com.softwaremill.quicklens" %% "quicklens"    % "1.4.12",
  "org.scalacheck"             %% "scalacheck"   % "1.14.1" % "test")