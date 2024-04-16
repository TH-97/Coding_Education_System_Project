var contents = document.querySelector("#contents-area")

contents.addEventListener("click",()=>{
   console.log(event.target.parentElement.parentElement)
   event.target.parentElement.parentElement.submit();
});