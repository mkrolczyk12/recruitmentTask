package io.github.restfulApiWebservice.restfulApiWebservice.notes;

import javax.validation.constraints.NotBlank;

public class NoteWriteModel {
    @NotBlank(message = "Note title can not be null or empty")
    private String title;
    @NotBlank(message = "Note content can not be null or empty")
    private String content;

    public String getTitle() {return title;}
    public void setTitle(final String title) {this.title = title;}

    public String getContent() {return content;}
    public void setContent(final String content) {this.content = content;}

    public Note toNote() {
        return new Note.NoteBuilder().build(title, content);
    }
}
