var files = document.getElementById("files")

files.addEventListener("click", () => {
    console.log(event.currentTarget.children)
    //  console.log(event.target.textContent)
    for (var i of event.currentTarget.children) {
        if (i.children[1].textContent === event.target.textContent) {
            console.log(i.children[0].src)
            window.open(`/admin/video?src=${i.children[0].src}`, "", "width=800,height=480,top=50%,left=50%")
        }
    }
});

var review = document.getElementById("review-write");
var reviewBtn = document.getElementById("review-btn");

reviewBtn.addEventListener("click", () => {
    var con_nm = document.getElementById("con_nm").textContent;
    // HTML 코드 문자열 생성
    const formHtml = `<form action="/admin/reviewSave" class="mb-3" name="myform" id="myform" method="post">
  <fieldset>
  <button id="reviewSave" style="margin-left: 20px; width: 60px; height: 30px; border: none; background-color: #5da886;border-radius: 4px">저장하기</button>
    <span class="text-bold">별점을 선택해주세요</span>
    <input type="radio" name="reviewStar" value="5" id="rate1"><label for="rate1">★</label>
    <input type="radio" name="reviewStar" value="4" id="rate2"><label for="rate2">★</label>
    <input type="radio" name="reviewStar" value="3" id="rate3"><label for="rate3">★</label>
    <input type="radio" name="reviewStar" value="2" id="rate4"><label for="rate4">★</label>
    <input type="radio" name="reviewStar" value="1" id="rate5"><label for="rate5">★</label>
  </fieldset>
  <div>
    <textarea name="textarea" class="col-auto form-control" type="text" id="reviewContents" placeholder="좋은 수강평을 남겨주시면 큰 힘이 됩니다"></textarea>
  </div>
  <input type="hidden" value="${con_nm}" name="content_name">
</form>`;
    // 기존 내용을 지우고 새로 생성한 HTML 코드를 삽입
    review.innerHTML = formHtml;
    window.scrollTo({
            top: review.offsetTop,
            behavior: "smooth" // 부드러운 스크롤 효과
        }
    );
});

var reviewSave = document.getElementById("reviewSave")
var myform = document.getElementById("myform")
reviewSave.addEventListener("click",()=>{
    myform.submit();
});

