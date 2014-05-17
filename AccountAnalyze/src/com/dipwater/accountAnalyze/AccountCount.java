package com.dipwater.accountAnalyze;

import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

public class AccountCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO 自动生成的方法存根
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "192.168.1.51:9001");
		conf.set("fs.default.name", "hdfs://192.168.1.51:9000");
		
		String[] ars = new String[]{"input", "accountCountOut"};
	    String[] otherArgs = new GenericOptionsParser(conf, ars).getRemainingArgs();
	    if (otherArgs.length != 2) {
	      System.err.println("Usage: accountcount <in> <out>");
	      System.exit(2);
	    }
	    
	    Job job = new Job(conf, "Account Count");
	    File jarFile = EJob.createTempJar("bin");
	    EJob.addClasspath("/home/hadoop/hadoop-1.2.1/conf");
	    ClassLoader classLoder = EJob.getClassLoader();
	    Thread.currentThread().setContextClassLoader(classLoder);
	    ((JobConf)job.getConfiguration()).setJar(jarFile.toString());
	    
	    job.setMapperClass(AccountCountMapper.class);
	    //job.setCombinerClass(AccountCountReducer.class);
	    job.setReducerClass(AccountCountReducer.class);
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    
	    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
	    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
	    
	    
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
