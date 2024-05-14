import React, { useState, useEffect } from "react";
import { IoIosAdd } from "react-icons/io";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { GridComponent, ColumnsDirective, ColumnDirective, Page, Sort, Search, Inject, Toolbar } from "@syncfusion/ej2-react-grids";
import { FiSettings } from "react-icons/fi";
import { TooltipComponent } from "@syncfusion/ej2-react-popups";
import { Navbar, Footer, Sidebar } from "../components";
import { Header } from "../components";
import axios from "axios";
import { MdDelete } from "react-icons/md";
import { useStateContext } from "../contexts/ContextProvider";

const Categorie = () => {
  const { activeMenu, setThemeSettings, currentColor } = useStateContext();
  const [show, setShow] = useState(false);
  const [categories, setCategories] = useState([]);
  const [catNom, setCatNom] = useState('');
  const [image, setImage] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://192.168.56.1:8081/categorie');
        setCategories(response.data.categories);
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };
    fetchData();
  }, []);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleAddCategory = async () => {
    const formData = new FormData();
    formData.append('catNom', catNom);
    if (image) {
      formData.append('image', image);
    }

    try {
      await axios.post('http://192.168.56.1:8081/ajouterCategorie', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      handleClose(); // Close modal after submitting
      setCatNom(''); // Clear input after submitting
      setImage(null); // Clear file input after submitting
      // Optionally refresh categories list here
    } catch (error) {
      console.error('Error adding category:', error.response ? error.response.data : "Network Error");
    }
  };

  const handleDelete = async (catId) => {
    try {
      await axios.delete(`http://192.168.56.1:8081/categorie/${catId}`);
      const updatedCategories = categories.filter(category => category.catId !== catId);
      setCategories(updatedCategories);
    } catch (error) {
      console.error('Error deleting category:', error);
    }
  };

  const handleFileChange = (event) => {
    setImage(event.target.files[0]);
  };

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
        <div className="flex relative dark:bg-main-dark-bg">
          <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
              <Modal.Title>Add a New Category</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <form>
                <div className="mb-3">
                  <label htmlFor="category-name" className="form-label">Category Name:</label>
                  <input type="text" className="form-control" id="category-name" value={catNom} onChange={(e) => setCatNom(e.target.value)} />
                </div>
                <div className="mb-3">
                  <label htmlFor="category-image" className="form-label">Image:</label>
                  <input type="file" className="form-control" id="category-image" onChange={handleFileChange} />
                </div>
              </form>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>Close</Button>
              <Button variant="primary" onClick={handleAddCategory}>Add</Button>
            </Modal.Footer>
          </Modal>

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
            dataSource={categories}
            allowPaging
            allowSorting
            toolbar={["Search"]}
            width="auto"
          >
            <ColumnsDirective>
              <ColumnDirective field="catId" headerText="Category ID" width="150" />
              <ColumnDirective field="catNom" headerText="Category Name" width="150" />
              <ColumnDirective headerText="Delete" width="150" template={(props) => (
                <button type="button" className="btn btn-danger" onClick={() => handleDelete(props.catId)}>
                  <MdDelete />
                </button>
              )} />
            </ColumnsDirective>
            <Inject services={[Page, Search, Toolbar, Sort]} />
          </GridComponent>
        </div>
        <Footer />
      </div>
    </div>
  );
};

export default Categorie;
