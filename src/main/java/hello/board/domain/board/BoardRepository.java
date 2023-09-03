package hello.board.domain.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class BoardRepository {

    private static final Map<Long, Board> store = new HashMap<>();
    private static long sequence = 0L;

    public Board save(Board board){
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }
    public Board findById(Long id){
        return store.get(id);
    }

    public List<Board> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long BoardId, Board updateParam){
        Board findBoard = findById(BoardId);
        findBoard.setName(updateParam.getName());
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setContents(updateParam.getContents());
    }


    public void delete(Long boardId) {
        store.remove(boardId);
    }
}
