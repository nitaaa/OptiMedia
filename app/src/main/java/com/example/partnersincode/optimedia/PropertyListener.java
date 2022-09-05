package com.example.partnersincode.optimedia;
@FunctionalInterface
public interface PropertyListener<T> {

    public void valueChanged(Property<T> property, T oldValue, T newValue);

}
