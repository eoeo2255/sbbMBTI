package com.ll.mbti;

import com.ll.mbti.article.Article;
import com.ll.mbti.article.ArticleRepository;
import com.ll.mbti.comment.Comment;
import com.ll.mbti.comment.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MbtiApplicationTests {
	@Autowired		//	테스트 코드는 생성자를 통한 객체주입이 안되기 때문에 @Autowired를 사용
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	@DisplayName("게시글 생성")
	void createArticle() {
		Article article1 = new Article();
		article1.setTitle("당신의 MBTI는 뭔가요?");
		article1.setContent("각자의 MBTI를 알려주세요.");
		article1.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(article1);

		Article article2 = new Article();
		article2.setTitle("E와 I의 차이점");
		article2.setContent("외향인과 내향인");
		article2.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(article2);
	}

	@Test
	@DisplayName("게시글 전체 조회")
	void findAllArticle() {
		List<Article> allArticle = this.articleRepository.findAll();
		assertEquals(2, allArticle.size());
	}

	@Test
	@DisplayName("id로 게시글 찾기")
	void findByArticleId() {
		Optional<Article> optionalArticle = this.articleRepository.findById(1);
		if(optionalArticle.isPresent()) {		//	Optional 엔 null 값도 들어올 수 있기 때문에 값이 있는지 확인해줘야 한다.
			Article article = optionalArticle.get();
			assertEquals("당신의 MBTI는 뭔가요?", article.getTitle());
		}
	}

	@Test
	@DisplayName("제목의 특정 문자열로 게시글 찾기")
	void findByTitleLike() {
		List<Article> articleList = this.articleRepository.findByTitleLike("E%");
		Article article = articleList.get(0);
		assertEquals("E와 I의 차이점", article.getTitle());
	}

	@Test
	@DisplayName("게시글 내용 수정하기")
	void modifyArticleContent() {
		Optional<Article> optionalArticle = this.articleRepository.findById(1);
		assertTrue(optionalArticle.isPresent());		//	값이 true 인지 확인
		Article article = optionalArticle.get();
		article.setContent("수정된 내용");
		this.articleRepository.save(article);
	}

	@Test
	@DisplayName("게시글 삭제하기")
	void deleteArticle() {
		assertEquals(2, this.articleRepository.count());
		Optional<Article> optionalArticle = this.articleRepository.findById(1);
		assertTrue(optionalArticle.isPresent());
		Article article = optionalArticle.get();
		this.articleRepository.delete(article);
		assertEquals(1, this.articleRepository.count());
	}

	@Test
	@DisplayName("댓글 생성")
	void createComment() {
		Optional<Article> optionalArticle = this.articleRepository.findById(2);
		assertTrue(optionalArticle.isPresent());
		Article article = optionalArticle.get();

		Comment comment = new Comment();
		comment.setContent("저는 E인데 내향적이에요.");
		comment.setArticle(article);  // 어떤 글의 댓글인지 정의
		comment.setCreateDate(LocalDateTime.now());
		this.commentRepository.save(comment);
	}

	@Test
	@Transactional
	@DisplayName("게시글에 연결 된 댓글 찾기")
	void articleConnectedComment() {
		Optional<Article> optionalArticle = this.articleRepository.findById(2);
		assertTrue(optionalArticle.isPresent());
		Article article = optionalArticle.get();

		List<Comment> commentList = article.getComments();

		assertEquals(1, commentList.size());
		assertEquals("저는 E인데 내향적이에요.", commentList.get(0).getContent());
	}

}
