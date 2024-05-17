import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios"; // Import axios
import { MdOutlineCancel } from "react-icons/md";

import { Button } from ".";
import { useStateContext } from "../contexts/ContextProvider";
import { FaRegUserCircle } from "react-icons/fa";

const UserProfile = () => {
  const { currentColor } = useStateContext();
  const navigate = useNavigate();
  const backendUrl = 'http://192.168.56.1:8081'; // Define your backend URL

  // State variables to store user information
  const [user, setUser] = useState({
    username: "",
    email: ""
  });

  // Function to retrieve user ID from local storage
  const getUserId = () => {
    return localStorage.getItem('userId');
  };

  const fetchData = async () => {
    const userId = getUserId();
    try {
      const response = await axios.get(`${backendUrl}/admin/profile?userId=${userId}`);
      setUser(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };


  // Function to logout user
  const handleLogout = () => {
    // localStorage.removeItem('userId'); 
    navigate("/");
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="nav-item absolute right-1 top-16 bg-white dark:bg-[#42464D] p-8 rounded-lg w-96">
      <div className="flex justify-between items-center">
        <p className="font-semibold text-lg dark:text-gray-200">User Profile</p>
        <Button
          icon={<MdOutlineCancel />}
          color="rgb(153, 171, 180)"
          bgHoverColor="light-gray"
          size="2xl"
          borderRadius="50%"
          opt="userProfile"
        />
      </div>
      <div className="flex gap-5 items-center mt-6 border-color border-b-1 pb-6">
        <div className="text-2xl opacity-0.9 rounded-full p-4 hover:drop-shadow-xl">
          <FaRegUserCircle />
        </div>
        <div>
          <p className="font-semibold text-xl dark:text-gray-200">
            {user.username} {/* Display username */}
          </p>
          <p className="text-gray-500 text-sm dark:text-gray-400">
            {user.email} {/* Display email */}
          </p>
        </div>
      </div>
      <div className="mt-5">
        <Button
          color="white"
          bgColor={currentColor}
          text="Logout"
          borderRadius="10px"
          width="full"
          onClick={handleLogout}
        />
      </div>
    </div>
  );
};

export default UserProfile;
