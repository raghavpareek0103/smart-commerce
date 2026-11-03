import React, { useEffect, useMemo, useRef, useState } from 'react'
import { Box, Modal, Rating, Snackbar, Alert, Avatar, IconButton } from '@mui/material';
import StarRoundedIcon from '@mui/icons-material/StarRounded';
import VerifiedOutlinedIcon from '@mui/icons-material/VerifiedOutlined';
import WorkspacePremiumOutlinedIcon from '@mui/icons-material/WorkspacePremiumOutlined';
import LocalShippingOutlinedIcon from '@mui/icons-material/LocalShippingOutlined';
import AccountBalanceWalletOutlinedIcon from '@mui/icons-material/AccountBalanceWalletOutlined';
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';
import ZoomInIcon from '@mui/icons-material/ZoomIn';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import EditOutlinedIcon from '@mui/icons-material/EditOutlined';
import LocalOfferOutlinedIcon from '@mui/icons-material/LocalOfferOutlined';
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import PlaceOutlinedIcon from '@mui/icons-material/PlaceOutlined';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import BoltIcon from '@mui/icons-material/Bolt';
import ZoomableImage from './ZoomableImage';
import { useAppDispatch, useAppSelector } from '../../../../Redux Toolkit/Store';
import { useNavigate, useParams } from 'react-router-dom';
import { fetchProductById, getAllProducts } from '../../../../Redux Toolkit/Customer/ProductSlice';
import { addItemToCart } from '../../../../Redux Toolkit/Customer/CartSlice';
import { addProductToWishlist } from '../../../../Redux Toolkit/Customer/WishlistSlice';
import { deleteReview, fetchReviewsByProductId } from '../../../../Redux Toolkit/Customer/ReviewSlice';

// Is page ki saari styling yahin hai — alag CSS file banane ki zarurat nahi
const pdCss = `
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,500;0,600;0,700;1,500;1,600&display=swap');
.pd-serif { font-family: 'Playfair Display', Georgia, serif; }
.pd-outline { color: transparent; -webkit-text-stroke: 1px rgba(42,29,23,0.08); }
.pd-outline-light { color: transparent; -webkit-text-stroke: 1px rgba(201,169,110,0.16); }
.pd-fade { animation: pdFade .5s ease both; }
@keyframes pdFade { from { opacity: 0; transform: scale(1.02); } to { opacity: 1; transform: scale(1); } }
.pd-spin { animation: pdSpin 18s linear infinite; }
@keyframes pdSpin { to { transform: rotate(360deg); } }
.pd-cta .pd-cta-arrow { transition: transform .35s ease; }
.pd-cta:hover .pd-cta-arrow { transform: rotate(45deg); }
.pd-card img { transition: transform .7s ease; }
.pd-card:hover img { transform: scale(1.06); }
.pd-thumbs::-webkit-scrollbar { display: none; }
.pd-zoom img { transition: transform .25s ease-out; }
.pd-marquee { animation: pdMarquee 30s linear infinite; }
@keyframes pdMarquee { to { transform: translateX(-50%); } }
.pd-acc { display: grid; grid-template-rows: 0fr; transition: grid-template-rows .35s ease; }
.pd-acc.open { grid-template-rows: 1fr; }
.pd-acc > div { overflow: hidden; }
.pd-bar { transition: transform .35s ease, opacity .35s ease; }

`;

const style = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: "auto",
    height: "100%",
    boxShadow: 24,
    outline: "none",
};

const features = [
    { icon: <VerifiedOutlinedIcon sx={{ fontSize: 20 }} />, title: "Authentic", text: "Quality assured" },
    { icon: <WorkspacePremiumOutlinedIcon sx={{ fontSize: 20 }} />, title: "Money back", text: "100% guarantee" },
    { icon: <LocalShippingOutlinedIcon sx={{ fontSize: 20 }} />, title: "Free shipping", text: "& easy returns" },
    { icon: <AccountBalanceWalletOutlinedIcon sx={{ fontSize: 20 }} />, title: "Pay on delivery", text: "Available" },
];

const offersList = [
    { code: "SHOPZY10", text: "Extra 10% off on prepaid orders" },
    { code: "FIRST200", text: "Flat ₹200 off on your first order above ₹999" },
    { code: "BANK5", text: "5% cashback on select bank cards" },
];

const trust = ["100% Original", "Free Delivery above ₹999", "7-Day Easy Returns", "Secure Payments", "Cash on Delivery", "Handpicked Edits"];

// Chhota heading label — "— LABEL"
const Kicker = ({ children, light }: { children: React.ReactNode; light?: boolean }) => (
    <div className={`flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase ${light ? "text-[#c9a96e]" : "text-[#b4532a]"}`}>
        <span className={`w-10 h-px ${light ? "bg-[#c9a96e]" : "bg-[#b4532a]"}`} />
        {children}
    </div>
);

const ProductDetails = () => {
    const [open, setOpen] = useState(false);
    const dispatch = useAppDispatch();
    const { products, review, wishlist, user } = useAppSelector(store => store)
    const navigate = useNavigate()
    const { productId, categoryId } = useParams()
    const [selectedImage, setSelectedImage] = useState(0);
    const [quantity, setQuantity] = useState(1)
    const [liked, setLiked] = useState(false)
    const [toast, setToast] = useState<string | null>(null)
    const [zoom, setZoom] = useState({ on: false, x: 50, y: 50 })
    const [pincode, setPincode] = useState("")
    const [delivery, setDelivery] = useState<string | null>(null)
    const [openAcc, setOpenAcc] = useState<number | null>(0)
    const [showBar, setShowBar] = useState(false)
    const ctaRef = useRef<HTMLDivElement>(null)

    const product = products.product;
    const images: string[] = product?.images || [];

    useEffect(() => {
        if (productId) {
            dispatch(fetchProductById(Number(productId)))
            dispatch(fetchReviewsByProductId({ productId: Number(productId) }))
        }
        dispatch(getAllProducts({ category: categoryId }));
        setSelectedImage(0);
        setQuantity(1);
        window.scrollTo({ top: 0, behavior: "smooth" });
    }, [productId])

    // Wishlist me pehle se hai to dil bhara hua dikhe
    useEffect(() => {
        const inList = wishlist.wishlist?.products?.some((p: any) => p.id === product?.id);
        setLiked(!!inList);
    }, [wishlist.wishlist, product?.id])

    const handleAddCart = () => {
        const jwt = localStorage.getItem('jwt');
        if (!jwt) { navigate("/login"); return; }
        dispatch(addItemToCart({
            jwt,
            request: { productId: Number(productId), size: "FREE", quantity }
        }))
        setToast("Added to your bag");
    }

    // Asli "Add to Bag" button screen se bahar jaye to mobile par neeche patti dikhe
    useEffect(() => {
        const onScroll = () => {
            const el = ctaRef.current;
            if (el) setShowBar(el.getBoundingClientRect().bottom < 0);
        };
        document.addEventListener("scroll", onScroll, true);
        return () => document.removeEventListener("scroll", onScroll, true);
    }, [])

    const handleBuyNow = () => {
        const jwt = localStorage.getItem('jwt');
        if (!jwt) { navigate("/login"); return; }
        dispatch(addItemToCart({
            jwt,
            request: { productId: Number(productId), size: "FREE", quantity }
        })).then(() => navigate("/cart"))
    }

    const copyCode = (code: string) => {
        navigator.clipboard?.writeText(code);
        setToast(`Code ${code} copied`);
    }

    const checkPincode = (e: React.FormEvent) => {
        e.preventDefault();
        if (!/^\d{6}$/.test(pincode)) { setDelivery("invalid"); return; }
        const d = new Date();
        d.setDate(d.getDate() + 3 + (Number(pincode[5]) % 3));
        setDelivery(d.toLocaleDateString("en-IN", { weekday: "short", day: "numeric", month: "short" }));
    }

    const handleWishlist = () => {
        if (!localStorage.getItem('jwt')) { navigate("/login"); return; }
        if (product?.id) dispatch(addProductToWishlist({ productId: product.id }));
        setLiked(!liked);
        setToast(liked ? "Removed from wishlist" : "Saved to wishlist");
    }

    // Reviews se asli average aur star-wise ginti
    const reviews = review.reviews || [];
    const avgRating = reviews.length ? reviews.reduce((s, r) => s + (r.rating || 0), 0) / reviews.length : 0;
    const starCounts = [5, 4, 3, 2, 1].map(star => ({
        star,
        count: reviews.filter(r => Math.round(r.rating) === star).length,
    }));

    const similar = useMemo(
        () => (products.products || []).filter((p: any) => p.id !== product?.id).slice(0, 4),
        [products.products, product?.id]
    );

    // Title ka aakhri shabd italic terracotta
    const titleWords = (product?.title || "").trim().split(" ");
    const lastWord = titleWords.length > 1 ? titleWords.pop() : "";
    const firstWords = titleWords.join(" ");

    const brand = product?.seller?.businessDetails?.businessName || "Shopzy Studio";
    const discount = product?.discountPercent ?? 0;

    return (
        <div className='bg-[#f6f1ea] text-[#2a1d17]'>
            <style>{pdCss}</style>

            {/* ---------- Main product block ---------- */}
            <section className='relative overflow-hidden px-5 lg:px-20 pt-8 pb-20'>
                {/* peeche halka watermark */}
                <span className='pd-serif pd-outline pointer-events-none select-none absolute -right-10 bottom-0 text-[160px] lg:text-[260px] italic leading-none'>
                    Shopzy
                </span>

                {/* Breadcrumb */}
                <nav className='relative flex flex-wrap items-center gap-2 text-[11px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/50 mb-8'>
                    <button onClick={() => navigate("/")} className='hover:text-[#b4532a] transition-colors'>Home</button>
                    <span className='w-4 h-px bg-[#2a1d17]/30' />
                    <button onClick={() => navigate(`/products/${categoryId}`)} className='hover:text-[#b4532a] transition-colors'>
                        {(categoryId || "").replace(/_/g, " ")}
                    </button>
                    <span className='w-4 h-px bg-[#2a1d17]/30' />
                    <span className='text-[#b4532a] truncate max-w-[200px]'>{product?.title}</span>
                </nav>

                <div className='relative grid grid-cols-1 lg:grid-cols-2 gap-10 lg:gap-16'>

                    {/* ---------- Gallery ---------- */}
                    <div className='flex flex-col-reverse lg:flex-row gap-4 lg:sticky lg:top-32 self-start'>
                        {/* thumbnails */}
                        <div className='pd-thumbs flex lg:flex-col gap-3 overflow-x-auto lg:overflow-visible lg:w-24 shrink-0'>
                            {images.map((item, index) => (
                                <button
                                    key={index}
                                    onClick={() => setSelectedImage(index)}
                                    className={`relative shrink-0 w-16 lg:w-full aspect-[3/4] rounded-t-full rounded-b-xl overflow-hidden border-2 transition-all ${selectedImage === index
                                        ? "border-[#b4532a] shadow-[0_10px_25px_-12px_rgba(180,83,42,0.7)]"
                                        : "border-transparent opacity-60 hover:opacity-100"}`}
                                >
                                    <img className='w-full h-full object-cover' src={item} alt="" />
                                </button>
                            ))}
                        </div>

                        {/* main image — arch frame */}
                        <div className='relative flex-1 self-start w-full'>
                            <div className='absolute -inset-3 lg:-inset-4 rounded-t-full border border-[#2a1d17]/15 pointer-events-none' />
                            <div
                                onClick={() => setOpen(true)}
                                onMouseMove={(e) => {
                                    const r = e.currentTarget.getBoundingClientRect();
                                    setZoom({ on: true, x: ((e.clientX - r.left) / r.width) * 100, y: ((e.clientY - r.top) / r.height) * 100 });
                                }}
                                onMouseLeave={() => setZoom(z => ({ ...z, on: false }))}
                                className='pd-zoom relative aspect-[4/5] rounded-t-full rounded-b-[28px] overflow-hidden bg-[#f3ebe3] cursor-zoom-in group'
                            >
                                {images[selectedImage] && (
                                    <img
                                        key={selectedImage}
                                        className='pd-fade w-full h-full object-cover object-top'
                                        style={{ transformOrigin: `${zoom.x}% ${zoom.y}%`, transform: zoom.on ? "scale(1.8)" : "scale(1)" }}
                                        src={images[selectedImage]}
                                        alt={product?.title}
                                    />
                                )}
                                <span className='absolute bottom-4 left-4 flex items-center gap-1.5 px-3 py-1.5 rounded-full bg-[#f6f1ea]/90 backdrop-blur text-[11px] font-semibold tracking-[0.2em] uppercase opacity-0 group-hover:opacity-100 transition-opacity'>
                                    <ZoomInIcon sx={{ fontSize: 15 }} /> Zoom
                                </span>
                                <span className='pd-serif absolute bottom-4 right-5 text-[#f6f1ea] text-sm italic drop-shadow'>
                                    {String(selectedImage + 1).padStart(2, "0")} / {String(images.length).padStart(2, "0")}
                                </span>
                            </div>

                            {/* ghoomta hua discount seal */}
                            {discount > 0 && (
                                <div className='absolute top-2 right-2 lg:top-6 lg:-right-6 w-24 h-24 lg:w-28 lg:h-28'>
                                    <svg viewBox="0 0 100 100" className='pd-spin absolute inset-0 w-full h-full'>
                                        <defs>
                                            <path id="pdCircle" d="M50,50 m-38,0 a38,38 0 1,1 76,0 a38,38 0 1,1 -76,0" />
                                        </defs>
                                        <text fontSize="9.5" letterSpacing="3" fill="#2a1d17" fontWeight="600">
                                            <textPath href="#pdCircle">LIMITED · OFFER · SHOPZY · EDIT ·</textPath>
                                        </text>
                                    </svg>
                                    <div className='absolute inset-[22%] rounded-full bg-[#b4532a] text-[#f6f1ea] grid place-items-center shadow-lg'>
                                        <span className='pd-serif text-lg lg:text-xl font-semibold leading-none text-center'>
                                            {discount}%<br /><span className='text-[9px] tracking-[0.2em] font-sans'>OFF</span>
                                        </span>
                                    </div>
                                </div>
                            )}
                        </div>

                        <Modal open={open} onClose={() => setOpen(false)}>
                            <Box sx={style}>
                                <ZoomableImage src={images[selectedImage]} alt="" />
                            </Box>
                        </Modal>
                    </div>

                    {/* ---------- Info ---------- */}
                    <div className='lg:pt-4'>
                        <Kicker>{brand}</Kicker>

                        <h1 className='pd-serif mt-4 text-4xl lg:text-5xl font-semibold leading-[1.1]'>
                            {firstWords} {lastWord && <span className='italic font-medium text-[#b4532a]'>{lastWord}</span>}
                        </h1>

                        {/* rating */}
                        <div className='mt-5 flex items-center gap-3'>
                            <Rating
                                value={avgRating || 0}
                                precision={0.5}
                                readOnly
                                icon={<StarRoundedIcon fontSize="inherit" />}
                                emptyIcon={<StarRoundedIcon fontSize="inherit" />}
                                sx={{ fontSize: 20, color: "#c9a96e", "& .MuiRating-iconEmpty": { color: "rgba(42,29,23,0.15)" } }}
                            />
                            <span className='text-sm text-[#2a1d17]/60'>
                                {reviews.length ? `${avgRating.toFixed(1)} · ${reviews.length} reviews` : "New arrival · no reviews yet"}
                            </span>
                        </div>

                        {/* price */}
                        <div className='mt-8 pb-8 border-b border-[#2a1d17]/10'>
                            <div className='flex items-end flex-wrap gap-x-4 gap-y-2'>
                                <span className='pd-serif text-5xl font-semibold leading-none'>₹{product?.sellingPrice}</span>
                                {discount > 0 && (
                                    <>
                                        <span className='text-lg text-[#2a1d17]/40 line-through mb-1'>₹{product?.mrpPrice}</span>
                                        <span className='mb-1 px-3 py-1 rounded-full bg-[#b4532a]/10 text-[#b4532a] text-xs font-bold tracking-[0.15em] uppercase'>
                                            Save {discount}%
                                        </span>
                                    </>
                                )}
                            </div>
                            <p className='mt-3 text-xs tracking-wide text-[#2a1d17]/55'>Inclusive of all taxes · Free shipping above ₹1500</p>
                        </div>

                        {/* offers */}
                        <div className='mt-8 rounded-[24px] border border-dashed border-[#b4532a]/40 bg-[#fbf8f4] p-5'>
                            <p className='flex items-center gap-2 text-[11px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]'>
                                <LocalOfferOutlinedIcon sx={{ fontSize: 16 }} /> Available offers
                            </p>
                            <div className='mt-4 space-y-3'>
                                {offersList.map(o => (
                                    <div key={o.code} className='flex items-center justify-between gap-3'>
                                        <p className='text-sm text-[#2a1d17]/75'>{o.text}</p>
                                        <button
                                            onClick={() => copyCode(o.code)}
                                            className='shrink-0 flex items-center gap-1.5 px-3 py-1.5 rounded-full border border-[#2a1d17]/15 hover:border-[#b4532a] hover:text-[#b4532a] text-[11px] font-bold tracking-[0.15em] transition-colors'
                                        >
                                            {o.code} <ContentCopyIcon sx={{ fontSize: 12 }} />
                                        </button>
                                    </div>
                                ))}
                            </div>
                        </div>

                        {/* features */}
                        <div className='grid grid-cols-2 gap-3 mt-8'>
                            {features.map(f => (
                                <div key={f.title} className='flex items-center gap-3 p-3 rounded-2xl bg-[#fbf8f4] border border-[#2a1d17]/[0.07]'>
                                    <span className='w-10 h-10 shrink-0 rounded-full grid place-items-center bg-[#1b2420] text-[#c9a96e]'>{f.icon}</span>
                                    <div className='leading-tight'>
                                        <p className='text-sm font-semibold'>{f.title}</p>
                                        <p className='text-xs text-[#2a1d17]/55'>{f.text}</p>
                                    </div>
                                </div>
                            ))}
                        </div>

                        {/* quantity */}
                        <div className='mt-8 flex items-center gap-5'>
                            <span className='text-[11px] font-semibold tracking-[0.3em] uppercase text-[#2a1d17]/60'>Quantity</span>
                            <div className='flex items-center h-12 rounded-full border border-[#2a1d17]/15 bg-[#fbf8f4]'>
                                <button
                                    disabled={quantity === 1}
                                    onClick={() => setQuantity(quantity - 1)}
                                    className='w-12 h-12 grid place-items-center rounded-full hover:text-[#b4532a] disabled:opacity-30'
                                    aria-label="Decrease"
                                >
                                    <RemoveIcon sx={{ fontSize: 18 }} />
                                </button>
                                <span className='pd-serif w-8 text-center text-xl font-semibold'>{quantity}</span>
                                <button
                                    onClick={() => setQuantity(quantity + 1)}
                                    className='w-12 h-12 grid place-items-center rounded-full hover:text-[#b4532a]'
                                    aria-label="Increase"
                                >
                                    <AddIcon sx={{ fontSize: 18 }} />
                                </button>
                            </div>
                        </div>

                        {/* CTAs */}
                        <div ref={ctaRef} className='mt-8 flex items-center gap-3'>
                            <button
                                onClick={handleAddCart}
                                className='pd-cta flex-1 flex items-center justify-between h-16 pl-8 pr-2 rounded-full bg-[#2a1d17] hover:bg-[#1b2420] text-[#f6f1ea] transition-colors'
                            >
                                <span className='text-sm font-semibold tracking-[0.25em] uppercase'>Add to Bag</span>
                                <span className='pd-cta-arrow w-12 h-12 rounded-full grid place-items-center bg-[#b4532a]'>
                                    <ArrowOutwardIcon sx={{ fontSize: 20 }} />
                                </span>
                            </button>
                            <button
                                onClick={handleWishlist}
                                aria-label="Wishlist"
                                className={`w-16 h-16 shrink-0 rounded-full grid place-items-center border transition-all hover:-translate-y-0.5 ${liked
                                    ? "bg-[#b4532a] border-[#b4532a] text-[#f6f1ea]"
                                    : "border-[#2a1d17]/20 text-[#2a1d17] hover:border-[#b4532a] hover:text-[#b4532a]"}`}
                            >
                                {liked ? <FavoriteIcon /> : <FavoriteBorderIcon />}
                            </button>
                        </div>

                        <button
                            onClick={handleBuyNow}
                            className='mt-3 w-full h-14 rounded-full border-2 border-[#2a1d17] hover:bg-[#2a1d17] hover:text-[#f6f1ea] flex items-center justify-center gap-2 text-sm font-semibold tracking-[0.25em] uppercase transition-colors'
                        >
                            <BoltIcon sx={{ fontSize: 18 }} /> Buy it now
                        </button>

                        {/* delivery check */}
                        <form onSubmit={checkPincode} className='mt-6'>
                            <div className='flex items-center h-12 rounded-full border border-[#2a1d17]/15 bg-[#fbf8f4] focus-within:border-[#b4532a] pl-4 pr-1.5 transition-colors'>
                                <PlaceOutlinedIcon sx={{ fontSize: 18, color: "#b4532a" }} />
                                <input
                                    value={pincode}
                                    onChange={(e) => { setPincode(e.target.value.replace(/\D/g, "").slice(0, 6)); setDelivery(null); }}
                                    placeholder='Enter pincode for delivery date'
                                    inputMode='numeric'
                                    className='flex-1 min-w-0 bg-transparent outline-none px-3 text-sm placeholder:text-[#2a1d17]/40'
                                />
                                <button type='submit' className='h-9 px-5 rounded-full bg-[#1b2420] text-[#f4efe6] text-xs font-semibold tracking-[0.15em] uppercase hover:bg-[#b4532a] transition-colors'>
                                    Check
                                </button>
                            </div>
                            <p className='mt-3 text-xs text-[#2a1d17]/60 flex items-center gap-2'>
                                <span className={`w-1.5 h-1.5 rounded-full ${delivery === "invalid" ? "bg-[#b4532a]" : "bg-[#5b7a5a]"}`} />
                                {delivery === "invalid"
                                    ? "Please enter a valid 6-digit pincode"
                                    : delivery
                                        ? <>Delivery by <span className='pd-serif italic font-semibold text-[#2a1d17] text-sm'>{delivery}</span> · Free · 7-day returns</>
                                        : "In stock · Usually delivered in 3–5 days · 7-day easy returns"}
                            </p>
                        </form>

                        {/* description */}
                        <div className='mt-10 border-t border-[#2a1d17]/10'>
                            {[
                                {
                                    title: "The Details",
                                    body: (
                                        <>
                                            <p className='text-[15px] leading-relaxed text-[#2a1d17]/75'>{product?.description}</p>
                                            <div className='mt-5 grid grid-cols-2 gap-y-3 text-sm'>
                                                {product?.color && (<><span className='text-[#2a1d17]/50'>Colour</span><span className='font-semibold capitalize'>{product.color}</span></>)}
                                                {product?.sizes && (<><span className='text-[#2a1d17]/50'>Size</span><span className='font-semibold uppercase'>{product.sizes}</span></>)}
                                                <span className='text-[#2a1d17]/50'>Sold by</span><span className='font-semibold'>{brand}</span>
                                            </div>
                                        </>
                                    ),
                                },
                                {
                                    title: "Care & Quality",
                                    body: (
                                        <ul className='space-y-2 text-[15px] text-[#2a1d17]/75'>
                                            <li>✦ Quality-checked before dispatch</li>
                                            <li>✦ Follow the care label for long life</li>
                                            <li>✦ Store in a cool, dry place</li>
                                        </ul>
                                    ),
                                },
                                {
                                    title: "Shipping & Returns",
                                    body: (
                                        <ul className='space-y-2 text-[15px] text-[#2a1d17]/75'>
                                            <li>✦ Free delivery on orders above ₹999</li>
                                            <li>✦ Dispatched within 24–48 hours</li>
                                            <li>✦ Easy 7-day returns & refunds</li>
                                        </ul>
                                    ),
                                },
                            ].map((a, i) => (
                                <div key={a.title} className='border-b border-[#2a1d17]/10'>
                                    <button
                                        onClick={() => setOpenAcc(openAcc === i ? null : i)}
                                        className='w-full flex items-center justify-between py-5 text-left group'
                                    >
                                        <span className='flex items-baseline gap-4'>
                                            <span className='pd-serif italic text-sm text-[#b4532a]'>{String(i + 1).padStart(2, "0")}</span>
                                            <span className='pd-serif text-xl font-semibold group-hover:text-[#b4532a] transition-colors'>{a.title}</span>
                                        </span>
                                        <span className={`w-8 h-8 rounded-full grid place-items-center border border-[#2a1d17]/15 transition-transform duration-300 ${openAcc === i ? "rotate-180 bg-[#2a1d17] text-[#f6f1ea]" : ""}`}>
                                            <ExpandMoreIcon sx={{ fontSize: 18 }} />
                                        </span>
                                    </button>
                                    <div className={`pd-acc ${openAcc === i ? "open" : ""}`}>
                                        <div><div className='pb-6 pl-9'>{a.body}</div></div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </section>

            {/* ---------- Trust strip ---------- */}
            <div className='overflow-hidden bg-[#2a1d17] text-[#f4efe6] py-4'>
                <div className='pd-marquee flex w-max'>
                    {[0, 1].map(copy => (
                        <div key={copy} className='flex shrink-0 items-center' aria-hidden={copy === 1}>
                            {trust.map(t => (
                                <span key={t + copy} className='flex items-center'>
                                    <span className='pd-serif italic text-lg px-8 whitespace-nowrap'>{t}</span>
                                    <span className='text-[#c9a96e] text-xs'>✦</span>
                                </span>
                            ))}
                        </div>
                    ))}
                </div>
            </div>

            {/* ---------- Reviews ---------- */}
            <section className='px-5 lg:px-20 py-20 bg-[#fbf8f4]'>
                <div className='flex flex-wrap items-end justify-between gap-6 mb-12'>
                    <div>
                        <Kicker>Reviews</Kicker>
                        <h2 className='pd-serif mt-4 text-4xl lg:text-5xl font-semibold'>What they <span className='italic font-medium text-[#b4532a]'>say</span></h2>
                    </div>
                    <button
                        onClick={() => navigate(`/reviews/${productId}/create`)}
                        className='pd-cta flex items-center gap-3 h-12 pl-6 pr-1.5 rounded-full border border-[#2a1d17]/20 hover:border-[#2a1d17] text-sm font-semibold'
                    >
                        <EditOutlinedIcon sx={{ fontSize: 17 }} /> Write a review
                        <span className='pd-cta-arrow w-9 h-9 rounded-full grid place-items-center bg-[#2a1d17] text-[#f6f1ea]'>
                            <ArrowOutwardIcon sx={{ fontSize: 16 }} />
                        </span>
                    </button>
                </div>

                <div className='grid grid-cols-1 lg:grid-cols-[360px_1fr] gap-10'>
                    {/* summary card */}
                    <div className='self-start rounded-[28px] bg-[#1b2420] text-[#f4efe6] p-8 relative overflow-hidden'>
                        <span className='pd-serif pd-outline-light absolute -right-4 -bottom-10 text-[150px] italic leading-none pointer-events-none'>★</span>
                        <p className='text-[11px] font-semibold tracking-[0.3em] uppercase text-[#c9a96e]'>Overall rating</p>
                        <div className='mt-3 flex items-end gap-2'>
                            <span className='pd-serif text-7xl font-semibold leading-none'>{reviews.length ? avgRating.toFixed(1) : "—"}</span>
                            <span className='mb-2 text-[#f4efe6]/50'>/ 5</span>
                        </div>
                        <Rating
                            value={avgRating}
                            precision={0.5}
                            readOnly
                            icon={<StarRoundedIcon fontSize="inherit" />}
                            emptyIcon={<StarRoundedIcon fontSize="inherit" />}
                            sx={{ mt: 1.5, fontSize: 22, color: "#c9a96e", "& .MuiRating-iconEmpty": { color: "rgba(244,239,230,0.18)" } }}
                        />
                        <p className='text-sm text-[#f4efe6]/60 mt-1'>Based on {reviews.length} review{reviews.length === 1 ? "" : "s"}</p>

                        <div className='mt-7 space-y-3 relative'>
                            {starCounts.map(({ star, count }) => {
                                const pct = reviews.length ? (count / reviews.length) * 100 : 0;
                                return (
                                    <div key={star} className='flex items-center gap-3 text-sm'>
                                        <span className='w-6 pd-serif'>{star}★</span>
                                        <div className='flex-1 h-1.5 rounded-full bg-[#f4efe6]/10 overflow-hidden'>
                                            <div className='h-full rounded-full bg-[#c9a96e] transition-all duration-700' style={{ width: `${pct}%` }} />
                                        </div>
                                        <span className='w-6 text-right text-[#f4efe6]/50'>{count}</span>
                                    </div>
                                );
                            })}
                        </div>
                    </div>

                    {/* review list */}
                    <div>
                        {reviews.length === 0 ? (
                            <div className='h-full min-h-[240px] rounded-[28px] border border-dashed border-[#2a1d17]/20 grid place-items-center text-center p-10'>
                                <div>
                                    <p className='pd-serif text-3xl'>Be the <span className='italic text-[#b4532a]'>first.</span></p>
                                    <p className='mt-2 text-sm text-[#2a1d17]/55'>No reviews yet — share how you liked it.</p>
                                </div>
                            </div>
                        ) : (
                            <div className='divide-y divide-[#2a1d17]/10'>
                                {reviews.slice(0, 4).map((item) => (
                                    <div key={item.id} className='py-6 first:pt-0 flex gap-4'>
                                        <Avatar sx={{ width: 48, height: 48, bgcolor: "#b4532a", fontFamily: "'Playfair Display', serif", fontWeight: 600 }}>
                                            {item.user?.fullName?.[0]?.toUpperCase()}
                                        </Avatar>
                                        <div className='flex-1 min-w-0'>
                                            <div className='flex items-start justify-between gap-3'>
                                                <div>
                                                    <p className='font-semibold'>{item.user?.fullName}</p>
                                                    <p className='text-xs text-[#2a1d17]/45 mt-0.5'>
                                                        {item.createdAt ? new Date(item.createdAt).toLocaleDateString("en-IN", { day: "numeric", month: "short", year: "numeric" }) : ""}
                                                    </p>
                                                </div>
                                                <div className='flex items-center gap-1'>
                                                    <Rating
                                                        value={item.rating}
                                                        precision={0.5}
                                                        readOnly
                                                        size="small"
                                                        icon={<StarRoundedIcon fontSize="inherit" />}
                                                        emptyIcon={<StarRoundedIcon fontSize="inherit" />}
                                                        sx={{ color: "#c9a96e", "& .MuiRating-iconEmpty": { color: "rgba(42,29,23,0.15)" } }}
                                                    />
                                                    {item.user?.id === user.user?.id && (
                                                        <IconButton
                                                            size="small"
                                                            onClick={() => dispatch(deleteReview({ reviewId: item.id, jwt: localStorage.getItem("jwt") || "" }))}
                                                            sx={{ color: "#b4532a" }}
                                                        >
                                                            <DeleteOutlineIcon fontSize="small" />
                                                        </IconButton>
                                                    )}
                                                </div>
                                            </div>
                                            <p className='mt-3 text-[15px] leading-relaxed text-[#2a1d17]/75'>{item.reviewText}</p>
                                            {item.productImages?.length > 0 && (
                                                <div className='mt-3 flex gap-2'>
                                                    {item.productImages.map((image) => (
                                                        <img key={image} className='w-20 h-20 rounded-xl object-cover' src={image} alt="" />
                                                    ))}
                                                </div>
                                            )}
                                        </div>
                                    </div>
                                ))}
                            </div>
                        )}

                        {reviews.length > 4 && (
                            <button
                                onClick={() => navigate(`/reviews/${productId}`)}
                                className='mt-6 text-sm font-semibold tracking-[0.2em] uppercase text-[#b4532a] hover:underline underline-offset-4'
                            >
                                View all {reviews.length} reviews →
                            </button>
                        )}
                    </div>
                </div>
            </section>

            {/* ---------- Similar products ---------- */}
            {similar.length > 0 && (
                <section className='relative overflow-hidden px-5 lg:px-20 py-20 bg-[#1b2420] text-[#f4efe6]'>
                    <span className='pd-serif pd-outline-light pointer-events-none select-none absolute -left-6 top-6 text-[120px] lg:text-[200px] italic leading-none'>
                        Curated
                    </span>
                    <div className='relative flex flex-wrap items-end justify-between gap-4 mb-12'>
                        <div>
                            <Kicker light>Pairs well with</Kicker>
                            <h2 className='pd-serif mt-4 text-4xl lg:text-5xl font-semibold'>You may also <span className='italic font-medium text-[#c9a96e]'>love</span></h2>
                        </div>
                        <button
                            onClick={() => navigate(`/products/${categoryId}`)}
                            className='pd-cta flex items-center gap-3 h-12 pl-6 pr-1.5 rounded-full border border-[#f4efe6]/25 hover:border-[#c9a96e] text-sm font-semibold'
                        >
                            View all
                            <span className='pd-cta-arrow w-9 h-9 rounded-full grid place-items-center bg-[#b4532a]'>
                                <ArrowOutwardIcon sx={{ fontSize: 16 }} />
                            </span>
                        </button>
                    </div>

                    <div className='relative grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-x-5 gap-y-10'>
                        {similar.map((item: any, i: number) => (
                            <div
                                key={item.id}
                                onClick={() => navigate(`/product-details/${item.category?.categoryId}/${item.title}/${item.id}`)}
                                className='pd-card group cursor-pointer'
                            >
                                <div className='relative aspect-[3/4] rounded-t-full rounded-b-2xl overflow-hidden bg-[#26312c]'>
                                    <img className='w-full h-full object-cover object-top' src={item.images?.[0]} alt={item.title} />
                                    <span className='pd-serif absolute bottom-3 left-4 text-[#f6f1ea] text-sm italic drop-shadow'>
                                        {String(i + 1).padStart(2, "0")}
                                    </span>
                                    {(item.discountPercent ?? 0) > 0 && (
                                        <span className='absolute bottom-3 right-3 px-2.5 py-1 rounded-full bg-[#b4532a] text-[#f6f1ea] text-[10px] font-bold tracking-[0.15em]'>
                                            -{item.discountPercent}%
                                        </span>
                                    )}
                                </div>
                                <div className='pt-4'>
                                    <p className='text-[10px] font-semibold tracking-[0.25em] uppercase text-[#c9a96e] truncate'>
                                        {item.seller?.businessDetails?.businessName || "Shopzy"}
                                    </p>
                                    <p className='pd-serif mt-1 text-lg truncate group-hover:text-[#e7b48f] transition-colors'>{item.title}</p>
                                    <div className='mt-1 flex items-center gap-2 text-sm'>
                                        <span className='font-semibold'>₹{item.sellingPrice}</span>
                                        {(item.discountPercent ?? 0) > 0 && <span className='line-through text-[#f4efe6]/40'>₹{item.mrpPrice}</span>}
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </section>
            )}

            {/* ---------- Mobile sticky bar ---------- */}
            <div className={`pd-bar lg:hidden fixed bottom-0 inset-x-0 z-40 bg-[#f6f1ea]/95 backdrop-blur border-t border-[#2a1d17]/10 px-4 py-3 flex items-center gap-3 ${showBar ? "translate-y-0 opacity-100" : "translate-y-full opacity-0 pointer-events-none"}`}>
                <div className='min-w-0'>
                    <p className='text-[10px] font-semibold tracking-[0.2em] uppercase text-[#2a1d17]/50 truncate'>{product?.title}</p>
                    <p className='pd-serif text-xl font-semibold leading-tight'>₹{product?.sellingPrice}</p>
                </div>
                <button
                    onClick={handleAddCart}
                    className='ml-auto shrink-0 whitespace-nowrap flex items-center gap-2 h-12 pl-5 pr-1.5 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-xs font-semibold tracking-[0.2em] uppercase'
                >
                    Add to Bag
                    <span className='w-9 h-9 rounded-full grid place-items-center bg-[#b4532a]'><ArrowOutwardIcon sx={{ fontSize: 16 }} /></span>
                </button>
            </div>

            <Snackbar
                open={!!toast}
                autoHideDuration={2200}
                onClose={() => setToast(null)}
                anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
            >
                <Alert
                    onClose={() => setToast(null)}
                    severity="success"
                    variant="filled"
                    sx={{ bgcolor: "#1b2420", color: "#f4efe6", borderRadius: 999, "& .MuiAlert-icon": { color: "#c9a96e" } }}
                >
                    {toast}
                </Alert>
            </Snackbar>
        </div>
    )
}

export default ProductDetails