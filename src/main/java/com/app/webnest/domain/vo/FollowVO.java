package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class FollowVO {
    private Long id;
    private Long userId;
    private Long userId2; // 확인 필요  -- 팔로잉 한 사용자
    private Date followCreateAt;  //create at으로 동일하게 erd를 고칩시다
}
