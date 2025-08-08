package br.com.vignoli.minimal_social_media.Dtos;

public record LikeRequestDto(
    Integer userId,
    Integer postId
) {}