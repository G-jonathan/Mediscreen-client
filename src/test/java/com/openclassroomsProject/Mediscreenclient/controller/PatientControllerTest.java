package com.openclassroomsProject.Mediscreenclient.controller;

import com.openclassroomsProject.Mediscreenclient.beans.NoteBean;
import com.openclassroomsProject.Mediscreenclient.beans.PatientBean;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenNoteProxy;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenPatientProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link PatientController} class
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediscreenPatientProxy patientProxy;

    @MockBean
    private MediscreenNoteProxy mediscreenNoteProxy;

    private PatientBean patientBean;
    private NoteBean noteBean;
    private PatientController patientController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        patientController = new PatientController(patientProxy);
        patientBean = new PatientBean("family", "given", new Date(1000), "F", "address", "0000000000");
        noteBean = new NoteBean();
        noteBean.setId("1");
        noteBean.setPatientId(1);
        noteBean.setComment("test note");
        List<NoteBean> noteList = new ArrayList<>();
        noteList.add(noteBean);
        when(patientProxy.getPatientBeanList()).thenReturn(List.of(patientBean));
        when(patientProxy.getPatientBeanById(1)).thenReturn(patientBean);
        when(mediscreenNoteProxy.getNotesBeanByPatientId(1)).thenReturn(noteList);
        when(patientProxy.addPatientBean(Mockito.any())).thenReturn(patientBean);
    }

    @Test
    void getPatientList_shouldReturnPatientListView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patient-list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("patientList"))
                .andExpect(MockMvcResultMatchers.model().attribute("patientList", List.of(patientBean)));
    }

    @Test
    void getPatientInfoById_shouldReturnPatientDetailsView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patient-details"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("patient"))
                .andExpect(MockMvcResultMatchers.model().attribute("patient", patientBean))
                .andExpect(MockMvcResultMatchers.model().attributeExists("patientNotes"))
                .andExpect(MockMvcResultMatchers.model().attribute("patientNotes", List.of(noteBean)));
    }

    @Test
    void testShowAddUserPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-patient"));
    }

    @Test
    void addPatient_shouldReturnRedirectView_whenNotValid() throws Exception {
        patientBean = new PatientBean("family", "given", new Date(1000), "F", "address", "0000000000");
        patientBean.setId(1);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(patientProxy.addPatientBean(patientBean)).thenReturn(patientBean);
        String viewName = patientController.addPatient(patientBean, bindingResult);
        assertEquals("redirect:/patient/1", viewName);
        verify(patientProxy, times(1)).addPatientBean(patientBean);
    }

    @Test
    void addPatient_shouldReturnAddPatient_whenViewIfInvalid() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = patientController.addPatient(patientBean, bindingResult);
        assertEquals("add-patient", viewName);
        verify(patientProxy, never()).addPatientBean(patientBean);
    }

    @Test
    void testShowUpdateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/edit/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("update-patient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("patient"))
                .andExpect(MockMvcResultMatchers.model().attribute("patient", patientBean));
    }

    @Test
    void updatePatient_shouldReturnRedirectView_whenIsValid() {
        int patientId = 1;
        BindingResult bindingResult = mock(BindingResult.class);
        String viewName = patientController.updatePatient(patientId, patientBean, bindingResult);
        assertEquals("redirect:/patient/1", viewName);
        verify(patientProxy, times(1)).updatePatientBean(patientBean, patientId);
    }

    @Test
    void updatePatient_shouldReturnUpdatePatientView_whenIsInvalid() {
        int patientId = 1;
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = patientController.updatePatient(patientId, patientBean, bindingResult);
        assertEquals("update-patient", viewName);
        verify(patientProxy, never()).updatePatientBean(patientBean, patientId);
    }

    @Test
    void testDeletePatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patient"));
    }
}