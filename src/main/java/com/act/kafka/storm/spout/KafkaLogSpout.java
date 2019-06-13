package com.act.kafka.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import com.act.kafka.storm.util.PropertiesUtils;
import com.alibaba.fastjson.JSON;
import kafka.admin.AdminUtils;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.security.JaasUtils;
import org.apache.log4j.Logger;
import org.apache.storm.shade.org.apache.commons.lang.time.DateFormatUtils;

import java.util.*;

public class KafkaLogSpout extends BaseRichSpout {

	private static final long serialVersionUID = 1L;
	private static final Logger log=Logger.getLogger(KafkaLogSpout.class);

	private SpoutOutputCollector collector = null;
    private KafkaConsumer<Integer,String> consumer = null;
    private String brokerServices = "127.0.0.1:9092";
    private String topic = "DNS_Storm_Analyse";
    private String groupId = "DnsAnalyse";

    public KafkaLogSpout(){
    }

	@Override
    public void open(final Map conf, final TopologyContext context, final SpoutOutputCollector collector) {
    	//checkTopicExists(topic);
        this.collector = collector;

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerServices);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "5242880");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<Integer, String>(props);
        consumer.subscribe(Arrays.asList(topic));
        System.out.println("brokerServices="+brokerServices+";topic="+topic+";groupId="+groupId);
    }

    /**
     * 检测主题是否存在
     * @param topic
     */
    public void checkTopicExists(String topic) {
    	String zookeeperServers = "192.168.237.131:2181";
    	ZkUtils zkUtils = ZkUtils.apply(zookeeperServers, 30000, 30000, JaasUtils.isZkSecurityEnabled());
    	//获取主题信息
    	Properties fetchEntityConfig = AdminUtils.fetchEntityConfig(zkUtils,ConfigType.Topic(),topic);
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	//如果主题不存在 则创建
    	if(fetchEntityConfig==null){
    		log.info("topic ["+topic+"] not exists! program will auto create....");
    	}else{
    		for(Map.Entry<Object,Object> entry:fetchEntityConfig.entrySet()){
    			log.info(entry.getKey()+" "+entry.getValue());
    		}
    		log.info("listen topic ["+topic+"] info "+JSON.toJSONString(fetchEntityConfig));
    	}
    	zkUtils.close();
	}

	@Override
    public void nextTuple() {
        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        for (ConsumerRecord<Integer, String> record : records) {
        	long offset = record.offset();
        	int partition = record.partition();
            System.out.println("record:"+DateFormatUtils.format(new Date(), "yyyyMmddHHmmss")+":"+record.value()+" offset:"+offset+" partition:"+partition);
            collector.emit(Arrays.asList((Object)record.value()), UUID.randomUUID().toString().replace("-", ""));
        }
    }

    @Override
    public void ack(final Object msgId) {
        super.ack(msgId);
    }

    @Override
    public void fail(final Object msgId) {
        System.out.println("fail:" + DateFormatUtils.format(new Date(), "yyyyMmddHHmmss")+msgId);
        super.fail(msgId);
    }

    @Override
    public void declareOutputFields(final OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("log"));
    }

    @Override
    public void close() {
        consumer.close();
    }
}
