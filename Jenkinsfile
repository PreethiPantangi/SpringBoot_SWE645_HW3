// pipeline{
//     agent any
//     environment {
//         DOCKERHUB_PASS = credentials('DOCKERHUB_PASS')
//     }

//     stages {
//         stage("Building Student Survey Mircoservices") {
//             steps {
//                 script {
//                     checkout scm
//                     sh "rm -rf *.war"
//                     sh 'jar -cvf survey.war *'
//                     sh 'echo ${BUILDVERSION}'
//                     sh "docker login -u preethipantangi -p ${DOCKERHUB_PASS}"
//                     def customImage = docker.build("preethipantangi/survey-api:${BUILD_TIMESTAMP}")
//                 }
//             }
//         }
//         stage("Pushing Image to DockerHub") {
//             steps {
//                 script {
//                     sh "docker push preethipantangi/survey-api:${BUILD_TIMESTAMP}"
//                 }
//             }
//         }
//         stage("Deploying to Rancher as single pod") {
//             steps {
//                 sh 'kubectl set image deployment/hw3-pipeline hw3-pipeline=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
//             }
//         }
//         stage("Deploying to Rancher with load balancer") {
//             steps {
//                 sh 'kubectl set image deployment/hw3-deployment1-loadbalancer hw3-deployment1-loadbalancer=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
//             }
//         }
//     }
// }

pipeline{

	agent {label 'linux'}

	environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
	}

	stages {
	    
	    stage('gitclone') {

			steps {
				git 'https://github.com/shazforiot/nodeapp_test.git'
			}
		}

		stage('Build') {

			steps {
				sh 'docker build -t thetips4you/nodeapp_test:latest .'
			}
		}

		stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				sh 'docker push thetips4you/nodeapp_test:latest'
			}
		}
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}