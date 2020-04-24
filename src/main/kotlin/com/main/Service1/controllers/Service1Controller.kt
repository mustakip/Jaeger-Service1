package com.main.Service1.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity


@RestController
class Service1Controller {

    @GetMapping("/")
    fun helloWorld(
            @RequestHeader
            headers: Map<String, String>
    ): String {
        return "HELLO WORLD FROM SERVICE 1";
    }
}