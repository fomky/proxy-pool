package org.primi.proxy.pool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Create AT 2020/3/16 14:03:14:03
 *
 * @author Primi.Chen
 */
@AllArgsConstructor
public enum ProxyStatus implements Serializable {
    /**
     * can use it;
     */
    ENABLE(1),
    /**
     * can't use , not active
     */
    DISABLE(1);
    /**
     * init status
     */

    @Getter
    int value;
}
