package org.nailyourinterview.lld.splitwise.repository;

import org.nailyourinterview.lld.splitwise.model.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryGroupRepository implements GroupRepository {

    private final Map<String, Group> store = new HashMap<>();

    @Override
    public Optional<Group> findById(String id) { return Optional.ofNullable(store.get(id)); }

    @Override
    public void save(Group g) { store.put(g.getId(), g); }
}