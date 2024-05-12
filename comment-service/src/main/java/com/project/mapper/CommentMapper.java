package com.project.mapper;

import com.project.dto.request.AddCommentRequestDto;
import com.project.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment fromAddCommentRequestDtoToComment(final AddCommentRequestDto dto);
}
