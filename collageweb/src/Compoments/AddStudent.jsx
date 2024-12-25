import React, { useState } from "react";
import BaseUrl from "../URLS/BaseUrl";
import { useAuth } from "../Authrozation/AuthContext";
import { useNavigate } from "react-router-dom";

const AddStudent = () => {
  const { token } = useAuth();
  const navagate = useNavigate();
  const [student, setStudent] = useState({
    roll_no: "",
    name: "",
    email: "",
    depertamentCode: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent({
      ...student,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = {
      roll_no: student.roll_no,
      name: student.name,
      email: student.email,
      department: {
        depertamentCode: student.depertamentCode,
      },
    };
    BaseUrl.post(`admin/addStudent`, payload, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        alert("Student added Sucessfully");
        navagate(-1);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <div>
      <h1>Adding New Student</h1>
      <form onSubmit={handleSubmit}>
        <label>Roll_no</label>
        <input
          type="text"
          name="roll_no"
          value={student.roll_no}
          onChange={handleChange}
        />
        <br />

        <label>name</label>
        <input
          type="text"
          name="name"
          value={student.name}
          onChange={handleChange}
        />
        <br />
        <label>email</label>
        <input
          type="text"
          name="email"
          value={student.email}
          onChange={handleChange}
        />
        <br />
        <label>depertamentCode</label>
        <input
          type="text"
          name="depertamentCode"
          value={student.depertamentCode}
          onChange={handleChange}
        />
        <br />
        <button type="submit">Add</button>
      </form>
    </div>
  );
};

export default AddStudent;

// = String name;
// String email;
// name="department_Code" Department department;
