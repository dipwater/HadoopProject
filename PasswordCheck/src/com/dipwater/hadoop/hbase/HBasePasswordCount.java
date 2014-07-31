package com.dipwater.hadoop.hbase;

import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

public class HBasePasswordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String tablename = "passwordcount";
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
	    if (otherArgs.length != 2) {
	      System.err.println("Usage: hbasepasswordcount <in> <out>");
	      System.exit(2);
	    }

	    createHBaseTable(tablename);
	    conf.set(TableOutputFormat.OUTPUT_TABLE, tablename);
	    Job job = new Job(conf, "HBase-Password Count");
	    job.setJarByClass(HBasePasswordCount.class);
	    
	    job.setMapperClass(HBasePasswordCountMapper.class);
	    job.setReducerClass(HBasePasswordCountReducer.class);
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    
	    job.setInputFormatClass(TextInputFormat.class);
	    job.setOutputFormatClass(TableOutputFormat.class);
	    
	    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
	    //FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
	    
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	public static void createHBaseTable(String tablename) throws IOException {
		HTableDescriptor htd = new HTableDescriptor(tablename);
		HColumnDescriptor col = new HColumnDescriptor("content");
		htd.addFamily(col);
		Configuration cfg = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(cfg);
		if (admin.tableExists(tablename)) {
			System.out.println("table exists.");
//			admin.disableTable(tablename);
//			admin.deleteTable(tablename);
//			admin.createTable(htd);
		} else {
			System.out.println("create new table:" + tablename);
			admin.createTable(htd);
		}
	}
}
