package com.backend.laboratorio.model.service.Authentication;

import com.backend.laboratorio.model.entity.User;

public interface IAuthenticationService {

    public User signup(User newUser);

    public User authenticate(User user);

}
