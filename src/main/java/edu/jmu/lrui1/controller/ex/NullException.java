package edu.jmu.lrui1.controller.ex;

/**
 * @author: lrui1
 * @description 空值异常
 * @date: 2023/6/17
 */
public class NullException extends RuntimeException{
    public NullException(String msg) {
        super(msg);
    }
}
