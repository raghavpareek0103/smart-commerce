import { useEffect, useRef, useState } from "react";
import { useAppDispatch, useAppSelector } from "../../../Redux Toolkit/Store";
import { chatBot } from "../../../Redux Toolkit/Customer/AiChatBotSlice";
import { IconButton } from "@mui/material";
import SendRoundedIcon from "@mui/icons-material/SendRounded";
import CloseIcon from "@mui/icons-material/Close";
import AutoAwesomeIcon from "@mui/icons-material/AutoAwesome";
import PromptMessage from "./PromptMessage";
import ResponseMessage from "./ResponseMessage";
import "./ChatBot.css";

interface ChatBotProps {
  handleClose: (e: any) => void;
  productId?: number;
}

// Ek click me poochne wale sawaal
const productSuggestions = ["Is this good quality?", "Which size should I pick?", "Is there any offer on this?"];
const generalSuggestions = ["Where is my order?", "What's in my cart?", "Suggest gifts under ₹999"];

const ChatBot = ({ handleClose, productId }: ChatBotProps) => {
  const dispatch = useAppDispatch();
  const [prompt, setPrompt] = useState("");
  const bottomRef = useRef<HTMLDivElement>(null);
  const { aiChatBot } = useAppSelector((store) => store);

  const suggestions = productId ? productSuggestions : generalSuggestions;

  const send = (text: string) => {
    const msg = text.trim();
    if (!msg || aiChatBot.loading) return;
    dispatch(chatBot({ prompt: { prompt: msg }, productId, userId: null }));
    setPrompt("");
  };

  // Naya message aane par neeche scroll
  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [aiChatBot.messages, aiChatBot.loading]);

  return (
    <div
      onClick={(e) => e.stopPropagation()}
      className="chat-pop w-[92vw] sm:w-[400px] h-[min(620px,80vh)] flex flex-col bg-white rounded-3xl shadow-[0_24px_60px_rgba(15,23,42,0.25)] overflow-hidden border border-gray-100"
    >
      {/* ---------- Header ---------- */}
      <div
        className="relative px-5 py-4 flex items-center justify-between text-white overflow-hidden"
        style={{ background: "linear-gradient(135deg,#0b1f1c 0%,#00705f 60%,#00b894 100%)" }}
      >
        <div className="absolute -right-8 -top-10 w-32 h-32 rounded-full bg-white/10" />
        <div className="relative flex items-center gap-3">
          <div className="w-11 h-11 rounded-2xl bg-white/15 backdrop-blur grid place-items-center">
            <AutoAwesomeIcon sx={{ fontSize: 22, color: "#fde68a" }} />
          </div>
          <div>
            <h2 className="font-bold leading-tight">Shopzy AI Assistant</h2>
            <p className="text-xs text-white/75 flex items-center gap-1.5">
              <span className="w-2 h-2 rounded-full bg-[#4ade80] online-dot" />
              {productId ? "Ask anything about this product" : "Online • replies instantly"}
            </p>
          </div>
        </div>
        <IconButton onClick={handleClose} size="small" sx={{ color: "white", bgcolor: "rgba(255,255,255,0.12)", "&:hover": { bgcolor: "rgba(255,255,255,0.25)" } }}>
          <CloseIcon fontSize="small" />
        </IconButton>
      </div>

      {/* ---------- Messages ---------- */}
      <div className="flex-1 overflow-y-auto px-4 py-5 space-y-4 bg-[#f7faf9] chat-scroll">
        {/* Welcome card */}
        <div className="bg-white rounded-2xl p-4 border border-gray-100 shadow-sm">
          <p className="text-sm text-gray-800">
            👋 <span className="font-semibold">Hi! I'm your shopping assistant.</span>
          </p>
          <p className="text-sm text-gray-500 mt-1">
            {productId
              ? "Ask me about this product's quality, size, price or offers."
              : "Ask me about your orders, your cart, or get product suggestions."}
          </p>
          {aiChatBot.messages.length === 0 && (
            <div className="flex flex-wrap gap-2 mt-3">
              {suggestions.map((s) => (
                <button
                  key={s}
                  onClick={() => send(s)}
                  className="text-xs font-medium text-[#00705f] bg-[#e6f6f3] hover:bg-[#00927c] hover:text-white px-3 py-1.5 rounded-full transition-colors"
                >
                  {s}
                </button>
              ))}
            </div>
          )}
        </div>

        {aiChatBot.messages.map((item: any, index: number) =>
          item.role === "user" ? (
            <PromptMessage key={index} message={item.message} />
          ) : (
            <ResponseMessage key={index} message={item.message} />
          )
        )}

        {/* Typing indicator */}
        {aiChatBot.loading && (
          <div className="flex items-end gap-2">
            <div className="w-7 h-7 shrink-0 rounded-full bg-[#00927c] grid place-items-center">
              <AutoAwesomeIcon sx={{ fontSize: 14, color: "white" }} />
            </div>
            <div className="bg-white border border-gray-100 rounded-2xl rounded-bl-md px-4 py-3 flex gap-1 shadow-sm">
              <span className="typing-dot" />
              <span className="typing-dot" />
              <span className="typing-dot" />
            </div>
          </div>
        )}

        {/* Error */}
        {aiChatBot.error && !aiChatBot.loading && (
          <p className="text-xs text-red-600 bg-red-50 border border-red-100 rounded-xl px-3 py-2">
            ⚠️ {aiChatBot.error}. Please try again.
          </p>
        )}

        <div ref={bottomRef} />
      </div>

      {/* ---------- Input ---------- */}
      <form
        onSubmit={(e) => { e.preventDefault(); send(prompt); }}
        className="p-3 border-t border-gray-100 bg-white"
      >
        <div className="flex items-center gap-2 bg-gray-100 rounded-full pl-4 pr-1.5 py-1.5 focus-within:bg-white focus-within:ring-2 focus-within:ring-[#00927c]/40 transition-all">
          <input
            value={prompt}
            onChange={(e) => setPrompt(e.target.value)}
            type="text"
            placeholder="Type your question…"
            className="flex-1 min-w-0 bg-transparent outline-none text-sm text-gray-800 placeholder:text-gray-400"
          />
          <button
            type="submit"
            disabled={!prompt.trim() || aiChatBot.loading}
            className="w-9 h-9 shrink-0 rounded-full grid place-items-center text-white bg-[#00927c] hover:bg-[#00705f] disabled:bg-gray-300 transition-colors"
          >
            <SendRoundedIcon sx={{ fontSize: 18 }} />
          </button>
        </div>
        <p className="text-[10px] text-gray-400 text-center mt-2">AI can make mistakes. Please check important details.</p>
      </form>
    </div>
  );
};

export default ChatBot;