@echo off

rem �����O������
rem JavaHome��path�ݒ�ƈقȂ�ΐݒ肵�Ă�������
rem SonarQube�����[�J���ŃZ�b�g�A�b�v���ăg�[�N����u�������Ă�������

cd ..
./gradlew sonar ^
  -Dorg.gradle.java.home="C:\java\jdk-17" ^
  -Dsonar.projectKey=spring-boot-api-jpa ^
  -Dsonar.host.url=http://localhost:9000 ^
  -Dsonar.login=sqp_e2fc1d94c8fe4c3c1bc1f742386c92363793920e

