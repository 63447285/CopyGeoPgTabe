#!/usr/bin/env bash

bin=$(dirname "${BASH_SOURCE-$0}")
bin=$(
  cd "$bin"
  pwd
)
app=atlas-restful-1.0.jar
zkUri=zookeeper://172.16.67.89:2181/atlas/plugin/DemoRestService-v1.0.0
serverPort=19870
for arg in "$@"; do
  #	echo $arg
  name=${arg%=*}
  value=${arg#*=}
  #	echo $name is $value
  case ${name} in
  serverPort) serverPort=${value} ;;
  zkUri) zkUri=${value} ;;
  *) ;;
  esac
done
echo "执行命令:nohup java -jar $bin/$app --spring.config.location=$bin/application.yml $1 > $bin/demoPlugin.log 2>&1 &"
nohup java -jar $bin/$app --spring.config.location=$bin/application.yml $1 > $bin/demoPlugin.log 2>&1 &
tail -f $bin/demoPlugin.log