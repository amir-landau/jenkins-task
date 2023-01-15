pipelineJob('example-pipeline') {
    definition {
        cps {
            script("""
                pipeline {
                    agent any
                    environment {
                       DOCKERHUB_CREDENTIALS = credentials("1372022-dockerhub")
                    }
                    stages {
                        stage('Checkout') {
                            steps {
                                git url: 'https://github.com/amir-landau/jenkins-task.git', branch: 'master'
                            }
                        }
                        stage('Build') {
                            steps {
                                sh 'docker build -t 1372022/app:latest -f app/dockerfile .'
                            }
                        }
                        stage('Push') {
                            steps {
								sh 'echo "\$DOCKERHUB_CREDENTIALS_PSW" | docker login --username \$DOCKERHUB_CREDENTIALS_USR --password-stdin'
    							#sh 'docker login -u \$DOCKERHUB_CREDENTIALS_USR -p \$DOCKERHUB_CREDENTIALS_PSW'
                                sh 'docker push 1372022/app:latest'
                            }
                        }
                    }
                }
            """)
            sandbox()
        }
    }
}

job('nginx') {
    scm {
        github('amir-landau/jenkins-task', 'master')
    }
    steps {
        shell('''
cat >> nginx/dockerfile <<EOF
RUN echo 'server { \\n \\\\
    listen 80; \\n \\\\
    location / { \\n \\\\
        proxy_set_header X-Real-IP \\$remote_addr; \\n \\\\
        proxy_pass http://app:5000; \\n \\\\
    } \\n \\\\
}' > /etc/nginx/conf.d/default.conf
EOF

cat nginx/dockerfile

docker build -t 1372022/nginx -f nginx/dockerfile .
docker login -u 1372022 -p dckr_pat_lnjpVpoTSiM1kjoVCdTstLLJhoU
docker push 1372022/nginx
        ''')
	}
}


job('containers-up') {
    scm {
        github('amir-landau/jenkins-task', 'master')
    }
    steps {
                
        shell('docker-compose up -d')
        readFileFromWorkspace('checkhealth.sh')	}
}