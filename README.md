# investment-broker
Investment broker

select stock - send order to broker - valid quantity on the market -  valid money in account - antifraud - Hedge validation


select stock - send order to broker -  valid money in account

KAFKA
####
you need to install bin not source
- start zookeeper: .\bin\windows\zookeeper-server-start.bat config\zookeeper.properties
- start kafka: .\bin\windows\kafka-server-start.bat config\server.properties
- create topic: .\bin\windows\kafka-topics.bat --create --topic topic_demo --bootstrap-server localhost:9092
- produce on topic: .\bin\windows\kafka-console-producer.bat --topic topic_demo --bootstrap-server localhost:9092
- consume on topic: .\bin\windows\kafka-console-consumer.bat --topic topic_demo --from-beginning --bootstrap-server localhost:9092
