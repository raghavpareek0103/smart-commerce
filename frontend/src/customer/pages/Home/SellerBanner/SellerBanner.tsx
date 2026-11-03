import { useNavigate } from "react-router-dom";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import TrendingUpIcon from "@mui/icons-material/TrendingUp";
import "./SellerBanner.css";

// Teen bade fayde (number + line)
const stats = [
  { big: "₹0", small: "Setup fee — start for free" },
  { big: "Lakhs", small: "Of customers across India" },
  { big: "1", small: "Simple dashboard for orders & stock" },
];

const steps = ["Register", "List products", "Start earning"];

const SellerBanner = () => {
  const navigate = useNavigate();

  return (
    <section className="relative overflow-hidden bg-[#f3ebe3] text-[#2a1d17]">
      {/* Peeche bada outline text */}
      <span className="seller-serif seller-watermark pointer-events-none select-none absolute right-0 -bottom-8 italic leading-none hidden lg:block">
        Sell
      </span>

      <div className="relative grid md:grid-cols-2 gap-10 lg:gap-16 px-5 lg:px-20 py-14 lg:py-20">
        {/* ---------- Left: arch photo ---------- */}
        <div className="relative hidden md:block min-h-[480px]">
          <div className="absolute left-0 top-0 bottom-0 w-[78%] rounded-t-full overflow-hidden shadow-[0_30px_60px_-30px_rgba(42,29,23,0.5)]">
            <img
              src="/seller_banner_image.jpg"
              alt="Seller"
              className="seller-zoom w-full h-full object-cover object-[70%_center]"
            />
          </div>

          {/* Ghoomta hua gol badge */}
          <button
            onClick={() => navigate("/become-seller")}
            aria-label="Become a seller"
            className="absolute right-[6%] top-8 w-32 h-32 grid place-items-center text-[#2a1d17]"
          >
            <svg viewBox="0 0 100 100" className="seller-spin absolute inset-0 w-full h-full">
              <defs>
                <path id="sellerCircle" d="M50,50 m-38,0 a38,38 0 1,1 76,0 a38,38 0 1,1 -76,0" />
              </defs>
              <text fill="currentColor" fontSize="9" fontWeight="600">
                <textPath href="#sellerCircle" textLength="236" lengthAdjust="spacing">SELL ON SHOPZY • GROW WITH US •</textPath>
              </text>
            </svg>
            <span className="w-14 h-14 rounded-full grid place-items-center bg-[#b4532a] text-white shadow-lg">
              <ArrowOutwardIcon />
            </span>
          </button>

          {/* Floating card: new order */}
          <div className="seller-float absolute left-[52%] top-[42%] bg-white rounded-2xl shadow-2xl px-5 py-4">
            <p className="text-[10px] font-semibold uppercase tracking-[0.25em] text-[#b4532a]">New order</p>
            <p className="seller-serif text-lg font-semibold mt-0.5">Cotton Kurta Set</p>
            <p className="text-xs text-[#2a1d17]/55 mt-0.5">Just now · Jaipur</p>
          </div>

          {/* Floating card: sales */}
          <div className="seller-float-2 absolute right-[2%] bottom-6 bg-[#2a1d17] text-[#f3ebe3] rounded-2xl shadow-2xl px-5 py-4">
            <p className="text-[10px] font-semibold uppercase tracking-[0.25em] text-[#e7b48f] flex items-center gap-1.5">
              <TrendingUpIcon sx={{ fontSize: 14 }} /> Sales this week
            </p>
            <div className="flex items-end gap-1.5 h-12 mt-3">
              {[35, 50, 40, 65, 55, 80, 95].map((h, i) => (
                <span key={i} className="seller-bar w-2.5 rounded-sm bg-gradient-to-t from-[#b4532a] to-[#e7b48f]" style={{ height: `${h}%`, animationDelay: `${i * 80}ms` }} />
              ))}
            </div>
          </div>
        </div>

        {/* ---------- Right: text ---------- */}
        <div className="relative flex flex-col justify-center">
          <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
            <span className="w-10 h-px bg-[#b4532a]" />
            For Sellers
          </p>

          <h2 className="seller-serif mt-4 text-4xl sm:text-5xl lg:text-6xl font-semibold leading-[1.02]">
            Grow your business <br className="hidden sm:block" />
            with <span className="italic font-medium text-[#b4532a]">Shopzy.</span>
          </h2>

          <p className="mt-5 max-w-md text-sm sm:text-base leading-relaxed text-[#2a1d17]/70">
            Join thousands of sellers who reach new customers every day — no setup cost, no hassle.
          </p>

          {/* Stats */}
          <div className="mt-8 grid grid-cols-3 border-y border-[#2a1d17]/15">
            {stats.map((s, i) => (
              <div key={s.small} className={`py-5 ${i ? "pl-4 sm:pl-6 border-l border-[#2a1d17]/15" : "pr-4"}`}>
                <p className="seller-serif text-2xl sm:text-4xl font-semibold text-[#2a1d17]">{s.big}</p>
                <p className="mt-1 text-[11px] sm:text-xs leading-snug text-[#2a1d17]/60">{s.small}</p>
              </div>
            ))}
          </div>

          {/* Steps */}
          <div className="mt-7 flex flex-wrap items-center gap-x-4 gap-y-2 text-sm font-semibold">
            {steps.map((s, i) => (
              <div key={s} className="flex items-center gap-4">
                <span className="flex items-baseline gap-2">
                  <span className="seller-serif italic text-[#b4532a]">0{i + 1}</span>
                  {s}
                </span>
                {i < steps.length - 1 && <span className="w-8 h-px bg-[#2a1d17]/25" />}
              </div>
            ))}
          </div>

          {/* Buttons */}
          <div className="mt-9 flex flex-wrap items-center gap-6">
            <button
              onClick={() => navigate("/become-seller")}
              className="seller-cta group flex items-center gap-3 h-12 pl-6 pr-2 rounded-full bg-[#2a1d17] text-[#f3ebe3] text-sm font-semibold tracking-wide"
            >
              Start Selling
              <span className="w-8 h-8 rounded-full grid place-items-center bg-[#b4532a] text-white group-hover:rotate-45 transition-transform duration-300">
                <ArrowOutwardIcon sx={{ fontSize: 17 }} />
              </span>
            </button>
            <button onClick={() => navigate("/become-seller")} className="group relative text-sm font-semibold">
              Seller Login
              <span className="absolute left-0 -bottom-1 h-px w-full bg-[#2a1d17]/30" />
              <span className="absolute left-0 -bottom-1 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#2a1d17] transition-transform duration-300" />
            </button>
          </div>
        </div>
      </div>
    </section>
  );
};

export default SellerBanner;