package com.project.geomin.admin.service;

import com.project.geomin.command.PaymentVO;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int check(String user_id);
    public PaymentVO getCtbStatus(String user_id);
    public void updateStatus(String user_id);
    public void insertContentBuy(@Param("user_id") String user_id,
                                   @Param("con_nm") String con_nm,
                                   @Param("ctb_pay_type") String ctb_pay_type,
                                   @Param("ctb_money") int ctb_money);


}