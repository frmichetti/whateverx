package br.com.codecode.whateverx.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Root SuperClass for Persistence in This Project
 * Every Entity passed to Persist MUST extends this Class
 * @author felipe
 *
 */
@MappedSuperclass
@SuppressWarnings("unused")
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id = 0L;

	@XmlTransient
	@Version
	@Column
	private int version;	
	
	@XmlTransient
	@Temporal(TemporalType.TIMESTAMP)		
	@Column(updatable=false,nullable=false)
	private Date createdAt;
	
	@XmlTransient
	@Temporal(TemporalType.TIMESTAMP)		
	@Column
	private Date updatedAt;

	@Column(updatable = false, nullable = false)
	private String uuid;

	public BaseEntity(){}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@PrePersist
	private void prepareToPersist(){
		insertTimeStamp();
		generateUUID();		
	}
	
	private void generateUUID(){
		uuid = UUID.randomUUID().toString();
	}
	
	private void insertTimeStamp(){
		createdAt = new Date();
	}

	@PreUpdate
	private void updateTimeStamp(){
		updatedAt = new Date();
	}
	
	public int getVersion() {
		return version;
	}
	
	private void setVersion(int version) {
		this.version = version;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	private void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	private void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUuid() {
		return uuid;
	}

	private void setUuid(String uuid) {
		this.uuid = uuid;
	}
}