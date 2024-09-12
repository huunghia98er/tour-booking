package org.tour_booking.payment_service.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/11/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VNPayCheckout {

    String vnp_Amount;
    String vnp_BankCode;
    String vnp_BankTranNo;
    String vnp_CardType;
    String vnp_OrderInfo;
    String vnp_PayDate;
    String vnp_ResponseCode;
    String vnp_TmnCode;
    String vnp_TransactionNo;
    String vnp_TransactionStatus;
    String vnp_TxnRef;
    String vnp_SecureHash;

}
