package org.primi.proxy.pool.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * Create AT 2020/3/16 14:08:14:08
 *
 * @author Primi.Chen
 */
@Data
public class ProxyStatistics implements Serializable {
    @Builder.ObtainVia(isStatic = true,method = "of")
    private String uid;
    private String hostname;
    private Integer port;
    private ProxyStatus status;
}
