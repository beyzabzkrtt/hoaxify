import App from '@/App';
import { Activation } from '@/pages/Activation';
import { Home } from '@/pages/Home';
import { SignUp } from '@/pages/Signup';
import { createBrowserRouter } from 'react-router-dom';

export default createBrowserRouter([
  {
    path: "/",
    Component: App,
    children:[
      {
        path: "/", 
        index:true,
        Component: Home
        //errorElement: <div>Unexpected Error</div> yanlis adresler icin eklenebilir
      },
      {
        path: "/signUp",
        Component: SignUp
      },
      {
        path:"/activation/:token",
        Component: Activation
      }
    ]
  }
    
  ])