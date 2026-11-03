import { Alert, Snackbar } from '@mui/material'
import { useEffect, useState } from 'react'
import { Route, Routes, useLocation, useNavigate } from 'react-router-dom'
import Order from './Order'
import UserDetails from './UserDetails'
import SavedCards from './SavedCards'
import OrderDetails from './OrderDetails'
import { useAppDispatch, useAppSelector } from '../../../Redux Toolkit/Store'
import { performLogout } from '../../../Redux Toolkit/Customer/AuthSlice'
import { fetchUserOrderHistory } from '../../../Redux Toolkit/Customer/OrderSlice'
import Addresses from './Adresses'
import ShoppingBagOutlinedIcon from '@mui/icons-material/ShoppingBagOutlined';
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import CreditCardOutlinedIcon from '@mui/icons-material/CreditCardOutlined';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import LogoutIcon from '@mui/icons-material/Logout';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';

// Account ke saare pages ki common styling — alag CSS file ki zarurat nahi
const accCss = `
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,500;0,600;0,700;1,500;1,600&display=swap');
.acc-serif { font-family: 'Playfair Display', Georgia, serif; }
.acc-outline { color: transparent; -webkit-text-stroke: 1px rgba(201,169,110,0.16); }
.acc-rise { animation: accRise .5s ease both; }
@keyframes accRise { from { opacity: 0; transform: translateY(12px); } to { opacity: 1; transform: translateY(0); } }
.acc-cta .acc-arrow { transition: transform .35s ease; }
.acc-cta:hover .acc-arrow { transform: rotate(45deg); }
.acc-tabs::-webkit-scrollbar { display: none; }
`;

const menu = [
    { name: "Orders", path: "/account/orders", icon: <ShoppingBagOutlinedIcon sx={{ fontSize: 19 }} /> },
    { name: "Profile", path: "/account/profile", icon: <PersonOutlineIcon sx={{ fontSize: 19 }} /> },
    { name: "Saved Cards", path: "/account/saved-card", icon: <CreditCardOutlinedIcon sx={{ fontSize: 19 }} /> },
    { name: "Addresses", path: "/account/addresses", icon: <HomeOutlinedIcon sx={{ fontSize: 19 }} /> },
]

const Profile = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const dispatch = useAppDispatch()
    const { user, orders } = useAppSelector(store => store)
    const [snackbarOpen, setOpenSnackbar] = useState(false);

    const handleLogout = () => {
        dispatch(performLogout())
        navigate("/")
    }

    // Header ke "Orders" count ke liye
    useEffect(() => {
        const jwt = localStorage.getItem("jwt");
        if (jwt) dispatch(fetchUserOrderHistory(jwt));
    }, [])

    useEffect(() => {
        if (user.profileUpdated || orders.orderCanceled || user.error) {
            setOpenSnackbar(true);
        }
    }, [user.profileUpdated, orders.orderCanceled]);

    const isActive = (path: string) =>
        location.pathname === path ||
        (path === "/account/orders" && location.pathname.startsWith("/account/orders")) ||
        (path === "/account/profile" && location.pathname === "/account");

    const fullName = user.user?.fullName || "";
    const firstName = fullName.split(" ")[0];
    const totalOrders = (orders.orders || []).reduce((n, o) => n + (o.orderItems?.length || 0), 0);
    const totalAddresses = user.user?.addresses?.length || 0;

    // Login nahi hai to sundar sa message
    if (!user.user && !localStorage.getItem("jwt")) {
        return (
            <div className='bg-[#f6f1ea] min-h-[80vh] grid place-items-center px-5 relative overflow-hidden'>
                <style>{accCss}</style>
                <span className='acc-serif pointer-events-none select-none absolute -bottom-10 left-1/2 -translate-x-1/2 text-[180px] lg:text-[280px] italic leading-none text-transparent' style={{ WebkitTextStroke: "1px rgba(42,29,23,0.07)" }}>Account</span>
                <div className='relative text-center max-w-md acc-rise'>
                    <div className='mx-auto w-20 h-20 rounded-full grid place-items-center bg-[#1b2420] text-[#c9a96e] border-2 border-[#c9a96e]/40'>
                        <PersonOutlineIcon sx={{ fontSize: 36 }} />
                    </div>
                    <p className='mt-8 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>— My Account —</p>
                    <h1 className='acc-serif mt-4 text-4xl lg:text-5xl font-semibold text-[#2a1d17]'>Your account <span className='italic font-medium text-[#b4532a]'>awaits.</span></h1>
                    <p className='mt-4 text-[#2a1d17]/60'>Sign in to track orders, manage addresses and see everything you love in one place.</p>
                    <button
                        onClick={() => navigate("/login")}
                        className='acc-cta mt-8 inline-flex items-center gap-4 h-14 pl-8 pr-2 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-sm font-semibold tracking-[0.2em] uppercase'
                    >
                        Sign in
                        <span className='acc-arrow w-10 h-10 rounded-full grid place-items-center bg-[#b4532a]'><ArrowOutwardIcon sx={{ fontSize: 18 }} /></span>
                    </button>
                </div>
            </div>
        )
    }

    return (
        <div className='bg-[#f6f1ea] text-[#2a1d17] min-h-screen'>
            <style>{accCss}</style>

            {/* ---------- Header band ---------- */}
            <section className='relative overflow-hidden bg-[#1b2420] text-[#f4efe6] px-5 lg:px-20 pt-12 pb-24'>
                <span className='acc-serif acc-outline pointer-events-none select-none absolute -right-8 -bottom-12 text-[150px] lg:text-[240px] italic leading-none'>
                    Account
                </span>
                <div className='relative flex flex-col md:flex-row md:items-end justify-between gap-8'>
                    <div className='flex items-center gap-5'>
                        <div className='w-20 h-20 lg:w-24 lg:h-24 shrink-0 rounded-full grid place-items-center bg-[#b4532a] border-[3px] border-[#c9a96e]/60 shadow-xl'>
                            <span className='acc-serif text-4xl lg:text-5xl font-semibold italic text-[#f6f1ea]'>{firstName?.[0]?.toUpperCase() || "S"}</span>
                        </div>
                        <div>
                            <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#c9a96e]'>
                                <span className='w-10 h-px bg-[#c9a96e]' /> My Account
                            </div>
                            <h1 className='acc-serif mt-3 text-4xl lg:text-6xl font-semibold leading-none'>
                                Hello, <span className='italic font-medium text-[#c9a96e]'>{firstName || "there"}.</span>
                            </h1>
                            <p className='mt-3 text-sm text-[#f4efe6]/60'>{user.user?.email}</p>
                        </div>
                    </div>

                    <div className='flex gap-3'>
                        {[
                            { k: "Orders", v: String(totalOrders).padStart(2, "0") },
                            { k: "Addresses", v: String(totalAddresses).padStart(2, "0") },
                            { k: "Member", v: "Gold" },
                        ].map(s => (
                            <div key={s.k} className='min-w-[92px] px-4 py-3 rounded-2xl border border-[#f4efe6]/15 bg-[#f4efe6]/[0.04]'>
                                <p className='acc-serif text-2xl font-semibold text-[#f4efe6]'>{s.v}</p>
                                <p className='text-[10px] font-semibold tracking-[0.25em] uppercase text-[#c9a96e] mt-1'>{s.k}</p>
                            </div>
                        ))}
                    </div>
                </div>
            </section>

            {/* ---------- Body ---------- */}
            <section className='px-5 lg:px-20 -mt-12 pb-24 relative'>
                <div className='grid grid-cols-1 lg:grid-cols-[280px_1fr] gap-6 lg:gap-10'>

                    {/* Sidebar */}
                    <aside className='lg:sticky lg:top-32 self-start rounded-[28px] bg-[#fbf8f4] border border-[#2a1d17]/10 shadow-[0_30px_60px_-35px_rgba(42,29,23,0.35)] p-3'>
                        <nav className='acc-tabs flex lg:flex-col gap-1 overflow-x-auto'>
                            {menu.map((item, index) => {
                                const active = isActive(item.path);
                                return (
                                    <button
                                        key={item.path}
                                        onClick={() => navigate(item.path)}
                                        className={`group shrink-0 flex items-center gap-3 px-4 py-3.5 rounded-2xl text-left transition-all ${active
                                            ? "bg-[#2a1d17] text-[#f6f1ea] shadow-lg"
                                            : "text-[#2a1d17]/70 hover:bg-[#f3ebe3] hover:text-[#2a1d17]"}`}
                                    >
                                        <span className={`acc-serif italic text-xs ${active ? "text-[#c9a96e]" : "text-[#b4532a]"}`}>{String(index + 1).padStart(2, "0")}</span>
                                        <span className={active ? "text-[#c9a96e]" : ""}>{item.icon}</span>
                                        <span className='text-sm font-semibold whitespace-nowrap'>{item.name}</span>
                                        {active && <span className='hidden lg:block ml-auto w-1.5 h-1.5 rounded-full bg-[#b4532a]' />}
                                    </button>
                                );
                            })}
                            <div className='hidden lg:block h-px bg-[#2a1d17]/10 my-2 mx-3' />
                            <button
                                onClick={handleLogout}
                                className='shrink-0 flex items-center gap-3 px-4 py-3.5 rounded-2xl text-[#b4532a] hover:bg-[#b4532a]/10 transition-colors'
                            >
                                <span className='acc-serif italic text-xs'>05</span>
                                <LogoutIcon sx={{ fontSize: 19 }} />
                                <span className='text-sm font-semibold whitespace-nowrap'>Logout</span>
                            </button>
                        </nav>

                        <div className='hidden lg:block mt-3 rounded-2xl bg-[#1b2420] text-[#f4efe6] p-5 relative overflow-hidden'>
                            <span className='acc-serif absolute -right-3 -bottom-6 text-[90px] italic leading-none text-transparent' style={{ WebkitTextStroke: "1px rgba(201,169,110,0.2)" }}>✦</span>
                            <p className='acc-serif text-lg'>Fresh <span className='italic text-[#c9a96e]'>edits</span> weekly</p>
                            <p className='text-xs text-[#f4efe6]/60 mt-1'>New arrivals and offers are waiting for you.</p>
                            <button onClick={() => navigate("/")} className='mt-4 text-[11px] font-semibold tracking-[0.2em] uppercase text-[#c9a96e] hover:underline underline-offset-4'>Continue shopping →</button>
                        </div>
                    </aside>

                    {/* Content */}
                    <div className='rounded-[28px] bg-[#fbf8f4] border border-[#2a1d17]/10 shadow-[0_30px_60px_-35px_rgba(42,29,23,0.35)] p-5 lg:p-10 min-h-[60vh]'>
                        <Routes>
                            <Route path='/' element={<UserDetails />} />
                            <Route path='/orders' element={<Order />} />
                            <Route path='/orders/:orderId/:orderItemId' element={<OrderDetails />} />
                            <Route path='/profile' element={<UserDetails />} />
                            <Route path='/saved-card' element={<SavedCards />} />
                            <Route path='/addresses' element={<Addresses />} />
                        </Routes>
                    </div>
                </div>
            </section>

            <Snackbar
                anchorOrigin={{ vertical: "top", horizontal: "right" }}
                open={snackbarOpen}
                autoHideDuration={6000}
                onClose={() => setOpenSnackbar(false)}
            >
                <Alert
                    onClose={() => setOpenSnackbar(false)}
                    severity={user.error ? "error" : "success"}
                    variant="filled"
                    sx={{ width: "100%", borderRadius: 999, bgcolor: user.error ? "#b4532a" : "#1b2420", color: "#f4efe6", "& .MuiAlert-icon": { color: "#c9a96e" } }}
                >
                    {user.error ? user.error : orders.orderCanceled ? "Order cancelled successfully" : "Success"}
                </Alert>
            </Snackbar>
        </div>
    )
}

export default Profile