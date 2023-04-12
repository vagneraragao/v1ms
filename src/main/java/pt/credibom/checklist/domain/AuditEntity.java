package pt.credibom.checklist.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
@Data
public abstract class AuditEntity implements Serializable {

	private static final long serialVersionUID = -3539320600526041711L;

	@CreatedDate
	@Column( name = "created_at", nullable = false )
	private Instant createdAt;

	@CreatedBy
	@Column( name = "created_by", nullable = false )
	private String createdBy;

	@LastModifiedDate
	@Column( name = "updated_at", nullable = false )
	private Instant updatedAt;

	@LastModifiedBy
	@Column( name = "updated_by", nullable = false )
	private String updatedBy;

	@Version
	@Column( name = "version", nullable = false )
	private int version;

	@PrePersist
	void prePersist() {
		this.createdAt = Instant.now();
		this.updatedAt = Instant.now();
	}

	@PreUpdate
	void preUpdate() {
		this.updatedAt = Instant.now();
	}
}
