package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable{
	

	@Id
	@GeneratedValue
	private Integer id;
	
	@CreatedDate
	@Column(name="creationDate",nullable = false,updatable = false)
	private Date creationDate = new Date();
	
	@LastModifiedDate
	@Column(name="lastModifiedDate")
	private Date lastUpdateDate=new Date();


	@CreatedBy
	private String createBy;

	@LastModifiedBy
	private String modifiedBy;

}
