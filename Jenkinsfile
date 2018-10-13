pipeline {
  agent any

  tools {
    maven 'm3'
    jdk 'jdk8'
  }

  environment {
    ARTIFACTORY_CONTEXT_URL = 'https://artifactory.walld.me/artifactory'
    ARTIFACTORY_CREDS = credentials('67493199-cb0b-4dbc-beae-35475bd3a55f')
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
        sh 'mvn -Dmaven.test.skip=true deploy'
      }
    }
  }
}
