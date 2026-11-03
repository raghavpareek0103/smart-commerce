import { useNavigate } from "react-router-dom";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import ElectronicCategoryCard from "./ElectronicCategoryCard";
import { useAppSelector } from "../../../../Redux Toolkit/Store";
import "./ElectronicCategory.css";

const ElectronicCategory = () => {
  const { homePage } = useAppSelector((store) => store);
  const navigate = useNavigate();
  const items = homePage.homePageData?.electricCategories || [];

  return (
    <section className="relative overflow-hidden bg-[#2a1d17] text-[#f3ebe3] px-5 lg:px-20 pt-10 pb-10 lg:pt-12 lg:pb-12">
      {/* halki terracotta roshni + lines */}
      <div className="absolute -top-32 right-[15%] w-[460px] h-[460px] rounded-full bg-[#b4532a]/20 blur-3xl pointer-events-none" />
      <div className="elec-lines absolute inset-0 pointer-events-none" />
      {/* Peeche bada outline text */}
      <span className="elec-serif elec-watermark pointer-events-none select-none absolute right-6 -bottom-8 italic leading-none hidden lg:block">
        Tech
      </span>

      <div className="relative">
        {/* Header */}
        <div className="flex items-end justify-between gap-4 mb-8 lg:mb-10">
          <div>
            <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#e7b48f]">
              <span className="w-10 h-px bg-[#e7b48f]" />
              The Tech Edit
            </p>
            <h2 className="elec-serif mt-2 text-3xl lg:text-5xl font-semibold leading-none">
              Top in <span className="italic font-medium text-[#e7b48f]">Electronics</span>
            </h2>
          </div>
          <button
            onClick={() => navigate("/products/mobiles")}
            className="group shrink-0 flex items-center gap-2.5 text-sm font-semibold"
          >
            <span className="relative hidden sm:inline">
              View all
              <span className="absolute left-0 -bottom-1 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#f3ebe3] transition-transform duration-300" />
            </span>
            <span className="w-10 h-10 rounded-full grid place-items-center bg-[#b4532a] text-white group-hover:bg-[#f3ebe3] group-hover:text-[#2a1d17] group-hover:rotate-45 transition-all duration-300">
              <ArrowOutwardIcon sx={{ fontSize: 18 }} />
            </span>
          </button>
        </div>

        {/* Items — desktop pe barabar grid, mobile pe swipe */}
        <div className="flex lg:grid lg:grid-cols-7 gap-4 lg:gap-6 overflow-x-auto snap-x snap-mandatory -mx-5 px-5 lg:mx-0 lg:px-0 pt-2 pb-2 [scrollbar-width:none] [&::-webkit-scrollbar]:hidden">
          {items.map((item, index) => (
            <ElectronicCategoryCard key={item.categoryId + index} item={item} index={index} />
          ))}
        </div>
      </div>
    </section>
  );
};

export default ElectronicCategory;