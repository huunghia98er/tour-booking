import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tour_booking.booking_service.BookingServiceApplication;
import org.tour_booking.booking_service.models.entity.Booking;
import org.tour_booking.booking_service.models.request.BookingRequest;
import org.tour_booking.booking_service.repository.BookingRepository;
import org.tour_booking.booking_service.service.BookingService;
import org.tour_booking.booking_service.utils.BookingUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/09/15
 */

@SpringBootTest(classes = BookingServiceApplication.class)
class BookingServiceTest {
    @MockBean
    private BookingRepository bookingRepository;
    @MockBean
    private BookingUtils bookingUtils;
    @Autowired
    private BookingService bookingService;

    @Test
    void createBooking() {
        BookingRequest request = BookingRequest.builder()
                .customerId(1)
                .departureDate(LocalDate.now())
                .totalPrice(1000)
                .createdBy("HuuNghia")
                .build();

        String bookingCode = "STB123456";

        Booking booking = Booking.builder()
                .bookingCode(bookingCode)
                .build();

        when(bookingUtils.generateBookingCode()).thenReturn(bookingCode);
        when(bookingUtils.convertRequestToEntity(request)).thenReturn(booking);
        when(bookingRepository.save(booking)).thenReturn(booking);

        String orderCodeAfterSave = bookingService.createBooking(request);

        assertEquals(bookingCode, orderCodeAfterSave);
    }

    @Test
    void cancelBooking() {
        String bookingCode = "STB123456";
        Booking booking = Booking.builder()
                .bookingCode(bookingCode)
                .build();

        when(bookingRepository.findByBookingCode(bookingCode)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(booking)).thenReturn(booking);

        boolean isUpdated = bookingService.cancelBooking(bookingCode);

        assertTrue(isUpdated);
    }
}
