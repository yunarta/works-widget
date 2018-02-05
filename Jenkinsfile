properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '5'))])

stage("Checkout") {
    node("android") {
        git = checkout scm
    }
}

stage("Build") {
    node("android") {
        writeFile file: "local.properties", text: "sdk.dir=" + env.ANDROID_HOME
        sh "./gradlew assembleRelease"
    }
}

if (BRANCH_NAME.startsWith("release/")) {
    didCancelled = false
    stage("Waiting for Input") {
        try {
            timeout(time: 300, unit: 'SECONDS') { // change to a convenient timeout for you
                input(id: 'Proceed1', message: 'Upload to Bintray')
            }
        } catch (err) { // timeout reached or input false
            didCancelled = true
        }
    }

    if (!didCancelled) {
        stage("Release") {
            node("android") {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "yunarta-jcenter", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh "./gradlew bintrayUpload -PjcenterUser=${USERNAME} -PjcenterKey=${PASSWORD}"
                }
            }
        }
    }
}