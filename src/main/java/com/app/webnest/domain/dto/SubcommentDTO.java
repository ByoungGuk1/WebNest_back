package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class SubcommentDTO {
    private Long id;
    private Long userId;
    private Long commentId;
    private Date subcommentCreateAt;
}
