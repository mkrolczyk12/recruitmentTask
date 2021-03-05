package io.github.restfulApiWebservice.restfulApiWebservice.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NoteRepository mockInMemoryRepository;

    @Test
    void httpPost_postsGivenTaskWithAllNeededFields_expectSuccess() throws Exception {
        // given
        var mockNote = new NoteWriteModel();
        mockNote.setTitle("mock title1");
        mockNote.setContent("mock content");
        // when + then
        mockMvc
            .perform(post("/notes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockNote)))
            .andExpect(status().is2xxSuccessful());
    }
    @Test
    void httpPost_postsGivenTaskWithNoRequiredField_expectBadRequest() throws Exception {
        // given
        var mockNote = new NoteWriteModel();
        mockNote.setContent("mock content");
        // when + then
        mockMvc
            .perform(post("/notes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockNote)))
            .andExpect(status().is4xxClientError())
            .andExpect(status().isBadRequest());
    }
    @Test
    void httpGet_getAllNotes_expectSuccess() throws Exception {
        // given + when + then
        mockMvc
            .perform(get("/notes")
                .contentType("application/json"))
            .andExpect(status().isOk());
    }
    @Test
    void httpGet_getSingleNote_expectSuccess() throws Exception {
        //given
        NoteWriteModel mockNote = new NoteWriteModel();
        mockNote.setTitle("title1");
        mockNote.setContent("content1");

        String noteId = mockInMemoryRepository.save(mockNote.toNote()).getNoteId();

        //when + then
        mockMvc
            .perform(get("/notes/" + noteId))
            .andExpect(status().is2xxSuccessful());
    }
    @Test
    void httpGet_getSingleNote_expectBadRequest() throws Exception {
        //given
        String notExistMockNoteId = "123125346gergerg34634634";
        //when + then
        mockMvc
            .perform(get("/notes/" + notExistMockNoteId))
            .andExpect(status().is4xxClientError());
    }
    @Test
    void httpPut_fullNoteUpdate_expectSuccess() throws Exception {
        // given
        // mock database note data
        NoteWriteModel mockNote1 = new NoteWriteModel();
        mockNote1.setTitle("title1");
        mockNote1.setContent("content1");
        Note note1 = mockNote1.toNote();
        mockInMemoryRepository.save(note1);

        // http mock request data given by client
        NoteWriteModel httpMockClientData = new NoteWriteModel();
        httpMockClientData.setTitle("title1 after PUT");
        httpMockClientData.setContent("content1 after update");

        // when + then
        mockMvc
            .perform(put("/notes/" + note1.getNoteId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(httpMockClientData)))
            .andExpect(status().is2xxSuccessful());
        Note actualVersionOfNote = mockInMemoryRepository
                .findByNoteIdAndDeletedAndActualVersion(note1.getNoteId(), false, true)
                .orElseThrow();
        assertThat(actualVersionOfNote.getTitle()).isEqualTo(httpMockClientData.getTitle());
        assertThat(actualVersionOfNote.getContent()).isEqualTo(httpMockClientData.getContent());
    }
    @Test
    void httpPut_fullNoteUpdateWithNoExistNoteId_expectBadRequest() throws Exception {
        //given
        String notExistMockNoteId = "123125346gergerg34634634";
        //when + then
        mockMvc
                .perform(put("/notes/" + notExistMockNoteId))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void httpPatch_partNoteUpdate_expectSuccess() throws Exception {
        // given
        // mock database note data
        NoteWriteModel mockNote1 = new NoteWriteModel();
        mockNote1.setTitle("title1");
        mockNote1.setContent("content1");
        Note note1 = mockNote1.toNote();
        mockInMemoryRepository.save(note1);

        // http mock request data given by client
        NoteWriteModel httpMockClientData = new NoteWriteModel();
        httpMockClientData.setTitle("title1 after PATCH");
        httpMockClientData.setContent("content1 after update");

        // when + then
        mockMvc
            .perform(patch("/notes/" + note1.getNoteId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(httpMockClientData)))
            .andExpect(status().is2xxSuccessful());
        Note actualVersionOfNote = mockInMemoryRepository
            .findByNoteIdAndDeletedAndActualVersion(note1.getNoteId(), false, true)
            .orElseThrow();
        assertThat(actualVersionOfNote.getTitle()).isEqualTo("title1 after PATCH");
    }
    @Test
    void httpPatch_partNoteUpdateWithNoExistNoteId_expectBadRequest() throws Exception {
        //given
        String notExistMockNoteId = "123125346gergerg34634634";
        //when + then
        mockMvc
                .perform(patch("/notes/" + notExistMockNoteId))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void httpDelete_deleteSingleNote_expectSuccess() throws Exception {
        // given
        // mock version 1
        NoteWriteModel mockNote1 = new NoteWriteModel();
        mockNote1.setTitle("title1");
        mockNote1.setContent("content1");
        Note note1 = mockNote1.toNote();
        mockInMemoryRepository.save(note1);
        // mock version 2
        NoteWriteModel mockNote2 = new NoteWriteModel();
        mockNote2.setTitle("title2");
        mockNote2.setContent("content2");
        Note note2 = mockNote2.toNote();
        mockInMemoryRepository.save(note2);
        // mock version 3
        NoteWriteModel mockNote3 = new NoteWriteModel();
        mockNote3.setTitle("title3");
        mockNote3.setContent("content3");
        Note note3 = mockNote3.toNote();
        mockInMemoryRepository.save(note3);

        // when + then
        mockMvc
            .perform(delete("/notes/" + note1.getNoteId()))
            .andExpect(status().is2xxSuccessful());
        List<Note> result = mockInMemoryRepository.findAllByNoteIdAndDeleted(note1.getNoteId(), false);
        assertThat(result.isEmpty()).isEqualTo(true);
    }
    @Test
    void httpDelete_deleteSingleNoteWithNoExistNoteId_expectBadRequest() throws Exception {
        //given
        String notExistMockNoteId = "123125346gergerg34634634";
        //when + then
        mockMvc
                .perform(delete("/notes/" + notExistMockNoteId))
                .andExpect(status().is4xxClientError());
    }
}