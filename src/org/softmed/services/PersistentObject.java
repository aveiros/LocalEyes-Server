package org.softmed.services;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class PersistentObject {

	@Id
	@GeneratedValue
	protected Long id;

	// @Type(type = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date created;

	protected Long createdMilis = null;

	// @Type(type = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updated;
	protected Long updatedMilis = null;

	@Version
	protected int version;

	public PersistentObject() {
		long milis = System.currentTimeMillis();
		created = new Timestamp(milis);
		createdMilis = milis;

	}

	// @PostPersist
	// @PostUpdate
	// @PostLoad
	public void loadMiliseconds() {
		if (createdMilis != null)
			created.setTime(createdMilis);

		if (updatedMilis != null)
			updated.setTime(updatedMilis);

	}

	// @PrePersist
	// @PreUpdate
	public void saveMiliseconds() {
		if (created != null)
			createdMilis = created.getTime();

		if (updated == null)
			updated = new Date();
		else
			updated.setTime(new Date().getTime());

		updatedMilis = updated.getTime();
		System.out.println("wazza");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void copyData(PersistentObject original) {

		if (original.id != null)
			this.id = new Long(original.id);

		// only copy if this object has no values for created and updated
		if (this.created == null && original.created != null)
			this.created = new Timestamp(original.created.getTime());

		if (this.updated == null && original.updated != null)
			this.updated = new Date(original.updated.getTime());

	}

	public long getCreatedMilis() {
		return createdMilis;
	}

	public void setCreatedMilis(long createdMilis) {
		this.createdMilis = createdMilis;
	}

	public long getUpdatedMilis() {
		return updatedMilis;
	}

	public void setUpdatedMilis(long updatedMilis) {
		this.updatedMilis = updatedMilis;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreatedMilis(Long createdMilis) {
		this.createdMilis = createdMilis;
	}

	public void setUpdatedMilis(Long updatedMilis) {
		this.updatedMilis = updatedMilis;
	}

}
