package com.project.service;

import com.project.dto.request.AddCommentRequestDto;
import com.project.dto.response.ManagerResponseDto;
import com.project.entity.Comment;
import com.project.exception.CommentServiceException;
import com.project.exception.ErrorType;
import com.project.manager.ManagerManager;
import com.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ManagerManager managerManager;
    public Boolean addComment(AddCommentRequestDto dto){
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CommentServiceException(ErrorType.MANAGER_NOT_FOUND));

        Optional<Comment> comment= commentRepository.findOptionalById(dto.getManagerId());

        if (comment.isPresent()){
            commentRepository.save(
                    Comment.builder()
                            .comment(dto.getComment())
                            .managerId(dto.getManagerId())
                            .managerAvatar(managerResponseDto.getAvatar())
                            .managerName(managerResponseDto.getName())
                            .managerSurname(managerResponseDto.getSurname())
                            .managerEmail(managerResponseDto.getEmail())
                            .managerOccupation(managerResponseDto.getOccupation())
                            .build()
            );
            return true;
        }
            throw new CommentServiceException(ErrorType.MANAGER_ALREADY_HAVE_COMMENT);


    }


    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
