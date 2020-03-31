package org.fomky.proxy.pool.handler;

import org.apache.http.client.fluent.Response;
import org.fomky.proxy.pool.entity.ProxyPage;
import org.fomky.proxy.pool.entity.ProxyNode;
import org.fomky.proxy.pool.tools.HttpClients;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Create AT 2020/3/16 13:51:13:51
 *
 * @author Primi.Chen
 */
public interface BaseHandler {

    /**
     * pase need load pages
     *
     * @return Page list
     */
    List<ProxyPage> parserLoadPages();

    /**
     * parser the page content , and back the list of proxy;
     *
     * @param response Response Object
     * @return proxy list
     * @throws IOException Network error {@link IOException}
     */
    default List<ProxyNode> parserPageContent(Response response) throws IOException {
        return this.parser(response.returnContent().asString());
    }

    /**
     * parser the page content , and back the list of proxy;
     *
     * @param content Response String
     * @return proxy list
     */
    List<ProxyNode> parser(String content);


    /**
     * request
     *
     * @param page Page Object
     * @return Response
     * @throws IOException throws IOExceprion
     */
    default Response request(ProxyPage page) throws IOException {
        HttpClients client = HttpClients.builder().uri(page.getUrl()).build();

        if (Objects.nonNull(page.getFormData())) {
            client.setFormData(page.getFormData());
        }
        return client.execute(page.getMethod());
    }

    /**
     * get the name of handler
     *
     * @return name string
     */
    default String handlerName() {
        return this.getClass().getName();
    }


    /**
     * 静态代理模式 / 默认 false
     *
     * @return default false , if ture the Method {@link #parserPageContent(Response)}
     */
    default boolean staticModel() {

        return false;
    }
}
