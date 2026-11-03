interface PromptMessageProps {
  message: string;
  index?: number;
}

// User ka message (right side, teal bubble)
const PromptMessage = ({ message }: PromptMessageProps) => {
  return (
    <div className="flex justify-end msg-in">
      <div className="max-w-[80%] text-sm text-white px-4 py-2.5 rounded-2xl rounded-br-md shadow-sm whitespace-pre-line break-words"
        style={{ background: "linear-gradient(135deg,#00927c,#00b894)" }}>
        {message}
      </div>
    </div>
  );
};

export default PromptMessage;