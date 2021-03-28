#!/usr/bin/env groovy

def BuildAndPublishToNexus(String buildNumber, String nexusRepoUrl, String repoName, String buildVersion, String buildGroupId, String nexusCredentials) {
    nexusArtifactUploader artifacts: [[artifactId: "pipeline-akarpyza-${buildNumber}", classifier: '', file: "pipeline-akarpyza-${buildNumber}.tar.gz", type: 'tar.gz']], credentialsId: "${nexusCredentials}", groupId: "${buildGroupId}", nexusUrl: "${nexusRepoUrl}", nexusVersion: 'nexus3', protocol: 'http', repository: "${repoName}", version: "${buildVersion}"
}