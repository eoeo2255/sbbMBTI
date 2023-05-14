package com.ll.mbti.comment;

import com.ll.mbti.article.Article;
import com.ll.mbti.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final ArticleService articleService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        //  @RequestParam 이 템플릿에서 답변으로 입력한 내용을 받아온다.
        Article article = this.articleService.getArticle(id);

        this.commentService.create(article, content);

        return String.format("redirect:/article/detail/%s", id);
    }

}
