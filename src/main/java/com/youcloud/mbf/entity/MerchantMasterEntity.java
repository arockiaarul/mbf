package com.youcloud.mbf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "MERCHANT_MASTER", uniqueConstraints = @UniqueConstraint(columnNames = "MID"))
public class MerchantMasterEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "COMPANY_ID", unique = true, nullable = false)
	private Integer companyId;

	@Column(name = "COMPANY_NAME", length = 100)
	private String companyName;

	@Column(name = "MID", unique = true, nullable = false, length = 15)
	private String mid;

	@Column(name = "ADDRESS", length = 200)
	private String address;

	@Column(name = "PIN_CODE", length = 6)
	private String pinCode;

	@Column(name = "TYPE_ESTABLISHMENT", length = 40)
	private String typeEstablishment;

	@Column(name = "ESTABLISHMENT_YRS", length = 4)
	private String establishmentYrs;

	@Column(name = "ESTABLISHMENT_NO", length = 30)
	private String establishmentNo;

	@Column(name = "SALE_TAX_NO", length = 10)
	private String saleTaxNo;

	@Column(name = "TIN_NO", length = 10)
	private String tinNo;

	@Column(name = "PAN_TAN", length = 10)
	private String panTan;

	@Column(name = "OWNER_NAME", length = 50)
	private String ownerName;

	@Column(name = "AUTHORIZED_PERSON", length = 20)
	private String authorizedPerson;

	@Column(name = "MOBILE_NO", length = 13)
	private String mobileNo;

	@Column(name = "OFFICE_NO", length = 13)
	private String officeNo;

	@Column(name = "FAX_NO", length = 13)
	private String faxNo;

	@Column(name = "OFFICE_PREMISES_STATUS", length = 25)
	private String officePremisesStatus;

	@Column(name = "YRS_CURRENT_LOCATION")
	private Integer yrsCurrentLocation;

	@Column(name = "BUSINESS_LINE", length = 20)
	private String businessLine;

	@Column(name = "BUSINESS_HRS", length = 20)
	private String businessHrs;

	@Column(name = "BUSINESS_INCOME", length = 20)
	private String businessIncome;

	@Column(name = "TURNOVER_YEAR1", length = 4)
	private String turnoverYear1;

	@Column(name = "TURNOVER1", precision = 53)
	private Double turnover1;

	@Column(name = "TURNOVER_YEAR2", length = 4)
	private String turnoverYear2;

	@Column(name = "TURNOVER2", precision = 53)
	private Double turnover2;

	@Column(name = "TURNOVER_YEAR3", length = 4)
	private String turnoverYear3;

	@Column(name = "TURNOVER3", precision = 53)
	private Double turnover3;

	@Column(name = "EXPECTED_CARD_BUSINESS_PER_MONTH", precision = 53)
	private Double expectedCardBusinessPerMonth;

	@Column(name = "AVERAGE_BILL_AMOUNT", precision = 53)
	private Double averageBillAmount;

	@Column(name = "BANKER_BRANCH_NAME", length = 100)
	private String bankerBranchName;

	@Temporal(TemporalType.DATE)
	@Column(name = "ACCOUNT_DT_OPEN", length = 10)
	private Date accountDtOpen;

	@Column(name = "ACCOUNT_NO", length = 20)
	private String accountNo;

	@Column(name = "IFSC_NO", length = 11)
	private String ifscNo;

	@Column(name = "MERCHANT_FREQUENCY", length = 25)
	private String merchantFrequency;

	@Column(name = "BANK_NAME", length = 100)
	private String bankName;

	@Column(name = "IFSC_CODE", length = 15)
	private String ifscCode;

	@Column(name = "MDR", precision = 53)
	private Double mdr;

	@Column(name = "POS_MACHINE_TYPE", length = 25)
	private String posMachineType;

	@Column(name = "NO_POS_REQUIRED")
	private Integer noPosRequired;

	@Column(name = "MDR_COMMISSION", precision = 53)
	private Double mdrCommission;

	@Column(name = "STATUS", length = 1)
	private String status;

	@Column(name = "CREATED_BY", length = 10)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", length = 23)
	private Date createdDt; // TODO Data type to be checked (LocalDate / LocalDateTime / Date).

	@Column(name = "MODIFY_BY", length = 10)
	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DT", length = 23)
	private Date modifyDt; // TODO Data type to be checked (LocalDate / LocalDateTime / Date).

	@Column(name = "AUTH_BY", length = 10)
	private String authBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUTH_DT", length = 23)
	private Date authDt; // TODO Data type to be checked (LocalDate / LocalDateTime / Date).

	@Column(name = "AUTH_STATUS", length = 1)
	private String authStatus;

	@Column(name = "GROUP_MID", length = 15)
	private String groupMid;

	@Column(name = "TERMINAL_ISSUE")
	private Integer terminalIssue;

	@Column(name = "PRINTED_NAME", length = 100)
	private String printedName;

	@Column(name = "AREA_CODE", length = 10)
	private String areaCode;

	@Column(name = "CITY_CODE", length = 10)
	private String cityCode;

	@Column(name = "FIRST_NAME", length = 20)
	private String firstName;

	@Column(name = "LAST_NAME", length = 20)
	private String lastName;

	@Column(name = "EMAIL_ID", length = 30)
	private String emailId;

	@Column(name = "ACCOUNT_TYPE", length = 20)
	private String accountType;

	@Column(name = "BUSINESS_TYPE", length = 20)
	private String businessType;

	@Column(name = "MDR_PRIMARY", precision = 53)
	private Double mdrPrimary;

	@Column(name = "MDR_NON_PRIMARY", precision = 53)
	private Double mdrNonPrimary;

	@Column(name = "CDR_PRIMARY", precision = 53)
	private Double cdrPrimary;

	@Column(name = "COUNTRY_CODE", length = 4)
	private String countryCode;

	@Column(name = "MCC_CODE")
	private Integer mccCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "TRANS_DATE", length = 10)
	private Date transDate; // TODO Data type to be checked (LocalDate / LocalDateTime / Date).

	@Column(name = "TRANS_PER_DAY")
	private Integer transPerDay;

	@Column(name = "TOT_AMT_PER_DAY", precision = 53)
	private Double totAmtPerDay;

	@Column(name = "AMOUNT_PER_TRANS", precision = 53)
	private Double amountPerTrans;

	@Column(name = "ALLOWED_TOT_AMT_PER_DAY", precision = 53)
	private Double allowedTotAmtPerDay;

	@Column(name = "ALLOWED_TRANS_PER_DAY")
	private Integer allowedTransPerDay;

	@Column(name = "LOCATION_BOUND", length = 1)
	private String locationBound;

	@Column(name = "ADDRESS_RES", length = 200)
	private String addressRes;

	@Column(name = "DL_FLAG", length = 1)
	private String dlFlag;

	@Column(name = "REMARK", length = 200)
	private String remark;

	@Column(name = "APPLICATION_ID", length = 36)
	private String applicationId;

	@Column(name = "REFERENCE_ID", length = 5)
	private String referenceId;

	@Column(name = "DMT_ACCOUNT_NO", length = 30)
	private String dmtAccountNo;

	@Column(name = "DMT_IFSC_CODE", length = 11)
	private String dmtIfscCode;

	@Column(name = "MICRO_ATM_STATUS", length = 2)
	private String microAtmStatus;

	@Column(name = "DMT_ACCOUNT_NAME", length = 50)
	private String dmtAccountName;

	@Column(name = "WALLET_BALANCE", /*nullable = false,*/ precision = 53)
	private double walletBalance;

	@Column(name = "WALLET_ACCOUNT_NO", length = 32)
	private String walletAccountNo;

	@Column(name = "WALLET_IFSC_CODE", length = 11)
	private String walletIfscCode;

	@Column(name = "VIRTUAL_ACCOUNTNO", length = 20)
	private String virtualAccountno;

	@Column(name = "VIRTUAL_IFSCCODE", length = 11)
	private String virtualIfsccode;

	@Column(name = "MATM_WALLET", /*nullable = false,*/ precision = 16)
	private BigDecimal matmWallet;

	@Column(name = "IS_MATM_WALLET", length = 1)
	private String isMatmWallet;

	@Column(name = "MERCHANT_TOKEN", length = 50)
	private String merchantToken;

	@Column(name = "ABN", length = 11)
	private String abn;

	@Column(name = "ACN", length = 11)
	private String acn;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		MerchantMasterEntity that = (MerchantMasterEntity) o;
		return companyId != null && Objects.equals(companyId, that.companyId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
