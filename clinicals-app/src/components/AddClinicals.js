import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const AddClinicals = () => {
  const { patientId } = useParams();
  const [patient, setPatient] = useState(null);
  const [clinicalData, setClinicalData] = useState([]);

  useEffect(() => {
    const fetchPatientDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/clinicalservices/patients/${patientId}`);
        setPatient(response.data);
      } catch (error) {
        console.error('Error fetching patient details:', error);
      }
    };

    fetchPatientDetails();

    const fetchClinicalData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/clinicalservices/clinicaldata/patient/${patientId}`);
        setClinicalData(response.data);
      } catch (error) {
        console.error('Error fetching clinical data:', error);
      }
    };
    fetchClinicalData();

  }, [patientId]);


  const [componentName, setComponentName] = useState('');
  const [componentValue, setComponentValue] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/clinicalservices/clinicaldata', {
        "patientId":patientId,
        "componentName":componentName,
        "componentValue":componentValue
      });
      console.log('Data saved successfully:', response.data);
    } catch (error) {
      console.error('Error saving data:', error);
    }
  };

  return (
    <div>
      <h1>Add Clinical Data</h1>

      {patient ? (
        <div>
          <h2>{patient.firstName} {patient.lastName}</h2>
          <p>Age: {patient.age}</p>
        </div>
      ) : (
        <p>Loading patient details...</p>
      )}
      
      
      {clinicalData.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Component Name</th>
              <th>Component Value</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {clinicalData.map((data, index) => (
              <tr key={index}>
                <td>{data.componentName}</td>
                <td>{data.componentValue}</td>
                <td>{new Date(data.measuredDateTime).toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Loading clinical data...</p>
      )}      

      <form onSubmit={handleSubmit}>
        <div>
          <label>Component Name:</label>
          <input
            type="text"
            value={componentName}
            onChange={(e) => setComponentName(e.target.value)}
          />
        </div>
        <div>
          <label>Component Value:</label>
          <input
            type="text"
            value={componentValue}
            onChange={(e) => setComponentValue(e.target.value)}
          />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default AddClinicals;