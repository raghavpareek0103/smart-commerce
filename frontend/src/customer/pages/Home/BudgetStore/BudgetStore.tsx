// BudgetStore.tsx
// Home page: "Shop by Budget" — 4 arch tiles. Har tile me us price range ke ASLI products ki
// photos (backend se) pankhe (fan) ki tarah dikhti hain + kitne styles hain wo count.
// Click -> /products/all?price=0-999 (backend me category "all" = koi category filter nahi).
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../../../Config/Api";

type Tile = {
  kicker: string; under: string; price: string; min: number; max: number;
  note: string; bg: string; win: string; ink: string; accent: string; dark?: boolean;
};

const tiles: Tile[] = [
  { kicker: "Pocket picks", under: "Under", price: "₹499", min: 0, max: 499, note: "Tees, décor & daily essentials",
    bg: "#efe2d2", win: "#e4cfb6", ink: "#2a1d17", accent: "#b4532a" },
  { kicker: "Smart buys", under: "Under", price: "₹999", min: 0, max: 999, note: "Kurtas, sneakers & accessories",
    bg: "#e3e8de", win: "#cdd8c6", ink: "#1c231f", accent: "#3e6b4f" },
  { kicker: "Best value", under: "Under", price: "₹1,999", min: 0, max: 1999, note: "Ethnic sets, smartwatches & more",
    bg: "#ebe4f2", win: "#d9cde8", ink: "#2b2140", accent: "#6a4fa3" },
  { kicker: "Treat yourself", under: "Luxe", price: "₹5k+", min: 5000, max: 1000000, note: "Premium tech, furniture & festive",
    bg: "#1c231f", win: "#2a332d", ink: "#f3ebe3", accent: "#e7b48f", dark: true },
];

type Preview = { images: string[]; total: number };

const css = `
.bs-serif{font-family:'Playfair Display',serif}
.bs-tile{position:relative;border-radius:26px;padding:14px 14px 22px;text-align:left;cursor:pointer;overflow:hidden;
  transition:transform .5s cubic-bezier(.2,.8,.2,1),box-shadow .5s}
.bs-tile:hover{transform:translateY(-8px);box-shadow:0 35px 60px -30px rgba(42,29,23,.55)}
.bs-win{position:relative;height:250px;border-radius:999px 999px 18px 18px;overflow:hidden}
.bs-win:before{content:"";position:absolute;inset:0;background-image:repeating-linear-gradient(115deg,rgba(255,255,255,.18) 0 1px,transparent 1px 18px);opacity:.6}
.bs-fan{position:absolute;left:50%;bottom:-14px;width:0;height:0}
.bs-card{position:absolute;bottom:0;width:112px;height:150px;border-radius:60px 60px 12px 12px;overflow:hidden;
  background:#fff;box-shadow:0 18px 30px -14px rgba(0,0,0,.45);border:3px solid #fff;transform-origin:50% 100%;
  transition:transform .6s cubic-bezier(.2,.8,.2,1)}
.bs-card img{width:100%;height:100%;object-fit:cover;object-position:top}
.bs-c0{left:-56px;z-index:3;transform:translateY(-34px) scale(1.12)}
.bs-c1{left:-118px;z-index:2;width:100px;height:136px;transform:rotate(-12deg) translateY(-4px)}
.bs-c2{left:18px;z-index:2;width:100px;height:136px;transform:rotate(12deg) translateY(-4px)}
.bs-tile:hover .bs-c0{transform:translateY(-44px) scale(1.16)}
.bs-tile:hover .bs-c1{transform:rotate(-17deg) translate(-8px,-8px)}
.bs-tile:hover .bs-c2{transform:rotate(17deg) translate(8px,-8px)}
.bs-skel{animation:bs-pulse 1.4s ease-in-out infinite;background:rgba(255,255,255,.55)}
.bs-bigprice{position:absolute;inset:0;display:grid;place-items:center;line-height:1}
.bs-badge{position:absolute;left:50%;top:20px;transform:translateX(-50%);white-space:nowrap;z-index:5;padding:6px 12px;border-radius:999px;font-size:10px;font-weight:700;
  letter-spacing:.2em;text-transform:uppercase}
.bs-go{width:40px;height:40px;border-radius:50%;display:grid;place-items:center;flex:none;transition:transform .35s}
.bs-tile:hover .bs-go{transform:rotate(45deg)}
@keyframes bs-pulse{0%,100%{opacity:.55}50%{opacity:1}}
@media (max-width:639px){
  .bs-win{height:180px}
  .bs-card{width:78px;height:106px;border-width:2px}
  .bs-c0{left:-39px;transform:translateY(-26px) scale(1.1)}
  .bs-c1{left:-84px;width:70px;height:96px}.bs-c2{left:14px;width:70px;height:96px}
  .bs-note{display:none}
}
@media (prefers-reduced-motion:reduce){.bs-card,.bs-tile,.bs-go{transition:none}.bs-skel{animation:none}}
`;

const BudgetStore = () => {
  const navigate = useNavigate();
  const [previews, setPreviews] = useState<Record<number, Preview>>({});
  const [loaded, setLoaded] = useState(false);

  // Har tile ke liye backend se us range ke products laao (photos + count)
  useEffect(() => {
    let alive = true;
    Promise.all(
      tiles.map((t) =>
        api
          .get("/products", {
            params: { category: "all", minPrice: t.min, maxPrice: t.max, sort: "price_high", pageNumber: 0 },
          })
          .then((r) => {
            const list: any[] = r.data?.content || [];
            const seen = new Set<string>();
            const images: string[] = [];
            for (const p of list) {
              const img = p?.images?.[0];
              if (img && !seen.has(img)) { seen.add(img); images.push(img); }
              if (images.length === 3) break;
            }
            return [t.max, { images, total: r.data?.totalElements ?? list.length }] as const;
          })
          .catch(() => [t.max, { images: [], total: 0 }] as const)
      )
    ).then((rows) => {
      if (!alive) return;
      setPreviews(Object.fromEntries(rows));
      setLoaded(true);
    });
    return () => { alive = false; };
  }, []);

  return (
    <section className="bg-[#f3ebe3] text-[#2a1d17] px-5 lg:px-20 py-14 lg:py-20">
      <style>{css}</style>

      <div className="flex flex-col sm:flex-row sm:items-end justify-between gap-4 mb-10">
        <div>
          <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
            <span className="w-10 h-px bg-[#b4532a]" />
            The Budget Store
          </p>
          <h2 className="bs-serif mt-3 text-3xl sm:text-4xl lg:text-5xl font-semibold">
            Shop by <span className="italic font-medium text-[#b4532a]">Budget</span>
          </h2>
        </div>
        <p className="max-w-xs text-sm text-[#2a1d17]/60">
          Great finds at every price — pick a budget, we'll do the filtering.
        </p>
      </div>

      <div className="grid grid-cols-2 lg:grid-cols-4 gap-3 sm:gap-5">
        {tiles.map((t) => {
          const pv = previews[t.max];
          const imgs = pv?.images || [];
          return (
            <button
              key={t.max}
              className="bs-tile"
              style={{ background: t.bg, color: t.ink }}
              onClick={() => navigate(`/products/all?price=${t.min}-${t.max}`)}
              aria-label={`${t.under} ${t.price}`}
            >
              {/* Arch window with fanned product photos */}
              <div className="bs-win" style={{ background: t.win }}>
                {pv && pv.total > 0 && (
                  <span className="bs-badge" style={{ background: t.dark ? "#f3ebe3" : "#fff", color: t.accent }}>
                    {pv.total}+ styles
                  </span>
                )}

                {!loaded ? (
                  <div className="bs-fan">
                    <span className="bs-card bs-c1 bs-skel" />
                    <span className="bs-card bs-c2 bs-skel" />
                    <span className="bs-card bs-c0 bs-skel" />
                  </div>
                ) : imgs.length > 0 ? (
                  <div className="bs-fan">
                    {imgs[1] && <span className="bs-card bs-c1"><img src={imgs[1]} alt="" loading="lazy" /></span>}
                    {imgs[2] && <span className="bs-card bs-c2"><img src={imgs[2]} alt="" loading="lazy" /></span>}
                    <span className="bs-card bs-c0"><img src={imgs[0]} alt="" loading="lazy" /></span>
                  </div>
                ) : (
                  // Photos na milein to bada price hi dikhao
                  <span className="bs-bigprice bs-serif text-5xl font-semibold">{t.price}</span>
                )}
              </div>

              {/* Text */}
              <div className="px-2 pt-5">
                <p className="text-[10px] font-bold tracking-[0.25em] uppercase" style={{ color: t.accent }}>
                  {t.kicker}
                </p>
                <div className="mt-1 flex items-end justify-between gap-3">
                  <p className="bs-serif leading-none">
                    <span className="block text-sm italic opacity-70">{t.under}</span>
                    <span className="block text-3xl sm:text-4xl lg:text-[42px] font-semibold mt-1">{t.price}</span>
                  </p>
                  <span className="bs-go" style={{ background: t.accent, color: t.dark ? "#1c231f" : "#fff" }} aria-hidden>↗</span>
                </div>
                <p className="bs-note mt-3 text-sm opacity-70">{t.note}</p>
              </div>
            </button>
          );
        })}
      </div>
    </section>
  );
};

export default BudgetStore;