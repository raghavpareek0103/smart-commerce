import { sumCartItemMrpPrice, sumCartItemSellingPrice } from "../../../util/cartCalculator";
import { useAppSelector } from "../../../Redux Toolkit/Store";

// Delivery ka rule — backend (PaymentServiceImpl) me bhi yahi same rule hai
export const FREE_SHIPPING_ABOVE = 999;
export const SHIPPING_FEE = 79;

const PricingCard = (_props: any) => {
  const { cart } = useAppSelector((store) => store);
  const items = cart.cart?.cartItems || [];
  const discount = sumCartItemMrpPrice(items) - sumCartItemSellingPrice(items);
  const couponPrice: number = (cart.cart as any)?.couponPrice || 0;

  // Products ka total (coupon ke baad) — backend se aata hai
  const itemsTotal = cart.cart?.totalSellingPrice ?? 0;

  // ₹999 ya usse zyada par delivery free, warna ₹79
  const shipping = items.length === 0 || itemsTotal >= FREE_SHIPPING_ABOVE ? 0 : SHIPPING_FEE;
  const total = itemsTotal + shipping;
  const moreForFree = FREE_SHIPPING_ABOVE - itemsTotal;

  const Row = ({ label, value, accent }: { label: string; value: string; accent?: boolean }) => (
    <div className="flex justify-between items-center">
      <span className="text-[#2a1d17]/60">{label}</span>
      <span className={accent ? "font-semibold text-[#5b7a5a]" : "text-[#2a1d17]"}>{value}</span>
    </div>
  );

  return (
    <div className="text-sm">
      <div className="space-y-3 px-5 pt-4 pb-5">
        <Row label="Subtotal (MRP)" value={`₹${cart.cart?.totalMrpPrice ?? 0}`} />
        <Row label="Discount" value={`− ₹${discount}`} accent />
        {couponPrice > 0 && <Row label="Coupon" value={`− ₹${couponPrice}`} accent />}
        <Row label="Shipping" value={shipping === 0 ? "Free" : `₹${shipping}`} accent={shipping === 0} />
        <Row label="Platform fee" value="Free" accent />
      </div>

      {/* Free delivery ke liye kitna aur */}
      {shipping > 0 && moreForFree > 0 && (
        <div className="mx-5 mb-4">
          <p className="text-xs text-[#2a1d17]/60">
            Add <span className="font-semibold text-[#b4532a]">₹{moreForFree}</span> more for <span className="font-semibold text-[#5b7a5a]">free delivery</span>
          </p>
          <div className="mt-2 h-1.5 rounded-full bg-[#2a1d17]/10 overflow-hidden">
            <div
              className="h-full rounded-full bg-[#c9a96e] transition-all duration-500"
              style={{ width: `${Math.min(100, (itemsTotal / FREE_SHIPPING_ABOVE) * 100)}%` }}
            />
          </div>
        </div>
      )}

      <div className="mx-5 border-t border-dashed border-[#2a1d17]/20" />
      <div className="px-5 py-4 flex justify-between items-baseline">
        <span className="text-[10px] font-semibold tracking-[0.3em] uppercase text-[#2a1d17]/60">Total</span>
        <span className="cart-serif text-2xl font-semibold text-[#2a1d17]">₹{total}</span>
      </div>
      {discount + couponPrice > 0 && (
        <div className="mx-5 mb-2 rounded-full bg-[#f3ebe3] text-[#b4532a] text-xs font-semibold text-center py-2">
          You save <span className="cart-serif italic text-sm">₹{discount + couponPrice}</span> on this order
        </div>
      )}
    </div>
  );
};

export default PricingCard;