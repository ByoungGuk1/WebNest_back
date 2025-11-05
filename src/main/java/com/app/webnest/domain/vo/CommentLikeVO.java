package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class CommentLikeVO {
    private Long id;
    private Long userId;
    private Long postId;
    private Long commentId;
}
