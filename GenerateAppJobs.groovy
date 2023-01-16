pipelineJob('App Pipeline') {
    definition {
        cps {
            script(readFileFromWorkspace('app/AppPipeline.groovy'))
            sandbox()
        }
    }
}


job('Nginx Job') {
    scm {
        github('amir-landau/jenkins-task', 'main')
    }
    steps {
        shell(readFileFromWorkspace('nginx/ModifyNginx.sh'))
        
        dockerBuildAndPublish {
            repositoryName('1372022/nginx')
            registryCredentials('1372022-dockerhub')
            buildContext('./nginx')
            forceTag(false)
            skipDecorate(true)
        }  
	}
}


job('Deploy And Validate') {
    scm {
        github('amir-landau/jenkins-task', 'main')
    }
    steps {
        shell(readFileFromWorkspace('DeployAndValidate.sh'))
    }
}