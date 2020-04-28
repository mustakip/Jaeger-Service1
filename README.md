# Jaeger spike to publish traces on kafka and elasticsearch(backend storage) simultaneously

This repo consists of a simple application with 2 endpoints and a docker compose file to run jaeger locally.  

###Instructions to run:
  * `docker-compose -f docker-compose/docker-compose.yml up` brings up the Jaeger backend, Kafka, Elasticsearch, and Kibana
  * `gradle bootRun` brings up the application on [http://localhost:8085](http://localhost:8085) which is instrumented with open tracing libraries
  * Traces being send from the application can be found in Jager UI running on [http://localhost:16686](http://localhost:16686/)
  * The traces are also available on kibana running on [http://localhost:5601](http://localhost:5601)
  * The traces are published to kafka as well running on port 9092 as can be consumed from the same
  
  
##Findings:
  * Currently the traces are being send to kafka and elasticsearch from Jaeger collector independently. 
  * Another approach is to publish traces on kafka as intermittent storage using jaeger collector but we need to consume the traces from 
    kafka and save in elasticsearch using jaeger ingester.
  * It’s possible to use multiple storage types at the same time by providing a comma-separated list of valid types to the SPAN_STORAGE_TYPE
    environment variable in collector configuration. It’s important to note that all listed storage types are used for writing, but only the
    first type in the list will be used for reading and archiving. [reference](https://www.jaegertracing.io/docs/1.17/deployment/)
    

