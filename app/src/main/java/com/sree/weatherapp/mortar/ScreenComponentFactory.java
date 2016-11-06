package com.sree.weatherapp.mortar;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
public interface ScreenComponentFactory<T> {

    Object createComponent(T parent);
}
