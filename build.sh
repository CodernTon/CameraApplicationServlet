#!/bin/bash

CLASSPATH="webroot/WEB-INF/classes;webroot/WEB-INF/lib/org.json.jar;webroot/WEB-INF/lib/javax.servlet-api-3.1.0.jar"
WINSTONE_JAR=winstone.jar
JAVA_FILES="webroot/WEB-INF/classes/JsonFormatter.java webroot/WEB-INF/classes/Chemical.java webroot/WEB-INF/classes/ChemicalsServlet.java webroot/WEB-INF/classes/Message.java"


compile () {
  javac -cp ${CLASSPATH}  ${JAVA_FILES}
  if [ $? -ne 0 ]
  then
    echo "Compilation failed"
    exit 1
  fi
}

compile
java -jar $WINSTONE_JAR --webroot=webroot
