import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import { homeCategories } from "../../../../data/homeCategories";
import "./FeaturedCollections.css";

// categoryId se photo nikalne ke liye (data/homeCategories.ts se)
const imageOf = (id: string) =>
  homeCategories.find((c) => c.categoryId === id)?.image || "";

// Hero banner wale hi classy rang
const collections = [
  {
    id: "women_western_wear",
    kicker: "Trending Now",
    line1: "Women's",
    line2: "Fashion",
    offer: "Up to 60% off",
    bg: "#f3ebe3", // cream
    fg: "#2a1d17",
    accent: "#b4532a", // terracotta
    size: "lg:col-span-2 lg:row-span-2",
    big: true,
  },
  {
    id: "men_topwear",
    kicker: "New In",
    line1: "Men's",
    line2: "Topwear",
    offer: "Min. 40% off",
    bg: "#1b2420", // deep forest
    fg: "#f4efe6",
    accent: "#c9a96e", // gold
    size: "lg:col-span-2",
  },
  {
    id: "mobiles",
    kicker: "Top Deals",
    line1: "Smart",
    line2: "phones",
    offer: "From ₹7,999",
    bg: "#eceaf4", // soft lavender
    fg: "#1e1b3a",
    accent: "#5b5bd6",
    size: "",
    product: true, // white-background product photo
  },
  {
    id: "home_decor",
    kicker: "Home",
    line1: "Home",
    line2: "Décor",
    offer: "Under ₹499",
    bg: "#e8e5da", // sage ivory
    fg: "#1f2d24",
    accent: "#5b7a5a", // olive
    size: "",
  },
];

// Raat 12 baje tak ka countdown
const useCountdown = () => {
  const calc = () => {
    const now = new Date();
    const end = new Date(now);
    end.setHours(24, 0, 0, 0);
    const diff = Math.max(0, end.getTime() - now.getTime());
    return {
      h: Math.floor(diff / 3600000),
      m: Math.floor((diff / 60000) % 60),
      s: Math.floor((diff / 1000) % 60),
    };
  };
  const [time, setTime] = useState(calc);
  useEffect(() => {
    const t = setInterval(() => setTime(calc()), 1000);
    return () => clearInterval(t);
  }, []);
  return time;
};

const TimeBox = ({ value, label }: { value: number; label: string }) => (
  <div className="flex flex-col items-center">
    <span
      key={value}
      className="fc-serif fc-tick w-14 lg:w-16 h-14 lg:h-16 grid place-items-center rounded-xl border border-[#c9a96e]/40 bg-white/5 text-2xl lg:text-3xl font-semibold text-[#f4efe6] tabular-nums"
    >
      {String(value).padStart(2, "0")}
    </span>
    <span className="text-[10px] font-semibold uppercase tracking-[0.25em] text-[#c9a96e] mt-2">{label}</span>
  </div>
);

const FeaturedCollections = () => {
  const navigate = useNavigate();
  const { h, m, s } = useCountdown();

  return (
    <section className="space-y-1.5">
      {/* ---------- Flash sale strip ---------- */}
      <div className="relative overflow-hidden bg-[#1b2420] text-[#f4efe6] px-5 lg:px-20 py-8 lg:py-10">
        {/* halki sunehri roshni + lines */}
        <div className="absolute -left-20 -top-24 w-80 h-80 rounded-full bg-[#c9a96e]/15 blur-3xl" />
        <div className="fc-lines absolute inset-0 pointer-events-none" />

        <div className="relative flex flex-col md:flex-row md:items-center justify-between gap-6">
          <div>
            <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#c9a96e]">
              <span className="w-10 h-px bg-[#c9a96e]" />
              Limited Time · Ends Tonight
            </p>
            <h2 className="fc-serif mt-3 text-3xl lg:text-5xl font-semibold leading-tight">
              The <span className="italic text-[#c9a96e]">Flash</span> Sale
            </h2>
            <p className="mt-2 text-sm text-[#f4efe6]/65">Extra 10% off on every prepaid order.</p>
          </div>

          <div className="flex items-start gap-2 lg:gap-3">
            <TimeBox value={h} label="Hours" />
            <span className="fc-serif text-3xl text-[#c9a96e] pt-3">:</span>
            <TimeBox value={m} label="Mins" />
            <span className="fc-serif text-3xl text-[#c9a96e] pt-3">:</span>
            <TimeBox value={s} label="Secs" />
          </div>
        </div>
      </div>

      {/* ---------- Editorial collection cards ---------- */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 lg:grid-rows-2 gap-1.5 lg:h-[600px]">
        {collections.map((c, i) => (
          <div
            key={c.id}
            onClick={() => navigate(`/products/${c.id}`)}
            className={`${c.size} group relative overflow-hidden cursor-pointer min-h-[260px]`}
            style={{ background: c.bg, color: c.fg }}
          >
            {/* Peeche bada outline number */}
            <span
              className="fc-serif fc-num pointer-events-none select-none absolute right-4 top-2 leading-none"
              style={{ ["--fg" as any]: c.fg, fontSize: c.big ? 200 : 110 }}
            >
              0{i + 1}
            </span>

            {/* Photo */}
            {c.product ? (
              <div className="absolute right-4 bottom-4 w-[48%] h-[70%] grid place-items-center">
                <span className="absolute w-[85%] aspect-square rounded-full bg-white/70" />
                <img
                  src={imageOf(c.id)}
                  alt={c.line1 + c.line2}
                  className="relative h-[85%] object-contain mix-blend-multiply group-hover:scale-110 group-hover:-rotate-3 transition-transform duration-500"
                />
              </div>
            ) : (
              <img
                src={imageOf(c.id)}
                alt={`${c.line1} ${c.line2}`}
                className={`absolute right-0 bottom-0 h-full ${c.big ? "w-[58%]" : "w-[50%]"} object-cover object-top group-hover:scale-105 transition-transform duration-700`}
                style={{ maskImage: "linear-gradient(to right, transparent 0%, black 40%)", WebkitMaskImage: "linear-gradient(to right, transparent 0%, black 40%)" }}
              />
            )}

            {/* Text */}
            <div className={`relative z-10 h-full flex flex-col justify-between ${c.big ? "p-8 lg:p-12 lg:pl-20" : "p-6 lg:p-8"}`}>
              <div className="max-w-[60%]">
                <p className="flex items-center gap-2.5 text-[10px] lg:text-[11px] font-semibold tracking-[0.3em] uppercase" style={{ color: c.accent }}>
                  <span className="w-7 h-px" style={{ background: c.accent }} />
                  {c.kicker}
                </p>
                <h3 className={`fc-serif mt-3 font-semibold leading-[0.95] ${c.big ? "text-5xl lg:text-7xl" : "text-3xl lg:text-4xl"}`}>
                  {c.line1}
                  <span className="block italic font-medium" style={{ color: c.accent }}>{c.line2}</span>
                </h3>
                <p className={`mt-4 font-medium opacity-75 ${c.big ? "text-base lg:text-lg" : "text-sm"}`}>{c.offer}</p>
              </div>

              {/* CTA */}
              <span className="mt-5 inline-flex w-fit items-center gap-3 text-sm font-semibold">
                <span className="relative">
                  Shop the Edit
                  <span className="absolute left-0 -bottom-1 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 transition-transform duration-300" style={{ background: c.fg }} />
                </span>
                <span
                  className="w-9 h-9 rounded-full grid place-items-center text-white group-hover:rotate-45 transition-transform duration-300"
                  style={{ background: c.accent }}
                >
                  <ArrowOutwardIcon sx={{ fontSize: 17 }} />
                </span>
              </span>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
};

export default FeaturedCollections;