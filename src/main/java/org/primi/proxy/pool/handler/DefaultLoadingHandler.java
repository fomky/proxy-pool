package org.primi.proxy.pool.handler;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.primi.proxy.pool.ProxyPoolConfig;
import org.primi.proxy.pool.entity.CheckStatus;
import org.primi.proxy.pool.entity.ProxyListPage;
import org.primi.proxy.pool.entity.ProxyStatistics;
import org.primi.proxy.pool.store.DefaultStoreSelect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Create AT 2020/3/18 17:25:17:25
 *
 * @author Primi.Chen
 */
@Data
@Slf4j
@Builder
public class DefaultLoadingHandler implements LoadingHandler {

    private List<BaseHandler> handlers;

    private DefaultStoreSelect storeSelect;

    private ProxyPoolConfig config;

    @Override
    public void run() {
        for (BaseHandler handler : handlers) {
            this.load(handler);
        }
    }

    @Override
    public List<ProxyStatistics> load(BaseHandler handler) {
        List<ProxyStatistics> backList = new ArrayList<>();
        List<ProxyListPage> pages = handler.parserLoadPages();
        if (Objects.nonNull(pages)) {
            for (ProxyListPage page : pages) {
                try {
                    Connection.Response response = handler.request(page);
                    List<ProxyStatistics> proxies = handler.parserPageContent(response);
                    proxies = proxies.stream()
                            .filter(proxy -> CheckStatus.isSuccess(check(proxy)))
                            .collect(Collectors.toList());
                    if (!proxies.isEmpty()) {
                        backList.addAll(proxies);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return backList;
    }

    @Override
    public CheckStatus check(ProxyStatistics proxy) {
        try {
            Connection.Response response = Jsoup.connect(config.getCheckUrl())
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.GET)
                    .execute();
            return response.statusCode() == 200 ? CheckStatus.SUCCESS : CheckStatus.FAIL;
        } catch (IOException e) {
            log.warn("Check proxy NET_CONNECT_ERROR : {}",e.getMessage());
        }
        return CheckStatus.FAIL;
    }
}
