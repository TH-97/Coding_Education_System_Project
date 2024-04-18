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

        paymentService.insertContentBuy(user_id, con_nm, ctb_pay_type, ctb_money);


        return "구매 완료 되었습니다";
    }
    @PostMapping("/payment/validate/check")
    public String update(@RequestBody PaymentVO paymentReq){
        System.out.println("업데이트 들어옴");
        String user_id = paymentReq.getBuyer_name();
        String con_nm = paymentReq.getName();
        System.out.println("유저아이디" + user_id);
        if(paymentService.check(user_id,con_nm) <1){
            System.out.println("구매 하지 않음");
            return "구매 시작하겠습니다";
        }else{

            return "이미 구매 하셨습니다";

        }

    }
}