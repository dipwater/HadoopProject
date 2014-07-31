package com.dipwater.hadoop.hbase;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author dipwater http://www.cnblogs.com/dipwater
 * Mapper 从HDFS读取原始文本文件数据并计数
 */
public class HBasePasswordCountMapper extends Mapper<Object, Text, Text, IntWritable>{
	
	final private IntWritable num = new IntWritable(1);
	private Text text = new Text();
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
		String[] items = value.toString().split(" +");
		if(items.length > 1){
			text.set(items[1]);

			context.write(text, num);
		}
	}
}
