#!/bin/bash
####################################
#
# Install Java and postgres
#
####################################

#Installing java
echo "Installing java"
add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java8-installer

#install postgres
apt-get install postgresql-contrib


#ensure spring does not crash:-)
apt-get install haveged -y
