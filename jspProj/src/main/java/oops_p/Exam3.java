package oops_p;

public class Exam3 {

	String name, grade;
	int [] jum;
	int tot, rank;
	// rank 연산시 더 정확하도록 double 적용
	double avg;
	
    public Exam3(String name, int kor, int eng, int mat) {
        this.name = name;
        this.jum = new int[]{kor, eng, mat};
        // tot, avg, grade 연산
        calc();
    }

    // tot, avg, grade 연산
	void calc() {
		tot = 0;
		for(int i:jum) {
			tot += i;
		}
		avg = (double)tot/jum.length;
		grade = "가가가가가가양미우수수".charAt((int)(avg/10))+"";
		// 등수 초기값 1
		rank = 1;
	}
	
	// rank 연산	// The method rankCalc(Exam3[]) from the type Exam3 is not visible
	public void rankCalc(Exam3 [] exArr) {
		for(Exam3 you:exArr) {
			// 내 평균이 남의 평균보다 낮을 경우 등수가 늘어나 순위가 낮아짐
//			if(avg<you.avg) {	//Cannot read field "avg" because "you" is null
//	        if (avg<you.avg && you != null) {	// 역시 같은 오류, 널처리를 앞에 넣어줘야 함
        	if (you!=null && avg<you.avg) {
	            rank++;
	        }
		}
	}
	
	// The field Exam3.~ is not visible
	public int[] getJum() {
		return jum;
	}
	
	public int getTot() {
		return tot;
	}
	
	public int getAvg() {
		// 출력시 포맷팅 번거로워서 double값을 반올림하여 int값으로
		return (int)avg;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public int getRank() {
		return rank;
	}
}
