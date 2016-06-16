#!/bin/sh

user="root@h4y.ru"
tomcat="/opt/apache-tomcat-8.0.35\ \(84\)"
project="mobile-booking-1.0-SNAPSHOT"
context="mobile-booking"

home="`dirname $0`"
scp $home/target/$project.war $user:
[ $? == 0 ] && {
    ssh $user "mv $project.war $tomcat/webapps/$context.war; tail -f $tomcat/logs/catalina.out;"
}
