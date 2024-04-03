<!--  모달창 -->
var regiBtn = document.querySelector("#regiBtn");
var con_modal = document.querySelector("#con-modal")
console.log(regiBtn)
regiBtn.addEventListener("click", () => {
    con_modal.style.display = "inline";
});
var closeModalButton = document.getElementById('con-close-modal');
closeModalButton.addEventListener('click', () => {
    con_modal.style.display = "none"
});
// 모달창 끝
//추가하기
const addButton = document.getElementById('addButton'); // 버튼 요소 가져오기
const tableBody = document.getElementById('file_div'); // 테이블 본문 요소 가져오기

addButton.addEventListener('click', function () {
    const newRow = tableBody.insertRow(); // 새로운 행 추가

    const videoCell = newRow.insertCell(); // '영상' 셀 추가
    videoCell.textContent = '영상';

    const numberCell = newRow.insertCell(); // '번호' 셀 추가
    var index = (filediv.children.length - 1) + 1
    numberCell.textContent = index; // 셀 내용 설정

    const fileCell = newRow.insertCell(); // '파일' 셀 추가
    fileCell.innerHTML = '<div class="file_box">드래그 해주세요</div>'; // 셀 내용 설정 (HTML 포함)
});


//drop box
const input_filename = document.querySelector('.file_name');
const dropbox = document.querySelectorAll('.file_box');
let file_data = [];
let filediv = document.querySelector("#file_div")
//박스 안에 drag 하고 있을 때

filediv.addEventListener('dragover', (event) => {
    // if(event.target.classList !== "file_box") return;
    event.preventDefault();
    // i.classList.add('highlight');
});

filediv.addEventListener('dragleave', (event) => {
    // if(event.target.classList !== "file_box") return;
    event.preventDefault();
    // i.classList.remove('highlight');
});

filediv.addEventListener('drop', (event) => {
    console.log(event.target)
    // if(event.target.classList !== "FILE_BOX") return;
    event.preventDefault();

    //데이터 크기 검사
    let byteSize = event.dataTransfer.files[0].size;
    let maxSize = 1024;

    if (byteSize / 1000000 > maxSize) {
        alert("파일은 최대 1024MB이하만 허용됩니다");
        return;
    } else {
        //백그라운드 색상변경
        //파일 이름을 text로 표시
        let filename = event.dataTransfer.files[0].name;
        event.target.parentElement.parentElement.children[2].innerText = filename;
        console.log(event.target.parentElement.parentElement.children[2])




        //파일 데이터를 변수에 저장
        // console.log(filediv.children.length)
        // file_data[index] = event.dataTransfer.files[0];
        console.log(file_data)
    }

});

//드롭 썸내일
const thumbnailbox = document.querySelector('.thumbnail_box');
thumbnailbox.addEventListener('dragover', (event) => {
    event.preventDefault();
    thumbnailbox.classList.add('highlight');
});

thumbnailbox.addEventListener('dragleave', (event) => {
    thumbnailbox.classList.remove('highlight');
});

thumbnailbox.addEventListener('drop', (event) => {
    event.preventDefault();

    //데이터 크기 검사
    let byteSize = event.dataTransfer.files[0].size;
    let maxSize = 1024;

    if (byteSize / 1000000 > maxSize) {
        alert("파일은 최대 1024MB이하만 허용됩니다");
        return;
    } else {
        //백그라운드 색상변경
        //파일 이름을 text로 표시
        let filename = event.dataTransfer.files[0].name;
        thumbnailbox.innerHTML = filename;

        //파일 데이터를 변수에 저장
        file_data[0] = event.dataTransfer.files[0];
        console.log(file_data)
    }

});