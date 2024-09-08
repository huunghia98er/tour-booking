package org.tour_booking.booking_service.constant;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */
public enum BookingStatus {
    PENDING, // Khi mới tạo booking
    CREATED, // Sau khi đã đặt chỗ thành công
    CONFIRMED, // Sau khi đã thanh toán và xác nhận bởi merchant
    COMPLETED, // Khi booking đã hoàn thành
    EXPIRED, // Khi booking đã hết hạn

    REJECTED_BY_MERCHANT, // Khi booking bị từ chối
    CANCELLED_BY_CUSTOMER, // Khi khách hàng hủy booking

    WAITING_FOR_PAYMENT, // Sau khi tạo request thanh toán
    WAITING_FOR_CONFIRMATION, // Sau khi đã thanh toán và chờ xác nhận từ merchant
}
