package com.example.code.api;

import com.example.code.model.dto.RequestUserInfoDTO;
import com.example.code.model.dto.ResponseUserInfoDTO;
import com.example.code.services.UserService.UserInfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/users")
@Data
public class UserController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/registration")
    public ResponseEntity registerNewUser(@RequestBody RequestUserInfoDTO requestUserInfoDTO) {
        userInfoService.register(requestUserInfoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseUserInfoDTO> loginUser(@RequestBody RequestUserInfoDTO requestUserInfoDTO) {
        Optional<ResponseUserInfoDTO> responseUserInfoDTO = userInfoService.login(requestUserInfoDTO);
        if (responseUserInfoDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseUserInfoDTO.get());
    }
}
