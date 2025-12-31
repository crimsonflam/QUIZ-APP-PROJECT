@echo off

javac -cp ".;lib/sqlite-jdbc-3.51.1.0.jar;." src/login.java src/*.java
java -cp "src;lib/sqlite-jdbc-3.51.1.0.jar;." login
pause