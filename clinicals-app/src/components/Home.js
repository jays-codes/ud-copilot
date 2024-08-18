import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function Home() {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    // Make the API call to fetch patient details
    axios.get('http://localhost:8080/clinicalservices/patients')
      .then(response => {
        setPatients(response.data);
      })
      .catch(error => {
        console.error('Error fetching patient details:', error);
      });
  }, []);

  return (
    <div>
      <h1>Patients</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Add Clinical Data</th> {/* New column */}
          </tr>
        </thead>
        <tbody>
          {patients.map(patient => (
            <tr key={patient.id}>
              <td>{patient.id}</td>
              <td>{patient.firstName}</td>
              <td>{patient.lastName}</td>
              <td>{patient.age}</td>
              <td>
                <Link to={`/addClinicals/${patient.id}`}>Add Clinical Data</Link> {/* Link to add clinical data */}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/addPatient">Add Patient</Link>
    </div>
  );
}

export default Home;
