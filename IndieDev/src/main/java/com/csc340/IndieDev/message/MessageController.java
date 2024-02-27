package com.csc340.IndieDev.message;
import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserRepository;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.sql.Timestamp;
import java.util.List;


@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            User userDetails = userRepository.findByUsername(user.getUsername()).orElse(null);

            if (userDetails != null) {
                return userDetails.getId();
            }
        }
        return null;
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam Long recipientId, @RequestParam String messageBody, RedirectAttributes redirectAttributes) {
        String senderName = SecurityContextHolder.getContext().getAuthentication().getName();
        User sender = userService.getUserByUserName(senderName);

        //Create and send message
        sendMessage(sender.getId(), recipientId, messageBody);

        //Create a redirect attribute for url
        redirectAttributes.addAttribute("userId", recipientId);

        // Redirect to the chat or inbox page
        return "redirect:/chats/{userId}";
    }
    public void sendMessage(Long senderId, Long recipientId, String messageBody) {
        Message message = new Message();
        message.setAuthorId(senderId);
        message.setRecipientId(recipientId);
        message.setBody(messageBody);
        messageService.saveMessage(message);
    }

    @GetMapping("/get-updates")
    public ResponseEntity<List<Message>> getUpdates() {
        Long currentUserId = getCurrentUserId();
        List<Message> latestMessages = messageService.getLatestMessages(currentUserId);

        //Return the data in response
        return new ResponseEntity<>(latestMessages, HttpStatus.OK);
    }


}
