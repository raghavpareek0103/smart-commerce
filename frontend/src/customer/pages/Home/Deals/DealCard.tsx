import type { Deal } from '../../../../types/dealTypes'
import { useNavigate } from 'react-router-dom'
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';

const DealCard = ({ deal, index }: { deal: Deal; index: number }) => {
  const navigate = useNavigate();
  const name = deal.category.name || deal.category.categoryId.split("_").join(" ");

  return (
    <div
      onClick={() => navigate(`/products/${deal.category.categoryId}`)}
      className='group cursor-pointer'
    >
      {/* Arch shape photo — hero banner jaisi */}
      <div className='relative'>
        <div className='deal-arch relative aspect-[3/4] rounded-t-full overflow-hidden bg-white border border-[#2a1d17]/10 group-hover:-translate-y-1.5 group-hover:shadow-[0_24px_40px_-24px_rgba(42,29,23,0.45)] transition-all duration-500'>
          <img
            className='absolute inset-0 w-full h-full object-cover object-top group-hover:scale-110 transition-transform duration-700'
            src={deal.category.image}
            alt={name}
            loading="lazy"
          />
        </div>

        {/* Gol discount seal */}
        <div className='deal-seal absolute -top-1 -right-1 w-[68px] h-[68px] rounded-full bg-[#2a1d17] text-[#f3ebe3] grid place-items-center text-center shadow-lg'>
          <span className='leading-none'>
            <span className='deal-serif block text-xl font-semibold'>{deal.discount}%</span>
            <span className='block text-[9px] font-semibold tracking-[0.25em] uppercase text-[#e7b48f] mt-0.5'>off</span>
          </span>
        </div>
      </div>

      {/* Naam + link */}
      <div className='pt-4 px-1'>
        <p className='text-[10px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]'>No. {String(index + 1).padStart(2, "0")}</p>
        <p className='deal-serif mt-1 text-lg lg:text-xl font-semibold text-[#2a1d17] capitalize leading-tight line-clamp-1'>{name}</p>
        <span className='mt-2 inline-flex items-center gap-2 text-sm font-semibold text-[#2a1d17]'>
          <span className='relative'>
            Shop the Edit
            <span className='absolute left-0 -bottom-0.5 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#2a1d17] transition-transform duration-300' />
          </span>
          <span className='w-7 h-7 rounded-full grid place-items-center bg-[#b4532a] text-white group-hover:rotate-45 transition-transform duration-300'>
            <ArrowOutwardIcon sx={{ fontSize: 14 }} />
          </span>
        </span>
      </div>
    </div>
  )
}

export default DealCard