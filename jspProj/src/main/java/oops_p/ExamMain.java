package oops_p;

import java.util.Arrays;

public class ExamMain {

	public static void main(String[] args) {
		String [] names = {"현빈","원빈","투빈","장희빈","미스터빈"};
		
		String [] kor = "72,94,85,72,83".split(",");
		String [] eng = "57,64,28,76,95".split(",");
		String [] mat = "67,93,64,83,94".split(",");

		Exam [] arr = new Exam[names.length];

		
		System.out.println(arr);	// 주소: [Loops_p.Exam;@6f2b958e
		System.out.println(arr.length);		// 배열 길이: 5
		System.out.println(arr[0]);		// 0번째: null
		System.out.println(arr[1]);		// 1번째: null
		System.out.println(Arrays.toString(arr));	// Exam배열: [null, null, null, null, null]
//		arr[0].name = names[0];	// Cannot assign field "name" because "arr[0]" is null
		
		Exam ex0 = new Exam(names[0], kor[0], eng[0], mat[0]);
		Exam ex1 = new Exam(names[1], kor[1], eng[1], mat[1]);
		System.out.println(ex0);
		System.out.println(ex1);
		
		Exam [] arr2 = {
				ex0,
				ex1,
				new Exam(names[2], kor[2], eng[2], mat[2]),
				new Exam(names[3], kor[3], eng[3], mat[3]),
				new Exam(names[4], kor[4], eng[4], mat[4])
				};
		
		System.out.println(Arrays.toString(arr2));

		System.out.println("---------------");
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Exam(names[i], kor[i], eng[i], mat[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i].rankCalc(arr);
			System.out.println(arr[i]);
		}
	}

}
