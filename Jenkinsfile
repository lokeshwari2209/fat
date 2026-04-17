pipeline {
  agent any
  environment {
    DOCKERHUB_CREDENTIALS = 'dockerhub-creds' // Jenkins credential ID
    KUBE_CONFIG_CREDENTIALS = 'kubeconfig-creds' // Jenkins credential ID
    IMAGE = "${env.DOCKERHUB_USER}/food-delivery"
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Test') {
      steps {
        sh 'mvn -B -DskipTests=false test package'
      }
    }
    stage('Build Docker Image') {
      steps {
        withCredentials([usernamePassword(credentialsId: env.DOCKERHUB_CREDENTIALS, usernameVariable: 'DB_USER', passwordVariable: 'DB_PASS')]) {
          sh "docker login -u $DB_USER -p $DB_PASS"
          sh "docker build -t ${env.IMAGE}:${env.GIT_COMMIT} ."
          sh "docker tag ${env.IMAGE}:${env.GIT_COMMIT} ${env.IMAGE}:latest"
          sh "docker push ${env.IMAGE}:${env.GIT_COMMIT}"
          sh "docker push ${env.IMAGE}:latest"
        }
      }
    }
    stage('Deploy to Kubernetes') {
      steps {
        withCredentials([file(credentialsId: env.KUBE_CONFIG_CREDENTIALS, variable: 'KUBECONFIG_FILE')]) {
          sh 'mkdir -p $HOME/.kube'
          sh 'cp $KUBECONFIG_FILE $HOME/.kube/config'
          sh 'kubectl apply -f deployment.yaml'
          sh 'kubectl apply -f service.yaml'
        }
      }
    }
  }
  post {
    always {
      sh 'docker logout || true'
    }
  }
}