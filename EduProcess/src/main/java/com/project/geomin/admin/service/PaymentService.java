//package com.project.geomin.admin.service;
//
//import com.siot.IamportRestClient.IamportClient;
//import com.siot.IamportRestClient.exception.IamportResponseException;
//import com.siot.IamportRestClient.request.CancelData;
//import com.siot.IamportRestClient.response.IamportResponse;
//import com.siot.IamportRestClient.response.Payment;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.services.iam.model.IamException;
//
//import java.io.IOException;
//
//@Service
//public class PaymentService {
//    private final IamportClient iamportClient;
//    @Value("${payment_api_key}")
//    private String API_KEY;
//    @Value("${payment_api_access_key}")
//    private String API_SECRET;
//
//
//    public PaymentService() {
//        this.iamportClient = new IamportClient(API_KEY, API_SECRET);
//    }
//
//    public PaymentRes createPayment(PaymentReq paymentReq)
//            throws IamportResponseException, IOException {
//        //DB에 imp_uid나 merchant_uid가 중복되었는지 확인
//        checkDuplicatePayment(paymentReq);
//
//        //DB에 있는 금액과 사용자가 결제한 금액이 같은지 확인
//        int amountForPay = fromDB; //db에서 가져온 금액
//        IamportResponse<Payment> iamResponse = iamportClient.paymentByImpUid(paymentReq.getImpUid());
//        int paidAmount = iamResponse.getResponse().getAmount(); //사용자가 결제한 금액
//
//        if (paidPrice != priceToPay) {
//            // 금액이 다르면 결제 취소
//            IamportResponse<Payment> response = iamportClient.paymentByImpUid(postReservationReq.getImpUid());
//            CancelData cancelData = createCancelData(response, FULL_REFUND);
//            iamportClient.cancelPaymentByImpUid(cancelData);
//
//            throw new IamException();
//        }
//
//        return PaymentRes(new PaymentRes(payment.getId()));
//    }
//
//}