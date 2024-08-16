# ud-copilot
Jay's proj repo for Github Copilot with Java and SpringBoot training (udemy)
List below ARE NOT NOTES. They are topics covered as of latest.

proj: clinicals-app
- generated code in AddClinicals.js to display patient clinical data in a table
- generated code in AddClinicals.js to create form for clinical data and submit; use axios api call to save
- generated code in Home to add column for links to add clinical data; generated AddClinicals.js component to diplay patient details via axios api call
- generated toastify pop-ups
- generated react component code to display a form and enable saving of patient details; call create patient api via axios; AddPatient.js component
- generated react component code to make api call to axios and render as html table
- generated react components, configured routing in App.js
- created react-app via npx create-react-app; installed dep: react-router-dom, axios, react-toastify

proj: clinicalsapi
- generated code for new getClinicalDataByPatientId; modified repository, service, and controller
- generated CORS fix to allow react app to connect to rest apis
- generated logging code; modified application.yaml to configure logging
- generated error handling code; GlobalExceptionHandler.java, custom exceptions; modified controller to call custom exceptions
- updated generated tests
- generated api tests on Controller, Service
- updated getPatient() service to return clinical data; refactored clinical data retrieval per Patient id
- updated getAllPatients() service to return clinical data info; modified cd repo;
- modified DTOs
- added datasource to application.yml
- generated Controller, exposing REST apis for Patient and Clinical Data
- updated generated Service for Patient and Clinical Data; fixed entity references
- generate Service (interface and impl)
- generate DTO and mapper classes (interface and impl)
- modfied model relationships
- generated models
- fixed packaging error
- demo project using copilot to generate springboot api project

proj: basics
- generated unit tests
- generated review and improve code
- generated Inventory management system puc
- generated code to update tests; add a method
- generated junit tests
- generated Classes; getters/setters, java doc, logging, error handling, concurrency improvements; ran review multiple times
- created test project