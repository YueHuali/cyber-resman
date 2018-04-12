package com.cyber.pool.service;

import com.cyber.pool.entities.Node;
import com.cyber.pool.entities.NodeGroup;
import com.cyber.pool.entities.Organization;

import java.util.List;

public interface NodeService
{
    String NODE_LABEL_PREFIX = "qy-node-";
    Boolean REMOVE_LABEL_FLAG = true;
    void createNode(Node node);
    List<Node> retrieveNode();
    void updateNode(Node node);
    void deleteNode(Node node);
    void deleteNode(Long id);
    Node getNodeById(Long id);
    Node getNodeByIp(String ip);
    void addNodeToGroup(Node node, NodeGroup nodeGroup);
    void removeNodeFromGroup(Node node, NodeGroup nodeGroup);
    void assignNodeToOrg(Node node, Organization organization);
    void reclaimNodeFromOrg (Node node, Organization organization);
    void deployNode(Node node);

}
