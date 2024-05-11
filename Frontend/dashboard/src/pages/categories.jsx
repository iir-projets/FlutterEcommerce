import React, { useState  } from "react";
import { IoIosAdd } from "react-icons/io";
import { earningData } from "../data/dummy";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

const Categories = () => {
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

  return (
    <div className="flex m-3 overflow-x-auto">
      {/* Modal */}

      <Modal 
       show={show}
       onHide={handleClose}
    //    size="lg"
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
      
      {/* Category items */}
      <div className="flex m-3 flex-wrap justify-center gap-1 items-center">
        {earningData.map((item) => (
          <div
            key={item.title}
            className="bg-white dark:text-gray-200 dark:bg-secondary-dark-bg md:w-56 p-4 pt-9 rounded-2xl"
          >
            <button
              type="button"
              style={{ color: item.color, backgroundColor: item.iconBg }}
              className="text-2xl opacity-0.9 rounded-full p-4 hover:drop-shadow-xl"
            >
              {item.icon}
            </button>
            <p className="mt-3">
              <span className="text-lg font-semibold">{item.amount}</span>
              <span className={`text-sm text-${item.pcColor} ml-2`}>
                {item.percentage}
              </span>
            </p>
            <p className="text-sm text-gray-400 mt-1">{item.title}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Categories;
