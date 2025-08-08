package br.com.vignoli.minimal_social_media.Controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vignoli.minimal_social_media.Dtos.LikeRequestDto;
import br.com.vignoli.minimal_social_media.Dtos.LikeResponseDto;
import br.com.vignoli.minimal_social_media.Entities.Like;
import br.com.vignoli.minimal_social_media.Entities.Post;
import br.com.vignoli.minimal_social_media.Entities.User;
import br.com.vignoli.minimal_social_media.Repositories.LikeRepository;
import br.com.vignoli.minimal_social_media.Repositories.PostRepository;
import br.com.vignoli.minimal_social_media.Repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public LikeController(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping 
    public ResponseEntity<Object> createLike(@RequestBody LikeRequestDto likeRequestDto) {
        Optional<User> user = this.userRepository.findById(likeRequestDto.userId());
        Optional<Post> post = this.postRepository.findById(likeRequestDto.postId());
        
        if (user.isEmpty()) {
            Map<String, Object> userError = new HashMap<>();
            userError.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);
        }

        if (post.isEmpty()) {
            Map<String, Object> postError = new HashMap<>();
            postError.put("message", "Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postError);
        }

        Like likeSave = new Like();
        likeSave.setPost(post.get());
        likeSave.setUser(user.get());

        this.likeRepository.save(likeSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new LikeResponseDto(likeSave.getId(), likeSave.getPost().getId(), likeSave.getUser().getId())
        );
    }
}
