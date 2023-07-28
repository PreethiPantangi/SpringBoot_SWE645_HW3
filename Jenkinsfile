pipeline{
    agent any
    environment {
        DOCKERHUB_CREDENTIALS=credentials("DOCKERHUB_PASS")
    }

    stages {
        stage("Build") {
            steps {
                sh "rm -rf *.war"
                sh 'mvn clean package'
                sh 'docker build -t preethipantangi/survey-api .'
            }
        }
        stage('Login') {
            steps { 
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage("Push Image to docker hub") {
            steps {
                script {
                    sh "docker push preethipantangi/survey-api  "
                }
            }
        }
        stage("Deploying on K8") {
            steps {
                sh 'kubectl set image deployment/hw3-deployment1 containe1=preethipantangi/survey-api -n default'
                sh 'kubectl rollout restart deploy hw3-deployment1 -n default'
            }
        }
    }
    post {
        always {
            sh 'docker logout'
            script {
                //skip the step if context is missing
                if (getContext(hudson.FilePath)) {
                    echo "It works"
                }
            }
        }
    }
}