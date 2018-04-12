package com.cyber.pool;

import com.cyber.pool.entities.Node;
import com.cyber.pool.service.NodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CybertronApplication.class)
public class NodeServiceTest {

    @Resource
    public NodeService nodeService;

    @Test
    public void testCreateNode() {
        Node node = new Node();
        node.setCpu("1");
        node.setIp("192.168.1.11");
        node.setMemory("2GB");
        node.setCreateTime(new Timestamp(System.currentTimeMillis()));
        node.setName("node1");
        node.setStatus("1");

        nodeService.createNode(node);
    }
}
