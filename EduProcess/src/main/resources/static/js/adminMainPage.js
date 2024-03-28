
$("#tree").treeview({
  collapsed: true,
  animated: "fast"
  });

  var dayManage = document.querySelector("#dayManage")

  dayManage.addEventListener("click", ()=>{
    var main = document.querySelector("#main");
    main.src = "adminHtml/roomAssignment.html"
  });
  
  var penaltySelect = document.querySelector("#penaltySelect")
  
  penaltySelect.addEventListener("click", ()=>{
  var main = document.querySelector("#main");
  main.src = "penaltySelect.html"
  });

  var mvnAply = document.querySelector("#mvnAply");
  
  mvnAply.addEventListener("click",() =>{
    var main = document.querySelector("#main");
    main.src ="mvnAplySelect.html";
  });
  
   var obAndEarlyConfirm = document.querySelector("#obAndEarlyConfirm");
   
     obAndEarlyConfirm.addEventListener("click",() =>{
    var main = document.querySelector("#main");
    main.src ="obAndEarlyConfirm.html";
  });
  
  var Afloor = document.querySelector("#Afloor");
var Afloor2 = document.querySelector("#Afloor2");
var Afloor3 = document.querySelector("#Afloor3");

  Afloor.addEventListener("click", ()=>{
  var main = document.querySelector("#main");
  var what = event.target.text;
  main.setAttribute("src",`/A1F?what=${what}`)
  });
  Afloor2.addEventListener("click", ()=>{
  var main = document.querySelector("#main");
    var what = event.target.text;
    main.setAttribute("src",`/A2F?what=${what}`)
  });
  Afloor3.addEventListener("click", ()=>{
  var main = document.querySelector("#main");
    var what = event.target.text;
    main.setAttribute("src",`/A3F?what=${what}`)
  });
  //여자관
var Bfloor = document.querySelector("#Bfloor");
var Bfloor2 = document.querySelector("#Bfloor2");
var Bfloor3 = document.querySelector("#Bfloor3");
Bfloor.addEventListener("click", ()=>{
  var main = document.querySelector("#main");
  var what = event.target.text;
  main.setAttribute("src",`/B1F?what=${what}`)
});

Bfloor2.addEventListener("click", ()=>{
  var main = document.querySelector("#main");
  var what = event.target.text;
  main.setAttribute("src",`/B2F?what=${what}`)
});

Bfloor3.addEventListener("click", ()=>{
  var main = document.querySelector("#main");
  var what = event.target.text;
  main.setAttribute("src",`/B3F?what=${what}`)
});

var suggestInquiry = document.querySelector("#suggestInquiry")
suggestInquiry.addEventListener("click" ,()=>{
  var main = document.querySelector("#main");
  var what = event.target.text;
  main.setAttribute("src",`/noticeInquiry.html`)
});



  

  
  
