import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminSignUp = () => {
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const backendUrl = 'http://192.168.56.1:8081';

  const handlesignup = async () => {
    try {
      const response = await axios.post(`${backendUrl}/admin/register`, { email, username, password }); // Use template literal
      if (response.status === 200) {
        console.log("Registration successful");
      } else {
        console.log("Registration failed"); 
      }
    } catch (error) {
      console.error("Error during registration:", error);
    }
  };

  return (
    <section className="vh-100" style={{ backgroundColor: "#c2a0a7" }}>
      <div className="container py-5 h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col col-xl-10">
            <div className="card" style={{ borderRadius: "1rem" }}>
              <div className="row g-0">
                <div className="col-md-6 col-lg-5 d-none d-md-block">
                  <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp" alt="login form" className="img-fluid" style={{ borderRadius: "1rem 0 0 1rem" }} />
                </div>
                <div className="col-md-6 col-lg-7 d-flex align-items-center">
                  <div className="card-body p-4 p-lg-5 text-black">
                    <div className="d-flex align-items-center mb-3 pb-1">
                      <i className="fas fa-cubes fa-2x me-3" style={{ color: "#ccb0b6" }}></i>
                      <span className="h1 fw-bold mb-0">Logo</span>
                    </div>
                    <h5 className="fw-normal mb-3 pb-3" style={{ letterSpacing: "1px" }}>Sign Up your account</h5>
                    <div data-mdb-input-init className="form-outline mb-4">
                      <input type="email" id="email" className="form-control form-control-lg" value={email} onChange={(e) => setEmail(e.target.value)} />
                      <label className="form-label" htmlFor="username">Email address</label>
                    </div>
                    <div data-mdb-input-init className="form-outline mb-4">
                      <input type="email" id="username" className="form-control form-control-lg" value={username} onChange={(e) => setUsername(e.target.value)} />
                      <label className="form-label" htmlFor="username">UserName</label>
                    </div>
                    <div data-mdb-input-init className="form-outline mb-4">
                      <input type="password" id="password" className="form-control form-control-lg" value={password} onChange={(e) => setPassword(e.target.value)} />
                      <label className="form-label" htmlFor="password">Password</label>
                    </div>
                    <div className="pt-1 mb-4">
                      <button className="btn btn-dark btn-lg btn-block" onClick={handlesignup}>SignUp</button>
                    </div>
                    <div className="pt-1 mb-4">
                      <p className="mb-5 pb-lg-2" style={{ color: "#393f81" }}>Don't have an account?
                        <Link to="/" style={{ color: "#393f81" }}>Register here</Link>
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AdminSignUp;
