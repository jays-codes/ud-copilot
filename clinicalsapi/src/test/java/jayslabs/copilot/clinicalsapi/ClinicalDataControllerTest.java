package jayslabs.copilot.clinicalsapi;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jayslabs.copilot.clinicalsapi.controller.ClinicalDataController;
import jayslabs.copilot.clinicalsapi.dto.ClinicalDataDTO;
import jayslabs.copilot.clinicalsapi.service.IClinicalDataService;

@WebMvcTest(ClinicalDataController.class)
public class ClinicalDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClinicalDataService clinicalDataService;

    private ClinicalDataDTO clinicalDataDTO;

    @BeforeEach
    public void setUp() {
        clinicalDataDTO = new ClinicalDataDTO();
        clinicalDataDTO.setId(1L);
        clinicalDataDTO.setComponentName("Blood Pressure");
        clinicalDataDTO.setComponentValue("120/80");
        clinicalDataDTO.setMeasuredDateTime(new Timestamp(System.currentTimeMillis()));
        clinicalDataDTO.setPatientId(1L);
    }

    @Test
    public void testCreateClinicalData() throws Exception {
        when(clinicalDataService.createClinicalData(any(ClinicalDataDTO.class))).thenReturn(clinicalDataDTO);

        mockMvc.perform(post("/clinicaldata")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"componentName\": \"Blood Pressure\", \"componentValue\": \"120/80\", \"measuredDateTime\": \"2023-10-10T10:00:00.000+00:00\", \"patientId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.componentName").value("Blood Pressure"))
                .andExpect(jsonPath("$.componentValue").value("120/80"))
                .andExpect(jsonPath("$.patientId").value(1L));

        verify(clinicalDataService, times(1)).createClinicalData(any(ClinicalDataDTO.class));
    }

    @Test
    public void testGetClinicalDataById() throws Exception {
        when(clinicalDataService.getClinicalDataById(1L)).thenReturn(clinicalDataDTO);

        mockMvc.perform(get("/clinicaldata/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.componentName").value("Blood Pressure"))
                .andExpect(jsonPath("$.componentValue").value("120/80"))
                .andExpect(jsonPath("$.patientId").value(1L));

        verify(clinicalDataService, times(1)).getClinicalDataById(1L);
    }

    @Test
    public void testGetAllClinicalData() throws Exception {
        List<ClinicalDataDTO> clinicalDataList = Arrays.asList(clinicalDataDTO);
        when(clinicalDataService.getAllClinicalData()).thenReturn(clinicalDataList);

        mockMvc.perform(get("/clinicaldata")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].componentName").value("Blood Pressure"))
                .andExpect(jsonPath("$[0].componentValue").value("120/80"))
                .andExpect(jsonPath("$[0].patientId").value(1L));

        verify(clinicalDataService, times(1)).getAllClinicalData();
    }

    @Test
    public void testUpdateClinicalData() throws Exception {
        when(clinicalDataService.updateClinicalData(any(ClinicalDataDTO.class))).thenReturn(true);

        mockMvc.perform(put("/clinicaldata")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"componentName\": \"Blood Pressure\", \"componentValue\": \"130/85\", \"measuredDateTime\": \"2023-10-10T10:00:00.000+00:00\", \"patientId\": 1}"))
                .andExpect(status().isOk());

        verify(clinicalDataService, times(1)).updateClinicalData(any(ClinicalDataDTO.class));
    }

    @Test
    public void testDeleteClinicalData() throws Exception {
        doNothing().when(clinicalDataService).deleteClinicalData(1L);

        mockMvc.perform(delete("/clinicaldata/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(clinicalDataService, times(1)).deleteClinicalData(1L);
    }
}