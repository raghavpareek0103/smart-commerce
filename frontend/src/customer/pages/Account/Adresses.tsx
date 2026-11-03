import { useNavigate } from 'react-router-dom'
import { useAppSelector } from '../../../Redux Toolkit/Store'
import UserAddressCard from './UserAddressCard'
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';

const Addresses = () => {
    const { user } = useAppSelector(store => store)
    const navigate = useNavigate()
    const list = user.user?.addresses || []

    return (
        <div className='acc-rise'>
            <div className='pb-8 border-b border-[#2a1d17]/10'>
                <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
                    <span className='w-10 h-px bg-[#b4532a]' /> Address Book
                </div>
                <h2 className='acc-serif mt-3 text-4xl lg:text-5xl font-semibold'>Saved <span className='italic font-medium text-[#b4532a]'>Addresses</span></h2>
                <p className='mt-2 text-sm text-[#2a1d17]/55'>{list.length ? `${list.length} address${list.length > 1 ? "es" : ""} saved for faster checkout.` : "Addresses you use at checkout are saved here."}</p>
            </div>

            {list.length === 0 ? (
                <div className='py-20 text-center'>
                    <div className='mx-auto w-20 h-20 rounded-t-full rounded-b-2xl grid place-items-center bg-[#f3ebe3] text-[#b4532a]'>
                        <HomeOutlinedIcon sx={{ fontSize: 34 }} />
                    </div>
                    <p className='acc-serif mt-6 text-3xl'>No addresses <span className='italic text-[#b4532a]'>yet.</span></p>
                    <p className='mt-2 text-sm text-[#2a1d17]/55'>Add one during checkout and it will appear here.</p>
                    <button onClick={() => navigate("/cart")} className='mt-6 text-sm font-semibold text-[#b4532a] hover:underline underline-offset-4'>Go to your bag →</button>
                </div>
            ) : (
                <div className='pt-8 grid grid-cols-1 md:grid-cols-2 gap-5'>
                    {list.map((item, index) => (
                        <UserAddressCard key={item.id || index} item={item} index={index} />
                    ))}
                </div>
            )}
        </div>
    )
}

export default Addresses