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
                        sh 'docker build -t preethipantangi/survey-api:${BUILDVERSION} .'
                    }
                }
            }
        }
        stage("Pushing Image to DockerHub") {
            steps {
                script {
                    sh "docker push preethipantangi/survey-api:${BUILDVERSION}"
                }
            }
        }
        stage("Deploying to Rancher") {
            steps {
                sh 'kubectl set image deployment/hw3-deployment container-0=preethipantangi/survey-api:${BUILDVERSION} -n hw3-namespace'
                sh 'kubectl rollout restart deploy hw3-deployment -n hw3-namespace'
            }
        }
    }
}