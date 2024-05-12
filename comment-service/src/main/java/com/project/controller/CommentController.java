package com.project.controller;

import com.project.dto.request.AddCommentRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Comment;
import com.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * Managerin comment eklemesi icin method
     * @param dto
     * @return
     */
    @PostMapping(ADD_COMMENT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> addComment(@RequestBody AddCommentRequestDto dto){
        commentService.addComment(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Comment created")
                .data(true)
                .build());
    }

    @GetMapping(GET_ALL_COMMENTS)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> getAllComments(){
        commentService.getAllComments();
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Comments get")
                .data(true)
                .build());

    }




}
