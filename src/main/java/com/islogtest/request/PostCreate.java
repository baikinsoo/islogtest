package com.islogtest.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
public class PostCreate {

    @NotBlank(message = "title을 입력해주세요")
    private String title;

    @NotBlank(message = "content를 입력해주세요")
    private String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
