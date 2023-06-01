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
import java.util.List;

/**
 * Controller for managing user interface patient related operations.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@Controller
public class PatientController {
    private final MediscreenPatientProxy patientProxy;
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private MediscreenNoteProxy mediscreenNoteProxy;

    /**
     * Constructor for PatientController.
     *
     * @param patientProxy The proxy for accessing patient microservice API
     */
    public PatientController(MediscreenPatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    /**
     * Retrieves a list of patients and adds it to the model.
     *
     * @param model The model object to add the patient list to.
     * @return The view name for displaying the patient list.
     */
    @GetMapping("/patient")
    public String getPatientList(Model model) {
        LOGGER.info("[CONTROLLER]-> call method : getPatientList");
        List<PatientBean> patientBeanList = patientProxy.getPatientBeanList();
        model.addAttribute("patientList", patientBeanList);
        return "patient-list";
    }

    /**
     * Retrieves patient information by ID and adds it to the model.
     *
     * @param id    The ID of the patient to retrieve.
     * @param model The model object to add the patient information to.
     * @return The view name for displaying the patient details.
     */
    @GetMapping("/patient/{id}")
    public String getPatientInfoById(@PathVariable("id") int id, Model model) {
        LOGGER.info("[CONTROLLER]-> call method : getPatientInfoById [PARAM]-> id = " + id);
        PatientBean patientBean = patientProxy.getPatientBeanById(id);
        List<NoteBean> patientNoteBeanList = mediscreenNoteProxy.getNotesBeanByPatientId(id);
        model.addAttribute("patient", patientBean);
        model.addAttribute("patientNotes", patientNoteBeanList);
        return "patient-details";
    }

    /**
     * Displays the add patient form.
     *
     * @return The view name for displaying the add patient form.
     */
    @GetMapping("/patient/add")
    public String showAddUserPage(PatientBean patientBean) {
        LOGGER.info("[CONTROLLER]-> call method : showAddUserPage");
        return "add-patient";
    }

    /**
     * Adds a new patient to the database.
     *
     * @param newPatient    The PatientBean object containing the new patient.
     * @param bindingResult The binding result object for validation errors.
     * @return The view name for redirecting after adding the patient.
     */
    @PostMapping("/patient/validate")
    public String addPatient(@Valid PatientBean newPatient, BindingResult bindingResult) {
        LOGGER.info("[CONTROLLER]-> call method : addPatient [PARAM]-> PatientBean = " + newPatient);
        if (bindingResult.hasErrors()) {
            LOGGER.error("[CONTROLLER]-> addPatient() : Validation error " + newPatient);
            return "add-patient";
        }
        PatientBean patientSave = patientProxy.addPatientBean(newPatient);
        return "redirect:/patient/" + patientSave.getId();
    }

    /**
     * Displays the update patient form.
     *
     * @param id    the ID of the patient to update.
     * @param model The model object to add the patient information to.
     * @return The view name for displaying the update patient form.
     */
    @GetMapping("/patient/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        LOGGER.info("[CONTROLLER]-> call method : showUpdateForm [PARAM]-> id = " + id);
        PatientBean patient = patientProxy.getPatientBeanById(id);
        model.addAttribute("patient", patient);
        return "update-patient";
    }

    /**
     * Updates an existing patient in the database.
     *
     * @param id            The ID of the patient to update.
     * @param patient       The PatientBean object containing the updated patient data.
     * @param bindingResult The binding result object for validation errors.
     * @return The view name for redirecting after updating the patient.
     */
    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") int id, @Valid PatientBean patient, BindingResult bindingResult) {
        LOGGER.info("[CONTROLLER]-> call method : updatePatient [PARAM]-> id = " + id + " [PARAM]-> patient = " + patient);
        if (bindingResult.hasErrors()) {
            LOGGER.error("[CONTROLLER]-> updatePatient() : Validation error " + patient);
            patient.setId(id);
            return "update-patient";
        }
        patientProxy.updatePatientBean(patient, id);
        return "redirect:/patient/" + id;
    }

    /**
     * Deletes a patient from the database.
     *
     * @param id The ID of the patient to delete.
     * @return The view name for redirecting after deleting the patient.
     */
    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        LOGGER.info("[CONTROLLER]-> call method : deletePatient");
        patientProxy.deletePatientBean(id);
        return "redirect:/";
    }
}