# developers-summit-kafka-streams
Kafka Streamsの簡単なサンプルです。  

Producerは [go-example-developers-summit](https://github.com/istyle-inc/go-example-developers-summit) をご覧ください

required Scala 2.11

## Configure
example.properties

```
# for kafka app
sample.app_id=appender

# クラスタのサーバを指定
sample.bootstrap.servers=localhost:9092

# stream処理対象のtopic指定
sample.stream.topic=sample.employees

# stream処理後の格納topic指定
sample.streamed.topic=sample.append_employees

```

## Build

```bash
$ sbt assembly
```

## Started

```bash
$ kafka-run-class -jar /path/to/developer-summit-kafka-streams-0.1.0.jar /path/to/sample.properties
# or
$ kafka-run-class jp.co.istyle.ElementAppenderStreamApplication /path/to/sample.properties
```
