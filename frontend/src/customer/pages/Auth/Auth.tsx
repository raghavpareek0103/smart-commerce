import { useEffect, useState } from 'react'
import { Alert, Snackbar } from '@mui/material';
import LoginForm from './LoginForm'
import SignupForm from './SignupForm';
import { useAppSelector } from '../../../Redux Toolkit/Store';
import "./Auth.css";

const perks = [
    "Exclusive member-only offers",
    "Track your orders in one place",
    "Save favourites to your wishlist",
    "Faster checkout with saved addresses",
]

const Auth = () => {
    const [isLoginPage, setIsLoginPage] = useState(true);
    const { auth } = useAppSelector(store => store)
    const [snackbarOpen, setSnackbarOpen] = useState(false);

    useEffect(() => {
        if (auth.otpSent || auth.error) setSnackbarOpen(true);
    }, [auth.otpSent, auth.error])

    return (
        <section className='relative overflow-hidden bg-[#f6f1ea] px-5 py-12 lg:py-20'>
            {/* Peeche bada outline text */}
            <span className='auth-serif auth-watermark pointer-events-none select-none absolute -left-6 bottom-0 italic leading-none hidden lg:block'>
                Members
            </span>

            <div className='relative max-w-5xl mx-auto grid md:grid-cols-2 md:min-h-[640px] rounded-3xl overflow-hidden bg-white border border-[#2a1d17]/10 shadow-[0_40px_80px_-40px_rgba(42,29,23,0.45)]'>
                {/* ---------- Left: dark forest panel ---------- */}
                <div className='relative hidden md:flex flex-col justify-between gap-10 overflow-hidden bg-[#1b2420] text-[#f4efe6] p-10 lg:p-12'>
                    <div className='absolute -top-24 -right-24 w-72 h-72 rounded-full bg-[#c9a96e]/15 blur-3xl' />
                    <div className='auth-lines absolute inset-0 pointer-events-none' />

                    {/* Logo */}
                    <div className='relative flex items-center gap-2'>
                        <span className='auth-serif w-9 h-9 rounded-xl grid place-items-center text-[#c9a96e] font-semibold italic text-xl bg-[#26312c] border border-[#c9a96e]/45'>S</span>
                        <span className='auth-serif text-[28px] font-semibold leading-none'>Shop<span className='italic font-medium text-[#c9a96e]'>zy</span></span>
                    </div>

                    <div key={String(isLoginPage)} className='relative auth-rise'>
                        <p className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#c9a96e]'>
                            <span className='w-10 h-px bg-[#c9a96e]' />
                            {isLoginPage ? "Members' Club" : "Join the Club"}
                        </p>
                        <h2 className='auth-serif mt-4 text-4xl lg:text-5xl font-semibold leading-[1.02]'>
                            {isLoginPage
                                ? <>Welcome <span className='italic font-medium text-[#c9a96e]'>back.</span></>
                                : <>Create your <span className='italic font-medium text-[#c9a96e]'>account.</span></>}
                        </h2>
                        <p className='mt-3 text-sm text-[#f4efe6]/60 max-w-xs'>Everything you need for a better shopping experience.</p>

                        <ul className='mt-8 border-t border-white/10'>
                            {perks.map((p, i) => (
                                <li key={p} className='flex items-baseline gap-4 py-3 border-b border-white/10 text-sm text-[#f4efe6]/85'>
                                    <span className='auth-serif italic text-[#c9a96e] text-sm w-5'>{String(i + 1).padStart(2, "0")}</span>
                                    {p}
                                </li>
                            ))}
                        </ul>
                    </div>

                    <p className='relative text-[10px] font-semibold tracking-[0.25em] uppercase text-[#f4efe6]/45'>
                        Secure · OTP sign-in · No password
                    </p>
                </div>

                {/* ---------- Right: form ---------- */}
                <div className='bg-[#fbf8f4] p-7 sm:p-10 lg:p-12 flex flex-col justify-center'>
                    {/* Text tabs */}
                    <div className='flex gap-8 border-b border-[#2a1d17]/10 mb-9'>
                        {["Log in", "Sign up"].map((label, i) => {
                            const active = (i === 0) === isLoginPage;
                            return (
                                <button key={label} onClick={() => setIsLoginPage(i === 0)}
                                    className={`relative pb-3 text-sm font-semibold tracking-wide transition-colors ${active ? "text-[#2a1d17]" : "text-[#2a1d17]/45 hover:text-[#2a1d17]/80"}`}>
                                    {label}
                                    <span className={`absolute left-0 -bottom-px h-[2px] bg-[#b4532a] transition-all duration-300 ${active ? "w-full" : "w-0"}`} />
                                </button>
                            )
                        })}
                    </div>

                    <div key={String(isLoginPage)} className='auth-form auth-rise'>
                        {isLoginPage ? <LoginForm /> : <SignupForm />}
                    </div>

                    <p className='text-center text-sm text-[#2a1d17]/60 mt-8'>
                        {isLoginPage ? "New to Shopzy?" : "Already have an account?"}{" "}
                        <button onClick={() => setIsLoginPage(!isLoginPage)} className='group relative font-semibold text-[#b4532a]'>
                            {isLoginPage ? "Create an account" : "Log in"}
                            <span className='absolute left-0 -bottom-0.5 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#b4532a] transition-transform duration-300' />
                        </button>
                    </p>
                    <p className='text-center text-[11px] text-[#2a1d17]/45 mt-3'>
                        By continuing, you agree to Shopzy's Terms of Use and Privacy Policy.
                    </p>
                </div>
            </div>

            <Snackbar anchorOrigin={{ vertical: "top", horizontal: "right" }} open={snackbarOpen}
                autoHideDuration={6000} onClose={() => setSnackbarOpen(false)}>
                <Alert onClose={() => setSnackbarOpen(false)} severity={auth.error ? "error" : "success"} variant="filled" sx={{ width: '100%' }}>
                    {auth.error ? auth.error : "We've sent a code to your email"}
                </Alert>
            </Snackbar>
        </section>
    )
}

export default Auth