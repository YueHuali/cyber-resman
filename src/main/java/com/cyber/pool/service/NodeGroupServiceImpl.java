package com.cyber.pool.service;

import com.cyber.pool.entities.NodeGroup;
import com.cyber.pool.entities.Organization;
import com.cyber.pool.repositories.NodeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class NodeGroupServiceImpl implements NodeGroupService {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${bigbang.url}")
    String deployUrl;

    @Autowired
    NodeGroupRepository nodeGroupRepository;

    @Autowired
    OpenshiftService openshiftService;

    @Override
    public void createNodeGroup(NodeGroup nodeGroup){
        logger.info("Create nodeGroup: name=" + nodeGroup.getName());
        String label = "qy-group-" + nodeGroup.getName();
        nodeGroup.setNodeGroupLabel(label);
        nodeGroup.setCreateTime(new Timestamp(System.currentTimeMillis()));
        nodeGroupRepository.save(nodeGroup);
    }

    @Override
    public List<NodeGroup> retrieveNodeGroup(){
        List<NodeGroup> list = new ArrayList<>();
        Iterable<NodeGroup> iterable = nodeGroupRepository.findAll();
        iterable.forEach(list::add);
        logger.info("List all nodeGroup: size=" + list.size());
        return list;
    }

    @Override
    public void updateNodeGroup(NodeGroup nodeGroup){
        logger.info("Update nodeGroup: name=" + nodeGroup.getName());
        nodeGroupRepository.save(nodeGroup);
    }

    @Override
    public void deleteNodeGroup(NodeGroup nodeGroup){
        logger.info("Delete nodeGroup: name=" + nodeGroup.getName());
        nodeGroupRepository.delete(nodeGroup);
    }

    @Override
    public void deleteNodeGroup(Long id){
        logger.info("Delete nodeGroup: id=" + id);
        nodeGroupRepository.deleteById(id);
    }

    @Override
    public NodeGroup getNodeGroupById(Long id){
        return nodeGroupRepository.findNodeGroupById(id);
    }

    @Override
    public void assignNodeGroupToOrg(NodeGroup nodeGroup, Organization organization){
        logger.info("assignNodeGroupToOrg nodeGroup: name=" + nodeGroup.getName() + ", organization: name=" + organization.getName());
        nodeGroup.getOrganizations().add(organization);
        nodeGroupRepository.save(nodeGroup);
    }

    @Override
    public void reclaimGroupFromOrg (NodeGroup nodeGroup, Organization organization){
        logger.info("reclaimGroupFromOrg nodeGroup: name=" + nodeGroup.getName() + ", organization: name=" + organization.getName());
        nodeGroup.getOrganizations().remove(organization);
        nodeGroupRepository.save(nodeGroup);
    }
}
