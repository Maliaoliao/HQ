package com.hq.common.exception.wx;

import com.hq.common.exception.BaseException;

/**
 * description.
 *
 * @author: mall
 * @date: 2023-01-06 14:02
 **/
public class WxPayException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WxPayException() {
        super();
    }

    public WxPayException(String message) {
        super(message);
    }

}
