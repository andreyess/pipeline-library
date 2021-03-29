#!/usr/bin/env groovy

def PushToDocker(String buildNumber, String nexusCredentialsId, String registryUrl, String dockerToolName) {
/*    withDockerRegistry(credentialsId: "${nexusCredentialsId}", url: "${registryUrl}",  toolName: "${dockerToolName}"){
        sh "docker build -t check_image-${buildNumber} ."
        sh "docker tag check_image-${buildNumber} docker.akarpyza.lab.playpit.by/helloworld-akarpyza:${buildNumber}"
        sh "docker push docker.akarpyza.lab.playpit.by/helloworld-akarpyza:${buildNumber}"
    }*/
    docker.withServer('helloworld-akarpyza') {
        docker.withTool(dockerToolName){
	        docker.withRegistry(registryUrl, nexusCredentialsId) {
	            	dockerImage = docker.build("helloworld-akarpyza:${buildNumber}", "./")
	            dockerImage.push(tag)
	        }
	    }
    } 
}


def BuildAndPublishToNexus(String buildNumber, String nexusRepoUrl, String repoName, String buildVersion, String buildGroupId, String nexusCredentials) {
    nexusArtifactUploader artifacts: [[artifactId: "pipeline-akarpyza-${buildNumber}", classifier: '', file: "pipeline-akarpyza-${buildNumber}.tar.gz", type: 'tar.gz']], credentialsId: "${nexusCredentials}", groupId: "${buildGroupId}", nexusUrl: "${nexusRepoUrl}", nexusVersion: 'nexus3', protocol: 'http', repository: "${repoName}", version: "${buildVersion}"
}