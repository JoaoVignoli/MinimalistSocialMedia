package br.com.vignoli.minimal_social_media.Dtos;

public record LikeResponseDto(
    Integer id,
    Integer postId,
    Integer userId
) {}