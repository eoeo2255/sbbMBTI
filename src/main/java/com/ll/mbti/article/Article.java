package com.ll.mbti.article;

import com.ll.mbti.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter     //  일반적으로 엔티티에는 @Setter 가 추천되지 않는다. 데이터가 자유롭게 변경될 수 있기 때문에 @Builder 를 사용한다.
@Entity     // 엔티티는 @Column 을 쓰지 않아도 테이블 컬럼으로 인식하기 때문에, 컬럼으로 사용하지 않으려면 @Transient 애너테이션 사용
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")      //  columnDefinition = "TEXT" 속성으로 해당 컬럼의  글자수를 제한할 수 없다.
    private String content;
    private LocalDateTime createDate;
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
