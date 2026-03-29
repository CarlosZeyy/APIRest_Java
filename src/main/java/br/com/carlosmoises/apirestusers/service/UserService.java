package br.com.carlosmoises.apirestusers.service;

import br.com.carlosmoises.apirestusers.Controllers.CreateUserDto;
import br.com.carlosmoises.apirestusers.Controllers.UpdateUserDto;
import br.com.carlosmoises.apirestusers.entity.User;
import br.com.carlosmoises.apirestusers.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        var hashPassoword = encoder.encode(createUserDto.password());

        if (userRepository.existsByEmail(createUserDto.email())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        var entity = new User(null, createUserDto.username(), createUserDto.email(), hashPassoword, Instant.now(), null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var userEntity = userRepository.findById(UUID.fromString(userId));

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                var hashPassword = encoder.encode(updateUserDto.password());
                user.setPassword(hashPassword);
            }

            userRepository.save(user);
        }
    }

    public void deleteUserById(String userId) {
        var userExists = userRepository.existsById(UUID.fromString(userId));

        if (userExists) {
            userRepository.deleteById(UUID.fromString(userId));
        } else {
            throw new EntityNotFoundException("ID do usuário não encontrado.");
        }
    }
}
