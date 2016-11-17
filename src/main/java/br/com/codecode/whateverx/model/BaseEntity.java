package br.com.codecode.whateverx.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

/**
 * Root SuperClass for Persistence in This Project
 * Every Entity passed to Persist MUST extends this Class
 * @author felipe
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id = 0L;

	@Version
	@Column
	private int version;

	@Column(updatable = false, nullable = false)
	private String uuid;

	public BaseEntity(){}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	private int getVersion() {
		return this.version;
	}

	private void setVersion(final int version) {
		this.version = version;
	}

	public String getUuid() {		
		return uuid;
	}

	public void setUuid(String uuid) {		
		this.uuid = uuid;
	}
	
	@PrePersist
	private void prePersist(){
		setUuid(UUID.randomUUID().toString());
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (uuid != null && !uuid.trim().isEmpty())
			result += "uuid: " + uuid;
		return result;
	}
}