package excercise;

public class ArgsExercise {

	public static void main(String[] args) { 
	// 입력된 값 중 짝수만 합하기(try catch)
	// 짝수 중 평균 최대값 최소값도 구하기
	// try catch 없이 공식만들기
		int sum = 0;
		int count=0;
		int avg = 0;
		int max = 0;
		int min = 0;
		for(String num : args) {
//			if(num.matches("\\D*")) {	// \\D: 숫자가 아닌 문자	*: 0번 이상 반복	// a8a, 8a8 등 숫자와 문자 혼합시 else로 넘어감ㅠㅠ
//			if(num.matches("^[0-9]*$")) {	// ^~$: 문자열 시작과 끝	 [0-9]: 숫자만 포함  *: 0번 이상 반복 // 음수가 포함안됨 ㅠㅠ
			if(num.matches("^[-]?[0-9]*$")) {	// ^~$: 문자열 시작과 끝	 [-]?: 음수가 포함될 수도 아닐수도 있음  [0-9]: 숫자만 포함  *: 0번 이상 반복 // 음수가 포함안됨 ㅠㅠ
				int num1 = Integer.parseInt(num);
				if(num1 % 2 == 0) {
					if(count == 0) {
						max = num1;
						min = num1;
					}
					count += 1;
					sum += num1;
					if(max < num1) {
						max = num1;
					}
					if(min > num1) {
						min = num1;
					}
				}
			}else {
			}
		}
		if(count == 0) {
			avg = 0;
		}else {
			avg = sum / count;
		}
		System.out.println("합계: " + sum);
		System.out.println("평균: " + avg);
		System.out.println("최대: " + max);
		System.out.println("최소: " + min);
	}

}
