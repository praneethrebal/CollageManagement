import React, { useEffect, useState } from "react";
import BaseUrl from "../URLS/BaseUrl";
import { useAuth } from "../Authrozation/AuthContext";
import "../Styles/StudentDetail.css";
import Logout from "./Logout";
import { useParams } from "react-router-dom";

const StudentDetail = () => {
  const [student, SetStudent] = useState(null);
  const [loading, setLoading] = useState(true);
  const [grade, setGrade] = useState();
  const { token } = useAuth();
  const { roll_no } = useParams();
  const queryParam = roll_no ? `?RollNo=${roll_no}` : "";
  useEffect(() => {
    BaseUrl.get(`/user/getStudent${queryParam}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        console.log(res.data);
        SetStudent(res.data);
        setLoading(false);
      })
      .catch((res) => {
        console.log("Geting error here");
        console.log(res);
        setLoading(false);
      });
  }, [token, queryParam]);
  if (loading) {
    return <div>Loading...</div>;
  }

  const showGrade = () => {
    BaseUrl.get(`user/getGrade`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        setGrade(res.data.grade);
        console.log(res);
      })
      .catch((err) => {
        setGrade(`Grade not Added`);
        console.log(err);
      });
  };

  const UpdatePassword = () => {
    const newPassword = prompt("Enter new Password");
    if (newPassword) {
      BaseUrl.put(
        `user/updatePassword`,
        { password: newPassword },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
        .then((res) => {
          alert("password updated sucessfully");
        })
        .catch((err) => {
          alert("Failed to update password");
        });
    }
  };

  return (
    <div className="student-details">
      <Logout />

      <h1 className="heading"> Student Details</h1>
      <div className="details">
        <div className="details-item">
          <label>Name:</label>
          <p>{student.name}</p>
        </div>

        <div className="details-item">
          <label>Email:</label>
          <p>{student.email}</p>
        </div>
        <div className="details-item">
          <label>Roll_no:</label>
          <p>{student.roll_no}</p>
        </div>
        <div className="details-item">
          <label>Department Name:</label>
          <p>{student.departmentName}</p>
        </div>
        <div className="details-item">
          <label>Depertament Code:</label>
          <p>{student.depertamentCode}</p>
        </div>
      </div>

      <div className="details-item">
        {queryParam === "" && (
          <>
            <button onClick={showGrade}>Click to check Grade</button>

            <button onClick={UpdatePassword}>Update Password</button>
          </>
        )}
      </div>
      <div className="details-item" details-item>
        {grade && <p> Grade:{grade}</p>}
      </div>
    </div>
  );
};

export default StudentDetail;
