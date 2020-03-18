package org.primi.proxy.pool;

import lombok.Builder;
import lombok.Data;
import org.primi.proxy.pool.entity.RunModel;

/**
 * Create AT 2020/3/18 15:15:15:15
 *
 * @author Primi.Chen
 */
@Data
@Builder
public class ProxyPoolConfig {
    /** check time out */
    @Builder.Default
    private Integer checkTimeout = 60000;

    /** check url */
    @Builder.Default
    private String checkUrl="http://www.baidu.com";

    /** use request retry times */
    @Builder.Default
    private Integer retry=5;

    /** use request time out */
    @Builder.Default
    private Integer timeout=60000;

    /** reload Rate Delay from data store */
    @Builder.Default
    private Integer reloadRateDelay=60000;

    /** run model */
    @Builder.Default
    private RunModel runModel = RunModel.HANDLERS;

    /** schedule thread pool size */
    @Builder.Default
    private Integer scheduleCore = 5;

    /** schedule thread schedual rate delay */
    @Builder.Default
    private Integer scheduleRateDelay=60000;
}
