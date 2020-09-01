@echo off
javac *.java
jar cvfe Sets.jar Main *.class
del *.class
::In cvfe, c is to create a jar, v is for verbose, f is for writing to file, e is for main class