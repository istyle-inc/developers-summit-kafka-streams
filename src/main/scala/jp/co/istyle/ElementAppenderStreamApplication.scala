package jp.co.istyle

import java.util.Properties

import org.apache.kafka.common.serialization._
import org.apache.kafka.streams._
import org.apache.kafka.streams.kstream.{KStream, ValueMapper}

object ElementAppenderStreamApplication {
  def main(args: Array[String]) {

    println("Kafka Streams Sample.")

    val config: Properties = {
      val prop = new Properties()
      // 引数で指定した*.properties読み込み
      prop.load(new java.io.FileInputStream(args(0).toString))
      prop.put(StreamsConfig.APPLICATION_ID_CONFIG, prop.getProperty("sample.app_id"))
      prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, prop.getProperty("sample.bootstrap.servers"))
      prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
      prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)
      // exactly once
      prop.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE)
      prop
    }

    val ft: String = config.getProperty("sample.stream.topic")
    val tt: String = config.getProperty("sample.streamed.topic")

    println("stream topic: from " + ft)
    println("to " + tt)

    val stringSerde: Serde[String] = Serdes.String()
    val builder: StreamsBuilder = new StreamsBuilder
    val rawStream: KStream[String, String] = builder.stream(ft)
    val mapStream: KStream[String, String] = rawStream.mapValues(new ValueMapper[String, String] {
      override def apply(value: String): String = new ElementAppender(value).append()
    })
    mapStream.to(stringSerde, stringSerde, tt)
    val streams: KafkaStreams = new KafkaStreams(builder.build(), config)
    streams.start()
  }
}
