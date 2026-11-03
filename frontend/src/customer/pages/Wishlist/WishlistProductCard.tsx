import React from 'react';
import { useNavigate } from 'react-router-dom';
import CloseIcon from '@mui/icons-material/Close';
import type { Product } from '../../../types/productTypes';
import { useAppDispatch } from '../../../Redux Toolkit/Store';
import { addProductToWishlist } from '../../../Redux Toolkit/Customer/WishlistSlice';

interface ProductCardProps {
    item: Product;
    index?: number;
}

const WishlistProductCard: React.FC<ProductCardProps> = ({ item, index = 0 }) => {
    const navigate = useNavigate();
    const dispatch = useAppDispatch();

    // Wishlist se hatao (same API jo pehle tha)
    const handleRemove = (e: React.MouseEvent) => {
        e.stopPropagation();
        if (item.id) dispatch(addProductToWishlist({ productId: item.id }))
    };

    const openProduct = () =>
        navigate(`/product-details/${item.category?.categoryId}/${item.title}/${item.id}`)

    return (
        <div onClick={openProduct} className='group relative cursor-pointer'>
            {/* Arch photo */}
            <div className='relative aspect-[3/4] rounded-t-full overflow-hidden bg-white border border-[#2a1d17]/10 group-hover:-translate-y-1.5 group-hover:shadow-[0_24px_40px_-24px_rgba(42,29,23,0.45)] transition-all duration-500'>
                <img
                    className='absolute inset-0 w-full h-full object-cover object-top group-hover:scale-105 transition-transform duration-700'
                    src={item.images[0]}
                    alt={item.title}
                />
            </div>

            {/* Discount seal */}
            {(item.discountPercent ?? 0) > 0 && (
                <div className='absolute -top-1 -left-1 w-14 h-14 rounded-full bg-[#2a1d17] text-[#f3ebe3] grid place-items-center text-center shadow-lg'>
                    <span className='leading-none'>
                        <span className='wish-serif block text-base font-semibold'>{item.discountPercent}%</span>
                        <span className='block text-[8px] font-semibold tracking-[0.25em] uppercase text-[#e7b48f] mt-0.5'>off</span>
                    </span>
                </div>
            )}

            {/* Remove */}
            <button
                onClick={handleRemove}
                aria-label="Remove from wishlist"
                className='absolute top-3 right-3 w-9 h-9 rounded-full grid place-items-center bg-[#fbf8f4]/90 backdrop-blur text-[#2a1d17] border border-[#2a1d17]/10 hover:bg-[#b4532a] hover:text-white hover:border-[#b4532a] transition-colors'
            >
                <CloseIcon sx={{ fontSize: 16 }} />
            </button>

            {/* Text */}
            <div className='pt-4 px-1'>
                <p className='text-[10px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]'>No. {String(index + 1).padStart(2, "0")}</p>
                <p className='wish-serif mt-1 text-base lg:text-lg font-semibold leading-snug line-clamp-2 group-hover:text-[#b4532a] transition-colors'>{item.title}</p>
                <div className='mt-1.5 flex items-baseline gap-2'>
                    <span className='wish-serif text-lg font-semibold'>₹{item.sellingPrice}</span>
                    {item.mrpPrice > item.sellingPrice && (
                        <span className='text-xs text-[#2a1d17]/40 line-through'>₹{item.mrpPrice}</span>
                    )}
                </div>
                <span className='mt-2 block h-px w-6 bg-[#c9a96e] group-hover:w-14 transition-all duration-500' />
            </div>
        </div>
    );
};

export default WishlistProductCard