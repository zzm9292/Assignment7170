FROM openjdk:8-jre
ARG VERSION=""
ENV JAVA_OPTS=""
ENV PARAMS=""
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone
ADD Assignment7170.jar /app.jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app.jar $PARAMS"]