package com.raghav.ecommerce.service;

import com.raghav.ecommerce.model.Seller;
import com.raghav.ecommerce.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport( SellerReport sellerReport);

}
