import React, { useState, useEffect } from "react";
import { Modal, Button } from 'react-bootstrap';
import axios from 'axios';
import { IoIosAdd } from "react-icons/io";

const AddProduct = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [quantite, setQuantite] = useState('');
  const [categorieId, setCategorieId] = useState('');
  const [image, setImage] = useState(null);
  const [categories, setCategories] = useState([]);
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);
  const backendUrl = 'http://192.168.56.1:8081';

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get(`${backendUrl}/categorie`);
        setCategories(response.data.categories);
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };
    fetchCategories();
  }, []); // Empty dependency array to run effect only once

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setImage(file);
  };

  const handleAddArticle = async () => {
    try {
      const formData = new FormData();
      formData.append('name', name);
      formData.append('description', description);
      formData.append('price', parseFloat(price));
      formData.append('quantite', parseInt(quantite));
      formData.append('categorieId', parseInt(categorieId));

      if (image) {
        formData.append('image', image);
      }

      const response = await axios.post(`${backendUrl}/addArticle`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      if (response.status === 200) {
        console.log('Article added successfully');
      }
      handleClose();
    } catch (error) {
      console.error('Error adding article:', error);
    }
  };

  return (
    <>
      <div className="bg-white dark:text-gray-200 dark:bg-secondary-dark-bg md:w-56 p-4 pt-9 rounded-2xl">
        <p className="text-lg font-semibold mb-3">Add a new article</p>
        <button
          type="button"
          className="text-2xl opacity-0.9 rounded-full p-4 hover:drop-shadow-xl"
          onClick={handleShow}
          style={{ backgroundColor: "beige" }}
        >
          <IoIosAdd />
        </button>
      </div>
      <Modal
        show={show}
        onHide={handleClose}
        aria-labelledby="contained-modal-title-vcenter"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title>Add a New Article</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <form>
            <div className="mb-3">
              <label htmlFor="article-name" className="col-form-label">Name:</label>
              <input type="text" className="form-control" id="article-name" value={name} onChange={(e) => setName(e.target.value)} />
            </div>
            <div className="mb-3">
              <label htmlFor="article-description" className="col-form-label">Description:</label>
              <textarea className="form-control" id="article-description" value={description} onChange={(e) => setDescription(e.target.value)} />
            </div>
            <div className="mb-3">
              <label htmlFor="article-price" className="col-form-label">Price:</label>
              <input type="number" className="form-control" id="article-price" value={price} onChange={(e) => setPrice(e.target.value)} />
            </div>
            <div className="mb-3">
              <label htmlFor="article-quantite" className="col-form-label">Quantite:</label>
              <input type="number" className="form-control" id="article-quantite" value={quantite} onChange={(e) => setQuantite(e.target.value)} />
            </div>
            <div className="mb-3">
              <label htmlFor="article-categorieId" className="col-form-label">Category:</label>
              <select className="form-control" id="article-categorieId" value={categorieId} onChange={(e) => setCategorieId(e.target.value)}>
                <option value="">Select Category</option>
                {categories.map(category => (
                  <option key={category.catId} value={category.catId}>{category.catNom}</option>
                ))}
              </select>
            </div>
            <div className="mb-3">
              <label htmlFor="article-image" className="col-form-label">Image:</label>
              <input accept="image/*" type="file" className="form-control" id="article-image" onChange={handleFileChange} />
            </div>
          </form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleAddArticle}>
            Add
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default AddProduct;