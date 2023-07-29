pipeline {
    agent any
    environment {
        DOCKERHUB_PASS = credentials('docker-password')
    }

    stages {
        stage("Building Student Survey Mircoservices") {
            steps {
                script {
                    checkout scm
                    sh "rm -rf *.war"
                    sh 'jar -cvf survey.war *'
                    sh 'echo ${BUILDVERSION}'
                    // sh('curl -u docker login -u preethipantangi -p $DOCKERHUB_PASS')
                    // def customImage = docker.build("preethipantangi/survey-api:${BUILD_TIMESTAMP}")
                }
                script {
                    // Docker login
                    docker.withRegistry('https://index.docker.io/v1/', 'DOCKERHUB_PASS') {
                        // The 'docker_credentials_id' should be an ID of the Jenkins credentials containing Docker Hub username and password/token.
                        
                        // Build your Docker image here
                        sh 'docker build -t preethipantangi/survey-api:${BUILD_TIMESTAMP} .'
                        
                        // Push the Docker image to the registry
                        sh 'docker push preethipantangi/survey-api:${BUILD_TIMESTAMP}'
                    }
                }
            }
        }
        stage("Pushing Image to DockerHub") {
            steps {
                script {
                    sh "curl -u docker push preethipantangi/survey-api:$BUILD_TIMESTAMP"
                }
            }
        }
        stage("Deploying to Rancher as single pod") {
            steps {
                sh 'kubectl set image deployment/hw3-pipeline hw3-pipeline=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
            }
        }
        stage("Deploying to Rancher with load balancer") {
            steps {
                sh 'kubectl set image deployment/hw3-deployment1-loadbalancer hw3-deployment1-loadbalancer=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
            }
        }
    }
}