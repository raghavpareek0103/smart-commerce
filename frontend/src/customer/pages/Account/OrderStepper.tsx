import CheckIcon from "@mui/icons-material/Check";
import CloseIcon from "@mui/icons-material/Close";

const steps = [
    { name: "Order Placed", description: "We've got your order", value: ["PENDING", "PLACED"] },
    { name: "Packed", description: "Ready at the warehouse", value: ["CONFIRMED"] },
    { name: "Shipped", description: "On its way to you", value: ["SHIPPED"] },
    { name: "Delivered", description: "Enjoy your purchase", value: ["DELIVERED"] },
];

const OrderStepper = ({ orderStatus }: { orderStatus?: string }) => {
    // Cancel hua order
    if (orderStatus === "CANCELLED") {
        return (
            <div className="flex items-center gap-4">
                <div className="flex items-center gap-3">
                    <span className="w-10 h-10 rounded-full grid place-items-center bg-[#1b2420] text-[#c9a96e]"><CheckIcon sx={{ fontSize: 18 }} /></span>
                    <div>
                        <p className="text-sm font-semibold">Order Placed</p>
                        <p className="text-xs text-[#2a1d17]/50">We got your order</p>
                    </div>
                </div>
                <div className="flex-1 h-px bg-[#b4532a]/40" />
                <div className="flex items-center gap-3">
                    <span className="w-10 h-10 rounded-full grid place-items-center bg-[#b4532a] text-[#f6f1ea]"><CloseIcon sx={{ fontSize: 18 }} /></span>
                    <div>
                        <p className="text-sm font-semibold text-[#b4532a]">Cancelled</p>
                        <p className="text-xs text-[#2a1d17]/50">Refund, if any, is on its way</p>
                    </div>
                </div>
            </div>
        );
    }

    const current = Math.max(0, steps.findIndex(s => s.value.includes(orderStatus || "PLACED")));
    const progress = (current / (steps.length - 1)) * 100;

    return (
        <div className="relative">
            {/* line — desktop par aadi, mobile par khadi */}
            <div className="hidden md:block absolute top-5 left-5 w-[75%] h-[2px] bg-[#2a1d17]/10">
                <div className="h-full bg-[#c9a96e] transition-all duration-700" style={{ width: `${progress}%` }} />
            </div>
            <div className="md:hidden absolute top-5 bottom-5 left-5 w-[2px] bg-[#2a1d17]/10">
                <div className="w-full bg-[#c9a96e] transition-all duration-700" style={{ height: `${progress}%` }} />
            </div>

            <div className="relative grid grid-cols-1 md:grid-cols-4 gap-6 md:gap-3">
                {steps.map((step, index) => {
                    const done = index < current;
                    const active = index === current;
                    return (
                        <div key={step.name} className="flex md:flex-col items-center md:items-start gap-4 md:gap-3">
                            <span
                                className={`relative w-10 h-10 shrink-0 rounded-full grid place-items-center text-sm transition-all ${done
                                    ? "bg-[#1b2420] text-[#c9a96e]"
                                    : active
                                        ? "bg-[#b4532a] text-[#f6f1ea] ring-[6px] ring-[#b4532a]/15"
                                        : "bg-[#fbf8f4] border border-[#2a1d17]/15 text-[#2a1d17]/40"}`}
                            >
                                {done ? <CheckIcon sx={{ fontSize: 18 }} /> : <span className="acc-serif italic">{String(index + 1).padStart(2, "0")}</span>}
                            </span>
                            <div>
                                <p className={`text-sm font-semibold ${active ? "text-[#b4532a]" : index > current ? "text-[#2a1d17]/45" : ""}`}>{step.name}</p>
                                <p className="text-xs text-[#2a1d17]/50 mt-0.5">{step.description}</p>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default OrderStepper;