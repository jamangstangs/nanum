package com.nanum.market.service;

import com.nanum.market.model.Board;
import com.nanum.market.model.Heart;
import com.nanum.market.model.User;
import com.nanum.market.repository.BoardRepository;
import com.nanum.market.repository.HeartRepository;
import com.nanum.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public HashMap<String,Object> getHeart(Long boardId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId);

        List<Heart> heartCount = heartRepository.findByBoardId(boardId);
        Integer Count = heartCount.size();
        HashMap<String,Object> hashMap = new HashMap<>();

        if(heart == null){
            hashMap.put("check", false);
            hashMap.put("heartCount", Count);
            return hashMap;
        }else{
            hashMap.put("check", true);
            hashMap.put("heartCount", Count);
            return hashMap;
        }
    }

    public Boolean createHeart(Long boardId, Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId);
        if(heart == null) {
            Heart newHeart = new Heart();
            newHeart.addUser(user);
            newHeart.addBoard(board);
            heartRepository.save(newHeart);
            return true;
        }else{
            heartRepository.deleteByBoardIdAndUserId(boardId,userId);
            return false;
        }
    }

    @Transactional
    public Heart DeleteHeart(Long boardId, Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId);
        if(heart == null){
            return null;
        }else{
            heartRepository.deleteById(heart.getId());
            return heart;
        }

    }
}
