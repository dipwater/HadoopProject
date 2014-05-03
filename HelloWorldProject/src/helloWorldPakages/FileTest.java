package helloWorldPakages;

import java.io.File;
import java.io.*;

public class FileTest {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		String dirname = "E:/Hadoop/workspace/HelloWorldProject/src/helloWorldPakages";
		File f1 = new File(dirname);
		if (f1.isDirectory()) {
			System.out.println("Directory of " + dirname);
			File files[] = f1.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					System.out.println(f.getName() + " is a directory");
				} else {
					System.out.println(f.getName() + " is a file");
				}
			}
		} else {
			System.out.println(dirname + " is not a directory");
		}
		
		FileReader fr = new FileReader("E:/Hadoop/workspace/HelloWorldProject/src/helloWorldPakages/HelloWorld.java"); 
		BufferedReader br = new BufferedReader(fr); 
		String s; 
		while((s = br.readLine()) != null) { 
		System.out.println(s); 
		} 
		fr.close();
	}
}
