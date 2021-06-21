pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build Type Detection') {
            steps {
                script {
                    env.BUILD_TYPE = 'Debug'
                }
            }
        }
        stage('Code Compiling') {
            steps {
                // Compile Code Command
                sh './gradlew clean compile${BUILD_TYPE}Source'
            }
        }
        stage('Code Unit Testing') {
            steps {
                sh './gradlew clean test${BUILD_TYPE}UnitTest'
            }
        }
        stage('Apk Building') {
            steps {
                sh './gradlew clean assemble${BUILD_TYPE}'
            }
        }
        stage('Apk Storing') {
            steps {
                sh './gradlew clean assemble${BUILD_TYPE}'
                archiveArtifacts "**/*.apk"
            }
        }
    }
}