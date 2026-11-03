import { useNavigate } from 'react-router-dom'
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward'
import { useAppSelector } from '../../../Redux Toolkit/Store'
import WishlistProductCard from './WishlistProductCard';
import "./Wishlist.css";

const Wishlist = () => {
    const navigate = useNavigate();
    const { wishlist } = useAppSelector(store => store)
    const products = wishlist.wishlist?.products || [];

    return (
        <div className='relative overflow-hidden bg-[#f6f1ea] text-[#2a1d17] min-h-[80vh]'>
            {products.length ? (
                <section className='px-5 lg:px-20 py-10 lg:py-14'>
                    {/* Heading */}
                    <div className='flex flex-wrap items-end justify-between gap-4 mb-10'>
                        <div>
                            <p className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
                                <span className='w-10 h-px bg-[#b4532a]' />
                                Saved for Later
                            </p>
                            <h1 className='wish-serif mt-3 text-4xl lg:text-5xl font-semibold leading-none'>
                                My <span className='italic font-medium text-[#b4532a]'>Wishlist</span>
                                <span className='wish-serif italic text-xl lg:text-2xl text-[#2a1d17]/40 ml-3'>({String(products.length).padStart(2, "0")})</span>
                            </h1>
                        </div>
                        <button onClick={() => navigate("/")} className='group flex items-center gap-2.5 text-sm font-semibold'>
                            <span className='relative'>
                                Continue shopping
                                <span className='absolute left-0 -bottom-1 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#2a1d17] transition-transform duration-300' />
                            </span>
                            <span className='w-9 h-9 rounded-full grid place-items-center bg-[#2a1d17] text-[#f6f1ea] group-hover:bg-[#b4532a] group-hover:rotate-45 transition-all duration-300'>
                                <ArrowOutwardIcon sx={{ fontSize: 16 }} />
                            </span>
                        </button>
                    </div>

                    {/* Cards */}
                    <div className='grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-x-5 gap-y-10'>
                        {products.map((item, i) => (
                            <div key={item.id ?? i} className='wish-in' style={{ animationDelay: `${i * 50}ms` }}>
                                <WishlistProductCard item={item} index={i} />
                            </div>
                        ))}
                    </div>
                </section>
            ) : (
                /* ---------- Khaali wishlist ---------- */
                <div className='relative min-h-[80vh] flex flex-col items-center justify-center text-center px-5 py-16'>
                    <span className='wish-serif wish-watermark pointer-events-none select-none absolute left-1/2 -translate-x-1/2 bottom-0 italic leading-none hidden md:block'>
                        Wishlist
                    </span>

                    {/* Arch ke andar dil */}
                    <div className='relative w-40 h-48 rounded-t-full bg-[#fbf8f4] border border-[#2a1d17]/10 grid place-items-center shadow-[0_30px_60px_-30px_rgba(42,29,23,0.45)]'>
                        <span className='absolute inset-2.5 rounded-t-full border border-[#c9a96e]/50' />
                        <svg viewBox="0 0 24 24" className='wish-heart w-14 h-14' fill="#f3ebe3" stroke="#b4532a" strokeWidth="1.4">
                            <path d="M12 20.5s-7.5-4.6-7.5-10.2A4.3 4.3 0 0 1 12 7.6a4.3 4.3 0 0 1 7.5 2.7c0 5.6-7.5 10.2-7.5 10.2z" />
                        </svg>
                    </div>

                    <p className='relative mt-9 flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
                        <span className='w-10 h-px bg-[#b4532a]' />
                        Your Wishlist
                        <span className='w-10 h-px bg-[#b4532a]' />
                    </p>
                    <h1 className='relative wish-serif mt-3 text-4xl lg:text-5xl font-semibold leading-tight'>
                        Nothing saved <span className='italic font-medium text-[#b4532a]'>yet.</span>
                    </h1>
                    <p className='relative mt-3 max-w-sm text-sm sm:text-base text-[#2a1d17]/60'>
                        Tap the heart on anything you love — it'll wait for you right here.
                    </p>

                    <button
                        onClick={() => navigate("/")}
                        className='wish-cta group relative mt-9 flex items-center gap-3 h-12 pl-6 pr-2 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-sm font-semibold tracking-wide'
                    >
                        Explore the Edit
                        <span className='w-8 h-8 rounded-full grid place-items-center bg-[#b4532a] text-white group-hover:rotate-45 transition-transform duration-300'>
                            <ArrowOutwardIcon sx={{ fontSize: 17 }} />
                        </span>
                    </button>
                </div>
            )}
        </div>
    )
}

export default Wishlist