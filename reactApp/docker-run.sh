#!/bin/bash
docker build -t react-client .

#docker run --rm -ti -v ${PWD}:/usr/src node:10.15.3 sh -c "cd /usr/src && npm install"
#sudo chown $USER:$USER -R node_modules/
#sudo chown $USER:$USER package-lock.json

docker run -it -v ${PWD}:/usr/src/client -p 3000:3000 --rm react-client

