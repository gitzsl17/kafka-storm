package com.act.kafka.storm.wordCount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ReportBolt
 * @Description TODO
 * @Autor Administrator
 * @Date 2019-6-11 14:35
 * @Version 1.0
 **/
public class ReportBolt implements IRichBolt {

    private HashMap<String, Long> counts = null;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counts = new HashMap<String, Long>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Long count = tuple.getLongByField("count");
        this.counts.put(word, count);

        //实时输出
        System.out.println("结果:"+this.counts);
    }

    @Override
    public void cleanup() {
        System.out.println("---------- FINAL COUNTS -----------");

        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(this.counts.keySet());
        Collections.sort(keys);
        for(String key : keys){
            System.out.println(key + " : " + this.counts.get(key));
        }

        System.out.println("----------------------------");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
