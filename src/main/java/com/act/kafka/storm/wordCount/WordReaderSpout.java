package com.act.kafka.storm.wordCount;


import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @version 1.0-SNAPSHOT
 * @Description WordReaderSpout(Spout)，用于从外部数据源words.txt中获取数据
 */
public class WordReaderSpout implements IRichSpout {

	private static final long serialVersionUID = -6964734938941608521L;
	private SpoutOutputCollector collector;

    private static final Map<String, Values> MESSAGE_MAP = new ConcurrentHashMap<String, Values>();
    private static final AtomicInteger MESSAGE_COUNTER = new AtomicInteger();

    private String[] sentences = {
            "my name is soul",
            "im a boy",
            "i have a dog",
            "my dog has fleas",
            "my girl friend is beautiful"
    };
    private int index=0;

    /**
     * Storm调用这个方法，向输出的collector发出tuple,
     * 在这里,这个方法做的唯一一件事情就是分发文件中的文本行
     */
    @Override
    public void nextTuple() {
        try {
//        	if(MESSAGE_COUNTER.incrementAndGet()>100){
//                return;
//            }
            this.collector.emit(new Values(sentences[index]));
            System.out.println("WordReaderSpout.emit msgId["+index+"],value["+sentences[index]+"]");
            index++;
            if (index>=sentences.length) {
                index=0;
            }
//            String uuid = UUID.randomUUID().toString().replace("-", "");
//            Values values = new Values(String.valueOf(MESSAGE_COUNTER.get()));
//            this.collector.emit(values, uuid);
//            MESSAGE_MAP.put(uuid, values);
//            System.out.println("WordReaderSpout.emit msgId["+uuid+"],value["+values.get(0)+"]");
            //TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当Spout被创建之后，这个方法会被禁用
     */
    @Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        System.out.println("WordReaderSpout.open");
        this.collector = collector;
    }

    /**
     * 声明数据格式，即输出的一个Tuple中，包含几个字段
     * declareOutputFields是在IComponent接口中定义的，所有Storm的组件（spout和bolt）都必须实现这个接口
     * 用于告诉Storm流组件将会发出那些数据流，每个流的tuple将包含的字段
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        System.out.println("WordReaderSpout.declareOutputFields");
        declarer.declare(new Fields("line"));
    }

    @Override
    public void activate() {
        System.out.println("WordReaderSpout.activate");
    }

    @Override
    public void deactivate() {
        System.out.println("WordReaderSpout.deactivate");
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        System.out.println("WordReaderSpout.getComponentConfiguration");
        return null;
    }

    /**
     * 当一个Tuple处理成功时，会调用这个方法
     */
    @Override
    public void ack(Object msgId) {
        MESSAGE_MAP.remove(msgId.toString());
        System.out.println("WordReaderSpout.ack:" + msgId);
    }

    /**
     * 当Topology停止时，会调用这个方法
     */
    @Override
    public void close() {
        System.out.println("WordReaderSpout.close()");
    }

    /**
     * 当一个Tuple处理失败时，会调用这个方法
     */
    @Override
    public void fail(Object msgId) {
        Values values = MESSAGE_MAP.get(msgId.toString());
        //collector.emit(values,msgId.toString());
        System.out.println("WordReaderSpout.fail msgId["+msgId+"],value["+values.get(0)+"]");
    }
}