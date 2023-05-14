package com.ll.mbti.comment;

import com.ll.mbti.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void create(Article article, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setArticle(article);
        comment.setCreateDate(LocalDateTime.now());

        this.commentRepository.save(comment);
    }

}
