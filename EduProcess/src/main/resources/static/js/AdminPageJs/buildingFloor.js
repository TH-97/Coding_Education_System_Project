var table = document.querySelector("#ListTable");
table.addEventListener("click", ()=>{
    if(event.target.tagName != "TD")
        return;
    var  bldg_cd= event.target.parentElement.children[0].innerText;

    var main = document.querySelector("#main")

    main.setAttribute('src', `/room?bldg_cd=${bldg_cd}`);

    var child = document.querySelectorAll(".className");
    for(var i of child){

        if(i.children[0].innerText === event.target.parentElement.children[0].innerText){
            console.log("들어왔습니다");
            i.classList.toggle("active");
        }else {
            console.log("삭제되었습니다");
            i.classList.remove("active");
        }
    }


});