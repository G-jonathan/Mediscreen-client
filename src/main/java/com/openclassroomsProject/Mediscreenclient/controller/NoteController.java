package com.openclassroomsProject.Mediscreenclient.controller;

import com.openclassroomsProject.Mediscreenclient.beans.NoteBean;
import com.openclassroomsProject.Mediscreenclient.beans.PatientBean;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenNoteProxy;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenPatientProxy;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for managing user interface note related operations.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@Controller
public class NoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);
    private final MediscreenNoteProxy mediscreenNoteProxy;

    @Autowired
    private MediscreenPatientProxy mediscreenPatientProxy;

    /**
     * Constructor for NoteController.
     *
     * @param mediscreenNoteProxy The proxy for accessing note microservice API
     */
    public NoteController(MediscreenNoteProxy mediscreenNoteProxy) {
        this.mediscreenNoteProxy = mediscreenNoteProxy;
    }

    /**
     * Displays the add note page for a specific patient.
     *
     * @param patientId The ID of the patient.
     * @param model     The Model object for passing data to the view.
     * @return The view name for the add-note page.
     */
    @GetMapping("/note/add/{patientId}")
    public String showAddNotePage(@PathVariable int patientId, Model model) {
        LOGGER.info("[CONTROLLER]-> call method : showAddNotePage [PARAM]-> patientId = " + patientId);
        PatientBean patientBean = mediscreenPatientProxy.getPatientBeanById(patientId);
        model.addAttribute("patient", patientBean);
        model.addAttribute("note", new NoteBean());
        return "add-note";
    }

    /**
     * Adds a note for a specific patient.
     *
     * @param patientId The ID of the patient.
     * @param noteBean  The NoteBean containing the note details.
     * @return The redirect URL for displaying the patient's details page.
     */
    @PostMapping("/note/validate/{patientId}")
    public String addNote(@PathVariable("patientId") int patientId, @Valid NoteBean noteBean, BindingResult bindingResult) {
        LOGGER.info("[CONTROLLER]-> call method : addNote [PARAM]-> patientId = " + patientId);
        if (bindingResult.hasErrors()) {
            LOGGER.error("[CONTROLLER]-> addNote() : Validation error ");
            return "add-note";
        }
        noteBean.setPatientId(patientId);
        mediscreenNoteProxy.createNoteBean(noteBean);
        return "redirect:/patient/" + patientId;
    }

    /**
     * Displays the update note form for a specific note.
     *
     * @param noteId The ID of the note.
     * @param model  The Model object for passing data to the view.
     * @return The view name for the update-note page.
     */
    @GetMapping("/note/edit/{noteId}")
    public String showUpdateNoteForm(@PathVariable("noteId") String noteId, Model model) {
        LOGGER.info("[CONTROLLER]-> call method : showUpdateNoteForm [PARAM]-> noteId = " + noteId);
        NoteBean noteBeanToUpdate = mediscreenNoteProxy.getNoteById(noteId);
        PatientBean patientBean = mediscreenPatientProxy.getPatientBeanById(noteBeanToUpdate.getPatientId());
        model.addAttribute("note", noteBeanToUpdate);
        model.addAttribute("patient", patientBean);
        return "update-note";
    }

    /**
     * Updates a note.
     *
     * @param noteId    The ID of the note.
     * @param noteBean  The updated Note.
     * @return The redirect URL for back to the patient's details page.
     */
    @PostMapping("/note/update/{noteId}")
    public String updateNote(@PathVariable("noteId") String noteId, @Valid NoteBean noteBean, BindingResult bindingResult) {
        LOGGER.info("[CONTROLLER]-> call method : updateNote [PARAM]-> noteId = " + noteId + " [PARAM]-> noteBean" + noteBean);
        if (bindingResult.hasErrors()) {
            LOGGER.error("[CONTROLLER]-> updateNote() : Validation error ");
            return "add-note";
        }
        NoteBean noteBeanUpdated =  mediscreenNoteProxy.updateNotBean(noteId, noteBean);
        return "redirect:/patient/" + noteBeanUpdated.getPatientId();
    }

    /**
     * Deletes a note.
     *
     * @param noteId The ID of the note.
     * @return The redirect URL for displaying the patient's details page.
     */
    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(@PathVariable String noteId) {
        LOGGER.info("[CONTROLLER]-> call method : deleteNote [PARAM]-> noteId = " + noteId);
        NoteBean noteBean = mediscreenNoteProxy.getNoteById(noteId);
        mediscreenNoteProxy.deleteNoteBean(noteId);
        return "redirect:/patient/" + noteBean.getPatientId();
    }
}