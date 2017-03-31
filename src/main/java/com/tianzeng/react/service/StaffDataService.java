package com.tianzeng.react.service;

import com.tianzeng.react.dao.StaffDataDao;
import com.tianzeng.react.dao.UserDao;
import com.tianzeng.react.moudel.Result;
import com.tianzeng.react.moudel.StaffDataMoudel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by tianzeng on 2017-03-26.
 */
@Service
public class StaffDataService {
    @Autowired
    private StaffDataDao staffDataDao;

    @Autowired
    private UserDao userDao;
    public Page<StaffDataMoudel> listStaffData(Integer page,Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return staffDataDao.findAll(pageable);
    }

    public void save(StaffDataMoudel staffDataMoudel){
        staffDataDao.save(staffDataMoudel);
    }

    public Result remove(String jobNumber) {
        Result result = new Result();
        try {
            userDao.delete(jobNumber);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
