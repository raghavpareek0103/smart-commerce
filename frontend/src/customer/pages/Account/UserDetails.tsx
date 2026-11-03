import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import MailOutlineIcon from "@mui/icons-material/MailOutline";
import PhoneIphoneIcon from "@mui/icons-material/PhoneIphone";
import VerifiedOutlinedIcon from "@mui/icons-material/VerifiedOutlined";
import { useAppSelector } from "../../../Redux Toolkit/Store";

const UserDetails = () => {
  const { user } = useAppSelector((store) => store);
  const u = user.user;

  const rows = [
    { label: "Full name", value: u?.fullName, icon: <PersonOutlineIcon sx={{ fontSize: 19 }} /> },
    { label: "Email address", value: u?.email, icon: <MailOutlineIcon sx={{ fontSize: 19 }} /> },
    { label: "Mobile number", value: u?.mobile, icon: <PhoneIphoneIcon sx={{ fontSize: 19 }} /> },
  ];

  return (
    <div className="acc-rise">
      <div className="pb-8 border-b border-[#2a1d17]/10">
        <div className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]">
          <span className="w-10 h-px bg-[#b4532a]" /> About you
        </div>
        <h2 className="acc-serif mt-3 text-4xl lg:text-5xl font-semibold">Personal <span className="italic font-medium text-[#b4532a]">Details</span></h2>
        <p className="mt-2 text-sm text-[#2a1d17]/55">The details we use for your orders and updates.</p>
      </div>

      <div className="pt-8 grid grid-cols-1 xl:grid-cols-[1fr_300px] gap-8">
        {/* fields */}
        <div className="divide-y divide-[#2a1d17]/10">
          {rows.map((r, i) => (
            <div key={r.label} className="flex items-center gap-4 py-5 first:pt-0">
              <span className="acc-serif italic text-xs text-[#b4532a] w-5">{String(i + 1).padStart(2, "0")}</span>
              <span className="w-11 h-11 shrink-0 rounded-full grid place-items-center bg-[#f3ebe3] text-[#2a1d17]">{r.icon}</span>
              <div className="min-w-0">
                <p className="text-[10px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/45">{r.label}</p>
                <p className="acc-serif mt-1 text-lg lg:text-xl font-semibold truncate">{r.value || <span className="italic text-[#2a1d17]/35 font-medium">Not added</span>}</p>
              </div>
            </div>
          ))}
        </div>

        {/* member card */}
        <div className="self-start rounded-[24px] bg-[#1b2420] text-[#f4efe6] p-6 relative overflow-hidden">
          <span className="acc-serif acc-outline absolute -right-4 -bottom-12 text-[140px] italic leading-none pointer-events-none">S</span>
          <div className="w-16 h-16 rounded-full grid place-items-center bg-[#b4532a] border-2 border-[#c9a96e]/60">
            <span className="acc-serif text-3xl italic font-semibold">{u?.fullName?.[0]?.toUpperCase() || "S"}</span>
          </div>
          <p className="acc-serif mt-5 text-2xl font-semibold">{u?.fullName}</p>
          <p className="mt-1 text-xs text-[#f4efe6]/55">Shopzy member</p>
          <div className="relative mt-6 inline-flex items-center gap-2 px-3 py-1.5 rounded-full border border-[#c9a96e]/40 text-[11px] font-semibold tracking-[0.2em] uppercase text-[#c9a96e]">
            <VerifiedOutlinedIcon sx={{ fontSize: 15 }} /> Verified account
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserDetails;