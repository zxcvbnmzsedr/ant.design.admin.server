package com.tianzeng.react.moudel;

import com.tianzeng.react.enums.SourcePermissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by tianzeng on 17-4-22.
 * 资源表
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_source")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // 资源的名称

    private String description; // 资源描述


}
