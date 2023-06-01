package com.openclassroomsProject.Mediscreenclient.beans;

import jakarta.validation.constraints.NotBlank;

/**
 * Bean class representing a Note.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
public class NoteBean {

    private String id;
    private Integer patientId;
    @NotBlank(message = "Comment can't be null or empty")
    private String comment;

    /**
     * Default constructor for NoteBean.
     */
    public NoteBean() {
    }

    /**
     * Constructor for NoteBean with patient ID and comment.
     *
     * @param patientId The ID of the patient associated with the note.
     * @param comment   The comment of the note.
     */
    public NoteBean(Integer patientId, String comment) {
        this.patientId = patientId;
        this.comment = comment;
    }

    /**
     * Constructor for NoteBean with ID, patient ID, and comment.
     *
     * @param id        The ID of the note.
     * @param patientId The ID of the patient associated with the note.
     * @param comment   The comment of the note.
     */
    public NoteBean(String id, Integer patientId, String comment) {
        this.id = id;
        this.patientId = patientId;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", patientId=" + patientId +
                ", comment='" + comment + '\'' +
                '}';
    }
}