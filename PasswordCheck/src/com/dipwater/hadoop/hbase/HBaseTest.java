package com.dipwater.hadoop.hbase;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseTest {

	static Configuration conf = null;
	
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		conf = HBaseConfiguration.create();
//		conf.set("hbase.zookeeper.quorum", "master");
//		conf.set("hbase.zookeeper.property.clientPort", "2181");
		String tableName = "blog";
		String column1 = "id";
		String column2 = "name";
		String[] columns = new String[]{column1, column2};
		createTable(tableName, columns);
		insert(tableName, columns);
		ArrayList array = select(tableName, "row1");
		
		for(Object o : array){
			System.out.println(o.toString());
		}
	}
	
	public static void createTable(String tableName, String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException{
		HBaseAdmin admin = new HBaseAdmin(conf);
		if(admin.tableExists(tableName)){
			deleteTable(tableName);
		}
		HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
		for(int i = 0; i < args.length; i++){
			tableDesc.addFamily(new HColumnDescriptor(args[i]));
		}
		admin.createTable(tableDesc);
        System.out.println("表创建成功！");
	}
	
	 public static void deleteTable(String tablename) throws IOException {
	     try {
	         HBaseAdmin admin = new HBaseAdmin(conf);
	         admin.disableTable(tablename);
	         admin.deleteTable(tablename);
	         System.out.println("表删除成功！");
	     } catch (MasterNotRunningException e) {
	         e.printStackTrace();
	     } catch (ZooKeeperConnectionException e) {
	         e.printStackTrace();
	     }
	 }
	 
	public static void insert(String tableName, String[] args){
		try {
			HTable table = new HTable(conf, tableName);
			Put put = new Put(Bytes.toBytes("row1"));
			for(int j = 0; j < args.length; j++){
				put.add(Bytes.toBytes(args[j]), Bytes.toBytes(String.valueOf(1)), Bytes.toBytes("value_1"));
				table.put(put);
			}
	        System.out.println("表添加数据成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delete(String tableName, String rowKey){
		HTable table;
		try {
			table = new HTable(conf, tableName);

			ArrayList list = new ArrayList();
			Delete del = new Delete(rowKey.getBytes());
			list.add(del);
			table.delete(list);

	        System.out.println("表删除数据成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList select(String tableName, String rowKey){
		try {
			HTable table = new HTable(conf, tableName);
			Get get = new Get(rowKey.getBytes());
			Result rs = table.get(get);
			ArrayList array = new ArrayList();
			for(KeyValue kv : rs.raw()){
				array.add(new String(kv.getRow()));
				array.add(new String(kv.getFamily()));
				array.add(new String(kv.getQualifier()));
				array.add(kv.getTimestamp());
				array.add(new String(kv.getValue()));
			}

	        System.out.println("表查询数据成功！");
			return array;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
