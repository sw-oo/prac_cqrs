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

    public BoardDto.RegRes register(Long userIdx, String userName, BoardDto.RegReq dto) {

        Board entity = boardRepository.save(dto.toEntity(userIdx, userName));

        return BoardDto.RegRes.from(entity);
    }

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
