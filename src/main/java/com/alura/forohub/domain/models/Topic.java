package com.alura.forohub.domain.models;

import com.alura.forohub.domain.dto.topic.TopicCreationDTO;
import com.alura.forohub.domain.dto.topic.TopicUpdateDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topic")
@Entity(name = "Topic")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "author_id")
    private User author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<Reply> replies;

    public Topic(TopicCreationDTO topicCreationDTO, User user, Course course) {
        this.title = topicCreationDTO.title();
        this.message = topicCreationDTO.message();
        this.creationDate = LocalDateTime.now();
        this.status = Status.OPEN;
        this.author = user;
        this.course = course;
    }

    public void updateTopic(TopicUpdateDTO topicUpdateDTO){
        if(topicUpdateDTO.title() != null) this.title = topicUpdateDTO.title();
        if(topicUpdateDTO.message() != null) this.message = topicUpdateDTO.message();
        if(topicUpdateDTO.status() != null) this.status = topicUpdateDTO.status();
    }

    public void closeTopic(){
        this.status = Status.CLOSED;
    }
}
