package com.tianzeng.react.service;

import com.tianzeng.react.dao.SourceRepository;
import com.tianzeng.react.moudel.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianzeng on 17-4-22.
 */
@Service
public class SourceService {
    @Autowired
    private SourceRepository sourceRepository;

    public Source findByName(String name){
        return sourceRepository.findByName(name);
    }

    public List<Source> findAll(){
        return sourceRepository.findAll();
    }
}
