package com.tianzeng.react.moudel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tianzeng on 17-4-22.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sec_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String value;

    private String url;

    private String intro;

    private String pid;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private String method;


}
