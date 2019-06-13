package com.act.kafka.kafka_storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import java.util.Map;

/**
 * @ClassName SplitBolt
 * @Description TODO
 * @Autor Administrator
 * @Date 2019-6-13 15:28
 * @Version 1.0
 **/
public class SplitBolt implements IRichBolt {

    private TopologyContext context;
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.context = context;
        this.collector = collector;

    }

    @Override
    public void execute(Tuple tuple) {
        String line = tuple.getString(0);
        System.out.println("@@@@@@@@@@" + line);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word", "count"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
