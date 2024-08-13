import React, { useEffect, useState } from 'react';
import axios from 'axios';

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
      <h1>Home</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
          </tr>
        </thead>
        <tbody>
          {patients.map(patient => (
            <tr key={patient.id}>
              <td>{patient.id}</td>
              <td>{patient.firstName}</td>
              <td>{patient.lastName}</td>
              <td>{patient.age}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Home;
