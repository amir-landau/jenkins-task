pipelineJob('example-pipeline') {
    definition {
        cps {
            script(readFileFromWorkspace('app/build-app-pipeline.groovy'))
            sandbox()
        }
    }
}

job('nginx') {
    scm {
        github('amir-landau/jenkins-task', 'main')
    }
    steps {
        shell(readFileFromWorkspace('nginx/edit-nginx.sh'))
        
        dockerBuildAndPublish {
            repositoryName('1372022/nginx')
            registryCredentials('1372022-dockerhub')
            buildContext('./nginx')
            forceTag(false)
        }  
	}
      
}


job('containers-up') {
    scm {
        github('amir-landau/jenkins-task', 'master')
    }
    steps {
        shell('echo Hello World!')
        shell(readFileFromWorkspace('checkhealth.sh'))
    }
}