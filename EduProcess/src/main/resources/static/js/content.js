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

//행 추가하기
const addButton = document.getElementById('addButton'); // 버튼 요소 가져오기
addButton.addEventListener("click", () => {


    var index = (filediv.children.length - 1) + 1

    var container = document.getElementById('file_div');
    var newContent = document.createElement('span');
    newContent.className = 'content-write';
    newContent.innerHTML = `
                    <div style="display: inline" class="th1">영상</div>
                    <div class="v-index" style="visibility: hidden;">${index}</div>
                    <input class="file_name" type="text" placeholder="영상의 이름">
                    <div class="file_area">
                        <div class="file_box">영상을 드래그 해주세요</div>
                    </div>
            `;
    container.appendChild(newContent);
    console.log(container);

});
//행 지우기
var pop = document.getElementById("popButton")
pop.addEventListener("click", () => {
    const list = document.getElementById('file_div');
    const items = list.getElementsByTagName('span'); // 전체 목록 항목 가져오기
    console.log(items);
    const lastItem = items[items.length - 1]; // 마지막 항목 가져오기
    let index = lastItem.children[1].textContent
    file_data.splice(index);
    console.table(file_data);
    list.removeChild(lastItem); // 마지막 항목 제거
});


//drop box
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
    // console.log(event.target)
    if (event.target.parentElement.tagName !== "DIV") return;
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
        event.target.parentElement.parentElement.children[3].innerText = filename;

        var index;
        for (var i of event.currentTarget.children) {
            console.log(i);
            // console.log(i.children[2].textContent)
            if (i.children[3].textContent === filename) {
                index = i.children[1].textContent
            }
        }
        //파일 데이터를 변수에 저장
        file_data[index] = event.dataTransfer.files[0];
        console.table(file_data)
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
    }

});

//저장하기 클릭시 데이터 넘겨주기
let file_data_name = [];
var regi = document.getElementById('regi')
regi.addEventListener("click", ()=>{
    let file_name = document.querySelectorAll(".file_name")
    console.log(file_name.value)
    file_data_name[0] = document.querySelector("#thumbnail_name").value;
    for (var i of file_name){
        file_data_name.push(i.value);
    }
    var con_nm = document.getElementById('con_nm')
    var cate_no = document.getElementById('cate_no')
    var con_price = document.getElementById('con_price')
    var con_discount = document.getElementById('con_discount')
    var con_description = document.getElementById('con_description')
    var con_lv = document.getElementById('con_lv')


    let formData = new FormData();
    for(var file of file_data){
        formData.append('file_data' , file)
    }
    for(var fileName of file_data_name){
        formData.append('file_data_name', fileName)
    }
    formData.append('con_nm',con_nm.value);
    formData.append('cate_no',cate_no.value);
    formData.append('con_price',con_price.value);
    formData.append('con_discount',con_discount.value);
    formData.append('con_description',con_description.value);
    formData.append('con_lv',con_lv.value);

    const loading = document.querySelector('#loading');
    loading.style.display = 'block';

    fetch('/cloudUpload', {
        method: 'post',
        body: formData}

    )
        .then(response => response.text() )
        .then(data => {
            let result = confirm(data);
            if(result){
                loading.style.display = 'none';
                con_modal.style.display ="none";
            }
        })
        .catch(err => alert('업로드에 실패했습니다 빈칸을 확인해주세요') )
        con_modal.style.display ="none";

});

//삭제 기능
