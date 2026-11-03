/*import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { Provider } from 'react-redux'
import store from './Redux Toolkit/Store.ts'
import { BrowserRouter } from 'react-router-dom'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>

    </Provider>

  </StrictMode>,
)*/
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { Provider } from 'react-redux'
import store from './Redux Toolkit/Store.ts'
import { BrowserRouter } from 'react-router-dom'

// ---- ADD THIS: log out on every page refresh / new page load ----
// Keep the login only on the payment return page, because the payment
// gateway redirects back with a full page load and needs the token.
// if (!window.location.pathname.startsWith('/payment-success')) {
//   localStorage.removeItem('jwt')
// }
// -----------------------------------------------------------------

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </StrictMode>,
)
