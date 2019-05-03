# base image
FROM node:10.15.3-jessie

# set working directory
RUN mkdir /usr/src/client
WORKDIR /usr/src/client
VOLUME node_modules
# add `/usr/src/app/node_modules/.bin` to $PATH
ENV PATH /usr/src/client/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package*.json /usr/src/client/package.json
RUN npm install
RUN npm install react-scripts@1.1.1 -g

# start app
CMD ["npm", "start"]

