package com.alura.forohub.domain.services;

import com.alura.forohub.domain.dto.reply.ReplyCreationDTO;
import com.alura.forohub.domain.dto.reply.ReplyDataDTO;
import com.alura.forohub.domain.dto.reply.ReplyUpdateDto;
import com.alura.forohub.domain.models.Reply;
import com.alura.forohub.domain.repositories.ReplyRepository;
import com.alura.forohub.domain.repositories.TopicRepository;
import com.alura.forohub.domain.repositories.UserRepository;
import com.alura.forohub.domain.validations.reply.CreateReplyValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private List<CreateReplyValidation> createValidations;

    public Reply createReply(ReplyCreationDTO replyCreationDTO) {
        createValidations.forEach(v -> v.validate(replyCreationDTO));
        var user = userRepository.findById(replyCreationDTO.authorId())
                .orElseThrow(() -> new ValidationException("El usuario ingresado no existe"));
        var topic = topicRepository.findById(replyCreationDTO.topicId())
                .orElseThrow(() -> new ValidationException("El topico ingresado no existe"));

        var reply = new Reply(replyCreationDTO, user, topic);
        replyRepository.save(reply);
        return reply;
    }

    public ReplyDataDTO getReplyById(Long id) {
        return new ReplyDataDTO(replyRepository.getReferenceById(id));
    }

    public ReplyDataDTO editReply(ReplyUpdateDto replyUpdateDto, Long id) {
        var reply = replyRepository.findById(id).
                orElseThrow(() -> new ValidationException("La respuesta ingresada no existe"));
        reply.editReply(replyUpdateDto.message());
        return new ReplyDataDTO(reply);
    }

    public ReplyDataDTO markReplyAsSolution(Long id) {
        var reply = replyRepository.findById(id).
                orElseThrow(() -> new ValidationException("La respuesta ingresada no existe"));
        reply.markAsSolution();
        return new ReplyDataDTO(reply);
    }

    public void deleteReply(Long id){
        var reply = replyRepository.findById(id).
                orElseThrow(() -> new ValidationException("La respuesta ingresada no existe"));
        reply.deleteReply();
    }
}
