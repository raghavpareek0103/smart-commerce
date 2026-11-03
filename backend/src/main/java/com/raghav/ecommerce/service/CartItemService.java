package com.raghav.ecommerce.service;

import com.raghav.ecommerce.exception.CartItemException;
import com.raghav.ecommerce.exception.UserException;
import com.raghav.ecommerce.model.CartItem;


public interface CartItemService {
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
