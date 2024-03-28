var openModalButton =  document.querySelector("iframe").contentDocument.querySelector("#loginBtn");
console.log(openModalButton);
var modal = document.getElementById('modal');
var closeModalButton = document.getElementById('close-modal');

openModalButton.addEventListener('click', () => {
  
  modal.style.display = "inline";
});


closeModalButton.addEventListener('click', () => {
  modal.style.display = "none"
});