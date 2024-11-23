# Development Environment Setup

To build and run this project, you'll need to set up the following tools:

## Required Software

1. Java Development Kit (JDK) 8 or higher
   - Download from: https://adoptopenjdk.net/
   - Add JAVA_HOME environment variable
   - Add %JAVA_HOME%\bin to PATH

2. Apache Ant
   - Download from: https://ant.apache.org/bindownload.cgi
   - Extract to a directory (e.g., C:\apache-ant)
   - Add ANT_HOME environment variable
   - Add %ANT_HOME%\bin to PATH

## Quick Setup Guide

1. Install JDK:
   ```
   a. Download JDK installer
   b. Run installer
   c. Set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_xxx
   d. Add %JAVA_HOME%\bin to PATH
   ```

2. Install Ant:
   ```
   a. Download Ant zip file
   b. Extract to C:\apache-ant
   c. Set ANT_HOME=C:\apache-ant
   d. Add %ANT_HOME%\bin to PATH
   ```

3. Verify Installation:
   ```
   java -version
   ant -version
   ```

## Building Without Ant

If you don't want to install Ant, you can compile and run manually:

1. Compile the project:
   ```
   mkdir build\classes
   javac -d build\classes -cp "lib/*" src/behavioral/command/*.java src/behavioral/command/animation/*.java
   ```

2. Run tests:
   ```
   mkdir build\test-classes
   javac -d build\test-classes -cp "build\classes;lib/*" test/behavioral/command/*.java
   java -cp "build\classes;build\test-classes;lib/*" org.junit.runner.JUnitCore behavioral.command.CommandPatternTest
   ```

3. Run the demo:
   ```
   java -cp "build\classes;lib/*" behavioral.command.animation.CommandPatternDemo
   ```

## Troubleshooting

1. "java" not recognized:
   - Make sure JAVA_HOME is set correctly
   - Verify Java is in PATH
   - Try using full path to java.exe

2. "ant" not recognized:
   - Make sure ANT_HOME is set correctly
   - Verify Ant is in PATH
   - Try using full path to ant.bat

3. Compilation errors:
   - Make sure all required JARs are in lib/
   - Check CLASSPATH includes required JARs
   - Verify source files are in correct packages

## Need Help?

If you encounter any issues:
1. Check environment variables are set correctly
2. Verify all required files are present
3. Look for error messages in console output
4. Refer to project documentation
