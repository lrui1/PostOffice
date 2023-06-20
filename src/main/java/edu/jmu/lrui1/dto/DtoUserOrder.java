package edu.jmu.lrui1.dto;

import edu.jmu.lrui1.entity.User;
import lombok.Data;

/**
 * @author: lrui1
 * @description
 * @date: 2023/6/21
 */
@Data
public class DtoUserOrder {
    private DtoPage page;
    private User user;
}
