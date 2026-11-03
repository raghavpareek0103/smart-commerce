import { useEffect, useState } from 'react'
import OrderItemCard from './OrderItemCard'
import { useAppDispatch, useAppSelector } from '../../../Redux Toolkit/Store';
import { fetchUserOrderHistory } from '../../../Redux Toolkit/Customer/OrderSlice';
import { useNavigate } from 'react-router-dom';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';
import ShoppingBagOutlinedIcon from '@mui/icons-material/ShoppingBagOutlined';

const filters = [
    { key: "ALL", label: "All" },
    { key: "ACTIVE", label: "Active" },
    { key: "DELIVERED", label: "Delivered" },
    { key: "CANCELLED", label: "Cancelled" },
];

const Order = () => {
    const dispatch = useAppDispatch()
    const navigate = useNavigate()
    const { auth, orders } = useAppSelector(store => store);
    const [filter, setFilter] = useState("ALL")

    useEffect(() => {
        dispatch(fetchUserOrderHistory(localStorage.getItem("jwt") || ""))
    }, [auth.jwt])

    const allOrders = orders?.orders || [];
    const visible = allOrders.filter((o: any) => {
        if (filter === "ALL") return true;
        if (filter === "ACTIVE") return !["DELIVERED", "CANCELLED"].includes(o.orderStatus);
        return o.orderStatus === filter;
    });
    const count = (key: string) => allOrders.filter((o: any) =>
        key === "ALL" ? true : key === "ACTIVE" ? !["DELIVERED", "CANCELLED"].includes(o.orderStatus) : o.orderStatus === key
    ).reduce((n: number, o: any) => n + (o.orderItems?.length || 0), 0);

    return (
        <div className='acc-rise'>
            {/* heading */}
            <div className='flex flex-wrap items-end justify-between gap-5 pb-8 border-b border-[#2a1d17]/10'>
                <div>
                    <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
                        <span className='w-10 h-px bg-[#b4532a]' /> Order History
                    </div>
                    <h2 className='acc-serif mt-3 text-4xl lg:text-5xl font-semibold'>Your <span className='italic font-medium text-[#b4532a]'>Orders</span></h2>
                    <p className='mt-2 text-sm text-[#2a1d17]/55'>Everything you've ordered, from anytime.</p>
                </div>

                {/* filter tabs */}
                <div className='flex flex-wrap gap-2'>
                    {filters.map(f => (
                        <button
                            key={f.key}
                            onClick={() => setFilter(f.key)}
                            className={`h-10 px-4 rounded-full text-sm font-semibold border transition-all ${filter === f.key
                                ? "bg-[#2a1d17] border-[#2a1d17] text-[#f6f1ea]"
                                : "border-[#2a1d17]/15 text-[#2a1d17]/70 hover:border-[#2a1d17]/40"}`}
                        >
                            {f.label}
                            <sup className={`ml-1 acc-serif italic ${filter === f.key ? "text-[#c9a96e]" : "text-[#b4532a]"}`}>{String(count(f.key)).padStart(2, "0")}</sup>
                        </button>
                    ))}
                </div>
            </div>

            {/* list */}
            {visible.length === 0 ? (
                <div className='py-20 text-center'>
                    <div className='mx-auto w-20 h-20 rounded-t-full rounded-b-2xl grid place-items-center bg-[#f3ebe3] text-[#b4532a]'>
                        <ShoppingBagOutlinedIcon sx={{ fontSize: 34 }} />
                    </div>
                    <p className='acc-serif mt-6 text-3xl'>Nothing here <span className='italic text-[#b4532a]'>yet.</span></p>
                    <p className='mt-2 text-sm text-[#2a1d17]/55'>
                        {filter === "ALL" ? "Your orders will show up here once you shop." : "No orders in this list right now."}
                    </p>
                    <button
                        onClick={() => navigate("/")}
                        className='acc-cta mt-8 inline-flex items-center gap-4 h-12 pl-6 pr-1.5 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-sm font-semibold'
                    >
                        Start shopping
                        <span className='acc-arrow w-9 h-9 rounded-full grid place-items-center bg-[#b4532a]'><ArrowOutwardIcon sx={{ fontSize: 16 }} /></span>
                    </button>
                </div>
            ) : (
                <div className='pt-8 space-y-4'>
                    {visible.map((order: any) => order?.orderItems?.map((item: any) =>
                        <OrderItemCard key={`${order.id}-${item.id}`} item={item} order={order} />
                    ))}
                </div>
            )}
        </div>
    )
}

export default Order