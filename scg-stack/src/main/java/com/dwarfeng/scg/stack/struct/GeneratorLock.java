package com.dwarfeng.scg.stack.struct;

/**
 * 生成器锁。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface GeneratorLock {

    /**
     * 上锁。
     *
     * @throws Exception 上锁时发生的任何异常。
     */
    void lock() throws Exception;

    /**
     * 解锁。
     *
     * @throws Exception 解锁时发生的任何异常。
     */
    void unlock() throws Exception;
}
