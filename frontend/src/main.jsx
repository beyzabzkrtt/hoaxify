import React from 'react'
import ReactDOM from 'react-dom/client'
//import App from './App.jsx'
import { SignUp } from './pages/Signup/index.jsx'
import "./styles.scss"

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <SignUp/>
  </React.StrictMode>,
)