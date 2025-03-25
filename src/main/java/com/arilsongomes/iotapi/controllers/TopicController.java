package com.arilsongomes.iotapi.controllers;

import com.arilsongomes.iotapi.dto.topic.TopicRequestDto;
import com.arilsongomes.iotapi.dto.topic.TopicResponseDto;
import com.arilsongomes.iotapi.security.GetEmailFomUserAuhtenticaed;
import com.arilsongomes.iotapi.services.topic.TopicServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/topic")
@RequiredArgsConstructor
public class TopicController {
    private final GetEmailFomUserAuhtenticaed getEmailFomUserAuhtenticaed;
    private final TopicServiceImpl topicService;

    @PostMapping("/subscribe/{deviceId}")
    public String registerTopic(@PathVariable String deviceId,@RequestBody @Valid TopicRequestDto requestDto){
        String email = getEmailFomUserAuhtenticaed.getAuthenticatedEmail();
        topicService.registerTopic(requestDto, email, deviceId);
        return "Topic registed";
    }

    @GetMapping
    public List<TopicResponseDto> getAllTopic(){
        String email = getEmailFomUserAuhtenticaed.getAuthenticatedEmail();
        return topicService.getAllTopic(email);
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<?> updateTopic(
            @PathVariable Long topicId,
            @RequestBody @Valid TopicRequestDto requestDto
    ){
        String email = getEmailFomUserAuhtenticaed.getAuthenticatedEmail();
        topicService.updateTopic(topicId, requestDto, email);
        return ResponseEntity.ok().body(Map.of("message", "topic updated sucess"));
    }


    @DeleteMapping("/{topicId}")
    public ResponseEntity<?> removeTopic(@PathVariable Long topicId){
        String email = getEmailFomUserAuhtenticaed.getAuthenticatedEmail();
        topicService.removeTopic(topicId, email);
        return ResponseEntity.ok().body(Map.of("message", "topic remove sucess"));
    }
}
