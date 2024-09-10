package org.tour_booking.merchant_service.mapper;

import org.mapstruct.Mapper;
import org.tour_booking.merchant_service.model.entity.MerchantEntity;
import org.tour_booking.merchant_service.model.request.RegisterMerchantRequest;
import org.tour_booking.merchant_service.model.response.AdminListMerchantResponse;
import org.tour_booking.merchant_service.model.response.RegisterMerchantResponse;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/1/2024
 */
@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantEntity toEntity(final RegisterMerchantRequest request);

    RegisterMerchantResponse toRegisterMerchantResponse(final MerchantEntity entity);

    AdminListMerchantResponse toAdminListMerchantResponse(final MerchantEntity entity);

}
