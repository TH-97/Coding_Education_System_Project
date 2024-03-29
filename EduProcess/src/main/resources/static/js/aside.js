var side = document.querySelector("#side");
side.addEventListener("click", ()=>{
  var menu = document.querySelectorAll(".menu")
  if(event.target.tagName === 'li') return;
  for(var vo of menu ){
    if(vo.textContent === event.target.textContent){
      event.target.classList.add("active");
    }else{
      vo.classList.remove("active");
    }
  }
});