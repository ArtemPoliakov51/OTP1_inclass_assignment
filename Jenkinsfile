pipeline {
  agent any

  tools {
    maven 'Maven3'   // В Jenkins должен быть tool с таким именем
  }

  environment {
    // Если Docker Desktop уже в PATH, эту строку можно удалить
    PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

    DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub' // credentialsId в Jenkins (если будешь пушить)
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
        jacoco(
          execPattern: '**/target/jacoco.exec',
          classPattern: '**/target/classes',
          sourcePattern: '**/src/main/java'
        )
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
        }
      }
    }

    stage('Push Docker Image to Docker Hub (optional)') {
      when { expression { return env.PUSH_TO_DOCKERHUB == 'true' } }
      steps {
        script {
          docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
            docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
          }
        }
      }
    }
  }
}