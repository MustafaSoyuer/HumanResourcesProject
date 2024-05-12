package com.project.service;

import com.project.dto.request.AddCommentRequestDto;
import com.project.entity.Comment;
import com.project.exception.CommentServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.CommentMapper;
import com.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Boolean addComment(AddCommentRequestDto dto){

        Comment comment= commentRepository.findByToken(dto.getToken());
        if (comment.getComment().isEmpty()){
            comment.setCreateAt(System.currentTimeMillis());
            comment.setUpdateAt(System.currentTimeMillis());
            commentRepository.save(CommentMapper.INSTANCE.fromAddCommentRequestDtoToComment(dto));
            return true;
        }
            throw new CommentServiceException(ErrorType.MANAGER_ALREADY_HAVE_COMMENT);


    }


    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
