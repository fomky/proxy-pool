package org.primi.proxy.pool.entity;

import lombok.Builder;
import lombok.Data;
import org.jsoup.Connection.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Create AT 2020/3/16 15:48:15:48
 *
 * @author Primi.Chen
 */
@Data
@Builder
public class ProxyListPage implements Serializable {

    private String name;

    private String url;

    private Method method;

    private Map<String, String> parameters;

    private Boolean needProxy;
}
