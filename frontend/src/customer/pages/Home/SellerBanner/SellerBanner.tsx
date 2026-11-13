import { useNavigate } from "react-router-dom";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import "./SellerBanner.css";
import SellerHeroVisual from "./SellerHeroVisual";

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
        {/* ---------- Left: seller dashboard visual (photo ki jagah) ---------- */}
        <div className="relative hidden md:flex items-center min-h-[480px]">
          <SellerHeroVisual />
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