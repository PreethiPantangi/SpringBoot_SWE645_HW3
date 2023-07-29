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
                    sh 'echo Logging to docker'
                    // sh('curl -u docker login -u preethipantangi -p ${DOCKERHUB_PASS}')
                }
            }
        }
        // stage("Pushing Image to DockerHub") {
        //     steps {
        //         script {
        //             sh "curl -u docker push preethipantangi/survey-api:$BUILD_TIMESTAMP"
        //         }
        //     }
        // }
        // stage("Deploying to Rancher as single pod") {
        //     steps {
        //         sh 'kubectl set image deployment/hw3-pipeline hw3-pipeline=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
        //     }
        // }
        // stage("Deploying to Rancher with load balancer") {
        //     steps {
        //         sh 'kubectl set image deployment/hw3-deployment1-loadbalancer hw3-deployment1-loadbalancer=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
        //     }
        // }
    }
}