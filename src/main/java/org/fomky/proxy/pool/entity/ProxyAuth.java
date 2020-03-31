package org.fomky.proxy.pool.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Create AT 2020/3/31 15:55:15:55
 *
 * @author Primi.Chen
 */
@Data
@Builder
public class ProxyAuth implements Serializable {
    private String username;
    private String password;
}
