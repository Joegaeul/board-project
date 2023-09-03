package hello.board.domain.board;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Board {

    private Long id;
    private String name;
    private String title;
    private String contents;

    public Board(){

    }

    public Board(Long id, String name, String title, String contents) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
    }
}
