package com.dipwater.hadoop.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/**
 * @author dipwater http://www.cnblogs.com/dipwater
 * 根据现有的密码库统计每个密码出现次数，
 * 客户端查询每个密码次数判断密码是否安全
 */
public class HBasePasswordGet {

	Configuration conf = null;
	final String tableName = "passwordcount";

	public HBasePasswordGet() {
		conf = HBaseConfiguration.create();
	}

	@Test
	public void test() {
		String password = "123456";
		System.out.println(getCount(password));
	}

	/**
	 * 获取密码到数量
	 * @param 密码字符串
	 * @return -1 表示错误，大于等于0表示密码数量
	 */
	public int getCount(String password) {
		try {
			if (tableExist(tableName) == false) {
				return -1;
			}
			
			return getRow(tableName, password);
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	private Boolean tableExist(String tablename)
			throws MasterNotRunningException, ZooKeeperConnectionException,
			IOException {
		HBaseAdmin admin = new HBaseAdmin(conf);
		return admin.tableExists(tablename);
	}
	
	private int getRow(String tablename, String key) throws IOException{
		HTable table = new HTable(conf, tableName);
		Get get = new Get(Bytes.toBytes(key));
		Result rs = table.get(get);
		String value = null;
		for(KeyValue kv : rs.raw()){
			value = new String(kv.getValue());
			break;
		}
		
		try{
			if(value == null){
				return 0;
			}
			return Integer.parseInt(value);
		}
		catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}

}
