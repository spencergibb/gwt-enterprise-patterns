ant install -f build-appengine.xml \
    -Dtargetdir=./target \
    -DlocalRepository=./repo \
    -Dmvngoal=deploy:deploy-file \
    -Dmvnargs="-q -Durl=file://repo -DrepositoryId=local-maven2"