package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentVO {
    private String imp_uid;
    private String merchant_uid;
    private String name;
    private String buyer_name;
    private int amount;
    private String ctb_pay_type;

    //찾을때
    private String ctb_status;

}
