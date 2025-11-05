package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class BanVO {
    private Long id;
    private Date banDate;
    private Long userId;
    private Long userId2; // 체크 : 차단당한 유저의 아이디
}
