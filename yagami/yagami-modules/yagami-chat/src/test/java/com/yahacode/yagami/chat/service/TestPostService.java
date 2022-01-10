package com.yahacode.yagami.chat.service;

import com.yahacode.yagami.base.common.ServletContextHolder;
import com.yahacode.yagami.base.consts.SessionKeyConsts;
import com.yahacode.yagami.chat.BaseTest;
import com.yahacode.yagami.chat.scrollboard.model.Post;
import com.yahacode.yagami.chat.scrollboard.model.Reply;
import com.yahacode.yagami.chat.scrollboard.service.PostService;
import com.yahacode.yagami.chat.scrollboard.service.ReplyService;
import com.yahacode.yagami.pd.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zengyongli 2018-10-12
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPostService extends BaseTest {

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @Before
    public void before() {
        Person people = Person.builder().code("test").name("测试用户").build();
        ServletContextHolder.getSession().setAttribute(SessionKeyConsts.LOGIN_PEOPLE, people);
    }

    @Test
    public void a1_testSavePost() {
        Post post = Post.builder().title("test post tile").content("test post content").build();
        String id = postService.save(post);
        Assert.assertNotNull(id);
    }

    @Test
    public void a2_testFindAllPosts() {
        List<Post> posts = postService.findAllPosts();
        Assert.assertTrue(posts.size() > 0);
    }

    @Test
    public void b1_testSaveReply() {
        List<Post> posts = postService.findAllPosts();
        Reply reply1 = Reply.builder().postId(posts.get(0).getId()).content("test reply content 1").build();
        String id = replyService.save(reply1);
        Assert.assertNotNull(id);

        Reply reply2 = Reply.builder().postId(posts.get(0).getId()).content("test reply content 2").build();
        id = replyService.save(reply2);
        Assert.assertNotNull(id);
    }

    @Test
    public void b2_testGetAllReplies() {
        List<Post> posts = postService.findAllPosts();
        List<Reply> replies = replyService.getAllReplies(posts.get(0).getId());
        Assert.assertTrue(replies.size() > 1);
    }

}
