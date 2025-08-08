package br.com.vignoli.minimal_social_media.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vignoli.minimal_social_media.Dtos.CommentRequestDto;
import br.com.vignoli.minimal_social_media.Dtos.CommentResponseDto;
import br.com.vignoli.minimal_social_media.Entities.Comment;
import br.com.vignoli.minimal_social_media.Entities.Post;
import br.com.vignoli.minimal_social_media.Entities.User;
import br.com.vignoli.minimal_social_media.Repositories.CommentRepository;
import br.com.vignoli.minimal_social_media.Repositories.PostRepository;
import br.com.vignoli.minimal_social_media.Repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public CommentController(
        CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<Object> postMethodName(@RequestBody CommentRequestDto commentRequestDto) {
        Optional<User> author = this.userRepository.findById(commentRequestDto.authorId());
        Optional<Post> post = this.postRepository.findById(commentRequestDto.postId());
        
        if (author.isEmpty()) {
            Map<String, Object> authorError = new HashMap<>();
            authorError.put("message", "Author not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(authorError);
        }

        if (post.isEmpty()) {
            Map<String, Object> postError = new HashMap<>();
            postError.put("message", "Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postError);
        }

        Comment commentSave = new Comment();
        commentSave.setAuthor(author.get());
        commentSave.setPost(post.get());
        commentSave.setText(commentRequestDto.text());
        commentSave.setCreatedAt(LocalDateTime.now());

        Comment commentSaved = this.commentRepository.save(commentSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new CommentResponseDto(
                commentSaved.getId(), 
                commentSaved.getAuthor().getId(), 
                commentSaved.getPost().getId(), 
                commentSaved.getText(), 
                commentSaved.getCreatedAt()
            )
        );
    }
    
}  
