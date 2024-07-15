package com.alura.forohub.domain.models;

import com.alura.forohub.domain.dto.reply.ReplyCreationDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "reply")
@Entity(name = "Reply")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    private Boolean deleted;
    private Boolean solution;


    public Reply(ReplyCreationDTO replyCreationDTO, User user, Topic topic){
        this.message = replyCreationDTO.message();
        this.topic = topic;
        this.creationDate = LocalDateTime.now();
        this.author = user;
        this.deleted = false;
        this.solution = false;
    }

    public void editReply(String message) {
        this.message = message;
    }

    public void markAsSolution(){
        this.solution = true;
    }

    public void deleteReply(){
        this.deleted = true;
    }

}
