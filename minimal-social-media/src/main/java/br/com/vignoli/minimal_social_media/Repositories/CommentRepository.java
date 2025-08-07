package main.java.br.com.vignoli.minimal_social_media.Repositories;

import com.senac.produtos.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
