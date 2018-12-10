pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(url: 'http://localhost:7990/scm/merc/merc.git', branch: 'developer', poll: true)
      }
    }
  }
}