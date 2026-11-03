package com.raghav.ecommerce.service;


import com.raghav.ecommerce.model.OrderItem;

public interface OrderItemService {

	OrderItem getOrderItemById(Long id) throws Exception;
	


}
