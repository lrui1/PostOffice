package edu.jmu.lrui1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lrui1
 * @description
 * @date: 2023/6/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private Object data;
    private String msg;

    public static Result success(Object o) {
        return new Result(Code.SUCCESS, o, "");
    }

    public static Result error(String msg) {
        return new Result(Code.ERROR, null, msg);
    }
}
