package com.tianzeng.react.moudel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tianzeng.react.enums.SourcePermissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tianzeng on 17-4-22.
 * 资源表
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = { "permissions","hibernateLazyInitializer", "handler"})
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // 资源的名称

    private String description; // 资源描述

    @OneToMany(mappedBy = "source",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//    @JsonIgnore
    private List<Permission> permissions;

}
