import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import BaseUrl from "../URLS/BaseUrl";
import { useAuth } from "../Authrozation/AuthContext";
import "../Styles/GetAllStudents.css";
import "../Styles/StudentDetail.css";
import Logout from "./Logout";

const AdminCompoment = () => {
  const { token } = useAuth();
  const { username } = useParams();

  const [departments, setDepartments] = useState([]);
  const [Students, setStudents] = useState([]);

  const [showDepartments, setShowDepartments] = useState(false);
  const [showStudents, setShowStudents] = useState(false);
  const navigate = useNavigate();
  const GetAllDep = () => {
    BaseUrl.get(`admin/getAllDepartments`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        setDepartments(res.data);
        setShowDepartments(true);
        setShowStudents(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const GetAllStd = () => {
    BaseUrl.get(`admin/getAllStudents`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }).then((res) => {
      setStudents(res.data);
      setShowStudents(true);
      setShowDepartments(false);
    });
  };

  const studentsInDep = (depertamentCode) => {
    BaseUrl.get(`admin/GetStudentsByBranch/${depertamentCode}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        if (res.data.length === 0) {
          alert(`No students in ${depertamentCode}`);
          return setShowDepartments(false);
        }
        setStudents(res.data);
        setShowStudents(true);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <div>
      <div className="admin">
        <h1>welcome, {username}</h1>
        <Logout />
        <div className="attributes">
          <div className="sub-attributes">
            <button onClick={GetAllDep}>Get All Departments</button>
            <button onClick={GetAllStd}>Get All Students</button>
            <button>Add new Department</button>
            <button onClick={() => navigate(`/addStudent`)}>
              Add New Student
            </button>
          </div>
        </div>
      </div>
      {showDepartments && (
        <div className="admin">
          <div className="attributes1">
            <div className="sub-attributes1">
              <h2>Departments</h2>
              <ul>
                {departments.map((dept) => (
                  <li
                    key={dept.id}
                    onClick={() => studentsInDep(dept.depertamentCode)}
                  >
                    <strong>Code:</strong> {dept.depertamentCode} -{" "}
                    <strong>Name:</strong> {dept.depertamentName}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      )}
      {showStudents && (
        <div className="admin">
          <div className="attributes1">
            <div className="sub-attributes1">
              <h2>Students</h2>
              <ul>
                {Students.map((std) => (
                  <li
                    key={std.roll_no}
                    onClick={() => {
                      navigate(`/stdDet/${std.roll_no}`);
                      console.log(std.roll_no);
                    }}
                  >
                    <strong>Name:</strong> {std.name} -{" "}
                    <strong>roll_no:</strong> {std.roll_no}
                    <strong>emailo:</strong> {std.email}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default AdminCompoment;
