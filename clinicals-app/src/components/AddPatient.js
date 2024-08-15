import React, { useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

const AddPatient = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [age, setAge] = useState('');

  const handleFirstNameChange = (event) => {
    setFirstName(event.target.value);
  };

  const handleLastNameChange = (event) => {
    setLastName(event.target.value);
  };

  const handleAgeChange = (event) => {
    setAge(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/clinicalservices/patients', {
        "firstName":firstName,
        "lastName":lastName,
        "age":age
      });

      toast.success('Patient saved successfully');
      console.log('Patient saved:', response.data);
      // Add any additional logic or UI updates here
    } catch (error) {
      console.error('Error saving patient:', error);
      // Handle error and display appropriate message to the user
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h1>Add Patient</h1>
      <label>
        First Name:
        <input type="text" value={firstName} onChange={handleFirstNameChange} />
      </label>
      <br />
      <label>
        Last Name:
        <input type="text" value={lastName} onChange={handleLastNameChange} />
      </label>
      <br />
      <label>
        Age:
        <input type="number" value={age} onChange={handleAgeChange} />
      </label>
      <br />
      <button type="submit">Save Patient</button>
    </form>
  );
};

export default AddPatient;