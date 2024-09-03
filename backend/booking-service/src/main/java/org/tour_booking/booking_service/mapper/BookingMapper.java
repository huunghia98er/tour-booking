package org.tour_booking.booking_service.mapper;

import org.mapstruct.Mapper;
import org.tour_booking.booking_service.models.entity.Booking;
import org.tour_booking.booking_service.models.request.CreateBookingRequest;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking fromRequestToEntity(CreateBookingRequest request);

}
