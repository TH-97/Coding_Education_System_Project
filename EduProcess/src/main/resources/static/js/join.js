document.getElementById("idCheck").addEventListener("click", function(e) {
	e.defaultPrevented;
            var idCheck = document.getElementById("id").value;
            // AJAX를 사용하여 서버로 코드를 전송하고 결과를 받아옴
            var check = new XMLHttpRequest();
            check.open("POST", "/idCheck", true);
            check.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            check.onreadystatechange = function() {
                if (check.readyState == XMLHttpRequest.DONE && check.status == 200) {
                    document.getElementById("check").innerText = check.responseText;
                }
            };
            check.send("user_id=" + encodeURIComponent(idCheck));
        });
document.querySelector("input[name=user_pw_check]").addEventListener("keyup",e => {
	var pw = document.querySelector("input[name=user_pw]").value;
	var check = document.querySelector(".pw_check")
	if(pw != e.target.value) {
		check.innerHTML='비밀번호가 일치하지않습니다'
	}else {
		check.innerHTML='비밀번호가 일치합니다'
	}
})