package jayslabs.copilot.clinicalsapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import jayslabs.copilot.clinicalsapi.controller.PatientController;
import jayslabs.copilot.clinicalsapi.service.impl.PatientServiceImpl;
import jayslabs.copilot.clinicalsapi.dto.PatientDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientService;

    private PatientDTO patientDTO;

    @BeforeEach
    public void setUp() {
        patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setFirstName("John");
        patientDTO.setLastName("Doe");
        patientDTO.setAge(30);
        patientDTO.setClinicalDataIds(Arrays.asList(1L, 2L));
    }

    @Test
    public void testGetAllPatients() throws Exception {
        List<PatientDTO> patientDTOList = Arrays.asList(patientDTO);
        when(patientService.getAllPatients()).thenReturn(patientDTOList);

        mockMvc.perform(get("/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].clinicalDataIds").isArray());

        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    public void testGetPatientById() throws Exception {
        when(patientService.getPatientById(1L)).thenReturn(patientDTO);

        mockMvc.perform(get("/patients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.clinicalDataIds").isArray());

        verify(patientService, times(1)).getPatientById(1L);
    }

    @Test
    public void testCreatePatient() throws Exception {
        when(patientService.createPatient(any(PatientDTO.class))).thenReturn(patientDTO);

        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"age\": 30, \"clinicalDataIds\": [1, 2]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.clinicalDataIds").isArray());

        verify(patientService, times(1)).createPatient(any(PatientDTO.class));
    }
}