// recentlyViewed.ts
// User ne jo products dekhe, unhe browser (localStorage) me yaad rakhta hai.
// Backend ki zaroorat nahi. Sabse naya product sabse aage, max 12 products.

export interface RecentProduct {
  id: number;
  title: string;
  image: string;
  sellingPrice: number;
  mrpPrice: number;
  discountPercent?: number;
  categoryId?: string;
  viewedAt: number;
}

const KEY = "syncart_recently_viewed";
const MAX = 12;
export const RECENT_EVENT = "syncart:recently-viewed";

export const getRecentlyViewed = (): RecentProduct[] => {
  try {
    const raw = localStorage.getItem(KEY);
    const list = raw ? JSON.parse(raw) : [];
    return Array.isArray(list) ? list : [];
  } catch {
    return [];
  }
};

export const addRecentlyViewed = (product: any) => {
  if (!product?.id) return;
  try {
    const item: RecentProduct = {
      id: product.id,
      title: product.title,
      image: product.images?.[0] || "",
      sellingPrice: product.sellingPrice,
      mrpPrice: product.mrpPrice,
      discountPercent: product.discountPercent,
      categoryId: product.category?.categoryId,
      viewedAt: Date.now(),
    };
    const list = getRecentlyViewed().filter((p) => p.id !== item.id);
    localStorage.setItem(KEY, JSON.stringify([item, ...list].slice(0, MAX)));
    window.dispatchEvent(new Event(RECENT_EVENT));
  } catch {
    /* storage band ho to kuch nahi karna */
  }
};

export const clearRecentlyViewed = () => {
  try {
    localStorage.removeItem(KEY);
    window.dispatchEvent(new Event(RECENT_EVENT));
  } catch {
    /* ignore */
  }
};