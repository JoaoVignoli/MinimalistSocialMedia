package br.com.vignoli.minimal_social_media.Repositories;

import br.com.vignoli.minimal_social_media.Dtos.PostResponseDto;
import br.com.vignoli.minimal_social_media.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {


    List<PostResponseDto> findAllByAuthor_Id(Integer authorId);

    @Query("SELECT new br.com.vignoli.minimal_social_media.Dtos.PostResponseDto(p.id, p.content, p.author.id, p.createdAt) " +
        "FROM Post p")
    List<PostResponseDto> findAllDtos();
}
