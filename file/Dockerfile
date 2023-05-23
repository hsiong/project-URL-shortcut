#依赖java8ub
FROM openjdk:17
#创建者
MAINTAINER ynfy
#暴露8848
EXPOSE 8001
#不确定jar名字可以写*.jar 即为所以
ADD ./project-URL-shortcut.jar ~/shortcut.jar
#挂载目录
VOLUME /root
#生产环境启动 可以写#容器已启动 默认运行命令 java -jar app.jar
# ENTRYPOINT ["java","-jar","/dkyedge.jar","--spring.profiles.active=prod"]
ENTRYPOINT ["java","-jar","~/shortcut.jar","-Xms32m -Xmx256m -Xmn64m  -Xss32k --spring.profiles.active=prod", ">/tmp/java/out.file", "&"]