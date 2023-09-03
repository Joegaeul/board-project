package hello.board.web.board.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoardUpdateForm {

    @NotBlank
    private String title;
    @NotBlank
    private String name;
    @NotBlank
    private String contents;
}
