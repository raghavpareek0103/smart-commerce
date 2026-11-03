import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import "./Banner.css";

// Pexels ki free photos (commercial use allowed). ID badal ke koi bhi photo laga sakte ho.
const px = (id: number, w = 900) =>
  `https://images.pexels.com/photos/${id}/pexels-photo-${id}.jpeg?auto=compress&cs=tinysrgb&w=${w}`;

// ---- Yahan se banners ka text / rang / photo badlo ----
const slides = [
  {
    label: "Women",
    kicker: "The Spring Edit",
    line1: "Effortless",
    line2: "Elegance.",
    text: "Dresses, co-ords & everyday luxe — curated for the woman who sets the trend.",
    offer: "Up to 60% off",
    chip: "Starting ₹499",
    route: "/products/women_western_wear",
    main: px(1036623),
    small: px(1536619, 500),
    bg: "#f3ebe3", // cream
    fg: "#2a1d17",
    accent: "#b4532a", // terracotta
  },
  {
    label: "Men",
    kicker: "Modern Tailoring",
    line1: "Sharp by",
    line2: "Design.",
    text: "Blazers, shirts & smart casuals cut for the man who means business.",
    offer: "Min. 40% off",
    chip: "Starting ₹799",
    route: "/products/men_topwear",
    main: px(1043474),
    small: px(1300550, 500),
    bg: "#1b2420", // deep forest
    fg: "#f4efe6",
    accent: "#c9a96e", // gold
  },
  {
    label: "Tech",
    kicker: "Sound & Smart",
    line1: "Hear Every",
    line2: "Detail.",
    text: "Premium headphones & smartwatches from brands you trust.",
    offer: "From ₹999",
    chip: "No-cost EMI",
    route: "/products/headphones_headsets",
    main: px(3756766),
    small: px(437037, 500),
    bg: "#11131f", // midnight
    fg: "#eef0ff",
    accent: "#a5b4fc", // lavender
  },
  {
    label: "Home",
    kicker: "Living Collection",
    line1: "Calm, Curated",
    line2: "Spaces.",
    text: "Décor, lighting & furnishings that turn a house into a home.",
    offer: "Under ₹499",
    chip: "New arrivals",
    route: "/products/home_decor",
    main: px(6908500),
    small: px(1571460, 500),
    bg: "#e8e5da", // sage ivory
    fg: "#1f2d24",
    accent: "#5b7a5a", // olive
  },
];

const DELAY = 3000; // har 3 second me agla banner

const Banner = () => {
  const navigate = useNavigate();
  const [active, setActive] = useState(0);
  const s = slides[active];

  // Automatic rotate (tab dabane pe timer phir se 3 sec se shuru)
  useEffect(() => {
    const t = setTimeout(() => setActive((active + 1) % slides.length), DELAY);
    return () => clearTimeout(t);
  }, [active]);

  return (
    <section
      className="hero relative overflow-hidden"
      style={{ background: s.bg, color: s.fg, ["--accent" as any]: s.accent, ["--bg" as any]: s.bg, ["--fg" as any]: s.fg }}
    >
      {/* Peeche bada outline number */}
      <span key={"n" + active} className="hero-number hero-serif pointer-events-none select-none absolute top-4 left-[40%] hidden lg:block">
        0{active + 1}
      </span>

      {/* Mobile: photo peeche, upar rang ki parat */}
      <div className="md:hidden absolute inset-0">
        <img key={"m" + active} src={s.main} alt="" className="hero-kenburns w-full h-full object-cover object-[70%_20%]" />
        <div className="absolute inset-0" style={{ background: `linear-gradient(180deg, ${s.bg}e6 0%, ${s.bg}cc 45%, ${s.bg}55 100%)` }} />
      </div>

      <div className="relative px-5 md:px-12 lg:px-20 h-[560px] md:h-[500px] lg:h-[580px] grid md:grid-cols-2 gap-8 pb-16">
        {/* ---------- Left: text ---------- */}
        <div key={"t" + active} className="relative z-10 flex flex-col justify-center">
          <p className="hero-rise flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase" style={{ animationDelay: "0ms" }}>
            <span className="w-10 h-px" style={{ background: s.accent }} />
            <span style={{ color: s.accent }}>{s.kicker}</span>
          </p>

          <h1 className="hero-serif mt-5 text-5xl sm:text-6xl lg:text-[84px] leading-[0.95] font-semibold">
            <span className="hero-rise block" style={{ animationDelay: "80ms" }}>{s.line1}</span>
            <span className="hero-rise block italic font-medium" style={{ animationDelay: "160ms", color: s.accent }}>{s.line2}</span>
          </h1>

          <p className="hero-rise mt-5 max-w-md text-sm sm:text-base leading-relaxed opacity-75" style={{ animationDelay: "240ms" }}>
            {s.text}
          </p>

          <div className="hero-rise mt-8 flex flex-wrap items-center gap-4" style={{ animationDelay: "320ms" }}>
            <button
              onClick={() => navigate(s.route)}
              className="hero-cta group flex items-center gap-3 h-12 pl-6 pr-2 rounded-full text-sm font-semibold tracking-wide"
              style={{ background: s.fg, color: s.bg }}
            >
              Shop the Edit
              <span className="w-8 h-8 rounded-full grid place-items-center group-hover:rotate-45 transition-transform duration-300" style={{ background: s.accent, color: "#fff" }}>
                <ArrowOutwardIcon sx={{ fontSize: 17 }} />
              </span>
            </button>
            <span className="h-12 px-5 rounded-full grid place-items-center text-sm font-semibold border" style={{ borderColor: s.fg + "33" }}>
              {s.offer}
            </span>
          </div>
        </div>

        {/* ---------- Right: photo composition (tablet/desktop) ---------- */}
        <div key={"p" + active} className="relative hidden md:block">
          {/* Arch photo */}
          <div className="hero-img-in absolute right-0 lg:right-6 top-10 bottom-0 w-[60%] rounded-t-full overflow-hidden shadow-2xl">
            <img src={s.main} alt={s.label} className="hero-kenburns w-full h-full object-cover object-top" />
          </div>

          {/* Chhoti tilted photo */}
          <div className="hero-small absolute left-[4%] bottom-8 w-[36%] aspect-[3/4] rounded-2xl overflow-hidden border-[6px] shadow-xl" style={{ borderColor: s.bg }}>
            <img src={s.small} alt="" className="w-full h-full object-cover" />
          </div>

          {/* Ghoomta hua gol badge */}
          <button
            onClick={() => navigate(s.route)}
            aria-label="Shop now"
            className="absolute left-[24%] top-10 w-28 h-28 lg:w-32 lg:h-32 grid place-items-center"
          >
            <svg viewBox="0 0 100 100" className="hero-spin absolute inset-0 w-full h-full">
              <defs>
                <path id="heroCircle" d="M50,50 m-38,0 a38,38 0 1,1 76,0 a38,38 0 1,1 -76,0" />
              </defs>
              <text fill="currentColor" fontSize="9" fontWeight="600">
                <textPath href="#heroCircle" textLength="236" lengthAdjust="spacing">NEW SEASON • SHOP NOW • SHOPZY •</textPath>
              </text>
            </svg>
            <span className="w-12 h-12 lg:w-14 lg:h-14 rounded-full grid place-items-center text-white shadow-lg" style={{ background: s.accent }}>
              <ArrowOutwardIcon />
            </span>
          </button>

          {/* Price chip */}
          <div className="hero-float absolute right-2 lg:right-0 bottom-14 rounded-2xl px-4 py-3 bg-white/90 backdrop-blur shadow-xl text-gray-900">
            <p className="text-[10px] font-semibold uppercase tracking-widest text-gray-500">{s.label} Collection</p>
            <p className="hero-serif text-lg font-semibold">{s.chip}</p>
          </div>
        </div>
      </div>

      {/* ---------- Neeche numbered tabs + progress ---------- */}
      <div className="absolute bottom-0 inset-x-0 px-5 md:px-12 lg:px-20">
        <div className="grid grid-cols-4 border-t" style={{ borderColor: s.fg + "22" }}>
          {slides.map((sl, i) => (
            <button key={sl.label} onClick={() => setActive(i)} className="relative text-left py-4 group">
              <span className="absolute -top-px left-0 right-3 h-[2px] overflow-hidden">
                {i === active && <span key={active} className="hero-progress block h-full" style={{ background: s.accent }} />}
              </span>
              <span className={`flex items-baseline gap-2 transition-opacity ${i === active ? "opacity-100" : "opacity-45 group-hover:opacity-80"}`}>
                <span className="hero-serif italic text-sm">0{i + 1}</span>
                <span className="hidden sm:inline text-xs font-semibold tracking-[0.2em] uppercase">{sl.label}</span>
                {i === active && <ArrowForwardIcon sx={{ fontSize: 14 }} className="hidden lg:inline" />}
              </span>
            </button>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Banner;