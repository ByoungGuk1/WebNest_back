package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class CommentLikeDTO {
    private Long id;
    private Long userId;
    private Long postId;
    private Long commentId;
}
