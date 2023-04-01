@echo off

rem ＜事前準備＞
rem JavaHomeがpath設定と異なれば設定してください
rem SonarQubeをローカルでセットアップしてトークンを置き換えてください

cd ..
./gradlew sonar ^
  -Dorg.gradle.java.home="C:\java\jdk-17" ^
  -Dsonar.projectKey=spring-boot-api-jpa ^
  -Dsonar.host.url=http://localhost:9000 ^
  -Dsonar.login=sqp_e2fc1d94c8fe4c3c1bc1f742386c92363793920e

