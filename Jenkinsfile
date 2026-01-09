pipeline {
    agent any

    tools {
        maven 'Maven-LOCAL'
        jdk 'JDK-Local'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/madhava4747/api-automation.git',
                    credentialsId: 'github-creds'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml'
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat '''
                if exist target\\allure-report rmdir /s /q target\\allure-report
                if exist target\\allure-results (
                    allure generate target\\allure-results --clean -o target\\allure-report
                ) else (
                    echo "Allure results not found"
                )
                '''
            }
        }
    }

    post {
        always {
            script {
                if (fileExists('target/allure-report/index.html')) {
                    publishHTML(target: [
                        reportName : 'API Automation - Allure Report',
                        reportDir  : 'target/allure-report',
                        reportFiles: 'index.html',
                        keepAll    : true,
                        alwaysLinkToLastBuild: true,
                        allowMissing: false
                    ])
                } else {
                    echo 'Allure report not generated'
                }
            }
        }

        success {
            echo 'API Automation Build SUCCESS'
        }

        failure {
            echo 'API Automation Build FAILED'
        }
    }
}
