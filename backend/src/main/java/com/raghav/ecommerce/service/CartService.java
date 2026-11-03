package com.raghav.ecommerce.service;

import com.raghav.ecommerce.exception.ProductException;
import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.CartItem;
import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.model.User;

public interface CartService {
	
	public CartItem addCartItem(User user,
								Product product,
								String size,
								int quantity) throws ProductException;
	
	public Cart findUserCart(User user);

}
