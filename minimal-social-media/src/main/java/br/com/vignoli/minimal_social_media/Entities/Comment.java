package main.java.br.com.vignoli.minimal_social_media.Entities;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;
    private LocalDateTime createdAt;
}
