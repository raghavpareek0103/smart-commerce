import { useEffect, useState } from "react";
import { useAppDispatch } from "../../../Redux Toolkit/Store";
import { paymentSuccess } from "../../../Redux Toolkit/Customer/OrderSlice";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import CheckIcon from "@mui/icons-material/Check";
import CloseIcon from "@mui/icons-material/Close";
import ArrowOutwardIcon from "@mui/icons-material/ArrowOutward";

// Is page ki styling yahin hai — alag CSS file ki zarurat nahi
const psCss = `
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,500;0,600;0,700;1,500;1,600&display=swap');
.ps-serif { font-family: 'Playfair Display', Georgia, serif; }
.ps-rise { animation: psRise .6s ease both; }
@keyframes psRise { from { opacity: 0; transform: translateY(16px); } to { opacity: 1; transform: translateY(0); } }
.ps-pop { animation: psPop .6s cubic-bezier(.2,1.4,.4,1) both .15s; }
@keyframes psPop { from { transform: scale(0); } to { transform: scale(1); } }
.ps-ring { animation: psRing 2.4s ease-out infinite; }
@keyframes psRing { from { transform: scale(1); opacity: .5; } to { transform: scale(1.9); opacity: 0; } }
.ps-spin { animation: psSpin 1s linear infinite; }
@keyframes psSpin { to { transform: rotate(360deg); } }
.ps-cta .ps-arrow { transition: transform .35s ease; }
.ps-cta:hover .ps-arrow { transform: rotate(45deg); }
`;

type Status = "verifying" | "success" | "failed";

const PaymentSuccessHandler = () => {
    const dispatch = useAppDispatch();
    const location = useLocation();
    const navigate = useNavigate();
    const { orderId } = useParams();

    const params = new URLSearchParams(location.search);
    const paymentId = params.get("razorpay_payment_id");
    const paymentLinkId = params.get("razorpay_payment_link_id");
    const razorpayStatus = params.get("razorpay_payment_link_status"); // "paid" / "failed" ...

    const [status, setStatus] = useState<Status>(paymentId ? "verifying" : "failed");

    useEffect(() => {
        if (!paymentId) return;
        if (razorpayStatus && razorpayStatus !== "paid") {
            setStatus("failed");
            return;
        }
        dispatch(
            paymentSuccess({
                paymentId,
                paymentLinkId: paymentLinkId || "",
                jwt: localStorage.getItem("jwt") || "",
            })
        ).then((res: any) => {
            setStatus(paymentSuccess.fulfilled.match(res) ? "success" : "failed");
        });
    }, [paymentId]);

    const ok = status === "success";
    const failed = status === "failed";

    return (
        <div className="relative overflow-hidden bg-[#f6f1ea] text-[#2a1d17] min-h-[calc(100vh-110px)] grid place-items-center px-5 py-16">
            <style>{psCss}</style>

            {/* peeche halka watermark */}
            <span
                className="ps-serif pointer-events-none select-none absolute -bottom-12 left-1/2 -translate-x-1/2 text-[160px] lg:text-[260px] italic leading-none text-transparent whitespace-nowrap"
                style={{ WebkitTextStroke: "1px rgba(42,29,23,0.07)" }}
            >
                {failed ? "Oops" : "Thank you"}
            </span>

            <div className="ps-rise relative w-full max-w-xl rounded-[32px] bg-[#fbf8f4] border border-[#2a1d17]/10 shadow-[0_40px_80px_-40px_rgba(42,29,23,0.55)] overflow-hidden">
                {/* upar ki dark patti */}
                <div className="relative bg-[#1b2420] text-[#f4efe6] px-8 pt-12 pb-16 text-center overflow-hidden">
                    <div className="absolute inset-0 opacity-[0.06]" style={{ backgroundImage: "repeating-linear-gradient(115deg,#c9a96e 0 1px,transparent 1px 22px)" }} />

                    {/* icon */}
                    <div className="relative mx-auto w-20 h-20">
                        {ok && <span className="ps-ring absolute inset-0 rounded-full bg-[#c9a96e]" />}
                        <div
                            className={`relative w-20 h-20 rounded-full grid place-items-center border-2 ${failed
                                ? "bg-[#b4532a] border-[#e7b48f]/60"
                                : "bg-[#c9a96e] border-[#f4efe6]/40"} ${status !== "verifying" ? "ps-pop" : ""}`}
                        >
                            {status === "verifying" && <span className="ps-spin w-8 h-8 rounded-full border-[3px] border-[#1b2420]/30 border-t-[#1b2420]" />}
                            {ok && <CheckIcon sx={{ fontSize: 40, color: "#1b2420" }} />}
                            {failed && <CloseIcon sx={{ fontSize: 38, color: "#f6f1ea" }} />}
                        </div>
                    </div>

                    <p className="relative mt-8 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#c9a96e]">
                        {status === "verifying" ? "— Confirming payment —" : ok ? "— Order confirmed —" : "— Payment not completed —"}
                    </p>
                    <h1 className="ps-serif relative mt-3 text-4xl lg:text-5xl font-semibold leading-tight">
                        {status === "verifying" && <>Just a <span className="italic text-[#c9a96e]">moment…</span></>}
                        {ok && <>Thank you, <span className="italic text-[#c9a96e]">order placed!</span></>}
                        {failed && <>Something went <span className="italic text-[#e7b48f]">wrong.</span></>}
                    </h1>
                    <p className="relative mt-3 text-sm text-[#f4efe6]/60 max-w-sm mx-auto">
                        {status === "verifying" && "We're verifying your payment with Razorpay. Please don't close this page."}
                        {ok && "Your payment was successful. We'll keep you posted as your order gets packed and shipped."}
                        {failed && "Your payment could not be confirmed. If money was deducted, it will be refunded automatically."}
                    </p>
                </div>

                {/* details */}
                <div className="px-8 py-8">
                    <div className="grid grid-cols-2 gap-4 text-sm">
                        <div className="rounded-2xl bg-[#f3ebe3] px-4 py-3">
                            <p className="text-[10px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/45">Reference</p>
                            <p className="ps-serif mt-1 text-lg font-semibold">#{orderId || "—"}</p>
                        </div>
                        <div className="rounded-2xl bg-[#f3ebe3] px-4 py-3 min-w-0">
                            <p className="text-[10px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/45">Payment ID</p>
                            <p className="mt-1 font-semibold truncate" title={paymentId || ""}>{paymentId || "—"}</p>
                        </div>
                    </div>

                    <div className="mt-6 flex flex-col sm:flex-row gap-3">
                        {failed ? (
                            <button
                                onClick={() => navigate("/cart")}
                                className="ps-cta flex-1 flex items-center justify-between h-14 pl-7 pr-2 rounded-full bg-[#2a1d17] hover:bg-[#1b2420] text-[#f6f1ea] text-sm font-semibold tracking-[0.15em] uppercase transition-colors"
                            >
                                Back to bag
                                <span className="ps-arrow w-10 h-10 rounded-full grid place-items-center bg-[#b4532a]"><ArrowOutwardIcon sx={{ fontSize: 18 }} /></span>
                            </button>
                        ) : (
                            <button
                                disabled={!ok}
                                onClick={() => navigate("/account/orders")}
                                className="ps-cta flex-1 flex items-center justify-between h-14 pl-7 pr-2 rounded-full bg-[#2a1d17] hover:bg-[#1b2420] disabled:opacity-50 text-[#f6f1ea] text-sm font-semibold tracking-[0.15em] uppercase transition-colors"
                            >
                                View my orders
                                <span className="ps-arrow w-10 h-10 rounded-full grid place-items-center bg-[#b4532a]"><ArrowOutwardIcon sx={{ fontSize: 18 }} /></span>
                            </button>
                        )}
                        <button
                            onClick={() => navigate("/")}
                            className="flex-1 h-14 rounded-full border border-[#2a1d17]/20 hover:border-[#2a1d17] text-sm font-semibold tracking-[0.15em] uppercase transition-colors"
                        >
                            Continue shopping
                        </button>
                    </div>

                    <p className="mt-6 text-center text-xs text-[#2a1d17]/45">
                        Secured by Razorpay · Test mode payment
                    </p>
                </div>
            </div>
        </div>
    );
};

export default PaymentSuccessHandler;