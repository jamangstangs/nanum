package com.nanum.market.service;

import com.nanum.market.dto.*;
import com.nanum.market.model.Board;
import com.nanum.market.model.Comment;
import com.nanum.market.model.Heart;
import com.nanum.market.model.User;
import com.nanum.market.repository.BoardRepository;
import com.nanum.market.repository.CommentRepository;
import com.nanum.market.repository.HeartRepository;
import com.nanum.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final HeartRepository heartRepository;
    private final CommentRepository commentRepository;
    private final static int size = 10;

    public List<BoardMainDto> getBoard() {
        List<Board> board = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardMainDto> mainDtoList = new ArrayList<>();
        for(int i=0; i<board.size(); i++){
            BoardMainDto mainDto = new BoardMainDto(board.get(i));
            mainDtoList.add(mainDto);
        }
        return mainDtoList;
    }

    public List<BoardMainDto> getBoards(Long userId) {
        List<Board> board = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardMainDto> mainDtoList = new ArrayList<>();

        for(int i=0; i<board.size(); i++){
            Heart heart = heartRepository.findByBoardIdAndUserId(board.get(i).getId(), userId);;
            if (heart == null && board.get(i).isStatus() == false && board.get(i).getUser().getId() != userId ) {
                BoardMainDto mainDto = new BoardMainDto(board.get(i));
                mainDtoList.add(mainDto);
            }
        }
        return mainDtoList;
    }

    public List<BoardCommentDto> getMyHeartBoard(Long userId) {
        List<Board> board = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardCommentDto> boardCommentDtoList = new ArrayList<>();
        for(int i=0; i<board.size(); i++){
            Long boardId = board.get(i).getId();
            String imgurl = board.get(i).getImgUrl();
            Long ownerId = board.get(i).getUser().getId();
            Boolean owned = false;
            if(ownerId == userId){
                owned = true;
            }
            Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId);;
            if (heart != null) {
                List<Comment> comment = commentRepository.findByBoardId(boardId);
                List<CommentDto> commentDtoList = new ArrayList<>();
                for(int j=0; j<comment.size(); j++) {
                    CommentDto commentDto = new CommentDto(comment.get(j));
                    commentDtoList.add(commentDto);
                }
                boardCommentDtoList.add(new BoardCommentDto(boardId, imgurl, owned, commentDtoList));
            }
        }
        return boardCommentDtoList;
    }


    public List<BoardMainDto> getMyBoard(User user) {
        List<Board> board = boardRepository.findByUser(user);
        List<BoardMainDto> mainDtoList = new ArrayList<>();
        for(int i=0; i<board.size(); i++){
            BoardMainDto mainDto = new BoardMainDto(board.get(i));
            mainDtoList.add(mainDto);
        }
        return mainDtoList;
    }

    public boolean completeBoard(Long boardId,Long userId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if(board.getUser().getId() == userId ){
            board.setStatus(true);
            boardRepository.save(board);
            return true;
        }
        return false;
    }

    public List<BoardMainDto> getSearchBoard(String title) {
        List<Board> board = boardRepository.findByTitleContainingOrContentContainingOrderByModifiedAtDesc(title, title);
        List<BoardMainDto> mainDtoList = new ArrayList<>();
        for(int i=0; i<board.size(); i++){
            BoardMainDto mainDto = new BoardMainDto(board.get(i));
            mainDtoList.add(mainDto);
        }
        return mainDtoList;
    }
    public BoardPostDto createBoard(BoardRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Board board = new Board(requestDto);
        board.addUser(user);
        boardRepository.save(board);
        BoardPostDto boardPostDto = new BoardPostDto(board);
        return boardPostDto;

    }

    @Transactional
    public BoardPostDto updateBoard(Long boardId, BoardRequestDto requestDto,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if (board.getUser().getId().equals(userId)){
            board.update(requestDto);
            BoardPostDto boardPostDto = new BoardPostDto(board);
            return boardPostDto;
        }
        else{
            return null;
        }

    }

    @Transactional
    public Board deleteBoard(Long boardId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if (board.getUser().getId().equals(userId)) {
            boardRepository.deleteById(boardId);
            return board;
        }
        else{
            return null;
        }
    }


    public BoardDetailDto getDetailBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        BoardDetailDto boardDetailDto = new BoardDetailDto(board);
        return boardDetailDto;
    }


}
