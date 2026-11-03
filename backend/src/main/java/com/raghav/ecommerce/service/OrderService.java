package com.raghav.ecommerce.service;

import com.raghav.ecommerce.domain.OrderStatus;
import com.raghav.ecommerce.exception.OrderException;
import com.raghav.ecommerce.model.Address;
import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.model.*;
import com.raghav.ecommerce.model.Order;

import java.util.List;
import java.util.Set;

public interface OrderService {
	
	public Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public List<Order>getShopsOrders(Long sellerId);

	public Order updateOrderStatus(Long orderId,
								   OrderStatus orderStatus)
			throws OrderException;
	
	public void deleteOrder(Long orderId) throws OrderException;

	Order cancelOrder(Long orderId,User user) throws OrderException;
	
}
