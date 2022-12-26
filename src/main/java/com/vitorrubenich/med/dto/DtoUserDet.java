package com.vitorrubenich.med.dto;

import com.vitorrubenich.med.model.User;

public record DtoUserDet(String login) {
    public DtoUserDet(User user){
        this(user.getLogin());
    }
}
