package br.com.vignoli.minimal_social_media.Repositories;

import br.com.vignoli.minimal_social_media.Dtos.LikeResponseDto;
import br.com.vignoli.minimal_social_media.Entities.Like;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    List<LikeResponseDto> findAllByPost_Id(Integer postId);
}
