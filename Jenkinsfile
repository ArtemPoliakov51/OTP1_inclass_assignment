pipeline {
  agent any

  stages {
    stage('check') {
      steps {
        echo 'Checking project'
      }
    }

    stage('build job') {
      steps {
        echo 'Building project'
      }
    }

    stage('test') {
      steps {
        echo 'Running tests'
      }
    }

    stage('Report') {
      steps {
        echo 'Generating report'
      }
    }

    stage('Build Docker Image') {
      steps {
        echo 'Building docker image'
      }
    }

    stage('Push Docker Image to Docker Hub') {
      steps {
        echo 'Pushing docker image'
      }
    }
  }
}