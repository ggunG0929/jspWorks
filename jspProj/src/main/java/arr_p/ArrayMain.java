package arr_p;

import java.util.Arrays;

public class ArrayMain {

	public static void main(String[] args) {
		int a = 10;
		int [] arr1 = {11, 22, 33};		// 배열 생성 및 초기화 후 주소 대입, 최초 생성시에는 new int []를 생략가능
		
		System.out.println("a: "+a);
		System.out.println("arr1: "+arr1);	// [I@515f550a	[: 배열의 차원  I: int  @: 구분  515f550a: hash코드 16진수
		
		a = 23;
		// arr1 = {55,66,77};	// 오류: new int []는 최초 배열변수 선언시에만 생략 가능, 중간 수정시에는 생략 불가
		
		arr1 = new int [] {55, 66, 77, 88, 99};
		System.out.println("a: "+a);
		System.out.println("arr1: "+arr1+"=>"+arr1.length);	// [I@626b2d4a
		
		int [] arr2 = new int[4];
		System.out.println("arr2: "+arr2+"=>"+arr2.length);	// [I@5e91993f

		// 얕은(shallow) 복사
		int [] arr3 = arr1;
		// 깊은(deep) 복사1
		int [] arr4 = new int[arr1.length];
		for(int i=0; i<arr1.length; i++) {
			arr4[i] = arr1[i];
		}
		// 깊은(deep) 복사2
		int [] arr5 = arr1.clone();
		
		System.out.println("arr3: "+arr3+"=>"+arr3.length);	// [I@626b2d4a
		System.out.println("arr4: "+arr4+"=>"+arr4.length);	// [I@1c4af82c
		System.out.println("arr5: "+arr5+"=>"+arr5.length);	// [I@379619aa
		
		System.out.println("arr1: "+Arrays.toString(arr1));
		System.out.println("arr3: "+Arrays.toString(arr3));
		System.out.println("arr4: "+Arrays.toString(arr4));
		System.out.println("arr5: "+Arrays.toString(arr5));
		
		int b = a;
		System.out.println("a: "+a+", b: "+b);
		a = 77;
		arr1[1] = 6543;
		System.out.println("a: "+a+", b: "+b);
		System.out.println("arr1[1]: "+arr1[1]);
		System.out.println("arr3[1]: "+arr3[1]);
		
		System.out.println("arr1: "+Arrays.toString(arr1));
		System.out.println("arr3: "+Arrays.toString(arr3));		// 얕은 복사는 인덱스1의 내용이 변경됨(원본과 공유)
		System.out.println("arr4: "+Arrays.toString(arr4));
		System.out.println("arr5: "+Arrays.toString(arr5));
		
		System.out.println("----------------------------");
		
		// 점수 : 87,65,82,46,98,65,72,34,91,79,45,67
		// 점수에 대응하는 수우미양가 등급 배열을 구현하세요
		String [] jum = "87,65,82,46,98,65,72,34,91,79,45,67".split(",");
		String [] lev = new String[jum.length];
		for(int i=0; i<jum.length; i++) {
			int ijum = Integer.parseInt(jum[i]); 
			lev[i] = "가가가가가가양미우수수".charAt(ijum/10)+"";
		}
		System.out.println("lev: "+Arrays.toString(lev));
	}

}
