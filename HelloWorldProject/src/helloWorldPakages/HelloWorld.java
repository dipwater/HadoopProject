package helloWorldPakages;

class MyThread implements Runnable {
	Thread thread = new Thread(this, "Thread1");

	public MyThread() {
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.print(i);
			System.out.print(' ');
			System.out.println(Thread.currentThread().getId());
		}
	}
}

//public class HelloWorld {
//
//	public static void main(String[] args) throws IOException,
//			InterruptedException {
//		System.out.println(Thread.currentThread().getId());
//		MyThread myThread = new MyThread();
//		myThread.thread.start();
//		myThread.thread.join();
//		System.in.read();
//		// throw new NullPointerException();
//		// System.out.println("This is hello from world.");
//		// int sum = Recursive(10);
//		// System.out.println(sum);
//	}
//
//	private static int Recursive(int num) {
//		int sum;
//		if (num == 1) {
//			return 1;
//		}
//		sum = num * Recursive(--num);
//		return sum;
//	}
//}

// This program is not synchronized.
class Callme {
	void call(String msg) {
		System.out.print("[" + msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println("]");
	}
}

class Caller implements Runnable {
	String msg;
	Callme target;
	Thread t;

	public Caller(Callme targ, String s) {
		target = targ;
		msg = s;
		t = new Thread(this);
		t.start();

	}

	public void run() {
		//synchronized(target){
			target.call(msg);
		//}
	}
}

class HelloWorld {
	public static void main(String args[]) {
		Callme target = new Callme();
		Caller ob1 = new Caller(target, "Hello");
		Caller ob2 = new Caller(target, "Synchronized");
		Caller ob3 = new Caller(target, "World");
		// wait for threads to end
		try {
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}
}