package com.example.saturn.models.dao;

import com.example.saturn.models.PaymentMethod;
import com.example.saturn.models.enums.PaymentMethodEnum;
public class PaymentMethodDAO {

    private static PaymentMethod bankMethod = new PaymentMethod(PaymentMethodEnum.BANK, "Chuyển khoản");
    private static PaymentMethod codMethod = new PaymentMethod(PaymentMethodEnum.COD, "Giao tiền khi nhận hàng");

    public static PaymentMethod getPaymentInfo(String paymentCode) {
        var listEnumValues = PaymentMethodEnum.values();
        switch (PaymentMethodEnum.valueOf(paymentCode)) {
            case BANK:
                return bankMethod;
            case COD:
                return codMethod;
            default:
                return null;
        }
    }
}
