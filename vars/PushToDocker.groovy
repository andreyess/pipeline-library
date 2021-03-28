#!/usr/bin/env groovy

def PushToDocker(String buildNumber, String nexusCredentialsId, String registryUrl, String dockerToolName) {
    withDockerRegistry(credentialsId: "${nexusCredentialsId}", url: "${registryUrl}",  toolName: "${dockerToolName}"){
        sh "/opt/docker-18.09.9/bin/docker build -t check_image-${buildNumber} ."
        sh "/opt/docker-18.09.9/bin/docker tag check_image-${buildNumber} docker.akarpyza.lab.playpit.by/helloworld-akarpyza:${buildNumber}"
        sh "/opt/docker-18.09.9/bin/docker push docker.akarpyza.lab.playpit.by/helloworld-akarpyza:${buildNumber}"
    }
}