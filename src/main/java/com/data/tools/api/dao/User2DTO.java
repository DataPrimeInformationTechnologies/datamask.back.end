package com.data.tools.api.dao;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User2DTO implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private Boolean active;
}
