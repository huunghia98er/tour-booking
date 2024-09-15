import api.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.tour_booking.booking_service.constant.BookingStatus;
import org.tour_booking.booking_service.controller.BookingController;
import org.tour_booking.booking_service.models.entity.Booking;
import org.tour_booking.booking_service.models.request.BookingRequest;
import org.tour_booking.booking_service.repository.BookingRepository;
import org.tour_booking.booking_service.service.BookingService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @Author: mosdvc
 * @LastModified: 2024/09/15
 */

class BookingControllerTest {
    @Mock
    private BookingService bookingService;
    @Mock
    private BookingRepository bookingRepository;
    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_success() {
        String bookingCode = "STB123456";

        BookingRequest request = BookingRequest.builder()
                .customerId(1)
                .departureDate(LocalDate.now())
                .totalPrice(1000)
                .createdBy("HuuNghia")
                .build();

        Booking booking = Booking.builder()
                .bookingCode(bookingCode)
                .build();

        when(bookingService.createBooking(any())).thenReturn(bookingCode);
        when(bookingRepository.save(any())).thenReturn(booking);

        ApiResponse<String> apiResponse = bookingController.createBooking(request);

        ApiResponse<String> expected = ApiResponse.<String>builder()
                .code(201)
                .message("Create booking successfully")
                .data("STB123456")
                .build();

        assertEquals(expected, apiResponse);
    }

    @Test
    void createBooking_failed() {
        BookingRequest request = BookingRequest.builder()
                .customerId(1)
                .departureDate(LocalDate.now())
                .totalPrice(1000)
                .createdBy("HuuNghia")
                .build();

        when(bookingService.createBooking(any())).thenReturn(null);
        when(bookingRepository.save(any())).thenReturn(null);

        ApiResponse<String> apiResponse = bookingController.createBooking(request);

        ApiResponse<String> expected = ApiResponse.<String>builder()
                .code(400)
                .message("Create booking failed")
                .build();

        assertEquals(expected, apiResponse);
    }

    @Test
    void cancelBooking_success() {
        String bookingCode = "STB123456";

        Booking booking = Booking.builder()
                .bookingCode(bookingCode)
                .bookingStatus(BookingStatus.CANCELLED_BY_CUSTOMER)
                .build();

        when(bookingService.cancelBooking(any())).thenReturn(true);
        when(bookingRepository.findByBookingCode(any())).thenReturn(Optional.of(booking));

        ApiResponse<String> result = bookingController.cancelBooking(bookingCode);

        ApiResponse<String> expected = ApiResponse.<String>builder()
                .code(200)
                .message("Cancel booking successfully")
                .build();

        assertEquals(expected, result);
    }

    @Test
    void cancelBooking_failed() {
        String bookingCode = "STB123456";

        Booking booking = Booking.builder()
                .bookingCode(bookingCode)
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();

        when(bookingService.cancelBooking(bookingCode)).thenReturn(false);
        when(bookingRepository.findByBookingCode(any())).thenReturn(Optional.of(booking));

        ApiResponse<String> result = bookingController.cancelBooking(bookingCode);

        ApiResponse<String> expected = ApiResponse.<String>builder()
                .code(400)
                .message("Cancel booking failed")
                .build();

        assertEquals(expected, result);
    }
}
