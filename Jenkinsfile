#!/usr/bin/env groovy

pipeline {
	agent none
	stages{                 
		stage('Checkout') {
			agent { label "master" }
		      steps {
		        git(url: 'https://github.com/dnjogu/Jenkins.git', branch: 'developer', poll: true)
		      } 
		  }
		stage('Build') {
			agent { label "master" }
            steps { [
                echo 'Building..'
                sh 'mvn -Dmaven.test.failure.ignore=true install'
                sh 'mvn -B -DskipTests clean package'
            ]
        }
    }
    	stage('Test') {

    		agent { label "master" }
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
            post {
            	success{
            		junit 'target/surefire-reports/*.xml'
            			}
            	}
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
  		}
}
	}

 