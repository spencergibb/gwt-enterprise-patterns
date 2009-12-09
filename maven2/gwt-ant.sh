#!/bin/bash

APP_NAME=gwt
APP_VERSION=2.0.0
REPO=$PWD/repo

ant install -f build.xml \
    -Dapp.name=$APP_NAME \
    -Dapp.version=$APP_VERSION \
    -Dapp.distribution.location=http://google-web-toolkit.googlecode.com/files/${APP_NAME}-${APP_VERSION}.zip \
    -Dartifacts=gwt-dev,gwt-servlet,gwt-soyc-vis,gwt-user,gwt-api-checker \
    -Dgroup.id=com.google.gwt \
    -Dverify.path=${REPO}/com/google/gwt/gwt-dev/${APP_VERSION}/gwt-dev-${APP_VERSION}.jar \
    -Dtargetdir=./target \
    -DlocalRepository=${REPO} \
    -Dmvngoal=deploy:deploy-file \
    -Dmvnargs="-q -Durl=file://${REPO} -DrepositoryId=local-maven2"
