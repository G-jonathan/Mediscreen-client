package com.openclassroomsProject.Mediscreenclient.controller;

import com.openclassroomsProject.Mediscreenclient.beans.NoteBean;
import com.openclassroomsProject.Mediscreenclient.beans.PatientBean;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenNoteProxy;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenPatientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.sql.Date;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link NoteController} class
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediscreenNoteProxy mediscreenNoteProxy;

    @MockBean
    private MediscreenPatientProxy mediscreenPatientProxy;

    @Test
    public void testShowAddNotePage() throws Exception {
        int patientId = 1;
        PatientBean patientBean = new PatientBean("family", "given", new Date(1000), "F", "address", "1111111111");
        when(mediscreenPatientProxy.getPatientBeanById(anyInt())).thenReturn(patientBean);
        mockMvc.perform(MockMvcRequestBuilders.get("/note/add/{patientId}", patientId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("patient"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("note"))
                .andExpect(MockMvcResultMatchers.view().name("add-note"));
    }

    @Test
    public void testAddNote_whenNoteIsValid_thenReturnCorrectRedirect() throws Exception {
        int patientId = 1;
        NoteBean noteBean = new NoteBean();
        noteBean.setPatientId(1);
        noteBean.setComment("test comment");
        mockMvc.perform(MockMvcRequestBuilders.post("/note/validate/{patientId}", patientId)
                        .flashAttr("noteBean", noteBean))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patient/1"));
    }

    @Test
    public void testShowUpdateNoteForm() throws Exception {
        String noteId = "123";
        NoteBean noteBean = new NoteBean();
        noteBean.setPatientId(1);
        noteBean.setComment("test comment");
        PatientBean patientBean = new PatientBean("family", "given", new Date(1000), "F", "address", "1111111111");
        when(mediscreenNoteProxy.getNoteById(anyString())).thenReturn(noteBean);
        when(mediscreenPatientProxy.getPatientBeanById(anyInt())).thenReturn(patientBean);
        mockMvc.perform(MockMvcRequestBuilders.get("/note/edit/{noteId}", noteId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("note"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("patient"))
                .andExpect(MockMvcResultMatchers.view().name("update-note"));
    }

    @Test
    public void testUpdateNote() throws Exception {
        String noteId = "123";
        NoteBean noteBean = new NoteBean();
        noteBean.setPatientId(1);
        noteBean.setComment("test comment");
        NoteBean updatedNoteBean = new NoteBean();
        when(mediscreenNoteProxy.updateNotBean(anyString(), any())).thenReturn(updatedNoteBean);
        mockMvc.perform(MockMvcRequestBuilders.post("/note/update/{noteId}", noteId)
                        .flashAttr("noteBean", noteBean))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patient/" + updatedNoteBean.getPatientId()));
    }

    @Test
    public void testDeleteNote() throws Exception {
        String noteId = "123";
        NoteBean noteBean = new NoteBean();
        noteBean.setPatientId(1);
        noteBean.setComment("test comment");
        when(mediscreenNoteProxy.getNoteById(anyString())).thenReturn(noteBean);
        mockMvc.perform(MockMvcRequestBuilders.get("/note/delete/{noteId}", noteId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patient/" + noteBean.getPatientId()));
    }
}