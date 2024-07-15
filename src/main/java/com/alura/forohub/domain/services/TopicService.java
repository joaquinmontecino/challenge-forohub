package com.alura.forohub.domain.services;

import com.alura.forohub.domain.dto.reply.ReplyDataDTO;
import com.alura.forohub.domain.dto.topic.TopicCreationDTO;
import com.alura.forohub.domain.dto.topic.TopicDataDTO;
import com.alura.forohub.domain.dto.topic.TopicUpdateDTO;
import com.alura.forohub.domain.dto.user.UserDataDTO;
import com.alura.forohub.domain.models.Category;
import com.alura.forohub.domain.models.Course;
import com.alura.forohub.domain.models.Status;
import com.alura.forohub.domain.models.Topic;
import com.alura.forohub.domain.repositories.CourseRepository;
import com.alura.forohub.domain.repositories.ReplyRepository;
import com.alura.forohub.domain.repositories.TopicRepository;
import com.alura.forohub.domain.repositories.UserRepository;
import com.alura.forohub.domain.validations.topic.CreateTopicValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private List<CreateTopicValidation> createValidations;


    public Topic createTopic(TopicCreationDTO topicCreationDTO) {
        createValidations.forEach(v -> v.validate(topicCreationDTO));

        var user = userRepository.findById(topicCreationDTO.authorId())
                .orElseThrow(() -> new ValidationException("El usuario ingresado no existe"));
        var course = courseRepository.findById(topicCreationDTO.courseId())
                .orElseThrow(() -> new ValidationException("El curso ingresado no existe"));

        var topic = new Topic(topicCreationDTO, user, course);

        topicRepository.save(topic);
        return topic;
    }

    public TopicDataDTO getTopicById(Long id) {
        return new TopicDataDTO(topicRepository.getReferenceById(id));
    }

    public Page<TopicDataDTO> getOpenTopics(Pageable pageable){
        return topicRepository.findAllByStatusIsNot(Status.CLOSED, pageable).map(TopicDataDTO::new);
    }

    public Page<TopicDataDTO> getAllTopics(Pageable pageable){
        return topicRepository.findAll(pageable).map(TopicDataDTO::new);
    }

    public Page<TopicDataDTO> getTopicsByCourse(Long id, Pageable pageable) {
        return topicRepository.findAllByCourseId(id, pageable).map(TopicDataDTO::new);
    }

    public Page<ReplyDataDTO> getRepliesByTopicId(Long id, Pageable pageable){
        return replyRepository.findAllByTopicId(id, pageable).map((ReplyDataDTO::new));
    }

    public TopicDataDTO updateTopic(TopicUpdateDTO topicUpdateDTO, Long id){
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El topico ingresado no existe"));
        topic.updateTopic(topicUpdateDTO);
        return new TopicDataDTO(topic);
    }

    public void closeTopic(Long id) {
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El topico ingresado no existe"));
        topic.closeTopic();
    }
}
