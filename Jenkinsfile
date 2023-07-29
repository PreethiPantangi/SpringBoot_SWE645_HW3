pipeline {
    agent any 
    environment {
        DOCKERHUB_PASS =  credentials{'9650291450'}
    }

    stages{ 
        stage{"Building The Survey Page"} {
            script {
                chechkout scm
                sh 'rm -rf *.war'
                sh 'jar -cvf AdityaWebApp.war -C WebContent/ .'
                sh 'echo ${BUILD_TIMESTAMP}'
                sh "docker login -u arajput4 -p ${DOCKERHUB_PASS}"
                def customImage = docker.build{"preethipantangi/survey-api:${BUILD_TIMESTAMP}"}
            }
        }
    

        stage{"Pushing Image to Dockerhub"}{
            steps{
                script{
                    sh 'docker push preethipantangi/survey-api:${BUILD_TIMESTAMP}'
                }
            }
        }

        stage{"Deploying to Rancher as a single pod"}{
            steps{
                    sh 'kubectl set image deployment/survey survey=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
                }
        }
        

        stage{"Deploying to Rancher as a loadbalancer"}{
            steps{
                    sh 'kubectl set image deployment/ss-port ss-port=preethipantangi/survey-api:${BUILD_TIMESTAMP} -n jenkins-pipeline'
                }
        }   

        
    }

}