pipeline {
  agent any

  tools {
    maven 'm3'
    jdk 'jdk8'
  }

  stages {
    stage ('Initialize') {
      steps {
        sh '''
           echo "PATH = ${PATH}"
           echo "M2_HOME = ${M2_HOME}"
           '''
      }
    }

    stage('Build') {
      steps {
        script {
          withMaven(
                  maven: 'm3',
                  jdk: 'jdk8',
                  mavenSettingsConfig: 'fc83efb4-91c1-4f69-95a0-cd2648c11242'
          ) {
            sh 'mvn -Dmaven.test.skip=true clean deploy'
          }
        }
      }
    }
  }
}
