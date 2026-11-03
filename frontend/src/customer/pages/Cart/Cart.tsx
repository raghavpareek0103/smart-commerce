import { Alert, Snackbar } from "@mui/material";
import { useEffect, useState } from "react";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import WestIcon from "@mui/icons-material/West";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import CloseIcon from "@mui/icons-material/Close";
import CartItemCard from "./CartItemCard";
import { useNavigate } from "react-router-dom";
import PricingCard from "./PricingCard";
import { useAppDispatch, useAppSelector } from "../../../Redux Toolkit/Store";
import { fetchUserCart } from "../../../Redux Toolkit/Customer/CartSlice";
import type { CartItem } from "../../../types/cartTypes";
import { applyCoupon } from "../../../Redux Toolkit/Customer/CouponSlice";
import "./Cart.css";

// Upar ke 3 steps: Bag → Address → Payment
const steps = ["Bag", "Address", "Payment"];

const Cart = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const { cart, auth, coupone } = useAppSelector((store) => store);
  const [couponCode, setCouponCode] = useState("");
  const [snackbarOpen, setOpenSnackbar] = useState(false);

  useEffect(() => {
    dispatch(fetchUserCart(localStorage.getItem("jwt") || ""));
  }, [auth.jwt]);

  const handleApllyCoupon = (apply: string) => {
    let code = couponCode;
    if (apply == "false") code = cart.cart?.couponCode || "";
    dispatch(applyCoupon({
      apply, code,
      orderValue: cart.cart?.totalSellingPrice || 100,
      jwt: localStorage.getItem("jwt") || "",
    }));
  };

  useEffect(() => {
    if (coupone.couponApplied || coupone.error) {
      setOpenSnackbar(true);
      setCouponCode("");
    }
  }, [coupone.couponApplied, coupone.error]);

  const itemCount = cart.cart?.cartItems.length || 0;

  return (
    <div className="relative overflow-hidden bg-[#f6f1ea] text-[#2a1d17] min-h-[80vh]">
      {cart.cart && itemCount !== 0 ? (
        <div className="relative px-5 lg:px-20 xl:px-32 py-10 lg:py-14">
          {/* Steps */}
          <ol className="flex items-center gap-3 sm:gap-5 text-[10px] sm:text-[11px] font-semibold tracking-[0.25em] uppercase">
            {steps.map((s, i) => (
              <li key={s} className="flex items-center gap-3 sm:gap-5">
                <span className={`flex items-center gap-2 ${i === 0 ? "text-[#2a1d17]" : "text-[#2a1d17]/35"}`}>
                  <span className={`cart-serif italic normal-case tracking-normal text-sm ${i === 0 ? "text-[#b4532a]" : ""}`}>0{i + 1}</span>
                  {s}
                </span>
                {i < steps.length - 1 && <span className="w-8 sm:w-14 h-px bg-[#2a1d17]/20" />}
              </li>
            ))}
          </ol>

          {/* Heading */}
          <div className="flex flex-wrap items-end justify-between gap-4 mt-8 mb-8 lg:mb-10">
            <div>
              <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
                <span className="w-10 h-px bg-[#b4532a]" />
                Your Selection
              </p>
              <h1 className="cart-serif mt-3 text-4xl lg:text-5xl font-semibold leading-none">
                Shopping <span className="italic font-medium text-[#b4532a]">Bag</span>
                <span className="cart-serif italic text-xl lg:text-2xl text-[#2a1d17]/40 ml-3">({String(itemCount).padStart(2, "0")})</span>
              </h1>
            </div>
            <button onClick={() => navigate("/")} className="group flex items-center gap-2 text-sm font-semibold">
              <WestIcon sx={{ fontSize: 16 }} className="group-hover:-translate-x-1 transition-transform" />
              <span className="relative">
                Continue shopping
                <span className="absolute left-0 -bottom-1 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#2a1d17] transition-transform duration-300" />
              </span>
            </button>
          </div>

          <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 lg:gap-10">
            {/* Items */}
            <div className="lg:col-span-2 space-y-4">
              {cart.cart?.cartItems.map((item: CartItem) => (
                <CartItemCard key={item.id} item={item} />
              ))}
            </div>

            {/* Summary */}
            <div className="col-span-1 space-y-4 lg:sticky lg:top-28 h-fit">
              {/* Coupon */}
              <div className="bg-[#fbf8f4] rounded-2xl border border-[#2a1d17]/10 p-5">
                <p className="text-[10px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]">Have a code?</p>
                <p className="cart-serif text-xl font-semibold mt-1 mb-4">Apply <span className="italic font-medium text-[#b4532a]">coupon</span></p>
                {!cart.cart?.couponCode ? (
                  <form
                    onSubmit={(e) => { e.preventDefault(); if (couponCode) handleApllyCoupon("true"); }}
                    className="flex items-center bg-white rounded-full border border-[#2a1d17]/15 focus-within:border-[#b4532a] p-1 transition-colors"
                  >
                    <input
                      value={couponCode}
                      onChange={(e) => setCouponCode(e.target.value.toUpperCase())}
                      placeholder="Enter coupon code"
                      className="flex-1 min-w-0 h-10 px-4 bg-transparent outline-none text-sm tracking-wider placeholder:tracking-normal placeholder:text-[#2a1d17]/35"
                    />
                    <button
                      type="submit"
                      disabled={!couponCode}
                      className="h-10 px-5 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-sm font-semibold hover:bg-[#b4532a] disabled:bg-[#2a1d17]/10 disabled:text-[#2a1d17]/35 transition-colors"
                    >
                      Apply
                    </button>
                  </form>
                ) : (
                  <div className="flex items-center justify-between rounded-full border border-dashed border-[#b4532a]/60 bg-[#f3ebe3] pl-5 pr-1.5 py-1.5">
                    <span className="text-sm">
                      <span className="font-bold tracking-wider text-[#b4532a]">{cart.cart.couponCode}</span>
                      <span className="text-[#2a1d17]/60"> applied</span>
                    </span>
                    <button
                      onClick={() => handleApllyCoupon("false")}
                      aria-label="Remove coupon"
                      className="w-8 h-8 rounded-full grid place-items-center text-[#2a1d17]/60 hover:bg-[#2a1d17] hover:text-[#f6f1ea] transition-colors"
                    >
                      <CloseIcon sx={{ fontSize: 16 }} />
                    </button>
                  </div>
                )}
              </div>

              {/* Price + checkout */}
              <section className="bg-[#fbf8f4] rounded-2xl border border-[#2a1d17]/10 overflow-hidden">
                <div className="px-5 pt-5">
                  <p className="text-[10px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]">Summary</p>
                  <p className="cart-serif text-xl font-semibold mt-1">Price <span className="italic font-medium text-[#b4532a]">details</span></p>
                </div>
                <PricingCard />
                <div className="p-5 pt-2 space-y-3">
                  <button
                    onClick={() => navigate("/checkout/address")}
                    className="cart-cta group w-full flex items-center justify-between h-14 pl-7 pr-2 rounded-full bg-[#2a1d17] text-[#f6f1ea] font-semibold tracking-wide"
                  >
                    Place Order
                    <span className="w-10 h-10 rounded-full grid place-items-center bg-[#b4532a] text-white group-hover:rotate-45 transition-transform duration-300">
                      <ArrowOutwardIcon sx={{ fontSize: 18 }} />
                    </span>
                  </button>
                  <p className="flex items-center justify-center gap-1.5 text-[10px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/45">
                    <LockOutlinedIcon sx={{ fontSize: 13 }} /> Safe & secure payments
                  </p>
                </div>
              </section>

              {/* Wishlist */}
              <button
                onClick={() => navigate("/wishlist")}
                className="group w-full bg-[#fbf8f4] rounded-2xl border border-[#2a1d17]/10 hover:border-[#b4532a]/40 px-5 py-4 flex justify-between items-center transition-colors"
              >
                <span className="flex items-center gap-3 text-sm font-semibold">
                  <span className="w-9 h-9 rounded-full grid place-items-center bg-[#f3ebe3] text-[#b4532a]">
                    <FavoriteBorderIcon sx={{ fontSize: 18 }} />
                  </span>
                  Add from Wishlist
                </span>
                <ArrowOutwardIcon sx={{ fontSize: 18 }} className="text-[#2a1d17]/40 group-hover:text-[#b4532a] group-hover:rotate-45 transition-all duration-300" />
              </button>
            </div>
          </div>
        </div>
      ) : (
        /* ---------- Khaali bag ---------- */
        <div className="relative min-h-[80vh] flex flex-col items-center justify-center text-center px-5 py-16">
          <span className="cart-serif cart-watermark pointer-events-none select-none absolute left-1/2 -translate-x-1/2 bottom-0 italic leading-none hidden md:block">
            Empty
          </span>

          {/* Arch ke andar bag */}
          <div className="relative w-40 h-48 rounded-t-full bg-[#fbf8f4] border border-[#2a1d17]/10 grid place-items-center shadow-[0_30px_60px_-30px_rgba(42,29,23,0.45)]">
            <span className="absolute inset-2.5 rounded-t-full border border-[#c9a96e]/50" />
            <svg viewBox="0 0 64 64" className="cart-bag w-16 h-16" fill="none" stroke="#2a1d17" strokeWidth="2">
              <path d="M14 22h36l-3 32H17z" fill="#f3ebe3" />
              <path d="M24 26v-6a8 8 0 0 1 16 0v6" stroke="#b4532a" />
            </svg>
          </div>

          <p className="relative mt-9 flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
            <span className="w-10 h-px bg-[#b4532a]" />
            Your Bag
            <span className="w-10 h-px bg-[#b4532a]" />
          </p>
          <h1 className="relative cart-serif mt-3 text-4xl lg:text-5xl font-semibold leading-tight">
            Feels a little <span className="italic font-medium text-[#b4532a]">light.</span>
          </h1>
          <p className="relative mt-3 max-w-sm text-sm sm:text-base text-[#2a1d17]/60">
            Looks like you haven't added anything yet. Let's find something you'll love.
          </p>

          <div className="relative mt-9 flex flex-wrap items-center justify-center gap-6">
            <button
              onClick={() => navigate("/")}
              className="cart-cta group flex items-center gap-3 h-12 pl-6 pr-2 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-sm font-semibold tracking-wide"
            >
              Start Shopping
              <span className="w-8 h-8 rounded-full grid place-items-center bg-[#b4532a] text-white group-hover:rotate-45 transition-transform duration-300">
                <ArrowOutwardIcon sx={{ fontSize: 17 }} />
              </span>
            </button>
            <button onClick={() => navigate("/wishlist")} className="group relative text-sm font-semibold">
              View Wishlist
              <span className="absolute left-0 -bottom-1 h-px w-full bg-[#2a1d17]/30" />
              <span className="absolute left-0 -bottom-1 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#2a1d17] transition-transform duration-300" />
            </button>
          </div>
        </div>
      )}

      <Snackbar anchorOrigin={{ vertical: "top", horizontal: "right" }} open={snackbarOpen}
        autoHideDuration={6000} onClose={() => setOpenSnackbar(false)}>
        <Alert onClose={() => setOpenSnackbar(false)} severity={coupone.error ? "error" : "success"}
          variant="filled" sx={{ width: "100%" }}>
          {coupone.error ? coupone.error : "Coupon applied successfully"}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default Cart;