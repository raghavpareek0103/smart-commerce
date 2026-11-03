import { Avatar, Badge, Drawer, Tooltip, useMediaQuery, useTheme } from "@mui/material";
import React, { useEffect, useRef, useState } from "react";
import "./Navbar.css";
import SearchIcon from "@mui/icons-material/Search";
import MenuIcon from "@mui/icons-material/Menu";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ShoppingBagOutlinedIcon from "@mui/icons-material/ShoppingBagOutlined";
import StorefrontOutlinedIcon from "@mui/icons-material/StorefrontOutlined";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import LocalShippingOutlinedIcon from "@mui/icons-material/LocalShippingOutlined";
import HelpOutlineIcon from "@mui/icons-material/HelpOutline";
import CloseIcon from "@mui/icons-material/Close";
import { mainCategory } from "../../../data/category/mainCategory";
import CategorySheet from "./CategorySheet";
import DrawerList from "./DrawerList";
import { useNavigate } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../../Redux Toolkit/Store";
import { searchProduct } from "../../../Redux Toolkit/Customer/ProductSlice";

// Upar wali patti ke messages — pehla hissa serif italic me, baaki chhote capital akshar
const offers = [
  { em: "The Flash Sale", text: "Extra 10% off on prepaid orders" },
  { em: "Complimentary", text: "Delivery on orders above ₹999" },
  { em: "New Arrivals", text: "Fresh edits every week" },
  { em: "Easy Returns", text: "7 days on all fashion" },
  { em: "Secure", text: "100% protected payments" },
];

// Search box me ghoomte hue suggestions
const searchHints = ["sneakers", "smartwatch", "kurta sets", "headphones", "home décor", "jeans"];

// Gol icon button + count badge
const IconBtn = ({ icon, label, count, onClick }: { icon: React.ReactNode; label: string; count?: number; onClick: () => void }) => (
  <Tooltip title={label} arrow>
    <button
      onClick={onClick}
      aria-label={label}
      className="w-10 h-10 lg:w-11 lg:h-11 rounded-full grid place-items-center text-[#2a1d17] hover:text-[#b4532a] bg-[#fbf8f4] hover:bg-[#f3ebe3] border border-[#2a1d17]/10 hover:border-[#b4532a]/40 transition-all hover:-translate-y-0.5"
    >
      <Badge
        badgeContent={count}
        color="error"
        sx={{ "& .MuiBadge-badge": { fontSize: 10, height: 17, minWidth: 17, fontWeight: 700, top: -2, right: -2 } }}
      >
        {icon}
      </Badge>
    </button>
  </Tooltip>
);

const Navbar = () => {
  const [showSheet, setShowSheet] = useState(false);
  const [selectedCategory, setSelectedCategory] = useState("men");
  const [open, setOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [showBar, setShowBar] = useState(true);
  const [hintIndex, setHintIndex] = useState(0);
  const [scrolled, setScrolled] = useState(false);
  const [pill, setPill] = useState<{ left: number; width: number } | null>(null);
  const searchRef = useRef<HTMLInputElement>(null);

  const theme = useTheme();
  const isLarge = useMediaQuery(theme.breakpoints.up("lg"));
  const dispatch = useAppDispatch();
  const { user, cart, sellers, wishlist } = useAppSelector((store) => store);
  const navigate = useNavigate();

  const toggleDrawer = (newOpen: boolean) => () => setOpen(newOpen);

  const becomeSellerClick = () => {
    if (sellers.profile?.id) navigate("/seller");
    else navigate("/become-seller");
  };

  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault();
    if (query.trim()) dispatch(searchProduct(query.trim()));
    navigate("/search-products");
  };

  // Search hints rotate
  useEffect(() => {
    const t = setInterval(() => {
      setHintIndex((i) => (i + 1) % searchHints.length);
    }, 3000);
    return () => clearInterval(t);
  }, []);

  // Scroll karne par navbar thoda chhota + shadow
  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 10);
    window.addEventListener("scroll", onScroll);
    return () => window.removeEventListener("scroll", onScroll);
  }, []);

  // "/" dabane par search box par focus
  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      const tag = (e.target as HTMLElement)?.tagName;
      if (e.key === "/" && tag !== "INPUT" && tag !== "TEXTAREA") {
        e.preventDefault();
        searchRef.current?.focus();
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, []);

  const firstName = user.user?.fullName?.split(" ")[0];

  return (
    <header className="sticky top-0 left-0 right-0 z-50">
      {/* ---------- Top announcement bar ---------- */}
      {showBar && (
        <div className="announce-bar relative overflow-hidden bg-[#141b18] text-[#f4efe6] border-b border-[#c9a96e]/20">
          {/* guzarti hui sunehri chamak */}
          <span className="announce-shine pointer-events-none absolute inset-y-0 w-40" />

          <div className="relative px-5 lg:px-20 h-9 flex items-center gap-4">
            {/* Chalta hua offers ticker */}
            <div className="ticker flex-1 min-w-0 overflow-hidden">
              <div className="ticker-track flex w-max items-center">
                {[0, 1].map((copy) => (
                  <div key={copy} className="flex shrink-0 items-center" aria-hidden={copy === 1}>
                    {offers.map((o) => (
                      <span key={o.em + copy} className="flex items-center whitespace-nowrap">
                        <span className="nav-serif italic text-[13px] text-[#c9a96e]">{o.em}</span>
                        <span className="mx-2.5 w-4 h-px bg-[#c9a96e]/50" />
                        <span className="text-[10px] font-semibold tracking-[0.22em] uppercase text-[#f4efe6]/80">{o.text}</span>
                        <span className="mx-7 text-[9px] text-[#c9a96e]/70">✦</span>
                      </span>
                    ))}
                  </div>
                ))}
              </div>
            </div>

            {/* Links — patli lines se alag */}
            <div className="hidden md:flex items-center shrink-0 text-[10px] font-semibold tracking-[0.22em] uppercase">
              <button onClick={() => navigate("/account/orders")} className="flex items-center gap-1.5 px-3 text-[#f4efe6]/70 hover:text-[#c9a96e] transition-colors">
                <LocalShippingOutlinedIcon sx={{ fontSize: 14 }} /> Track Order
              </button>
              <span className="w-px h-3 bg-[#f4efe6]/20" />
              <button onClick={becomeSellerClick} className="flex items-center gap-1.5 px-3 text-[#f4efe6]/70 hover:text-[#c9a96e] transition-colors">
                <StorefrontOutlinedIcon sx={{ fontSize: 14 }} /> Sell on Shopzy
              </button>
              <span className="w-px h-3 bg-[#f4efe6]/20" />
              <button onClick={() => navigate("/")} className="flex items-center gap-1.5 px-3 text-[#f4efe6]/70 hover:text-[#c9a96e] transition-colors">
                <HelpOutlineIcon sx={{ fontSize: 14 }} /> Help
              </button>
            </div>

            {/* Band karne ka button */}
            <button
              onClick={() => setShowBar(false)}
              aria-label="Close announcement"
              className="shrink-0 w-6 h-6 rounded-full grid place-items-center border border-[#c9a96e]/30 text-[#c9a96e]/80 hover:bg-[#c9a96e] hover:text-[#141b18] transition-colors"
            >
              <CloseIcon sx={{ fontSize: 13 }} />
            </button>
          </div>
        </div>
      )}

      {/* ---------- Main navbar (poori chaudai) ---------- */}
      <div>
        <div
          className={`nav-capsule relative border-b transition-all duration-300 ${scrolled
            ? "bg-[#f6f1ea]/95 border-[#2a1d17]/10 shadow-[0_12px_30px_-18px_rgba(42,29,23,0.35)]"
            : "bg-[#f6f1ea] border-[#2a1d17]/10"}`}
        >
          <div className={`flex items-center gap-2 lg:gap-6 px-3 sm:px-5 lg:px-20 transition-all duration-300 ${scrolled ? "h-16" : "h-[72px]"}`}>
            {/* Menu (mobile) + Logo */}
            <div className="flex items-center gap-1 shrink-0">
              {!isLarge && (
                <button onClick={toggleDrawer(true)} aria-label="Menu" className="w-10 h-10 rounded-full grid place-items-center text-[#2a1d17] hover:bg-[#2a1d17]/5">
                  <MenuIcon />
                </button>
              )}
              <button onClick={() => navigate("/")} className="flex items-center gap-2 group">
                <span className="logo-badge nav-serif w-9 h-9 rounded-xl grid place-items-center text-[#c9a96e] font-semibold italic text-xl shadow-md">
                  S
                </span>
                <span className="nav-serif text-2xl md:text-[28px] font-semibold text-[#2a1d17] leading-none">Shop<span className="italic font-medium text-[#b4532a]">zy</span></span>
              </button>
            </div>

            {/* Categories with sliding pill (desktop) */}
            {isLarge && (
              <nav
                className="relative flex items-center bg-[#2a1d17]/[0.05] rounded-full p-1"
                onMouseLeave={() => setPill(null)}
              >
                {/* sliding pill */}
                <span
                  className="nav-pill absolute top-1 bottom-1 rounded-full bg-white shadow-sm"
                  style={{
                    left: pill?.left ?? 0,
                    width: pill?.width ?? 0,
                    opacity: pill ? 1 : 0,
                  }}
                />
                {mainCategory.map((item) => (
                  <div
                    key={item.categoryId}
                    onMouseEnter={(e) => {
                      const el = e.currentTarget;
                      setPill({ left: el.offsetLeft, width: el.offsetWidth });
                      setSelectedCategory(item.categoryId);
                      setShowSheet(true);
                    }}
                    onMouseLeave={() => setShowSheet(false)}
                    className={`relative z-10 px-4 py-2 rounded-full text-sm font-semibold whitespace-nowrap cursor-pointer transition-colors ${showSheet && selectedCategory === item.categoryId ? "text-[#b4532a]" : "text-[#2a1d17]/75 hover:text-[#2a1d17]"}`}
                  >
                    {item.name}
                  </div>
                ))}
              </nav>
            )}

            {/* Search (tablet / desktop) */}
            <form onSubmit={handleSearch} className="hidden md:flex flex-1 max-w-lg ml-auto">
              <div className="group/search relative flex items-center w-full h-11 rounded-full bg-[#2a1d17]/[0.05] border border-transparent focus-within:bg-white focus-within:border-[#b4532a] focus-within:shadow-[0_0_0_4px_rgba(180,83,42,0.12)] transition-all">
                <SearchIcon sx={{ fontSize: 20, color: "#8a7a70", ml: 1.75 }} />
                <div className="relative flex-1 min-w-0 h-full">
                  <input
                    ref={searchRef}
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    aria-label="Search products"
                    className="absolute inset-0 w-full bg-transparent outline-none px-3 text-sm text-gray-800"
                  />
                  {/* Ghoomta hua placeholder */}
                  {!query && (
                    <span className="pointer-events-none absolute inset-y-0 left-3 flex items-center text-sm text-[#2a1d17]/45 whitespace-nowrap overflow-hidden">
                      Search for&nbsp;
                      <span key={hintIndex} className="hint-roll nav-serif italic font-semibold text-[#b4532a]">"{searchHints[hintIndex]}"</span>
                    </span>
                  )}
                </div>
                <kbd className="hidden xl:grid place-items-center w-6 h-6 mr-1.5 rounded-md border border-[#2a1d17]/20 bg-[#fbf8f4] text-[11px] font-semibold text-[#2a1d17]/55 group-focus-within/search:hidden">/</kbd>
                <button
                  type="submit"
                  aria-label="Search"
                  className="search-btn w-9 h-9 mr-1 rounded-full grid place-items-center text-white transition-transform hover:scale-105"
                >
                  <ArrowForwardIcon sx={{ fontSize: 18 }} />
                </button>
              </div>
            </form>

            {/* Right side */}
            <div className="flex items-center gap-1.5 lg:gap-2 ml-auto md:ml-0 shrink-0">
              <span className="md:hidden">
                <IconBtn icon={<SearchIcon fontSize="small" />} label="Search" onClick={() => navigate("/search-products")} />
              </span>

              <IconBtn
                icon={<FavoriteBorderIcon fontSize="small" />}
                label="Wishlist"
                count={wishlist.wishlist?.products?.length || 0}
                onClick={() => navigate("/wishlist")}
              />
              <IconBtn
                icon={<ShoppingBagOutlinedIcon fontSize="small" />}
                label="Bag"
                count={cart.cart?.cartItems.length || 0}
                onClick={() => navigate("/cart")}
              />

              {/* Profile / Login chip */}
              {user.user ? (
                <button
                  onClick={() => navigate("/account/orders")}
                  className="flex items-center gap-2 h-10 lg:h-11 pl-1 pr-1 lg:pr-3.5 rounded-full bg-[#fbf8f4] border border-[#2a1d17]/10 hover:border-[#b4532a]/40 hover:bg-[#f3ebe3] transition-colors"
                >
                  <Avatar sx={{ width: 32, height: 32, fontSize: 14, fontWeight: 700, bgcolor: "#b4532a" }}>
                    {firstName?.[0]?.toUpperCase()}
                  </Avatar>
                  <span className="hidden lg:block text-sm font-semibold text-[#2a1d17] max-w-[80px] truncate">{firstName}</span>
                </button>
              ) : (
                <button
                  onClick={() => navigate("/login")}
                  className="flex items-center gap-1.5 h-10 lg:h-11 px-3 lg:px-4 rounded-full bg-[#2a1d17] hover:bg-[#b4532a] text-[#f6f1ea] text-sm font-semibold transition-colors"
                >
                  <PersonOutlineIcon sx={{ fontSize: 20 }} />
                  <span className="hidden sm:inline">Login</span>
                </button>
              )}

              {/* Become a seller (bahut badi screen) */}
              <button
                onClick={becomeSellerClick}
                className="seller-pill hidden 2xl:flex items-center gap-1.5 h-11 px-4 rounded-full text-sm font-semibold text-[#2a1d17]"
              >
                <StorefrontOutlinedIcon sx={{ fontSize: 19 }} />
                Become a Seller
              </button>
            </div>
          </div>

          {/* Mega menu */}
          {showSheet && selectedCategory && (
            <div
              onMouseLeave={() => setShowSheet(false)}
              onMouseEnter={() => setShowSheet(true)}
              className="categorySheet absolute top-full left-0 right-0 px-5 lg:px-20 pt-3"
            >
              <div className="rounded-2xl overflow-hidden shadow-[0_30px_60px_-20px_rgba(42,29,23,0.45)] border border-[#2a1d17]/10">
                <CategorySheet setShowSheet={setShowSheet} selectedCategory={selectedCategory} />
              </div>
            </div>
          )}
        </div>
      </div>

      <Drawer open={open} onClose={toggleDrawer(false)}>
        <DrawerList toggleDrawer={toggleDrawer} />
      </Drawer>
    </header>
  );
};

export default Navbar;