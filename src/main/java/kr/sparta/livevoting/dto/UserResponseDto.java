package kr.sparta.livevoting.dto;

import kr.sparta.livevoting.entity.Users;
public record UserResponseDto(
        String id,
        String name
) {
    public static UserResponseDto from(Users users) {
        return new UserResponseDto(
                users.getLoginId(),
                users.getNickname()
        );
    }
}
