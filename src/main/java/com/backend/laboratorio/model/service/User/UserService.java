package com.backend.laboratorio.model.service.User;

import com.backend.laboratorio.model.entity.User;
import com.backend.laboratorio.model.repository.UsuRol.IRoleRepository;
import com.backend.laboratorio.model.repository.UsuRol.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User addUser(User user, List<Long> rolIds) {
        return null;
    }

    @Override
    public void remove(User user) {

    }
}
