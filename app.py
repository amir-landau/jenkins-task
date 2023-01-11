from flask import Flask, jsonify
import docker

app = Flask(__name__)

"""
An alternative way is to use the -v flag which mounts the host's /var/run/docker.sock file into the container's file system, giving the container access to the host's Docker daemon, like this:
docker run -p 5000:5000 -v /var/run/docker.sock:/var/run/docker.sock app3
"""

@app.route('/')
def containers():
    client = docker.from_env()
    containers = client.containers.list(all=True)
    return jsonify([c.name for c in containers])

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
