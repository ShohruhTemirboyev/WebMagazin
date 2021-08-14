package com.example.webmagazin.repository;

import com.example.webmagazin.entity.enam.Role;
import com.example.webmagazin.entity.enam.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(RoleName roleName);
}
