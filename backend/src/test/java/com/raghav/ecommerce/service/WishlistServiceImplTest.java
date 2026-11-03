package com.raghav.ecommerce.service;

import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.model.Wishlist;
import com.raghav.ecommerce.repository.WishlistRepository;
import com.raghav.ecommerce.service.impl.WishlistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {

    @Mock private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    private User user;
    private Product product;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        product = new Product();
        product.setId(5L);
        product.setTitle("Classic Smart Watch");
    }

    @Test
    @DisplayName("Wishlist na ho to nayi ban jaati hai")
    void getWishlist_createsIfMissing() {
        when(wishlistRepository.findByUserId(1L)).thenReturn(null);
        when(wishlistRepository.save(any(Wishlist.class))).thenAnswer(inv -> inv.getArgument(0));

        Wishlist w = wishlistService.getWishlistByUserId(user);

        assertThat(w).isNotNull();
        assertThat(w.getUser()).isEqualTo(user);
        verify(wishlistRepository).save(any(Wishlist.class));
    }

    @Test
    @DisplayName("Product wishlist me nahi hai to jud jaata hai")
    void addProduct_addsWhenAbsent() throws Exception {
        Wishlist w = new Wishlist();
        w.setUser(user);
        when(wishlistRepository.findByUserId(1L)).thenReturn(w);
        when(wishlistRepository.save(any(Wishlist.class))).thenAnswer(inv -> inv.getArgument(0));

        Wishlist result = wishlistService.addProductToWishlist(user, product);

        assertThat(result.getProducts()).containsExactly(product);
    }

    @Test
    @DisplayName("Product pehle se hai to dobara dabane par hat jaata hai (toggle)")
    void addProduct_removesWhenPresent() throws Exception {
        Wishlist w = new Wishlist();
        w.setUser(user);
        w.getProducts().add(product);
        when(wishlistRepository.findByUserId(1L)).thenReturn(w);
        when(wishlistRepository.save(any(Wishlist.class))).thenAnswer(inv -> inv.getArgument(0));

        Wishlist result = wishlistService.addProductToWishlist(user, product);

        assertThat(result.getProducts()).isEmpty();
    }
}