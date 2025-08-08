package br.com.vignoli.minimal_social_media.Repositories;

import br.com.vignoli.minimal_social_media.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {

}
