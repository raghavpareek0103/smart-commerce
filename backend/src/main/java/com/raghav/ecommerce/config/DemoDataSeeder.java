////package com.raghav.ecommerce.config;
////
////import com.raghav.ecommerce.domain.AccountStatus;
////import com.raghav.ecommerce.domain.USER_ROLE;
////import com.raghav.ecommerce.model.Category;
////import com.raghav.ecommerce.model.Product;
////import com.raghav.ecommerce.model.Seller;
////import com.raghav.ecommerce.repository.CategoryRepository;
////import com.raghav.ecommerce.repository.ProductRepository;
////import com.raghav.ecommerce.repository.SellerRepository;
////import org.springframework.boot.CommandLineRunner;
////import org.springframework.stereotype.Component;
////
////import java.time.LocalDateTime;
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.Map;
////import java.util.List;
////
/////**
//// * DEMO DATA — backend start hote hi navbar ke mega menu ki HAR category (216) me 4 products daal deta hai.
//// * Har product ki photo alag hai (864 products, 864 alag photos).
//// * Jo demo products pehle se bane hain unhe dobara nahi banata — unki photos nayi kar deta hai aur
//// * bas baaki bache products banata hai, isliye restart karne par products double nahi hote.
//// * Band karna ho to neeche ENABLED = false kar do.
//// */
////@Component
////public class DemoDataSeeder implements CommandLineRunner {
////
////    private static final boolean ENABLED = true;
////    private static final String DEMO_EMAIL = "demo.seller@shopzy.in";
////
////    private final SellerRepository sellerRepository;
////    private final CategoryRepository categoryRepository;
////    private final ProductRepository productRepository;
////
////    public DemoDataSeeder(SellerRepository sellerRepository,
////                          CategoryRepository categoryRepository,
////                          ProductRepository productRepository) {
////        this.sellerRepository = sellerRepository;
////        this.categoryRepository = categoryRepository;
////        this.productRepository = productRepository;
////    }
////
////    // level1Id | level1Name | level2Id | level2Name | level3Id | level3Name | image1 | image2 | image3 | image4
////    // Har image = ek product. Har category me 4 products, sab ki photo alag.
////    private static final String[] ROWS = {
////            "men|Men|men_topwear|Topwear|men_t_shirts|Men T-Shirts|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2024/AUGUST/26/zLmM5KEB_e4781ac0cc0945119e653f4130994bd2.jpg|https://images.pexels.com/photos/9558713/pexels-photo-9558713.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6311251/pexels-photo-6311251.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5159242/pexels-photo-5159242.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_casual_shirts|Men Casual Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/2.webp|https://images.pexels.com/photos/35182320/pexels-photo-35182320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40070436/pexels-photo-40070436.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_formal_shirts|Men Formal Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/2.webp|https://images.pexels.com/photos/29765480/pexels-photo-29765480.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35415004/pexels-photo-35415004.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_sweatshirts|Men Sweatshirts|https://images.pexels.com/photos/19338764/pexels-photo-19338764.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34582212/pexels-photo-34582212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9920820/pexels-photo-9920820.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6650360/pexels-photo-6650360.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_sweaters|Men Sweaters|https://images.pexels.com/photos/10427343/pexels-photo-10427343.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7237067/pexels-photo-7237067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14269719/pexels-photo-14269719.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558897/pexels-photo-9558897.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_jackets|Men Jackets|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23029834/2023/9/18/96c015ae-1090-4036-954b-d9c80085b1d71695022844653-HRX-by-Hrithik-Roshan-Men-Jackets-6981695022843934-1.jpg|https://images.pexels.com/photos/18720773/pexels-photo-18720773.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21619158/pexels-photo-21619158.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18633077/pexels-photo-18633077.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_blazers_and_coats|Men Blazers & Coats|https://images.pexels.com/photos/9210389/pexels-photo-9210389.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2955375/pexels-photo-2955375.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32670017/pexels-photo-32670017.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29722004/pexels-photo-29722004.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_suits|Men Suits|https://images.pexels.com/photos/2505053/pexels-photo-2505053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/935969/pexels-photo-935969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050435/pexels-photo-6050435.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050409/pexels-photo-6050409.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_rain_jackets|Men Rain Jackets|https://images.pexels.com/photos/6851461/pexels-photo-6851461.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2766298/pexels-photo-2766298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12505397/pexels-photo-12505397.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26838402/pexels-photo-26838402.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_indian_and_festive_wear|Men Indian & Festive Wear|https://rukminim2.flixcart.com/image/612/612/xif0q/ethnic-set/o/n/o/l-02-langit-original-imah4jwwctkjtman.jpeg?q=70|https://shreeman.in/cdn/shop/files/20_3cfbd5a3-ecb6-482a-b798-7ffd9de1c784.jpg?v=1712061674&width=700|https://images.pexels.com/photos/13624155/pexels-photo-13624155.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217656/pexels-photo-8217656.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_kurtas_and_kurta_sets|Men Kurtas & Kurta Sets|https://images.pexels.com/photos/13222257/pexels-photo-13222257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777497/pexels-photo-16777497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38443317/pexels-photo-38443317.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36697919/pexels-photo-36697919.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_sherwanis|Men Sherwanis|https://images.pexels.com/photos/12963836/pexels-photo-12963836.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18694227/pexels-photo-18694227.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37163999/pexels-photo-37163999.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28802603/pexels-photo-28802603.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_nehru_jackets|Men Nehru Jackets|https://images.pexels.com/photos/34423748/pexels-photo-34423748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777516/pexels-photo-16777516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33049965/pexels-photo-33049965.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32947800/pexels-photo-32947800.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_topwear|Topwear|men_dhotis|Men Dhotis|https://images.pexels.com/photos/20871919/pexels-photo-20871919.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14934020/pexels-photo-14934020.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29041818/pexels-photo-29041818.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29153202/pexels-photo-29153202.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_bottomwear|Bottomwear|men_jeans|Men Jeans|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/20122324/2022/9/22/91c61c45-fe17-4d1d-8e20-0aaaf90186b61663827920015RaymondSlimFitBlueJeansForMen1.jpg|https://images.pexels.com/photos/9775489/pexels-photo-9775489.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16069736/pexels-photo-16069736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4258605/pexels-photo-4258605.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_bottomwear|Bottomwear|men_casual_trousers|Men Casual Trousers|https://images.pexels.com/photos/11034966/pexels-photo-11034966.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10004175/pexels-photo-10004175.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897539/pexels-photo-2897539.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9464625/pexels-photo-9464625.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_bottomwear|Bottomwear|men_formal_trousers|Men Formal Trousers|https://images.pexels.com/photos/2815417/pexels-photo-2815417.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558246/pexels-photo-9558246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897533/pexels-photo-2897533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17082930/pexels-photo-17082930.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_bottomwear|Bottomwear|men_shorts|Men Shorts|https://images.pexels.com/photos/1098364/pexels-photo-1098364.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13081849/pexels-photo-13081849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25315887/pexels-photo-25315887.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18178103/pexels-photo-18178103.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_bottomwear|Bottomwear|men_track_pants_and_joggers|Men Track Pants & Joggers|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24528350/2023/8/17/d9ee03c9-7e15-49e4-8f15-0b6f38e568121692275415567TrackPants1.jpg|https://images.pexels.com/photos/11328499/pexels-photo-11328499.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9901666/pexels-photo-9901666.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9499128/pexels-photo-9499128.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_briefs_and_trunks|Men Briefs & Trunks|https://images.pexels.com/photos/8875156/pexels-photo-8875156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874670/pexels-photo-8874670.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874933/pexels-photo-8874933.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874910/pexels-photo-8874910.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_boxers|Men Boxers|https://images.pexels.com/photos/8875171/pexels-photo-8875171.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874672/pexels-photo-8874672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8875173/pexels-photo-8875173.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17510799/pexels-photo-17510799.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_vests|Men Vests|https://images.pexels.com/photos/7711284/pexels-photo-7711284.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13544290/pexels-photo-13544290.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17604597/pexels-photo-17604597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38216749/pexels-photo-38216749.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_sleepwear_and_loungewear|Men Sleepwear & Loungewear|https://images.pexels.com/photos/8416232/pexels-photo-8416232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30444027/pexels-photo-30444027.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6976782/pexels-photo-6976782.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443431/pexels-photo-6443431.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_thermals|Men Thermals|https://images.pexels.com/photos/5924447/pexels-photo-5924447.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4763665/pexels-photo-4763665.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6712133/pexels-photo-6712133.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6799715/pexels-photo-6799715.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_plus_size|Men Plus Size|https://images.pexels.com/photos/8217535/pexels-photo-8217535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217536/pexels-photo-8217536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33676395/pexels-photo-33676395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30589840/pexels-photo-30589840.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_footwear|Footwear|men_casual_shoes|Men Casual Shoes|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27107914/2024/1/30/bd09f650-d8a4-4570-8e53-e2cfafc4ea6c1706609911015SparxMenMeshRunningShoes1.jpg|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24651572/2023/8/25/4fbf6d8c-d093-46c5-a5a6-7dd67c0c76551692964752597HouseofPataudiMenTanFauxLeatherFormalSlipOnLoafers1.jpg|https://images.pexels.com/photos/40263252/pexels-photo-40263252.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40325298/pexels-photo-40325298.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_footwear|Footwear|men_sports_shoes|Men Sports Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/2.webp|https://images.pexels.com/photos/260044/pexels-photo-260044.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8454904/pexels-photo-8454904.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_footwear|Footwear|men_formal_shoes|Men Formal Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/2.webp|https://images.pexels.com/photos/12210270/pexels-photo-12210270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10352336/pexels-photo-10352336.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_footwear|Footwear|men_sneakers|Men Sneakers|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/2.webp|https://images.pexels.com/photos/5560288/pexels-photo-5560288.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4901935/pexels-photo-4901935.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_footwear|Footwear|men_sandals_and_floaters|Men Sandals & Floaters|https://images.pexels.com/photos/4996738/pexels-photo-4996738.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/952843/pexels-photo-952843.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23692992/pexels-photo-23692992.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9214975/pexels-photo-9214975.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_footwear|Footwear|men_flip_flops|Men Flip Flops|https://images.pexels.com/photos/14934601/pexels-photo-14934601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34447680/pexels-photo-34447680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30335806/pexels-photo-30335806.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35166184/pexels-photo-35166184.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_footwear|Footwear|men_socks|Men Socks|https://images.pexels.com/photos/15195233/pexels-photo-15195233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14943497/pexels-photo-14943497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10563910/pexels-photo-10563910.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12932537/pexels-photo-12932537.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sunglasses_and_frames|Men Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/2.webp|https://images.pexels.com/photos/5202048/pexels-photo-5202048.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2767694/pexels-photo-2767694.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_watches|Men Watches|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/1.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/2.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/3.webp|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/1.webp",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_and_active_wear|Men Sports & Active Wear|https://images.pexels.com/photos/1311617/pexels-photo-1311617.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036883/pexels-photo-5036883.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29259727/pexels-photo-29259727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3927383/pexels-photo-3927383.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_sandals|Men Sports Sandals|https://images.pexels.com/photos/15669736/pexels-photo-15669736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3342769/pexels-photo-3342769.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996974/pexels-photo-4996974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6008231/pexels-photo-6008231.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_active_t_shirts|Men Active T-Shirts|https://images.pexels.com/photos/8498700/pexels-photo-8498700.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/1.webp|https://images.pexels.com/photos/4004222/pexels-photo-4004222.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6998798/pexels-photo-6998798.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_track_pants_and_shorts|Men Track Pants & Shorts|https://images.pexels.com/photos/13069429/pexels-photo-13069429.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8567521/pexels-photo-8567521.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30710156/pexels-photo-30710156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11029622/pexels-photo-11029622.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_tracksuits|Men Tracksuits|https://images.pexels.com/photos/9488418/pexels-photo-9488418.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2652236/pexels-photo-2652236.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6158668/pexels-photo-6158668.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33558335/pexels-photo-33558335.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_jackets_and_sweatshirts|Men Jackets & Sweatshirts|https://images.pexels.com/photos/14611192/pexels-photo-14611192.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9775733/pexels-photo-9775733.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28701960/pexels-photo-28701960.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15127546/pexels-photo-15127546.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_accessories|Men Sports Accessories|https://images.pexels.com/photos/4162451/pexels-photo-4162451.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6740821/pexels-photo-6740821.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35567437/pexels-photo-35567437.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19025674/pexels-photo-19025674.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_swimwear|Men Swimwear|https://images.pexels.com/photos/9030517/pexels-photo-9030517.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6011894/pexels-photo-6011894.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8028651/pexels-photo-8028651.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8688187/pexels-photo-8688187.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_wallets|Men Wallets|https://images.pexels.com/photos/915917/pexels-photo-915917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/164637/pexels-photo-164637.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7952556/pexels-photo-7952556.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4452393/pexels-photo-4452393.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_belts|Men Belts|https://images.pexels.com/photos/6654763/pexels-photo-6654763.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31323080/pexels-photo-31323080.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959214/pexels-photo-31959214.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959216/pexels-photo-31959216.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_perfumes_and_body_mists|Men Perfumes & Body Mists|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/1.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/2.webp|https://images.pexels.com/photos/15096784/pexels-photo-15096784.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7703038/pexels-photo-7703038.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_trimmers|Men Trimmers|https://images.pexels.com/photos/897262/pexels-photo-897262.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6007395/pexels-photo-6007395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7253884/pexels-photo-7253884.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16553361/pexels-photo-16553361.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_deodorants|Men Deodorants|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/1.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/2.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/3.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/3.webp",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_ties_cufflinks_and_pocket_squares|Men Ties, Cufflinks & Pocket Squares|https://images.pexels.com/photos/21928749/pexels-photo-21928749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38270/pexels-photo-38270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32392710/pexels-photo-32392710.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34342976/pexels-photo-34342976.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_accessory_gift_sets|Men Accessory Gift Sets|https://images.pexels.com/photos/190930/pexels-photo-190930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/264771/pexels-photo-264771.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6087540/pexels-photo-6087540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1303087/pexels-photo-1303087.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_caps_and_hats|Men Caps & Hats|https://images.pexels.com/photos/18417334/pexels-photo-18417334.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13265200/pexels-photo-13265200.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27401299/pexels-photo-27401299.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29925645/pexels-photo-29925645.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_mufflers_scarves_and_gloves|Men Mufflers, Scarves & Gloves|https://images.pexels.com/photos/1903760/pexels-photo-1903760.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20281423/pexels-photo-20281423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16049198/pexels-photo-16049198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35830300/pexels-photo-35830300.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_phone_cases|Men Phone Cases|https://images.pexels.com/photos/374140/pexels-photo-374140.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7742503/pexels-photo-7742503.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13706809/pexels-photo-13706809.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18403793/pexels-photo-18403793.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_rings_and_wristwear|Men Rings & Wristwear|https://images.pexels.com/photos/16304565/pexels-photo-16304565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9742330/pexels-photo-9742330.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2442905/pexels-photo-2442905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15325460/pexels-photo-15325460.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "men|Men|men_fashion_accessories|Fashion Accessories|men_helmets|Men Helmets|https://images.pexels.com/photos/9835238/pexels-photo-9835238.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12000077/pexels-photo-12000077.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5803274/pexels-photo-5803274.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5355536/pexels-photo-5355536.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtas_and_suits|Women Kurtas & Suits|https://images.pexels.com/photos/11661072/pexels-photo-11661072.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33824984/pexels-photo-33824984.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30196701/pexels-photo-30196701.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1322993/pexels-photo-1322993.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtis_tunics_tops|Women Kurtis, Tunics & Tops|https://images.pexels.com/photos/19556879/pexels-photo-19556879.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7371768/pexels-photo-7371768.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36311379/pexels-photo-36311379.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35521738/pexels-photo-35521738.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_sarees|Women Sarees|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950865601_540x.jpg?v=1704965372|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950931137_540x.jpg?v=1704965372|https://images.pexels.com/photos/7920188/pexels-photo-7920188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27719401/pexels-photo-27719401.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_ethnic_wear|Women Ethnic Wear|https://images.pexels.com/photos/37523799/pexels-photo-37523799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512776/pexels-photo-28512776.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512787/pexels-photo-28512787.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13178920/pexels-photo-13178920.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_leggings_salwars_churidars|Women Leggings, Salwars & Churidars|https://rukminim2.flixcart.com/image/612/612/xif0q/skirt/k/o/h/free-1-00003-custom-fashion-original-imagztykafwbqupf.jpeg?q=70|https://images.pexels.com/photos/19932178/pexels-photo-19932178.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20382095/pexels-photo-20382095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19271174/pexels-photo-19271174.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_skirts_palazzos|Women Skirts & Palazzos|https://images.pexels.com/photos/14997427/pexels-photo-14997427.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16721105/pexels-photo-16721105.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40293396/pexels-photo-40293396.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39030338/pexels-photo-39030338.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dress_materials|Women Dress Materials|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950996673_540x.jpg?v=1704965372|https://rukminim2.flixcart.com/image/832/832/xif0q/sari/z/a/4/free-saree-new2023-simple-light-weight-saree-chiffon-print-saree-original-imagzyhp2azy7rwz.jpeg?q=70&crop=false|https://images.pexels.com/photos/34933671/pexels-photo-34933671.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20841148/pexels-photo-20841148.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_lehenga_cholis|Women Lehenga Cholis|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23807268/2023/6/29/9930b235-5318-4755-abbe-08f99e969e781688026636544LehengaCholi7.jpg|https://images.pexels.com/photos/12730873/pexels-photo-12730873.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28405815/pexels-photo-28405815.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33343591/pexels-photo-33343591.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dupattas_shawls|Women Dupattas & Shawls|https://images.pexels.com/photos/11004120/pexels-photo-11004120.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18659930/pexels-photo-18659930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158070/pexels-photo-35158070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158052/pexels-photo-35158052.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_jackets|Women Jackets|https://images.pexels.com/photos/1818431/pexels-photo-1818431.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8386453/pexels-photo-8386453.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39054974/pexels-photo-39054974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036161/pexels-photo-5036161.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_belts_scarves_more|Women Belts, Scarves & More|https://images.pexels.com/photos/31432650/pexels-photo-31432650.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8014845/pexels-photo-8014845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37483619/pexels-photo-37483619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959217/pexels-photo-31959217.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_watches_wearables|Women Watches & Wearables|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/3.webp",
////            "women|Women|women_western_wear|Western Wear|women_dresses|Women Dresses|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/2.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/3.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/4.webp",
////            "women|Women|women_western_wear|Western Wear|women_tops|Women Tops|https://cdn.dummyjson.com/product-images/tops/blue-frock/1.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/2.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/3.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/4.webp",
////            "women|Women|women_western_wear|Western Wear|women_tshirts|Women Tshirts|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/2.webp|https://images.pexels.com/photos/30957211/pexels-photo-30957211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24205642/pexels-photo-24205642.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_jeans|Women Jeans|https://images.pexels.com/photos/17302419/pexels-photo-17302419.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/936117/pexels-photo-936117.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20729717/pexels-photo-20729717.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7636101/pexels-photo-7636101.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_trousers_capris|Women Trousers & Capris|https://images.pexels.com/photos/450212/pexels-photo-450212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6182574/pexels-photo-6182574.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7205905/pexels-photo-7205905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19995460/pexels-photo-19995460.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_shorts_skirts|Women Shorts & Skirts|https://images.pexels.com/photos/6012003/pexels-photo-6012003.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/383603/pexels-photo-383603.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7391126/pexels-photo-7391126.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16431244/pexels-photo-16431244.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_coords|Women Co-ords|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/2.webp|https://images.pexels.com/photos/31400265/pexels-photo-31400265.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37036179/pexels-photo-37036179.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_playsuits|Women Playsuits|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/2.webp|https://images.pexels.com/photos/7545423/pexels-photo-7545423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29017408/pexels-photo-29017408.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_jumpsuits|Women Jumpsuits|https://images.pexels.com/photos/21374896/pexels-photo-21374896.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4006508/pexels-photo-4006508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2699250/pexels-photo-2699250.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13219628/pexels-photo-13219628.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_shrugs|Women Shrugs|https://cdn.dummyjson.com/product-images/tops/gray-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/gray-dress/2.webp|https://images.pexels.com/photos/7585673/pexels-photo-7585673.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5710033/pexels-photo-5710033.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_sweaters_sweatshirts|Women Sweaters & Sweatshirts|https://images.pexels.com/photos/2080383/pexels-photo-2080383.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9811069/pexels-photo-9811069.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33688875/pexels-photo-33688875.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6347515/pexels-photo-6347515.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_jackets_coats|Women Jackets & Coats|https://images.pexels.com/photos/6974936/pexels-photo-6974936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11587144/pexels-photo-11587144.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12083001/pexels-photo-12083001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34366695/pexels-photo-34366695.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_blazers_waistcoats|Women Blazers & Waistcoats|https://images.pexels.com/photos/10955935/pexels-photo-10955935.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14495270/pexels-photo-14495270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7691041/pexels-photo-7691041.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8484005/pexels-photo-8484005.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_plus_size|Women Plus Size|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/2.webp|https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/1.webp|https://images.pexels.com/photos/14693247/pexels-photo-14693247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18586865/pexels-photo-18586865.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_maternity|Women Maternity|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/2.webp|https://images.pexels.com/photos/20770621/pexels-photo-20770621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15968597/pexels-photo-15968597.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_western_wear|Western Wear|women_sunglasses_frames|Women Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/2.webp|https://images.pexels.com/photos/15735139/pexels-photo-15735139.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26575042/pexels-photo-26575042.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_footwear|Footwear|women_flats|Women Flats|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/2.webp|https://images.pexels.com/photos/14816287/pexels-photo-14816287.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36581190/pexels-photo-36581190.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_footwear|Footwear|women_casual_shoes|Women Casual Shoes|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/2.webp|https://images.pexels.com/photos/7887198/pexels-photo-7887198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1027130/pexels-photo-1027130.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_footwear|Footwear|women_heels|Women Heels|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/2.webp|https://images.pexels.com/photos/36212466/pexels-photo-36212466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34294446/pexels-photo-34294446.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_footwear|Footwear|women_boots|Women Boots|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/2.webp|https://images.pexels.com/photos/26856061/pexels-photo-26856061.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27174561/pexels-photo-27174561.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_footwear|Footwear|women_sports_shoes_floaters|Women Sports Shoes & Floaters|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/2.webp|https://images.pexels.com/photos/4065509/pexels-photo-4065509.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15659357/pexels-photo-15659357.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_sports_active_wear|Sports & Active Wear|women_clothing|Women Clothing|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/22109480/2023/9/5/06a17ac3-46b0-4f9d-bcb1-2d3582feda041693895310152PumaWomenBrandLogoPrintedPureCottonOutdoorT-shirt1.jpg|https://images.pexels.com/photos/8436750/pexels-photo-8436750.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27433185/pexels-photo-27433185.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7894597/pexels-photo-7894597.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_accessories|Women Sports Accessories|https://images.pexels.com/photos/9944907/pexels-photo-9944907.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16513597/pexels-photo-16513597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6246682/pexels-photo-6246682.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6339731/pexels-photo-6339731.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_equipment|Women Sports Equipment|https://cdn.dummyjson.com/product-images/sports-accessories/american-football/1.webp|https://cdn.dummyjson.com/product-images/sports-accessories/baseball-ball/1.webp|https://images.pexels.com/photos/7743320/pexels-photo-7743320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6550849/pexels-photo-6550849.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_bra|Women Bra|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/362895/2023/10/18/bfb55058-fe1a-45d6-a39d-1b8d421e02001697603774147TriumphShapeSensation33withHighWaistTummyandThighControlMaxi1.jpg|https://images.pexels.com/photos/10782049/pexels-photo-10782049.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6879815/pexels-photo-6879815.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29835139/pexels-photo-29835139.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_briefs|Women Briefs|https://images.pexels.com/photos/10476640/pexels-photo-10476640.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17604582/pexels-photo-17604582.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29474759/pexels-photo-29474759.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11415285/pexels-photo-11415285.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_shapewear|Women Shapewear|https://images.pexels.com/photos/8966046/pexels-photo-8966046.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17040812/pexels-photo-17040812.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5256325/pexels-photo-5256325.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5256331/pexels-photo-5256331.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_sleepwear_loungewear|Women Sleepwear & Loungewear|https://images.pexels.com/photos/6976434/pexels-photo-6976434.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18820123/pexels-photo-18820123.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8416240/pexels-photo-8416240.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8497487/pexels-photo-8497487.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_swimwear|Women Swimwear|https://images.pexels.com/photos/7365485/pexels-photo-7365485.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5638968/pexels-photo-5638968.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13697814/pexels-photo-13697814.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31094948/pexels-photo-31094948.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_camisoles_thermals|Women Camisoles & Thermals|https://images.pexels.com/photos/4029921/pexels-photo-4029921.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6569963/pexels-photo-6569963.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6569968/pexels-photo-6569968.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6544543/pexels-photo-6544543.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_beauty_personal_care|Beauty & Personal Care|https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/1.webp|https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/1.webp|https://cdn.dummyjson.com/product-images/beauty/powder-canister/1.webp|https://cdn.dummyjson.com/product-images/beauty/red-lipstick/1.webp",
////            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_makeup|Women Makeup|https://images.pexels.com/photos/23228944/pexels-photo-23228944.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1571585/pexels-photo-1571585.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4938498/pexels-photo-4938498.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3148938/pexels-photo-3148938.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_skincare|Women Skincare|https://cdn.dummyjson.com/product-images/beauty/red-nail-polish/1.webp|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/1.webp|https://images.pexels.com/photos/12115040/pexels-photo-12115040.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28994644/pexels-photo-28994644.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_premium_beauty|Women Premium Beauty|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/2.webp|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/1.webp|https://images.pexels.com/photos/7020247/pexels-photo-7020247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6925496/pexels-photo-6925496.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_lipsticks|Women Lipsticks|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/2.webp|https://cdn.dummyjson.com/product-images/skin-care/vaseline-men-body-and-face-lotion/1.webp|https://images.pexels.com/photos/34321279/pexels-photo-34321279.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6648498/pexels-photo-6648498.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_fragrances|Women Fragrances|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/1.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/2.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/3.webp|https://cdn.dummyjson.com/product-images/fragrances/dolce-shine-eau-de/1.webp",
////            "women|Women|women_gadgets|Gadgets|women_gadgets|Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/2.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/2.webp",
////            "women|Women|women_gadgets|Gadgets|women_smart_wearables|Women Smart Wearables|https://rukminim2.flixcart.com/image/612/612/xif0q/smartwatch/f/g/g/-original-imagywnz46fngcks.jpeg?q=70|https://rukminim2.flixcart.com/image/416/416/xif0q/smartwatch/u/r/h/-original-imagyxxfgz52vzqn.jpeg?q=70&crop=false|https://images.pexels.com/photos/31406895/pexels-photo-31406895.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31406903/pexels-photo-31406903.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_gadgets|Gadgets|women_fitness_gadgets|Women Fitness Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/2.webp|https://images.pexels.com/photos/5036927/pexels-photo-5036927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/374673/pexels-photo-374673.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_gadgets|Gadgets|women_headphones|Women Headphones|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods-max-silver/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/beats-flex-wireless-earphones/1.webp|https://images.pexels.com/photos/210927/pexels-photo-210927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7772548/pexels-photo-7772548.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_gadgets|Gadgets|women_speakers|Women Speakers|https://images.pexels.com/photos/374110/pexels-photo-374110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13465232/pexels-photo-13465232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4917455/pexels-photo-4917455.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29617989/pexels-photo-29617989.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_jewellery|Jewellery|women_fashion_jewellery|Women Fashion Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/2.webp|https://images.pexels.com/photos/10862233/pexels-photo-10862233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7514818/pexels-photo-7514818.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_jewellery|Jewellery|women_fine_jewellery|Women Fine Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/2.webp|https://images.pexels.com/photos/13924051/pexels-photo-13924051.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15925159/pexels-photo-15925159.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_jewellery|Jewellery|women_earrings|Women Earrings|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/2.webp|https://images.pexels.com/photos/10082804/pexels-photo-10082804.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15785528/pexels-photo-15785528.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_handbags_bags_wallets|Handbags, Bags & Wallets|https://images.pexels.com/photos/4430246/pexels-photo-4430246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6102145/pexels-photo-6102145.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/1.webp|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/2.webp",
////            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_luggages_trolleys|Women Luggages & Trolleys|https://images.pexels.com/photos/1682694/pexels-photo-1682694.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8212229/pexels-photo-8212229.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36933446/pexels-photo-36933446.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11499403/pexels-photo-11499403.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobiles|Mobiles|mi_mobile|Mi Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/1.webp",
////            "electronics|Electronics|mobiles|Mobiles|realme_mobile|Realme Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/3.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/1.webp",
////            "electronics|Electronics|mobiles|Mobiles|samsung_mobile|Samsung Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/1.webp",
////            "electronics|Electronics|mobiles|Mobiles|infinix_mobile|Infinix Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/2.webp",
////            "electronics|Electronics|mobiles|Mobiles|oppo_mobile|OPPO Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/1.webp",
////            "electronics|Electronics|mobiles|Mobiles|apple_mobile|Apple Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/3.webp",
////            "electronics|Electronics|mobiles|Mobiles|vivo_mobile|Vivo Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/1.webp",
////            "electronics|Electronics|mobiles|Mobiles|honor_mobile|Honor Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/2.webp",
////            "electronics|Electronics|mobiles|Mobiles|asus_mobile|Asus Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/4.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/3.webp",
////            "electronics|Electronics|mobiles|Mobiles|poco_x2_mobile|Poco X2 Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/2.webp",
////            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10_mobile|Realme Narzo 10 Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/3.webp",
////            "electronics|Electronics|mobiles|Mobiles|infinix_hot_9_mobile|Infinix Hot 9 Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/2.webp",
////            "electronics|Electronics|mobiles|Mobiles|iqoo_3_mobile|IQOO 3 Mobile|https://images.pexels.com/photos/6901037/pexels-photo-6901037.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15092/pexels-photo-15092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5337378/pexels-photo-5337378.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5253002/pexels-photo-5253002.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobiles|Mobiles|iphone_se_mobile|IPhone SE Mobile|https://images.pexels.com/photos/3945672/pexels-photo-3945672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18311092/pexels-photo-18311092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945691/pexels-photo-3945691.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945695/pexels-photo-3945695.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobiles|Mobiles|motorola_razr_mobile|Motorola Razr Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/3.webp|https://images.pexels.com/photos/47261/pexels-photo-47261.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24181865/pexels-photo-24181865.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13844013/pexels-photo-13844013.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10a_mobile|Realme Narzo 10A Mobile|https://images.pexels.com/photos/3850561/pexels-photo-3850561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36680543/pexels-photo-36680543.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21854468/pexels-photo-21854468.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14486282/pexels-photo-14486282.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobiles|Mobiles|motorola_g8_power_lite_mobile|Motorola G8 Power Lite Mobile|https://images.pexels.com/photos/30639091/pexels-photo-30639091.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16442035/pexels-photo-16442035.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26775917/pexels-photo-26775917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19281836/pexels-photo-19281836.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cases|Mobile Cases|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/2.webp|https://images.pexels.com/photos/12359130/pexels-photo-12359130.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1670768/pexels-photo-1670768.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|headphones_headsets|Headphones & Headsets|https://rukminim2.flixcart.com/image/612/612/kz4gh3k0/headphone/c/v/r/-original-imagb7bmhdgghzxq.jpeg?q=70|https://images.pexels.com/photos/3394660/pexels-photo-3394660.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5650531/pexels-photo-5650531.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3081173/pexels-photo-3081173.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|power_banks|Power Banks|https://images.pexels.com/photos/4072683/pexels-photo-4072683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37475677/pexels-photo-37475677.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6296911/pexels-photo-6296911.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3921704/pexels-photo-3921704.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|screenguards|Screenguards|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpower-wireless-charger/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/1.webp|https://images.pexels.com/photos/12368095/pexels-photo-12368095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17610713/pexels-photo-17610713.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|memory_cards|Memory Cards|https://images.pexels.com/photos/5882581/pexels-photo-5882581.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7057/pexels-photo-7057.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2147082/pexels-photo-2147082.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32346379/pexels-photo-32346379.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|smart_headphones|Smart Headphones|https://images.pexels.com/photos/4887213/pexels-photo-4887213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28920288/pexels-photo-28920288.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/3.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-homepod-mini-cosmic-grey/1.webp",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cables|Mobile Cables|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/2.webp|https://images.pexels.com/photos/4219860/pexels-photo-4219860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1028674/pexels-photo-1028674.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/947407/pexels-photo-947407.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_chargers|Mobile Chargers|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/2.webp|https://images.pexels.com/photos/36012993/pexels-photo-36012993.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/216369/pexels-photo-216369.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_holders|Mobile Holders|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/2.webp|https://images.pexels.com/photos/694424/pexels-photo-694424.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35037256/pexels-photo-35037256.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_watches|Smart Watches|https://images.pexels.com/photos/11031515/pexels-photo-11031515.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18662969/pexels-photo-18662969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8760799/pexels-photo-8760799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18078946/pexels-photo-18078946.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_glasses_vr|Smart Glasses (VR)|https://images.pexels.com/photos/373905/pexels-photo-373905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6848112/pexels-photo-6848112.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7561900/pexels-photo-7561900.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19545617/pexels-photo-19545617.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_bands|Smart Bands|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/2.webp|https://images.pexels.com/photos/374619/pexels-photo-374619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4426516/pexels-photo-4426516.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|gaming_laptops|Gaming Laptops|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/1.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/2.webp|https://images.pexels.com/photos/3951449/pexels-photo-3951449.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19012030/pexels-photo-19012030.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|desktop_pcs|Desktop PCs|https://images.pexels.com/photos/6415/pexels-photo-6415.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1714341/pexels-photo-1714341.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31145466/pexels-photo-31145466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9072379/pexels-photo-9072379.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|gaming_accessories|Gaming & Accessories|https://images.pexels.com/photos/194511/pexels-photo-194511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1337247/pexels-photo-1337247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7887636/pexels-photo-7887636.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34592708/pexels-photo-34592708.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|computer_accessories|Computer Accessories|https://images.pexels.com/photos/392018/pexels-photo-392018.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12786602/pexels-photo-12786602.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4065748/pexels-photo-4065748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1420709/pexels-photo-1420709.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|external_hard_disks|External Hard Disks|https://images.pexels.com/photos/20213726/pexels-photo-20213726.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6937936/pexels-photo-6937936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32892856/pexels-photo-32892856.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28461160/pexels-photo-28461160.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|pendrives|Pendrives|https://images.pexels.com/photos/19954918/pexels-photo-19954918.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10133432/pexels-photo-10133432.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4792751/pexels-photo-4792751.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10336136/pexels-photo-10336136.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|laptop_skins_decals|Laptop Skins & Decals|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/1.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/2.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/3.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/3.webp",
////            "electronics|Electronics|laptops|Laptops|laptop_bags|Laptop Bags|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/1.webp|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/2.webp|https://images.pexels.com/photos/20318749/pexels-photo-20318749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5330724/pexels-photo-5330724.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|mouse|Mouse|https://images.pexels.com/photos/7151696/pexels-photo-7151696.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38006271/pexels-photo-38006271.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6932748/pexels-photo-6932748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12029640/pexels-photo-12029640.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|computer_peripherals|Computer Peripherals|https://images.pexels.com/photos/8297860/pexels-photo-8297860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12509206/pexels-photo-12509206.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4120698/pexels-photo-4120698.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20182039/pexels-photo-20182039.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|printers_ink_cartridges|Printers & Ink Cartridges|https://images.pexels.com/photos/4792283/pexels-photo-4792283.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/777001/pexels-photo-777001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574569/pexels-photo-9574569.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574453/pexels-photo-9574453.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|laptops|Laptops|monitors|Monitors|https://images.pexels.com/photos/3521937/pexels-photo-3521937.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/326512/pexels-photo-326512.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37148217/pexels-photo-37148217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6804606/pexels-photo-6804606.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|tablets|Tablets|apple_ipads|Apple IPads|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/1.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/2.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/3.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/4.webp",
////            "electronics|Electronics|speakers|Speakers|home_audio_speakers|Home Audio Speakers|https://rukminim2.flixcart.com/image/612/612/xif0q/speaker/6/z/2/-original-imahfgfkr5gkk9aq.jpeg?q=70|https://images.pexels.com/photos/191877/pexels-photo-191877.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/128611/pexels-photo-128611.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2651794/pexels-photo-2651794.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|speakers|Speakers|home_theatres|Home Theatres|https://images.pexels.com/photos/31683433/pexels-photo-31683433.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/880874/pexels-photo-880874.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7031762/pexels-photo-7031762.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5008383/pexels-photo-5008383.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|speakers|Speakers|soundbars|Soundbars|https://images.pexels.com/photos/374067/pexels-photo-374067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18542243/pexels-photo-18542243.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/373638/pexels-photo-373638.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10486182/pexels-photo-10486182.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|speakers|Speakers|bluetooth_speakers|Bluetooth Speakers|https://images.pexels.com/photos/29606737/pexels-photo-29606737.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9767551/pexels-photo-9767551.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25450654/pexels-photo-25450654.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14017595/pexels-photo-14017595.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|speakers|Speakers|dth_set_top_box|DTH Set Top Box|https://images.pexels.com/photos/5207508/pexels-photo-5207508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40195705/pexels-photo-40195705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5207514/pexels-photo-5207514.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4777979/pexels-photo-4777979.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|camera|Camera|dslr_mirrorless_camera|DSLR & Mirrorless Camera|https://rukminim2.flixcart.com/image/312/312/jfbfde80/camera/n/r/n/canon-eos-eos-3000d-dslr-original-imaf3t5h9yuyc5zu.jpeg?q=70|https://images.pexels.com/photos/17345664/pexels-photo-17345664.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26873540/pexels-photo-26873540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6891485/pexels-photo-6891485.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|camera|Camera|compact_bridge_cameras|Compact & Bridge Cameras|https://images.pexels.com/photos/1595232/pexels-photo-1595232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1144699/pexels-photo-1144699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16104735/pexels-photo-16104735.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4372403/pexels-photo-4372403.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|camera|Camera|sports_action_camera|Sports & Action Camera|https://images.pexels.com/photos/274973/pexels-photo-274973.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996005/pexels-photo-4996005.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4817132/pexels-photo-4817132.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1276531/pexels-photo-1276531.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|camera|Camera|camera_accessories|Camera Accessories|https://images.pexels.com/photos/7776871/pexels-photo-7776871.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3405454/pexels-photo-3405454.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35001053/pexels-photo-35001053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34642455/pexels-photo-34642455.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|camera|Camera|lens|Camera Lens|https://images.pexels.com/photos/838680/pexels-photo-838680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/734535/pexels-photo-734535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11713436/pexels-photo-11713436.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29293968/pexels-photo-29293968.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|camera|Camera|camera_tripods|Camera Tripods|https://images.pexels.com/photos/20382217/pexels-photo-20382217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15062358/pexels-photo-15062358.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11331527/pexels-photo-11331527.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20954706/pexels-photo-20954706.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_runners|Bed Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/19284508/2022/7/28/92df52de-27dc-4d72-8ab4-fee2c82c85081659003977664DreamscapeUnisexPinkBedsheets1.jpg|https://images.pexels.com/photos/5629134/pexels-photo-5629134.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6903157/pexels-photo-6903157.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27671434/pexels-photo-27671434.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|mattress_protectors|Mattress Protectors|https://images.pexels.com/photos/3030684/pexels-photo-3030684.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4740586/pexels-photo-4740586.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28513849/pexels-photo-28513849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5629138/pexels-photo-5629138.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedsheets|Bedsheets|https://images.pexels.com/photos/3682240/pexels-photo-3682240.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7019028/pexels-photo-7019028.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12200060/pexels-photo-12200060.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38651677/pexels-photo-38651677.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedding_sets|Bedding Sets|https://images.pexels.com/photos/212269/pexels-photo-212269.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10373508/pexels-photo-10373508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22711513/pexels-photo-22711513.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32418083/pexels-photo-32418083.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|blankets_quilts_dohars|Blankets, Quilts & Dohars|https://images.pexels.com/photos/8266834/pexels-photo-8266834.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6633533/pexels-photo-6633533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30399211/pexels-photo-30399211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30350497/pexels-photo-30350497.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|pillows_pillow_covers|Pillows & Pillow Covers|https://images.pexels.com/photos/12326545/pexels-photo-12326545.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9582579/pexels-photo-9582579.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9290601/pexels-photo-9290601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1239221/pexels-photo-1239221.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_covers|Bed Covers|https://images.pexels.com/photos/10980163/pexels-photo-10980163.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9262007/pexels-photo-9262007.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6782479/pexels-photo-6782479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980080/pexels-photo-19980080.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|diwan_sets|Diwan Sets|https://images.pexels.com/photos/3557507/pexels-photo-3557507.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/910458/pexels-photo-910458.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34574606/pexels-photo-34574606.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7147299/pexels-photo-7147299.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|chair_pads_covers|Chair Pads & Covers|https://images.pexels.com/photos/462197/pexels-photo-462197.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/1.webp|https://images.pexels.com/photos/18829799/pexels-photo-18829799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29917912/pexels-photo-29917912.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|sofa_covers|Sofa Covers|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/2.webp|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/1.webp|https://images.pexels.com/photos/6758245/pexels-photo-6758245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7394787/pexels-photo-7394787.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|flooring|Flooring|floor_runners|Floor Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27868878/2024/2/25/53b7c07f-24a7-48e2-a791-f63dce4a0c981708844793060SANACARPETMulticolouredFloralAnti-SkidPolyesterCarpet1.jpg|https://images.pexels.com/photos/5417293/pexels-photo-5417293.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15896109/pexels-photo-15896109.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29614636/pexels-photo-29614636.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|flooring|Flooring|carpets|Carpets|https://images.pexels.com/photos/6933860/pexels-photo-6933860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/45980/pexels-photo-45980.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16820353/pexels-photo-16820353.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37219747/pexels-photo-37219747.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|flooring|Flooring|floor_mats_dhurries|Floor Mats & Dhurries|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/2.webp|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/1.webp|https://images.pexels.com/photos/7417505/pexels-photo-7417505.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35769473/pexels-photo-35769473.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|flooring|Flooring|door_mats|Door Mats|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/2.webp|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/1.webp|https://images.pexels.com/photos/5840868/pexels-photo-5840868.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2950003/pexels-photo-2950003.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|bath_towels|Bath Towels|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/16719378/2024/7/2/97418bad-8216-494f-a863-9b159aec93cf1719897710069JockeyPackof2CottonTerry500GSMUltrasoftandDurableSolidHandTo1.jpg|https://images.pexels.com/photos/12679/pexels-photo-12679.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20523089/pexels-photo-20523089.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8535619/pexels-photo-8535619.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|hand_face_towels|Hand & Face Towels|https://images.pexels.com/photos/7691101/pexels-photo-7691101.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210373/pexels-photo-4210373.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5833316/pexels-photo-5833316.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5732631/pexels-photo-5732631.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|beach_towels|Beach Towels|https://images.pexels.com/photos/271711/pexels-photo-271711.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1304110/pexels-photo-1304110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18674213/pexels-photo-18674213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25649794/pexels-photo-25649794.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|towels_set|Towels Set|https://images.pexels.com/photos/282892/pexels-photo-282892.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210371/pexels-photo-4210371.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6188050/pexels-photo-6188050.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20665616/pexels-photo-20665616.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|bath_rugs|Bath Rugs|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/2.webp|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/1.webp|https://images.pexels.com/photos/34667257/pexels-photo-34667257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18894029/pexels-photo-18894029.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|bath_robes|Bath Robes|https://images.pexels.com/photos/10924511/pexels-photo-10924511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/433624/pexels-photo-433624.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6202201/pexels-photo-6202201.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6634836/pexels-photo-6634836.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|bathroom_accessories|Bathroom Accessories|https://images.pexels.com/photos/10574059/pexels-photo-10574059.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3761560/pexels-photo-3761560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4194705/pexels-photo-4194705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980252/pexels-photo-19980252.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|bath|Bath|shower_curtains|Shower Curtains|https://images.pexels.com/photos/11430604/pexels-photo-11430604.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5137571/pexels-photo-5137571.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5825561/pexels-photo-5825561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6430731/pexels-photo-6430731.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|floor_lamps|Floor Lamps|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/productimage/2021/6/10/5be80654-037c-4591-99c9-a403646971981623344562713-1.jpg|https://images.pexels.com/photos/112811/pexels-photo-112811.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6045108/pexels-photo-6045108.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11850174/pexels-photo-11850174.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|ceiling_lamps|Ceiling Lamps|https://images.pexels.com/photos/7533932/pexels-photo-7533932.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17940121/pexels-photo-17940121.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9628657/pexels-photo-9628657.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12202390/pexels-photo-12202390.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|table_lamps|Table Lamps|https://images.pexels.com/photos/16683246/pexels-photo-16683246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/573298/pexels-photo-573298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35392792/pexels-photo-35392792.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6053887/pexels-photo-6053887.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|wall_lamps|Wall Lamps|https://images.pexels.com/photos/721039/pexels-photo-721039.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2187141/pexels-photo-2187141.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9510486/pexels-photo-9510486.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29343148/pexels-photo-29343148.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|outdoor_lamps|Outdoor Lamps|https://images.pexels.com/photos/1683405/pexels-photo-1683405.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1554740/pexels-photo-1554740.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18074245/pexels-photo-18074245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37460216/pexels-photo-37460216.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|string_lights|String Lights|https://images.pexels.com/photos/3368822/pexels-photo-3368822.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1791747/pexels-photo-1791747.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5836535/pexels-photo-5836535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15433418/pexels-photo-15433418.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|plants_planters|Plants & Planters|https://images.pexels.com/photos/8549167/pexels-photo-8549167.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3952031/pexels-photo-3952031.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7245621/pexels-photo-7245621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31479159/pexels-photo-31479159.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|aromas_candles|Aromas & Candles|https://images.pexels.com/photos/5816995/pexels-photo-5816995.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18408479/pexels-photo-18408479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31588837/pexels-photo-31588837.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35047380/pexels-photo-35047380.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|clocks|Clocks|https://images.pexels.com/photos/6616845/pexels-photo-6616845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2182727/pexels-photo-2182727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38699699/pexels-photo-38699699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/707676/pexels-photo-707676.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|mirrors|Mirrors|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/28460938/2024/3/22/7fb09e9c-86e0-4602-b54e-fa5c0171b50b1711104156746IrregularMirrorHomeDecor1.jpg|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/1.webp|https://images.pexels.com/photos/7319683/pexels-photo-7319683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28533705/pexels-photo-28533705.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|wall_decor|Wall Décor|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/2.webp|https://cdn.dummyjson.com/product-images/home-decoration/family-tree-photo-frame/1.webp|https://images.pexels.com/photos/6925113/pexels-photo-6925113.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6580004/pexels-photo-6580004.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|festive_decor|Festive Decor|https://images.pexels.com/photos/4078516/pexels-photo-4078516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34387790/pexels-photo-34387790.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443947/pexels-photo-6443947.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34473414/pexels-photo-34473414.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|pooja_essentials|Pooja Essentials|https://images.pexels.com/photos/6213685/pexels-photo-6213685.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31372002/pexels-photo-31372002.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10182772/pexels-photo-10182772.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37650548/pexels-photo-37650548.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|wall_shelves|Wall Shelves|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/2.webp|https://images.pexels.com/photos/6851831/pexels-photo-6851831.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19857232/pexels-photo-19857232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25906569/pexels-photo-25906569.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|fountains|Fountains|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/2.webp|https://images.pexels.com/photos/11651964/pexels-photo-11651964.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17087908/pexels-photo-17087908.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|showpieces_vases|Showpieces & Vases|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/2.webp|https://images.pexels.com/photos/40167789/pexels-photo-40167789.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35211364/pexels-photo-35211364.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|ottoman|Ottoman|https://images.pexels.com/photos/32123565/pexels-photo-32123565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12289357/pexels-photo-12289357.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17771969/pexels-photo-17771969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19580060/pexels-photo-19580060.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|cushions_cushion_covers|Cushions & Cushion Covers|https://images.pexels.com/photos/30163102/pexels-photo-30163102.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36482950/pexels-photo-36482950.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7218188/pexels-photo-7218188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18258470/pexels-photo-18258470.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|curtains|Curtains|https://images.pexels.com/photos/5835536/pexels-photo-5835536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35165092/pexels-photo-35165092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23110026/pexels-photo-23110026.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12944779/pexels-photo-12944779.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|home_decor|Home Décor|home_gift_sets|Home Gift Sets|https://cdn.dummyjson.com/product-images/home-decoration/table-lamp/1.webp|https://images.pexels.com/photos/7674564/pexels-photo-7674564.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1666070/pexels-photo-1666070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3873488/pexels-photo-3873488.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_runners|Table Runners|https://cdn.dummyjson.com/product-images/kitchen-accessories/bamboo-spatula/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/1.webp|https://images.pexels.com/photos/34232560/pexels-photo-34232560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8108068/pexels-photo-8108068.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|dinnerware_serveware|Dinnerware & Serveware|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/2.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-whisk/1.webp|https://images.pexels.com/photos/3847465/pexels-photo-3847465.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8903360/pexels-photo-8903360.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|cups_mugs|Cups And Mugs|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/2.webp|https://images.pexels.com/photos/9261414/pexels-photo-9261414.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7192236/pexels-photo-7192236.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bakeware_cookware|Bakeware & Cookware|https://cdn.dummyjson.com/product-images/kitchen-accessories/carbon-steel-wok/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/chopping-board/1.webp|https://images.pexels.com/photos/12586814/pexels-photo-12586814.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29462835/pexels-photo-29462835.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|kitchen_storage_tools|Kitchen Storage & Tools|https://cdn.dummyjson.com/product-images/kitchen-accessories/citrus-squeezer-yellow/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/egg-slicer/1.webp|https://images.pexels.com/photos/10568352/pexels-photo-10568352.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8175333/pexels-photo-8175333.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bar_drinkware|Bar & Drinkware|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/2.webp|https://images.pexels.com/photos/7282898/pexels-photo-7282898.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19393192/pexels-photo-19393192.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_covers_furnishings|Table Covers & Furnishings|https://cdn.dummyjson.com/product-images/kitchen-accessories/fine-mesh-strainer/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/fork/1.webp|https://images.pexels.com/photos/8158010/pexels-photo-8158010.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9512746/pexels-photo-9512746.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|television|Television|television|Television|https://rukminim2.flixcart.com/image/312/312/xif0q/television/9/p/9/-original-imah2v29z86u7b79.jpeg?q=70|https://images.pexels.com/photos/5202925/pexels-photo-5202925.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13806260/pexels-photo-13806260.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6020432/pexels-photo-6020432.jpeg?auto=compress&cs=tinysrgb&w=800",
////            "electronics|Electronics|cameras|Cameras|cameras|Cameras|https://images.pexels.com/photos/15111004/pexels-photo-15111004.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12548528/pexels-photo-12548528.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19358981/pexels-photo-19358981.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22064439/pexels-photo-22064439.jpeg?auto=compress&cs=tinysrgb&w=800"
////    };
////
////    private static final String[] STYLES = {"Signature", "Everyday", "Classic", "Premium", "Essential", "Heritage"};
////    private static final String[] COLORS = {"Black", "White", "Blue", "Red", "Green", "Pink", "Yellow", "Brown", "Grey", "Beige"};
////
////    @Override
////    public void run(String... args) {
////        if (!ENABLED) return;
////
////        // Demo seller pehle se hai to wahi use karo, warna naya banao
////        Seller seller = sellerRepository.findByEmail(DEMO_EMAIL);
////        if (seller == null) seller = createDemoSeller();
////
////        // Pehle se bane demo products (category ke hisaab se) — inki sirf photos update hongi, double nahi banenge
////        Map<String, List<Product>> existing = new HashMap<>();
////        for (Product p : productRepository.findBySellerId(seller.getId())) {
////            if (p.getCategory() != null) {
////                existing.computeIfAbsent(p.getCategory().getCategoryId(), x -> new ArrayList<>()).add(p);
////            }
////        }
////
////        List<Product> products = new ArrayList<>();
////        int created = 0, updated = 0;
////
////        for (int i = 0; i < ROWS.length; i++) {
////            String[] r = ROWS[i].split("\\|");
////            String[] imgs = java.util.Arrays.copyOfRange(r, 6, r.length);   // is category ki saari photos
////            List<Product> old = existing.getOrDefault(r[4], new ArrayList<>());
////
////            Category c1 = findOrCreate(r[0], r[1], 1, null);
////            Category c2 = findOrCreate(r[2], r[3], 2, c1);
////            // Agar level-3 id aur level-2 id same hai to product seedha level 2 me jaata hai
////            Category c3 = r[4].equals(r[2]) ? c2 : findOrCreate(r[4], r[5], 3, c2);
////
////            // 1) Purane demo products ko nayi alag photo do (double nahi banenge)
////            for (int k = 0; k < old.size(); k++) {
////                old.get(k).setImages(imagesFor(imgs, k));
////                products.add(old.get(k));
////                updated++;
////            }
////
////            // 2) Jitne kam hain utne naye banao — har category me kul 4 products
////            for (int k = old.size(); k < imgs.length; k++) {
////                products.add(buildProduct(r, imgs, c3, seller, i, k));
////                created++;
////            }
////        }
////
////        productRepository.saveAll(products);
////        System.out.println("[DemoDataSeeder] " + created + " naye demo products bane, " + updated + " purano ki photos update hui ✔");
////    }
////
////    private Seller createDemoSeller() {
////        Seller seller = new Seller();
////        seller.setSellerName("Shopzy Studio");
////        seller.setEmail(DEMO_EMAIL);
////        seller.setMobile("9999999999");
////        seller.setGSTIN("22AAAAA0000A1Z5");
////        seller.setRole(USER_ROLE.ROLE_SELLER);
////        seller.setEmailVerified(true);
////        seller.setAccountStatus(AccountStatus.ACTIVE);
////        seller.getBusinessDetails().setBusinessName("Shopzy Studio");
////        seller.getBusinessDetails().setBusinessEmail(DEMO_EMAIL);
////        seller.getBusinessDetails().setBusinessMobile("9999999999");
////        seller.getPickupAddress().setName("Shopzy Studio");
////        seller.getPickupAddress().setCity("Jaipur");
////        seller.getPickupAddress().setState("Rajasthan");
////        seller.getPickupAddress().setPinCode("302001");
////        seller.getPickupAddress().setMobile("9999999999");
////        return sellerRepository.save(seller);
////    }
////
////    private Category findOrCreate(String id, String name, int level, Category parent) {
////        Category existing = categoryRepository.findByCategoryId(id);
////        if (existing != null) return existing;
////        Category c = new Category();
////        c.setCategoryId(id);
////        c.setName(name);
////        c.setLevel(level);
////        c.setParentCategory(parent);
////        return categoryRepository.save(c);
////    }
////
////    // Product k ki pehli photo sirf usi ki hai; doosri photo (hover) agle product ki
////    private List<String> imagesFor(String[] imgs, int k) {
////        String main = imgs[k % imgs.length];
////        String other = imgs[(k + 1) % imgs.length];
////        return new ArrayList<>(List.of(main, other));
////    }
////
////    private Product buildProduct(String[] r, String[] imgs, Category category, Seller seller, int i, int k) {
////        String level1 = r[0];
////        String level2 = r[2];
////        String name = r[5];
////        int seed = Math.abs((r[4] + k).hashCode());
////
////        // Category ke hisaab se price range
////        int base;
////        if (level2.equals("mobiles")) base = 9999 + (seed % 30) * 1000;
////        else if (level1.equals("electronics")) base = 799 + (seed % 40) * 250;
////        else if (level1.equals("furniture")) base = 499 + (seed % 30) * 150;
////        else base = 599 + (seed % 25) * 100;
////
////        int discount = 20 + (seed % 5) * 10;              // 20% - 60%
////        int mrp = (int) Math.round(base / (1 - discount / 100.0) / 10.0) * 10 - 1;
////        int selling = base;
////
////        String sizes;
////        if (level2.contains("footwear")) sizes = "6,7,8,9,10";
////        else if (level1.equals("men") || level1.equals("women")) sizes = "S,M,L,XL,XXL";
////        else sizes = "FREE";
////
////        Product p = new Product();
////        p.setTitle(STYLES[(i + k) % STYLES.length] + " " + name);
////        p.setDescription("A " + STYLES[(i + k) % STYLES.length].toLowerCase() + " pick from our " + name
////                + " collection — thoughtfully made, easy to love and built to last.");
////        p.setMrpPrice(mrp);
////        p.setSellingPrice(selling);
////        p.setDiscountPercent((int) Math.round((mrp - selling) * 100.0 / mrp));
////        p.setQuantity(100);
////        p.setColor(COLORS[seed % COLORS.length]);
////        p.setImages(imagesFor(imgs, k));
////        p.setNumRatings(0);
////        p.setCategory(category);
////        p.setSeller(seller);
////        p.setCreatedAt(LocalDateTime.now());
////        p.setSizes(sizes);
////        return p;
////    }
////}
//
//
//package com.raghav.ecommerce.config;
//
//import com.raghav.ecommerce.domain.AccountStatus;
//import com.raghav.ecommerce.domain.USER_ROLE;
//import com.raghav.ecommerce.model.Category;
//import com.raghav.ecommerce.model.Product;
//import com.raghav.ecommerce.model.Seller;
//import com.raghav.ecommerce.repository.CategoryRepository;
//import com.raghav.ecommerce.repository.ProductRepository;
//import com.raghav.ecommerce.repository.SellerRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
///**
// * DEMO DATA — backend start hote hi mega menu ki HAR category (216 + 4) me 6-8 asli jaise
// * demo products daal deta hai (kul 1326 products): brand, sahi daam, discount, size, colour, specs.
// *
// * - Photos: har category ki 4 photos (ROWS) — har product ki pehli photo alag ghoomti hai.
// * - Dobara chalane par double nahi banta: (category + title) pehle se hai to skip.
// * - Purane generic demo products ("Essential Women Sarees", "Classic Smart Watches" ...) hata deta hai,
// *   lekin jo kisi order / cart / wishlist me hai use nahi chhoota.
// * - Band karna ho to ENABLED = false. Purane generic rakhne ho to REMOVE_OLD_GENERIC = false.
// */
//@Component
//public class DemoDataSeeder implements CommandLineRunner {
//
//    private static final boolean ENABLED = true;
//    private static final boolean REMOVE_OLD_GENERIC = true;
//    private static final String DEMO_EMAIL = "demo.seller@shopzy.in";
//
//    private final SellerRepository sellerRepository;
//    private final CategoryRepository categoryRepository;
//    private final ProductRepository productRepository;
//
//    public DemoDataSeeder(SellerRepository sellerRepository,
//                          CategoryRepository categoryRepository,
//                          ProductRepository productRepository) {
//        this.sellerRepository = sellerRepository;
//        this.categoryRepository = categoryRepository;
//        this.productRepository = productRepository;
//    }
//
//    // level1Id | level1Name | level2Id | level2Name | level3Id | level3Name | image1 | image2 | image3 | image4
//    private static final String[] ROWS = {
//            "men|Men|men_topwear|Topwear|men_t_shirts|Men T-Shirts|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2024/AUGUST/26/zLmM5KEB_e4781ac0cc0945119e653f4130994bd2.jpg|https://images.pexels.com/photos/9558713/pexels-photo-9558713.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6311251/pexels-photo-6311251.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5159242/pexels-photo-5159242.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_casual_shirts|Men Casual Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/2.webp|https://images.pexels.com/photos/35182320/pexels-photo-35182320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40070436/pexels-photo-40070436.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_formal_shirts|Men Formal Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/2.webp|https://images.pexels.com/photos/29765480/pexels-photo-29765480.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35415004/pexels-photo-35415004.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_sweatshirts|Men Sweatshirts|https://images.pexels.com/photos/19338764/pexels-photo-19338764.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34582212/pexels-photo-34582212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9920820/pexels-photo-9920820.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6650360/pexels-photo-6650360.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_sweaters|Men Sweaters|https://images.pexels.com/photos/10427343/pexels-photo-10427343.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7237067/pexels-photo-7237067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14269719/pexels-photo-14269719.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558897/pexels-photo-9558897.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_jackets|Men Jackets|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23029834/2023/9/18/96c015ae-1090-4036-954b-d9c80085b1d71695022844653-HRX-by-Hrithik-Roshan-Men-Jackets-6981695022843934-1.jpg|https://images.pexels.com/photos/18720773/pexels-photo-18720773.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21619158/pexels-photo-21619158.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18633077/pexels-photo-18633077.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_blazers_and_coats|Men Blazers & Coats|https://images.pexels.com/photos/9210389/pexels-photo-9210389.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2955375/pexels-photo-2955375.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32670017/pexels-photo-32670017.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29722004/pexels-photo-29722004.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_suits|Men Suits|https://images.pexels.com/photos/2505053/pexels-photo-2505053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/935969/pexels-photo-935969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050435/pexels-photo-6050435.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050409/pexels-photo-6050409.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_rain_jackets|Men Rain Jackets|https://images.pexels.com/photos/6851461/pexels-photo-6851461.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2766298/pexels-photo-2766298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12505397/pexels-photo-12505397.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26838402/pexels-photo-26838402.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_indian_and_festive_wear|Men Indian & Festive Wear|https://rukminim2.flixcart.com/image/612/612/xif0q/ethnic-set/o/n/o/l-02-langit-original-imah4jwwctkjtman.jpeg?q=70|https://shreeman.in/cdn/shop/files/20_3cfbd5a3-ecb6-482a-b798-7ffd9de1c784.jpg?v=1712061674&width=700|https://images.pexels.com/photos/13624155/pexels-photo-13624155.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217656/pexels-photo-8217656.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_kurtas_and_kurta_sets|Men Kurtas & Kurta Sets|https://images.pexels.com/photos/13222257/pexels-photo-13222257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777497/pexels-photo-16777497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38443317/pexels-photo-38443317.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36697919/pexels-photo-36697919.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_sherwanis|Men Sherwanis|https://images.pexels.com/photos/12963836/pexels-photo-12963836.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18694227/pexels-photo-18694227.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37163999/pexels-photo-37163999.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28802603/pexels-photo-28802603.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_nehru_jackets|Men Nehru Jackets|https://images.pexels.com/photos/34423748/pexels-photo-34423748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777516/pexels-photo-16777516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33049965/pexels-photo-33049965.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32947800/pexels-photo-32947800.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_dhotis|Men Dhotis|https://images.pexels.com/photos/20871919/pexels-photo-20871919.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14934020/pexels-photo-14934020.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29041818/pexels-photo-29041818.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29153202/pexels-photo-29153202.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_jeans|Men Jeans|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/20122324/2022/9/22/91c61c45-fe17-4d1d-8e20-0aaaf90186b61663827920015RaymondSlimFitBlueJeansForMen1.jpg|https://images.pexels.com/photos/9775489/pexels-photo-9775489.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16069736/pexels-photo-16069736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4258605/pexels-photo-4258605.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_casual_trousers|Men Casual Trousers|https://images.pexels.com/photos/11034966/pexels-photo-11034966.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10004175/pexels-photo-10004175.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897539/pexels-photo-2897539.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9464625/pexels-photo-9464625.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_formal_trousers|Men Formal Trousers|https://images.pexels.com/photos/2815417/pexels-photo-2815417.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558246/pexels-photo-9558246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897533/pexels-photo-2897533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17082930/pexels-photo-17082930.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_shorts|Men Shorts|https://images.pexels.com/photos/1098364/pexels-photo-1098364.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13081849/pexels-photo-13081849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25315887/pexels-photo-25315887.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18178103/pexels-photo-18178103.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_track_pants_and_joggers|Men Track Pants & Joggers|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24528350/2023/8/17/d9ee03c9-7e15-49e4-8f15-0b6f38e568121692275415567TrackPants1.jpg|https://images.pexels.com/photos/11328499/pexels-photo-11328499.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9901666/pexels-photo-9901666.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9499128/pexels-photo-9499128.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_briefs_and_trunks|Men Briefs & Trunks|https://images.pexels.com/photos/8875156/pexels-photo-8875156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874670/pexels-photo-8874670.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874933/pexels-photo-8874933.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874910/pexels-photo-8874910.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_boxers|Men Boxers|https://images.pexels.com/photos/8875171/pexels-photo-8875171.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874672/pexels-photo-8874672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8875173/pexels-photo-8875173.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17510799/pexels-photo-17510799.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_vests|Men Vests|https://images.pexels.com/photos/7711284/pexels-photo-7711284.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13544290/pexels-photo-13544290.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17604597/pexels-photo-17604597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38216749/pexels-photo-38216749.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_sleepwear_and_loungewear|Men Sleepwear & Loungewear|https://images.pexels.com/photos/8416232/pexels-photo-8416232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30444027/pexels-photo-30444027.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6976782/pexels-photo-6976782.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443431/pexels-photo-6443431.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_thermals|Men Thermals|https://images.pexels.com/photos/5924447/pexels-photo-5924447.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4763665/pexels-photo-4763665.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6712133/pexels-photo-6712133.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6799715/pexels-photo-6799715.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_plus_size|Men Plus Size|https://images.pexels.com/photos/8217535/pexels-photo-8217535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217536/pexels-photo-8217536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33676395/pexels-photo-33676395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30589840/pexels-photo-30589840.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_casual_shoes|Men Casual Shoes|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27107914/2024/1/30/bd09f650-d8a4-4570-8e53-e2cfafc4ea6c1706609911015SparxMenMeshRunningShoes1.jpg|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24651572/2023/8/25/4fbf6d8c-d093-46c5-a5a6-7dd67c0c76551692964752597HouseofPataudiMenTanFauxLeatherFormalSlipOnLoafers1.jpg|https://images.pexels.com/photos/40263252/pexels-photo-40263252.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40325298/pexels-photo-40325298.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_sports_shoes|Men Sports Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/2.webp|https://images.pexels.com/photos/260044/pexels-photo-260044.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8454904/pexels-photo-8454904.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_formal_shoes|Men Formal Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/2.webp|https://images.pexels.com/photos/12210270/pexels-photo-12210270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10352336/pexels-photo-10352336.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_sneakers|Men Sneakers|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/2.webp|https://images.pexels.com/photos/5560288/pexels-photo-5560288.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4901935/pexels-photo-4901935.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_sandals_and_floaters|Men Sandals & Floaters|https://images.pexels.com/photos/4996738/pexels-photo-4996738.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/952843/pexels-photo-952843.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23692992/pexels-photo-23692992.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9214975/pexels-photo-9214975.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_flip_flops|Men Flip Flops|https://images.pexels.com/photos/14934601/pexels-photo-14934601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34447680/pexels-photo-34447680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30335806/pexels-photo-30335806.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35166184/pexels-photo-35166184.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_socks|Men Socks|https://images.pexels.com/photos/15195233/pexels-photo-15195233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14943497/pexels-photo-14943497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10563910/pexels-photo-10563910.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12932537/pexels-photo-12932537.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sunglasses_and_frames|Men Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/2.webp|https://images.pexels.com/photos/5202048/pexels-photo-5202048.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2767694/pexels-photo-2767694.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_watches|Men Watches|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/1.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/2.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/3.webp|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/1.webp",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_and_active_wear|Men Sports & Active Wear|https://images.pexels.com/photos/1311617/pexels-photo-1311617.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036883/pexels-photo-5036883.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29259727/pexels-photo-29259727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3927383/pexels-photo-3927383.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_sandals|Men Sports Sandals|https://images.pexels.com/photos/15669736/pexels-photo-15669736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3342769/pexels-photo-3342769.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996974/pexels-photo-4996974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6008231/pexels-photo-6008231.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_active_t_shirts|Men Active T-Shirts|https://images.pexels.com/photos/8498700/pexels-photo-8498700.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/1.webp|https://images.pexels.com/photos/4004222/pexels-photo-4004222.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6998798/pexels-photo-6998798.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_track_pants_and_shorts|Men Track Pants & Shorts|https://images.pexels.com/photos/13069429/pexels-photo-13069429.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8567521/pexels-photo-8567521.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30710156/pexels-photo-30710156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11029622/pexels-photo-11029622.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_tracksuits|Men Tracksuits|https://images.pexels.com/photos/9488418/pexels-photo-9488418.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2652236/pexels-photo-2652236.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6158668/pexels-photo-6158668.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33558335/pexels-photo-33558335.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_jackets_and_sweatshirts|Men Jackets & Sweatshirts|https://images.pexels.com/photos/14611192/pexels-photo-14611192.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9775733/pexels-photo-9775733.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28701960/pexels-photo-28701960.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15127546/pexels-photo-15127546.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_accessories|Men Sports Accessories|https://images.pexels.com/photos/4162451/pexels-photo-4162451.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6740821/pexels-photo-6740821.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35567437/pexels-photo-35567437.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19025674/pexels-photo-19025674.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_swimwear|Men Swimwear|https://images.pexels.com/photos/9030517/pexels-photo-9030517.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6011894/pexels-photo-6011894.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8028651/pexels-photo-8028651.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8688187/pexels-photo-8688187.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_wallets|Men Wallets|https://images.pexels.com/photos/915917/pexels-photo-915917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/164637/pexels-photo-164637.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7952556/pexels-photo-7952556.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4452393/pexels-photo-4452393.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_belts|Men Belts|https://images.pexels.com/photos/6654763/pexels-photo-6654763.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31323080/pexels-photo-31323080.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959214/pexels-photo-31959214.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959216/pexels-photo-31959216.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_perfumes_and_body_mists|Men Perfumes & Body Mists|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/1.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/2.webp|https://images.pexels.com/photos/15096784/pexels-photo-15096784.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7703038/pexels-photo-7703038.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_trimmers|Men Trimmers|https://images.pexels.com/photos/897262/pexels-photo-897262.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6007395/pexels-photo-6007395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7253884/pexels-photo-7253884.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16553361/pexels-photo-16553361.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_deodorants|Men Deodorants|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/1.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/2.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/3.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/3.webp",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_ties_cufflinks_and_pocket_squares|Men Ties, Cufflinks & Pocket Squares|https://images.pexels.com/photos/21928749/pexels-photo-21928749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38270/pexels-photo-38270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32392710/pexels-photo-32392710.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34342976/pexels-photo-34342976.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_accessory_gift_sets|Men Accessory Gift Sets|https://images.pexels.com/photos/190930/pexels-photo-190930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/264771/pexels-photo-264771.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6087540/pexels-photo-6087540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1303087/pexels-photo-1303087.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_caps_and_hats|Men Caps & Hats|https://images.pexels.com/photos/18417334/pexels-photo-18417334.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13265200/pexels-photo-13265200.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27401299/pexels-photo-27401299.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29925645/pexels-photo-29925645.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_mufflers_scarves_and_gloves|Men Mufflers, Scarves & Gloves|https://images.pexels.com/photos/1903760/pexels-photo-1903760.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20281423/pexels-photo-20281423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16049198/pexels-photo-16049198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35830300/pexels-photo-35830300.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_phone_cases|Men Phone Cases|https://images.pexels.com/photos/374140/pexels-photo-374140.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7742503/pexels-photo-7742503.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13706809/pexels-photo-13706809.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18403793/pexels-photo-18403793.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_rings_and_wristwear|Men Rings & Wristwear|https://images.pexels.com/photos/16304565/pexels-photo-16304565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9742330/pexels-photo-9742330.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2442905/pexels-photo-2442905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15325460/pexels-photo-15325460.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_helmets|Men Helmets|https://images.pexels.com/photos/9835238/pexels-photo-9835238.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12000077/pexels-photo-12000077.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5803274/pexels-photo-5803274.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5355536/pexels-photo-5355536.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtas_and_suits|Women Kurtas & Suits|https://images.pexels.com/photos/11661072/pexels-photo-11661072.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33824984/pexels-photo-33824984.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30196701/pexels-photo-30196701.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1322993/pexels-photo-1322993.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtis_tunics_tops|Women Kurtis, Tunics & Tops|https://images.pexels.com/photos/19556879/pexels-photo-19556879.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7371768/pexels-photo-7371768.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36311379/pexels-photo-36311379.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35521738/pexels-photo-35521738.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_sarees|Women Sarees|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950865601_540x.jpg?v=1704965372|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950931137_540x.jpg?v=1704965372|https://images.pexels.com/photos/7920188/pexels-photo-7920188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27719401/pexels-photo-27719401.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_ethnic_wear|Women Ethnic Wear|https://images.pexels.com/photos/37523799/pexels-photo-37523799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512776/pexels-photo-28512776.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512787/pexels-photo-28512787.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13178920/pexels-photo-13178920.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_leggings_salwars_churidars|Women Leggings, Salwars & Churidars|https://rukminim2.flixcart.com/image/612/612/xif0q/skirt/k/o/h/free-1-00003-custom-fashion-original-imagztykafwbqupf.jpeg?q=70|https://images.pexels.com/photos/19932178/pexels-photo-19932178.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20382095/pexels-photo-20382095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19271174/pexels-photo-19271174.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_skirts_palazzos|Women Skirts & Palazzos|https://images.pexels.com/photos/14997427/pexels-photo-14997427.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16721105/pexels-photo-16721105.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40293396/pexels-photo-40293396.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39030338/pexels-photo-39030338.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dress_materials|Women Dress Materials|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950996673_540x.jpg?v=1704965372|https://rukminim2.flixcart.com/image/832/832/xif0q/sari/z/a/4/free-saree-new2023-simple-light-weight-saree-chiffon-print-saree-original-imagzyhp2azy7rwz.jpeg?q=70&crop=false|https://images.pexels.com/photos/34933671/pexels-photo-34933671.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20841148/pexels-photo-20841148.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_lehenga_cholis|Women Lehenga Cholis|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23807268/2023/6/29/9930b235-5318-4755-abbe-08f99e969e781688026636544LehengaCholi7.jpg|https://images.pexels.com/photos/12730873/pexels-photo-12730873.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28405815/pexels-photo-28405815.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33343591/pexels-photo-33343591.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dupattas_shawls|Women Dupattas & Shawls|https://images.pexels.com/photos/11004120/pexels-photo-11004120.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18659930/pexels-photo-18659930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158070/pexels-photo-35158070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158052/pexels-photo-35158052.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_jackets|Women Jackets|https://images.pexels.com/photos/1818431/pexels-photo-1818431.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8386453/pexels-photo-8386453.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39054974/pexels-photo-39054974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036161/pexels-photo-5036161.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_belts_scarves_more|Women Belts, Scarves & More|https://images.pexels.com/photos/31432650/pexels-photo-31432650.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8014845/pexels-photo-8014845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37483619/pexels-photo-37483619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959217/pexels-photo-31959217.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_watches_wearables|Women Watches & Wearables|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/3.webp",
//            "women|Women|women_western_wear|Western Wear|women_dresses|Women Dresses|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/2.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/3.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/4.webp",
//            "women|Women|women_western_wear|Western Wear|women_tops|Women Tops|https://cdn.dummyjson.com/product-images/tops/blue-frock/1.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/2.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/3.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/4.webp",
//            "women|Women|women_western_wear|Western Wear|women_tshirts|Women Tshirts|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/2.webp|https://images.pexels.com/photos/30957211/pexels-photo-30957211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24205642/pexels-photo-24205642.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_jeans|Women Jeans|https://images.pexels.com/photos/17302419/pexels-photo-17302419.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/936117/pexels-photo-936117.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20729717/pexels-photo-20729717.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7636101/pexels-photo-7636101.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_trousers_capris|Women Trousers & Capris|https://images.pexels.com/photos/450212/pexels-photo-450212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6182574/pexels-photo-6182574.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7205905/pexels-photo-7205905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19995460/pexels-photo-19995460.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_shorts_skirts|Women Shorts & Skirts|https://images.pexels.com/photos/6012003/pexels-photo-6012003.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/383603/pexels-photo-383603.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7391126/pexels-photo-7391126.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16431244/pexels-photo-16431244.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_coords|Women Co-ords|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/2.webp|https://images.pexels.com/photos/31400265/pexels-photo-31400265.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37036179/pexels-photo-37036179.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_playsuits|Women Playsuits|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/2.webp|https://images.pexels.com/photos/7545423/pexels-photo-7545423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29017408/pexels-photo-29017408.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_jumpsuits|Women Jumpsuits|https://images.pexels.com/photos/21374896/pexels-photo-21374896.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4006508/pexels-photo-4006508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2699250/pexels-photo-2699250.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13219628/pexels-photo-13219628.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_shrugs|Women Shrugs|https://cdn.dummyjson.com/product-images/tops/gray-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/gray-dress/2.webp|https://images.pexels.com/photos/7585673/pexels-photo-7585673.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5710033/pexels-photo-5710033.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_sweaters_sweatshirts|Women Sweaters & Sweatshirts|https://images.pexels.com/photos/2080383/pexels-photo-2080383.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9811069/pexels-photo-9811069.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33688875/pexels-photo-33688875.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6347515/pexels-photo-6347515.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_jackets_coats|Women Jackets & Coats|https://images.pexels.com/photos/6974936/pexels-photo-6974936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11587144/pexels-photo-11587144.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12083001/pexels-photo-12083001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34366695/pexels-photo-34366695.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_blazers_waistcoats|Women Blazers & Waistcoats|https://images.pexels.com/photos/10955935/pexels-photo-10955935.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14495270/pexels-photo-14495270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7691041/pexels-photo-7691041.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8484005/pexels-photo-8484005.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_plus_size|Women Plus Size|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/2.webp|https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/1.webp|https://images.pexels.com/photos/14693247/pexels-photo-14693247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18586865/pexels-photo-18586865.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_maternity|Women Maternity|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/2.webp|https://images.pexels.com/photos/20770621/pexels-photo-20770621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15968597/pexels-photo-15968597.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_sunglasses_frames|Women Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/2.webp|https://images.pexels.com/photos/15735139/pexels-photo-15735139.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26575042/pexels-photo-26575042.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_flats|Women Flats|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/2.webp|https://images.pexels.com/photos/14816287/pexels-photo-14816287.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36581190/pexels-photo-36581190.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_casual_shoes|Women Casual Shoes|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/2.webp|https://images.pexels.com/photos/7887198/pexels-photo-7887198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1027130/pexels-photo-1027130.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_heels|Women Heels|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/2.webp|https://images.pexels.com/photos/36212466/pexels-photo-36212466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34294446/pexels-photo-34294446.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_boots|Women Boots|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/2.webp|https://images.pexels.com/photos/26856061/pexels-photo-26856061.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27174561/pexels-photo-27174561.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_sports_shoes_floaters|Women Sports Shoes & Floaters|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/2.webp|https://images.pexels.com/photos/4065509/pexels-photo-4065509.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15659357/pexels-photo-15659357.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_sports_active_wear|Sports & Active Wear|women_clothing|Women Clothing|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/22109480/2023/9/5/06a17ac3-46b0-4f9d-bcb1-2d3582feda041693895310152PumaWomenBrandLogoPrintedPureCottonOutdoorT-shirt1.jpg|https://images.pexels.com/photos/8436750/pexels-photo-8436750.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27433185/pexels-photo-27433185.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7894597/pexels-photo-7894597.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_accessories|Women Sports Accessories|https://images.pexels.com/photos/9944907/pexels-photo-9944907.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16513597/pexels-photo-16513597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6246682/pexels-photo-6246682.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6339731/pexels-photo-6339731.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_equipment|Women Sports Equipment|https://cdn.dummyjson.com/product-images/sports-accessories/american-football/1.webp|https://cdn.dummyjson.com/product-images/sports-accessories/baseball-ball/1.webp|https://images.pexels.com/photos/7743320/pexels-photo-7743320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6550849/pexels-photo-6550849.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_bra|Women Bra|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/362895/2023/10/18/bfb55058-fe1a-45d6-a39d-1b8d421e02001697603774147TriumphShapeSensation33withHighWaistTummyandThighControlMaxi1.jpg|https://images.pexels.com/photos/10782049/pexels-photo-10782049.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6879815/pexels-photo-6879815.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29835139/pexels-photo-29835139.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_briefs|Women Briefs|https://images.pexels.com/photos/10476640/pexels-photo-10476640.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17604582/pexels-photo-17604582.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29474759/pexels-photo-29474759.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11415285/pexels-photo-11415285.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_shapewear|Women Shapewear|https://images.pexels.com/photos/8966046/pexels-photo-8966046.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17040812/pexels-photo-17040812.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5256325/pexels-photo-5256325.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5256331/pexels-photo-5256331.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_sleepwear_loungewear|Women Sleepwear & Loungewear|https://images.pexels.com/photos/6976434/pexels-photo-6976434.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18820123/pexels-photo-18820123.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8416240/pexels-photo-8416240.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8497487/pexels-photo-8497487.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_swimwear|Women Swimwear|https://images.pexels.com/photos/7365485/pexels-photo-7365485.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5638968/pexels-photo-5638968.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13697814/pexels-photo-13697814.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31094948/pexels-photo-31094948.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_camisoles_thermals|Women Camisoles & Thermals|https://images.pexels.com/photos/4029921/pexels-photo-4029921.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6569963/pexels-photo-6569963.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6569968/pexels-photo-6569968.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6544543/pexels-photo-6544543.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_beauty_personal_care|Beauty & Personal Care|https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/1.webp|https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/1.webp|https://cdn.dummyjson.com/product-images/beauty/powder-canister/1.webp|https://cdn.dummyjson.com/product-images/beauty/red-lipstick/1.webp",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_makeup|Women Makeup|https://images.pexels.com/photos/23228944/pexels-photo-23228944.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1571585/pexels-photo-1571585.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4938498/pexels-photo-4938498.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3148938/pexels-photo-3148938.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_skincare|Women Skincare|https://cdn.dummyjson.com/product-images/beauty/red-nail-polish/1.webp|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/1.webp|https://images.pexels.com/photos/12115040/pexels-photo-12115040.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28994644/pexels-photo-28994644.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_premium_beauty|Women Premium Beauty|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/2.webp|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/1.webp|https://images.pexels.com/photos/7020247/pexels-photo-7020247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6925496/pexels-photo-6925496.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_lipsticks|Women Lipsticks|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/2.webp|https://cdn.dummyjson.com/product-images/skin-care/vaseline-men-body-and-face-lotion/1.webp|https://images.pexels.com/photos/34321279/pexels-photo-34321279.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6648498/pexels-photo-6648498.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_fragrances|Women Fragrances|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/1.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/2.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/3.webp|https://cdn.dummyjson.com/product-images/fragrances/dolce-shine-eau-de/1.webp",
//            "women|Women|women_gadgets|Gadgets|women_gadgets|Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/2.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/2.webp",
//            "women|Women|women_gadgets|Gadgets|women_smart_wearables|Women Smart Wearables|https://rukminim2.flixcart.com/image/612/612/xif0q/smartwatch/f/g/g/-original-imagywnz46fngcks.jpeg?q=70|https://rukminim2.flixcart.com/image/416/416/xif0q/smartwatch/u/r/h/-original-imagyxxfgz52vzqn.jpeg?q=70&crop=false|https://images.pexels.com/photos/31406895/pexels-photo-31406895.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31406903/pexels-photo-31406903.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_gadgets|Gadgets|women_fitness_gadgets|Women Fitness Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/2.webp|https://images.pexels.com/photos/5036927/pexels-photo-5036927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/374673/pexels-photo-374673.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_gadgets|Gadgets|women_headphones|Women Headphones|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods-max-silver/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/beats-flex-wireless-earphones/1.webp|https://images.pexels.com/photos/210927/pexels-photo-210927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7772548/pexels-photo-7772548.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_gadgets|Gadgets|women_speakers|Women Speakers|https://images.pexels.com/photos/374110/pexels-photo-374110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13465232/pexels-photo-13465232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4917455/pexels-photo-4917455.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29617989/pexels-photo-29617989.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_jewellery|Jewellery|women_fashion_jewellery|Women Fashion Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/2.webp|https://images.pexels.com/photos/10862233/pexels-photo-10862233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7514818/pexels-photo-7514818.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_jewellery|Jewellery|women_fine_jewellery|Women Fine Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/2.webp|https://images.pexels.com/photos/13924051/pexels-photo-13924051.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15925159/pexels-photo-15925159.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_jewellery|Jewellery|women_earrings|Women Earrings|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/2.webp|https://images.pexels.com/photos/10082804/pexels-photo-10082804.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15785528/pexels-photo-15785528.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_handbags_bags_wallets|Handbags, Bags & Wallets|https://images.pexels.com/photos/4430246/pexels-photo-4430246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6102145/pexels-photo-6102145.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/1.webp|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/2.webp",
//            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_luggages_trolleys|Women Luggages & Trolleys|https://images.pexels.com/photos/1682694/pexels-photo-1682694.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8212229/pexels-photo-8212229.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36933446/pexels-photo-36933446.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11499403/pexels-photo-11499403.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|mi_mobile|Mi Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|realme_mobile|Realme Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/3.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|samsung_mobile|Samsung Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|infinix_mobile|Infinix Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|oppo_mobile|OPPO Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|apple_mobile|Apple Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/3.webp",
//            "electronics|Electronics|mobiles|Mobiles|vivo_mobile|Vivo Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|honor_mobile|Honor Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|asus_mobile|Asus Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/4.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/3.webp",
//            "electronics|Electronics|mobiles|Mobiles|poco_x2_mobile|Poco X2 Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10_mobile|Realme Narzo 10 Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/3.webp",
//            "electronics|Electronics|mobiles|Mobiles|infinix_hot_9_mobile|Infinix Hot 9 Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|iqoo_3_mobile|IQOO 3 Mobile|https://images.pexels.com/photos/6901037/pexels-photo-6901037.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15092/pexels-photo-15092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5337378/pexels-photo-5337378.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5253002/pexels-photo-5253002.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|iphone_se_mobile|IPhone SE Mobile|https://images.pexels.com/photos/3945672/pexels-photo-3945672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18311092/pexels-photo-18311092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945691/pexels-photo-3945691.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945695/pexels-photo-3945695.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|motorola_razr_mobile|Motorola Razr Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/3.webp|https://images.pexels.com/photos/47261/pexels-photo-47261.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24181865/pexels-photo-24181865.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13844013/pexels-photo-13844013.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10a_mobile|Realme Narzo 10A Mobile|https://images.pexels.com/photos/3850561/pexels-photo-3850561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36680543/pexels-photo-36680543.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21854468/pexels-photo-21854468.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14486282/pexels-photo-14486282.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|motorola_g8_power_lite_mobile|Motorola G8 Power Lite Mobile|https://images.pexels.com/photos/30639091/pexels-photo-30639091.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16442035/pexels-photo-16442035.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26775917/pexels-photo-26775917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19281836/pexels-photo-19281836.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cases|Mobile Cases|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/2.webp|https://images.pexels.com/photos/12359130/pexels-photo-12359130.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1670768/pexels-photo-1670768.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|headphones_headsets|Headphones & Headsets|https://rukminim2.flixcart.com/image/612/612/kz4gh3k0/headphone/c/v/r/-original-imagb7bmhdgghzxq.jpeg?q=70|https://images.pexels.com/photos/3394660/pexels-photo-3394660.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5650531/pexels-photo-5650531.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3081173/pexels-photo-3081173.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|power_banks|Power Banks|https://images.pexels.com/photos/4072683/pexels-photo-4072683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37475677/pexels-photo-37475677.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6296911/pexels-photo-6296911.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3921704/pexels-photo-3921704.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|screenguards|Screenguards|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpower-wireless-charger/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/1.webp|https://images.pexels.com/photos/12368095/pexels-photo-12368095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17610713/pexels-photo-17610713.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|memory_cards|Memory Cards|https://images.pexels.com/photos/5882581/pexels-photo-5882581.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7057/pexels-photo-7057.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2147082/pexels-photo-2147082.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32346379/pexels-photo-32346379.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|smart_headphones|Smart Headphones|https://images.pexels.com/photos/4887213/pexels-photo-4887213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28920288/pexels-photo-28920288.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/3.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-homepod-mini-cosmic-grey/1.webp",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cables|Mobile Cables|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/2.webp|https://images.pexels.com/photos/4219860/pexels-photo-4219860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1028674/pexels-photo-1028674.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/947407/pexels-photo-947407.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_chargers|Mobile Chargers|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/2.webp|https://images.pexels.com/photos/36012993/pexels-photo-36012993.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/216369/pexels-photo-216369.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_holders|Mobile Holders|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/2.webp|https://images.pexels.com/photos/694424/pexels-photo-694424.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35037256/pexels-photo-35037256.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_watches|Smart Watches|https://images.pexels.com/photos/11031515/pexels-photo-11031515.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18662969/pexels-photo-18662969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8760799/pexels-photo-8760799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18078946/pexels-photo-18078946.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_glasses_vr|Smart Glasses (VR)|https://images.pexels.com/photos/373905/pexels-photo-373905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6848112/pexels-photo-6848112.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7561900/pexels-photo-7561900.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19545617/pexels-photo-19545617.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_bands|Smart Bands|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/2.webp|https://images.pexels.com/photos/374619/pexels-photo-374619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4426516/pexels-photo-4426516.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|gaming_laptops|Gaming Laptops|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/1.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/2.webp|https://images.pexels.com/photos/3951449/pexels-photo-3951449.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19012030/pexels-photo-19012030.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|desktop_pcs|Desktop PCs|https://images.pexels.com/photos/6415/pexels-photo-6415.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1714341/pexels-photo-1714341.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31145466/pexels-photo-31145466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9072379/pexels-photo-9072379.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|gaming_accessories|Gaming & Accessories|https://images.pexels.com/photos/194511/pexels-photo-194511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1337247/pexels-photo-1337247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7887636/pexels-photo-7887636.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34592708/pexels-photo-34592708.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|computer_accessories|Computer Accessories|https://images.pexels.com/photos/392018/pexels-photo-392018.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12786602/pexels-photo-12786602.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4065748/pexels-photo-4065748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1420709/pexels-photo-1420709.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|external_hard_disks|External Hard Disks|https://images.pexels.com/photos/20213726/pexels-photo-20213726.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6937936/pexels-photo-6937936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32892856/pexels-photo-32892856.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28461160/pexels-photo-28461160.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|pendrives|Pendrives|https://images.pexels.com/photos/19954918/pexels-photo-19954918.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10133432/pexels-photo-10133432.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4792751/pexels-photo-4792751.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10336136/pexels-photo-10336136.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|laptop_skins_decals|Laptop Skins & Decals|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/1.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/2.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/3.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/3.webp",
//            "electronics|Electronics|laptops|Laptops|laptop_bags|Laptop Bags|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/1.webp|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/2.webp|https://images.pexels.com/photos/20318749/pexels-photo-20318749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5330724/pexels-photo-5330724.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|mouse|Mouse|https://images.pexels.com/photos/7151696/pexels-photo-7151696.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38006271/pexels-photo-38006271.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6932748/pexels-photo-6932748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12029640/pexels-photo-12029640.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|computer_peripherals|Computer Peripherals|https://images.pexels.com/photos/8297860/pexels-photo-8297860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12509206/pexels-photo-12509206.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4120698/pexels-photo-4120698.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20182039/pexels-photo-20182039.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|printers_ink_cartridges|Printers & Ink Cartridges|https://images.pexels.com/photos/4792283/pexels-photo-4792283.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/777001/pexels-photo-777001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574569/pexels-photo-9574569.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574453/pexels-photo-9574453.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|monitors|Monitors|https://images.pexels.com/photos/3521937/pexels-photo-3521937.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/326512/pexels-photo-326512.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37148217/pexels-photo-37148217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6804606/pexels-photo-6804606.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|tablets|Tablets|apple_ipads|Apple IPads|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/1.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/2.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/3.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/4.webp",
//            "electronics|Electronics|speakers|Speakers|home_audio_speakers|Home Audio Speakers|https://rukminim2.flixcart.com/image/612/612/xif0q/speaker/6/z/2/-original-imahfgfkr5gkk9aq.jpeg?q=70|https://images.pexels.com/photos/191877/pexels-photo-191877.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/128611/pexels-photo-128611.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2651794/pexels-photo-2651794.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|home_theatres|Home Theatres|https://images.pexels.com/photos/31683433/pexels-photo-31683433.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/880874/pexels-photo-880874.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7031762/pexels-photo-7031762.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5008383/pexels-photo-5008383.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|soundbars|Soundbars|https://images.pexels.com/photos/374067/pexels-photo-374067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18542243/pexels-photo-18542243.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/373638/pexels-photo-373638.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10486182/pexels-photo-10486182.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|bluetooth_speakers|Bluetooth Speakers|https://images.pexels.com/photos/29606737/pexels-photo-29606737.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9767551/pexels-photo-9767551.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25450654/pexels-photo-25450654.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14017595/pexels-photo-14017595.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|dth_set_top_box|DTH Set Top Box|https://images.pexels.com/photos/5207508/pexels-photo-5207508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40195705/pexels-photo-40195705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5207514/pexels-photo-5207514.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4777979/pexels-photo-4777979.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|dslr_mirrorless_camera|DSLR & Mirrorless Camera|https://rukminim2.flixcart.com/image/312/312/jfbfde80/camera/n/r/n/canon-eos-eos-3000d-dslr-original-imaf3t5h9yuyc5zu.jpeg?q=70|https://images.pexels.com/photos/17345664/pexels-photo-17345664.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26873540/pexels-photo-26873540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6891485/pexels-photo-6891485.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|compact_bridge_cameras|Compact & Bridge Cameras|https://images.pexels.com/photos/1595232/pexels-photo-1595232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1144699/pexels-photo-1144699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16104735/pexels-photo-16104735.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4372403/pexels-photo-4372403.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|sports_action_camera|Sports & Action Camera|https://images.pexels.com/photos/274973/pexels-photo-274973.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996005/pexels-photo-4996005.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4817132/pexels-photo-4817132.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1276531/pexels-photo-1276531.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|camera_accessories|Camera Accessories|https://images.pexels.com/photos/7776871/pexels-photo-7776871.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3405454/pexels-photo-3405454.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35001053/pexels-photo-35001053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34642455/pexels-photo-34642455.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|lens|Camera Lens|https://images.pexels.com/photos/838680/pexels-photo-838680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/734535/pexels-photo-734535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11713436/pexels-photo-11713436.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29293968/pexels-photo-29293968.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|camera_tripods|Camera Tripods|https://images.pexels.com/photos/20382217/pexels-photo-20382217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15062358/pexels-photo-15062358.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11331527/pexels-photo-11331527.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20954706/pexels-photo-20954706.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_runners|Bed Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/19284508/2022/7/28/92df52de-27dc-4d72-8ab4-fee2c82c85081659003977664DreamscapeUnisexPinkBedsheets1.jpg|https://images.pexels.com/photos/5629134/pexels-photo-5629134.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6903157/pexels-photo-6903157.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27671434/pexels-photo-27671434.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|mattress_protectors|Mattress Protectors|https://images.pexels.com/photos/3030684/pexels-photo-3030684.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4740586/pexels-photo-4740586.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28513849/pexels-photo-28513849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5629138/pexels-photo-5629138.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedsheets|Bedsheets|https://images.pexels.com/photos/3682240/pexels-photo-3682240.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7019028/pexels-photo-7019028.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12200060/pexels-photo-12200060.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38651677/pexels-photo-38651677.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedding_sets|Bedding Sets|https://images.pexels.com/photos/212269/pexels-photo-212269.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10373508/pexels-photo-10373508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22711513/pexels-photo-22711513.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32418083/pexels-photo-32418083.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|blankets_quilts_dohars|Blankets, Quilts & Dohars|https://images.pexels.com/photos/8266834/pexels-photo-8266834.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6633533/pexels-photo-6633533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30399211/pexels-photo-30399211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30350497/pexels-photo-30350497.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|pillows_pillow_covers|Pillows & Pillow Covers|https://images.pexels.com/photos/12326545/pexels-photo-12326545.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9582579/pexels-photo-9582579.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9290601/pexels-photo-9290601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1239221/pexels-photo-1239221.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_covers|Bed Covers|https://images.pexels.com/photos/10980163/pexels-photo-10980163.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9262007/pexels-photo-9262007.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6782479/pexels-photo-6782479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980080/pexels-photo-19980080.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|diwan_sets|Diwan Sets|https://images.pexels.com/photos/3557507/pexels-photo-3557507.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/910458/pexels-photo-910458.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34574606/pexels-photo-34574606.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7147299/pexels-photo-7147299.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|chair_pads_covers|Chair Pads & Covers|https://images.pexels.com/photos/462197/pexels-photo-462197.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/1.webp|https://images.pexels.com/photos/18829799/pexels-photo-18829799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29917912/pexels-photo-29917912.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|sofa_covers|Sofa Covers|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/2.webp|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/1.webp|https://images.pexels.com/photos/6758245/pexels-photo-6758245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7394787/pexels-photo-7394787.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|floor_runners|Floor Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27868878/2024/2/25/53b7c07f-24a7-48e2-a791-f63dce4a0c981708844793060SANACARPETMulticolouredFloralAnti-SkidPolyesterCarpet1.jpg|https://images.pexels.com/photos/5417293/pexels-photo-5417293.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15896109/pexels-photo-15896109.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29614636/pexels-photo-29614636.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|carpets|Carpets|https://images.pexels.com/photos/6933860/pexels-photo-6933860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/45980/pexels-photo-45980.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16820353/pexels-photo-16820353.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37219747/pexels-photo-37219747.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|floor_mats_dhurries|Floor Mats & Dhurries|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/2.webp|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/1.webp|https://images.pexels.com/photos/7417505/pexels-photo-7417505.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35769473/pexels-photo-35769473.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|door_mats|Door Mats|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/2.webp|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/1.webp|https://images.pexels.com/photos/5840868/pexels-photo-5840868.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2950003/pexels-photo-2950003.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bath_towels|Bath Towels|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/16719378/2024/7/2/97418bad-8216-494f-a863-9b159aec93cf1719897710069JockeyPackof2CottonTerry500GSMUltrasoftandDurableSolidHandTo1.jpg|https://images.pexels.com/photos/12679/pexels-photo-12679.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20523089/pexels-photo-20523089.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8535619/pexels-photo-8535619.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|hand_face_towels|Hand & Face Towels|https://images.pexels.com/photos/7691101/pexels-photo-7691101.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210373/pexels-photo-4210373.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5833316/pexels-photo-5833316.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5732631/pexels-photo-5732631.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|beach_towels|Beach Towels|https://images.pexels.com/photos/271711/pexels-photo-271711.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1304110/pexels-photo-1304110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18674213/pexels-photo-18674213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25649794/pexels-photo-25649794.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|towels_set|Towels Set|https://images.pexels.com/photos/282892/pexels-photo-282892.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210371/pexels-photo-4210371.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6188050/pexels-photo-6188050.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20665616/pexels-photo-20665616.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bath_rugs|Bath Rugs|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/2.webp|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/1.webp|https://images.pexels.com/photos/34667257/pexels-photo-34667257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18894029/pexels-photo-18894029.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bath_robes|Bath Robes|https://images.pexels.com/photos/10924511/pexels-photo-10924511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/433624/pexels-photo-433624.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6202201/pexels-photo-6202201.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6634836/pexels-photo-6634836.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bathroom_accessories|Bathroom Accessories|https://images.pexels.com/photos/10574059/pexels-photo-10574059.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3761560/pexels-photo-3761560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4194705/pexels-photo-4194705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980252/pexels-photo-19980252.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|shower_curtains|Shower Curtains|https://images.pexels.com/photos/11430604/pexels-photo-11430604.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5137571/pexels-photo-5137571.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5825561/pexels-photo-5825561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6430731/pexels-photo-6430731.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|floor_lamps|Floor Lamps|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/productimage/2021/6/10/5be80654-037c-4591-99c9-a403646971981623344562713-1.jpg|https://images.pexels.com/photos/112811/pexels-photo-112811.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6045108/pexels-photo-6045108.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11850174/pexels-photo-11850174.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|ceiling_lamps|Ceiling Lamps|https://images.pexels.com/photos/7533932/pexels-photo-7533932.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17940121/pexels-photo-17940121.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9628657/pexels-photo-9628657.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12202390/pexels-photo-12202390.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|table_lamps|Table Lamps|https://images.pexels.com/photos/16683246/pexels-photo-16683246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/573298/pexels-photo-573298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35392792/pexels-photo-35392792.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6053887/pexels-photo-6053887.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|wall_lamps|Wall Lamps|https://images.pexels.com/photos/721039/pexels-photo-721039.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2187141/pexels-photo-2187141.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9510486/pexels-photo-9510486.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29343148/pexels-photo-29343148.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|outdoor_lamps|Outdoor Lamps|https://images.pexels.com/photos/1683405/pexels-photo-1683405.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1554740/pexels-photo-1554740.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18074245/pexels-photo-18074245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37460216/pexels-photo-37460216.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|string_lights|String Lights|https://images.pexels.com/photos/3368822/pexels-photo-3368822.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1791747/pexels-photo-1791747.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5836535/pexels-photo-5836535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15433418/pexels-photo-15433418.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|plants_planters|Plants & Planters|https://images.pexels.com/photos/8549167/pexels-photo-8549167.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3952031/pexels-photo-3952031.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7245621/pexels-photo-7245621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31479159/pexels-photo-31479159.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|aromas_candles|Aromas & Candles|https://images.pexels.com/photos/5816995/pexels-photo-5816995.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18408479/pexels-photo-18408479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31588837/pexels-photo-31588837.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35047380/pexels-photo-35047380.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|clocks|Clocks|https://images.pexels.com/photos/6616845/pexels-photo-6616845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2182727/pexels-photo-2182727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38699699/pexels-photo-38699699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/707676/pexels-photo-707676.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|mirrors|Mirrors|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/28460938/2024/3/22/7fb09e9c-86e0-4602-b54e-fa5c0171b50b1711104156746IrregularMirrorHomeDecor1.jpg|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/1.webp|https://images.pexels.com/photos/7319683/pexels-photo-7319683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28533705/pexels-photo-28533705.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|wall_decor|Wall Décor|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/2.webp|https://cdn.dummyjson.com/product-images/home-decoration/family-tree-photo-frame/1.webp|https://images.pexels.com/photos/6925113/pexels-photo-6925113.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6580004/pexels-photo-6580004.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|festive_decor|Festive Decor|https://images.pexels.com/photos/4078516/pexels-photo-4078516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34387790/pexels-photo-34387790.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443947/pexels-photo-6443947.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34473414/pexels-photo-34473414.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|pooja_essentials|Pooja Essentials|https://images.pexels.com/photos/6213685/pexels-photo-6213685.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31372002/pexels-photo-31372002.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10182772/pexels-photo-10182772.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37650548/pexels-photo-37650548.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|wall_shelves|Wall Shelves|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/2.webp|https://images.pexels.com/photos/6851831/pexels-photo-6851831.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19857232/pexels-photo-19857232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25906569/pexels-photo-25906569.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|fountains|Fountains|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/2.webp|https://images.pexels.com/photos/11651964/pexels-photo-11651964.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17087908/pexels-photo-17087908.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|showpieces_vases|Showpieces & Vases|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/2.webp|https://images.pexels.com/photos/40167789/pexels-photo-40167789.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35211364/pexels-photo-35211364.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|ottoman|Ottoman|https://images.pexels.com/photos/32123565/pexels-photo-32123565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12289357/pexels-photo-12289357.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17771969/pexels-photo-17771969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19580060/pexels-photo-19580060.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|cushions_cushion_covers|Cushions & Cushion Covers|https://images.pexels.com/photos/30163102/pexels-photo-30163102.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36482950/pexels-photo-36482950.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7218188/pexels-photo-7218188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18258470/pexels-photo-18258470.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|curtains|Curtains|https://images.pexels.com/photos/5835536/pexels-photo-5835536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35165092/pexels-photo-35165092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23110026/pexels-photo-23110026.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12944779/pexels-photo-12944779.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|home_gift_sets|Home Gift Sets|https://cdn.dummyjson.com/product-images/home-decoration/table-lamp/1.webp|https://images.pexels.com/photos/7674564/pexels-photo-7674564.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1666070/pexels-photo-1666070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3873488/pexels-photo-3873488.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_runners|Table Runners|https://cdn.dummyjson.com/product-images/kitchen-accessories/bamboo-spatula/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/1.webp|https://images.pexels.com/photos/34232560/pexels-photo-34232560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8108068/pexels-photo-8108068.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|dinnerware_serveware|Dinnerware & Serveware|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/2.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-whisk/1.webp|https://images.pexels.com/photos/3847465/pexels-photo-3847465.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8903360/pexels-photo-8903360.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|cups_mugs|Cups And Mugs|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/2.webp|https://images.pexels.com/photos/9261414/pexels-photo-9261414.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7192236/pexels-photo-7192236.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bakeware_cookware|Bakeware & Cookware|https://cdn.dummyjson.com/product-images/kitchen-accessories/carbon-steel-wok/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/chopping-board/1.webp|https://images.pexels.com/photos/12586814/pexels-photo-12586814.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29462835/pexels-photo-29462835.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|kitchen_storage_tools|Kitchen Storage & Tools|https://cdn.dummyjson.com/product-images/kitchen-accessories/citrus-squeezer-yellow/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/egg-slicer/1.webp|https://images.pexels.com/photos/10568352/pexels-photo-10568352.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8175333/pexels-photo-8175333.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bar_drinkware|Bar & Drinkware|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/2.webp|https://images.pexels.com/photos/7282898/pexels-photo-7282898.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19393192/pexels-photo-19393192.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_covers_furnishings|Table Covers & Furnishings|https://cdn.dummyjson.com/product-images/kitchen-accessories/fine-mesh-strainer/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/fork/1.webp|https://images.pexels.com/photos/8158010/pexels-photo-8158010.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9512746/pexels-photo-9512746.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|television|Television|television|Television|https://rukminim2.flixcart.com/image/312/312/xif0q/television/9/p/9/-original-imah2v29z86u7b79.jpeg?q=70|https://images.pexels.com/photos/5202925/pexels-photo-5202925.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13806260/pexels-photo-13806260.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6020432/pexels-photo-6020432.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|cameras|Cameras|cameras|Cameras|https://images.pexels.com/photos/15111004/pexels-photo-15111004.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12548528/pexels-photo-12548528.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19358981/pexels-photo-19358981.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22064439/pexels-photo-22064439.jpeg?auto=compress&cs=tinysrgb&w=800"
//    };
//
//    // categoryId | title | description | mrp | sellingPrice | colour | sizes | stock | ratings | daysOld
//    private static final String[] PRODUCTS = {
//            // ---- men_t_shirts
//            "men_t_shirts|Roadster Cotton Crew Neck T-Shirt|100% combed cotton, regular fit, ribbed round neck. Machine wash cold. Easy 7-day returns.|2199|1399|Navy Blue|S,M,L,XL,XXL|79|763|42",
//            "men_t_shirts|H&M Oversized Graphic Print T-Shirt|Heavy 240 GSM cotton, drop shoulders, bold back print. Machine wash cold. Easy 7-day returns.|3599|1299|Beige|S,M,L,XL,XXL|112|3854|113",
//            "men_t_shirts|Levi's Slim Fit Polo T-Shirt|Pique cotton polo with two-button placket and ribbed collar. Machine wash cold. Easy 7-day returns.|1799|969|Charcoal|S,M,L,XL,XXL|131|2943|99",
//            "men_t_shirts|U.S. Polo Assn. Henley Neck Full Sleeve T-Shirt|Soft slub cotton, three-button henley placket. Machine wash cold. Easy 7-day returns.|2299|1199|Teal|S,M,L,XL,XXL|21|4652|67",
//            "men_t_shirts|Puma Striped Round Neck T-Shirt|Breton stripes in breathable cotton jersey. Machine wash cold. Easy 7-day returns.|2999|1499|Black|S,M,L,XL,XXL|244|3019|11",
//            "men_t_shirts|HRX Solid V-Neck T-Shirt|Stretch cotton-lycra blend for a clean, close fit. Machine wash cold. Easy 7-day returns.|2299|1399|Olive|S,M,L,XL,XXL|40|3491|62",
//            "men_t_shirts|Bewakoof Acid Wash Relaxed T-Shirt|Vintage acid-wash finish, relaxed boxy cut. Machine wash cold. Easy 7-day returns.|1799|859|Khaki|S,M,L,XL,XXL|76|184|41",
//            "men_t_shirts|Jack & Jones Pocket Detail Cotton T-Shirt|Chest patch pocket, pre-shrunk cotton. Machine wash cold. Easy 7-day returns.|2299|1099|Navy Blue|S,M,L,XL,XXL|40|3677|34",
//            // ---- men_casual_shirts
//            "men_casual_shirts|Roadster Checked Flannel Casual Shirt|Brushed cotton flannel, button-down collar. Machine wash cold. Easy 7-day returns.|2999|1599|Charcoal|S,M,L,XL,XXL|84|2015|77",
//            "men_casual_shirts|Levi's Linen Blend Mandarin Collar Shirt|Breathable linen-cotton, mandarin collar, curved hem. Machine wash cold. Easy 7-day returns.|2299|869|Charcoal|S,M,L,XL,XXL|15|2710|5",
//            "men_casual_shirts|U.S. Polo Assn. Denim Slim Fit Shirt|Light-wash denim with twin flap pockets. Machine wash cold. Easy 7-day returns.|2699|1899|Beige|S,M,L,XL,XXL|46|3277|33",
//            "men_casual_shirts|Highlander Printed Resort Collar Shirt|Tropical print on soft viscose, short sleeves. Machine wash cold. Easy 7-day returns.|2399|1799|Maroon|S,M,L,XL,XXL|170|4038|20",
//            "men_casual_shirts|Peter England Oxford Button-Down Shirt|Classic cotton oxford weave, regular fit. Machine wash cold. Easy 7-day returns.|1299|889|Charcoal|S,M,L,XL,XXL|58|1518|103",
//            "men_casual_shirts|Jack & Jones Corduroy Overshirt|Fine-wale corduroy, chest pockets, layer-ready. Machine wash cold. Easy 7-day returns.|1599|1199|Olive|S,M,L,XL,XXL|163|4152|76",
//            "men_casual_shirts|Mufti Striped Cuban Collar Shirt|Vertical stripes, camp collar, relaxed fit. Machine wash cold. Easy 7-day returns.|3099|1699|Navy Blue|S,M,L,XL,XXL|157|2671|10",
//            // ---- men_formal_shirts
//            "men_formal_shirts|Van Heusen Slim Fit Solid Formal Shirt|Wrinkle-free cotton, spread collar, single cuff. Machine wash cold. Easy 7-day returns.|3499|1499|Mustard|S,M,L,XL,XXL|226|2952|63",
//            "men_formal_shirts|Louis Philippe Regular Fit Pinstripe Shirt|Fine pinstripes on giza-style cotton poplin. Machine wash cold. Easy 7-day returns.|1599|959|White|S,M,L,XL,XXL|169|4266|84",
//            "men_formal_shirts|Peter England Self-Design Dobby Formal Shirt|Textured dobby weave, cutaway collar. Machine wash cold. Easy 7-day returns.|4599|2299|Teal|S,M,L,XL,XXL|197|4282|119",
//            "men_formal_shirts|Allen Solly Pure Cotton Checked Office Shirt|Micro checks, easy iron finish. Machine wash cold. Easy 7-day returns.|3299|2299|Beige|S,M,L,XL,XXL|195|2312|38",
//            "men_formal_shirts|Arrow Satin Finish Party Shirt|Subtle sheen satin cotton for evening events. Machine wash cold. Easy 7-day returns.|4999|2899|Charcoal|S,M,L,XL,XXL|72|2023|89",
//            "men_formal_shirts|Raymond Double Cuff French Shirt|Twill weave with double cuffs for cufflinks. Machine wash cold. Easy 7-day returns.|5399|2699|Maroon|S,M,L,XL,XXL|233|4713|51",
//            // ---- men_sweatshirts
//            "men_sweatshirts|Puma Fleece Crew Neck Sweatshirt|Brushed fleece inside, ribbed cuffs and hem. Machine wash cold. Easy 7-day returns.|2099|1299|Beige|S,M,L,XL,XXL|192|1879|50",
//            "men_sweatshirts|HRX Hooded Zip-Up Sweatshirt|Full zip with kangaroo pockets and drawstring hood. Machine wash cold. Easy 7-day returns.|1399|1099|Navy Blue|S,M,L,XL,XXL|129|2572|67",
//            "men_sweatshirts|Roadster Oversized Graphic Hoodie|Dropped shoulders, heavyweight 320 GSM fleece. Machine wash cold. Easy 7-day returns.|4599|2399|Black|S,M,L,XL,XXL|94|121|41",
//            "men_sweatshirts|Nike Colourblock Pullover Sweatshirt|Bold colourblock panels, regular fit. Machine wash cold. Easy 7-day returns.|3899|1499|Khaki|S,M,L,XL,XXL|124|3152|87",
//            "men_sweatshirts|Adidas Varsity Print Sweatshirt|Collegiate print, cotton-rich fleece. Machine wash cold. Easy 7-day returns.|5599|1999|Beige|S,M,L,XL,XXL|119|3981|67",
//            "men_sweatshirts|The Souled Store Half-Zip Mock Neck Sweatshirt|Quarter zip, stand collar, soft terry. Machine wash cold. Easy 7-day returns.|3299|1199|Navy Blue|S,M,L,XL,XXL|91|1632|90",
//            // ---- men_sweaters
//            "men_sweaters|Allen Solly Cable Knit Pullover Sweater|Chunky cable knit acrylic-wool blend. Machine wash cold. Easy 7-day returns.|4599|2799|Teal|S,M,L,XL,XXL|157|2685|111",
//            "men_sweaters|Monte Carlo V-Neck Merino Wool Sweater|Fine merino wool, lightweight and warm. Machine wash cold. Easy 7-day returns.|5299|1999|Maroon|S,M,L,XL,XXL|170|4154|5",
//            "men_sweaters|United Colors of Benetton Sleeveless Argyle Sweater Vest|Classic argyle pattern, layer over shirts. Machine wash cold. Easy 7-day returns.|3999|2399|White|S,M,L,XL,XXL|15|4169|115",
//            "men_sweaters|Roadster Turtleneck Ribbed Sweater|Rib-knit turtleneck, slim fit. Machine wash cold. Easy 7-day returns.|4399|2699|White|S,M,L,XL,XXL|212|2939|65",
//            "men_sweaters|Louis Philippe Zip Cardigan Sweater|Front zip cardigan with pockets. Machine wash cold. Easy 7-day returns.|6499|2499|Khaki|S,M,L,XL,XXL|138|3171|119",
//            "men_sweaters|Van Heusen Fair Isle Crew Neck Sweater|Festive fair isle pattern, soft knit. Machine wash cold. Easy 7-day returns.|4899|2699|Black|S,M,L,XL,XXL|146|2344|89",
//            // ---- men_jackets
//            "men_jackets|Roadster Puffer Quilted Jacket|Lightweight quilted puffer with water-repellent shell. Machine wash cold. Easy 7-day returns.|5899|3699|Khaki|S,M,L,XL,XXL|166|3969|100",
//            "men_jackets|HRX Denim Trucker Jacket|Classic trucker silhouette in rigid denim. Machine wash cold. Easy 7-day returns.|6799|3799|Charcoal|S,M,L,XL,XXL|28|3319|48",
//            "men_jackets|Wildcraft Faux Leather Biker Jacket|Asymmetric zip, quilted shoulder panels. Machine wash cold. Easy 7-day returns.|7799|4799|Green|S,M,L,XL,XXL|19|3971|65",
//            "men_jackets|Levi's Bomber Jacket|Satin-finish bomber with ribbed trims. Machine wash cold. Easy 7-day returns.|5699|3399|Khaki|S,M,L,XL,XXL|243|4240|64",
//            "men_jackets|Jack & Jones Windcheater Hooded Jacket|Packable windcheater with mesh lining. Machine wash cold. Easy 7-day returns.|3899|2699|Navy Blue|S,M,L,XL,XXL|68|1382|49",
//            "men_jackets|Puma Suede Finish Trucker Jacket|Soft suede-feel fabric, button front. Machine wash cold. Easy 7-day returns.|4199|2499|Charcoal|S,M,L,XL,XXL|219|2085|51",
//            // ---- men_blazers_and_coats
//            "men_blazers_and_coats|Raymond Slim Fit Single-Breasted Blazer|Two-button blazer in poly-viscose suiting. Machine wash cold. Easy 7-day returns.|11599|7399|Mustard|S,M,L,XL,XXL|141|3587|33",
//            "men_blazers_and_coats|Blackberrys Textured Linen Blend Blazer|Unstructured linen blazer for summer events. Machine wash cold. Easy 7-day returns.|6399|4999|White|S,M,L,XL,XXL|193|3516|101",
//            "men_blazers_and_coats|Louis Philippe Double-Breasted Wool Overcoat|Wool blend overcoat, notch lapel. Machine wash cold. Easy 7-day returns.|9799|4299|Beige|S,M,L,XL,XXL|56|567|21",
//            "men_blazers_and_coats|Van Heusen Checked Casual Blazer|Prince of Wales check, patch pockets. Machine wash cold. Easy 7-day returns.|5699|4299|Green|S,M,L,XL,XXL|171|3984|63",
//            "men_blazers_and_coats|Arrow Velvet Party Blazer|Rich velvet with satin peak lapel. Machine wash cold. Easy 7-day returns.|11799|5299|Black|S,M,L,XL,XXL|143|1355|111",
//            "men_blazers_and_coats|Park Avenue Trench Coat|Belted trench with storm flap, water resistant. Machine wash cold. Easy 7-day returns.|9899|5499|Navy Blue|S,M,L,XL,XXL|189|1587|1",
//            // ---- men_suits
//            "men_suits|Raymond Two-Piece Slim Fit Suit|Blazer and trouser set in premium suiting fabric. Machine wash cold. Easy 7-day returns.|24499|16899|Olive|S,M,L,XL,XXL|197|2114|13",
//            "men_suits|Blackberrys Three-Piece Wedding Suit|Blazer, waistcoat and trouser, notch lapel. Machine wash cold. Easy 7-day returns.|14999|8099|Mustard|S,M,L,XL,XXL|18|27|81",
//            "men_suits|Louis Philippe Pinstripe Business Suit|Navy pinstripe, half-canvas construction. Machine wash cold. Easy 7-day returns.|15899|11699|White|S,M,L,XL,XXL|99|1816|59",
//            "men_suits|Van Heusen Tuxedo Suit with Satin Lapel|Classic black tuxedo with satin trims. Machine wash cold. Easy 7-day returns.|41699|19999|Mustard|S,M,L,XL,XXL|203|3186|50",
//            "men_suits|Park Avenue Checked Three-Piece Suit|Windowpane check with matching waistcoat. Machine wash cold. Easy 7-day returns.|25899|16799|White|S,M,L,XL,XXL|77|2871|107",
//            "men_suits|Manyavar Linen Blend Summer Suit|Breathable linen blend, unlined jacket. Machine wash cold. Easy 7-day returns.|18199|12499|Black|S,M,L,XL,XXL|51|2664|37",
//            // ---- men_rain_jackets
//            "men_rain_jackets|Wildcraft Waterproof Hooded Rain Jacket|Taped seams, 10K waterproof rating. Machine wash cold. Easy 7-day returns.|2399|1699|White|S,M,L,XL,XXL|137|3748|61",
//            "men_rain_jackets|Decathlon Quechua Packable Rain Poncho Jacket|Folds into its own pocket, lightweight. Machine wash cold. Easy 7-day returns.|2299|1399|Beige|S,M,L,XL,XXL|236|513|9",
//            "men_rain_jackets|Columbia Breathable Trekking Rain Shell|Pit zips for ventilation, adjustable hood. Machine wash cold. Easy 7-day returns.|4099|2999|Beige|S,M,L,XL,XXL|162|451|35",
//            "men_rain_jackets|Puma Reflective Biker Rain Suit|Jacket with pants, reflective piping. Machine wash cold. Easy 7-day returns.|3799|2299|Charcoal|S,M,L,XL,XXL|106|1993|27",
//            "men_rain_jackets|Duckback Longline Rain Coat|Knee-length coverage, storm flap. Machine wash cold. Easy 7-day returns.|1899|1399|Green|S,M,L,XL,XXL|134|234|29",
//            "men_rain_jackets|Zeel Ultralight Running Rain Jacket|Featherweight nylon with DWR coating. Machine wash cold. Easy 7-day returns.|7199|3599|Olive|S,M,L,XL,XXL|237|3677|94",
//            // ---- men_indian_and_festive_wear
//            "men_indian_and_festive_wear|Manyavar Silk Blend Kurta Pyjama Set|Festive silk-blend kurta with churidar pyjama. Dry clean recommended. Perfect for festive occasions.|13699|6299|Mustard|S,M,L,XL,XXL|180|4350|15",
//            "men_indian_and_festive_wear|FabIndia Embroidered Bandhgala Set|Thread embroidery, mandarin collar, trousers. Dry clean recommended. Perfect for festive occasions.|3399|2299|Navy Blue|S,M,L,XL,XXL|27|442|4",
//            "men_indian_and_festive_wear|Sojanya Printed Nehru Jacket Kurta Set|Three-piece set with printed jacket. Dry clean recommended. Perfect for festive occasions.|4899|3899|Navy Blue|S,M,L,XL,XXL|24|2960|41",
//            "men_indian_and_festive_wear|Vastramay Chikankari Kurta Set|Hand-feel chikankari on cotton. Dry clean recommended. Perfect for festive occasions.|5099|2899|Teal|S,M,L,XL,XXL|20|2278|51",
//            "men_indian_and_festive_wear|Jompers Jacquard Indo-Western Set|Asymmetric kurta with dhoti pants. Dry clean recommended. Perfect for festive occasions.|4699|2099|Rust|S,M,L,XL,XXL|22|4185|2",
//            "men_indian_and_festive_wear|Tasva Festive Angrakha Kurta Set|Angrakha wrap style with tie-ups. Dry clean recommended. Perfect for festive occasions.|5199|2999|Green|S,M,L,XL,XXL|17|80|98",
//            // ---- men_kurtas_and_kurta_sets
//            "men_kurtas_and_kurta_sets|FabIndia Straight Cotton Kurta|Pure cotton, mandarin collar, side slits. Dry clean recommended. Perfect for festive occasions.|7899|3299|Cream|S,M,L,XL,XXL|58|2245|62",
//            "men_kurtas_and_kurta_sets|Manyavar Printed Short Kurta|Block print short kurta for casual wear. Dry clean recommended. Perfect for festive occasions.|1599|1099|Green|S,M,L,XL,XXL|70|2933|84",
//            "men_kurtas_and_kurta_sets|Vastramay Linen Pathani Kurta Set|Pathani style with salwar, linen blend. Dry clean recommended. Perfect for festive occasions.|4299|2499|Mustard|S,M,L,XL,XXL|197|1692|108",
//            "men_kurtas_and_kurta_sets|Jompers Silk Blend Festive Kurta|Self-design jacquard for festive occasions. Dry clean recommended. Perfect for festive occasions.|1999|1199|Teal|S,M,L,XL,XXL|43|1015|112",
//            "men_kurtas_and_kurta_sets|Anouk Chanderi Kurta Pyjama Set|Lightweight chanderi with pyjama. Dry clean recommended. Perfect for festive occasions.|1599|1199|Rust|S,M,L,XL,XXL|59|4382|34",
//            "men_kurtas_and_kurta_sets|See Designs Khadi Long Kurta|Handwoven khadi, relaxed fit. Dry clean recommended. Perfect for festive occasions.|4199|1499|Gold|S,M,L,XL,XXL|79|1027|77",
//            // ---- men_sherwanis
//            "men_sherwanis|Manyavar Embroidered Wedding Sherwani Set|Zari embroidery with churidar and stole. Dry clean recommended. Perfect for festive occasions.|41799|23799|Gold|S,M,L,XL,XXL|168|4491|71",
//            "men_sherwanis|Tasva Velvet Royal Sherwani|Rich velvet with antique buttons. Dry clean recommended. Perfect for festive occasions.|26099|12499|Maroon|S,M,L,XL,XXL|103|3632|86",
//            "men_sherwanis|Sojanya Brocade Achkan Sherwani|Brocade weave, knee-length achkan. Dry clean recommended. Perfect for festive occasions.|23499|18299|Magenta|S,M,L,XL,XXL|27|4707|112",
//            "men_sherwanis|Ethnix by Raymond Indo-Western Sherwani with Dhoti|Modern cut with draped dhoti. Dry clean recommended. Perfect for festive occasions.|27599|16799|Green|S,M,L,XL,XXL|221|924|45",
//            "men_sherwanis|Vastramay Pastel Thread Work Sherwani|Pastel tone with tonal thread work. Dry clean recommended. Perfect for festive occasions.|27299|19099|Maroon|S,M,L,XL,XXL|47|1306|45",
//            "men_sherwanis|Jompers Raw Silk Sherwani with Safa|Comes with matching turban. Dry clean recommended. Perfect for festive occasions.|26399|11599|Magenta|S,M,L,XL,XXL|142|1807|94",
//            // ---- men_nehru_jackets
//            "men_nehru_jackets|Manyavar Jacquard Nehru Jacket|Woven jacquard, mandarin collar. Dry clean recommended. Perfect for festive occasions.|3499|2699|Navy Blue|S,M,L,XL,XXL|197|962|94",
//            "men_nehru_jackets|FabIndia Linen Blend Nehru Jacket|Breathable linen, welt pockets. Dry clean recommended. Perfect for festive occasions.|5699|2699|Maroon|S,M,L,XL,XXL|243|1200|40",
//            "men_nehru_jackets|Vastramay Printed Festive Nehru Jacket|Paisley print, satin lining. Dry clean recommended. Perfect for festive occasions.|9199|3399|Mustard|S,M,L,XL,XXL|112|3671|46",
//            "men_nehru_jackets|Jompers Velvet Nehru Jacket|Plush velvet for winter weddings. Dry clean recommended. Perfect for festive occasions.|2099|1599|Maroon|S,M,L,XL,XXL|228|2885|52",
//            "men_nehru_jackets|Tasva Khadi Bandi Jacket|Handspun khadi, relaxed fit. Dry clean recommended. Perfect for festive occasions.|2099|1399|Gold|S,M,L,XL,XXL|42|2938|100",
//            "men_nehru_jackets|Peter England Embroidered Silk Nehru Jacket|Tonal embroidery on silk blend. Dry clean recommended. Perfect for festive occasions.|1999|1399|Magenta|S,M,L,XL,XXL|83|2626|3",
//            // ---- men_dhotis
//            "men_dhotis|Ramraj Cotton Pure Cotton Veshti with Border|Traditional white veshti, gold jari border. Dry clean recommended. Perfect for festive occasions.|3799|1899|Cream|S,M,L,XL,XXL|27|1270|68",
//            "men_dhotis|Manyavar Ready-to-Wear Dhoti Pants|Elastic waist, pre-stitched pleats. Dry clean recommended. Perfect for festive occasions.|1299|939|Cream|S,M,L,XL,XXL|99|2507|46",
//            "men_dhotis|FabIndia Silk Blend Festive Dhoti|Silk blend with contrast border. Dry clean recommended. Perfect for festive occasions.|999|649|Maroon|S,M,L,XL,XXL|142|148|111",
//            "men_dhotis|Vastramay Patiala Style Dhoti Salwar|Draped patiala dhoti. Dry clean recommended. Perfect for festive occasions.|1299|729|Magenta|S,M,L,XL,XXL|70|2274|46",
//            "men_dhotis|Jompers Art Silk Cowl Dhoti|Cowl drape for Indo-western looks. Dry clean recommended. Perfect for festive occasions.|1899|1199|Magenta|S,M,L,XL,XXL|124|2771|79",
//            "men_dhotis|Sojanya Double Dhoti with Angavastram|Set with matching angavastram. Dry clean recommended. Perfect for festive occasions.|1099|799|Teal|S,M,L,XL,XXL|145|1067|17",
//            // ---- men_jeans
//            "men_jeans|Levi's 511 Slim Fit Stretch Jeans|Slim through hip and thigh, stretch denim. Machine wash. Easy 7-day returns.|7399|3399|Maroon|28,30,32,34,36|62|2881|114",
//            "men_jeans|Wrangler Straight Fit Mid-Rise Jeans|Classic straight leg, mid rise. Machine wash. Easy 7-day returns.|3699|2799|White|28,30,32,34,36|249|493|36",
//            "men_jeans|Pepe Jeans Skinny Fit Distressed Jeans|Ripped knees, whiskered wash. Machine wash. Easy 7-day returns.|5399|2799|Grey Melange|28,30,32,34,36|68|77|69",
//            "men_jeans|Roadster Tapered Fit Dark Wash Jeans|Clean dark indigo, tapered leg. Machine wash. Easy 7-day returns.|3499|2199|Black|28,30,32,34,36|182|4023|35",
//            "men_jeans|Lee Relaxed Fit Carpenter Jeans|Utility loops, relaxed cut. Machine wash. Easy 7-day returns.|3699|2799|Black|28,30,32,34,36|77|1666|120",
//            "men_jeans|Spykar Bootcut Mid-Rise Jeans|Slight flare at hem, vintage wash. Machine wash. Easy 7-day returns.|4799|2999|Olive|28,30,32,34,36|82|4079|42",
//            // ---- men_casual_trousers
//            "men_casual_trousers|Highlander Slim Fit Cotton Chinos|Stretch cotton twill chinos. Machine wash. Easy 7-day returns.|2299|1699|Olive|28,30,32,34,36|238|2573|22",
//            "men_casual_trousers|U.S. Polo Assn. Cargo Trousers with Flap Pockets|Six-pocket utility cargo. Machine wash. Easy 7-day returns.|3999|2999|White|28,30,32,34,36|182|2337|6",
//            "men_casual_trousers|Roadster Pleated Relaxed Trousers|Single pleat, relaxed taper. Machine wash. Easy 7-day returns.|3299|1999|Beige|28,30,32,34,36|91|2629|83",
//            "men_casual_trousers|Jack & Jones Linen Blend Drawstring Trousers|Breathable summer trousers. Machine wash. Easy 7-day returns.|3799|1899|Black|28,30,32,34,36|124|4066|24",
//            "men_casual_trousers|Mufti Corduroy Straight Trousers|Fine-wale corduroy, straight fit. Machine wash. Easy 7-day returns.|2099|1499|Black|28,30,32,34,36|137|4470|92",
//            "men_casual_trousers|Peter England Jogger Fit Casual Trousers|Elastic cuffs, smart-casual. Machine wash. Easy 7-day returns.|2699|1899|Green|28,30,32,34,36|146|3957|42",
//            // ---- men_formal_trousers
//            "men_formal_trousers|Van Heusen Slim Fit Flat-Front Trousers|Wrinkle-resistant poly-viscose. Machine wash. Easy 7-day returns.|1899|1399|Khaki|28,30,32,34,36|68|671|37",
//            "men_formal_trousers|Louis Philippe Regular Fit Pleated Trousers|Double pleat, comfort waist. Machine wash. Easy 7-day returns.|1699|1199|Beige|28,30,32,34,36|83|292|23",
//            "men_formal_trousers|Raymond Checked Formal Trousers|Subtle checks, tapered. Machine wash. Easy 7-day returns.|6299|2199|Green|28,30,32,34,36|182|2915|3",
//            "men_formal_trousers|Allen Solly Wool Blend Office Trousers|Premium wool blend with crease. Machine wash. Easy 7-day returns.|6899|3299|Maroon|28,30,32,34,36|68|98|107",
//            "men_formal_trousers|Arrow Stretch Tailored Trousers|Four-way stretch for all-day comfort. Machine wash. Easy 7-day returns.|4699|1999|Green|28,30,32,34,36|218|2724|4",
//            "men_formal_trousers|Park Avenue Herringbone Formal Trousers|Herringbone weave, slim fit. Machine wash. Easy 7-day returns.|2499|1799|Black|28,30,32,34,36|149|1338|14",
//            // ---- men_shorts
//            "men_shorts|Puma Cotton Chino Shorts|Mid-thigh length, slant pockets. Machine wash. Easy 7-day returns.|1499|999|Grey Melange|28,30,32,34,36|40|1901|97",
//            "men_shorts|Nike Denim Shorts|Rolled hem, light wash. Machine wash. Easy 7-day returns.|2699|1199|Charcoal|28,30,32,34,36|224|504|8",
//            "men_shorts|Roadster Cargo Shorts|Side flap pockets, relaxed. Machine wash. Easy 7-day returns.|2399|1799|Maroon|28,30,32,34,36|109|2459|4",
//            "men_shorts|H&M Dri-Fit Running Shorts|Moisture-wicking with inner brief. Machine wash. Easy 7-day returns.|2599|1499|Mustard|28,30,32,34,36|235|1271|7",
//            "men_shorts|Jack & Jones Printed Swim-Style Shorts|Quick-dry, drawstring waist. Machine wash. Easy 7-day returns.|1199|899|White|28,30,32,34,36|147|2386|69",
//            "men_shorts|Levi's Knit Lounge Shorts|Soft french terry. Machine wash. Easy 7-day returns.|849|669|Green|28,30,32,34,36|163|3438|111",
//            // ---- men_track_pants_and_joggers
//            "men_track_pants_and_joggers|Puma Slim Fit Joggers|Tapered joggers with zip pockets. Machine wash. Easy 7-day returns.|3399|2199|Maroon|28,30,32,34,36|123|1620|1",
//            "men_track_pants_and_joggers|Adidas Three-Stripe Track Pants|Classic side stripes, tricot fabric. Machine wash. Easy 7-day returns.|4099|2499|Grey Melange|28,30,32,34,36|236|185|29",
//            "men_track_pants_and_joggers|Nike Fleece Cuffed Joggers|Brushed fleece for winter. Machine wash. Easy 7-day returns.|4399|1799|Maroon|28,30,32,34,36|222|1779|9",
//            "men_track_pants_and_joggers|HRX Dri-Fit Training Track Pants|Moisture-wicking, stretch. Machine wash. Easy 7-day returns.|3999|1799|Navy Blue|28,30,32,34,36|131|3414|68",
//            "men_track_pants_and_joggers|Reebok Cargo Joggers|Utility pockets, elastic hems. Machine wash. Easy 7-day returns.|4299|1799|Black|28,30,32,34,36|73|2710|70",
//            "men_track_pants_and_joggers|Decathlon Kalenji Woven Windbreaker Track Pants|Lightweight woven shell. Machine wash. Easy 7-day returns.|1899|1299|Green|28,30,32,34,36|129|2018|75",
//            // ---- men_briefs_and_trunks
//            "men_briefs_and_trunks|Jockey Pack of 3 Cotton Briefs|Combed cotton with soft waistband. Soft on skin. Machine wash.|1499|589|Navy Blue|S,M,L,XL,XXL|215|212|28",
//            "men_briefs_and_trunks|Calvin Klein Micro Modal Trunks|Ultra-soft modal, no-ride legs. Soft on skin. Machine wash.|1299|509|Grey Melange|S,M,L,XL,XXL|137|2659|34",
//            "men_briefs_and_trunks|XYXX Pack of 2 Stretch Trunks|Cotton-elastane, contour pouch. Soft on skin. Machine wash.|819|569|White|S,M,L,XL,XXL|15|941|115",
//            "men_briefs_and_trunks|Van Heusen Anti-Bacterial Briefs|Odour control finish. Soft on skin. Machine wash.|899|339|Black|S,M,L,XL,XXL|190|1038|25",
//            "men_briefs_and_trunks|DaMENSCH Long Leg Trunks|Longer leg, anti-chafe. Soft on skin. Machine wash.|1399|989|Navy Blue|S,M,L,XL,XXL|84|2017|76",
//            "men_briefs_and_trunks|Puma Seamless Trunks|Laser cut, invisible under clothes. Soft on skin. Machine wash.|999|449|Green|S,M,L,XL,XXL|106|2006|43",
//            // ---- men_boxers
//            "men_boxers|Jockey Printed Woven Boxers|Breathable cotton, button fly. Soft on skin. Machine wash.|599|319|Charcoal|S,M,L,XL,XXL|97|1631|66",
//            "men_boxers|XYXX Pack of 2 Checked Boxers|Classic checks, inner waistband. Soft on skin. Machine wash.|1299|989|Olive|S,M,L,XL,XXL|96|1628|25",
//            "men_boxers|U.S. Polo Assn. Knit Boxer Shorts|Stretch knit, side pockets. Soft on skin. Machine wash.|1799|669|Maroon|S,M,L,XL,XXL|230|1979|101",
//            "men_boxers|Van Heusen Super Combed Cotton Boxers|Soft and durable. Soft on skin. Machine wash.|1299|679|Teal|S,M,L,XL,XXL|207|3036|29",
//            "men_boxers|Bewakoof Pack of 3 Solid Boxers|Everyday essentials. Soft on skin. Machine wash.|589|439|White|S,M,L,XL,XXL|80|295|44",
//            "men_boxers|DaMENSCH Quick-Dry Boxers|Moisture-wicking fabric. Soft on skin. Machine wash.|589|329|Navy Blue|S,M,L,XL,XXL|154|792|5",
//            // ---- men_vests
//            "men_vests|Jockey Pack of 2 Cotton Vests|Round neck, sleeveless. Soft on skin. Machine wash.|939|479|Maroon|S,M,L,XL,XXL|214|306|91",
//            "men_vests|Rupa Gym Vest|Dri-fit, deep armholes. Soft on skin. Machine wash.|599|349|Grey Melange|S,M,L,XL,XXL|91|3700|88",
//            "men_vests|Dollar Ribbed Sleeveless Vest|Rib knit, slim fit. Soft on skin. Machine wash.|459|219|Navy Blue|S,M,L,XL,XXL|114|189|2",
//            "men_vests|Van Heusen Micro Modal Vest|Silky soft modal. Soft on skin. Machine wash.|1099|769|Khaki|S,M,L,XL,XXL|227|3835|4",
//            "men_vests|XYXX Sports Tank Vest|Breathable mesh panels. Soft on skin. Machine wash.|519|369|Olive|S,M,L,XL,XXL|138|2370|100",
//            "men_vests|Lux Cozi Pack of 3 Classic Vests|Everyday comfort. Soft on skin. Machine wash.|589|259|Maroon|S,M,L,XL,XXL|125|3570|37",
//            // ---- men_sleepwear_and_loungewear
//            "men_sleepwear_and_loungewear|Jockey Cotton Pyjama Set|T-shirt and pyjama, soft cotton. Soft on skin. Machine wash.|2699|1399|Teal|S,M,L,XL,XXL|98|4515|73",
//            "men_sleepwear_and_loungewear|XYXX Checked Lounge Pants|Woven checks, drawstring. Soft on skin. Machine wash.|3099|1799|Maroon|S,M,L,XL,XXL|51|56|49",
//            "men_sleepwear_and_loungewear|U.S. Polo Assn. Printed Shorts Set|Tee with printed shorts. Soft on skin. Machine wash.|1299|939|Charcoal|S,M,L,XL,XXL|203|496|63",
//            "men_sleepwear_and_loungewear|Bewakoof Modal Lounge Set|Silky modal, relaxed. Soft on skin. Machine wash.|5199|1999|Black|S,M,L,XL,XXL|76|1539|78",
//            "men_sleepwear_and_loungewear|H&M Flannel Night Suit|Brushed flannel shirt and pants. Soft on skin. Machine wash.|2199|1099|Maroon|S,M,L,XL,XXL|237|3056|22",
//            "men_sleepwear_and_loungewear|Van Heusen Waffle Knit Lounge Set|Textured waffle knit. Soft on skin. Machine wash.|2499|1699|Beige|S,M,L,XL,XXL|231|870|43",
//            // ---- men_thermals
//            "men_thermals|Jockey Thermal Top and Bottom Set|Brushed inner, warm and light. Soft on skin. Machine wash.|929|649|Beige|S,M,L,XL,XXL|240|4574|79",
//            "men_thermals|Lux Cottswool Wool Blend Thermal Top|Merino blend, full sleeve. Soft on skin. Machine wash.|659|489|Mustard|S,M,L,XL,XXL|128|259|88",
//            "men_thermals|Dollar Heat Retention Thermal Leggings|Stretch thermal bottoms. Soft on skin. Machine wash.|1599|1199|Charcoal|S,M,L,XL,XXL|163|1378|109",
//            "men_thermals|Rupa V-Neck Thermal Vest|Layer under shirts. Soft on skin. Machine wash.|1899|1399|Mustard|S,M,L,XL,XXL|126|3383|94",
//            "men_thermals|Decathlon Wedze Ski Base Layer Set|Moisture-wicking base layer. Soft on skin. Machine wash.|1999|819|Green|S,M,L,XL,XXL|243|2002|4",
//            "men_thermals|HRX Fleece Lined Thermal Top|Extra warmth for winters. Soft on skin. Machine wash.|1199|529|Black|S,M,L,XL,XXL|175|3692|69",
//            // ---- men_plus_size
//            "men_plus_size|Instafab Plus Plus Size Cotton T-Shirt|Relaxed cut, longer length. Designed for a comfortable plus-size fit.|1799|1199|Olive|XXL,3XL,4XL,5XL|185|779|25",
//            "men_plus_size|Levi's Plus Size Casual Shirt|Comfort fit with stretch. Designed for a comfortable plus-size fit.|3399|2199|Olive|XXL,3XL,4XL,5XL|141|2601|51",
//            "men_plus_size|Roadster Plus Size Jeans|Comfort waist, straight leg. Designed for a comfortable plus-size fit.|4499|2099|Black|XXL,3XL,4XL,5XL|223|4310|108",
//            "men_plus_size|U.S. Polo Assn. Plus Size Chinos|Flex waistband. Designed for a comfortable plus-size fit.|4699|1899|Green|XXL,3XL,4XL,5XL|105|2821|2",
//            "men_plus_size|Peter England Plus Size Polo T-Shirt|Pique cotton, roomy fit. Designed for a comfortable plus-size fit.|1699|769|White|XXL,3XL,4XL,5XL|65|4307|3",
//            "men_plus_size|Jack & Jones Plus Size Hoodie|Brushed fleece, kangaroo pocket. Designed for a comfortable plus-size fit.|2499|1499|Beige|XXL,3XL,4XL,5XL|76|1570|69",
//            // ---- men_casual_shoes
//            "men_casual_shoes|Red Tape Leather Loafers|Genuine leather slip-on loafers. Cushioned insole for all-day comfort. 30-day warranty.|2699|1599|White|6,7,8,9,10,11|140|4729|110",
//            "men_casual_shoes|Woodland Suede Desert Boots|Crepe sole, lace-up. Cushioned insole for all-day comfort. 30-day warranty.|8699|3099|Coffee Brown|6,7,8,9,10,11|248|2455|66",
//            "men_casual_shoes|Bata Canvas Slip-On Shoes|Lightweight canvas, cushioned insole. Cushioned insole for all-day comfort. 30-day warranty.|3799|1899|Brown|6,7,8,9,10,11|208|4393|120",
//            "men_casual_shoes|Clarks Boat Shoes|Leather boat shoes with 360 lacing. Cushioned insole for all-day comfort. 30-day warranty.|2399|1799|Brown|6,7,8,9,10,11|63|4048|25",
//            "men_casual_shoes|U.S. Polo Assn. Casual Derby Shoes|Smart-casual derbies. Cushioned insole for all-day comfort. 30-day warranty.|1499|1099|Coffee Brown|6,7,8,9,10,11|21|3395|73",
//            "men_casual_shoes|Roadster Driving Moccasins|Pebble sole moccasins. Cushioned insole for all-day comfort. 30-day warranty.|6299|3899|White|6,7,8,9,10,11|72|1259|70",
//            // ---- men_sports_shoes
//            "men_sports_shoes|Nike Running Shoes with Cushioned Sole|Responsive foam midsole, breathable mesh. Cushioned insole for all-day comfort. 30-day warranty.|4799|3599|Beige|6,7,8,9,10,11|230|4594|70",
//            "men_sports_shoes|Adidas Training Shoes|Stable base for gym workouts. Cushioned insole for all-day comfort. 30-day warranty.|5499|4399|Beige|6,7,8,9,10,11|240|3378|102",
//            "men_sports_shoes|Puma Walking Shoes|Memory foam insole. Cushioned insole for all-day comfort. 30-day warranty.|5299|3299|Beige|6,7,8,9,10,11|152|4008|60",
//            "men_sports_shoes|Asics Trail Running Shoes|Grippy lugged outsole. Cushioned insole for all-day comfort. 30-day warranty.|6399|4699|Tan|6,7,8,9,10,11|62|3302|48",
//            "men_sports_shoes|Skechers Basketball High-Tops|Ankle support, cushioned. Cushioned insole for all-day comfort. 30-day warranty.|10299|6899|White|6,7,8,9,10,11|150|106|58",
//            "men_sports_shoes|Campus Lightweight Knit Runners|Sock-like knit upper. Cushioned insole for all-day comfort. 30-day warranty.|10899|6399|Brown|6,7,8,9,10,11|161|511|94",
//            // ---- men_formal_shoes
//            "men_formal_shoes|Hush Puppies Leather Oxford Shoes|Classic cap-toe oxfords. Cushioned insole for all-day comfort. 30-day warranty.|6599|2699|Brown|6,7,8,9,10,11|127|1135|4",
//            "men_formal_shoes|Clarks Brogue Derby Shoes|Wingtip brogue detailing. Cushioned insole for all-day comfort. 30-day warranty.|5699|3799|Beige|6,7,8,9,10,11|185|1616|18",
//            "men_formal_shoes|Bata Monk Strap Shoes|Double monk strap, leather. Cushioned insole for all-day comfort. 30-day warranty.|9899|5999|Beige|6,7,8,9,10,11|71|3081|117",
//            "men_formal_shoes|Red Tape Patent Leather Party Shoes|High-shine finish. Cushioned insole for all-day comfort. 30-day warranty.|3799|2799|Brown|6,7,8,9,10,11|131|993|0",
//            "men_formal_shoes|Louis Philippe Chelsea Boots|Elastic side panels. Cushioned insole for all-day comfort. 30-day warranty.|4399|2699|Navy Blue|6,7,8,9,10,11|53|2424|56",
//            "men_formal_shoes|Van Heusen Slip-On Formal Loafers|Apron toe loafers. Cushioned insole for all-day comfort. 30-day warranty.|4799|2399|Tan|6,7,8,9,10,11|104|2792|99",
//            // ---- men_sneakers
//            "men_sneakers|Nike White Leather Sneakers|Minimal white leather low-tops. Cushioned insole for all-day comfort. 30-day warranty.|12399|7999|White|6,7,8,9,10,11|128|3200|90",
//            "men_sneakers|Adidas Chunky Sneakers|Retro chunky sole. Cushioned insole for all-day comfort. 30-day warranty.|2999|2099|Beige|6,7,8,9,10,11|232|2515|11",
//            "men_sneakers|Puma Canvas High-Top Sneakers|Classic canvas high-tops. Cushioned insole for all-day comfort. 30-day warranty.|10399|6599|Beige|6,7,8,9,10,11|40|2878|71",
//            "men_sneakers|Converse Court Classic Sneakers|Tennis-inspired design. Cushioned insole for all-day comfort. 30-day warranty.|9599|4399|Navy Blue|6,7,8,9,10,11|102|254|55",
//            "men_sneakers|Vans Colourblock Retro Sneakers|Suede overlays. Cushioned insole for all-day comfort. 30-day warranty.|9299|4899|Coffee Brown|6,7,8,9,10,11|206|1689|60",
//            "men_sneakers|Bewakoof Air Slip-On Checkerboard Sneakers|Iconic checker print. Cushioned insole for all-day comfort. 30-day warranty.|2499|1699|Brown|6,7,8,9,10,11|115|1534|21",
//            // ---- men_sandals_and_floaters
//            "men_sandals_and_floaters|Crocs Velcro Floaters|Adjustable straps, cushioned footbed. Cushioned insole for all-day comfort. 30-day warranty.|2599|879|Tan|6,7,8,9,10,11|225|4628|69",
//            "men_sandals_and_floaters|Bata Leather Sandals|Genuine leather, buckle closure. Cushioned insole for all-day comfort. 30-day warranty.|3299|2099|Navy Blue|6,7,8,9,10,11|67|3639|107",
//            "men_sandals_and_floaters|Woodland Sports Sandals|Rugged outsole for treks. Cushioned insole for all-day comfort. 30-day warranty.|3499|2499|Brown|6,7,8,9,10,11|61|4336|80",
//            "men_sandals_and_floaters|Sparx Classic Clogs|Lightweight foam clogs. Cushioned insole for all-day comfort. 30-day warranty.|4599|1899|Beige|6,7,8,9,10,11|53|1947|4",
//            "men_sandals_and_floaters|Red Chief Slide Sandals|Soft footbed slides. Cushioned insole for all-day comfort. 30-day warranty.|2699|1699|White|6,7,8,9,10,11|34|3293|87",
//            "men_sandals_and_floaters|Puma Kolhapuri Sandals|Handcrafted leather kolhapuris. Cushioned insole for all-day comfort. 30-day warranty.|1299|879|Coffee Brown|6,7,8,9,10,11|200|4024|106",
//            // ---- men_flip_flops
//            "men_flip_flops|Adidas Rubber Flip Flops|Soft rubber, textured footbed. Cushioned insole for all-day comfort. 30-day warranty.|649|369|Beige|6,7,8,9,10,11|101|2295|106",
//            "men_flip_flops|Puma Printed Flip Flops|Graphic printed straps. Cushioned insole for all-day comfort. 30-day warranty.|519|269|Beige|6,7,8,9,10,11|224|3840|92",
//            "men_flip_flops|Crocs Comfort Slides|Moulded footbed slides. Cushioned insole for all-day comfort. 30-day warranty.|609|449|White|6,7,8,9,10,11|148|1544|4",
//            "men_flip_flops|Bata Thong Sandals|Lightweight EVA. Cushioned insole for all-day comfort. 30-day warranty.|1299|929|Coffee Brown|6,7,8,9,10,11|21|488|60",
//            "men_flip_flops|Nike Cushioned Flip Flops|Extra soft cushioning. Cushioned insole for all-day comfort. 30-day warranty.|939|459|Grey|6,7,8,9,10,11|208|3702|65",
//            "men_flip_flops|Sparx Anti-Skid Bathroom Slippers|Grippy sole. Cushioned insole for all-day comfort. 30-day warranty.|799|399|Coffee Brown|6,7,8,9,10,11|180|3717|106",
//            // ---- men_socks
//            "men_socks|Puma Pack of 3 Ankle Socks|Cotton-rich ankle socks. Quality checked. Easy returns.|729|319|Navy Blue|FREE|249|4653|62",
//            "men_socks|Adidas Pack of 5 Low Show Socks|Invisible no-show socks. Quality checked. Easy returns.|929|529|White|FREE|197|289|3",
//            "men_socks|Jockey Crew Length Sports Socks|Cushioned sole. Quality checked. Easy returns.|509|219|Green|FREE|102|4435|21",
//            "men_socks|Nike Formal Calf Socks|Fine mercerised cotton. Quality checked. Easy returns.|869|389|Maroon|FREE|146|4389|38",
//            "men_socks|Bonjour Woollen Winter Socks|Warm wool blend. Quality checked. Easy returns.|719|299|Beige|FREE|33|3654|40",
//            "men_socks|Supersox Anti-Odour Bamboo Socks|Bamboo fibre, odour control. Quality checked. Easy returns.|409|279|Grey Melange|FREE|238|2518|40",
//            // ---- men_sunglasses_and_frames
//            "men_sunglasses_and_frames|Ray-Ban Aviator Sunglasses|UV400 polarised lenses. Quality checked. Easy returns.|13899|4999|Mustard|FREE|142|1675|63",
//            "men_sunglasses_and_frames|Fastrack Wayfarer Sunglasses|Classic wayfarer frame. Quality checked. Easy returns.|5499|3199|Green|FREE|198|3740|67",
//            "men_sunglasses_and_frames|Vincent Chase Round Metal Sunglasses|Retro round lenses. Quality checked. Easy returns.|2799|1399|Khaki|FREE|181|3469|14",
//            "men_sunglasses_and_frames|IDEE Sports Wrap Sunglasses|Lightweight wrap-around. Quality checked. Easy returns.|9599|4399|Gold|FREE|17|3256|116",
//            "men_sunglasses_and_frames|Carrera Clubmaster Sunglasses|Browline design. Quality checked. Easy returns.|4399|3499|Beige|FREE|219|4121|15",
//            "men_sunglasses_and_frames|Lenskart Air Rectangle Eyeglass Frames|Blue-cut lens ready frames. Quality checked. Easy returns.|9099|6399|Grey Melange|FREE|97|3009|110",
//            // ---- men_watches
//            "men_watches|Titan Analog Leather Strap Watch|Minimal dial, genuine leather strap. Quality checked. Easy returns.|22099|9699|Black|FREE|206|1817|76",
//            "men_watches|Fossil Chronograph Stainless Steel Watch|Three sub-dials, 50m water resistance. Quality checked. Easy returns.|15999|9399|Charcoal|FREE|136|1387|91",
//            "men_watches|Casio Digital Sports Watch|Stopwatch, alarm and backlight. Quality checked. Easy returns.|7399|5099|Charcoal|FREE|47|3588|65",
//            "men_watches|Timex Automatic Skeleton Watch|Exposed movement dial. Quality checked. Easy returns.|11099|8299|Olive|FREE|122|2356|23",
//            "men_watches|Fastrack Analog-Digital Watch|Dual display, resin strap. Quality checked. Easy returns.|15499|6199|Teal|FREE|243|566|108",
//            "men_watches|Tommy Hilfiger Minimal Mesh Strap Watch|Ultra-slim case with mesh strap. Quality checked. Easy returns.|3399|2099|Beige|FREE|16|1205|117",
//            // ---- men_sports_and_active_wear
//            "men_sports_and_active_wear|Nike Training Set T-Shirt and Shorts|Dri-fit two-piece set. Machine wash cold. Easy 7-day returns.|5399|2499|Charcoal|S,M,L,XL,XXL|143|1732|70",
//            "men_sports_and_active_wear|Adidas Compression Tights|Muscle support, quick dry. Machine wash cold. Easy 7-day returns.|2799|1199|Green|S,M,L,XL,XXL|104|4319|65",
//            "men_sports_and_active_wear|Puma Sleeveless Gym Tank|Breathable mesh. Machine wash cold. Easy 7-day returns.|3699|1299|White|S,M,L,XL,XXL|133|224|94",
//            "men_sports_and_active_wear|HRX Running Jacket|Lightweight, reflective prints. Machine wash cold. Easy 7-day returns.|3999|2699|Beige|S,M,L,XL,XXL|250|4069|26",
//            "men_sports_and_active_wear|Decathlon Domyos Performance Polo|Moisture-wicking polo. Machine wash cold. Easy 7-day returns.|3199|1999|Black|S,M,L,XL,XXL|56|3513|62",
//            "men_sports_and_active_wear|Under Armour Yoga Pants|Four-way stretch. Machine wash cold. Easy 7-day returns.|5799|2399|Mustard|S,M,L,XL,XXL|204|1123|89",
//            // ---- men_sports_sandals
//            "men_sports_sandals|Woodland Trekking Sports Sandals|Rugged lugged outsole. Cushioned insole for all-day comfort. 30-day warranty.|1199|829|Beige|6,7,8,9,10,11|175|1087|31",
//            "men_sports_sandals|Puma Velcro Strap Sports Sandals|Adjustable three straps. Cushioned insole for all-day comfort. 30-day warranty.|2199|1599|Brown|6,7,8,9,10,11|31|3966|112",
//            "men_sports_sandals|Adidas Water Sports Sandals|Quick-dry straps. Cushioned insole for all-day comfort. 30-day warranty.|4299|2499|Tan|6,7,8,9,10,11|112|1413|118",
//            "men_sports_sandals|Decathlon Quechua Hiking Sandals|Toe protection bumper. Cushioned insole for all-day comfort. 30-day warranty.|1799|1199|Coffee Brown|6,7,8,9,10,11|64|3285|111",
//            "men_sports_sandals|Sparx Lightweight Running Sandals|Minimal cushioned base. Cushioned insole for all-day comfort. 30-day warranty.|4099|1699|White|6,7,8,9,10,11|179|3861|52",
//            "men_sports_sandals|Bata Leather Sports Sandals|Nubuck leather straps. Cushioned insole for all-day comfort. 30-day warranty.|1899|1199|Black|6,7,8,9,10,11|131|4029|112",
//            // ---- men_active_t_shirts
//            "men_active_t_shirts|Nike Dri-Fit Training T-Shirt|Sweat-wicking, quick-dry. Machine wash cold. Easy 7-day returns.|2799|1699|Navy Blue|S,M,L,XL,XXL|133|3818|9",
//            "men_active_t_shirts|Adidas Running T-Shirt with Reflective Logo|Reflective print for night runs. Machine wash cold. Easy 7-day returns.|1599|889|White|S,M,L,XL,XXL|187|799|94",
//            "men_active_t_shirts|Puma Compression T-Shirt|Second-skin fit. Machine wash cold. Easy 7-day returns.|1999|1299|Mustard|S,M,L,XL,XXL|123|1358|85",
//            "men_active_t_shirts|HRX Seamless Training Tee|Body-mapped ventilation. Machine wash cold. Easy 7-day returns.|2399|1299|Grey Melange|S,M,L,XL,XXL|162|4683|66",
//            "men_active_t_shirts|Decathlon Kalenji Graphic Gym T-Shirt|Bold gym graphic. Machine wash cold. Easy 7-day returns.|2699|1499|Olive|S,M,L,XL,XXL|240|659|117",
//            "men_active_t_shirts|Reebok Full Sleeve Running Tee|Thumbholes, light. Machine wash cold. Easy 7-day returns.|1799|839|Khaki|S,M,L,XL,XXL|96|1776|8",
//            // ---- men_track_pants_and_shorts
//            "men_track_pants_and_shorts|Puma Training Shorts with Zip Pocket|Quick-dry woven shorts. Machine wash. Easy 7-day returns.|2199|1299|Olive|28,30,32,34,36|187|2696|33",
//            "men_track_pants_and_shorts|Adidas 2-in-1 Running Shorts|Inner compression liner. Machine wash. Easy 7-day returns.|1399|659|Maroon|28,30,32,34,36|168|298|41",
//            "men_track_pants_and_shorts|Nike Slim Track Pants|Tapered track pants. Machine wash. Easy 7-day returns.|4199|1599|Green|28,30,32,34,36|153|1352|55",
//            "men_track_pants_and_shorts|HRX Basketball Shorts|Mesh, longer length. Machine wash. Easy 7-day returns.|4099|1499|Olive|28,30,32,34,36|65|170|112",
//            "men_track_pants_and_shorts|Reebok Woven Track Pants|Lightweight and breathable. Machine wash. Easy 7-day returns.|1999|1299|Olive|28,30,32,34,36|245|4035|75",
//            "men_track_pants_and_shorts|Decathlon Kalenji Gym Joggers|Stretch with cuffed hems. Machine wash. Easy 7-day returns.|3499|1499|Olive|28,30,32,34,36|76|3138|117",
//            // ---- men_tracksuits
//            "men_tracksuits|Adidas Three-Stripe Tracksuit|Jacket and pants in tricot. Machine wash. Easy 7-day returns.|5199|2499|White|28,30,32,34,36|84|1470|101",
//            "men_tracksuits|Puma Fleece Tracksuit|Hoodie and jogger set. Machine wash. Easy 7-day returns.|6299|3499|Maroon|28,30,32,34,36|207|2187|19",
//            "men_tracksuits|Nike Colourblock Tracksuit|Retro colourblock design. Machine wash. Easy 7-day returns.|7299|2599|Mustard|28,30,32,34,36|103|1659|83",
//            "men_tracksuits|HRX Woven Windbreaker Tracksuit|Lightweight shell set. Machine wash. Easy 7-day returns.|8999|4499|Maroon|28,30,32,34,36|124|3961|47",
//            "men_tracksuits|Reebok Slim Fit Training Tracksuit|Stretch training set. Machine wash. Easy 7-day returns.|3199|1799|Charcoal|28,30,32,34,36|71|2326|50",
//            "men_tracksuits|Fila Velour Tracksuit|Plush velour, zip jacket. Machine wash. Easy 7-day returns.|9199|4399|White|28,30,32,34,36|117|2041|90",
//            // ---- men_jackets_and_sweatshirts
//            "men_jackets_and_sweatshirts|Puma Training Hoodie|Brushed fleece, kangaroo pocket. Machine wash cold. Easy 7-day returns.|5599|2399|White|S,M,L,XL,XXL|194|4004|53",
//            "men_jackets_and_sweatshirts|Adidas Running Windbreaker|Packable, water resistant. Machine wash cold. Easy 7-day returns.|4899|1799|Olive|S,M,L,XL,XXL|203|4018|99",
//            "men_jackets_and_sweatshirts|Nike Track Jacket|Full zip with stand collar. Machine wash cold. Easy 7-day returns.|3399|1399|Mustard|S,M,L,XL,XXL|152|2734|33",
//            "men_jackets_and_sweatshirts|HRX Quarter-Zip Training Top|Thermal knit. Machine wash cold. Easy 7-day returns.|3299|1199|Maroon|S,M,L,XL,XXL|49|3202|14",
//            "men_jackets_and_sweatshirts|Under Armour Puffer Sports Vest|Sleeveless padded vest. Machine wash cold. Easy 7-day returns.|6499|3799|Olive|S,M,L,XL,XXL|242|3016|72",
//            "men_jackets_and_sweatshirts|Reebok Fleece Zip Jacket|Polar fleece. Machine wash cold. Easy 7-day returns.|3099|2399|Teal|S,M,L,XL,XXL|66|1591|74",
//            // ---- men_sports_accessories
//            "men_sports_accessories|Nike Gym Duffel Bag|Shoe compartment, water-resistant. Quality checked. Easy returns.|4799|2799|Grey Melange|FREE|185|933|51",
//            "men_sports_accessories|Adidas Yoga Mat 6mm|Anti-slip TPE mat. Quality checked. Easy returns.|2099|1499|Grey Melange|FREE|81|2593|83",
//            "men_sports_accessories|Puma Resistance Bands Set|Five resistance levels. Quality checked. Easy returns.|1399|929|Charcoal|FREE|38|2252|13",
//            "men_sports_accessories|Decathlon Domyos Sports Water Bottle 750ml|BPA-free, leak-proof. Quality checked. Easy returns.|2499|1899|Black|FREE|175|2158|96",
//            "men_sports_accessories|Boldfit Gym Gloves|Padded palm, wrist wrap. Quality checked. Easy returns.|5299|2499|Green|FREE|33|1305|4",
//            "men_sports_accessories|Cosco Skipping Rope|Ball-bearing speed rope. Quality checked. Easy returns.|8399|2999|Teal|FREE|96|4629|42",
//            // ---- men_swimwear
//            "men_swimwear|Speedo Swim Jammers|Chlorine-resistant, knee length. Soft on skin. Machine wash.|2599|1299|Khaki|S,M,L,XL,XXL|32|1370|93",
//            "men_swimwear|Arena Swim Trunks|Quick-dry, drawstring. Soft on skin. Machine wash.|3099|1199|Mustard|S,M,L,XL,XXL|22|1267|90",
//            "men_swimwear|Decathlon Nabaiji Printed Board Shorts|Beach-ready print. Soft on skin. Machine wash.|2299|1399|Grey Melange|S,M,L,XL,XXL|20|3860|10",
//            "men_swimwear|Puma Swim Briefs|Competition cut. Soft on skin. Machine wash.|1399|899|Olive|S,M,L,XL,XXL|243|3296|97",
//            "men_swimwear|Adidas Rash Guard Top|UPF 50+ sun protection. Soft on skin. Machine wash.|1199|549|Beige|S,M,L,XL,XXL|16|239|16",
//            "men_swimwear|Jockey Swimming Kit with Goggles|Trunks, cap and goggles. Soft on skin. Machine wash.|3799|1799|Teal|S,M,L,XL,XXL|123|1314|62",
//            // ---- men_wallets
//            "men_wallets|Tommy Hilfiger Genuine Leather Bi-Fold Wallet|RFID blocking, 8 card slots. Quality checked. Easy returns.|899|449|White|FREE|190|1644|24",
//            "men_wallets|Woodland Slim Card Holder|Minimal card case. Quality checked. Easy returns.|6899|2399|Navy Blue|FREE|40|3705|45",
//            "men_wallets|Hidesign Tri-Fold Leather Wallet|Coin pocket and ID window. Quality checked. Easy returns.|6599|2499|Olive|FREE|65|1761|55",
//            "men_wallets|Wildhorn Zip-Around Travel Wallet|Passport compartment. Quality checked. Easy returns.|1899|1099|Green|FREE|196|3495|104",
//            "men_wallets|Fossil Money Clip Wallet|Front pocket money clip. Quality checked. Easy returns.|4899|2499|Black|FREE|80|2834|75",
//            "men_wallets|Levi's Canvas Bi-Fold Wallet|Lightweight canvas. Quality checked. Easy returns.|1799|799|Gold|FREE|141|1029|28",
//            // ---- men_belts
//            "men_belts|Tommy Hilfiger Genuine Leather Formal Belt|Pin buckle, 35mm. Quality checked. Easy returns.|439|319|Khaki|FREE|149|3656|114",
//            "men_belts|Louis Philippe Reversible Leather Belt|Black and brown in one. Quality checked. Easy returns.|1699|639|Teal|FREE|42|2946|78",
//            "men_belts|Woodland Casual Canvas Belt|Woven canvas with metal tip. Quality checked. Easy returns.|4499|1799|Maroon|FREE|136|4029|55",
//            "men_belts|Levi's Auto-Lock Buckle Belt|Ratchet buckle, no holes. Quality checked. Easy returns.|1499|1099|Navy Blue|FREE|246|3145|21",
//            "men_belts|Allen Solly Braided Leather Belt|Stretch braided design. Quality checked. Easy returns.|2199|1499|Mustard|FREE|78|1372|119",
//            "men_belts|Hidesign Suede Casual Belt|Soft suede finish. Quality checked. Easy returns.|1199|899|Navy Blue|FREE|244|364|68",
//            // ---- men_perfumes_and_body_mists
//            "men_perfumes_and_body_mists|Denver Eau de Parfum 100ml|Woody aromatic, long lasting. Quality checked. Easy returns.|2299|1699|Beige|FREE|133|4004|62",
//            "men_perfumes_and_body_mists|Bella Vita Body Spray 150ml|Fresh citrus notes. Quality checked. Easy returns.|5099|2599|Green|FREE|25|4778|67",
//            "men_perfumes_and_body_mists|Wild Stone Oud Perfume 50ml|Rich oud with amber. Quality checked. Easy returns.|6499|3499|Olive|FREE|191|840|2",
//            "men_perfumes_and_body_mists|Fogg Aqua Fresh EDT 100ml|Marine and mint. Quality checked. Easy returns.|5599|3199|Green|FREE|197|4455|91",
//            "men_perfumes_and_body_mists|Engage Luxury Gift Set of 4 Perfumes|4 x 20ml travel set. Quality checked. Easy returns.|839|459|Mustard|FREE|164|3488|76",
//            "men_perfumes_and_body_mists|Park Avenue Musk Body Mist 200ml|Light musk. Quality checked. Easy returns.|2099|1299|Teal|FREE|28|784|14",
//            // ---- men_trimmers
//            "men_trimmers|Philips Cordless Beard Trimmer|60 min runtime, 20 length settings. Quality checked. Easy returns.|4899|2399|Black|FREE|166|2641|46",
//            "men_trimmers|Mi Multi-Grooming Kit 9-in-1|Hair, beard and nose. Quality checked. Easy returns.|4199|2199|White|FREE|221|3061|110",
//            "men_trimmers|Braun Body Groomer|Skin-friendly guards. Quality checked. Easy returns.|1299|829|Mustard|FREE|92|2430|35",
//            "men_trimmers|Nova Nose and Ear Trimmer|Dual-edge blades. Quality checked. Easy returns.|1799|1399|Gold|FREE|118|2851|24",
//            "men_trimmers|Havells Rechargeable Hair Clipper|Titanium blades. Quality checked. Easy returns.|3299|1199|Green|FREE|108|1105|76",
//            "men_trimmers|Syska Foil Shaver|Close shave, wet and dry. Quality checked. Easy returns.|3199|2099|White|FREE|228|80|28",
//            // ---- men_deodorants
//            "men_deodorants|Nivea 48H Fresh Deodorant 150ml|Anti-perspirant protection. Quality checked. Easy returns.|709|409|Green|FREE|64|2289|99",
//            "men_deodorants|Axe Pocket Perfume 18ml|Travel friendly. Quality checked. Easy returns.|409|259|White|FREE|173|3163|26",
//            "men_deodorants|Park Avenue Roll-On Deodorant 50ml|Alcohol-free. Quality checked. Easy returns.|409|259|Grey Melange|FREE|138|1663|88",
//            "men_deodorants|Fogg Deo Combo Pack of 3|Three fragrances. Quality checked. Easy returns.|709|559|Khaki|FREE|82|3324|106",
//            "men_deodorants|Wild Stone Sport Deodorant 200ml|Active freshness. Quality checked. Easy returns.|919|569|Gold|FREE|15|2502|51",
//            "men_deodorants|Engage Aqua Deodorant 150ml|Cool marine notes. Quality checked. Easy returns.|579|399|Navy Blue|FREE|63|2867|29",
//            // ---- men_ties_cufflinks_and_pocket_squares
//            "men_ties_cufflinks_and_pocket_squares|Louis Philippe Silk Tie with Pocket Square|Woven silk, matching square. Quality checked. Easy returns.|1999|1599|Maroon|FREE|135|349|62",
//            "men_ties_cufflinks_and_pocket_squares|Van Heusen Tie, Cufflinks and Pocket Square Set|Gift boxed trio. Quality checked. Easy returns.|1899|909|Green|FREE|161|2554|43",
//            "men_ties_cufflinks_and_pocket_squares|Peluche Skinny Knit Tie|Textured knit. Quality checked. Easy returns.|1299|779|Maroon|FREE|69|1804|74",
//            "men_ties_cufflinks_and_pocket_squares|Tossido Paisley Print Tie|Classic paisley. Quality checked. Easy returns.|3399|1799|Black|FREE|39|3306|107",
//            "men_ties_cufflinks_and_pocket_squares|Alvaro Castagnino Metal Cufflinks Set|Rhodium plated. Quality checked. Easy returns.|1199|719|Green|FREE|17|537|19",
//            "men_ties_cufflinks_and_pocket_squares|Raymond Bow Tie with Pocket Square|Pre-tied bow tie. Quality checked. Easy returns.|2599|1999|Beige|FREE|26|2196|11",
//            // ---- men_accessory_gift_sets
//            "men_accessory_gift_sets|Tommy Hilfiger Wallet and Belt Combo|Leather gift box. Quality checked. Easy returns.|4099|2499|Beige|FREE|98|259|62",
//            "men_accessory_gift_sets|Louis Philippe Watch and Wallet Set|Analog watch with wallet. Quality checked. Easy returns.|4099|2699|Grey Melange|FREE|86|924|92",
//            "men_accessory_gift_sets|Fossil Grooming Gift Kit|Trimmer, perfume, comb. Quality checked. Easy returns.|4199|1899|White|FREE|72|137|44",
//            "men_accessory_gift_sets|Woodland Perfume Gift Set|Four travel sprays. Quality checked. Easy returns.|3499|2399|Maroon|FREE|136|3310|89",
//            "men_accessory_gift_sets|Hidesign Keychain and Card Holder Set|Leather duo. Quality checked. Easy returns.|2399|1799|Charcoal|FREE|47|1168|80",
//            "men_accessory_gift_sets|Bella Vita Sunglasses and Belt Combo|Gift ready. Quality checked. Easy returns.|1699|999|Beige|FREE|92|4421|90",
//            // ---- men_caps_and_hats
//            "men_caps_and_hats|Puma Baseball Cap|Adjustable strap, cotton twill. Quality checked. Easy returns.|409|309|Grey Melange|FREE|69|1928|103",
//            "men_caps_and_hats|Nike Snapback Cap|Flat brim, embroidered logo. Quality checked. Easy returns.|1199|919|Beige|FREE|79|3399|75",
//            "men_caps_and_hats|Adidas Bucket Hat|Reversible cotton. Quality checked. Easy returns.|2699|1399|Navy Blue|FREE|161|4687|61",
//            "men_caps_and_hats|Roadster Woollen Beanie|Ribbed knit. Quality checked. Easy returns.|1599|1199|Black|FREE|134|2104|36",
//            "men_caps_and_hats|Levi's Running Cap|Lightweight, quick dry. Quality checked. Easy returns.|1299|869|Silver|FREE|190|4524|111",
//            "men_caps_and_hats|New Era Fedora Hat|Classic band detail. Quality checked. Easy returns.|1299|649|Brown|FREE|134|2593|120",
//            // ---- men_mufflers_scarves_and_gloves
//            "men_mufflers_scarves_and_gloves|Monte Carlo Woollen Muffler|Soft knit, fringed ends. Quality checked. Easy returns.|959|469|Navy Blue|FREE|37|3939|94",
//            "men_mufflers_scarves_and_gloves|Woodland Touchscreen Winter Gloves|Fleece lined. Quality checked. Easy returns.|1799|839|Navy Blue|FREE|215|4305|83",
//            "men_mufflers_scarves_and_gloves|Wildcraft Checked Scarf|Brushed acrylic. Quality checked. Easy returns.|1899|869|Beige|FREE|221|373|55",
//            "men_mufflers_scarves_and_gloves|Decathlon Wedze Neck Warmer|Fleece gaiter. Quality checked. Easy returns.|2199|1099|Gold|FREE|227|4307|39",
//            "men_mufflers_scarves_and_gloves|Roadster Riding Gloves|Padded knuckles. Quality checked. Easy returns.|739|519|White|FREE|93|673|63",
//            "men_mufflers_scarves_and_gloves|Fabindia Cashmere Blend Scarf|Lightweight luxury. Quality checked. Easy returns.|669|529|Silver|FREE|210|2408|108",
//            // ---- men_phone_cases
//            "men_phone_cases|Spigen Rugged Armor Case|Shock-absorbing TPU. Quality checked. Easy returns.|469|219|Gold|FREE|224|4732|4",
//            "men_phone_cases|Casekaro Leather Flip Cover|Card slot with stand. Quality checked. Easy returns.|1299|589|Mustard|FREE|100|2244|88",
//            "men_phone_cases|DailyObjects Clear Transparent Case|Anti-yellowing. Quality checked. Easy returns.|2199|739|Green|FREE|201|2587|116",
//            "men_phone_cases|Ringke Printed Hard Case|Matte printed design. Quality checked. Easy returns.|1199|869|Gold|FREE|68|709|67",
//            "men_phone_cases|Bewakoof MagSafe Compatible Case|Magnetic ring. Quality checked. Easy returns.|389|239|Olive|FREE|186|2136|40",
//            "men_phone_cases|OtterBox Silicone Case|Soft-touch microfiber lining. Quality checked. Easy returns.|549|349|Khaki|FREE|45|3050|20",
//            // ---- men_rings_and_wristwear
//            "men_rings_and_wristwear|Fossil Stainless Steel Band Ring|Brushed finish. Quality checked. Easy returns.|919|459|Khaki|FREE|94|4276|52",
//            "men_rings_and_wristwear|Giva Leather Braided Bracelet|Magnetic clasp. Quality checked. Easy returns.|2699|1699|Beige|FREE|74|2850|100",
//            "men_rings_and_wristwear|Salty Beaded Stone Bracelet|Natural stone beads. Quality checked. Easy returns.|3599|1899|Gold|FREE|235|1244|107",
//            "men_rings_and_wristwear|Dare by Voylla Silver Kada|925 sterling silver. Quality checked. Easy returns.|1399|979|Silver|FREE|184|3422|50",
//            "men_rings_and_wristwear|Tommy Hilfiger Chain Link Bracelet|Steel cuban link. Quality checked. Easy returns.|2099|769|Teal|FREE|92|4017|95",
//            "men_rings_and_wristwear|Bewakoof Signet Ring|Black onyx inlay. Quality checked. Easy returns.|1699|1299|Beige|FREE|95|3394|56",
//            // ---- men_helmets
//            "men_helmets|Steelbird Full Face Helmet|ISI certified, clear visor. Quality checked. Easy returns.|1999|1199|Navy Blue|FREE|127|1977|8",
//            "men_helmets|Vega Open Face Helmet|Retro open face. Quality checked. Easy returns.|2299|1099|Green|FREE|207|282|77",
//            "men_helmets|Studds Modular Flip-Up Helmet|Flip-up chin bar. Quality checked. Easy returns.|5399|3399|Charcoal|FREE|135|2878|108",
//            "men_helmets|SMK Off-Road Motocross Helmet|Peak visor. Quality checked. Easy returns.|5599|3699|Mustard|FREE|186|914|81",
//            "men_helmets|LS2 Bluetooth Ready Helmet|Speaker pockets. Quality checked. Easy returns.|3299|2199|White|FREE|131|3613|73",
//            "men_helmets|Royal Enfield Kids Half Helmet|Lightweight shell. Quality checked. Easy returns.|2899|1999|Olive|FREE|96|2490|114",
//            // ---- women_kurtas_and_suits
//            "women_kurtas_and_suits|Biba Anarkali Kurta with Dupatta|Flared anarkali in printed rayon. Gentle hand wash. Ideal for festive and daily wear.|4499|2499|Magenta|XS,S,M,L,XL,XXL|51|1745|95",
//            "women_kurtas_and_suits|W Straight Kurta Palazzo Set|Three-piece set with dupatta. Gentle hand wash. Ideal for festive and daily wear.|13999|4899|Off White|XS,S,M,L,XL,XXL|166|4374|84",
//            "women_kurtas_and_suits|Libas Embroidered Silk Blend Suit Set|Thread work yoke, churidar. Gentle hand wash. Ideal for festive and daily wear.|3399|1299|Rust|XS,S,M,L,XL,XXL|146|2669|111",
//            "women_kurtas_and_suits|Aurelia Chikankari Kurta Set|Lucknowi chikankari on georgette. Gentle hand wash. Ideal for festive and daily wear.|1599|1099|Navy Blue|XS,S,M,L,XL,XXL|85|384|37",
//            "women_kurtas_and_suits|Anouk A-Line Block Print Kurta|Hand block printed cotton. Gentle hand wash. Ideal for festive and daily wear.|4599|3399|Peach|XS,S,M,L,XL,XXL|159|1413|72",
//            "women_kurtas_and_suits|FabIndia Sharara Suit Set|Festive kurta with flared sharara. Gentle hand wash. Ideal for festive and daily wear.|3499|1999|Green|XS,S,M,L,XL,XXL|30|1887|75",
//            // ---- women_kurtis_tunics_tops
//            "women_kurtis_tunics_tops|W Printed Cotton Kurti|Short kurti with mandarin collar. Gentle hand wash. Ideal for festive and daily wear.|1299|779|Peach|XS,S,M,L,XL,XXL|132|1142|71",
//            "women_kurtis_tunics_tops|Aurelia Embroidered Tunic|Neck embroidery, flared hem. Gentle hand wash. Ideal for festive and daily wear.|1799|689|Cream|XS,S,M,L,XL,XXL|243|3194|81",
//            "women_kurtis_tunics_tops|Biba Kaftan Style Top|Relaxed kaftan in viscose. Gentle hand wash. Ideal for festive and daily wear.|879|629|Magenta|XS,S,M,L,XL,XXL|170|4605|82",
//            "women_kurtis_tunics_tops|Libas Peplum Ethnic Top|Peplum flare with tie-up. Gentle hand wash. Ideal for festive and daily wear.|3799|1799|Navy Blue|XS,S,M,L,XL,XXL|213|3788|55",
//            "women_kurtis_tunics_tops|Rangriti Angrakha Short Kurti|Wrap style with tassels. Gentle hand wash. Ideal for festive and daily wear.|1799|909|Pink|XS,S,M,L,XL,XXL|210|353|28",
//            "women_kurtis_tunics_tops|Global Desi Ikat Print Tunic|Handloom-inspired ikat. Gentle hand wash. Ideal for festive and daily wear.|1499|839|Maroon|XS,S,M,L,XL,XXL|239|4644|15",
//            // ---- women_sarees
//            "women_sarees|Kalini Banarasi Silk Saree|Zari woven pallu, with blouse piece. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|9799|3899|Teal|FREE|135|2294|89",
//            "women_sarees|Saree Mall Kanjivaram Silk Blend Saree|Temple border, rich zari. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|2699|1399|Maroon|FREE|105|1210|91",
//            "women_sarees|Mitera Georgette Sequinned Saree|Party-wear sequin work. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|16499|8699|Peach|FREE|97|375|41",
//            "women_sarees|Sangria Chiffon Printed Saree|Lightweight floral chiffon. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|4399|3499|Rust|FREE|173|1531|106",
//            "women_sarees|Kanjivaram Silks Cotton Handloom Saree|Breathable handloom with tassels. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|8899|5599|Rust|FREE|84|3586|21",
//            "women_sarees|Banarasi House Organza Embroidered Saree|Sheer organza with thread work. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|20499|8199|Navy Blue|FREE|86|2521|117",
//            "women_sarees|Satrani Linen Jamdani Saree|Jamdani motifs on linen. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|8699|6299|Green|FREE|113|1418|7",
//            "women_sarees|Kalini Bandhani Art Silk Saree|Traditional bandhej print. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|10999|6999|Rust|FREE|139|3627|76",
//            // ---- women_ethnic_wear
//            "women_ethnic_wear|Biba Indo-Western Cape Set|Cape with palazzo and top. Gentle hand wash. Ideal for festive and daily wear.|4499|1699|Pink|XS,S,M,L,XL,XXL|114|2657|50",
//            "women_ethnic_wear|Libas Gharara Set|Kurta with tiered gharara. Gentle hand wash. Ideal for festive and daily wear.|7899|5399|Rust|XS,S,M,L,XL,XXL|174|3655|29",
//            "women_ethnic_wear|Aurelia Dhoti Kurta Set|Fusion dhoti with kurta. Gentle hand wash. Ideal for festive and daily wear.|9199|3399|Mustard|XS,S,M,L,XL,XXL|61|2881|118",
//            "women_ethnic_wear|Sangria Jacket Style Kurta Set|Long jacket over kurta. Gentle hand wash. Ideal for festive and daily wear.|6499|3699|Mustard|XS,S,M,L,XL,XXL|214|3289|109",
//            "women_ethnic_wear|Anouk Ethnic Maxi Dress|Printed flared maxi. Gentle hand wash. Ideal for festive and daily wear.|2699|1499|Green|XS,S,M,L,XL,XXL|189|498|0",
//            "women_ethnic_wear|Indya Festive Co-ord Set|Embroidered top and pants. Gentle hand wash. Ideal for festive and daily wear.|3199|1499|Peach|XS,S,M,L,XL,XXL|78|888|98",
//            // ---- women_leggings_salwars_churidars
//            "women_leggings_salwars_churidars|Go Colors Cotton Lycra Churidar Leggings|Four-way stretch. Machine wash. Easy 7-day returns.|2499|959|Lavender|26,28,30,32,34|100|4395|16",
//            "women_leggings_salwars_churidars|De Moza Ankle Length Leggings|Bio-washed cotton. Machine wash. Easy 7-day returns.|1099|659|Mustard|26,28,30,32,34|79|2425|20",
//            "women_leggings_salwars_churidars|Twin Birds Patiala Salwar|Flowy patiala with drawstring. Machine wash. Easy 7-day returns.|589|299|Coral|26,28,30,32,34|72|859|60",
//            "women_leggings_salwars_churidars|Biba Dhoti Salwar|Draped dhoti style. Machine wash. Easy 7-day returns.|1399|809|Rust|26,28,30,32,34|159|4685|45",
//            "women_leggings_salwars_churidars|W Shimmer Party Leggings|Metallic shine. Machine wash. Easy 7-day returns.|629|389|Navy Blue|26,28,30,32,34|208|3702|43",
//            "women_leggings_salwars_churidars|Lyra Straight Cigarette Pants|Ethnic straight pants. Machine wash. Easy 7-day returns.|2599|899|Pink|26,28,30,32,34|37|88|66",
//            // ---- women_skirts_palazzos
//            "women_skirts_palazzos|W Flared Palazzo Pants|Wide leg in rayon. Machine wash. Easy 7-day returns.|1899|659|Teal|26,28,30,32,34|82|782|56",
//            "women_skirts_palazzos|Biba Printed Ethnic Skirt|Tiered printed skirt. Machine wash. Easy 7-day returns.|2099|839|Teal|26,28,30,32,34|98|4601|51",
//            "women_skirts_palazzos|Global Desi Culottes Palazzo|Cropped culottes. Machine wash. Easy 7-day returns.|959|699|Navy Blue|26,28,30,32,34|135|2396|42",
//            "women_skirts_palazzos|Libas Lehenga Style Skirt|Flared with gota trim. Machine wash. Easy 7-day returns.|899|669|Peach|26,28,30,32,34|120|1937|110",
//            "women_skirts_palazzos|Aurelia Pleated Palazzo|Knife pleats, elastic waist. Machine wash. Easy 7-day returns.|2199|1399|Green|26,28,30,32,34|102|3317|24",
//            "women_skirts_palazzos|Rangriti Wrap Ethnic Skirt|Wrap style with tie. Machine wash. Easy 7-day returns.|2699|1199|Maroon|26,28,30,32,34|182|3368|64",
//            // ---- women_dress_materials
//            "women_dress_materials|Satrani Unstitched Cotton Dress Material|Top, bottom and dupatta. Gentle hand wash. Ideal for festive and daily wear.|8099|2899|Mustard|XS,S,M,L,XL,XXL|15|2357|56",
//            "women_dress_materials|Kalini Embroidered Georgette Dress Material|Semi-stitched with net dupatta. Gentle hand wash. Ideal for festive and daily wear.|4399|2699|Green|XS,S,M,L,XL,XXL|155|4262|109",
//            "women_dress_materials|Saree Mall Chanderi Silk Dress Material|Zari butti. Gentle hand wash. Ideal for festive and daily wear.|2299|909|Gold|XS,S,M,L,XL,XXL|218|1300|12",
//            "women_dress_materials|Rajnandini Printed Rayon Dress Material|Digital print. Gentle hand wash. Ideal for festive and daily wear.|2999|2199|Magenta|XS,S,M,L,XL,XXL|202|4151|44",
//            "women_dress_materials|Mitera Pashmina Winter Dress Material|Warm pashmina blend. Gentle hand wash. Ideal for festive and daily wear.|4399|2199|Cream|XS,S,M,L,XL,XXL|54|1680|113",
//            "women_dress_materials|Ishin Organza Dress Material|Sheer organza dupatta. Gentle hand wash. Ideal for festive and daily wear.|2599|1499|Rust|XS,S,M,L,XL,XXL|126|976|64",
//            // ---- women_lehenga_cholis
//            "women_lehenga_cholis|Fabcartz Bridal Velvet Lehenga Choli|Heavy zari and stone work. Gentle hand wash. Ideal for festive and daily wear.|29899|16099|Gold|XS,S,M,L,XL,XXL|157|4404|99",
//            "women_lehenga_cholis|Sangria Sequinned Net Lehenga Set|Party-wear sequins. Gentle hand wash. Ideal for festive and daily wear.|16699|12799|Magenta|XS,S,M,L,XL,XXL|248|2082|86",
//            "women_lehenga_cholis|Kalini Silk Blend Festive Lehenga|Brocade with dupatta. Gentle hand wash. Ideal for festive and daily wear.|9499|5199|Cream|XS,S,M,L,XL,XXL|108|281|120",
//            "women_lehenga_cholis|Panash India Floral Printed Lehenga|Organza dupatta, light. Gentle hand wash. Ideal for festive and daily wear.|22899|17399|Green|XS,S,M,L,XL,XXL|230|2429|15",
//            "women_lehenga_cholis|Saree Mall Mirror Work Navratri Lehenga|Gujarati mirror work. Gentle hand wash. Ideal for festive and daily wear.|20199|13899|Peach|XS,S,M,L,XL,XXL|235|1825|9",
//            "women_lehenga_cholis|Mitera Pastel Georgette Lehenga|Engagement-ready pastel. Gentle hand wash. Ideal for festive and daily wear.|10799|5999|Mustard|XS,S,M,L,XL,XXL|223|2723|95",
//            // ---- women_dupattas_shawls
//            "women_dupattas_shawls|FabIndia Phulkari Embroidered Dupatta|Punjabi phulkari. Quality checked. Easy returns.|3599|1399|Beige|FREE|213|102|106",
//            "women_dupattas_shawls|Biba Banarasi Silk Dupatta|Zari woven. Quality checked. Easy returns.|1999|1099|Silver|FREE|180|3463|92",
//            "women_dupattas_shawls|W Pashmina Shawl|Soft woollen shawl. Quality checked. Easy returns.|2899|1699|White|FREE|111|1792|66",
//            "women_dupattas_shawls|Kashmiri Kaarigar Bandhani Chiffon Dupatta|Tie-dye bandhej. Quality checked. Easy returns.|739|329|Silver|FREE|16|2870|45",
//            "women_dupattas_shawls|Weavers Villa Chikankari Georgette Dupatta|Lucknowi embroidery. Quality checked. Easy returns.|1199|669|White|FREE|155|4355|23",
//            "women_dupattas_shawls|Rangriti Kalamkari Cotton Dupatta|Hand-painted motifs. Quality checked. Easy returns.|3299|1299|Maroon|FREE|24|1498|111",
//            // ---- women_jackets
//            "women_jackets|Biba Ethnic Long Shrug Jacket|Printed long jacket. Gentle hand wash. Ideal for festive and daily wear.|2599|1399|Mustard|XS,S,M,L,XL,XXL|177|421|27",
//            "women_jackets|Global Desi Embroidered Short Jacket|Festive bolero. Gentle hand wash. Ideal for festive and daily wear.|2299|859|Gold|XS,S,M,L,XL,XXL|225|3318|101",
//            "women_jackets|W Kantha Work Jacket|Hand kantha stitch. Gentle hand wash. Ideal for festive and daily wear.|6899|2599|Rust|XS,S,M,L,XL,XXL|124|2743|60",
//            "women_jackets|FabIndia Quilted Ethnic Jacket|Winter quilted. Gentle hand wash. Ideal for festive and daily wear.|5199|3099|Pink|XS,S,M,L,XL,XXL|249|4659|21",
//            "women_jackets|Libas Velvet Ethnic Jacket|Rich velvet. Gentle hand wash. Ideal for festive and daily wear.|1699|1299|Navy Blue|XS,S,M,L,XL,XXL|76|1454|64",
//            "women_jackets|Anouk Ikat Waistcoat|Sleeveless ikat. Gentle hand wash. Ideal for festive and daily wear.|4299|2199|Rust|XS,S,M,L,XL,XXL|25|953|57",
//            // ---- women_belts_scarves_more
//            "women_belts_scarves_more|Accessorize Skinny Leather Belt|Gold-tone buckle. Quality checked. Easy returns.|1999|929|Navy Blue|FREE|77|4032|105",
//            "women_belts_scarves_more|Lino Perros Printed Silk Scarf|Square satin scarf. Quality checked. Easy returns.|999|569|Mustard|FREE|215|2948|117",
//            "women_belts_scarves_more|Allen Solly Waist Corset Belt|Statement waist belt. Quality checked. Easy returns.|959|649|Green|FREE|76|3518|43",
//            "women_belts_scarves_more|Global Desi Embroidered Stole|Lightweight cotton stole. Quality checked. Easy returns.|2999|1399|Charcoal|FREE|94|3939|67",
//            "women_belts_scarves_more|Mango Hair Scrunchie Set|Set of 6. Quality checked. Easy returns.|1899|1299|Grey Melange|FREE|117|1607|120",
//            "women_belts_scarves_more|H&M Woollen Winter Scarf|Ribbed knit. Quality checked. Easy returns.|1199|539|Teal|FREE|99|1794|42",
//            // ---- women_watches_wearables
//            "women_watches_wearables|Titan Raga Rose Gold Bracelet Watch|Slim dial, link strap. Quality checked. Easy returns.|8599|5399|Brown|FREE|59|1753|56",
//            "women_watches_wearables|Fossil Leather Strap Analog Watch|Minimal white dial. Quality checked. Easy returns.|4499|2299|Silver|FREE|216|4680|66",
//            "women_watches_wearables|Daniel Wellington Mesh Strap Watch|Ultra slim case. Quality checked. Easy returns.|3499|1699|Beige|FREE|83|4533|3",
//            "women_watches_wearables|Fastrack Smartwatch with AMOLED Display|Health tracking, Bluetooth calling. Quality checked. Easy returns.|10399|7899|Beige|FREE|86|366|41",
//            "women_watches_wearables|Casio Ceramic Analog Watch|Scratch resistant. Quality checked. Easy returns.|3299|1699|Olive|FREE|54|1921|87",
//            "women_watches_wearables|Noise Fitness Band|Steps, sleep and SpO2. Quality checked. Easy returns.|3199|2299|Grey Melange|FREE|120|2043|48",
//            // ---- women_western_wear
//            "women_western_wear|ONLY Smocked Midi Dress|Square neck, puff sleeves. Machine wash cold. Easy 7-day returns.|4299|2299|Mauve|XS,S,M,L,XL|227|4636|26",
//            "women_western_wear|Vero Moda Ribbed Crop Top|Stretch rib knit. Machine wash cold. Easy 7-day returns.|3899|2399|Lavender|XS,S,M,L,XL|200|1292|25",
//            "women_western_wear|Mango High-Rise Wide Leg Trousers|Flowy wide leg. Machine wash cold. Easy 7-day returns.|1099|769|Black|XS,S,M,L,XL|153|2022|26",
//            "women_western_wear|H&M Denim Jacket|Classic blue wash. Machine wash cold. Easy 7-day returns.|2799|1599|Mustard|XS,S,M,L,XL|113|3540|111",
//            "women_western_wear|Zara Satin Shirt|Relaxed satin shirt. Machine wash cold. Easy 7-day returns.|2099|959|Navy Blue|XS,S,M,L,XL|87|179|82",
//            "women_western_wear|SASSAFRAS Knit Co-ord Set|Top and skirt set. Machine wash cold. Easy 7-day returns.|1699|1099|Navy Blue|XS,S,M,L,XL|81|1596|49",
//            // ---- women_dresses
//            "women_dresses|ONLY Floral Wrap Midi Dress|Tie waist wrap dress. Machine wash cold. Easy 7-day returns.|5299|2999|Teal|XS,S,M,L,XL|244|3565|43",
//            "women_dresses|Vero Moda Bodycon Mini Dress|Stretch rib bodycon. Machine wash cold. Easy 7-day returns.|3899|2799|Black|XS,S,M,L,XL|170|2284|4",
//            "women_dresses|Mango Tiered Maxi Dress|Flowy cotton maxi. Machine wash cold. Easy 7-day returns.|8699|3299|Pink|XS,S,M,L,XL|197|2052|119",
//            "women_dresses|Berrylush Satin Slip Dress|Bias cut slip. Machine wash cold. Easy 7-day returns.|6999|2699|Navy Blue|XS,S,M,L,XL|161|150|26",
//            "women_dresses|SASSAFRAS Shirt Dress with Belt|Button-down shirt dress. Machine wash cold. Easy 7-day returns.|4799|2799|Pink|XS,S,M,L,XL|26|2810|35",
//            "women_dresses|Tokyo Talkies Smocked Fit and Flare Dress|Puff sleeves. Machine wash cold. Easy 7-day returns.|1599|1199|Beige|XS,S,M,L,XL|80|2109|33",
//            // ---- women_tops
//            "women_tops|ONLY Puff Sleeve Top|Square neck. Machine wash cold. Easy 7-day returns.|1499|969|Teal|XS,S,M,L,XL|217|3535|60",
//            "women_tops|Vero Moda Off-Shoulder Ruffle Top|Bardot neckline. Machine wash cold. Easy 7-day returns.|2499|1599|Navy Blue|XS,S,M,L,XL|84|449|82",
//            "women_tops|H&M Ribbed Tank Top|Stretch rib. Machine wash cold. Easy 7-day returns.|1099|689|Lavender|XS,S,M,L,XL|83|2910|40",
//            "women_tops|Tokyo Talkies Peplum Top|Flared peplum. Machine wash cold. Easy 7-day returns.|2199|1199|Green|XS,S,M,L,XL|125|251|29",
//            "women_tops|SASSAFRAS Satin Cowl Neck Top|Cowl drape. Machine wash cold. Easy 7-day returns.|1199|689|Lavender|XS,S,M,L,XL|42|4133|91",
//            "women_tops|Mango Corset Style Top|Structured bustier. Machine wash cold. Easy 7-day returns.|2899|1799|Navy Blue|XS,S,M,L,XL|211|2310|3",
//            // ---- women_tshirts
//            "women_tshirts|H&M Oversized Graphic T-Shirt|Boyfriend fit. Machine wash cold. Easy 7-day returns.|1599|799|Coral|XS,S,M,L,XL|184|2139|9",
//            "women_tshirts|Puma Cotton Crew Neck T-Shirt|Regular fit essential. Machine wash cold. Easy 7-day returns.|1099|699|Black|XS,S,M,L,XL|173|3977|79",
//            "women_tshirts|Bewakoof Cropped T-Shirt|Boxy crop. Machine wash cold. Easy 7-day returns.|2499|1299|Rust|XS,S,M,L,XL|242|1171|79",
//            "women_tshirts|ONLY Striped Breton T-Shirt|Classic stripes. Machine wash cold. Easy 7-day returns.|649|379|Beige|XS,S,M,L,XL|245|3625|45",
//            "women_tshirts|Roadster Polo T-Shirt|Pique cotton. Machine wash cold. Easy 7-day returns.|1099|529|Black|XS,S,M,L,XL|247|1352|37",
//            "women_tshirts|Levi's Pack of 2 Basic T-Shirts|Everyday basics. Machine wash cold. Easy 7-day returns.|2299|1099|Navy Blue|XS,S,M,L,XL|24|4800|33",
//            // ---- women_jeans
//            "women_jeans|Levi's High-Rise Skinny Jeans|Super stretch. Machine wash. Easy 7-day returns.|6499|2899|Peach|26,28,30,32,34|181|1113|92",
//            "women_jeans|ONLY Mom Fit Jeans|Relaxed tapered. Machine wash. Easy 7-day returns.|2499|1699|Coral|26,28,30,32,34|247|2342|60",
//            "women_jeans|Vero Moda Wide Leg Jeans|Flowy wide leg. Machine wash. Easy 7-day returns.|2099|849|Mauve|26,28,30,32,34|188|2500|49",
//            "women_jeans|Roadster Straight Fit Jeans|Mid rise classic. Machine wash. Easy 7-day returns.|2799|1599|Rust|26,28,30,32,34|67|1945|88",
//            "women_jeans|Pepe Jeans Flared Bootcut Jeans|Retro flare. Machine wash. Easy 7-day returns.|3399|2399|Green|26,28,30,32,34|106|2915|67",
//            "women_jeans|Wrangler Distressed Boyfriend Jeans|Ripped details. Machine wash. Easy 7-day returns.|2699|1799|Lavender|26,28,30,32,34|187|2209|51",
//            // ---- women_trousers_capris
//            "women_trousers_capris|Marks & Spencer Formal Straight Trousers|Office-ready. Machine wash. Easy 7-day returns.|1899|1299|Maroon|26,28,30,32,34|133|1440|31",
//            "women_trousers_capris|Mango Wide Leg Pleated Trousers|High waist pleats. Machine wash. Easy 7-day returns.|2099|1199|Mauve|26,28,30,32,34|229|417|30",
//            "women_trousers_capris|ONLY Cigarette Trousers|Slim ankle length. Machine wash. Easy 7-day returns.|1699|1199|Coral|26,28,30,32,34|203|397|104",
//            "women_trousers_capris|W Linen Blend Trousers|Breathable. Machine wash. Easy 7-day returns.|3599|1899|Beige|26,28,30,32,34|73|1452|52",
//            "women_trousers_capris|Van Heusen Woman Cotton Capris|Three-fourth length. Machine wash. Easy 7-day returns.|3699|2099|Coral|26,28,30,32,34|144|116|85",
//            "women_trousers_capris|Allen Solly Paperbag Waist Trousers|Tie waist. Machine wash. Easy 7-day returns.|4999|2199|Green|26,28,30,32,34|185|278|84",
//            // ---- women_shorts_skirts
//            "women_shorts_skirts|ONLY Denim Shorts|Frayed hem. Machine wash. Easy 7-day returns.|2899|1599|Maroon|26,28,30,32,34|215|4472|37",
//            "women_shorts_skirts|H&M A-Line Mini Skirt|Button front. Machine wash. Easy 7-day returns.|1999|1299|Mustard|26,28,30,32,34|213|3615|51",
//            "women_shorts_skirts|Mango Pleated Midi Skirt|Satin pleats. Machine wash. Easy 7-day returns.|1999|899|White|26,28,30,32,34|182|3295|56",
//            "women_shorts_skirts|Vero Moda Paperbag Shorts|Linen blend. Machine wash. Easy 7-day returns.|2199|1399|Mauve|26,28,30,32,34|159|1089|63",
//            "women_shorts_skirts|Levi's Denim Skirt|Mid-length denim. Machine wash. Easy 7-day returns.|3199|1799|Rust|26,28,30,32,34|30|771|62",
//            "women_shorts_skirts|Tokyo Talkies Tennis Skirt|Built-in shorts. Machine wash. Easy 7-day returns.|3499|1599|Mustard|26,28,30,32,34|38|4166|54",
//            // ---- women_coords
//            "women_coords|ONLY Printed Shirt and Trousers Co-ord|Relaxed resort set. Machine wash cold. Easy 7-day returns.|3399|1299|Rust|XS,S,M,L,XL|117|4249|111",
//            "women_coords|Vero Moda Knit Crop Top and Skirt Set|Ribbed knit. Machine wash cold. Easy 7-day returns.|4499|1599|Navy Blue|XS,S,M,L,XL|241|268|74",
//            "women_coords|Berrylush Blazer and Shorts Co-ord|Smart casual. Machine wash cold. Easy 7-day returns.|2199|1099|Teal|XS,S,M,L,XL|250|2846|2",
//            "women_coords|Tokyo Talkies Satin Shirt and Pants Set|Lounge-luxe. Machine wash cold. Easy 7-day returns.|1799|1099|Mauve|XS,S,M,L,XL|148|4682|66",
//            "women_coords|SASSAFRAS Linen Co-ord Set|Breathable linen. Machine wash cold. Easy 7-day returns.|2299|1499|Beige|XS,S,M,L,XL|192|1125|29",
//            "women_coords|Mango Tie-Dye Lounge Co-ord|Comfy loungewear. Machine wash cold. Easy 7-day returns.|6099|2299|Lavender|XS,S,M,L,XL|213|4210|20",
//            // ---- women_playsuits
//            "women_playsuits|ONLY Floral Playsuit|Ruffle hem. Machine wash cold. Easy 7-day returns.|2799|1499|White|XS,S,M,L,XL|24|3082|75",
//            "women_playsuits|Berrylush Denim Playsuit|Button front. Machine wash cold. Easy 7-day returns.|1799|859|Mustard|XS,S,M,L,XL|114|4676|35",
//            "women_playsuits|Tokyo Talkies Wrap Playsuit|Tie waist. Machine wash cold. Easy 7-day returns.|2099|959|Maroon|XS,S,M,L,XL|104|1694|15",
//            "women_playsuits|SASSAFRAS Off-Shoulder Playsuit|Bardot neckline. Machine wash cold. Easy 7-day returns.|3499|2199|Beige|XS,S,M,L,XL|43|353|37",
//            "women_playsuits|H&M Linen Playsuit|Breathable. Machine wash cold. Easy 7-day returns.|2999|1999|Green|XS,S,M,L,XL|93|82|74",
//            "women_playsuits|Mango Halter Neck Playsuit|Summer ready. Machine wash cold. Easy 7-day returns.|1699|809|White|XS,S,M,L,XL|230|4471|33",
//            // ---- women_jumpsuits
//            "women_jumpsuits|ONLY Wide Leg Jumpsuit|Belted waist. Machine wash cold. Easy 7-day returns.|2299|1499|Mauve|XS,S,M,L,XL|93|232|103",
//            "women_jumpsuits|Vero Moda Denim Dungaree Jumpsuit|Utility pockets. Machine wash cold. Easy 7-day returns.|4699|2699|Pink|XS,S,M,L,XL|90|1285|119",
//            "women_jumpsuits|Mango Satin Party Jumpsuit|Cowl neck. Machine wash cold. Easy 7-day returns.|3799|2599|Mauve|XS,S,M,L,XL|33|1222|106",
//            "women_jumpsuits|Berrylush Utility Boiler Jumpsuit|Zip front. Machine wash cold. Easy 7-day returns.|2099|1299|Black|XS,S,M,L,XL|180|2105|59",
//            "women_jumpsuits|Harpa Printed Culotte Jumpsuit|Cropped legs. Machine wash cold. Easy 7-day returns.|5199|2799|Rust|XS,S,M,L,XL|177|2502|72",
//            "women_jumpsuits|SASSAFRAS Halter Jumpsuit|Backless detail. Machine wash cold. Easy 7-day returns.|3499|2399|Teal|XS,S,M,L,XL|174|1753|96",
//            // ---- women_shrugs
//            "women_shrugs|ONLY Longline Open Front Shrug|Flowy georgette. Machine wash cold. Easy 7-day returns.|2399|969|Pink|XS,S,M,L,XL|96|999|104",
//            "women_shrugs|Vero Moda Crochet Shrug|Boho crochet. Machine wash cold. Easy 7-day returns.|1299|949|Green|XS,S,M,L,XL|106|4409|70",
//            "women_shrugs|H&M Knit Cardigan Shrug|Soft knit. Machine wash cold. Easy 7-day returns.|3499|1399|Coral|XS,S,M,L,XL|106|4638|59",
//            "women_shrugs|Tokyo Talkies Kimono Shrug|Printed kimono. Machine wash cold. Easy 7-day returns.|1699|1199|Maroon|XS,S,M,L,XL|69|1682|14",
//            "women_shrugs|Global Desi Cropped Shrug|Ribbed crop. Machine wash cold. Easy 7-day returns.|1699|1099|Beige|XS,S,M,L,XL|250|3393|10",
//            "women_shrugs|Mango Lace Shrug|Delicate lace. Machine wash cold. Easy 7-day returns.|1499|819|Maroon|XS,S,M,L,XL|125|2760|117",
//            // ---- women_sweaters_sweatshirts
//            "women_sweaters_sweatshirts|H&M Oversized Hoodie|Brushed fleece. Machine wash cold. Easy 7-day returns.|1799|1299|Black|XS,S,M,L,XL|169|1378|105",
//            "women_sweaters_sweatshirts|ONLY Cable Knit Sweater|Chunky knit. Machine wash cold. Easy 7-day returns.|6299|2199|White|XS,S,M,L,XL|65|1212|70",
//            "women_sweaters_sweatshirts|Puma Cropped Sweatshirt|Boxy fit. Machine wash cold. Easy 7-day returns.|2599|1199|Navy Blue|XS,S,M,L,XL|185|756|116",
//            "women_sweaters_sweatshirts|Roadster Turtleneck Sweater|Rib knit. Machine wash cold. Easy 7-day returns.|2599|1399|Beige|XS,S,M,L,XL|169|1270|32",
//            "women_sweaters_sweatshirts|Vero Moda Button Cardigan|V-neck cardigan. Machine wash cold. Easy 7-day returns.|3699|1699|Peach|XS,S,M,L,XL|80|939|9",
//            "women_sweaters_sweatshirts|Mango Graphic Sweatshirt|Bold print. Machine wash cold. Easy 7-day returns.|2899|1899|Navy Blue|XS,S,M,L,XL|183|1777|120",
//            // ---- women_jackets_coats
//            "women_jackets_coats|ONLY Puffer Jacket|Quilted, hooded. Machine wash cold. Easy 7-day returns.|7399|5299|Peach|XS,S,M,L,XL|240|4537|43",
//            "women_jackets_coats|Vero Moda Trench Coat|Belted classic. Machine wash cold. Easy 7-day returns.|4399|2199|Green|XS,S,M,L,XL|233|2794|56",
//            "women_jackets_coats|Mango Denim Jacket|Oversized wash. Machine wash cold. Easy 7-day returns.|7199|2799|Teal|XS,S,M,L,XL|156|2955|13",
//            "women_jackets_coats|Zara Faux Leather Jacket|Biker style. Machine wash cold. Easy 7-day returns.|9799|4899|Black|XS,S,M,L,XL|60|4178|74",
//            "women_jackets_coats|H&M Wool Blend Overcoat|Longline coat. Machine wash cold. Easy 7-day returns.|8199|4299|Teal|XS,S,M,L,XL|156|1638|92",
//            "women_jackets_coats|Roadster Teddy Fleece Jacket|Sherpa fleece. Machine wash cold. Easy 7-day returns.|7699|2999|Green|XS,S,M,L,XL|249|4608|83",
//            // ---- women_blazers_waistcoats
//            "women_blazers_waistcoats|Mango Single-Breasted Blazer|Tailored fit. Machine wash cold. Easy 7-day returns.|8399|4699|Black|XS,S,M,L,XL|162|1248|94",
//            "women_blazers_waistcoats|ONLY Oversized Boyfriend Blazer|Relaxed shoulders. Machine wash cold. Easy 7-day returns.|3599|2599|Black|XS,S,M,L,XL|141|2030|48",
//            "women_blazers_waistcoats|Vero Moda Double-Breasted Blazer|Gold buttons. Machine wash cold. Easy 7-day returns.|3499|1599|Black|XS,S,M,L,XL|166|1970|110",
//            "women_blazers_waistcoats|Van Heusen Woman Linen Blend Blazer|Unlined summer blazer. Machine wash cold. Easy 7-day returns.|2999|1699|Black|XS,S,M,L,XL|44|3502|86",
//            "women_blazers_waistcoats|Allen Solly Tailored Waistcoat|V-neck vest. Machine wash cold. Easy 7-day returns.|7899|4499|Lavender|XS,S,M,L,XL|148|3173|66",
//            "women_blazers_waistcoats|Marks & Spencer Checked Blazer|Houndstooth check. Machine wash cold. Easy 7-day returns.|3199|2299|Peach|XS,S,M,L,XL|154|950|7",
//            // ---- women_plus_size
//            "women_plus_size|Instafab Plus Plus Size Wrap Dress|Flattering wrap. Designed for a comfortable plus-size fit.|3099|2199|Mauve|XXL,3XL,4XL|243|4689|88",
//            "women_plus_size|ONLY Carmakoma Plus Size Straight Kurta|Comfort cotton. Designed for a comfortable plus-size fit.|4299|2999|Pink|XXL,3XL,4XL|230|748|10",
//            "women_plus_size|Aurelia Curve Plus Size High-Rise Jeans|Stretch denim. Designed for a comfortable plus-size fit.|3899|2999|Mustard|XXL,3XL,4XL|247|2786|43",
//            "women_plus_size|Libas Plus Plus Size Tunic Top|Relaxed tunic. Designed for a comfortable plus-size fit.|3899|1599|Pink|XXL,3XL,4XL|38|1915|54",
//            "women_plus_size|Mango Plus Plus Size Palazzo|Wide leg. Designed for a comfortable plus-size fit.|2799|1199|Mauve|XXL,3XL,4XL|33|1986|80",
//            "women_plus_size|H&M+ Plus Size Shirt|Button-down. Designed for a comfortable plus-size fit.|5399|2299|Maroon|XXL,3XL,4XL|64|4503|91",
//            // ---- women_footwear
//            "women_footwear|Metro Block Heel Sandals|Ankle strap, cushioned. Cushioned insole for all-day comfort.|5099|2699|Navy Blue|3,4,5,6,7,8|44|3209|92",
//            "women_footwear|Mochi Embellished Ethnic Flats|Juttis with stones. Cushioned insole for all-day comfort.|3899|2899|White|3,4,5,6,7,8|105|512|52",
//            "women_footwear|Catwalk Casual Sneakers|White lace-ups. Cushioned insole for all-day comfort.|3499|1299|Beige|3,4,5,6,7,8|238|4020|88",
//            "women_footwear|Bata Platform Slides|Chunky sole slides. Cushioned insole for all-day comfort.|2299|1799|Black|3,4,5,6,7,8|190|1470|44",
//            "women_footwear|Inc.5 Wedge Sandals|Comfort wedges. Cushioned insole for all-day comfort.|7899|2999|Navy Blue|3,4,5,6,7,8|160|4378|47",
//            "women_footwear|Crocs Classic Clogs|Lightweight clogs. Cushioned insole for all-day comfort.|1799|1099|Brown|3,4,5,6,7,8|162|992|108",
//            // ---- women_flats
//            "women_flats|Metro Embroidered Juttis|Handcrafted juttis. Cushioned insole for all-day comfort.|2099|1299|Navy Blue|3,4,5,6,7,8|220|4371|51",
//            "women_flats|Mochi Ballet Flats|Pointed toe. Cushioned insole for all-day comfort.|1099|789|Coffee Brown|3,4,5,6,7,8|152|1861|35",
//            "women_flats|Catwalk Kolhapuri Flats|Leather kolhapuris. Cushioned insole for all-day comfort.|2299|1599|Coffee Brown|3,4,5,6,7,8|203|712|61",
//            "women_flats|Bata Strappy Flat Sandals|Gladiator straps. Cushioned insole for all-day comfort.|1099|769|Coffee Brown|3,4,5,6,7,8|217|1860|30",
//            "women_flats|Carlton London Mules|Slip-on mules. Cushioned insole for all-day comfort.|3199|1199|Brown|3,4,5,6,7,8|132|2166|12",
//            "women_flats|Inc.5 Bow Detail Flats|Satin bow. Cushioned insole for all-day comfort.|1399|1099|Coffee Brown|3,4,5,6,7,8|31|3999|29",
//            // ---- women_casual_shoes
//            "women_casual_shoes|Skechers White Sneakers|Minimal leather. Cushioned insole for all-day comfort.|4999|3099|White|3,4,5,6,7,8|222|3974|99",
//            "women_casual_shoes|Puma Slip-On Canvas Shoes|Lightweight. Cushioned insole for all-day comfort.|2199|1499|Grey|3,4,5,6,7,8|15|1459|36",
//            "women_casual_shoes|Adidas Loafers|Penny loafers. Cushioned insole for all-day comfort.|4999|3299|Brown|3,4,5,6,7,8|181|637|116",
//            "women_casual_shoes|Bata Chunky Sneakers|Retro sole. Cushioned insole for all-day comfort.|3899|2599|Navy Blue|3,4,5,6,7,8|88|795|27",
//            "women_casual_shoes|Red Tape Memory Foam Walking Shoes|All-day comfort. Cushioned insole for all-day comfort.|1699|839|Grey|3,4,5,6,7,8|202|3425|21",
//            "women_casual_shoes|Roadster Espadrilles|Jute sole. Cushioned insole for all-day comfort.|5499|2399|White|3,4,5,6,7,8|66|2578|88",
//            // ---- women_heels
//            "women_heels|Metro Stiletto Pumps|Pointed toe pumps. Cushioned insole for all-day comfort.|3799|2499|Navy Blue|3,4,5,6,7,8|84|2111|115",
//            "women_heels|Mochi Block Heel Mules|Square toe. Cushioned insole for all-day comfort.|6499|3299|Brown|3,4,5,6,7,8|232|2044|58",
//            "women_heels|Inc.5 Kitten Heels|Low slingback. Cushioned insole for all-day comfort.|2299|1399|Black|3,4,5,6,7,8|247|3076|64",
//            "women_heels|Catwalk Platform Heels|Party platforms. Cushioned insole for all-day comfort.|6599|2499|Tan|3,4,5,6,7,8|174|522|105",
//            "women_heels|Carlton London Embellished Wedges|Ethnic wedges. Cushioned insole for all-day comfort.|8999|3299|White|3,4,5,6,7,8|93|724|21",
//            "women_heels|Steve Madden Strappy Heeled Sandals|Barely-there straps. Cushioned insole for all-day comfort.|6699|2599|Tan|3,4,5,6,7,8|230|125|26",
//            // ---- women_boots
//            "women_boots|Mochi Ankle Chelsea Boots|Elastic gussets. Cushioned insole for all-day comfort.|2999|2299|Brown|3,4,5,6,7,8|130|4228|40",
//            "women_boots|Metro Knee-High Boots|Block heel. Cushioned insole for all-day comfort.|6099|4699|White|3,4,5,6,7,8|189|2536|40",
//            "women_boots|Bata Combat Boots|Lace-up chunky. Cushioned insole for all-day comfort.|4699|2499|Navy Blue|3,4,5,6,7,8|92|4536|113",
//            "women_boots|Carlton London Suede Heeled Booties|Pointed toe. Cushioned insole for all-day comfort.|11699|4199|Coffee Brown|3,4,5,6,7,8|80|4357|45",
//            "women_boots|Inc.5 Winter Snow Boots|Faux fur lined. Cushioned insole for all-day comfort.|6399|3099|Brown|3,4,5,6,7,8|58|211|53",
//            "women_boots|Woodland Western Cowboy Boots|Stitch detailing. Cushioned insole for all-day comfort.|7099|4299|White|3,4,5,6,7,8|123|1155|53",
//            // ---- women_sports_shoes_floaters
//            "women_sports_shoes_floaters|Nike Running Shoes|Cushioned foam. Cushioned insole for all-day comfort.|6899|3199|Tan|3,4,5,6,7,8|111|3385|37",
//            "women_sports_shoes_floaters|Adidas Training Shoes|Stable base. Cushioned insole for all-day comfort.|3999|2599|Brown|3,4,5,6,7,8|205|4080|35",
//            "women_sports_shoes_floaters|Puma Walking Shoes|Memory foam. Cushioned insole for all-day comfort.|4799|2099|Coffee Brown|3,4,5,6,7,8|178|2889|93",
//            "women_sports_shoes_floaters|Skechers Sports Floaters|Adjustable straps. Cushioned insole for all-day comfort.|3199|1999|Black|3,4,5,6,7,8|156|657|77",
//            "women_sports_shoes_floaters|Asics Knit Slip-On Trainers|Sock-like fit. Cushioned insole for all-day comfort.|4699|2899|Brown|3,4,5,6,7,8|158|3698|8",
//            "women_sports_shoes_floaters|Crocs Trail Shoes|Grippy outsole. Cushioned insole for all-day comfort.|3699|2699|Beige|3,4,5,6,7,8|121|1279|86",
//            // ---- women_sports_active_wear
//            "women_sports_active_wear|Nike High Support Sports Bra|Removable pads. Machine wash cold. Easy 7-day returns.|3599|1799|Maroon|XS,S,M,L,XL|23|4216|72",
//            "women_sports_active_wear|Adidas High-Waist Yoga Leggings|Squat-proof. Machine wash cold. Easy 7-day returns.|1899|689|Mustard|XS,S,M,L,XL|115|954|22",
//            "women_sports_active_wear|Puma Training Tank Top|Racerback. Machine wash cold. Easy 7-day returns.|1999|1599|Green|XS,S,M,L,XL|144|2612|115",
//            "women_sports_active_wear|HRX Running Shorts|Inner brief. Machine wash cold. Easy 7-day returns.|2999|1799|Pink|XS,S,M,L,XL|102|3192|66",
//            "women_sports_active_wear|Decathlon Domyos Seamless Workout Set|Bra and leggings. Machine wash cold. Easy 7-day returns.|3699|1399|Mustard|XS,S,M,L,XL|144|873|77",
//            "women_sports_active_wear|Reebok Zip-Up Training Jacket|Lightweight. Machine wash cold. Easy 7-day returns.|5499|2499|Peach|XS,S,M,L,XL|75|3793|45",
//            // ---- women_clothing
//            "women_clothing|Puma Dri-Fit Training T-Shirt|Sweat-wicking. Machine wash cold. Easy 7-day returns.|1999|1499|Lavender|XS,S,M,L,XL|133|4213|39",
//            "women_clothing|Adidas Flared Yoga Pants|Bootcut yoga. Machine wash cold. Easy 7-day returns.|919|519|Beige|XS,S,M,L,XL|146|3740|34",
//            "women_clothing|Nike Joggers|Tapered fleece. Machine wash cold. Easy 7-day returns.|3399|1999|Beige|XS,S,M,L,XL|47|2442|97",
//            "women_clothing|HRX Hoodie|Brushed fleece. Machine wash cold. Easy 7-day returns.|2399|1599|Pink|XS,S,M,L,XL|50|4719|45",
//            "women_clothing|Decathlon Kalenji Cycling Shorts|Bike shorts. Machine wash cold. Easy 7-day returns.|2199|1599|Peach|XS,S,M,L,XL|65|3249|32",
//            "women_clothing|Under Armour Track Jacket|Full zip. Machine wash cold. Easy 7-day returns.|1299|919|White|XS,S,M,L,XL|77|1030|76",
//            // ---- women_sports_accessories
//            "women_sports_accessories|Nike Yoga Mat 6mm|Anti-slip. Quality checked. Easy returns.|4299|1799|Charcoal|FREE|87|4631|58",
//            "women_sports_accessories|Adidas Gym Bag|Wet pocket. Quality checked. Easy returns.|1599|759|Khaki|FREE|219|178|60",
//            "women_sports_accessories|Puma Water Bottle 1L|Time marker. Quality checked. Easy returns.|1799|1099|Black|FREE|89|4010|117",
//            "women_sports_accessories|Decathlon Domyos Hair Bands Pack|Sports headbands. Quality checked. Easy returns.|4499|1899|Mustard|FREE|23|2634|80",
//            "women_sports_accessories|Boldfit Gym Gloves|Padded grip. Quality checked. Easy returns.|799|459|Silver|FREE|142|3299|25",
//            "women_sports_accessories|Strauss Resistance Loop Bands|Set of 5. Quality checked. Easy returns.|2899|1099|Khaki|FREE|234|2026|98",
//            // ---- women_sports_equipment
//            "women_sports_equipment|Strauss Dumbbell Set 2x2kg|Neoprene coated. Quality checked. Easy returns.|5699|2699|Grey Melange|FREE|77|3198|8",
//            "women_sports_equipment|Boldfit Skipping Rope|Adjustable. Quality checked. Easy returns.|3999|2599|Olive|FREE|234|2696|10",
//            "women_sports_equipment|Decathlon Domyos Ab Roller Wheel|Knee pad included. Quality checked. Easy returns.|4099|2099|Black|FREE|56|390|0",
//            "women_sports_equipment|Cosco Yoga Block and Strap Set|Eco foam. Quality checked. Easy returns.|2799|1299|Khaki|FREE|134|63|23",
//            "women_sports_equipment|Nivia Kettlebell 6kg|Vinyl coated. Quality checked. Easy returns.|3799|2999|Grey Melange|FREE|62|3666|65",
//            "women_sports_equipment|Kore Badminton Racket Set|Two rackets with shuttles. Quality checked. Easy returns.|1899|1099|Charcoal|FREE|248|1001|92",
//            // ---- women_lingerie_sleepwear
//            "women_lingerie_sleepwear|Zivame Lace Bralette Set|Bralette and brief. Soft on skin. Machine wash.|1299|579|Teal|S,M,L,XL,XXL|43|3114|82",
//            "women_lingerie_sleepwear|Clovia Satin Nightdress|Short satin slip. Soft on skin. Machine wash.|1499|989|Olive|S,M,L,XL,XXL|178|834|101",
//            "women_lingerie_sleepwear|Amante Cotton Pyjama Set|Printed tee and pyjama. Soft on skin. Machine wash.|899|619|Maroon|S,M,L,XL,XXL|172|4091|39",
//            "women_lingerie_sleepwear|Jockey Robe Set|Satin robe with nightie. Soft on skin. Machine wash.|1499|759|Navy Blue|S,M,L,XL,XXL|82|1941|25",
//            "women_lingerie_sleepwear|Triumph T-Shirt Bra Pack of 2|Seamless. Soft on skin. Machine wash.|3699|1399|Maroon|S,M,L,XL,XXL|48|3431|92",
//            "women_lingerie_sleepwear|Enamor Lounge Shorts Set|Soft modal. Soft on skin. Machine wash.|2799|1899|Mustard|S,M,L,XL,XXL|42|1597|62",
//            // ---- women_bra
//            "women_bra|Jockey T-Shirt Bra|Seamless moulded cups. Soft on skin. Machine wash.|639|359|Black|S,M,L,XL,XXL|119|3580|96",
//            "women_bra|Enamor Sports Bra|Medium support. Soft on skin. Machine wash.|3599|1399|Black|S,M,L,XL,XXL|231|4095|32",
//            "women_bra|Triumph Push-Up Bra|Padded underwire. Soft on skin. Machine wash.|3499|1199|Mustard|S,M,L,XL,XXL|60|635|5",
//            "women_bra|Zivame Bralette|Lace bralette. Soft on skin. Machine wash.|3599|1299|Grey Melange|S,M,L,XL,XXL|244|2485|96",
//            "women_bra|Clovia Minimiser Bra|Full coverage. Soft on skin. Machine wash.|1299|989|Teal|S,M,L,XL,XXL|107|529|49",
//            "women_bra|Amante Strapless Bra|Silicone grip. Soft on skin. Machine wash.|4199|1499|Beige|S,M,L,XL,XXL|204|3618|120",
//            // ---- women_briefs
//            "women_briefs|Jockey Pack of 3 Hipster Briefs|Cotton stretch. Soft on skin. Machine wash.|439|299|Navy Blue|S,M,L,XL,XXL|222|1848|119",
//            "women_briefs|Zivame Bikini Briefs Pack of 5|Everyday. Soft on skin. Machine wash.|949|549|Teal|S,M,L,XL,XXL|162|3144|3",
//            "women_briefs|Clovia Seamless Briefs|No panty lines. Soft on skin. Machine wash.|1399|539|White|S,M,L,XL,XXL|33|962|74",
//            "women_briefs|Enamor High-Waist Briefs|Tummy coverage. Soft on skin. Machine wash.|1799|949|White|S,M,L,XL,XXL|194|1260|83",
//            "women_briefs|Amante Lace Briefs|Delicate lace. Soft on skin. Machine wash.|399|299|Navy Blue|S,M,L,XL,XXL|57|619|94",
//            "women_briefs|Van Heusen Woman Boyshorts Pack of 3|Full coverage. Soft on skin. Machine wash.|1299|949|Beige|S,M,L,XL,XXL|195|2681|28",
//            // ---- women_shapewear
//            "women_shapewear|Zivame Tummy Tucker|High compression. Soft on skin. Machine wash.|2999|1299|Beige|S,M,L,XL,XXL|179|2961|59",
//            "women_shapewear|Clovia Thigh Shaper|Anti-chafe. Soft on skin. Machine wash.|2899|1999|Olive|S,M,L,XL,XXL|196|4799|46",
//            "women_shapewear|Triumph Saree Shapewear|Flared petticoat shaper. Soft on skin. Machine wash.|1499|729|White|S,M,L,XL,XXL|134|2668|56",
//            "women_shapewear|Amante Body Suit Shaper|Full body. Soft on skin. Machine wash.|2099|1499|Navy Blue|S,M,L,XL,XXL|159|174|33",
//            "women_shapewear|Jockey Waist Cincher|Hourglass shape. Soft on skin. Machine wash.|2099|999|Grey Melange|S,M,L,XL,XXL|246|3314|60",
//            "women_shapewear|Swee Shaping Camisole|Smoothing cami. Soft on skin. Machine wash.|2499|1899|Navy Blue|S,M,L,XL,XXL|250|3623|109",
//            // ---- women_sleepwear_loungewear
//            "women_sleepwear_loungewear|Clovia Cotton Night Suit|Button-down set. Soft on skin. Machine wash.|3099|1799|Maroon|S,M,L,XL,XXL|109|4323|39",
//            "women_sleepwear_loungewear|Zivame Satin Pyjama Set|Silky set. Soft on skin. Machine wash.|2899|1299|Grey Melange|S,M,L,XL,XXL|176|2510|81",
//            "women_sleepwear_loungewear|Jockey Nightgown|Long cotton nightie. Soft on skin. Machine wash.|1199|899|Grey Melange|S,M,L,XL,XXL|225|4733|107",
//            "women_sleepwear_loungewear|H&M Lounge Joggers Set|Comfy fleece. Soft on skin. Machine wash.|4599|1899|Navy Blue|S,M,L,XL,XXL|137|2154|4",
//            "women_sleepwear_loungewear|Sweet Dreams Printed Shorts Set|Summer sleep set. Soft on skin. Machine wash.|799|619|Grey Melange|S,M,L,XL,XXL|235|3104|46",
//            "women_sleepwear_loungewear|Nite Flite Flannel Pyjamas|Warm checks. Soft on skin. Machine wash.|1599|1199|White|S,M,L,XL,XXL|73|3141|39",
//            // ---- women_swimwear
//            "women_swimwear|Speedo One-Piece Swimsuit|Chlorine resistant. Soft on skin. Machine wash.|1899|1199|Grey Melange|S,M,L,XL,XXL|36|3508|48",
//            "women_swimwear|Arena Bikini Set|Triangle top. Soft on skin. Machine wash.|4399|1699|Grey Melange|S,M,L,XL,XXL|98|2922|81",
//            "women_swimwear|Decathlon Nabaiji Swim Dress|Skirted swimwear. Soft on skin. Machine wash.|4099|2899|Mustard|S,M,L,XL,XXL|31|3288|23",
//            "women_swimwear|Zivame Rash Guard Set|UPF 50+. Soft on skin. Machine wash.|4799|2399|White|S,M,L,XL,XXL|52|1831|93",
//            "women_swimwear|Clovia Tankini Set|Two-piece. Soft on skin. Machine wash.|2499|1799|Black|S,M,L,XL,XXL|232|996|8",
//            "women_swimwear|Shecup Cover-Up Kaftan|Beach cover-up. Soft on skin. Machine wash.|2499|1499|Maroon|S,M,L,XL,XXL|88|3644|8",
//            // ---- women_camisoles_thermals
//            "women_camisoles_thermals|Jockey Cotton Camisole Pack of 2|Adjustable straps. Soft on skin. Machine wash.|1599|1099|Black|S,M,L,XL,XXL|155|1213|0",
//            "women_camisoles_thermals|Lux Cottswool Thermal Top|Brushed warmth. Soft on skin. Machine wash.|759|539|Olive|S,M,L,XL,XXL|47|3395|93",
//            "women_camisoles_thermals|Zivame Thermal Leggings|Winter warmth. Soft on skin. Machine wash.|1699|699|Grey Melange|S,M,L,XL,XXL|141|74|0",
//            "women_camisoles_thermals|Clovia Lace Trim Camisole|Delicate trim. Soft on skin. Machine wash.|1599|1199|Charcoal|S,M,L,XL,XXL|16|4190|35",
//            "women_camisoles_thermals|Dollar Missy Shelf Bra Camisole|Built-in support. Soft on skin. Machine wash.|1699|719|Beige|S,M,L,XL,XXL|164|3268|14",
//            "women_camisoles_thermals|Rupa Thermal Set|Top and bottom. Soft on skin. Machine wash.|3099|1299|Charcoal|S,M,L,XL,XXL|41|3332|106",
//            // ---- women_maternity
//            "women_maternity|Momsoon Maternity Feeding Kurti|Hidden zip nursing access. Machine wash cold. Easy 7-day returns.|1899|819|Beige|XS,S,M,L,XL|104|3962|17",
//            "women_maternity|Mine4Nine Maternity Dress|Stretch empire waist. Machine wash cold. Easy 7-day returns.|2399|1399|White|XS,S,M,L,XL|60|72|68",
//            "women_maternity|Morph Maternity Jeans|Over-belly stretch panel. Machine wash cold. Easy 7-day returns.|1699|889|Green|XS,S,M,L,XL|181|978|103",
//            "women_maternity|H&M Mama Nursing Night Suit|Easy feeding. Machine wash cold. Easy 7-day returns.|3199|2199|Beige|XS,S,M,L,XL|143|1017|112",
//            "women_maternity|Clovia Maternity Leggings|Adjustable waistband. Machine wash cold. Easy 7-day returns.|2899|1599|Rust|XS,S,M,L,XL|197|1249|74",
//            "women_maternity|Zivame Maternity Co-ord Set|Comfort lounge set. Machine wash cold. Easy 7-day returns.|3599|2499|Lavender|XS,S,M,L,XL|20|118|91",
//            // ---- women_sunglasses_frames
//            "women_sunglasses_frames|Ray-Ban Cat-Eye Sunglasses|UV400 protection. Quality checked. Easy returns.|12799|5599|Maroon|FREE|97|3536|14",
//            "women_sunglasses_frames|Fastrack Oversized Square Sunglasses|Gradient lenses. Quality checked. Easy returns.|2799|1499|Silver|FREE|158|3687|102",
//            "women_sunglasses_frames|Vincent Chase Round Metal Sunglasses|Retro gold frame. Quality checked. Easy returns.|1399|819|Brown|FREE|17|2504|66",
//            "women_sunglasses_frames|IDEE Aviator Sunglasses|Polarised. Quality checked. Easy returns.|9299|6199|White|FREE|22|3555|29",
//            "women_sunglasses_frames|Carlton London Butterfly Sunglasses|Statement frame. Quality checked. Easy returns.|16199|6299|Gold|FREE|158|28|49",
//            "women_sunglasses_frames|Lenskart Air Transparent Eyeglass Frames|Blue-cut lens ready. Quality checked. Easy returns.|4999|2499|Olive|FREE|24|2918|11",
//            // ---- women_beauty_personal_care
//            "women_beauty_personal_care|Nykaa Hair Straightener|Ceramic plates, quick heat. Quality checked. Easy returns.|519|359|Charcoal|FREE|50|3373|120",
//            "women_beauty_personal_care|Lakme Body Lotion 400ml|Deep moisture. Quality checked. Easy returns.|1299|799|Charcoal|FREE|234|3426|10",
//            "women_beauty_personal_care|Mamaearth Bath and Body Gift Set|Shower gel, scrub, lotion. Quality checked. Easy returns.|3499|1299|Green|FREE|189|4733|32",
//            "women_beauty_personal_care|The Body Shop Hair Dryer 1200W|Cool shot. Quality checked. Easy returns.|2499|1599|Teal|FREE|143|2631|46",
//            "women_beauty_personal_care|Dove Nail Care Kit|Manicure set. Quality checked. Easy returns.|2899|1999|Navy Blue|FREE|112|994|73",
//            "women_beauty_personal_care|Philips Onion Hair Oil 250ml|Reduces hair fall. Quality checked. Easy returns.|979|369|Gold|FREE|135|4651|63",
//            // ---- women_makeup
//            "women_makeup|Maybelline Fit Me Matte Foundation|Natural matte finish. Quality checked. Easy returns.|1599|1199|Khaki|FREE|186|2045|73",
//            "women_makeup|Lakme Kajal Eyeliner|Smudge proof. Quality checked. Easy returns.|1399|669|Gold|FREE|167|2502|119",
//            "women_makeup|Nykaa Eyeshadow Palette 12 Shades|Matte and shimmer. Quality checked. Easy returns.|1099|759|Beige|FREE|220|3405|1",
//            "women_makeup|SUGAR Compact Powder|Oil control. Quality checked. Easy returns.|899|579|Brown|FREE|172|485|93",
//            "women_makeup|M.A.C Lash Sensational Mascara|Volumising. Quality checked. Easy returns.|4399|1699|Teal|FREE|37|3318|41",
//            "women_makeup|Swiss Beauty Makeup Brush Set of 10|Soft synthetic. Quality checked. Easy returns.|1699|1299|Gold|FREE|216|3508|0",
//            // ---- women_skincare
//            "women_skincare|Minimalist 10% Niacinamide Serum|Blemish control. Quality checked. Easy returns.|2899|1399|Olive|FREE|152|2325|113",
//            "women_skincare|Cetaphil Gentle Skin Cleanser|For all skin types. Quality checked. Easy returns.|1499|679|Grey Melange|FREE|17|1817|62",
//            "women_skincare|The Derma Co Sunscreen SPF 50|No white cast. Quality checked. Easy returns.|809|299|Beige|FREE|224|459|49",
//            "women_skincare|Plum Hyaluronic Acid Moisturiser|Hydrating. Quality checked. Easy returns.|839|469|Teal|FREE|223|1604|118",
//            "women_skincare|Mamaearth Vitamin C Face Wash|Brightening. Quality checked. Easy returns.|799|479|Khaki|FREE|155|1747|89",
//            "women_skincare|Neutrogena Sheet Mask Pack of 5|Glow boost. Quality checked. Easy returns.|1499|689|Khaki|FREE|32|3776|118",
//            // ---- women_premium_beauty
//            "women_premium_beauty|Estee Lauder Advanced Night Repair Serum|Overnight recovery. Quality checked. Easy returns.|21799|8900|Maroon|FREE|93|1515|117",
//            "women_premium_beauty|Clinique Moisture Surge Gel Cream|72h hydration. Quality checked. Easy returns.|5099|3500|Navy Blue|FREE|32|4552|85",
//            "women_premium_beauty|M.A.C Studio Fix Foundation|Full coverage. Quality checked. Easy returns.|9799|3900|Mustard|FREE|174|1802|0",
//            "women_premium_beauty|Kiehl's Ultra Facial Cream|Lightweight daily. Quality checked. Easy returns.|3899|2900|Beige|FREE|19|483|120",
//            "women_premium_beauty|Charlotte Tilbury Pillow Talk Lipstick|Iconic nude. Quality checked. Easy returns.|6499|3200|Navy Blue|FREE|230|3354|33",
//            "women_premium_beauty|Huda Beauty Eyeshadow Palette|Highly pigmented. Quality checked. Easy returns.|5899|2299|Maroon|FREE|229|4499|114",
//            // ---- women_lipsticks
//            "women_lipsticks|Maybelline SuperStay Matte Ink Liquid Lipstick|16h wear. Quality checked. Easy returns.|2599|1699|Navy Blue|FREE|186|4782|96",
//            "women_lipsticks|Lakme 9to5 Primer Matte Lipstick|Smooth matte. Quality checked. Easy returns.|4499|1999|Maroon|FREE|52|3546|19",
//            "women_lipsticks|SUGAR Matte As Hell Crayon Lipstick|Long lasting. Quality checked. Easy returns.|1099|679|Brown|FREE|119|506|77",
//            "women_lipsticks|M.A.C Retro Matte Lipstick|Classic red. Quality checked. Easy returns.|3899|1999|Teal|FREE|26|1699|66",
//            "women_lipsticks|Nykaa Lip Gloss Set of 3|High shine. Quality checked. Easy returns.|1599|1199|Beige|FREE|61|534|115",
//            "women_lipsticks|Kay Beauty Tinted Lip Balm|SPF 15. Quality checked. Easy returns.|3399|1699|Gold|FREE|36|135|32",
//            // ---- women_fragrances
//            "women_fragrances|Bella Vita Eau de Parfum 100ml|Floral fruity. Quality checked. Easy returns.|5399|4099|Olive|FREE|175|2699|15",
//            "women_fragrances|Skinn by Titan Body Mist 250ml|Vanilla bliss. Quality checked. Easy returns.|6999|4699|Gold|FREE|249|1551|27",
//            "women_fragrances|Fogg Luxury Perfume Gift Set|4 x 20ml. Quality checked. Easy returns.|649|439|Brown|FREE|235|2255|29",
//            "women_fragrances|Victoria's Secret Rose Eau de Toilette 50ml|Fresh rose. Quality checked. Easy returns.|6199|3999|Grey Melange|FREE|56|982|56",
//            "women_fragrances|Engage Roll-On Perfume Oil|Alcohol free. Quality checked. Easy returns.|7199|3499|Olive|FREE|54|1781|44",
//            "women_fragrances|Plum Pocket Perfume Pack of 3|Travel size. Quality checked. Easy returns.|3799|1899|Charcoal|FREE|109|3083|115",
//            // ---- women_gadgets
//            "women_gadgets|Philips Hair Straightener Brush|Ionic, 30 sec heat-up. 1 year manufacturer warranty.|2099|1399|Gold|FREE|219|2183|39",
//            "women_gadgets|Dyson Facial Cleansing Brush|Waterproof, rechargeable. 1 year manufacturer warranty.|8799|6099|Silver|FREE|164|61|102",
//            "women_gadgets|Nova Bikini Trimmer|Cordless. 1 year manufacturer warranty.|4099|3399|Black|FREE|186|3991|9",
//            "women_gadgets|Noise Hair Curler|Ceramic barrel. 1 year manufacturer warranty.|8199|6899|Black|FREE|46|2196|8",
//            "women_gadgets|boAt Smart Scale|Body composition. 1 year manufacturer warranty.|5399|4799|White|FREE|218|3118|2",
//            "women_gadgets|Agaro Electric Massager|Neck and shoulder. 1 year manufacturer warranty.|11199|7999|White|FREE|117|1821|75",
//            // ---- women_smart_wearables
//            "women_smart_wearables|Apple Watch SE GPS 40mm|Crash detection. 1 year manufacturer warranty.|42199|29900|White|FREE|244|4150|118",
//            "women_smart_wearables|Samsung Galaxy Watch6 40mm|Sleep coaching. 1 year manufacturer warranty.|32699|29999|Green|FREE|39|2435|15",
//            "women_smart_wearables|Noise ColorFit Pristine Smartwatch|Metal strap, AMOLED. 1 year manufacturer warranty.|4099|3499|Gold|FREE|248|2991|120",
//            "women_smart_wearables|boAt Lunar Pro Smartwatch|Round AMOLED. 1 year manufacturer warranty.|4299|2999|Gold|FREE|55|2337|15",
//            "women_smart_wearables|Fire-Boltt Emerald Smartwatch|Jewel-style strap. 1 year manufacturer warranty.|2999|2499|White|FREE|45|4104|26",
//            "women_smart_wearables|Fastrack Reflex Play Smartwatch|Fitness tracking. 1 year manufacturer warranty.|4099|2999|Green|FREE|248|3980|26",
//            // ---- women_fitness_gadgets
//            "women_fitness_gadgets|Fitbit Inspire 3 Fitness Tracker|Heart rate, sleep. 1 year manufacturer warranty.|9799|8999|Grey|FREE|84|2651|107",
//            "women_fitness_gadgets|Xiaomi Smart Band 8|16-day battery. 1 year manufacturer warranty.|4299|3499|Blue|FREE|214|2444|32",
//            "women_fitness_gadgets|Noise Smart Body Fat Scale|App sync. 1 year manufacturer warranty.|5599|4999|Silver|FREE|132|3336|107",
//            "women_fitness_gadgets|Omron Digital Skipping Rope|Counter. 1 year manufacturer warranty.|3399|2299|Green|FREE|42|2358|35",
//            "women_fitness_gadgets|Dr Trust Fitness Band with SpO2|Stress tracking. 1 year manufacturer warranty.|11499|7899|White|FREE|153|3052|73",
//            "women_fitness_gadgets|HealthSense Massage Gun|Deep tissue. 1 year manufacturer warranty.|9299|6499|Silver|FREE|73|3786|38",
//            // ---- women_headphones
//            "women_headphones|Apple AirPods (3rd Gen)|Spatial audio. 1 year manufacturer warranty.|22199|19900|Silver|FREE|115|1312|108",
//            "women_headphones|Sony WH-CH720N Headphones|Lightweight ANC. 1 year manufacturer warranty.|13799|11990|Blue|FREE|181|3574|33",
//            "women_headphones|JBL Tune 520BT Headphones|57h battery. 1 year manufacturer warranty.|4799|3999|Grey|FREE|39|1210|84",
//            "women_headphones|boAt Rockerz 550 Headphones|Soft cushions. 1 year manufacturer warranty.|2199|1799|Blue|FREE|150|2730|112",
//            "women_headphones|Marshall Major IV Headphones|80h playtime. 1 year manufacturer warranty.|17399|12999|Green|FREE|189|2881|76",
//            "women_headphones|Skullcandy Dime 3 TWS Earbuds|Compact. 1 year manufacturer warranty.|1799|1499|Black|FREE|134|2300|106",
//            // ---- women_speakers
//            "women_speakers|JBL Go 3 Portable Speaker|IP67. 1 year manufacturer warranty.|4399|3999|Silver|FREE|106|2882|35",
//            "women_speakers|Sony SRS-XB100 Speaker|Strap included. 1 year manufacturer warranty.|7299|4990|White|FREE|201|2126|104",
//            "women_speakers|boAt Stone 350 Speaker|10W RGB. 1 year manufacturer warranty.|1899|1499|Green|FREE|188|2792|24",
//            "women_speakers|Marshall Willen Speaker|Compact Marshall. 1 year manufacturer warranty.|8799|7999|Green|FREE|209|4507|78",
//            "women_speakers|Ultimate Ears WONDERBOOM 3|Floats in water. 1 year manufacturer warranty.|10499|8999|White|FREE|190|2273|86",
//            "women_speakers|Mivi Roam Speaker|Rugged mini. 1 year manufacturer warranty.|1799|1499|Green|FREE|195|2130|99",
//            // ---- women_fashion_jewellery
//            "women_fashion_jewellery|Zaveri Pearls Kundan Necklace Set|Gold-plated with earrings. Quality checked. Easy returns.|2099|1499|Olive|FREE|20|4689|101",
//            "women_fashion_jewellery|Accessorize Oxidised Silver Jhumkas|Boho style. Quality checked. Easy returns.|3899|1699|Khaki|FREE|50|2291|110",
//            "women_fashion_jewellery|Sukkhi Layered Chain Necklace|Minimal gold tone. Quality checked. Easy returns.|1199|459|Khaki|FREE|222|2328|4",
//            "women_fashion_jewellery|Rubans Bangle Set of 6|Stone studded. Quality checked. Easy returns.|1799|979|Beige|FREE|103|4115|21",
//            "women_fashion_jewellery|Giva Choker Set|Pearl and stone. Quality checked. Easy returns.|2199|1499|Grey Melange|FREE|45|3812|105",
//            "women_fashion_jewellery|Priyaasi Statement Ring|Adjustable. Quality checked. Easy returns.|1599|559|Mustard|FREE|42|4412|49",
//            // ---- women_fine_jewellery
//            "women_fine_jewellery|Tanishq 18K Gold Diamond Pendant|BIS hallmarked. Quality checked. Easy returns.|42199|30799|Grey Melange|FREE|99|1386|26",
//            "women_fine_jewellery|CaratLane 925 Silver Anklet Pair|Sterling silver. Quality checked. Easy returns.|70199|37899|Navy Blue|FREE|42|3547|58",
//            "women_fine_jewellery|Giva Diamond Stud Earrings|IGI certified. Quality checked. Easy returns.|53299|19699|Olive|FREE|61|2906|104",
//            "women_fine_jewellery|Melorra 22K Gold Bangle|Traditional. Quality checked. Easy returns.|20799|13899|Silver|FREE|130|2987|14",
//            "women_fine_jewellery|PC Jeweller Platinum Band Ring|Minimal. Quality checked. Easy returns.|80399|48199|Maroon|FREE|197|3875|66",
//            "women_fine_jewellery|BlueStone Sapphire Ring in Silver|Lab-created stone. Quality checked. Easy returns.|55999|34699|Charcoal|FREE|181|3118|27",
//            // ---- women_earrings
//            "women_earrings|Giva Gold-Plated Hoop Earrings|Lightweight. Quality checked. Easy returns.|869|629|Maroon|FREE|245|2879|27",
//            "women_earrings|Zaveri Pearls Pearl Drop Earrings|Classic pearl. Quality checked. Easy returns.|3199|1899|Beige|FREE|43|2881|59",
//            "women_earrings|Accessorize Silver Stud Earrings|925 silver. Quality checked. Easy returns.|3299|2499|Mustard|FREE|216|3431|110",
//            "women_earrings|Sukkhi Chandbali Earrings|Festive kundan. Quality checked. Easy returns.|519|319|Mustard|FREE|146|4671|8",
//            "women_earrings|Rubans Tassel Earrings|Colourful. Quality checked. Easy returns.|3399|2199|Brown|FREE|79|711|64",
//            "women_earrings|Priyaasi Ear Cuff Set|No piercing needed. Quality checked. Easy returns.|3599|2699|Beige|FREE|137|3473|105",
//            // ---- women_handbags_bags_wallets
//            "women_handbags_bags_wallets|Lavie Structured Tote Bag|Laptop sleeve. Quality checked. Easy returns.|3599|2399|Brown|FREE|142|2113|98",
//            "women_handbags_bags_wallets|Caprese Sling Bag|Adjustable strap. Quality checked. Easy returns.|7499|3799|Mustard|FREE|198|2911|43",
//            "women_handbags_bags_wallets|Baggit Clutch|Embellished party clutch. Quality checked. Easy returns.|8199|4799|Mustard|FREE|232|2312|67",
//            "women_handbags_bags_wallets|Hidesign Zip-Around Wallet|Card slots. Quality checked. Easy returns.|5499|3399|Beige|FREE|63|2660|94",
//            "women_handbags_bags_wallets|Accessorize Shoulder Bag|Vegan leather. Quality checked. Easy returns.|1199|929|Charcoal|FREE|208|2210|33",
//            "women_handbags_bags_wallets|Lino Perros Mini Backpack|Everyday. Quality checked. Easy returns.|4199|2599|Grey Melange|FREE|178|2304|2",
//            // ---- women_luggages_trolleys
//            "women_luggages_trolleys|American Tourister Cabin Trolley 55 cm|Hard shell, 4 wheels. Quality checked. Easy returns.|16099|8999|Green|FREE|113|3916|83",
//            "women_luggages_trolleys|Safari Check-in Trolley 68 cm|TSA lock. Quality checked. Easy returns.|9499|7399|Navy Blue|FREE|214|147|41",
//            "women_luggages_trolleys|VIP Trolley Set of 3|Cabin, medium, large. Quality checked. Easy returns.|11599|8999|Maroon|FREE|174|2877|27",
//            "women_luggages_trolleys|Skybags Soft Duffle Trolley|Expandable. Quality checked. Easy returns.|8399|4399|Charcoal|FREE|88|1174|119",
//            "women_luggages_trolleys|Aristocrat Vanity Case|Makeup organiser. Quality checked. Easy returns.|4199|3099|Charcoal|FREE|200|644|30",
//            "women_luggages_trolleys|Mokobara Weekender Bag|Travel duffel. Quality checked. Easy returns.|20399|11599|Olive|FREE|54|4769|53",
//            // ---- bed_runners
//            "bed_runners|Swayam Silk Blend Bed Runner with Cushion Covers|Set of 3 for king bed. Easy care. Great for gifting.|1299|899|Grey|FREE|157|269|28",
//            "bed_runners|Story@Home Jacquard Bed Runner|Woven damask pattern, 180 x 50 cm. Easy care. Great for gifting.|1499|1099|Mustard|FREE|106|889|42",
//            "bed_runners|Home Centre Block Print Cotton Bed Runner|Hand block printed. Easy care. Great for gifting.|929|639|Beige|FREE|153|4312|23",
//            "bed_runners|Spaces Velvet Bed Runner|Plush velvet with tassels. Easy care. Great for gifting.|2099|1399|Grey|FREE|229|3169|117",
//            "bed_runners|Dhrohar Embroidered Bed Runner|Thread embroidery border. Easy care. Great for gifting.|679|489|Teal|FREE|203|4122|53",
//            "bed_runners|Raymond Home Ikat Woven Bed Runner|Handloom ikat. Easy care. Great for gifting.|999|569|Grey|FREE|113|2736|114",
//            // ---- mattress_protectors
//            "mattress_protectors|Sleepwell Waterproof Mattress Protector Queen|Breathable TPU layer, fitted. Easy care. Great for gifting.|4399|1999|Brown|FREE|148|334|91",
//            "mattress_protectors|Wakefit Terry Cotton Mattress Protector King|Soft terry surface. Easy care. Great for gifting.|1599|1199|Beige|FREE|74|875|50",
//            "mattress_protectors|Kurlon Quilted Mattress Protector|Extra padding. Easy care. Great for gifting.|1999|729|Multi|FREE|67|3762|94",
//            "mattress_protectors|Spaces Anti-Bacterial Mattress Protector|Dust-mite resistant. Easy care. Great for gifting.|1599|1199|Teal|FREE|148|587|20",
//            "mattress_protectors|Story@Home Bamboo Mattress Protector|Cooling bamboo fabric. Easy care. Great for gifting.|1799|1199|Cream|FREE|129|4490|61",
//            "mattress_protectors|Trance Home Linen Single Bed Mattress Protector|Elastic skirt fit. Easy care. Great for gifting.|1099|599|Cream|FREE|224|4163|59",
//            // ---- bedsheets
//            "bedsheets|Bombay Dyeing Cotton Double Bedsheet with 2 Pillow Covers|180 TC, floral print. Easy care. Great for gifting.|1999|759|Multi|FREE|90|4701|112",
//            "bedsheets|Spaces King Size Fitted Bedsheet|Elastic all around. Easy care. Great for gifting.|779|519|Green|FREE|30|3417|97",
//            "bedsheets|D'Decor Satin Stripe Bedsheet Set|300 TC sateen. Easy care. Great for gifting.|2599|1799|Mustard|FREE|246|1890|45",
//            "bedsheets|Raymond Home Jaipuri Block Print Bedsheet|Hand-block print. Easy care. Great for gifting.|1999|1299|Maroon|FREE|116|4542|18",
//            "bedsheets|Story@Home Microfibre Solid Bedsheet|Wrinkle resistant. Easy care. Great for gifting.|3699|2499|Beige|FREE|190|4174|57",
//            "bedsheets|Portico Geometric Print Bedsheet|Modern geometric design. Easy care. Great for gifting.|5699|2199|Beige|FREE|69|3174|1",
//            // ---- bedding_sets
//            "bedding_sets|Bombay Dyeing 5-Piece Bedding Set|Bedsheet, comforter and pillow covers. Easy care. Great for gifting.|7199|2699|Brown|FREE|94|1911|15",
//            "bedding_sets|Spaces Duvet Cover Set King|Cotton duvet with zip. Easy care. Great for gifting.|3499|2199|Grey|FREE|213|1699|112",
//            "bedding_sets|D'Decor Embroidered Bedding Set|Thread work. Easy care. Great for gifting.|12699|5199|Multi|FREE|143|1514|96",
//            "bedding_sets|Portico Kids Cartoon Bedding Set|Soft and colourful. Easy care. Great for gifting.|10599|5999|Blue|FREE|199|3741|100",
//            "bedding_sets|Raymond Home Satin Bedding Set|Luxurious sheen. Easy care. Great for gifting.|2699|2099|Mustard|FREE|155|3896|81",
//            "bedding_sets|Home Centre Patchwork Bedding Set|Quilted patchwork. Easy care. Great for gifting.|3099|2099|Brown|FREE|131|1555|117",
//            // ---- blankets_quilts_dohars
//            "blankets_quilts_dohars|Jaipuri Haat Jaipuri Razai Double Bed|Hand-filled cotton. Easy care. Great for gifting.|8599|4199|Beige|FREE|125|1446|8",
//            "blankets_quilts_dohars|Spaces Mink Blanket Double|Super soft mink. Easy care. Great for gifting.|7499|3499|Multi|FREE|240|3674|120",
//            "blankets_quilts_dohars|Raymond Home Cotton Dohar Single|Lightweight AC blanket. Easy care. Great for gifting.|2899|1799|Blue|FREE|177|4582|75",
//            "blankets_quilts_dohars|Bombay Dyeing Microfibre Comforter|All-season comforter. Easy care. Great for gifting.|5299|1999|Beige|FREE|93|2855|85",
//            "blankets_quilts_dohars|Story@Home Fleece Throw Blanket|Cosy throw. Easy care. Great for gifting.|4399|3099|Brown|FREE|195|3990|15",
//            "blankets_quilts_dohars|Home Centre Reversible Quilt King|Two looks in one. Easy care. Great for gifting.|5999|3499|Green|FREE|62|387|77",
//            // ---- pillows_pillow_covers
//            "pillows_pillow_covers|Wakefit Microfibre Pillow Set of 2|Medium soft. Easy care. Great for gifting.|2399|839|Teal|FREE|190|1819|17",
//            "pillows_pillow_covers|Sleepwell Memory Foam Pillow|Contour neck support. Easy care. Great for gifting.|1199|879|Mustard|FREE|227|2941|22",
//            "pillows_pillow_covers|Spaces Cotton Pillow Covers Set of 4|Zip closure. Easy care. Great for gifting.|4299|1499|Brown|FREE|81|758|89",
//            "pillows_pillow_covers|Story@Home Satin Pillow Covers|Hair friendly. Easy care. Great for gifting.|1499|959|Cream|FREE|63|2537|77",
//            "pillows_pillow_covers|The Sleep Company Orthopedic Cervical Pillow|Neck pain relief. Easy care. Great for gifting.|1599|1199|Blue|FREE|113|418|71",
//            "pillows_pillow_covers|Home Centre Body Pillow|Long full body support. Easy care. Great for gifting.|2299|1199|Teal|FREE|43|1752|27",
//            // ---- bed_covers
//            "bed_covers|Spaces Quilted Bed Cover King|Reversible quilt. Easy care. Great for gifting.|4199|1999|White|FREE|75|3958|65",
//            "bed_covers|D'Decor Jacquard Bed Cover with Pillow Covers|Woven design. Easy care. Great for gifting.|5199|1999|Blue|FREE|24|524|63",
//            "bed_covers|Story@Home Kantha Bed Cover|Hand kantha stitch. Easy care. Great for gifting.|2899|2299|White|FREE|68|2813|102",
//            "bed_covers|Rajasthan Decor Velvet Bed Cover|Rich velvet. Easy care. Great for gifting.|6699|2599|Grey|FREE|213|2115|29",
//            "bed_covers|Home Centre Crochet Bed Cover|Handmade crochet. Easy care. Great for gifting.|6599|2499|Green|FREE|193|4143|76",
//            "bed_covers|Raymond Home Patchwork Bed Cover|Colourful patchwork. Easy care. Great for gifting.|2699|1199|Mustard|FREE|104|1536|69",
//            // ---- diwan_sets
//            "diwan_sets|Swayam 6-Piece Diwan Set|Sheet, bolsters and cushion covers. Easy care. Great for gifting.|4699|2299|Brown|FREE|245|4602|103",
//            "diwan_sets|Story@Home Jaipuri Diwan Set|Block print. Easy care. Great for gifting.|3899|2999|Blue|FREE|117|2025|108",
//            "diwan_sets|Rajasthan Decor Velvet Diwan Set|Rich velvet. Easy care. Great for gifting.|3999|2799|Multi|FREE|187|4504|48",
//            "diwan_sets|Home Centre Silk Blend Diwan Set|Festive sheen. Easy care. Great for gifting.|4299|2999|Beige|FREE|227|3898|26",
//            "diwan_sets|Dhrohar Embroidered Diwan Set|Mirror work. Easy care. Great for gifting.|6699|2399|Brown|FREE|201|154|9",
//            "diwan_sets|Spaces Cotton Diwan Set|Breathable cotton. Easy care. Great for gifting.|3299|1499|Teal|FREE|73|4731|4",
//            // ---- chair_pads_covers
//            "chair_pads_covers|Home Centre Chair Pads Set of 4|Tie-on cushions. Easy care. Great for gifting.|1999|1299|Beige|FREE|185|4753|24",
//            "chair_pads_covers|Story@Home Stretch Dining Chair Covers Set of 6|Spandex fit. Easy care. Great for gifting.|2599|1199|Maroon|FREE|214|1077|61",
//            "chair_pads_covers|Spaces Office Chair Cushion|Memory foam. Easy care. Great for gifting.|639|419|Maroon|FREE|221|4406|22",
//            "chair_pads_covers|Lushomes Floor Seating Cushion|Round pouf cushion. Easy care. Great for gifting.|659|449|Mustard|FREE|140|3656|109",
//            "chair_pads_covers|Kuber Industries Printed Chair Pads|Boho print. Easy care. Great for gifting.|799|639|Brown|FREE|217|3792|6",
//            "chair_pads_covers|HOKIPO Velvet Chair Covers|Elegant velvet. Easy care. Great for gifting.|2899|1399|Beige|FREE|18|3549|63",
//            // ---- sofa_covers
//            "sofa_covers|Home Centre Stretchable 3 Seater Sofa Cover|Elastic fit. Easy care. Great for gifting.|2699|1599|Teal|FREE|35|4790|4",
//            "sofa_covers|Story@Home 5 Seater Sofa Cover Set|Polycotton. Easy care. Great for gifting.|3499|2599|Cream|FREE|77|873|103",
//            "sofa_covers|Spaces L-Shape Sofa Cover|Corner sofa fit. Easy care. Great for gifting.|3899|1399|Beige|FREE|61|2861|107",
//            "sofa_covers|Kuber Industries Quilted Sofa Throw|Reversible throw. Easy care. Great for gifting.|5299|2999|Cream|FREE|104|1330|94",
//            "sofa_covers|HOKIPO Velvet Sofa Cover|Plush velvet. Easy care. Great for gifting.|1099|819|Multi|FREE|210|1499|31",
//            "sofa_covers|Lushomes Waterproof Sofa Protector|Pet friendly. Easy care. Great for gifting.|2699|2099|Beige|FREE|223|4456|54",
//            // ---- floor_runners
//            "floor_runners|Saral Home Anti-Skid Floor Runner 2x6 ft|Kitchen and corridor. Easy care. Great for gifting.|1199|759|Cream|FREE|184|125|67",
//            "floor_runners|Story@Home Jute Floor Runner|Natural jute. Easy care. Great for gifting.|3199|2499|Mustard|FREE|67|480|25",
//            "floor_runners|Status Printed Kitchen Runner|Washable. Easy care. Great for gifting.|1599|819|Multi|FREE|56|3582|45",
//            "floor_runners|Home Centre Shaggy Floor Runner|Soft pile. Easy care. Great for gifting.|4699|1899|Grey|FREE|188|2153|113",
//            "floor_runners|Obsessions Cotton Dhurrie Runner|Handwoven. Easy care. Great for gifting.|3299|2099|Cream|FREE|95|4711|106",
//            "floor_runners|Swayam Geometric Floor Runner|Modern print. Easy care. Great for gifting.|1499|959|Brown|FREE|97|3168|107",
//            // ---- carpets
//            "carpets|Obsessions Hand-Tufted Wool Carpet 5x7 ft|Rich wool pile. Easy care. Great for gifting.|20599|7599|Blue|FREE|88|540|112",
//            "carpets|Home Centre Shaggy Carpet 4x6 ft|Ultra soft. Easy care. Great for gifting.|15599|7599|Beige|FREE|43|1310|55",
//            "carpets|Status Persian Design Carpet|Traditional motifs. Easy care. Great for gifting.|10999|8099|Brown|FREE|170|700|50",
//            "carpets|Saral Home Abstract Modern Rug|Contemporary art. Easy care. Great for gifting.|4699|2599|Teal|FREE|73|2486|105",
//            "carpets|Story@Home Jute Braided Rug|Eco-friendly. Easy care. Great for gifting.|11099|5299|Brown|FREE|76|2266|51",
//            "carpets|Jaipur Rugs Kilim Flatweave Carpet|Reversible. Easy care. Great for gifting.|11099|4099|Maroon|FREE|69|319|17",
//            // ---- floor_mats_dhurries
//            "floor_mats_dhurries|Saral Home Cotton Dhurrie 4x6 ft|Handwoven striped dhurrie. Easy care. Great for gifting.|3999|1699|Cream|FREE|120|3541|107",
//            "floor_mats_dhurries|Story@Home Jute Floor Mat|Natural braided jute. Easy care. Great for gifting.|6099|2899|Blue|FREE|156|3946|58",
//            "floor_mats_dhurries|Swayam Handwoven Geometric Rug|Recycled cotton. Easy care. Great for gifting.|4899|1899|Cream|FREE|47|3142|111",
//            "floor_mats_dhurries|Obsessions Chindi Rag Rug|Colourful upcycled. Easy care. Great for gifting.|2899|2099|Multi|FREE|122|982|39",
//            "floor_mats_dhurries|Jaipur Rugs Kilim Dhurrie|Tribal pattern. Easy care. Great for gifting.|6699|2999|Beige|FREE|152|3486|89",
//            "floor_mats_dhurries|Status Anti-Skid Kitchen Mat|Washable. Easy care. Great for gifting.|4399|1699|Teal|FREE|161|870|4",
//            // ---- door_mats
//            "door_mats|Status Coir Welcome Door Mat|Natural coir. Easy care. Great for gifting.|909|569|Mustard|FREE|121|2076|101",
//            "door_mats|Saral Home Anti-Skid Microfibre Door Mat|Super absorbent. Easy care. Great for gifting.|1699|809|Brown|FREE|211|3811|39",
//            "door_mats|Kuber Industries Rubber Outdoor Door Mat|Heavy duty. Easy care. Great for gifting.|1199|579|Brown|FREE|229|1745|51",
//            "door_mats|Story@Home Printed Door Mat Set of 3|Fun prints. Easy care. Great for gifting.|1199|639|Beige|FREE|205|2378|59",
//            "door_mats|Home Centre Shaggy Bathroom Door Mat|Soft pile. Easy care. Great for gifting.|1999|879|White|FREE|219|3511|88",
//            "door_mats|Obsessions Jute Door Mat|Eco friendly. Easy care. Great for gifting.|1499|809|Mustard|FREE|125|3809|25",
//            // ---- bath_towels
//            "bath_towels|Bombay Dyeing Cotton Bath Towel 500 GSM|Super absorbent. Easy care. Great for gifting.|1399|879|Maroon|FREE|197|2691|85",
//            "bath_towels|Spaces Bamboo Bath Towel|Soft and odour resistant. Easy care. Great for gifting.|1999|1099|White|FREE|57|2011|83",
//            "bath_towels|Trident Turkish Bath Towel|Lightweight, quick dry. Easy care. Great for gifting.|879|369|Teal|FREE|167|3814|100",
//            "bath_towels|Story@Home Zero Twist Bath Towel|Plush loops. Easy care. Great for gifting.|1599|1199|Teal|FREE|169|2810|12",
//            "bath_towels|Welspun Striped Bath Towel|Classic stripes. Easy care. Great for gifting.|1299|549|Brown|FREE|208|1170|101",
//            "bath_towels|Raymond Home Microfibre Bath Towel|Compact and fast drying. Easy care. Great for gifting.|3799|1499|Mustard|FREE|17|3501|100",
//            // ---- hand_face_towels
//            "hand_face_towels|Bombay Dyeing Hand Towels Set of 4|Cotton terry. Easy care. Great for gifting.|519|319|Beige|FREE|152|3335|66",
//            "hand_face_towels|Spaces Face Towels Set of 6|Soft loops. Easy care. Great for gifting.|419|309|White|FREE|123|2870|100",
//            "hand_face_towels|Trident Bamboo Face Towels|Gentle on skin. Easy care. Great for gifting.|889|609|Grey|FREE|117|1227|8",
//            "hand_face_towels|Welspun Embroidered Hand Towels|Gift ready. Easy care. Great for gifting.|959|609|Green|FREE|235|103|97",
//            "hand_face_towels|Story@Home Quick Dry Face Towels|Microfibre. Easy care. Great for gifting.|1399|699|Maroon|FREE|185|1756|112",
//            "hand_face_towels|Home Centre Waffle Hand Towels|Textured waffle. Easy care. Great for gifting.|609|249|Green|FREE|121|226|28",
//            // ---- beach_towels
//            "beach_towels|Spaces Printed Beach Towel|Large 90x180 cm. Easy care. Great for gifting.|1399|769|Green|FREE|167|3575|35",
//            "beach_towels|Story@Home Turkish Peshtemal|Lightweight, tassels. Easy care. Great for gifting.|3299|1399|Blue|FREE|62|1133|61",
//            "beach_towels|Trident Microfibre Beach Towel|Sand free. Easy care. Great for gifting.|1599|889|Beige|FREE|222|2990|120",
//            "beach_towels|Decathlon Nabaiji Round Beach Towel|Mandala print. Easy care. Great for gifting.|2799|979|Teal|FREE|126|2145|23",
//            "beach_towels|Home Centre Striped Beach Towel|Cabana stripes. Easy care. Great for gifting.|869|529|Multi|FREE|177|1115|43",
//            "beach_towels|Welspun Kids Hooded Beach Towel|Animal hood. Easy care. Great for gifting.|1199|669|Multi|FREE|181|4166|12",
//            // ---- towels_set
//            "towels_set|Bombay Dyeing 6-Piece Towel Set|2 bath, 2 hand, 2 face. Easy care. Great for gifting.|3699|1799|Multi|FREE|219|3803|5",
//            "towels_set|Spaces Cotton Towel Gift Set|Gift boxed. Easy care. Great for gifting.|2699|1199|White|FREE|155|2678|14",
//            "towels_set|Trident Bamboo Towel Set|Eco-friendly. Easy care. Great for gifting.|1799|1299|Beige|FREE|74|3644|36",
//            "towels_set|Welspun Hotel Collection Towel Set|600 GSM. Easy care. Great for gifting.|3699|2199|White|FREE|132|787|2",
//            "towels_set|Raymond Home Kids Towel Set|Fun prints. Easy care. Great for gifting.|2799|2099|Blue|FREE|249|2678|60",
//            "towels_set|Story@Home Couple Towel Set|His and hers. Easy care. Great for gifting.|3499|2499|Cream|FREE|126|3927|20",
//            // ---- bath_rugs
//            "bath_rugs|Story@Home Memory Foam Bath Mat|Ultra soft. Easy care. Great for gifting.|959|679|Brown|FREE|208|4713|85",
//            "bath_rugs|Saral Home Microfibre Bath Rug|Absorbent. Easy care. Great for gifting.|2599|1199|Green|FREE|17|4563|1",
//            "bath_rugs|Spaces Cotton Bath Mat Set of 2|Washable. Easy care. Great for gifting.|1199|899|Multi|FREE|35|3065|48",
//            "bath_rugs|Status Diatomite Stone Bath Mat|Quick dry. Easy care. Great for gifting.|919|539|Teal|FREE|195|3975|76",
//            "bath_rugs|Home Centre Shaggy Bath Rug|Plush pile. Easy care. Great for gifting.|1199|709|Teal|FREE|171|1954|84",
//            "bath_rugs|Kuber Industries Anti-Skid Bath Mat|Rubber backing. Easy care. Great for gifting.|1599|949|Cream|FREE|236|3007|5",
//            // ---- bath_robes
//            "bath_robes|Spaces Cotton Terry Bath Robe|Shawl collar. Easy care. Great for gifting.|3899|2999|Brown|FREE|87|2493|75",
//            "bath_robes|Trident Waffle Bath Robe|Lightweight. Easy care. Great for gifting.|3399|1199|Blue|FREE|178|1515|87",
//            "bath_robes|Bombay Dyeing Kids Hooded Bath Robe|Soft terry. Easy care. Great for gifting.|3499|2599|White|FREE|127|3935|63",
//            "bath_robes|Story@Home Bamboo Bath Robe|Silky soft. Easy care. Great for gifting.|5399|2599|Blue|FREE|85|1211|84",
//            "bath_robes|Welspun Kimono Bath Robe|Spa style. Easy care. Great for gifting.|2599|1099|Brown|FREE|69|1838|114",
//            "bath_robes|Raymond Home Fleece Bath Robe|Winter warm. Easy care. Great for gifting.|2399|1799|Maroon|FREE|30|1256|35",
//            // ---- bathroom_accessories
//            "bathroom_accessories|Home Centre Ceramic Bathroom Set of 4|Dispenser, tumbler, soap dish. Easy care. Great for gifting.|2899|1999|Multi|FREE|141|3382|118",
//            "bathroom_accessories|Story@Home Wall Mounted Shower Caddy|Rust-proof steel. Easy care. Great for gifting.|559|369|Brown|FREE|164|489|107",
//            "bathroom_accessories|Kuber Industries Toothbrush Holder|Ceramic. Easy care. Great for gifting.|1299|769|Cream|FREE|98|2003|68",
//            "bathroom_accessories|Plantex Soap Dispenser|Bamboo lid. Easy care. Great for gifting.|3899|1399|Green|FREE|171|1056|80",
//            "bathroom_accessories|Umbra Towel Rack|Stainless steel. Easy care. Great for gifting.|1199|779|Green|FREE|121|2505|45",
//            "bathroom_accessories|Ebern Designs Bathroom Corner Shelf|Tempered glass. Easy care. Great for gifting.|1499|879|White|FREE|212|3168|109",
//            // ---- shower_curtains
//            "shower_curtains|Home Centre Waterproof Shower Curtain with Hooks|PEVA. Easy care. Great for gifting.|419|319|Teal|FREE|103|1745|115",
//            "shower_curtains|Story@Home Printed Fabric Shower Curtain|Botanical print. Easy care. Great for gifting.|729|559|Beige|FREE|25|4770|0",
//            "shower_curtains|Kuber Industries Transparent Shower Curtain|Frosted. Easy care. Great for gifting.|1899|949|Beige|FREE|58|2192|70",
//            "shower_curtains|Lushomes Striped Shower Curtain|Modern stripes. Easy care. Great for gifting.|1299|969|Blue|FREE|249|1638|99",
//            "shower_curtains|Spaces Polyester Shower Curtain|Mildew resistant. Easy care. Great for gifting.|2199|899|White|FREE|221|4427|5",
//            "shower_curtains|HOKIPO 3D Print Shower Curtain|Ocean scene. Easy care. Great for gifting.|1699|929|Multi|FREE|149|4338|10",
//            // ---- floor_lamps
//            "floor_lamps|Philips Tripod Floor Lamp|Wooden tripod, fabric shade. Easy care. Great for gifting.|16299|5999|Cream|FREE|243|4485|52",
//            "floor_lamps|Home Centre Arc Floor Lamp|Curved arc, marble base. Easy care. Great for gifting.|6999|2499|Mustard|FREE|55|1829|30",
//            "floor_lamps|Pepperfry LED Corner Floor Lamp|RGB colour changing. Easy care. Great for gifting.|12799|5499|Blue|FREE|245|251|60",
//            "floor_lamps|Homesake Rattan Floor Lamp|Handwoven shade. Easy care. Great for gifting.|17499|7699|Green|FREE|136|2967|24",
//            "floor_lamps|Fos Lighting Reading Floor Lamp|Adjustable head. Easy care. Great for gifting.|16199|6299|Maroon|FREE|197|1337|87",
//            "floor_lamps|Wipro Metal Floor Lamp|Matte black finish. Easy care. Great for gifting.|12399|6199|Multi|FREE|235|2623|21",
//            // ---- ceiling_lamps
//            "ceiling_lamps|Philips LED Ceiling Light 24W|Slim panel. Easy care. Great for gifting.|5999|4199|Blue|FREE|115|4312|37",
//            "ceiling_lamps|Wipro Pendant Hanging Lamp|Metal dome. Easy care. Great for gifting.|11199|4899|Maroon|FREE|25|2949|56",
//            "ceiling_lamps|Syska Chandelier 5 Light|Crystal accents. Easy care. Great for gifting.|2699|1999|Maroon|FREE|210|2882|80",
//            "ceiling_lamps|Home Centre Rattan Pendant Light|Boho rattan. Easy care. Great for gifting.|4999|3099|Green|FREE|26|3604|91",
//            "ceiling_lamps|Pepperfry Glass Globe Pendant|Smoked glass. Easy care. Great for gifting.|3899|3099|White|FREE|118|3666|27",
//            "ceiling_lamps|Homesake Smart Ceiling Light|App controlled. Easy care. Great for gifting.|5799|3199|White|FREE|174|2635|77",
//            // ---- table_lamps
//            "table_lamps|Philips Ceramic Table Lamp|Fabric drum shade. Easy care. Great for gifting.|2399|1199|Green|FREE|213|4523|69",
//            "table_lamps|Home Centre Wooden Base Table Lamp|Natural wood. Easy care. Great for gifting.|2299|889|Brown|FREE|118|1346|21",
//            "table_lamps|Pepperfry LED Study Lamp|Touch dimmer. Easy care. Great for gifting.|4599|2099|Beige|FREE|216|4319|50",
//            "table_lamps|Homesake Mushroom Table Lamp|Retro mushroom. Easy care. Great for gifting.|3999|1399|Multi|FREE|38|3072|53",
//            "table_lamps|Fos Lighting Brass Table Lamp|Antique brass. Easy care. Great for gifting.|6599|2999|Beige|FREE|35|3111|105",
//            "table_lamps|Wipro Rechargeable Table Lamp|Cordless. Easy care. Great for gifting.|7399|2999|Multi|FREE|91|3092|62",
//            // ---- wall_lamps
//            "wall_lamps|Philips Swing Arm Wall Lamp|Adjustable arm. Easy care. Great for gifting.|1899|679|Multi|FREE|188|4685|17",
//            "wall_lamps|Home Centre Up-Down LED Wall Light|Modern effect. Easy care. Great for gifting.|4799|2499|Grey|FREE|69|2923|38",
//            "wall_lamps|Homesake Glass Wall Sconce|Vintage style. Easy care. Great for gifting.|3399|1199|Grey|FREE|98|2469|77",
//            "wall_lamps|Pepperfry Rattan Wall Lamp|Boho. Easy care. Great for gifting.|2799|959|Maroon|FREE|51|3237|29",
//            "wall_lamps|Fos Lighting Plug-In Wall Lamp|No wiring needed. Easy care. Great for gifting.|2499|1299|Grey|FREE|107|3691|49",
//            "wall_lamps|Wipro Brass Wall Sconce|Antique finish. Easy care. Great for gifting.|2999|1799|Beige|FREE|203|1305|96",
//            // ---- outdoor_lamps
//            "outdoor_lamps|Philips Solar Garden Lights Pack of 4|Auto on/off. Easy care. Great for gifting.|2199|1699|Mustard|FREE|104|3785|21",
//            "outdoor_lamps|Wipro Outdoor Wall Lantern|Weatherproof. Easy care. Great for gifting.|1999|1499|Green|FREE|102|4742|63",
//            "outdoor_lamps|Syska LED Flood Light 50W|IP65. Easy care. Great for gifting.|5799|2399|Teal|FREE|21|2708|82",
//            "outdoor_lamps|Homesake Bollard Garden Light|Pathway lighting. Easy care. Great for gifting.|2499|859|Brown|FREE|32|314|115",
//            "outdoor_lamps|Home Centre Solar Hanging Lantern|Decorative. Easy care. Great for gifting.|1199|699|Cream|FREE|76|1803|17",
//            "outdoor_lamps|Halonix Motion Sensor Light|PIR sensor. Easy care. Great for gifting.|3299|2099|Maroon|FREE|119|3726|119",
//            // ---- string_lights
//            "string_lights|Philips Fairy String Lights 10m|Warm white. Easy care. Great for gifting.|2999|1499|Green|FREE|32|3566|103",
//            "string_lights|Home Centre Curtain String Lights|300 LED. Easy care. Great for gifting.|449|309|Blue|FREE|53|2898|7",
//            "string_lights|Homesake Photo Clip String Lights|Hang memories. Easy care. Great for gifting.|1299|719|Green|FREE|192|753|66",
//            "string_lights|Desidiya Solar String Lights|Outdoor. Easy care. Great for gifting.|1399|469|Mustard|FREE|69|3071|45",
//            "string_lights|Syska Ball String Lights|Cotton balls. Easy care. Great for gifting.|889|489|Mustard|FREE|130|100|8",
//            "string_lights|Fos Lighting Star String Lights|Festive stars. Easy care. Great for gifting.|1399|849|Teal|FREE|241|736|85",
//            // ---- plants_planters
//            "plants_planters|Ugaoo Snake Plant with Ceramic Pot|Air purifying. Easy care. Great for gifting.|2999|1999|Maroon|FREE|161|3401|98",
//            "plants_planters|Nurserylive Money Plant in Glass Bottle|Low maintenance. Easy care. Great for gifting.|5999|2399|White|FREE|161|4014|81",
//            "plants_planters|Home Centre Ceramic Planters Set of 3|Matte finish. Easy care. Great for gifting.|889|609|Cream|FREE|157|3187|11",
//            "plants_planters|Trudeau Hanging Macrame Planter|Boho decor. Easy care. Great for gifting.|1099|839|Maroon|FREE|216|1140|12",
//            "plants_planters|Chumbak Artificial Bamboo Plant|No maintenance. Easy care. Great for gifting.|2199|1599|Maroon|FREE|210|3583|16",
//            "plants_planters|Pepperfry Self-Watering Planter|Built-in reservoir. Easy care. Great for gifting.|3099|1599|Mustard|FREE|67|906|103",
//            // ---- aromas_candles
//            "aromas_candles|Iris Scented Jar Candle|Lavender, 40 hr burn. Easy care. Great for gifting.|1699|729|Brown|FREE|116|722|86",
//            "aromas_candles|Bath & Body Works Reed Diffuser Set|Fragrance oil with reeds. Easy care. Great for gifting.|2199|1099|Teal|FREE|37|2320|59",
//            "aromas_candles|Home Centre Aroma Oil Diffuser|Ceramic burner. Easy care. Great for gifting.|1399|779|Blue|FREE|37|2088|119",
//            "aromas_candles|Rosemoore Tealight Candles Pack of 50|Unscented. Easy care. Great for gifting.|1099|749|Grey|FREE|189|3842|60",
//            "aromas_candles|Ekam Soy Wax Candle|Vanilla. Easy care. Great for gifting.|629|379|Cream|FREE|125|2973|106",
//            "aromas_candles|The Body Shop Essential Oils Set of 6|Pure oils. Easy care. Great for gifting.|1599|809|Green|FREE|28|1913|10",
//            // ---- clocks
//            "clocks|Ajanta Silent Sweep Wall Clock|No ticking. Easy care. Great for gifting.|3699|2299|Green|FREE|125|4160|33",
//            "clocks|Home Centre Wooden Wall Clock|Natural wood. Easy care. Great for gifting.|879|409|Brown|FREE|75|4008|96",
//            "clocks|Chumbak Metal Sunburst Clock|Gold finish. Easy care. Great for gifting.|4399|2399|Multi|FREE|84|1074|51",
//            "clocks|Seiko Pendulum Wall Clock|Classic design. Easy care. Great for gifting.|2399|1199|Grey|FREE|57|722|37",
//            "clocks|Random Digital LED Clock|Temperature display. Easy care. Great for gifting.|3199|1399|Grey|FREE|96|3364|94",
//            "clocks|Pepperfry Table Alarm Clock|Retro twin bell. Easy care. Great for gifting.|3999|2499|Blue|FREE|98|1137|33",
//            // ---- mirrors
//            "mirrors|Home Centre Round Wall Mirror|Metal frame. Easy care. Great for gifting.|7399|4899|Cream|FREE|20|1732|71",
//            "mirrors|Pepperfry Sunburst Decorative Mirror|Gold finish. Easy care. Great for gifting.|5899|3599|Mustard|FREE|136|571|8",
//            "mirrors|Chumbak Full Length Floor Mirror|Wooden frame. Easy care. Great for gifting.|4399|3099|Green|FREE|69|4703|29",
//            "mirrors|Wooden Street Arch Wall Mirror|Modern arch. Easy care. Great for gifting.|3599|1799|Cream|FREE|233|2624|54",
//            "mirrors|Art Street Bathroom Vanity Mirror|LED backlit. Easy care. Great for gifting.|6999|3599|Blue|FREE|18|117|1",
//            "mirrors|Urban Ladder Rattan Mirror|Boho cane frame. Easy care. Great for gifting.|3199|2399|Beige|FREE|184|983|75",
//            // ---- wall_decor
//            "wall_decor|Art Street Framed Wall Art Set of 3|Abstract prints. Easy care. Great for gifting.|3199|2199|White|FREE|64|117|37",
//            "wall_decor|Chumbak Metal Wall Art|Laser cut. Easy care. Great for gifting.|3199|1699|Cream|FREE|233|4686|53",
//            "wall_decor|Home Centre Wall Hanging Macrame|Handmade. Easy care. Great for gifting.|3399|1899|Teal|FREE|97|2959|58",
//            "wall_decor|Pepperfry Photo Frame Collage|12 frames. Easy care. Great for gifting.|3499|2299|Beige|FREE|53|4297|0",
//            "wall_decor|Paper Plane Design Wall Plates Set|Decorative ceramic. Easy care. Great for gifting.|1699|1099|Teal|FREE|78|1248|37",
//            "wall_decor|Hometown Canvas Painting|Modern art. Easy care. Great for gifting.|2899|1099|Teal|FREE|200|3546|56",
//            // ---- festive_decor
//            "festive_decor|Home Centre Toran Door Hanging|Bead and bells. Easy care. Great for gifting.|3699|1499|Green|FREE|228|4375|66",
//            "festive_decor|Chumbak Diya Set of 12|Terracotta. Easy care. Great for gifting.|1299|619|Multi|FREE|41|2366|67",
//            "festive_decor|Desidiya Artificial Marigold Garland|5 ft pack. Easy care. Great for gifting.|2699|1399|Grey|FREE|100|1481|103",
//            "festive_decor|Ekam Rangoli Stencils Kit|With colours. Easy care. Great for gifting.|1499|1099|Cream|FREE|226|3961|99",
//            "festive_decor|Fabindia LED Diya Lights|Battery operated. Easy care. Great for gifting.|1799|619|Maroon|FREE|27|1739|18",
//            "festive_decor|Rangoli Art Urli Bowl with Floating Candles|Brass finish. Easy care. Great for gifting.|3899|1399|Mustard|FREE|20|2724|88",
//            // ---- pooja_essentials
//            "pooja_essentials|Ekam Brass Pooja Thali Set|7-piece set. Easy care. Great for gifting.|1599|729|Brown|FREE|83|2741|112",
//            "pooja_essentials|Home Centre Wooden Pooja Mandir|Wall mounted. Easy care. Great for gifting.|2299|1699|Brown|FREE|40|1698|90",
//            "pooja_essentials|Fabindia Brass Diya Pair|Traditional. Easy care. Great for gifting.|4599|2299|Teal|FREE|128|4758|114",
//            "pooja_essentials|Divine Gifts Incense Stick Holder|Brass. Easy care. Great for gifting.|2999|1899|Multi|FREE|97|1374|113",
//            "pooja_essentials|Brass Gift Center Copper Kalash|Pure copper. Easy care. Great for gifting.|609|369|Green|FREE|128|1988|79",
//            "pooja_essentials|Shubh Labh Ganesha Idol|Resin finish. Easy care. Great for gifting.|3999|2499|Grey|FREE|195|1413|33",
//            // ---- wall_shelves
//            "wall_shelves|Home Centre Floating Wall Shelves Set of 3|MDF. Easy care. Great for gifting.|6299|2199|Cream|FREE|141|4526|69",
//            "wall_shelves|Pepperfry Hexagon Wall Shelf|Honeycomb. Easy care. Great for gifting.|3899|1799|Mustard|FREE|105|2179|31",
//            "wall_shelves|Wooden Street Corner Wall Shelf|Space saving. Easy care. Great for gifting.|2699|1699|Brown|FREE|227|117|90",
//            "wall_shelves|Urban Ladder Metal Ladder Shelf|Industrial look. Easy care. Great for gifting.|1399|909|Mustard|FREE|129|2860|62",
//            "wall_shelves|Art Street Rope Hanging Shelf|Rustic. Easy care. Great for gifting.|739|589|Brown|FREE|162|4513|33",
//            "wall_shelves|Solimo Book Shelf Wall Mounted|Engineered wood. Easy care. Great for gifting.|1899|1499|Grey|FREE|153|4153|36",
//            // ---- fountains
//            "fountains|Home Centre Tabletop Water Fountain|LED light. Easy care. Great for gifting.|6399|4399|Maroon|FREE|207|863|20",
//            "fountains|Pepperfry Buddha Fountain|Resin. Easy care. Great for gifting.|1999|1199|Beige|FREE|218|637|90",
//            "fountains|Ekam Ceramic Fountain|Cascading. Easy care. Great for gifting.|10899|3799|Multi|FREE|161|2759|57",
//            "fountains|Nurserylive Rock Wall Fountain|Natural look. Easy care. Great for gifting.|8399|3099|Beige|FREE|233|3607|36",
//            "fountains|Ugaoo Solar Bird Bath Fountain|Garden. Easy care. Great for gifting.|4699|2199|Green|FREE|138|2728|13",
//            "fountains|Craftmart Indoor Zen Fountain|Calming. Easy care. Great for gifting.|8599|4099|Green|FREE|19|1921|66",
//            // ---- showpieces_vases
//            "showpieces_vases|Home Centre Ceramic Flower Vase|Matte finish. Easy care. Great for gifting.|4199|2099|Brown|FREE|46|2889|24",
//            "showpieces_vases|Chumbak Glass Bud Vase Set|Set of 3. Easy care. Great for gifting.|989|739|Blue|FREE|223|480|16",
//            "showpieces_vases|Pepperfry Buddha Showpiece|Resin. Easy care. Great for gifting.|4799|2399|Blue|FREE|188|4625|116",
//            "showpieces_vases|Ekam Brass Elephant Showpiece|Handcrafted. Easy care. Great for gifting.|2299|1699|Multi|FREE|110|4175|6",
//            "showpieces_vases|Art Street Abstract Sculpture|Modern art. Easy care. Great for gifting.|2299|1499|Beige|FREE|187|2339|58",
//            "showpieces_vases|Fabindia Terracotta Vase|Hand painted. Easy care. Great for gifting.|3299|1999|Blue|FREE|147|396|115",
//            // ---- ottoman
//            "ottoman|Home Centre Velvet Round Ottoman|Gold legs. Easy care. Great for gifting.|6799|3399|Teal|FREE|25|1485|113",
//            "ottoman|Pepperfry Storage Ottoman Pouffe|Hidden storage. Easy care. Great for gifting.|5799|2699|Brown|FREE|99|3498|103",
//            "ottoman|Wooden Street Knitted Pouf|Chunky knit. Easy care. Great for gifting.|9099|3999|Beige|FREE|235|69|52",
//            "ottoman|Urban Ladder Leatherette Ottoman|Tufted top. Easy care. Great for gifting.|7599|4399|Green|FREE|40|1567|81",
//            "ottoman|Hometown Jute Pouf|Handwoven. Easy care. Great for gifting.|3299|2099|White|FREE|176|2687|62",
//            "ottoman|Solimo Bench Ottoman|Bed end. Easy care. Great for gifting.|3899|2899|Mustard|FREE|172|4183|107",
//            // ---- cushions_cushion_covers
//            "cushions_cushion_covers|Home Centre Cushion Covers Set of 5|16x16 inches. Easy care. Great for gifting.|1299|849|Brown|FREE|217|1947|58",
//            "cushions_cushion_covers|Chumbak Velvet Cushion Covers|Plush. Easy care. Great for gifting.|1899|1099|Teal|FREE|93|3173|38",
//            "cushions_cushion_covers|Story@Home Printed Cotton Cushions|Filled cushions. Easy care. Great for gifting.|2299|1499|Teal|FREE|178|1383|52",
//            "cushions_cushion_covers|Spaces Embroidered Cushion Covers|Mirror work. Easy care. Great for gifting.|2499|999|Brown|FREE|233|1901|26",
//            "cushions_cushion_covers|Fabindia Boho Tufted Cushion|Tassels. Easy care. Great for gifting.|1299|649|Multi|FREE|160|3214|60",
//            "cushions_cushion_covers|Swayam Floor Cushion|Large 24 inch. Easy care. Great for gifting.|2399|1399|Beige|FREE|154|2812|0",
//            // ---- curtains
//            "curtains|Home Centre Blackout Curtains Set of 2|Door 7 ft. Easy care. Great for gifting.|6499|2399|Cream|FREE|124|3982|73",
//            "curtains|D'Decor Sheer Window Curtains|Voile. Easy care. Great for gifting.|5299|1899|Maroon|FREE|149|1954|50",
//            "curtains|Story@Home Printed Cotton Curtains|Eyelet. Easy care. Great for gifting.|3099|2099|Maroon|FREE|181|1111|1",
//            "curtains|Spaces Velvet Curtains|Thermal insulation. Easy care. Great for gifting.|5599|2499|Mustard|FREE|120|295|118",
//            "curtains|Lushomes Linen Look Curtains|Natural texture. Easy care. Great for gifting.|4099|1799|Green|FREE|129|4737|29",
//            "curtains|Swayam Kids Room Curtains|Fun print. Easy care. Great for gifting.|4699|2399|Blue|FREE|107|1265|106",
//            // ---- home_gift_sets
//            "home_gift_sets|Home Centre Candle and Diffuser Gift Box|Aroma set. Easy care. Great for gifting.|4499|2199|Brown|FREE|176|2287|26",
//            "home_gift_sets|Chumbak Coffee Mug Gift Set|Set of 2. Easy care. Great for gifting.|3199|1199|Brown|FREE|43|679|66",
//            "home_gift_sets|Iris Decor Hamper|Assorted decor. Easy care. Great for gifting.|1999|1399|Blue|FREE|132|1692|33",
//            "home_gift_sets|Ekam Brass Gift Set|Festive. Easy care. Great for gifting.|1199|899|Brown|FREE|200|4548|82",
//            "home_gift_sets|Fabindia Plant Gift Set|Indoor plant with pot. Easy care. Great for gifting.|6599|2799|Brown|FREE|241|376|117",
//            "home_gift_sets|Story@Home Photo Frame Gift Set|Set of 3. Easy care. Great for gifting.|3599|2699|Mustard|FREE|26|1096|111",
//            // ---- table_runners
//            "table_runners|Home Centre Cotton Table Runner|6 seater. Easy care. Great for gifting.|869|339|Mustard|FREE|153|2337|117",
//            "table_runners|Story@Home Jute Table Runner|Natural. Easy care. Great for gifting.|1899|1299|Beige|FREE|198|2556|53",
//            "table_runners|Swayam Embroidered Table Runner|Festive. Easy care. Great for gifting.|2599|1299|Grey|FREE|163|402|100",
//            "table_runners|Fabindia Block Print Table Runner|Hand printed. Easy care. Great for gifting.|1099|729|Grey|FREE|132|2626|36",
//            "table_runners|Spaces Velvet Table Runner|Luxury. Easy care. Great for gifting.|1799|1199|Multi|FREE|197|1624|61",
//            "table_runners|Lushomes Macrame Table Runner|Boho. Easy care. Great for gifting.|2999|1199|White|FREE|249|3314|118",
//            // ---- dinnerware_serveware
//            "dinnerware_serveware|Corelle Dinner Set 18 Pieces|Opal glass. Easy care. Great for gifting.|4299|2899|Cream|FREE|101|3221|8",
//            "dinnerware_serveware|Borosil Ceramic Serving Bowls Set|Set of 4. Easy care. Great for gifting.|4399|2999|Teal|FREE|15|381|49",
//            "dinnerware_serveware|La Opala Melamine Dinner Set|Unbreakable. Easy care. Great for gifting.|2899|1199|Maroon|FREE|50|4063|41",
//            "dinnerware_serveware|Clay Craft Stainless Steel Thali Set|6 pieces. Easy care. Great for gifting.|7299|4299|Multi|FREE|102|1951|54",
//            "dinnerware_serveware|Home Centre Serving Platter|Wooden. Easy care. Great for gifting.|5499|3299|Grey|FREE|124|2478|97",
//            "dinnerware_serveware|Cello Glass Casserole Set|Microwave safe. Easy care. Great for gifting.|7499|4599|Teal|FREE|210|2453|66",
//            // ---- cups_mugs
//            "cups_mugs|Chumbak Ceramic Coffee Mugs Set of 2|350 ml. Easy care. Great for gifting.|1299|759|Beige|FREE|200|2520|20",
//            "cups_mugs|Borosil Double Wall Glass Mugs|Insulated. Easy care. Great for gifting.|989|659|Beige|FREE|169|1481|80",
//            "cups_mugs|Clay Craft Printed Quirky Mug|Gift ready. Easy care. Great for gifting.|579|359|Beige|FREE|207|1160|100",
//            "cups_mugs|Home Centre Tea Cups Set of 6|Fine bone china. Easy care. Great for gifting.|899|539|White|FREE|167|3196|55",
//            "cups_mugs|Femora Travel Mug|Spill proof. Easy care. Great for gifting.|2099|949|Brown|FREE|125|3014|116",
//            "cups_mugs|Cello Stoneware Mug|Handmade glaze. Easy care. Great for gifting.|1699|789|Green|FREE|33|1678|85",
//            // ---- bakeware_cookware
//            "bakeware_cookware|Prestige Non-Stick Cookware Set 3 Pieces|Induction base. Easy care. Great for gifting.|7699|4199|Multi|FREE|45|2442|15",
//            "bakeware_cookware|Hawkins Pressure Cooker 5L|Aluminium. Easy care. Great for gifting.|5099|2799|Mustard|FREE|84|1534|64",
//            "bakeware_cookware|Pigeon Cast Iron Tawa|Pre-seasoned. Easy care. Great for gifting.|9199|4099|Brown|FREE|32|2424|108",
//            "bakeware_cookware|Wonderchef Silicone Baking Moulds Set|Oven safe. Easy care. Great for gifting.|6999|3399|Grey|FREE|168|2823|47",
//            "bakeware_cookware|Borosil Stainless Steel Kadai|Triply. Easy care. Great for gifting.|6199|3099|White|FREE|61|4581|30",
//            "bakeware_cookware|Meyer Glass Baking Dish|Borosilicate. Easy care. Great for gifting.|4899|3499|Grey|FREE|90|4056|92",
//            // ---- kitchen_storage_tools
//            "kitchen_storage_tools|Tupperware Airtight Containers Set of 6|BPA free. Easy care. Great for gifting.|2199|1699|Brown|FREE|222|2335|44",
//            "kitchen_storage_tools|Cello Spice Rack Organiser|Rotating. Easy care. Great for gifting.|2499|1199|White|FREE|49|3769|61",
//            "kitchen_storage_tools|Milton Glass Storage Jars|Bamboo lids. Easy care. Great for gifting.|3099|1999|Beige|FREE|244|3890|57",
//            "kitchen_storage_tools|Prestige Chopping Board|Bamboo. Easy care. Great for gifting.|809|549|Beige|FREE|29|3605|17",
//            "kitchen_storage_tools|Borosil Kitchen Tools Set|Silicone. Easy care. Great for gifting.|729|449|Teal|FREE|157|1879|103",
//            "kitchen_storage_tools|Solimo Vegetable Chopper|Pull cord. Easy care. Great for gifting.|1999|1299|Grey|FREE|61|3936|10",
//            // ---- bar_drinkware
//            "bar_drinkware|Borosil Whiskey Glasses Set of 6|Heavy base. Easy care. Great for gifting.|2099|729|Brown|FREE|108|4503|101",
//            "bar_drinkware|Ocean Wine Glasses Set of 4|Crystal clear. Easy care. Great for gifting.|2399|1599|Multi|FREE|37|2218|86",
//            "bar_drinkware|Home Centre Cocktail Shaker Set|Stainless steel. Easy care. Great for gifting.|3299|1899|White|FREE|228|1564|23",
//            "bar_drinkware|Luminarc Beer Mugs Set|500 ml. Easy care. Great for gifting.|1599|709|Blue|FREE|82|95|31",
//            "bar_drinkware|Femora Decanter with Glasses|Gift set. Easy care. Great for gifting.|1899|1099|Mustard|FREE|16|4323|67",
//            "bar_drinkware|Treo Juice Glasses Set of 6|Everyday. Easy care. Great for gifting.|2699|1499|Mustard|FREE|96|4136|102",
//            // ---- table_covers_furnishings
//            "table_covers_furnishings|Home Centre Table Cover 6 Seater|Waterproof. Easy care. Great for gifting.|749|449|Green|FREE|139|4536|95",
//            "table_covers_furnishings|Story@Home Cotton Table Cloth|Printed. Easy care. Great for gifting.|1299|759|Maroon|FREE|242|3528|86",
//            "table_covers_furnishings|Kuber Industries Placemats Set of 6|Woven. Easy care. Great for gifting.|949|509|Cream|FREE|164|487|14",
//            "table_covers_furnishings|Swayam Coasters Set of 6|Cork. Easy care. Great for gifting.|2199|1099|Cream|FREE|229|1636|21",
//            "table_covers_furnishings|Spaces Lace Table Cover|Elegant lace. Easy care. Great for gifting.|599|479|Beige|FREE|74|2078|34",
//            "table_covers_furnishings|Lushomes Table Napkins Set|Cotton. Easy care. Great for gifting.|1999|1199|Maroon|FREE|120|2887|51",
//            // ---- mi_mobile
//            "mi_mobile|Xiaomi Redmi Note 13 Pro 5G (8GB RAM, 256GB)|6.67\" 120Hz AMOLED, 200MP camera, 5100mAh, 67W charging. 1 year manufacturer warranty. Box includes charging cable.|27999|25999|Grey|FREE|105|1305|64",
//            "mi_mobile|Xiaomi Redmi 13C 5G (4GB RAM, 128GB)|6.74\" 90Hz display, 50MP AI camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|10999|9999|Black|FREE|129|296|98",
//            "mi_mobile|Xiaomi 14 Civi (12GB RAM, 512GB)|Leica optics, Snapdragon 8s Gen 3, 4700mAh. 1 year manufacturer warranty. Box includes charging cable.|52499|42999|Black|FREE|74|1631|5",
//            "mi_mobile|Xiaomi Redmi Note 13 5G (6GB RAM, 128GB)|108MP camera, Dimensity 6080, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|21499|17999|Silver|FREE|25|2716|59",
//            "mi_mobile|Xiaomi Redmi 13 5G (8GB RAM, 128GB)|108MP camera, Snapdragon 4 Gen 2. 1 year manufacturer warranty. Box includes charging cable.|15399|13999|Grey|FREE|97|4121|90",
//            "mi_mobile|Xiaomi Redmi A3 (3GB RAM, 64GB)|6.71\" 90Hz, 5000mAh, budget everyday phone. 1 year manufacturer warranty. Box includes charging cable.|7999|7299|Gold|FREE|204|898|0",
//            // ---- realme_mobile
//            "realme_mobile|realme 12 Pro+ 5G (8GB RAM, 256GB)|Periscope portrait camera, curved AMOLED. 1 year manufacturer warranty. Box includes charging cable.|35799|29999|Blue|FREE|19|3082|12",
//            "realme_mobile|realme 12x 5G (6GB RAM, 128GB)|45W SuperVOOC, 120Hz display. 1 year manufacturer warranty. Box includes charging cable.|13099|11999|Black|FREE|47|1799|72",
//            "realme_mobile|realme GT 6T (8GB RAM, 128GB)|Snapdragon 7+ Gen 3, 5500mAh, 120W. 1 year manufacturer warranty. Box includes charging cable.|32299|30999|White|FREE|107|1128|1",
//            "realme_mobile|realme C65 (4GB RAM, 128GB)|50MP AI camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|12899|10499|Gold|FREE|228|2176|102",
//            "realme_mobile|realme 13 Pro 5G (8GB RAM, 256GB)|Sony LYT-600 camera, AMOLED. 1 year manufacturer warranty. Box includes charging cable.|32599|26999|Blue|FREE|125|557|69",
//            "realme_mobile|realme Narzo 70 Pro 5G (8GB RAM, 128GB)|Air gesture, 50MP Sony IMX890. 1 year manufacturer warranty. Box includes charging cable.|22999|19999|Silver|FREE|33|1793|43",
//            // ---- samsung_mobile
//            "samsung_mobile|Samsung Galaxy S24 Ultra (12GB RAM, 256GB)|Built-in S Pen, 200MP camera, Galaxy AI. 1 year manufacturer warranty. Box includes charging cable.|145299|121999|Gold|FREE|235|1092|59",
//            "samsung_mobile|Samsung Galaxy S24 (8GB RAM, 256GB)|6.2\" Dynamic AMOLED, Exynos 2400. 1 year manufacturer warranty. Box includes charging cable.|86299|74999|Black|FREE|60|1270|16",
//            "samsung_mobile|Samsung Galaxy A55 5G (8GB RAM, 128GB)|IP67, 50MP OIS camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|43499|39999|Gold|FREE|59|3223|43",
//            "samsung_mobile|Samsung Galaxy M35 5G (6GB RAM, 128GB)|6000mAh battery, Super AMOLED. 1 year manufacturer warranty. Box includes charging cable.|19399|17999|Silver|FREE|137|1373|63",
//            "samsung_mobile|Samsung Galaxy Z Flip6 (12GB RAM, 256GB)|Foldable, 3.4\" FlexWindow. 1 year manufacturer warranty. Box includes charging cable.|124999|109999|White|FREE|154|1221|82",
//            "samsung_mobile|Samsung Galaxy F15 5G (4GB RAM, 128GB)|6000mAh, sAMOLED display. 1 year manufacturer warranty. Box includes charging cable.|14799|12999|Grey|FREE|114|3326|10",
//            // ---- infinix_mobile
//            "infinix_mobile|Infinix Note 40 Pro 5G (8GB RAM, 256GB)|108MP camera, 3D curved AMOLED, magnetic charging. 1 year manufacturer warranty. Box includes charging cable.|24799|21999|Grey|FREE|61|2898|71",
//            "infinix_mobile|Infinix Hot 40i (8GB RAM, 256GB)|6.56\" 90Hz, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|10699|9499|Green|FREE|206|4610|86",
//            "infinix_mobile|Infinix GT 20 Pro 5G (8GB RAM, 256GB)|Gaming phone, Dimensity 8200 Ultimate. 1 year manufacturer warranty. Box includes charging cable.|26599|24999|Grey|FREE|221|4315|116",
//            "infinix_mobile|Infinix Zero 30 5G (12GB RAM, 256GB)|4K 60fps selfie camera. 1 year manufacturer warranty. Box includes charging cable.|25299|23999|Blue|FREE|33|4525|17",
//            "infinix_mobile|Infinix Smart 8 HD (3GB RAM, 64GB)|Budget, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|7799|6499|Silver|FREE|128|2607|32",
//            "infinix_mobile|Infinix Note 40 5G (8GB RAM, 256GB)|120Hz AMOLED, 33W charging. 1 year manufacturer warranty. Box includes charging cable.|21699|17999|Gold|FREE|94|3604|21",
//            // ---- oppo_mobile
//            "oppo_mobile|OPPO Reno12 Pro 5G (12GB RAM, 256GB)|50MP portrait camera, AI eraser. 1 year manufacturer warranty. Box includes charging cable.|43599|36999|Black|FREE|147|937|115",
//            "oppo_mobile|OPPO F27 Pro+ 5G (8GB RAM, 128GB)|IP69 rated, cool curved display. 1 year manufacturer warranty. Box includes charging cable.|31899|27999|Grey|FREE|195|4392|120",
//            "oppo_mobile|OPPO A79 5G (8GB RAM, 128GB)|33W SUPERVOOC, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|24399|19999|Green|FREE|168|1529|108",
//            "oppo_mobile|OPPO Reno11 5G (8GB RAM, 256GB)|Ultra-clear portrait camera. 1 year manufacturer warranty. Box includes charging cable.|32299|29999|White|FREE|246|1237|41",
//            "oppo_mobile|OPPO A3x 5G (4GB RAM, 64GB)|Military-grade drop resistance. 1 year manufacturer warranty. Box includes charging cable.|15299|12499|Black|FREE|183|2639|31",
//            "oppo_mobile|OPPO K12x 5G (6GB RAM, 128GB)|360° damage-proof armour body. 1 year manufacturer warranty. Box includes charging cable.|15499|12999|Blue|FREE|213|3972|103",
//            // ---- apple_mobile
//            "apple_mobile|Apple iPhone 15 (128GB)|Dynamic Island, 48MP main camera, USB-C. 1 year manufacturer warranty. Box includes charging cable.|85299|69900|Black|FREE|64|173|12",
//            "apple_mobile|Apple iPhone 15 Plus (256GB)|6.7\" display, all-day battery. 1 year manufacturer warranty. Box includes charging cable.|99899|89900|Blue|FREE|104|2236|14",
//            "apple_mobile|Apple iPhone 15 Pro (256GB)|Titanium design, A17 Pro chip. 1 year manufacturer warranty. Box includes charging cable.|158499|129900|Gold|FREE|211|2623|2",
//            "apple_mobile|Apple iPhone 15 Pro Max (512GB)|5x telephoto, titanium. 1 year manufacturer warranty. Box includes charging cable.|168399|159900|White|FREE|109|1745|65",
//            "apple_mobile|Apple iPhone 14 (128GB)|A15 Bionic, Crash Detection. 1 year manufacturer warranty. Box includes charging cable.|63799|59900|Gold|FREE|214|429|51",
//            "apple_mobile|Apple iPhone 13 (128GB)|Dual camera, A15 Bionic. 1 year manufacturer warranty. Box includes charging cable.|58799|49900|Silver|FREE|71|1442|71",
//            // ---- vivo_mobile
//            "vivo_mobile|vivo V30 Pro 5G (12GB RAM, 512GB)|ZEISS camera, Aura light portrait. 1 year manufacturer warranty. Box includes charging cable.|52299|46999|Black|FREE|18|4077|24",
//            "vivo_mobile|vivo T3 5G (8GB RAM, 128GB)|Dimensity 7200, 50MP Sony IMX882. 1 year manufacturer warranty. Box includes charging cable.|23599|19999|Gold|FREE|237|2429|11",
//            "vivo_mobile|vivo Y28s 5G (6GB RAM, 128GB)|5000mAh, 90Hz. 1 year manufacturer warranty. Box includes charging cable.|16899|15499|Black|FREE|182|543|39",
//            "vivo_mobile|vivo X100 Pro (16GB RAM, 512GB)|ZEISS APO telephoto. 1 year manufacturer warranty. Box includes charging cable.|93799|89999|Black|FREE|71|3676|55",
//            "vivo_mobile|vivo Y18 (4GB RAM, 128GB)|Budget, 50MP camera. 1 year manufacturer warranty. Box includes charging cable.|10599|8999|Gold|FREE|18|1630|63",
//            "vivo_mobile|vivo T3 Ultra 5G (8GB RAM, 256GB)|3D curved AMOLED. 1 year manufacturer warranty. Box includes charging cable.|37299|31999|Grey|FREE|195|205|116",
//            // ---- honor_mobile
//            "honor_mobile|Honor 200 Pro 5G (12GB RAM, 512GB)|Studio Harcourt portrait camera. 1 year manufacturer warranty. Box includes charging cable.|64499|57999|Green|FREE|243|4800|17",
//            "honor_mobile|Honor X9b 5G (8GB RAM, 256GB)|Ultra-bounce anti-drop display. 1 year manufacturer warranty. Box includes charging cable.|31799|25999|Blue|FREE|123|4066|57",
//            "honor_mobile|Honor Magic6 Pro (12GB RAM, 512GB)|180MP periscope camera. 1 year manufacturer warranty. Box includes charging cable.|103499|89999|Grey|FREE|76|125|87",
//            "honor_mobile|Honor 90 5G (8GB RAM, 256GB)|200MP main camera. 1 year manufacturer warranty. Box includes charging cable.|30499|27999|Blue|FREE|24|1042|73",
//            "honor_mobile|Honor X7b (8GB RAM, 128GB)|6000mAh battery. 1 year manufacturer warranty. Box includes charging cable.|17099|15999|Black|FREE|15|233|65",
//            "honor_mobile|Honor 200 5G (8GB RAM, 256GB)|Eye comfort OLED. 1 year manufacturer warranty. Box includes charging cable.|40299|34999|Blue|FREE|167|2702|102",
//            // ---- asus_mobile
//            "asus_mobile|ASUS ROG Phone 8 (12GB RAM, 256GB)|Gaming, Snapdragon 8 Gen 3, AirTrigger. 1 year manufacturer warranty. Box includes charging cable.|102199|94999|Silver|FREE|221|4669|21",
//            "asus_mobile|ASUS ROG Phone 8 Pro (16GB RAM, 512GB)|AniMe Vision display. 1 year manufacturer warranty. Box includes charging cable.|137999|119999|Silver|FREE|168|4113|34",
//            "asus_mobile|ASUS Zenfone 11 Ultra (12GB RAM, 256GB)|6.78\" AMOLED, gimbal stabiliser. 1 year manufacturer warranty. Box includes charging cable.|72999|69999|Black|FREE|81|1405|75",
//            "asus_mobile|ASUS ROG Phone 7 (12GB RAM, 256GB)|6000mAh, 165Hz. 1 year manufacturer warranty. Box includes charging cable.|89299|74999|Green|FREE|25|907|16",
//            "asus_mobile|ASUS Zenfone 10 (8GB RAM, 256GB)|Compact flagship. 1 year manufacturer warranty. Box includes charging cable.|67099|54999|Blue|FREE|181|2410|20",
//            "asus_mobile|ASUS ROG Phone 6 (12GB RAM, 256GB)|GameCool 6 cooling. 1 year manufacturer warranty. Box includes charging cable.|60299|49999|Green|FREE|33|4686|45",
//            // ---- poco_x2_mobile
//            "poco_x2_mobile|POCO X2 (6GB RAM, 64GB) Atlantis Blue|120Hz display, 64MP Sony camera. 1 year manufacturer warranty. Box includes charging cable.|18899|15999|Blue|FREE|235|4115|14",
//            "poco_x2_mobile|POCO X2 (6GB RAM, 128GB) Matrix Purple|Snapdragon 730G, 4500mAh. 1 year manufacturer warranty. Box includes charging cable.|17899|16999|Purple|FREE|220|280|47",
//            "poco_x2_mobile|POCO X2 (8GB RAM, 256GB) Phoenix Red|Dual selfie, 27W fast charging. 1 year manufacturer warranty. Box includes charging cable.|22299|19999|Red|FREE|142|3740|59",
//            "poco_x2_mobile|POCO X2 (6GB RAM, 128GB) Atlantis Blue|Liquid cooling. 1 year manufacturer warranty. Box includes charging cable.|18499|16999|Blue|FREE|186|1113|100",
//            "poco_x2_mobile|POCO X2 (8GB RAM, 256GB) Matrix Purple|Side fingerprint sensor. 1 year manufacturer warranty. Box includes charging cable.|21099|19999|Purple|FREE|148|1793|2",
//            "poco_x2_mobile|POCO X2 (6GB RAM, 64GB) Phoenix Red|6.67\" FHD+ RealityFlow display. 1 year manufacturer warranty. Box includes charging cable.|17599|15999|Red|FREE|19|1092|110",
//            // ---- realme_narzo_10_mobile
//            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 128GB) That Green|48MP quad camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|14799|12999|Green|FREE|76|4289|2",
//            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 128GB) That White|Helio G80 gaming processor. 1 year manufacturer warranty. Box includes charging cable.|13599|12999|White|FREE|232|1930|5",
//            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 64GB) That Blue|6.5\" mini-drop display. 1 year manufacturer warranty. Box includes charging cable.|13399|11999|Blue|FREE|109|820|73",
//            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 128GB) That Blue|18W fast charge. 1 year manufacturer warranty. Box includes charging cable.|15699|12999|Blue|FREE|148|563|118",
//            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 64GB) That Green|Reverse charging. 1 year manufacturer warranty. Box includes charging cable.|14299|11999|Green|FREE|105|444|99",
//            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 64GB) That White|Ultra macro lens. 1 year manufacturer warranty. Box includes charging cable.|14699|11999|White|FREE|18|1066|92",
//            // ---- infinix_hot_9_mobile
//            "infinix_hot_9_mobile|Infinix Hot 9 (4GB RAM, 64GB) Ocean Wave|6.6\" punch-hole, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|9999|8999|Blue|FREE|66|4783|88",
//            "infinix_hot_9_mobile|Infinix Hot 9 (4GB RAM, 64GB) Violet|16MP quad camera. 1 year manufacturer warranty. Box includes charging cable.|9699|8999|Purple|FREE|131|3705|72",
//            "infinix_hot_9_mobile|Infinix Hot 9 Pro (4GB RAM, 64GB) Quetzal Cyan|48MP camera. 1 year manufacturer warranty. Box includes charging cable.|11499|9999|Teal|FREE|76|3924|79",
//            "infinix_hot_9_mobile|Infinix Hot 9 (4GB RAM, 64GB) Quetzal Cyan|Helio A25. 1 year manufacturer warranty. Box includes charging cable.|9999|8999|Teal|FREE|221|4449|13",
//            "infinix_hot_9_mobile|Infinix Hot 9 Pro (4GB RAM, 64GB) Violet|Fingerprint sensor. 1 year manufacturer warranty. Box includes charging cable.|10599|9999|Purple|FREE|209|775|92",
//            "infinix_hot_9_mobile|Infinix Hot 9 Pro (4GB RAM, 64GB) Ocean Wave|Dual SIM 4G. 1 year manufacturer warranty. Box includes charging cable.|11299|9999|Blue|FREE|50|298|1",
//            // ---- iqoo_3_mobile
//            "iqoo_3_mobile|iQOO 3 5G (8GB RAM, 256GB) Volcano Orange|Snapdragon 865, 55W FlashCharge. 1 year manufacturer warranty. Box includes charging cable.|42699|34990|Orange|FREE|242|3265|35",
//            "iqoo_3_mobile|iQOO 3 (8GB RAM, 128GB) Tornado Black|Pressure-sensitive shoulder keys. 1 year manufacturer warranty. Box includes charging cable.|33399|29990|Black|FREE|232|3872|60",
//            "iqoo_3_mobile|iQOO 3 (12GB RAM, 256GB) Quantum Silver|4400mAh, quad camera. 1 year manufacturer warranty. Box includes charging cable.|44099|36990|Silver|FREE|26|3467|73",
//            "iqoo_3_mobile|iQOO 3 5G (12GB RAM, 256GB) Tornado Black|UFS 3.1 storage. 1 year manufacturer warranty. Box includes charging cable.|43899|38990|Black|FREE|63|2277|34",
//            "iqoo_3_mobile|iQOO 3 (8GB RAM, 256GB) Quantum Silver|Super AMOLED. 1 year manufacturer warranty. Box includes charging cable.|35999|31990|Silver|FREE|172|708|28",
//            "iqoo_3_mobile|iQOO 3 (8GB RAM, 128GB) Volcano Orange|Dual-turbo performance. 1 year manufacturer warranty. Box includes charging cable.|32599|29990|Orange|FREE|97|1687|71",
//            // ---- iphone_se_mobile
//            "iphone_se_mobile|Apple iPhone SE 3rd Gen (64GB) Midnight|A15 Bionic, 5G, Touch ID. 1 year manufacturer warranty. Box includes charging cable.|48299|43900|Black|FREE|215|3394|98",
//            "iphone_se_mobile|Apple iPhone SE 3rd Gen (128GB) Starlight|4.7\" Retina HD. 1 year manufacturer warranty. Box includes charging cable.|54399|48900|Off White|FREE|211|4017|6",
//            "iphone_se_mobile|Apple iPhone SE 3rd Gen (256GB) Product Red|12MP camera. 1 year manufacturer warranty. Box includes charging cable.|69299|58900|Red|FREE|38|3521|88",
//            "iphone_se_mobile|Apple iPhone SE 3rd Gen (128GB) Midnight|IP67. 1 year manufacturer warranty. Box includes charging cable.|52599|48900|Black|FREE|208|3530|62",
//            "iphone_se_mobile|Apple iPhone SE 3rd Gen (64GB) Starlight|Wireless charging. 1 year manufacturer warranty. Box includes charging cable.|50499|43900|Off White|FREE|219|2736|19",
//            "iphone_se_mobile|Apple iPhone SE 2nd Gen (128GB) White|A13 Bionic. 1 year manufacturer warranty. Box includes charging cable.|37499|32999|White|FREE|238|838|17",
//            // ---- motorola_razr_mobile
//            "motorola_razr_mobile|Motorola razr 50 Ultra (12GB RAM, 512GB)|4\" external display, flip. 1 year manufacturer warranty. Box includes charging cable.|114999|99999|Silver|FREE|43|4741|5",
//            "motorola_razr_mobile|Motorola razr 50 (8GB RAM, 256GB)|3.6\" external display. 1 year manufacturer warranty. Box includes charging cable.|72299|64999|Black|FREE|125|2518|23",
//            "motorola_razr_mobile|Motorola razr 40 Ultra (8GB RAM, 256GB)|165Hz pOLED. 1 year manufacturer warranty. Box includes charging cable.|77799|69999|Blue|FREE|106|1375|57",
//            "motorola_razr_mobile|Motorola razr 40 (8GB RAM, 256GB)|Vegan leather finish. 1 year manufacturer warranty. Box includes charging cable.|57499|49999|White|FREE|168|1191|1",
//            "motorola_razr_mobile|Motorola razr 50 Ultra (12GB RAM, 512GB) Spring Green|Gemini AI. 1 year manufacturer warranty. Box includes charging cable.|111199|99999|Green|FREE|151|2054|65",
//            "motorola_razr_mobile|Motorola razr 40 Ultra (8GB RAM, 256GB) Viva Magenta|Flex view. 1 year manufacturer warranty. Box includes charging cable.|85399|69999|Magenta|FREE|173|3424|102",
//            // ---- realme_narzo_10a_mobile
//            "realme_narzo_10a_mobile|realme Narzo 10A (3GB RAM, 32GB) So Blue|5000mAh, triple camera. 1 year manufacturer warranty. Box includes charging cable.|9299|8499|Blue|FREE|122|2956|93",
//            "realme_narzo_10a_mobile|realme Narzo 10A (4GB RAM, 64GB) So White|Helio G70. 1 year manufacturer warranty. Box includes charging cable.|10999|9499|White|FREE|91|4799|28",
//            "realme_narzo_10a_mobile|realme Narzo 10A (3GB RAM, 32GB) So White|6.5\" display. 1 year manufacturer warranty. Box includes charging cable.|8999|8499|White|FREE|180|2205|5",
//            "realme_narzo_10a_mobile|realme Narzo 10A (4GB RAM, 64GB) So Blue|Reverse charging. 1 year manufacturer warranty. Box includes charging cable.|10199|9499|Blue|FREE|148|3201|11",
//            "realme_narzo_10a_mobile|realme Narzo 10A (4GB RAM, 64GB) So Blue Bundle|With back cover. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|30|152|32",
//            "realme_narzo_10a_mobile|realme Narzo 10A (3GB RAM, 32GB) So Blue Combo|With screen guard. 1 year manufacturer warranty. Box includes charging cable.|8899|8499|Blue|FREE|169|1938|53",
//            // ---- motorola_g8_power_lite_mobile
//            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Royal Blue|5000mAh, 3-day battery. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|220|1418|119",
//            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Arctic Blue|16MP triple camera. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|200|2370|88",
//            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Royal Blue Combo|With case. 1 year manufacturer warranty. Box includes charging cable.|9999|9499|Blue|FREE|186|2580|56",
//            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Arctic Blue Combo|With screen guard. 1 year manufacturer warranty. Box includes charging cable.|10999|9499|Blue|FREE|167|3894|98",
//            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Polar Blue|Android 9 Pie. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|176|3030|57",
//            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Steel Grey|Water-repellent design. 1 year manufacturer warranty. Box includes charging cable.|11099|9499|Grey|FREE|237|1791|115",
//            // ---- mobile_cases
//            "mobile_cases|Spigen Tough Armor Case for iPhone 15|Military-grade drop protection. Quality checked. Easy returns.|2399|1499|White|FREE|110|4264|82",
//            "mobile_cases|Casekaro Silicone Case for Galaxy S24|Soft-touch. Quality checked. Easy returns.|809|409|Black|FREE|44|1462|110",
//            "mobile_cases|DailyObjects Clear MagSafe Case|Magnetic. Quality checked. Easy returns.|1899|1399|Green|FREE|51|3018|119",
//            "mobile_cases|Ringke Leather Wallet Flip Cover|Card slots. Quality checked. Easy returns.|859|459|Beige|FREE|113|2007|47",
//            "mobile_cases|Apple Kickstand Rugged Case|Built-in stand. Quality checked. Easy returns.|2999|1499|Beige|FREE|135|2424|98",
//            "mobile_cases|Samsung Printed Designer Case|Matte print. Quality checked. Easy returns.|2499|1499|White|FREE|18|1932|29",
//            // ---- headphones_headsets
//            "headphones_headsets|Sony WH-1000XM5 Wireless ANC Headphones|Industry-leading noise cancelling, 30h battery. 1 year manufacturer warranty.|46199|29990|Gold|FREE|155|1662|33",
//            "headphones_headsets|boAt Rockerz 450 Bluetooth Headphones|15h playback, padded ear cushions. 1 year manufacturer warranty.|2299|1499|Grey|FREE|43|3749|39",
//            "headphones_headsets|JBL Tune 760NC Over-Ear Headphones|Active noise cancelling. 1 year manufacturer warranty.|6899|5999|White|FREE|17|558|26",
//            "headphones_headsets|Sennheiser HD 450BT Wireless Headphones|Bass boost, ANC. 1 year manufacturer warranty.|11499|9990|Blue|FREE|134|2879|118",
//            "headphones_headsets|Bose QuietComfort Ultra Headphones|Immersive spatial audio. 1 year manufacturer warranty.|40399|35900|Black|FREE|140|851|78",
//            "headphones_headsets|OnePlus Bullets Wireless Z2 Neckband|30h battery, fast charge. 1 year manufacturer warranty.|2299|1999|Blue|FREE|69|1733|3",
//            // ---- power_banks
//            "power_banks|Mi 20000mAh Power Bank 22.5W|Dual USB + Type-C. 1 year manufacturer warranty.|2299|1599|Grey|FREE|52|4094|33",
//            "power_banks|Ambrane 10000mAh Slim Power Bank|Pocket size. 1 year manufacturer warranty.|2199|1799|White|FREE|102|3882|118",
//            "power_banks|Anker MagSafe Magnetic Power Bank 5000mAh|Wireless. 1 year manufacturer warranty.|4399|3499|Grey|FREE|168|3247|107",
//            "power_banks|boAt Laptop Power Bank 25000mAh 65W|PD fast charge. 1 year manufacturer warranty.|1299|889|Silver|FREE|160|2356|87",
//            "power_banks|Samsung Compact 10000mAh with Built-in Cable|No extra cable. 1 year manufacturer warranty.|2799|2199|Gold|FREE|174|2962|19",
//            "power_banks|URBN Solar Power Bank 20000mAh|Outdoor rugged. 1 year manufacturer warranty.|1099|759|Blue|FREE|200|3986|102",
//            // ---- screenguards
//            "screenguards|Spigen Tempered Glass for iPhone 15|9H hardness, edge-to-edge. Quality checked. Easy returns.|869|689|Beige|FREE|193|949|95",
//            "screenguards|Gorilla Privacy Screen Guard|Anti-spy. Quality checked. Easy returns.|1599|939|Gold|FREE|231|2004|37",
//            "screenguards|Tempered Glass Co Matte Anti-Glare Guard|Fingerprint resistant. Quality checked. Easy returns.|1699|659|Navy Blue|FREE|154|2347|21",
//            "screenguards|Ringke Camera Lens Protector|Full coverage. Quality checked. Easy returns.|1399|849|Navy Blue|FREE|46|3387|87",
//            "screenguards|Belkin UV Glass for Curved Display|Liquid glue. Quality checked. Easy returns.|1899|919|Mustard|FREE|100|642|55",
//            "screenguards|OtterBox Hydrogel Film Pack of 2|Self-healing. Quality checked. Easy returns.|309|169|Olive|FREE|53|4497|70",
//            // ---- memory_cards
//            "memory_cards|SanDisk Ultra microSD 128GB Class 10|Up to 140MB/s. 1 year manufacturer warranty.|2999|2399|Silver|FREE|178|1547|20",
//            "memory_cards|Samsung EVO Plus microSD 256GB|4K ready. 1 year manufacturer warranty.|3699|2499|Silver|FREE|246|2664|72",
//            "memory_cards|Kingston Extreme microSD 64GB|For action cameras. 1 year manufacturer warranty.|809|659|Silver|FREE|52|4214|38",
//            "memory_cards|HP Canvas Go microSD 512GB|A2 rated. 1 year manufacturer warranty.|1699|1499|White|FREE|96|964|103",
//            "memory_cards|Lexar SD Card 64GB for DSLR|UHS-I. 1 year manufacturer warranty.|3299|2599|Green|FREE|158|4039|18",
//            "memory_cards|Sony microSD 32GB with Adapter|Everyday. 1 year manufacturer warranty.|3099|2199|Grey|FREE|25|595|92",
//            // ---- smart_headphones
//            "smart_headphones|Apple AirPods Pro (2nd Gen)|Adaptive audio, USB-C. 1 year manufacturer warranty.|27999|24900|Grey|FREE|50|1947|52",
//            "smart_headphones|Samsung Galaxy Buds2 Pro|24-bit Hi-Fi, ANC. 1 year manufacturer warranty.|17399|11999|Blue|FREE|51|2465|17",
//            "smart_headphones|OnePlus Buds 3 TWS|49dB ANC. 1 year manufacturer warranty.|6399|5499|Black|FREE|40|2711|51",
//            "smart_headphones|Nothing Ear (a) TWS|Transparent design. 1 year manufacturer warranty.|8899|5999|Green|FREE|238|3032|82",
//            "smart_headphones|Sony WF-1000XM5 Earbuds|Best-in-class ANC. 1 year manufacturer warranty.|23799|19990|Grey|FREE|80|680|78",
//            "smart_headphones|boAt Airdopes 141 TWS|42h playback. 1 year manufacturer warranty.|1799|1299|Green|FREE|100|3335|90",
//            // ---- mobile_cables
//            "mobile_cables|Anker USB-C to USB-C Cable 1.5m 100W|Braided. 1 year manufacturer warranty.|999|759|Grey|FREE|21|1742|51",
//            "mobile_cables|Belkin Lightning to USB-C Cable|MFi certified. 1 year manufacturer warranty.|1599|1399|White|FREE|183|1318|47",
//            "mobile_cables|Apple 3-in-1 Multi Charging Cable|Type-C, Micro, Lightning. 1 year manufacturer warranty.|719|589|White|FREE|103|3811|88",
//            "mobile_cables|Ambrane Magnetic Charging Cable|Easy snap. 1 year manufacturer warranty.|1699|1399|Black|FREE|93|979|55",
//            "mobile_cables|boAt Fast Charging Type-C Cable 2m|65W. 1 year manufacturer warranty.|689|569|Blue|FREE|177|1553|25",
//            "mobile_cables|Mi Retractable Charging Cable|Travel friendly. 1 year manufacturer warranty.|639|469|Silver|FREE|170|2540|85",
//            // ---- mobile_chargers
//            "mobile_chargers|Apple 20W USB-C Power Adapter|Fast charge. 1 year manufacturer warranty.|2299|1799|White|FREE|35|3779|54",
//            "mobile_chargers|Samsung 45W Super Fast Charger|PPS support. 1 year manufacturer warranty.|2599|2099|White|FREE|234|4239|33",
//            "mobile_chargers|Anker 65W GaN Charger 3-Port|Laptop + phone. 1 year manufacturer warranty.|3199|2699|Grey|FREE|54|767|44",
//            "mobile_chargers|Mi Wireless Charging Pad 15W|Qi certified. 1 year manufacturer warranty.|3199|2499|White|FREE|19|463|56",
//            "mobile_chargers|Ambrane 3-in-1 Wireless Charging Stand|Phone, watch, buds. 1 year manufacturer warranty.|2699|1799|Green|FREE|43|241|13",
//            "mobile_chargers|Belkin Car Charger Dual Port|PD 30W. 1 year manufacturer warranty.|3499|2799|Black|FREE|117|1146|74",
//            // ---- mobile_holders
//            "mobile_holders|Portronics Car Dashboard Phone Holder|360° rotation. Quality checked. Easy returns.|2199|769|Beige|FREE|211|2765|75",
//            "mobile_holders|Spigen Bike Phone Mount|Anti-shake. Quality checked. Easy returns.|2999|1399|Mustard|FREE|121|2134|28",
//            "mobile_holders|Ambrane Desk Phone Stand|Adjustable aluminium. Quality checked. Easy returns.|2699|1199|Brown|FREE|172|4028|10",
//            "mobile_holders|STRIFF Magnetic Car Vent Mount|Strong magnet. Quality checked. Easy returns.|569|449|Grey Melange|FREE|236|3502|85",
//            "mobile_holders|Belkin Bed Gooseneck Holder|Flexible arm. Quality checked. Easy returns.|1399|929|Charcoal|FREE|142|4646|7",
//            "mobile_holders|boAt Tripod with Phone Holder|Bluetooth remote. Quality checked. Easy returns.|2999|1499|Maroon|FREE|199|702|94",
//            // ---- smart_watches
//            "smart_watches|Apple Watch Series 9 GPS 45mm|Always-on Retina, ECG, S9 chip. 1 year manufacturer warranty.|46099|41900|Black|FREE|228|1046|88",
//            "smart_watches|Samsung Galaxy Watch6 Classic 47mm|Rotating bezel, BP & ECG. 1 year manufacturer warranty.|45699|36999|Green|FREE|159|4655|93",
//            "smart_watches|Noise ColorFit Pro 5 Smartwatch|1.85\" AMOLED, Bluetooth calling, 7-day battery. 1 year manufacturer warranty.|5499|3999|Green|FREE|64|2808|86",
//            "smart_watches|boAt Wave Call 2 Smartwatch|1.83\" display, 700+ active modes. 1 year manufacturer warranty.|2099|1499|Silver|FREE|220|3768|24",
//            "smart_watches|Fire-Boltt Phoenix Ultra Smartwatch|Stainless steel, AMOLED. 1 year manufacturer warranty.|3899|2499|Silver|FREE|196|1294|85",
//            "smart_watches|Amazfit GTR 4 Smartwatch|Dual-band GPS, 14-day battery. 1 year manufacturer warranty.|22399|16999|Blue|FREE|233|2695|23",
//            // ---- smart_glasses_vr
//            "smart_glasses_vr|Meta Quest 3 VR Headset 128GB|Mixed reality. 1 year manufacturer warranty.|66699|49999|Black|FREE|109|1519|1",
//            "smart_glasses_vr|Lenskart Phonic Smart Audio Glasses|Open-ear audio, UV protection. 1 year manufacturer warranty.|5299|3499|White|FREE|74|3049|49",
//            "smart_glasses_vr|Noise i1 Smart Glasses|Touch controls. 1 year manufacturer warranty.|7199|5999|White|FREE|186|3049|119",
//            "smart_glasses_vr|boAt Lyte Smart Audio Glasses|Polarised lenses. 1 year manufacturer warranty.|3399|2999|Gold|FREE|25|4118|43",
//            "smart_glasses_vr|Sony VR Box Headset for Phones|3D movies and games. 1 year manufacturer warranty.|1499|999|Silver|FREE|33|903|29",
//            "smart_glasses_vr|Fastrack Ray-Ban Meta Smart Glasses|Camera and audio. 1 year manufacturer warranty.|34399|29900|Blue|FREE|122|569|39",
//            // ---- smart_bands
//            "smart_bands|Xiaomi Smart Band 8|1.62\" AMOLED, 16-day battery. 1 year manufacturer warranty.|4999|3499|Black|FREE|156|4643|36",
//            "smart_bands|Fitbit Inspire 3 Fitness Tracker|Heart rate, sleep. 1 year manufacturer warranty.|10999|8999|Blue|FREE|140|2822|28",
//            "smart_bands|Huawei Band 9|Ultra thin. 1 year manufacturer warranty.|5999|3999|Blue|FREE|126|4490|117",
//            "smart_bands|Amazfit Band 7|120 sports modes. 1 year manufacturer warranty.|4399|3499|Black|FREE|71|3792|75",
//            "smart_bands|Noise ColorFit Band|SpO2 tracking. 1 year manufacturer warranty.|2699|1799|Green|FREE|103|3619|67",
//            "smart_bands|Samsung Galaxy Fit3|1.6\" AMOLED. 1 year manufacturer warranty.|5899|4999|Grey|FREE|188|1499|54",
//            // ---- gaming_laptops
//            "gaming_laptops|ASUS ROG Strix G16 (i7-13650HX, RTX 4060, 16GB, 1TB)|16\" FHD+ 165Hz, Windows 11. 1 year onsite warranty. Windows/macOS preinstalled.|151199|129990|Black|FREE|202|2382|28",
//            "gaming_laptops|Lenovo Legion 5 Pro (Ryzen 7 7745HX, RTX 4070, 16GB, 1TB)|16\" WQXGA 240Hz. 1 year onsite warranty. Windows/macOS preinstalled.|170499|149990|Black|FREE|220|3455|36",
//            "gaming_laptops|HP Victus 15 (i5-12450H, RTX 3050, 16GB, 512GB)|15.6\" FHD 144Hz. 1 year onsite warranty. Windows/macOS preinstalled.|77399|64990|Blue|FREE|93|3382|115",
//            "gaming_laptops|Acer Nitro V 15 (i5-13420H, RTX 4050, 16GB, 512GB)|15.6\" FHD 144Hz. 1 year onsite warranty. Windows/macOS preinstalled.|99999|74990|Silver|FREE|121|4777|43",
//            "gaming_laptops|MSI Katana 15 (i7-13620H, RTX 4060, 16GB, 1TB)|15.6\" FHD 144Hz. 1 year onsite warranty. Windows/macOS preinstalled.|127899|109990|Blue|FREE|47|2825|28",
//            "gaming_laptops|Dell G15 Gaming (Ryzen 5 7640HS, RTX 3050, 16GB, 512GB)|15.6\" 120Hz. 1 year onsite warranty. Windows/macOS preinstalled.|87999|72990|Blue|FREE|198|1008|80",
//            "gaming_laptops|ASUS TUF Gaming F15 (i5-11400H, RTX 2050, 8GB, 512GB)|Military-grade durability. 1 year onsite warranty. Windows/macOS preinstalled.|77099|56990|Grey|FREE|170|1608|110",
//            // ---- desktop_pcs
//            "desktop_pcs|HP All-in-One 24\" (i5-1235U, 8GB, 512GB SSD)|FHD touch display. 1 year onsite warranty. Windows/macOS preinstalled.|64699|54990|Silver|FREE|28|229|13",
//            "desktop_pcs|Dell Inspiron Desktop (i3-13100, 8GB, 512GB)|Compact tower. 1 year onsite warranty. Windows/macOS preinstalled.|50699|36990|Black|FREE|23|50|45",
//            "desktop_pcs|Lenovo IdeaCentre AIO 3 (Ryzen 5, 16GB, 512GB)|27\" FHD. 1 year onsite warranty. Windows/macOS preinstalled.|77399|64990|Grey|FREE|183|1897|43",
//            "desktop_pcs|Apple iMac 24\" M3 (8GB, 256GB)|4.5K Retina. 1 year onsite warranty. Windows/macOS preinstalled.|160599|134900|Blue|FREE|148|3187|62",
//            "desktop_pcs|ASUS ROG Strix Gaming PC (i7, RTX 4060, 16GB, 1TB)|RGB tower. 1 year onsite warranty. Windows/macOS preinstalled.|141199|119990|Grey|FREE|52|1949|36",
//            "desktop_pcs|Acer Mini PC (Ryzen 7, 16GB, 512GB)|Palm-size. 1 year onsite warranty. Windows/macOS preinstalled.|53399|39990|Black|FREE|89|1991|47",
//            // ---- gaming_accessories
//            "gaming_accessories|Logitech Mechanical Gaming Keyboard RGB|Hot-swappable switches. 1 year manufacturer warranty.|5499|4499|Silver|FREE|181|749|57",
//            "gaming_accessories|Razer Gaming Headset 7.1 Surround|Detachable mic. 1 year manufacturer warranty.|4899|3999|Gold|FREE|44|701|14",
//            "gaming_accessories|HyperX DualSense Wireless Controller|Haptic feedback. 1 year manufacturer warranty.|7599|5990|Green|FREE|136|2294|21",
//            "gaming_accessories|Sony Gaming Mouse 16000 DPI|Programmable buttons. 1 year manufacturer warranty.|4299|2999|Black|FREE|145|2825|58",
//            "gaming_accessories|Cosmic Byte XXL RGB Mouse Pad|Stitched edges. 1 year manufacturer warranty.|1499|1299|Gold|FREE|74|1722|107",
//            "gaming_accessories|Redgear Gaming Chair|Ergonomic recliner. 1 year manufacturer warranty.|16499|12999|Green|FREE|25|392|119",
//            // ---- computer_accessories
//            "computer_accessories|Logitech Wireless Keyboard and Mouse Combo|2.4GHz, long battery. 1 year manufacturer warranty.|4699|3599|Blue|FREE|135|2865|3",
//            "computer_accessories|HP USB-C Hub 7-in-1|HDMI 4K, USB 3.0, SD. 1 year manufacturer warranty.|6699|4399|Silver|FREE|43|2556|24",
//            "computer_accessories|Dell HD Webcam 1080p|Dual mic. 1 year manufacturer warranty.|4999|3699|Green|FREE|81|3694|35",
//            "computer_accessories|Portronics Laptop Cooling Pad|5 fans. 1 year manufacturer warranty.|4199|3699|White|FREE|91|826|58",
//            "computer_accessories|Zebronics Laptop Stand Aluminium|Adjustable height. 1 year manufacturer warranty.|539|349|White|FREE|226|1037|96",
//            "computer_accessories|Lenovo Wireless Presenter|Laser pointer. 1 year manufacturer warranty.|939|789|White|FREE|226|3269|43",
//            // ---- external_hard_disks
//            "external_hard_disks|Seagate Expansion 1TB Portable HDD|USB 3.0. 1 year manufacturer warranty.|5199|4199|Grey|FREE|159|1918|110",
//            "external_hard_disks|WD My Passport 2TB|Password protected. 1 year manufacturer warranty.|7899|6299|Silver|FREE|207|838|43",
//            "external_hard_disks|Samsung T7 Portable SSD 1TB|1050MB/s. 1 year manufacturer warranty.|12699|8999|Black|FREE|121|444|65",
//            "external_hard_disks|Toshiba Canvio Basics 4TB|Plug and play. 1 year manufacturer warranty.|11499|9999|Gold|FREE|132|2864|8",
//            "external_hard_disks|SanDisk Extreme Portable SSD 1TB|IP65 rugged. 1 year manufacturer warranty.|11099|9499|Blue|FREE|68|2402|20",
//            "external_hard_disks|Crucial X9 Pro Portable SSD 2TB|2000MB/s. 1 year manufacturer warranty.|20599|15999|Gold|FREE|49|3785|23",
//            // ---- pendrives
//            "pendrives|SanDisk Ultra Dual Drive 64GB Type-C|OTG. 1 year manufacturer warranty.|2899|2099|Green|FREE|118|889|87",
//            "pendrives|HP Cruzer Blade 32GB|Compact. 1 year manufacturer warranty.|2399|1899|Gold|FREE|199|497|17",
//            "pendrives|Kingston DataTraveler 128GB USB 3.2|Metal. 1 year manufacturer warranty.|819|529|White|FREE|165|3331|105",
//            "pendrives|Samsung BAR Plus 256GB|400MB/s. 1 year manufacturer warranty.|3299|2399|Silver|FREE|164|684|8",
//            "pendrives|Sony Metal Pendrive 64GB|Keyring. 1 year manufacturer warranty.|2599|1699|Blue|FREE|115|3517|102",
//            "pendrives|Strontium iXpand Flash Drive 128GB|For iPhone. 1 year manufacturer warranty.|3099|2299|Green|FREE|87|2958|59",
//            // ---- laptop_skins_decals
//            "laptop_skins_decals|GADGETS WRAP Matte Black Laptop Skin 15.6\"|Bubble-free vinyl. Quality checked. Easy returns.|399|319|Black|FREE|145|4239|0",
//            "laptop_skins_decals|Gadgetshieldz Marble Texture Laptop Skin|Full body. Quality checked. Easy returns.|2199|799|White|FREE|122|392|82",
//            "laptop_skins_decals|DailyObjects Carbon Fibre Laptop Skin|Textured. Quality checked. Easy returns.|679|519|Black|FREE|243|3388|31",
//            "laptop_skins_decals|Skinova Anime Print Laptop Skin|HD print. Quality checked. Easy returns.|539|409|Grey Melange|FREE|195|2334|27",
//            "laptop_skins_decals|Wrapcart Wooden Texture Laptop Skin|Natural look. Quality checked. Easy returns.|519|379|Brown|FREE|124|4565|61",
//            "laptop_skins_decals|The Souled Store Laptop Stickers Pack of 50|Waterproof. Quality checked. Easy returns.|1699|789|Navy Blue|FREE|92|2770|52",
//            // ---- laptop_bags
//            "laptop_bags|American Tourister Laptop Backpack 15.6\"|Water resistant, USB port. Quality checked. Easy returns.|4399|2099|Teal|FREE|39|4628|66",
//            "laptop_bags|Wildcraft Anti-Theft Laptop Backpack|Hidden zips. Quality checked. Easy returns.|11499|3999|Teal|FREE|203|3917|71",
//            "laptop_bags|Safari Laptop Sleeve 14\"|Padded neoprene. Quality checked. Easy returns.|6999|2699|Charcoal|FREE|147|4298|38",
//            "laptop_bags|Skybags Messenger Laptop Bag|Shoulder strap. Quality checked. Easy returns.|6399|2399|Mustard|FREE|110|1727|87",
//            "laptop_bags|HP Trolley Laptop Bag|Wheels. Quality checked. Easy returns.|3399|2199|Olive|FREE|168|2995|114",
//            "laptop_bags|Dell Leather Laptop Briefcase|Office ready. Quality checked. Easy returns.|5899|3199|Silver|FREE|116|2097|107",
//            // ---- mouse
//            "mouse|Logitech MX Master 3S Wireless Mouse|8K DPI, silent clicks. 1 year manufacturer warranty.|12199|9995|Green|FREE|206|3779|7",
//            "mouse|HP M331 Silent Wireless Mouse|Quiet clicks. 1 year manufacturer warranty.|1399|1199|White|FREE|27|1339|102",
//            "mouse|Dell Ergonomic Vertical Mouse|Wrist friendly. 1 year manufacturer warranty.|2199|1499|Blue|FREE|210|1121|108",
//            "mouse|Razer DeathAdder Gaming Mouse|Optical switches. 1 year manufacturer warranty.|4299|3499|Blue|FREE|180|980|81",
//            "mouse|Lenovo Bluetooth Multi-Device Mouse|3 devices. 1 year manufacturer warranty.|2999|2499|Black|FREE|123|4279|119",
//            "mouse|Zebronics Wired Optical Mouse|Plug and play. 1 year manufacturer warranty.|379|349|Black|FREE|164|2859|110",
//            // ---- computer_peripherals
//            "computer_peripherals|Logitech UPS 600VA|Power backup. 1 year manufacturer warranty.|4299|3399|Gold|FREE|80|3357|14",
//            "computer_peripherals|HP USB Speakers 2.0|RGB. 1 year manufacturer warranty.|13599|9199|Green|FREE|123|4408|28",
//            "computer_peripherals|APC Graphic Drawing Tablet|8192 pressure levels. 1 year manufacturer warranty.|11599|8099|Green|FREE|116|3637|95",
//            "computer_peripherals|TP-Link Wi-Fi USB Adapter|Dual band. 1 year manufacturer warranty.|7199|5899|Grey|FREE|91|4371|61",
//            "computer_peripherals|Zebronics Document Scanner|A4 flatbed. 1 year manufacturer warranty.|10199|7099|White|FREE|134|1745|59",
//            "computer_peripherals|Portronics Surge Protector 6 Socket|Spike guard. 1 year manufacturer warranty.|5299|4199|Green|FREE|114|1169|93",
//            // ---- printers_ink_cartridges
//            "printers_ink_cartridges|HP Ink Tank All-in-One Printer|Print, scan, copy, Wi-Fi. 1 year manufacturer warranty.|16699|13999|Silver|FREE|151|3795|13",
//            "printers_ink_cartridges|Canon Laser Printer Mono|Fast 30 ppm. 1 year manufacturer warranty.|17199|11999|Silver|FREE|158|2351|40",
//            "printers_ink_cartridges|Epson EcoTank Colour Printer|Low cost per page. 1 year manufacturer warranty.|21399|15999|Silver|FREE|103|4008|49",
//            "printers_ink_cartridges|Brother Photo Printer Compact|Instant photos. 1 year manufacturer warranty.|11699|8999|Gold|FREE|208|4727|80",
//            "printers_ink_cartridges|HP Black Ink Cartridge|High yield. 1 year manufacturer warranty.|1499|999|Blue|FREE|161|4033|119",
//            "printers_ink_cartridges|Canon Colour Ink Bottle Set|Genuine ink. 1 year manufacturer warranty.|1999|1299|White|FREE|84|1635|82",
//            // ---- monitors
//            "monitors|LG 27\" 4K UHD IPS Monitor|HDR10, USB-C. 1 year manufacturer warranty.|38699|28999|White|FREE|196|3649|49",
//            "monitors|Samsung 24\" FHD IPS Monitor 75Hz|Eye care. 1 year manufacturer warranty.|11699|8999|Green|FREE|114|1606|94",
//            "monitors|Dell 27\" QHD 165Hz Gaming Monitor|1ms. 1 year manufacturer warranty.|37899|24999|Green|FREE|203|965|0",
//            "monitors|BenQ 32\" Curved Monitor|1500R curve. 1 year manufacturer warranty.|29799|21999|Blue|FREE|228|73|41",
//            "monitors|ASUS 34\" Ultrawide Monitor|21:9. 1 year manufacturer warranty.|39799|34999|Silver|FREE|113|3780|1",
//            "monitors|Acer 22\" FHD Monitor|Slim bezel. 1 year manufacturer warranty.|9299|6999|Green|FREE|218|1617|8",
//            // ---- apple_ipads
//            "apple_ipads|Apple iPad 10th Gen Wi-Fi 64GB|10.9\" Liquid Retina, A14. 1 year manufacturer warranty.|49899|34900|Gold|FREE|239|1694|117",
//            "apple_ipads|Apple iPad Air M2 Wi-Fi 128GB|11\" display, Apple Pencil Pro support. 1 year manufacturer warranty.|67399|59900|Grey|FREE|127|3056|45",
//            "apple_ipads|Apple iPad Pro M4 11\" 256GB|Ultra Retina XDR. 1 year manufacturer warranty.|146999|99900|White|FREE|208|3785|58",
//            "apple_ipads|Apple iPad mini Wi-Fi 64GB|8.3\" compact. 1 year manufacturer warranty.|57399|49900|Silver|FREE|60|2018|63",
//            "apple_ipads|Apple iPad 9th Gen Wi-Fi 64GB|10.2\" Retina. 1 year manufacturer warranty.|46199|29999|Gold|FREE|104|1862|119",
//            "apple_ipads|Apple iPad Air M2 13\" 256GB|Large display. 1 year manufacturer warranty.|96299|79900|Grey|FREE|207|2024|82",
//            // ---- home_audio_speakers
//            "home_audio_speakers|Sony Tower Speaker 100W|Deep bass. 1 year manufacturer warranty.|14299|9999|Silver|FREE|138|4141|108",
//            "home_audio_speakers|JBL Bookshelf Speakers Pair|Hi-Fi. 1 year manufacturer warranty.|10299|7999|Grey|FREE|85|2678|31",
//            "home_audio_speakers|Bose Smart Speaker with Alexa|Voice control. 1 year manufacturer warranty.|5899|4499|Gold|FREE|63|3872|81",
//            "home_audio_speakers|Philips Party Speaker with Lights|Karaoke mic. 1 year manufacturer warranty.|16699|14999|Gold|FREE|19|3675|2",
//            "home_audio_speakers|boAt Stanmore III Speaker|Iconic design. 1 year manufacturer warranty.|48199|39999|Black|FREE|85|3685|73",
//            "home_audio_speakers|Marshall Multimedia 2.1 Speaker|Subwoofer. 1 year manufacturer warranty.|4699|2999|Gold|FREE|75|4480|47",
//            // ---- home_theatres
//            "home_theatres|Sony 5.1 Home Theatre 600W|Dolby Digital. 1 year manufacturer warranty.|29499|19999|Gold|FREE|90|432|75",
//            "home_theatres|Samsung Bluetooth Home Theatre 2.1|Wireless subwoofer. 1 year manufacturer warranty.|12199|8999|Grey|FREE|207|1292|106",
//            "home_theatres|JBL Dolby Atmos 7.1 System|Immersive. 1 year manufacturer warranty.|63299|49999|White|FREE|137|4672|71",
//            "home_theatres|Philips Tower Home Theatre|Floor standing. 1 year manufacturer warranty.|18099|14999|Gold|FREE|53|544|38",
//            "home_theatres|Zebronics Compact Home Theatre|Space saving. 1 year manufacturer warranty.|8199|6999|Black|FREE|241|2971|82",
//            "home_theatres|LG Wireless Rear Speakers Kit|Upgrade kit. 1 year manufacturer warranty.|11999|9999|Gold|FREE|202|2625|107",
//            // ---- soundbars
//            "soundbars|Sony Dolby Atmos Soundbar 5.1|Wireless subwoofer. 1 year manufacturer warranty.|50699|39999|White|FREE|66|2831|10",
//            "soundbars|JBL 2.1 Channel Soundbar 160W|Bluetooth. 1 year manufacturer warranty.|10999|8999|Blue|FREE|53|2088|61",
//            "soundbars|Samsung Smart Soundbar 700|Voice assistant. 1 year manufacturer warranty.|66699|59999|Gold|FREE|44|3056|106",
//            "soundbars|boAt Compact Soundbar 60W|TV ready. 1 year manufacturer warranty.|4899|3999|Gold|FREE|233|3368|63",
//            "soundbars|Bose Q-Series Soundbar|Q-Symphony. 1 year manufacturer warranty.|34799|24999|White|FREE|164|2338|47",
//            "soundbars|LG Aavante Bar 1500|Multiple EQ modes. 1 year manufacturer warranty.|7499|5999|Green|FREE|36|2663|85",
//            // ---- bluetooth_speakers
//            "bluetooth_speakers|JBL Flip 6 Portable Speaker|IP67, 12h battery. 1 year manufacturer warranty.|11999|9999|Gold|FREE|248|3000|33",
//            "bluetooth_speakers|Sony SRS-XB13 Extra Bass|Compact. 1 year manufacturer warranty.|4699|3990|Silver|FREE|201|355|111",
//            "bluetooth_speakers|boAt Stone 1200 Speaker|14W, IPX7. 1 year manufacturer warranty.|3499|2999|Gold|FREE|70|826|62",
//            "bluetooth_speakers|Marshall Emberton II|30h playtime. 1 year manufacturer warranty.|21799|16999|Grey|FREE|152|1217|84",
//            "bluetooth_speakers|Bose SoundLink Flex|PositionIQ. 1 year manufacturer warranty.|21999|14900|White|FREE|247|863|99",
//            "bluetooth_speakers|Ultimate Ears BOOM 3|360° sound. 1 year manufacturer warranty.|14699|12999|Silver|FREE|79|4530|87",
//            // ---- dth_set_top_box
//            "dth_set_top_box|Tata Play HD Set Top Box with 1 Month Pack|Full HD channels. 1 year manufacturer warranty.|2999|2499|Black|FREE|113|2520|50",
//            "dth_set_top_box|Airtel Digital TV Android Smart Set Top Box|OTT apps. 1 year manufacturer warranty.|2199|1799|Green|FREE|173|326|118",
//            "dth_set_top_box|Dish TV 4K Set Top Box|UHD. 1 year manufacturer warranty.|3599|3199|Grey|FREE|151|3393|104",
//            "dth_set_top_box|d2h SD Set Top Box|Budget. 1 year manufacturer warranty.|3099|2199|Silver|FREE|145|1268|47",
//            "dth_set_top_box|Sun Direct Recorder Set Top Box|Record shows. 1 year manufacturer warranty.|1899|1699|Black|FREE|192|1795|94",
//            "dth_set_top_box|DD Free Dish Free Dish Receiver|No monthly fee. 1 year manufacturer warranty.|3499|3099|Silver|FREE|95|4752|77",
//            // ---- dslr_mirrorless_camera
//            "dslr_mirrorless_camera|Canon EOS R50 Mirrorless with 18-45mm|24.2MP APS-C, 4K video. 1 year manufacturer warranty.|84899|72990|Green|FREE|194|2729|111",
//            "dslr_mirrorless_camera|Nikon Z50 Mirrorless with 16-50mm|20.9MP DX. 1 year manufacturer warranty.|114299|79990|Silver|FREE|82|1961|90",
//            "dslr_mirrorless_camera|Sony Alpha 7 IV Body|33MP full-frame. 1 year manufacturer warranty.|268799|214990|Black|FREE|23|1864|10",
//            "dslr_mirrorless_camera|Fujifilm X-T30 II with 18-55mm|26.1MP X-Trans. 1 year manufacturer warranty.|123299|89999|Blue|FREE|159|562|64",
//            "dslr_mirrorless_camera|Panasonic Lumix G85 with 12-60mm|Dual IS. 1 year manufacturer warranty.|82199|59990|Blue|FREE|39|1283|111",
//            "dslr_mirrorless_camera|Canon EOS 1500D DSLR with 18-55mm|24.1MP, Wi-Fi. 1 year manufacturer warranty.|43499|39999|Black|FREE|170|1215|45",
//            // ---- compact_bridge_cameras
//            "compact_bridge_cameras|Sony Cyber-shot RX100 VII|1\" sensor. 1 year manufacturer warranty.|154399|104990|Blue|FREE|99|3692|93",
//            "compact_bridge_cameras|Canon PowerShot SX740 HS|40x zoom. 1 year manufacturer warranty.|48599|32990|Black|FREE|166|3248|18",
//            "compact_bridge_cameras|Nikon Coolpix P950|83x zoom bridge. 1 year manufacturer warranty.|89299|74990|Grey|FREE|198|2776|97",
//            "compact_bridge_cameras|Panasonic Lumix FZ1000 II|4K bridge. 1 year manufacturer warranty.|84399|69990|Blue|FREE|249|2733|41",
//            "compact_bridge_cameras|Fujifilm Instax Mini 12 Instant Camera|Instant prints. 1 year manufacturer warranty.|7699|6999|White|FREE|43|3605|54",
//            "compact_bridge_cameras|Kodak PIXPRO FZ55|Compact 16MP. 1 year manufacturer warranty.|13899|9999|Silver|FREE|198|2767|92",
//            // ---- sports_action_camera
//            "sports_action_camera|GoPro HERO12 Black|5.3K60, HyperSmooth 6.0. 1 year manufacturer warranty.|53599|37990|Black|FREE|195|2432|8",
//            "sports_action_camera|DJI Osmo Action 4|1/1.3\" sensor. 1 year manufacturer warranty.|49299|32990|Green|FREE|250|1086|11",
//            "sports_action_camera|Insta360 X3 360° Camera|5.7K 360. 1 year manufacturer warranty.|65799|49990|Black|FREE|38|2724|27",
//            "sports_action_camera|SJCAM SJ4000 Action Camera|Budget waterproof. 1 year manufacturer warranty.|5699|4999|White|FREE|204|2103|62",
//            "sports_action_camera|Akaso Brave 7 LE|4K30 dual screen. 1 year manufacturer warranty.|16099|12999|Silver|FREE|241|1575|37",
//            "sports_action_camera|Sony Osmo Pocket 3|1\" CMOS gimbal camera. 1 year manufacturer warranty.|59599|49990|Green|FREE|155|2868|115",
//            // ---- camera_accessories
//            "camera_accessories|Digitek Camera Backpack|Padded dividers. Quality checked. Easy returns.|5199|3799|Maroon|FREE|94|4205|38",
//            "camera_accessories|Godox LED Video Light|Bi-colour. Quality checked. Easy returns.|5999|3499|Silver|FREE|163|2521|44",
//            "camera_accessories|SanDisk Extra Battery LP-E17|Genuine. Quality checked. Easy returns.|7399|4999|Brown|FREE|74|2163|90",
//            "camera_accessories|Canon Camera Cleaning Kit|Lens pen and blower. Quality checked. Easy returns.|8299|4299|Charcoal|FREE|189|882|100",
//            "camera_accessories|Nikon Ring Light 18\"|With stand. Quality checked. Easy returns.|3099|1699|Silver|FREE|190|4113|80",
//            "camera_accessories|SmallRig Camera Cage Kit|Cold shoe mounts. Quality checked. Easy returns.|7399|4799|Olive|FREE|203|123|30",
//            // ---- lens
//            "lens|Canon RF 50mm f/1.8 STM|Nifty fifty. 1 year manufacturer warranty.|21999|17990|Blue|FREE|241|986|54",
//            "lens|Nikon Z 24-70mm f/4 S|Standard zoom. 1 year manufacturer warranty.|78399|64990|Black|FREE|65|2070|32",
//            "lens|Sony FE 85mm f/1.8|Portrait prime. 1 year manufacturer warranty.|68499|49990|Silver|FREE|220|4392|90",
//            "lens|Sigma 18-35mm f/1.8 Art|Fast zoom. 1 year manufacturer warranty.|104499|69990|Gold|FREE|79|365|107",
//            "lens|Tamron 70-300mm Telephoto Zoom|Wildlife. 1 year manufacturer warranty.|35799|29990|Silver|FREE|39|65|78",
//            "lens|Fujifilm XF 35mm f/2 R WR|Weather resistant. 1 year manufacturer warranty.|38499|34990|Gold|FREE|62|2865|0",
//            // ---- camera_tripods
//            "camera_tripods|Digitek Aluminium Tripod 60\"|With phone holder. Quality checked. Easy returns.|7199|2699|Teal|FREE|186|3248|97",
//            "camera_tripods|Manfrotto Travel Tripod with Ball Head|Compact. Quality checked. Easy returns.|18699|6899|Black|FREE|208|2923|33",
//            "camera_tripods|SmallRig GorillaPod Flexible Tripod|Wrap anywhere. Quality checked. Easy returns.|9999|7999|White|FREE|134|1085|60",
//            "camera_tripods|Benro Monopod|Lightweight. Quality checked. Easy returns.|7899|5599|Olive|FREE|101|1212|2",
//            "camera_tripods|Osaka Video Tripod with Fluid Head|Smooth pan. Quality checked. Easy returns.|11599|7599|Charcoal|FREE|128|2504|76",
//            "camera_tripods|JOBY Selfie Stick Tripod|Bluetooth remote. Quality checked. Easy returns.|10699|8299|Black|FREE|247|4305|8",
//            // ---- television
//            "television|Samsung Crystal 4K Vivid 55\" Smart TV|UHD, HDR10+. 1 year manufacturer warranty.|66699|47990|White|FREE|52|2542|95",
//            "television|Sony Bravia 65\" 4K Google TV|X1 processor, Dolby Vision. 1 year manufacturer warranty.|159999|119990|Gold|FREE|72|1242|106",
//            "television|LG OLED evo 55\" Smart TV|Self-lit pixels. 1 year manufacturer warranty.|159099|139990|Gold|FREE|241|2918|94",
//            "television|Xiaomi X Pro 43\" 4K Google TV|Dolby Audio. 1 year manufacturer warranty.|33399|27999|Gold|FREE|117|4475|84",
//            "television|OnePlus Y1S Pro 50\" 4K Smart TV|MEMC. 1 year manufacturer warranty.|49299|32999|Green|FREE|182|2914|1",
//            "television|TCL QLED 55\" 4K Google TV|Quantum dot. 1 year manufacturer warranty.|62499|44999|Grey|FREE|137|3142|24",
//            // ---- cameras
//            "cameras|Canon EOS 3000D DSLR Kit|18MP beginner DSLR. 1 year manufacturer warranty.|39999|29999|Black|FREE|119|1199|112",
//            "cameras|Fujifilm Instax Mini 40 Instant Camera|Retro look. 1 year manufacturer warranty.|13899|8999|Grey|FREE|150|662|42",
//            "cameras|Sony ZV-1F Vlog Camera|Creator friendly. 1 year manufacturer warranty.|66699|49990|White|FREE|126|105|80",
//            "cameras|Nikon Coolpix B600|60x zoom. 1 year manufacturer warranty.|27499|24990|White|FREE|100|1845|45",
//            "cameras|Kodak Printomatic Instant Camera|Prints on zink. 1 year manufacturer warranty.|7899|5999|Gold|FREE|154|246|85",
//            "cameras|Polaroid Now+ Instant Camera|Bluetooth app. 1 year manufacturer warranty.|18699|13999|White|FREE|192|1513|42",
//    };
//
//    // Purane seeder ke title isi tarah bante the: STYLE + " " + category name
//    private static final String[] OLD_STYLES = {"Signature", "Everyday", "Classic", "Premium", "Essential", "Heritage"};
//
//    @Override
//    public void run(String... args) {
//        if (!ENABLED) return;
//        try {
//            seed();
//        } catch (Exception e) {
//            // Demo data ki wajah se app kabhi band na ho
//            System.out.println("[DemoDataSeeder] FAILED: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private void seed() {
//        // 1) Demo seller
//        Seller seller = sellerRepository.findByEmail(DEMO_EMAIL);
//        if (seller == null) seller = createDemoSeller();
//
//        // 2) Categories (pehle se hain to wahi use hongi) + har category ki photos
//        Map<String, Category> categories = new HashMap<>();
//        Map<String, String[]> photos = new HashMap<>();
//        Map<String, List<String>> groupPhotos = new HashMap<>();   // level-2 ki saari photos
//        Map<String, String> oldGenericTitles = new HashMap<>();     // "categoryId|title" -> true
//        for (String row : ROWS) {
//            String[] r = row.split("\\|");
//            String[] imgs = Arrays.copyOfRange(r, 6, r.length);
//            Category c1 = findOrCreate(r[0], r[1], 1, null);
//            Category c2 = findOrCreate(r[2], r[3], 2, c1);
//            Category c3 = r[4].equals(r[2]) ? c2 : findOrCreate(r[4], r[5], 3, c2);
//            categories.put(r[2], c2);
//            categories.put(r[4], c3);
//            photos.put(r[4], imgs);
//            groupPhotos.computeIfAbsent(r[2], k -> new ArrayList<>()).addAll(Arrays.asList(imgs));
//            for (String style : OLD_STYLES) oldGenericTitles.put(key(r[4], style + " " + r[5]), "1");
//        }
//
//        // 3) Is seller ke pehle se bane products
//        List<Product> existingList = productRepository.findBySellerId(seller.getId());
//        Set<String> existing = new HashSet<>();
//        for (Product p : existingList) {
//            if (p.getCategory() != null) existing.add(key(p.getCategory().getCategoryId(), p.getTitle()));
//        }
//
//        // 4) Naye products banao (jo pehle se hain unhe skip)
//        Set<String> newTitles = new HashSet<>();
//        Map<String, Integer> position = new HashMap<>();
//        List<Product> toSave = new ArrayList<>();
//        int skipped = 0;
//        for (String row : PRODUCTS) {
//            String[] f = row.split("\\|");
//            String catId = f[0];
//            int k = position.merge(catId, 1, Integer::sum) - 1;
//            newTitles.add(key(catId, f[1]));
//            if (existing.contains(key(catId, f[1]))) { skipped++; continue; }
//
//            Category category = categories.get(catId);
//            if (category == null) category = categoryRepository.findByCategoryId(catId);
//            if (category == null) continue;
//
//            String[] imgs = photos.get(catId);
//            if (imgs == null) {
//                List<String> g = groupPhotos.getOrDefault(catId, List.of());
//                imgs = g.toArray(new String[0]);
//            }
//            toSave.add(buildProduct(f, category, seller, imgs, k));
//        }
//        productRepository.saveAll(toSave);
//
//        // 5) Purane generic demo products hatao (order/cart/wishlist wale safe rehte hain)
//        int removed = 0, kept = 0;
//        if (REMOVE_OLD_GENERIC) {
//            for (Product p : existingList) {
//                if (p.getCategory() == null) continue;
//                String k = key(p.getCategory().getCategoryId(), p.getTitle());
//                if (!oldGenericTitles.containsKey(k) || newTitles.contains(k)) continue;
//                try {
//                    productRepository.deleteById(p.getId());
//                    removed++;
//                } catch (Exception e) {
//                    kept++;
//                }
//            }
//        }
//
//        // 6) Report — har category me kitne products hain
//        Map<String, Integer> count = new HashMap<>();
//        for (Product p : productRepository.findBySellerId(seller.getId())) {
//            if (p.getCategory() != null) count.merge(p.getCategory().getCategoryId(), 1, Integer::sum);
//        }
//        int pass = 0;
//        StringBuilder fail = new StringBuilder();
//        for (String catId : position.keySet()) {
//            int n = count.getOrDefault(catId, 0);
//            if (n >= 5) pass++;
//            else fail.append("\n[DemoDataSeeder]   FAIL ").append(catId).append(" -> ").append(n);
//        }
//        System.out.println("[DemoDataSeeder] " + toSave.size() + " naye demo products bane, " + skipped + " pehle se the, "
//                + removed + " purane generic hataye" + (kept > 0 ? " (" + kept + " order/cart/wishlist me the isliye rakhe)" : ""));
//        System.out.println("[DemoDataSeeder] coverage: " + pass + "/" + position.size()
//                + " categories me 5+ products ✔" + fail);
//    }
//
//    private Seller createDemoSeller() {
//        Seller seller = new Seller();
//        seller.setSellerName("Shopzy Studio");
//        seller.setEmail(DEMO_EMAIL);
//        seller.setMobile("9999999999");
//        seller.setGSTIN("22AAAAA0000A1Z5");
//        seller.setRole(USER_ROLE.ROLE_SELLER);
//        seller.setEmailVerified(true);
//        seller.setAccountStatus(AccountStatus.ACTIVE);
//        seller.getBusinessDetails().setBusinessName("Shopzy Studio");
//        seller.getBusinessDetails().setBusinessEmail(DEMO_EMAIL);
//        seller.getBusinessDetails().setBusinessMobile("9999999999");
//        seller.getPickupAddress().setName("Shopzy Studio");
//        seller.getPickupAddress().setCity("Jaipur");
//        seller.getPickupAddress().setState("Rajasthan");
//        seller.getPickupAddress().setPinCode("302001");
//        seller.getPickupAddress().setMobile("9999999999");
//        return sellerRepository.save(seller);
//    }
//
//    private Category findOrCreate(String id, String name, int level, Category parent) {
//        Category existing = categoryRepository.findByCategoryId(id);
//        if (existing != null) return existing;
//        Category c = new Category();
//        c.setCategoryId(id);
//        c.setName(name);
//        c.setLevel(level);
//        c.setParentCategory(parent);
//        return categoryRepository.save(c);
//    }
//
//    // Product k ki pehli photo category ki k-th photo; doosri photo (hover) agli wali
//    private List<String> imagesFor(String[] imgs, int k) {
//        if (imgs == null || imgs.length == 0) {
//            return new ArrayList<>(List.of("https://placehold.co/600x800/f3ebe3/2a1d17/png?text=Shopzy"));
//        }
//        String main = imgs[k % imgs.length];
//        String other = imgs[(k + 1) % imgs.length];
//        return new ArrayList<>(List.of(main, other));
//    }
//
//    private Product buildProduct(String[] f, Category category, Seller seller, String[] imgs, int k) {
//        int mrp = Integer.parseInt(f[3]);
//        int selling = Integer.parseInt(f[4]);
//        Product p = new Product();
//        p.setTitle(f[1]);
//        p.setDescription(f[2]);
//        p.setMrpPrice(mrp);
//        p.setSellingPrice(selling);
//        p.setDiscountPercent(mrp > 0 ? (int) ((mrp - selling) * 100L / mrp) : 0);
//        p.setColor(f[5]);
//        p.setSizes(f[6]);
//        p.setQuantity(Integer.parseInt(f[7]));
//        p.setNumRatings(Integer.parseInt(f[8]));
//        p.setCreatedAt(LocalDateTime.now().minusDays(Integer.parseInt(f[9])));
//        p.setImages(imagesFor(imgs, k));
//        p.setCategory(category);
//        p.setSeller(seller);
//        return p;
//    }
//
//    private static String key(String categoryId, String title) {
//        return categoryId + "|" + (title == null ? "" : title.trim().toLowerCase(Locale.ROOT));
//    }
//}

//package com.raghav.ecommerce.config;
//
//import com.raghav.ecommerce.domain.AccountStatus;
//import com.raghav.ecommerce.domain.USER_ROLE;
//import com.raghav.ecommerce.model.Category;
//import com.raghav.ecommerce.model.Product;
//import com.raghav.ecommerce.model.Seller;
//import com.raghav.ecommerce.repository.CategoryRepository;
//import com.raghav.ecommerce.repository.ProductRepository;
//import com.raghav.ecommerce.repository.SellerRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//
///**
// * DEMO DATA — backend start hote hi navbar ke mega menu ki HAR category (216) me 4 products daal deta hai.
// * Har product ki photo alag hai (864 products, 864 alag photos).
// * Jo demo products pehle se bane hain unhe dobara nahi banata — unki photos nayi kar deta hai aur
// * bas baaki bache products banata hai, isliye restart karne par products double nahi hote.
// * Band karna ho to neeche ENABLED = false kar do.
// */
//@Component
//public class DemoDataSeeder implements CommandLineRunner {
//
//    private static final boolean ENABLED = true;
//    private static final String DEMO_EMAIL = "demo.seller@shopzy.in";
//
//    private final SellerRepository sellerRepository;
//    private final CategoryRepository categoryRepository;
//    private final ProductRepository productRepository;
//
//    public DemoDataSeeder(SellerRepository sellerRepository,
//                          CategoryRepository categoryRepository,
//                          ProductRepository productRepository) {
//        this.sellerRepository = sellerRepository;
//        this.categoryRepository = categoryRepository;
//        this.productRepository = productRepository;
//    }
//
//    // level1Id | level1Name | level2Id | level2Name | level3Id | level3Name | image1 | image2 | image3 | image4
//    // Har image = ek product. Har category me 4 products, sab ki photo alag.
//    private static final String[] ROWS = {
//            "men|Men|men_topwear|Topwear|men_t_shirts|Men T-Shirts|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2024/AUGUST/26/zLmM5KEB_e4781ac0cc0945119e653f4130994bd2.jpg|https://images.pexels.com/photos/9558713/pexels-photo-9558713.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6311251/pexels-photo-6311251.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5159242/pexels-photo-5159242.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_casual_shirts|Men Casual Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/2.webp|https://images.pexels.com/photos/35182320/pexels-photo-35182320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40070436/pexels-photo-40070436.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_formal_shirts|Men Formal Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/2.webp|https://images.pexels.com/photos/29765480/pexels-photo-29765480.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35415004/pexels-photo-35415004.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_sweatshirts|Men Sweatshirts|https://images.pexels.com/photos/19338764/pexels-photo-19338764.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34582212/pexels-photo-34582212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9920820/pexels-photo-9920820.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6650360/pexels-photo-6650360.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_sweaters|Men Sweaters|https://images.pexels.com/photos/10427343/pexels-photo-10427343.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7237067/pexels-photo-7237067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14269719/pexels-photo-14269719.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558897/pexels-photo-9558897.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_jackets|Men Jackets|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23029834/2023/9/18/96c015ae-1090-4036-954b-d9c80085b1d71695022844653-HRX-by-Hrithik-Roshan-Men-Jackets-6981695022843934-1.jpg|https://images.pexels.com/photos/18720773/pexels-photo-18720773.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21619158/pexels-photo-21619158.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18633077/pexels-photo-18633077.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_blazers_and_coats|Men Blazers & Coats|https://images.pexels.com/photos/9210389/pexels-photo-9210389.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2955375/pexels-photo-2955375.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32670017/pexels-photo-32670017.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29722004/pexels-photo-29722004.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_suits|Men Suits|https://images.pexels.com/photos/2505053/pexels-photo-2505053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/935969/pexels-photo-935969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050435/pexels-photo-6050435.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050409/pexels-photo-6050409.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_rain_jackets|Men Rain Jackets|https://images.pexels.com/photos/6851461/pexels-photo-6851461.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2766298/pexels-photo-2766298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12505397/pexels-photo-12505397.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26838402/pexels-photo-26838402.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_indian_and_festive_wear|Men Indian & Festive Wear|https://rukminim2.flixcart.com/image/612/612/xif0q/ethnic-set/o/n/o/l-02-langit-original-imah4jwwctkjtman.jpeg?q=70|https://shreeman.in/cdn/shop/files/20_3cfbd5a3-ecb6-482a-b798-7ffd9de1c784.jpg?v=1712061674&width=700|https://images.pexels.com/photos/13624155/pexels-photo-13624155.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217656/pexels-photo-8217656.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_kurtas_and_kurta_sets|Men Kurtas & Kurta Sets|https://images.pexels.com/photos/13222257/pexels-photo-13222257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777497/pexels-photo-16777497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38443317/pexels-photo-38443317.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36697919/pexels-photo-36697919.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_sherwanis|Men Sherwanis|https://images.pexels.com/photos/12963836/pexels-photo-12963836.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18694227/pexels-photo-18694227.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37163999/pexels-photo-37163999.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28802603/pexels-photo-28802603.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_nehru_jackets|Men Nehru Jackets|https://images.pexels.com/photos/34423748/pexels-photo-34423748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777516/pexels-photo-16777516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33049965/pexels-photo-33049965.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32947800/pexels-photo-32947800.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_topwear|Topwear|men_dhotis|Men Dhotis|https://images.pexels.com/photos/20871919/pexels-photo-20871919.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14934020/pexels-photo-14934020.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29041818/pexels-photo-29041818.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29153202/pexels-photo-29153202.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_jeans|Men Jeans|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/20122324/2022/9/22/91c61c45-fe17-4d1d-8e20-0aaaf90186b61663827920015RaymondSlimFitBlueJeansForMen1.jpg|https://images.pexels.com/photos/9775489/pexels-photo-9775489.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16069736/pexels-photo-16069736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4258605/pexels-photo-4258605.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_casual_trousers|Men Casual Trousers|https://images.pexels.com/photos/11034966/pexels-photo-11034966.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10004175/pexels-photo-10004175.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897539/pexels-photo-2897539.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9464625/pexels-photo-9464625.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_formal_trousers|Men Formal Trousers|https://images.pexels.com/photos/2815417/pexels-photo-2815417.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558246/pexels-photo-9558246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897533/pexels-photo-2897533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17082930/pexels-photo-17082930.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_shorts|Men Shorts|https://images.pexels.com/photos/1098364/pexels-photo-1098364.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13081849/pexels-photo-13081849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25315887/pexels-photo-25315887.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18178103/pexels-photo-18178103.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_bottomwear|Bottomwear|men_track_pants_and_joggers|Men Track Pants & Joggers|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24528350/2023/8/17/d9ee03c9-7e15-49e4-8f15-0b6f38e568121692275415567TrackPants1.jpg|https://images.pexels.com/photos/11328499/pexels-photo-11328499.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9901666/pexels-photo-9901666.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9499128/pexels-photo-9499128.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_briefs_and_trunks|Men Briefs & Trunks|https://images.pexels.com/photos/8875156/pexels-photo-8875156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874670/pexels-photo-8874670.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874933/pexels-photo-8874933.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874910/pexels-photo-8874910.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_boxers|Men Boxers|https://images.pexels.com/photos/8875171/pexels-photo-8875171.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874672/pexels-photo-8874672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8875173/pexels-photo-8875173.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17510799/pexels-photo-17510799.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_vests|Men Vests|https://images.pexels.com/photos/7711284/pexels-photo-7711284.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13544290/pexels-photo-13544290.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17604597/pexels-photo-17604597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38216749/pexels-photo-38216749.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_sleepwear_and_loungewear|Men Sleepwear & Loungewear|https://images.pexels.com/photos/8416232/pexels-photo-8416232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30444027/pexels-photo-30444027.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6976782/pexels-photo-6976782.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443431/pexels-photo-6443431.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_thermals|Men Thermals|https://images.pexels.com/photos/5924447/pexels-photo-5924447.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4763665/pexels-photo-4763665.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6712133/pexels-photo-6712133.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6799715/pexels-photo-6799715.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_plus_size|Men Plus Size|https://images.pexels.com/photos/8217535/pexels-photo-8217535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217536/pexels-photo-8217536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33676395/pexels-photo-33676395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30589840/pexels-photo-30589840.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_casual_shoes|Men Casual Shoes|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27107914/2024/1/30/bd09f650-d8a4-4570-8e53-e2cfafc4ea6c1706609911015SparxMenMeshRunningShoes1.jpg|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24651572/2023/8/25/4fbf6d8c-d093-46c5-a5a6-7dd67c0c76551692964752597HouseofPataudiMenTanFauxLeatherFormalSlipOnLoafers1.jpg|https://images.pexels.com/photos/40263252/pexels-photo-40263252.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40325298/pexels-photo-40325298.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_sports_shoes|Men Sports Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/2.webp|https://images.pexels.com/photos/260044/pexels-photo-260044.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8454904/pexels-photo-8454904.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_formal_shoes|Men Formal Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/2.webp|https://images.pexels.com/photos/12210270/pexels-photo-12210270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10352336/pexels-photo-10352336.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_sneakers|Men Sneakers|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/2.webp|https://images.pexels.com/photos/5560288/pexels-photo-5560288.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4901935/pexels-photo-4901935.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_sandals_and_floaters|Men Sandals & Floaters|https://images.pexels.com/photos/4996738/pexels-photo-4996738.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/952843/pexels-photo-952843.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23692992/pexels-photo-23692992.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9214975/pexels-photo-9214975.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_flip_flops|Men Flip Flops|https://images.pexels.com/photos/14934601/pexels-photo-14934601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34447680/pexels-photo-34447680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30335806/pexels-photo-30335806.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35166184/pexels-photo-35166184.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_footwear|Footwear|men_socks|Men Socks|https://images.pexels.com/photos/15195233/pexels-photo-15195233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14943497/pexels-photo-14943497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10563910/pexels-photo-10563910.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12932537/pexels-photo-12932537.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sunglasses_and_frames|Men Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/2.webp|https://images.pexels.com/photos/5202048/pexels-photo-5202048.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2767694/pexels-photo-2767694.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_watches|Men Watches|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/1.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/2.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/3.webp|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/1.webp",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_and_active_wear|Men Sports & Active Wear|https://images.pexels.com/photos/1311617/pexels-photo-1311617.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036883/pexels-photo-5036883.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29259727/pexels-photo-29259727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3927383/pexels-photo-3927383.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_sandals|Men Sports Sandals|https://images.pexels.com/photos/15669736/pexels-photo-15669736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3342769/pexels-photo-3342769.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996974/pexels-photo-4996974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6008231/pexels-photo-6008231.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_active_t_shirts|Men Active T-Shirts|https://images.pexels.com/photos/8498700/pexels-photo-8498700.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/1.webp|https://images.pexels.com/photos/4004222/pexels-photo-4004222.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6998798/pexels-photo-6998798.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_track_pants_and_shorts|Men Track Pants & Shorts|https://images.pexels.com/photos/13069429/pexels-photo-13069429.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8567521/pexels-photo-8567521.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30710156/pexels-photo-30710156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11029622/pexels-photo-11029622.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_tracksuits|Men Tracksuits|https://images.pexels.com/photos/9488418/pexels-photo-9488418.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2652236/pexels-photo-2652236.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6158668/pexels-photo-6158668.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33558335/pexels-photo-33558335.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_jackets_and_sweatshirts|Men Jackets & Sweatshirts|https://images.pexels.com/photos/14611192/pexels-photo-14611192.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9775733/pexels-photo-9775733.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28701960/pexels-photo-28701960.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15127546/pexels-photo-15127546.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_accessories|Men Sports Accessories|https://images.pexels.com/photos/4162451/pexels-photo-4162451.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6740821/pexels-photo-6740821.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35567437/pexels-photo-35567437.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19025674/pexels-photo-19025674.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_swimwear|Men Swimwear|https://images.pexels.com/photos/9030517/pexels-photo-9030517.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6011894/pexels-photo-6011894.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8028651/pexels-photo-8028651.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8688187/pexels-photo-8688187.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_wallets|Men Wallets|https://images.pexels.com/photos/915917/pexels-photo-915917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/164637/pexels-photo-164637.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7952556/pexels-photo-7952556.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4452393/pexels-photo-4452393.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_belts|Men Belts|https://images.pexels.com/photos/6654763/pexels-photo-6654763.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31323080/pexels-photo-31323080.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959214/pexels-photo-31959214.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959216/pexels-photo-31959216.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_perfumes_and_body_mists|Men Perfumes & Body Mists|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/1.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/2.webp|https://images.pexels.com/photos/15096784/pexels-photo-15096784.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7703038/pexels-photo-7703038.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_trimmers|Men Trimmers|https://images.pexels.com/photos/897262/pexels-photo-897262.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6007395/pexels-photo-6007395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7253884/pexels-photo-7253884.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16553361/pexels-photo-16553361.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_deodorants|Men Deodorants|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/1.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/2.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/3.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/3.webp",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_ties_cufflinks_and_pocket_squares|Men Ties, Cufflinks & Pocket Squares|https://images.pexels.com/photos/21928749/pexels-photo-21928749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38270/pexels-photo-38270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32392710/pexels-photo-32392710.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34342976/pexels-photo-34342976.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_accessory_gift_sets|Men Accessory Gift Sets|https://images.pexels.com/photos/190930/pexels-photo-190930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/264771/pexels-photo-264771.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6087540/pexels-photo-6087540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1303087/pexels-photo-1303087.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_caps_and_hats|Men Caps & Hats|https://images.pexels.com/photos/18417334/pexels-photo-18417334.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13265200/pexels-photo-13265200.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27401299/pexels-photo-27401299.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29925645/pexels-photo-29925645.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_mufflers_scarves_and_gloves|Men Mufflers, Scarves & Gloves|https://images.pexels.com/photos/1903760/pexels-photo-1903760.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20281423/pexels-photo-20281423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16049198/pexels-photo-16049198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35830300/pexels-photo-35830300.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_phone_cases|Men Phone Cases|https://images.pexels.com/photos/374140/pexels-photo-374140.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7742503/pexels-photo-7742503.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13706809/pexels-photo-13706809.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18403793/pexels-photo-18403793.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_rings_and_wristwear|Men Rings & Wristwear|https://images.pexels.com/photos/16304565/pexels-photo-16304565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9742330/pexels-photo-9742330.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2442905/pexels-photo-2442905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15325460/pexels-photo-15325460.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "men|Men|men_fashion_accessories|Fashion Accessories|men_helmets|Men Helmets|https://images.pexels.com/photos/9835238/pexels-photo-9835238.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12000077/pexels-photo-12000077.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5803274/pexels-photo-5803274.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5355536/pexels-photo-5355536.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtas_and_suits|Women Kurtas & Suits|https://images.pexels.com/photos/11661072/pexels-photo-11661072.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33824984/pexels-photo-33824984.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30196701/pexels-photo-30196701.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1322993/pexels-photo-1322993.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtis_tunics_tops|Women Kurtis, Tunics & Tops|https://images.pexels.com/photos/19556879/pexels-photo-19556879.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7371768/pexels-photo-7371768.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36311379/pexels-photo-36311379.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35521738/pexels-photo-35521738.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_sarees|Women Sarees|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950865601_540x.jpg?v=1704965372|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950931137_540x.jpg?v=1704965372|https://images.pexels.com/photos/7920188/pexels-photo-7920188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27719401/pexels-photo-27719401.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_ethnic_wear|Women Ethnic Wear|https://images.pexels.com/photos/37523799/pexels-photo-37523799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512776/pexels-photo-28512776.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512787/pexels-photo-28512787.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13178920/pexels-photo-13178920.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_leggings_salwars_churidars|Women Leggings, Salwars & Churidars|https://rukminim2.flixcart.com/image/612/612/xif0q/skirt/k/o/h/free-1-00003-custom-fashion-original-imagztykafwbqupf.jpeg?q=70|https://images.pexels.com/photos/19932178/pexels-photo-19932178.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20382095/pexels-photo-20382095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19271174/pexels-photo-19271174.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_skirts_palazzos|Women Skirts & Palazzos|https://images.pexels.com/photos/14997427/pexels-photo-14997427.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16721105/pexels-photo-16721105.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40293396/pexels-photo-40293396.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39030338/pexels-photo-39030338.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dress_materials|Women Dress Materials|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950996673_540x.jpg?v=1704965372|https://rukminim2.flixcart.com/image/832/832/xif0q/sari/z/a/4/free-saree-new2023-simple-light-weight-saree-chiffon-print-saree-original-imagzyhp2azy7rwz.jpeg?q=70&crop=false|https://images.pexels.com/photos/34933671/pexels-photo-34933671.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20841148/pexels-photo-20841148.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_lehenga_cholis|Women Lehenga Cholis|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23807268/2023/6/29/9930b235-5318-4755-abbe-08f99e969e781688026636544LehengaCholi7.jpg|https://images.pexels.com/photos/12730873/pexels-photo-12730873.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28405815/pexels-photo-28405815.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33343591/pexels-photo-33343591.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dupattas_shawls|Women Dupattas & Shawls|https://images.pexels.com/photos/11004120/pexels-photo-11004120.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18659930/pexels-photo-18659930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158070/pexels-photo-35158070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158052/pexels-photo-35158052.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_jackets|Women Jackets|https://images.pexels.com/photos/1818431/pexels-photo-1818431.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8386453/pexels-photo-8386453.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39054974/pexels-photo-39054974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036161/pexels-photo-5036161.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_belts_scarves_more|Women Belts, Scarves & More|https://images.pexels.com/photos/31432650/pexels-photo-31432650.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8014845/pexels-photo-8014845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37483619/pexels-photo-37483619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959217/pexels-photo-31959217.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_watches_wearables|Women Watches & Wearables|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/3.webp",
//            "women|Women|women_western_wear|Western Wear|women_dresses|Women Dresses|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/2.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/3.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/4.webp",
//            "women|Women|women_western_wear|Western Wear|women_tops|Women Tops|https://cdn.dummyjson.com/product-images/tops/blue-frock/1.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/2.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/3.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/4.webp",
//            "women|Women|women_western_wear|Western Wear|women_tshirts|Women Tshirts|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/2.webp|https://images.pexels.com/photos/30957211/pexels-photo-30957211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24205642/pexels-photo-24205642.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_jeans|Women Jeans|https://images.pexels.com/photos/17302419/pexels-photo-17302419.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/936117/pexels-photo-936117.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20729717/pexels-photo-20729717.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7636101/pexels-photo-7636101.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_trousers_capris|Women Trousers & Capris|https://images.pexels.com/photos/450212/pexels-photo-450212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6182574/pexels-photo-6182574.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7205905/pexels-photo-7205905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19995460/pexels-photo-19995460.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_shorts_skirts|Women Shorts & Skirts|https://images.pexels.com/photos/6012003/pexels-photo-6012003.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/383603/pexels-photo-383603.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7391126/pexels-photo-7391126.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16431244/pexels-photo-16431244.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_coords|Women Co-ords|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/2.webp|https://images.pexels.com/photos/31400265/pexels-photo-31400265.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37036179/pexels-photo-37036179.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_playsuits|Women Playsuits|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/2.webp|https://images.pexels.com/photos/7545423/pexels-photo-7545423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29017408/pexels-photo-29017408.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_jumpsuits|Women Jumpsuits|https://images.pexels.com/photos/21374896/pexels-photo-21374896.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4006508/pexels-photo-4006508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2699250/pexels-photo-2699250.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13219628/pexels-photo-13219628.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_shrugs|Women Shrugs|https://cdn.dummyjson.com/product-images/tops/gray-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/gray-dress/2.webp|https://images.pexels.com/photos/7585673/pexels-photo-7585673.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5710033/pexels-photo-5710033.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_sweaters_sweatshirts|Women Sweaters & Sweatshirts|https://images.pexels.com/photos/2080383/pexels-photo-2080383.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9811069/pexels-photo-9811069.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33688875/pexels-photo-33688875.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6347515/pexels-photo-6347515.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_jackets_coats|Women Jackets & Coats|https://images.pexels.com/photos/6974936/pexels-photo-6974936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11587144/pexels-photo-11587144.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12083001/pexels-photo-12083001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34366695/pexels-photo-34366695.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_blazers_waistcoats|Women Blazers & Waistcoats|https://images.pexels.com/photos/10955935/pexels-photo-10955935.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14495270/pexels-photo-14495270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7691041/pexels-photo-7691041.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8484005/pexels-photo-8484005.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_plus_size|Women Plus Size|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/2.webp|https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/1.webp|https://images.pexels.com/photos/14693247/pexels-photo-14693247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18586865/pexels-photo-18586865.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_maternity|Women Maternity|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/2.webp|https://images.pexels.com/photos/20770621/pexels-photo-20770621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15968597/pexels-photo-15968597.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_western_wear|Western Wear|women_sunglasses_frames|Women Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/2.webp|https://images.pexels.com/photos/15735139/pexels-photo-15735139.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26575042/pexels-photo-26575042.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_flats|Women Flats|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/2.webp|https://images.pexels.com/photos/14816287/pexels-photo-14816287.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36581190/pexels-photo-36581190.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_casual_shoes|Women Casual Shoes|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/2.webp|https://images.pexels.com/photos/7887198/pexels-photo-7887198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1027130/pexels-photo-1027130.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_heels|Women Heels|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/2.webp|https://images.pexels.com/photos/36212466/pexels-photo-36212466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34294446/pexels-photo-34294446.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_boots|Women Boots|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/2.webp|https://images.pexels.com/photos/26856061/pexels-photo-26856061.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27174561/pexels-photo-27174561.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_footwear|Footwear|women_sports_shoes_floaters|Women Sports Shoes & Floaters|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/2.webp|https://images.pexels.com/photos/4065509/pexels-photo-4065509.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15659357/pexels-photo-15659357.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_sports_active_wear|Sports & Active Wear|women_clothing|Women Clothing|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/22109480/2023/9/5/06a17ac3-46b0-4f9d-bcb1-2d3582feda041693895310152PumaWomenBrandLogoPrintedPureCottonOutdoorT-shirt1.jpg|https://images.pexels.com/photos/8436750/pexels-photo-8436750.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27433185/pexels-photo-27433185.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7894597/pexels-photo-7894597.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_accessories|Women Sports Accessories|https://images.pexels.com/photos/9944907/pexels-photo-9944907.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16513597/pexels-photo-16513597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6246682/pexels-photo-6246682.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6339731/pexels-photo-6339731.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_equipment|Women Sports Equipment|https://cdn.dummyjson.com/product-images/sports-accessories/american-football/1.webp|https://cdn.dummyjson.com/product-images/sports-accessories/baseball-ball/1.webp|https://images.pexels.com/photos/7743320/pexels-photo-7743320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6550849/pexels-photo-6550849.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_bra|Women Bra|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/362895/2023/10/18/bfb55058-fe1a-45d6-a39d-1b8d421e02001697603774147TriumphShapeSensation33withHighWaistTummyandThighControlMaxi1.jpg|https://images.pexels.com/photos/10782049/pexels-photo-10782049.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6879815/pexels-photo-6879815.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29835139/pexels-photo-29835139.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_briefs|Women Briefs|https://images.pexels.com/photos/10476640/pexels-photo-10476640.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17604582/pexels-photo-17604582.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29474759/pexels-photo-29474759.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11415285/pexels-photo-11415285.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_shapewear|Women Shapewear|https://images.pexels.com/photos/8966046/pexels-photo-8966046.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17040812/pexels-photo-17040812.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5256325/pexels-photo-5256325.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5256331/pexels-photo-5256331.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_sleepwear_loungewear|Women Sleepwear & Loungewear|https://images.pexels.com/photos/6976434/pexels-photo-6976434.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18820123/pexels-photo-18820123.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8416240/pexels-photo-8416240.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8497487/pexels-photo-8497487.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_swimwear|Women Swimwear|https://images.pexels.com/photos/7365485/pexels-photo-7365485.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5638968/pexels-photo-5638968.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13697814/pexels-photo-13697814.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31094948/pexels-photo-31094948.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_lingerie_sleepwear|Lingerie Sleepwear|women_camisoles_thermals|Women Camisoles & Thermals|https://images.pexels.com/photos/4029921/pexels-photo-4029921.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6569963/pexels-photo-6569963.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6569968/pexels-photo-6569968.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6544543/pexels-photo-6544543.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_beauty_personal_care|Beauty & Personal Care|https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/1.webp|https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/1.webp|https://cdn.dummyjson.com/product-images/beauty/powder-canister/1.webp|https://cdn.dummyjson.com/product-images/beauty/red-lipstick/1.webp",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_makeup|Women Makeup|https://images.pexels.com/photos/23228944/pexels-photo-23228944.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1571585/pexels-photo-1571585.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4938498/pexels-photo-4938498.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3148938/pexels-photo-3148938.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_skincare|Women Skincare|https://cdn.dummyjson.com/product-images/beauty/red-nail-polish/1.webp|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/1.webp|https://images.pexels.com/photos/12115040/pexels-photo-12115040.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28994644/pexels-photo-28994644.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_premium_beauty|Women Premium Beauty|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/2.webp|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/1.webp|https://images.pexels.com/photos/7020247/pexels-photo-7020247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6925496/pexels-photo-6925496.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_lipsticks|Women Lipsticks|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/2.webp|https://cdn.dummyjson.com/product-images/skin-care/vaseline-men-body-and-face-lotion/1.webp|https://images.pexels.com/photos/34321279/pexels-photo-34321279.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6648498/pexels-photo-6648498.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_fragrances|Women Fragrances|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/1.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/2.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/3.webp|https://cdn.dummyjson.com/product-images/fragrances/dolce-shine-eau-de/1.webp",
//            "women|Women|women_gadgets|Gadgets|women_gadgets|Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/2.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/2.webp",
//            "women|Women|women_gadgets|Gadgets|women_smart_wearables|Women Smart Wearables|https://rukminim2.flixcart.com/image/612/612/xif0q/smartwatch/f/g/g/-original-imagywnz46fngcks.jpeg?q=70|https://rukminim2.flixcart.com/image/416/416/xif0q/smartwatch/u/r/h/-original-imagyxxfgz52vzqn.jpeg?q=70&crop=false|https://images.pexels.com/photos/31406895/pexels-photo-31406895.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31406903/pexels-photo-31406903.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_gadgets|Gadgets|women_fitness_gadgets|Women Fitness Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/2.webp|https://images.pexels.com/photos/5036927/pexels-photo-5036927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/374673/pexels-photo-374673.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_gadgets|Gadgets|women_headphones|Women Headphones|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods-max-silver/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/beats-flex-wireless-earphones/1.webp|https://images.pexels.com/photos/210927/pexels-photo-210927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7772548/pexels-photo-7772548.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_gadgets|Gadgets|women_speakers|Women Speakers|https://images.pexels.com/photos/374110/pexels-photo-374110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13465232/pexels-photo-13465232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4917455/pexels-photo-4917455.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29617989/pexels-photo-29617989.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_jewellery|Jewellery|women_fashion_jewellery|Women Fashion Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/2.webp|https://images.pexels.com/photos/10862233/pexels-photo-10862233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7514818/pexels-photo-7514818.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_jewellery|Jewellery|women_fine_jewellery|Women Fine Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/2.webp|https://images.pexels.com/photos/13924051/pexels-photo-13924051.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15925159/pexels-photo-15925159.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_jewellery|Jewellery|women_earrings|Women Earrings|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/2.webp|https://images.pexels.com/photos/10082804/pexels-photo-10082804.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15785528/pexels-photo-15785528.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_handbags_bags_wallets|Handbags, Bags & Wallets|https://images.pexels.com/photos/4430246/pexels-photo-4430246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6102145/pexels-photo-6102145.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/1.webp|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/2.webp",
//            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_luggages_trolleys|Women Luggages & Trolleys|https://images.pexels.com/photos/1682694/pexels-photo-1682694.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8212229/pexels-photo-8212229.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36933446/pexels-photo-36933446.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11499403/pexels-photo-11499403.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|mi_mobile|Mi Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|realme_mobile|Realme Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/3.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|samsung_mobile|Samsung Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|infinix_mobile|Infinix Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|oppo_mobile|OPPO Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|apple_mobile|Apple Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/3.webp",
//            "electronics|Electronics|mobiles|Mobiles|vivo_mobile|Vivo Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/1.webp",
//            "electronics|Electronics|mobiles|Mobiles|honor_mobile|Honor Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|asus_mobile|Asus Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/4.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/3.webp",
//            "electronics|Electronics|mobiles|Mobiles|poco_x2_mobile|Poco X2 Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10_mobile|Realme Narzo 10 Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/3.webp",
//            "electronics|Electronics|mobiles|Mobiles|infinix_hot_9_mobile|Infinix Hot 9 Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/2.webp",
//            "electronics|Electronics|mobiles|Mobiles|iqoo_3_mobile|IQOO 3 Mobile|https://images.pexels.com/photos/6901037/pexels-photo-6901037.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15092/pexels-photo-15092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5337378/pexels-photo-5337378.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5253002/pexels-photo-5253002.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|iphone_se_mobile|IPhone SE Mobile|https://images.pexels.com/photos/3945672/pexels-photo-3945672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18311092/pexels-photo-18311092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945691/pexels-photo-3945691.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945695/pexels-photo-3945695.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|motorola_razr_mobile|Motorola Razr Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/3.webp|https://images.pexels.com/photos/47261/pexels-photo-47261.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24181865/pexels-photo-24181865.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13844013/pexels-photo-13844013.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10a_mobile|Realme Narzo 10A Mobile|https://images.pexels.com/photos/3850561/pexels-photo-3850561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36680543/pexels-photo-36680543.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21854468/pexels-photo-21854468.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14486282/pexels-photo-14486282.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobiles|Mobiles|motorola_g8_power_lite_mobile|Motorola G8 Power Lite Mobile|https://images.pexels.com/photos/30639091/pexels-photo-30639091.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16442035/pexels-photo-16442035.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26775917/pexels-photo-26775917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19281836/pexels-photo-19281836.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cases|Mobile Cases|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/2.webp|https://images.pexels.com/photos/12359130/pexels-photo-12359130.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1670768/pexels-photo-1670768.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|headphones_headsets|Headphones & Headsets|https://rukminim2.flixcart.com/image/612/612/kz4gh3k0/headphone/c/v/r/-original-imagb7bmhdgghzxq.jpeg?q=70|https://images.pexels.com/photos/3394660/pexels-photo-3394660.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5650531/pexels-photo-5650531.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3081173/pexels-photo-3081173.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|power_banks|Power Banks|https://images.pexels.com/photos/4072683/pexels-photo-4072683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37475677/pexels-photo-37475677.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6296911/pexels-photo-6296911.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3921704/pexels-photo-3921704.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|screenguards|Screenguards|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpower-wireless-charger/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/1.webp|https://images.pexels.com/photos/12368095/pexels-photo-12368095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17610713/pexels-photo-17610713.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|memory_cards|Memory Cards|https://images.pexels.com/photos/5882581/pexels-photo-5882581.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7057/pexels-photo-7057.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2147082/pexels-photo-2147082.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32346379/pexels-photo-32346379.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|smart_headphones|Smart Headphones|https://images.pexels.com/photos/4887213/pexels-photo-4887213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28920288/pexels-photo-28920288.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/3.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-homepod-mini-cosmic-grey/1.webp",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cables|Mobile Cables|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/2.webp|https://images.pexels.com/photos/4219860/pexels-photo-4219860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1028674/pexels-photo-1028674.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/947407/pexels-photo-947407.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_chargers|Mobile Chargers|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/2.webp|https://images.pexels.com/photos/36012993/pexels-photo-36012993.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/216369/pexels-photo-216369.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_holders|Mobile Holders|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/2.webp|https://images.pexels.com/photos/694424/pexels-photo-694424.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35037256/pexels-photo-35037256.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_watches|Smart Watches|https://images.pexels.com/photos/11031515/pexels-photo-11031515.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18662969/pexels-photo-18662969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8760799/pexels-photo-8760799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18078946/pexels-photo-18078946.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_glasses_vr|Smart Glasses (VR)|https://images.pexels.com/photos/373905/pexels-photo-373905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6848112/pexels-photo-6848112.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7561900/pexels-photo-7561900.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19545617/pexels-photo-19545617.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_bands|Smart Bands|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/2.webp|https://images.pexels.com/photos/374619/pexels-photo-374619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4426516/pexels-photo-4426516.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|gaming_laptops|Gaming Laptops|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/1.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/2.webp|https://images.pexels.com/photos/3951449/pexels-photo-3951449.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19012030/pexels-photo-19012030.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|desktop_pcs|Desktop PCs|https://images.pexels.com/photos/6415/pexels-photo-6415.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1714341/pexels-photo-1714341.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31145466/pexels-photo-31145466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9072379/pexels-photo-9072379.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|gaming_accessories|Gaming & Accessories|https://images.pexels.com/photos/194511/pexels-photo-194511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1337247/pexels-photo-1337247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7887636/pexels-photo-7887636.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34592708/pexels-photo-34592708.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|computer_accessories|Computer Accessories|https://images.pexels.com/photos/392018/pexels-photo-392018.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12786602/pexels-photo-12786602.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4065748/pexels-photo-4065748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1420709/pexels-photo-1420709.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|external_hard_disks|External Hard Disks|https://images.pexels.com/photos/20213726/pexels-photo-20213726.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6937936/pexels-photo-6937936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32892856/pexels-photo-32892856.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28461160/pexels-photo-28461160.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|pendrives|Pendrives|https://images.pexels.com/photos/19954918/pexels-photo-19954918.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10133432/pexels-photo-10133432.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4792751/pexels-photo-4792751.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10336136/pexels-photo-10336136.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|laptop_skins_decals|Laptop Skins & Decals|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/1.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/2.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/3.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/3.webp",
//            "electronics|Electronics|laptops|Laptops|laptop_bags|Laptop Bags|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/1.webp|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/2.webp|https://images.pexels.com/photos/20318749/pexels-photo-20318749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5330724/pexels-photo-5330724.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|mouse|Mouse|https://images.pexels.com/photos/7151696/pexels-photo-7151696.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38006271/pexels-photo-38006271.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6932748/pexels-photo-6932748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12029640/pexels-photo-12029640.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|computer_peripherals|Computer Peripherals|https://images.pexels.com/photos/8297860/pexels-photo-8297860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12509206/pexels-photo-12509206.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4120698/pexels-photo-4120698.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20182039/pexels-photo-20182039.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|printers_ink_cartridges|Printers & Ink Cartridges|https://images.pexels.com/photos/4792283/pexels-photo-4792283.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/777001/pexels-photo-777001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574569/pexels-photo-9574569.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574453/pexels-photo-9574453.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|laptops|Laptops|monitors|Monitors|https://images.pexels.com/photos/3521937/pexels-photo-3521937.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/326512/pexels-photo-326512.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37148217/pexels-photo-37148217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6804606/pexels-photo-6804606.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|tablets|Tablets|apple_ipads|Apple IPads|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/1.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/2.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/3.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/4.webp",
//            "electronics|Electronics|speakers|Speakers|home_audio_speakers|Home Audio Speakers|https://rukminim2.flixcart.com/image/612/612/xif0q/speaker/6/z/2/-original-imahfgfkr5gkk9aq.jpeg?q=70|https://images.pexels.com/photos/191877/pexels-photo-191877.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/128611/pexels-photo-128611.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2651794/pexels-photo-2651794.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|home_theatres|Home Theatres|https://images.pexels.com/photos/31683433/pexels-photo-31683433.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/880874/pexels-photo-880874.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7031762/pexels-photo-7031762.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5008383/pexels-photo-5008383.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|soundbars|Soundbars|https://images.pexels.com/photos/374067/pexels-photo-374067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18542243/pexels-photo-18542243.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/373638/pexels-photo-373638.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10486182/pexels-photo-10486182.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|bluetooth_speakers|Bluetooth Speakers|https://images.pexels.com/photos/29606737/pexels-photo-29606737.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9767551/pexels-photo-9767551.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25450654/pexels-photo-25450654.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14017595/pexels-photo-14017595.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|speakers|Speakers|dth_set_top_box|DTH Set Top Box|https://images.pexels.com/photos/5207508/pexels-photo-5207508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40195705/pexels-photo-40195705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5207514/pexels-photo-5207514.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4777979/pexels-photo-4777979.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|dslr_mirrorless_camera|DSLR & Mirrorless Camera|https://rukminim2.flixcart.com/image/312/312/jfbfde80/camera/n/r/n/canon-eos-eos-3000d-dslr-original-imaf3t5h9yuyc5zu.jpeg?q=70|https://images.pexels.com/photos/17345664/pexels-photo-17345664.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26873540/pexels-photo-26873540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6891485/pexels-photo-6891485.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|compact_bridge_cameras|Compact & Bridge Cameras|https://images.pexels.com/photos/1595232/pexels-photo-1595232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1144699/pexels-photo-1144699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16104735/pexels-photo-16104735.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4372403/pexels-photo-4372403.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|sports_action_camera|Sports & Action Camera|https://images.pexels.com/photos/274973/pexels-photo-274973.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996005/pexels-photo-4996005.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4817132/pexels-photo-4817132.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1276531/pexels-photo-1276531.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|camera_accessories|Camera Accessories|https://images.pexels.com/photos/7776871/pexels-photo-7776871.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3405454/pexels-photo-3405454.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35001053/pexels-photo-35001053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34642455/pexels-photo-34642455.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|lens|Camera Lens|https://images.pexels.com/photos/838680/pexels-photo-838680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/734535/pexels-photo-734535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11713436/pexels-photo-11713436.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29293968/pexels-photo-29293968.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|camera|Camera|camera_tripods|Camera Tripods|https://images.pexels.com/photos/20382217/pexels-photo-20382217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15062358/pexels-photo-15062358.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11331527/pexels-photo-11331527.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20954706/pexels-photo-20954706.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_runners|Bed Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/19284508/2022/7/28/92df52de-27dc-4d72-8ab4-fee2c82c85081659003977664DreamscapeUnisexPinkBedsheets1.jpg|https://images.pexels.com/photos/5629134/pexels-photo-5629134.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6903157/pexels-photo-6903157.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27671434/pexels-photo-27671434.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|mattress_protectors|Mattress Protectors|https://images.pexels.com/photos/3030684/pexels-photo-3030684.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4740586/pexels-photo-4740586.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28513849/pexels-photo-28513849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5629138/pexels-photo-5629138.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedsheets|Bedsheets|https://images.pexels.com/photos/3682240/pexels-photo-3682240.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7019028/pexels-photo-7019028.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12200060/pexels-photo-12200060.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38651677/pexels-photo-38651677.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedding_sets|Bedding Sets|https://images.pexels.com/photos/212269/pexels-photo-212269.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10373508/pexels-photo-10373508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22711513/pexels-photo-22711513.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32418083/pexels-photo-32418083.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|blankets_quilts_dohars|Blankets, Quilts & Dohars|https://images.pexels.com/photos/8266834/pexels-photo-8266834.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6633533/pexels-photo-6633533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30399211/pexels-photo-30399211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30350497/pexels-photo-30350497.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|pillows_pillow_covers|Pillows & Pillow Covers|https://images.pexels.com/photos/12326545/pexels-photo-12326545.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9582579/pexels-photo-9582579.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9290601/pexels-photo-9290601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1239221/pexels-photo-1239221.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_covers|Bed Covers|https://images.pexels.com/photos/10980163/pexels-photo-10980163.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9262007/pexels-photo-9262007.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6782479/pexels-photo-6782479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980080/pexels-photo-19980080.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|diwan_sets|Diwan Sets|https://images.pexels.com/photos/3557507/pexels-photo-3557507.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/910458/pexels-photo-910458.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34574606/pexels-photo-34574606.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7147299/pexels-photo-7147299.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|chair_pads_covers|Chair Pads & Covers|https://images.pexels.com/photos/462197/pexels-photo-462197.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/1.webp|https://images.pexels.com/photos/18829799/pexels-photo-18829799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29917912/pexels-photo-29917912.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|sofa_covers|Sofa Covers|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/2.webp|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/1.webp|https://images.pexels.com/photos/6758245/pexels-photo-6758245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7394787/pexels-photo-7394787.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|floor_runners|Floor Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27868878/2024/2/25/53b7c07f-24a7-48e2-a791-f63dce4a0c981708844793060SANACARPETMulticolouredFloralAnti-SkidPolyesterCarpet1.jpg|https://images.pexels.com/photos/5417293/pexels-photo-5417293.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15896109/pexels-photo-15896109.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29614636/pexels-photo-29614636.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|carpets|Carpets|https://images.pexels.com/photos/6933860/pexels-photo-6933860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/45980/pexels-photo-45980.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16820353/pexels-photo-16820353.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37219747/pexels-photo-37219747.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|floor_mats_dhurries|Floor Mats & Dhurries|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/2.webp|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/1.webp|https://images.pexels.com/photos/7417505/pexels-photo-7417505.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35769473/pexels-photo-35769473.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|flooring|Flooring|door_mats|Door Mats|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/2.webp|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/1.webp|https://images.pexels.com/photos/5840868/pexels-photo-5840868.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2950003/pexels-photo-2950003.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bath_towels|Bath Towels|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/16719378/2024/7/2/97418bad-8216-494f-a863-9b159aec93cf1719897710069JockeyPackof2CottonTerry500GSMUltrasoftandDurableSolidHandTo1.jpg|https://images.pexels.com/photos/12679/pexels-photo-12679.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20523089/pexels-photo-20523089.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8535619/pexels-photo-8535619.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|hand_face_towels|Hand & Face Towels|https://images.pexels.com/photos/7691101/pexels-photo-7691101.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210373/pexels-photo-4210373.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5833316/pexels-photo-5833316.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5732631/pexels-photo-5732631.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|beach_towels|Beach Towels|https://images.pexels.com/photos/271711/pexels-photo-271711.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1304110/pexels-photo-1304110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18674213/pexels-photo-18674213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25649794/pexels-photo-25649794.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|towels_set|Towels Set|https://images.pexels.com/photos/282892/pexels-photo-282892.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210371/pexels-photo-4210371.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6188050/pexels-photo-6188050.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20665616/pexels-photo-20665616.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bath_rugs|Bath Rugs|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/2.webp|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/1.webp|https://images.pexels.com/photos/34667257/pexels-photo-34667257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18894029/pexels-photo-18894029.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bath_robes|Bath Robes|https://images.pexels.com/photos/10924511/pexels-photo-10924511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/433624/pexels-photo-433624.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6202201/pexels-photo-6202201.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6634836/pexels-photo-6634836.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|bathroom_accessories|Bathroom Accessories|https://images.pexels.com/photos/10574059/pexels-photo-10574059.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3761560/pexels-photo-3761560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4194705/pexels-photo-4194705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980252/pexels-photo-19980252.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|bath|Bath|shower_curtains|Shower Curtains|https://images.pexels.com/photos/11430604/pexels-photo-11430604.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5137571/pexels-photo-5137571.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5825561/pexels-photo-5825561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6430731/pexels-photo-6430731.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|floor_lamps|Floor Lamps|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/productimage/2021/6/10/5be80654-037c-4591-99c9-a403646971981623344562713-1.jpg|https://images.pexels.com/photos/112811/pexels-photo-112811.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6045108/pexels-photo-6045108.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11850174/pexels-photo-11850174.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|ceiling_lamps|Ceiling Lamps|https://images.pexels.com/photos/7533932/pexels-photo-7533932.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17940121/pexels-photo-17940121.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9628657/pexels-photo-9628657.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12202390/pexels-photo-12202390.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|table_lamps|Table Lamps|https://images.pexels.com/photos/16683246/pexels-photo-16683246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/573298/pexels-photo-573298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35392792/pexels-photo-35392792.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6053887/pexels-photo-6053887.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|wall_lamps|Wall Lamps|https://images.pexels.com/photos/721039/pexels-photo-721039.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2187141/pexels-photo-2187141.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9510486/pexels-photo-9510486.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29343148/pexels-photo-29343148.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|outdoor_lamps|Outdoor Lamps|https://images.pexels.com/photos/1683405/pexels-photo-1683405.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1554740/pexels-photo-1554740.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18074245/pexels-photo-18074245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37460216/pexels-photo-37460216.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|string_lights|String Lights|https://images.pexels.com/photos/3368822/pexels-photo-3368822.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1791747/pexels-photo-1791747.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5836535/pexels-photo-5836535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15433418/pexels-photo-15433418.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|plants_planters|Plants & Planters|https://images.pexels.com/photos/8549167/pexels-photo-8549167.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3952031/pexels-photo-3952031.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7245621/pexels-photo-7245621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31479159/pexels-photo-31479159.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|aromas_candles|Aromas & Candles|https://images.pexels.com/photos/5816995/pexels-photo-5816995.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18408479/pexels-photo-18408479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31588837/pexels-photo-31588837.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35047380/pexels-photo-35047380.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|clocks|Clocks|https://images.pexels.com/photos/6616845/pexels-photo-6616845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2182727/pexels-photo-2182727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38699699/pexels-photo-38699699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/707676/pexels-photo-707676.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|mirrors|Mirrors|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/28460938/2024/3/22/7fb09e9c-86e0-4602-b54e-fa5c0171b50b1711104156746IrregularMirrorHomeDecor1.jpg|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/1.webp|https://images.pexels.com/photos/7319683/pexels-photo-7319683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28533705/pexels-photo-28533705.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|wall_decor|Wall Décor|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/2.webp|https://cdn.dummyjson.com/product-images/home-decoration/family-tree-photo-frame/1.webp|https://images.pexels.com/photos/6925113/pexels-photo-6925113.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6580004/pexels-photo-6580004.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|festive_decor|Festive Decor|https://images.pexels.com/photos/4078516/pexels-photo-4078516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34387790/pexels-photo-34387790.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443947/pexels-photo-6443947.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34473414/pexels-photo-34473414.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|pooja_essentials|Pooja Essentials|https://images.pexels.com/photos/6213685/pexels-photo-6213685.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31372002/pexels-photo-31372002.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10182772/pexels-photo-10182772.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37650548/pexels-photo-37650548.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|wall_shelves|Wall Shelves|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/2.webp|https://images.pexels.com/photos/6851831/pexels-photo-6851831.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19857232/pexels-photo-19857232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25906569/pexels-photo-25906569.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|fountains|Fountains|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/2.webp|https://images.pexels.com/photos/11651964/pexels-photo-11651964.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17087908/pexels-photo-17087908.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|showpieces_vases|Showpieces & Vases|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/2.webp|https://images.pexels.com/photos/40167789/pexels-photo-40167789.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35211364/pexels-photo-35211364.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|ottoman|Ottoman|https://images.pexels.com/photos/32123565/pexels-photo-32123565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12289357/pexels-photo-12289357.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17771969/pexels-photo-17771969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19580060/pexels-photo-19580060.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|cushions_cushion_covers|Cushions & Cushion Covers|https://images.pexels.com/photos/30163102/pexels-photo-30163102.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36482950/pexels-photo-36482950.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7218188/pexels-photo-7218188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18258470/pexels-photo-18258470.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|curtains|Curtains|https://images.pexels.com/photos/5835536/pexels-photo-5835536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35165092/pexels-photo-35165092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23110026/pexels-photo-23110026.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12944779/pexels-photo-12944779.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|home_decor|Home Décor|home_gift_sets|Home Gift Sets|https://cdn.dummyjson.com/product-images/home-decoration/table-lamp/1.webp|https://images.pexels.com/photos/7674564/pexels-photo-7674564.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1666070/pexels-photo-1666070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3873488/pexels-photo-3873488.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_runners|Table Runners|https://cdn.dummyjson.com/product-images/kitchen-accessories/bamboo-spatula/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/1.webp|https://images.pexels.com/photos/34232560/pexels-photo-34232560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8108068/pexels-photo-8108068.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|dinnerware_serveware|Dinnerware & Serveware|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/2.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-whisk/1.webp|https://images.pexels.com/photos/3847465/pexels-photo-3847465.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8903360/pexels-photo-8903360.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|cups_mugs|Cups And Mugs|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/2.webp|https://images.pexels.com/photos/9261414/pexels-photo-9261414.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7192236/pexels-photo-7192236.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bakeware_cookware|Bakeware & Cookware|https://cdn.dummyjson.com/product-images/kitchen-accessories/carbon-steel-wok/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/chopping-board/1.webp|https://images.pexels.com/photos/12586814/pexels-photo-12586814.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29462835/pexels-photo-29462835.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|kitchen_storage_tools|Kitchen Storage & Tools|https://cdn.dummyjson.com/product-images/kitchen-accessories/citrus-squeezer-yellow/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/egg-slicer/1.webp|https://images.pexels.com/photos/10568352/pexels-photo-10568352.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8175333/pexels-photo-8175333.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bar_drinkware|Bar & Drinkware|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/2.webp|https://images.pexels.com/photos/7282898/pexels-photo-7282898.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19393192/pexels-photo-19393192.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_covers_furnishings|Table Covers & Furnishings|https://cdn.dummyjson.com/product-images/kitchen-accessories/fine-mesh-strainer/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/fork/1.webp|https://images.pexels.com/photos/8158010/pexels-photo-8158010.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9512746/pexels-photo-9512746.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|television|Television|television|Television|https://rukminim2.flixcart.com/image/312/312/xif0q/television/9/p/9/-original-imah2v29z86u7b79.jpeg?q=70|https://images.pexels.com/photos/5202925/pexels-photo-5202925.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13806260/pexels-photo-13806260.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6020432/pexels-photo-6020432.jpeg?auto=compress&cs=tinysrgb&w=800",
//            "electronics|Electronics|cameras|Cameras|cameras|Cameras|https://images.pexels.com/photos/15111004/pexels-photo-15111004.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12548528/pexels-photo-12548528.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19358981/pexels-photo-19358981.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22064439/pexels-photo-22064439.jpeg?auto=compress&cs=tinysrgb&w=800"
//    };
//
//    private static final String[] STYLES = {"Signature", "Everyday", "Classic", "Premium", "Essential", "Heritage"};
//    private static final String[] COLORS = {"Black", "White", "Blue", "Red", "Green", "Pink", "Yellow", "Brown", "Grey", "Beige"};
//
//    @Override
//    public void run(String... args) {
//        if (!ENABLED) return;
//
//        // Demo seller pehle se hai to wahi use karo, warna naya banao
//        Seller seller = sellerRepository.findByEmail(DEMO_EMAIL);
//        if (seller == null) seller = createDemoSeller();
//
//        // Pehle se bane demo products (category ke hisaab se) — inki sirf photos update hongi, double nahi banenge
//        Map<String, List<Product>> existing = new HashMap<>();
//        for (Product p : productRepository.findBySellerId(seller.getId())) {
//            if (p.getCategory() != null) {
//                existing.computeIfAbsent(p.getCategory().getCategoryId(), x -> new ArrayList<>()).add(p);
//            }
//        }
//
//        List<Product> products = new ArrayList<>();
//        int created = 0, updated = 0;
//
//        for (int i = 0; i < ROWS.length; i++) {
//            String[] r = ROWS[i].split("\\|");
//            String[] imgs = java.util.Arrays.copyOfRange(r, 6, r.length);   // is category ki saari photos
//            List<Product> old = existing.getOrDefault(r[4], new ArrayList<>());
//
//            Category c1 = findOrCreate(r[0], r[1], 1, null);
//            Category c2 = findOrCreate(r[2], r[3], 2, c1);
//            // Agar level-3 id aur level-2 id same hai to product seedha level 2 me jaata hai
//            Category c3 = r[4].equals(r[2]) ? c2 : findOrCreate(r[4], r[5], 3, c2);
//
//            // 1) Purane demo products ko nayi alag photo do (double nahi banenge)
//            for (int k = 0; k < old.size(); k++) {
//                old.get(k).setImages(imagesFor(imgs, k));
//                products.add(old.get(k));
//                updated++;
//            }
//
//            // 2) Jitne kam hain utne naye banao — har category me kul 4 products
//            for (int k = old.size(); k < imgs.length; k++) {
//                products.add(buildProduct(r, imgs, c3, seller, i, k));
//                created++;
//            }
//        }
//
//        productRepository.saveAll(products);
//        System.out.println("[DemoDataSeeder] " + created + " naye demo products bane, " + updated + " purano ki photos update hui ✔");
//    }
//
//    private Seller createDemoSeller() {
//        Seller seller = new Seller();
//        seller.setSellerName("Shopzy Studio");
//        seller.setEmail(DEMO_EMAIL);
//        seller.setMobile("9999999999");
//        seller.setGSTIN("22AAAAA0000A1Z5");
//        seller.setRole(USER_ROLE.ROLE_SELLER);
//        seller.setEmailVerified(true);
//        seller.setAccountStatus(AccountStatus.ACTIVE);
//        seller.getBusinessDetails().setBusinessName("Shopzy Studio");
//        seller.getBusinessDetails().setBusinessEmail(DEMO_EMAIL);
//        seller.getBusinessDetails().setBusinessMobile("9999999999");
//        seller.getPickupAddress().setName("Shopzy Studio");
//        seller.getPickupAddress().setCity("Jaipur");
//        seller.getPickupAddress().setState("Rajasthan");
//        seller.getPickupAddress().setPinCode("302001");
//        seller.getPickupAddress().setMobile("9999999999");
//        return sellerRepository.save(seller);
//    }
//
//    private Category findOrCreate(String id, String name, int level, Category parent) {
//        Category existing = categoryRepository.findByCategoryId(id);
//        if (existing != null) return existing;
//        Category c = new Category();
//        c.setCategoryId(id);
//        c.setName(name);
//        c.setLevel(level);
//        c.setParentCategory(parent);
//        return categoryRepository.save(c);
//    }
//
//    // Product k ki pehli photo sirf usi ki hai; doosri photo (hover) agle product ki
//    private List<String> imagesFor(String[] imgs, int k) {
//        String main = imgs[k % imgs.length];
//        String other = imgs[(k + 1) % imgs.length];
//        return new ArrayList<>(List.of(main, other));
//    }
//
//    private Product buildProduct(String[] r, String[] imgs, Category category, Seller seller, int i, int k) {
//        String level1 = r[0];
//        String level2 = r[2];
//        String name = r[5];
//        int seed = Math.abs((r[4] + k).hashCode());
//
//        // Category ke hisaab se price range
//        int base;
//        if (level2.equals("mobiles")) base = 9999 + (seed % 30) * 1000;
//        else if (level1.equals("electronics")) base = 799 + (seed % 40) * 250;
//        else if (level1.equals("furniture")) base = 499 + (seed % 30) * 150;
//        else base = 599 + (seed % 25) * 100;
//
//        int discount = 20 + (seed % 5) * 10;              // 20% - 60%
//        int mrp = (int) Math.round(base / (1 - discount / 100.0) / 10.0) * 10 - 1;
//        int selling = base;
//
//        String sizes;
//        if (level2.contains("footwear")) sizes = "6,7,8,9,10";
//        else if (level1.equals("men") || level1.equals("women")) sizes = "S,M,L,XL,XXL";
//        else sizes = "FREE";
//
//        Product p = new Product();
//        p.setTitle(STYLES[(i + k) % STYLES.length] + " " + name);
//        p.setDescription("A " + STYLES[(i + k) % STYLES.length].toLowerCase() + " pick from our " + name
//                + " collection — thoughtfully made, easy to love and built to last.");
//        p.setMrpPrice(mrp);
//        p.setSellingPrice(selling);
//        p.setDiscountPercent((int) Math.round((mrp - selling) * 100.0 / mrp));
//        p.setQuantity(100);
//        p.setColor(COLORS[seed % COLORS.length]);
//        p.setImages(imagesFor(imgs, k));
//        p.setNumRatings(0);
//        p.setCategory(category);
//        p.setSeller(seller);
//        p.setCreatedAt(LocalDateTime.now());
//        p.setSizes(sizes);
//        return p;
//    }
//}


package com.raghav.ecommerce.config;

import com.raghav.ecommerce.domain.AccountStatus;
import com.raghav.ecommerce.domain.USER_ROLE;
import com.raghav.ecommerce.model.Category;
import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.model.Seller;
import com.raghav.ecommerce.repository.CategoryRepository;
import com.raghav.ecommerce.repository.ProductRepository;
import com.raghav.ecommerce.repository.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * DEMO DATA — backend start hote hi mega menu ki HAR category (216 + 4) me 6-8 asli jaise
 * demo products daal deta hai (kul 1326 products): brand, sahi daam, discount, size, colour, specs.
 *
 * - Photos: har category ki 4 photos (ROWS) — har product ki pehli photo alag ghoomti hai.
 * - Dobara chalane par double nahi banta: (category + title) pehle se hai to skip.
 * - Purane generic demo products ("Essential Women Sarees", "Classic Smart Watches" ...) hata deta hai,
 *   lekin jo kisi order / cart / wishlist me hai use nahi chhoota.
 * - Band karna ho to ENABLED = false. Purane generic rakhne ho to REMOVE_OLD_GENERIC = false.
 */
@Component
public class DemoDataSeeder implements CommandLineRunner {

    private static final boolean ENABLED = true;
    private static final boolean REMOVE_OLD_GENERIC = true;
    private static final String DEMO_EMAIL = "demo.seller@shopzy.in";

    // Ye categories site se hata di gayi hain — inke purane demo products bhi delete honge
    private static final Set<String> REMOVED_CATEGORIES = Set.of(
            "women_lingerie_sleepwear", "women_bra", "women_briefs", "women_shapewear",
            "women_sleepwear_loungewear", "women_swimwear", "women_camisoles_thermals");

    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DemoDataSeeder(SellerRepository sellerRepository,
                          CategoryRepository categoryRepository,
                          ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    // level1Id | level1Name | level2Id | level2Name | level3Id | level3Name | image1 | image2 | image3 | image4
    private static final String[] ROWS = {
            "men|Men|men_topwear|Topwear|men_t_shirts|Men T-Shirts|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/2024/AUGUST/26/zLmM5KEB_e4781ac0cc0945119e653f4130994bd2.jpg|https://images.pexels.com/photos/9558713/pexels-photo-9558713.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6311251/pexels-photo-6311251.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5159242/pexels-photo-5159242.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_casual_shirts|Men Casual Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/2.webp|https://images.pexels.com/photos/35182320/pexels-photo-35182320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40070436/pexels-photo-40070436.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_formal_shirts|Men Formal Shirts|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/1.webp|https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/2.webp|https://images.pexels.com/photos/29765480/pexels-photo-29765480.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35415004/pexels-photo-35415004.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_sweatshirts|Men Sweatshirts|https://images.pexels.com/photos/19338764/pexels-photo-19338764.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34582212/pexels-photo-34582212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9920820/pexels-photo-9920820.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6650360/pexels-photo-6650360.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_sweaters|Men Sweaters|https://images.pexels.com/photos/10427343/pexels-photo-10427343.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7237067/pexels-photo-7237067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14269719/pexels-photo-14269719.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558897/pexels-photo-9558897.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_jackets|Men Jackets|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23029834/2023/9/18/96c015ae-1090-4036-954b-d9c80085b1d71695022844653-HRX-by-Hrithik-Roshan-Men-Jackets-6981695022843934-1.jpg|https://images.pexels.com/photos/18720773/pexels-photo-18720773.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21619158/pexels-photo-21619158.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18633077/pexels-photo-18633077.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_blazers_and_coats|Men Blazers & Coats|https://images.pexels.com/photos/9210389/pexels-photo-9210389.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2955375/pexels-photo-2955375.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32670017/pexels-photo-32670017.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29722004/pexels-photo-29722004.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_suits|Men Suits|https://images.pexels.com/photos/2505053/pexels-photo-2505053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/935969/pexels-photo-935969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050435/pexels-photo-6050435.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6050409/pexels-photo-6050409.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_rain_jackets|Men Rain Jackets|https://images.pexels.com/photos/6851461/pexels-photo-6851461.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2766298/pexels-photo-2766298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12505397/pexels-photo-12505397.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26838402/pexels-photo-26838402.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_indian_and_festive_wear|Men Indian & Festive Wear|https://rukminim2.flixcart.com/image/612/612/xif0q/ethnic-set/o/n/o/l-02-langit-original-imah4jwwctkjtman.jpeg?q=70|https://shreeman.in/cdn/shop/files/20_3cfbd5a3-ecb6-482a-b798-7ffd9de1c784.jpg?v=1712061674&width=700|https://images.pexels.com/photos/13624155/pexels-photo-13624155.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217656/pexels-photo-8217656.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_kurtas_and_kurta_sets|Men Kurtas & Kurta Sets|https://images.pexels.com/photos/13222257/pexels-photo-13222257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777497/pexels-photo-16777497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38443317/pexels-photo-38443317.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36697919/pexels-photo-36697919.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_sherwanis|Men Sherwanis|https://images.pexels.com/photos/12963836/pexels-photo-12963836.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18694227/pexels-photo-18694227.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37163999/pexels-photo-37163999.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28802603/pexels-photo-28802603.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_nehru_jackets|Men Nehru Jackets|https://images.pexels.com/photos/34423748/pexels-photo-34423748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16777516/pexels-photo-16777516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33049965/pexels-photo-33049965.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32947800/pexels-photo-32947800.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_topwear|Topwear|men_dhotis|Men Dhotis|https://images.pexels.com/photos/20871919/pexels-photo-20871919.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14934020/pexels-photo-14934020.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29041818/pexels-photo-29041818.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29153202/pexels-photo-29153202.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_bottomwear|Bottomwear|men_jeans|Men Jeans|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/20122324/2022/9/22/91c61c45-fe17-4d1d-8e20-0aaaf90186b61663827920015RaymondSlimFitBlueJeansForMen1.jpg|https://images.pexels.com/photos/9775489/pexels-photo-9775489.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16069736/pexels-photo-16069736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4258605/pexels-photo-4258605.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_bottomwear|Bottomwear|men_casual_trousers|Men Casual Trousers|https://images.pexels.com/photos/11034966/pexels-photo-11034966.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10004175/pexels-photo-10004175.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897539/pexels-photo-2897539.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9464625/pexels-photo-9464625.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_bottomwear|Bottomwear|men_formal_trousers|Men Formal Trousers|https://images.pexels.com/photos/2815417/pexels-photo-2815417.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9558246/pexels-photo-9558246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2897533/pexels-photo-2897533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17082930/pexels-photo-17082930.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_bottomwear|Bottomwear|men_shorts|Men Shorts|https://images.pexels.com/photos/1098364/pexels-photo-1098364.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13081849/pexels-photo-13081849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25315887/pexels-photo-25315887.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18178103/pexels-photo-18178103.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_bottomwear|Bottomwear|men_track_pants_and_joggers|Men Track Pants & Joggers|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24528350/2023/8/17/d9ee03c9-7e15-49e4-8f15-0b6f38e568121692275415567TrackPants1.jpg|https://images.pexels.com/photos/11328499/pexels-photo-11328499.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9901666/pexels-photo-9901666.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9499128/pexels-photo-9499128.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_briefs_and_trunks|Men Briefs & Trunks|https://images.pexels.com/photos/8875156/pexels-photo-8875156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874670/pexels-photo-8874670.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874933/pexels-photo-8874933.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874910/pexels-photo-8874910.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_boxers|Men Boxers|https://images.pexels.com/photos/8875171/pexels-photo-8875171.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8874672/pexels-photo-8874672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8875173/pexels-photo-8875173.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17510799/pexels-photo-17510799.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_vests|Men Vests|https://images.pexels.com/photos/7711284/pexels-photo-7711284.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13544290/pexels-photo-13544290.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17604597/pexels-photo-17604597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38216749/pexels-photo-38216749.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_sleepwear_and_loungewear|Men Sleepwear & Loungewear|https://images.pexels.com/photos/8416232/pexels-photo-8416232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30444027/pexels-photo-30444027.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6976782/pexels-photo-6976782.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443431/pexels-photo-6443431.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_thermals|Men Thermals|https://images.pexels.com/photos/5924447/pexels-photo-5924447.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4763665/pexels-photo-4763665.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6712133/pexels-photo-6712133.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6799715/pexels-photo-6799715.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_innerwear_and_sleepwear|Innerwear And Sleepwear|men_plus_size|Men Plus Size|https://images.pexels.com/photos/8217535/pexels-photo-8217535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8217536/pexels-photo-8217536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33676395/pexels-photo-33676395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30589840/pexels-photo-30589840.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_footwear|Footwear|men_casual_shoes|Men Casual Shoes|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27107914/2024/1/30/bd09f650-d8a4-4570-8e53-e2cfafc4ea6c1706609911015SparxMenMeshRunningShoes1.jpg|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/24651572/2023/8/25/4fbf6d8c-d093-46c5-a5a6-7dd67c0c76551692964752597HouseofPataudiMenTanFauxLeatherFormalSlipOnLoafers1.jpg|https://images.pexels.com/photos/40263252/pexels-photo-40263252.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40325298/pexels-photo-40325298.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_footwear|Footwear|men_sports_shoes|Men Sports Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/2.webp|https://images.pexels.com/photos/260044/pexels-photo-260044.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8454904/pexels-photo-8454904.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_footwear|Footwear|men_formal_shoes|Men Formal Shoes|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/2.webp|https://images.pexels.com/photos/12210270/pexels-photo-12210270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10352336/pexels-photo-10352336.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_footwear|Footwear|men_sneakers|Men Sneakers|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/1.webp|https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/2.webp|https://images.pexels.com/photos/5560288/pexels-photo-5560288.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4901935/pexels-photo-4901935.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_footwear|Footwear|men_sandals_and_floaters|Men Sandals & Floaters|https://images.pexels.com/photos/4996738/pexels-photo-4996738.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/952843/pexels-photo-952843.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23692992/pexels-photo-23692992.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9214975/pexels-photo-9214975.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_footwear|Footwear|men_flip_flops|Men Flip Flops|https://images.pexels.com/photos/14934601/pexels-photo-14934601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34447680/pexels-photo-34447680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30335806/pexels-photo-30335806.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35166184/pexels-photo-35166184.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_footwear|Footwear|men_socks|Men Socks|https://images.pexels.com/photos/15195233/pexels-photo-15195233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14943497/pexels-photo-14943497.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10563910/pexels-photo-10563910.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12932537/pexels-photo-12932537.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sunglasses_and_frames|Men Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/2.webp|https://images.pexels.com/photos/5202048/pexels-photo-5202048.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2767694/pexels-photo-2767694.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_watches|Men Watches|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/1.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/2.webp|https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/3.webp|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/1.webp",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_and_active_wear|Men Sports & Active Wear|https://images.pexels.com/photos/1311617/pexels-photo-1311617.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036883/pexels-photo-5036883.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29259727/pexels-photo-29259727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3927383/pexels-photo-3927383.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_sandals|Men Sports Sandals|https://images.pexels.com/photos/15669736/pexels-photo-15669736.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3342769/pexels-photo-3342769.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996974/pexels-photo-4996974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6008231/pexels-photo-6008231.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_active_t_shirts|Men Active T-Shirts|https://images.pexels.com/photos/8498700/pexels-photo-8498700.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/1.webp|https://images.pexels.com/photos/4004222/pexels-photo-4004222.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6998798/pexels-photo-6998798.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_track_pants_and_shorts|Men Track Pants & Shorts|https://images.pexels.com/photos/13069429/pexels-photo-13069429.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8567521/pexels-photo-8567521.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30710156/pexels-photo-30710156.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11029622/pexels-photo-11029622.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_tracksuits|Men Tracksuits|https://images.pexels.com/photos/9488418/pexels-photo-9488418.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2652236/pexels-photo-2652236.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6158668/pexels-photo-6158668.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33558335/pexels-photo-33558335.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_jackets_and_sweatshirts|Men Jackets & Sweatshirts|https://images.pexels.com/photos/14611192/pexels-photo-14611192.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9775733/pexels-photo-9775733.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28701960/pexels-photo-28701960.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15127546/pexels-photo-15127546.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_sports_accessories|Men Sports Accessories|https://images.pexels.com/photos/4162451/pexels-photo-4162451.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6740821/pexels-photo-6740821.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35567437/pexels-photo-35567437.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19025674/pexels-photo-19025674.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_personal_care_and_grooming|Personal Care And Grooming|men_swimwear|Men Swimwear|https://images.pexels.com/photos/9030517/pexels-photo-9030517.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6011894/pexels-photo-6011894.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8028651/pexels-photo-8028651.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8688187/pexels-photo-8688187.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_wallets|Men Wallets|https://images.pexels.com/photos/915917/pexels-photo-915917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/164637/pexels-photo-164637.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7952556/pexels-photo-7952556.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4452393/pexels-photo-4452393.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_belts|Men Belts|https://images.pexels.com/photos/6654763/pexels-photo-6654763.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31323080/pexels-photo-31323080.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959214/pexels-photo-31959214.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959216/pexels-photo-31959216.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_perfumes_and_body_mists|Men Perfumes & Body Mists|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/1.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/2.webp|https://images.pexels.com/photos/15096784/pexels-photo-15096784.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7703038/pexels-photo-7703038.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_trimmers|Men Trimmers|https://images.pexels.com/photos/897262/pexels-photo-897262.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6007395/pexels-photo-6007395.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7253884/pexels-photo-7253884.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16553361/pexels-photo-16553361.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_deodorants|Men Deodorants|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/1.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/2.webp|https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/3.webp|https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/3.webp",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_ties_cufflinks_and_pocket_squares|Men Ties, Cufflinks & Pocket Squares|https://images.pexels.com/photos/21928749/pexels-photo-21928749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38270/pexels-photo-38270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32392710/pexels-photo-32392710.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34342976/pexels-photo-34342976.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_accessory_gift_sets|Men Accessory Gift Sets|https://images.pexels.com/photos/190930/pexels-photo-190930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/264771/pexels-photo-264771.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6087540/pexels-photo-6087540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1303087/pexels-photo-1303087.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_caps_and_hats|Men Caps & Hats|https://images.pexels.com/photos/18417334/pexels-photo-18417334.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13265200/pexels-photo-13265200.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27401299/pexels-photo-27401299.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29925645/pexels-photo-29925645.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_mufflers_scarves_and_gloves|Men Mufflers, Scarves & Gloves|https://images.pexels.com/photos/1903760/pexels-photo-1903760.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20281423/pexels-photo-20281423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16049198/pexels-photo-16049198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35830300/pexels-photo-35830300.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_phone_cases|Men Phone Cases|https://images.pexels.com/photos/374140/pexels-photo-374140.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7742503/pexels-photo-7742503.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13706809/pexels-photo-13706809.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18403793/pexels-photo-18403793.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_rings_and_wristwear|Men Rings & Wristwear|https://images.pexels.com/photos/16304565/pexels-photo-16304565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9742330/pexels-photo-9742330.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2442905/pexels-photo-2442905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15325460/pexels-photo-15325460.jpeg?auto=compress&cs=tinysrgb&w=800",
            "men|Men|men_fashion_accessories|Fashion Accessories|men_helmets|Men Helmets|https://images.pexels.com/photos/9835238/pexels-photo-9835238.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12000077/pexels-photo-12000077.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5803274/pexels-photo-5803274.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5355536/pexels-photo-5355536.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtas_and_suits|Women Kurtas & Suits|https://images.pexels.com/photos/11661072/pexels-photo-11661072.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33824984/pexels-photo-33824984.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30196701/pexels-photo-30196701.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1322993/pexels-photo-1322993.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_kurtis_tunics_tops|Women Kurtis, Tunics & Tops|https://images.pexels.com/photos/19556879/pexels-photo-19556879.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7371768/pexels-photo-7371768.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36311379/pexels-photo-36311379.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35521738/pexels-photo-35521738.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_sarees|Women Sarees|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950865601_540x.jpg?v=1704965372|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950931137_540x.jpg?v=1704965372|https://images.pexels.com/photos/7920188/pexels-photo-7920188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27719401/pexels-photo-27719401.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_ethnic_wear|Women Ethnic Wear|https://images.pexels.com/photos/37523799/pexels-photo-37523799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512776/pexels-photo-28512776.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28512787/pexels-photo-28512787.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13178920/pexels-photo-13178920.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_leggings_salwars_churidars|Women Leggings, Salwars & Churidars|https://rukminim2.flixcart.com/image/612/612/xif0q/skirt/k/o/h/free-1-00003-custom-fashion-original-imagztykafwbqupf.jpeg?q=70|https://images.pexels.com/photos/19932178/pexels-photo-19932178.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20382095/pexels-photo-20382095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19271174/pexels-photo-19271174.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_skirts_palazzos|Women Skirts & Palazzos|https://images.pexels.com/photos/14997427/pexels-photo-14997427.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16721105/pexels-photo-16721105.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40293396/pexels-photo-40293396.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39030338/pexels-photo-39030338.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dress_materials|Women Dress Materials|https://www.karagiri.com/cdn/shop/products/patola-saree-hot-pink-patola-saree-silk-saree-online-32261950996673_540x.jpg?v=1704965372|https://rukminim2.flixcart.com/image/832/832/xif0q/sari/z/a/4/free-saree-new2023-simple-light-weight-saree-chiffon-print-saree-original-imagzyhp2azy7rwz.jpeg?q=70&crop=false|https://images.pexels.com/photos/34933671/pexels-photo-34933671.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20841148/pexels-photo-20841148.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_lehenga_cholis|Women Lehenga Cholis|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/23807268/2023/6/29/9930b235-5318-4755-abbe-08f99e969e781688026636544LehengaCholi7.jpg|https://images.pexels.com/photos/12730873/pexels-photo-12730873.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28405815/pexels-photo-28405815.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33343591/pexels-photo-33343591.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_dupattas_shawls|Women Dupattas & Shawls|https://images.pexels.com/photos/11004120/pexels-photo-11004120.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18659930/pexels-photo-18659930.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158070/pexels-photo-35158070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35158052/pexels-photo-35158052.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_jackets|Women Jackets|https://images.pexels.com/photos/1818431/pexels-photo-1818431.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8386453/pexels-photo-8386453.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/39054974/pexels-photo-39054974.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5036161/pexels-photo-5036161.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_belts_scarves_more|Women Belts, Scarves & More|https://images.pexels.com/photos/31432650/pexels-photo-31432650.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8014845/pexels-photo-8014845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37483619/pexels-photo-37483619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31959217/pexels-photo-31959217.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_indian_and_fusion_wear|Indian & Fusion Wear|women_watches_wearables|Women Watches & Wearables|https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/2.webp|https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/3.webp",
            "women|Women|women_western_wear|Western Wear|women_dresses|Women Dresses|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/2.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/3.webp|https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/4.webp",
            "women|Women|women_western_wear|Western Wear|women_tops|Women Tops|https://cdn.dummyjson.com/product-images/tops/blue-frock/1.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/2.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/3.webp|https://cdn.dummyjson.com/product-images/tops/blue-frock/4.webp",
            "women|Women|women_western_wear|Western Wear|women_tshirts|Women Tshirts|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/girl-summer-dress/2.webp|https://images.pexels.com/photos/30957211/pexels-photo-30957211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24205642/pexels-photo-24205642.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_jeans|Women Jeans|https://images.pexels.com/photos/17302419/pexels-photo-17302419.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/936117/pexels-photo-936117.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20729717/pexels-photo-20729717.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7636101/pexels-photo-7636101.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_trousers_capris|Women Trousers & Capris|https://images.pexels.com/photos/450212/pexels-photo-450212.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6182574/pexels-photo-6182574.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7205905/pexels-photo-7205905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19995460/pexels-photo-19995460.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_shorts_skirts|Women Shorts & Skirts|https://images.pexels.com/photos/6012003/pexels-photo-6012003.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/383603/pexels-photo-383603.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7391126/pexels-photo-7391126.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16431244/pexels-photo-16431244.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_coords|Women Co-ords|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/2.webp|https://images.pexels.com/photos/31400265/pexels-photo-31400265.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37036179/pexels-photo-37036179.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_playsuits|Women Playsuits|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/2.webp|https://images.pexels.com/photos/7545423/pexels-photo-7545423.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29017408/pexels-photo-29017408.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_jumpsuits|Women Jumpsuits|https://images.pexels.com/photos/21374896/pexels-photo-21374896.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4006508/pexels-photo-4006508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2699250/pexels-photo-2699250.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13219628/pexels-photo-13219628.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_shrugs|Women Shrugs|https://cdn.dummyjson.com/product-images/tops/gray-dress/1.webp|https://cdn.dummyjson.com/product-images/tops/gray-dress/2.webp|https://images.pexels.com/photos/7585673/pexels-photo-7585673.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5710033/pexels-photo-5710033.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_sweaters_sweatshirts|Women Sweaters & Sweatshirts|https://images.pexels.com/photos/2080383/pexels-photo-2080383.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9811069/pexels-photo-9811069.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/33688875/pexels-photo-33688875.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6347515/pexels-photo-6347515.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_jackets_coats|Women Jackets & Coats|https://images.pexels.com/photos/6974936/pexels-photo-6974936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11587144/pexels-photo-11587144.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12083001/pexels-photo-12083001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34366695/pexels-photo-34366695.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_blazers_waistcoats|Women Blazers & Waistcoats|https://images.pexels.com/photos/10955935/pexels-photo-10955935.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14495270/pexels-photo-14495270.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7691041/pexels-photo-7691041.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8484005/pexels-photo-8484005.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_plus_size|Women Plus Size|https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/2.webp|https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/1.webp|https://images.pexels.com/photos/14693247/pexels-photo-14693247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18586865/pexels-photo-18586865.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_maternity|Women Maternity|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/1.webp|https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/2.webp|https://images.pexels.com/photos/20770621/pexels-photo-20770621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15968597/pexels-photo-15968597.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_western_wear|Western Wear|women_sunglasses_frames|Women Sunglasses & Frames|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/1.webp|https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/2.webp|https://images.pexels.com/photos/15735139/pexels-photo-15735139.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26575042/pexels-photo-26575042.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_footwear|Footwear|women_flats|Women Flats|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/2.webp|https://images.pexels.com/photos/14816287/pexels-photo-14816287.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36581190/pexels-photo-36581190.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_footwear|Footwear|women_casual_shoes|Women Casual Shoes|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/2.webp|https://images.pexels.com/photos/7887198/pexels-photo-7887198.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1027130/pexels-photo-1027130.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_footwear|Footwear|women_heels|Women Heels|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/2.webp|https://images.pexels.com/photos/36212466/pexels-photo-36212466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34294446/pexels-photo-34294446.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_footwear|Footwear|women_boots|Women Boots|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/2.webp|https://images.pexels.com/photos/26856061/pexels-photo-26856061.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27174561/pexels-photo-27174561.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_footwear|Footwear|women_sports_shoes_floaters|Women Sports Shoes & Floaters|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/1.webp|https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/2.webp|https://images.pexels.com/photos/4065509/pexels-photo-4065509.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15659357/pexels-photo-15659357.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_sports_active_wear|Sports & Active Wear|women_clothing|Women Clothing|https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/22109480/2023/9/5/06a17ac3-46b0-4f9d-bcb1-2d3582feda041693895310152PumaWomenBrandLogoPrintedPureCottonOutdoorT-shirt1.jpg|https://images.pexels.com/photos/8436750/pexels-photo-8436750.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27433185/pexels-photo-27433185.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7894597/pexels-photo-7894597.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_accessories|Women Sports Accessories|https://images.pexels.com/photos/9944907/pexels-photo-9944907.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16513597/pexels-photo-16513597.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6246682/pexels-photo-6246682.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6339731/pexels-photo-6339731.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_sports_active_wear|Sports & Active Wear|women_sports_equipment|Women Sports Equipment|https://cdn.dummyjson.com/product-images/sports-accessories/american-football/1.webp|https://cdn.dummyjson.com/product-images/sports-accessories/baseball-ball/1.webp|https://images.pexels.com/photos/7743320/pexels-photo-7743320.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6550849/pexels-photo-6550849.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_beauty_personal_care|Beauty & Personal Care|https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/1.webp|https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/1.webp|https://cdn.dummyjson.com/product-images/beauty/powder-canister/1.webp|https://cdn.dummyjson.com/product-images/beauty/red-lipstick/1.webp",
            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_makeup|Women Makeup|https://images.pexels.com/photos/23228944/pexels-photo-23228944.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1571585/pexels-photo-1571585.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4938498/pexels-photo-4938498.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3148938/pexels-photo-3148938.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_skincare|Women Skincare|https://cdn.dummyjson.com/product-images/beauty/red-nail-polish/1.webp|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/1.webp|https://images.pexels.com/photos/12115040/pexels-photo-12115040.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28994644/pexels-photo-28994644.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_premium_beauty|Women Premium Beauty|https://cdn.dummyjson.com/product-images/skin-care/attitude-super-leaves-hand-soap/2.webp|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/1.webp|https://images.pexels.com/photos/7020247/pexels-photo-7020247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6925496/pexels-photo-6925496.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_lipsticks|Women Lipsticks|https://cdn.dummyjson.com/product-images/skin-care/olay-ultra-moisture-shea-butter-body-wash/2.webp|https://cdn.dummyjson.com/product-images/skin-care/vaseline-men-body-and-face-lotion/1.webp|https://images.pexels.com/photos/34321279/pexels-photo-34321279.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6648498/pexels-photo-6648498.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_beauty_personal_care|Beauty & Personal Care|women_fragrances|Women Fragrances|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/1.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/2.webp|https://cdn.dummyjson.com/product-images/fragrances/dior-j'adore/3.webp|https://cdn.dummyjson.com/product-images/fragrances/dolce-shine-eau-de/1.webp",
            "women|Women|women_gadgets|Gadgets|women_gadgets|Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/2.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/amazon-echo-plus/2.webp",
            "women|Women|women_gadgets|Gadgets|women_smart_wearables|Women Smart Wearables|https://rukminim2.flixcart.com/image/612/612/xif0q/smartwatch/f/g/g/-original-imagywnz46fngcks.jpeg?q=70|https://rukminim2.flixcart.com/image/416/416/xif0q/smartwatch/u/r/h/-original-imagyxxfgz52vzqn.jpeg?q=70&crop=false|https://images.pexels.com/photos/31406895/pexels-photo-31406895.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31406903/pexels-photo-31406903.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_gadgets|Gadgets|women_fitness_gadgets|Women Fitness Gadgets|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-watch-series-4-gold/2.webp|https://images.pexels.com/photos/5036927/pexels-photo-5036927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/374673/pexels-photo-374673.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_gadgets|Gadgets|women_headphones|Women Headphones|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods-max-silver/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/beats-flex-wireless-earphones/1.webp|https://images.pexels.com/photos/210927/pexels-photo-210927.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7772548/pexels-photo-7772548.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_gadgets|Gadgets|women_speakers|Women Speakers|https://images.pexels.com/photos/374110/pexels-photo-374110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13465232/pexels-photo-13465232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4917455/pexels-photo-4917455.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29617989/pexels-photo-29617989.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_jewellery|Jewellery|women_fashion_jewellery|Women Fashion Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/2.webp|https://images.pexels.com/photos/10862233/pexels-photo-10862233.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7514818/pexels-photo-7514818.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_jewellery|Jewellery|women_fine_jewellery|Women Fine Jewellery|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/2.webp|https://images.pexels.com/photos/13924051/pexels-photo-13924051.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15925159/pexels-photo-15925159.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_jewellery|Jewellery|women_earrings|Women Earrings|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/1.webp|https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/2.webp|https://images.pexels.com/photos/10082804/pexels-photo-10082804.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15785528/pexels-photo-15785528.jpeg?auto=compress&cs=tinysrgb&w=800",
            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_handbags_bags_wallets|Handbags, Bags & Wallets|https://images.pexels.com/photos/4430246/pexels-photo-4430246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6102145/pexels-photo-6102145.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/1.webp|https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/2.webp",
            "women|Women|women_handbags_bags_wallets|Handbags, Bags & Wallets|women_luggages_trolleys|Women Luggages & Trolleys|https://images.pexels.com/photos/1682694/pexels-photo-1682694.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8212229/pexels-photo-8212229.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36933446/pexels-photo-36933446.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11499403/pexels-photo-11499403.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobiles|Mobiles|mi_mobile|Mi Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/1.webp",
            "electronics|Electronics|mobiles|Mobiles|realme_mobile|Realme Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-c35/3.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/1.webp",
            "electronics|Electronics|mobiles|Mobiles|samsung_mobile|Samsung Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s7/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/1.webp",
            "electronics|Electronics|mobiles|Mobiles|infinix_mobile|Infinix Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-6/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/2.webp",
            "electronics|Electronics|mobiles|Mobiles|oppo_mobile|OPPO Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-a57/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/1.webp",
            "electronics|Electronics|mobiles|Mobiles|apple_mobile|Apple Mobile|https://cdn.dummyjson.com/product-images/smartphones/iphone-13-pro/3.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/1.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/iphone-x/3.webp",
            "electronics|Electronics|mobiles|Mobiles|vivo_mobile|Vivo Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-s1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/1.webp",
            "electronics|Electronics|mobiles|Mobiles|honor_mobile|Honor Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/2.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-f19-pro-plus/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/1.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/2.webp",
            "electronics|Electronics|mobiles|Mobiles|asus_mobile|Asus Mobile|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/3.webp|https://cdn.dummyjson.com/product-images/smartphones/oppo-k1/4.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-x/3.webp",
            "electronics|Electronics|mobiles|Mobiles|poco_x2_mobile|Poco X2 Mobile|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/1.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/2.webp|https://cdn.dummyjson.com/product-images/smartphones/realme-xt/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/2.webp",
            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10_mobile|Realme Narzo 10 Mobile|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s8/3.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/1.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/2.webp|https://cdn.dummyjson.com/product-images/smartphones/samsung-galaxy-s10/3.webp",
            "electronics|Electronics|mobiles|Mobiles|infinix_hot_9_mobile|Infinix Hot 9 Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/2.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-v9/3.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/1.webp|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/2.webp",
            "electronics|Electronics|mobiles|Mobiles|iqoo_3_mobile|IQOO 3 Mobile|https://images.pexels.com/photos/6901037/pexels-photo-6901037.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15092/pexels-photo-15092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5337378/pexels-photo-5337378.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5253002/pexels-photo-5253002.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobiles|Mobiles|iphone_se_mobile|IPhone SE Mobile|https://images.pexels.com/photos/3945672/pexels-photo-3945672.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18311092/pexels-photo-18311092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945691/pexels-photo-3945691.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3945695/pexels-photo-3945695.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobiles|Mobiles|motorola_razr_mobile|Motorola Razr Mobile|https://cdn.dummyjson.com/product-images/smartphones/vivo-x21/3.webp|https://images.pexels.com/photos/47261/pexels-photo-47261.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/24181865/pexels-photo-24181865.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13844013/pexels-photo-13844013.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobiles|Mobiles|realme_narzo_10a_mobile|Realme Narzo 10A Mobile|https://images.pexels.com/photos/3850561/pexels-photo-3850561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36680543/pexels-photo-36680543.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/21854468/pexels-photo-21854468.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14486282/pexels-photo-14486282.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobiles|Mobiles|motorola_g8_power_lite_mobile|Motorola G8 Power Lite Mobile|https://images.pexels.com/photos/30639091/pexels-photo-30639091.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16442035/pexels-photo-16442035.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26775917/pexels-photo-26775917.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19281836/pexels-photo-19281836.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cases|Mobile Cases|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/iphone-12-silicone-case-with-magsafe-plum/2.webp|https://images.pexels.com/photos/12359130/pexels-photo-12359130.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1670768/pexels-photo-1670768.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|headphones_headsets|Headphones & Headsets|https://rukminim2.flixcart.com/image/612/612/kz4gh3k0/headphone/c/v/r/-original-imagb7bmhdgghzxq.jpeg?q=70|https://images.pexels.com/photos/3394660/pexels-photo-3394660.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5650531/pexels-photo-5650531.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3081173/pexels-photo-3081173.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|power_banks|Power Banks|https://images.pexels.com/photos/4072683/pexels-photo-4072683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37475677/pexels-photo-37475677.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6296911/pexels-photo-6296911.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3921704/pexels-photo-3921704.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|screenguards|Screenguards|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpower-wireless-charger/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/1.webp|https://images.pexels.com/photos/12368095/pexels-photo-12368095.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17610713/pexels-photo-17610713.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|memory_cards|Memory Cards|https://images.pexels.com/photos/5882581/pexels-photo-5882581.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7057/pexels-photo-7057.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2147082/pexels-photo-2147082.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32346379/pexels-photo-32346379.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|smart_headphones|Smart Headphones|https://images.pexels.com/photos/4887213/pexels-photo-4887213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28920288/pexels-photo-28920288.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-airpods/3.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-homepod-mini-cosmic-grey/1.webp",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_cables|Mobile Cables|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-iphone-charger/2.webp|https://images.pexels.com/photos/4219860/pexels-photo-4219860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1028674/pexels-photo-1028674.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/947407/pexels-photo-947407.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_chargers|Mobile Chargers|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/apple-magsafe-battery-pack/2.webp|https://images.pexels.com/photos/36012993/pexels-photo-36012993.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/216369/pexels-photo-216369.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|mobile_accessories|Mobile Accessories|mobile_holders|Mobile Holders|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/1.webp|https://cdn.dummyjson.com/product-images/mobile-accessories/monopod/2.webp|https://images.pexels.com/photos/694424/pexels-photo-694424.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35037256/pexels-photo-35037256.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_watches|Smart Watches|https://images.pexels.com/photos/11031515/pexels-photo-11031515.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18662969/pexels-photo-18662969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8760799/pexels-photo-8760799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18078946/pexels-photo-18078946.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_glasses_vr|Smart Glasses (VR)|https://images.pexels.com/photos/373905/pexels-photo-373905.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6848112/pexels-photo-6848112.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7561900/pexels-photo-7561900.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19545617/pexels-photo-19545617.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|smart_wearable_tech|Smart Wearable Tech|smart_bands|Smart Bands|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/1.webp|https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/2.webp|https://images.pexels.com/photos/374619/pexels-photo-374619.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4426516/pexels-photo-4426516.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|gaming_laptops|Gaming Laptops|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/1.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/2.webp|https://images.pexels.com/photos/3951449/pexels-photo-3951449.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19012030/pexels-photo-19012030.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|desktop_pcs|Desktop PCs|https://images.pexels.com/photos/6415/pexels-photo-6415.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1714341/pexels-photo-1714341.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31145466/pexels-photo-31145466.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9072379/pexels-photo-9072379.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|gaming_accessories|Gaming & Accessories|https://images.pexels.com/photos/194511/pexels-photo-194511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1337247/pexels-photo-1337247.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7887636/pexels-photo-7887636.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34592708/pexels-photo-34592708.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|computer_accessories|Computer Accessories|https://images.pexels.com/photos/392018/pexels-photo-392018.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12786602/pexels-photo-12786602.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4065748/pexels-photo-4065748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1420709/pexels-photo-1420709.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|external_hard_disks|External Hard Disks|https://images.pexels.com/photos/20213726/pexels-photo-20213726.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6937936/pexels-photo-6937936.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32892856/pexels-photo-32892856.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28461160/pexels-photo-28461160.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|pendrives|Pendrives|https://images.pexels.com/photos/19954918/pexels-photo-19954918.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10133432/pexels-photo-10133432.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4792751/pexels-photo-4792751.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10336136/pexels-photo-10336136.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|laptop_skins_decals|Laptop Skins & Decals|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/1.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/2.webp|https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/3.webp|https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/3.webp",
            "electronics|Electronics|laptops|Laptops|laptop_bags|Laptop Bags|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/1.webp|https://cdn.dummyjson.com/product-images/laptops/huawei-matebook-x-pro/2.webp|https://images.pexels.com/photos/20318749/pexels-photo-20318749.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5330724/pexels-photo-5330724.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|mouse|Mouse|https://images.pexels.com/photos/7151696/pexels-photo-7151696.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38006271/pexels-photo-38006271.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6932748/pexels-photo-6932748.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12029640/pexels-photo-12029640.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|computer_peripherals|Computer Peripherals|https://images.pexels.com/photos/8297860/pexels-photo-8297860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12509206/pexels-photo-12509206.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4120698/pexels-photo-4120698.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20182039/pexels-photo-20182039.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|printers_ink_cartridges|Printers & Ink Cartridges|https://images.pexels.com/photos/4792283/pexels-photo-4792283.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/777001/pexels-photo-777001.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574569/pexels-photo-9574569.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9574453/pexels-photo-9574453.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|laptops|Laptops|monitors|Monitors|https://images.pexels.com/photos/3521937/pexels-photo-3521937.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/326512/pexels-photo-326512.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37148217/pexels-photo-37148217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6804606/pexels-photo-6804606.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|tablets|Tablets|apple_ipads|Apple IPads|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/1.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/2.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/3.webp|https://cdn.dummyjson.com/product-images/tablets/ipad-mini-2021-starlight/4.webp",
            "electronics|Electronics|speakers|Speakers|home_audio_speakers|Home Audio Speakers|https://rukminim2.flixcart.com/image/612/612/xif0q/speaker/6/z/2/-original-imahfgfkr5gkk9aq.jpeg?q=70|https://images.pexels.com/photos/191877/pexels-photo-191877.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/128611/pexels-photo-128611.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2651794/pexels-photo-2651794.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|speakers|Speakers|home_theatres|Home Theatres|https://images.pexels.com/photos/31683433/pexels-photo-31683433.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/880874/pexels-photo-880874.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7031762/pexels-photo-7031762.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5008383/pexels-photo-5008383.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|speakers|Speakers|soundbars|Soundbars|https://images.pexels.com/photos/374067/pexels-photo-374067.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18542243/pexels-photo-18542243.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/373638/pexels-photo-373638.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10486182/pexels-photo-10486182.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|speakers|Speakers|bluetooth_speakers|Bluetooth Speakers|https://images.pexels.com/photos/29606737/pexels-photo-29606737.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9767551/pexels-photo-9767551.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25450654/pexels-photo-25450654.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/14017595/pexels-photo-14017595.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|speakers|Speakers|dth_set_top_box|DTH Set Top Box|https://images.pexels.com/photos/5207508/pexels-photo-5207508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/40195705/pexels-photo-40195705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5207514/pexels-photo-5207514.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4777979/pexels-photo-4777979.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|camera|Camera|dslr_mirrorless_camera|DSLR & Mirrorless Camera|https://rukminim2.flixcart.com/image/312/312/jfbfde80/camera/n/r/n/canon-eos-eos-3000d-dslr-original-imaf3t5h9yuyc5zu.jpeg?q=70|https://images.pexels.com/photos/17345664/pexels-photo-17345664.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/26873540/pexels-photo-26873540.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6891485/pexels-photo-6891485.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|camera|Camera|compact_bridge_cameras|Compact & Bridge Cameras|https://images.pexels.com/photos/1595232/pexels-photo-1595232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1144699/pexels-photo-1144699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16104735/pexels-photo-16104735.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4372403/pexels-photo-4372403.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|camera|Camera|sports_action_camera|Sports & Action Camera|https://images.pexels.com/photos/274973/pexels-photo-274973.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4996005/pexels-photo-4996005.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4817132/pexels-photo-4817132.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1276531/pexels-photo-1276531.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|camera|Camera|camera_accessories|Camera Accessories|https://images.pexels.com/photos/7776871/pexels-photo-7776871.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3405454/pexels-photo-3405454.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35001053/pexels-photo-35001053.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34642455/pexels-photo-34642455.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|camera|Camera|lens|Camera Lens|https://images.pexels.com/photos/838680/pexels-photo-838680.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/734535/pexels-photo-734535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11713436/pexels-photo-11713436.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29293968/pexels-photo-29293968.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|camera|Camera|camera_tripods|Camera Tripods|https://images.pexels.com/photos/20382217/pexels-photo-20382217.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15062358/pexels-photo-15062358.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11331527/pexels-photo-11331527.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20954706/pexels-photo-20954706.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_runners|Bed Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/19284508/2022/7/28/92df52de-27dc-4d72-8ab4-fee2c82c85081659003977664DreamscapeUnisexPinkBedsheets1.jpg|https://images.pexels.com/photos/5629134/pexels-photo-5629134.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6903157/pexels-photo-6903157.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/27671434/pexels-photo-27671434.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|mattress_protectors|Mattress Protectors|https://images.pexels.com/photos/3030684/pexels-photo-3030684.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4740586/pexels-photo-4740586.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28513849/pexels-photo-28513849.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5629138/pexels-photo-5629138.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedsheets|Bedsheets|https://images.pexels.com/photos/3682240/pexels-photo-3682240.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7019028/pexels-photo-7019028.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12200060/pexels-photo-12200060.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38651677/pexels-photo-38651677.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bedding_sets|Bedding Sets|https://images.pexels.com/photos/212269/pexels-photo-212269.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10373508/pexels-photo-10373508.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22711513/pexels-photo-22711513.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/32418083/pexels-photo-32418083.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|blankets_quilts_dohars|Blankets, Quilts & Dohars|https://images.pexels.com/photos/8266834/pexels-photo-8266834.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6633533/pexels-photo-6633533.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30399211/pexels-photo-30399211.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/30350497/pexels-photo-30350497.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|pillows_pillow_covers|Pillows & Pillow Covers|https://images.pexels.com/photos/12326545/pexels-photo-12326545.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9582579/pexels-photo-9582579.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9290601/pexels-photo-9290601.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1239221/pexels-photo-1239221.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|bed_covers|Bed Covers|https://images.pexels.com/photos/10980163/pexels-photo-10980163.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9262007/pexels-photo-9262007.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6782479/pexels-photo-6782479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980080/pexels-photo-19980080.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|diwan_sets|Diwan Sets|https://images.pexels.com/photos/3557507/pexels-photo-3557507.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/910458/pexels-photo-910458.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34574606/pexels-photo-34574606.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7147299/pexels-photo-7147299.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|chair_pads_covers|Chair Pads & Covers|https://images.pexels.com/photos/462197/pexels-photo-462197.jpeg?auto=compress&cs=tinysrgb&w=800|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/1.webp|https://images.pexels.com/photos/18829799/pexels-photo-18829799.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29917912/pexels-photo-29917912.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bed_linen_furnishing|Bed Linen & Furnishing|sofa_covers|Sofa Covers|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-bed/2.webp|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/1.webp|https://images.pexels.com/photos/6758245/pexels-photo-6758245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7394787/pexels-photo-7394787.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|flooring|Flooring|floor_runners|Floor Runners|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/27868878/2024/2/25/53b7c07f-24a7-48e2-a791-f63dce4a0c981708844793060SANACARPETMulticolouredFloralAnti-SkidPolyesterCarpet1.jpg|https://images.pexels.com/photos/5417293/pexels-photo-5417293.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15896109/pexels-photo-15896109.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29614636/pexels-photo-29614636.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|flooring|Flooring|carpets|Carpets|https://images.pexels.com/photos/6933860/pexels-photo-6933860.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/45980/pexels-photo-45980.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/16820353/pexels-photo-16820353.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37219747/pexels-photo-37219747.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|flooring|Flooring|floor_mats_dhurries|Floor Mats & Dhurries|https://cdn.dummyjson.com/product-images/furniture/annibale-colombo-sofa/2.webp|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/1.webp|https://images.pexels.com/photos/7417505/pexels-photo-7417505.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35769473/pexels-photo-35769473.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|flooring|Flooring|door_mats|Door Mats|https://cdn.dummyjson.com/product-images/furniture/bedside-table-african-cherry/2.webp|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/1.webp|https://images.pexels.com/photos/5840868/pexels-photo-5840868.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2950003/pexels-photo-2950003.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|bath_towels|Bath Towels|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/16719378/2024/7/2/97418bad-8216-494f-a863-9b159aec93cf1719897710069JockeyPackof2CottonTerry500GSMUltrasoftandDurableSolidHandTo1.jpg|https://images.pexels.com/photos/12679/pexels-photo-12679.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20523089/pexels-photo-20523089.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8535619/pexels-photo-8535619.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|hand_face_towels|Hand & Face Towels|https://images.pexels.com/photos/7691101/pexels-photo-7691101.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210373/pexels-photo-4210373.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5833316/pexels-photo-5833316.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5732631/pexels-photo-5732631.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|beach_towels|Beach Towels|https://images.pexels.com/photos/271711/pexels-photo-271711.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1304110/pexels-photo-1304110.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18674213/pexels-photo-18674213.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25649794/pexels-photo-25649794.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|towels_set|Towels Set|https://images.pexels.com/photos/282892/pexels-photo-282892.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4210371/pexels-photo-4210371.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6188050/pexels-photo-6188050.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/20665616/pexels-photo-20665616.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|bath_rugs|Bath Rugs|https://cdn.dummyjson.com/product-images/furniture/knoll-saarinen-executive-conference-chair/2.webp|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/1.webp|https://images.pexels.com/photos/34667257/pexels-photo-34667257.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18894029/pexels-photo-18894029.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|bath_robes|Bath Robes|https://images.pexels.com/photos/10924511/pexels-photo-10924511.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/433624/pexels-photo-433624.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6202201/pexels-photo-6202201.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6634836/pexels-photo-6634836.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|bathroom_accessories|Bathroom Accessories|https://images.pexels.com/photos/10574059/pexels-photo-10574059.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3761560/pexels-photo-3761560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/4194705/pexels-photo-4194705.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19980252/pexels-photo-19980252.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|bath|Bath|shower_curtains|Shower Curtains|https://images.pexels.com/photos/11430604/pexels-photo-11430604.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5137571/pexels-photo-5137571.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5825561/pexels-photo-5825561.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6430731/pexels-photo-6430731.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|floor_lamps|Floor Lamps|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/productimage/2021/6/10/5be80654-037c-4591-99c9-a403646971981623344562713-1.jpg|https://images.pexels.com/photos/112811/pexels-photo-112811.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6045108/pexels-photo-6045108.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/11850174/pexels-photo-11850174.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|ceiling_lamps|Ceiling Lamps|https://images.pexels.com/photos/7533932/pexels-photo-7533932.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17940121/pexels-photo-17940121.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9628657/pexels-photo-9628657.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12202390/pexels-photo-12202390.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|table_lamps|Table Lamps|https://images.pexels.com/photos/16683246/pexels-photo-16683246.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/573298/pexels-photo-573298.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35392792/pexels-photo-35392792.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6053887/pexels-photo-6053887.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|wall_lamps|Wall Lamps|https://images.pexels.com/photos/721039/pexels-photo-721039.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2187141/pexels-photo-2187141.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9510486/pexels-photo-9510486.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29343148/pexels-photo-29343148.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|outdoor_lamps|Outdoor Lamps|https://images.pexels.com/photos/1683405/pexels-photo-1683405.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1554740/pexels-photo-1554740.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18074245/pexels-photo-18074245.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37460216/pexels-photo-37460216.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|lamps_lighting|Lamps & Lighting|string_lights|String Lights|https://images.pexels.com/photos/3368822/pexels-photo-3368822.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1791747/pexels-photo-1791747.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/5836535/pexels-photo-5836535.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/15433418/pexels-photo-15433418.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|plants_planters|Plants & Planters|https://images.pexels.com/photos/8549167/pexels-photo-8549167.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3952031/pexels-photo-3952031.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7245621/pexels-photo-7245621.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31479159/pexels-photo-31479159.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|aromas_candles|Aromas & Candles|https://images.pexels.com/photos/5816995/pexels-photo-5816995.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18408479/pexels-photo-18408479.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31588837/pexels-photo-31588837.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35047380/pexels-photo-35047380.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|clocks|Clocks|https://images.pexels.com/photos/6616845/pexels-photo-6616845.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/2182727/pexels-photo-2182727.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/38699699/pexels-photo-38699699.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/707676/pexels-photo-707676.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|mirrors|Mirrors|https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/28460938/2024/3/22/7fb09e9c-86e0-4602-b54e-fa5c0171b50b1711104156746IrregularMirrorHomeDecor1.jpg|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/1.webp|https://images.pexels.com/photos/7319683/pexels-photo-7319683.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/28533705/pexels-photo-28533705.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|wall_decor|Wall Décor|https://cdn.dummyjson.com/product-images/home-decoration/decoration-swing/2.webp|https://cdn.dummyjson.com/product-images/home-decoration/family-tree-photo-frame/1.webp|https://images.pexels.com/photos/6925113/pexels-photo-6925113.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6580004/pexels-photo-6580004.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|festive_decor|Festive Decor|https://images.pexels.com/photos/4078516/pexels-photo-4078516.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34387790/pexels-photo-34387790.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6443947/pexels-photo-6443947.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/34473414/pexels-photo-34473414.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|pooja_essentials|Pooja Essentials|https://images.pexels.com/photos/6213685/pexels-photo-6213685.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/31372002/pexels-photo-31372002.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/10182772/pexels-photo-10182772.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/37650548/pexels-photo-37650548.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|wall_shelves|Wall Shelves|https://cdn.dummyjson.com/product-images/furniture/wooden-bathroom-sink-with-mirror/2.webp|https://images.pexels.com/photos/6851831/pexels-photo-6851831.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19857232/pexels-photo-19857232.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/25906569/pexels-photo-25906569.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|fountains|Fountains|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/house-showpiece-plant/2.webp|https://images.pexels.com/photos/11651964/pexels-photo-11651964.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17087908/pexels-photo-17087908.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|showpieces_vases|Showpieces & Vases|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/1.webp|https://cdn.dummyjson.com/product-images/home-decoration/plant-pot/2.webp|https://images.pexels.com/photos/40167789/pexels-photo-40167789.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35211364/pexels-photo-35211364.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|ottoman|Ottoman|https://images.pexels.com/photos/32123565/pexels-photo-32123565.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12289357/pexels-photo-12289357.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/17771969/pexels-photo-17771969.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19580060/pexels-photo-19580060.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|cushions_cushion_covers|Cushions & Cushion Covers|https://images.pexels.com/photos/30163102/pexels-photo-30163102.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/36482950/pexels-photo-36482950.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7218188/pexels-photo-7218188.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/18258470/pexels-photo-18258470.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|curtains|Curtains|https://images.pexels.com/photos/5835536/pexels-photo-5835536.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/35165092/pexels-photo-35165092.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/23110026/pexels-photo-23110026.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12944779/pexels-photo-12944779.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|home_decor|Home Décor|home_gift_sets|Home Gift Sets|https://cdn.dummyjson.com/product-images/home-decoration/table-lamp/1.webp|https://images.pexels.com/photos/7674564/pexels-photo-7674564.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/1666070/pexels-photo-1666070.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/3873488/pexels-photo-3873488.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_runners|Table Runners|https://cdn.dummyjson.com/product-images/kitchen-accessories/bamboo-spatula/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/1.webp|https://images.pexels.com/photos/34232560/pexels-photo-34232560.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8108068/pexels-photo-8108068.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|dinnerware_serveware|Dinnerware & Serveware|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-aluminium-cup/2.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/black-whisk/1.webp|https://images.pexels.com/photos/3847465/pexels-photo-3847465.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8903360/pexels-photo-8903360.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|cups_mugs|Cups And Mugs|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/boxed-blender/2.webp|https://images.pexels.com/photos/9261414/pexels-photo-9261414.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/7192236/pexels-photo-7192236.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bakeware_cookware|Bakeware & Cookware|https://cdn.dummyjson.com/product-images/kitchen-accessories/carbon-steel-wok/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/chopping-board/1.webp|https://images.pexels.com/photos/12586814/pexels-photo-12586814.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/29462835/pexels-photo-29462835.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|kitchen_storage_tools|Kitchen Storage & Tools|https://cdn.dummyjson.com/product-images/kitchen-accessories/citrus-squeezer-yellow/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/egg-slicer/1.webp|https://images.pexels.com/photos/10568352/pexels-photo-10568352.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/8175333/pexels-photo-8175333.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|bar_drinkware|Bar & Drinkware|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/electric-stove/2.webp|https://images.pexels.com/photos/7282898/pexels-photo-7282898.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19393192/pexels-photo-19393192.jpeg?auto=compress&cs=tinysrgb&w=800",
            "furniture|Home & Furniture|kitchen_table|Kitchen & Table|table_covers_furnishings|Table Covers & Furnishings|https://cdn.dummyjson.com/product-images/kitchen-accessories/fine-mesh-strainer/1.webp|https://cdn.dummyjson.com/product-images/kitchen-accessories/fork/1.webp|https://images.pexels.com/photos/8158010/pexels-photo-8158010.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/9512746/pexels-photo-9512746.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|television|Television|television|Television|https://rukminim2.flixcart.com/image/312/312/xif0q/television/9/p/9/-original-imah2v29z86u7b79.jpeg?q=70|https://images.pexels.com/photos/5202925/pexels-photo-5202925.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/13806260/pexels-photo-13806260.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/6020432/pexels-photo-6020432.jpeg?auto=compress&cs=tinysrgb&w=800",
            "electronics|Electronics|cameras|Cameras|cameras|Cameras|https://images.pexels.com/photos/15111004/pexels-photo-15111004.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/12548528/pexels-photo-12548528.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/19358981/pexels-photo-19358981.jpeg?auto=compress&cs=tinysrgb&w=800|https://images.pexels.com/photos/22064439/pexels-photo-22064439.jpeg?auto=compress&cs=tinysrgb&w=800"
    };

    // categoryId | title | description | mrp | sellingPrice | colour | sizes | stock | ratings | daysOld
    private static final String[] PRODUCTS = {
            // ---- men_t_shirts
            "men_t_shirts|Roadster Cotton Crew Neck T-Shirt|100% combed cotton, regular fit, ribbed round neck. Machine wash cold. Easy 7-day returns.|2199|1399|Navy Blue|S,M,L,XL,XXL|79|763|42",
            "men_t_shirts|H&M Oversized Graphic Print T-Shirt|Heavy 240 GSM cotton, drop shoulders, bold back print. Machine wash cold. Easy 7-day returns.|3599|1299|Beige|S,M,L,XL,XXL|112|3854|113",
            "men_t_shirts|Levi's Slim Fit Polo T-Shirt|Pique cotton polo with two-button placket and ribbed collar. Machine wash cold. Easy 7-day returns.|1799|969|Charcoal|S,M,L,XL,XXL|131|2943|99",
            "men_t_shirts|U.S. Polo Assn. Henley Neck Full Sleeve T-Shirt|Soft slub cotton, three-button henley placket. Machine wash cold. Easy 7-day returns.|2299|1199|Teal|S,M,L,XL,XXL|21|4652|67",
            "men_t_shirts|Puma Striped Round Neck T-Shirt|Breton stripes in breathable cotton jersey. Machine wash cold. Easy 7-day returns.|2999|1499|Black|S,M,L,XL,XXL|244|3019|11",
            "men_t_shirts|HRX Solid V-Neck T-Shirt|Stretch cotton-lycra blend for a clean, close fit. Machine wash cold. Easy 7-day returns.|2299|1399|Olive|S,M,L,XL,XXL|40|3491|62",
            "men_t_shirts|Bewakoof Acid Wash Relaxed T-Shirt|Vintage acid-wash finish, relaxed boxy cut. Machine wash cold. Easy 7-day returns.|1799|859|Khaki|S,M,L,XL,XXL|76|184|41",
            "men_t_shirts|Jack & Jones Pocket Detail Cotton T-Shirt|Chest patch pocket, pre-shrunk cotton. Machine wash cold. Easy 7-day returns.|2299|1099|Navy Blue|S,M,L,XL,XXL|40|3677|34",
            // ---- men_casual_shirts
            "men_casual_shirts|Roadster Checked Flannel Casual Shirt|Brushed cotton flannel, button-down collar. Machine wash cold. Easy 7-day returns.|2999|1599|Charcoal|S,M,L,XL,XXL|84|2015|77",
            "men_casual_shirts|Levi's Linen Blend Mandarin Collar Shirt|Breathable linen-cotton, mandarin collar, curved hem. Machine wash cold. Easy 7-day returns.|2299|869|Charcoal|S,M,L,XL,XXL|15|2710|5",
            "men_casual_shirts|U.S. Polo Assn. Denim Slim Fit Shirt|Light-wash denim with twin flap pockets. Machine wash cold. Easy 7-day returns.|2699|1899|Beige|S,M,L,XL,XXL|46|3277|33",
            "men_casual_shirts|Highlander Printed Resort Collar Shirt|Tropical print on soft viscose, short sleeves. Machine wash cold. Easy 7-day returns.|2399|1799|Maroon|S,M,L,XL,XXL|170|4038|20",
            "men_casual_shirts|Peter England Oxford Button-Down Shirt|Classic cotton oxford weave, regular fit. Machine wash cold. Easy 7-day returns.|1299|889|Charcoal|S,M,L,XL,XXL|58|1518|103",
            "men_casual_shirts|Jack & Jones Corduroy Overshirt|Fine-wale corduroy, chest pockets, layer-ready. Machine wash cold. Easy 7-day returns.|1599|1199|Olive|S,M,L,XL,XXL|163|4152|76",
            "men_casual_shirts|Mufti Striped Cuban Collar Shirt|Vertical stripes, camp collar, relaxed fit. Machine wash cold. Easy 7-day returns.|3099|1699|Navy Blue|S,M,L,XL,XXL|157|2671|10",
            // ---- men_formal_shirts
            "men_formal_shirts|Van Heusen Slim Fit Solid Formal Shirt|Wrinkle-free cotton, spread collar, single cuff. Machine wash cold. Easy 7-day returns.|3499|1499|Mustard|S,M,L,XL,XXL|226|2952|63",
            "men_formal_shirts|Louis Philippe Regular Fit Pinstripe Shirt|Fine pinstripes on giza-style cotton poplin. Machine wash cold. Easy 7-day returns.|1599|959|White|S,M,L,XL,XXL|169|4266|84",
            "men_formal_shirts|Peter England Self-Design Dobby Formal Shirt|Textured dobby weave, cutaway collar. Machine wash cold. Easy 7-day returns.|4599|2299|Teal|S,M,L,XL,XXL|197|4282|119",
            "men_formal_shirts|Allen Solly Pure Cotton Checked Office Shirt|Micro checks, easy iron finish. Machine wash cold. Easy 7-day returns.|3299|2299|Beige|S,M,L,XL,XXL|195|2312|38",
            "men_formal_shirts|Arrow Satin Finish Party Shirt|Subtle sheen satin cotton for evening events. Machine wash cold. Easy 7-day returns.|4999|2899|Charcoal|S,M,L,XL,XXL|72|2023|89",
            "men_formal_shirts|Raymond Double Cuff French Shirt|Twill weave with double cuffs for cufflinks. Machine wash cold. Easy 7-day returns.|5399|2699|Maroon|S,M,L,XL,XXL|233|4713|51",
            // ---- men_sweatshirts
            "men_sweatshirts|Puma Fleece Crew Neck Sweatshirt|Brushed fleece inside, ribbed cuffs and hem. Machine wash cold. Easy 7-day returns.|2099|1299|Beige|S,M,L,XL,XXL|192|1879|50",
            "men_sweatshirts|HRX Hooded Zip-Up Sweatshirt|Full zip with kangaroo pockets and drawstring hood. Machine wash cold. Easy 7-day returns.|1399|1099|Navy Blue|S,M,L,XL,XXL|129|2572|67",
            "men_sweatshirts|Roadster Oversized Graphic Hoodie|Dropped shoulders, heavyweight 320 GSM fleece. Machine wash cold. Easy 7-day returns.|4599|2399|Black|S,M,L,XL,XXL|94|121|41",
            "men_sweatshirts|Nike Colourblock Pullover Sweatshirt|Bold colourblock panels, regular fit. Machine wash cold. Easy 7-day returns.|3899|1499|Khaki|S,M,L,XL,XXL|124|3152|87",
            "men_sweatshirts|Adidas Varsity Print Sweatshirt|Collegiate print, cotton-rich fleece. Machine wash cold. Easy 7-day returns.|5599|1999|Beige|S,M,L,XL,XXL|119|3981|67",
            "men_sweatshirts|The Souled Store Half-Zip Mock Neck Sweatshirt|Quarter zip, stand collar, soft terry. Machine wash cold. Easy 7-day returns.|3299|1199|Navy Blue|S,M,L,XL,XXL|91|1632|90",
            // ---- men_sweaters
            "men_sweaters|Allen Solly Cable Knit Pullover Sweater|Chunky cable knit acrylic-wool blend. Machine wash cold. Easy 7-day returns.|4599|2799|Teal|S,M,L,XL,XXL|157|2685|111",
            "men_sweaters|Monte Carlo V-Neck Merino Wool Sweater|Fine merino wool, lightweight and warm. Machine wash cold. Easy 7-day returns.|5299|1999|Maroon|S,M,L,XL,XXL|170|4154|5",
            "men_sweaters|United Colors of Benetton Sleeveless Argyle Sweater Vest|Classic argyle pattern, layer over shirts. Machine wash cold. Easy 7-day returns.|3999|2399|White|S,M,L,XL,XXL|15|4169|115",
            "men_sweaters|Roadster Turtleneck Ribbed Sweater|Rib-knit turtleneck, slim fit. Machine wash cold. Easy 7-day returns.|4399|2699|White|S,M,L,XL,XXL|212|2939|65",
            "men_sweaters|Louis Philippe Zip Cardigan Sweater|Front zip cardigan with pockets. Machine wash cold. Easy 7-day returns.|6499|2499|Khaki|S,M,L,XL,XXL|138|3171|119",
            "men_sweaters|Van Heusen Fair Isle Crew Neck Sweater|Festive fair isle pattern, soft knit. Machine wash cold. Easy 7-day returns.|4899|2699|Black|S,M,L,XL,XXL|146|2344|89",
            // ---- men_jackets
            "men_jackets|Roadster Puffer Quilted Jacket|Lightweight quilted puffer with water-repellent shell. Machine wash cold. Easy 7-day returns.|5899|3699|Khaki|S,M,L,XL,XXL|166|3969|100",
            "men_jackets|HRX Denim Trucker Jacket|Classic trucker silhouette in rigid denim. Machine wash cold. Easy 7-day returns.|6799|3799|Charcoal|S,M,L,XL,XXL|28|3319|48",
            "men_jackets|Wildcraft Faux Leather Biker Jacket|Asymmetric zip, quilted shoulder panels. Machine wash cold. Easy 7-day returns.|7799|4799|Green|S,M,L,XL,XXL|19|3971|65",
            "men_jackets|Levi's Bomber Jacket|Satin-finish bomber with ribbed trims. Machine wash cold. Easy 7-day returns.|5699|3399|Khaki|S,M,L,XL,XXL|243|4240|64",
            "men_jackets|Jack & Jones Windcheater Hooded Jacket|Packable windcheater with mesh lining. Machine wash cold. Easy 7-day returns.|3899|2699|Navy Blue|S,M,L,XL,XXL|68|1382|49",
            "men_jackets|Puma Suede Finish Trucker Jacket|Soft suede-feel fabric, button front. Machine wash cold. Easy 7-day returns.|4199|2499|Charcoal|S,M,L,XL,XXL|219|2085|51",
            // ---- men_blazers_and_coats
            "men_blazers_and_coats|Raymond Slim Fit Single-Breasted Blazer|Two-button blazer in poly-viscose suiting. Machine wash cold. Easy 7-day returns.|11599|7399|Mustard|S,M,L,XL,XXL|141|3587|33",
            "men_blazers_and_coats|Blackberrys Textured Linen Blend Blazer|Unstructured linen blazer for summer events. Machine wash cold. Easy 7-day returns.|6399|4999|White|S,M,L,XL,XXL|193|3516|101",
            "men_blazers_and_coats|Louis Philippe Double-Breasted Wool Overcoat|Wool blend overcoat, notch lapel. Machine wash cold. Easy 7-day returns.|9799|4299|Beige|S,M,L,XL,XXL|56|567|21",
            "men_blazers_and_coats|Van Heusen Checked Casual Blazer|Prince of Wales check, patch pockets. Machine wash cold. Easy 7-day returns.|5699|4299|Green|S,M,L,XL,XXL|171|3984|63",
            "men_blazers_and_coats|Arrow Velvet Party Blazer|Rich velvet with satin peak lapel. Machine wash cold. Easy 7-day returns.|11799|5299|Black|S,M,L,XL,XXL|143|1355|111",
            "men_blazers_and_coats|Park Avenue Trench Coat|Belted trench with storm flap, water resistant. Machine wash cold. Easy 7-day returns.|9899|5499|Navy Blue|S,M,L,XL,XXL|189|1587|1",
            // ---- men_suits
            "men_suits|Raymond Two-Piece Slim Fit Suit|Blazer and trouser set in premium suiting fabric. Machine wash cold. Easy 7-day returns.|24499|16899|Olive|S,M,L,XL,XXL|197|2114|13",
            "men_suits|Blackberrys Three-Piece Wedding Suit|Blazer, waistcoat and trouser, notch lapel. Machine wash cold. Easy 7-day returns.|14999|8099|Mustard|S,M,L,XL,XXL|18|27|81",
            "men_suits|Louis Philippe Pinstripe Business Suit|Navy pinstripe, half-canvas construction. Machine wash cold. Easy 7-day returns.|15899|11699|White|S,M,L,XL,XXL|99|1816|59",
            "men_suits|Van Heusen Tuxedo Suit with Satin Lapel|Classic black tuxedo with satin trims. Machine wash cold. Easy 7-day returns.|41699|19999|Mustard|S,M,L,XL,XXL|203|3186|50",
            "men_suits|Park Avenue Checked Three-Piece Suit|Windowpane check with matching waistcoat. Machine wash cold. Easy 7-day returns.|25899|16799|White|S,M,L,XL,XXL|77|2871|107",
            "men_suits|Manyavar Linen Blend Summer Suit|Breathable linen blend, unlined jacket. Machine wash cold. Easy 7-day returns.|18199|12499|Black|S,M,L,XL,XXL|51|2664|37",
            // ---- men_rain_jackets
            "men_rain_jackets|Wildcraft Waterproof Hooded Rain Jacket|Taped seams, 10K waterproof rating. Machine wash cold. Easy 7-day returns.|2399|1699|White|S,M,L,XL,XXL|137|3748|61",
            "men_rain_jackets|Decathlon Quechua Packable Rain Poncho Jacket|Folds into its own pocket, lightweight. Machine wash cold. Easy 7-day returns.|2299|1399|Beige|S,M,L,XL,XXL|236|513|9",
            "men_rain_jackets|Columbia Breathable Trekking Rain Shell|Pit zips for ventilation, adjustable hood. Machine wash cold. Easy 7-day returns.|4099|2999|Beige|S,M,L,XL,XXL|162|451|35",
            "men_rain_jackets|Puma Reflective Biker Rain Suit|Jacket with pants, reflective piping. Machine wash cold. Easy 7-day returns.|3799|2299|Charcoal|S,M,L,XL,XXL|106|1993|27",
            "men_rain_jackets|Duckback Longline Rain Coat|Knee-length coverage, storm flap. Machine wash cold. Easy 7-day returns.|1899|1399|Green|S,M,L,XL,XXL|134|234|29",
            "men_rain_jackets|Zeel Ultralight Running Rain Jacket|Featherweight nylon with DWR coating. Machine wash cold. Easy 7-day returns.|7199|3599|Olive|S,M,L,XL,XXL|237|3677|94",
            // ---- men_indian_and_festive_wear
            "men_indian_and_festive_wear|Manyavar Silk Blend Kurta Pyjama Set|Festive silk-blend kurta with churidar pyjama. Dry clean recommended. Perfect for festive occasions.|13699|6299|Mustard|S,M,L,XL,XXL|180|4350|15",
            "men_indian_and_festive_wear|FabIndia Embroidered Bandhgala Set|Thread embroidery, mandarin collar, trousers. Dry clean recommended. Perfect for festive occasions.|3399|2299|Navy Blue|S,M,L,XL,XXL|27|442|4",
            "men_indian_and_festive_wear|Sojanya Printed Nehru Jacket Kurta Set|Three-piece set with printed jacket. Dry clean recommended. Perfect for festive occasions.|4899|3899|Navy Blue|S,M,L,XL,XXL|24|2960|41",
            "men_indian_and_festive_wear|Vastramay Chikankari Kurta Set|Hand-feel chikankari on cotton. Dry clean recommended. Perfect for festive occasions.|5099|2899|Teal|S,M,L,XL,XXL|20|2278|51",
            "men_indian_and_festive_wear|Jompers Jacquard Indo-Western Set|Asymmetric kurta with dhoti pants. Dry clean recommended. Perfect for festive occasions.|4699|2099|Rust|S,M,L,XL,XXL|22|4185|2",
            "men_indian_and_festive_wear|Tasva Festive Angrakha Kurta Set|Angrakha wrap style with tie-ups. Dry clean recommended. Perfect for festive occasions.|5199|2999|Green|S,M,L,XL,XXL|17|80|98",
            // ---- men_kurtas_and_kurta_sets
            "men_kurtas_and_kurta_sets|FabIndia Straight Cotton Kurta|Pure cotton, mandarin collar, side slits. Dry clean recommended. Perfect for festive occasions.|7899|3299|Cream|S,M,L,XL,XXL|58|2245|62",
            "men_kurtas_and_kurta_sets|Manyavar Printed Short Kurta|Block print short kurta for casual wear. Dry clean recommended. Perfect for festive occasions.|1599|1099|Green|S,M,L,XL,XXL|70|2933|84",
            "men_kurtas_and_kurta_sets|Vastramay Linen Pathani Kurta Set|Pathani style with salwar, linen blend. Dry clean recommended. Perfect for festive occasions.|4299|2499|Mustard|S,M,L,XL,XXL|197|1692|108",
            "men_kurtas_and_kurta_sets|Jompers Silk Blend Festive Kurta|Self-design jacquard for festive occasions. Dry clean recommended. Perfect for festive occasions.|1999|1199|Teal|S,M,L,XL,XXL|43|1015|112",
            "men_kurtas_and_kurta_sets|Anouk Chanderi Kurta Pyjama Set|Lightweight chanderi with pyjama. Dry clean recommended. Perfect for festive occasions.|1599|1199|Rust|S,M,L,XL,XXL|59|4382|34",
            "men_kurtas_and_kurta_sets|See Designs Khadi Long Kurta|Handwoven khadi, relaxed fit. Dry clean recommended. Perfect for festive occasions.|4199|1499|Gold|S,M,L,XL,XXL|79|1027|77",
            // ---- men_sherwanis
            "men_sherwanis|Manyavar Embroidered Wedding Sherwani Set|Zari embroidery with churidar and stole. Dry clean recommended. Perfect for festive occasions.|41799|23799|Gold|S,M,L,XL,XXL|168|4491|71",
            "men_sherwanis|Tasva Velvet Royal Sherwani|Rich velvet with antique buttons. Dry clean recommended. Perfect for festive occasions.|26099|12499|Maroon|S,M,L,XL,XXL|103|3632|86",
            "men_sherwanis|Sojanya Brocade Achkan Sherwani|Brocade weave, knee-length achkan. Dry clean recommended. Perfect for festive occasions.|23499|18299|Magenta|S,M,L,XL,XXL|27|4707|112",
            "men_sherwanis|Ethnix by Raymond Indo-Western Sherwani with Dhoti|Modern cut with draped dhoti. Dry clean recommended. Perfect for festive occasions.|27599|16799|Green|S,M,L,XL,XXL|221|924|45",
            "men_sherwanis|Vastramay Pastel Thread Work Sherwani|Pastel tone with tonal thread work. Dry clean recommended. Perfect for festive occasions.|27299|19099|Maroon|S,M,L,XL,XXL|47|1306|45",
            "men_sherwanis|Jompers Raw Silk Sherwani with Safa|Comes with matching turban. Dry clean recommended. Perfect for festive occasions.|26399|11599|Magenta|S,M,L,XL,XXL|142|1807|94",
            // ---- men_nehru_jackets
            "men_nehru_jackets|Manyavar Jacquard Nehru Jacket|Woven jacquard, mandarin collar. Dry clean recommended. Perfect for festive occasions.|3499|2699|Navy Blue|S,M,L,XL,XXL|197|962|94",
            "men_nehru_jackets|FabIndia Linen Blend Nehru Jacket|Breathable linen, welt pockets. Dry clean recommended. Perfect for festive occasions.|5699|2699|Maroon|S,M,L,XL,XXL|243|1200|40",
            "men_nehru_jackets|Vastramay Printed Festive Nehru Jacket|Paisley print, satin lining. Dry clean recommended. Perfect for festive occasions.|9199|3399|Mustard|S,M,L,XL,XXL|112|3671|46",
            "men_nehru_jackets|Jompers Velvet Nehru Jacket|Plush velvet for winter weddings. Dry clean recommended. Perfect for festive occasions.|2099|1599|Maroon|S,M,L,XL,XXL|228|2885|52",
            "men_nehru_jackets|Tasva Khadi Bandi Jacket|Handspun khadi, relaxed fit. Dry clean recommended. Perfect for festive occasions.|2099|1399|Gold|S,M,L,XL,XXL|42|2938|100",
            "men_nehru_jackets|Peter England Embroidered Silk Nehru Jacket|Tonal embroidery on silk blend. Dry clean recommended. Perfect for festive occasions.|1999|1399|Magenta|S,M,L,XL,XXL|83|2626|3",
            // ---- men_dhotis
            "men_dhotis|Ramraj Cotton Pure Cotton Veshti with Border|Traditional white veshti, gold jari border. Dry clean recommended. Perfect for festive occasions.|3799|1899|Cream|S,M,L,XL,XXL|27|1270|68",
            "men_dhotis|Manyavar Ready-to-Wear Dhoti Pants|Elastic waist, pre-stitched pleats. Dry clean recommended. Perfect for festive occasions.|1299|939|Cream|S,M,L,XL,XXL|99|2507|46",
            "men_dhotis|FabIndia Silk Blend Festive Dhoti|Silk blend with contrast border. Dry clean recommended. Perfect for festive occasions.|999|649|Maroon|S,M,L,XL,XXL|142|148|111",
            "men_dhotis|Vastramay Patiala Style Dhoti Salwar|Draped patiala dhoti. Dry clean recommended. Perfect for festive occasions.|1299|729|Magenta|S,M,L,XL,XXL|70|2274|46",
            "men_dhotis|Jompers Art Silk Cowl Dhoti|Cowl drape for Indo-western looks. Dry clean recommended. Perfect for festive occasions.|1899|1199|Magenta|S,M,L,XL,XXL|124|2771|79",
            "men_dhotis|Sojanya Double Dhoti with Angavastram|Set with matching angavastram. Dry clean recommended. Perfect for festive occasions.|1099|799|Teal|S,M,L,XL,XXL|145|1067|17",
            // ---- men_jeans
            "men_jeans|Levi's 511 Slim Fit Stretch Jeans|Slim through hip and thigh, stretch denim. Machine wash. Easy 7-day returns.|7399|3399|Maroon|28,30,32,34,36|62|2881|114",
            "men_jeans|Wrangler Straight Fit Mid-Rise Jeans|Classic straight leg, mid rise. Machine wash. Easy 7-day returns.|3699|2799|White|28,30,32,34,36|249|493|36",
            "men_jeans|Pepe Jeans Skinny Fit Distressed Jeans|Ripped knees, whiskered wash. Machine wash. Easy 7-day returns.|5399|2799|Grey Melange|28,30,32,34,36|68|77|69",
            "men_jeans|Roadster Tapered Fit Dark Wash Jeans|Clean dark indigo, tapered leg. Machine wash. Easy 7-day returns.|3499|2199|Black|28,30,32,34,36|182|4023|35",
            "men_jeans|Lee Relaxed Fit Carpenter Jeans|Utility loops, relaxed cut. Machine wash. Easy 7-day returns.|3699|2799|Black|28,30,32,34,36|77|1666|120",
            "men_jeans|Spykar Bootcut Mid-Rise Jeans|Slight flare at hem, vintage wash. Machine wash. Easy 7-day returns.|4799|2999|Olive|28,30,32,34,36|82|4079|42",
            // ---- men_casual_trousers
            "men_casual_trousers|Highlander Slim Fit Cotton Chinos|Stretch cotton twill chinos. Machine wash. Easy 7-day returns.|2299|1699|Olive|28,30,32,34,36|238|2573|22",
            "men_casual_trousers|U.S. Polo Assn. Cargo Trousers with Flap Pockets|Six-pocket utility cargo. Machine wash. Easy 7-day returns.|3999|2999|White|28,30,32,34,36|182|2337|6",
            "men_casual_trousers|Roadster Pleated Relaxed Trousers|Single pleat, relaxed taper. Machine wash. Easy 7-day returns.|3299|1999|Beige|28,30,32,34,36|91|2629|83",
            "men_casual_trousers|Jack & Jones Linen Blend Drawstring Trousers|Breathable summer trousers. Machine wash. Easy 7-day returns.|3799|1899|Black|28,30,32,34,36|124|4066|24",
            "men_casual_trousers|Mufti Corduroy Straight Trousers|Fine-wale corduroy, straight fit. Machine wash. Easy 7-day returns.|2099|1499|Black|28,30,32,34,36|137|4470|92",
            "men_casual_trousers|Peter England Jogger Fit Casual Trousers|Elastic cuffs, smart-casual. Machine wash. Easy 7-day returns.|2699|1899|Green|28,30,32,34,36|146|3957|42",
            // ---- men_formal_trousers
            "men_formal_trousers|Van Heusen Slim Fit Flat-Front Trousers|Wrinkle-resistant poly-viscose. Machine wash. Easy 7-day returns.|1899|1399|Khaki|28,30,32,34,36|68|671|37",
            "men_formal_trousers|Louis Philippe Regular Fit Pleated Trousers|Double pleat, comfort waist. Machine wash. Easy 7-day returns.|1699|1199|Beige|28,30,32,34,36|83|292|23",
            "men_formal_trousers|Raymond Checked Formal Trousers|Subtle checks, tapered. Machine wash. Easy 7-day returns.|6299|2199|Green|28,30,32,34,36|182|2915|3",
            "men_formal_trousers|Allen Solly Wool Blend Office Trousers|Premium wool blend with crease. Machine wash. Easy 7-day returns.|6899|3299|Maroon|28,30,32,34,36|68|98|107",
            "men_formal_trousers|Arrow Stretch Tailored Trousers|Four-way stretch for all-day comfort. Machine wash. Easy 7-day returns.|4699|1999|Green|28,30,32,34,36|218|2724|4",
            "men_formal_trousers|Park Avenue Herringbone Formal Trousers|Herringbone weave, slim fit. Machine wash. Easy 7-day returns.|2499|1799|Black|28,30,32,34,36|149|1338|14",
            // ---- men_shorts
            "men_shorts|Puma Cotton Chino Shorts|Mid-thigh length, slant pockets. Machine wash. Easy 7-day returns.|1499|999|Grey Melange|28,30,32,34,36|40|1901|97",
            "men_shorts|Nike Denim Shorts|Rolled hem, light wash. Machine wash. Easy 7-day returns.|2699|1199|Charcoal|28,30,32,34,36|224|504|8",
            "men_shorts|Roadster Cargo Shorts|Side flap pockets, relaxed. Machine wash. Easy 7-day returns.|2399|1799|Maroon|28,30,32,34,36|109|2459|4",
            "men_shorts|H&M Dri-Fit Running Shorts|Moisture-wicking with inner brief. Machine wash. Easy 7-day returns.|2599|1499|Mustard|28,30,32,34,36|235|1271|7",
            "men_shorts|Jack & Jones Printed Swim-Style Shorts|Quick-dry, drawstring waist. Machine wash. Easy 7-day returns.|1199|899|White|28,30,32,34,36|147|2386|69",
            "men_shorts|Levi's Knit Lounge Shorts|Soft french terry. Machine wash. Easy 7-day returns.|849|669|Green|28,30,32,34,36|163|3438|111",
            // ---- men_track_pants_and_joggers
            "men_track_pants_and_joggers|Puma Slim Fit Joggers|Tapered joggers with zip pockets. Machine wash. Easy 7-day returns.|3399|2199|Maroon|28,30,32,34,36|123|1620|1",
            "men_track_pants_and_joggers|Adidas Three-Stripe Track Pants|Classic side stripes, tricot fabric. Machine wash. Easy 7-day returns.|4099|2499|Grey Melange|28,30,32,34,36|236|185|29",
            "men_track_pants_and_joggers|Nike Fleece Cuffed Joggers|Brushed fleece for winter. Machine wash. Easy 7-day returns.|4399|1799|Maroon|28,30,32,34,36|222|1779|9",
            "men_track_pants_and_joggers|HRX Dri-Fit Training Track Pants|Moisture-wicking, stretch. Machine wash. Easy 7-day returns.|3999|1799|Navy Blue|28,30,32,34,36|131|3414|68",
            "men_track_pants_and_joggers|Reebok Cargo Joggers|Utility pockets, elastic hems. Machine wash. Easy 7-day returns.|4299|1799|Black|28,30,32,34,36|73|2710|70",
            "men_track_pants_and_joggers|Decathlon Kalenji Woven Windbreaker Track Pants|Lightweight woven shell. Machine wash. Easy 7-day returns.|1899|1299|Green|28,30,32,34,36|129|2018|75",
            // ---- men_briefs_and_trunks
            "men_briefs_and_trunks|Jockey Pack of 3 Cotton Briefs|Combed cotton with soft waistband. Soft on skin. Machine wash.|1499|589|Navy Blue|S,M,L,XL,XXL|215|212|28",
            "men_briefs_and_trunks|Calvin Klein Micro Modal Trunks|Ultra-soft modal, no-ride legs. Soft on skin. Machine wash.|1299|509|Grey Melange|S,M,L,XL,XXL|137|2659|34",
            "men_briefs_and_trunks|XYXX Pack of 2 Stretch Trunks|Cotton-elastane, contour pouch. Soft on skin. Machine wash.|819|569|White|S,M,L,XL,XXL|15|941|115",
            "men_briefs_and_trunks|Van Heusen Anti-Bacterial Briefs|Odour control finish. Soft on skin. Machine wash.|899|339|Black|S,M,L,XL,XXL|190|1038|25",
            "men_briefs_and_trunks|DaMENSCH Long Leg Trunks|Longer leg, anti-chafe. Soft on skin. Machine wash.|1399|989|Navy Blue|S,M,L,XL,XXL|84|2017|76",
            "men_briefs_and_trunks|Puma Seamless Trunks|Laser cut, invisible under clothes. Soft on skin. Machine wash.|999|449|Green|S,M,L,XL,XXL|106|2006|43",
            // ---- men_boxers
            "men_boxers|Jockey Printed Woven Boxers|Breathable cotton, button fly. Soft on skin. Machine wash.|599|319|Charcoal|S,M,L,XL,XXL|97|1631|66",
            "men_boxers|XYXX Pack of 2 Checked Boxers|Classic checks, inner waistband. Soft on skin. Machine wash.|1299|989|Olive|S,M,L,XL,XXL|96|1628|25",
            "men_boxers|U.S. Polo Assn. Knit Boxer Shorts|Stretch knit, side pockets. Soft on skin. Machine wash.|1799|669|Maroon|S,M,L,XL,XXL|230|1979|101",
            "men_boxers|Van Heusen Super Combed Cotton Boxers|Soft and durable. Soft on skin. Machine wash.|1299|679|Teal|S,M,L,XL,XXL|207|3036|29",
            "men_boxers|Bewakoof Pack of 3 Solid Boxers|Everyday essentials. Soft on skin. Machine wash.|589|439|White|S,M,L,XL,XXL|80|295|44",
            "men_boxers|DaMENSCH Quick-Dry Boxers|Moisture-wicking fabric. Soft on skin. Machine wash.|589|329|Navy Blue|S,M,L,XL,XXL|154|792|5",
            // ---- men_vests
            "men_vests|Jockey Pack of 2 Cotton Vests|Round neck, sleeveless. Soft on skin. Machine wash.|939|479|Maroon|S,M,L,XL,XXL|214|306|91",
            "men_vests|Rupa Gym Vest|Dri-fit, deep armholes. Soft on skin. Machine wash.|599|349|Grey Melange|S,M,L,XL,XXL|91|3700|88",
            "men_vests|Dollar Ribbed Sleeveless Vest|Rib knit, slim fit. Soft on skin. Machine wash.|459|219|Navy Blue|S,M,L,XL,XXL|114|189|2",
            "men_vests|Van Heusen Micro Modal Vest|Silky soft modal. Soft on skin. Machine wash.|1099|769|Khaki|S,M,L,XL,XXL|227|3835|4",
            "men_vests|XYXX Sports Tank Vest|Breathable mesh panels. Soft on skin. Machine wash.|519|369|Olive|S,M,L,XL,XXL|138|2370|100",
            "men_vests|Lux Cozi Pack of 3 Classic Vests|Everyday comfort. Soft on skin. Machine wash.|589|259|Maroon|S,M,L,XL,XXL|125|3570|37",
            // ---- men_sleepwear_and_loungewear
            "men_sleepwear_and_loungewear|Jockey Cotton Pyjama Set|T-shirt and pyjama, soft cotton. Soft on skin. Machine wash.|2699|1399|Teal|S,M,L,XL,XXL|98|4515|73",
            "men_sleepwear_and_loungewear|XYXX Checked Lounge Pants|Woven checks, drawstring. Soft on skin. Machine wash.|3099|1799|Maroon|S,M,L,XL,XXL|51|56|49",
            "men_sleepwear_and_loungewear|U.S. Polo Assn. Printed Shorts Set|Tee with printed shorts. Soft on skin. Machine wash.|1299|939|Charcoal|S,M,L,XL,XXL|203|496|63",
            "men_sleepwear_and_loungewear|Bewakoof Modal Lounge Set|Silky modal, relaxed. Soft on skin. Machine wash.|5199|1999|Black|S,M,L,XL,XXL|76|1539|78",
            "men_sleepwear_and_loungewear|H&M Flannel Night Suit|Brushed flannel shirt and pants. Soft on skin. Machine wash.|2199|1099|Maroon|S,M,L,XL,XXL|237|3056|22",
            "men_sleepwear_and_loungewear|Van Heusen Waffle Knit Lounge Set|Textured waffle knit. Soft on skin. Machine wash.|2499|1699|Beige|S,M,L,XL,XXL|231|870|43",
            // ---- men_thermals
            "men_thermals|Jockey Thermal Top and Bottom Set|Brushed inner, warm and light. Soft on skin. Machine wash.|929|649|Beige|S,M,L,XL,XXL|240|4574|79",
            "men_thermals|Lux Cottswool Wool Blend Thermal Top|Merino blend, full sleeve. Soft on skin. Machine wash.|659|489|Mustard|S,M,L,XL,XXL|128|259|88",
            "men_thermals|Dollar Heat Retention Thermal Leggings|Stretch thermal bottoms. Soft on skin. Machine wash.|1599|1199|Charcoal|S,M,L,XL,XXL|163|1378|109",
            "men_thermals|Rupa V-Neck Thermal Vest|Layer under shirts. Soft on skin. Machine wash.|1899|1399|Mustard|S,M,L,XL,XXL|126|3383|94",
            "men_thermals|Decathlon Wedze Ski Base Layer Set|Moisture-wicking base layer. Soft on skin. Machine wash.|1999|819|Green|S,M,L,XL,XXL|243|2002|4",
            "men_thermals|HRX Fleece Lined Thermal Top|Extra warmth for winters. Soft on skin. Machine wash.|1199|529|Black|S,M,L,XL,XXL|175|3692|69",
            // ---- men_plus_size
            "men_plus_size|Instafab Plus Plus Size Cotton T-Shirt|Relaxed cut, longer length. Designed for a comfortable plus-size fit.|1799|1199|Olive|XXL,3XL,4XL,5XL|185|779|25",
            "men_plus_size|Levi's Plus Size Casual Shirt|Comfort fit with stretch. Designed for a comfortable plus-size fit.|3399|2199|Olive|XXL,3XL,4XL,5XL|141|2601|51",
            "men_plus_size|Roadster Plus Size Jeans|Comfort waist, straight leg. Designed for a comfortable plus-size fit.|4499|2099|Black|XXL,3XL,4XL,5XL|223|4310|108",
            "men_plus_size|U.S. Polo Assn. Plus Size Chinos|Flex waistband. Designed for a comfortable plus-size fit.|4699|1899|Green|XXL,3XL,4XL,5XL|105|2821|2",
            "men_plus_size|Peter England Plus Size Polo T-Shirt|Pique cotton, roomy fit. Designed for a comfortable plus-size fit.|1699|769|White|XXL,3XL,4XL,5XL|65|4307|3",
            "men_plus_size|Jack & Jones Plus Size Hoodie|Brushed fleece, kangaroo pocket. Designed for a comfortable plus-size fit.|2499|1499|Beige|XXL,3XL,4XL,5XL|76|1570|69",
            // ---- men_casual_shoes
            "men_casual_shoes|Red Tape Leather Loafers|Genuine leather slip-on loafers. Cushioned insole for all-day comfort. 30-day warranty.|2699|1599|White|6,7,8,9,10,11|140|4729|110",
            "men_casual_shoes|Woodland Suede Desert Boots|Crepe sole, lace-up. Cushioned insole for all-day comfort. 30-day warranty.|8699|3099|Coffee Brown|6,7,8,9,10,11|248|2455|66",
            "men_casual_shoes|Bata Canvas Slip-On Shoes|Lightweight canvas, cushioned insole. Cushioned insole for all-day comfort. 30-day warranty.|3799|1899|Brown|6,7,8,9,10,11|208|4393|120",
            "men_casual_shoes|Clarks Boat Shoes|Leather boat shoes with 360 lacing. Cushioned insole for all-day comfort. 30-day warranty.|2399|1799|Brown|6,7,8,9,10,11|63|4048|25",
            "men_casual_shoes|U.S. Polo Assn. Casual Derby Shoes|Smart-casual derbies. Cushioned insole for all-day comfort. 30-day warranty.|1499|1099|Coffee Brown|6,7,8,9,10,11|21|3395|73",
            "men_casual_shoes|Roadster Driving Moccasins|Pebble sole moccasins. Cushioned insole for all-day comfort. 30-day warranty.|6299|3899|White|6,7,8,9,10,11|72|1259|70",
            // ---- men_sports_shoes
            "men_sports_shoes|Nike Running Shoes with Cushioned Sole|Responsive foam midsole, breathable mesh. Cushioned insole for all-day comfort. 30-day warranty.|4799|3599|Beige|6,7,8,9,10,11|230|4594|70",
            "men_sports_shoes|Adidas Training Shoes|Stable base for gym workouts. Cushioned insole for all-day comfort. 30-day warranty.|5499|4399|Beige|6,7,8,9,10,11|240|3378|102",
            "men_sports_shoes|Puma Walking Shoes|Memory foam insole. Cushioned insole for all-day comfort. 30-day warranty.|5299|3299|Beige|6,7,8,9,10,11|152|4008|60",
            "men_sports_shoes|Asics Trail Running Shoes|Grippy lugged outsole. Cushioned insole for all-day comfort. 30-day warranty.|6399|4699|Tan|6,7,8,9,10,11|62|3302|48",
            "men_sports_shoes|Skechers Basketball High-Tops|Ankle support, cushioned. Cushioned insole for all-day comfort. 30-day warranty.|10299|6899|White|6,7,8,9,10,11|150|106|58",
            "men_sports_shoes|Campus Lightweight Knit Runners|Sock-like knit upper. Cushioned insole for all-day comfort. 30-day warranty.|10899|6399|Brown|6,7,8,9,10,11|161|511|94",
            // ---- men_formal_shoes
            "men_formal_shoes|Hush Puppies Leather Oxford Shoes|Classic cap-toe oxfords. Cushioned insole for all-day comfort. 30-day warranty.|6599|2699|Brown|6,7,8,9,10,11|127|1135|4",
            "men_formal_shoes|Clarks Brogue Derby Shoes|Wingtip brogue detailing. Cushioned insole for all-day comfort. 30-day warranty.|5699|3799|Beige|6,7,8,9,10,11|185|1616|18",
            "men_formal_shoes|Bata Monk Strap Shoes|Double monk strap, leather. Cushioned insole for all-day comfort. 30-day warranty.|9899|5999|Beige|6,7,8,9,10,11|71|3081|117",
            "men_formal_shoes|Red Tape Patent Leather Party Shoes|High-shine finish. Cushioned insole for all-day comfort. 30-day warranty.|3799|2799|Brown|6,7,8,9,10,11|131|993|0",
            "men_formal_shoes|Louis Philippe Chelsea Boots|Elastic side panels. Cushioned insole for all-day comfort. 30-day warranty.|4399|2699|Navy Blue|6,7,8,9,10,11|53|2424|56",
            "men_formal_shoes|Van Heusen Slip-On Formal Loafers|Apron toe loafers. Cushioned insole for all-day comfort. 30-day warranty.|4799|2399|Tan|6,7,8,9,10,11|104|2792|99",
            // ---- men_sneakers
            "men_sneakers|Nike White Leather Sneakers|Minimal white leather low-tops. Cushioned insole for all-day comfort. 30-day warranty.|12399|7999|White|6,7,8,9,10,11|128|3200|90",
            "men_sneakers|Adidas Chunky Sneakers|Retro chunky sole. Cushioned insole for all-day comfort. 30-day warranty.|2999|2099|Beige|6,7,8,9,10,11|232|2515|11",
            "men_sneakers|Puma Canvas High-Top Sneakers|Classic canvas high-tops. Cushioned insole for all-day comfort. 30-day warranty.|10399|6599|Beige|6,7,8,9,10,11|40|2878|71",
            "men_sneakers|Converse Court Classic Sneakers|Tennis-inspired design. Cushioned insole for all-day comfort. 30-day warranty.|9599|4399|Navy Blue|6,7,8,9,10,11|102|254|55",
            "men_sneakers|Vans Colourblock Retro Sneakers|Suede overlays. Cushioned insole for all-day comfort. 30-day warranty.|9299|4899|Coffee Brown|6,7,8,9,10,11|206|1689|60",
            "men_sneakers|Bewakoof Air Slip-On Checkerboard Sneakers|Iconic checker print. Cushioned insole for all-day comfort. 30-day warranty.|2499|1699|Brown|6,7,8,9,10,11|115|1534|21",
            // ---- men_sandals_and_floaters
            "men_sandals_and_floaters|Crocs Velcro Floaters|Adjustable straps, cushioned footbed. Cushioned insole for all-day comfort. 30-day warranty.|2599|879|Tan|6,7,8,9,10,11|225|4628|69",
            "men_sandals_and_floaters|Bata Leather Sandals|Genuine leather, buckle closure. Cushioned insole for all-day comfort. 30-day warranty.|3299|2099|Navy Blue|6,7,8,9,10,11|67|3639|107",
            "men_sandals_and_floaters|Woodland Sports Sandals|Rugged outsole for treks. Cushioned insole for all-day comfort. 30-day warranty.|3499|2499|Brown|6,7,8,9,10,11|61|4336|80",
            "men_sandals_and_floaters|Sparx Classic Clogs|Lightweight foam clogs. Cushioned insole for all-day comfort. 30-day warranty.|4599|1899|Beige|6,7,8,9,10,11|53|1947|4",
            "men_sandals_and_floaters|Red Chief Slide Sandals|Soft footbed slides. Cushioned insole for all-day comfort. 30-day warranty.|2699|1699|White|6,7,8,9,10,11|34|3293|87",
            "men_sandals_and_floaters|Puma Kolhapuri Sandals|Handcrafted leather kolhapuris. Cushioned insole for all-day comfort. 30-day warranty.|1299|879|Coffee Brown|6,7,8,9,10,11|200|4024|106",
            // ---- men_flip_flops
            "men_flip_flops|Adidas Rubber Flip Flops|Soft rubber, textured footbed. Cushioned insole for all-day comfort. 30-day warranty.|649|369|Beige|6,7,8,9,10,11|101|2295|106",
            "men_flip_flops|Puma Printed Flip Flops|Graphic printed straps. Cushioned insole for all-day comfort. 30-day warranty.|519|269|Beige|6,7,8,9,10,11|224|3840|92",
            "men_flip_flops|Crocs Comfort Slides|Moulded footbed slides. Cushioned insole for all-day comfort. 30-day warranty.|609|449|White|6,7,8,9,10,11|148|1544|4",
            "men_flip_flops|Bata Thong Sandals|Lightweight EVA. Cushioned insole for all-day comfort. 30-day warranty.|1299|929|Coffee Brown|6,7,8,9,10,11|21|488|60",
            "men_flip_flops|Nike Cushioned Flip Flops|Extra soft cushioning. Cushioned insole for all-day comfort. 30-day warranty.|939|459|Grey|6,7,8,9,10,11|208|3702|65",
            "men_flip_flops|Sparx Anti-Skid Bathroom Slippers|Grippy sole. Cushioned insole for all-day comfort. 30-day warranty.|799|399|Coffee Brown|6,7,8,9,10,11|180|3717|106",
            // ---- men_socks
            "men_socks|Puma Pack of 3 Ankle Socks|Cotton-rich ankle socks. Quality checked. Easy returns.|729|319|Navy Blue|FREE|249|4653|62",
            "men_socks|Adidas Pack of 5 Low Show Socks|Invisible no-show socks. Quality checked. Easy returns.|929|529|White|FREE|197|289|3",
            "men_socks|Jockey Crew Length Sports Socks|Cushioned sole. Quality checked. Easy returns.|509|219|Green|FREE|102|4435|21",
            "men_socks|Nike Formal Calf Socks|Fine mercerised cotton. Quality checked. Easy returns.|869|389|Maroon|FREE|146|4389|38",
            "men_socks|Bonjour Woollen Winter Socks|Warm wool blend. Quality checked. Easy returns.|719|299|Beige|FREE|33|3654|40",
            "men_socks|Supersox Anti-Odour Bamboo Socks|Bamboo fibre, odour control. Quality checked. Easy returns.|409|279|Grey Melange|FREE|238|2518|40",
            // ---- men_sunglasses_and_frames
            "men_sunglasses_and_frames|Ray-Ban Aviator Sunglasses|UV400 polarised lenses. Quality checked. Easy returns.|13899|4999|Mustard|FREE|142|1675|63",
            "men_sunglasses_and_frames|Fastrack Wayfarer Sunglasses|Classic wayfarer frame. Quality checked. Easy returns.|5499|3199|Green|FREE|198|3740|67",
            "men_sunglasses_and_frames|Vincent Chase Round Metal Sunglasses|Retro round lenses. Quality checked. Easy returns.|2799|1399|Khaki|FREE|181|3469|14",
            "men_sunglasses_and_frames|IDEE Sports Wrap Sunglasses|Lightweight wrap-around. Quality checked. Easy returns.|9599|4399|Gold|FREE|17|3256|116",
            "men_sunglasses_and_frames|Carrera Clubmaster Sunglasses|Browline design. Quality checked. Easy returns.|4399|3499|Beige|FREE|219|4121|15",
            "men_sunglasses_and_frames|Lenskart Air Rectangle Eyeglass Frames|Blue-cut lens ready frames. Quality checked. Easy returns.|9099|6399|Grey Melange|FREE|97|3009|110",
            // ---- men_watches
            "men_watches|Titan Analog Leather Strap Watch|Minimal dial, genuine leather strap. Quality checked. Easy returns.|22099|9699|Black|FREE|206|1817|76",
            "men_watches|Fossil Chronograph Stainless Steel Watch|Three sub-dials, 50m water resistance. Quality checked. Easy returns.|15999|9399|Charcoal|FREE|136|1387|91",
            "men_watches|Casio Digital Sports Watch|Stopwatch, alarm and backlight. Quality checked. Easy returns.|7399|5099|Charcoal|FREE|47|3588|65",
            "men_watches|Timex Automatic Skeleton Watch|Exposed movement dial. Quality checked. Easy returns.|11099|8299|Olive|FREE|122|2356|23",
            "men_watches|Fastrack Analog-Digital Watch|Dual display, resin strap. Quality checked. Easy returns.|15499|6199|Teal|FREE|243|566|108",
            "men_watches|Tommy Hilfiger Minimal Mesh Strap Watch|Ultra-slim case with mesh strap. Quality checked. Easy returns.|3399|2099|Beige|FREE|16|1205|117",
            // ---- men_sports_and_active_wear
            "men_sports_and_active_wear|Nike Training Set T-Shirt and Shorts|Dri-fit two-piece set. Machine wash cold. Easy 7-day returns.|5399|2499|Charcoal|S,M,L,XL,XXL|143|1732|70",
            "men_sports_and_active_wear|Adidas Compression Tights|Muscle support, quick dry. Machine wash cold. Easy 7-day returns.|2799|1199|Green|S,M,L,XL,XXL|104|4319|65",
            "men_sports_and_active_wear|Puma Sleeveless Gym Tank|Breathable mesh. Machine wash cold. Easy 7-day returns.|3699|1299|White|S,M,L,XL,XXL|133|224|94",
            "men_sports_and_active_wear|HRX Running Jacket|Lightweight, reflective prints. Machine wash cold. Easy 7-day returns.|3999|2699|Beige|S,M,L,XL,XXL|250|4069|26",
            "men_sports_and_active_wear|Decathlon Domyos Performance Polo|Moisture-wicking polo. Machine wash cold. Easy 7-day returns.|3199|1999|Black|S,M,L,XL,XXL|56|3513|62",
            "men_sports_and_active_wear|Under Armour Yoga Pants|Four-way stretch. Machine wash cold. Easy 7-day returns.|5799|2399|Mustard|S,M,L,XL,XXL|204|1123|89",
            // ---- men_sports_sandals
            "men_sports_sandals|Woodland Trekking Sports Sandals|Rugged lugged outsole. Cushioned insole for all-day comfort. 30-day warranty.|1199|829|Beige|6,7,8,9,10,11|175|1087|31",
            "men_sports_sandals|Puma Velcro Strap Sports Sandals|Adjustable three straps. Cushioned insole for all-day comfort. 30-day warranty.|2199|1599|Brown|6,7,8,9,10,11|31|3966|112",
            "men_sports_sandals|Adidas Water Sports Sandals|Quick-dry straps. Cushioned insole for all-day comfort. 30-day warranty.|4299|2499|Tan|6,7,8,9,10,11|112|1413|118",
            "men_sports_sandals|Decathlon Quechua Hiking Sandals|Toe protection bumper. Cushioned insole for all-day comfort. 30-day warranty.|1799|1199|Coffee Brown|6,7,8,9,10,11|64|3285|111",
            "men_sports_sandals|Sparx Lightweight Running Sandals|Minimal cushioned base. Cushioned insole for all-day comfort. 30-day warranty.|4099|1699|White|6,7,8,9,10,11|179|3861|52",
            "men_sports_sandals|Bata Leather Sports Sandals|Nubuck leather straps. Cushioned insole for all-day comfort. 30-day warranty.|1899|1199|Black|6,7,8,9,10,11|131|4029|112",
            // ---- men_active_t_shirts
            "men_active_t_shirts|Nike Dri-Fit Training T-Shirt|Sweat-wicking, quick-dry. Machine wash cold. Easy 7-day returns.|2799|1699|Navy Blue|S,M,L,XL,XXL|133|3818|9",
            "men_active_t_shirts|Adidas Running T-Shirt with Reflective Logo|Reflective print for night runs. Machine wash cold. Easy 7-day returns.|1599|889|White|S,M,L,XL,XXL|187|799|94",
            "men_active_t_shirts|Puma Compression T-Shirt|Second-skin fit. Machine wash cold. Easy 7-day returns.|1999|1299|Mustard|S,M,L,XL,XXL|123|1358|85",
            "men_active_t_shirts|HRX Seamless Training Tee|Body-mapped ventilation. Machine wash cold. Easy 7-day returns.|2399|1299|Grey Melange|S,M,L,XL,XXL|162|4683|66",
            "men_active_t_shirts|Decathlon Kalenji Graphic Gym T-Shirt|Bold gym graphic. Machine wash cold. Easy 7-day returns.|2699|1499|Olive|S,M,L,XL,XXL|240|659|117",
            "men_active_t_shirts|Reebok Full Sleeve Running Tee|Thumbholes, light. Machine wash cold. Easy 7-day returns.|1799|839|Khaki|S,M,L,XL,XXL|96|1776|8",
            // ---- men_track_pants_and_shorts
            "men_track_pants_and_shorts|Puma Training Shorts with Zip Pocket|Quick-dry woven shorts. Machine wash. Easy 7-day returns.|2199|1299|Olive|28,30,32,34,36|187|2696|33",
            "men_track_pants_and_shorts|Adidas 2-in-1 Running Shorts|Inner compression liner. Machine wash. Easy 7-day returns.|1399|659|Maroon|28,30,32,34,36|168|298|41",
            "men_track_pants_and_shorts|Nike Slim Track Pants|Tapered track pants. Machine wash. Easy 7-day returns.|4199|1599|Green|28,30,32,34,36|153|1352|55",
            "men_track_pants_and_shorts|HRX Basketball Shorts|Mesh, longer length. Machine wash. Easy 7-day returns.|4099|1499|Olive|28,30,32,34,36|65|170|112",
            "men_track_pants_and_shorts|Reebok Woven Track Pants|Lightweight and breathable. Machine wash. Easy 7-day returns.|1999|1299|Olive|28,30,32,34,36|245|4035|75",
            "men_track_pants_and_shorts|Decathlon Kalenji Gym Joggers|Stretch with cuffed hems. Machine wash. Easy 7-day returns.|3499|1499|Olive|28,30,32,34,36|76|3138|117",
            // ---- men_tracksuits
            "men_tracksuits|Adidas Three-Stripe Tracksuit|Jacket and pants in tricot. Machine wash. Easy 7-day returns.|5199|2499|White|28,30,32,34,36|84|1470|101",
            "men_tracksuits|Puma Fleece Tracksuit|Hoodie and jogger set. Machine wash. Easy 7-day returns.|6299|3499|Maroon|28,30,32,34,36|207|2187|19",
            "men_tracksuits|Nike Colourblock Tracksuit|Retro colourblock design. Machine wash. Easy 7-day returns.|7299|2599|Mustard|28,30,32,34,36|103|1659|83",
            "men_tracksuits|HRX Woven Windbreaker Tracksuit|Lightweight shell set. Machine wash. Easy 7-day returns.|8999|4499|Maroon|28,30,32,34,36|124|3961|47",
            "men_tracksuits|Reebok Slim Fit Training Tracksuit|Stretch training set. Machine wash. Easy 7-day returns.|3199|1799|Charcoal|28,30,32,34,36|71|2326|50",
            "men_tracksuits|Fila Velour Tracksuit|Plush velour, zip jacket. Machine wash. Easy 7-day returns.|9199|4399|White|28,30,32,34,36|117|2041|90",
            // ---- men_jackets_and_sweatshirts
            "men_jackets_and_sweatshirts|Puma Training Hoodie|Brushed fleece, kangaroo pocket. Machine wash cold. Easy 7-day returns.|5599|2399|White|S,M,L,XL,XXL|194|4004|53",
            "men_jackets_and_sweatshirts|Adidas Running Windbreaker|Packable, water resistant. Machine wash cold. Easy 7-day returns.|4899|1799|Olive|S,M,L,XL,XXL|203|4018|99",
            "men_jackets_and_sweatshirts|Nike Track Jacket|Full zip with stand collar. Machine wash cold. Easy 7-day returns.|3399|1399|Mustard|S,M,L,XL,XXL|152|2734|33",
            "men_jackets_and_sweatshirts|HRX Quarter-Zip Training Top|Thermal knit. Machine wash cold. Easy 7-day returns.|3299|1199|Maroon|S,M,L,XL,XXL|49|3202|14",
            "men_jackets_and_sweatshirts|Under Armour Puffer Sports Vest|Sleeveless padded vest. Machine wash cold. Easy 7-day returns.|6499|3799|Olive|S,M,L,XL,XXL|242|3016|72",
            "men_jackets_and_sweatshirts|Reebok Fleece Zip Jacket|Polar fleece. Machine wash cold. Easy 7-day returns.|3099|2399|Teal|S,M,L,XL,XXL|66|1591|74",
            // ---- men_sports_accessories
            "men_sports_accessories|Nike Gym Duffel Bag|Shoe compartment, water-resistant. Quality checked. Easy returns.|4799|2799|Grey Melange|FREE|185|933|51",
            "men_sports_accessories|Adidas Yoga Mat 6mm|Anti-slip TPE mat. Quality checked. Easy returns.|2099|1499|Grey Melange|FREE|81|2593|83",
            "men_sports_accessories|Puma Resistance Bands Set|Five resistance levels. Quality checked. Easy returns.|1399|929|Charcoal|FREE|38|2252|13",
            "men_sports_accessories|Decathlon Domyos Sports Water Bottle 750ml|BPA-free, leak-proof. Quality checked. Easy returns.|2499|1899|Black|FREE|175|2158|96",
            "men_sports_accessories|Boldfit Gym Gloves|Padded palm, wrist wrap. Quality checked. Easy returns.|5299|2499|Green|FREE|33|1305|4",
            "men_sports_accessories|Cosco Skipping Rope|Ball-bearing speed rope. Quality checked. Easy returns.|8399|2999|Teal|FREE|96|4629|42",
            // ---- men_swimwear
            "men_swimwear|Speedo Swim Jammers|Chlorine-resistant, knee length. Soft on skin. Machine wash.|2599|1299|Khaki|S,M,L,XL,XXL|32|1370|93",
            "men_swimwear|Arena Swim Trunks|Quick-dry, drawstring. Soft on skin. Machine wash.|3099|1199|Mustard|S,M,L,XL,XXL|22|1267|90",
            "men_swimwear|Decathlon Nabaiji Printed Board Shorts|Beach-ready print. Soft on skin. Machine wash.|2299|1399|Grey Melange|S,M,L,XL,XXL|20|3860|10",
            "men_swimwear|Puma Swim Briefs|Competition cut. Soft on skin. Machine wash.|1399|899|Olive|S,M,L,XL,XXL|243|3296|97",
            "men_swimwear|Adidas Rash Guard Top|UPF 50+ sun protection. Soft on skin. Machine wash.|1199|549|Beige|S,M,L,XL,XXL|16|239|16",
            "men_swimwear|Jockey Swimming Kit with Goggles|Trunks, cap and goggles. Soft on skin. Machine wash.|3799|1799|Teal|S,M,L,XL,XXL|123|1314|62",
            // ---- men_wallets
            "men_wallets|Tommy Hilfiger Genuine Leather Bi-Fold Wallet|RFID blocking, 8 card slots. Quality checked. Easy returns.|899|449|White|FREE|190|1644|24",
            "men_wallets|Woodland Slim Card Holder|Minimal card case. Quality checked. Easy returns.|6899|2399|Navy Blue|FREE|40|3705|45",
            "men_wallets|Hidesign Tri-Fold Leather Wallet|Coin pocket and ID window. Quality checked. Easy returns.|6599|2499|Olive|FREE|65|1761|55",
            "men_wallets|Wildhorn Zip-Around Travel Wallet|Passport compartment. Quality checked. Easy returns.|1899|1099|Green|FREE|196|3495|104",
            "men_wallets|Fossil Money Clip Wallet|Front pocket money clip. Quality checked. Easy returns.|4899|2499|Black|FREE|80|2834|75",
            "men_wallets|Levi's Canvas Bi-Fold Wallet|Lightweight canvas. Quality checked. Easy returns.|1799|799|Gold|FREE|141|1029|28",
            // ---- men_belts
            "men_belts|Tommy Hilfiger Genuine Leather Formal Belt|Pin buckle, 35mm. Quality checked. Easy returns.|439|319|Khaki|FREE|149|3656|114",
            "men_belts|Louis Philippe Reversible Leather Belt|Black and brown in one. Quality checked. Easy returns.|1699|639|Teal|FREE|42|2946|78",
            "men_belts|Woodland Casual Canvas Belt|Woven canvas with metal tip. Quality checked. Easy returns.|4499|1799|Maroon|FREE|136|4029|55",
            "men_belts|Levi's Auto-Lock Buckle Belt|Ratchet buckle, no holes. Quality checked. Easy returns.|1499|1099|Navy Blue|FREE|246|3145|21",
            "men_belts|Allen Solly Braided Leather Belt|Stretch braided design. Quality checked. Easy returns.|2199|1499|Mustard|FREE|78|1372|119",
            "men_belts|Hidesign Suede Casual Belt|Soft suede finish. Quality checked. Easy returns.|1199|899|Navy Blue|FREE|244|364|68",
            // ---- men_perfumes_and_body_mists
            "men_perfumes_and_body_mists|Denver Eau de Parfum 100ml|Woody aromatic, long lasting. Quality checked. Easy returns.|2299|1699|Beige|FREE|133|4004|62",
            "men_perfumes_and_body_mists|Bella Vita Body Spray 150ml|Fresh citrus notes. Quality checked. Easy returns.|5099|2599|Green|FREE|25|4778|67",
            "men_perfumes_and_body_mists|Wild Stone Oud Perfume 50ml|Rich oud with amber. Quality checked. Easy returns.|6499|3499|Olive|FREE|191|840|2",
            "men_perfumes_and_body_mists|Fogg Aqua Fresh EDT 100ml|Marine and mint. Quality checked. Easy returns.|5599|3199|Green|FREE|197|4455|91",
            "men_perfumes_and_body_mists|Engage Luxury Gift Set of 4 Perfumes|4 x 20ml travel set. Quality checked. Easy returns.|839|459|Mustard|FREE|164|3488|76",
            "men_perfumes_and_body_mists|Park Avenue Musk Body Mist 200ml|Light musk. Quality checked. Easy returns.|2099|1299|Teal|FREE|28|784|14",
            // ---- men_trimmers
            "men_trimmers|Philips Cordless Beard Trimmer|60 min runtime, 20 length settings. Quality checked. Easy returns.|4899|2399|Black|FREE|166|2641|46",
            "men_trimmers|Mi Multi-Grooming Kit 9-in-1|Hair, beard and nose. Quality checked. Easy returns.|4199|2199|White|FREE|221|3061|110",
            "men_trimmers|Braun Body Groomer|Skin-friendly guards. Quality checked. Easy returns.|1299|829|Mustard|FREE|92|2430|35",
            "men_trimmers|Nova Nose and Ear Trimmer|Dual-edge blades. Quality checked. Easy returns.|1799|1399|Gold|FREE|118|2851|24",
            "men_trimmers|Havells Rechargeable Hair Clipper|Titanium blades. Quality checked. Easy returns.|3299|1199|Green|FREE|108|1105|76",
            "men_trimmers|Syska Foil Shaver|Close shave, wet and dry. Quality checked. Easy returns.|3199|2099|White|FREE|228|80|28",
            // ---- men_deodorants
            "men_deodorants|Nivea 48H Fresh Deodorant 150ml|Anti-perspirant protection. Quality checked. Easy returns.|709|409|Green|FREE|64|2289|99",
            "men_deodorants|Axe Pocket Perfume 18ml|Travel friendly. Quality checked. Easy returns.|409|259|White|FREE|173|3163|26",
            "men_deodorants|Park Avenue Roll-On Deodorant 50ml|Alcohol-free. Quality checked. Easy returns.|409|259|Grey Melange|FREE|138|1663|88",
            "men_deodorants|Fogg Deo Combo Pack of 3|Three fragrances. Quality checked. Easy returns.|709|559|Khaki|FREE|82|3324|106",
            "men_deodorants|Wild Stone Sport Deodorant 200ml|Active freshness. Quality checked. Easy returns.|919|569|Gold|FREE|15|2502|51",
            "men_deodorants|Engage Aqua Deodorant 150ml|Cool marine notes. Quality checked. Easy returns.|579|399|Navy Blue|FREE|63|2867|29",
            // ---- men_ties_cufflinks_and_pocket_squares
            "men_ties_cufflinks_and_pocket_squares|Louis Philippe Silk Tie with Pocket Square|Woven silk, matching square. Quality checked. Easy returns.|1999|1599|Maroon|FREE|135|349|62",
            "men_ties_cufflinks_and_pocket_squares|Van Heusen Tie, Cufflinks and Pocket Square Set|Gift boxed trio. Quality checked. Easy returns.|1899|909|Green|FREE|161|2554|43",
            "men_ties_cufflinks_and_pocket_squares|Peluche Skinny Knit Tie|Textured knit. Quality checked. Easy returns.|1299|779|Maroon|FREE|69|1804|74",
            "men_ties_cufflinks_and_pocket_squares|Tossido Paisley Print Tie|Classic paisley. Quality checked. Easy returns.|3399|1799|Black|FREE|39|3306|107",
            "men_ties_cufflinks_and_pocket_squares|Alvaro Castagnino Metal Cufflinks Set|Rhodium plated. Quality checked. Easy returns.|1199|719|Green|FREE|17|537|19",
            "men_ties_cufflinks_and_pocket_squares|Raymond Bow Tie with Pocket Square|Pre-tied bow tie. Quality checked. Easy returns.|2599|1999|Beige|FREE|26|2196|11",
            // ---- men_accessory_gift_sets
            "men_accessory_gift_sets|Tommy Hilfiger Wallet and Belt Combo|Leather gift box. Quality checked. Easy returns.|4099|2499|Beige|FREE|98|259|62",
            "men_accessory_gift_sets|Louis Philippe Watch and Wallet Set|Analog watch with wallet. Quality checked. Easy returns.|4099|2699|Grey Melange|FREE|86|924|92",
            "men_accessory_gift_sets|Fossil Grooming Gift Kit|Trimmer, perfume, comb. Quality checked. Easy returns.|4199|1899|White|FREE|72|137|44",
            "men_accessory_gift_sets|Woodland Perfume Gift Set|Four travel sprays. Quality checked. Easy returns.|3499|2399|Maroon|FREE|136|3310|89",
            "men_accessory_gift_sets|Hidesign Keychain and Card Holder Set|Leather duo. Quality checked. Easy returns.|2399|1799|Charcoal|FREE|47|1168|80",
            "men_accessory_gift_sets|Bella Vita Sunglasses and Belt Combo|Gift ready. Quality checked. Easy returns.|1699|999|Beige|FREE|92|4421|90",
            // ---- men_caps_and_hats
            "men_caps_and_hats|Puma Baseball Cap|Adjustable strap, cotton twill. Quality checked. Easy returns.|409|309|Grey Melange|FREE|69|1928|103",
            "men_caps_and_hats|Nike Snapback Cap|Flat brim, embroidered logo. Quality checked. Easy returns.|1199|919|Beige|FREE|79|3399|75",
            "men_caps_and_hats|Adidas Bucket Hat|Reversible cotton. Quality checked. Easy returns.|2699|1399|Navy Blue|FREE|161|4687|61",
            "men_caps_and_hats|Roadster Woollen Beanie|Ribbed knit. Quality checked. Easy returns.|1599|1199|Black|FREE|134|2104|36",
            "men_caps_and_hats|Levi's Running Cap|Lightweight, quick dry. Quality checked. Easy returns.|1299|869|Silver|FREE|190|4524|111",
            "men_caps_and_hats|New Era Fedora Hat|Classic band detail. Quality checked. Easy returns.|1299|649|Brown|FREE|134|2593|120",
            // ---- men_mufflers_scarves_and_gloves
            "men_mufflers_scarves_and_gloves|Monte Carlo Woollen Muffler|Soft knit, fringed ends. Quality checked. Easy returns.|959|469|Navy Blue|FREE|37|3939|94",
            "men_mufflers_scarves_and_gloves|Woodland Touchscreen Winter Gloves|Fleece lined. Quality checked. Easy returns.|1799|839|Navy Blue|FREE|215|4305|83",
            "men_mufflers_scarves_and_gloves|Wildcraft Checked Scarf|Brushed acrylic. Quality checked. Easy returns.|1899|869|Beige|FREE|221|373|55",
            "men_mufflers_scarves_and_gloves|Decathlon Wedze Neck Warmer|Fleece gaiter. Quality checked. Easy returns.|2199|1099|Gold|FREE|227|4307|39",
            "men_mufflers_scarves_and_gloves|Roadster Riding Gloves|Padded knuckles. Quality checked. Easy returns.|739|519|White|FREE|93|673|63",
            "men_mufflers_scarves_and_gloves|Fabindia Cashmere Blend Scarf|Lightweight luxury. Quality checked. Easy returns.|669|529|Silver|FREE|210|2408|108",
            // ---- men_phone_cases
            "men_phone_cases|Spigen Rugged Armor Case|Shock-absorbing TPU. Quality checked. Easy returns.|469|219|Gold|FREE|224|4732|4",
            "men_phone_cases|Casekaro Leather Flip Cover|Card slot with stand. Quality checked. Easy returns.|1299|589|Mustard|FREE|100|2244|88",
            "men_phone_cases|DailyObjects Clear Transparent Case|Anti-yellowing. Quality checked. Easy returns.|2199|739|Green|FREE|201|2587|116",
            "men_phone_cases|Ringke Printed Hard Case|Matte printed design. Quality checked. Easy returns.|1199|869|Gold|FREE|68|709|67",
            "men_phone_cases|Bewakoof MagSafe Compatible Case|Magnetic ring. Quality checked. Easy returns.|389|239|Olive|FREE|186|2136|40",
            "men_phone_cases|OtterBox Silicone Case|Soft-touch microfiber lining. Quality checked. Easy returns.|549|349|Khaki|FREE|45|3050|20",
            // ---- men_rings_and_wristwear
            "men_rings_and_wristwear|Fossil Stainless Steel Band Ring|Brushed finish. Quality checked. Easy returns.|919|459|Khaki|FREE|94|4276|52",
            "men_rings_and_wristwear|Giva Leather Braided Bracelet|Magnetic clasp. Quality checked. Easy returns.|2699|1699|Beige|FREE|74|2850|100",
            "men_rings_and_wristwear|Salty Beaded Stone Bracelet|Natural stone beads. Quality checked. Easy returns.|3599|1899|Gold|FREE|235|1244|107",
            "men_rings_and_wristwear|Dare by Voylla Silver Kada|925 sterling silver. Quality checked. Easy returns.|1399|979|Silver|FREE|184|3422|50",
            "men_rings_and_wristwear|Tommy Hilfiger Chain Link Bracelet|Steel cuban link. Quality checked. Easy returns.|2099|769|Teal|FREE|92|4017|95",
            "men_rings_and_wristwear|Bewakoof Signet Ring|Black onyx inlay. Quality checked. Easy returns.|1699|1299|Beige|FREE|95|3394|56",
            // ---- men_helmets
            "men_helmets|Steelbird Full Face Helmet|ISI certified, clear visor. Quality checked. Easy returns.|1999|1199|Navy Blue|FREE|127|1977|8",
            "men_helmets|Vega Open Face Helmet|Retro open face. Quality checked. Easy returns.|2299|1099|Green|FREE|207|282|77",
            "men_helmets|Studds Modular Flip-Up Helmet|Flip-up chin bar. Quality checked. Easy returns.|5399|3399|Charcoal|FREE|135|2878|108",
            "men_helmets|SMK Off-Road Motocross Helmet|Peak visor. Quality checked. Easy returns.|5599|3699|Mustard|FREE|186|914|81",
            "men_helmets|LS2 Bluetooth Ready Helmet|Speaker pockets. Quality checked. Easy returns.|3299|2199|White|FREE|131|3613|73",
            "men_helmets|Royal Enfield Kids Half Helmet|Lightweight shell. Quality checked. Easy returns.|2899|1999|Olive|FREE|96|2490|114",
            // ---- women_kurtas_and_suits
            "women_kurtas_and_suits|Biba Anarkali Kurta with Dupatta|Flared anarkali in printed rayon. Gentle hand wash. Ideal for festive and daily wear.|4499|2499|Magenta|XS,S,M,L,XL,XXL|51|1745|95",
            "women_kurtas_and_suits|W Straight Kurta Palazzo Set|Three-piece set with dupatta. Gentle hand wash. Ideal for festive and daily wear.|13999|4899|Off White|XS,S,M,L,XL,XXL|166|4374|84",
            "women_kurtas_and_suits|Libas Embroidered Silk Blend Suit Set|Thread work yoke, churidar. Gentle hand wash. Ideal for festive and daily wear.|3399|1299|Rust|XS,S,M,L,XL,XXL|146|2669|111",
            "women_kurtas_and_suits|Aurelia Chikankari Kurta Set|Lucknowi chikankari on georgette. Gentle hand wash. Ideal for festive and daily wear.|1599|1099|Navy Blue|XS,S,M,L,XL,XXL|85|384|37",
            "women_kurtas_and_suits|Anouk A-Line Block Print Kurta|Hand block printed cotton. Gentle hand wash. Ideal for festive and daily wear.|4599|3399|Peach|XS,S,M,L,XL,XXL|159|1413|72",
            "women_kurtas_and_suits|FabIndia Sharara Suit Set|Festive kurta with flared sharara. Gentle hand wash. Ideal for festive and daily wear.|3499|1999|Green|XS,S,M,L,XL,XXL|30|1887|75",
            // ---- women_kurtis_tunics_tops
            "women_kurtis_tunics_tops|W Printed Cotton Kurti|Short kurti with mandarin collar. Gentle hand wash. Ideal for festive and daily wear.|1299|779|Peach|XS,S,M,L,XL,XXL|132|1142|71",
            "women_kurtis_tunics_tops|Aurelia Embroidered Tunic|Neck embroidery, flared hem. Gentle hand wash. Ideal for festive and daily wear.|1799|689|Cream|XS,S,M,L,XL,XXL|243|3194|81",
            "women_kurtis_tunics_tops|Biba Kaftan Style Top|Relaxed kaftan in viscose. Gentle hand wash. Ideal for festive and daily wear.|879|629|Magenta|XS,S,M,L,XL,XXL|170|4605|82",
            "women_kurtis_tunics_tops|Libas Peplum Ethnic Top|Peplum flare with tie-up. Gentle hand wash. Ideal for festive and daily wear.|3799|1799|Navy Blue|XS,S,M,L,XL,XXL|213|3788|55",
            "women_kurtis_tunics_tops|Rangriti Angrakha Short Kurti|Wrap style with tassels. Gentle hand wash. Ideal for festive and daily wear.|1799|909|Pink|XS,S,M,L,XL,XXL|210|353|28",
            "women_kurtis_tunics_tops|Global Desi Ikat Print Tunic|Handloom-inspired ikat. Gentle hand wash. Ideal for festive and daily wear.|1499|839|Maroon|XS,S,M,L,XL,XXL|239|4644|15",
            // ---- women_sarees
            "women_sarees|Kalini Banarasi Silk Saree|Zari woven pallu, with blouse piece. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|9799|3899|Teal|FREE|135|2294|89",
            "women_sarees|Saree Mall Kanjivaram Silk Blend Saree|Temple border, rich zari. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|2699|1399|Maroon|FREE|105|1210|91",
            "women_sarees|Mitera Georgette Sequinned Saree|Party-wear sequin work. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|16499|8699|Peach|FREE|97|375|41",
            "women_sarees|Sangria Chiffon Printed Saree|Lightweight floral chiffon. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|4399|3499|Rust|FREE|173|1531|106",
            "women_sarees|Kanjivaram Silks Cotton Handloom Saree|Breathable handloom with tassels. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|8899|5599|Rust|FREE|84|3586|21",
            "women_sarees|Banarasi House Organza Embroidered Saree|Sheer organza with thread work. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|20499|8199|Navy Blue|FREE|86|2521|117",
            "women_sarees|Satrani Linen Jamdani Saree|Jamdani motifs on linen. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|8699|6299|Green|FREE|113|1418|7",
            "women_sarees|Kalini Bandhani Art Silk Saree|Traditional bandhej print. Saree length 5.5 m with 0.8 m blouse piece. Dry clean only.|10999|6999|Rust|FREE|139|3627|76",
            // ---- women_ethnic_wear
            "women_ethnic_wear|Biba Indo-Western Cape Set|Cape with palazzo and top. Gentle hand wash. Ideal for festive and daily wear.|4499|1699|Pink|XS,S,M,L,XL,XXL|114|2657|50",
            "women_ethnic_wear|Libas Gharara Set|Kurta with tiered gharara. Gentle hand wash. Ideal for festive and daily wear.|7899|5399|Rust|XS,S,M,L,XL,XXL|174|3655|29",
            "women_ethnic_wear|Aurelia Dhoti Kurta Set|Fusion dhoti with kurta. Gentle hand wash. Ideal for festive and daily wear.|9199|3399|Mustard|XS,S,M,L,XL,XXL|61|2881|118",
            "women_ethnic_wear|Sangria Jacket Style Kurta Set|Long jacket over kurta. Gentle hand wash. Ideal for festive and daily wear.|6499|3699|Mustard|XS,S,M,L,XL,XXL|214|3289|109",
            "women_ethnic_wear|Anouk Ethnic Maxi Dress|Printed flared maxi. Gentle hand wash. Ideal for festive and daily wear.|2699|1499|Green|XS,S,M,L,XL,XXL|189|498|0",
            "women_ethnic_wear|Indya Festive Co-ord Set|Embroidered top and pants. Gentle hand wash. Ideal for festive and daily wear.|3199|1499|Peach|XS,S,M,L,XL,XXL|78|888|98",
            // ---- women_leggings_salwars_churidars
            "women_leggings_salwars_churidars|Go Colors Cotton Lycra Churidar Leggings|Four-way stretch. Machine wash. Easy 7-day returns.|2499|959|Lavender|26,28,30,32,34|100|4395|16",
            "women_leggings_salwars_churidars|De Moza Ankle Length Leggings|Bio-washed cotton. Machine wash. Easy 7-day returns.|1099|659|Mustard|26,28,30,32,34|79|2425|20",
            "women_leggings_salwars_churidars|Twin Birds Patiala Salwar|Flowy patiala with drawstring. Machine wash. Easy 7-day returns.|589|299|Coral|26,28,30,32,34|72|859|60",
            "women_leggings_salwars_churidars|Biba Dhoti Salwar|Draped dhoti style. Machine wash. Easy 7-day returns.|1399|809|Rust|26,28,30,32,34|159|4685|45",
            "women_leggings_salwars_churidars|W Shimmer Party Leggings|Metallic shine. Machine wash. Easy 7-day returns.|629|389|Navy Blue|26,28,30,32,34|208|3702|43",
            "women_leggings_salwars_churidars|Lyra Straight Cigarette Pants|Ethnic straight pants. Machine wash. Easy 7-day returns.|2599|899|Pink|26,28,30,32,34|37|88|66",
            // ---- women_skirts_palazzos
            "women_skirts_palazzos|W Flared Palazzo Pants|Wide leg in rayon. Machine wash. Easy 7-day returns.|1899|659|Teal|26,28,30,32,34|82|782|56",
            "women_skirts_palazzos|Biba Printed Ethnic Skirt|Tiered printed skirt. Machine wash. Easy 7-day returns.|2099|839|Teal|26,28,30,32,34|98|4601|51",
            "women_skirts_palazzos|Global Desi Culottes Palazzo|Cropped culottes. Machine wash. Easy 7-day returns.|959|699|Navy Blue|26,28,30,32,34|135|2396|42",
            "women_skirts_palazzos|Libas Lehenga Style Skirt|Flared with gota trim. Machine wash. Easy 7-day returns.|899|669|Peach|26,28,30,32,34|120|1937|110",
            "women_skirts_palazzos|Aurelia Pleated Palazzo|Knife pleats, elastic waist. Machine wash. Easy 7-day returns.|2199|1399|Green|26,28,30,32,34|102|3317|24",
            "women_skirts_palazzos|Rangriti Wrap Ethnic Skirt|Wrap style with tie. Machine wash. Easy 7-day returns.|2699|1199|Maroon|26,28,30,32,34|182|3368|64",
            // ---- women_dress_materials
            "women_dress_materials|Satrani Unstitched Cotton Dress Material|Top, bottom and dupatta. Gentle hand wash. Ideal for festive and daily wear.|8099|2899|Mustard|XS,S,M,L,XL,XXL|15|2357|56",
            "women_dress_materials|Kalini Embroidered Georgette Dress Material|Semi-stitched with net dupatta. Gentle hand wash. Ideal for festive and daily wear.|4399|2699|Green|XS,S,M,L,XL,XXL|155|4262|109",
            "women_dress_materials|Saree Mall Chanderi Silk Dress Material|Zari butti. Gentle hand wash. Ideal for festive and daily wear.|2299|909|Gold|XS,S,M,L,XL,XXL|218|1300|12",
            "women_dress_materials|Rajnandini Printed Rayon Dress Material|Digital print. Gentle hand wash. Ideal for festive and daily wear.|2999|2199|Magenta|XS,S,M,L,XL,XXL|202|4151|44",
            "women_dress_materials|Mitera Pashmina Winter Dress Material|Warm pashmina blend. Gentle hand wash. Ideal for festive and daily wear.|4399|2199|Cream|XS,S,M,L,XL,XXL|54|1680|113",
            "women_dress_materials|Ishin Organza Dress Material|Sheer organza dupatta. Gentle hand wash. Ideal for festive and daily wear.|2599|1499|Rust|XS,S,M,L,XL,XXL|126|976|64",
            // ---- women_lehenga_cholis
            "women_lehenga_cholis|Fabcartz Bridal Velvet Lehenga Choli|Heavy zari and stone work. Gentle hand wash. Ideal for festive and daily wear.|29899|16099|Gold|XS,S,M,L,XL,XXL|157|4404|99",
            "women_lehenga_cholis|Sangria Sequinned Net Lehenga Set|Party-wear sequins. Gentle hand wash. Ideal for festive and daily wear.|16699|12799|Magenta|XS,S,M,L,XL,XXL|248|2082|86",
            "women_lehenga_cholis|Kalini Silk Blend Festive Lehenga|Brocade with dupatta. Gentle hand wash. Ideal for festive and daily wear.|9499|5199|Cream|XS,S,M,L,XL,XXL|108|281|120",
            "women_lehenga_cholis|Panash India Floral Printed Lehenga|Organza dupatta, light. Gentle hand wash. Ideal for festive and daily wear.|22899|17399|Green|XS,S,M,L,XL,XXL|230|2429|15",
            "women_lehenga_cholis|Saree Mall Mirror Work Navratri Lehenga|Gujarati mirror work. Gentle hand wash. Ideal for festive and daily wear.|20199|13899|Peach|XS,S,M,L,XL,XXL|235|1825|9",
            "women_lehenga_cholis|Mitera Pastel Georgette Lehenga|Engagement-ready pastel. Gentle hand wash. Ideal for festive and daily wear.|10799|5999|Mustard|XS,S,M,L,XL,XXL|223|2723|95",
            // ---- women_dupattas_shawls
            "women_dupattas_shawls|FabIndia Phulkari Embroidered Dupatta|Punjabi phulkari. Quality checked. Easy returns.|3599|1399|Beige|FREE|213|102|106",
            "women_dupattas_shawls|Biba Banarasi Silk Dupatta|Zari woven. Quality checked. Easy returns.|1999|1099|Silver|FREE|180|3463|92",
            "women_dupattas_shawls|W Pashmina Shawl|Soft woollen shawl. Quality checked. Easy returns.|2899|1699|White|FREE|111|1792|66",
            "women_dupattas_shawls|Kashmiri Kaarigar Bandhani Chiffon Dupatta|Tie-dye bandhej. Quality checked. Easy returns.|739|329|Silver|FREE|16|2870|45",
            "women_dupattas_shawls|Weavers Villa Chikankari Georgette Dupatta|Lucknowi embroidery. Quality checked. Easy returns.|1199|669|White|FREE|155|4355|23",
            "women_dupattas_shawls|Rangriti Kalamkari Cotton Dupatta|Hand-painted motifs. Quality checked. Easy returns.|3299|1299|Maroon|FREE|24|1498|111",
            // ---- women_jackets
            "women_jackets|Biba Ethnic Long Shrug Jacket|Printed long jacket. Gentle hand wash. Ideal for festive and daily wear.|2599|1399|Mustard|XS,S,M,L,XL,XXL|177|421|27",
            "women_jackets|Global Desi Embroidered Short Jacket|Festive bolero. Gentle hand wash. Ideal for festive and daily wear.|2299|859|Gold|XS,S,M,L,XL,XXL|225|3318|101",
            "women_jackets|W Kantha Work Jacket|Hand kantha stitch. Gentle hand wash. Ideal for festive and daily wear.|6899|2599|Rust|XS,S,M,L,XL,XXL|124|2743|60",
            "women_jackets|FabIndia Quilted Ethnic Jacket|Winter quilted. Gentle hand wash. Ideal for festive and daily wear.|5199|3099|Pink|XS,S,M,L,XL,XXL|249|4659|21",
            "women_jackets|Libas Velvet Ethnic Jacket|Rich velvet. Gentle hand wash. Ideal for festive and daily wear.|1699|1299|Navy Blue|XS,S,M,L,XL,XXL|76|1454|64",
            "women_jackets|Anouk Ikat Waistcoat|Sleeveless ikat. Gentle hand wash. Ideal for festive and daily wear.|4299|2199|Rust|XS,S,M,L,XL,XXL|25|953|57",
            // ---- women_belts_scarves_more
            "women_belts_scarves_more|Accessorize Skinny Leather Belt|Gold-tone buckle. Quality checked. Easy returns.|1999|929|Navy Blue|FREE|77|4032|105",
            "women_belts_scarves_more|Lino Perros Printed Silk Scarf|Square satin scarf. Quality checked. Easy returns.|999|569|Mustard|FREE|215|2948|117",
            "women_belts_scarves_more|Allen Solly Waist Corset Belt|Statement waist belt. Quality checked. Easy returns.|959|649|Green|FREE|76|3518|43",
            "women_belts_scarves_more|Global Desi Embroidered Stole|Lightweight cotton stole. Quality checked. Easy returns.|2999|1399|Charcoal|FREE|94|3939|67",
            "women_belts_scarves_more|Mango Hair Scrunchie Set|Set of 6. Quality checked. Easy returns.|1899|1299|Grey Melange|FREE|117|1607|120",
            "women_belts_scarves_more|H&M Woollen Winter Scarf|Ribbed knit. Quality checked. Easy returns.|1199|539|Teal|FREE|99|1794|42",
            // ---- women_watches_wearables
            "women_watches_wearables|Titan Raga Rose Gold Bracelet Watch|Slim dial, link strap. Quality checked. Easy returns.|8599|5399|Brown|FREE|59|1753|56",
            "women_watches_wearables|Fossil Leather Strap Analog Watch|Minimal white dial. Quality checked. Easy returns.|4499|2299|Silver|FREE|216|4680|66",
            "women_watches_wearables|Daniel Wellington Mesh Strap Watch|Ultra slim case. Quality checked. Easy returns.|3499|1699|Beige|FREE|83|4533|3",
            "women_watches_wearables|Fastrack Smartwatch with AMOLED Display|Health tracking, Bluetooth calling. Quality checked. Easy returns.|10399|7899|Beige|FREE|86|366|41",
            "women_watches_wearables|Casio Ceramic Analog Watch|Scratch resistant. Quality checked. Easy returns.|3299|1699|Olive|FREE|54|1921|87",
            "women_watches_wearables|Noise Fitness Band|Steps, sleep and SpO2. Quality checked. Easy returns.|3199|2299|Grey Melange|FREE|120|2043|48",
            // ---- women_western_wear
            "women_western_wear|ONLY Smocked Midi Dress|Square neck, puff sleeves. Machine wash cold. Easy 7-day returns.|4299|2299|Mauve|XS,S,M,L,XL|227|4636|26",
            "women_western_wear|Vero Moda Ribbed Crop Top|Stretch rib knit. Machine wash cold. Easy 7-day returns.|3899|2399|Lavender|XS,S,M,L,XL|200|1292|25",
            "women_western_wear|Mango High-Rise Wide Leg Trousers|Flowy wide leg. Machine wash cold. Easy 7-day returns.|1099|769|Black|XS,S,M,L,XL|153|2022|26",
            "women_western_wear|H&M Denim Jacket|Classic blue wash. Machine wash cold. Easy 7-day returns.|2799|1599|Mustard|XS,S,M,L,XL|113|3540|111",
            "women_western_wear|Zara Satin Shirt|Relaxed satin shirt. Machine wash cold. Easy 7-day returns.|2099|959|Navy Blue|XS,S,M,L,XL|87|179|82",
            "women_western_wear|SASSAFRAS Knit Co-ord Set|Top and skirt set. Machine wash cold. Easy 7-day returns.|1699|1099|Navy Blue|XS,S,M,L,XL|81|1596|49",
            // ---- women_dresses
            "women_dresses|ONLY Floral Wrap Midi Dress|Tie waist wrap dress. Machine wash cold. Easy 7-day returns.|5299|2999|Teal|XS,S,M,L,XL|244|3565|43",
            "women_dresses|Vero Moda Bodycon Mini Dress|Stretch rib bodycon. Machine wash cold. Easy 7-day returns.|3899|2799|Black|XS,S,M,L,XL|170|2284|4",
            "women_dresses|Mango Tiered Maxi Dress|Flowy cotton maxi. Machine wash cold. Easy 7-day returns.|8699|3299|Pink|XS,S,M,L,XL|197|2052|119",
            "women_dresses|Berrylush Satin Slip Dress|Bias cut slip. Machine wash cold. Easy 7-day returns.|6999|2699|Navy Blue|XS,S,M,L,XL|161|150|26",
            "women_dresses|SASSAFRAS Shirt Dress with Belt|Button-down shirt dress. Machine wash cold. Easy 7-day returns.|4799|2799|Pink|XS,S,M,L,XL|26|2810|35",
            "women_dresses|Tokyo Talkies Smocked Fit and Flare Dress|Puff sleeves. Machine wash cold. Easy 7-day returns.|1599|1199|Beige|XS,S,M,L,XL|80|2109|33",
            // ---- women_tops
            "women_tops|ONLY Puff Sleeve Top|Square neck. Machine wash cold. Easy 7-day returns.|1499|969|Teal|XS,S,M,L,XL|217|3535|60",
            "women_tops|Vero Moda Off-Shoulder Ruffle Top|Bardot neckline. Machine wash cold. Easy 7-day returns.|2499|1599|Navy Blue|XS,S,M,L,XL|84|449|82",
            "women_tops|H&M Ribbed Tank Top|Stretch rib. Machine wash cold. Easy 7-day returns.|1099|689|Lavender|XS,S,M,L,XL|83|2910|40",
            "women_tops|Tokyo Talkies Peplum Top|Flared peplum. Machine wash cold. Easy 7-day returns.|2199|1199|Green|XS,S,M,L,XL|125|251|29",
            "women_tops|SASSAFRAS Satin Cowl Neck Top|Cowl drape. Machine wash cold. Easy 7-day returns.|1199|689|Lavender|XS,S,M,L,XL|42|4133|91",
            "women_tops|Mango Corset Style Top|Structured bustier. Machine wash cold. Easy 7-day returns.|2899|1799|Navy Blue|XS,S,M,L,XL|211|2310|3",
            // ---- women_tshirts
            "women_tshirts|H&M Oversized Graphic T-Shirt|Boyfriend fit. Machine wash cold. Easy 7-day returns.|1599|799|Coral|XS,S,M,L,XL|184|2139|9",
            "women_tshirts|Puma Cotton Crew Neck T-Shirt|Regular fit essential. Machine wash cold. Easy 7-day returns.|1099|699|Black|XS,S,M,L,XL|173|3977|79",
            "women_tshirts|Bewakoof Cropped T-Shirt|Boxy crop. Machine wash cold. Easy 7-day returns.|2499|1299|Rust|XS,S,M,L,XL|242|1171|79",
            "women_tshirts|ONLY Striped Breton T-Shirt|Classic stripes. Machine wash cold. Easy 7-day returns.|649|379|Beige|XS,S,M,L,XL|245|3625|45",
            "women_tshirts|Roadster Polo T-Shirt|Pique cotton. Machine wash cold. Easy 7-day returns.|1099|529|Black|XS,S,M,L,XL|247|1352|37",
            "women_tshirts|Levi's Pack of 2 Basic T-Shirts|Everyday basics. Machine wash cold. Easy 7-day returns.|2299|1099|Navy Blue|XS,S,M,L,XL|24|4800|33",
            // ---- women_jeans
            "women_jeans|Levi's High-Rise Skinny Jeans|Super stretch. Machine wash. Easy 7-day returns.|6499|2899|Peach|26,28,30,32,34|181|1113|92",
            "women_jeans|ONLY Mom Fit Jeans|Relaxed tapered. Machine wash. Easy 7-day returns.|2499|1699|Coral|26,28,30,32,34|247|2342|60",
            "women_jeans|Vero Moda Wide Leg Jeans|Flowy wide leg. Machine wash. Easy 7-day returns.|2099|849|Mauve|26,28,30,32,34|188|2500|49",
            "women_jeans|Roadster Straight Fit Jeans|Mid rise classic. Machine wash. Easy 7-day returns.|2799|1599|Rust|26,28,30,32,34|67|1945|88",
            "women_jeans|Pepe Jeans Flared Bootcut Jeans|Retro flare. Machine wash. Easy 7-day returns.|3399|2399|Green|26,28,30,32,34|106|2915|67",
            "women_jeans|Wrangler Distressed Boyfriend Jeans|Ripped details. Machine wash. Easy 7-day returns.|2699|1799|Lavender|26,28,30,32,34|187|2209|51",
            // ---- women_trousers_capris
            "women_trousers_capris|Marks & Spencer Formal Straight Trousers|Office-ready. Machine wash. Easy 7-day returns.|1899|1299|Maroon|26,28,30,32,34|133|1440|31",
            "women_trousers_capris|Mango Wide Leg Pleated Trousers|High waist pleats. Machine wash. Easy 7-day returns.|2099|1199|Mauve|26,28,30,32,34|229|417|30",
            "women_trousers_capris|ONLY Cigarette Trousers|Slim ankle length. Machine wash. Easy 7-day returns.|1699|1199|Coral|26,28,30,32,34|203|397|104",
            "women_trousers_capris|W Linen Blend Trousers|Breathable. Machine wash. Easy 7-day returns.|3599|1899|Beige|26,28,30,32,34|73|1452|52",
            "women_trousers_capris|Van Heusen Woman Cotton Capris|Three-fourth length. Machine wash. Easy 7-day returns.|3699|2099|Coral|26,28,30,32,34|144|116|85",
            "women_trousers_capris|Allen Solly Paperbag Waist Trousers|Tie waist. Machine wash. Easy 7-day returns.|4999|2199|Green|26,28,30,32,34|185|278|84",
            // ---- women_shorts_skirts
            "women_shorts_skirts|ONLY Denim Shorts|Frayed hem. Machine wash. Easy 7-day returns.|2899|1599|Maroon|26,28,30,32,34|215|4472|37",
            "women_shorts_skirts|H&M A-Line Mini Skirt|Button front. Machine wash. Easy 7-day returns.|1999|1299|Mustard|26,28,30,32,34|213|3615|51",
            "women_shorts_skirts|Mango Pleated Midi Skirt|Satin pleats. Machine wash. Easy 7-day returns.|1999|899|White|26,28,30,32,34|182|3295|56",
            "women_shorts_skirts|Vero Moda Paperbag Shorts|Linen blend. Machine wash. Easy 7-day returns.|2199|1399|Mauve|26,28,30,32,34|159|1089|63",
            "women_shorts_skirts|Levi's Denim Skirt|Mid-length denim. Machine wash. Easy 7-day returns.|3199|1799|Rust|26,28,30,32,34|30|771|62",
            "women_shorts_skirts|Tokyo Talkies Tennis Skirt|Built-in shorts. Machine wash. Easy 7-day returns.|3499|1599|Mustard|26,28,30,32,34|38|4166|54",
            // ---- women_coords
            "women_coords|ONLY Printed Shirt and Trousers Co-ord|Relaxed resort set. Machine wash cold. Easy 7-day returns.|3399|1299|Rust|XS,S,M,L,XL|117|4249|111",
            "women_coords|Vero Moda Knit Crop Top and Skirt Set|Ribbed knit. Machine wash cold. Easy 7-day returns.|4499|1599|Navy Blue|XS,S,M,L,XL|241|268|74",
            "women_coords|Berrylush Blazer and Shorts Co-ord|Smart casual. Machine wash cold. Easy 7-day returns.|2199|1099|Teal|XS,S,M,L,XL|250|2846|2",
            "women_coords|Tokyo Talkies Satin Shirt and Pants Set|Lounge-luxe. Machine wash cold. Easy 7-day returns.|1799|1099|Mauve|XS,S,M,L,XL|148|4682|66",
            "women_coords|SASSAFRAS Linen Co-ord Set|Breathable linen. Machine wash cold. Easy 7-day returns.|2299|1499|Beige|XS,S,M,L,XL|192|1125|29",
            "women_coords|Mango Tie-Dye Lounge Co-ord|Comfy loungewear. Machine wash cold. Easy 7-day returns.|6099|2299|Lavender|XS,S,M,L,XL|213|4210|20",
            // ---- women_playsuits
            "women_playsuits|ONLY Floral Playsuit|Ruffle hem. Machine wash cold. Easy 7-day returns.|2799|1499|White|XS,S,M,L,XL|24|3082|75",
            "women_playsuits|Berrylush Denim Playsuit|Button front. Machine wash cold. Easy 7-day returns.|1799|859|Mustard|XS,S,M,L,XL|114|4676|35",
            "women_playsuits|Tokyo Talkies Wrap Playsuit|Tie waist. Machine wash cold. Easy 7-day returns.|2099|959|Maroon|XS,S,M,L,XL|104|1694|15",
            "women_playsuits|SASSAFRAS Off-Shoulder Playsuit|Bardot neckline. Machine wash cold. Easy 7-day returns.|3499|2199|Beige|XS,S,M,L,XL|43|353|37",
            "women_playsuits|H&M Linen Playsuit|Breathable. Machine wash cold. Easy 7-day returns.|2999|1999|Green|XS,S,M,L,XL|93|82|74",
            "women_playsuits|Mango Halter Neck Playsuit|Summer ready. Machine wash cold. Easy 7-day returns.|1699|809|White|XS,S,M,L,XL|230|4471|33",
            // ---- women_jumpsuits
            "women_jumpsuits|ONLY Wide Leg Jumpsuit|Belted waist. Machine wash cold. Easy 7-day returns.|2299|1499|Mauve|XS,S,M,L,XL|93|232|103",
            "women_jumpsuits|Vero Moda Denim Dungaree Jumpsuit|Utility pockets. Machine wash cold. Easy 7-day returns.|4699|2699|Pink|XS,S,M,L,XL|90|1285|119",
            "women_jumpsuits|Mango Satin Party Jumpsuit|Cowl neck. Machine wash cold. Easy 7-day returns.|3799|2599|Mauve|XS,S,M,L,XL|33|1222|106",
            "women_jumpsuits|Berrylush Utility Boiler Jumpsuit|Zip front. Machine wash cold. Easy 7-day returns.|2099|1299|Black|XS,S,M,L,XL|180|2105|59",
            "women_jumpsuits|Harpa Printed Culotte Jumpsuit|Cropped legs. Machine wash cold. Easy 7-day returns.|5199|2799|Rust|XS,S,M,L,XL|177|2502|72",
            "women_jumpsuits|SASSAFRAS Halter Jumpsuit|Backless detail. Machine wash cold. Easy 7-day returns.|3499|2399|Teal|XS,S,M,L,XL|174|1753|96",
            // ---- women_shrugs
            "women_shrugs|ONLY Longline Open Front Shrug|Flowy georgette. Machine wash cold. Easy 7-day returns.|2399|969|Pink|XS,S,M,L,XL|96|999|104",
            "women_shrugs|Vero Moda Crochet Shrug|Boho crochet. Machine wash cold. Easy 7-day returns.|1299|949|Green|XS,S,M,L,XL|106|4409|70",
            "women_shrugs|H&M Knit Cardigan Shrug|Soft knit. Machine wash cold. Easy 7-day returns.|3499|1399|Coral|XS,S,M,L,XL|106|4638|59",
            "women_shrugs|Tokyo Talkies Kimono Shrug|Printed kimono. Machine wash cold. Easy 7-day returns.|1699|1199|Maroon|XS,S,M,L,XL|69|1682|14",
            "women_shrugs|Global Desi Cropped Shrug|Ribbed crop. Machine wash cold. Easy 7-day returns.|1699|1099|Beige|XS,S,M,L,XL|250|3393|10",
            "women_shrugs|Mango Lace Shrug|Delicate lace. Machine wash cold. Easy 7-day returns.|1499|819|Maroon|XS,S,M,L,XL|125|2760|117",
            // ---- women_sweaters_sweatshirts
            "women_sweaters_sweatshirts|H&M Oversized Hoodie|Brushed fleece. Machine wash cold. Easy 7-day returns.|1799|1299|Black|XS,S,M,L,XL|169|1378|105",
            "women_sweaters_sweatshirts|ONLY Cable Knit Sweater|Chunky knit. Machine wash cold. Easy 7-day returns.|6299|2199|White|XS,S,M,L,XL|65|1212|70",
            "women_sweaters_sweatshirts|Puma Cropped Sweatshirt|Boxy fit. Machine wash cold. Easy 7-day returns.|2599|1199|Navy Blue|XS,S,M,L,XL|185|756|116",
            "women_sweaters_sweatshirts|Roadster Turtleneck Sweater|Rib knit. Machine wash cold. Easy 7-day returns.|2599|1399|Beige|XS,S,M,L,XL|169|1270|32",
            "women_sweaters_sweatshirts|Vero Moda Button Cardigan|V-neck cardigan. Machine wash cold. Easy 7-day returns.|3699|1699|Peach|XS,S,M,L,XL|80|939|9",
            "women_sweaters_sweatshirts|Mango Graphic Sweatshirt|Bold print. Machine wash cold. Easy 7-day returns.|2899|1899|Navy Blue|XS,S,M,L,XL|183|1777|120",
            // ---- women_jackets_coats
            "women_jackets_coats|ONLY Puffer Jacket|Quilted, hooded. Machine wash cold. Easy 7-day returns.|7399|5299|Peach|XS,S,M,L,XL|240|4537|43",
            "women_jackets_coats|Vero Moda Trench Coat|Belted classic. Machine wash cold. Easy 7-day returns.|4399|2199|Green|XS,S,M,L,XL|233|2794|56",
            "women_jackets_coats|Mango Denim Jacket|Oversized wash. Machine wash cold. Easy 7-day returns.|7199|2799|Teal|XS,S,M,L,XL|156|2955|13",
            "women_jackets_coats|Zara Faux Leather Jacket|Biker style. Machine wash cold. Easy 7-day returns.|9799|4899|Black|XS,S,M,L,XL|60|4178|74",
            "women_jackets_coats|H&M Wool Blend Overcoat|Longline coat. Machine wash cold. Easy 7-day returns.|8199|4299|Teal|XS,S,M,L,XL|156|1638|92",
            "women_jackets_coats|Roadster Teddy Fleece Jacket|Sherpa fleece. Machine wash cold. Easy 7-day returns.|7699|2999|Green|XS,S,M,L,XL|249|4608|83",
            // ---- women_blazers_waistcoats
            "women_blazers_waistcoats|Mango Single-Breasted Blazer|Tailored fit. Machine wash cold. Easy 7-day returns.|8399|4699|Black|XS,S,M,L,XL|162|1248|94",
            "women_blazers_waistcoats|ONLY Oversized Boyfriend Blazer|Relaxed shoulders. Machine wash cold. Easy 7-day returns.|3599|2599|Black|XS,S,M,L,XL|141|2030|48",
            "women_blazers_waistcoats|Vero Moda Double-Breasted Blazer|Gold buttons. Machine wash cold. Easy 7-day returns.|3499|1599|Black|XS,S,M,L,XL|166|1970|110",
            "women_blazers_waistcoats|Van Heusen Woman Linen Blend Blazer|Unlined summer blazer. Machine wash cold. Easy 7-day returns.|2999|1699|Black|XS,S,M,L,XL|44|3502|86",
            "women_blazers_waistcoats|Allen Solly Tailored Waistcoat|V-neck vest. Machine wash cold. Easy 7-day returns.|7899|4499|Lavender|XS,S,M,L,XL|148|3173|66",
            "women_blazers_waistcoats|Marks & Spencer Checked Blazer|Houndstooth check. Machine wash cold. Easy 7-day returns.|3199|2299|Peach|XS,S,M,L,XL|154|950|7",
            // ---- women_plus_size
            "women_plus_size|Instafab Plus Plus Size Wrap Dress|Flattering wrap. Designed for a comfortable plus-size fit.|3099|2199|Mauve|XXL,3XL,4XL|243|4689|88",
            "women_plus_size|ONLY Carmakoma Plus Size Straight Kurta|Comfort cotton. Designed for a comfortable plus-size fit.|4299|2999|Pink|XXL,3XL,4XL|230|748|10",
            "women_plus_size|Aurelia Curve Plus Size High-Rise Jeans|Stretch denim. Designed for a comfortable plus-size fit.|3899|2999|Mustard|XXL,3XL,4XL|247|2786|43",
            "women_plus_size|Libas Plus Plus Size Tunic Top|Relaxed tunic. Designed for a comfortable plus-size fit.|3899|1599|Pink|XXL,3XL,4XL|38|1915|54",
            "women_plus_size|Mango Plus Plus Size Palazzo|Wide leg. Designed for a comfortable plus-size fit.|2799|1199|Mauve|XXL,3XL,4XL|33|1986|80",
            "women_plus_size|H&M+ Plus Size Shirt|Button-down. Designed for a comfortable plus-size fit.|5399|2299|Maroon|XXL,3XL,4XL|64|4503|91",
            // ---- women_footwear
            "women_footwear|Metro Block Heel Sandals|Ankle strap, cushioned. Cushioned insole for all-day comfort.|5099|2699|Navy Blue|3,4,5,6,7,8|44|3209|92",
            "women_footwear|Mochi Embellished Ethnic Flats|Juttis with stones. Cushioned insole for all-day comfort.|3899|2899|White|3,4,5,6,7,8|105|512|52",
            "women_footwear|Catwalk Casual Sneakers|White lace-ups. Cushioned insole for all-day comfort.|3499|1299|Beige|3,4,5,6,7,8|238|4020|88",
            "women_footwear|Bata Platform Slides|Chunky sole slides. Cushioned insole for all-day comfort.|2299|1799|Black|3,4,5,6,7,8|190|1470|44",
            "women_footwear|Inc.5 Wedge Sandals|Comfort wedges. Cushioned insole for all-day comfort.|7899|2999|Navy Blue|3,4,5,6,7,8|160|4378|47",
            "women_footwear|Crocs Classic Clogs|Lightweight clogs. Cushioned insole for all-day comfort.|1799|1099|Brown|3,4,5,6,7,8|162|992|108",
            // ---- women_flats
            "women_flats|Metro Embroidered Juttis|Handcrafted juttis. Cushioned insole for all-day comfort.|2099|1299|Navy Blue|3,4,5,6,7,8|220|4371|51",
            "women_flats|Mochi Ballet Flats|Pointed toe. Cushioned insole for all-day comfort.|1099|789|Coffee Brown|3,4,5,6,7,8|152|1861|35",
            "women_flats|Catwalk Kolhapuri Flats|Leather kolhapuris. Cushioned insole for all-day comfort.|2299|1599|Coffee Brown|3,4,5,6,7,8|203|712|61",
            "women_flats|Bata Strappy Flat Sandals|Gladiator straps. Cushioned insole for all-day comfort.|1099|769|Coffee Brown|3,4,5,6,7,8|217|1860|30",
            "women_flats|Carlton London Mules|Slip-on mules. Cushioned insole for all-day comfort.|3199|1199|Brown|3,4,5,6,7,8|132|2166|12",
            "women_flats|Inc.5 Bow Detail Flats|Satin bow. Cushioned insole for all-day comfort.|1399|1099|Coffee Brown|3,4,5,6,7,8|31|3999|29",
            // ---- women_casual_shoes
            "women_casual_shoes|Skechers White Sneakers|Minimal leather. Cushioned insole for all-day comfort.|4999|3099|White|3,4,5,6,7,8|222|3974|99",
            "women_casual_shoes|Puma Slip-On Canvas Shoes|Lightweight. Cushioned insole for all-day comfort.|2199|1499|Grey|3,4,5,6,7,8|15|1459|36",
            "women_casual_shoes|Adidas Loafers|Penny loafers. Cushioned insole for all-day comfort.|4999|3299|Brown|3,4,5,6,7,8|181|637|116",
            "women_casual_shoes|Bata Chunky Sneakers|Retro sole. Cushioned insole for all-day comfort.|3899|2599|Navy Blue|3,4,5,6,7,8|88|795|27",
            "women_casual_shoes|Red Tape Memory Foam Walking Shoes|All-day comfort. Cushioned insole for all-day comfort.|1699|839|Grey|3,4,5,6,7,8|202|3425|21",
            "women_casual_shoes|Roadster Espadrilles|Jute sole. Cushioned insole for all-day comfort.|5499|2399|White|3,4,5,6,7,8|66|2578|88",
            // ---- women_heels
            "women_heels|Metro Stiletto Pumps|Pointed toe pumps. Cushioned insole for all-day comfort.|3799|2499|Navy Blue|3,4,5,6,7,8|84|2111|115",
            "women_heels|Mochi Block Heel Mules|Square toe. Cushioned insole for all-day comfort.|6499|3299|Brown|3,4,5,6,7,8|232|2044|58",
            "women_heels|Inc.5 Kitten Heels|Low slingback. Cushioned insole for all-day comfort.|2299|1399|Black|3,4,5,6,7,8|247|3076|64",
            "women_heels|Catwalk Platform Heels|Party platforms. Cushioned insole for all-day comfort.|6599|2499|Tan|3,4,5,6,7,8|174|522|105",
            "women_heels|Carlton London Embellished Wedges|Ethnic wedges. Cushioned insole for all-day comfort.|8999|3299|White|3,4,5,6,7,8|93|724|21",
            "women_heels|Steve Madden Strappy Heeled Sandals|Barely-there straps. Cushioned insole for all-day comfort.|6699|2599|Tan|3,4,5,6,7,8|230|125|26",
            // ---- women_boots
            "women_boots|Mochi Ankle Chelsea Boots|Elastic gussets. Cushioned insole for all-day comfort.|2999|2299|Brown|3,4,5,6,7,8|130|4228|40",
            "women_boots|Metro Knee-High Boots|Block heel. Cushioned insole for all-day comfort.|6099|4699|White|3,4,5,6,7,8|189|2536|40",
            "women_boots|Bata Combat Boots|Lace-up chunky. Cushioned insole for all-day comfort.|4699|2499|Navy Blue|3,4,5,6,7,8|92|4536|113",
            "women_boots|Carlton London Suede Heeled Booties|Pointed toe. Cushioned insole for all-day comfort.|11699|4199|Coffee Brown|3,4,5,6,7,8|80|4357|45",
            "women_boots|Inc.5 Winter Snow Boots|Faux fur lined. Cushioned insole for all-day comfort.|6399|3099|Brown|3,4,5,6,7,8|58|211|53",
            "women_boots|Woodland Western Cowboy Boots|Stitch detailing. Cushioned insole for all-day comfort.|7099|4299|White|3,4,5,6,7,8|123|1155|53",
            // ---- women_sports_shoes_floaters
            "women_sports_shoes_floaters|Nike Running Shoes|Cushioned foam. Cushioned insole for all-day comfort.|6899|3199|Tan|3,4,5,6,7,8|111|3385|37",
            "women_sports_shoes_floaters|Adidas Training Shoes|Stable base. Cushioned insole for all-day comfort.|3999|2599|Brown|3,4,5,6,7,8|205|4080|35",
            "women_sports_shoes_floaters|Puma Walking Shoes|Memory foam. Cushioned insole for all-day comfort.|4799|2099|Coffee Brown|3,4,5,6,7,8|178|2889|93",
            "women_sports_shoes_floaters|Skechers Sports Floaters|Adjustable straps. Cushioned insole for all-day comfort.|3199|1999|Black|3,4,5,6,7,8|156|657|77",
            "women_sports_shoes_floaters|Asics Knit Slip-On Trainers|Sock-like fit. Cushioned insole for all-day comfort.|4699|2899|Brown|3,4,5,6,7,8|158|3698|8",
            "women_sports_shoes_floaters|Crocs Trail Shoes|Grippy outsole. Cushioned insole for all-day comfort.|3699|2699|Beige|3,4,5,6,7,8|121|1279|86",
            // ---- women_sports_active_wear
            "women_sports_active_wear|Nike High Support Sports Bra|Removable pads. Machine wash cold. Easy 7-day returns.|3599|1799|Maroon|XS,S,M,L,XL|23|4216|72",
            "women_sports_active_wear|Adidas High-Waist Yoga Leggings|Squat-proof. Machine wash cold. Easy 7-day returns.|1899|689|Mustard|XS,S,M,L,XL|115|954|22",
            "women_sports_active_wear|Puma Training Tank Top|Racerback. Machine wash cold. Easy 7-day returns.|1999|1599|Green|XS,S,M,L,XL|144|2612|115",
            "women_sports_active_wear|HRX Running Shorts|Inner brief. Machine wash cold. Easy 7-day returns.|2999|1799|Pink|XS,S,M,L,XL|102|3192|66",
            "women_sports_active_wear|Decathlon Domyos Seamless Workout Set|Bra and leggings. Machine wash cold. Easy 7-day returns.|3699|1399|Mustard|XS,S,M,L,XL|144|873|77",
            "women_sports_active_wear|Reebok Zip-Up Training Jacket|Lightweight. Machine wash cold. Easy 7-day returns.|5499|2499|Peach|XS,S,M,L,XL|75|3793|45",
            // ---- women_clothing
            "women_clothing|Puma Dri-Fit Training T-Shirt|Sweat-wicking. Machine wash cold. Easy 7-day returns.|1999|1499|Lavender|XS,S,M,L,XL|133|4213|39",
            "women_clothing|Adidas Flared Yoga Pants|Bootcut yoga. Machine wash cold. Easy 7-day returns.|919|519|Beige|XS,S,M,L,XL|146|3740|34",
            "women_clothing|Nike Joggers|Tapered fleece. Machine wash cold. Easy 7-day returns.|3399|1999|Beige|XS,S,M,L,XL|47|2442|97",
            "women_clothing|HRX Hoodie|Brushed fleece. Machine wash cold. Easy 7-day returns.|2399|1599|Pink|XS,S,M,L,XL|50|4719|45",
            "women_clothing|Decathlon Kalenji Cycling Shorts|Bike shorts. Machine wash cold. Easy 7-day returns.|2199|1599|Peach|XS,S,M,L,XL|65|3249|32",
            "women_clothing|Under Armour Track Jacket|Full zip. Machine wash cold. Easy 7-day returns.|1299|919|White|XS,S,M,L,XL|77|1030|76",
            // ---- women_sports_accessories
            "women_sports_accessories|Nike Yoga Mat 6mm|Anti-slip. Quality checked. Easy returns.|4299|1799|Charcoal|FREE|87|4631|58",
            "women_sports_accessories|Adidas Gym Bag|Wet pocket. Quality checked. Easy returns.|1599|759|Khaki|FREE|219|178|60",
            "women_sports_accessories|Puma Water Bottle 1L|Time marker. Quality checked. Easy returns.|1799|1099|Black|FREE|89|4010|117",
            "women_sports_accessories|Decathlon Domyos Hair Bands Pack|Sports headbands. Quality checked. Easy returns.|4499|1899|Mustard|FREE|23|2634|80",
            "women_sports_accessories|Boldfit Gym Gloves|Padded grip. Quality checked. Easy returns.|799|459|Silver|FREE|142|3299|25",
            "women_sports_accessories|Strauss Resistance Loop Bands|Set of 5. Quality checked. Easy returns.|2899|1099|Khaki|FREE|234|2026|98",
            // ---- women_sports_equipment
            "women_sports_equipment|Strauss Dumbbell Set 2x2kg|Neoprene coated. Quality checked. Easy returns.|5699|2699|Grey Melange|FREE|77|3198|8",
            "women_sports_equipment|Boldfit Skipping Rope|Adjustable. Quality checked. Easy returns.|3999|2599|Olive|FREE|234|2696|10",
            "women_sports_equipment|Decathlon Domyos Ab Roller Wheel|Knee pad included. Quality checked. Easy returns.|4099|2099|Black|FREE|56|390|0",
            "women_sports_equipment|Cosco Yoga Block and Strap Set|Eco foam. Quality checked. Easy returns.|2799|1299|Khaki|FREE|134|63|23",
            "women_sports_equipment|Nivia Kettlebell 6kg|Vinyl coated. Quality checked. Easy returns.|3799|2999|Grey Melange|FREE|62|3666|65",
            "women_sports_equipment|Kore Badminton Racket Set|Two rackets with shuttles. Quality checked. Easy returns.|1899|1099|Charcoal|FREE|248|1001|92",
            // ---- women_maternity
            "women_maternity|Momsoon Maternity Feeding Kurti|Hidden zip nursing access. Machine wash cold. Easy 7-day returns.|1899|819|Beige|XS,S,M,L,XL|104|3962|17",
            "women_maternity|Mine4Nine Maternity Dress|Stretch empire waist. Machine wash cold. Easy 7-day returns.|2399|1399|White|XS,S,M,L,XL|60|72|68",
            "women_maternity|Morph Maternity Jeans|Over-belly stretch panel. Machine wash cold. Easy 7-day returns.|1699|889|Green|XS,S,M,L,XL|181|978|103",
            "women_maternity|H&M Mama Nursing Night Suit|Easy feeding. Machine wash cold. Easy 7-day returns.|3199|2199|Beige|XS,S,M,L,XL|143|1017|112",
            "women_maternity|Clovia Maternity Leggings|Adjustable waistband. Machine wash cold. Easy 7-day returns.|2899|1599|Rust|XS,S,M,L,XL|197|1249|74",
            "women_maternity|Zivame Maternity Co-ord Set|Comfort lounge set. Machine wash cold. Easy 7-day returns.|3599|2499|Lavender|XS,S,M,L,XL|20|118|91",
            // ---- women_sunglasses_frames
            "women_sunglasses_frames|Ray-Ban Cat-Eye Sunglasses|UV400 protection. Quality checked. Easy returns.|12799|5599|Maroon|FREE|97|3536|14",
            "women_sunglasses_frames|Fastrack Oversized Square Sunglasses|Gradient lenses. Quality checked. Easy returns.|2799|1499|Silver|FREE|158|3687|102",
            "women_sunglasses_frames|Vincent Chase Round Metal Sunglasses|Retro gold frame. Quality checked. Easy returns.|1399|819|Brown|FREE|17|2504|66",
            "women_sunglasses_frames|IDEE Aviator Sunglasses|Polarised. Quality checked. Easy returns.|9299|6199|White|FREE|22|3555|29",
            "women_sunglasses_frames|Carlton London Butterfly Sunglasses|Statement frame. Quality checked. Easy returns.|16199|6299|Gold|FREE|158|28|49",
            "women_sunglasses_frames|Lenskart Air Transparent Eyeglass Frames|Blue-cut lens ready. Quality checked. Easy returns.|4999|2499|Olive|FREE|24|2918|11",
            // ---- women_beauty_personal_care
            "women_beauty_personal_care|Nykaa Hair Straightener|Ceramic plates, quick heat. Quality checked. Easy returns.|519|359|Charcoal|FREE|50|3373|120",
            "women_beauty_personal_care|Lakme Body Lotion 400ml|Deep moisture. Quality checked. Easy returns.|1299|799|Charcoal|FREE|234|3426|10",
            "women_beauty_personal_care|Mamaearth Bath and Body Gift Set|Shower gel, scrub, lotion. Quality checked. Easy returns.|3499|1299|Green|FREE|189|4733|32",
            "women_beauty_personal_care|The Body Shop Hair Dryer 1200W|Cool shot. Quality checked. Easy returns.|2499|1599|Teal|FREE|143|2631|46",
            "women_beauty_personal_care|Dove Nail Care Kit|Manicure set. Quality checked. Easy returns.|2899|1999|Navy Blue|FREE|112|994|73",
            "women_beauty_personal_care|Philips Onion Hair Oil 250ml|Reduces hair fall. Quality checked. Easy returns.|979|369|Gold|FREE|135|4651|63",
            // ---- women_makeup
            "women_makeup|Maybelline Fit Me Matte Foundation|Natural matte finish. Quality checked. Easy returns.|1599|1199|Khaki|FREE|186|2045|73",
            "women_makeup|Lakme Kajal Eyeliner|Smudge proof. Quality checked. Easy returns.|1399|669|Gold|FREE|167|2502|119",
            "women_makeup|Nykaa Eyeshadow Palette 12 Shades|Matte and shimmer. Quality checked. Easy returns.|1099|759|Beige|FREE|220|3405|1",
            "women_makeup|SUGAR Compact Powder|Oil control. Quality checked. Easy returns.|899|579|Brown|FREE|172|485|93",
            "women_makeup|M.A.C Lash Sensational Mascara|Volumising. Quality checked. Easy returns.|4399|1699|Teal|FREE|37|3318|41",
            "women_makeup|Swiss Beauty Makeup Brush Set of 10|Soft synthetic. Quality checked. Easy returns.|1699|1299|Gold|FREE|216|3508|0",
            // ---- women_skincare
            "women_skincare|Minimalist 10% Niacinamide Serum|Blemish control. Quality checked. Easy returns.|2899|1399|Olive|FREE|152|2325|113",
            "women_skincare|Cetaphil Gentle Skin Cleanser|For all skin types. Quality checked. Easy returns.|1499|679|Grey Melange|FREE|17|1817|62",
            "women_skincare|The Derma Co Sunscreen SPF 50|No white cast. Quality checked. Easy returns.|809|299|Beige|FREE|224|459|49",
            "women_skincare|Plum Hyaluronic Acid Moisturiser|Hydrating. Quality checked. Easy returns.|839|469|Teal|FREE|223|1604|118",
            "women_skincare|Mamaearth Vitamin C Face Wash|Brightening. Quality checked. Easy returns.|799|479|Khaki|FREE|155|1747|89",
            "women_skincare|Neutrogena Sheet Mask Pack of 5|Glow boost. Quality checked. Easy returns.|1499|689|Khaki|FREE|32|3776|118",
            // ---- women_premium_beauty
            "women_premium_beauty|Estee Lauder Advanced Night Repair Serum|Overnight recovery. Quality checked. Easy returns.|21799|8900|Maroon|FREE|93|1515|117",
            "women_premium_beauty|Clinique Moisture Surge Gel Cream|72h hydration. Quality checked. Easy returns.|5099|3500|Navy Blue|FREE|32|4552|85",
            "women_premium_beauty|M.A.C Studio Fix Foundation|Full coverage. Quality checked. Easy returns.|9799|3900|Mustard|FREE|174|1802|0",
            "women_premium_beauty|Kiehl's Ultra Facial Cream|Lightweight daily. Quality checked. Easy returns.|3899|2900|Beige|FREE|19|483|120",
            "women_premium_beauty|Charlotte Tilbury Pillow Talk Lipstick|Iconic nude. Quality checked. Easy returns.|6499|3200|Navy Blue|FREE|230|3354|33",
            "women_premium_beauty|Huda Beauty Eyeshadow Palette|Highly pigmented. Quality checked. Easy returns.|5899|2299|Maroon|FREE|229|4499|114",
            // ---- women_lipsticks
            "women_lipsticks|Maybelline SuperStay Matte Ink Liquid Lipstick|16h wear. Quality checked. Easy returns.|2599|1699|Navy Blue|FREE|186|4782|96",
            "women_lipsticks|Lakme 9to5 Primer Matte Lipstick|Smooth matte. Quality checked. Easy returns.|4499|1999|Maroon|FREE|52|3546|19",
            "women_lipsticks|SUGAR Matte As Hell Crayon Lipstick|Long lasting. Quality checked. Easy returns.|1099|679|Brown|FREE|119|506|77",
            "women_lipsticks|M.A.C Retro Matte Lipstick|Classic red. Quality checked. Easy returns.|3899|1999|Teal|FREE|26|1699|66",
            "women_lipsticks|Nykaa Lip Gloss Set of 3|High shine. Quality checked. Easy returns.|1599|1199|Beige|FREE|61|534|115",
            "women_lipsticks|Kay Beauty Tinted Lip Balm|SPF 15. Quality checked. Easy returns.|3399|1699|Gold|FREE|36|135|32",
            // ---- women_fragrances
            "women_fragrances|Bella Vita Eau de Parfum 100ml|Floral fruity. Quality checked. Easy returns.|5399|4099|Olive|FREE|175|2699|15",
            "women_fragrances|Skinn by Titan Body Mist 250ml|Vanilla bliss. Quality checked. Easy returns.|6999|4699|Gold|FREE|249|1551|27",
            "women_fragrances|Fogg Luxury Perfume Gift Set|4 x 20ml. Quality checked. Easy returns.|649|439|Brown|FREE|235|2255|29",
            "women_fragrances|Victoria's Secret Rose Eau de Toilette 50ml|Fresh rose. Quality checked. Easy returns.|6199|3999|Grey Melange|FREE|56|982|56",
            "women_fragrances|Engage Roll-On Perfume Oil|Alcohol free. Quality checked. Easy returns.|7199|3499|Olive|FREE|54|1781|44",
            "women_fragrances|Plum Pocket Perfume Pack of 3|Travel size. Quality checked. Easy returns.|3799|1899|Charcoal|FREE|109|3083|115",
            // ---- women_gadgets
            "women_gadgets|Philips Hair Straightener Brush|Ionic, 30 sec heat-up. 1 year manufacturer warranty.|2099|1399|Gold|FREE|219|2183|39",
            "women_gadgets|Dyson Facial Cleansing Brush|Waterproof, rechargeable. 1 year manufacturer warranty.|8799|6099|Silver|FREE|164|61|102",
            "women_gadgets|Nova Bikini Trimmer|Cordless. 1 year manufacturer warranty.|4099|3399|Black|FREE|186|3991|9",
            "women_gadgets|Noise Hair Curler|Ceramic barrel. 1 year manufacturer warranty.|8199|6899|Black|FREE|46|2196|8",
            "women_gadgets|boAt Smart Scale|Body composition. 1 year manufacturer warranty.|5399|4799|White|FREE|218|3118|2",
            "women_gadgets|Agaro Electric Massager|Neck and shoulder. 1 year manufacturer warranty.|11199|7999|White|FREE|117|1821|75",
            // ---- women_smart_wearables
            "women_smart_wearables|Apple Watch SE GPS 40mm|Crash detection. 1 year manufacturer warranty.|42199|29900|White|FREE|244|4150|118",
            "women_smart_wearables|Samsung Galaxy Watch6 40mm|Sleep coaching. 1 year manufacturer warranty.|32699|29999|Green|FREE|39|2435|15",
            "women_smart_wearables|Noise ColorFit Pristine Smartwatch|Metal strap, AMOLED. 1 year manufacturer warranty.|4099|3499|Gold|FREE|248|2991|120",
            "women_smart_wearables|boAt Lunar Pro Smartwatch|Round AMOLED. 1 year manufacturer warranty.|4299|2999|Gold|FREE|55|2337|15",
            "women_smart_wearables|Fire-Boltt Emerald Smartwatch|Jewel-style strap. 1 year manufacturer warranty.|2999|2499|White|FREE|45|4104|26",
            "women_smart_wearables|Fastrack Reflex Play Smartwatch|Fitness tracking. 1 year manufacturer warranty.|4099|2999|Green|FREE|248|3980|26",
            // ---- women_fitness_gadgets
            "women_fitness_gadgets|Fitbit Inspire 3 Fitness Tracker|Heart rate, sleep. 1 year manufacturer warranty.|9799|8999|Grey|FREE|84|2651|107",
            "women_fitness_gadgets|Xiaomi Smart Band 8|16-day battery. 1 year manufacturer warranty.|4299|3499|Blue|FREE|214|2444|32",
            "women_fitness_gadgets|Noise Smart Body Fat Scale|App sync. 1 year manufacturer warranty.|5599|4999|Silver|FREE|132|3336|107",
            "women_fitness_gadgets|Omron Digital Skipping Rope|Counter. 1 year manufacturer warranty.|3399|2299|Green|FREE|42|2358|35",
            "women_fitness_gadgets|Dr Trust Fitness Band with SpO2|Stress tracking. 1 year manufacturer warranty.|11499|7899|White|FREE|153|3052|73",
            "women_fitness_gadgets|HealthSense Massage Gun|Deep tissue. 1 year manufacturer warranty.|9299|6499|Silver|FREE|73|3786|38",
            // ---- women_headphones
            "women_headphones|Apple AirPods (3rd Gen)|Spatial audio. 1 year manufacturer warranty.|22199|19900|Silver|FREE|115|1312|108",
            "women_headphones|Sony WH-CH720N Headphones|Lightweight ANC. 1 year manufacturer warranty.|13799|11990|Blue|FREE|181|3574|33",
            "women_headphones|JBL Tune 520BT Headphones|57h battery. 1 year manufacturer warranty.|4799|3999|Grey|FREE|39|1210|84",
            "women_headphones|boAt Rockerz 550 Headphones|Soft cushions. 1 year manufacturer warranty.|2199|1799|Blue|FREE|150|2730|112",
            "women_headphones|Marshall Major IV Headphones|80h playtime. 1 year manufacturer warranty.|17399|12999|Green|FREE|189|2881|76",
            "women_headphones|Skullcandy Dime 3 TWS Earbuds|Compact. 1 year manufacturer warranty.|1799|1499|Black|FREE|134|2300|106",
            // ---- women_speakers
            "women_speakers|JBL Go 3 Portable Speaker|IP67. 1 year manufacturer warranty.|4399|3999|Silver|FREE|106|2882|35",
            "women_speakers|Sony SRS-XB100 Speaker|Strap included. 1 year manufacturer warranty.|7299|4990|White|FREE|201|2126|104",
            "women_speakers|boAt Stone 350 Speaker|10W RGB. 1 year manufacturer warranty.|1899|1499|Green|FREE|188|2792|24",
            "women_speakers|Marshall Willen Speaker|Compact Marshall. 1 year manufacturer warranty.|8799|7999|Green|FREE|209|4507|78",
            "women_speakers|Ultimate Ears WONDERBOOM 3|Floats in water. 1 year manufacturer warranty.|10499|8999|White|FREE|190|2273|86",
            "women_speakers|Mivi Roam Speaker|Rugged mini. 1 year manufacturer warranty.|1799|1499|Green|FREE|195|2130|99",
            // ---- women_fashion_jewellery
            "women_fashion_jewellery|Zaveri Pearls Kundan Necklace Set|Gold-plated with earrings. Quality checked. Easy returns.|2099|1499|Olive|FREE|20|4689|101",
            "women_fashion_jewellery|Accessorize Oxidised Silver Jhumkas|Boho style. Quality checked. Easy returns.|3899|1699|Khaki|FREE|50|2291|110",
            "women_fashion_jewellery|Sukkhi Layered Chain Necklace|Minimal gold tone. Quality checked. Easy returns.|1199|459|Khaki|FREE|222|2328|4",
            "women_fashion_jewellery|Rubans Bangle Set of 6|Stone studded. Quality checked. Easy returns.|1799|979|Beige|FREE|103|4115|21",
            "women_fashion_jewellery|Giva Choker Set|Pearl and stone. Quality checked. Easy returns.|2199|1499|Grey Melange|FREE|45|3812|105",
            "women_fashion_jewellery|Priyaasi Statement Ring|Adjustable. Quality checked. Easy returns.|1599|559|Mustard|FREE|42|4412|49",
            // ---- women_fine_jewellery
            "women_fine_jewellery|Tanishq 18K Gold Diamond Pendant|BIS hallmarked. Quality checked. Easy returns.|42199|30799|Grey Melange|FREE|99|1386|26",
            "women_fine_jewellery|CaratLane 925 Silver Anklet Pair|Sterling silver. Quality checked. Easy returns.|70199|37899|Navy Blue|FREE|42|3547|58",
            "women_fine_jewellery|Giva Diamond Stud Earrings|IGI certified. Quality checked. Easy returns.|53299|19699|Olive|FREE|61|2906|104",
            "women_fine_jewellery|Melorra 22K Gold Bangle|Traditional. Quality checked. Easy returns.|20799|13899|Silver|FREE|130|2987|14",
            "women_fine_jewellery|PC Jeweller Platinum Band Ring|Minimal. Quality checked. Easy returns.|80399|48199|Maroon|FREE|197|3875|66",
            "women_fine_jewellery|BlueStone Sapphire Ring in Silver|Lab-created stone. Quality checked. Easy returns.|55999|34699|Charcoal|FREE|181|3118|27",
            // ---- women_earrings
            "women_earrings|Giva Gold-Plated Hoop Earrings|Lightweight. Quality checked. Easy returns.|869|629|Maroon|FREE|245|2879|27",
            "women_earrings|Zaveri Pearls Pearl Drop Earrings|Classic pearl. Quality checked. Easy returns.|3199|1899|Beige|FREE|43|2881|59",
            "women_earrings|Accessorize Silver Stud Earrings|925 silver. Quality checked. Easy returns.|3299|2499|Mustard|FREE|216|3431|110",
            "women_earrings|Sukkhi Chandbali Earrings|Festive kundan. Quality checked. Easy returns.|519|319|Mustard|FREE|146|4671|8",
            "women_earrings|Rubans Tassel Earrings|Colourful. Quality checked. Easy returns.|3399|2199|Brown|FREE|79|711|64",
            "women_earrings|Priyaasi Ear Cuff Set|No piercing needed. Quality checked. Easy returns.|3599|2699|Beige|FREE|137|3473|105",
            // ---- women_handbags_bags_wallets
            "women_handbags_bags_wallets|Lavie Structured Tote Bag|Laptop sleeve. Quality checked. Easy returns.|3599|2399|Brown|FREE|142|2113|98",
            "women_handbags_bags_wallets|Caprese Sling Bag|Adjustable strap. Quality checked. Easy returns.|7499|3799|Mustard|FREE|198|2911|43",
            "women_handbags_bags_wallets|Baggit Clutch|Embellished party clutch. Quality checked. Easy returns.|8199|4799|Mustard|FREE|232|2312|67",
            "women_handbags_bags_wallets|Hidesign Zip-Around Wallet|Card slots. Quality checked. Easy returns.|5499|3399|Beige|FREE|63|2660|94",
            "women_handbags_bags_wallets|Accessorize Shoulder Bag|Vegan leather. Quality checked. Easy returns.|1199|929|Charcoal|FREE|208|2210|33",
            "women_handbags_bags_wallets|Lino Perros Mini Backpack|Everyday. Quality checked. Easy returns.|4199|2599|Grey Melange|FREE|178|2304|2",
            // ---- women_luggages_trolleys
            "women_luggages_trolleys|American Tourister Cabin Trolley 55 cm|Hard shell, 4 wheels. Quality checked. Easy returns.|16099|8999|Green|FREE|113|3916|83",
            "women_luggages_trolleys|Safari Check-in Trolley 68 cm|TSA lock. Quality checked. Easy returns.|9499|7399|Navy Blue|FREE|214|147|41",
            "women_luggages_trolleys|VIP Trolley Set of 3|Cabin, medium, large. Quality checked. Easy returns.|11599|8999|Maroon|FREE|174|2877|27",
            "women_luggages_trolleys|Skybags Soft Duffle Trolley|Expandable. Quality checked. Easy returns.|8399|4399|Charcoal|FREE|88|1174|119",
            "women_luggages_trolleys|Aristocrat Vanity Case|Makeup organiser. Quality checked. Easy returns.|4199|3099|Charcoal|FREE|200|644|30",
            "women_luggages_trolleys|Mokobara Weekender Bag|Travel duffel. Quality checked. Easy returns.|20399|11599|Olive|FREE|54|4769|53",
            // ---- bed_runners
            "bed_runners|Swayam Silk Blend Bed Runner with Cushion Covers|Set of 3 for king bed. Easy care. Great for gifting.|1299|899|Grey|FREE|157|269|28",
            "bed_runners|Story@Home Jacquard Bed Runner|Woven damask pattern, 180 x 50 cm. Easy care. Great for gifting.|1499|1099|Mustard|FREE|106|889|42",
            "bed_runners|Home Centre Block Print Cotton Bed Runner|Hand block printed. Easy care. Great for gifting.|929|639|Beige|FREE|153|4312|23",
            "bed_runners|Spaces Velvet Bed Runner|Plush velvet with tassels. Easy care. Great for gifting.|2099|1399|Grey|FREE|229|3169|117",
            "bed_runners|Dhrohar Embroidered Bed Runner|Thread embroidery border. Easy care. Great for gifting.|679|489|Teal|FREE|203|4122|53",
            "bed_runners|Raymond Home Ikat Woven Bed Runner|Handloom ikat. Easy care. Great for gifting.|999|569|Grey|FREE|113|2736|114",
            // ---- mattress_protectors
            "mattress_protectors|Sleepwell Waterproof Mattress Protector Queen|Breathable TPU layer, fitted. Easy care. Great for gifting.|4399|1999|Brown|FREE|148|334|91",
            "mattress_protectors|Wakefit Terry Cotton Mattress Protector King|Soft terry surface. Easy care. Great for gifting.|1599|1199|Beige|FREE|74|875|50",
            "mattress_protectors|Kurlon Quilted Mattress Protector|Extra padding. Easy care. Great for gifting.|1999|729|Multi|FREE|67|3762|94",
            "mattress_protectors|Spaces Anti-Bacterial Mattress Protector|Dust-mite resistant. Easy care. Great for gifting.|1599|1199|Teal|FREE|148|587|20",
            "mattress_protectors|Story@Home Bamboo Mattress Protector|Cooling bamboo fabric. Easy care. Great for gifting.|1799|1199|Cream|FREE|129|4490|61",
            "mattress_protectors|Trance Home Linen Single Bed Mattress Protector|Elastic skirt fit. Easy care. Great for gifting.|1099|599|Cream|FREE|224|4163|59",
            // ---- bedsheets
            "bedsheets|Bombay Dyeing Cotton Double Bedsheet with 2 Pillow Covers|180 TC, floral print. Easy care. Great for gifting.|1999|759|Multi|FREE|90|4701|112",
            "bedsheets|Spaces King Size Fitted Bedsheet|Elastic all around. Easy care. Great for gifting.|779|519|Green|FREE|30|3417|97",
            "bedsheets|D'Decor Satin Stripe Bedsheet Set|300 TC sateen. Easy care. Great for gifting.|2599|1799|Mustard|FREE|246|1890|45",
            "bedsheets|Raymond Home Jaipuri Block Print Bedsheet|Hand-block print. Easy care. Great for gifting.|1999|1299|Maroon|FREE|116|4542|18",
            "bedsheets|Story@Home Microfibre Solid Bedsheet|Wrinkle resistant. Easy care. Great for gifting.|3699|2499|Beige|FREE|190|4174|57",
            "bedsheets|Portico Geometric Print Bedsheet|Modern geometric design. Easy care. Great for gifting.|5699|2199|Beige|FREE|69|3174|1",
            // ---- bedding_sets
            "bedding_sets|Bombay Dyeing 5-Piece Bedding Set|Bedsheet, comforter and pillow covers. Easy care. Great for gifting.|7199|2699|Brown|FREE|94|1911|15",
            "bedding_sets|Spaces Duvet Cover Set King|Cotton duvet with zip. Easy care. Great for gifting.|3499|2199|Grey|FREE|213|1699|112",
            "bedding_sets|D'Decor Embroidered Bedding Set|Thread work. Easy care. Great for gifting.|12699|5199|Multi|FREE|143|1514|96",
            "bedding_sets|Portico Kids Cartoon Bedding Set|Soft and colourful. Easy care. Great for gifting.|10599|5999|Blue|FREE|199|3741|100",
            "bedding_sets|Raymond Home Satin Bedding Set|Luxurious sheen. Easy care. Great for gifting.|2699|2099|Mustard|FREE|155|3896|81",
            "bedding_sets|Home Centre Patchwork Bedding Set|Quilted patchwork. Easy care. Great for gifting.|3099|2099|Brown|FREE|131|1555|117",
            // ---- blankets_quilts_dohars
            "blankets_quilts_dohars|Jaipuri Haat Jaipuri Razai Double Bed|Hand-filled cotton. Easy care. Great for gifting.|8599|4199|Beige|FREE|125|1446|8",
            "blankets_quilts_dohars|Spaces Mink Blanket Double|Super soft mink. Easy care. Great for gifting.|7499|3499|Multi|FREE|240|3674|120",
            "blankets_quilts_dohars|Raymond Home Cotton Dohar Single|Lightweight AC blanket. Easy care. Great for gifting.|2899|1799|Blue|FREE|177|4582|75",
            "blankets_quilts_dohars|Bombay Dyeing Microfibre Comforter|All-season comforter. Easy care. Great for gifting.|5299|1999|Beige|FREE|93|2855|85",
            "blankets_quilts_dohars|Story@Home Fleece Throw Blanket|Cosy throw. Easy care. Great for gifting.|4399|3099|Brown|FREE|195|3990|15",
            "blankets_quilts_dohars|Home Centre Reversible Quilt King|Two looks in one. Easy care. Great for gifting.|5999|3499|Green|FREE|62|387|77",
            // ---- pillows_pillow_covers
            "pillows_pillow_covers|Wakefit Microfibre Pillow Set of 2|Medium soft. Easy care. Great for gifting.|2399|839|Teal|FREE|190|1819|17",
            "pillows_pillow_covers|Sleepwell Memory Foam Pillow|Contour neck support. Easy care. Great for gifting.|1199|879|Mustard|FREE|227|2941|22",
            "pillows_pillow_covers|Spaces Cotton Pillow Covers Set of 4|Zip closure. Easy care. Great for gifting.|4299|1499|Brown|FREE|81|758|89",
            "pillows_pillow_covers|Story@Home Satin Pillow Covers|Hair friendly. Easy care. Great for gifting.|1499|959|Cream|FREE|63|2537|77",
            "pillows_pillow_covers|The Sleep Company Orthopedic Cervical Pillow|Neck pain relief. Easy care. Great for gifting.|1599|1199|Blue|FREE|113|418|71",
            "pillows_pillow_covers|Home Centre Body Pillow|Long full body support. Easy care. Great for gifting.|2299|1199|Teal|FREE|43|1752|27",
            // ---- bed_covers
            "bed_covers|Spaces Quilted Bed Cover King|Reversible quilt. Easy care. Great for gifting.|4199|1999|White|FREE|75|3958|65",
            "bed_covers|D'Decor Jacquard Bed Cover with Pillow Covers|Woven design. Easy care. Great for gifting.|5199|1999|Blue|FREE|24|524|63",
            "bed_covers|Story@Home Kantha Bed Cover|Hand kantha stitch. Easy care. Great for gifting.|2899|2299|White|FREE|68|2813|102",
            "bed_covers|Rajasthan Decor Velvet Bed Cover|Rich velvet. Easy care. Great for gifting.|6699|2599|Grey|FREE|213|2115|29",
            "bed_covers|Home Centre Crochet Bed Cover|Handmade crochet. Easy care. Great for gifting.|6599|2499|Green|FREE|193|4143|76",
            "bed_covers|Raymond Home Patchwork Bed Cover|Colourful patchwork. Easy care. Great for gifting.|2699|1199|Mustard|FREE|104|1536|69",
            // ---- diwan_sets
            "diwan_sets|Swayam 6-Piece Diwan Set|Sheet, bolsters and cushion covers. Easy care. Great for gifting.|4699|2299|Brown|FREE|245|4602|103",
            "diwan_sets|Story@Home Jaipuri Diwan Set|Block print. Easy care. Great for gifting.|3899|2999|Blue|FREE|117|2025|108",
            "diwan_sets|Rajasthan Decor Velvet Diwan Set|Rich velvet. Easy care. Great for gifting.|3999|2799|Multi|FREE|187|4504|48",
            "diwan_sets|Home Centre Silk Blend Diwan Set|Festive sheen. Easy care. Great for gifting.|4299|2999|Beige|FREE|227|3898|26",
            "diwan_sets|Dhrohar Embroidered Diwan Set|Mirror work. Easy care. Great for gifting.|6699|2399|Brown|FREE|201|154|9",
            "diwan_sets|Spaces Cotton Diwan Set|Breathable cotton. Easy care. Great for gifting.|3299|1499|Teal|FREE|73|4731|4",
            // ---- chair_pads_covers
            "chair_pads_covers|Home Centre Chair Pads Set of 4|Tie-on cushions. Easy care. Great for gifting.|1999|1299|Beige|FREE|185|4753|24",
            "chair_pads_covers|Story@Home Stretch Dining Chair Covers Set of 6|Spandex fit. Easy care. Great for gifting.|2599|1199|Maroon|FREE|214|1077|61",
            "chair_pads_covers|Spaces Office Chair Cushion|Memory foam. Easy care. Great for gifting.|639|419|Maroon|FREE|221|4406|22",
            "chair_pads_covers|Lushomes Floor Seating Cushion|Round pouf cushion. Easy care. Great for gifting.|659|449|Mustard|FREE|140|3656|109",
            "chair_pads_covers|Kuber Industries Printed Chair Pads|Boho print. Easy care. Great for gifting.|799|639|Brown|FREE|217|3792|6",
            "chair_pads_covers|HOKIPO Velvet Chair Covers|Elegant velvet. Easy care. Great for gifting.|2899|1399|Beige|FREE|18|3549|63",
            // ---- sofa_covers
            "sofa_covers|Home Centre Stretchable 3 Seater Sofa Cover|Elastic fit. Easy care. Great for gifting.|2699|1599|Teal|FREE|35|4790|4",
            "sofa_covers|Story@Home 5 Seater Sofa Cover Set|Polycotton. Easy care. Great for gifting.|3499|2599|Cream|FREE|77|873|103",
            "sofa_covers|Spaces L-Shape Sofa Cover|Corner sofa fit. Easy care. Great for gifting.|3899|1399|Beige|FREE|61|2861|107",
            "sofa_covers|Kuber Industries Quilted Sofa Throw|Reversible throw. Easy care. Great for gifting.|5299|2999|Cream|FREE|104|1330|94",
            "sofa_covers|HOKIPO Velvet Sofa Cover|Plush velvet. Easy care. Great for gifting.|1099|819|Multi|FREE|210|1499|31",
            "sofa_covers|Lushomes Waterproof Sofa Protector|Pet friendly. Easy care. Great for gifting.|2699|2099|Beige|FREE|223|4456|54",
            // ---- floor_runners
            "floor_runners|Saral Home Anti-Skid Floor Runner 2x6 ft|Kitchen and corridor. Easy care. Great for gifting.|1199|759|Cream|FREE|184|125|67",
            "floor_runners|Story@Home Jute Floor Runner|Natural jute. Easy care. Great for gifting.|3199|2499|Mustard|FREE|67|480|25",
            "floor_runners|Status Printed Kitchen Runner|Washable. Easy care. Great for gifting.|1599|819|Multi|FREE|56|3582|45",
            "floor_runners|Home Centre Shaggy Floor Runner|Soft pile. Easy care. Great for gifting.|4699|1899|Grey|FREE|188|2153|113",
            "floor_runners|Obsessions Cotton Dhurrie Runner|Handwoven. Easy care. Great for gifting.|3299|2099|Cream|FREE|95|4711|106",
            "floor_runners|Swayam Geometric Floor Runner|Modern print. Easy care. Great for gifting.|1499|959|Brown|FREE|97|3168|107",
            // ---- carpets
            "carpets|Obsessions Hand-Tufted Wool Carpet 5x7 ft|Rich wool pile. Easy care. Great for gifting.|20599|7599|Blue|FREE|88|540|112",
            "carpets|Home Centre Shaggy Carpet 4x6 ft|Ultra soft. Easy care. Great for gifting.|15599|7599|Beige|FREE|43|1310|55",
            "carpets|Status Persian Design Carpet|Traditional motifs. Easy care. Great for gifting.|10999|8099|Brown|FREE|170|700|50",
            "carpets|Saral Home Abstract Modern Rug|Contemporary art. Easy care. Great for gifting.|4699|2599|Teal|FREE|73|2486|105",
            "carpets|Story@Home Jute Braided Rug|Eco-friendly. Easy care. Great for gifting.|11099|5299|Brown|FREE|76|2266|51",
            "carpets|Jaipur Rugs Kilim Flatweave Carpet|Reversible. Easy care. Great for gifting.|11099|4099|Maroon|FREE|69|319|17",
            // ---- floor_mats_dhurries
            "floor_mats_dhurries|Saral Home Cotton Dhurrie 4x6 ft|Handwoven striped dhurrie. Easy care. Great for gifting.|3999|1699|Cream|FREE|120|3541|107",
            "floor_mats_dhurries|Story@Home Jute Floor Mat|Natural braided jute. Easy care. Great for gifting.|6099|2899|Blue|FREE|156|3946|58",
            "floor_mats_dhurries|Swayam Handwoven Geometric Rug|Recycled cotton. Easy care. Great for gifting.|4899|1899|Cream|FREE|47|3142|111",
            "floor_mats_dhurries|Obsessions Chindi Rag Rug|Colourful upcycled. Easy care. Great for gifting.|2899|2099|Multi|FREE|122|982|39",
            "floor_mats_dhurries|Jaipur Rugs Kilim Dhurrie|Tribal pattern. Easy care. Great for gifting.|6699|2999|Beige|FREE|152|3486|89",
            "floor_mats_dhurries|Status Anti-Skid Kitchen Mat|Washable. Easy care. Great for gifting.|4399|1699|Teal|FREE|161|870|4",
            // ---- door_mats
            "door_mats|Status Coir Welcome Door Mat|Natural coir. Easy care. Great for gifting.|909|569|Mustard|FREE|121|2076|101",
            "door_mats|Saral Home Anti-Skid Microfibre Door Mat|Super absorbent. Easy care. Great for gifting.|1699|809|Brown|FREE|211|3811|39",
            "door_mats|Kuber Industries Rubber Outdoor Door Mat|Heavy duty. Easy care. Great for gifting.|1199|579|Brown|FREE|229|1745|51",
            "door_mats|Story@Home Printed Door Mat Set of 3|Fun prints. Easy care. Great for gifting.|1199|639|Beige|FREE|205|2378|59",
            "door_mats|Home Centre Shaggy Bathroom Door Mat|Soft pile. Easy care. Great for gifting.|1999|879|White|FREE|219|3511|88",
            "door_mats|Obsessions Jute Door Mat|Eco friendly. Easy care. Great for gifting.|1499|809|Mustard|FREE|125|3809|25",
            // ---- bath_towels
            "bath_towels|Bombay Dyeing Cotton Bath Towel 500 GSM|Super absorbent. Easy care. Great for gifting.|1399|879|Maroon|FREE|197|2691|85",
            "bath_towels|Spaces Bamboo Bath Towel|Soft and odour resistant. Easy care. Great for gifting.|1999|1099|White|FREE|57|2011|83",
            "bath_towels|Trident Turkish Bath Towel|Lightweight, quick dry. Easy care. Great for gifting.|879|369|Teal|FREE|167|3814|100",
            "bath_towels|Story@Home Zero Twist Bath Towel|Plush loops. Easy care. Great for gifting.|1599|1199|Teal|FREE|169|2810|12",
            "bath_towels|Welspun Striped Bath Towel|Classic stripes. Easy care. Great for gifting.|1299|549|Brown|FREE|208|1170|101",
            "bath_towels|Raymond Home Microfibre Bath Towel|Compact and fast drying. Easy care. Great for gifting.|3799|1499|Mustard|FREE|17|3501|100",
            // ---- hand_face_towels
            "hand_face_towels|Bombay Dyeing Hand Towels Set of 4|Cotton terry. Easy care. Great for gifting.|519|319|Beige|FREE|152|3335|66",
            "hand_face_towels|Spaces Face Towels Set of 6|Soft loops. Easy care. Great for gifting.|419|309|White|FREE|123|2870|100",
            "hand_face_towels|Trident Bamboo Face Towels|Gentle on skin. Easy care. Great for gifting.|889|609|Grey|FREE|117|1227|8",
            "hand_face_towels|Welspun Embroidered Hand Towels|Gift ready. Easy care. Great for gifting.|959|609|Green|FREE|235|103|97",
            "hand_face_towels|Story@Home Quick Dry Face Towels|Microfibre. Easy care. Great for gifting.|1399|699|Maroon|FREE|185|1756|112",
            "hand_face_towels|Home Centre Waffle Hand Towels|Textured waffle. Easy care. Great for gifting.|609|249|Green|FREE|121|226|28",
            // ---- beach_towels
            "beach_towels|Spaces Printed Beach Towel|Large 90x180 cm. Easy care. Great for gifting.|1399|769|Green|FREE|167|3575|35",
            "beach_towels|Story@Home Turkish Peshtemal|Lightweight, tassels. Easy care. Great for gifting.|3299|1399|Blue|FREE|62|1133|61",
            "beach_towels|Trident Microfibre Beach Towel|Sand free. Easy care. Great for gifting.|1599|889|Beige|FREE|222|2990|120",
            "beach_towels|Decathlon Nabaiji Round Beach Towel|Mandala print. Easy care. Great for gifting.|2799|979|Teal|FREE|126|2145|23",
            "beach_towels|Home Centre Striped Beach Towel|Cabana stripes. Easy care. Great for gifting.|869|529|Multi|FREE|177|1115|43",
            "beach_towels|Welspun Kids Hooded Beach Towel|Animal hood. Easy care. Great for gifting.|1199|669|Multi|FREE|181|4166|12",
            // ---- towels_set
            "towels_set|Bombay Dyeing 6-Piece Towel Set|2 bath, 2 hand, 2 face. Easy care. Great for gifting.|3699|1799|Multi|FREE|219|3803|5",
            "towels_set|Spaces Cotton Towel Gift Set|Gift boxed. Easy care. Great for gifting.|2699|1199|White|FREE|155|2678|14",
            "towels_set|Trident Bamboo Towel Set|Eco-friendly. Easy care. Great for gifting.|1799|1299|Beige|FREE|74|3644|36",
            "towels_set|Welspun Hotel Collection Towel Set|600 GSM. Easy care. Great for gifting.|3699|2199|White|FREE|132|787|2",
            "towels_set|Raymond Home Kids Towel Set|Fun prints. Easy care. Great for gifting.|2799|2099|Blue|FREE|249|2678|60",
            "towels_set|Story@Home Couple Towel Set|His and hers. Easy care. Great for gifting.|3499|2499|Cream|FREE|126|3927|20",
            // ---- bath_rugs
            "bath_rugs|Story@Home Memory Foam Bath Mat|Ultra soft. Easy care. Great for gifting.|959|679|Brown|FREE|208|4713|85",
            "bath_rugs|Saral Home Microfibre Bath Rug|Absorbent. Easy care. Great for gifting.|2599|1199|Green|FREE|17|4563|1",
            "bath_rugs|Spaces Cotton Bath Mat Set of 2|Washable. Easy care. Great for gifting.|1199|899|Multi|FREE|35|3065|48",
            "bath_rugs|Status Diatomite Stone Bath Mat|Quick dry. Easy care. Great for gifting.|919|539|Teal|FREE|195|3975|76",
            "bath_rugs|Home Centre Shaggy Bath Rug|Plush pile. Easy care. Great for gifting.|1199|709|Teal|FREE|171|1954|84",
            "bath_rugs|Kuber Industries Anti-Skid Bath Mat|Rubber backing. Easy care. Great for gifting.|1599|949|Cream|FREE|236|3007|5",
            // ---- bath_robes
            "bath_robes|Spaces Cotton Terry Bath Robe|Shawl collar. Easy care. Great for gifting.|3899|2999|Brown|FREE|87|2493|75",
            "bath_robes|Trident Waffle Bath Robe|Lightweight. Easy care. Great for gifting.|3399|1199|Blue|FREE|178|1515|87",
            "bath_robes|Bombay Dyeing Kids Hooded Bath Robe|Soft terry. Easy care. Great for gifting.|3499|2599|White|FREE|127|3935|63",
            "bath_robes|Story@Home Bamboo Bath Robe|Silky soft. Easy care. Great for gifting.|5399|2599|Blue|FREE|85|1211|84",
            "bath_robes|Welspun Kimono Bath Robe|Spa style. Easy care. Great for gifting.|2599|1099|Brown|FREE|69|1838|114",
            "bath_robes|Raymond Home Fleece Bath Robe|Winter warm. Easy care. Great for gifting.|2399|1799|Maroon|FREE|30|1256|35",
            // ---- bathroom_accessories
            "bathroom_accessories|Home Centre Ceramic Bathroom Set of 4|Dispenser, tumbler, soap dish. Easy care. Great for gifting.|2899|1999|Multi|FREE|141|3382|118",
            "bathroom_accessories|Story@Home Wall Mounted Shower Caddy|Rust-proof steel. Easy care. Great for gifting.|559|369|Brown|FREE|164|489|107",
            "bathroom_accessories|Kuber Industries Toothbrush Holder|Ceramic. Easy care. Great for gifting.|1299|769|Cream|FREE|98|2003|68",
            "bathroom_accessories|Plantex Soap Dispenser|Bamboo lid. Easy care. Great for gifting.|3899|1399|Green|FREE|171|1056|80",
            "bathroom_accessories|Umbra Towel Rack|Stainless steel. Easy care. Great for gifting.|1199|779|Green|FREE|121|2505|45",
            "bathroom_accessories|Ebern Designs Bathroom Corner Shelf|Tempered glass. Easy care. Great for gifting.|1499|879|White|FREE|212|3168|109",
            // ---- shower_curtains
            "shower_curtains|Home Centre Waterproof Shower Curtain with Hooks|PEVA. Easy care. Great for gifting.|419|319|Teal|FREE|103|1745|115",
            "shower_curtains|Story@Home Printed Fabric Shower Curtain|Botanical print. Easy care. Great for gifting.|729|559|Beige|FREE|25|4770|0",
            "shower_curtains|Kuber Industries Transparent Shower Curtain|Frosted. Easy care. Great for gifting.|1899|949|Beige|FREE|58|2192|70",
            "shower_curtains|Lushomes Striped Shower Curtain|Modern stripes. Easy care. Great for gifting.|1299|969|Blue|FREE|249|1638|99",
            "shower_curtains|Spaces Polyester Shower Curtain|Mildew resistant. Easy care. Great for gifting.|2199|899|White|FREE|221|4427|5",
            "shower_curtains|HOKIPO 3D Print Shower Curtain|Ocean scene. Easy care. Great for gifting.|1699|929|Multi|FREE|149|4338|10",
            // ---- floor_lamps
            "floor_lamps|Philips Tripod Floor Lamp|Wooden tripod, fabric shade. Easy care. Great for gifting.|16299|5999|Cream|FREE|243|4485|52",
            "floor_lamps|Home Centre Arc Floor Lamp|Curved arc, marble base. Easy care. Great for gifting.|6999|2499|Mustard|FREE|55|1829|30",
            "floor_lamps|Pepperfry LED Corner Floor Lamp|RGB colour changing. Easy care. Great for gifting.|12799|5499|Blue|FREE|245|251|60",
            "floor_lamps|Homesake Rattan Floor Lamp|Handwoven shade. Easy care. Great for gifting.|17499|7699|Green|FREE|136|2967|24",
            "floor_lamps|Fos Lighting Reading Floor Lamp|Adjustable head. Easy care. Great for gifting.|16199|6299|Maroon|FREE|197|1337|87",
            "floor_lamps|Wipro Metal Floor Lamp|Matte black finish. Easy care. Great for gifting.|12399|6199|Multi|FREE|235|2623|21",
            // ---- ceiling_lamps
            "ceiling_lamps|Philips LED Ceiling Light 24W|Slim panel. Easy care. Great for gifting.|5999|4199|Blue|FREE|115|4312|37",
            "ceiling_lamps|Wipro Pendant Hanging Lamp|Metal dome. Easy care. Great for gifting.|11199|4899|Maroon|FREE|25|2949|56",
            "ceiling_lamps|Syska Chandelier 5 Light|Crystal accents. Easy care. Great for gifting.|2699|1999|Maroon|FREE|210|2882|80",
            "ceiling_lamps|Home Centre Rattan Pendant Light|Boho rattan. Easy care. Great for gifting.|4999|3099|Green|FREE|26|3604|91",
            "ceiling_lamps|Pepperfry Glass Globe Pendant|Smoked glass. Easy care. Great for gifting.|3899|3099|White|FREE|118|3666|27",
            "ceiling_lamps|Homesake Smart Ceiling Light|App controlled. Easy care. Great for gifting.|5799|3199|White|FREE|174|2635|77",
            // ---- table_lamps
            "table_lamps|Philips Ceramic Table Lamp|Fabric drum shade. Easy care. Great for gifting.|2399|1199|Green|FREE|213|4523|69",
            "table_lamps|Home Centre Wooden Base Table Lamp|Natural wood. Easy care. Great for gifting.|2299|889|Brown|FREE|118|1346|21",
            "table_lamps|Pepperfry LED Study Lamp|Touch dimmer. Easy care. Great for gifting.|4599|2099|Beige|FREE|216|4319|50",
            "table_lamps|Homesake Mushroom Table Lamp|Retro mushroom. Easy care. Great for gifting.|3999|1399|Multi|FREE|38|3072|53",
            "table_lamps|Fos Lighting Brass Table Lamp|Antique brass. Easy care. Great for gifting.|6599|2999|Beige|FREE|35|3111|105",
            "table_lamps|Wipro Rechargeable Table Lamp|Cordless. Easy care. Great for gifting.|7399|2999|Multi|FREE|91|3092|62",
            // ---- wall_lamps
            "wall_lamps|Philips Swing Arm Wall Lamp|Adjustable arm. Easy care. Great for gifting.|1899|679|Multi|FREE|188|4685|17",
            "wall_lamps|Home Centre Up-Down LED Wall Light|Modern effect. Easy care. Great for gifting.|4799|2499|Grey|FREE|69|2923|38",
            "wall_lamps|Homesake Glass Wall Sconce|Vintage style. Easy care. Great for gifting.|3399|1199|Grey|FREE|98|2469|77",
            "wall_lamps|Pepperfry Rattan Wall Lamp|Boho. Easy care. Great for gifting.|2799|959|Maroon|FREE|51|3237|29",
            "wall_lamps|Fos Lighting Plug-In Wall Lamp|No wiring needed. Easy care. Great for gifting.|2499|1299|Grey|FREE|107|3691|49",
            "wall_lamps|Wipro Brass Wall Sconce|Antique finish. Easy care. Great for gifting.|2999|1799|Beige|FREE|203|1305|96",
            // ---- outdoor_lamps
            "outdoor_lamps|Philips Solar Garden Lights Pack of 4|Auto on/off. Easy care. Great for gifting.|2199|1699|Mustard|FREE|104|3785|21",
            "outdoor_lamps|Wipro Outdoor Wall Lantern|Weatherproof. Easy care. Great for gifting.|1999|1499|Green|FREE|102|4742|63",
            "outdoor_lamps|Syska LED Flood Light 50W|IP65. Easy care. Great for gifting.|5799|2399|Teal|FREE|21|2708|82",
            "outdoor_lamps|Homesake Bollard Garden Light|Pathway lighting. Easy care. Great for gifting.|2499|859|Brown|FREE|32|314|115",
            "outdoor_lamps|Home Centre Solar Hanging Lantern|Decorative. Easy care. Great for gifting.|1199|699|Cream|FREE|76|1803|17",
            "outdoor_lamps|Halonix Motion Sensor Light|PIR sensor. Easy care. Great for gifting.|3299|2099|Maroon|FREE|119|3726|119",
            // ---- string_lights
            "string_lights|Philips Fairy String Lights 10m|Warm white. Easy care. Great for gifting.|2999|1499|Green|FREE|32|3566|103",
            "string_lights|Home Centre Curtain String Lights|300 LED. Easy care. Great for gifting.|449|309|Blue|FREE|53|2898|7",
            "string_lights|Homesake Photo Clip String Lights|Hang memories. Easy care. Great for gifting.|1299|719|Green|FREE|192|753|66",
            "string_lights|Desidiya Solar String Lights|Outdoor. Easy care. Great for gifting.|1399|469|Mustard|FREE|69|3071|45",
            "string_lights|Syska Ball String Lights|Cotton balls. Easy care. Great for gifting.|889|489|Mustard|FREE|130|100|8",
            "string_lights|Fos Lighting Star String Lights|Festive stars. Easy care. Great for gifting.|1399|849|Teal|FREE|241|736|85",
            // ---- plants_planters
            "plants_planters|Ugaoo Snake Plant with Ceramic Pot|Air purifying. Easy care. Great for gifting.|2999|1999|Maroon|FREE|161|3401|98",
            "plants_planters|Nurserylive Money Plant in Glass Bottle|Low maintenance. Easy care. Great for gifting.|5999|2399|White|FREE|161|4014|81",
            "plants_planters|Home Centre Ceramic Planters Set of 3|Matte finish. Easy care. Great for gifting.|889|609|Cream|FREE|157|3187|11",
            "plants_planters|Trudeau Hanging Macrame Planter|Boho decor. Easy care. Great for gifting.|1099|839|Maroon|FREE|216|1140|12",
            "plants_planters|Chumbak Artificial Bamboo Plant|No maintenance. Easy care. Great for gifting.|2199|1599|Maroon|FREE|210|3583|16",
            "plants_planters|Pepperfry Self-Watering Planter|Built-in reservoir. Easy care. Great for gifting.|3099|1599|Mustard|FREE|67|906|103",
            // ---- aromas_candles
            "aromas_candles|Iris Scented Jar Candle|Lavender, 40 hr burn. Easy care. Great for gifting.|1699|729|Brown|FREE|116|722|86",
            "aromas_candles|Bath & Body Works Reed Diffuser Set|Fragrance oil with reeds. Easy care. Great for gifting.|2199|1099|Teal|FREE|37|2320|59",
            "aromas_candles|Home Centre Aroma Oil Diffuser|Ceramic burner. Easy care. Great for gifting.|1399|779|Blue|FREE|37|2088|119",
            "aromas_candles|Rosemoore Tealight Candles Pack of 50|Unscented. Easy care. Great for gifting.|1099|749|Grey|FREE|189|3842|60",
            "aromas_candles|Ekam Soy Wax Candle|Vanilla. Easy care. Great for gifting.|629|379|Cream|FREE|125|2973|106",
            "aromas_candles|The Body Shop Essential Oils Set of 6|Pure oils. Easy care. Great for gifting.|1599|809|Green|FREE|28|1913|10",
            // ---- clocks
            "clocks|Ajanta Silent Sweep Wall Clock|No ticking. Easy care. Great for gifting.|3699|2299|Green|FREE|125|4160|33",
            "clocks|Home Centre Wooden Wall Clock|Natural wood. Easy care. Great for gifting.|879|409|Brown|FREE|75|4008|96",
            "clocks|Chumbak Metal Sunburst Clock|Gold finish. Easy care. Great for gifting.|4399|2399|Multi|FREE|84|1074|51",
            "clocks|Seiko Pendulum Wall Clock|Classic design. Easy care. Great for gifting.|2399|1199|Grey|FREE|57|722|37",
            "clocks|Random Digital LED Clock|Temperature display. Easy care. Great for gifting.|3199|1399|Grey|FREE|96|3364|94",
            "clocks|Pepperfry Table Alarm Clock|Retro twin bell. Easy care. Great for gifting.|3999|2499|Blue|FREE|98|1137|33",
            // ---- mirrors
            "mirrors|Home Centre Round Wall Mirror|Metal frame. Easy care. Great for gifting.|7399|4899|Cream|FREE|20|1732|71",
            "mirrors|Pepperfry Sunburst Decorative Mirror|Gold finish. Easy care. Great for gifting.|5899|3599|Mustard|FREE|136|571|8",
            "mirrors|Chumbak Full Length Floor Mirror|Wooden frame. Easy care. Great for gifting.|4399|3099|Green|FREE|69|4703|29",
            "mirrors|Wooden Street Arch Wall Mirror|Modern arch. Easy care. Great for gifting.|3599|1799|Cream|FREE|233|2624|54",
            "mirrors|Art Street Bathroom Vanity Mirror|LED backlit. Easy care. Great for gifting.|6999|3599|Blue|FREE|18|117|1",
            "mirrors|Urban Ladder Rattan Mirror|Boho cane frame. Easy care. Great for gifting.|3199|2399|Beige|FREE|184|983|75",
            // ---- wall_decor
            "wall_decor|Art Street Framed Wall Art Set of 3|Abstract prints. Easy care. Great for gifting.|3199|2199|White|FREE|64|117|37",
            "wall_decor|Chumbak Metal Wall Art|Laser cut. Easy care. Great for gifting.|3199|1699|Cream|FREE|233|4686|53",
            "wall_decor|Home Centre Wall Hanging Macrame|Handmade. Easy care. Great for gifting.|3399|1899|Teal|FREE|97|2959|58",
            "wall_decor|Pepperfry Photo Frame Collage|12 frames. Easy care. Great for gifting.|3499|2299|Beige|FREE|53|4297|0",
            "wall_decor|Paper Plane Design Wall Plates Set|Decorative ceramic. Easy care. Great for gifting.|1699|1099|Teal|FREE|78|1248|37",
            "wall_decor|Hometown Canvas Painting|Modern art. Easy care. Great for gifting.|2899|1099|Teal|FREE|200|3546|56",
            // ---- festive_decor
            "festive_decor|Home Centre Toran Door Hanging|Bead and bells. Easy care. Great for gifting.|3699|1499|Green|FREE|228|4375|66",
            "festive_decor|Chumbak Diya Set of 12|Terracotta. Easy care. Great for gifting.|1299|619|Multi|FREE|41|2366|67",
            "festive_decor|Desidiya Artificial Marigold Garland|5 ft pack. Easy care. Great for gifting.|2699|1399|Grey|FREE|100|1481|103",
            "festive_decor|Ekam Rangoli Stencils Kit|With colours. Easy care. Great for gifting.|1499|1099|Cream|FREE|226|3961|99",
            "festive_decor|Fabindia LED Diya Lights|Battery operated. Easy care. Great for gifting.|1799|619|Maroon|FREE|27|1739|18",
            "festive_decor|Rangoli Art Urli Bowl with Floating Candles|Brass finish. Easy care. Great for gifting.|3899|1399|Mustard|FREE|20|2724|88",
            // ---- pooja_essentials
            "pooja_essentials|Ekam Brass Pooja Thali Set|7-piece set. Easy care. Great for gifting.|1599|729|Brown|FREE|83|2741|112",
            "pooja_essentials|Home Centre Wooden Pooja Mandir|Wall mounted. Easy care. Great for gifting.|2299|1699|Brown|FREE|40|1698|90",
            "pooja_essentials|Fabindia Brass Diya Pair|Traditional. Easy care. Great for gifting.|4599|2299|Teal|FREE|128|4758|114",
            "pooja_essentials|Divine Gifts Incense Stick Holder|Brass. Easy care. Great for gifting.|2999|1899|Multi|FREE|97|1374|113",
            "pooja_essentials|Brass Gift Center Copper Kalash|Pure copper. Easy care. Great for gifting.|609|369|Green|FREE|128|1988|79",
            "pooja_essentials|Shubh Labh Ganesha Idol|Resin finish. Easy care. Great for gifting.|3999|2499|Grey|FREE|195|1413|33",
            // ---- wall_shelves
            "wall_shelves|Home Centre Floating Wall Shelves Set of 3|MDF. Easy care. Great for gifting.|6299|2199|Cream|FREE|141|4526|69",
            "wall_shelves|Pepperfry Hexagon Wall Shelf|Honeycomb. Easy care. Great for gifting.|3899|1799|Mustard|FREE|105|2179|31",
            "wall_shelves|Wooden Street Corner Wall Shelf|Space saving. Easy care. Great for gifting.|2699|1699|Brown|FREE|227|117|90",
            "wall_shelves|Urban Ladder Metal Ladder Shelf|Industrial look. Easy care. Great for gifting.|1399|909|Mustard|FREE|129|2860|62",
            "wall_shelves|Art Street Rope Hanging Shelf|Rustic. Easy care. Great for gifting.|739|589|Brown|FREE|162|4513|33",
            "wall_shelves|Solimo Book Shelf Wall Mounted|Engineered wood. Easy care. Great for gifting.|1899|1499|Grey|FREE|153|4153|36",
            // ---- fountains
            "fountains|Home Centre Tabletop Water Fountain|LED light. Easy care. Great for gifting.|6399|4399|Maroon|FREE|207|863|20",
            "fountains|Pepperfry Buddha Fountain|Resin. Easy care. Great for gifting.|1999|1199|Beige|FREE|218|637|90",
            "fountains|Ekam Ceramic Fountain|Cascading. Easy care. Great for gifting.|10899|3799|Multi|FREE|161|2759|57",
            "fountains|Nurserylive Rock Wall Fountain|Natural look. Easy care. Great for gifting.|8399|3099|Beige|FREE|233|3607|36",
            "fountains|Ugaoo Solar Bird Bath Fountain|Garden. Easy care. Great for gifting.|4699|2199|Green|FREE|138|2728|13",
            "fountains|Craftmart Indoor Zen Fountain|Calming. Easy care. Great for gifting.|8599|4099|Green|FREE|19|1921|66",
            // ---- showpieces_vases
            "showpieces_vases|Home Centre Ceramic Flower Vase|Matte finish. Easy care. Great for gifting.|4199|2099|Brown|FREE|46|2889|24",
            "showpieces_vases|Chumbak Glass Bud Vase Set|Set of 3. Easy care. Great for gifting.|989|739|Blue|FREE|223|480|16",
            "showpieces_vases|Pepperfry Buddha Showpiece|Resin. Easy care. Great for gifting.|4799|2399|Blue|FREE|188|4625|116",
            "showpieces_vases|Ekam Brass Elephant Showpiece|Handcrafted. Easy care. Great for gifting.|2299|1699|Multi|FREE|110|4175|6",
            "showpieces_vases|Art Street Abstract Sculpture|Modern art. Easy care. Great for gifting.|2299|1499|Beige|FREE|187|2339|58",
            "showpieces_vases|Fabindia Terracotta Vase|Hand painted. Easy care. Great for gifting.|3299|1999|Blue|FREE|147|396|115",
            // ---- ottoman
            "ottoman|Home Centre Velvet Round Ottoman|Gold legs. Easy care. Great for gifting.|6799|3399|Teal|FREE|25|1485|113",
            "ottoman|Pepperfry Storage Ottoman Pouffe|Hidden storage. Easy care. Great for gifting.|5799|2699|Brown|FREE|99|3498|103",
            "ottoman|Wooden Street Knitted Pouf|Chunky knit. Easy care. Great for gifting.|9099|3999|Beige|FREE|235|69|52",
            "ottoman|Urban Ladder Leatherette Ottoman|Tufted top. Easy care. Great for gifting.|7599|4399|Green|FREE|40|1567|81",
            "ottoman|Hometown Jute Pouf|Handwoven. Easy care. Great for gifting.|3299|2099|White|FREE|176|2687|62",
            "ottoman|Solimo Bench Ottoman|Bed end. Easy care. Great for gifting.|3899|2899|Mustard|FREE|172|4183|107",
            // ---- cushions_cushion_covers
            "cushions_cushion_covers|Home Centre Cushion Covers Set of 5|16x16 inches. Easy care. Great for gifting.|1299|849|Brown|FREE|217|1947|58",
            "cushions_cushion_covers|Chumbak Velvet Cushion Covers|Plush. Easy care. Great for gifting.|1899|1099|Teal|FREE|93|3173|38",
            "cushions_cushion_covers|Story@Home Printed Cotton Cushions|Filled cushions. Easy care. Great for gifting.|2299|1499|Teal|FREE|178|1383|52",
            "cushions_cushion_covers|Spaces Embroidered Cushion Covers|Mirror work. Easy care. Great for gifting.|2499|999|Brown|FREE|233|1901|26",
            "cushions_cushion_covers|Fabindia Boho Tufted Cushion|Tassels. Easy care. Great for gifting.|1299|649|Multi|FREE|160|3214|60",
            "cushions_cushion_covers|Swayam Floor Cushion|Large 24 inch. Easy care. Great for gifting.|2399|1399|Beige|FREE|154|2812|0",
            // ---- curtains
            "curtains|Home Centre Blackout Curtains Set of 2|Door 7 ft. Easy care. Great for gifting.|6499|2399|Cream|FREE|124|3982|73",
            "curtains|D'Decor Sheer Window Curtains|Voile. Easy care. Great for gifting.|5299|1899|Maroon|FREE|149|1954|50",
            "curtains|Story@Home Printed Cotton Curtains|Eyelet. Easy care. Great for gifting.|3099|2099|Maroon|FREE|181|1111|1",
            "curtains|Spaces Velvet Curtains|Thermal insulation. Easy care. Great for gifting.|5599|2499|Mustard|FREE|120|295|118",
            "curtains|Lushomes Linen Look Curtains|Natural texture. Easy care. Great for gifting.|4099|1799|Green|FREE|129|4737|29",
            "curtains|Swayam Kids Room Curtains|Fun print. Easy care. Great for gifting.|4699|2399|Blue|FREE|107|1265|106",
            // ---- home_gift_sets
            "home_gift_sets|Home Centre Candle and Diffuser Gift Box|Aroma set. Easy care. Great for gifting.|4499|2199|Brown|FREE|176|2287|26",
            "home_gift_sets|Chumbak Coffee Mug Gift Set|Set of 2. Easy care. Great for gifting.|3199|1199|Brown|FREE|43|679|66",
            "home_gift_sets|Iris Decor Hamper|Assorted decor. Easy care. Great for gifting.|1999|1399|Blue|FREE|132|1692|33",
            "home_gift_sets|Ekam Brass Gift Set|Festive. Easy care. Great for gifting.|1199|899|Brown|FREE|200|4548|82",
            "home_gift_sets|Fabindia Plant Gift Set|Indoor plant with pot. Easy care. Great for gifting.|6599|2799|Brown|FREE|241|376|117",
            "home_gift_sets|Story@Home Photo Frame Gift Set|Set of 3. Easy care. Great for gifting.|3599|2699|Mustard|FREE|26|1096|111",
            // ---- table_runners
            "table_runners|Home Centre Cotton Table Runner|6 seater. Easy care. Great for gifting.|869|339|Mustard|FREE|153|2337|117",
            "table_runners|Story@Home Jute Table Runner|Natural. Easy care. Great for gifting.|1899|1299|Beige|FREE|198|2556|53",
            "table_runners|Swayam Embroidered Table Runner|Festive. Easy care. Great for gifting.|2599|1299|Grey|FREE|163|402|100",
            "table_runners|Fabindia Block Print Table Runner|Hand printed. Easy care. Great for gifting.|1099|729|Grey|FREE|132|2626|36",
            "table_runners|Spaces Velvet Table Runner|Luxury. Easy care. Great for gifting.|1799|1199|Multi|FREE|197|1624|61",
            "table_runners|Lushomes Macrame Table Runner|Boho. Easy care. Great for gifting.|2999|1199|White|FREE|249|3314|118",
            // ---- dinnerware_serveware
            "dinnerware_serveware|Corelle Dinner Set 18 Pieces|Opal glass. Easy care. Great for gifting.|4299|2899|Cream|FREE|101|3221|8",
            "dinnerware_serveware|Borosil Ceramic Serving Bowls Set|Set of 4. Easy care. Great for gifting.|4399|2999|Teal|FREE|15|381|49",
            "dinnerware_serveware|La Opala Melamine Dinner Set|Unbreakable. Easy care. Great for gifting.|2899|1199|Maroon|FREE|50|4063|41",
            "dinnerware_serveware|Clay Craft Stainless Steel Thali Set|6 pieces. Easy care. Great for gifting.|7299|4299|Multi|FREE|102|1951|54",
            "dinnerware_serveware|Home Centre Serving Platter|Wooden. Easy care. Great for gifting.|5499|3299|Grey|FREE|124|2478|97",
            "dinnerware_serveware|Cello Glass Casserole Set|Microwave safe. Easy care. Great for gifting.|7499|4599|Teal|FREE|210|2453|66",
            // ---- cups_mugs
            "cups_mugs|Chumbak Ceramic Coffee Mugs Set of 2|350 ml. Easy care. Great for gifting.|1299|759|Beige|FREE|200|2520|20",
            "cups_mugs|Borosil Double Wall Glass Mugs|Insulated. Easy care. Great for gifting.|989|659|Beige|FREE|169|1481|80",
            "cups_mugs|Clay Craft Printed Quirky Mug|Gift ready. Easy care. Great for gifting.|579|359|Beige|FREE|207|1160|100",
            "cups_mugs|Home Centre Tea Cups Set of 6|Fine bone china. Easy care. Great for gifting.|899|539|White|FREE|167|3196|55",
            "cups_mugs|Femora Travel Mug|Spill proof. Easy care. Great for gifting.|2099|949|Brown|FREE|125|3014|116",
            "cups_mugs|Cello Stoneware Mug|Handmade glaze. Easy care. Great for gifting.|1699|789|Green|FREE|33|1678|85",
            // ---- bakeware_cookware
            "bakeware_cookware|Prestige Non-Stick Cookware Set 3 Pieces|Induction base. Easy care. Great for gifting.|7699|4199|Multi|FREE|45|2442|15",
            "bakeware_cookware|Hawkins Pressure Cooker 5L|Aluminium. Easy care. Great for gifting.|5099|2799|Mustard|FREE|84|1534|64",
            "bakeware_cookware|Pigeon Cast Iron Tawa|Pre-seasoned. Easy care. Great for gifting.|9199|4099|Brown|FREE|32|2424|108",
            "bakeware_cookware|Wonderchef Silicone Baking Moulds Set|Oven safe. Easy care. Great for gifting.|6999|3399|Grey|FREE|168|2823|47",
            "bakeware_cookware|Borosil Stainless Steel Kadai|Triply. Easy care. Great for gifting.|6199|3099|White|FREE|61|4581|30",
            "bakeware_cookware|Meyer Glass Baking Dish|Borosilicate. Easy care. Great for gifting.|4899|3499|Grey|FREE|90|4056|92",
            // ---- kitchen_storage_tools
            "kitchen_storage_tools|Tupperware Airtight Containers Set of 6|BPA free. Easy care. Great for gifting.|2199|1699|Brown|FREE|222|2335|44",
            "kitchen_storage_tools|Cello Spice Rack Organiser|Rotating. Easy care. Great for gifting.|2499|1199|White|FREE|49|3769|61",
            "kitchen_storage_tools|Milton Glass Storage Jars|Bamboo lids. Easy care. Great for gifting.|3099|1999|Beige|FREE|244|3890|57",
            "kitchen_storage_tools|Prestige Chopping Board|Bamboo. Easy care. Great for gifting.|809|549|Beige|FREE|29|3605|17",
            "kitchen_storage_tools|Borosil Kitchen Tools Set|Silicone. Easy care. Great for gifting.|729|449|Teal|FREE|157|1879|103",
            "kitchen_storage_tools|Solimo Vegetable Chopper|Pull cord. Easy care. Great for gifting.|1999|1299|Grey|FREE|61|3936|10",
            // ---- bar_drinkware
            "bar_drinkware|Borosil Whiskey Glasses Set of 6|Heavy base. Easy care. Great for gifting.|2099|729|Brown|FREE|108|4503|101",
            "bar_drinkware|Ocean Wine Glasses Set of 4|Crystal clear. Easy care. Great for gifting.|2399|1599|Multi|FREE|37|2218|86",
            "bar_drinkware|Home Centre Cocktail Shaker Set|Stainless steel. Easy care. Great for gifting.|3299|1899|White|FREE|228|1564|23",
            "bar_drinkware|Luminarc Beer Mugs Set|500 ml. Easy care. Great for gifting.|1599|709|Blue|FREE|82|95|31",
            "bar_drinkware|Femora Decanter with Glasses|Gift set. Easy care. Great for gifting.|1899|1099|Mustard|FREE|16|4323|67",
            "bar_drinkware|Treo Juice Glasses Set of 6|Everyday. Easy care. Great for gifting.|2699|1499|Mustard|FREE|96|4136|102",
            // ---- table_covers_furnishings
            "table_covers_furnishings|Home Centre Table Cover 6 Seater|Waterproof. Easy care. Great for gifting.|749|449|Green|FREE|139|4536|95",
            "table_covers_furnishings|Story@Home Cotton Table Cloth|Printed. Easy care. Great for gifting.|1299|759|Maroon|FREE|242|3528|86",
            "table_covers_furnishings|Kuber Industries Placemats Set of 6|Woven. Easy care. Great for gifting.|949|509|Cream|FREE|164|487|14",
            "table_covers_furnishings|Swayam Coasters Set of 6|Cork. Easy care. Great for gifting.|2199|1099|Cream|FREE|229|1636|21",
            "table_covers_furnishings|Spaces Lace Table Cover|Elegant lace. Easy care. Great for gifting.|599|479|Beige|FREE|74|2078|34",
            "table_covers_furnishings|Lushomes Table Napkins Set|Cotton. Easy care. Great for gifting.|1999|1199|Maroon|FREE|120|2887|51",
            // ---- mi_mobile
            "mi_mobile|Xiaomi Redmi Note 13 Pro 5G (8GB RAM, 256GB)|6.67\" 120Hz AMOLED, 200MP camera, 5100mAh, 67W charging. 1 year manufacturer warranty. Box includes charging cable.|27999|25999|Grey|FREE|105|1305|64",
            "mi_mobile|Xiaomi Redmi 13C 5G (4GB RAM, 128GB)|6.74\" 90Hz display, 50MP AI camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|10999|9999|Black|FREE|129|296|98",
            "mi_mobile|Xiaomi 14 Civi (12GB RAM, 512GB)|Leica optics, Snapdragon 8s Gen 3, 4700mAh. 1 year manufacturer warranty. Box includes charging cable.|52499|42999|Black|FREE|74|1631|5",
            "mi_mobile|Xiaomi Redmi Note 13 5G (6GB RAM, 128GB)|108MP camera, Dimensity 6080, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|21499|17999|Silver|FREE|25|2716|59",
            "mi_mobile|Xiaomi Redmi 13 5G (8GB RAM, 128GB)|108MP camera, Snapdragon 4 Gen 2. 1 year manufacturer warranty. Box includes charging cable.|15399|13999|Grey|FREE|97|4121|90",
            "mi_mobile|Xiaomi Redmi A3 (3GB RAM, 64GB)|6.71\" 90Hz, 5000mAh, budget everyday phone. 1 year manufacturer warranty. Box includes charging cable.|7999|7299|Gold|FREE|204|898|0",
            // ---- realme_mobile
            "realme_mobile|realme 12 Pro+ 5G (8GB RAM, 256GB)|Periscope portrait camera, curved AMOLED. 1 year manufacturer warranty. Box includes charging cable.|35799|29999|Blue|FREE|19|3082|12",
            "realme_mobile|realme 12x 5G (6GB RAM, 128GB)|45W SuperVOOC, 120Hz display. 1 year manufacturer warranty. Box includes charging cable.|13099|11999|Black|FREE|47|1799|72",
            "realme_mobile|realme GT 6T (8GB RAM, 128GB)|Snapdragon 7+ Gen 3, 5500mAh, 120W. 1 year manufacturer warranty. Box includes charging cable.|32299|30999|White|FREE|107|1128|1",
            "realme_mobile|realme C65 (4GB RAM, 128GB)|50MP AI camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|12899|10499|Gold|FREE|228|2176|102",
            "realme_mobile|realme 13 Pro 5G (8GB RAM, 256GB)|Sony LYT-600 camera, AMOLED. 1 year manufacturer warranty. Box includes charging cable.|32599|26999|Blue|FREE|125|557|69",
            "realme_mobile|realme Narzo 70 Pro 5G (8GB RAM, 128GB)|Air gesture, 50MP Sony IMX890. 1 year manufacturer warranty. Box includes charging cable.|22999|19999|Silver|FREE|33|1793|43",
            // ---- samsung_mobile
            "samsung_mobile|Samsung Galaxy S24 Ultra (12GB RAM, 256GB)|Built-in S Pen, 200MP camera, Galaxy AI. 1 year manufacturer warranty. Box includes charging cable.|145299|121999|Gold|FREE|235|1092|59",
            "samsung_mobile|Samsung Galaxy S24 (8GB RAM, 256GB)|6.2\" Dynamic AMOLED, Exynos 2400. 1 year manufacturer warranty. Box includes charging cable.|86299|74999|Black|FREE|60|1270|16",
            "samsung_mobile|Samsung Galaxy A55 5G (8GB RAM, 128GB)|IP67, 50MP OIS camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|43499|39999|Gold|FREE|59|3223|43",
            "samsung_mobile|Samsung Galaxy M35 5G (6GB RAM, 128GB)|6000mAh battery, Super AMOLED. 1 year manufacturer warranty. Box includes charging cable.|19399|17999|Silver|FREE|137|1373|63",
            "samsung_mobile|Samsung Galaxy Z Flip6 (12GB RAM, 256GB)|Foldable, 3.4\" FlexWindow. 1 year manufacturer warranty. Box includes charging cable.|124999|109999|White|FREE|154|1221|82",
            "samsung_mobile|Samsung Galaxy F15 5G (4GB RAM, 128GB)|6000mAh, sAMOLED display. 1 year manufacturer warranty. Box includes charging cable.|14799|12999|Grey|FREE|114|3326|10",
            // ---- infinix_mobile
            "infinix_mobile|Infinix Note 40 Pro 5G (8GB RAM, 256GB)|108MP camera, 3D curved AMOLED, magnetic charging. 1 year manufacturer warranty. Box includes charging cable.|24799|21999|Grey|FREE|61|2898|71",
            "infinix_mobile|Infinix Hot 40i (8GB RAM, 256GB)|6.56\" 90Hz, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|10699|9499|Green|FREE|206|4610|86",
            "infinix_mobile|Infinix GT 20 Pro 5G (8GB RAM, 256GB)|Gaming phone, Dimensity 8200 Ultimate. 1 year manufacturer warranty. Box includes charging cable.|26599|24999|Grey|FREE|221|4315|116",
            "infinix_mobile|Infinix Zero 30 5G (12GB RAM, 256GB)|4K 60fps selfie camera. 1 year manufacturer warranty. Box includes charging cable.|25299|23999|Blue|FREE|33|4525|17",
            "infinix_mobile|Infinix Smart 8 HD (3GB RAM, 64GB)|Budget, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|7799|6499|Silver|FREE|128|2607|32",
            "infinix_mobile|Infinix Note 40 5G (8GB RAM, 256GB)|120Hz AMOLED, 33W charging. 1 year manufacturer warranty. Box includes charging cable.|21699|17999|Gold|FREE|94|3604|21",
            // ---- oppo_mobile
            "oppo_mobile|OPPO Reno12 Pro 5G (12GB RAM, 256GB)|50MP portrait camera, AI eraser. 1 year manufacturer warranty. Box includes charging cable.|43599|36999|Black|FREE|147|937|115",
            "oppo_mobile|OPPO F27 Pro+ 5G (8GB RAM, 128GB)|IP69 rated, cool curved display. 1 year manufacturer warranty. Box includes charging cable.|31899|27999|Grey|FREE|195|4392|120",
            "oppo_mobile|OPPO A79 5G (8GB RAM, 128GB)|33W SUPERVOOC, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|24399|19999|Green|FREE|168|1529|108",
            "oppo_mobile|OPPO Reno11 5G (8GB RAM, 256GB)|Ultra-clear portrait camera. 1 year manufacturer warranty. Box includes charging cable.|32299|29999|White|FREE|246|1237|41",
            "oppo_mobile|OPPO A3x 5G (4GB RAM, 64GB)|Military-grade drop resistance. 1 year manufacturer warranty. Box includes charging cable.|15299|12499|Black|FREE|183|2639|31",
            "oppo_mobile|OPPO K12x 5G (6GB RAM, 128GB)|360° damage-proof armour body. 1 year manufacturer warranty. Box includes charging cable.|15499|12999|Blue|FREE|213|3972|103",
            // ---- apple_mobile
            "apple_mobile|Apple iPhone 15 (128GB)|Dynamic Island, 48MP main camera, USB-C. 1 year manufacturer warranty. Box includes charging cable.|85299|69900|Black|FREE|64|173|12",
            "apple_mobile|Apple iPhone 15 Plus (256GB)|6.7\" display, all-day battery. 1 year manufacturer warranty. Box includes charging cable.|99899|89900|Blue|FREE|104|2236|14",
            "apple_mobile|Apple iPhone 15 Pro (256GB)|Titanium design, A17 Pro chip. 1 year manufacturer warranty. Box includes charging cable.|158499|129900|Gold|FREE|211|2623|2",
            "apple_mobile|Apple iPhone 15 Pro Max (512GB)|5x telephoto, titanium. 1 year manufacturer warranty. Box includes charging cable.|168399|159900|White|FREE|109|1745|65",
            "apple_mobile|Apple iPhone 14 (128GB)|A15 Bionic, Crash Detection. 1 year manufacturer warranty. Box includes charging cable.|63799|59900|Gold|FREE|214|429|51",
            "apple_mobile|Apple iPhone 13 (128GB)|Dual camera, A15 Bionic. 1 year manufacturer warranty. Box includes charging cable.|58799|49900|Silver|FREE|71|1442|71",
            // ---- vivo_mobile
            "vivo_mobile|vivo V30 Pro 5G (12GB RAM, 512GB)|ZEISS camera, Aura light portrait. 1 year manufacturer warranty. Box includes charging cable.|52299|46999|Black|FREE|18|4077|24",
            "vivo_mobile|vivo T3 5G (8GB RAM, 128GB)|Dimensity 7200, 50MP Sony IMX882. 1 year manufacturer warranty. Box includes charging cable.|23599|19999|Gold|FREE|237|2429|11",
            "vivo_mobile|vivo Y28s 5G (6GB RAM, 128GB)|5000mAh, 90Hz. 1 year manufacturer warranty. Box includes charging cable.|16899|15499|Black|FREE|182|543|39",
            "vivo_mobile|vivo X100 Pro (16GB RAM, 512GB)|ZEISS APO telephoto. 1 year manufacturer warranty. Box includes charging cable.|93799|89999|Black|FREE|71|3676|55",
            "vivo_mobile|vivo Y18 (4GB RAM, 128GB)|Budget, 50MP camera. 1 year manufacturer warranty. Box includes charging cable.|10599|8999|Gold|FREE|18|1630|63",
            "vivo_mobile|vivo T3 Ultra 5G (8GB RAM, 256GB)|3D curved AMOLED. 1 year manufacturer warranty. Box includes charging cable.|37299|31999|Grey|FREE|195|205|116",
            // ---- honor_mobile
            "honor_mobile|Honor 200 Pro 5G (12GB RAM, 512GB)|Studio Harcourt portrait camera. 1 year manufacturer warranty. Box includes charging cable.|64499|57999|Green|FREE|243|4800|17",
            "honor_mobile|Honor X9b 5G (8GB RAM, 256GB)|Ultra-bounce anti-drop display. 1 year manufacturer warranty. Box includes charging cable.|31799|25999|Blue|FREE|123|4066|57",
            "honor_mobile|Honor Magic6 Pro (12GB RAM, 512GB)|180MP periscope camera. 1 year manufacturer warranty. Box includes charging cable.|103499|89999|Grey|FREE|76|125|87",
            "honor_mobile|Honor 90 5G (8GB RAM, 256GB)|200MP main camera. 1 year manufacturer warranty. Box includes charging cable.|30499|27999|Blue|FREE|24|1042|73",
            "honor_mobile|Honor X7b (8GB RAM, 128GB)|6000mAh battery. 1 year manufacturer warranty. Box includes charging cable.|17099|15999|Black|FREE|15|233|65",
            "honor_mobile|Honor 200 5G (8GB RAM, 256GB)|Eye comfort OLED. 1 year manufacturer warranty. Box includes charging cable.|40299|34999|Blue|FREE|167|2702|102",
            // ---- asus_mobile
            "asus_mobile|ASUS ROG Phone 8 (12GB RAM, 256GB)|Gaming, Snapdragon 8 Gen 3, AirTrigger. 1 year manufacturer warranty. Box includes charging cable.|102199|94999|Silver|FREE|221|4669|21",
            "asus_mobile|ASUS ROG Phone 8 Pro (16GB RAM, 512GB)|AniMe Vision display. 1 year manufacturer warranty. Box includes charging cable.|137999|119999|Silver|FREE|168|4113|34",
            "asus_mobile|ASUS Zenfone 11 Ultra (12GB RAM, 256GB)|6.78\" AMOLED, gimbal stabiliser. 1 year manufacturer warranty. Box includes charging cable.|72999|69999|Black|FREE|81|1405|75",
            "asus_mobile|ASUS ROG Phone 7 (12GB RAM, 256GB)|6000mAh, 165Hz. 1 year manufacturer warranty. Box includes charging cable.|89299|74999|Green|FREE|25|907|16",
            "asus_mobile|ASUS Zenfone 10 (8GB RAM, 256GB)|Compact flagship. 1 year manufacturer warranty. Box includes charging cable.|67099|54999|Blue|FREE|181|2410|20",
            "asus_mobile|ASUS ROG Phone 6 (12GB RAM, 256GB)|GameCool 6 cooling. 1 year manufacturer warranty. Box includes charging cable.|60299|49999|Green|FREE|33|4686|45",
            // ---- poco_x2_mobile
            "poco_x2_mobile|POCO X2 (6GB RAM, 64GB) Atlantis Blue|120Hz display, 64MP Sony camera. 1 year manufacturer warranty. Box includes charging cable.|18899|15999|Blue|FREE|235|4115|14",
            "poco_x2_mobile|POCO X2 (6GB RAM, 128GB) Matrix Purple|Snapdragon 730G, 4500mAh. 1 year manufacturer warranty. Box includes charging cable.|17899|16999|Purple|FREE|220|280|47",
            "poco_x2_mobile|POCO X2 (8GB RAM, 256GB) Phoenix Red|Dual selfie, 27W fast charging. 1 year manufacturer warranty. Box includes charging cable.|22299|19999|Red|FREE|142|3740|59",
            "poco_x2_mobile|POCO X2 (6GB RAM, 128GB) Atlantis Blue|Liquid cooling. 1 year manufacturer warranty. Box includes charging cable.|18499|16999|Blue|FREE|186|1113|100",
            "poco_x2_mobile|POCO X2 (8GB RAM, 256GB) Matrix Purple|Side fingerprint sensor. 1 year manufacturer warranty. Box includes charging cable.|21099|19999|Purple|FREE|148|1793|2",
            "poco_x2_mobile|POCO X2 (6GB RAM, 64GB) Phoenix Red|6.67\" FHD+ RealityFlow display. 1 year manufacturer warranty. Box includes charging cable.|17599|15999|Red|FREE|19|1092|110",
            // ---- realme_narzo_10_mobile
            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 128GB) That Green|48MP quad camera, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|14799|12999|Green|FREE|76|4289|2",
            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 128GB) That White|Helio G80 gaming processor. 1 year manufacturer warranty. Box includes charging cable.|13599|12999|White|FREE|232|1930|5",
            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 64GB) That Blue|6.5\" mini-drop display. 1 year manufacturer warranty. Box includes charging cable.|13399|11999|Blue|FREE|109|820|73",
            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 128GB) That Blue|18W fast charge. 1 year manufacturer warranty. Box includes charging cable.|15699|12999|Blue|FREE|148|563|118",
            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 64GB) That Green|Reverse charging. 1 year manufacturer warranty. Box includes charging cable.|14299|11999|Green|FREE|105|444|99",
            "realme_narzo_10_mobile|realme Narzo 10 (4GB RAM, 64GB) That White|Ultra macro lens. 1 year manufacturer warranty. Box includes charging cable.|14699|11999|White|FREE|18|1066|92",
            // ---- infinix_hot_9_mobile
            "infinix_hot_9_mobile|Infinix Hot 9 (4GB RAM, 64GB) Ocean Wave|6.6\" punch-hole, 5000mAh. 1 year manufacturer warranty. Box includes charging cable.|9999|8999|Blue|FREE|66|4783|88",
            "infinix_hot_9_mobile|Infinix Hot 9 (4GB RAM, 64GB) Violet|16MP quad camera. 1 year manufacturer warranty. Box includes charging cable.|9699|8999|Purple|FREE|131|3705|72",
            "infinix_hot_9_mobile|Infinix Hot 9 Pro (4GB RAM, 64GB) Quetzal Cyan|48MP camera. 1 year manufacturer warranty. Box includes charging cable.|11499|9999|Teal|FREE|76|3924|79",
            "infinix_hot_9_mobile|Infinix Hot 9 (4GB RAM, 64GB) Quetzal Cyan|Helio A25. 1 year manufacturer warranty. Box includes charging cable.|9999|8999|Teal|FREE|221|4449|13",
            "infinix_hot_9_mobile|Infinix Hot 9 Pro (4GB RAM, 64GB) Violet|Fingerprint sensor. 1 year manufacturer warranty. Box includes charging cable.|10599|9999|Purple|FREE|209|775|92",
            "infinix_hot_9_mobile|Infinix Hot 9 Pro (4GB RAM, 64GB) Ocean Wave|Dual SIM 4G. 1 year manufacturer warranty. Box includes charging cable.|11299|9999|Blue|FREE|50|298|1",
            // ---- iqoo_3_mobile
            "iqoo_3_mobile|iQOO 3 5G (8GB RAM, 256GB) Volcano Orange|Snapdragon 865, 55W FlashCharge. 1 year manufacturer warranty. Box includes charging cable.|42699|34990|Orange|FREE|242|3265|35",
            "iqoo_3_mobile|iQOO 3 (8GB RAM, 128GB) Tornado Black|Pressure-sensitive shoulder keys. 1 year manufacturer warranty. Box includes charging cable.|33399|29990|Black|FREE|232|3872|60",
            "iqoo_3_mobile|iQOO 3 (12GB RAM, 256GB) Quantum Silver|4400mAh, quad camera. 1 year manufacturer warranty. Box includes charging cable.|44099|36990|Silver|FREE|26|3467|73",
            "iqoo_3_mobile|iQOO 3 5G (12GB RAM, 256GB) Tornado Black|UFS 3.1 storage. 1 year manufacturer warranty. Box includes charging cable.|43899|38990|Black|FREE|63|2277|34",
            "iqoo_3_mobile|iQOO 3 (8GB RAM, 256GB) Quantum Silver|Super AMOLED. 1 year manufacturer warranty. Box includes charging cable.|35999|31990|Silver|FREE|172|708|28",
            "iqoo_3_mobile|iQOO 3 (8GB RAM, 128GB) Volcano Orange|Dual-turbo performance. 1 year manufacturer warranty. Box includes charging cable.|32599|29990|Orange|FREE|97|1687|71",
            // ---- iphone_se_mobile
            "iphone_se_mobile|Apple iPhone SE 3rd Gen (64GB) Midnight|A15 Bionic, 5G, Touch ID. 1 year manufacturer warranty. Box includes charging cable.|48299|43900|Black|FREE|215|3394|98",
            "iphone_se_mobile|Apple iPhone SE 3rd Gen (128GB) Starlight|4.7\" Retina HD. 1 year manufacturer warranty. Box includes charging cable.|54399|48900|Off White|FREE|211|4017|6",
            "iphone_se_mobile|Apple iPhone SE 3rd Gen (256GB) Product Red|12MP camera. 1 year manufacturer warranty. Box includes charging cable.|69299|58900|Red|FREE|38|3521|88",
            "iphone_se_mobile|Apple iPhone SE 3rd Gen (128GB) Midnight|IP67. 1 year manufacturer warranty. Box includes charging cable.|52599|48900|Black|FREE|208|3530|62",
            "iphone_se_mobile|Apple iPhone SE 3rd Gen (64GB) Starlight|Wireless charging. 1 year manufacturer warranty. Box includes charging cable.|50499|43900|Off White|FREE|219|2736|19",
            "iphone_se_mobile|Apple iPhone SE 2nd Gen (128GB) White|A13 Bionic. 1 year manufacturer warranty. Box includes charging cable.|37499|32999|White|FREE|238|838|17",
            // ---- motorola_razr_mobile
            "motorola_razr_mobile|Motorola razr 50 Ultra (12GB RAM, 512GB)|4\" external display, flip. 1 year manufacturer warranty. Box includes charging cable.|114999|99999|Silver|FREE|43|4741|5",
            "motorola_razr_mobile|Motorola razr 50 (8GB RAM, 256GB)|3.6\" external display. 1 year manufacturer warranty. Box includes charging cable.|72299|64999|Black|FREE|125|2518|23",
            "motorola_razr_mobile|Motorola razr 40 Ultra (8GB RAM, 256GB)|165Hz pOLED. 1 year manufacturer warranty. Box includes charging cable.|77799|69999|Blue|FREE|106|1375|57",
            "motorola_razr_mobile|Motorola razr 40 (8GB RAM, 256GB)|Vegan leather finish. 1 year manufacturer warranty. Box includes charging cable.|57499|49999|White|FREE|168|1191|1",
            "motorola_razr_mobile|Motorola razr 50 Ultra (12GB RAM, 512GB) Spring Green|Gemini AI. 1 year manufacturer warranty. Box includes charging cable.|111199|99999|Green|FREE|151|2054|65",
            "motorola_razr_mobile|Motorola razr 40 Ultra (8GB RAM, 256GB) Viva Magenta|Flex view. 1 year manufacturer warranty. Box includes charging cable.|85399|69999|Magenta|FREE|173|3424|102",
            // ---- realme_narzo_10a_mobile
            "realme_narzo_10a_mobile|realme Narzo 10A (3GB RAM, 32GB) So Blue|5000mAh, triple camera. 1 year manufacturer warranty. Box includes charging cable.|9299|8499|Blue|FREE|122|2956|93",
            "realme_narzo_10a_mobile|realme Narzo 10A (4GB RAM, 64GB) So White|Helio G70. 1 year manufacturer warranty. Box includes charging cable.|10999|9499|White|FREE|91|4799|28",
            "realme_narzo_10a_mobile|realme Narzo 10A (3GB RAM, 32GB) So White|6.5\" display. 1 year manufacturer warranty. Box includes charging cable.|8999|8499|White|FREE|180|2205|5",
            "realme_narzo_10a_mobile|realme Narzo 10A (4GB RAM, 64GB) So Blue|Reverse charging. 1 year manufacturer warranty. Box includes charging cable.|10199|9499|Blue|FREE|148|3201|11",
            "realme_narzo_10a_mobile|realme Narzo 10A (4GB RAM, 64GB) So Blue Bundle|With back cover. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|30|152|32",
            "realme_narzo_10a_mobile|realme Narzo 10A (3GB RAM, 32GB) So Blue Combo|With screen guard. 1 year manufacturer warranty. Box includes charging cable.|8899|8499|Blue|FREE|169|1938|53",
            // ---- motorola_g8_power_lite_mobile
            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Royal Blue|5000mAh, 3-day battery. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|220|1418|119",
            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Arctic Blue|16MP triple camera. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|200|2370|88",
            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Royal Blue Combo|With case. 1 year manufacturer warranty. Box includes charging cable.|9999|9499|Blue|FREE|186|2580|56",
            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Arctic Blue Combo|With screen guard. 1 year manufacturer warranty. Box includes charging cable.|10999|9499|Blue|FREE|167|3894|98",
            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Polar Blue|Android 9 Pie. 1 year manufacturer warranty. Box includes charging cable.|10499|9499|Blue|FREE|176|3030|57",
            "motorola_g8_power_lite_mobile|Motorola g8 power lite (4GB RAM, 64GB) Steel Grey|Water-repellent design. 1 year manufacturer warranty. Box includes charging cable.|11099|9499|Grey|FREE|237|1791|115",
            // ---- mobile_cases
            "mobile_cases|Spigen Tough Armor Case for iPhone 15|Military-grade drop protection. Quality checked. Easy returns.|2399|1499|White|FREE|110|4264|82",
            "mobile_cases|Casekaro Silicone Case for Galaxy S24|Soft-touch. Quality checked. Easy returns.|809|409|Black|FREE|44|1462|110",
            "mobile_cases|DailyObjects Clear MagSafe Case|Magnetic. Quality checked. Easy returns.|1899|1399|Green|FREE|51|3018|119",
            "mobile_cases|Ringke Leather Wallet Flip Cover|Card slots. Quality checked. Easy returns.|859|459|Beige|FREE|113|2007|47",
            "mobile_cases|Apple Kickstand Rugged Case|Built-in stand. Quality checked. Easy returns.|2999|1499|Beige|FREE|135|2424|98",
            "mobile_cases|Samsung Printed Designer Case|Matte print. Quality checked. Easy returns.|2499|1499|White|FREE|18|1932|29",
            // ---- headphones_headsets
            "headphones_headsets|Sony WH-1000XM5 Wireless ANC Headphones|Industry-leading noise cancelling, 30h battery. 1 year manufacturer warranty.|46199|29990|Gold|FREE|155|1662|33",
            "headphones_headsets|boAt Rockerz 450 Bluetooth Headphones|15h playback, padded ear cushions. 1 year manufacturer warranty.|2299|1499|Grey|FREE|43|3749|39",
            "headphones_headsets|JBL Tune 760NC Over-Ear Headphones|Active noise cancelling. 1 year manufacturer warranty.|6899|5999|White|FREE|17|558|26",
            "headphones_headsets|Sennheiser HD 450BT Wireless Headphones|Bass boost, ANC. 1 year manufacturer warranty.|11499|9990|Blue|FREE|134|2879|118",
            "headphones_headsets|Bose QuietComfort Ultra Headphones|Immersive spatial audio. 1 year manufacturer warranty.|40399|35900|Black|FREE|140|851|78",
            "headphones_headsets|OnePlus Bullets Wireless Z2 Neckband|30h battery, fast charge. 1 year manufacturer warranty.|2299|1999|Blue|FREE|69|1733|3",
            // ---- power_banks
            "power_banks|Mi 20000mAh Power Bank 22.5W|Dual USB + Type-C. 1 year manufacturer warranty.|2299|1599|Grey|FREE|52|4094|33",
            "power_banks|Ambrane 10000mAh Slim Power Bank|Pocket size. 1 year manufacturer warranty.|2199|1799|White|FREE|102|3882|118",
            "power_banks|Anker MagSafe Magnetic Power Bank 5000mAh|Wireless. 1 year manufacturer warranty.|4399|3499|Grey|FREE|168|3247|107",
            "power_banks|boAt Laptop Power Bank 25000mAh 65W|PD fast charge. 1 year manufacturer warranty.|1299|889|Silver|FREE|160|2356|87",
            "power_banks|Samsung Compact 10000mAh with Built-in Cable|No extra cable. 1 year manufacturer warranty.|2799|2199|Gold|FREE|174|2962|19",
            "power_banks|URBN Solar Power Bank 20000mAh|Outdoor rugged. 1 year manufacturer warranty.|1099|759|Blue|FREE|200|3986|102",
            // ---- screenguards
            "screenguards|Spigen Tempered Glass for iPhone 15|9H hardness, edge-to-edge. Quality checked. Easy returns.|869|689|Beige|FREE|193|949|95",
            "screenguards|Gorilla Privacy Screen Guard|Anti-spy. Quality checked. Easy returns.|1599|939|Gold|FREE|231|2004|37",
            "screenguards|Tempered Glass Co Matte Anti-Glare Guard|Fingerprint resistant. Quality checked. Easy returns.|1699|659|Navy Blue|FREE|154|2347|21",
            "screenguards|Ringke Camera Lens Protector|Full coverage. Quality checked. Easy returns.|1399|849|Navy Blue|FREE|46|3387|87",
            "screenguards|Belkin UV Glass for Curved Display|Liquid glue. Quality checked. Easy returns.|1899|919|Mustard|FREE|100|642|55",
            "screenguards|OtterBox Hydrogel Film Pack of 2|Self-healing. Quality checked. Easy returns.|309|169|Olive|FREE|53|4497|70",
            // ---- memory_cards
            "memory_cards|SanDisk Ultra microSD 128GB Class 10|Up to 140MB/s. 1 year manufacturer warranty.|2999|2399|Silver|FREE|178|1547|20",
            "memory_cards|Samsung EVO Plus microSD 256GB|4K ready. 1 year manufacturer warranty.|3699|2499|Silver|FREE|246|2664|72",
            "memory_cards|Kingston Extreme microSD 64GB|For action cameras. 1 year manufacturer warranty.|809|659|Silver|FREE|52|4214|38",
            "memory_cards|HP Canvas Go microSD 512GB|A2 rated. 1 year manufacturer warranty.|1699|1499|White|FREE|96|964|103",
            "memory_cards|Lexar SD Card 64GB for DSLR|UHS-I. 1 year manufacturer warranty.|3299|2599|Green|FREE|158|4039|18",
            "memory_cards|Sony microSD 32GB with Adapter|Everyday. 1 year manufacturer warranty.|3099|2199|Grey|FREE|25|595|92",
            // ---- smart_headphones
            "smart_headphones|Apple AirPods Pro (2nd Gen)|Adaptive audio, USB-C. 1 year manufacturer warranty.|27999|24900|Grey|FREE|50|1947|52",
            "smart_headphones|Samsung Galaxy Buds2 Pro|24-bit Hi-Fi, ANC. 1 year manufacturer warranty.|17399|11999|Blue|FREE|51|2465|17",
            "smart_headphones|OnePlus Buds 3 TWS|49dB ANC. 1 year manufacturer warranty.|6399|5499|Black|FREE|40|2711|51",
            "smart_headphones|Nothing Ear (a) TWS|Transparent design. 1 year manufacturer warranty.|8899|5999|Green|FREE|238|3032|82",
            "smart_headphones|Sony WF-1000XM5 Earbuds|Best-in-class ANC. 1 year manufacturer warranty.|23799|19990|Grey|FREE|80|680|78",
            "smart_headphones|boAt Airdopes 141 TWS|42h playback. 1 year manufacturer warranty.|1799|1299|Green|FREE|100|3335|90",
            // ---- mobile_cables
            "mobile_cables|Anker USB-C to USB-C Cable 1.5m 100W|Braided. 1 year manufacturer warranty.|999|759|Grey|FREE|21|1742|51",
            "mobile_cables|Belkin Lightning to USB-C Cable|MFi certified. 1 year manufacturer warranty.|1599|1399|White|FREE|183|1318|47",
            "mobile_cables|Apple 3-in-1 Multi Charging Cable|Type-C, Micro, Lightning. 1 year manufacturer warranty.|719|589|White|FREE|103|3811|88",
            "mobile_cables|Ambrane Magnetic Charging Cable|Easy snap. 1 year manufacturer warranty.|1699|1399|Black|FREE|93|979|55",
            "mobile_cables|boAt Fast Charging Type-C Cable 2m|65W. 1 year manufacturer warranty.|689|569|Blue|FREE|177|1553|25",
            "mobile_cables|Mi Retractable Charging Cable|Travel friendly. 1 year manufacturer warranty.|639|469|Silver|FREE|170|2540|85",
            // ---- mobile_chargers
            "mobile_chargers|Apple 20W USB-C Power Adapter|Fast charge. 1 year manufacturer warranty.|2299|1799|White|FREE|35|3779|54",
            "mobile_chargers|Samsung 45W Super Fast Charger|PPS support. 1 year manufacturer warranty.|2599|2099|White|FREE|234|4239|33",
            "mobile_chargers|Anker 65W GaN Charger 3-Port|Laptop + phone. 1 year manufacturer warranty.|3199|2699|Grey|FREE|54|767|44",
            "mobile_chargers|Mi Wireless Charging Pad 15W|Qi certified. 1 year manufacturer warranty.|3199|2499|White|FREE|19|463|56",
            "mobile_chargers|Ambrane 3-in-1 Wireless Charging Stand|Phone, watch, buds. 1 year manufacturer warranty.|2699|1799|Green|FREE|43|241|13",
            "mobile_chargers|Belkin Car Charger Dual Port|PD 30W. 1 year manufacturer warranty.|3499|2799|Black|FREE|117|1146|74",
            // ---- mobile_holders
            "mobile_holders|Portronics Car Dashboard Phone Holder|360° rotation. Quality checked. Easy returns.|2199|769|Beige|FREE|211|2765|75",
            "mobile_holders|Spigen Bike Phone Mount|Anti-shake. Quality checked. Easy returns.|2999|1399|Mustard|FREE|121|2134|28",
            "mobile_holders|Ambrane Desk Phone Stand|Adjustable aluminium. Quality checked. Easy returns.|2699|1199|Brown|FREE|172|4028|10",
            "mobile_holders|STRIFF Magnetic Car Vent Mount|Strong magnet. Quality checked. Easy returns.|569|449|Grey Melange|FREE|236|3502|85",
            "mobile_holders|Belkin Bed Gooseneck Holder|Flexible arm. Quality checked. Easy returns.|1399|929|Charcoal|FREE|142|4646|7",
            "mobile_holders|boAt Tripod with Phone Holder|Bluetooth remote. Quality checked. Easy returns.|2999|1499|Maroon|FREE|199|702|94",
            // ---- smart_watches
            "smart_watches|Apple Watch Series 9 GPS 45mm|Always-on Retina, ECG, S9 chip. 1 year manufacturer warranty.|46099|41900|Black|FREE|228|1046|88",
            "smart_watches|Samsung Galaxy Watch6 Classic 47mm|Rotating bezel, BP & ECG. 1 year manufacturer warranty.|45699|36999|Green|FREE|159|4655|93",
            "smart_watches|Noise ColorFit Pro 5 Smartwatch|1.85\" AMOLED, Bluetooth calling, 7-day battery. 1 year manufacturer warranty.|5499|3999|Green|FREE|64|2808|86",
            "smart_watches|boAt Wave Call 2 Smartwatch|1.83\" display, 700+ active modes. 1 year manufacturer warranty.|2099|1499|Silver|FREE|220|3768|24",
            "smart_watches|Fire-Boltt Phoenix Ultra Smartwatch|Stainless steel, AMOLED. 1 year manufacturer warranty.|3899|2499|Silver|FREE|196|1294|85",
            "smart_watches|Amazfit GTR 4 Smartwatch|Dual-band GPS, 14-day battery. 1 year manufacturer warranty.|22399|16999|Blue|FREE|233|2695|23",
            // ---- smart_glasses_vr
            "smart_glasses_vr|Meta Quest 3 VR Headset 128GB|Mixed reality. 1 year manufacturer warranty.|66699|49999|Black|FREE|109|1519|1",
            "smart_glasses_vr|Lenskart Phonic Smart Audio Glasses|Open-ear audio, UV protection. 1 year manufacturer warranty.|5299|3499|White|FREE|74|3049|49",
            "smart_glasses_vr|Noise i1 Smart Glasses|Touch controls. 1 year manufacturer warranty.|7199|5999|White|FREE|186|3049|119",
            "smart_glasses_vr|boAt Lyte Smart Audio Glasses|Polarised lenses. 1 year manufacturer warranty.|3399|2999|Gold|FREE|25|4118|43",
            "smart_glasses_vr|Sony VR Box Headset for Phones|3D movies and games. 1 year manufacturer warranty.|1499|999|Silver|FREE|33|903|29",
            "smart_glasses_vr|Fastrack Ray-Ban Meta Smart Glasses|Camera and audio. 1 year manufacturer warranty.|34399|29900|Blue|FREE|122|569|39",
            // ---- smart_bands
            "smart_bands|Xiaomi Smart Band 8|1.62\" AMOLED, 16-day battery. 1 year manufacturer warranty.|4999|3499|Black|FREE|156|4643|36",
            "smart_bands|Fitbit Inspire 3 Fitness Tracker|Heart rate, sleep. 1 year manufacturer warranty.|10999|8999|Blue|FREE|140|2822|28",
            "smart_bands|Huawei Band 9|Ultra thin. 1 year manufacturer warranty.|5999|3999|Blue|FREE|126|4490|117",
            "smart_bands|Amazfit Band 7|120 sports modes. 1 year manufacturer warranty.|4399|3499|Black|FREE|71|3792|75",
            "smart_bands|Noise ColorFit Band|SpO2 tracking. 1 year manufacturer warranty.|2699|1799|Green|FREE|103|3619|67",
            "smart_bands|Samsung Galaxy Fit3|1.6\" AMOLED. 1 year manufacturer warranty.|5899|4999|Grey|FREE|188|1499|54",
            // ---- gaming_laptops
            "gaming_laptops|ASUS ROG Strix G16 (i7-13650HX, RTX 4060, 16GB, 1TB)|16\" FHD+ 165Hz, Windows 11. 1 year onsite warranty. Windows/macOS preinstalled.|151199|129990|Black|FREE|202|2382|28",
            "gaming_laptops|Lenovo Legion 5 Pro (Ryzen 7 7745HX, RTX 4070, 16GB, 1TB)|16\" WQXGA 240Hz. 1 year onsite warranty. Windows/macOS preinstalled.|170499|149990|Black|FREE|220|3455|36",
            "gaming_laptops|HP Victus 15 (i5-12450H, RTX 3050, 16GB, 512GB)|15.6\" FHD 144Hz. 1 year onsite warranty. Windows/macOS preinstalled.|77399|64990|Blue|FREE|93|3382|115",
            "gaming_laptops|Acer Nitro V 15 (i5-13420H, RTX 4050, 16GB, 512GB)|15.6\" FHD 144Hz. 1 year onsite warranty. Windows/macOS preinstalled.|99999|74990|Silver|FREE|121|4777|43",
            "gaming_laptops|MSI Katana 15 (i7-13620H, RTX 4060, 16GB, 1TB)|15.6\" FHD 144Hz. 1 year onsite warranty. Windows/macOS preinstalled.|127899|109990|Blue|FREE|47|2825|28",
            "gaming_laptops|Dell G15 Gaming (Ryzen 5 7640HS, RTX 3050, 16GB, 512GB)|15.6\" 120Hz. 1 year onsite warranty. Windows/macOS preinstalled.|87999|72990|Blue|FREE|198|1008|80",
            "gaming_laptops|ASUS TUF Gaming F15 (i5-11400H, RTX 2050, 8GB, 512GB)|Military-grade durability. 1 year onsite warranty. Windows/macOS preinstalled.|77099|56990|Grey|FREE|170|1608|110",
            // ---- desktop_pcs
            "desktop_pcs|HP All-in-One 24\" (i5-1235U, 8GB, 512GB SSD)|FHD touch display. 1 year onsite warranty. Windows/macOS preinstalled.|64699|54990|Silver|FREE|28|229|13",
            "desktop_pcs|Dell Inspiron Desktop (i3-13100, 8GB, 512GB)|Compact tower. 1 year onsite warranty. Windows/macOS preinstalled.|50699|36990|Black|FREE|23|50|45",
            "desktop_pcs|Lenovo IdeaCentre AIO 3 (Ryzen 5, 16GB, 512GB)|27\" FHD. 1 year onsite warranty. Windows/macOS preinstalled.|77399|64990|Grey|FREE|183|1897|43",
            "desktop_pcs|Apple iMac 24\" M3 (8GB, 256GB)|4.5K Retina. 1 year onsite warranty. Windows/macOS preinstalled.|160599|134900|Blue|FREE|148|3187|62",
            "desktop_pcs|ASUS ROG Strix Gaming PC (i7, RTX 4060, 16GB, 1TB)|RGB tower. 1 year onsite warranty. Windows/macOS preinstalled.|141199|119990|Grey|FREE|52|1949|36",
            "desktop_pcs|Acer Mini PC (Ryzen 7, 16GB, 512GB)|Palm-size. 1 year onsite warranty. Windows/macOS preinstalled.|53399|39990|Black|FREE|89|1991|47",
            // ---- gaming_accessories
            "gaming_accessories|Logitech Mechanical Gaming Keyboard RGB|Hot-swappable switches. 1 year manufacturer warranty.|5499|4499|Silver|FREE|181|749|57",
            "gaming_accessories|Razer Gaming Headset 7.1 Surround|Detachable mic. 1 year manufacturer warranty.|4899|3999|Gold|FREE|44|701|14",
            "gaming_accessories|HyperX DualSense Wireless Controller|Haptic feedback. 1 year manufacturer warranty.|7599|5990|Green|FREE|136|2294|21",
            "gaming_accessories|Sony Gaming Mouse 16000 DPI|Programmable buttons. 1 year manufacturer warranty.|4299|2999|Black|FREE|145|2825|58",
            "gaming_accessories|Cosmic Byte XXL RGB Mouse Pad|Stitched edges. 1 year manufacturer warranty.|1499|1299|Gold|FREE|74|1722|107",
            "gaming_accessories|Redgear Gaming Chair|Ergonomic recliner. 1 year manufacturer warranty.|16499|12999|Green|FREE|25|392|119",
            // ---- computer_accessories
            "computer_accessories|Logitech Wireless Keyboard and Mouse Combo|2.4GHz, long battery. 1 year manufacturer warranty.|4699|3599|Blue|FREE|135|2865|3",
            "computer_accessories|HP USB-C Hub 7-in-1|HDMI 4K, USB 3.0, SD. 1 year manufacturer warranty.|6699|4399|Silver|FREE|43|2556|24",
            "computer_accessories|Dell HD Webcam 1080p|Dual mic. 1 year manufacturer warranty.|4999|3699|Green|FREE|81|3694|35",
            "computer_accessories|Portronics Laptop Cooling Pad|5 fans. 1 year manufacturer warranty.|4199|3699|White|FREE|91|826|58",
            "computer_accessories|Zebronics Laptop Stand Aluminium|Adjustable height. 1 year manufacturer warranty.|539|349|White|FREE|226|1037|96",
            "computer_accessories|Lenovo Wireless Presenter|Laser pointer. 1 year manufacturer warranty.|939|789|White|FREE|226|3269|43",
            // ---- external_hard_disks
            "external_hard_disks|Seagate Expansion 1TB Portable HDD|USB 3.0. 1 year manufacturer warranty.|5199|4199|Grey|FREE|159|1918|110",
            "external_hard_disks|WD My Passport 2TB|Password protected. 1 year manufacturer warranty.|7899|6299|Silver|FREE|207|838|43",
            "external_hard_disks|Samsung T7 Portable SSD 1TB|1050MB/s. 1 year manufacturer warranty.|12699|8999|Black|FREE|121|444|65",
            "external_hard_disks|Toshiba Canvio Basics 4TB|Plug and play. 1 year manufacturer warranty.|11499|9999|Gold|FREE|132|2864|8",
            "external_hard_disks|SanDisk Extreme Portable SSD 1TB|IP65 rugged. 1 year manufacturer warranty.|11099|9499|Blue|FREE|68|2402|20",
            "external_hard_disks|Crucial X9 Pro Portable SSD 2TB|2000MB/s. 1 year manufacturer warranty.|20599|15999|Gold|FREE|49|3785|23",
            // ---- pendrives
            "pendrives|SanDisk Ultra Dual Drive 64GB Type-C|OTG. 1 year manufacturer warranty.|2899|2099|Green|FREE|118|889|87",
            "pendrives|HP Cruzer Blade 32GB|Compact. 1 year manufacturer warranty.|2399|1899|Gold|FREE|199|497|17",
            "pendrives|Kingston DataTraveler 128GB USB 3.2|Metal. 1 year manufacturer warranty.|819|529|White|FREE|165|3331|105",
            "pendrives|Samsung BAR Plus 256GB|400MB/s. 1 year manufacturer warranty.|3299|2399|Silver|FREE|164|684|8",
            "pendrives|Sony Metal Pendrive 64GB|Keyring. 1 year manufacturer warranty.|2599|1699|Blue|FREE|115|3517|102",
            "pendrives|Strontium iXpand Flash Drive 128GB|For iPhone. 1 year manufacturer warranty.|3099|2299|Green|FREE|87|2958|59",
            // ---- laptop_skins_decals
            "laptop_skins_decals|GADGETS WRAP Matte Black Laptop Skin 15.6\"|Bubble-free vinyl. Quality checked. Easy returns.|399|319|Black|FREE|145|4239|0",
            "laptop_skins_decals|Gadgetshieldz Marble Texture Laptop Skin|Full body. Quality checked. Easy returns.|2199|799|White|FREE|122|392|82",
            "laptop_skins_decals|DailyObjects Carbon Fibre Laptop Skin|Textured. Quality checked. Easy returns.|679|519|Black|FREE|243|3388|31",
            "laptop_skins_decals|Skinova Anime Print Laptop Skin|HD print. Quality checked. Easy returns.|539|409|Grey Melange|FREE|195|2334|27",
            "laptop_skins_decals|Wrapcart Wooden Texture Laptop Skin|Natural look. Quality checked. Easy returns.|519|379|Brown|FREE|124|4565|61",
            "laptop_skins_decals|The Souled Store Laptop Stickers Pack of 50|Waterproof. Quality checked. Easy returns.|1699|789|Navy Blue|FREE|92|2770|52",
            // ---- laptop_bags
            "laptop_bags|American Tourister Laptop Backpack 15.6\"|Water resistant, USB port. Quality checked. Easy returns.|4399|2099|Teal|FREE|39|4628|66",
            "laptop_bags|Wildcraft Anti-Theft Laptop Backpack|Hidden zips. Quality checked. Easy returns.|11499|3999|Teal|FREE|203|3917|71",
            "laptop_bags|Safari Laptop Sleeve 14\"|Padded neoprene. Quality checked. Easy returns.|6999|2699|Charcoal|FREE|147|4298|38",
            "laptop_bags|Skybags Messenger Laptop Bag|Shoulder strap. Quality checked. Easy returns.|6399|2399|Mustard|FREE|110|1727|87",
            "laptop_bags|HP Trolley Laptop Bag|Wheels. Quality checked. Easy returns.|3399|2199|Olive|FREE|168|2995|114",
            "laptop_bags|Dell Leather Laptop Briefcase|Office ready. Quality checked. Easy returns.|5899|3199|Silver|FREE|116|2097|107",
            // ---- mouse
            "mouse|Logitech MX Master 3S Wireless Mouse|8K DPI, silent clicks. 1 year manufacturer warranty.|12199|9995|Green|FREE|206|3779|7",
            "mouse|HP M331 Silent Wireless Mouse|Quiet clicks. 1 year manufacturer warranty.|1399|1199|White|FREE|27|1339|102",
            "mouse|Dell Ergonomic Vertical Mouse|Wrist friendly. 1 year manufacturer warranty.|2199|1499|Blue|FREE|210|1121|108",
            "mouse|Razer DeathAdder Gaming Mouse|Optical switches. 1 year manufacturer warranty.|4299|3499|Blue|FREE|180|980|81",
            "mouse|Lenovo Bluetooth Multi-Device Mouse|3 devices. 1 year manufacturer warranty.|2999|2499|Black|FREE|123|4279|119",
            "mouse|Zebronics Wired Optical Mouse|Plug and play. 1 year manufacturer warranty.|379|349|Black|FREE|164|2859|110",
            // ---- computer_peripherals
            "computer_peripherals|Logitech UPS 600VA|Power backup. 1 year manufacturer warranty.|4299|3399|Gold|FREE|80|3357|14",
            "computer_peripherals|HP USB Speakers 2.0|RGB. 1 year manufacturer warranty.|13599|9199|Green|FREE|123|4408|28",
            "computer_peripherals|APC Graphic Drawing Tablet|8192 pressure levels. 1 year manufacturer warranty.|11599|8099|Green|FREE|116|3637|95",
            "computer_peripherals|TP-Link Wi-Fi USB Adapter|Dual band. 1 year manufacturer warranty.|7199|5899|Grey|FREE|91|4371|61",
            "computer_peripherals|Zebronics Document Scanner|A4 flatbed. 1 year manufacturer warranty.|10199|7099|White|FREE|134|1745|59",
            "computer_peripherals|Portronics Surge Protector 6 Socket|Spike guard. 1 year manufacturer warranty.|5299|4199|Green|FREE|114|1169|93",
            // ---- printers_ink_cartridges
            "printers_ink_cartridges|HP Ink Tank All-in-One Printer|Print, scan, copy, Wi-Fi. 1 year manufacturer warranty.|16699|13999|Silver|FREE|151|3795|13",
            "printers_ink_cartridges|Canon Laser Printer Mono|Fast 30 ppm. 1 year manufacturer warranty.|17199|11999|Silver|FREE|158|2351|40",
            "printers_ink_cartridges|Epson EcoTank Colour Printer|Low cost per page. 1 year manufacturer warranty.|21399|15999|Silver|FREE|103|4008|49",
            "printers_ink_cartridges|Brother Photo Printer Compact|Instant photos. 1 year manufacturer warranty.|11699|8999|Gold|FREE|208|4727|80",
            "printers_ink_cartridges|HP Black Ink Cartridge|High yield. 1 year manufacturer warranty.|1499|999|Blue|FREE|161|4033|119",
            "printers_ink_cartridges|Canon Colour Ink Bottle Set|Genuine ink. 1 year manufacturer warranty.|1999|1299|White|FREE|84|1635|82",
            // ---- monitors
            "monitors|LG 27\" 4K UHD IPS Monitor|HDR10, USB-C. 1 year manufacturer warranty.|38699|28999|White|FREE|196|3649|49",
            "monitors|Samsung 24\" FHD IPS Monitor 75Hz|Eye care. 1 year manufacturer warranty.|11699|8999|Green|FREE|114|1606|94",
            "monitors|Dell 27\" QHD 165Hz Gaming Monitor|1ms. 1 year manufacturer warranty.|37899|24999|Green|FREE|203|965|0",
            "monitors|BenQ 32\" Curved Monitor|1500R curve. 1 year manufacturer warranty.|29799|21999|Blue|FREE|228|73|41",
            "monitors|ASUS 34\" Ultrawide Monitor|21:9. 1 year manufacturer warranty.|39799|34999|Silver|FREE|113|3780|1",
            "monitors|Acer 22\" FHD Monitor|Slim bezel. 1 year manufacturer warranty.|9299|6999|Green|FREE|218|1617|8",
            // ---- apple_ipads
            "apple_ipads|Apple iPad 10th Gen Wi-Fi 64GB|10.9\" Liquid Retina, A14. 1 year manufacturer warranty.|49899|34900|Gold|FREE|239|1694|117",
            "apple_ipads|Apple iPad Air M2 Wi-Fi 128GB|11\" display, Apple Pencil Pro support. 1 year manufacturer warranty.|67399|59900|Grey|FREE|127|3056|45",
            "apple_ipads|Apple iPad Pro M4 11\" 256GB|Ultra Retina XDR. 1 year manufacturer warranty.|146999|99900|White|FREE|208|3785|58",
            "apple_ipads|Apple iPad mini Wi-Fi 64GB|8.3\" compact. 1 year manufacturer warranty.|57399|49900|Silver|FREE|60|2018|63",
            "apple_ipads|Apple iPad 9th Gen Wi-Fi 64GB|10.2\" Retina. 1 year manufacturer warranty.|46199|29999|Gold|FREE|104|1862|119",
            "apple_ipads|Apple iPad Air M2 13\" 256GB|Large display. 1 year manufacturer warranty.|96299|79900|Grey|FREE|207|2024|82",
            // ---- home_audio_speakers
            "home_audio_speakers|Sony Tower Speaker 100W|Deep bass. 1 year manufacturer warranty.|14299|9999|Silver|FREE|138|4141|108",
            "home_audio_speakers|JBL Bookshelf Speakers Pair|Hi-Fi. 1 year manufacturer warranty.|10299|7999|Grey|FREE|85|2678|31",
            "home_audio_speakers|Bose Smart Speaker with Alexa|Voice control. 1 year manufacturer warranty.|5899|4499|Gold|FREE|63|3872|81",
            "home_audio_speakers|Philips Party Speaker with Lights|Karaoke mic. 1 year manufacturer warranty.|16699|14999|Gold|FREE|19|3675|2",
            "home_audio_speakers|boAt Stanmore III Speaker|Iconic design. 1 year manufacturer warranty.|48199|39999|Black|FREE|85|3685|73",
            "home_audio_speakers|Marshall Multimedia 2.1 Speaker|Subwoofer. 1 year manufacturer warranty.|4699|2999|Gold|FREE|75|4480|47",
            // ---- home_theatres
            "home_theatres|Sony 5.1 Home Theatre 600W|Dolby Digital. 1 year manufacturer warranty.|29499|19999|Gold|FREE|90|432|75",
            "home_theatres|Samsung Bluetooth Home Theatre 2.1|Wireless subwoofer. 1 year manufacturer warranty.|12199|8999|Grey|FREE|207|1292|106",
            "home_theatres|JBL Dolby Atmos 7.1 System|Immersive. 1 year manufacturer warranty.|63299|49999|White|FREE|137|4672|71",
            "home_theatres|Philips Tower Home Theatre|Floor standing. 1 year manufacturer warranty.|18099|14999|Gold|FREE|53|544|38",
            "home_theatres|Zebronics Compact Home Theatre|Space saving. 1 year manufacturer warranty.|8199|6999|Black|FREE|241|2971|82",
            "home_theatres|LG Wireless Rear Speakers Kit|Upgrade kit. 1 year manufacturer warranty.|11999|9999|Gold|FREE|202|2625|107",
            // ---- soundbars
            "soundbars|Sony Dolby Atmos Soundbar 5.1|Wireless subwoofer. 1 year manufacturer warranty.|50699|39999|White|FREE|66|2831|10",
            "soundbars|JBL 2.1 Channel Soundbar 160W|Bluetooth. 1 year manufacturer warranty.|10999|8999|Blue|FREE|53|2088|61",
            "soundbars|Samsung Smart Soundbar 700|Voice assistant. 1 year manufacturer warranty.|66699|59999|Gold|FREE|44|3056|106",
            "soundbars|boAt Compact Soundbar 60W|TV ready. 1 year manufacturer warranty.|4899|3999|Gold|FREE|233|3368|63",
            "soundbars|Bose Q-Series Soundbar|Q-Symphony. 1 year manufacturer warranty.|34799|24999|White|FREE|164|2338|47",
            "soundbars|LG Aavante Bar 1500|Multiple EQ modes. 1 year manufacturer warranty.|7499|5999|Green|FREE|36|2663|85",
            // ---- bluetooth_speakers
            "bluetooth_speakers|JBL Flip 6 Portable Speaker|IP67, 12h battery. 1 year manufacturer warranty.|11999|9999|Gold|FREE|248|3000|33",
            "bluetooth_speakers|Sony SRS-XB13 Extra Bass|Compact. 1 year manufacturer warranty.|4699|3990|Silver|FREE|201|355|111",
            "bluetooth_speakers|boAt Stone 1200 Speaker|14W, IPX7. 1 year manufacturer warranty.|3499|2999|Gold|FREE|70|826|62",
            "bluetooth_speakers|Marshall Emberton II|30h playtime. 1 year manufacturer warranty.|21799|16999|Grey|FREE|152|1217|84",
            "bluetooth_speakers|Bose SoundLink Flex|PositionIQ. 1 year manufacturer warranty.|21999|14900|White|FREE|247|863|99",
            "bluetooth_speakers|Ultimate Ears BOOM 3|360° sound. 1 year manufacturer warranty.|14699|12999|Silver|FREE|79|4530|87",
            // ---- dth_set_top_box
            "dth_set_top_box|Tata Play HD Set Top Box with 1 Month Pack|Full HD channels. 1 year manufacturer warranty.|2999|2499|Black|FREE|113|2520|50",
            "dth_set_top_box|Airtel Digital TV Android Smart Set Top Box|OTT apps. 1 year manufacturer warranty.|2199|1799|Green|FREE|173|326|118",
            "dth_set_top_box|Dish TV 4K Set Top Box|UHD. 1 year manufacturer warranty.|3599|3199|Grey|FREE|151|3393|104",
            "dth_set_top_box|d2h SD Set Top Box|Budget. 1 year manufacturer warranty.|3099|2199|Silver|FREE|145|1268|47",
            "dth_set_top_box|Sun Direct Recorder Set Top Box|Record shows. 1 year manufacturer warranty.|1899|1699|Black|FREE|192|1795|94",
            "dth_set_top_box|DD Free Dish Free Dish Receiver|No monthly fee. 1 year manufacturer warranty.|3499|3099|Silver|FREE|95|4752|77",
            // ---- dslr_mirrorless_camera
            "dslr_mirrorless_camera|Canon EOS R50 Mirrorless with 18-45mm|24.2MP APS-C, 4K video. 1 year manufacturer warranty.|84899|72990|Green|FREE|194|2729|111",
            "dslr_mirrorless_camera|Nikon Z50 Mirrorless with 16-50mm|20.9MP DX. 1 year manufacturer warranty.|114299|79990|Silver|FREE|82|1961|90",
            "dslr_mirrorless_camera|Sony Alpha 7 IV Body|33MP full-frame. 1 year manufacturer warranty.|268799|214990|Black|FREE|23|1864|10",
            "dslr_mirrorless_camera|Fujifilm X-T30 II with 18-55mm|26.1MP X-Trans. 1 year manufacturer warranty.|123299|89999|Blue|FREE|159|562|64",
            "dslr_mirrorless_camera|Panasonic Lumix G85 with 12-60mm|Dual IS. 1 year manufacturer warranty.|82199|59990|Blue|FREE|39|1283|111",
            "dslr_mirrorless_camera|Canon EOS 1500D DSLR with 18-55mm|24.1MP, Wi-Fi. 1 year manufacturer warranty.|43499|39999|Black|FREE|170|1215|45",
            // ---- compact_bridge_cameras
            "compact_bridge_cameras|Sony Cyber-shot RX100 VII|1\" sensor. 1 year manufacturer warranty.|154399|104990|Blue|FREE|99|3692|93",
            "compact_bridge_cameras|Canon PowerShot SX740 HS|40x zoom. 1 year manufacturer warranty.|48599|32990|Black|FREE|166|3248|18",
            "compact_bridge_cameras|Nikon Coolpix P950|83x zoom bridge. 1 year manufacturer warranty.|89299|74990|Grey|FREE|198|2776|97",
            "compact_bridge_cameras|Panasonic Lumix FZ1000 II|4K bridge. 1 year manufacturer warranty.|84399|69990|Blue|FREE|249|2733|41",
            "compact_bridge_cameras|Fujifilm Instax Mini 12 Instant Camera|Instant prints. 1 year manufacturer warranty.|7699|6999|White|FREE|43|3605|54",
            "compact_bridge_cameras|Kodak PIXPRO FZ55|Compact 16MP. 1 year manufacturer warranty.|13899|9999|Silver|FREE|198|2767|92",
            // ---- sports_action_camera
            "sports_action_camera|GoPro HERO12 Black|5.3K60, HyperSmooth 6.0. 1 year manufacturer warranty.|53599|37990|Black|FREE|195|2432|8",
            "sports_action_camera|DJI Osmo Action 4|1/1.3\" sensor. 1 year manufacturer warranty.|49299|32990|Green|FREE|250|1086|11",
            "sports_action_camera|Insta360 X3 360° Camera|5.7K 360. 1 year manufacturer warranty.|65799|49990|Black|FREE|38|2724|27",
            "sports_action_camera|SJCAM SJ4000 Action Camera|Budget waterproof. 1 year manufacturer warranty.|5699|4999|White|FREE|204|2103|62",
            "sports_action_camera|Akaso Brave 7 LE|4K30 dual screen. 1 year manufacturer warranty.|16099|12999|Silver|FREE|241|1575|37",
            "sports_action_camera|Sony Osmo Pocket 3|1\" CMOS gimbal camera. 1 year manufacturer warranty.|59599|49990|Green|FREE|155|2868|115",
            // ---- camera_accessories
            "camera_accessories|Digitek Camera Backpack|Padded dividers. Quality checked. Easy returns.|5199|3799|Maroon|FREE|94|4205|38",
            "camera_accessories|Godox LED Video Light|Bi-colour. Quality checked. Easy returns.|5999|3499|Silver|FREE|163|2521|44",
            "camera_accessories|SanDisk Extra Battery LP-E17|Genuine. Quality checked. Easy returns.|7399|4999|Brown|FREE|74|2163|90",
            "camera_accessories|Canon Camera Cleaning Kit|Lens pen and blower. Quality checked. Easy returns.|8299|4299|Charcoal|FREE|189|882|100",
            "camera_accessories|Nikon Ring Light 18\"|With stand. Quality checked. Easy returns.|3099|1699|Silver|FREE|190|4113|80",
            "camera_accessories|SmallRig Camera Cage Kit|Cold shoe mounts. Quality checked. Easy returns.|7399|4799|Olive|FREE|203|123|30",
            // ---- lens
            "lens|Canon RF 50mm f/1.8 STM|Nifty fifty. 1 year manufacturer warranty.|21999|17990|Blue|FREE|241|986|54",
            "lens|Nikon Z 24-70mm f/4 S|Standard zoom. 1 year manufacturer warranty.|78399|64990|Black|FREE|65|2070|32",
            "lens|Sony FE 85mm f/1.8|Portrait prime. 1 year manufacturer warranty.|68499|49990|Silver|FREE|220|4392|90",
            "lens|Sigma 18-35mm f/1.8 Art|Fast zoom. 1 year manufacturer warranty.|104499|69990|Gold|FREE|79|365|107",
            "lens|Tamron 70-300mm Telephoto Zoom|Wildlife. 1 year manufacturer warranty.|35799|29990|Silver|FREE|39|65|78",
            "lens|Fujifilm XF 35mm f/2 R WR|Weather resistant. 1 year manufacturer warranty.|38499|34990|Gold|FREE|62|2865|0",
            // ---- camera_tripods
            "camera_tripods|Digitek Aluminium Tripod 60\"|With phone holder. Quality checked. Easy returns.|7199|2699|Teal|FREE|186|3248|97",
            "camera_tripods|Manfrotto Travel Tripod with Ball Head|Compact. Quality checked. Easy returns.|18699|6899|Black|FREE|208|2923|33",
            "camera_tripods|SmallRig GorillaPod Flexible Tripod|Wrap anywhere. Quality checked. Easy returns.|9999|7999|White|FREE|134|1085|60",
            "camera_tripods|Benro Monopod|Lightweight. Quality checked. Easy returns.|7899|5599|Olive|FREE|101|1212|2",
            "camera_tripods|Osaka Video Tripod with Fluid Head|Smooth pan. Quality checked. Easy returns.|11599|7599|Charcoal|FREE|128|2504|76",
            "camera_tripods|JOBY Selfie Stick Tripod|Bluetooth remote. Quality checked. Easy returns.|10699|8299|Black|FREE|247|4305|8",
            // ---- television
            "television|Samsung Crystal 4K Vivid 55\" Smart TV|UHD, HDR10+. 1 year manufacturer warranty.|66699|47990|White|FREE|52|2542|95",
            "television|Sony Bravia 65\" 4K Google TV|X1 processor, Dolby Vision. 1 year manufacturer warranty.|159999|119990|Gold|FREE|72|1242|106",
            "television|LG OLED evo 55\" Smart TV|Self-lit pixels. 1 year manufacturer warranty.|159099|139990|Gold|FREE|241|2918|94",
            "television|Xiaomi X Pro 43\" 4K Google TV|Dolby Audio. 1 year manufacturer warranty.|33399|27999|Gold|FREE|117|4475|84",
            "television|OnePlus Y1S Pro 50\" 4K Smart TV|MEMC. 1 year manufacturer warranty.|49299|32999|Green|FREE|182|2914|1",
            "television|TCL QLED 55\" 4K Google TV|Quantum dot. 1 year manufacturer warranty.|62499|44999|Grey|FREE|137|3142|24",
            // ---- cameras
            "cameras|Canon EOS 3000D DSLR Kit|18MP beginner DSLR. 1 year manufacturer warranty.|39999|29999|Black|FREE|119|1199|112",
            "cameras|Fujifilm Instax Mini 40 Instant Camera|Retro look. 1 year manufacturer warranty.|13899|8999|Grey|FREE|150|662|42",
            "cameras|Sony ZV-1F Vlog Camera|Creator friendly. 1 year manufacturer warranty.|66699|49990|White|FREE|126|105|80",
            "cameras|Nikon Coolpix B600|60x zoom. 1 year manufacturer warranty.|27499|24990|White|FREE|100|1845|45",
            "cameras|Kodak Printomatic Instant Camera|Prints on zink. 1 year manufacturer warranty.|7899|5999|Gold|FREE|154|246|85",
            "cameras|Polaroid Now+ Instant Camera|Bluetooth app. 1 year manufacturer warranty.|18699|13999|White|FREE|192|1513|42",
    };

    // Purane seeder ke title isi tarah bante the: STYLE + " " + category name
    private static final String[] OLD_STYLES = {"Signature", "Everyday", "Classic", "Premium", "Essential", "Heritage"};

    @Override
    public void run(String... args) {
        if (!ENABLED) return;
        try {
            seed();
        } catch (Exception e) {
            // Demo data ki wajah se app kabhi band na ho
            System.out.println("[DemoDataSeeder] FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void seed() {
        // 1) Demo seller
        Seller seller = sellerRepository.findByEmail(DEMO_EMAIL);
        if (seller == null) seller = createDemoSeller();

        // 2) Categories (pehle se hain to wahi use hongi) + har category ki photos
        Map<String, Category> categories = new HashMap<>();
        Map<String, String[]> photos = new HashMap<>();
        Map<String, List<String>> groupPhotos = new HashMap<>();   // level-2 ki saari photos
        Map<String, String> oldGenericTitles = new HashMap<>();     // "categoryId|title" -> true
        for (String row : ROWS) {
            String[] r = row.split("\\|");
            String[] imgs = Arrays.copyOfRange(r, 6, r.length);
            Category c1 = findOrCreate(r[0], r[1], 1, null);
            Category c2 = findOrCreate(r[2], r[3], 2, c1);
            Category c3 = r[4].equals(r[2]) ? c2 : findOrCreate(r[4], r[5], 3, c2);
            categories.put(r[2], c2);
            categories.put(r[4], c3);
            photos.put(r[4], imgs);
            groupPhotos.computeIfAbsent(r[2], k -> new ArrayList<>()).addAll(Arrays.asList(imgs));
            for (String style : OLD_STYLES) oldGenericTitles.put(key(r[4], style + " " + r[5]), "1");
        }

        // 3) Is seller ke pehle se bane products
        List<Product> existingList = productRepository.findBySellerId(seller.getId());
        Set<String> existing = new HashSet<>();
        for (Product p : existingList) {
            if (p.getCategory() != null) existing.add(key(p.getCategory().getCategoryId(), p.getTitle()));
        }

        // 4) Naye products banao (jo pehle se hain unhe skip)
        Set<String> newTitles = new HashSet<>();
        Map<String, Integer> position = new HashMap<>();
        List<Product> toSave = new ArrayList<>();
        int skipped = 0;
        for (String row : PRODUCTS) {
            String[] f = row.split("\\|");
            String catId = f[0];
            int k = position.merge(catId, 1, Integer::sum) - 1;
            newTitles.add(key(catId, f[1]));
            if (existing.contains(key(catId, f[1]))) { skipped++; continue; }

            Category category = categories.get(catId);
            if (category == null) category = categoryRepository.findByCategoryId(catId);
            if (category == null) continue;

            String[] imgs = photos.get(catId);
            if (imgs == null) {
                List<String> g = groupPhotos.getOrDefault(catId, List.of());
                imgs = g.toArray(new String[0]);
            }
            toSave.add(buildProduct(f, category, seller, imgs, k));
        }
        productRepository.saveAll(toSave);

        // 5) Purane generic demo products hatao (order/cart/wishlist wale safe rehte hain)
        int removed = 0, kept = 0;
        if (REMOVE_OLD_GENERIC) {
            for (Product p : existingList) {
                if (p.getCategory() == null) continue;
                String k = key(p.getCategory().getCategoryId(), p.getTitle());
                boolean removedCategory = REMOVED_CATEGORIES.contains(p.getCategory().getCategoryId());
                if (!removedCategory && (!oldGenericTitles.containsKey(k) || newTitles.contains(k))) continue;
                try {
                    productRepository.deleteById(p.getId());
                    removed++;
                } catch (Exception e) {
                    kept++;
                }
            }
        }

        // 6) Report — har category me kitne products hain
        Map<String, Integer> count = new HashMap<>();
        for (Product p : productRepository.findBySellerId(seller.getId())) {
            if (p.getCategory() != null) count.merge(p.getCategory().getCategoryId(), 1, Integer::sum);
        }
        int pass = 0;
        StringBuilder fail = new StringBuilder();
        for (String catId : position.keySet()) {
            int n = count.getOrDefault(catId, 0);
            if (n >= 5) pass++;
            else fail.append("\n[DemoDataSeeder]   FAIL ").append(catId).append(" -> ").append(n);
        }
        System.out.println("[DemoDataSeeder] " + toSave.size() + " naye demo products bane, " + skipped + " pehle se the, "
                + removed + " purane generic hataye" + (kept > 0 ? " (" + kept + " order/cart/wishlist me the isliye rakhe)" : ""));
        System.out.println("[DemoDataSeeder] coverage: " + pass + "/" + position.size()
                + " categories me 5+ products ✔" + fail);
    }

    private Seller createDemoSeller() {
        Seller seller = new Seller();
        seller.setSellerName("Shopzy Studio");
        seller.setEmail(DEMO_EMAIL);
        seller.setMobile("9999999999");
        seller.setGSTIN("22AAAAA0000A1Z5");
        seller.setRole(USER_ROLE.ROLE_SELLER);
        seller.setEmailVerified(true);
        seller.setAccountStatus(AccountStatus.ACTIVE);
        seller.getBusinessDetails().setBusinessName("Shopzy Studio");
        seller.getBusinessDetails().setBusinessEmail(DEMO_EMAIL);
        seller.getBusinessDetails().setBusinessMobile("9999999999");
        seller.getPickupAddress().setName("Shopzy Studio");
        seller.getPickupAddress().setCity("Jaipur");
        seller.getPickupAddress().setState("Rajasthan");
        seller.getPickupAddress().setPinCode("302001");
        seller.getPickupAddress().setMobile("9999999999");
        return sellerRepository.save(seller);
    }

    private Category findOrCreate(String id, String name, int level, Category parent) {
        Category existing = categoryRepository.findByCategoryId(id);
        if (existing != null) return existing;
        Category c = new Category();
        c.setCategoryId(id);
        c.setName(name);
        c.setLevel(level);
        c.setParentCategory(parent);
        return categoryRepository.save(c);
    }

    // Product k ki pehli photo category ki k-th photo; doosri photo (hover) agli wali
    private List<String> imagesFor(String[] imgs, int k) {
        if (imgs == null || imgs.length == 0) {
            return new ArrayList<>(List.of("https://placehold.co/600x800/f3ebe3/2a1d17/png?text=Shopzy"));
        }
        String main = imgs[k % imgs.length];
        String other = imgs[(k + 1) % imgs.length];
        return new ArrayList<>(List.of(main, other));
    }

    private Product buildProduct(String[] f, Category category, Seller seller, String[] imgs, int k) {
        int mrp = Integer.parseInt(f[3]);
        int selling = Integer.parseInt(f[4]);
        Product p = new Product();
        p.setTitle(f[1]);
        p.setDescription(f[2]);
        p.setMrpPrice(mrp);
        p.setSellingPrice(selling);
        p.setDiscountPercent(mrp > 0 ? (int) ((mrp - selling) * 100L / mrp) : 0);
        p.setColor(f[5]);
        p.setSizes(f[6]);
        p.setQuantity(Integer.parseInt(f[7]));
        p.setNumRatings(Integer.parseInt(f[8]));
        p.setCreatedAt(LocalDateTime.now().minusDays(Integer.parseInt(f[9])));
        p.setImages(imagesFor(imgs, k));
        p.setCategory(category);
        p.setSeller(seller);
        return p;
    }

    private static String key(String categoryId, String title) {
        return categoryId + "|" + (title == null ? "" : title.trim().toLowerCase(Locale.ROOT));
    }
}