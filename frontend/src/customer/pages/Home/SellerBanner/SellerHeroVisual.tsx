// SellerHeroVisual.tsx
// Home page ke "Grow your business with SynCart" section ka LEFT side visual.
// Photo ki jagah ek live-jaisa "Seller Studio" dashboard — arch frame, earnings chart,
// floating order / payout cards. Koi bahar ki image nahi, isliye kabhi tootega nahi.

const css = `
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,500;0,600;0,700;1,500;1,600&display=swap');

.sv-wrap{position:relative;width:100%;max-width:840px;height:530px;font-family:inherit}
.sv-arch{position:absolute;left:0;top:0;bottom:44px;width:80%;border-radius:420px 420px 22px 22px;overflow:hidden;
  background:radial-gradient(120% 80% at 30% 0%,#2f3a33 0%,#1c231f 55%,#151a17 100%);
  box-shadow:0 40px 80px -30px rgba(42,29,23,.45)}
.sv-arch:before{content:"";position:absolute;inset:0;
  background-image:repeating-linear-gradient(115deg,rgba(255,255,255,.035) 0 1px,transparent 1px 22px)}
.sv-glow{position:absolute;width:340px;height:340px;border-radius:50%;left:50%;top:-90px;transform:translateX(-50%);
  background:radial-gradient(circle,rgba(201,155,92,.35),transparent 65%);filter:blur(10px)}
.sv-water{position:absolute;right:-10px;bottom:-40px;font-family:'Playfair Display',serif;font-style:italic;
  font-size:190px;line-height:1;color:rgba(255,255,255,.04);pointer-events:none}

.sv-dash{position:absolute;left:50%;top:116px;transform:translateX(-50%);width:78%;
  background:#fbf8f4;border-radius:22px;padding:22px 24px 18px;box-shadow:0 30px 60px -20px rgba(0,0,0,.55)}
.sv-dhead{display:flex;align-items:center;justify-content:space-between}
.sv-brand{display:flex;align-items:center;gap:8px;font-size:12px;font-weight:600;color:#2a1d17}
.sv-logo{width:24px;height:24px;border-radius:7px;background:#1c231f;color:#c99b5c;display:grid;place-items:center;
  font-family:'Playfair Display',serif;font-style:italic;font-size:13px}
.sv-live{display:flex;align-items:center;gap:6px;font-size:10px;letter-spacing:.14em;text-transform:uppercase;color:#2f7a4f;font-weight:700}
.sv-dot{width:7px;height:7px;border-radius:50%;background:#2f9e5f;box-shadow:0 0 0 0 rgba(47,158,95,.6);animation:sv-ping 1.8s infinite}
.sv-label{margin-top:16px;font-size:10px;letter-spacing:.2em;text-transform:uppercase;color:#8a7a70;font-weight:600}
.sv-amt{display:flex;align-items:baseline;gap:10px;margin-top:4px}
.sv-amt b{font-family:'Playfair Display',serif;font-size:38px;font-weight:600;color:#2a1d17;letter-spacing:-.01em}
.sv-up{font-size:11px;font-weight:700;color:#2f7a4f;background:#e6f2ea;padding:3px 8px;border-radius:999px}
.sv-chart{margin-top:6px;width:100%;height:78px;display:block}
.sv-line{stroke-dasharray:600;stroke-dashoffset:600;animation:sv-draw 2.2s .3s ease-out forwards}
.sv-area{opacity:0;animation:sv-fade 1.2s 1.2s forwards}
.sv-days{display:flex;justify-content:space-between;font-size:9.5px;color:#a3958c;margin-top:2px;letter-spacing:.08em}
.sv-stats{display:grid;grid-template-columns:repeat(3,1fr);margin-top:14px;border-top:1px solid rgba(42,29,23,.1);padding-top:12px}
.sv-stats div{padding-left:10px;border-left:1px solid rgba(42,29,23,.1)}
.sv-stats div:first-child{padding-left:0;border-left:0}
.sv-stats b{display:block;font-family:'Playfair Display',serif;font-size:19px;color:#2a1d17;font-weight:600}
.sv-stats span{font-size:9.5px;letter-spacing:.14em;text-transform:uppercase;color:#8a7a70;font-weight:600}

.sv-card{position:absolute;background:#fff;border-radius:16px;box-shadow:0 22px 45px -18px rgba(42,29,23,.45)}
.sv-order{right:0;top:4px;width:250px;padding:14px;display:flex;gap:12px;align-items:center;animation:sv-float 6s ease-in-out infinite}
.sv-thumb{width:52px;height:60px;border-radius:12px;flex:none;position:relative;overflow:hidden;
  background:linear-gradient(160deg,#e9c9a8,#b4532a)}
.sv-thumb:after{content:"";position:absolute;left:50%;top:10px;width:26px;height:42px;transform:translateX(-50%);
  border-radius:12px 12px 4px 4px;background:rgba(255,255,255,.28)}
.sv-kicker{font-size:9.5px;letter-spacing:.2em;text-transform:uppercase;color:#b4532a;font-weight:700}
.sv-title{font-family:'Playfair Display',serif;font-size:15px;color:#2a1d17;font-weight:600;margin-top:2px}
.sv-meta{display:flex;align-items:center;justify-content:space-between;margin-top:4px;font-size:11px;color:#8a7a70}
.sv-paid{font-size:10px;font-weight:700;color:#2f7a4f;background:#e6f2ea;padding:2px 7px;border-radius:999px}

.sv-payout{left:24px;bottom:0;padding:13px 16px;display:flex;gap:12px;align-items:center;background:#2a1d17;color:#fbf8f4;
  animation:sv-float 7s 1s ease-in-out infinite}
.sv-check{width:34px;height:34px;border-radius:50%;background:#b4532a;display:grid;place-items:center;flex:none}
.sv-payout small{display:block;font-size:9.5px;letter-spacing:.18em;text-transform:uppercase;color:#c99b5c;font-weight:700}
.sv-payout b{font-family:'Playfair Display',serif;font-size:17px;font-weight:600}
.sv-payout em{font-style:normal;font-size:11px;color:rgba(251,248,244,.6);margin-left:6px}

.sv-rating{right:20px;bottom:120px;padding:12px 14px;animation:sv-float 6.5s .5s ease-in-out infinite}
.sv-stars{color:#c99b5c;font-size:13px;letter-spacing:2px}
.sv-rating p{font-size:11px;color:#2a1d17;margin-top:3px;font-weight:600}
.sv-rating span{font-size:10px;color:#8a7a70}

@keyframes sv-draw{to{stroke-dashoffset:0}}
@keyframes sv-fade{to{opacity:1}}
@keyframes sv-float{0%,100%{transform:translateY(0)}50%{transform:translateY(-9px)}}
@keyframes sv-ping{0%{box-shadow:0 0 0 0 rgba(47,158,95,.55)}100%{box-shadow:0 0 0 9px rgba(47,158,95,0)}}

@media (max-width:768px){
  .sv-wrap{height:500px}
  .sv-arch{width:100%}
  .sv-dash{width:88%;top:104px;padding:18px}
  .sv-amt b{font-size:30px}
  .sv-order{top:0;right:-6px;width:220px}
  .sv-rating{display:none}
  .sv-payout{left:8px;bottom:0}
}
@media (prefers-reduced-motion:reduce){
  .sv-order,.sv-payout,.sv-rating,.sv-dot{animation:none}
  .sv-line{animation:none;stroke-dashoffset:0}
  .sv-area{animation:none;opacity:1}
}
`;

const SellerHeroVisual = () => {
  // 7 din ki kamai (chart ke liye)
  const points = [18, 26, 22, 34, 30, 44, 52];
  const W = 400, H = 92, max = 56;
  const xy = points.map((v, i) => [ (i / (points.length - 1)) * W, H - (v / max) * (H - 8) ]);
  const line = xy.map(([x, y], i) => `${i ? "L" : "M"}${x.toFixed(1)},${y.toFixed(1)}`).join(" ");
  const area = `${line} L${W},${H} L0,${H} Z`;
  const [lx, ly] = xy[xy.length - 1];

  return (
    <div className="sv-wrap" aria-label="SynCart seller dashboard preview">
      <style>{css}</style>

      {/* Arch + dashboard */}
      <div className="sv-arch">
        <div className="sv-glow" />
        <div className="sv-water">Sell</div>

        <div className="sv-dash">
          <div className="sv-dhead">
            <div className="sv-brand">
              <span className="sv-logo">S</span> Seller Studio
            </div>
            <div className="sv-live"><span className="sv-dot" /> Live</div>
          </div>

          <p className="sv-label">This week's earnings</p>
          <div className="sv-amt">
            <b>₹48,250</b>
            <span className="sv-up">▲ 18%</span>
          </div>

          <svg className="sv-chart" viewBox={`0 0 ${W} ${H}`} preserveAspectRatio="none">
            <defs>
              <linearGradient id="svFill" x1="0" y1="0" x2="0" y2="1">
                <stop offset="0%" stopColor="#b4532a" stopOpacity="0.28" />
                <stop offset="100%" stopColor="#b4532a" stopOpacity="0" />
              </linearGradient>
            </defs>
            <path className="sv-area" d={area} fill="url(#svFill)" />
            <path className="sv-line" d={line} fill="none" stroke="#b4532a" strokeWidth="2.5"
              strokeLinecap="round" strokeLinejoin="round" vectorEffect="non-scaling-stroke" />
            <circle className="sv-area" cx={lx} cy={ly} r="5" fill="#fbf8f4" stroke="#b4532a" strokeWidth="2.5" />
          </svg>
          <div className="sv-days">
            {["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"].map(d => <span key={d}>{d}</span>)}
          </div>

          <div className="sv-stats">
            <div><b>36</b><span>Orders</span></div>
            <div><b>1.2k</b><span>Visitors</span></div>
            <div><b>4.8★</b><span>Rating</span></div>
          </div>
        </div>
      </div>

      {/* Floating: new order */}
      <div className="sv-card sv-order">
        <div className="sv-thumb" />
        <div style={{ flex: 1, minWidth: 0 }}>
          <p className="sv-kicker">New order</p>
          <p className="sv-title">Cotton Kurta Set</p>
          <div className="sv-meta">
            <span>Jaipur · ₹1,299</span>
            <span className="sv-paid">Paid</span>
          </div>
        </div>
      </div>

      {/* Floating: payout */}
      <div className="sv-card sv-payout">
        <span className="sv-check">
          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="#fbf8f4" strokeWidth="3"
            strokeLinecap="round" strokeLinejoin="round"><path d="M5 12l5 5L20 7" /></svg>
        </span>
        <div>
          <small>Payout sent</small>
          <b>₹12,400</b><em>to your bank</em>
        </div>
      </div>

      {/* Floating: rating */}
      <div className="sv-card sv-rating">
        <div className="sv-stars">★★★★★</div>
        <p>"Loved the fabric!"</p>
        <span>— Ananya, Pune</span>
      </div>
    </div>
  );
};

export default SellerHeroVisual;