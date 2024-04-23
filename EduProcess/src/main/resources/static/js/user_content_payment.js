var IMP = window.IMP;
IMP.init('imp67011510');

var today = new Date();
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours +  minutes + seconds + milliseconds;

function requestPay() {
    var name = document.getElementById("con_nm").textContent;
    var amount = document.getElementById("con_price").textContent.replace("원","");
    //회원아이디
    var buyer_name = document.getElementById("user_id").value;
    if (buyer_name === "") {
        alert("로그인 하세요");
    } else {
        $.ajax({
            url: "http://ec2-52-79-194-111.ap-northeast-2.compute.amazonaws.com:8989/payment/validate/check", // 실제 엔드포인트로 변경
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                buyer_name: buyer_name,
                name: name,
            }),
            success: function(response) {
                if (response === "이미 구매 하셨습니다") {
                    alert(response); // 서버 응답에 따라 경고 표시
                    return; // AJAX 요청 종료
                } else {
                    alert(response);
                    // 이미 구매한 상품이 아닌 경우에만 이하의 코드 실행
                    IMP.request_pay({
                        pg: 'kakaopay',
                        pay_method: 'kakaopay',
                        merchant_uid: "IMP" + makeMerchantUid,
                        name: name,
                        amount: amount,
                        buyer_name: buyer_name,
                        card: {
                            detail: [
                                {card_code: "*", enabled: false}, //모든 카드사 비활성화
                            ]
                        }
                    }, function(rsp) { // callback
                        if (rsp.success) {
                            $.ajax({
                                url: "http://ec2-52-79-194-111.ap-northeast-2.compute.amazonaws.com:8989/payment/validate",
                                method: "POST",
                                contentType: "application/json",
                                data: JSON.stringify({
                                    imp_uid: rsp.imp_uid, // 결제 고유번호
                                    merchant_uid: rsp.merchant_uid, // 주문번호
                                    amount: rsp.paid_amount, //가격
                                    name: rsp.name, // 상품 이름
                                    buyer_name: buyer_name, // 구매자 이름
                                    x3: rsp.pay_method, // 결제 수단
                                }),
                            }).done(function(data) {
                                if (data === "이미 구매 하셨습니다") {
                                    console.log(data);
                                    return;
                                } else {
                                    alert(data);
                                }
                            });
                        } else {
                            alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
                        }
                    });
                }
            },
            error: function(xhr, status, error) {
                alert("에러 발생: " + error); // 에러 메시지 표시
            }
        });
    }
}
//통합 결제
// var IMP2 = window.IMP;
// IMP2.init("imp67011511");
function requestPay2() {
    var name = document.getElementById("con_nm").textContent;
    var amount = document.getElementById("con_price").textContent.replace("원","");
    //회원아이디
    var buyer_name = document.getElementById("user_id").value;
    if (buyer_name === "") {
        alert("로그인 하세요");
    } else {
        $.ajax({
            url: "http://ec2-52-79-194-111.ap-northeast-2.compute.amazonaws.com:8989/payment/validate/check", // 실제 엔드포인트로 변경
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                buyer_name: buyer_name,
                name: name,
            }),
            success: function(response) {
                if (response === "이미 구매 하셨습니다") {
                    alert(response); // 서버 응답에 따라 경고 표시
                    return; // AJAX 요청 종료
                } else {
                    alert(response);
                    // 이미 구매한 상품이 아닌 경우에만 이하의 코드 실행
                    IMP.request_pay({
                        pg : 'html5_inicis',
                        pay_method : 'card',
                        merchant_uid: "IMP"+makeMerchantUid,
                        name : '당근 10kg',
                        amount : 1,
                        buyer_email : 'Iamport@chai.finance',
                        buyer_name : '아임포트 기술지원팀',
                        buyer_tel : '010-1234-5678',
                        buyer_addr : '서울특별시 강남구 삼성동',
                        buyer_postcode : '123-456'
                    }, function(rsp) { // callback
                        if (rsp.success) {
                            $.ajax({
                                url: "http://ec2-52-79-194-111.ap-northeast-2.compute.amazonaws.com:8989/payment/validate",
                                method: "POST",
                                contentType: "application/json",
                                data: JSON.stringify({
                                    imp_uid: rsp.imp_uid, // 결제 고유번호
                                    merchant_uid: rsp.merchant_uid, // 주문번호
                                    amount: rsp.paid_amount, //가격
                                    name: rsp.name, // 상품 이름
                                    buyer_name: buyer_name, // 구매자 이름
                                    x3: rsp.pay_method, // 결제 수단
                                }),
                            }).done(function(data) {
                                if (data === "이미 구매 하셨습니다") {
                                    console.log(data);
                                    return;
                                } else {
                                    alert(data);
                                }
                            });
                        } else {
                            alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
                        }
                    });
                }
            },
            error: function(xhr, status, error) {
                alert("에러 발생: " + error); // 에러 메시지 표시
            }
        });
    }
}