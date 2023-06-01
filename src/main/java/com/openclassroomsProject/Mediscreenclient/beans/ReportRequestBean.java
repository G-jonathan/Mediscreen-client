package com.openclassroomsProject.Mediscreenclient.beans;

import java.util.List;

/**
 * Bean class representing a ReportRequest.
 * Contains a list of notes and information about a patient.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
public class ReportRequestBean {

    private List<NoteBean> noteList;
    private PatientBean patient;

    public ReportRequestBean(List<NoteBean> noteList, PatientBean patient) {
        this.noteList = noteList;
        this.patient = patient;
    }

    public List<NoteBean> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NoteBean> noteList) {
        this.noteList = noteList;
    }

    public PatientBean getPatient() {
        return patient;
    }

    public void setPatient(PatientBean patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "ReportRequestBean{" +
                "noteList=" + noteList +
                ", patient=" + patient +
                '}';
    }
}