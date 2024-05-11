import React, { useState  } from "react";
import { Modal, Button } from 'react-bootstrap';
import axios from 'axios';

const AddProduct = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [image, setImage] = useState('');
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const backendUrl = 'http://192.168.56.1:8081';

  
    const handleAddArticle = async () => {
        try {
          const response = await axios.post(`${backendUrl}/article`, {
            name,
            desc: description, 
            price: parseFloat(price), 
            image, 
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
    <Modal show={show} onHide={handleClose}>
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
            <label htmlFor="article-image" className="col-form-label">Image:</label>
            <input type="text" className="form-control" id="article-image" value={image} onChange={(e) => setImage(e.target.value)} />
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
  );
};

export default AddProduct;
