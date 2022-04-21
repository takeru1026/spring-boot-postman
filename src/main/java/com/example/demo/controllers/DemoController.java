package com.example.demo.controllers;

import com.example.demo.model.Demo;
import com.example.demo.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/todo")
public class DemoController {
    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoservice) {
        this.demoService = demoservice;
    }

    @GetMapping
    public ResponseEntity<List<Demo>> getAllDemos() {
        return new ResponseEntity<>(demoService.getDemos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demo> getDemo(@PathVariable Long id) {
        Demo demo = demoService.getDemoById(id);
        return demo != null ? new ResponseEntity<>(demo, HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/post")
    public ResponseEntity<Demo> saveDemo(@RequestBody Demo demo) {
        Demo newDemo = demoService.insertDemo(demo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("demo", "/api/v1/demo/" + newDemo.getId().toString());
        return new ResponseEntity<>(newDemo, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Demo> updateDemo(@PathVariable Long id, @RequestBody Demo demo) {
        Demo demoFromDb = demoService.getDemoById(id);
        if(demoFromDb != null) {
            demoService.updateDemo(id, demo);
        }
        return demoFromDb != null ? new ResponseEntity<>(demoService.getDemoById(id), HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Demo> deleteDemo(@PathVariable Long id) {
        Demo demoFromDb = demoService.getDemoById(id);
        if (demoFromDb != null) {
            demoService.deleteDemo(id);
        }
        return demoFromDb != null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                :new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
