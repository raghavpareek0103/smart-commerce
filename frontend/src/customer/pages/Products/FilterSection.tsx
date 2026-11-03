import { FormControlLabel, Radio, RadioGroup } from "@mui/material";
import { useState } from "react";
import { colors } from "../../../data/Filter/color";
import { price } from "../../../data/Filter/price";
import { discount } from "../../../data/Filter/discount";
import { useSearchParams } from "react-router-dom";

const FilterSection = () => {
  const [expendColor, setExpendColor] = useState(false);
  const [searchParams, setSearchParams] = useSearchParams();

  const updateFilterParams = (e: any) => {
    const { value, name } = e.target;
    if (value) searchParams.set(name, value);
    else searchParams.delete(name);
    setSearchParams(searchParams);
  };

  const clearAllFilters = () => {
    [...searchParams.keys()].forEach((key) => searchParams.delete(key));
    setSearchParams(searchParams);
  };

  const Heading = ({ n, title }: { n: string; title: string }) => (
    <p className="flex items-baseline gap-2 mb-3">
      <span className="pl-serif italic text-xs text-[#b4532a]">{n}</span>
      <span className="pl-serif text-lg font-semibold text-[#2a1d17]">{title}</span>
    </p>
  );

  return (
    <div className="pl-filter bg-[#fbf8f4] text-[#2a1d17]">
      {/* Header */}
      <div className="flex items-end justify-between px-7 pt-6 pb-5 border-b border-[#2a1d17]/10">
        <div>
          <p className="text-[10px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">Refine</p>
          <p className="pl-serif text-2xl font-semibold leading-none mt-1">Filters</p>
        </div>
        <button onClick={clearAllFilters} className="group relative text-xs font-semibold tracking-wide text-[#2a1d17]/70 hover:text-[#b4532a] transition-colors">
          Clear all
          <span className="absolute left-0 -bottom-0.5 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#b4532a] transition-transform duration-300" />
        </button>
      </div>

      <div className="px-7 py-6 space-y-7">
        {/* Color */}
        <section>
          <Heading n="01" title="Colour" />
          <RadioGroup onChange={updateFilterParams} value={searchParams.get("color") || ""} name="color">
            {colors.slice(0, expendColor ? colors.length : 6).map((item) => (
              <FormControlLabel
                key={item.name}
                value={item.name}
                control={<Radio size="small" />}
                label={
                  <span className="flex items-center gap-2.5 text-sm">
                    <span style={{ backgroundColor: item.hex }} className="w-4 h-4 rounded-full border border-[#2a1d17]/15 shadow-inner" />
                    {item.name}
                  </span>
                }
              />
            ))}
          </RadioGroup>
          <button
            onClick={() => setExpendColor(!expendColor)}
            className="mt-1 text-xs font-semibold text-[#b4532a] hover:underline"
          >
            {expendColor ? "Show less" : `+ ${colors.length - 6} more`}
          </button>
        </section>

        <div className="h-px bg-[#2a1d17]/10" />

        {/* Price */}
        <section>
          <Heading n="02" title="Price" />
          <RadioGroup name="price" onChange={updateFilterParams} value={searchParams.get("price") || ""}>
            {price.map((item) => (
              <FormControlLabel key={item.name} value={item.value} control={<Radio size="small" />} label={<span className="text-sm">{item.name}</span>} />
            ))}
          </RadioGroup>
        </section>

        <div className="h-px bg-[#2a1d17]/10" />

        {/* Discount */}
        <section>
          <Heading n="03" title="Discount" />
          <RadioGroup name="discount" onChange={updateFilterParams} value={searchParams.get("discount") || ""}>
            {discount.map((item) => (
              <FormControlLabel key={item.name} value={item.value} control={<Radio size="small" />} label={<span className="text-sm">{item.name}</span>} />
            ))}
          </RadioGroup>
        </section>
      </div>
    </div>
  );
};

export default FilterSection;