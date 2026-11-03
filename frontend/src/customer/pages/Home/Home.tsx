import { useState } from 'react'
import Banner from './Banner/Banner'
import HomeCategory from './HomeCategory/HomeCategory'
import FeaturedCollections from './Collections/FeaturedCollections'
import ElectronicCategory from './Electronic Category/ElectronicCategory'
import DealSlider from './Deals/Deals'
import SellerBanner from './SellerBanner/SellerBanner'
import ChatBot from '../ChatBot/ChatBot'
import BrandStrip from './TopBrands/BrandStrip'
import { Backdrop, CircularProgress } from '@mui/material'
import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';
import { useAppSelector } from '../../../Redux Toolkit/Store'

const Home = () => {
    const [showChatBot, setShowChatBot] = useState(false)
    const { homePage } = useAppSelector(store => store)

    if (homePage.loading) {
        return <Backdrop open={true}><CircularProgress color="inherit" /></Backdrop>
    }

    return (
        <div className='relative'>
            {/* Electronics patti */}
            {homePage.homePageData?.electricCategories && <ElectronicCategory />}

            {/* Hero banners */}
            <Banner />

            {/* Top brands — do chalti lines */}
            <BrandStrip />

            {/* Poori chaudai wale sections — ek doosre se chipke hue (beech me koi gap nahi) */}
            <div>
                {/* Flash sale + collections */}
                <FeaturedCollections />

                {/* Today's Best Deals */}
                {homePage.homePageData?.deals && <DealSlider />}

                {/* Shop by Category */}
                {homePage.homePageData?.shopByCategories && <HomeCategory />}

                {/* Become seller CTA */}
                <SellerBanner />
            </div>

                        {/* AI chat button */}
                        <section className='fixed bottom-6 right-6 z-50'>
                            {showChatBot
                                ? <ChatBot handleClose={() => setShowChatBot(false)} />
                                : <button
                                    onClick={() => setShowChatBot(true)}
                                    className='ai-fab flex items-center gap-2 p-2.5 sm:pl-3 sm:pr-5 h-14 rounded-full text-white font-semibold hover:scale-105 transition-transform'
                                    style={{ background: "linear-gradient(135deg,#1b2420,#2f3d35)", border: "1px solid rgba(201,169,110,0.5)" }}
                                >
                                    <span className='w-9 h-9 rounded-full bg-[#c9a96e]/20 grid place-items-center'>
                                        <AutoAwesomeIcon sx={{ fontSize: 20, color: "#c9a96e" }} />
                                    </span>
                                    <span className='hidden sm:inline text-[#f6f1ea]'>Ask Shopzy <span className='italic' style={{ fontFamily: '"Playfair Display", Georgia, serif', color: '#c9a96e' }}>AI</span></span>
                                </button>}
                        </section>
        </div>
    )
}

export default Home