#!/bin/bash

cat allt_priodb.csv | while read line
do
   #substance=$(echo $line | cut -d "'" -f1 | sed -e 's/\ \ \*//g')
   #echo "Line: $line"
   #echo $substance
   substance=$(echo -e "$line" | tr '\t' '|' | cut -d '|' -f1|tr -d '"')
   CAS=$(echo -e "$line" | tr '\t' '|' | cut -d '|' -f2|tr -d "'")
   EG=$(echo -e "$line" | tr '\t' '|' | cut -d '|' -f3|tr -d "'")
   priolevel=$(echo -e "$line" | tr '\t' '|' | cut -d '|' -f4)
   criteria=$(echo -e "$line" | tr '\t' '|' | cut -d '|' -f5|tr -d '"')
   #echo "Kriteria: $criteria priolev: $priolevel egnr: $EG subst: $substance cas: $CAS"
   echo "INSERT INTO chemicals(substance, CAS, EG, prio_level, criteria) VALUES('$substance','$CAS','$EG','$priolevel','$criteria');"
 done
