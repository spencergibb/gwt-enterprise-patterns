ant install -f build-gwt.xml \
    -Dtargetdir=./../../../target \
    -DlocalRepository=./repo \
    -Dmvngoal=deploy:deploy-file \
    -Dmvnargs="-q -Durl=file://repo -DrepositoryId=local-maven2"