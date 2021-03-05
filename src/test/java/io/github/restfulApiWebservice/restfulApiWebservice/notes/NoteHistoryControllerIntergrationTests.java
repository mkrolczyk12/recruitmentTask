package io.github.restfulApiWebservice.restfulApiWebservice.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteHistoryControllerIntergrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoteRepository mockInMemoryRepository;

    @Test
    void httpGet_getNoteHistory_expectSuccess() throws Exception {
        // given
        // mock note version 1
        NoteWriteModel mockNote1 = new NoteWriteModel();
        mockNote1.setTitle("title1");
        mockNote1.setContent("content1");
        Note note1 = mockNote1.toNote();
        mockInMemoryRepository.save(note1);
        NoteHistoryReadModel noteVersion1 = new NoteHistoryReadModel(note1);
        // mock note version 2
        Note note2 = new Note.NoteBuilder().build(note1);
        note2.setTitle("title version 2");
        mockInMemoryRepository.save(note2);
        NoteHistoryReadModel noteVersion2 = new NoteHistoryReadModel(note2);

        List<NoteHistoryReadModel> listOfNotesToCompare = List.of(noteVersion1, noteVersion2);

        // when + then
        mockMvc
                .perform(get("/notes/history/" + note1.getNoteId())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(listOfNotesToCompare)));
    }
    @Test
    void httpGet_getNoteHistory_expectBadRequest() throws Exception {
        //given
        String notExistMockNoteId = "123125346gergerg34634634";
        //when + then
        mockMvc
                .perform(delete("/notes/" + notExistMockNoteId))
                .andExpect(status().is4xxClientError());
    }
}