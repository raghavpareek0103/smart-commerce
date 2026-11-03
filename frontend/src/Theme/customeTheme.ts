import { createTheme } from "@mui/material";

// Poori site ka rang — cream, espresso brown, terracotta, gold (home page jaisa)
const customeTheme = createTheme({
  palette: {
    mode: "light",
    primary: { main: "#2a1d17", dark: "#1a120e", light: "#f3ebe3", contrastText: "#f6f1ea" },
    secondary: { main: "#b4532a", dark: "#8f4021", light: "#f3ebe3", contrastText: "#ffffff" },
    error: { main: "#c2410c" },
    success: { main: "#5b7a5a" },
    text: { primary: "#2a1d17", secondary: "rgba(42,29,23,0.6)" },
    divider: "rgba(42,29,23,0.12)",
    background: { default: "#fbf8f4", paper: "#ffffff" },
  },
  shape: { borderRadius: 14 },
  typography: {
    fontFamily: '"Poppins", "Inter", system-ui, sans-serif',
    h1: { fontFamily: '"Playfair Display", Georgia, serif', fontWeight: 600 },
    h2: { fontFamily: '"Playfair Display", Georgia, serif', fontWeight: 600 },
    h3: { fontFamily: '"Playfair Display", Georgia, serif', fontWeight: 600 },
    h4: { fontFamily: '"Playfair Display", Georgia, serif', fontWeight: 600 },
    h5: { fontFamily: '"Playfair Display", Georgia, serif', fontWeight: 600 },
    button: { textTransform: "none", fontWeight: 600, letterSpacing: "0.02em" },
  },
  components: {
    MuiButton: {
      defaultProps: { disableElevation: true },
      styleOverrides: {
        root: { borderRadius: 999, paddingInline: 22, transition: "background-color .25s ease, box-shadow .25s ease, border-color .25s ease" },
        sizeLarge: { paddingBlock: 12, fontSize: 15 },
        containedPrimary: {
          "&:hover": { backgroundColor: "#b4532a", boxShadow: "0 14px 30px -12px rgba(180,83,42,0.6)" },
        },
        outlined: {
          borderColor: "rgba(42,29,23,0.25)",
          color: "#2a1d17",
          "&:hover": { borderColor: "#2a1d17", background: "rgba(42,29,23,0.04)" },
        },
        text: { color: "#b4532a" },
      },
    },
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          borderRadius: 14,
          backgroundColor: "#ffffff",
          "& .MuiOutlinedInput-notchedOutline": { borderColor: "rgba(42,29,23,0.18)" },
          "&:hover .MuiOutlinedInput-notchedOutline": { borderColor: "rgba(42,29,23,0.4)" },
          "&.Mui-focused .MuiOutlinedInput-notchedOutline": { borderColor: "#b4532a", borderWidth: 1.5 },
        },
      },
    },
    MuiInputLabel: { styleOverrides: { root: { "&.Mui-focused": { color: "#b4532a" } } } },
    MuiCheckbox: { styleOverrides: { root: { "&.Mui-checked": { color: "#b4532a" } } } },
    MuiRadio: { styleOverrides: { root: { "&.Mui-checked": { color: "#b4532a" } } } },
    MuiStepIcon: {
      styleOverrides: {
        root: { color: "rgba(42,29,23,0.15)", "&.Mui-active": { color: "#b4532a" }, "&.Mui-completed": { color: "#2a1d17" } },
      },
    },
    MuiChip: { styleOverrides: { root: { borderRadius: 999 } } },
    MuiPaper: { styleOverrides: { rounded: { borderRadius: 16 } } },
    MuiTooltip: { styleOverrides: { tooltip: { backgroundColor: "#2a1d17", fontSize: 12 } } },
    MuiCircularProgress: { defaultProps: { color: "secondary" } },
  },
});

export default customeTheme;