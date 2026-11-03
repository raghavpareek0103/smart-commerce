import type { Address } from '../../../types/userTypes'
import PlaceOutlinedIcon from '@mui/icons-material/PlaceOutlined';
import PhoneIphoneIcon from '@mui/icons-material/PhoneIphone';

const UserAddressCard = ({ item, index = 0 }: { item: Address; index?: number }) => {
  return (
    <div className='group relative rounded-[24px] bg-[#f6f1ea] border border-[#2a1d17]/[0.08] hover:border-[#b4532a]/40 p-6 transition-all hover:shadow-[0_20px_40px_-25px_rgba(42,29,23,0.45)] overflow-hidden'>
      <span className='acc-serif absolute right-4 top-2 text-6xl italic text-transparent pointer-events-none' style={{ WebkitTextStroke: "1px rgba(42,29,23,0.1)" }}>
        {String(index + 1).padStart(2, "0")}
      </span>

      <div className='flex items-center gap-3'>
        <span className='w-10 h-10 rounded-full grid place-items-center bg-[#1b2420] text-[#c9a96e]'>
          <PlaceOutlinedIcon sx={{ fontSize: 19 }} />
        </span>
        <span className='px-3 py-1 rounded-full bg-[#b4532a]/10 text-[#b4532a] text-[10px] font-bold tracking-[0.2em] uppercase'>
          {index === 0 ? "Default" : "Address"}
        </span>
      </div>

      <h3 className='acc-serif mt-5 text-xl font-semibold'>{item.name}</h3>
      <p className='mt-2 text-sm leading-relaxed text-[#2a1d17]/65'>
        {item.address}{item.locality ? `, ${item.locality}` : ""}, {item.city}, {item.state} — <b className='text-[#2a1d17]'>{item.pinCode}</b>
      </p>
      <p className='mt-4 pt-4 border-t border-[#2a1d17]/10 flex items-center gap-2 text-sm text-[#2a1d17]/65'>
        <PhoneIphoneIcon sx={{ fontSize: 16, color: "#b4532a" }} /> {item.mobile}
      </p>
    </div>
  )
}

export default UserAddressCard