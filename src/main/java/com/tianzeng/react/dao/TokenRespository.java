package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by tianzeng on 17-4-22.
 */
@RepositoryRestResource
public interface TokenRespository extends JpaRepository<TokenModel,Long>{
    TokenModel findByUserId(Long userId);

    TokenModel findByToken(String token);
}
