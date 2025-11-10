package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class DeclarationDTO {
    private Long id;
    private Long userId; // erd 고치기
    private String declarationContent;
}
