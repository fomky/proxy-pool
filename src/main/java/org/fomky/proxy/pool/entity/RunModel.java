package org.fomky.proxy.pool.entity;

import java.io.Serializable;

/**
 * Create AT 2020/3/18 17:10:17:10
 *
 * @author Primi.Chen
 */
public enum RunModel implements Serializable {
    /** 单线程 */
    SINGLETON,
    /** 多线程,根据 Handler 数量 */
    HANDLERS;
}
