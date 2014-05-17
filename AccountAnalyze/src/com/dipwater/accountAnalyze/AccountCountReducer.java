package com.dipwater.accountAnalyze;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AccountCountReducer extends Reducer<Text, IntWritable, IntWritable, Text>{
	
	private IntWritable result = new IntWritable();
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
		int sum = 0;
		for(IntWritable val : values){
			sum += val.get();
		}
		
		result.set(sum);
		context.write(result, key);
	}
}
