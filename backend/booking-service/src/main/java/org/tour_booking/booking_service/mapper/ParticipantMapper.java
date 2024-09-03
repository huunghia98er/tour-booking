package org.tour_booking.booking_service.mapper;

import org.mapstruct.Mapper;
import org.tour_booking.booking_service.models.entity.Participants;
import org.tour_booking.booking_service.models.request.ParticipantsRequest;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/09/03
 */

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    List<Participants> fromRequestToEntity(List<ParticipantsRequest> participants);
}
