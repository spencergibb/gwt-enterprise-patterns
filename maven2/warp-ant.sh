#!/bin/bash

APP_NAME=warp-persist
APP_VERSION=2.0-20090214
REPO=$PWD/repo

EXEC="ant install -f build.xml \
    -Dapp.name=${APP_NAME} \
    -Dapp.version=${APP_VERSION} \
    -Dapp.distribution.location=http://warp-persist.googlecode.com/svn/trunk/warp-persist/dist/${APP_NAME}-${APP_VERSION}.zip \
    -Dartifact.suffix=-${APP_VERSION}.jar
    -Dartifacts=${APP_NAME} \
    -Dgroup.id=com.wideplay.warp.persist \
    -Dverify.path=${REPO}/com/wideplay/warp/persist/${APP_VERSION}/${APP_NAME}-${APP_VERSION}.jar \
    -Dtargetdir=./target \
    -DlocalRepository=${REPO} \
    -Dmvngoal=deploy:deploy-file \
    -Dmvnargs=\"-q -Durl=file://${REPO} -DrepositoryId=local-maven2\""
    
echo $EXEC

$EXEC