import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const AddClinicals = () => {
  const { patientId } = useParams();
  const [patient, setPatient] = useState(null);

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
  }, [patientId]);

  return (
    <div>
      {patient ? (
        <div>
          <h2>{patient.firstName} {patient.lastName}</h2>
          <p>Age: {patient.age}</p>
        </div>
      ) : (
        <p>Loading patient details...</p>
      )}
    </div>
  );
};

export default AddClinicals;