
import { Outlet } from "react-router-dom";

import { LanguageSelector } from "./shared/components/LanguageSelector";

function App() {
  return (
    <>
      <div className="container mt-3" >
        <Outlet />
        <LanguageSelector />
      </div>
    </>
  );
}

export default App;
