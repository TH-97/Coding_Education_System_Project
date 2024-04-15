 function addExperience() {window.open('/board/regiPopup', 'Popup', 'width=300,height=300,top=300,left=500');}
         
 // 팝업 창으로부터 데이터를 받는 함수
function receiveDataFromPopup(resumeData) {
// 이력서 정보를 받아서 부모 페이지에 표시
   var resumeContainer = document.getElementById('experienceList');
    var resumeHTML = '<div class=resume>';
    resumeHTML += '<p class=company>회사명: ' + resumeData.company + '</p>';
   resumeHTML += '<p class=position>직책: ' + resumeData.position + '</p>';
     resumeHTML += '<p class=duration>기간: ' + resumeData.duration + '</p>';
     resumeHTML += '</div>';
  resumeContainer.innerHTML += resumeHTML;
      }
function passDataToParent(data) {
 // 팝업 창에서 데이터를 받는 함수 호출
  receiveDataFromPopup(data);
  console.log("passdatatopar")
   }

function sendExperienceList() {
    // 경력 정보를 담을 리스트 생성
    var experienceList = [];
    console.log('experienclist')
    // 경력 정보들을 DOM에서 가져와서 리스트에 추가
    var experiences = document.querySelectorAll('.resume');
    experiences.forEach(function(experience) {
        var company = experience.querySelector('.company').innerText;
        var position = experience.querySelector('.position').innerText;
        var duration = experience.querySelector('.duration').innerText;

        // 경력 정보 객체 생성 후 리스트에 추가
        var experienceData = {
            company: company,
            position: position,
            duration: duration
        };
        experienceList.push(experienceData);
    console.log(experienceData.company +'                 s')
    console.log(experienceList[0].company +'                 d')
    });
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/board/regist_educator1', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(experienceList));
}
function submitForm() {
    // 경력 정보를 서버로 보내는 함수 호출
    console.log("submtfdsafs")
    sendExperienceList();

    // 폼의 제출을 수동으로 처리
    var form = document.getElementById("regist_btn");
    form.submit();
}
