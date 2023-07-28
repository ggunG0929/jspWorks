package arr_p;

import java.util.Arrays;

public class SortMain {
	// sort: 정렬
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		System.out.println(a+", "+b);	// 10, 20
		
		//b = a;
		//a = b;
		
		int buf = b;
		b = a;
		a = buf;
		
		System.out.println(a+", " +b);	// 20, 10	// 10, 10
		
		int [] arr = {77,65,98,46,82};
		System.out.println("정렬전: "+Arrays.toString(arr));
		
		for(int me=0; me<arr.length; me++) {
			System.out.println(arr[me]);
			for(int you=me+1; you<arr.length; you++) {
				System.out.println(arr[you]+", " );
				if(arr[me] > arr[you]) {
					buf = arr[you];
					arr[you] = arr[me];
					arr[me] = buf;
				}
			}
			System.out.println("\n::"+Arrays.toString(arr));
		}

	}

}
