import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import { AuthProvider } from "./Authrozation/AuthContext";
import Login from "./Compoments/Login";
import StudentDetail from "./Compoments/StudentDetail";
import IsAuthrozie from "./Authrozation/IsAuthrozie";
import AdminCompoment from "./Compoments/AdminCompoment";
import AddStudent from "./Compoments/AddStudent";

function App() {
  return (
    <div>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Login />} />
            <Route
              path="welcome/:username"
              element={
                <IsAuthrozie>
                  <AdminCompoment />
                </IsAuthrozie>
              }
            />
            <Route
              path="/stdDet/:roll_no?"
              element={
                <IsAuthrozie>
                  <StudentDetail />
                </IsAuthrozie>
              }
            />
            <Route
              path="/addStudent"
              element={
                <IsAuthrozie>
                  <AddStudent />
                </IsAuthrozie>
              }
            />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;
