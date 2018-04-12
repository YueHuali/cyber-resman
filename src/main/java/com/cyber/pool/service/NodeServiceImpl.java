package com.cyber.pool.service;

import com.cyber.pool.entities.Node;
import com.cyber.pool.entities.NodeGroup;
import com.cyber.pool.entities.Organization;
import com.cyber.pool.repositories.NodeRepository;
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
public class NodeServiceImpl implements NodeService {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${bigbang.url}")
    String deployUrl;

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    OpenshiftService openshiftService;

    @Override
    public void createNode(Node node){
        logger.info("Create node: name=" + node.getName());
        node.setCreateTime(new Timestamp(System.currentTimeMillis()));
        nodeRepository.save(node);
    }

    @Override
    public List<Node> retrieveNode(){
        List<Node> list = new ArrayList<>();
        Iterable<Node> iterable = nodeRepository.findAll();
        iterable.forEach(list::add);
        logger.info("List all node: size=" + list.size());
        return list;
    }

    @Override
    public void updateNode(Node node){
        logger.info("Update node: name=" + node.getName());
        nodeRepository.save(node);
    }

    @Override
    public void deleteNode(Node node){
        logger.info("Delete node: name=" + node.getName());
        nodeRepository.delete(node);
    }

    @Override
    public void deleteNode(Long id){
        logger.info("Delete node: id=" + id);
        nodeRepository.deleteById(id);
    }

    @Override
    public Node getNodeById(Long id){
        return nodeRepository.findNodeById(id);
    }

    @Override
    public Node getNodeByIp(String ip){
        return nodeRepository.findByIp(ip);
    }

    @Override
    public void addNodeToGroup (Node node, NodeGroup nodeGroup){
        node.getNodeGroups().add(nodeGroup);
        nodeRepository.save(node);

        //此处需要打标签
        String ocResponse = openshiftService.updateLable(nodeGroup.getNodeGroupLabel(), node.getName());
        logger.info("addNodeToGroup ocResponse=" + ocResponse);
    }

    @Override
    public void removeNodeFromGroup (Node node, NodeGroup nodeGroup){
        node.getNodeGroups().remove(nodeGroup);
        nodeRepository.save(node);

        //此处需要删除标签
        String ocResponse = openshiftService.updateLable(nodeGroup.getNodeGroupLabel(), node.getName(), REMOVE_LABEL_FLAG);
        logger.info("removeNodeFromGroup ocResponse=" + ocResponse);
    }

    @Override
    public void assignNodeToOrg (Node node, Organization organization){
        node.getOrganizations().add(organization);
        String labelName = NODE_LABEL_PREFIX + organization.getName();
//        node.setLabel(labelName);
        nodeRepository.save(node);

        //此处需要打标签
        String ocResponse = openshiftService.updateLable(labelName, node.getName());
        logger.info("assignNodeToOrg ocResponse=" + ocResponse);
    }

    @Override
    public void reclaimNodeFromOrg (Node node, Organization organization){
        node.getOrganizations().remove(organization);
//        String label = node.getLabel();
//        node.setLabel(null);
        nodeRepository.save(node);

        //此处需要删除标签
        String labelName = NODE_LABEL_PREFIX + organization.getName();
        String ocResponse = openshiftService.updateLable(labelName, node.getName(), REMOVE_LABEL_FLAG);
        logger.info("reclaimNodeFromOrg ocResponse=" + ocResponse);
    }

    @Override
    public void deployNode (Node node){
        //Call bigbang api to deploy node
        node.setStatus("2");
        nodeRepository.save(node);

//        deployUrl
    }

}
