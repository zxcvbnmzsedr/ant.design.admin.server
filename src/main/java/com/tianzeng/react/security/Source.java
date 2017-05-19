package com.tianzeng.react.security;

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
@Table(name = "sec_source")
//@JsonIgnoreProperties(value = { "permissions","hibernateLazyInitializer", "handler"})
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // 资源描述

    private String httpUrl; // 资源地址

    @OneToMany(mappedBy = "source",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
    private List<Permission> permissions;

}
