ARG ES_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/elasticsearch/elasticsearch:${ES_VERSION}

# federate tools require a valid Java home
ENV JAVA_HOME /usr/share/elasticsearch/jdk
ENV ES_JAVA_HOME /usr/share/elasticsearch/jdk

# Search Guard plugin
ARG SG_FILE_PATH
ADD ${SG_FILE_PATH} /tmp/searchguard.zip
RUN elasticsearch-plugin install --batch file:///tmp/searchguard.zip
RUN rm -rf /tmp/searchguard.zip

RUN mkdir -p /usr/share/elasticsearch/config/sgconfig /usr/share/elasticsearch/config/searchguard
RUN chmod +x /usr/share/elasticsearch/plugins/search-guard**/tools/sgadmin.sh