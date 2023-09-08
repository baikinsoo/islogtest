package com.islogtest.repository;

import com.islogtest.domain.Post;
import com.islogtest.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
