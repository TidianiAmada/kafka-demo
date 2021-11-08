package com.saraya.app.kafka_producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer1 {
    public static void main(String[] args) {
        Logger logger= LoggerFactory.getLogger(Producer1.class);

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer=new KafkaProducer<String, String>(properties);
        ProducerRecord record = new ProducerRecord("first_topic", "bataaxal bu bayyiko java");

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e==null){
                    logger.info("Received new metadata : \n "+
                            " Topic "+recordMetadata.topic()+"\n"+
                            "Partition "+recordMetadata.partition()+"\n"+
                            "Offset "+recordMetadata.offset());
                }
                else {
                    logger.error("Error while producing ",e);
                }
            }
        });
        producer.flush();
        producer.close();
    }


}
