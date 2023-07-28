package oops_p;

import java.util.Arrays;

class AAA{
	
	public AAA() {
		System.out.println("AAA 기본생성자");	// 4번
	}
	
	int setA() {
		System.out.println("setA() 실행");	// 2번으로 실행
		return b;
	}
	int setB() {
		System.out.println("setB() 실행");	// 3번으로 실행
		return 60;
	}
	static int setSA() {
		System.out.println("setSA() 실행");	// 1번으로 실행
		return 5555;
	}

	// 대입
	int a = setA();
	int b = setB();
	static int sa = setSA();
	
	@Override
	public String toString() {
		return "AAA [a=" + a + ", b=" + b + ", sa=" + sa + "]";
	}
}

class BBB {
	void meth_1(int a) {
		System.out.println("meth_1 실행: " +a);
	}
	void meth_2(int a, int b) {
		System.out.println("meth_2 실행: " +a+", "+b);
	}
	void meth_3(int[] a) {
		System.out.println("meth_3 실행: " +Arrays.toString(a));
	}
	void meth_4(int ... a) {	// 몇개가 들어와도 배열로 넣을게
		System.out.println("meth_4 실행: " +Arrays.toString(a));
	}
	void meth_33(int [] a, int b) {
		System.out.println("meth_33 실행: " +Arrays.toString(a)+", "+b);
	}
//	void meth_44(int ... a, int b) {
//		System.out.println("meth_44 실행: " +Arrays.toString(a)+", "+b);
//	}
//	void meth_444(int ... a, int ... b) {
//		System.out.println("meth_444 실행: " +Arrays.toString(a)+", "+b);
//	}
	void meth_4444(int a, int ... b) {	// 스프레드 연산자 ... : 맨 뒤에 넣어야 함
		System.out.println("meth_4444 실행: " +a+", "+Arrays.toString(b));
	}
}

public class InstanceMain {

	public static void main(String[] args) {
		
//		AAA a1 = new AAA();			// 3
//		System.out.println(a1);		// 4번으로 실행

		new AAA();
		System.out.println("------------");
		AAA a1;
		System.out.println("------------");
		a1 = new AAA();
		System.out.println("------------");
		AAA a2 = new AAA();			// 4
		System.out.println("------------");
		System.out.println(a1);		// 5번으로 실행
		
		BBB bb = new BBB();
		int [] arr = {11, 22, 33, 44};
		bb.meth_1(10);
//		bb.meth_1(10,20);
//		bb.meth_1(10,20,30);
//		bb.meth_1(arr);
		
//		bb.meth_2(10);
		bb.meth_2(10,20);
//		bb.meth_2(arr);

//		bb.meth_3(10);
//		bb.meth_3(10,20);
		bb.meth_3(arr);
		
		bb.meth_4(10);
		bb.meth_4(10,20);
		bb.meth_4(arr);
//		bb.meth_4(arr,50);	// Error: Unresolved compilation problem: is not applicable for the arguments

		bb.meth_33(arr,50);
		
		bb.meth_4444(10);
		bb.meth_4444(10,20);
//		bb.meth_4444(arr);
		bb.meth_4444(50,arr);

	}

}
