import { Alert, Snackbar } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import { useAppSelector } from "../../../Redux Toolkit/Store";
import SellerLoginForm from "./SellerLoginForm";
import SellerAccountForm from "./SellerAccountForm";
import "./BecomeSeller.css";

// Right panel ke 3 bade numbers
const stats = [
  { big: "₹0", small: "Setup fee" },
  { big: "Lakhs", small: "Customers" },
  { big: "4", small: "Steps to go live" },
];

const BecomeSeller = () => {
  const navigate = useNavigate();
  const [isLoginPage, setIsLoginPage] = useState(false);
  const { sellerAuth } = useAppSelector((store) => store);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const handleCloseSnackbar = () => setSnackbarOpen(false);

  useEffect(() => {
    if (sellerAuth.sellerCreated || sellerAuth.error || sellerAuth.otpSent) {
      setSnackbarOpen(true);
    }
  }, [sellerAuth.sellerCreated, sellerAuth.error, sellerAuth.otpSent]);

  return (
    <div className="grid lg:grid-cols-5 min-h-screen bg-[#f6f1ea] text-[#2a1d17]">
      {/* ---------- Left: form ---------- */}
      <section className="lg:col-span-2 bg-[#fbf8f4] px-6 sm:px-10 lg:px-12 py-10 lg:py-12 border-r border-[#2a1d17]/10">
        {/* Logo */}
        <button onClick={() => navigate("/")} className="flex items-center gap-2 mb-10">
          <span className="bs-serif w-9 h-9 rounded-xl grid place-items-center text-[#c9a96e] font-semibold italic text-xl bg-[#1b2420] border border-[#c9a96e]/45">S</span>
          <span className="bs-serif text-[26px] font-semibold leading-none">
            Shop<span className="italic font-medium text-[#b4532a]">zy</span>
            <span className="ml-2 align-middle text-[10px] font-sans font-semibold tracking-[0.3em] uppercase text-[#2a1d17]/50">Seller</span>
          </span>
        </button>

        {/* Heading */}
        <div key={String(isLoginPage)} className="bs-rise mb-9">
          <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
            <span className="w-10 h-px bg-[#b4532a]" />
            {isLoginPage ? "Seller Login" : "Seller Registration"}
          </p>
          <h1 className="bs-serif mt-3 text-4xl font-semibold leading-tight">
            {isLoginPage
              ? <>Welcome <span className="italic font-medium text-[#b4532a]">back.</span></>
              : <>Start <span className="italic font-medium text-[#b4532a]">selling.</span></>}
          </h1>
          <p className="mt-2 text-sm text-[#2a1d17]/60">
            {isLoginPage ? "Log in to manage your orders, products and payouts." : "Four quick steps and your store is live."}
          </p>
        </div>

        {/* Form (purane components, naye rang me) */}
        <div key={"f" + String(isLoginPage)} className="bs-form bs-rise">
          {!isLoginPage ? <SellerAccountForm /> : <SellerLoginForm />}
        </div>

        {/* Login / Register toggle */}
        <div className="mt-10 pt-6 border-t border-[#2a1d17]/10 flex items-center justify-between gap-4">
          <p className="text-sm text-[#2a1d17]/60">
            {isLoginPage ? "New to Shopzy?" : "Already a seller?"}
          </p>
          <button
            onClick={() => setIsLoginPage(!isLoginPage)}
            className="group flex items-center gap-2.5 h-11 pl-5 pr-1.5 rounded-full border border-[#2a1d17]/20 text-sm font-semibold hover:border-[#2a1d17] transition-colors"
          >
            {isLoginPage ? "Register" : "Login"}
            <span className="w-8 h-8 rounded-full grid place-items-center bg-[#2a1d17] text-[#f6f1ea] group-hover:bg-[#b4532a] group-hover:rotate-45 transition-all duration-300">
              <ArrowOutwardIcon sx={{ fontSize: 16 }} />
            </span>
          </button>
        </div>
      </section>

      {/* ---------- Right: editorial panel ---------- */}
      <section className="relative hidden lg:flex lg:col-span-3 overflow-hidden bg-[#1b2420] text-[#f4efe6]">
        <div className="absolute -top-32 left-1/4 w-[520px] h-[520px] rounded-full bg-[#c9a96e]/12 blur-3xl" />
        <div className="bs-lines absolute inset-0 pointer-events-none" />
        <span className="bs-serif bs-watermark pointer-events-none select-none absolute -right-6 -bottom-10 italic leading-none">Sell</span>

        <div className="relative w-full grid grid-cols-2 gap-10 items-center px-14 xl:px-20 py-16">
          {/* Text */}
          <div>
            <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#c9a96e]">
              <span className="w-10 h-px bg-[#c9a96e]" />
              For Sellers
            </p>
            <h2 className="bs-serif mt-4 text-5xl xl:text-6xl font-semibold leading-[1.02]">
              Join the marketplace <span className="italic font-medium text-[#c9a96e]">revolution.</span>
            </h2>
            <p className="mt-5 text-sm leading-relaxed text-[#f4efe6]/65 max-w-sm">
              Boost your sales today — reach lakhs of shoppers across India with zero setup cost.
            </p>

            <div className="mt-10 grid grid-cols-3 border-y border-white/10">
              {stats.map((s, i) => (
                <div key={s.small} className={`py-5 ${i ? "pl-5 border-l border-white/10" : "pr-4"}`}>
                  <p className="bs-serif text-2xl xl:text-3xl font-semibold">{s.big}</p>
                  <p className="mt-1 text-[10px] font-semibold tracking-[0.25em] uppercase text-[#c9a96e]">{s.small}</p>
                </div>
              ))}
            </div>
          </div>

          {/* Arch photo + floating cards */}
          <div className="relative h-[520px]">
            <div className="absolute inset-x-6 top-0 bottom-0 rounded-t-full overflow-hidden border border-[#c9a96e]/30 shadow-[0_40px_80px_-30px_rgba(0,0,0,0.6)]">
              <img src="/seller_banner_image.jpg" alt="Seller" className="bs-zoom w-full h-full object-cover object-[70%_center]" />
              <div className="absolute inset-0 bg-gradient-to-t from-[#1b2420]/50 to-transparent" />
            </div>

            <div className="bs-float absolute -left-4 top-[38%] bg-[#fbf8f4] text-[#2a1d17] rounded-2xl shadow-2xl px-5 py-4">
              <p className="text-[10px] font-semibold uppercase tracking-[0.25em] text-[#b4532a]">New order</p>
              <p className="bs-serif text-lg font-semibold mt-0.5">Cotton Kurta Set</p>
              <p className="text-xs text-[#2a1d17]/55 mt-0.5">Just now · Jaipur</p>
            </div>

            <div className="bs-float-2 absolute -right-2 bottom-10 bg-[#2a1d17] rounded-2xl shadow-2xl px-5 py-4 border border-[#c9a96e]/25">
              <p className="text-[10px] font-semibold uppercase tracking-[0.25em] text-[#e7b48f]">Sales this week</p>
              <div className="flex items-end gap-1.5 h-12 mt-3">
                {[35, 50, 40, 65, 55, 80, 95].map((h, i) => (
                  <span key={i} className="bs-bar w-2.5 rounded-sm bg-gradient-to-t from-[#b4532a] to-[#e7b48f]" style={{ height: `${h}%`, animationDelay: `${i * 80}ms` }} />
                ))}
              </div>
            </div>
          </div>
        </div>
      </section>

      <Snackbar
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
        open={snackbarOpen}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
      >
        <Alert onClose={handleCloseSnackbar} severity={sellerAuth.error ? "error" : "success"} variant="filled" sx={{ width: "100%" }}>
          {sellerAuth.error ? sellerAuth.error : sellerAuth.sellerCreated ? sellerAuth.sellerCreated : " otp sent to your email!"}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default BecomeSeller;