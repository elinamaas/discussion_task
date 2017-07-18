package ai.leverton.api;

import ai.leverton.domain.Discussion;
import ai.leverton.domain.Message;
import ai.leverton.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/discussion")
public class DiscussionApi {

    @Autowired
    private DiscussionService discussionService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Discussion<Message>> startDiscussion(String text) {
        final HttpHeaders headers = new HttpHeaders();

        Discussion<Message> discussion = discussionService.startDiscussion( text);

        return new ResponseEntity<>(discussion, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Integer> getNumberSubEntries(Discussion<Message> discussion) {
        final HttpHeaders headers = new HttpHeaders();

        int childrenNumber = 0;
        childrenNumber = discussionService.getNumberSubEntries(discussion, childrenNumber);

        return new ResponseEntity<>(childrenNumber, headers, HttpStatus.OK);
    }


}
