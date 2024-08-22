package org.tour_booking.order_service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tour_booking.order_service.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

}
