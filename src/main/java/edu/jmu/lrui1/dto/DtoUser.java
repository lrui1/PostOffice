package edu.jmu.lrui1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lrui1
 * @description 登录返回的用户信息
 * @date: 2023/6/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
    private String username;
    private Integer identity;
}
