pipeline {
  agent any

  tools {
    maven 'Maven'
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Run Tests') {
      steps {
        bat 'mvn clean test'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Code Coverage') {
      steps {
        bat 'mvn jacoco:report'
      }
    }

    stage('Publish Coverage Report') {
      steps {
        echo 'Publishing coverage report'
      }
    }

    stage('Build Docker Image') {
      steps {
        echo 'Docker build stage'
      }
    }

    stage('Push Docker Image to Docker Hub') {
      steps {
        echo 'Docker push stage'
      }
    }

  }
}