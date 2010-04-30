#!/bin/bash

APP_NAME=gwt-ent
APP_VERSION=0.7.1
ARTIFACTS=gwtent
GROUPID=com.google.code.gwt-ent
GROUPDIR=com/google/code/gwt-ent
REPO=$PWD/repo
BASEURL=http://gwt-ent.googlecode.com/files/

EXEC="ant install -f build.xml \
    -Dapp.name=${APP_NAME} \
    -Dapp.version=${APP_VERSION} \
    -Dapp.distribution.location=${BASEURL}/${APP_NAME}-${APP_VERSION}.zip \
    -Dartifacts=${ARTIFACTS} \
    -Dgroup.id=${GROUPID} \
    -Dverify.path=${REPO}/${GROUPDIR}/${APP_VERSION}/${APP_NAME}-${APP_VERSION}.jar \
    -DdistroInternalPath=\"*\" \
    -Dtargetdir=./target \
    -DlocalRepository=${REPO} \
    -Dmvngoal=deploy:deploy-file \
    -Dmvnargs=\"-q -Durl=file://${REPO} -DrepositoryId=local-maven2\""
    
echo $EXEC

$EXEC
