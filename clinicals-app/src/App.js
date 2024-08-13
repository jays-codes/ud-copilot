import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Link, BrowserRouter, Routes } from 'react-router-dom'; 
import AddClinicals from './components/AddClinicals';
import AddPatient from './components/AddPatient';
import Home from './components/Home';
import { Route } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/addPatient" component={AddPatient} />
          <Route path="/addClinicals/:patientId" component={AddClinicals} />
          <Route path="/" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
