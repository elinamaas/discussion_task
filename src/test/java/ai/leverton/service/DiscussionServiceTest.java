package ai.leverton.service;

import ai.leverton.domain.Discussion;
import ai.leverton.domain.Message;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DiscussionServiceTest extends BaseTest {

    @Autowired
    private DiscussionService discussionService;

    @Test
    public void startDiscussion() throws Exception {
        Discussion<Message> discussion = discussionService.startDiscussion("msg1");

        assertThat(discussion.getMsg().getText(), is("msg1"));
        assertThat(discussion.getChildren().size(), is(0));
    }

    @Test
    public void addAnswer() throws Exception {
        Discussion<Message> discussion1 = discussionService.startDiscussion("msg1");
        Discussion<Message> discussion2 = discussionService.startDiscussion("msg2");

        discussion1.addChild(discussion2);

        assertThat(discussion1.getMsg().getText(), is("msg1"));
        assertThat(discussion1.getChildren().size(), is(1));

        assertThat(discussion2.getMsg().getText(), is("msg2"));
        assertThat(discussion2.getChildren().size(), is(0));

    }

    @Test
    public void removeMsg() throws Exception {
        Discussion<Message> discussion1 = discussionService.startDiscussion("msg1");
        Discussion<Message> discussion2 = discussionService.startDiscussion("msg2");
        Discussion<Message> discussion3 = discussionService.startDiscussion("msg3");

        discussion1.addChild(discussion2);
        discussion2.addChild(discussion3);

        Message msg = new Message("msg2");

        discussionService.removeMsg(discussion2, msg);
        assertThat(discussion1.getChildren().size(), is(0));

    }

    @Test
    public void getNumberSubEntries() throws Exception {
        Discussion<Message> discussion1 = discussionService.startDiscussion("msg1");
        Discussion<Message> discussion2 = discussionService.startDiscussion("msg2");
        Discussion<Message> discussion3 = discussionService.startDiscussion("msg3");

        discussion1.addChild(discussion2);
        discussion2.addChild(discussion3);

        int amount = 0;
        amount = discussionService.getNumberSubEntries(discussion1, amount);
        assertThat(amount, is(2));


    }

}