import AutoAwesomeIcon from "@mui/icons-material/AutoAwesome";

interface ResponseMessageProps {
  message: string;
}

// AI ka jawab (left side, white bubble + avatar)
const ResponseMessage = ({ message }: ResponseMessageProps) => {
  return (
    <div className="flex items-end gap-2 msg-in">
      <div className="w-7 h-7 shrink-0 rounded-full bg-[#00927c] grid place-items-center">
        <AutoAwesomeIcon sx={{ fontSize: 14, color: "white" }} />
      </div>
      <div className="max-w-[80%] text-sm text-gray-800 bg-white border border-gray-100 px-4 py-2.5 rounded-2xl rounded-bl-md shadow-sm whitespace-pre-line break-words leading-relaxed">
        {message}
      </div>
    </div>
  );
};

export default ResponseMessage;