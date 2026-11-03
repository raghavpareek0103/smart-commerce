import React, { useState, useEffect, type MouseEvent } from "react";
import "./ProductCard.css";
import { Box, Modal, Tooltip } from "@mui/material";
import { useNavigate } from "react-router-dom";
import type { Product } from "../../../../types/productTypes";
import { useAppDispatch, useAppSelector } from "../../../../Redux Toolkit/Store";
import { addProductToWishlist } from "../../../../Redux Toolkit/Customer/WishlistSlice";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import AutoAwesomeIcon from "@mui/icons-material/AutoAwesome";
import { isWishlisted } from "../../../../util/isWishlisted";
import ChatBot from "../../ChatBot/ChatBot";

interface ProductCardProps {
    item: Product;
}

const modalStyle = {
    position: "absolute" as const,
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: "auto",
    borderRadius: "1rem",
    boxShadow: 24,
};

const ProductCard: React.FC<ProductCardProps> = ({ item }) => {
    const [currentImage, setCurrentImage] = useState(0);
    const [isHovered, setIsHovered] = useState(false);
    const { wishlist } = useAppSelector((store) => store);
    const navigate = useNavigate();
    const dispatch = useAppDispatch();
    const [showChatBot, setShowChatBot] = useState(false);

    const handleAddWishlist = (event: MouseEvent) => {
        event.stopPropagation();
        if (item.id) dispatch(addProductToWishlist({ productId: item.id }));
    };

    // Hover par photos har 1 sec me badalti hain
    useEffect(() => {
        let interval: any;
        if (isHovered && item.images.length > 1) {
            interval = setInterval(() => {
                setCurrentImage((prev) => (prev + 1) % item.images.length);
            }, 1000);
        } else {
            setCurrentImage(0);
        }
        return () => clearInterval(interval);
    }, [isHovered, item.images.length]);

    const handleShowChatBot = (event: MouseEvent) => {
        event.stopPropagation();
        setShowChatBot(true);
    };
    const handleCloseChatBot = (e: any) => {
        e?.stopPropagation?.();
        setShowChatBot(false);
    };

    const liked = wishlist.wishlist ? isWishlisted(wishlist.wishlist, item) : false;

    return (
        <>
            <div
                onClick={() => navigate(`/product-details/${item.category?.categoryId}/${item.title}/${item.id}`)}
                className="pc-card group relative cursor-pointer"
            >
                {/* Arch photo with sliding images */}
                <div
                    className="pc-media relative aspect-[3/4] rounded-t-full overflow-hidden bg-white border border-[#2a1d17]/10"
                    onMouseEnter={() => setIsHovered(true)}
                    onMouseLeave={() => setIsHovered(false)}
                >
                    {item.images.map((image: string, index: number) => (
                        <img
                            key={index}
                            className="pc-img"
                            src={image}
                            alt={`${item.title}-${index}`}
                            style={{ transform: `translateX(${(index - currentImage) * 100}%)` }}
                        />
                    ))}

                    {/* Hover pe neeche dots + buttons */}
                    <div className="pc-actions absolute inset-x-0 bottom-0 p-3 flex flex-col items-center gap-2.5 bg-gradient-to-t from-[#2a1d17]/55 to-transparent">
                        {item.images.length > 1 && (
                            <div className="flex gap-1.5">
                                {item.images.map((_: string, index: number) => (
                                    <button
                                        key={index}
                                        onClick={(e) => { e.stopPropagation(); setCurrentImage(index); }}
                                        className={`h-1.5 rounded-full transition-all ${index === currentImage ? "w-5 bg-white" : "w-1.5 bg-white/50"}`}
                                        aria-label={`Image ${index + 1}`}
                                    />
                                ))}
                            </div>
                        )}
                        <div className="flex gap-2">
                            {wishlist.wishlist && (
                                <Tooltip title={liked ? "Saved" : "Save to wishlist"}>
                                    <button
                                        onClick={handleAddWishlist}
                                        aria-label="Wishlist"
                                        className="w-9 h-9 rounded-full grid place-items-center bg-[#fbf8f4] text-[#b4532a] hover:scale-110 transition-transform"
                                    >
                                        {liked ? <FavoriteIcon sx={{ fontSize: 18 }} /> : <FavoriteBorderIcon sx={{ fontSize: 18 }} />}
                                    </button>
                                </Tooltip>
                            )}
                            <Tooltip title="Ask Shopzy AI">
                                <button
                                    onClick={handleShowChatBot}
                                    aria-label="Ask AI about this product"
                                    className="w-9 h-9 rounded-full grid place-items-center bg-[#2a1d17] text-[#c9a96e] hover:scale-110 transition-transform"
                                >
                                    <AutoAwesomeIcon sx={{ fontSize: 17 }} />
                                </button>
                            </Tooltip>
                        </div>
                    </div>
                </div>

                {/* Discount seal */}
                {(item.discountPercent ?? 0) > 0 && (
                    <div className="absolute -top-1 -left-1 w-14 h-14 rounded-full bg-[#2a1d17] text-[#f3ebe3] grid place-items-center text-center shadow-lg">
                        <span className="leading-none">
                            <span className="pc-serif block text-base font-semibold">{item.discountPercent}%</span>
                            <span className="block text-[8px] font-semibold tracking-[0.25em] uppercase text-[#e7b48f] mt-0.5">off</span>
                        </span>
                    </div>
                )}

                {/* Text */}
                <div className="pt-4 px-1">
                    <p className="text-[10px] font-semibold tracking-[0.3em] uppercase text-[#b4532a] truncate">
                        {item.seller?.businessDetails?.businessName}
                    </p>
                    <p className="pc-serif mt-1 text-base lg:text-lg font-semibold leading-snug line-clamp-2 text-[#2a1d17] group-hover:text-[#b4532a] transition-colors">
                        {item.title}
                    </p>
                    <div className="mt-1.5 flex items-baseline gap-2">
                        <span className="pc-serif text-lg font-semibold text-[#2a1d17]">₹{item.sellingPrice}</span>
                        {item.mrpPrice > item.sellingPrice && (
                            <span className="text-xs text-[#2a1d17]/40 line-through">₹{item.mrpPrice}</span>
                        )}
                    </div>
                    <span className="mt-2 block h-px w-6 bg-[#c9a96e] group-hover:w-14 transition-all duration-500" />
                </div>
            </div>

            {showChatBot && (
                <Modal open={true} onClose={handleCloseChatBot}>
                    <Box sx={modalStyle}>
                        <ChatBot handleClose={handleCloseChatBot} productId={item.id} />
                    </Box>
                </Modal>
            )}
        </>
    );
};

export default ProductCard;