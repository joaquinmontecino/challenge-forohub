package com.alura.forohub.domain.services;

import com.alura.forohub.domain.dto.reply.ReplyDataDTO;
import com.alura.forohub.domain.dto.topic.TopicDataDTO;
import com.alura.forohub.domain.dto.user.UserCreationDTO;
import com.alura.forohub.domain.dto.user.UserDataDTO;
import com.alura.forohub.domain.dto.user.UserUpdateDTO;
import com.alura.forohub.domain.models.User;
import com.alura.forohub.domain.repositories.ReplyRepository;
import com.alura.forohub.domain.repositories.TopicRepository;
import com.alura.forohub.domain.repositories.UserRepository;
import com.alura.forohub.domain.validations.user.DuplicatedUserValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private DuplicatedUserValidation duplicatedUserValidation;

    public User registerUser(UserCreationDTO userCreationDTO){
        duplicatedUserValidation.validate(userCreationDTO);
        String encodedPassword = passwordEncoder.encode(userCreationDTO.password());
        var user = new User(userCreationDTO, encodedPassword);
        userRepository.save(user);
        return user;
    }

    public UserDataDTO getUserById(Long id) {
        return new UserDataDTO(userRepository.getReferenceById(id));
    }

    public Page<UserDataDTO> getActiveUsers(Pageable pageable) {
        return userRepository.findAllByActiveTrue(pageable).map(UserDataDTO::new);
    }

    public Page<UserDataDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDataDTO::new);
    }

    public Page<TopicDataDTO> getAllTopicsByUser(Long id, Pageable pageable) {
        return topicRepository.findAllByAuthorId(id, pageable).map(TopicDataDTO::new);
    }

    public Page<ReplyDataDTO> getAllRepliesByUser(Long id, Pageable pageable) {
        return replyRepository.findAllByAuthorId(id, pageable).map((ReplyDataDTO::new));
    }

    public UserDataDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El usuario ingresado no existe"));
        if (userUpdateDTO.password() != null){
            String encodedPassword = passwordEncoder.encode(userUpdateDTO.password());
            user.updateUserData(userUpdateDTO, encodedPassword);
        } else {
            user.updateUserData(userUpdateDTO, null);
        }
        return new UserDataDTO(user);
    }

    public void removeUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El usuario ingresado no existe"));
        user.removeUser();
    }
}
