package com.dipwater.hadoop.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author dipwater http://www.cnblogs.com/dipwater
 * Reducer 从Mapper获取Key和计数合并后写入hbase 
 */
public class HBasePasswordCountReducer extends TableReducer<Text, IntWritable, NullWritable>{
	
	private IntWritable result = new IntWritable();
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
		int sum = 0;
		for(IntWritable val : values){
			sum += val.get();
		}
		
		result.set(sum);
		Put put = new Put(Bytes.toBytes(key.toString()));
		put.add(Bytes.toBytes("content"), Bytes.toBytes("count"), Bytes.toBytes(String.valueOf(sum)));
		context.write(NullWritable.get(), put);
	}
}
