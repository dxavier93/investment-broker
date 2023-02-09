# Investment-broker


### TO-DO list
- Cache database for event history
- How other projects organize directories
- add some number format for account Model (like "xxx-xxx-xxx")
- search for String.format in java 8++
- separate event controller (separate event insert/read)(maybe a new service for it)
- add github code coverage
- Completable future for kafka
- 
#### Initial idea
- select stock - send order to broker - valid quantity on the market -  valid money in account - antifraud - Hedge validation

#### Actual idea
- {GOOD} - [Send order] -- Stock order ->  valid account [OK]   -> stock order confirm -> [Confirm order]
- {BAD} -  [Send order] -- Stock order ->  valid account [FAIL] -> stock order denied  -> [Cancel order]

KAFKA
####
you need to install bin not source
- start zookeeper: .\bin\windows\zookeeper-server-start.bat config\zookeeper.properties
- start kafka: .\bin\windows\kafka-server-start.bat config\server.properties
- create topic: .\bin\windows\kafka-topics.bat --create --topic topic_demo --bootstrap-server localhost:9092
- produce on topic: .\bin\windows\kafka-console-producer.bat --topic topic_demo --bootstrap-server localhost:9092
- consume on topic: .\bin\windows\kafka-console-consumer.bat --topic topic_demo --from-beginning --bootstrap-server localhost:9092
