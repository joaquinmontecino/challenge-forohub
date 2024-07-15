package com.alura.forohub.domain.validations.reply;

import com.alura.forohub.domain.dto.reply.ReplyCreationDTO;

public interface CreateReplyValidation {
    public void validate(ReplyCreationDTO data);
}
