package com.soundbridge.domain.member.controller;

import com.soundbridge.domain.member.entity.Role;
import com.soundbridge.domain.member.request.ModifyNicknameReq;
import com.soundbridge.domain.member.request.ModifyProfileReq;
import com.soundbridge.domain.member.request.SaveRoleReq;
import com.soundbridge.domain.member.response.MemberInfoRes;
import com.soundbridge.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "member", description = "member API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    @Operation(summary = "내 정보 조회", description = "내 정보 조회 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity getMemberInfo(@PathVariable Long memberId) {
        //401 error : 는 인증이 안된유저임 즉 토큰이 없거나 만료된 유저, ExceptionHandlerFilter에서 처리해야함.
        log.info("request 내 정보 조회 ID {}", memberId);
//        MemberAccessRes memberAccessRes = (MemberAccessRes) Optional.ofNullable(
//            SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get();

//        if (memberId != memberAccessRes.getId()) { //권한 없음,
//            throw new AccessDeniedException(ErrorCode.NOT_AUTHORIZATION);
//        }

        MemberInfoRes member = memberService.getMemberById(memberId);

        return ResponseEntity.ok(member);
    }


    @Operation(summary = "회원 닉네임 수정", description = "닉네임 수정 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/nickname/{memberId}")
    public ResponseEntity modifyMemberNickname(@PathVariable Long memberId, @RequestBody
    ModifyNicknameReq modifyNicknameReq) {

        String nickname = modifyNicknameReq.getNickname();

        log.info("request 닉네임 수정: {}", nickname);

        memberService.modifyMemberNickname(memberId, nickname);

        return ResponseEntity.ok().body(nickname);
    }


    @Operation(summary = "프로필 이미지 수정", description = "프로필 이미지 수정 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
        @ApiResponse(responseCode = "413", description = "파일용량 초과"),
        @ApiResponse(responseCode = "415", description = "지원하지않는 확장자"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/profile/{memberId}")
    public ResponseEntity modifyMemberProfile(@PathVariable Long memberId, @ModelAttribute @Valid
    ModifyProfileReq profileReq) {

        String newProfileName = memberService.modifyMemberProfile(memberId,
            profileReq.getProfile());

        return ResponseEntity.ok().body(newProfileName);
    }


    @Operation(summary = "역할 저장", description = "내 역할을 저장하는 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/role/{memberId}")
    public ResponseEntity saveRoleMember(@PathVariable Long memberId,
        @RequestBody SaveRoleReq saveRoleReq) {
        log.info("request 역할 {} ", saveRoleReq);
        Role role = memberService.saveRole(memberId, saveRoleReq);

        return ResponseEntity.ok().body(role);
    }
}