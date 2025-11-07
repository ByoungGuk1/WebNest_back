package com.app.webnest.handler;

import com.app.webnest.domain.dto.UserResponseDTO;
import com.app.webnest.domain.dto.TokenDTO;
import com.app.webnest.domain.vo.UserInsertSocialVO;
import com.app.webnest.domain.vo.UserSocialVO;
import com.app.webnest.service.user.OAuthService;
import com.app.webnest.service.user.UserService;
import com.app.webnest.service.user.UserSocialService;
import com.app.webnest.util.JwtTokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final UserSocialService userSocialService;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisTemplate redisTemplate;
    private final OAuthService oauthService;

    // 소셜로그인 인가된 데이터가 들어온다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication instanceof OAuth2AuthenticationToken authToken){
            OAuth2User user = authToken.getPrincipal();
            Map<String, Object> attributes = user.getAttributes();
            // naver, google, kakao
            String userProvider = authToken.getAuthorizedClientRegistrationId();
            String userEmail = null;
            String userName = null;
            String userSocialProviderId = null;
            Long userId = null;
            Map<String, String> tokens = null;

            log.info("user: {}", user);
            // 1. 어디로 들어왔는지를 확인
            if(userProvider.equals("google")){
                userEmail = (String)attributes.get("email");
                userName = (String)attributes.get("name");
                userSocialProviderId = (String)attributes.get("sub");
            } else if(userProvider.equals("naver")){
                userEmail = (String)attributes.get("email");
                userName = (String)attributes.get("name");
                userSocialProviderId = (String)attributes.get("id");
            } else if(userProvider.equals("kakao")){
                userEmail = (String)attributes.get("email");
                userName = (String)attributes.get("nickname");
                userSocialProviderId = (String)attributes.get("id");
            }

            // 2. 이미 회원가입이 되어있는지
            if(userService.existsByUserEmail(userEmail)){
                userId = userService.getUserIdByUserEmail(userEmail);
                UserResponseDTO foundUser = userService.getUserById(userId);
                // 3. 어디로 접속했는지 확인!
                log.info("이미 회원가입 됨: {}", foundUser);
                log.info("이미 회원가입 된 아이디: {}", userId);
                // 4. 이미 회원가입이라면 토큰 발급

                List<String> providers = userSocialService.findAllProvidersById(userId);
                log.info("providers: {}", userSocialService.findAllProvidersById(userId));

                boolean isProviderConfirm = false;
                for(String provider : providers){
                    if(userProvider.equals(provider)){
                        isProviderConfirm = true;
                        break;
                    }
                }

                log.info("isProviderConfirm: {}", isProviderConfirm);
                if(isProviderConfirm){
                    // 아이디 같고, 프로바이더도 일치

                    // 토큰 생성
                    Map<String, String> claim = new HashMap<>();
                    claim.put("userEmail", foundUser.getUserEmail());
                    String accessToken = jwtTokenUtil.generateAccessToken(claim);
                    String refreshToken = jwtTokenUtil.generateRefreshToken(claim);

                    // 토큰 전달
                    tokens = new HashMap<String, String>();
                    tokens.put("accessToken", accessToken);
                    tokens.put("refreshToken", refreshToken);

                }else {
                    // 아이디는 같지만 프로바이더 불일치
                    // 계정 통합 - 화면으로 리다이렉트 시킨 후 인증절차 필요
                    String redirectUrl = "http://localhost:3000/oauth2/confirm?provider=" + userProvider;
                    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
                }

            } else {
                // 4. 신규 회원가입 후 토큰 발급
                UserInsertSocialVO newUser = new  UserInsertSocialVO();
                newUser.setUserEmail(userEmail);
                newUser.setUserName(userName);
                newUser.setUserProvider(userProvider);

                UserSocialVO userSocialVO = new UserSocialVO();
                userSocialVO.setUserSocialProvider(userProvider);
                userSocialVO.setUserSocialProviderId(userSocialProviderId);

                tokens = userService.registerSocial(newUser, userSocialVO);
            }


            String refreshToken = tokens.get("refreshToken");
            tokens.remove("refreshToken");

            // 5. redis로 교환하기 위한 key를 등록
            String key = UUID.randomUUID().toString();
            redisTemplate.opsForHash().putAll(key, tokens);
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);

            // 6. redis에 refresh 토큰을 등록 (검증)
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setUserId(userId);
            tokenDTO.setRefreshToken(refreshToken);
            oauthService.saveRefreshToken(tokenDTO);

            // 7. 쿠키에 심는다
            ResponseCookie cookie = ResponseCookie.from("refreshToken",  refreshToken)
                    .httpOnly(true) // *필수
//                .secure(true) // https에서 사용
                    .path("/") // 모든 경로에 쿠키 전송 사용
                    .maxAge(60 * 60 * 24 * 7)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            // 7. 프론트 React 리다이렉트(3000번포트로)
            String redirectUrl = "http://localhost:3000/oauth2/success?key=" + key;
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        }
    }
}
























