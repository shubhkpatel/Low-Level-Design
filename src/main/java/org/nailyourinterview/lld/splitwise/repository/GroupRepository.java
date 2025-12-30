package org.nailyourinterview.lld.splitwise.repository;

import org.nailyourinterview.lld.splitwise.model.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(String id);
    void save(Group group);
}
