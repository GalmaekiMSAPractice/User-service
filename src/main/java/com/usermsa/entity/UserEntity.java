package com.usermsa.entity;

import com.usermsa.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String userId;
    private String encryptPw;

    public UserEntity(UserDto dto) {
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.userId = dto.getUserId();
        this.encryptPw = dto.getEncryptPw();
    }
}
