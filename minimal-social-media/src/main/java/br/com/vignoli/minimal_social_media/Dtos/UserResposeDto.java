package br.com.vignoli.minimal_social_media.Dtos;

import java.time.LocalDate;

public record UserResposeDto(
    Integer id,
    String name,
    String email,
    LocalDate registerDate
) {}
