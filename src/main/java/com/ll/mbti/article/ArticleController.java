package com.ll.mbti.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이 붙은 필드를 포함하는 생성자를 자동으로 생성
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/list")
    public String list(Model model) {       //  Model 객체에 값을 담아두면 템플릿에서 사용 가능
        List<Article> articleList = this.articleService.getList();

        model.addAttribute("articleList", articleList);

        return "article_list";
    }

    @GetMapping("/article/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);

        return "article_detail";
    }


}
