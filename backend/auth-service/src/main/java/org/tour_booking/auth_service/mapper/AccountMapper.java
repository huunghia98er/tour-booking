package org.tour_booking.auth_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.tour_booking.auth_service.model.entity.Account;
import org.tour_booking.auth_service.model.entity.Permission;
import org.tour_booking.auth_service.model.request.AccountCreationRequest;
import org.tour_booking.auth_service.model.request.AccountUpdateRequest;
import org.tour_booking.auth_service.model.response.AccountResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountCreationRequest request);

    AccountResponse toDto(Account account);

    @Mapping(target = "permissions", source = "permissions")
    void updateAccount(@MappingTarget Account account, AccountUpdateRequest request);

    default Set<Permission> mapPermissions(Set<String> permissions) {
        if (permissions == null) {
            return null;
        }
        return permissions.stream()
                .map(this::mapPermission)
                .collect(Collectors.toSet());
    }

    Permission mapPermission(String permission);

}
