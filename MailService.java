package com.company;

import java.util.*;
import java.util.function.Consumer;

public class MailService<T> implements Consumer<Sendable<T>> {
    private Map<String, List<T>> listMap = new HashMap<>();

    @Override
    public void accept(Sendable<T> sendable) {
        try {
            if (sendable != null) {
                List<T> elements =
                        listMap.get(sendable.getKey()) == Collections.EMPTY_LIST ? new LinkedList<T>() :
                                listMap.get(sendable.getKey());
                elements.add(sendable.getValue());
                listMap.put(sendable.getKey(), elements);
            }
        } catch (Exception ex) {
        }
    }

    public Map<String, List<T>> getMailBox() {
        return listMap;
    }
}
