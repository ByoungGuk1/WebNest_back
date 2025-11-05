package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class SubcommentVO {
    private Long id;
    private Long userId;
    private Long commentId;
    private Date subcommentCreateAt;
}
