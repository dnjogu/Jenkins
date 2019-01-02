pipeline {
	agent any     
 	stages {                 

		stage('Checkout') {
		      steps {
		        git(url: 'https://github.com/dnjogu/Jenkins.git', branch: 'developer', poll: true)
		      } 
		  }

		stage('Build') {
            steps { [
                echo 'Building..'
                sh 'mvn -Dmaven.test.failure.ignore=true install'
                sh 'mvn -B -DskipTests clean package'
            ]
        }
    }
    	stage('Test') {
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
 