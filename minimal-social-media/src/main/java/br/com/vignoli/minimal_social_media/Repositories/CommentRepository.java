package br.com.vignoli.minimal_social_media.Repositories;

import br.com.vignoli.minimal_social_media.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
