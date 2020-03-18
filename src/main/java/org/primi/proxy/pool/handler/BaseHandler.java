package org.primi.proxy.pool.handler;

import org.jsoup.Connection;
import org.jsoup.Connection.*;
import org.jsoup.Jsoup;
import org.primi.proxy.pool.entity.CheckStatus;
import org.primi.proxy.pool.entity.ProxyListPage;
import org.primi.proxy.pool.entity.ProxyStatistics;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    List<ProxyListPage> parserLoadPages();

    /**
     * parser the page content , and back the list of proxy;
     *
     * @param response Response Object
     * @return proxy list
     */
    List<ProxyStatistics> parserPageContent(Response response);

    /**
     * request
     * @param page Page Object
     * @return Response
     * @throws IOException throws IOExceprion
     */
    default Response request(ProxyListPage page) throws IOException {
        Connection connection = Jsoup.connect(page.getUrl())
                .ignoreHttpErrors(true)
                .method(Optional.of(page.getMethod()).orElse(Method.GET));

        if (Objects.nonNull(page.getParameters())) {
            connection.data(page.getParameters());
        }
        return connection.execute();
    }

    /**
     * get the name of handler
     * @return name string
     */
    default String handlerName(){
        return this.getClass().getName();
    }

}
