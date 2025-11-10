package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class SubcommentLikeDTO {
    private Long id;
    private Long userId;
    private Long postId;
    private Long commentId;
}
