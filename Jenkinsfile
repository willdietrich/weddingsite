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

    stage('Deploy') {
      steps {
        script {
          def server = Artifactory.server('Artifactory')
          def rtMaven = Artifactory.newMavenBuild()

          rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
          rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'

          rtMaven.tool = 'm3'

          def buildInfo = rtMaven.run pom: 'pom.xml', goals: '-Dmaven.test.skip=true clean install'
        }
      }
    }
  }
}
