package com.example.demo.services;

import com.example.demo.model.Demo;
import com.example.demo.repositories.DemoRepository;
import  org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {
    DemoRepository demoRepository;

    public DemoServiceImpl(DemoRepository demoRepository){
        this.demoRepository = demoRepository;
    }

    @Override
    public List<Demo> getDemos() {
        List<Demo> demos = new ArrayList<>();
        demoRepository.findAll().forEach(demos::add);
        return demos;
    }

    @Override
    public Demo getDemoById(Long id) {
        Optional<Demo> demo = demoRepository.findById(id);
        return demo.orElse(null);
    }

    @Override
    public Demo insertDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    @Override
    public Demo updateDemo(Long id,Demo demo) {
        Demo demoFromDb = demoRepository.findById(id).get();
        demoFromDb.setTitle(demo.getTitle());
        demoFromDb.setDescription(demo.getDescription());
        demoFromDb.setDemoStatus(demo.getDemoStatus());
        return demoRepository.save(demoFromDb);
    }

    @Override
    public void deleteDemo(Long id) {
        demoRepository.deleteById(id);
    }
}
