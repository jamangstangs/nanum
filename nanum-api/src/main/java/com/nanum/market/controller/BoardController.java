package com.nanum.market.controller;

import com.nanum.market.config.auth.PrincipalDetails;
import com.nanum.market.dto.*;
import com.nanum.market.model.Board;

import com.nanum.market.model.Message;
import com.nanum.market.s3.S3Uploader;
import com.nanum.market.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final S3Uploader s3Uploader;

    @GetMapping("/main")
    public List<BoardMainDto> getBoard(@RequestParam(value = "searchText", required = false) String searchText, @AuthenticationPrincipal PrincipalDetails userDetails){
        if (searchText == null){
            return boardService.getBoards(userDetails.getUser().getId());
        }else{
            return boardService.getSearchBoard(searchText);
        }
    }

    @GetMapping("/boards/me/heart")
    public List<BoardCommentDto> getHeartBoard(@AuthenticationPrincipal PrincipalDetails userDetails){
        Long userId = userDetails.getUser().getId();
        return boardService.getMyHeartBoard(userId);
    }

    @GetMapping("/boards/me")
    public List<BoardMainDto> getMyBoard(@AuthenticationPrincipal PrincipalDetails userDetails) {
        return boardService.getMyBoard(userDetails.getUser());
    }

    @PostMapping("/boards")
    public BoardPostDto createBoard(@RequestParam(value = "title") String title, @RequestParam(value = "content", required = false) String content,
                                      @RequestParam(value = "file") MultipartFile files, @AuthenticationPrincipal PrincipalDetails userDetails) throws IOException {

        String imgUrl = s3Uploader.upload(files);
        BoardRequestDto requestDto = new BoardRequestDto(title, content, false, imgUrl);

        return boardService.createBoard(requestDto, userDetails.getUser().getId());

    }

    @PutMapping("/boards/{boardId}")
    public BoardPostDto updateBoard(@PathVariable Long boardId, @RequestParam("title") String title, @RequestParam("content") String content,
                                      @RequestParam(value = "status") boolean status,
                                      @RequestParam(value = "file", required = false) MultipartFile files, @RequestParam(value = "imgUrl", required = false) String imgUrl, @AuthenticationPrincipal PrincipalDetails userDetails) throws IOException {

        if(imgUrl == null) {
            imgUrl = s3Uploader.upload(files);
        }
        BoardRequestDto requestDto = new BoardRequestDto(title, content,status, imgUrl);

        return boardService.updateBoard(boardId, requestDto
                ,userDetails.getUser().getId());
    }

    @PutMapping("/boards/{boardId}/complete")
    public boolean completeBoard(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails userDetails) throws IOException {
        Long userId = userDetails.getUser().getId();
        return boardService.completeBoard(boardId,userId);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails userDetails){
        Board board = boardService.deleteBoard(boardId, userDetails.getUser().getId());
        if (board==null){
            Message message = new Message("자신이 작성한 게시글만 삭제할 수 있습니다.");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return ResponseEntity.ok().build();

    }

    @GetMapping("/boards/{boardId}/details")
    public BoardDetailDto getDetailBoard(@PathVariable Long boardId){
        return boardService.getDetailBoard(boardId);
    }

}
