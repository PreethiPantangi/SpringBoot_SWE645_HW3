pipeline {
    agent any
    tools {
        
        maven 'maven_3_8_6'
    }
    
    stages {
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/PreethiPantangi/swe645-assignment3']]])
                sh 'mvn clean install'
            }
        }
    stage('Docker Build and Tag') {
           steps {
              
              // building the docker image with name survey-ms
                sh 'docker build -t survey-ms .' 
                
                // we�re going to tag our new image and tag name is latest
                sh 'docker image tag survey-ms preethipantangi/mysurvey:latest'
               
          }
        }
    stage('Push docker image to docker hub') {
        steps {
            withCredentials([string(credentialsId: 'admin', variable: 'admin')]) {
                // providing credentials to login into dockerhub with configured credentials
            sh 'docker login -u preethipantangi -p ${admin}'
           }
           // pushing image to dockerhub
        sh 'docker image push preethipantangi/survey-ms:latest'
        }
    }
    stage('Deploying to k8s')
    {
        steps {
            sh 'kubectl set image deployment/clusterdeploy container-0=preethipantangi/survey-ms:latest -n default'
            sh 'kubectl rollout restart deploy clusterdeploy -n default'
        }
    }
 }
}
