// RecentlyViewed.tsx
// Home page row: user ne jo products dekhe the (localStorage se). Kuch na dekha ho to section chhupa rehta hai.
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  getRecentlyViewed,
  clearRecentlyViewed,
  RECENT_EVENT,
  type RecentProduct,
} from "../../../../util/recentlyViewed";

const css = `
.rv-serif{font-family:'Playfair Display',serif}
.rv-row{display:flex;gap:20px;overflow-x:auto;scroll-snap-type:x mandatory;padding-bottom:10px;scrollbar-width:thin}
.rv-row::-webkit-scrollbar{height:6px}
.rv-row::-webkit-scrollbar-thumb{background:rgba(42,29,23,.2);border-radius:9px}
.rv-card{flex:0 0 200px;scroll-snap-align:start;cursor:pointer;text-align:left}
.rv-img{height:250px;border-radius:999px 999px 18px 18px;overflow:hidden;background:#ebe2d8;position:relative}
.rv-img img{width:100%;height:100%;object-fit:cover;object-position:top;transition:transform .6s}
.rv-card:hover .rv-img img{transform:scale(1.06)}
.rv-off{position:absolute;bottom:12px;left:12px;background:#2a1d17;color:#f3ebe3;font-size:10px;font-weight:700;padding:4px 8px;border-radius:999px}
.rv-title{margin-top:12px;font-size:14px;font-weight:600;color:#2a1d17;white-space:nowrap;overflow:hidden;text-overflow:ellipsis}
.rv-ago{font-size:10px;letter-spacing:.2em;text-transform:uppercase;color:#b4532a;font-weight:700;margin-top:10px}
@media (min-width:1024px){.rv-card{flex-basis:220px}.rv-img{height:280px}}
`;

const timeAgo = (t: number) => {
  const m = Math.floor((Date.now() - t) / 60000);
  if (m < 1) return "Just now";
  if (m < 60) return `${m} min ago`;
  const h = Math.floor(m / 60);
  if (h < 24) return `${h} hr ago`;
  return `${Math.floor(h / 24)} day ago`;
};

const RecentlyViewed = () => {
  const navigate = useNavigate();
  const [items, setItems] = useState<RecentProduct[]>([]);

  useEffect(() => {
    const load = () => setItems(getRecentlyViewed());
    load();
    window.addEventListener(RECENT_EVENT, load);
    window.addEventListener("storage", load);
    return () => {
      window.removeEventListener(RECENT_EVENT, load);
      window.removeEventListener("storage", load);
    };
  }, []);

  if (items.length === 0) return null;

  return (
    <section className="bg-[#f3ebe3] text-[#2a1d17] px-5 lg:px-20 py-14 lg:py-16">
      <style>{css}</style>

      <div className="flex items-end justify-between gap-6 mb-8">
        <div>
          <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
            <span className="w-10 h-px bg-[#b4532a]" />
            Picked up where you left
          </p>
          <h2 className="rv-serif mt-3 text-3xl sm:text-4xl lg:text-5xl font-semibold">
            Recently <span className="italic font-medium text-[#b4532a]">Viewed</span>
          </h2>
        </div>
        <button
          onClick={clearRecentlyViewed}
          className="text-xs font-semibold underline underline-offset-4 text-[#2a1d17]/60 hover:text-[#2a1d17]"
        >
          Clear history
        </button>
      </div>

      <div className="rv-row">
        {items.map((p) => (
          <button
            key={p.id}
            className="rv-card"
            onClick={() =>
              navigate(`/product-details/${p.categoryId}/${encodeURIComponent(p.title)}/${p.id}`)
            }
          >
            <div className="rv-img">
              {p.image && <img src={p.image} alt={p.title} loading="lazy" />}
              {!!p.discountPercent && <span className="rv-off">{p.discountPercent}% OFF</span>}
            </div>
            <p className="rv-ago">{timeAgo(p.viewedAt)}</p>
            <p className="rv-title" title={p.title}>{p.title}</p>
            <p className="mt-1 flex items-baseline gap-2">
              <span className="rv-serif text-lg font-semibold">₹{p.sellingPrice?.toLocaleString("en-IN")}</span>
              {p.mrpPrice > p.sellingPrice && (
                <span className="text-xs line-through text-[#2a1d17]/45">₹{p.mrpPrice?.toLocaleString("en-IN")}</span>
              )}
            </p>
          </button>
        ))}
      </div>
    </section>
  );
};

export default RecentlyViewed;