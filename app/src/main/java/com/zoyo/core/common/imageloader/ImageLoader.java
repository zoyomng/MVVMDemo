package com.zoyo.core.common.imageloader;

/**
 * 图片加载框架(策略设计模式)
 * 无缝切换Glide,Picasso,Fresco框架
 * 开发者只需要关心ImageLoder+LoaderOptions
 */
public class ImageLoader {
    //加载策略:Glide/Picasso/Fresco
    ILoderStrategy strategy;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        static ImageLoader instance = new ImageLoader();
    }

    /**
     * 全局切换图片加载策略,策略:Glide/Picasso/Fresco
     *
     * @param strategy
     */
    public void setGlobalImageStrategy(ILoderStrategy strategy) {
        this.strategy = strategy;
    }
}
