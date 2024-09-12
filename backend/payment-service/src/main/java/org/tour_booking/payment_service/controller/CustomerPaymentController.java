package org.tour_booking.payment_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tour_booking.payment_service.model.VNPayCheckout;
import org.tour_booking.payment_service.service.VNPayPayment;

import java.io.IOException;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/11/2024
 */
@RestController
@RequestMapping("/payment/customer")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerPaymentController {

    VNPayPayment vnPayPayment;

    @PostMapping("/create")
    public String createPayment(@RequestParam(name = "amount") Double amount,
                                @RequestParam(name = "bookingId") String bookingID) {
        return vnPayPayment.createPaymentApi(amount, bookingID);
    }

    @PostMapping("/checkPayment")
    public ResponseEntity<String> checkPayment(@RequestBody VNPayCheckout checkout,
                                            HttpServletRequest servletRequest) throws IOException {
        return ResponseEntity.ok(vnPayPayment.checkPayment(checkout, servletRequest));
    }

}
