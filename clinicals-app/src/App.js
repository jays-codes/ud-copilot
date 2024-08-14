import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Link, Routes, Route } from 'react-router-dom'; 
import AddClinicals from './components/AddClinicals';
import AddPatient from './components/AddPatient';
import Home from './components/Home';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/addPatient" element={<AddPatient />} />
          <Route path="/addClinicals/:patientId" element={<AddClinicals />} />
          <Route path="/" element={<Home />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
