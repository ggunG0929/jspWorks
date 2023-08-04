package excercise;

public class Exam2 {
	// 필드 선언
	String name, grade;
	int[] jum, jumrank;
	int tot, rank;
	// rank 연산시 더 정확하도록 double 적용
	double avg;

	// 입력이 필요한 필드와 그에 따른 연산
	public Exam2(String name, int ... jum) {
		this.name = name;
		this.jum = jum.clone();		// 원래는 배열이라 대입시 주소를 가져와 마지막 학생의 점수만으로 출력됨. 깊은 복사를 통해 값을 받음.
		this.jumrank = new int[jum.length]; // jumrank 배열 초기화
		// tot, avg, grade 연산
		calc();
	}

	// tot, avg, grade 연산
	void calc() {
		tot = 0;
		for(int i : jum) {
			tot += i;
		}
		avg = (double)tot/jum.length;
		// charAt은 char를 반환. 뒤에 +""를 합해주면서 문자열이 되어 String 자료형 가능
		grade = "가가가가가가양미우수수".charAt((int)(avg/10))+"";
	}

	// rank 연산	// The method rankCalc(Exam2[]) from the type Exam2 is not visible - public 붙임
	public void rankCalc(Exam2 [] exArr) {
		// 등수 초기값 1위
		rank = 1;
		for(Exam2 you : exArr) {
			// 내 평균이 남의 평균보다 낮을 경우 등수가 늘어나 순위가 낮아짐
//			if(avg<you.avg) {	//Cannot read field "avg" because "you" is null
//	        if(avg<you.avg && you!=null) {	// 역시 같은 오류, 널처리를 앞에 넣어줘야 함
        	if(you!=null && avg<you.avg) {
				rank++;
			}
		}
	}
	
	// jumrank 연산(과목별 등수)
	public void jumrankCalc(Exam2 [] exArr, int index) {
		// 등수 초기값 1위
		jumrank[index] = 1;
		for(Exam2 you : exArr) {
			if(you!=null && jum[index]<you.jum[index]) {
				jumrank[index]++;
			}
		}
	}

	// The field Exam2.~ is not visible	// public으로
	public String getName() {
		return name;
	}

	public int[] getJum() {
		return jum;
	}
	
	public int[] getJumrank() {
		return jumrank;
	}
	
	public int getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return "<td>" + tot + "</td><td>" + (int)avg + "</td><td>"
				+ grade + "</td><td>" + rank + "</td>";
	}

}