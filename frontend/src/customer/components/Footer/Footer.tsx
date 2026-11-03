import { useNavigate } from "react-router-dom";
import FacebookIcon from "@mui/icons-material/Facebook";
import InstagramIcon from "@mui/icons-material/Instagram";
import XIcon from "@mui/icons-material/X";
import YouTubeIcon from "@mui/icons-material/YouTube";
import EmailOutlinedIcon from "@mui/icons-material/EmailOutlined";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";
import LocalShippingOutlinedIcon from "@mui/icons-material/LocalShippingOutlined";
import ReplayOutlinedIcon from "@mui/icons-material/ReplayOutlined";
import VerifiedUserOutlinedIcon from "@mui/icons-material/VerifiedUserOutlined";
import "./Footer.css";

const columns = [
  {
    title: "Shop",
    links: [
      { name: "Men", to: "/products/men_topwear" },
      { name: "Women", to: "/products/women_western_wear" },
      { name: "Electronics", to: "/products/mobiles" },
      { name: "Home & Furniture", to: "/products/home_decor" },
    ],
  },
  {
    title: "My Account",
    links: [
      { name: "Orders", to: "/account/orders" },
      { name: "Wishlist", to: "/wishlist" },
      { name: "Cart", to: "/cart" },
      { name: "Become a Seller", to: "/become-seller" },
    ],
  },
  {
    title: "Help",
    links: [
      { name: "Shipping", to: "/" },
      { name: "Returns", to: "/" },
      { name: "FAQs", to: "/" },
      { name: "Contact Us", to: "/" },
    ],
  },
];

// Hover par har icon apne brand ke rang ka
const socials = [
  { Icon: FacebookIcon, label: "Facebook", color: "#1877f2" },
  { Icon: InstagramIcon, label: "Instagram", color: "#e1306c" },
  { Icon: XIcon, label: "X", color: "#000000" },
  { Icon: YouTubeIcon, label: "YouTube", color: "#ff0000" },
];

const payments = ["UPI", "Visa", "Mastercard", "RuPay", "Razorpay", "Stripe"];

const trust = [
  { Icon: LocalShippingOutlinedIcon, text: "Free delivery above ₹999" },
  { Icon: ReplayOutlinedIcon, text: "7-day easy returns" },
  { Icon: VerifiedUserOutlinedIcon, text: "100% secure payments" },
];

const Footer = () => {
  const navigate = useNavigate();

  return (
    // Poori chaudai, upar koi margin nahi — Seller banner se seedha chipka hua
    <footer className="relative overflow-hidden bg-[#141b18] text-[#f4efe6]/60">
      <div className="relative">
        {/* Bada watermark text */}
        <span className="footer-watermark footer-serif italic pointer-events-none select-none absolute left-1/2 -translate-x-1/2 bottom-6 whitespace-nowrap">
          Shopzy
        </span>

      {/* ---------- Newsletter card (footer ke andar, upar) ---------- */}
      <div className="relative z-10 px-5 lg:px-20 pt-10 lg:pt-12">
        <div
          className="relative overflow-hidden rounded-2xl px-6 py-8 lg:px-10 lg:py-10 flex flex-col lg:flex-row lg:items-center justify-between gap-6 bg-[#f6f1ea] text-[#2a1d17]"
        >
          <span className="footer-serif absolute -right-4 -bottom-10 italic text-[140px] leading-none text-transparent pointer-events-none select-none hidden md:block" style={{ WebkitTextStroke: "1.5px rgba(42,29,23,0.07)" }}>Letters</span>

          <div className="relative flex items-center gap-4">
            <span className="hidden sm:grid w-14 h-14 shrink-0 rounded-full bg-[#2a1d17] text-[#c9a96e] place-items-center">
              <MarkEmailReadOutlinedIcon sx={{ fontSize: 26 }} />
            </span>
            <div>
              <p className="text-[10px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">The Shopzy Letter</p>
              <h3 className="footer-serif mt-1 text-2xl lg:text-3xl font-semibold leading-tight">Get exclusive offers <span className="italic font-medium text-[#b4532a]">first</span></h3>
              <p className="text-[#2a1d17]/65 text-sm mt-1">New arrivals, flash sales & coupons — straight to your inbox.</p>
            </div>
          </div>

          <form
            onSubmit={(e) => e.preventDefault()}
            className="relative flex items-center bg-white rounded-full p-1.5 w-full lg:max-w-md border border-[#2a1d17]/10 shadow-[0_10px_30px_-15px_rgba(42,29,23,0.35)]"
          >
            <EmailOutlinedIcon sx={{ fontSize: 20, ml: 1.5, color: "#9ca3af" }} />
            <input
              type="email"
              required
              placeholder="Enter your email"
              className="newsletter-input flex-1 min-w-0 h-10 bg-transparent outline-none rounded-full px-3 text-sm text-gray-800 placeholder:text-gray-400"
            />
            <button className="shrink-0 bg-[#2a1d17] hover:bg-[#b4532a] text-[#f6f1ea] text-sm font-semibold px-5 py-2.5 rounded-full transition-colors">
              Subscribe
            </button>
          </form>
        </div>
      </div>

      {/* ---------- Main footer ---------- */}
        <div className="relative px-5 lg:px-20 pt-12 lg:pt-14 pb-12 grid gap-10 grid-cols-2 md:grid-cols-4 lg:grid-cols-12">
          {/* Brand */}
          <div className="col-span-2 md:col-span-4 lg:col-span-4 space-y-5 min-w-0">
            <button onClick={() => navigate("/")} className="flex items-center gap-2">
              <span className="footer-serif w-9 h-9 rounded-xl grid place-items-center text-[#c9a96e] font-semibold italic text-xl bg-[#26312c] border border-[#c9a96e]/45">
                S
              </span>
              <span className="footer-serif text-3xl font-semibold text-[#f4efe6]">Shop<span className="italic font-medium text-[#c9a96e]">zy</span></span>
            </button>
            <p className="text-sm leading-relaxed max-w-sm">
              Your one-stop shop for fashion, electronics and home essentials — from trusted sellers, delivered fast.
            </p>
            <ul className="space-y-2.5">
              {trust.map(({ Icon, text }) => (
                <li key={text} className="flex items-center gap-2.5 text-sm text-[#f4efe6]/75">
                  <Icon sx={{ fontSize: 18, color: "#c9a96e" }} /> {text}
                </li>
              ))}
            </ul>
          </div>

          {/* Link columns */}
          {columns.map((col) => (
            <div key={col.title} className="lg:col-span-2">
              <h4 className="footer-serif text-[#f4efe6] text-lg font-semibold mb-5 relative inline-block">
                {col.title}
                <span className="absolute -bottom-2 left-0 w-6 h-0.5 rounded-full bg-[#c9a96e]" />
              </h4>
              <ul className="space-y-3 text-sm">
                {col.links.map((l) => (
                  <li key={l.name}>
                    <button onClick={() => navigate(l.to)} className="footer-link text-left hover:text-white transition-colors">
                      {l.name}
                    </button>
                  </li>
                ))}
              </ul>
            </div>
          ))}

          {/* Social */}
          <div className="col-span-2 md:col-span-4 lg:col-span-2">
            <h4 className="footer-serif text-[#f4efe6] text-lg font-semibold mb-5 relative inline-block">
              Follow us
              <span className="absolute -bottom-2 left-0 w-6 h-0.5 rounded-full bg-[#c9a96e]" />
            </h4>
            <div className="flex flex-wrap gap-1.5">
              {socials.map(({ Icon, label, color }) => (
                <a
                  key={label}
                  href="#"
                  aria-label={label}
                  className="social-btn w-8 h-8 rounded-lg bg-white/5 border border-white/10 grid place-items-center text-gray-300 hover:text-white hover:-translate-y-1 transition-all"
                  style={{ ["--brand" as any]: color }}
                >
                  <Icon sx={{ fontSize: 17 }} />
                </a>
              ))}
            </div>
          </div>
        </div>

        {/* Payments */}
        <div className="relative px-5 lg:px-20 pb-8 flex flex-wrap items-center gap-2">
          <span className="text-xs font-semibold uppercase tracking-wider text-gray-500 mr-2">We accept</span>
          {payments.map((p) => (
            <span key={p} className="text-xs font-bold text-[#f4efe6]/75 bg-white/5 border border-[#c9a96e]/20 rounded-md px-2.5 py-1">
              {p}
            </span>
          ))}
        </div>

        {/* Bottom bar */}
        <div className="relative border-t border-white/10">
          <div className="px-5 lg:px-20 py-5 flex flex-col md:flex-row justify-between items-center gap-4 text-xs text-gray-500">
            <p>© {new Date().getFullYear()} Shopzy. All rights reserved. Made with ❤️ in India</p>
            <div className="flex items-center gap-5">
              <span className="hover:text-white cursor-pointer">Privacy</span>
              <span className="hover:text-white cursor-pointer">Terms</span>
              <button
                onClick={() => window.scrollTo({ top: 0, behavior: "smooth" })}
                className="group flex items-center gap-1.5 text-gray-300 hover:text-[#1b2420] bg-white/5 hover:bg-[#c9a96e] border border-white/10 rounded-full pl-3 pr-1.5 py-1.5 transition-colors"
              >
                Back to top
                <span className="w-5 h-5 rounded-full bg-white/10 grid place-items-center group-hover:-translate-y-0.5 transition-transform">
                  <ArrowUpwardIcon sx={{ fontSize: 13 }} />
                </span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;