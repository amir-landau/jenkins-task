from flask import Flask, render_template
import docker

app = Flask(__name__)

@app.route('/')
def containers():
    client = docker.from_env()
    containers = client.containers.list(all=True)
    return render_template("home.html", containers=containers)

if __name__ == '__main__':
    app.run(debug=True)
