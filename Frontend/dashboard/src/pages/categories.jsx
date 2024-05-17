import React, { useState, useEffect } from "react";
import { IoIosAdd } from "react-icons/io";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios'; // Import Axios
import { MdDelete } from "react-icons/md";
import { FaEdit } from "react-icons/fa";

const Categories = () => {
  const [categories, setCategories] = useState([]); // State to store categories
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    // Fetch categories data when component mounts
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const response = await axios.get("http://192.168.56.1:8081/categorie");
      console.log("Response data:", response.data); 
      setCategories(response.data.categories); 
    } catch (error) {
      console.error("Error fetching categories:", error);
    }
  };

  const handleEdit = (category) => {
    // Handle edit functionality here
    console.log("Edit category:", category);
  };

  const handleDelete = (category) => {
    // Handle delete functionality here
    console.log("Delete category:", category);
  };

  return (
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
      <div className="flex m-3 flex-wrap justify-center gap-1 items-center">
        {categories.map((category) => (
          <div
            key={category.catId}
            className="bg-white dark:text-gray-200 dark:bg-secondary-dark-bg md:w-56 p-4 pt-9 rounded-2xl"
          >
            <p className="text-lg font-semibold">{category.catNom}</p>
            <img src={category.image} alt={category.catNom} className="w-full h-auto" />
            <div>
            <div className="flex m-3 flex-wrap justify-center items-center">
              <button type="button" className="btn btn-info" onClick={() => handleEdit(category)}><FaEdit /></button>
            </div>
            <div>
              <button type="button" className="btn btn-danger" onClick={() => handleDelete(category)}> <MdDelete /> </button>
            </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Categories;
