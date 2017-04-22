package com.tianzeng.react.moudel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by tianzeng on 17-4-19.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel  {

    // 用户 id
    @Id
    private Long userId;
    // 随机生成的 uuid
    private String token;
}
