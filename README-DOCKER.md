## HADOOP DOCKER 

This documentation contains instructs on setting up a single instance hadoop docker application.

We will use the help of [docker-compose](https://docs.docker.com/compose/) in order to spawn the image.

### Things to note
- The docker-compose file is located at the root folder (docker-compose.yml) 
- Configurations are being picked up form hadoop.env file

### Run the Image 

To run the hadoop cluster, we will run(in detatched mode):

``docker-compose up -d``

or build and bring up container:

``docker-compose -f "docker-compose.yaml" up -d --build``


To view the yarn resource manager, go to this url:

``http://localhost:8088``