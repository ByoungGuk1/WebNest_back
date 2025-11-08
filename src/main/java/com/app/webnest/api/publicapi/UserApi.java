package com.app.webnest.api.publicapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApi {

    private final UserService userService;

    // 스웨거 추가
    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> register(@RequestBody UserVO userVO){
        userService.register(userVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.of("회원가입이 완료되었습니다")); // 201
    }

//    // 회원수정
//    @PutMapping("/modify")
//    public ResponseEntity<ApiResponseDTO> modify(@RequestBody UserVO userVO){
//        userService.modify(userVO);
//        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("정보 수정이 완료되었습니다.")); // 200
//    }
//
//    // 회원탈퇴
//    @DeleteMapping("/unregister")
//    public ResponseEntity<ApiResponseDTO> unregister(@RequestBody Long id){
//        userService.withdraw(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponseDTO.of("회원 탈퇴가 완료되었습니다.")); // 204
//    }

}
