#!/bin/bash

echo "-------------------------------------" 
echo "-      Bienvenido a turismoUy       -" 
echo "-------------------------------------" 

#/Users/francocabrera/apache-tomcat-9.0.68/bin/catalina.sh stop

echo "1...compiling server"
cd ServidorCentral
ant create_run_jar
cd ..

echo "2...compiling web"
cd turismoUy
ant war
cd ..

#echo "3...levantando Tomcat"
#/Users/francocabrera/apache-tomcat-9.0.68/bin/catalina.sh start 

#cd ServidorCentral
#java -jar servidor.jar