#!/bin/bash
set -ex

ip=ip


#mkdir -p docker/java

cd ../
mvn clean package -P prod
cd file
cp ../backend-system/target/project-URL-shortcut.jar ./
scp -r ./* ubuntu@$ip:~/backend/url-shortcut

# docker rmi $(docker images | grep "url_shortcut" | awk '{print $3}')

ssh -T ubuntu@$ip  << remotessh
mkdir -p ~/backend/url-shortcut/log
cd ~/backend/url-shortcut
docker stop urlshortcut
docker rm  urlshortcut
docker rmi \$(docker images | grep "urlshortcut" | awk '{print \$3}')
docker build -t urlshortcut:0.1 .
docker run -d \\
  --name urlshortcut \\
  -p 8001:8001 \\
  --network test-net \\
  urlshortcut:0.1 
docker ps
exit
remotessh

rm ./project-URL-shortcut.jar