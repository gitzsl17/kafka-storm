/**
 * Project Name:storm-analyse
 * File Name:ZookeeperUtil.java
 * Package Name:com.surfilter.zookeeper
 * Date:2016年3月8日上午11:51:53
 *
*/

package com.act.kafka.storm.util;

import org.apache.log4j.Logger;
import org.apache.storm.shade.org.apache.zookeeper.*;
import org.apache.storm.shade.org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * ClassName:ZookeeperUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月8日 上午11:51:53 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ZookeeperUtil {
	
	private static Logger logger = Logger.getLogger(ZookeeperUtil.class);
	
	public static Properties properties;
	
	public static Watcher watcher=new Watcher() {
		
		@Override
		public void process(WatchedEvent event) {
//			System.out.println("connect success!");
		}
	};
	
	public static ZooKeeper connect(Watcher watcher){
		ZooKeeper zooKeeper=null;
		try {
			if(properties==null){
				loadProperties();
			}
			String cluster=properties.getProperty("zookeeper.cluster");
			String sessionTimeout=properties.getProperty("zookeeper.sessionTime");
			zooKeeper = new ZooKeeper(cluster, Integer.valueOf(sessionTimeout), watcher);
		} catch (IOException e) {
			logger.error(e);
		}
		return zooKeeper;
	}


	private static void loadProperties() {
		 try {
			properties=new Properties();
			properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public static void main(String[] args) {
		initZookeeper("ISMI_Kafka_Analyse");
		initZookeeper("ISMI_Storm_Analyse");
//		create("/brokers", "");
		
//		String content=getData("/storm_analyse");
//		System.out.println(content);
		
//		List<String> lst=getChildren("/");
//		for(String s:lst){
//			System.out.println(s);
//		}
		
//		delete("/storm_analyse");
//		exist("/storm_analyse");
//		delete("/storm_analyse");
//		exist("/storm_analyse");
	}
	
	/**
	 * 
	 * create:创建zookeeper目录
	 *
	 * @author Administrator
	 * @param path 路径以/开始
	 * @param data 数据内容
	 * @since JDK 1.6
	 */
	public static void create(String path, String data){
		ZooKeeper zookeeper=connect(watcher);
		try {
			zookeeper.create(path, data.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	
	/**
	 * 
	 * getData:获取数据内容
	 *
	 * @author Administrator
	 * @param path 路径以/开始
	 * @return
	 * @since JDK 1.6
	 */
	public static String getData(String path){
		String content="";
		ZooKeeper zookeeper=connect(watcher);
		try {
			byte[] bytes=zookeeper.getData(path, false, null);
			content=new String(bytes);
		} catch (KeeperException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		return content;
	}
	
	/**
	 * 
	 * getChildren:节点下的子目录
	 *
	 * @author Administrator
	 * @param path 路径以/开始
	 * @return
	 * @since JDK 1.6
	 */
	public static List<String> getChildren(String path){
		List<String> lst=null;
		ZooKeeper zookeeper=connect(watcher);
		try {
			lst=zookeeper.getChildren(path, true);
		} catch (KeeperException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		return lst;
	}
	
	/**
	 * 
	 * delete:删除节点目录
	 *
	 * @author Administrator
	 * @param path 路径以/开始
	 * @return
	 * @since JDK 1.6
	 */
	public static void delete(String path){
		ZooKeeper zookeeper=connect(watcher);
		try {
			zookeeper.delete(path, -1);
		} catch (InterruptedException e) {
			logger.error(e);
			
		} catch (KeeperException e) {
			logger.error(e);
			
		}
	}
	
	
	/**
	 * 
	 * exist:是否存在
	 *
	 * @author Administrator
	 * @param path 路径以/开始
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean exist(String path){
		boolean flag=false;
		ZooKeeper zookeeper=connect(watcher);
		try {
			Stat stat=zookeeper.exists(path, true);
			if(stat!=null){
				flag=true;
			}else{
				flag=false;
			}
		} catch (InterruptedException e) {
			logger.error(e);
			
		} catch (KeeperException e) {
			logger.error(e);
		}
		return flag;
	}
	
	/**
	 * 
	 * initZookeeper:初始化zookeeper关于topic的节点. <br/>
	 *
	 *@param topicName	kafka topic节点名称
	 * @author quanli
	 * @since JDK 1.6
	 */
	public static void initZookeeper(String topicName){
		try {
			loadProperties();
			String zookRoot = properties.getProperty("zookeeper.root");
			if(!exist(zookRoot)){
				create(zookRoot,"");
			}
			if(!exist(zookRoot+"/topics")){
				create(zookRoot+"/topics","");
			}
			if(!exist(zookRoot+"/topics/"+topicName)){
				create(zookRoot+"/topics/"+topicName,"");
			}
			if(!exist(zookRoot+"/topics/"+topicName+"/partitions")){
				create(zookRoot+"/topics/"+topicName+"/partitions","");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}

