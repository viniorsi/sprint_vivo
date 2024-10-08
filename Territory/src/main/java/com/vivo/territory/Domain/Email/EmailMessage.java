package com.vivo.territory.Domain.Email;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EmailMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String to;
    private String subject;
    private String body;
//    private List<String> attachments;
}
