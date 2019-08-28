node {
    stage('com'){
        def mvnHome = tool name: 'Maven 3.6.1', type: 'maven'
        sh "${mvnHome}/bin/mvn -B -DskipTests clean package"
    }
        stage ('Build') {
            try {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            } catch (err) {
        }finally{}
        }

        stage('Smoke') {
            try {
                sh 'mvn install'
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
                sh "mvn install"
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
