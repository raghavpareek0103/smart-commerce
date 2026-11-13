// AskShopzyAI.tsx
// Home page section: Gemini chatbot ko saamne laata hai.
// Chip pe click ya sawaal type karke Enter -> sawaal chatbot ko jaata hai aur chatbot khul jaata hai.
import { useState } from "react";
import { useAppDispatch } from "../../../../Redux Toolkit/Store";
import { chatBot } from "../../../../Redux Toolkit/Customer/AiChatBotSlice";

// Home.tsx isi event ko sunke chatbot kholta hai
export const OPEN_CHAT_EVENT = "shopzy:open-chat";

const prompts = [
  { tag: "Gifting", text: "Gifts under ₹999" },
  { tag: "Men", text: "Wedding outfit for men" },
  { tag: "Tech", text: "Best phone under ₹20k" },
  { tag: "Women", text: "Office wear for women" },
  { tag: "Trending", text: "Trending sneakers" },
  { tag: "Home", text: "Home décor ideas under ₹1,500" },
];

const css = `
.ai-sec{position:relative;overflow:hidden;background:radial-gradient(90% 120% at 85% 0%,#2f3a33 0%,#1c231f 55%,#151a17 100%);color:#f3ebe3}
.ai-sec:before{content:"";position:absolute;inset:0;background-image:repeating-linear-gradient(115deg,rgba(255,255,255,.03) 0 1px,transparent 1px 24px);pointer-events:none}
.ai-water{position:absolute;right:-20px;bottom:-60px;font-family:'Playfair Display',serif;font-style:italic;font-size:220px;line-height:1;color:rgba(255,255,255,.035);pointer-events:none;white-space:nowrap}
.ai-serif{font-family:'Playfair Display',serif}
.ai-orb{width:54px;height:54px;border-radius:50%;flex:none;position:relative;
  background:conic-gradient(from 0deg,#b4532a,#e7b48f,#c99b5c,#b4532a);animation:ai-spin 6s linear infinite}
.ai-orb:after{content:"✦";position:absolute;inset:5px;border-radius:50%;background:#1c231f;display:grid;place-items:center;color:#e7b48f;font-size:20px}
.ai-input{display:flex;align-items:center;gap:10px;background:#fbf8f4;border-radius:999px;padding:7px 7px 7px 22px;
  box-shadow:0 25px 50px -20px rgba(0,0,0,.6);transition:box-shadow .3s}
.ai-input:focus-within{box-shadow:0 0 0 4px rgba(231,180,143,.35),0 25px 50px -20px rgba(0,0,0,.6)}
.ai-input input{flex:1;min-width:0;background:transparent;border:0;outline:0;color:#2a1d17;font-size:15px}
.ai-input input::placeholder{color:#9b8b80}
.ai-send{height:46px;padding:0 20px;border-radius:999px;background:#b4532a;color:#fff;font-weight:600;font-size:14px;display:flex;align-items:center;gap:8px;transition:background .25s,transform .25s}
.ai-send:hover{background:#9c4622;transform:translateX(2px)}
.ai-chip{display:flex;flex-direction:column;align-items:flex-start;gap:6px;text-align:left;padding:16px 18px;border-radius:18px;
  border:1px solid rgba(243,235,227,.14);background:rgba(243,235,227,.04);transition:all .3s;cursor:pointer}
.ai-chip:hover{background:#f3ebe3;color:#2a1d17;border-color:#f3ebe3;transform:translateY(-4px)}
.ai-chip:hover .ai-tag{color:#b4532a}
.ai-chip:hover .ai-arrow{transform:rotate(45deg);background:#b4532a;color:#fff}
.ai-tag{font-size:10px;letter-spacing:.25em;text-transform:uppercase;color:#e7b48f;font-weight:700;transition:color .3s}
.ai-arrow{width:26px;height:26px;border-radius:50%;display:grid;place-items:center;border:1px solid currentColor;font-size:13px;transition:all .3s;flex:none}
@keyframes ai-spin{to{transform:rotate(360deg)}}
@media (prefers-reduced-motion:reduce){.ai-orb{animation:none}}
`;

const AskShopzyAI = () => {
  const dispatch = useAppDispatch();
  const [question, setQuestion] = useState("");

  const ask = (text: string) => {
    const q = text.trim();
    if (!q) return;
    dispatch(chatBot({ prompt: { prompt: q }, productId: undefined, userId: null }));
    window.dispatchEvent(new Event(OPEN_CHAT_EVENT));
    setQuestion("");
  };

  return (
    <section className="ai-sec">
      <style>{css}</style>
      <span className="ai-water">Ask anything</span>

      <div className="relative grid grid-cols-1 lg:grid-cols-[1.05fr_1fr] gap-10 lg:gap-16 px-5 lg:px-20 py-14 lg:py-20 items-center">
        {/* Left: heading + input */}
        <div className="min-w-0">
          <p className="flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#e7b48f]">
            <span className="w-10 h-px bg-[#e7b48f]" />
            Shopzy AI · Powered by Gemini
          </p>

          <div className="mt-5 flex items-start gap-4">
            <span className="ai-orb hidden sm:block" />
            <h2 className="ai-serif text-4xl sm:text-5xl xl:text-6xl font-semibold leading-[1.04]">
              Not sure what to buy?{" "}
              <span className="italic font-medium text-[#e7b48f]">Just ask.</span>
            </h2>
          </div>

          <p className="mt-5 max-w-md text-sm sm:text-base leading-relaxed text-[#f3ebe3]/65">
            Your personal shopping assistant — tell it the occasion, budget or style and get picks in seconds.
          </p>

          <form
            className="ai-input mt-8 max-w-xl"
            onSubmit={(e) => {
              e.preventDefault();
              ask(question);
            }}
          >
            <input
              value={question}
              onChange={(e) => setQuestion(e.target.value)}
              placeholder="e.g. Kurta for Diwali under ₹1,500"
              aria-label="Ask Shopzy AI"
            />
            <button type="submit" className="ai-send">
              Ask <span aria-hidden>↗</span>
            </button>
          </form>
        </div>

        {/* Right: prompt chips */}
        <div className="min-w-0">
          <p className="text-[11px] font-semibold tracking-[0.3em] uppercase text-[#f3ebe3]/50 mb-4">Try asking</p>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
            {prompts.map((p) => (
              <button key={p.text} type="button" className="ai-chip" onClick={() => ask(p.text)}>
                <span className="ai-tag">{p.tag}</span>
                <span className="flex items-center justify-between gap-3 w-full">
                  <span className="ai-serif text-lg font-semibold leading-snug">{p.text}</span>
                  <span className="ai-arrow" aria-hidden>↗</span>
                </span>
              </button>
            ))}
          </div>
        </div>
      </div>
    </section>
  );
};

export default AskShopzyAI;