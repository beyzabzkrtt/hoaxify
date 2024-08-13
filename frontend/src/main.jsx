
import ReactDOM from 'react-dom/client';
//import App from './App.jsx'
import { RouterProvider } from 'react-router-dom';
//import { BrowserRouter, Route, Routes } from 'react-router-dom';
import "./locales";
import router from './router';
import "./styles.scss";




ReactDOM.createRoot(document.getElementById('root')).render(
  
    <RouterProvider router = {router} />
    /* <BrowserRouter>
      <Routes>
        <Route path = "/" element={<div><Home/></div>} />
        <Route path = "/signUp" element={<SignUp/>} />
        </Routes> 
    </BrowserRouter> */
    
  
)
