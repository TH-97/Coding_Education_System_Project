package com.project.geomin.admin.service;

import com.project.geomin.command.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceimpl implements PaymentService{
    @Autowired
    public PaymentMapper paymentMapper;

    @Override
    public int check(String user_id,String con_nm) {
        return paymentMapper.check(user_id,con_nm);
    }

    @Override
    public PaymentVO getCtbStatus(String user_id) {
        return paymentMapper.getCtbStatus(user_id);
    }

    @Override
    public void updateStatus(String user_id) {
        paymentMapper.updateStatus(user_id);
    }

    @Override
    public void insertContentBuy(String user_id, String con_nm, String ctb_pay_type, int ctb_money) {
         paymentMapper.insertContentBuy(user_id,con_nm,ctb_pay_type,ctb_money);
    }
}
