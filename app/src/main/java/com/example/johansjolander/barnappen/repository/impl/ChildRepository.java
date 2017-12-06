package com.example.johansjolander.barnappen.repository.impl;

import com.example.johansjolander.barnappen.model.Child;

import java.util.List;

public interface ChildRepository {
    Child getChild(long id);
    List<Child> getAllChildren();
    Child addChild(Child child);
    Child removeChild(long id);
}
