package com.cyber.pool.service;

import com.cyber.pool.entities.Node;
import com.cyber.pool.entities.OrgGroup;

import java.util.List;

public interface OrgGroupService
{
    Boolean REMOVELABELFLAG = true;
    void createOrgGroup(OrgGroup orgGroup);
    List<OrgGroup> retrieveOrgGroup();
    void updateOrgGroup(OrgGroup orgGroup);
    void deleteOrgGroup(OrgGroup orgGroup);
    void deleteOrgGroup(Long id);
    OrgGroup getOrgGroupById(Long id);
    void addNodeToOrgGroup(Node node, OrgGroup orgGroup);
    void removeNodeFromOrgGroup(Node node, OrgGroup orgGroup);
}