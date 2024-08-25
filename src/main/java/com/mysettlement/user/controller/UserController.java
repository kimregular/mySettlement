package com.mysettlement.user.controller;

import com.mysettlement.globalResponse.MySettlementGlobalResponse;
import com.mysettlement.user.entity.UserRole;
import com.mysettlement.user.exception.InvalidSigninRequestException;
import com.mysettlement.user.request.UserSigninRequestDto;
import com.mysettlement.user.response.UserResponseDto;
import com.mysettlement.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public MySettlementGlobalResponse<UserResponseDto> signsin(@RequestBody @Valid UserSigninRequestDto userSigninRequestDto, BindingResult errors) {
        if(errors.hasErrors()) throw new InvalidSigninRequestException(errors);
        return MySettlementGlobalResponse.success(userService.signinUser(userSigninRequestDto));
    }

    @GetMapping("/{username}")
    public MySettlementGlobalResponse<UserResponseDto> findUser(@PathVariable String username) {
        return MySettlementGlobalResponse.success(userService.findByUserName(username));
    }

    @PatchMapping("/{userId}")
    public MySettlementGlobalResponse<UserResponseDto> changeUserStatue(@PathVariable Long userId) {
        return MySettlementGlobalResponse.success(userService.changeUserStatus(userId, UserRole.DEFAULT));
    }

}
