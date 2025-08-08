package br.com.vignoli.minimal_social_media.Dtos;

public record CommentRequestDto(
    Integer authorId,
    Integer postId,
    String text
) {}
