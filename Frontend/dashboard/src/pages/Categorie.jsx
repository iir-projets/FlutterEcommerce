import React, { useState} from "react";
import { IoIosAdd } from "react-icons/io";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import {
  GridComponent,
  ColumnsDirective,
  ColumnDirective,
  Page,
  Sort,
  Search,
  Inject,
  Toolbar,
} from "@syncfusion/ej2-react-grids";
import { FiSettings } from "react-icons/fi";
import { TooltipComponent } from "@syncfusion/ej2-react-popups";
import { Navbar, Footer, Sidebar} from "../components";
import { useStateContext } from "../contexts/ContextProvider";
import { MdDelete } from "react-icons/md";
import axios from "axios";
import { employeesData, employeesGrid } from "../data/dummy";
import { Header } from "../components";
const Categorie = () => {
  const { activeMenu, setThemeSettings, currentColor} = useStateContext();
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
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
  return (
    <>
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
        <div className="flex m-3 overflow-x-auto">
    <Modal
      show={show}
      onHide={handleClose}
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title>Add a New Category</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form>
          <div className="mb-3">
            <label htmlFor="category-name" className="col-form-label">Category Name:</label>
            <input type="text" className="form-control" id="category-name" />
          </div>
        </form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" onClick={handleClose}>
          ADD
        </Button>
      </Modal.Footer>
    </Modal>

    {/* Add category button */}
    <div className="flex m-3 justify-center items-center">
      <div className="bg-white dark:text-gray-200 dark:bg-secondary-dark-bg md:w-56 p-4 pt-9 rounded-2xl">
        <p className="text-lg font-semibold mb-3">Add a new category</p>
        <button
          type="button"
          className="text-2xl opacity-0.9 rounded-full p-4 hover:drop-shadow-xl"
          onClick={handleShow}
          style={{ backgroundColor: "beige" }}
        >
          <IoIosAdd />
        </button>
      </div>
    </div>
  </div>
        <div className="m-2 mt-24 md:m-10 p-2 md:p-10 bg-white rounded-3xl">
          <Header category="Page" title="Categories" />
          <GridComponent
            dataSource={employeesData}
            allowPaging
            allowSorting
            toolbar={["Search"]}
            width="auto"
          >
            <ColumnsDirective>
              {employeesGrid.map((item, index) => (
                <ColumnDirective key={index} {...item} />
              ))}
             <ColumnDirective headerText="delete" width="150" template={(props) => {
                return (
                  <div>
                    <button type="button" className="btn btn-danger" onClick={() => handleDelete(props)}> <MdDelete /> </button>
                  </div>
                );
              }} />
            </ColumnsDirective>
            <Inject services={[Page, Search, Toolbar, Sort]} />
          </GridComponent>
        </div>
        <Footer />
      </div>
    </div>
    </>
  );
};

export default Categorie;
