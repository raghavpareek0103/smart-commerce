import { Tooltip } from '@mui/material'
import React from 'react'
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import CloseIcon from '@mui/icons-material/Close';
import { useNavigate } from 'react-router-dom';
import type { CartItem } from '../../../types/cartTypes';
import { useAppDispatch } from '../../../Redux Toolkit/Store';
import { deleteCartItem, updateCartItem } from '../../../Redux Toolkit/Customer/CartSlice';

interface CartItemProps { item: CartItem }

const CartItemCard: React.FC<CartItemProps> = ({ item }) => {
    const dispatch = useAppDispatch();
    const navigate = useNavigate();

    const handleUpdateQuantity = (value: number) => {
        dispatch(updateCartItem({ jwt: localStorage.getItem("jwt"),
            cartItemId: item.id, cartItem: { quantity: item.quantity + value } }))
    }
    const handleRemoveCartItem = () => {
        dispatch(deleteCartItem({ jwt: localStorage.getItem("jwt") || "", cartItemId: item.id }))
    }

    const product = item.product;
    const openProduct = () =>
        navigate(`/product-details/${product?.category?.categoryId}/${product?.title}/${product?.id}`)
    const off = item.mrpPrice > item.sellingPrice ? Math.round(((item.mrpPrice - item.sellingPrice) / item.mrpPrice) * 100) : 0;

    return (
        <div className='group/card relative bg-[#fbf8f4] rounded-2xl border border-[#2a1d17]/10 hover:border-[#b4532a]/30 hover:shadow-[0_20px_40px_-25px_rgba(42,29,23,0.45)] transition-all p-4 sm:p-5 flex gap-4 sm:gap-5'>
            {/* Arch photo */}
            <div onClick={openProduct} className='shrink-0 w-[92px] h-[124px] sm:w-[118px] sm:h-[150px] rounded-t-full overflow-hidden bg-white cursor-pointer'>
                <img
                    className='w-full h-full object-cover object-top group-hover/card:scale-105 transition-transform duration-500'
                    src={product?.images[0]} alt={product?.title} />
            </div>

            <div className='flex-1 min-w-0 flex flex-col justify-between'>
                <div className='pr-8'>
                    <p className='text-[10px] font-semibold tracking-[0.3em] uppercase text-[#b4532a] truncate'>
                        {product?.seller?.businessDetails.businessName}
                    </p>
                    <p onClick={openProduct} className='cart-serif mt-1 text-base sm:text-lg font-semibold leading-snug line-clamp-2 cursor-pointer hover:text-[#b4532a] transition-colors'>
                        {product?.title}
                    </p>
                    <div className='flex flex-wrap gap-2 pt-2'>
                        {item.size && <span className='text-[11px] font-semibold text-[#2a1d17]/70 border border-[#2a1d17]/15 px-2.5 py-0.5 rounded-full'>Size · {item.size}</span>}
                        <span className='text-[11px] font-semibold text-[#5b7a5a] border border-[#5b7a5a]/30 px-2.5 py-0.5 rounded-full'>7 days return</span>
                    </div>
                </div>

                <div className='flex justify-between items-end pt-4 gap-3 flex-wrap'>
                    {/* Quantity */}
                    <div className='flex items-center rounded-full border border-[#2a1d17]/15 bg-white'>
                        <button
                            disabled={item.quantity == 1}
                            onClick={() => handleUpdateQuantity(-1)}
                            aria-label="Decrease quantity"
                            className='w-9 h-9 rounded-full grid place-items-center text-[#2a1d17] hover:bg-[#f3ebe3] disabled:text-[#2a1d17]/25 disabled:hover:bg-transparent transition-colors'
                        >
                            <RemoveIcon sx={{ fontSize: 16 }} />
                        </button>
                        <span className='cart-serif w-8 text-center font-semibold'>{item.quantity}</span>
                        <button
                            onClick={() => handleUpdateQuantity(1)}
                            aria-label="Increase quantity"
                            className='w-9 h-9 rounded-full grid place-items-center text-[#2a1d17] hover:bg-[#f3ebe3] transition-colors'
                        >
                            <AddIcon sx={{ fontSize: 16 }} />
                        </button>
                    </div>

                    {/* Price */}
                    <div className='text-right'>
                        <p className='cart-serif text-xl font-semibold'>₹{item.sellingPrice}</p>
                        {off > 0 && (
                            <p className='text-xs'>
                                <span className='text-[#2a1d17]/40 line-through'>₹{item.mrpPrice}</span>
                                <span className='ml-1.5 font-semibold text-[#b4532a]'>{off}% off</span>
                            </p>
                        )}
                    </div>
                </div>
            </div>

            {/* Remove */}
            <Tooltip title="Remove">
                <button
                    onClick={handleRemoveCartItem}
                    aria-label="Remove item"
                    className='absolute top-3 right-3 w-8 h-8 rounded-full grid place-items-center text-[#2a1d17]/40 border border-transparent hover:border-[#2a1d17]/15 hover:text-[#b4532a] transition-colors'
                >
                    <CloseIcon sx={{ fontSize: 16 }} />
                </button>
            </Tooltip>
        </div>
    )
}

export default CartItemCard