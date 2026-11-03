package com.raghav.ecommerce.service;

import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.CartItem;
import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.repository.CartItemRepository;
import com.raghav.ecommerce.repository.CartRepository;
import com.raghav.ecommerce.service.impl.CartServiceImplementation;
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
class CartServiceImplementationTest {

    @Mock private CartRepository cartRepository;
    @Mock private CartItemRepository cartItemRepository;
    @Mock private ProductService productService;

    @InjectMocks
    private CartServiceImplementation cartService;

    private User user;
    private Cart cart;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        cart = new Cart();
        cart.setUser(user);
    }

    private CartItem item(int mrp, int selling, int qty) {
        CartItem ci = new CartItem();
        ci.setMrpPrice(mrp);
        ci.setSellingPrice(selling);
        ci.setQuantity(qty);
        return ci;
    }

    @Test
    @DisplayName("Cart ka total MRP, selling price, discount aur item count sahi nikalta hai")
    void findUserCart_calculatesTotals() {
        cart.getCartItems().add(item(2000, 1500, 1));
        cart.getCartItems().add(item(1000, 500, 2));
        cart.setCouponPrice(100);

        when(cartRepository.findByUserId(1L)).thenReturn(cart);
        when(cartRepository.save(any(Cart.class))).thenAnswer(inv -> inv.getArgument(0));

        Cart result = cartService.findUserCart(user);

        assertThat(result.getTotalMrpPrice()).isEqualTo(3000);
        assertThat(result.getTotalSellingPrice()).isEqualTo(1900.0); // 2000 - 100 coupon
        assertThat(result.getDiscount()).isEqualTo(33);              // (3000-2000)/3000 = 33%
        assertThat(result.getTotalItem()).isEqualTo(3);              // 1 + 2
    }

    @Test
    @DisplayName("Khaali cart me sab 0 rehta hai")
    void findUserCart_emptyCart() {
        when(cartRepository.findByUserId(1L)).thenReturn(cart);
        when(cartRepository.save(any(Cart.class))).thenAnswer(inv -> inv.getArgument(0));

        Cart result = cartService.findUserCart(user);

        assertThat(result.getTotalMrpPrice()).isZero();
        assertThat(result.getTotalItem()).isZero();
        assertThat(result.getDiscount()).isZero();
    }

    @Test
    @DisplayName("Discount % ka formula")
    void calculateDiscountPercentage() {
        assertThat(CartServiceImplementation.calculateDiscountPercentage(1000, 700)).isEqualTo(30);
        assertThat(CartServiceImplementation.calculateDiscountPercentage(500, 500)).isZero();
        assertThat(CartServiceImplementation.calculateDiscountPercentage(0, 0)).isZero(); // divide by zero nahi
    }

    @Test
    @DisplayName("Naya product cart me jaata hai, price quantity se guna hota hai")
    void addCartItem_newProduct_isAdded() throws Exception {
        Product product = new Product();
        product.setId(10L);
        product.setMrpPrice(800);
        product.setSellingPrice(500);

        when(cartRepository.findByUserId(1L)).thenReturn(cart);
        when(cartRepository.save(any(Cart.class))).thenAnswer(inv -> inv.getArgument(0));
        when(cartItemRepository.findByCartAndProductAndSize(cart, product, "M")).thenReturn(null);
        when(cartItemRepository.save(any(CartItem.class))).thenAnswer(inv -> inv.getArgument(0));

        CartItem added = cartService.addCartItem(user, product, "M", 2);

        assertThat(added.getSellingPrice()).isEqualTo(1000);
        assertThat(added.getMrpPrice()).isEqualTo(1600);
        assertThat(added.getQuantity()).isEqualTo(2);
        assertThat(added.getUserId()).isEqualTo(1L);
        assertThat(cart.getCartItems()).contains(added);
    }

    @Test
    @DisplayName("Wahi product aur size pehle se cart me ho to dobara nahi judta")
    void addCartItem_existing_returnsSameItem() throws Exception {
        Product product = new Product();
        CartItem existing = item(800, 500, 1);

        when(cartRepository.findByUserId(1L)).thenReturn(cart);
        when(cartRepository.save(any(Cart.class))).thenAnswer(inv -> inv.getArgument(0));
        when(cartItemRepository.findByCartAndProductAndSize(cart, product, "M")).thenReturn(existing);

        CartItem result = cartService.addCartItem(user, product, "M", 1);

        assertThat(result).isSameAs(existing);
        verify(cartItemRepository, never()).save(any());
    }
}