package com.openclassroomsProject.Mediscreenclient.proxies;

import com.openclassroomsProject.Mediscreenclient.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Feign client interface that allows interaction with the mediscreen-patient microservice.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@FeignClient(name = "mediscreen-patient", url = "${PATIENT_API_URL}")
public interface MediscreenPatientProxy {

    /**
     * Retrieves a list of PatientBean.
     *
     * @return The list of PatientBean.
     */
    @GetMapping(value = "/api/patients")
    List<PatientBean> getPatientBeanList();

    /**
     * Retrieves a PatientBean by ID.
     *
     * @param id The ID of the patient.
     * @return The PatientBean corresponding to the ID.
     */
    @GetMapping(value = "/api/patients/{id}")
    PatientBean getPatientBeanById(@PathVariable("id") int id);

    /**
     * Adds a new PatientBean.
     *
     * @param patientBean The PatientBean to add.
     * @return The added PatientBean.
     */
    @PostMapping(value = "/api/patients")
    PatientBean addPatientBean(@RequestBody PatientBean patientBean);

    /**
     * Updates an existing PatientBean.
     *
     * @param newPatientBean The new PatientBean.
     * @param id             The ID of the patient to update.
     * @return The updated PatientBean.
     */
    @PutMapping("/api/patients/{id}")
    PatientBean updatePatientBean(@RequestBody PatientBean newPatientBean, @PathVariable int id);

    /**
     * Deletes a PatientBean by ID.
     *
     * @param id The ID of the patient to delete.
     */
    @DeleteMapping("/api/patients/{id}")
    void deletePatientBean(@PathVariable int id);
}