package org.apache.hadoop.examples;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class CreateDir {
	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();
		//conf.set("mapred.job.tracker", "192.168.1.51:9001");
	    //conf.set("fs.default.name", "hdfs://192.168.1.51:9000");
	    
		FileSystem hdfs = FileSystem.get(conf);
		
		Path dfs = new Path("/TestDir");
		boolean result = hdfs.mkdirs(dfs);
		System.out.println(result);
	}
}
