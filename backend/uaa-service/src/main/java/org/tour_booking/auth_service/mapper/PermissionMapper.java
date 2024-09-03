package org.tour_booking.auth_service.mapper;

import org.mapstruct.Mapper;
import org.tour_booking.auth_service.model.entity.Permission;
import org.tour_booking.auth_service.model.request.PermissionRequest;
import org.tour_booking.auth_service.model.response.PermissionResponse;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequest request);

    PermissionResponse toDto(Permission permission);
}
