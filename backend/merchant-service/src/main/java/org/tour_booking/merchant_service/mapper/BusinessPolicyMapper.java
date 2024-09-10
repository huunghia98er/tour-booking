package org.tour_booking.merchant_service.mapper;

import org.mapstruct.Mapper;
import org.tour_booking.merchant_service.model.entity.BusinessPolicyEntity;
import org.tour_booking.merchant_service.model.request.BusinessPolicyCreationRequest;
import org.tour_booking.merchant_service.model.request.BusinessPolicyUpdateRequest;
import org.tour_booking.merchant_service.model.response.BusinessPolicyDTO;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/1/2024
 */
@Mapper(componentModel = "spring")
public interface BusinessPolicyMapper {

    BusinessPolicyEntity createReqToEntity(final BusinessPolicyCreationRequest request);

    BusinessPolicyEntity updateReqToEntity(final BusinessPolicyUpdateRequest request);

    BusinessPolicyDTO toDto(final BusinessPolicyEntity entity);

}
