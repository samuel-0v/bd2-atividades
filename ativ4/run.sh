#!/bin/bash
JAR=postgresql-42.7.11.jar

javac -cp $JAR *.java

if [ $? -eq 0 ]; then
    java -cp .:$JAR Main
fi