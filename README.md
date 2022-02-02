# ani-research-system-test
System tests.

The tests are run in container markhobson/maven-chrome that has all the necessaries dependencies.
It contains:
-JDK 8/11/16
-Maven 3.8.1
-Chrome 91
-ChromeDriver 91

It is available on Docker Hub at: https://hub.docker.com/r/markhobson/maven-chrome/


Build container with:
docker build -t runanitests .

At build time the container will run the set of tests in order to download all dependencies needed.


Run container using:
docker run -v /local_writable_path:/tmp/test_results:Z localhost/runanitests

At every run two different tests results report files are generated in container at /tmp/test_results path. 
At the end of the run they will be copied in /local_writable_path to be easily accessible.

List all containers:
docker ps

List all containers and runs:
docker ps -a

Remove container:
docker rm [ID]


Remove all runs:
docker system prune

This will clean-up all container runs from system.

Remove docker image:
docker images 
docker images rm [image_id]

https://linuxize.com/post/how-to-remove-docker-images-containers-volumes-and-networks/