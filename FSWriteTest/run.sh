#!/bin/sh
PDIR=`pwd`

CLASSPATH=".:${PDIR}/lib:${PDIR}/lib/hadoop-core-1.1.2.23.jar:${PDIR}/lib/glusterfs-0.0.1-20130418-1738.jar:${PDIR}/lib/log4j-1.2.15.jar:${PDIR}/lib/commons-logging-1.1.1.jar:${PDIR}/lib/commons-logging-api-1.0.4.jar"

# echo "Using classpath: ${CLASSPATH}"
java -classpath ${CLASSPATH} DFSTester $0 $1 $2 $3 $4 $5 $6 $7  
cd $PDIR 
