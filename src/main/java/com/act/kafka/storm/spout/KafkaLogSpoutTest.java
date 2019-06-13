package com.act.kafka.storm.spout;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.storm.shade.org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaLogSpoutTest {

	public void sendTopicMessage(){
		Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.237.131:9092");
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.132.33:9092,172.31.132.34:9092,172.31.132.35:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DNS_gjStorm_Analyse_RE_test");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<Integer, String>(props);
        for (int i = 0; i < 10; i++) {
            Future<RecordMetadata> future = kafkaProducer.send(new ProducerRecord<Integer, String>("DNS_gjStorm_Analyse_RE", i, "lovecws" + DateFormatUtils.format(new Date(),"yyyyMMddHHmmss")));
            try {
                System.out.println(future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        kafkaProducer.close();
	}
	
	public void checkTopicExists(){
		KafkaLogSpout kafkaLogSpout=new KafkaLogSpout();
		kafkaLogSpout.checkTopicExists("DNS_gjStorm_Analyse_RE");
	}
}
