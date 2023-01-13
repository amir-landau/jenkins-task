from flask import Flask, render_template
import docker

app = Flask(__name__)

"""
An alternative way is to use the -v flag which mounts the host's /var/run/docker.sock file into the container's file system, giving the container access to the host's Docker daemon, like this:
docker run -p 5000:5000 --mount type=bind,source=//var/run/docker.sock,target=/var/run/docker.sock
 1372022/devops-home-assignment
"""
#docker run --name myjenkins -p 8080:8080 -p 50000:50000 -u 0 -v "C:\Users\amirl\OneDrive - CodeValue Ltd\jenkins_home:/var/jenkins_home" jenkins/jenkinss
#docker run --name myjenkins -p 8080:8080 -p 50000:50000 -u 0 -v "C:\Users\amirl\OneDrive - CodeValue Ltd\jenkins_home:/var/jenkins_home" jenkins/jenkins
#Access token: docker login -u 1372022 -p dckr_pat_lnjpVpoTSiM1kjoVCdTstLLJhoU
@app.route('/')
def containers():
    client = docker.from_env()
    containers = client.containers.list(all=True)
    return render_template("home.html", containers=containers)

if __name__ == '__main__':
    app.run(debug=True)

#docker run -p 5000:5000 -v /var/run/docker.sock:/var/run/docker.sock app3


#from flask import Flask
#
#app = Flask(__name__)
#
#@app.route("/")
#def index():
#    return "Welcome to the Data Science Learner"
#
#if __name__== "__main__":
#    app.run(debug=True)
#
