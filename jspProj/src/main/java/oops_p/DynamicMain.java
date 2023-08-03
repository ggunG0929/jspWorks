package oops_p;

class Par{
	int a = 10;
	int b = 20;
	
	void meth_1() {
//		System.out.println("부모 meth_1()");
		System.out.println("부모 meth_1(): "+a+", "+b);
	}
	
	void meth_2() {
//		System.out.println("부모 meth_2()");
		System.out.println("부모 meth_2(): "+a+", "+b);
	}
}

class Child1 extends Par{
	int a = 1000;	// hiding(새로 추가가 된다고 생각하기)
	int c = 3000;
	
	void meth_1() {	// overriding
//		System.out.println("자식1 meth_1()");
		System.out.println("자식1 meth_1(): "+a+", "+b+", "+c);
	}
	
	void meth_3() {
//		System.out.println("자식1 meth_3()");
		System.out.println("자식1 meth_3(): "+a+", "+b+", "+c);
	}
}

class Child2 extends Par{
	int d = 1234;
	
	void meth_4() {
		System.out.println("자식2 meth_4()");
	}
}

class Uncle{
	
}

public class DynamicMain {
	
	public static void main(String[] args) {
		Par pp = new Par();
		Child1 cc1 = new Child1();
		Child2 cc2 = new Child2();
		
		// 부모(a,b,1,2) - 자식거는 사용못함
		System.out.println("pp: "+pp.a+", "+pp.b);	// pp: 10, 20
//		System.out.println("pp: "+pp.a+", "+pp.b+", "+pp.c);	// c cannot be resolved or is not a field
		pp.meth_1();	// 부모 meth_1(): 10, 20
		pp.meth_2();	// 부모 meth_2(): 10, 20
//		pp.meth_3();	// The method meth_3() is undefined for the type Par
//		pp.meth_4();
		
		// 자식1(a(hiding),c,3) - 형제거는 사용못함
		System.out.println("cc1: "+cc1.a+", "+cc1.b+", "+cc1.c);	// cc1: 1000, 20, 3000
//		System.out.println("cc1: "+cc1.a+", "+cc1.b+", "+cc1.c+", "+cc1.d);
		cc1.meth_1();	// 자식1 meth_1(): 1000, 20, 3000
		cc1.meth_2();	// 부모 meth_2(): 10, 20	// 부모꺼 받아온거라 a=10
		cc1.meth_3();	// 자식1 meth_3(): 1000, 20, 3000
//		cc1.meth_4();
		
		// 자식2(d,4) - 형제거는 사용못함
		System.out.println("cc2: "+cc2.a+", "+cc2.b+", "+cc2.d);	// cc2: 10, 20, 1234
//		System.out.println("cc2: "+cc2.a+", "+cc2.b+", "+cc2.c+", "+cc2.d);
		cc2.meth_1();	// 부모 meth_1(): 10, 20
		cc2.meth_2();	// 부모 meth_2(): 10, 20
//		cc2.meth_3();
		cc2.meth_4();	// 자식2 meth_4()
		
		System.out.println("-----------------------");
		
		// 형식 - 내용
		Par dpp = new Par();
		Par dpc = new Child1();
//		Child1 dcp = new Par();	// child1이 par보다 필드가 더 많음	// Type mismatch: cannot convert from Par to Child1
		Child1 dcc = new Child1();

		System.out.println("dpp: "+dpp.a+", "+dpp.b);	// dpp: 10, 20
//		System.out.println("dpp: "+dpp.a+", "+dpp.b+", "+dpp.c+", "+dpp.d);	// c cannot be resolved or is not a field
		dpp.meth_1();	// 부모 meth_1(): 10, 20
		dpp.meth_2();	// 부모 meth_2(): 10, 20
//		dpp.meth_3();	// The method meth_3() is undefined for the type Par
		
		System.out.println("dpc: "+dpc.a+", "+dpc.b);	// dpc: 10, 20
//		System.out.println("dpc: "+dpc.a+", "+dpc.b+", "+dpc.c+", "+dpc.d);
		dpc.meth_1();	// 자식1 meth_1(): 1000, 20, 3000
		dpc.meth_2();	// 부모 meth_2(): 10, 20
//		dpc.meth_3();
		
		System.out.println("dcc : "+dcc.a+","+dcc.b+","+dcc.c);		// dcc : 1000(하이딩),20(상속),3000(자체정의)
		dcc.a = 1111;
		dcc.b = 2222;
		dcc.c = 3333;
		System.out.println("dcc : "+dcc.a+","+dcc.b+","+dcc.c);		// dcc : 1111,2222,3333 - 모두 재정의
		dcc.meth_1();	// 자식1 meth_1(): 1111, 2222, 3333	- meth_1(오버라이딩)
		dcc.meth_2();	// 부모 meth_2(): 10, 2222	- meth_2(상속)	// a는 왜 다시 부모레벨에서 정의된 값이 나오지? b는 재정의된 값이 나오는데	
		// child1에게는 b 값이 따로 없기 때문에 child1의 b를 재정의 할 때 par의 b로 넘어가서 재정의 한다.
		// meth_2는 par에만 있는 메서드였기 때문에 par의 a, b를 우선으로 가져오는데 그래서 par의 a인 10을 가져오고,
		// child1에서 재정의하면서 변경된 b인 2222를 가져온다.
		dcc.meth_3();	// 자식1 meth_3(): 1111, 2222, 3333	- meth_3(자체정의)
		
		Par dpcc = dcc;
		System.out.println("dpcc : "+dpcc.a+","+dpcc.b);	// dpcc : 10,2222
		//System.out.println("dpcc : "+dpcc.a+","+dpcc.b+","+dpcc.c);
		dpcc.meth_1();	// 자식1 meth_1(): 1111, 2222, 3333
		dpcc.meth_2();	// 부모 meth_2(): 10, 2222
		//dpcc.meth_3();
		
		Child1 dcpcc = (Child1)dpcc;
		System.out.println("dcpcc : "+dcpcc.a+","+dcpcc.b+","+dcpcc.c);		// dcpcc : 1111,2222,3333
		dcpcc.meth_1();	// 자식1 meth_1(): 1111, 2222, 3333
		dcpcc.meth_2();	// 부모 meth_2(): 10, 2222
		dcpcc.meth_3();	// 자식1 meth_3(): 1111, 2222, 3333
		
//		Child1 cpp = (Child1)pp;	// Par cannot be cast to Child1
		// pp는 Par로 형변환이 가능한지, pp는 Child1에게 상속을 받을 수 있는지
		System.out.println(pp instanceof Par);	// true
		System.out.println(pp instanceof Child1);	// false
		System.out.println(cc1 instanceof Par);		// true
		System.out.println(cc1 instanceof Child1);	// true
//		System.out.println(cc1 instanceof Uncle);	// Incompatible conditional operand types Child1 and Uncle
	}

}
