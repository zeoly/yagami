package com.yahacode.yagami.chat.scrollboard.action;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.chat.scrollboard.model.Post;
import com.yahacode.yagami.chat.scrollboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zengyongli 2018-10-08
 */
@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    private PostService postService;

    @GetMapping
    public List<Post> getALlPosts() {
        return postService.findAllPosts();
    }

    @PostMapping
    public void addPost(@RequestBody Post post) {
        postService.save(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable String postId) {
        postService.delete(postId);
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
