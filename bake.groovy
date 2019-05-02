pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/AGES-PUCRS/mase-auth-service.git'
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t mase/auth-service .'
            }
        }
    }
}