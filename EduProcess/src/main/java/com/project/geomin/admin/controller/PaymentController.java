package com.project.geomin.admin.controller;

import com.project.geomin.command.PaymentVO;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PaymentController{

//    private final ReservationService reservationService;

//    @PostMapping("/payment/validate")
//    public Response<PaymentRes> createPayment(@RequestBody PaymentReq paymentReq){
//
//        PaymentRes paymentRes = reservationService.createReservation(paymentReq);
//        return new Response<>(paymentRes);
//    }
    @PostMapping("/payment/validate")
    public void createPayment(@RequestBody PaymentVO paymentReq){
        System.out.println("들어옴");
        System.out.println(paymentReq);
    }
}