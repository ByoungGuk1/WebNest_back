package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PostResponseDTO {
    private Long id;
    private String postContent;
    private String postTitle;
    private Date postCreateAt;
    private Integer postViewCount;
    private Long userId;
    private String postType;
    private String userNickname;
}


//package com.app.threetier.domain.dto;
//
//import com.app.threetier.domain.vo.MemberVO;
//import com.app.threetier.domain.vo.PostVO;
//import lombok.*;
//
//public class PostResponseDTO {
//    private Long id;
//    private String postTitle;
//    private String postContent;
//    private Long postReadCount;
//    private Long memberId;
//    private String memberName;
//
//
//}
