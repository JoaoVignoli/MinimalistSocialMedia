package br.com.vignoli.minimal_social_media.Dtos;

public record PostRequestDto(
    String content,
    Integer authorId
) {}
