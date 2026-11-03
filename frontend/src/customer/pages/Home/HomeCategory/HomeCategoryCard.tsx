import { useNavigate } from 'react-router-dom'
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';

const groupOf = (id: string) =>
  id.startsWith("women_") ? "Women" : id.startsWith("men_") ? "Men" : "Home & Living";

// Data me spelling galtiyan theek karke naam dikhao ("Topwere" -> "Topwear")
const prettyName = (name: string = "") =>
  name
    .replace(/were\b/gi, "wear")
    .replace(/\band\b/gi, "&")
    .split(" ")
    .map((w) => (w ? w[0].toUpperCase() + w.slice(1) : w))
    .join(" ");

const HomeCategoryCard = ({ item, index = 0 }: any) => {
  const navigate = useNavigate()
  const group = groupOf(item.categoryId);
  const name = prettyName(item.name || item.categoryId.split("_").join(" "));

  return (
    <div
      onClick={() => navigate(`/products/${item.categoryId}`)}
      className='group relative cursor-pointer rounded-xl overflow-hidden aspect-[3/4] bg-[#26312c]'
    >
      <img
        className='absolute inset-0 w-full h-full object-cover object-top group-hover:scale-110 transition-transform duration-[900ms] ease-out'
        src={item.image}
        alt={name}
        loading="lazy"
      />

      {/* Neeche gehra shade */}
      <div className='absolute inset-0 bg-gradient-to-t from-[#111815]/90 via-[#111815]/15 to-transparent' />

      {/* Hover pe andar sunehri frame */}
      <span className='cat-frame absolute inset-2.5 rounded-lg border border-[#c9a96e]/70 pointer-events-none' />

      {/* Number */}
      <span className='cat-serif absolute top-3 left-4 italic text-sm text-white/90 drop-shadow'>
        {String(index + 1).padStart(2, "0")}
      </span>

      {/* Arrow */}
      <span className='absolute top-3 right-3 w-8 h-8 rounded-full grid place-items-center bg-white/15 backdrop-blur text-white group-hover:bg-[#c9a96e] group-hover:text-[#1b2420] group-hover:rotate-45 transition-all duration-300'>
        <ArrowOutwardIcon sx={{ fontSize: 16 }} />
      </span>

      {/* Naam */}
      <div className='absolute left-0 right-0 bottom-0 p-4 sm:p-5'>
        <p className='text-[9px] sm:text-[10px] font-semibold tracking-[0.3em] uppercase text-[#c9a96e]'>{group}</p>
        <p className='cat-serif mt-1 text-base sm:text-xl font-semibold text-white leading-tight line-clamp-2'>{name}</p>
        <span className='mt-2 block h-px w-8 bg-[#c9a96e] group-hover:w-16 transition-all duration-500' />
      </div>
    </div>
  )
}

export default HomeCategoryCard