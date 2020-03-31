package org.fomky.proxy.pool.entity;

import lombok.Builder;
import lombok.Data;
import org.apache.http.NameValuePair;

import java.io.Serializable;
import java.util.List;

/**
 * Create AT 2020/3/16 15:48:15:48
 *
 * @author Primi.Chen
 */
@Data
@Builder
public class ProxyPage implements Serializable {

    private String name;

    private String url;

    private HttpMethod method;

    private List<NameValuePair> formData;

    private Boolean needProxy;
}
