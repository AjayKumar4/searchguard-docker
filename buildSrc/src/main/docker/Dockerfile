ARG ES_VERSION
ARG ES_BASE_IMAGE

# https://www.docker.elastic.co/
FROM docker.elastic.co/elasticsearch/elasticsearch-oss:${ES_VERSION}

# Search Guard plugin
ARG SG_FILE_PATH
ADD ${SG_FILE_PATH} /tmp/searchguard.zip
RUN elasticsearch-plugin install --batch file:///tmp/searchguard.zip
RUN rm -rf /tmp/searchguard.zip

ENV ES_CLUSTER_NAME=searchguard-distribution
CMD ["/bin/bash", "-c", "bin/elasticsearch -Enetwork.host=0.0.0.0 -Ecluster.name=$ES_CLUSTER_NAME -p es.pid"]