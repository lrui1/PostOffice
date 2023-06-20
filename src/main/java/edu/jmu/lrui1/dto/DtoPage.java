package edu.jmu.lrui1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lrui1
 * @description
 * @date: 2023/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPage {
    private Long currentPage;
    private Long pageSize;
    private Long totalPages; //一共多少页
    private Long total; // 一共多少条
    private Object data;
}
