package com.openclassroomsProject.Mediscreenclient.controller;

import com.openclassroomsProject.Mediscreenclient.beans.NoteBean;
import com.openclassroomsProject.Mediscreenclient.beans.PatientBean;
import com.openclassroomsProject.Mediscreenclient.beans.ReportBean;
import com.openclassroomsProject.Mediscreenclient.beans.ReportRequestBean;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenNoteProxy;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenPatientProxy;
import com.openclassroomsProject.Mediscreenclient.proxies.MediscreenReportProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

/**
 * Controller class for handling report-related requests.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@Controller
public class ReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private MediscreenReportProxy mediscreenReportProxy;

    @Autowired
    private MediscreenPatientProxy mediscreenPatientProxy;

    @Autowired
    private MediscreenNoteProxy mediscreenNoteProxy;

    /**
     * Handles the GET request for the patient report page.
     *
     * @param id    The ID of the patient for which to generate the report.
     * @param model The Spring MVC model to populate the view with data.
     * @return The name of the view template for the patient report page.
     */
    @GetMapping("/report/{id}")
    public String getReportPage(@PathVariable("id") int id, Model model) {
        LOGGER.info("[CONTROLLER]-> call method : getReportPage [PARAM]-> id = " + id);
        List<NoteBean> noteBeanList = mediscreenNoteProxy.getNotesBeanByPatientId(id);
        PatientBean patientBean = mediscreenPatientProxy.getPatientBeanById(id);
        ReportBean reportBean = mediscreenReportProxy.generateReport(new ReportRequestBean(noteBeanList, patientBean));
        model.addAttribute(reportBean);
        return "patient-report";
    }
}