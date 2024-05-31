package com.backend.laboratorio.model.repository.UsuRol;

import com.backend.laboratorio.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

}
