
import { Outlet } from "react-router-dom";

import { LanguageSelector } from "./shared/components/LanguageSelector";

function App() {
  return (
    // eslint-disable-next-line react/jsx-no-undef
    <AuthenticationContext>
      <div className="container mt-3" >
        <Outlet />
        <LanguageSelector />
      </div>
      </AuthenticationContext>
  );
}

export default App;
