package com.soundbridge.domain.member.controller;

import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.member.response.MemberAccessRes;
import com.soundbridge.domain.member.service.TokenService;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/token")
public class TokenController {

    private final MemberRepository memberRepository;

    private final TokenService tokenService;
//    private final RedisTemplate redisTemplate;

    @Operation(summary = "Access 토큰 재발급", description = "Access 토큰 재발급 메소드입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "사용자 없음"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/re-issue")
    public String refreshAuth(HttpServletResponse response) {
        log.info("refresh 진입");
        try {
            MemberAccessRes memberAccessRes = (MemberAccessRes) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
            response.addHeader("access-token", memberAccessRes.getAccessToken());
            log.info(memberAccessRes.getAccessToken());
            return memberAccessRes.getAccessToken();
        } catch (Exception e){
            throw new AccessDeniedException(ErrorCode.NOT_AUTHENTICATION);
        }
    }
}