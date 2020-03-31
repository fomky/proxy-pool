package org.fomky.proxy.pool.handler;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Response;
import org.fomky.proxy.pool.ProxyPoolConfig;
import org.fomky.proxy.pool.entity.CheckStatus;
import org.fomky.proxy.pool.entity.ProxyPage;
import org.fomky.proxy.pool.entity.ProxyNode;
import org.fomky.proxy.pool.store.DefaultStoreSelect;
import org.fomky.proxy.pool.tools.HttpClients;

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
            List<ProxyNode> proxyStatistics = this.load(handler);
            storeSelect.addActives(proxyStatistics);
        }
    }

    @Override
    public List<ProxyNode> load(BaseHandler handler) {
        // 静态代理模式
        if (handler.staticModel()) {
            return handler.parser(null);
        }
        List<ProxyNode> backList = new ArrayList<>();
        List<ProxyPage> pages = handler.parserLoadPages();
        if (Objects.nonNull(pages)) {
            for (ProxyPage page : pages) {
                try {
                    Response response = handler.request(page);
                    List<ProxyNode> proxies = handler.parserPageContent(response);
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
    public CheckStatus check(ProxyNode proxy) {
        try {

            HttpClients clients = HttpClients.builder()
                    .uri(config.getCheckUrl())
                    .proxy(proxy)
                    .proxyAuth(proxy.getAuth())
                    .build();

            Response response = clients.get();
            StatusLine statusLine = response.returnResponse().getStatusLine();
            return statusLine.getStatusCode() == 200 ? CheckStatus.SUCCESS : CheckStatus.FAIL;
        } catch (IOException e) {
            log.warn("Check proxy NET_CONNECT_ERROR : {}", e.getMessage());
        }
        return CheckStatus.FAIL;
    }
}
