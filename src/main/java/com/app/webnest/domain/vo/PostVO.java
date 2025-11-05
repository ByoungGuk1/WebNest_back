package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class PostVO {
    private Long id;
    private String postContent;
    private String postTitle; // erd 추가
    private Date postCreateAt;
    private Integer postViewCount;
    private Long userId;
    private String postType; // erd추가 -> js, orvcle, ...( 문제 둥지 ) / 없음 ( 열린 둥지 )

}
