# 基础镜像
FROM openjdk:8-jdk-alpine

# 作者信息
MAINTAINER "qsmy"

# 添加一个存储空间
VOLUME /tmp

# 暴露8089端口
EXPOSE 8089

# 添加变量，如果使用dockerfile-maven-plugin，则会自动替换这里的变量内容
ARG JAR_FILE

# 往容器中添加jar包
ADD ./target/${JAR_FILE} app.jar

# 启动镜像自动运行程序
ENTRYPOINT ["java","-Xmx512m","-Xms512m","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]

#CMD ["--spring.profiles.active=test"]