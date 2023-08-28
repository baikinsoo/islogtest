package com.islogtest.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
public class PostCreate {

    @NotBlank(message = "title을 입력해주세요")
    private String title;

    @NotBlank(message = "content를 입력해주세요")
    private String content;
}
