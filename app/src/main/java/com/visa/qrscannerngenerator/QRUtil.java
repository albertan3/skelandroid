package com.visa.qrscannerngenerator;

import com.google.android.gms.common.api.CommonStatusCodes;

/**
 * Created by ntelukun on 10/12/16.
 */
public class QRUtil {

    public static final int RC_BARCODE_CAPTURE = 9002;
    public static final int SUCCESS = CommonStatusCodes.SUCCESS;
    public static final String BARCODE_VALUE = "BarCodeData";
    public static final String AutoFocus = "AutoFocus";
    public static final String UseFlash = "UseFlash";

    public static String getStatusCodeString(int resultCode){
        return CommonStatusCodes.getStatusCodeString(resultCode);
    }


}
