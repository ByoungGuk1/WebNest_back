package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class CommentDTO {
    private Long id;
    private String commentDescription;
    private Long userId;
    private Long postId;
    private boolean commentIsAccept; //
    private Date commentCreateAt; //check
}
