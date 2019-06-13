package com.act.kafka.storm.wordCount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName WorldCountBolt
 * @Description TODO
 * @Autor Administrator
 * @Date 2019-6-11 14:16
 * @Version 1.0
 **/
public class WorldCountBolt implements IRichBolt {

    private OutputCollector collector;

    private HashMap<String, Long> counts = null;

    private static final AtomicInteger MESSAGE_COUNTER = new AtomicInteger();

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        System.out.println("WorldCountBolt.prepare()");
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Long count = this.counts.get(word);
        if (count == null) {
            count = 0L;//如果不存在，初始化为0
        }
        count++;//增加计数
        System.out.println("WorldCountBolt.execute ack ["+MESSAGE_COUNTER.incrementAndGet()+"]"+input.getString(0));
        this.counts.put(word, count);//存储计数
        this.collector.emit(new Values(word,count));
    }

    @Override
    public void cleanup() {
        System.out.println("WorldCountBolt.cleanup()");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //声明一个输出流，其中tuple包括了单词和对应的计数，向后发射
        //其他bolt可以订阅这个数据流进一步处理
        outputFieldsDeclarer.declare(new Fields("word","count"));
        System.out.println("WorldCountBolt.declareOutputFields()");
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        System.out.println("WorldCountBolt.getComponentConfiguration()");
        return null;
    }
}
