pipeline {
    agent any
    environment {
       DOCKERHUB_CREDENTIALS = credentials("1372022-dockerhub")
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/amir-landau/jenkins-task.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t 1372022/app -f app/dockerfile .'
            }
        }
        stage('Push') {
            steps {
			sh 'echo "$DOCKERHUB_CREDENTIALS_PSW" | docker login --username $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            sh 'docker push 1372022/app'
            }
        }
    }
        post { 
        always { 
            sh 'docker logout'
        }
    }
}