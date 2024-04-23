


var IDRegex = /^[a-zA-Z0-9]{4,12}$/; //4~12글자, 영어나 숫자만 가능
var passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*#?&])[a-zA-Z\d@$!%*#?&]{8,}$/; //8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&*) 각 하나씩은 포함
var userNameRegex =   /^(?:[a-zA-Z]{3,}|[가-힣]{2,})$/; //영어 최소 3글자이상, 한글 이름 최소 2글자 이상
var userAgeRegex = /^(?:1[0-4]\d|[1-9]\d|\d)$/; //1세~150세
var phoneRegex = /^010\d{8}$/; //010, 11자리

function validateCheck(){
    var id = document.querySelector("#id");
    var userPw = document.querySelector("#user_pw");
    var userPwCheck = document.querySelector("#user_pw_check");
    var userName = document.querySelector("#user_nm");
    var userAge = document.querySelector("#user_age");
    var userPhone = document.querySelector("#user_pn");
    var parentPhone = document.querySelector("#parn_pn");
    
    if(!IDRegex.test(id.value)){
        alert("4~12글자, 영어나 숫자만 가능")
        id.value="";
        id.focus();
        return;
    }
    if(!passwordRegex.test(userPw.value)){
        alert("8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&*) 각 하나씩은 포함해야함")
        userPw.value="";
        userPwCheck.value="";
        userPreturn;w.focus();
        return;
    }

    if(!userNameRegex.test(userName.value)){
        alert("영어 최소 3글자이상, 한글 이름 최소 2글자 이상")
        userName.value="";
        userName.focus();
        return;

    }
    if(!userAgeRegex.test(userAge.value)){
        alert("나이에 맞는 숫자를 넣어주세요")
        userAge.value="";
        userAge.focus();
        return;
    }
    if(!phoneRegex.test(userPhone.value)){
        alert("010으로 시작하는 11자리로 입력하여주세요.")
        userPhone.value="";
        userPhone.focus();
        return;
    }
    if(!phoneRegex.test(parentPhone.value)){
        alert("010으로 시작하는 11자리로 입력하여주세요.")
        parentPhone.value="";
        parentPhone.focus();
        return;
    }
    document.regist.submit();
}

