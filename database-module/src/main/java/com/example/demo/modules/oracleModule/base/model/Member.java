package com.example.demo.modules.oracleModule.base.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/27
 */
@Data
public class Member {

        private static final long serialVersionUID = 1L;

        /**
         * id  NUMBER(16)
         */
        private Long id;

        /**
         * name VARCHAR2(10)
         */
        private String name;

        /**
         * age VARCHAR2(4)
         */
        private String age;

        /**
         * memberType CHAR(1)
         */
        private String memberType;

        /**
         * amount NUMBER(10)
         */
        private Long amount;

        /**
         * balance NUMBER(10,2)
         */
        private BigDecimal balance;

        /**
         * day DATE
         */
        private Date day;

        /**
         * createBy VARCHAR2(64)
         */
        private String createBy;

        /**
         * createTime VARCHAR2(64)
         */
        private Date createTime;

        /**
         * modifyBy  VARCHAR2(64)
         */
        private String modifyBy;

        /**
         * modifyTime TIMESTAMP(6)
         */
        private Date modifyTime;


}
