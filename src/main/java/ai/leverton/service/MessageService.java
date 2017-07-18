package ai.leverton.service;

import ai.leverton.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {


    public Message startDiscussion(String text){
        Message rootMsg = new Message( text);
        return rootMsg;
    }

    public Message addAnswer(String text){
        Message rootMsg = new Message(text);
        return rootMsg;
    }


}
