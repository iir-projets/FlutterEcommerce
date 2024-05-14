import React, { useState, useEffect } from "react";
import {
  GridComponent,
  ColumnsDirective,
  ColumnDirective,
  Page,
  Toolbar,
  Selection,
  Inject,
  Edit,
  Sort,
  Filter,
} from "@syncfusion/ej2-react-grids";
import { FiSettings } from "react-icons/fi";
import { TooltipComponent } from "@syncfusion/ej2-react-popups";
import { Navbar, Footer, Sidebar } from "../components";
import { Header } from "../components";
import axios from "axios";
import { useStateContext } from "../contexts/ContextProvider"; // Import useStateContext function
import {customersGrid} from "../data/data";

const Customers = () => {
  const { activeMenu, setThemeSettings, currentColor } = useStateContext(); // Use useStateContext function to access context
  const [customersData, setCustomersData] = useState([]);

  useEffect(() => {
    // Fetch data from the API
    const fetchData = async () => {
      try {
        const response = await axios.get("http://192.168.56.1:8081/user/getUser");
        setCustomersData(response.data); // Set the retrieved data as the customersData
      } catch (error) {
        console.error("Error fetching customer data:", error);
      }
    };

    fetchData(); // Call the fetchData function when the component mounts
  }, []); // Empty dependency array to ensure it runs only once when the component mounts

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
        className={`dark:bg-main-dark-bg bg-main-bg min-h-screen w-full ${
          activeMenu ? "md:ml-72" : "flex-2"
        }`}
      >
        <div className="fixed md:static bg-main-bg dark:bg-main-dark-bg navbar w-full">
          <Navbar />
        </div>
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
          <Header category="Page" title="Customers" />
          <GridComponent
            dataSource={customersData}
            allowPaging
            allowSorting
            toolbar={["Delete"]}
            editSettings={{ allowDeleting: true, allowEditing: true }}
            width="auto"
          >
            <ColumnsDirective>
            {customersGrid.map((item, index) => (
                <ColumnDirective key={index} {...item} />
              ))}
            </ColumnsDirective>
            <Inject services={[Page, Toolbar, Selection, Edit, Sort, Filter]} />
          </GridComponent>
        </div>
        <Footer />
      </div>
    </div>
  );
};

export default Customers;
