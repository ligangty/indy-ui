def artifact_glob="deployments/launcher/target/*.tar.gz"
def artifact="deployments/launcher/target/*-skinny.tar.gz"
def data_artifact="deployments/launcher/target/*-data.tar.gz"

def ocp_map = '/mnt/ocp/jenkins-openshift-mappings.json'
def bc_section = 'build-configs'

def my_bc = null

pipeline {
    agent { label 'maven-jdk11' }
    stages {
        stage('Prepare') {
            steps {
                sh 'printenv'
            }
        }
        stage('Build & Functional Test') {
            when {
                expression { env.CHANGE_ID != null } // Pull request
            }
            steps {
                withEnv(['JAVA_HOME=/usr/lib/jvm/java-11-openjdk', 'JAVA_11_HOME=/usr/lib/jvm/java-11-openjdk', 'SKIP_NPM_CONFIG=false']){
                    sh 'mvn -B -V clean verify --global-toolchains toolchains.xml'
                }
            }
        }
        stage('Load OCP Mappings') {
            when {
                allOf {
                    expression { env.CHANGE_ID == null } // Not pull request
                }
            }
            steps {
                echo "Load OCP Mapping document"
                script {
                    def exists = fileExists ocp_map
                    if (exists){
                        def jsonObj = readJSON file: ocp_map
                        if (bc_section in jsonObj){
                            if (env.GIT_URL in jsonObj[bc_section]) {
                                echo "Found BC for Git repo: ${env.GIT_URL}"
                                if (env.BRANCH_NAME in jsonObj[bc_section][env.GIT_URL]) {
                                    my_bc = jsonObj[bc_section][env.GIT_URL][env.BRANCH_NAME]
                                } else {
                                    my_bc = jsonObj[bc_section][env.GIT_URL]['default']
                                }

                                echo "Using BuildConfig: ${my_bc}"
                            }
                            else {
                                echo "Git URL: ${env.GIT_URL} not found in BC mapping."
                            }
                        }
                        else {
                            "BC mapping is invalid! No ${bc_section} sub-object found!"
                        }
                    }
                    else {
                        echo "JSON configuration file not found: ${ocp_map}"
                    }

                    // if ( my_bc == null ) {
                    //     error("No valid BuildConfig reference found for Git URL: ${env.GIT_URL} with branch: ${env.BRANCH_NAME}")
                    // }
                }
            }
        }
        stage('Build') {
            when {
                allOf {
                    expression { my_bc != null }
                    expression { env.CHANGE_ID == null } // Not pull request
                }
            }
            steps {
                withEnv(['JAVA_HOME=/usr/lib/jvm/java-11-openjdk']){
                    sh 'mvn -B -V clean install -DskipNpmConfig=false --global-toolchains toolchains.xml'
                }
            }
        }
        stage('Deploy') {
            when {
                allOf {
                    expression { my_bc != null }
                    expression { env.CHANGE_ID == null } // Not pull request
                }
            }
            steps {
                echo "Deploy"
                withEnv(['JAVA_HOME=/usr/lib/jvm/java-11-openjdk', 'JAVA_11_HOME=/usr/lib/jvm/java-11-openjdk']){
                    sh 'mvn help:effective-settings -B -V -DskipTests=true -DskipNpmConfig=false deploy -e --global-toolchains toolchains.xml'
                }
            }
        }
        stage('Archive') {
            steps {
                echo "Archive"
                archiveArtifacts artifacts: "$artifact_glob", fingerprint: true
            }
        }
        stage('Build & Push Image') {
            when {
                allOf {
                    expression { my_bc != null }
                    expression { env.CHANGE_ID == null } // Not pull request
                }
            }
            steps {
                script {
                    def artifact_file = sh(script: "ls $artifact", returnStdout: true)?.trim()
                    def tarball_url = "${BUILD_URL}artifact/$artifact_file"
                    openshift.withCluster() {
                        openshift.withProject() {
                            echo "Starting image build: ${openshift.project()}:${my_bc}"
                            def bc = openshift.selector("bc", my_bc)

                            def data_artifact_file = sh(script: "ls $data_artifact", returnStdout: true)?.trim()
                            def data_tarball_url = "${BUILD_URL}artifact/$data_artifact_file"
                            
                            def buildSel = bc.startBuild("-e tarball_url=${tarball_url} -e data_tarball_url=${data_tarball_url}")
                            buildSel.logs("-f")
                        }
                    }
                }
            }
        }
    }
}
