package com.raghav.ecommerce.controller;

import com.raghav.ecommerce.dto.RevenueChart;
import com.raghav.ecommerce.exception.SellerException;
import com.raghav.ecommerce.model.Seller;
import com.raghav.ecommerce.service.RevenueService;
import com.raghav.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/revenue/chart")
public class RevenueController {
    private final RevenueService revenueService;
    private final SellerService sellerService;

    @GetMapping()
    public ResponseEntity<List<RevenueChart>> getRevenueChart(
            @RequestParam(defaultValue = "today") String type,
            @RequestHeader("Authorization") String jwt) throws SellerException {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<RevenueChart> revenue = revenueService
                .getRevenueChartByType(type, seller.getId());
        return ResponseEntity.ok(revenue);
    }

}
