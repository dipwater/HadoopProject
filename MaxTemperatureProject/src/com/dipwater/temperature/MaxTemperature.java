// cc MaxTemperature Application to find the maximum temperature in the weather dataset
// vv MaxTemperature
package com.dipwater.temperature;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.GenericOptionsParser;

public class MaxTemperature {

	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "192.168.1.51:9001");
		conf.set("fs.default.name", "hdfs://192.168.1.51:9000");

		String[] ars = new String[] { "temperature", "maxTemperature" };
		String[] otherArgs = new GenericOptionsParser(conf, ars).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: MaxTemperature <input path> <output path>");
			System.exit(-1);
		}

		JobConf jobCf = new JobConf(conf);
		jobCf.setJobName("MaxTemperature");

		File jarFile = EJob.createTempJar("bin");
		EJob.addClasspath("/home/hadoop/hadoop-1.2.1/conf");
		ClassLoader classLoader = EJob.getClassLoader();
		Thread.currentThread().setContextClassLoader(classLoader);
		jobCf.setJar(jarFile.toString());

		jobCf.setMapperClass(MaxTemperatureMapper.class);
		jobCf.setCombinerClass(MaxTemperatureReducer.class);
		jobCf.setReducerClass(MaxTemperatureReducer.class);
		jobCf.setOutputKeyClass(Text.class);
		jobCf.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(jobCf, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(jobCf, new Path(otherArgs[1]));
		
		//System.out.println(otherArgs[0] + " " + otherArgs[1]);

		JobClient.runJob(jobCf);
	}

	public static void main0(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: MaxTemperature <input path> <output path>");
			System.exit(-1);
		}

		JobConf conf = new JobConf(MaxTemperature.class);
		conf.setJobName("Max temperature");

		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		conf.setMapperClass(MaxTemperatureMapper.class);
		conf.setReducerClass(MaxTemperatureReducer.class);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		JobClient.runJob(conf);
	}
}
// ^^ MaxTemperature
