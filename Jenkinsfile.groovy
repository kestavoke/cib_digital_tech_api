pipeline {
    agent { any 'linux' }
    stages {

        stage ('Build') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Smoke') {
            steps {
                sh 'mvn clean install'
                publishHTML(target: [
                        reportDir  : 'target/site/serenity',
                        reportFiles: 'index.html',
                        reportName : "Smoke tests report"
                ])
            }
        }
        stage('API') {
                sh 'mvn clean install'
                publishHTML(target: [
                        reportDir  : 'target/site/serenity',
                        reportFiles: 'index.html',
                        reportName : "API tests report"
                ])
            }
        stage('Results') {
            junit testResults: '**/target/failsafe-reports/*.xml', allowEmptyResults: true
            archiveArtifacts '**target/**'
        }
    }
}