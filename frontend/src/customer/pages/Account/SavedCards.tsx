import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import BoltIcon from '@mui/icons-material/Bolt';
import VerifiedUserOutlinedIcon from '@mui/icons-material/VerifiedUserOutlined';
import { useAppSelector } from '../../../Redux Toolkit/Store';

const points = [
  { icon: <LockOutlinedIcon sx={{ fontSize: 18 }} />, title: "128-bit encryption", text: "Your card details stay protected." },
  { icon: <BoltIcon sx={{ fontSize: 18 }} />, title: "One-tap checkout", text: "Pay faster on your next order." },
  { icon: <VerifiedUserOutlinedIcon sx={{ fontSize: 18 }} />, title: "Never shared", text: "We don't share card data with sellers." },
];

const SavedCards = () => {
  const { user } = useAppSelector(store => store);

  return (
    <div className='acc-rise'>
      <div className='pb-8 border-b border-[#2a1d17]/10'>
        <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
          <span className='w-10 h-px bg-[#b4532a]' /> Payments
        </div>
        <h2 className='acc-serif mt-3 text-4xl lg:text-5xl font-semibold'>Saved <span className='italic font-medium text-[#b4532a]'>Cards</span></h2>
        <p className='mt-2 text-sm text-[#2a1d17]/55'>Save your credit or debit card during payment — it will show up here.</p>
      </div>

      <div className='pt-10 grid grid-cols-1 xl:grid-cols-2 gap-10 items-center'>
        {/* decorative card */}
        <div className='relative mx-auto w-full max-w-[380px]'>
          <div className='absolute inset-0 translate-x-4 translate-y-4 rounded-[24px] border border-[#2a1d17]/15' />
          <div className='relative aspect-[1.6/1] rounded-[24px] p-6 text-[#f4efe6] overflow-hidden shadow-[0_30px_60px_-25px_rgba(27,36,32,0.7)]' style={{ background: "linear-gradient(135deg,#1b2420 0%,#26312c 55%,#2a1d17 100%)" }}>
            <span className='acc-serif absolute -right-6 -bottom-14 text-[170px] italic leading-none text-transparent' style={{ WebkitTextStroke: "1px rgba(201,169,110,0.18)" }}>zy</span>
            <div className='relative flex items-start justify-between'>
              <span className='acc-serif text-xl'>Shop<span className='italic text-[#c9a96e]'>zy</span></span>
              <span className='text-[10px] font-semibold tracking-[0.25em] uppercase text-[#c9a96e]'>Member</span>
            </div>
            <div className='relative mt-6 w-11 h-8 rounded-md' style={{ background: "linear-gradient(135deg,#e2c891,#c9a96e 50%,#a88a52)" }} />
            <p className='relative mt-5 tracking-[0.3em] text-lg font-semibold'>•••• •••• •••• ••••</p>
            <div className='relative mt-3 flex justify-between text-[10px] tracking-[0.2em] uppercase text-[#f4efe6]/60'>
              <span>{user.user?.fullName || "Card holder"}</span>
              <span>MM / YY</span>
            </div>
          </div>
        </div>

        {/* points */}
        <div className='space-y-4'>
          <p className='acc-serif text-2xl font-semibold'>No cards <span className='italic text-[#b4532a]'>saved yet.</span></p>
          {points.map(p => (
            <div key={p.title} className='flex items-center gap-4 p-4 rounded-2xl bg-[#f6f1ea] border border-[#2a1d17]/[0.07]'>
              <span className='w-10 h-10 shrink-0 rounded-full grid place-items-center bg-[#1b2420] text-[#c9a96e]'>{p.icon}</span>
              <div>
                <p className='text-sm font-semibold'>{p.title}</p>
                <p className='text-xs text-[#2a1d17]/55'>{p.text}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default SavedCards