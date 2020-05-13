package com.zoyo.core.common.imageloader;

interface ILoderStrategy {
    /**
     * 加载参数
     *
     * @param options
     */
    void loadOptions(LoaderOptions options);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache();

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache();
}
