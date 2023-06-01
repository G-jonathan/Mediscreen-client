package com.openclassroomsProject.Mediscreenclient.controller;

import com.openclassroomsProject.Mediscreenclient.beans.NoteBean;
import com.openclassroomsProject.Mediscreenclient.beans.PatientBean;
import com.openclassroomsProject.Mediscreenclient.beans.ReportBean;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenNoteProxy;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenPatientProxy;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenReportProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ReportController} class
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediscreenReportProxy mediscreenReportProxy;

    @MockBean
    private MediscreenPatientProxy mediscreenPatientProxy;

    @MockBean
    private MediscreenNoteProxy mediscreenNoteProxy;

    @Test
    public void testGetReportPage() throws Exception {
        int patientId = 1;
        List<NoteBean> noteBeanList = new ArrayList<>();
        PatientBean patientBean = new PatientBean("family", "given", new Date(1000), "F", "address", "1111111111");
        ReportBean reportBean = new ReportBean();
        when(mediscreenNoteProxy.getNotesBeanByPatientId(anyInt())).thenReturn(noteBeanList);
        when(mediscreenPatientProxy.getPatientBeanById(anyInt())).thenReturn(patientBean);
        when(mediscreenReportProxy.generateReport(any())).thenReturn(reportBean);
        mockMvc.perform(MockMvcRequestBuilders.get("/report/{id}", patientId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("reportBean"))
                .andExpect(MockMvcResultMatchers.view().name("patient-report"));
    }
}