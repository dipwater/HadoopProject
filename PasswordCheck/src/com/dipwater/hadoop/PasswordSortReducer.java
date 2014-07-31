package com.dipwater.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class PasswordSortReducer extends Reducer<IntWritable, Text, IntWritable, Text>{
	
	public void reduce(IntWritable key, Text value, Context context) throws IOException, InterruptedException{
		context.write(key, value);
	}
}
