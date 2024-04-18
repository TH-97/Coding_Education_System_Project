package com.project.geomin.admin.controller;

import com.project.geomin.admin.service.PaymentService;
import com.project.geomin.admin.service.PaymentServiceimpl;
import com.project.geomin.command.PaymentVO;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PaymentController{
    @Autowired
    PaymentService paymentService;
    @PostMapping("/payment/validate")
    public String createPayment(@RequestBody PaymentVO paymentReq){
        System.out.println("들어옴");

        String user_id = paymentReq.getBuyer_name();
        System.out.println(user_id);
        String con_nm = paymentReq.getName();
        String ctb_pay_type = paymentReq.getCtb_pay_type();
        int ctb_money = paymentReq.getAmount();

        if(paymentService.check(user_id) == 0) {
            //만약 구매 이력이 없다면 db에 넣기
            paymentService.insertContentBuy(user_id, con_nm, ctb_pay_type, ctb_money);
        }else if(paymentService.check(user_id) <= 1){
            System.out.println("구매이력이 있습니다");
            PaymentVO status = paymentService.getCtbStatus(user_id);
            if(status.getCtb_status().equals("완료")){
                return "이미 구매 하셨습니다";
            }
        }
        return "구매 완료 되었습니다";
    }
    @PostMapping("/payment/validate/update")
    public void update(@RequestBody PaymentVO paymentReq){
        System.out.println("업데이트 들어옴");
        String user_id = paymentReq.getBuyer_name();
        paymentService.updateStatus(user_id);
    }
}