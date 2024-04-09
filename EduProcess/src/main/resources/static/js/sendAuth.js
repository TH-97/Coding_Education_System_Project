function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    var expired = document.getElementById('expired');
    setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

          if (timer <= 0) {
            expired.innerHTML = "인증시간이 만료되었습니다.";
            clearInterval(); // 타이머 정지
        } else {
            --timer;
        }
    }, 1000);
}

window.onload = function () {
    var threeMinutes = 60 * 3, // 3분을 초 단위로 변환
        display = document.querySelector('#timer'); // 타이머를 표시할 요소

    startTimer(threeMinutes, display);
};