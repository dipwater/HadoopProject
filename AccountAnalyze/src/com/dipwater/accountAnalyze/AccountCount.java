package com.dipwater.accountAnalyze;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;

public class AccountCount {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "192.168.1.51:9001");
		conf.set("fs.default.name", "hdfs://192.168.1.51:9000");
		
		String[] ars = new String[]{"account", "accountCountOut"};
	    String[] otherArgs = new GenericOptionsParser(conf, ars).getRemainingArgs();
	    if (otherArgs.length != 2) {
	      System.err.println("Usage: accountcount <in> <out>");
	      System.exit(2);
	    }
	    
	    Job job = new Job(conf, "account count");
	    
	}

}
