import React from 'react'
import { useNavigate } from 'react-router-dom';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';
import type { Order, OrderItem } from '../../../types/orderTypes';
import { formatDate } from '../../util/fomateDate';

// Har status ka apna rang
export const statusStyle: Record<string, { label: string; dot: string; chip: string }> = {
    PENDING: { label: "Pending", dot: "bg-[#c9a96e]", chip: "bg-[#c9a96e]/15 text-[#8a6d3b]" },
    PLACED: { label: "Order Placed", dot: "bg-[#c9a96e]", chip: "bg-[#c9a96e]/15 text-[#8a6d3b]" },
    CONFIRMED: { label: "Packed", dot: "bg-[#c9a96e]", chip: "bg-[#c9a96e]/15 text-[#8a6d3b]" },
    SHIPPED: { label: "Shipped", dot: "bg-[#5b7a5a]", chip: "bg-[#5b7a5a]/15 text-[#3f5a3e]" },
    DELIVERED: { label: "Delivered", dot: "bg-[#c9a96e]", chip: "bg-[#1b2420] text-[#f4efe6]" },
    CANCELLED: { label: "Cancelled", dot: "bg-[#b4532a]", chip: "bg-[#b4532a]/10 text-[#b4532a]" },
};

interface OrderItemCardProps {
    item: OrderItem,
    order: Order
}

const OrderItemCard: React.FC<OrderItemCardProps> = ({ item, order }) => {
    const navigate = useNavigate()
    const st = statusStyle[order.orderStatus] || statusStyle.PENDING;
    const dateLine =
        order.orderStatus === "DELIVERED" ? `Delivered on ${formatDate(order.deliverDate)}`
            : order.orderStatus === "CANCELLED" ? "This order was cancelled"
                : `Arriving by ${formatDate(order.deliverDate)}`;

    return (
        <div
            onClick={() => navigate(`/account/orders/${order.id}/${item.id}`)}
            className='acc-cta group flex gap-4 lg:gap-6 p-4 lg:p-5 rounded-[24px] bg-[#f6f1ea] border border-[#2a1d17]/[0.08] hover:border-[#b4532a]/40 hover:shadow-[0_20px_40px_-25px_rgba(42,29,23,0.45)] transition-all cursor-pointer'
        >
            {/* photo */}
            <div className='w-20 lg:w-24 shrink-0 aspect-[3/4] rounded-t-full rounded-b-xl overflow-hidden bg-[#f3ebe3]'>
                <img className='w-full h-full object-cover object-top group-hover:scale-105 transition-transform duration-500' src={item.product?.images?.[0]} alt="" />
            </div>

            {/* info */}
            <div className='flex-1 min-w-0 flex flex-col'>
                <div className='flex flex-wrap items-center gap-2'>
                    <span className={`inline-flex items-center gap-1.5 px-3 py-1 rounded-full text-[10px] font-bold tracking-[0.18em] uppercase ${st.chip}`}>
                        <span className={`w-1.5 h-1.5 rounded-full ${st.dot}`} /> {st.label}
                    </span>
                    <span className='text-xs text-[#2a1d17]/50'>{dateLine}</span>
                </div>

                <p className='mt-3 text-[10px] font-semibold tracking-[0.25em] uppercase text-[#b4532a] truncate'>
                    {item.product?.seller?.businessDetails?.businessName || "Shopzy"}
                </p>
                <h3 className='acc-serif mt-1 text-lg lg:text-xl font-semibold line-clamp-2 group-hover:text-[#b4532a] transition-colors'>
                    {item.product?.title}
                </h3>

                <div className='mt-auto pt-3 flex flex-wrap items-center gap-x-4 gap-y-1 text-xs text-[#2a1d17]/55'>
                    <span>Qty <b className='text-[#2a1d17]'>{item.quantity || 1}</b></span>
                    <span>Size <b className='text-[#2a1d17]'>{item.size || "FREE"}</b></span>
                    <span className='acc-serif text-base font-semibold text-[#2a1d17]'>₹{item.sellingPrice}</span>
                </div>
            </div>

            {/* arrow */}
            <span className='acc-arrow self-center shrink-0 w-10 h-10 rounded-full hidden sm:grid place-items-center border border-[#2a1d17]/15 group-hover:bg-[#2a1d17] group-hover:text-[#f6f1ea] group-hover:border-[#2a1d17] transition-colors'>
                <ArrowOutwardIcon sx={{ fontSize: 17 }} />
            </span>
        </div>
    )
}

export default OrderItemCard