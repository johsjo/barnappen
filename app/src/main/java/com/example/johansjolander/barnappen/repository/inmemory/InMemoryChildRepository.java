package com.example.johansjolander.barnappen.repository.inmemory;

import com.example.johansjolander.barnappen.model.Child;
import com.example.johansjolander.barnappen.repository.impl.ChildRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryChildRepository implements ChildRepository {
    private static final Map<Long,Child> childList = new HashMap<>();

    public InMemoryChildRepository() {


        //TODO TESTCODE Put some dummy String in list for Testing
        for(int i = 0; i < 5 ;i++){
            Child child = new Child(1001 +i,"Nisse " +i,-30*i);
            childList.put(child.getId(),child);
        }
    }

    @Override
    public Child getChild(long id) {
        return childList.get(id);
    }

    @Override
    public List<Child> getAllChildren() {
        return new ArrayList<>(childList.values());
    }

    @Override
    public Child addChild(Child child) {
        return childList.put(child.getId(), child);
    }

    @Override
    public Child removeChild(long id) {
        return childList.remove(id);
    }
}
