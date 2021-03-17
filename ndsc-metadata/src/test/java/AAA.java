
public class AAA{
	public static void main(String[] args) {
//		CCC c = new CCC(0);
//		BBB b1= new BBB("A",c);
//		BBB b2= new BBB("B",c);
//		for(int i = 0; i < 20000; i++) {
//			new Thread(b1).start();
//			new Thread(b2).start();
//		}
	}
}
class BBB implements Runnable{
	private CCC c;
	private String name;
	public BBB(String name,CCC c){
		this.name=name;
		this.c=c;
	}
	public  void run() {
		System.out.println(name+":"+c.push());
	}
}
class CCC{
	private int num=0;
	public CCC(int num){
		this.num=num;
	}
	public synchronized int push(){
		num++;
		return num;
	}
}