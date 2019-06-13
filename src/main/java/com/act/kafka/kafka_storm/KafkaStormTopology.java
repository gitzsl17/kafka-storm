package com.act.kafka.kafka_storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import com.act.kafka.kafka_storm.bolt.SplitBolt;
import com.act.kafka.storm.wordCount.WordCountTopology;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import storm.kafka.*;

/**
 * @ClassName KafkaStormTopology
 * @Description TODO
 * @Autor Administrator
 * @Date 2019-6-13 15:07
 * @Version 1.0
 **/
@RestController
@RequestMapping("kafkaStormTopology")
public class KafkaStormTopology {

    public void topology(){
        TopologyBuilder builder = new TopologyBuilder();

        // 配置zk连接
        BrokerHosts hosts = new ZkHosts("192.168.237.132:2181");
        SpoutConfig spoutConfig = new SpoutConfig(hosts, "tutorialspoint", "/brokers", "IsAnalyse");
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        builder.setSpout("kafka-spout",kafkaSpout).setNumTasks(2);
        builder.setBolt("spilit-bolt",new SplitBolt(), 2).shuffleGrouping("kafka-spout").setNumTasks(2);

        //配置
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(2);
//        conf.setNumAckers(1);
//        conf.setMessageTimeoutSecs(10);
//        conf.setMaxSpoutPending(10);

        //运行拓扑,拓扑是不会关闭的,除非手动kill
        LocalCluster cluster = new LocalCluster();
        System.out.println("运行KafkaStormTopology.topology()");
        cluster.submitTopology("KafkaStormTopology", conf, builder.createTopology());
    }

    @RequestMapping(value = "/startkafkaStormTopology", method = {RequestMethod.POST,RequestMethod.GET})
    public void startWordCountTopology() throws InterruptedException {
        new KafkaStormTopology().topology();
    }

}
