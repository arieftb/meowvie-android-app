pipeline {
    agent any
    environment {
        APP_NAME = "MeowVie-Android"
    }
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
        stage('Aok Storing') {
            steps {
                archiveArtifacts "**/${APP_NAME}-${BUILD_TYPE}-${TAG_TIMESTAMP}.apk"
            }
        }
    }
}