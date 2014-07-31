package com.dipwater.hadoop;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.*;

public class PasswordSortMapper extends Mapper<Object, Text, IntWritable, Text>{
	
	private Text text = new Text();
	private IntWritable count = new IntWritable();
	
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
		String[] items = value.toString().split("	");
		if(items.length > 1){
			text.set(items[1]);
			count.set(Integer.parseInt(items[0]));
			
			context.write(count, text);
		}
	}
}
