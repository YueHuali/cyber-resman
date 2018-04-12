package com.cyber.pool.repositories;

import com.cyber.pool.entities.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NodeRepository extends CrudRepository<Node, Long>
{
    Node findNodeById(@Param("id") long id);
    Node findByIp(@Param("ip") String ip);
}
