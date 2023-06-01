package com.openclassroomsProject.Mediscreenclient.proxies;

import com.openclassroomsProject.Mediscreenclient.beans.ReportBean;
import com.openclassroomsProject.Mediscreenclient.beans.ReportRequestBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client interface for interacting with the "mediscreen-report" service.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@FeignClient(name = "mediscreen-report", url = "localhost:8083")
public interface MediscreenReportProxy {

    /**
     * Generates a report by making a POST request to the "/report" endpoint.
     *
     * @param reportRequest The report request object containing the necessary data.
     * @return The generated report as a ReportBean.
     */
    @PostMapping("/report")
    ReportBean generateReport(@RequestBody ReportRequestBean reportRequest);
}