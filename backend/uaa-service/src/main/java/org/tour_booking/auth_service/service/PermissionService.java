package org.tour_booking.auth_service.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tour_booking.auth_service.mapper.PermissionMapper;
import org.tour_booking.auth_service.model.entity.PermissionEntity;
import org.tour_booking.auth_service.model.request.PermissionRequest;
import org.tour_booking.auth_service.model.response.PermissionResponse;
import org.tour_booking.auth_service.repository.PermissionRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepo;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        PermissionEntity permission = permissionMapper.toEntity(request);
        permission = permissionRepo.save(permission);
        return permissionMapper.toDto(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepo.findAll();
        return permissions.stream().map(permissionMapper::toDto).toList();
    }

    public void delete(String permission) {
        permissionRepo.deleteByName(permission);
    }

    Set<PermissionEntity> findAllByNames(Set<String> permissions) {
        return permissionRepo.findAllByNames(permissions);
    }

}
