#!/usr/bin/env groovy
pipeline {
    agent { node { label 'master' } }

    stages {
        stage("Checkout") {
            steps {
                git(url: 'https://github.com/dnjogu/Jenkins.git', branch: 'developer', poll: true)
            }
        }

    }

}