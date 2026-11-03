import { useEffect } from 'react'
import PaymentsOutlinedIcon from '@mui/icons-material/PaymentsOutlined';
import PlaceOutlinedIcon from '@mui/icons-material/PlaceOutlined';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';
import EditOutlinedIcon from '@mui/icons-material/EditOutlined';
import OrderStepper from './OrderStepper';
import { statusStyle } from './OrderItemCard';
import { useAppDispatch, useAppSelector } from '../../../Redux Toolkit/Store';
import { cancelOrder, fetchOrderById, fetchOrderItemById } from '../../../Redux Toolkit/Customer/OrderSlice';
import { useNavigate, useParams } from 'react-router-dom';
import { formatDate } from '../../util/fomateDate';

const Label = ({ children }: { children: React.ReactNode }) => (
  <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.3em] uppercase text-[#b4532a]'>
    <span className='w-8 h-px bg-[#b4532a]' /> {children}
  </div>
);

const OrderDetails = () => {
  const dispatch = useAppDispatch()
  const { auth, orders } = useAppSelector(store => store);
  const { orderItemId, orderId } = useParams()
  const navigate = useNavigate();

  useEffect(() => {
    dispatch(fetchOrderItemById({
      orderItemId: Number(orderItemId),
      jwt: localStorage.getItem("jwt") || ""
    }))
    dispatch(fetchOrderById({
      orderId: Number(orderId),
      jwt: localStorage.getItem("jwt") || ""
    }))
  }, [auth.jwt, orderId, orderItemId])

  if (!orders.orders || !orders.orderItem) {
    return (
      <div className='min-h-[50vh] grid place-items-center text-center'>
        <div>
          <p className='acc-serif text-3xl'>Order not <span className='italic text-[#b4532a]'>found.</span></p>
          <button onClick={() => navigate("/account/orders")} className='mt-5 text-sm font-semibold text-[#b4532a] hover:underline underline-offset-4'>← Back to orders</button>
        </div>
      </div>
    );
  }

  const item = orders.orderItem;
  const order = orders.currentOrder;
  const status = order?.orderStatus || "PLACED";
  const st = statusStyle[status] || statusStyle.PENDING;
  const cancelled = status === "CANCELLED";
  const saved = (item.mrpPrice || 0) - (item.sellingPrice || 0);
  const addr = order?.shippingAddress;

  const handleCancelOrder = () => {
    dispatch(cancelOrder(orderId))
  }

  return (
    <div className='acc-rise space-y-6'>
      <button
        onClick={() => navigate("/account/orders")}
        className='inline-flex items-center gap-2 text-[11px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/55 hover:text-[#b4532a] transition-colors'
      >
        <ArrowBackIcon sx={{ fontSize: 16 }} /> All orders
      </button>

      {/* ---------- product hero ---------- */}
      <section className='flex flex-col sm:flex-row gap-6 pb-8 border-b border-[#2a1d17]/10'>
        <div className='w-32 sm:w-36 shrink-0 aspect-[3/4] rounded-t-full rounded-b-2xl overflow-hidden bg-[#f3ebe3]'>
          <img className='w-full h-full object-cover object-top' src={item.product?.images?.[0]} alt="" />
        </div>
        <div className='flex-1 min-w-0'>
          <span className={`inline-flex items-center gap-1.5 px-3 py-1 rounded-full text-[10px] font-bold tracking-[0.18em] uppercase ${st.chip}`}>
            <span className={`w-1.5 h-1.5 rounded-full ${st.dot}`} /> {st.label}
          </span>
          <p className='mt-4 text-[10px] font-semibold tracking-[0.25em] uppercase text-[#b4532a]'>
            {item.product?.seller?.businessDetails?.businessName || "Shopzy"}
          </p>
          <h2 className='acc-serif mt-1 text-3xl lg:text-4xl font-semibold leading-tight'>{item.product?.title}</h2>
          <div className='mt-3 flex flex-wrap items-center gap-x-5 gap-y-1 text-sm text-[#2a1d17]/60'>
            <span>Qty <b className='text-[#2a1d17]'>{item.quantity || 1}</b></span>
            <span>Size <b className='text-[#2a1d17]'>{item.size || "FREE"}</b></span>
            {order?.orderId && <span>Order <b className='text-[#2a1d17]'>#{order.orderId}</b></span>}
          </div>
          <div className='mt-5 flex flex-wrap gap-3'>
            <button
              onClick={() => navigate(`/reviews/${item.product?.id}/create`)}
              className='acc-cta inline-flex items-center gap-3 h-11 pl-5 pr-1.5 rounded-full bg-[#2a1d17] text-[#f6f1ea] text-sm font-semibold'
            >
              <EditOutlinedIcon sx={{ fontSize: 16 }} /> Write a review
              <span className='acc-arrow w-8 h-8 rounded-full grid place-items-center bg-[#b4532a]'><ArrowOutwardIcon sx={{ fontSize: 15 }} /></span>
            </button>
            <button
              onClick={() => navigate(`/product-details/${item.product?.category?.categoryId}/${item.product?.title}/${item.product?.id}`)}
              className='h-11 px-5 rounded-full border border-[#2a1d17]/20 hover:border-[#2a1d17] text-sm font-semibold transition-colors'
            >
              Buy again
            </button>
          </div>
        </div>
      </section>

      {/* ---------- tracking ---------- */}
      <section className='rounded-[24px] bg-[#f6f1ea] border border-[#2a1d17]/[0.08] p-6 lg:p-8'>
        <div className='flex flex-wrap items-end justify-between gap-3 mb-8'>
          <div>
            <Label>Tracking</Label>
            <p className='acc-serif mt-3 text-2xl font-semibold'>
              {cancelled ? <>Order <span className='italic text-[#b4532a]'>cancelled</span></>
                : status === "DELIVERED" ? <>Delivered <span className='italic text-[#b4532a]'>{formatDate(order?.deliverDate)}</span></>
                  : <>Arriving by <span className='italic text-[#b4532a]'>{formatDate(order?.deliverDate)}</span></>}
            </p>
          </div>
          {order?.orderDate && <p className='text-xs text-[#2a1d17]/50'>Ordered on {formatDate(order.orderDate)}</p>}
        </div>
        <OrderStepper orderStatus={status} />
      </section>

      {/* ---------- address + payment ---------- */}
      <div className='grid grid-cols-1 md:grid-cols-2 gap-6'>
        <section className='rounded-[24px] bg-[#f6f1ea] border border-[#2a1d17]/[0.08] p-6'>
          <Label>Delivery address</Label>
          <div className='mt-5 flex gap-3'>
            <span className='w-10 h-10 shrink-0 rounded-full grid place-items-center bg-[#1b2420] text-[#c9a96e]'><PlaceOutlinedIcon sx={{ fontSize: 19 }} /></span>
            <div className='text-sm'>
              <p className='acc-serif text-lg font-semibold'>{addr?.name}</p>
              <p className='mt-1 text-[#2a1d17]/65 leading-relaxed'>
                {addr?.address}{addr?.locality ? `, ${addr.locality}` : ""}, {addr?.city}, {addr?.state} — {addr?.pinCode}
              </p>
              <p className='mt-2 text-[#2a1d17]/65'>Mobile <b className='text-[#2a1d17]'>{addr?.mobile}</b></p>
            </div>
          </div>
        </section>

        <section className='rounded-[24px] bg-[#1b2420] text-[#f4efe6] p-6 relative overflow-hidden'>
          <span className='acc-serif acc-outline absolute -right-2 -bottom-10 text-[130px] italic leading-none pointer-events-none'>₹</span>
          <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.3em] uppercase text-[#c9a96e]'>
            <span className='w-8 h-px bg-[#c9a96e]' /> Price details
          </div>
          <div className='relative mt-5 space-y-3 text-sm'>
            <div className='flex justify-between'><span className='text-[#f4efe6]/60'>MRP</span><span className='line-through text-[#f4efe6]/50'>₹{item.mrpPrice}</span></div>
            <div className='flex justify-between'><span className='text-[#f4efe6]/60'>Discount</span><span className='text-[#e7b48f]'>− ₹{saved}</span></div>
            <div className='flex justify-between'><span className='text-[#f4efe6]/60'>Delivery</span><span className='text-[#c9a96e]'>Free</span></div>
            <div className='h-px bg-[#f4efe6]/10' />
            <div className='flex justify-between items-end'>
              <span className='font-semibold'>Total paid</span>
              <span className='acc-serif text-3xl font-semibold'>₹{item.sellingPrice}</span>
            </div>
            {saved > 0 && <p className='text-xs text-[#c9a96e]'>You saved ₹{saved} on this item ✦</p>}
            <div className='mt-2 inline-flex items-center gap-2 px-3 py-1.5 rounded-full bg-[#f4efe6]/10 text-xs'>
              <PaymentsOutlinedIcon sx={{ fontSize: 15, color: "#c9a96e" }} /> Pay on delivery / Online
            </div>
          </div>
        </section>
      </div>

      {/* ---------- cancel ---------- */}
      <div className='flex flex-wrap items-center justify-between gap-4 pt-2'>
        <p className='text-xs text-[#2a1d17]/50'>Sold by <b className='text-[#2a1d17]'>{item.product?.seller?.businessDetails?.businessName || "Shopzy"}</b></p>
        {status !== "DELIVERED" && (
          <button
            disabled={cancelled}
            onClick={handleCancelOrder}
            className='h-12 px-7 rounded-full border-2 border-[#b4532a] text-[#b4532a] text-sm font-semibold tracking-[0.15em] uppercase hover:bg-[#b4532a] hover:text-[#f6f1ea] disabled:opacity-40 disabled:pointer-events-none transition-colors'
          >
            {cancelled ? "Order cancelled" : "Cancel order"}
          </button>
        )}
      </div>
    </div>
  )
}

export default OrderDetails