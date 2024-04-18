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

// var reviewSave = document.getElementById("reviewSave")
// var myform = document.getElementById("myform")
// reviewSave.addEventListener("click",()=>{
//     myform.submit();
// });
//결제
var IMP = window.IMP;
IMP.init("imp81710247");

var today = new Date();
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours +  minutes + seconds + milliseconds;

function requestPay() {
    var name = document.getElementById("con_nm").textContent;
    var amount = document.getElementById("con_price").textContent.replace("원","");
    console.log(amount)
    //회원아이디
    var buyer_name =document.getElementById("user_id").value;
    if(buyer_name === ""){
        alert("로그인 하세요")
    }else {
        IMP.request_pay({
            pg: 'kakaopay',
            pay_method: 'kakaopay',
            merchant_uid: "IMP" + makeMerchantUid,
            name: name,
            amount: amount,
            // buyer_name : buyer_name,
            card: {
                detail: [
                    {card_code: "*", enabled: false},     //모든 카드사 비활성화
                ]
            }
        }, function (rsp) { // callback
            if (rsp.success) {
                $.ajax({
                    url: "http://localhost:8989/payment/validate",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({
                        imp_uid: rsp.imp_uid,            // 결제 고유번호
                        merchant_uid: rsp.merchant_uid,   // 주문번호
                        amount: rsp.paid_amount, //가격
                        name: rsp.name, // 상품 이름
                        buyer_name:buyer_name, // 구매자 이름
                        x3: rsp.pay_method, // 결제 수단
                    }),
                }).done(function (data) {
                    if (data === "이미 구매 하셨습니다"){
                        console.log(data)
                        return;
                    }else {
                        $.ajax({
                            url: "http://localhost:8989/payment/validate/update", // 실제 엔드포인트로 변경
                            method: "POST",
                            data: JSON.stringify({
                                buyer_name: buyer_name,
                            }),
                            success: function(response) {
                                alert(response); // 서버 응답에 따라 경고 표시
                            },
                            error: function(xhr, status, error) {
                                alert("에러 발생: " + error); // 에러 메시지 표시
                            }
                        });
                        alert(data);
                    }
                });
            } else {
                alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
            }
        });
    }
}
