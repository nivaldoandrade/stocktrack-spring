package com.nasa.stocktrack.infra.bootstrap;

import com.nasa.stocktrack.domain.enums.RoleEnum;
import com.nasa.stocktrack.infra.persistence.entities.RoleEntity;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import com.nasa.stocktrack.infra.persistence.repositories.RoleRepository;
import com.nasa.stocktrack.infra.persistence.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(2)
public class CreateUserAdmin implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public CreateUserAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createAdmin();
    }

    private void createAdmin() {
        Optional<RoleEntity> roleOptional = this.roleRepository.findByName(RoleEnum.ADMIN);

        if(roleOptional.isEmpty()) {
            throw new RuntimeException("Role admin is not found.");
        }

        Optional<UserEntity> userAdminExists = this.userRepository.findByRoleId(roleOptional.get().getId());

        if(userAdminExists.isPresent()) {
            return;
        }

        String password = "12345678";

        String hashPassword = passwordEncoder.encode(password);

        UserEntity user = new UserEntity();

        user.setFull_name("admin");
        user.setUsername("admin");
        user.setPassword(hashPassword);
        user.setRole(roleOptional.get());

        this.userRepository.save(user);
        System.out.println("User admin has been created!");
        System.out.println("Usuário de teste: ");
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + password);
    }
}
