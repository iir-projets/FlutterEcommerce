import React from "react";
import { Routes, Route } from "react-router-dom";
import { Ecommerce, Orders, Categories,AddProduct,
  //  Calendar,
    Employees,Products,AdminSignUp,
    // Stacked, Pyramid,
     Customers,
      // Kanban, Area, Bar, Pie, Line, Financial, ColorPicker, ColorMapping, Editor,
       Adminlog } from "./pages";
import App from './App'
const AppRoutes = () => {
  return (
    <Routes>
      {/* Dashboard */}
      <Route path="/" element={<Adminlog />} />
      <Route path="/ecommerce" element={<Ecommerce />} />
      <Route path="/app" element={<App />} />
      {/* Pages */}
      <Route path="/orders" element={<Orders />} />
      <Route path="/employees" element={<Employees />} />
      <Route path="/customers" element={<Customers />} />
      <Route path="/Products" element={<Products />} />
      <Route path="/categories" element={<Categories /> }/>
      <Route path="/AddProduct" element={<AddProduct /> }/>
      <Route path="/AdminSignUp" element={<AdminSignUp /> }/>

      {/* Apps */}
      {/* <Route path="/kanban" element={<Kanban />} />
      <Route path="/editor" element={<Editor />} />
      <Route path="/calendar" element={<Calendar />} />
      <Route path="/color-picker" element={<ColorPicker />} /> */}

      {/* Charts */}
      {/* <Route path="/line" element={<Line />} />
      <Route path="/area" element={<Area />} />
      <Route path="/bar" element={<Bar />} />
      <Route path="/pie" element={<Pie />} />
      <Route path="/financial" element={<Financial />} />
      <Route path="/color-mapping" element={<ColorMapping />} />
      <Route path="/pyramid" element={<Pyramid />} />
      <Route path="/stacked" element={<Stacked />} /> */}
    </Routes>
  );
};

export default AppRoutes;
