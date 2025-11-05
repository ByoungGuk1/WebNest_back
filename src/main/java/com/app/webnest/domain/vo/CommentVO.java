package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class CommentVO {
    private Long id;
    private String commentDesciption;
    private Long userId;
    private Long postId;
    private Boolean commentIsAccept; //
    private Date commentCreateAt; //check
}
