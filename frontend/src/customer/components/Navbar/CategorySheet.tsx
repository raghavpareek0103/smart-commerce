import { menLevelThree } from '../../../data/category/level three/menLevelThree'
import { menLevelTwo } from '../../../data/category/level two/menLevelTwo'
import { womenLevelThree } from '../../../data/category/level three/womenLevelThree'
import { womenLevelTwo } from '../../../data/category/level two/womenLevelTwo'
import { useNavigate } from 'react-router-dom'
import { electronicsLevelTwo } from '../../../data/category/level two/electronicsLavelTwo'
import { furnitureLevelTwo } from '../../../data/category/level two/furnitureLevleTwo'
import { furnitureLevelThree } from '../../../data/category/level three/furnitureLevelThree'
import { electronicsLevelThree } from '../../../data/category/level three/electronicsLevelThree'
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward'

const categoryTwo: { [key: string]: any[] } = {
    men: menLevelTwo,
    women: womenLevelTwo,
    electronics: electronicsLevelTwo,
    home_furniture: furnitureLevelTwo,
}

const categoryThree: { [key: string]: any[] } = {
    men: menLevelThree,
    women: womenLevelThree,
    electronics: electronicsLevelThree,
    home_furniture: furnitureLevelThree,
}

// Upar heading me dikhne wala naam
const titles: { [key: string]: string } = {
    men: "Men",
    women: "Women",
    electronics: "Electronics",
    home_furniture: "Home",
}

// "western wear" -> "Western Wear"
const pretty = (name: string = "") =>
    name.split(" ").map((w) => (w ? w[0].toUpperCase() + w.slice(1) : w)).join(" ")

const CategorySheet = ({ selectedCategory, toggleDrawer, setShowSheet, compact = false }: any) => {
    const navigate = useNavigate()

    const childCategory = (category: any[], parentCategoryId: string) =>
        category?.filter((child: any) => child.parentCategoryId == parentCategoryId)

    const handleCategoryClick = (category: string) => {
        if (toggleDrawer) toggleDrawer(false)()
        if (setShowSheet) setShowSheet(false)
        navigate("/products/" + category)
    }

    const groups = categoryTwo[selectedCategory] || []

    // ---------- Mobile drawer ke andar (chhota) ----------
    if (compact) {
        return (
            <div className='bg-[#fbf8f4] py-2'>
                {groups.map((item: any, i: number) => (
                    <div key={item.name} className='px-6 py-3'>
                        <p
                            onClick={() => handleCategoryClick(item.categoryId)}
                            className='flex items-baseline gap-2 mb-2 cursor-pointer'
                        >
                            <span className='nav-serif italic text-xs text-[#b4532a]'>{String(i + 1).padStart(2, "0")}</span>
                            <span className='nav-serif text-base font-semibold text-[#2a1d17]'>{pretty(item.name)}</span>
                        </p>
                        <ul className='space-y-1.5 pl-6'>
                            {childCategory(categoryThree[selectedCategory], item.categoryId)?.map((child: any) => (
                                <li
                                    key={child.name}
                                    onClick={() => handleCategoryClick(child.categoryId)}
                                    className='text-sm text-[#2a1d17]/65 hover:text-[#b4532a] cursor-pointer transition-colors'
                                >
                                    {child.name}
                                </li>
                            ))}
                        </ul>
                    </div>
                ))}
            </div>
        )
    }

    // ---------- Desktop mega menu ----------
    return (
        <div className='mega-menu relative bg-[#fbf8f4] text-[#2a1d17] max-h-[70vh] overflow-y-auto'>
            {/* Upar heading patti */}
            <div className='sticky top-0 z-10 flex items-end justify-between gap-6 px-10 pt-7 pb-5 bg-[#fbf8f4]/95 backdrop-blur border-b border-[#2a1d17]/10'>
                <div>
                    <p className='flex items-center gap-3 text-[10px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
                        <span className='w-8 h-px bg-[#b4532a]' />
                        The Directory
                    </p>
                    <h3 className='nav-serif mt-1.5 text-3xl font-semibold leading-none'>
                        The <span className='italic font-medium text-[#b4532a]'>{titles[selectedCategory]}</span> Edit
                    </h3>
                </div>
                <button
                    onClick={() => handleCategoryClick(groups[0]?.categoryId)}
                    className='group flex items-center gap-2.5 text-sm font-semibold'
                >
                    <span className='relative'>
                        Shop all {titles[selectedCategory]}
                        <span className='absolute left-0 -bottom-1 h-px w-full origin-left scale-x-0 group-hover:scale-x-100 bg-[#2a1d17] transition-transform duration-300' />
                    </span>
                    <span className='w-9 h-9 rounded-full grid place-items-center bg-[#2a1d17] text-[#f6f1ea] group-hover:bg-[#b4532a] group-hover:rotate-45 transition-all duration-300'>
                        <ArrowOutwardIcon sx={{ fontSize: 16 }} />
                    </span>
                </button>
            </div>

            {/* Columns — beech me patli lines */}
            <div className='grid grid-cols-5 divide-x divide-[#2a1d17]/10 px-4 py-7'>
                {groups.map((item: any, i: number) => (
                    <div key={item.name} className='px-6'>
                        <p
                            onClick={() => handleCategoryClick(item.categoryId)}
                            className='group/head cursor-pointer mb-4'
                        >
                            <span className='nav-serif italic text-xs text-[#b4532a]'>{String(i + 1).padStart(2, "0")}</span>
                            <span className='nav-serif block mt-0.5 text-lg font-semibold leading-tight group-hover/head:text-[#b4532a] transition-colors'>
                                {pretty(item.name)}
                            </span>
                            <span className='mt-2 block h-px w-6 bg-[#c9a96e] group-hover/head:w-12 transition-all duration-300' />
                        </p>
                        <ul className='space-y-2.5'>
                            {childCategory(categoryThree[selectedCategory], item.categoryId)?.map((child: any) => (
                                <li
                                    key={child.name}
                                    onClick={() => handleCategoryClick(child.categoryId)}
                                    className='group/item flex items-center text-[13.5px] text-[#2a1d17]/65 hover:text-[#b4532a] cursor-pointer transition-colors'
                                >
                                    <span className='w-0 h-px bg-[#b4532a] mr-0 group-hover/item:w-3 group-hover/item:mr-2 transition-all duration-300' />
                                    {child.name}
                                </li>
                            ))}
                        </ul>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default CategorySheet