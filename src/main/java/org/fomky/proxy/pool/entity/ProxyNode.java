package org.fomky.proxy.pool.entity;

import lombok.Builder;
import lombok.Data;
import org.apache.http.HttpHost;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;


/**
 * Create AT 2020/3/16 14:08:14:08
 *
 * @author Primi.Chen
 */
@Data
@Builder
public class ProxyNode implements Serializable {
    private String uid;

    private Proxy.Type type;

    private String hostname;

    private Integer port;

    private ProxyStatus status;

    private ProxyAuth auth;

    public String key() {
        return String.format("%s@%s:%s", type.toString(), hostname, port);
    }

    public Proxy proxy() {
        return new Proxy(type, new InetSocketAddress(hostname, port));
    }
    public HttpHost httpHost() {
        return new HttpHost(hostname, port);
    }
}
