import React, { useState } from 'react'
import PricingCard, { FREE_SHIPPING_ABOVE, SHIPPING_FEE } from '../Cart/PricingCard'
import { Box, CircularProgress, Modal } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import AddressForm from './AddresssForm'
import AddressCard from './AddressCard'
import AddIcon from '@mui/icons-material/Add';
import CheckIcon from '@mui/icons-material/Check';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { createOrder } from '../../../Redux Toolkit/Customer/OrderSlice'
import { useAppDispatch, useAppSelector } from '../../../Redux Toolkit/Store'

// Is page ki styling yahin hai — alag CSS file ki zarurat nahi
const coCss = `
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,500;0,600;0,700;1,500;1,600&display=swap');
.co-serif, .cart-serif { font-family: 'Playfair Display', Georgia, serif; }
.co-rise { animation: coRise .5s ease both; }
@keyframes coRise { from { opacity: 0; transform: translateY(12px); } to { opacity: 1; transform: translateY(0); } }
`;

const modalStyle = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: "min(560px, calc(100vw - 32px))",
    maxHeight: "calc(100vh - 32px)",
    overflowY: "auto" as "auto",
    bgcolor: '#f6f1ea',
    borderRadius: "28px",
    boxShadow: "0 40px 80px -30px rgba(42,29,23,0.6)",
    p: { xs: 3, sm: 4 },
    outline: "none",
};

const paymentGatwayList = [
    {
        value: "RAZORPAY",
        label: "Razorpay",
        note: "UPI · Cards · Netbanking",
        available: true,
    },
    {
        value: "STRIPE",
        label: "Stripe",
        note: "International cards · Coming soon",
        available: false,
    }
]

const steps = ["Bag", "Address", "Payment"];

const AddressPage = () => {
    const navigate = useNavigate()
    const [value, setValue] = React.useState(0);
    const dispatch = useAppDispatch();
    const { user, cart, orders } = useAppSelector(store => store)
    const [paymentGateway, setPaymentGateway] = useState(paymentGatwayList[0].value);

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const addresses = user.user?.addresses || [];

    // PricingCard jaisa hi total — button par dikhane ke liye
    const itemsTotal = cart.cart?.totalSellingPrice ?? 0;
    const itemCount = cart.cart?.cartItems?.length || 0;
    const shipping = itemCount === 0 || itemsTotal >= FREE_SHIPPING_ABOVE ? 0 : SHIPPING_FEE;
    const payable = itemsTotal + shipping;

    const handleChange = (event: any) => {
        setValue(Number(event.target.value));
    };

    const handleCreateOrder = () => {
        if (addresses.length > 0)
            dispatch(createOrder({
                paymentGateway,
                address: addresses[value],
                jwt: localStorage.getItem('jwt') || ""
            }))
    }

    return (
        <div className='bg-[#f6f1ea] text-[#2a1d17] min-h-screen'>
            <style>{coCss}</style>

            <div className='px-5 lg:px-20 pt-8 pb-24'>
                {/* back + steps */}
                <div className='flex flex-wrap items-center justify-between gap-4'>
                    <button
                        onClick={() => navigate("/cart")}
                        className='inline-flex items-center gap-2 text-[11px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/55 hover:text-[#b4532a] transition-colors'
                    >
                        <ArrowBackIcon sx={{ fontSize: 16 }} /> Back to bag
                    </button>

                    <ol className='flex items-center gap-2 sm:gap-3 text-[11px] font-semibold tracking-[0.2em] uppercase'>
                        {steps.map((s, i) => {
                            const done = i < 1, active = i === 1;
                            return (
                                <li key={s} className='flex items-center gap-2 sm:gap-3'>
                                    <span className={`flex items-center gap-2 ${active ? "text-[#2a1d17]" : done ? "text-[#5b7a5a]" : "text-[#2a1d17]/35"}`}>
                                        <span className={`w-6 h-6 rounded-full grid place-items-center text-[10px] ${active
                                            ? "bg-[#2a1d17] text-[#c9a96e]"
                                            : done ? "bg-[#5b7a5a] text-[#f6f1ea]" : "border border-[#2a1d17]/20"}`}>
                                            {done ? <CheckIcon sx={{ fontSize: 13 }} /> : <span className='co-serif italic'>{String(i + 1).padStart(2, "0")}</span>}
                                        </span>
                                        <span className='hidden sm:inline'>{s}</span>
                                    </span>
                                    {i < steps.length - 1 && <span className='w-6 sm:w-10 h-px bg-[#2a1d17]/20' />}
                                </li>
                            );
                        })}
                    </ol>
                </div>

                {/* heading */}
                <div className='mt-10'>
                    <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
                        <span className='w-10 h-px bg-[#b4532a]' /> Checkout
                    </div>
                    <h1 className='co-serif mt-3 text-4xl lg:text-5xl font-semibold'>
                        Where should we <span className='italic font-medium text-[#b4532a]'>deliver?</span>
                    </h1>
                </div>

                <div className='mt-10 grid grid-cols-1 lg:grid-cols-[1fr_400px] gap-8 lg:gap-12'>

                    {/* ---------- Left: addresses ---------- */}
                    <div className='co-rise'>
                        <div className='flex items-center justify-between pb-4'>
                            <p className='text-[11px] font-semibold tracking-[0.3em] uppercase text-[#2a1d17]/55'>
                                Saved addresses <span className='co-serif italic text-[#b4532a] normal-case tracking-normal'>{String(addresses.length).padStart(2, "0")}</span>
                            </p>
                        </div>

                        <div className='space-y-3'>
                            {addresses.map((item, index) => (
                                <AddressCard
                                    key={item.id || index}
                                    item={item}
                                    selectedValue={value}
                                    value={index}
                                    handleChange={handleChange}
                                />
                            ))}

                            {/* naya address */}
                            <button
                                onClick={handleOpen}
                                className='w-full flex items-center gap-4 p-5 rounded-[24px] border border-dashed border-[#2a1d17]/25 hover:border-[#b4532a] hover:bg-[#fbf8f4] text-left transition-colors group'
                            >
                                <span className='w-10 h-10 rounded-full grid place-items-center bg-[#1b2420] text-[#c9a96e] group-hover:bg-[#b4532a] group-hover:text-[#f6f1ea] transition-colors'>
                                    <AddIcon sx={{ fontSize: 20 }} />
                                </span>
                                <span>
                                    <span className='co-serif block text-lg font-semibold'>Add a new address</span>
                                    <span className='text-xs text-[#2a1d17]/55'>Home, office or anywhere else</span>
                                </span>
                            </button>
                        </div>
                    </div>

                    {/* ---------- Right: payment + summary ---------- */}
                    <div className='lg:sticky lg:top-32 self-start space-y-5'>
                        {/* payment method */}
                        <section className='rounded-[28px] bg-[#fbf8f4] border border-[#2a1d17]/10 p-5'>
                            <p className='text-[11px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]'>Pay with</p>
                            <div className='mt-4 space-y-2'>
                                {paymentGatwayList.map((g) => {
                                    const selected = paymentGateway === g.value;
                                    return (
                                        <button
                                            key={g.value}
                                            disabled={!g.available}
                                            onClick={() => setPaymentGateway(g.value)}
                                            className={`w-full flex items-center gap-3 p-4 rounded-2xl border text-left transition-all disabled:opacity-45 disabled:cursor-not-allowed ${selected
                                                ? "border-[#2a1d17] bg-[#f6f1ea]"
                                                : "border-[#2a1d17]/10 hover:border-[#b4532a]/40"}`}
                                        >
                                            <span className={`w-5 h-5 shrink-0 rounded-full grid place-items-center border ${selected ? "bg-[#2a1d17] border-[#2a1d17] text-[#c9a96e]" : "border-[#2a1d17]/25 text-transparent"}`}>
                                                <CheckIcon sx={{ fontSize: 13 }} />
                                            </span>
                                            <span className='flex-1'>
                                                <span className='co-serif block text-base font-semibold'>{g.label}</span>
                                                <span className='text-xs text-[#2a1d17]/55'>{g.note}</span>
                                            </span>
                                        </button>
                                    );
                                })}
                            </div>
                        </section>

                        {/* summary */}
                        <section className='rounded-[28px] bg-[#fbf8f4] border border-[#2a1d17]/10 overflow-hidden'>
                            <div className='px-5 pt-5'>
                                <p className='text-[11px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]'>Summary</p>
                                <p className='co-serif text-2xl font-semibold'>Price <span className='italic font-medium text-[#b4532a]'>details</span></p>
                            </div>
                            <PricingCard />
                            <div className='p-5 pt-3'>
                                <button
                                    onClick={handleCreateOrder}
                                    disabled={addresses.length === 0 || itemCount === 0 || orders.loading}
                                    className='group w-full flex items-center justify-between h-14 pl-7 pr-2 rounded-full bg-[#2a1d17] hover:bg-[#1b2420] disabled:opacity-50 disabled:cursor-not-allowed text-[#f6f1ea] text-sm font-semibold tracking-[0.15em] uppercase transition-colors'
                                >
                                    {orders.loading ? "Redirecting…" : `Pay ₹${payable}`}
                                    <span className='w-10 h-10 rounded-full grid place-items-center bg-[#b4532a] transition-transform duration-300 group-hover:rotate-45'>
                                        {orders.loading ? <CircularProgress size={18} sx={{ color: "#f6f1ea" }} /> : <ArrowOutwardIcon sx={{ fontSize: 18 }} />}
                                    </span>
                                </button>
                                {addresses.length === 0 && (
                                    <p className='mt-3 text-center text-xs text-[#b4532a]'>Add a delivery address to continue.</p>
                                )}
                                <p className='mt-3 flex items-center justify-center gap-1.5 text-[10px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/45'>
                                    <LockOutlinedIcon sx={{ fontSize: 13 }} /> Safe & secure payments
                                </p>
                            </div>
                        </section>
                    </div>
                </div>
            </div>

            <Modal open={open} onClose={handleClose}>
                <Box sx={modalStyle}>
                    <style>{coCss}</style>
                    <AddressForm paymentGateway={paymentGateway} handleClose={handleClose} />
                </Box>
            </Modal>
        </div>
    )
}

export default AddressPage