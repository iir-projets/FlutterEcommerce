import React, { useState, useEffect } from 'react';
import {
  GridComponent,
  ColumnsDirective,
  ColumnDirective,
  Resize,
  Sort,
  ContextMenu,
  Filter,
  Page,
  Search,
  ExcelExport,
  PdfExport,
  Edit,
  Inject,
} from "@syncfusion/ej2-react-grids";
import { useStateContext } from "../contexts/ContextProvider";
import { FiSettings } from "react-icons/fi";
import { TooltipComponent } from "@syncfusion/ej2-react-popups";
import { MdDelete } from "react-icons/md";
import { FaEdit } from "react-icons/fa";
import axios from 'axios';
import { Navbar, Footer, Sidebar } from "../components";
import AddProduct from "./AddProduct";
// import { Button } from "../components";

import { contextMenuItems, ProductGrid } from "../data/data";
import { Header } from "../components";

const Products = () => {
  const { activeMenu, setThemeSettings, currentColor } = useStateContext();

  const handleEdit = (rowData) => {
    // Implement the edit functionality here
  };

  const handleDelete = async (rowData) => {
    try {
      const response = await axios.delete(`http://192.168.56.1:8081/api/articles/dropArticle/${rowData.id}`);
      console.log(response.data); // Log success message
      // You can perform additional actions after successful deletion
    } catch (error) {
      console.error('Error deleting article:', error);
      // Handle error scenarios
    }
  };

  const [ordersData, setOrdersData] = useState([]);

  useEffect(() => {
    // Function to fetch data from the backend API
    const fetchData = async () => {
      try {
        const response = await fetch('http://192.168.56.1:8081/articles');
        if (response.ok) {
          const data = await response.json();
          setOrdersData(data);
        } else {
          console.error('Failed to fetch data');
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="flex relative dark:bg-main-dark-bg">
      <div className="fixed right-4 bottom-4" style={{ zIndex: "1000" }}>
        <TooltipComponent content="Settings" position="Top">
          <button
            type="button"
            className="text-3xl p-3 hover:drop-shadow-xl hover:bg-light-gray text-white"
            style={{ background: currentColor, borderRadius: "50%" }}
            onClick={() => setThemeSettings(true)}
          >
            <FiSettings />
          </button>
        </TooltipComponent>
      </div>
      {activeMenu ? (
        <div className="w-72 fixed sidebar dark:bg-secondary-dark-bg bg-white ">
          <Sidebar />
        </div>
      ) : (
        <div className="w-0 dark:bg-secondary-dark-bg">
          <Sidebar />
        </div>
      )}
      <div
        className={`dark:bg-main-dark-bg bg-main-bg min-h-screen w-full ${activeMenu ? "md:ml-72" : "flex-2"
          }`}
      >
        <div className="fixed md:static bg-main-bg dark:bg-main-dark-bg navbar w-full">
          <Navbar />
        </div>
        <AddProduct />
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
          <Header category="Page" title="Products" />
          <GridComponent
            id="gridcomp"
            dataSource={ordersData}
            allowPaging
            allowSorting
            allowExcelExport
            allowPdfExport
            toolbar={["Search"]}
            contextMenuItems={contextMenuItems}
            editSettings={{ allowDeleting: true, allowEditing: true }}
          >
            <ColumnsDirective>
              {ProductGrid.map((item, index) => (
                <ColumnDirective key={index} {...item} />
              ))}
              <ColumnDirective headerText="edit" width="150" template={(props) => {
                return (
                  <div>
                    <button type="button" className="btn btn-info" onClick={() => handleEdit(props)}><FaEdit /></button>
                  </div>
                );
              }} />
              <ColumnDirective headerText="delete" width="150" template={(props) => {
                return (
                  <div>
                    <button type="button" className="btn btn-danger" onClick={() => handleDelete(props)}> <MdDelete /> </button>
                  </div>
                );
              }} />
            </ColumnsDirective>
            <Inject
              services={[
                Resize,
                Sort,
                ContextMenu,
                Filter,
                Page,
                Search,
                ExcelExport,
                Edit,
                PdfExport,
              ]}
            />
          </GridComponent>
        </div>
        <Footer />
      </div>
    </div>
  );
};

export default Products;
