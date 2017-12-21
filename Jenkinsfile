pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                withMaven(jdk: 'Current JDK 8',
                        maven: 'Current Maven 3',
                        mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/',
                        globalMavenSettingsConfig: '9a4daf6d-06dd-434a-83cc-9ba9bd2326fc') {
                    sh "mvn clean install checkstyle:checkstyle findbugs:findbugs pmd:pmd"
                }
                checkstyle canComputeNew: false, pattern: '**/checkstyle-result.xml'
                findbugs canComputeNew: false, pattern: '**/target/findbugsXml.xml'
                jacoco exclusionPattern: '**/jaxb/*.class'
                pmd canComputeNew: false, pattern: '**/pmd.xml'
            }
        }
        stage('Document and Deploy') {
            // run this stage only when on master in the original repository
            when {
                environment name: 'CHANGE_FORK', value: ''
                expression { GIT_URL ==~ 'https://github.com/sw4j-org/.*' }
            }
            parallel {
                stage('Deploy') {
                    steps {
                        withMaven(jdk: 'Current JDK 8',
                                maven: 'Current Maven 3',
                                mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/',
                                globalMavenSettingsConfig: '03c863c2-c19c-4ed5-bc3a-7650b8f73ecf') {
                            sh "mvn deploy"
                        }
                    }
                }
                stage('Site') {
                    steps {
                        sshagent (credentials: ['4cab8b17-578f-49fc-908b-0e318625d63b']) {
                            withMaven(jdk: 'Current JDK 8',
                                    maven: 'Current Maven 3',
                                    mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/',
                                    globalMavenSettingsConfig: '03c863c2-c19c-4ed5-bc3a-7650b8f73ecf') {
                                sh "mvn post-site scm-publish:publish-scm"
                            }
                        }
                    }
                }
            }
        }
    }
}
