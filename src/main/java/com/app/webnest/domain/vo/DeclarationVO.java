package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class DeclarationVO {
    private Long id;
    private Long userId; // erd 고치기
    private String declarationContent;
}
