package helloWorldPakages;

public class StringSplitDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String pwd = "无敌的奇兵           jobpiejobpie           alalei4@163.com";
		//String pwd2 = "alalei410           142210           alalei410@163.com";
		
		String[] strList1 = pwd.split(" +");
		for(String str : strList1){
			System.out.println(str);
		}
	}

}
