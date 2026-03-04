package com.example.demo.board;

import com.example.demo.board.model.Board;
import com.example.demo.common.exception.BaseException;
import com.example.demo.common.model.BaseResponse;
import com.example.demo.common.model.BaseResponseStatus;
import com.example.demo.user.UserRepository;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import com.example.demo.board.model.BoardDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.common.model.BaseResponseStatus.SIGNUP_INVALID_UUID;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardDto.RegRes register(BoardDto.RegReq dto, Long idx) {
        User user = userRepository.findById(idx).orElseThrow(() -> BaseException.from(SIGNUP_INVALID_UUID));
        Board entity = boardRepository.save(dto.toEntity(user));

        return BoardDto.RegRes.from(entity);
    }

    public List<BoardDto.ListRes> list() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream().map(BoardDto.ListRes::from).toList();
    }

    @Transactional(readOnly = true)
    public BoardDto.ReadRes read(Long idx) {
        Board board = boardRepository.findById(idx).orElseThrow();
        return BoardDto.ReadRes.from(board);
    }

    @Transactional // 설정을 통해 readOnly 설정해줄수있음
    public BoardDto.RegRes update(Long idx, BoardDto.RegReq dto) {
        Board board = boardRepository.findById(idx).orElseThrow();
        board.update(dto);

        boardRepository.save(board);

        return BoardDto.RegRes.from(board);
    }

    public void delete(Long idx) {
        boardRepository.deleteById(idx);
    }
}
