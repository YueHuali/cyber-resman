package com.cyber.pool.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
public class NodeGroup {
	private Long id;
	private String name;
	private String nodeGroupLabel;
	private String status;
	private String createMan;
	private Timestamp createTime;
	private String remark;
 	private Set<Node> nodes = new HashSet<Node>();
	private Set<Organization> organizations = new HashSet<Organization>();

	public NodeGroup() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "nodeGroupLabel", nullable = false, length = 200)
	public String getNodeGroupLabel() {
		return nodeGroupLabel;
	}

	public void setNodeGroupLabel(String nodeGroupLabel) {
		this.nodeGroupLabel = nodeGroupLabel;
	}

	@Column(name = "createMan", nullable = false, length = 200)
	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	@Column(name = "create_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status", length = 100)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "remark", length = 2000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nodeGroups")
	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "nodegroup_organization",
			joinColumns = { @JoinColumn(name = "nodegroup_id") },
			inverseJoinColumns = { @JoinColumn(name = "organization_id")}
	)
	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

}
