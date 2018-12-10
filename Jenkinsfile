pipeline {
  agent any
  parameters {               
  	string( name: 'USERID', defaultValue: '', description: 'Enter your userid') }       

 	 stages {               

		stage(' Login') {  
	  		steps { 
	  			echo "Active user is now ${ params.USERID}"} 
	  			}   

		stage('Checkout') {
		      steps {
		        git(url: 'http://localhost:7990/scm/merc/merc.git', branch: 'developer', poll: true)
		      } 
		  }

		stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn -Dmaven.test.failure.ignore=true install'
                sh 'mvn -B -DskipTests clean package'
            ]
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
}