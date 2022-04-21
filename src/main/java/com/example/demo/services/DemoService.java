package com.example.demo.services;

import com.example.demo.model.Demo;

import java.util.List;

public interface DemoService {
    List<Demo> getDemos();
    Demo getDemoById(Long id);
    Demo insertDemo(Demo Demo);
    Demo updateDemo(Long id, Demo demo);
    void deleteDemo(Long id);
}
