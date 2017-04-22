package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tianzeng on 17-4-22.
 */
public interface TokenRespository extends JpaRepository<TokenModel,Long>{
    TokenModel findByUserId(Long userId);

    TokenModel findByToken(String token);
}
