package com.cyber.pool.service;

import com.cyber.pool.entities.NodeGroup;
import com.cyber.pool.entities.Organization;

import java.util.List;

public interface NodeGroupService
{
    void createNodeGroup(NodeGroup nodeGroup);
    List<NodeGroup> retrieveNodeGroup();
    void updateNodeGroup(NodeGroup nodeGroup);
    void deleteNodeGroup(NodeGroup nodeGroup);
    void deleteNodeGroup(Long id);
    NodeGroup getNodeGroupById(Long id);
    void assignNodeGroupToOrg(NodeGroup nodeGroup, Organization organization);
    void reclaimGroupFromOrg (NodeGroup nodeGroup, Organization organization);
}