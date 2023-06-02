package com.youcloud.mbf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "MBF_DATA")
public class MbfDataEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "BSB_NUMBER")
	private String bsbNumber;

	@Column(name = "CREATED_ON")
	private LocalDateTime createdOn;

	@Column(name = "GROUP_ID", length = 6)
	private String groupId;

	@Column(name = "MBF", length = 2048)
	private String mbf;

	@Column(name = "MERCHANT_FUNDING_ACCOUNT_NAME")
	private String merchantFundingAccountName;

	@Column(name = "MERCHANT_ID")
	private String merchantId;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "SUB_GROUP_ID")
	private String subGroupId;

	@Column(name = "TPP_ID")
	private String tppId;

	@Column(name = "TPP_NAME")
	private String tppName;

	@Column(name = "API_KEY", length = 100)
	private String apiKey;

	@Column(name = "UUID", length = 100)
	private String uuid;

}
