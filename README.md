Introduction
============

This is an template project to start developping a minecraft plugin for Spigot server.

The project is separated in two folders:
- The "plugin" folder is a sample Gradle to start working on a plugin 
- The "spigot-docker" folder is a docker image to provide a spigot server to deploy the plugin you are working on

What’s needed
=============
- A docker instance (docker desktop for windows for instance)
- A java JDK 21. Apache corretto or any other provider would do the job
- Your favorite IDE

Spigot Server Docker Image
--------------------------
First, you’ll need to build the image according to your needs. Then, you’ll need to run the docker container. 
Finally, you can start/stop the container as you need.

To build the docker image and name it "spigot", simply run the following command at the root of the spigot-docker folder:

    docker buildx build . -t <image name>:<image version> --build-arg MINECRAFT_VERSION=<minecraft version>

parameters: 
- \<image name\>: the name of the image you want to create
- \<image version\>: the version of the image to create
- \<minecraft version\>: the minecraft version you want to use. By default : 1.21.1

Here is an example to build spigot 1.21.1 and create the spigot:1.21.1 docker image

    docker buildx build . --build-arg MINECRAFT_VERSION=1.21.1 -t spigot:1.21.1

Then, to run the image, use the following command:

    docker run -p <local port>:<export port> -v <local path>:<container path> <image name>:<image version> -d --name <instance name>

arguments:
- -p \<local port\>:\<export port\>: maps the docker service running in the container on port \<export port\> 
  to \<local port\> on the local machine
- -v \<local path\>:\<container path\>: will mount \<local path\> inside the container \<container path\>. 
  Container path should be a subfolder of /mnt/server_data. For example "/mnt/server_data/plugin" folder is 
  the folder containing plugin to be deployed by the server
- \<image name\>:\<image version\>: the name/version of the image to use
- --name \<instance name\>: the name of the instance to be started. A default name is generated if not set.

Example:

    docker run -p 25565:25565 -v D:\work\dev\minecraft\tmp_data:/mnt/server_data/ spigot:1.21.1 -d --name spigot

Please note, if you run the server by mounting /mnt/server_data, it will stop after generating a bunch of files.
It’s then mandatory to accept the licence by editing the eula.txt file and changing eula=false to eula=true.
You can also edit server.properties to tune the server configuration.

Once the image has been started once, you can start/stop the container by using : 

    docker start <name>
    docker stop <name>

Replace name by the name of the container you set in the previous section, "spigot" with the example above.

Plugin development
------------------

If you are using Intellij IDEA, you can add the "Minecraft Development" plugin.

To build the plugin, go to the plugin folder and run

    gradlew build

The plugin will be generated in build/libs folder. 

To deploy it, copy the .jar file to the folder mounted in /mnt/server_data/plugins 
(D:\work\dev\minecraft\tmp_data\plugins in my example) and restart the minecraft server.

Note that docker start/stop does not restart the minecraft server.

To do so, you have to remove the container and recreating it, 
or more simply use the --rm parameter to delete it when it’s started.

Example:

    docker run --rm -it --name spigot -p 25565:25565 -v D:\work\dev\minecraft\tmp_data:/mnt/server_data spigot:1.21.1
