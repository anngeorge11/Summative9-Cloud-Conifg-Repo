package com.trilogyed.stwitter.util.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "comment-service")
public interface CommentClient {
//
//    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
//    public String getRandomGreeting();

}
