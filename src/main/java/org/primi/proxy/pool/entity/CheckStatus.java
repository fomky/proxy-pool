package org.primi.proxy.pool.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Create AT 2020/3/18 14:59:14:59
 *
 * @author Primi.Chen
 */
public enum CheckStatus implements Serializable {
    /** 成功 */
    SUCCESS,
    /** 失败 */
    FAIL;
    public static boolean isSuccess(CheckStatus status){
        return Objects.equals(SUCCESS,status);
    }
}
