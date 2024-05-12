import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

const Adminlog = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const backendUrl = 'http://192.168.56.1:8081'; // Update with your backend URL
  const storeUserId = (userId) => {
    localStorage.setItem('userId', userId);
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`${backendUrl}/admin/connexion`, { username, password });
      if (response.data.login) {
        const userId = response.data.admin.user_id; // Assuming userId is directly under 'admin' object
        storeUserId(userId); // Store user ID in local storage
        console.log(userId);
        navigate("/ecommerce");
      } else {
        alert("Identifiants incorrects");
      }
    } catch (error) {
      console.error("Error during login:", error);
      alert("An error occurred during login.");
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
                    <h5 className="fw-normal mb-3 pb-3" style={{ letterSpacing: "1px" }}>Sign into your account</h5>
                    <form onSubmit={handleLogin}> {/* Added onSubmit handler */}
                      <div data-mdb-input-init className="form-outline mb-4">
                        <input type="text" id="username" className="form-control form-control-lg" value={username} onChange={(e) => setUsername(e.target.value)} />
                        <label className="form-label" htmlFor="username">UserName</label>
                      </div>
                      <div data-mdb-input-init className="form-outline mb-4">
                        <input type="password" id="password" className="form-control form-control-lg" value={password} onChange={(e) => setPassword(e.target.value)} />
                        <label className="form-label" htmlFor="password">Password</label>
                      </div>
                      <div className="pt-1 mb-4">
                        <button type="submit" className="btn btn-dark btn-lg btn-block">Login</button> {/* Added type="submit" */}
                      </div>
                      <div className="pt-1 mb-4">
                        <p className="mb-5 pb-lg-2" style={{ color: "#393f81" }}>Don't have an account?
                          <Link to="/AdminSignUp" style={{ color: "#393f81" }}>Register here</Link>
                        </p>
                      </div>
                    </form>
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

export default Adminlog;

