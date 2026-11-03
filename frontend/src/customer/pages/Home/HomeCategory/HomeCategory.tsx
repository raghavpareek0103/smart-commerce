import { useState } from 'react'
import HomeCategoryCard from './HomeCategoryCard'
import { useAppSelector } from '../../../../Redux Toolkit/Store';
import "./HomeCategoryCard.css"

// categoryId ke shuru se group pata chalta hai
const groupOf = (categoryId: string) =>
  categoryId.startsWith("women_") ? "Women" : categoryId.startsWith("men_") ? "Men" : "Home & Living";

const tabs = ["All", "Women", "Men", "Home & Living"];

const HomeCategory = () => {
  const { homePage } = useAppSelector((store) => store);
  const [active, setActive] = useState("All");

  const all = homePage.homePageData?.shopByCategories || [];
  const items = active === "All" ? all : all.filter((c) => groupOf(c.categoryId) === active);

  return (
    <section className='relative overflow-hidden bg-[#1b2420] text-[#f4efe6] px-5 lg:px-20 py-16 lg:py-24'>
      {/* halki sunehri roshni */}
      <div className='absolute -top-32 left-1/3 w-[500px] h-[500px] rounded-full bg-[#c9a96e]/10 blur-3xl pointer-events-none' />
      {/* Peeche bada outline text */}
      <span className='cat-serif cat-watermark pointer-events-none select-none absolute -left-4 bottom-0 italic leading-none hidden md:block'>
        Categories
      </span>

      <div className='relative'>
        {/* Header + tabs */}
        <div className='flex flex-col lg:flex-row lg:items-end lg:justify-between gap-8 mb-10 lg:mb-14'>
          <div>
            <p className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#c9a96e]'>
              <span className='w-10 h-px bg-[#c9a96e]' />
              The Directory
            </p>
            <h2 className='cat-serif mt-3 text-4xl lg:text-6xl font-semibold leading-[1]'>
              Shop by <span className='italic font-medium text-[#c9a96e]'>Category</span>
            </h2>
            <p className='mt-3 text-sm lg:text-base text-[#f4efe6]/60 max-w-md'>
              Find exactly what you're looking for — {all.length} curated collections.
            </p>
          </div>

          {/* Text tabs, neeche sunehri line */}
          <div className='flex gap-7 overflow-x-auto [scrollbar-width:none] [&::-webkit-scrollbar]:hidden -mx-5 px-5 lg:mx-0 lg:px-0 border-b border-white/10'>
            {tabs.map((t) => {
              const count = t === "All" ? all.length : all.filter((c) => groupOf(c.categoryId) === t).length;
              const isActive = active === t;
              return (
                <button
                  key={t}
                  onClick={() => setActive(t)}
                  className={`relative shrink-0 pb-3 text-sm font-semibold tracking-wide transition-colors ${isActive ? "text-[#f4efe6]" : "text-[#f4efe6]/45 hover:text-[#f4efe6]/80"}`}
                >
                  {t}
                  <sup className={`cat-serif italic ml-1 text-[11px] ${isActive ? "text-[#c9a96e]" : ""}`}>{String(count).padStart(2, "0")}</sup>
                  <span className={`absolute left-0 -bottom-px h-[2px] bg-[#c9a96e] transition-all duration-300 ${isActive ? "w-full" : "w-0"}`} />
                </button>
              )
            })}
          </div>
        </div>

        {/* Cards */}
        <div key={active} className='grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-5 gap-3 sm:gap-5'>
          {items.map((item, i) => (
            <div key={item.categoryId} className='cat-in' style={{ animationDelay: `${i * 40}ms` }}>
              <HomeCategoryCard item={item} index={i} />
            </div>
          ))}
        </div>
      </div>
    </section>
  )
}

export default HomeCategory