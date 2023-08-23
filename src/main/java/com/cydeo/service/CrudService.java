package com.cydeo.service;

import java.util.List;

public interface CrudService <T,ID>{
    T save(T user);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}