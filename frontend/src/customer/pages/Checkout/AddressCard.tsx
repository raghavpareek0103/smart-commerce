import React from 'react'
import type { Address } from '../../../types/userTypes';
import PlaceOutlinedIcon from '@mui/icons-material/PlaceOutlined';
import PhoneIphoneIcon from '@mui/icons-material/PhoneIphone';
import CheckIcon from '@mui/icons-material/Check';

interface AddressCardProps {
    value: number;
    selectedValue: number;
    handleChange: (e: any) => void;
    item: Address
}

const AddressCard: React.FC<AddressCardProps> = ({ value, selectedValue, handleChange, item }) => {
    const selected = value == selectedValue;

    return (
        <label
            className={`group relative flex gap-4 p-5 rounded-[24px] border cursor-pointer transition-all ${selected
                ? "bg-[#fbf8f4] border-[#2a1d17] shadow-[0_20px_40px_-25px_rgba(42,29,23,0.5)]"
                : "bg-[#fbf8f4]/60 border-[#2a1d17]/10 hover:border-[#b4532a]/40"}`}
        >
            {/* asli radio (chhupa hua) — purana handleChange waise hi chalega */}
            <input
                type="radio"
                name="delivery-address"
                value={value}
                checked={selected}
                onChange={handleChange}
                className="sr-only"
            />

            {/* gol check */}
            <span className={`mt-0.5 w-6 h-6 shrink-0 rounded-full grid place-items-center border transition-colors ${selected
                ? "bg-[#2a1d17] border-[#2a1d17] text-[#c9a96e]"
                : "border-[#2a1d17]/25 text-transparent"}`}>
                <CheckIcon sx={{ fontSize: 15 }} />
            </span>

            <div className='flex-1 min-w-0'>
                <div className='flex flex-wrap items-center gap-2'>
                    <h3 className='co-serif text-lg font-semibold'>{item.name}</h3>
                    {value === 0 && (
                        <span className='px-2.5 py-0.5 rounded-full bg-[#b4532a]/10 text-[#b4532a] text-[10px] font-bold tracking-[0.18em] uppercase'>Default</span>
                    )}
                </div>
                <p className='mt-2 text-sm leading-relaxed text-[#2a1d17]/65 flex gap-2'>
                    <PlaceOutlinedIcon sx={{ fontSize: 17, color: "#b4532a", mt: "2px" }} />
                    <span>
                        {item.address}{item.locality ? `, ${item.locality}` : ""}, {item.city}, {item.state} — <b className='text-[#2a1d17]'>{item.pinCode}</b>
                    </span>
                </p>
                <p className='mt-2 text-sm text-[#2a1d17]/65 flex items-center gap-2'>
                    <PhoneIphoneIcon sx={{ fontSize: 16, color: "#b4532a" }} /> {item.mobile}
                </p>
            </div>

            {selected && (
                <span className='hidden sm:block self-start text-[10px] font-bold tracking-[0.2em] uppercase text-[#5b7a5a]'>Delivering here</span>
            )}
        </label>
    )
}

export default AddressCard