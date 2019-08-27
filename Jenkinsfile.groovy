#!groovy
node {

    stage('Smoke') {
        try {
            sh "mvn clean install"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: "Smoke tests report"
            ])
        }
    }
    stage('API') {
        try {
            sh "mvn clean install"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: "API tests report"
            ])
        }
    }
    stage('Results') {
        junit testResults: '**/target/failsafe-reports/*.xml', allowEmptyResults: true
        archiveArtifacts '**target/**'
    }
}