package com.raghav.ecommerce.service;


import com.raghav.ecommerce.exception.WishlistNotFoundException;
import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.model.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(User user);

    Wishlist getWishlistByUserId(User user);

    Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException;

}

