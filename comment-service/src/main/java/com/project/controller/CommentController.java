package com.project.controller;

import com.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

}
