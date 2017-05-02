package com.tianzeng.react.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.dao.PermissionRepository;
import com.tianzeng.react.moudel.Permission;
import com.tianzeng.react.moudel.Result;
import com.tianzeng.react.moudel.Source;
import com.tianzeng.react.service.SourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianzeng on 17-4-26.
 */
@RestController
@RequestMapping("/api/sources")
public class SourceController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private SourceService sourceService;
    @Autowired
    private PermissionRepository permissionRepository;
    @GetMapping
    public Result sources(){
        Result result = new Result();
        List<Source> sourceList = sourceService.findAll();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Permission.class, "id","permissions","description");
        Assert.notNull(sourceList,"资源列表为空");
        logger.info(JSON.toJSONString(sourceList,filter));
        result.setObj(JSONObject.parse(JSON.toJSONString(sourceList,filter)));
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id){
        if(id.contains(",")){
            String s = id.split(",")[0];
            Permission permission = new Permission();
            permission.setId(Long.valueOf(s));
            permissionRepository.save(permission);
        }else {
            sourceService.delete(Long.valueOf(id));
        }
    }

}
