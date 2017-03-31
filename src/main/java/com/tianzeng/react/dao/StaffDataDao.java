package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.StaffDataMoudel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by tianzeng on 2017-03-26.
 */
public interface StaffDataDao extends PagingAndSortingRepository<StaffDataMoudel,Long> {
    void deleteByJobNumber(String jobNumber);
}
