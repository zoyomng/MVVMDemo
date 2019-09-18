package com.zoyo.core.common.imageloader;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;

public class LoaderOptions {
    public String url;
    public Uri uri;
    public File file;
    public int placeholderResId;
    public int errorResId;
    Bitmap.Config config = Bitmap.Config.RGB_565;
    ILoderStrategy strategy;

    public LoaderOptions url(String url) {
        this.url = url;
        return this;
    }

    public LoaderOptions uri(Uri uri) {
        this.uri = uri;
        return this;

    }

    public LoaderOptions file(File file) {
        this.file = file;
        return this;

    }

    public LoaderOptions placeholderResId(int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;

    }

    public LoaderOptions errorResId(int errorResId) {
        this.errorResId = errorResId;
        return this;

    }

    public LoaderOptions config(Bitmap.Config config) {
        this.config = config;
        return this;

    }

    public LoaderOptions strategy(ILoderStrategy strategy) {
        this.strategy = strategy;
        return this;

    }
}
