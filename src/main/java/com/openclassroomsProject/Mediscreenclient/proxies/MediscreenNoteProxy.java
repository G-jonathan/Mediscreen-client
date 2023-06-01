package com.openclassroomsProject.Mediscreenclient.proxies;

import com.openclassroomsProject.Mediscreenclient.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Feign client interface that allows interaction with the mediscreen-notes microservice.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@FeignClient(name = "mediscreen-notes", url = "${NOTE_API_URL}")
public interface MediscreenNoteProxy {

    /**
     * Retrieves notes for a specific patient based on their ID.
     *
     * @param patientId The ID of the patient.
     * @return The list of NoteBean corresponding to the patient.
     */
    @GetMapping(value = "/api/note/patient/{patientId}")
    List<NoteBean> getNotesBeanByPatientId(@PathVariable("patientId") Integer patientId);

    /**
     * Retrieves a note based on its ID.
     *
     * @param noteId The ID of the note.
     * @return The NoteBean corresponding to the ID.
     */
    @GetMapping(value = "/api/note/{noteId}")
    NoteBean getNoteById(@PathVariable("noteId") String noteId);

    /**
     * Creates a new note.
     *
     * @param noteBean The NoteBean to create.
     * @return The created NoteBean.
     */
    @PostMapping(value = "/api/note")
    NoteBean createNoteBean(@RequestBody NoteBean noteBean);

    /**
     * Updates an existing note.
     *
     * @param noteId       The ID of the note to update.
     * @param newNoteBean  The new NoteBean.
     * @return The updated NoteBean.
     */
    @PutMapping("/api/note/{noteId}")
    NoteBean updateNotBean(@PathVariable String noteId, @RequestBody NoteBean newNoteBean);

    /**
     * Deletes a note based on its ID.
     *
     * @param noteId The ID of the note to delete.
     */
    @DeleteMapping("/api/note/{noteId}")
    void deleteNoteBean(@PathVariable String noteId);
}