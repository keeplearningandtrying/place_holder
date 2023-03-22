cmd /C copy d:\wei\dev\java\pom-formatter.xml .
cmd /C mvn -f pom-formatter.xml spotless:apply
cmd /C del .\pom-formatter.xml