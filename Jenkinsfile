// pipeline{
// 	agent any
// 	environment {
// 		DOCKERHUB_PASS = "Preethi@1998"
// 	}
// 	stages{
// 		stage("Generating the Build for SWE645 student survey"){
// 			steps{
// 				script{
// 					checkout scm
// 					sh 'rm -rf *.war'
// 					sh 'jar -cvf SWE645Assignment2.war *'
// 					sh 'docker login -u preethipantangi -p Preethi@1998'
// 					sh 'docker build -t preethipantangi/survey-api .'
// 				}
// 			}
// 		}
// 		stage("Pushing image to docker"){
// 			steps{
// 				script{
// 					sh 'docker push preethipantangi/survey-api'
// 				}
// 			}
// 		}
// 		// stage("Deploying to rancher"){
// 		// 	steps{
// 		// 		script{
				
// 		// 			sh 'kubectl rollout restart deploy deployment1 -n assignments'
// 		// 		}
// 		// 	}
// 		// }
// 	}
// }


pipeline {
    agent any

    stages {
      //   stage('Build') {
      //       steps {
      //           // Your build steps here
      //       }
      //   }

        stage('Push Docker Image') {
            environment {
                // Set your Docker credentials as environment variables
                DOCKER_HUB_USERNAME = credentials('preethipantangi')
                DOCKER_HUB_PASSWORD = credentials('Preethi@1998')
            }
            steps {
                script {
                    // Log in to Docker registry using Docker CLI
                    sh "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"

                    // Build and tag your Docker image (replace "your-image-name" and "your-tag" with appropriate names)
                    sh "docker build -t preethipantangi/survey-api ."

                    // Push the Docker image to the registry
                    sh "docker push preethipantangi/survey-api"
                }
            }
        }

      //   stage('Deploy') {
      //       steps {
      //           // Your deployment steps here, if any
      //       }
      //   }
    }

    post {
        always {
            // Clean up by logging out of Docker
            sh "docker logout"
        }
    }
}
