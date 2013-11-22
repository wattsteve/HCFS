#!/bin/sh
PDIR=`pwd`

CLASSPATH="${PDIR}/lib:${PDIR}/lib/hadoop-core-1.1.2.23.jar:${PDIR}/glusterfs-0.0.1-20130418-1738.jar"

cd src
javac -d ${PDIR} -classpath ${CLASSPATH} DFSTester.java
cd $PDIR 
