pipeline{
    agent any
    environment {
        def BUILDVERSION = sh(script: "echo `date +%s`", returnStdout: true).trim()
    }

    stages {
        stage("Building Student Survey Form page") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-pass', passwordVariable: 'C_PASS', usernameVariable: 'C_USER')]) {
                        checkout scm
                        sh "rm -rf *.war"
                        sh 'jar -cvf survey.war *'
                        sh 'echo ${BUILDVERSION}'
                        println(C_PASS+" "+C_USER)
                        sh 'docker login -u preethipantangi -p ${C_PASS}'
                        sh 'docker build -t preethipantangi/surveyapi:${BUILDVERSION} .'
                    }
                }
            }
        }
        stage("Pushing Image to DockerHub") {
            steps {
                script {
                    sh "docker push preethipantangi/surveyapi:${BUILDVERSION}"
                }
            }
        }
        stage("Deploying to Rancher as single pod") {
            steps {
               sh 'kubectl --kubeconfig=config get nodes'
               //  sh 'kubectl --kubeconfig=config set image deployment/survey-pipeline survey-pipeline=preethipantangi/surveyapi:${BUILDVERSION} -n jenkins-pipeline'
            }
        }
        stage("Deploying to Rancher as with load balancer") {
            steps {
                sh 'kubectl set image deployment/hw3-deployment1-loadbalancer hw3-deployment1-loadbalancer=preethipantangi/surveyapi:${BUILDVERSION} -n jenkins-pipeline'
            }
        }
    }
}