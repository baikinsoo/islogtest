package com.islogtest.service;

import com.islogtest.domain.Post;
import com.islogtest.repository.PostRepository;
import com.islogtest.request.PostCreate;
import com.islogtest.request.PostSearch;
import com.islogtest.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(postCreate);

        //then
        Assertions.assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        //given
        Post request = Post.builder()
                .title("name")
                .content("insoo")
                .build();
        postRepository.save(request);

        //when
        PostResponse postResponse = postService.get(request.getId());

        //then
        assertEquals(1L, postRepository.count());
        assertEquals("name", request.getTitle());
        assertEquals("insoo",request.getContent());
    }

    @Test
    @DisplayName("글 다건 조회")
    void test3() {
        postRepository.saveAll(List.of(
                Post.builder()
                        .title("Title_1")
                        .content("Content_1")
                        .build(),
                Post.builder()
                        .title("Title_2")
                        .content("Content_2")
                        .build()
        ));

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        List<PostResponse> postResponses = postService.getList(postSearch);

        assertEquals(2L, postResponses.size());
    }

    @Test
    @DisplayName("그냥 테스트")
    void justTest() {
        //given
        Post request1 = Post.builder()
                .title("name123123")
                .content("insoo")
                .build();
        postRepository.save(request1);
        //given
        Post request2 = Post.builder()
                .title("123123123123name")
                .content("insoo")
                .build();
        postRepository.save(request2);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        List<PostResponse> postResponses = postService.getList(postSearch);
    }
}