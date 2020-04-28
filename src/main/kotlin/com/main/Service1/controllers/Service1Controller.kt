package com.main.Service1.controllers

import io.opentracing.Tracer
import io.opentracing.contrib.spring.web.client.TracingRestTemplateInterceptor
import io.opentracing.propagation.Format
import io.opentracing.util.GlobalTracer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity






@RestController
class Service1Controller {

    val restTemplate :RestTemplate = RestTemplate()

    @GetMapping("/")
    fun helloWorld(
            @RequestHeader
            headers: Map<String, String>
    ): String {
        val tracer = GlobalTracer.get()
        restTemplate.interceptors.add(TracingRestTemplateInterceptor(tracer))
        val responseEntity = restTemplate.getForEntity<String>("http://localhost:8085/helloUniverse");
        return "HELLO WORLD FROM SERVICE 1 & ${responseEntity.body}";
    }

    @GetMapping("/helloUniverse")
    fun helloUniverse(
            @RequestHeader
            headers: Map<String, String>
    ): String {
        println(headers)
        return "HELLO UNIVERSE FROM SERVICE 1 ";
    }



}