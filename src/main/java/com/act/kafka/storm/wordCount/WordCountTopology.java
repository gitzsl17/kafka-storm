package com.act.kafka.storm.wordCount;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("wordCountTopology")
public class WordCountTopology {

    public void topology() throws InterruptedException {
        //定义拓扑
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReaderSpout(), 1);
        builder.setBolt("word-normalizer", new WordNormalizerBolt(), 4).localOrShuffleGrouping("word-reader");  //设置4个bolt,会执行四次WordNormalizerBolt.prepare()
//        builder.setBolt("word-count", new WorldCountBolt(), 4).localOrShuffleGrouping("word-normalizer");  //设置4个bolt,会执行四次WorldCountBolt.prepare()
//        builder.setBolt("word-report", new ReportBolt(), 4).localOrShuffleGrouping("word-count");  //设置4个bolt,会执行四次ReportBolt.prepare()
        StormTopology topology = builder.createTopology();

        //配置
        Config conf = new Config();
        conf.setDebug(false);
        conf.setNumWorkers(1); 
        conf.setNumAckers(1);
        conf.setMessageTimeoutSecs(10);
        conf.setMaxSpoutPending(10);

        //运行拓扑,拓扑是不会关闭的,除非手动kill
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("WordCountTopology", conf, topology);
//        Thread.sleep(1000);
//        cluster.shutdown();
    }

    @RequestMapping(value = "/startWordCountTopology", method = {RequestMethod.POST,RequestMethod.GET})
    public void startWordCountTopology() throws InterruptedException {
        new WordCountTopology().topology();
    }
//    public static void main(String[] args) throws InterruptedException, AlreadyAliveException, InvalidTopologyException {
//        new WordCountTopology().topology();
//    }
}