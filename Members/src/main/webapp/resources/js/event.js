function play() {
    let name = ["성호","은효","민정","지혜","승우","가은","은별","세현","기은","유진","의석","경철","화룡","재훈","영준","현우","정현","성길","주훈","진성"];
    let numbers = new Array(5);  // 랜덤 번호 5개를 저장할 배열 선언

    for(let i=0;i<numbers.length;i++){
        let lotto = Math.floor(Math.random()*(name.length));
        numbers[i] = lotto;
        for(let j=0;j<i;j++){
            if(numbers[j]===numbers[i]){
                i--;
            }
        }
    }
    let winners = new Array(5);
    for(let i=0; i<numbers.length; i++) {
		winners[i] = name[numbers[i]];
	}
    
    document.getElementById("display").innerHTML = winners;
}