package org.fomky.proxy.pool.tools;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.impl.client.BasicCookieStore;
import org.fomky.proxy.pool.entity.HttpMethod;
import org.fomky.proxy.pool.entity.ProxyAuth;
import org.fomky.proxy.pool.entity.ProxyNode;

import java.io.IOException;
import java.util.*;

/**
 * Create AT 2020/3/31 15:52:15:52
 *
 * @author Primi.Chen
 */
@Data
@Slf4j
@Builder
public class HttpClients {
    private String uri;

    private Map<String, String> parameters;

    private Map<String, String> headers;

    private List<NameValuePair> formData;

    private HttpEntity httpEntity;

    private ProxyNode proxy;

    private BasicCookieStore basicCookieStore;

    private ProxyAuth proxyAuth;

    public HttpClients header(String key, String value) {
        Optional.ofNullable(this.headers).orElse(new HashMap<>()).put(key, value);
        return this;
    }

    public HttpClients header(Map<String, String> headers) {
        Optional.ofNullable(this.headers).orElse(new HashMap<>()).putAll(headers);
        return this;
    }

    public Response get() throws IOException {
        return this.execute(Request.Get(uri));
    }

    public Response post() throws IOException {
        Request request = Request.Post(uri);
        return this.execute(request);
    }
    public Response delete() throws IOException {
        Request request = Request.Delete(uri);
        return this.execute(request);
    }

    public Response put() throws IOException {
        Request request = Request.Put(uri);
        return this.execute(request);
    }

    public Response execute(HttpMethod method) throws IOException {
        switch (method) {
            case PUT:
                return this.put();
            case DELETE:
                return this.delete();
            case POST:
                return this.post();
            default:
                return this.get();
        }
    }


    public Response execute(Request request) throws IOException {
        Executor executor = Executor.newInstance();
        if (Objects.nonNull(proxy)) {
            request.viaProxy(proxy.httpHost());
            if (Objects.nonNull(proxyAuth)) {
                executor.auth(proxyAuth.getUsername(), proxyAuth.getPassword());
            }
        }
        if (Objects.nonNull(basicCookieStore)) {
            executor.use(basicCookieStore);
        }
        if (Objects.nonNull(httpEntity)) {
            request.body(httpEntity);
        }
        if (Objects.nonNull(formData)) {
            request.bodyForm(formData);
        }
        return executor.execute(request);
    }
}
