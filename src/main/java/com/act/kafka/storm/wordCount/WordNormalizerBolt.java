package com.act.kafka.storm.wordCount;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.TupleUtils;
import org.apache.storm.shade.org.apache.commons.lang.math.RandomUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WordNormalizerBolt implements IRichBolt {
   
    private static final long serialVersionUID = 3644849073824009317L;
    private OutputCollector collector;
    private static final AtomicInteger MESSAGE_COUNTER = new AtomicInteger();
    
    /**
     * *bolt*从单词文件接收到文本行，并标准化它。
     * 文本行会全部转化成小写，并切分它，从中得到所有单词。
     */
    @Override
    public void execute(Tuple input) {
        if(TupleUtils.isTick(input)){
            collector.ack(input);
            System.out.println("WordNormalizerBolt.execute tick "+input.getString(0));
            return;
        }
        //使用随机boolean来模拟失败
        boolean nextBoolean = RandomUtils.nextBoolean();
//        if(nextBoolean){
        if(true){
            System.out.println("WordNormalizerBolt.execute ack ["+MESSAGE_COUNTER.incrementAndGet()+"]"+input.getString(0));
//            String sentence = input.getString(0);
            String sentence = input.getStringByField("line");
            String[] words = sentence.split(" ");
            for (String word : words) {
                word = word.trim();
                if (!word.isEmpty()) {
                    word = word.toLowerCase();
                    collector.emit(input, new Values(word));
                }
            }
            //对元组做出应答
            collector.ack(input);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            collector.fail(input);
            System.out.println("WordNormalizerBolt.execute fail "+input.getString(0));
        }
    }

    @Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        System.out.println("WordNormalizerBolt.prepare()");
        this.collector = collector;
    }

    /**
     * 这个*bolt*只会发布“word”域
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        System.out.println("WordNormalizerBolt.declareOutputFields()");
        declarer.declare(new Fields("word"));
    }


    @Override
    public Map<String, Object> getComponentConfiguration() {
        System.out.println("WordNormalizerBolt.getComponentConfiguration()");
        return null;
    }


    @Override
    public void cleanup() {
        System.out.println("WordNormalizerBolt.cleanup()");
    }
}