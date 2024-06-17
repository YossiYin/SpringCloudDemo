package com.yen.dto.other;

import lombok.Data;

/**
 * @author Yhx
 * @date 2024/6/17 16:32
 */
@Data
public class Message {

    private int cashNum;
    private double cashMoney;
    private double couponTotalDiscount;
    private int couponTotalNumber;
    private double disTotalMoney;
    private double firstTotalMoney;
    private int id;
    private double lastTotalMoney;
    private int storeId;
    private String signIn;
    private String signOut;
    private int status;
    private int totalNum;
    private int wxNum;
    private double wxMoney;
    private int zfbNum;
    private double zfbMoney;

}
