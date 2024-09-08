package org.tour_booking.merchant_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
public interface CloudService {

    Boolean upload(String merchantId, List<MultipartFile> file);

}
