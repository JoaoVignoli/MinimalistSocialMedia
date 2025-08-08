package br.com.vignoli.minimal_social_media.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vignoli.minimal_social_media.Dtos.LikeResponseDto;
import br.com.vignoli.minimal_social_media.Dtos.PostRequestDto;
import br.com.vignoli.minimal_social_media.Dtos.PostResponseDto;
import br.com.vignoli.minimal_social_media.Entities.Post;
import br.com.vignoli.minimal_social_media.Entities.User;
import br.com.vignoli.minimal_social_media.Repositories.CommentRepository;
import br.com.vignoli.minimal_social_media.Repositories.LikeRepository;
import br.com.vignoli.minimal_social_media.Repositories.PostRepository;
import br.com.vignoli.minimal_social_media.Repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public PostController (
        PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository, LikeRepository likeRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public ResponseEntity<Object> createNewPost(@RequestBody PostRequestDto postRequestDto) {
        
        Optional<User> author = this.userRepository.findById(postRequestDto.authorId());

        if (author.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Author not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        
        Post postSave = new Post();
        postSave.setAuthor(author.get());
        postSave.setContent(postRequestDto.content());
        postSave.setCreatedAt(LocalDateTime.now());

        this.postRepository.save(postSave);

        PostResponseDto responseDto = new PostResponseDto(
            postSave.getId(), postSave.getContent(), author.get().getId() ,postSave.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(this.postRepository.findAllDtos()); 
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getMethodName(@PathVariable Integer postId) {
        
        Optional<Post> post = this.postRepository.findById(postId);
        
        if (post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        PostResponseDto responseDto = new PostResponseDto(
            post.get().getId(), post.get().getContent(), post.get().getAuthor().getId(), post.get().getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    
    
    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<LikeResponseDto>> getPostLikes(@PathVariable Integer postId) {
        Optional<Post> post = this.postRepository.findById(postId);
        
        if (post.isEmpty()) {
            Map<String, Object> postError = new HashMap<>();
            postError.put("message", "Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.likeRepository.findAllByPost_Id(postId));

    }
    
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<?>> getPostComments(@PathVariable Integer postId) {
        Optional<Post> post = this.postRepository.findById(postId);
        
        if (post.isEmpty()) {
            Map<String, Object> postError = new HashMap<>();
            postError.put("message", "Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    
        return ResponseEntity.status(HttpStatus.OK).body(this.commentRepository.findAllByPost_Id(postId));
    }
}
