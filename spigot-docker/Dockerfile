FROM archlinux:latest

ARG MINECRAFT_VERSION=1.21.1

RUN mkdir /mnt/server_data
VOLUME /mnt/server_data

RUN ln -s /mnt/server_data /var/games/spigot

WORKDIR /var/games/spigot

ENV MINECRAFT_VERSION=${MINECRAFT_VERSION}
ENV JAVA_HOME="/opt/jdk"
ENV PATH="$PATH:$JAVA_HOME/bin"

RUN pacman -Syu --noconfirm
RUN pacman -S wget git --noconfirm

ADD https://corretto.aws/downloads/latest/amazon-corretto-21-x64-linux-jdk.tar.gz /packages/amazon-corretto-21-x64-linux-jdk.tar.gz 
ADD https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar /packages/BuildTools.jar

RUN mkdir /opt/jdk && cd /opt/jdk && tar -xzf /packages/amazon-corretto-21-x64-linux-jdk.tar.gz --strip-components=1

RUN mkdir /opt/buildtools && cd /opt/buildtools && cp /packages/BuildTools.jar . && java -jar BuildTools.jar --rev ${MINECRAFT_VERSION} --output-dir /opt/spigot/ --final-name=server.jar

COPY /conf/eula.txt .
COPY /conf/server.properties /mnt/server_data/server.properties

EXPOSE 25565
EXPOSE 5005

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","/opt/spigot/server.jar"]