import { useNavigate } from "react-router-dom";
import { useAppDispatch } from "../../../../Redux Toolkit/Store";
import { searchProduct } from "../../../../Redux Toolkit/Customer/ProductSlice";
import "./BrandStrip.css";

// Brand + chhota offer (naam wahi rakho jo products me hai, taaki search chale)
const brands = [
  { name: "Nike", offer: "Min. 30% off" },
  { name: "Samsung", offer: "Up to 40% off" },
  { name: "Levi's", offer: "Flat 50% off" },
  { name: "Zara", offer: "New arrivals" },
  { name: "boAt", offer: "From ₹799" },
  { name: "Puma", offer: "Up to 60% off" },
  { name: "Apple", offer: "Bank offers" },
  { name: "Adidas", offer: "Min. 40% off" },
  { name: "H&M", offer: "Under ₹999" },
  { name: "Sony", offer: "Up to 35% off" },
  { name: "Biba", offer: "Festive edit" },
  { name: "Fossil", offer: "Extra 10% off" },
];

const BrandStrip = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const go = (q: string) => {
    dispatch(searchProduct(q));
    navigate("/search-products");
  };

  return (
    <section className="relative overflow-hidden bg-[#f6f1ea] text-[#2a1d17] py-8 lg:py-10">
      {/* Upar chhoti line */}
      <div className="px-5 lg:px-20 flex items-center justify-between gap-4 mb-5 lg:mb-6">
        <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
          <span className="w-10 h-px bg-[#b4532a]" />
          The Brand House
        </p>
        <p className="hidden sm:flex items-center gap-2 text-[11px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/55">
          <span className="brand-dot w-1.5 h-1.5 rounded-full bg-[#b4532a]" />
          100% Original · Brand Warranty
        </p>
      </div>

      {/* Chalti brand line — upar neeche patli lines */}
      <div className="brand-marquee border-y border-[#2a1d17]/15 overflow-hidden">
        <div className="brand-track flex w-max items-center py-5 lg:py-7">
          {[0, 1].map((copy) => (
            <div key={copy} className="flex shrink-0 items-center" aria-hidden={copy === 1}>
              {brands.map((b, i) => (
                <div key={b.name + copy} className="flex items-center">
                  <button
                    onClick={() => go(b.name)}
                    tabIndex={copy === 1 ? -1 : 0}
                    className="brand-item group flex items-start gap-2 px-6 lg:px-10"
                  >
                    <span
                      className={`brand-serif text-4xl lg:text-6xl font-semibold leading-none whitespace-nowrap transition-colors duration-300 group-hover:text-[#b4532a] ${i % 2 ? "italic font-medium" : ""}`}
                    >
                      {b.name}
                    </span>
                    <span className="mt-1 text-[9px] lg:text-[10px] font-semibold tracking-[0.2em] uppercase whitespace-nowrap text-[#2a1d17]/45 group-hover:text-[#b4532a] transition-colors">
                      {b.offer}
                    </span>
                  </button>
                  <span className="text-[#b4532a] text-lg lg:text-2xl">✦</span>
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default BrandStrip;