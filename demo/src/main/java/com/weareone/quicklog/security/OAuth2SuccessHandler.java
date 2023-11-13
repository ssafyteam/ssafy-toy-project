package com.weareone.quicklog.security;

import com.weareone.quicklog.dto.JwtToken;
import com.weareone.quicklog.dto.Role;
import com.weareone.quicklog.dto.oauth.CustomOAuth2User;
import com.weareone.quicklog.entity.Token;
import com.weareone.quicklog.repository.TokenRepository;
import com.weareone.quicklog.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            log.info("OAuth2 Login 성공!");
            try {
                CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

                // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트
                if(oAuth2User.getRole() == Role.GUEST) {
                    JwtToken token = jwtTokenProvider.generateToken(oAuth2User.getEmail());
                    String accessToken = token.getAccessToken();
                    response.addHeader(jwtTokenProvider.getAccessHeader(), "Bearer " + accessToken);
                    response.sendRedirect("users/signin"); // 프론트의 회원가입 추가 정보 입력 폼으로 리다이렉트

                    jwtTokenProvider.sendAccessAndRefreshToken(response, accessToken, null);
                } else {
                    loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
                }
            } catch (Exception e) {
                throw e;
            }

        }

        // TODO : 소셜 로그인 시에도 무조건 토큰 생성하지 말고 JWT 인증 필터처럼 RefreshToken 유/무에 따라 다르게 처리해보기
        private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
            JwtToken token = jwtTokenProvider.generateToken(oAuth2User.getEmail());
            String accessToken = token.getAccessToken();
            String refreshToken = token.getRefreshToken();
            response.addHeader(jwtTokenProvider.getAccessHeader(), "Bearer " + accessToken);
            response.addHeader(jwtTokenProvider.getRefreshHeader(), "Bearer " + refreshToken);

            jwtTokenProvider.sendAccessAndRefreshToken(response, accessToken, refreshToken);
            tokenRepository.save(new Token(token.getRefreshToken()));
        }
}
