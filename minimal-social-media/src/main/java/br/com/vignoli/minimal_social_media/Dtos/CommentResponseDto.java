package br.com.vignoli.minimal_social_media.Dtos;

import java.time.LocalDateTime;

public record CommentResponseDto(
    Integer id,
    Integer authorId,
    Integer postId,
    String text,
    LocalDateTime createdAt
) {}
