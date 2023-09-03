package hello.board.web.board;

import hello.board.domain.board.Board;
import hello.board.domain.board.BoardRepository;
import hello.board.web.board.form.BoardSaveForm;
import hello.board.web.board.form.BoardUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping
    public String boards(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "boards/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "boards/board";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("board", new Board());
        return "boards/addForm";
    }

    @PostMapping("/add")
    public String addBoard(@Validated @ModelAttribute("board")BoardSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "boards/addForm";
        }

        // 성공 로직
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setName(form.getName());
        board.setContents(form.getContents());

        Board savedBoard = boardRepository.save(board);
        redirectAttributes.addAttribute("boardId", savedBoard.getId());
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "boards/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId,@Validated @ModelAttribute("board") BoardUpdateForm form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "boards/editForm";
        }

        Board boardParam = new Board();
        boardParam.setTitle(form.getTitle());
        boardParam.setName(form.getName());
        boardParam.setContents(form.getContents());

        boardRepository.update(boardId, boardParam);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable Long boardId){
        boardRepository.delete(boardId);
        return "redirect:/boards";
    }
}
