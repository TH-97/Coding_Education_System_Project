var table = document.querySelector("#ListTable");
table.addEventListener("click", ()=>{
    //이것 말고는 클릭 되지않게 하겠다
    if(event.target.tagName != "TD")
        return;

    var  rm_cd = event.target.parentElement.children[0].innerText;
    console.log(rm_cd)

    //background 색 바꾸기
    var child = document.querySelectorAll(".className");
    for(var i of child){
        if(i.children[0].innerText === event.target.parentElement.children[0].innerText){
            i.classList.toggle("active");
        }else {
            i.classList.remove("active");
        }
    }
    var stud_no = window.parent.parent.document.querySelector(".className.active").children[0];
    var name = window.parent.parent.document.querySelector(".className.active").children[1];

    modify.innerHTML = "    <form action=\"/modify\" method=\"post\">\n" +
        "      <input type=\"text\" name=\"stud_no\" value=\"" + stud_no.textContent + "\" placeholder=\"학번\">" +
        "      <input type=\"text\" name=\"name\" value=\"" + name.textContent + "\" placeholder=\"이름\">" +
        "      <input type=\"text\" name=\"altmnt_rm_cd\" value=\"" + rm_cd + "\" placeholder=\"배정호실\">" +
        "       <button type=\"submit\">저장하기</button>\n" +
        "    </form>";
});

var modify = document.querySelector("#ModifyBtn")

