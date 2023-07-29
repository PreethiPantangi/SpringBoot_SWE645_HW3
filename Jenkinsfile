pipeline{
	agent any
	environment {
		DOCKERHUB_PASS = "Preethi@1998"
	}
	stages{
		stage("Generating the Build for SWE645 student survey"){
			steps{
				script{
					checkout scm
					sh 'rm -rf *.war'
					sh 'jar -cvf survey.war *'
					sh 'sudo docker login -u preethipantangi -p Preethi@1998'
					sh 'docker build -t preethipantangi/survey-api .'
				}
			}
		}
		stage("Pushing image to docker"){
			steps{
				script{
					sh 'docker push preethipantangi/survey-api'
				}
			}
		}
		// stage("Deploying to rancher"){
		// 	steps{
		// 		script{
				
		// 			sh 'kubectl rollout restart deploy deployment1 -n assignments'
		// 		}
		// 	}
		// }
	}
}