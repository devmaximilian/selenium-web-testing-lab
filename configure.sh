#!/bin/sh

# Download and unpack Selenium Jar files
curl -o selenium-java.zip https://selenium-release.storage.googleapis.com/3.141/selenium-java-3.141.59.zip
mkdir -p selenium
unzip -o selenium-java.zip -d selenium/
rm selenium-java.zip
