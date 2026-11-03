package com.raghav.ecommerce.service;

import com.raghav.ecommerce.model.Home;
import com.raghav.ecommerce.model.HomeCategory;

import java.util.List;

public interface HomeService {

    Home creatHomePageData(List<HomeCategory> categories);

}
