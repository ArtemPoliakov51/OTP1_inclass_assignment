pipeline {
  agent any

  tools {
    maven 'Maven'
  }

  environment {
    PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

    DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
    DOCKERHUB_REPO = 'YOUR_DOCKERHUB_USER/temperature-converter'
    DOCKER_IMAGE_TAG = 'latest'
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Run Tests') {
      steps {
        bat 'mvn -B clean test'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Code Coverage') {
      steps {
        bat 'mvn -B jacoco:report'
      }
    }

    stage('Publish Coverage Report') {
      steps {
        script {
          try {
            jacoco(
              execPattern: '**/target/jacoco.exec',
              classPattern: '**/target/classes',
              sourcePattern: '**/src/main/java'
            )
          } catch (err) {
            echo "JaCoCo publish skipped (plugin missing or not configured): ${err}"
            archiveArtifacts artifacts: 'target/site/jacoco/**', allowEmptyArchive: true
          }
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          try {
            bat 'docker version'
            docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
          } catch (err) {
            echo "Docker build skipped (Docker not available on Jenkins agent): ${err}"
          }
        }
      }
    }

    stage('Push Docker Image to Docker Hub (optional)') {
      when { expression { return env.PUSH_TO_DOCKERHUB == 'true' } }
      steps {
        script {
          try {
            docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
              docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
            }
          } catch (err) {
            echo "Docker push skipped (credentials/docker not available): ${err}"
          }
        }
      }
    }
  }
}