package helloWorldPakages;

public class StringSplitDemo {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		String pwd = "�޵е����           jobpiejobpie           alalei4@163.com";
		//String pwd2 = "alalei410           142210           alalei410@163.com";
		
		String[] strList1 = pwd.split(" +");
		for(String str : strList1){
			System.out.println(str);
		}
	}

}
