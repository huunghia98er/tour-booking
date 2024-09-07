package org.tour_booking.auth_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.tour_booking.auth_service.model.entity.AccountEntity;
import org.tour_booking.auth_service.model.entity.PermissionEntity;
import org.tour_booking.auth_service.model.request.AccountCreationRequest;
import org.tour_booking.auth_service.model.request.AccountUpdateRequest;
import org.tour_booking.auth_service.model.response.AccountResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity toEntity(AccountCreationRequest request);

    @Mapping(target = "permissions", source = "permissions")
    AccountResponse toDto(AccountEntity account);

    @Mapping(target = "permissions", source = "permissions")
    void updateAccount(@MappingTarget AccountEntity account, AccountUpdateRequest request);

    default Set<PermissionEntity> mapPermissions(Set<String> permissions) {
        if (permissions == null) {
            return null;
        }
        return permissions.stream()
                .map(this::mapPermission)
                .collect(Collectors.toSet());
    }

    PermissionEntity mapPermission(String permission);

}
