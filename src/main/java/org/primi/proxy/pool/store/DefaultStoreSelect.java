package org.primi.proxy.pool.store;

import lombok.*;

import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.primi.proxy.pool.entity.ProxyStatistics;

import java.util.*;

/**
 * Create AT 2020/3/16 16:18:16:18
 *
 * @author Primi.Chen
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class DefaultStoreSelect implements ProxyStore, ProxySelect {

    @Builder.Default
    private int index = 0;

    @Builder.Default
    private int size = 0;

    @Builder.Default
    private final Object lock = new Object();

    @FieldNameConstants.Include
    private ProxyStore store;

    @FieldNameConstants.Include
    private ProxySelect select;

    @Builder.Default
    private List<ProxyStatistics> proxies = new ArrayList<>();

    private int index() {
        if (index == Integer.MAX_VALUE) {
            index = 0;
        }
        return index++;
    }

    @Override
    public void updateActives(List<ProxyStatistics> proxies) {
        synchronized (lock) {
            this.proxies = proxies;
            this.updateSize();
        }
        Optional.of(store).ifPresent(proxyStore -> proxyStore.updateActives(this.proxies));
    }

    @Override
    public void addActive(ProxyStatistics proxy) {
        synchronized (lock) {
            this.proxies.add(proxy);
            this.updateSize();
        }
        Optional.of(store).ifPresent(proxyStore -> proxyStore.addActive(proxy));
    }

    private void updateSize() {
        this.size = this.proxies.size();
    }

    public ProxyStatistics route(int indexCode) {
        synchronized (lock){
            if (Objects.isNull(proxies) || size == 0) {
                return null;
            }
            int index = Math.abs(indexCode % size);
            return proxies.get(index);
        }
    }

    public ProxyStatistics route() {
        return route(index());
    }

    @Override
    public List<ProxyStatistics> resume() {
        Optional.of(store).ifPresent(proxyStore -> {
            List<ProxyStatistics> list = proxyStore.resume();
            this.updateActives(list);
        });
        return this.proxies;
    }

    @Override
    public ProxyStatistics select() {
        if (Optional.ofNullable(select).isPresent()) {
            return select.select();
        } else {
            return route();
        }
    }
}
