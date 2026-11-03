import { useNavigate } from 'react-router-dom'
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';

const ElectronicCategoryCard = ({ item, index = 0 }: any) => {
  const navigate = useNavigate();

  return (
    <button
      onClick={() => navigate(`/products/${item.categoryId}`)}
      className='group flex flex-col justify-start snap-start shrink-0 w-[140px] lg:w-auto text-left'
    >
      {/* Arch shape — hero banner aur deals jaisi */}
      <div className='elec-arch relative w-full h-40 lg:h-44 rounded-t-full bg-gradient-to-b from-[#f8f3ec] to-[#ece2d5] grid place-items-center overflow-hidden group-hover:-translate-y-1.5 transition-all duration-500'>
        {/* andar patli terracotta arch line */}
        <span className='absolute inset-2 rounded-t-full border border-[#b4532a]/0 group-hover:border-[#b4532a]/40 transition-colors duration-500' />
        <span className='absolute bottom-5 w-[55%] h-3 rounded-full bg-[#2a1d17]/15 blur-md' />
        <img
          className='relative mt-4 h-[76px] lg:h-[84px] w-auto max-w-[72%] object-contain mix-blend-multiply group-hover:scale-110 group-hover:-translate-y-1 transition-transform duration-500'
          src={item.image}
          alt={item.name}
        />
      </div>

      {/* Naam */}
      <div className='w-full pt-3 px-0.5'>
        <p className='elec-serif italic text-xs text-[#e7b48f]'>{String(index + 1).padStart(2, "0")}</p>
        <div className='flex items-center justify-between gap-2 mt-0.5'>
          <p className='elec-serif text-[15px] lg:text-base font-semibold text-[#f3ebe3] leading-tight line-clamp-2'>{item.name}</p>
          <span className='shrink-0 w-6 h-6 rounded-full grid place-items-center text-[#e7b48f] border border-[#e7b48f]/40 group-hover:bg-[#b4532a] group-hover:border-[#b4532a] group-hover:text-white group-hover:rotate-45 transition-all duration-300'>
            <ArrowOutwardIcon sx={{ fontSize: 13 }} />
          </span>
        </div>
        <span className='mt-2 block h-px w-6 bg-[#e7b48f]/60 group-hover:w-full transition-all duration-500' />
      </div>
    </button>
  )
}

export default ElectronicCategoryCard