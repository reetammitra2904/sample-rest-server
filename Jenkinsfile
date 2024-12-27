pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "reetammitra2904/sample-rest-server:latest"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'git@github.com:reetammitra2904/sample-rest-server.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build(DOCKER_IMAGE)
                }
            }
        }
        stage('Push Docker Image') {
//             when {
//                 expression{ return env.BRANCH_NAME == 'main' }
//             }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh "docker login -u ${USERNAME} -p ${PASSWORD}"
                    script {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Deploy to Docker Host') {
//             when {
//                 expression { return env.BRANCH_NAME == 'main' }
//             }
            steps {
                sh """
                    docker stop sample-rest-server || true
                    docker rm sample-rest-server || true
                    docker run -d -p 8090:8090 --name sample-rest-server ${DOCKER_IMAGE}
                """
            }
        }
    }
}