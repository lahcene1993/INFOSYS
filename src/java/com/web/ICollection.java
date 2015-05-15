package com.web;
public interface ICollection<T> {
    boolean add(T item);
    boolean remove(T item);
    void clear();
}