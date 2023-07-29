pipeline{
	agent any
	environment {
		DOCKERHUB_PASS = credentials('docker-pass')
	}
	stages{
		stage("Generating the Build for SWE645 student survey"){
			steps{
				script{
					checkout scm
					sh 'rm -rf *.war'
					sh 'jar -cvf survey.war *'
					// sh "docker login -u preethipantangi -p ${DOCKERHUB_PASS}"
               sh('curl -u docker login -u preethipantangi -p ${DOCKERHUB_PASS} https://hub.docker.com/')
					// sh 'docker build -t preethipantangi/survey-api .'
				}
			}
		}
		// stage("Pushing image to docker"){
		// 	steps{
		// 		script{
		// 			sh 'docker push preethipantangi/survey-api'
		// 		}
		// 	}
		// }
		// stage("Deploying to rancher"){
		// 	steps{
		// 		script{
				
		// 			sh 'kubectl rollout restart deploy deployment1 -n assignments'
		// 		}
		// 	}
		// }
	}
}