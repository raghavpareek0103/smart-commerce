import { useRef, useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import WestIcon from "@mui/icons-material/West";
import EastIcon from "@mui/icons-material/East";
import DealCard from "./DealCard";
import { useAppSelector } from "../../../../Redux Toolkit/Store";
import type { Deal } from "../../../../types/dealTypes";
import "./Deals.css";

export default function DealSlider() {
  const { homePage } = useAppSelector((store) => store);
  const sliderRef = useRef<any>(null);
  const [current, setCurrent] = useState(0);
  const deals = homePage.homePageData?.deals || [];
  const total = deals.length;

  const settings = {
    dots: false,
    arrows: false,
    infinite: true,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    speed: 700,
    autoplaySpeed: 3000,
    pauseOnHover: true,
    afterChange: (i: number) => setCurrent(i),
    responsive: [
      { breakpoint: 1280, settings: { slidesToShow: 4 } },
      { breakpoint: 1024, settings: { slidesToShow: 3 } },
      { breakpoint: 640, settings: { slidesToShow: 2 } },
    ],
  };

  const pad = (n: number) => String(n).padStart(2, "0");

  return (
    <section className="relative overflow-hidden bg-[#f6f1ea] px-5 py-14 lg:px-20 lg:py-20">
      {/* Peeche bada halka "Deals" text */}
      <span className="deal-serif deal-watermark pointer-events-none select-none absolute -right-6 -top-10 italic leading-none hidden md:block">
        Deals
      </span>

      {/* Header */}
      <div className="relative flex flex-col sm:flex-row sm:items-end justify-between gap-6 mb-10 lg:mb-12">
        <div>
          <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
            <span className="w-10 h-px bg-[#b4532a]" />
            The Deal Edit
          </p>
          <h2 className="deal-serif mt-3 text-4xl lg:text-6xl font-semibold text-[#2a1d17] leading-[1]">
            Today's <span className="italic font-medium text-[#b4532a]">Best</span> Deals
          </h2>
          <p className="mt-3 text-sm lg:text-base text-[#2a1d17]/65 max-w-md">
            Handpicked offers, refreshed every day — grab them before they're gone.
          </p>
        </div>

        {/* Counter + arrows */}
        <div className="flex items-center gap-5 shrink-0">
          {total > 0 && (
            <p className="deal-serif text-[#2a1d17] tabular-nums">
              <span className="text-3xl font-semibold">{pad((current % total) + 1)}</span>
              <span className="text-[#2a1d17]/40 text-lg"> / {pad(total)}</span>
            </p>
          )}
          <div className="flex gap-2">
            <button
              onClick={() => sliderRef.current?.slickPrev()}
              aria-label="Previous deals"
              className="w-12 h-12 rounded-full grid place-items-center border border-[#2a1d17]/25 text-[#2a1d17] hover:bg-[#2a1d17] hover:text-[#f6f1ea] transition-colors"
            >
              <WestIcon sx={{ fontSize: 18 }} />
            </button>
            <button
              onClick={() => sliderRef.current?.slickNext()}
              aria-label="Next deals"
              className="w-12 h-12 rounded-full grid place-items-center bg-[#2a1d17] text-[#f6f1ea] hover:bg-[#b4532a] transition-colors"
            >
              <EastIcon sx={{ fontSize: 18 }} />
            </button>
          </div>
        </div>
      </div>

      {/* Cards */}
      <div className="relative deals-slider -mx-3">
        <Slider ref={sliderRef} {...settings}>
          {deals.map((item: Deal, i: number) => (
            <div key={i} className="px-3 pt-3">
              <DealCard deal={item} index={i} />
            </div>
          ))}
        </Slider>
      </div>

      {/* Neeche progress line */}
      {total > 0 && (
        <div className="relative mt-10 h-px bg-[#2a1d17]/15">
          <span
            className="absolute left-0 top-0 h-[2px] -translate-y-[0.5px] bg-[#b4532a] transition-all duration-700"
            style={{ width: `${(((current % total) + 1) / total) * 100}%` }}
          />
        </div>
      )}
    </section>
  );
}