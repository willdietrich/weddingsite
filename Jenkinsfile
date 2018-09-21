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
        sh 'mvn -Dmaven.test.skip=true install'
      }
    }
  }
}
