/* eslint-disable @typescript-eslint/no-explicit-any */
import React, { useEffect, useState } from "react";
import ProductCard from "./ProductCard/ProductCard";
import FilterSection from "./FilterSection";
import { Drawer, MenuItem, Pagination, Select, type SelectChangeEvent } from "@mui/material";
import TuneIcon from "@mui/icons-material/Tune";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";
import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../../Redux Toolkit/Store";
import { getAllProducts } from "../../../Redux Toolkit/Customer/ProductSlice";
import "./Products.css";

// "men_plus_size" -> ["Men", "Plus", "Size"]
const words = (id: string = "") =>
  id.split("_").filter(Boolean).map((w) => w[0].toUpperCase() + w.slice(1));

const Products = () => {
  const [sort, setSort] = React.useState("");
  const [showFilter, setShowFilter] = useState(false);
  const { categoryId } = useParams();
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const { products } = useAppSelector((store) => store);
  const [searchParams] = useSearchParams();
  const [page, setPage] = useState(1);

  const handleSortProduct = (event: SelectChangeEvent) => {
    setSort(event.target.value as string);
  };

  useEffect(() => {
    const [minPrice, maxPrice] = searchParams.get("price")?.split("-") || [];
    const newFilters = {
      brand: searchParams.get("brand") || "",
      color: searchParams.get("color") || "",
      minPrice: minPrice ? Number(minPrice) : undefined,
      maxPrice: maxPrice ? Number(maxPrice) : undefined,
      pageNumber: page - 1,
      minDiscount: searchParams.get("discount")
        ? Number(searchParams.get("discount"))
        : undefined,
    };

    dispatch(getAllProducts({ category: categoryId, sort, ...newFilters }));
  }, [searchParams, categoryId, sort, page]);

  const title = words(categoryId);
  const first = title.slice(0, -1).join(" ");
  const last = title[title.length - 1] || "";
  const list = products.products || [];

  return (
    <div className="relative bg-[#f6f1ea] text-[#2a1d17] min-h-screen">
      {/* ---------- Heading band ---------- */}
      <div className="relative overflow-hidden border-b border-[#2a1d17]/10 px-5 lg:px-20 pt-10 pb-8 lg:pt-14 lg:pb-10">
        <span className="pl-serif pl-watermark pointer-events-none select-none absolute right-0 -bottom-10 italic leading-none hidden md:block">
          {last}
        </span>
        <p className="relative flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
          <span className="w-10 h-px bg-[#b4532a]" />
          The Collection
        </p>
        <h1 className="relative pl-serif mt-3 text-4xl lg:text-6xl font-semibold leading-none">
          {first} <span className="italic font-medium text-[#b4532a]">{last}</span>
        </h1>
        <p className="relative mt-3 text-sm text-[#2a1d17]/60">
          {list.length > 0 ? `${list.length} pieces on this page` : "Curated just for you"}
        </p>
      </div>

      <div className="lg:flex">
        {/* ---------- Filters (desktop) ---------- */}
        <aside className="hidden lg:block w-[280px] xl:w-[300px] shrink-0 border-r border-[#2a1d17]/10">
          <div className="sticky top-28 max-h-[calc(100vh-7rem)] overflow-y-auto pl-scroll">
            <FilterSection />
          </div>
        </aside>

        {/* ---------- Products ---------- */}
        <div className="flex-1 min-w-0">
          {/* Toolbar */}
          <div className="flex items-center justify-between gap-4 px-5 lg:px-10 py-5 border-b border-[#2a1d17]/10">
            <button
              onClick={() => setShowFilter(true)}
              className="lg:hidden flex items-center gap-2 h-10 px-4 rounded-full border border-[#2a1d17]/20 text-sm font-semibold"
            >
              <TuneIcon sx={{ fontSize: 18 }} /> Filters
            </button>
            <p className="hidden lg:block text-[11px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/50">
              Showing <span className="pl-serif italic normal-case tracking-normal text-sm text-[#2a1d17]">{list.length}</span> styles
            </p>

            <div className="flex items-center gap-3">
              <span className="hidden sm:block text-[11px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/50">Sort by</span>
              <Select
                size="small"
                value={sort}
                displayEmpty
                onChange={handleSortProduct}
                className="pl-sort"
                sx={{ minWidth: 190, borderRadius: "999px", backgroundColor: "#fbf8f4", fontSize: 14 }}
              >
                <MenuItem value="">Recommended</MenuItem>
                <MenuItem value={"price_low"}>Price : Low - High</MenuItem>
                <MenuItem value={"price_high"}>Price : High - Low</MenuItem>
              </Select>
            </div>
          </div>

          {list.length > 0 ? (
            <section className="grid grid-cols-2 md:grid-cols-3 xl:grid-cols-4 gap-x-4 sm:gap-x-6 gap-y-10 px-5 lg:px-10 py-8">
              {list.map((item: any, index: number) => (
                <div key={item.id ?? index} className="pl-in" style={{ animationDelay: `${(index % 8) * 50}ms` }}>
                  <ProductCard item={item} />
                </div>
              ))}
            </section>
          ) : (
            /* ---------- Khaali ---------- */
            <section className="flex flex-col items-center justify-center text-center px-5 py-20 min-h-[60vh]">
              <div className="relative w-36 h-44 rounded-t-full bg-[#fbf8f4] border border-[#2a1d17]/10 grid place-items-center shadow-[0_30px_60px_-30px_rgba(42,29,23,0.45)]">
                <span className="absolute inset-2.5 rounded-t-full border border-[#c9a96e]/50" />
                <span className="pl-serif italic text-5xl text-[#b4532a]">?</span>
              </div>
              <p className="mt-8 flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
                <span className="w-10 h-px bg-[#b4532a]" />
                {title.join(" ")}
                <span className="w-10 h-px bg-[#b4532a]" />
              </p>
              <h2 className="pl-serif mt-3 text-3xl lg:text-4xl font-semibold">
                Nothing here <span className="italic font-medium text-[#b4532a]">yet.</span>
              </h2>
              <p className="mt-3 max-w-sm text-sm text-[#2a1d17]/60">
                We couldn't find products for this selection. Try clearing a filter or explore other collections.
              </p>
              <button
                onClick={() => navigate("/")}
                className="pl-cta group mt-8 flex items-center gap-3 h-12 pl-6 pr-2 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-sm font-semibold"
              >
                Explore the Edit
                <span className="w-8 h-8 rounded-full grid place-items-center bg-[#b4532a] text-white group-hover:rotate-45 transition-transform duration-300">
                  <ArrowOutwardIcon sx={{ fontSize: 17 }} />
                </span>
              </button>
            </section>
          )}

          {(products?.totalPages || 0) > 1 && (
            <div className="flex justify-center pb-14 pt-4">
              <Pagination
                page={page}
                onChange={(_e, value) => setPage(value)}
                count={products?.totalPages}
                className="pl-pagination"
              />
            </div>
          )}
        </div>
      </div>

      {/* Mobile filter drawer */}
      <Drawer
        anchor="left"
        open={showFilter}
        onClose={() => setShowFilter(false)}
        PaperProps={{ sx: { width: 300, backgroundColor: "#fbf8f4", borderRadius: 0 } }}
      >
        <FilterSection />
      </Drawer>
    </div>
  );
};

export default Products;