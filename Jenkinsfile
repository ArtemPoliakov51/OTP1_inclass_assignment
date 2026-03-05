pipeline {
  agent any

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('check') {
      steps {
        sh 'mvn -v'
      }
    }

    stage('build job') {
      steps {
        sh 'mvn -B clean compile'
      }
    }

    stage('test') {
      steps {
        sh 'mvn -B test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }

    stage('Report') {
      steps {
        sh 'mvn -B jacoco:report'
        archiveArtifacts artifacts: 'target/site/jacoco/**', allowEmptyArchive: true
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t temperature-converter:latest .'
      }
    }

    stage('Push Docker Image to Docker Hub') {
      steps {
        echo 'Optional step (can be added later)'
      }
    }
  }
}