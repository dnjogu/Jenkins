pipeline {
<<<<<<< HEAD
	agent any     
 	stages {                 

		stage('Checkout') {
=======
	agent none
 	stages {                 
		stage('Checkout') {
			agent { label "master" }
>>>>>>> reinitialised commit
		      steps {
		        git(url: 'https://github.com/dnjogu/Jenkins.git', branch: 'developer', poll: true)
		      } 
		  }

		stage('Build') {
<<<<<<< HEAD
=======
			agent { label "master" }
>>>>>>> reinitialised commit
            steps { [
                echo 'Building..'
                sh 'mvn -Dmaven.test.failure.ignore=true install'
                sh 'mvn -B -DskipTests clean package'
            ]
        }
    }
    	stage('Test') {
<<<<<<< HEAD
=======
    		agent { label "master" }
>>>>>>> reinitialised commit
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
<<<<<<< HEAD
}
=======
	}
>>>>>>> reinitialised commit

}
 