package com.youcloud.mbf.common.constant;

import com.youcloud.mbf.common.util.DateUtil;

public class MbfFileConstant {

    private MbfFileConstant() {}

    public static final String SENDER_CODE = "FZ";
    public static final String TPP_IDENTIFIER = "555";

    public static final Character VERSION = 'V';
    public static final String EXTENSION = "csv";

    public static final Integer USER_IDENTIFICATION_NUMBER = 123456;
    public static final String PROCESSING_DATE_DDMMYY = DateUtil.getCurrentDayInDDMMYY();

    // FileBody
    public static final Character TAX_INDICATOR = ' ';
    public static final String TRACE_BSB_NUMBER = "123-111";
    public static final String TRACE_ACCOUNT_NUMBER = "TOUCH2PAY";
    public static final String REMITTER_NAME = "TOUCH2PAY";
    public static final String WITH_HOLDING_AMOUNT = "        ";

}
