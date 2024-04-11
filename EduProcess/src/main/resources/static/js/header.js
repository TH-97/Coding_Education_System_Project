var openModalButton =  document.querySelector("#loginBtn");
console.log(openModalButton);
var modal = document.getElementById('modal');
var closeModalButton = document.getElementById('close-modal');

openModalButton.addEventListener('click', () => {

    modal.style.display = "inline";
});

closeModalButton.addEventListener('click', () => {
    modal.style.display = "none"
});

var dropbtn = document.querySelector('.dropbtn');
var loginBtn = document.querySelector("#loginBtn");
if (dropbtn !== null)loginBtn.style.display = "none";
function toggleDropdown() {
  var dropdownContent = document.querySelector('.dropdown-content');
  dropdownContent.style.display = dropdownContent.style.display === 'block' ? 'none' : 'block';
}