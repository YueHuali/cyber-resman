package com.cyber.pool.repositories;

import com.cyber.pool.entities.NodeGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NodeGroupRepository extends CrudRepository<NodeGroup, Long>
{
    NodeGroup findNodeGroupById(@Param("id") long id);
}