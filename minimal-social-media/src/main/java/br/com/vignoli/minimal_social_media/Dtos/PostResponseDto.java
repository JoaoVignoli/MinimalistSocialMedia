package br.com.vignoli.minimal_social_media.Dtos;

import java.time.LocalDateTime;

public record PostResponseDto(
    Integer id,
    String content,
    Integer authorId,
    LocalDateTime createdAt
) {}
