package br.com.vignoli.minimal_social_media.Repositories;

import br.com.vignoli.minimal_social_media.Dtos.CommentResponseDto;
import br.com.vignoli.minimal_social_media.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<CommentResponseDto> findAllByPost_Id(Integer postId);

}
