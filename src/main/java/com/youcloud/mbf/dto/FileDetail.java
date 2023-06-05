package com.youcloud.mbf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class FileDetail {

    /**
     * Record Type , M, H, 2, AN, Constant ‘H’ value (8)
     */
    @Builder.Default
    private String recordType = "D";

    /**
     *
     * Merchant Number, M, 14, N, Merchant ID from pre-defined list provided to each TPP, Must start with ‘422985’ prefix
     */
    private Long merchantNumber;

    /**
     *
     * Merchant Level, M, Drop down
     * Drop down field – values are:
     *  Member Level
     *  Sub-Group Level
     *  Group Level
     * Recommended to use ‘Member Level’ for sub-merchant boarding.
     */
    private String merchantLevel;

    /**
     *
     * Parent Client, M, 14, AN, Fiserv provided Long Sub-group MID (starting with ‘422985’ prefix)
     * to be populated here. TPP must ensure that correct
     * Sub-Group
     * MID is entered so that appropriate pricing is set against the sub-merchant account.
     */
    private String parentClient;

    /**
     *
     * Trading Business Name, M, 22, AN, Free form text.
     * In case of incorrect details, the same would be highlighted in the output file with the correction (12)
     */
    private String tradingBusinessName;

    /**
     * Legal Name, M, 50, AN, Free form text.
     * In case of incorrect details, the same would be highlighted in the output file with the correction.
     * 15When loaded into Fiserv Merchant platform, value will truncate the name to first 35 characters
     */
    private String legalName;

    /**
     * ABN M 11 N 11 digits ABN, no special characters
     */
    private Long abn;


    /**
     * 15 ACN C 9 N Nine digits ACN, no special characters. This is mandatory for Companies
     */
    private Long acn;


    /**
     * 16 Web Address C 45 AN Mandatory if Percent Internet > 0
     * URL must contain full Web address; Example www.fiserv.com’
     */
    private String webAddress;

    /**
     * 17 DCC Merchant M No Drop down
     * Default – NO
     * Currently not offered for all TPPs. TPP should contact Fiserv to enable this
     */
    private String dcc;

    /**
     * 18 Type of Business M N/A Drop down
     * Drop down field – options that best describes the business (sole trader, partnership, corporation, Trust, Association and Other)
     */
    private String typeOfBusiness;

    /**
     * 19 MCC M 4 N Four digit numeric code
     * Merchants under prohibited or unqualified MCC list (as deemed by Fiserv) will be rejected
     */
    private Integer mcc;

    /**
     * 20 Ecommerce Enabled M Drop down
     * Yes or No, depending upon whether the merchant processes Card Not Present/ecommerce transactions
     */
    private String ecommerceEnabled;

    /**
     * 21 Trading Address Contact Name M 35 AN Free form text
     */
    private String tradingAddressContactName;

    /**
     * 22 Trading Address Line 1 M 35 AN Free form text
     */
    private String tradingAddressLine1;

    /**
     * 23 Trading Address Line 2 M 35 AN Free form text
     */
    private String tradingAddressLine2;

    /**
     * 24 Trading Address Line 3 O 35 AN Free form text
     */
    private String tradingAddressLine3;

    /**
     * 25 Trading Address Line 4 O 35 AN Free form text
     */
    private String tradingAddressLine4;

    /**
     * 26 Trading Address City M 22 AN Free form text
     */
    private String tradingAddressCity;

    /**
     * 27 Trading Address State M Drop  down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     */
    private String tradingAddressState;

    /**
     * 28 Trading Address Postcode M 11 N Mandatory field – numeric values only. Must not be more than four digits and 0000 is not valid. Must correspond to the Trading Address City
     */
    private String tradingAddressPostcode;

    /**
     * 29 Trading Address Country M Australia Drop down
     * Mandatory field – set to Australia only
     */
    private String tradingAddressCountry;

    /**
     * 30 Legal Address Contact Name M 35 AN Free form text
     */
    private String legalAddressContactName;

    /**
     * 31 Legal Address Line 1 M 35 AN Free form text
     */
    private String legalAddressLine1;

    /**
     * 32 Legal Address Line 2 O 35 AN Free form text
     */
    private String legalAddressLine2;

    /**
     * 33 Legal Address Line 3 O 35 AN Free form text
     */
    private String legalAddressLine3;

    /**
     * 34 Legal Address Line 4 O 35 AN Free form text
     */
    private String legalAddressLine4;

    /**
     * 35 Legal Address City M 22 AN Free form text
     */
    private String legalAddressCity;

    /**
     * 36 Legal Address State M Drop down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     */
    private String legalAddressState;

    /**
     * 37 Legal Address Postcode M 11 N Mandatory field – numeric values only. Must not be
     * more than four digits and 0000 is not valid. Must correspond to  the Legal Address City
     */
    private String legalAddressPostcode;

    /**
     * 38 Legal Address Country M Australia Drop down
     * Mandatory field – set to Australia only
     */
    private String legalAddressCountry;

    /**
     * 39 Signed Date M 8 AN The date the merchant signed the application. Format is – DDMMYYYY
     */
    private String signedDate;

    /**
     * 40 Total Annual Cash or Credit Debit Turnover M 18 N In AUD only
     * Only numbers – No commas or currency symbol to be specified
     * If blank or incorrect then populate error in the Output file
     */
    private Double totalAnnualCashOrCreditDebitTurnover;

    /**
     * 41 Total Annual Credit Turnover M 18 N In AUD only
     * Only numbers – No commas or currency symbol to be specified
     * If blank or incorrect then populate error in the Output file
     */
    private Double totalAnnualCreditTurnover;

    /**
     * 42 Average Ticket or Sales Amount M 18 N Only numbers –
     * If blank or incorrect then populate error in the Output file
     */
    private Double averageTicketOrSalesAmount;

    /**
     * 43 Capture Method O Drop down
     * Optional Field, Fiserv to override based on Capture method set at Sub-Group/Parent Client MID.
     * Valid values –
     *  Card Present
     *  Card Not Present
     */
    private String captureMethod;

    /**
     * 44 Deposit Required M No 3 Drop down
     * Whether a deposit is required by the merchant, format
     * No or Yes
     */
    private String depositRequired;

    /**
     * 45 Deposit Percentage C 0 3 N If deposit required, what is the percentage taken.
     * Mandatory if Deposit Required set to Yes and must be greater than zero
     */
    private String depositPercentage;

    /**
     * 46 Average Delivery Timeframe M 3 N In number of days, numeric only
     */
    private String averageDeliveryTimeframe;

    /**
     * 47 Recurring Tran M Drop down
     * Mandatory field, No or Yes
     */
    private String recurringTran;

    /**
     * 48 Terminal Type C Drop down
     * Only required if TPP is using Fiserv provided POS
     * Terminal for the sub-merchant (Please contact Fiserv for the list of accepted values)
     * Optional for TPPs who process Card Not Present/ecommerce transactions and TPPs who provide their own POS terminal/Solution to the sub-merchants
     */
    private String terminalType;

    /**
     * 49 Config Code C Drop down
     * Configuration of the terminal; Drop down fields are- Hospitality, Mail, Restaurant and Retail
     */
    private String configCode;

    /**
     * 50 Term Qty C 1 3 N Number of devices to be on-boarded. Default to one,
     * if not provided. Based on this, Fiserv will create the TID numbers in the system
     */
    private Integer termQty;

    /**
     * 51 Acquiring Ausdebit O Drop down
     * EFTPOS processing required, set to Yes or No
     */
    private String acquiringAusdebit;

    /**
     * 52 Acquiring Visa MasterCard M Yes Drop down
     * Mandatory field
     * If Visa and/or MasterCard processing is required, set to Yes
     */
    private String acquiringVisaMasterCard;

    /**
     * 53 Acquiring Amex O Yes Drop down
     * Amex processing required, set to Yes or No
     */
    private String acquiringAmex;

    /**
     * 54 Amex Existing C Drop down
     * Amex processing is existing, set to Yes or leave blank
     * Mandatory if Amex is set to ‘Yes’ previously
     */
    private String amexExisting;

    /**
     * 55 Amex SE Number C 10 N Amex MID for an existing merchant, Mandatory if Amex Existing is Yes
     */
    private String amexSENumber;

    /**
     * 56 Amex New Required C Yes Drop down
     * Yes or No
     */
    private String amexNewRequired;

    /**
     * 57 Acquiring Diners O Drop down
     * Diners processing, set to Yes or No
     */
    private String acquiringDiners;

    /**
     * 58 Diners SE Number C 10 N Diners MID for an existing merchant, Mandatory if Acquiring Diners is Yes
     */
    private String dinersSENumber;

    /**
     * 59 Principal 1 First Name M 50 AN Free form text
     */
    private String principal1FirstName;

    /**
     * 60 Principal 1 Middle Initial O 50 AN Free form text
     */
    private String principal1MiddleInitial;

    /**
     * 61 Principal 1 Surname M 50 AN Free form text
     */
    private String principal1Surname;

    /**
     * 62 Principal 1 Type M Beneficial
     * Owner
     *  Drop down
     * Drop down Fields are Authorised Signer, Beneficial Owner, Chairman, Secretary, Treasurer, Director.
     * Need to capture at least one Beneficial Owner and at least one Authorised Signer populated (these may be the same person in some cases)
     */
    private String principal1Type;
    /**
     * 63 Principal 1 Date of Birth M 10 AN DDMMYYYY
     */
    private String principal1DateOfBirth;

    /**
     * 64 Principal 1 Address Line 1 M 60 AN Free form text
     */
    private String principal1AddressLine1;
    /**
     * 65 Principal 1 Address Line 2 O 60 AN Free form text
     */
    private String principal1AddressLine2;

    /**
     * 66 Principal 1 Address Line 3 O 60 AN Free form text
     */
    private String principal1AddressLine3;
    /**
     * 67 Principal 1 City M 60 AN Free form text
     */
    private String principal1City;

    /**
     * 68 Principal 1 State M 3 Drop down
     * Drop down menu; Mandatory field – characters only.
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     */
    private String principal1State;
    /**
     * 69 Principal 1 Post Code M 10 N Mandatory field – numeric values only. Must not be
     * more than four digits and 0000 is not valid. Must correspond to the Principal 1 City
     */
    private String principal1PostCode;

    /**
     * 70 Principal 1 Country M Australia Drop down
     * Mandatory field – set to Australia only
     */
    private String principal1Country;
    /**
     * 71 Principal 1 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not accepted
     */
    private String principal1PhoneNumber;

    /**
     * 72 Principal 1 Passport Number C 15 AN Either of Passport or Driving license is mandatory
     */
    private String principal1PassportNumber;
    /**
     * 73 Principal 1 Drivers License C 20 AN Either of Passport or Driving license is mandatory
     */
    private String principal1DriversLicense;

    /**
     * 74 Principal 1 Drivers License State C Drop down
     * Drop down menu; Mandatory field – characters only
     * ACT NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     */
    private String principal1DriversLicenseState;
    /**
     * 75 Principal 2 First Name M 50 AN Free form text. At least two Principal Owners are needed, rest are optional
     */
    private String principal2FirstName;

    /**
     * 76 Principal 2 Middle Initial O 50 AN Free form text
     */
    private String principal2MiddleInitial;
    /**
     * 77 Principal 2 Surname M 50 AN Free form text
     */
    private String principal2Surname;

    /**
     * 78 Principal 2 Type M Authorised
     * Signer
     *  Drop down
     * Drop down Fields are Authorised Signer, Beneficial Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory field – set to Authorised Signer only Need to capture at least one Beneficial Owner and at least one Authorised Signer populated
     * (these may be the same person in some cases)
     */
    private String principal2Type;
    /**
     * 79 Principal 2 Date of Birth M 10 AN DDMMYYYY
     * Mandatory field
     */
    private String principal2DateOfBirth;

    /**
     * 80 Principal 2 Address Line 1 M 60 AN Free form text
     */
    private String principal2AddressLine1;

    /**
     * 81 Principal 2 Address Line 2 O 60 AN Free form text
     */
    private String principal2AddressLine2;

    /**
     * 82 Principal 2 Address Line 3 O 60 AN Free form text
     */
    private String principal2AddressLine3;

    /**
     * 83 Principal 2 City M 60 AN Free form text
     */
    private String principal2City;

    /**
     * 84 Principal 2 State M 3 Drop down
     * Drop down menu; Mandatory field – characters only.
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     */
    private String principal2State;

    /**
     * 85 Principal 2 Postcode M 10 N Mandatory field – numeric values only. Must not be
     * more than four digits and 0000 is not valid. Must
     * correspond to the Principal 2 City
     */
    private String principal2Postcode;

    /**
     * 86 Principal 2 Country M Australia Drop down
     * Mandatory field – Set to
     * Australia only
     */
    private String principal2Country;

    /**
     * 87 Principal 2 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not accepted
     */
    private String principal2PhoneNumber;

    /**
     * 88 Principal 2 Passport Number C 15 AN Either of Passport or Driving license is mandatory
     */
    private String principal2PassportNumber;

    /**
     * 89 Principal 2 Drivers License C 20 AN Either of Passport or Driving license is mandatory
     */
    private String principal2DriversLicense;

    /**
     * 90 Principal 2 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal2DriversLicenseState;

    /**
     * 91 Principal 3 First Name O 50 AN Free form text
     */
    private String principal3FirstName;

    /**
     * 92 Principal 3 Middle Initial O 50 AN Free form text
     */
    private String principal3MiddleInitial;
    /**
     * 93 Principal 3 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal3Surname;

    /**
     * 94 Principal 3 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal3Type;
    /**
     * 95 Principal 3 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal3DateOfBirth;

    /**
     * 96 Principal 3 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal3AddressLine1;

    /**
     * 97 Principal 3 Address Line 2 O 60 AN Free form text
     */
    private String principal3AddressLine2;

    /**
     * 98 Principal 3 Address Line 3 O 60 AN Free form text
     */
    private String principal3AddressLine3;

    /**
     * 99 Principal 3 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal3City;

    /**
     * 100 Principal 3 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal3State;

    /**
     * 101 Principal 3 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the Principal 3 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal3Postcode;

    /**
     * 102 Principal 3 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal3Country;

    /**
     * 103 Principal 3 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal3PhoneNumber;

    /**
     * 104 Principal 3 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal3PassportNumber;

    /**
     * 105 Principal 3 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal3DriversLicense;

    /**
     * 106 Principal 3 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal3DriversLicenseState;


    /**
     * 107 principal 4 First Name O 50 AN Free form text
     */
    private String principal4FirstName;

    /**
     * 92 principal 4 Middle Initial O 50 AN Free form text
     */
    private String principal4MiddleInitial;
    /**
     * 93 principal 4 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal4Surname;

    /**
     * 94 principal 4 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal4Type;
    /**
     * 95 principal 4 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal4DateOfBirth;

    /**
     * 96 principal 4 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal4AddressLine1;

    /**
     * 97 principal 4 Address Line 2 O 60 AN Free form text
     */
    private String principal4AddressLine2;

    /**
     * 98 principal 4 Address Line 3 O 60 AN Free form text
     */
    private String principal4AddressLine3;

    /**
     * 99 principal 4 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal4City;

    /**
     * 100 principal 4 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal4State;

    /**
     * 101 principal 4 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the principal 4 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal4Postcode;

    /**
     * 102 principal 4 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal4Country;

    /**
     * 103 principal 4 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal4PhoneNumber;

    /**
     * 104 principal 4 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal4PassportNumber;

    /**
     * 105 principal 4 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal4DriversLicense;

    /**
     * 106 principal 4 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal4DriversLicenseState;


    /**
     * 91 principal  5 First Name O 50 AN Free form text
     */
    private String principal5FirstName;

    /**
     * 92 principal  5 Middle Initial O 50 AN Free form text
     */
    private String principal5MiddleInitial;
    /**
     * 93 principal  5 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal5Surname;

    /**
     * 94 principal  5 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal5Type;
    /**
     * 95 principal  5 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal5DateOfBirth;

    /**
     * 96 principal  5 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal5AddressLine1;

    /**
     * 97 principal  5 Address Line 2 O 60 AN Free form text
     */
    private String principal5AddressLine2;

    /**
     * 98 principal  5 Address Line 3 O 60 AN Free form text
     */
    private String principal5AddressLine3;

    /**
     * 99 principal  5 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal5City;

    /**
     * 100 principal  5 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal5State;

    /**
     * 101 principal  5 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the principal  5 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal5Postcode;

    /**
     * 102 principal  5 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal5Country;

    /**
     * 103 principal  5 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal5PhoneNumber;

    /**
     * 104 principal  5 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal5PassportNumber;

    /**
     * 105 principal  5 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal5DriversLicense;

    /**
     * 106 principal  5 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal5DriversLicenseState;


    /**
     * 91 principal  6 First Name O 50 AN Free form text
     */
    private String principal6FirstName;

    /**
     * 92 principal  6 Middle Initial O 50 AN Free form text
     */
    private String principal6MiddleInitial;
    /**
     * 93 principal  6 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal6Surname;

    /**
     * 94 principal  6 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal6Type;
    /**
     * 95 principal  6 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal6DateOfBirth;

    /**
     * 96 principal  6 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal6AddressLine1;

    /**
     * 97 principal  6 Address Line 2 O 60 AN Free form text
     */
    private String principal6AddressLine2;

    /**
     * 98 principal  6 Address Line 3 O 60 AN Free form text
     */
    private String principal6AddressLine3;

    /**
     * 99 principal  6 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal6City;

    /**
     * 100 principal  6 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal6State;

    /**
     * 101 principal  6 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the principal  6 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal6Postcode;

    /**
     * 102 principal  6 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal6Country;

    /**
     * 103 principal  6 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal6PhoneNumber;

    /**
     * 104 principal  6 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal6PassportNumber;

    /**
     * 105 principal  6 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal6DriversLicense;

    /**
     * 106 principal  6 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal6DriversLicenseState;


    /**
     * 91 principal  7 First Name O 50 AN Free form text
     */
    private String principal7FirstName;

    /**
     * 92 principal  7 Middle Initial O 50 AN Free form text
     */
    private String principal7MiddleInitial;
    /**
     * 93 principal  7 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal7Surname;

    /**
     * 94 principal  7 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal7Type;
    /**
     * 95 principal  7 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal7DateOfBirth;

    /**
     * 96 principal  7 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal7AddressLine1;

    /**
     * 97 principal  7 Address Line 2 O 60 AN Free form text
     */
    private String principal7AddressLine2;

    /**
     * 98 principal  7 Address Line 3 O 60 AN Free form text
     */
    private String principal7AddressLine3;

    /**
     * 99 principal  7 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal7City;

    /**
     * 100 principal  7 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal7State;

    /**
     * 101 principal  7 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the principal  7 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal7Postcode;

    /**
     * 102 principal  7 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal7Country;

    /**
     * 103 principal  7 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal7PhoneNumber;

    /**
     * 104 principal  7 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal7PassportNumber;

    /**
     * 105 principal  7 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal7DriversLicense;

    /**
     * 106 principal  7 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal7DriversLicenseState;


    /**
     * 91 principal 8 First Name O 50 AN Free form text
     */
    private String principal8FirstName;

    /**
     * 92 principal 8 Middle Initial O 50 AN Free form text
     */
    private String principal8MiddleInitial;
    /**
     * 93 principal 8 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal8Surname;

    /**
     * 94 principal 8 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal8Type;
    /**
     * 95 principal 8 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal8DateOfBirth;

    /**
     * 96 principal 8 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal8AddressLine1;

    /**
     * 97 principal 8 Address Line 2 O 60 AN Free form text
     */
    private String principal8AddressLine2;

    /**
     * 98 principal 8 Address Line 3 O 60 AN Free form text
     */
    private String principal8AddressLine3;

    /**
     * 99 principal 8 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal8City;

    /**
     * 100 principal 8 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal8State;

    /**
     * 101 principal 8 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the principal 8 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal8Postcode;

    /**
     * 102 principal 8 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal8Country;

    /**
     * 103 principal 8 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal8PhoneNumber;

    /**
     * 104 principal 8 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal8PassportNumber;

    /**
     * 105 principal 8 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal8DriversLicense;

    /**
     * 106 principal 8 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal8DriversLicenseState;


    /**
     * 91 principal 9 First Name O 50 AN Free form text
     */
    private String principal9FirstName;

    /**
     * 92 principal 9 Middle Initial O 50 AN Free form text
     */
    private String principal9MiddleInitial;
    /**
     * 93 principal 9 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal9Surname;

    /**
     * 94 principal 9 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal9Type;
    /**
     * 95 principal 9 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal9DateOfBirth;

    /**
     * 96 principal 9 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal9AddressLine1;

    /**
     * 97 principal 9 Address Line 2 O 60 AN Free form text
     */
    private String principal9AddressLine2;

    /**
     * 98 principal 9 Address Line 3 O 60 AN Free form text
     */
    private String principal9AddressLine3;

    /**
     * 99 principal 9 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal9City;

    /**
     * 100 principal 9 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal9State;

    /**
     * 101 principal 9 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the principal 9 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal9Postcode;

    /**
     * 102 principal 9 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal9Country;

    /**
     * 103 principal 9 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal9PhoneNumber;

    /**
     * 104 principal 9 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal9PassportNumber;

    /**
     * 105 principal 9 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal9DriversLicense;

    /**
     * 106 principal 9 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal9DriversLicenseState;


    /**
     * 91 principal 10 First Name O 50 AN Free form text
     */
    private String principal10FirstName;

    /**
     * 92 principal 10 Middle Initial O 50 AN Free form text
     */
    private String principal10MiddleInitial;
    /**
     * 93 principal 10 Surname C 50 AN Free form text. Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal10Surname;

    /**
     * 94 principal 10 Type C Drop down
     * Drop down Fields are Authorised Signer, Beneficial
     * Owner, Chairman, Secretary, Treasurer, Director.
     * Mandatory if Principal Owner 3 First Name is filled
     */
    private String principal10Type;
    /**
     * 95 principal 10 Date of Birth C 10 AN DDMMYYYY
     */
    private String principal10DateOfBirth;

    /**
     * 96 principal 10 Address Line 1 C 60 AN Mandatory field if Principal Owner 3 First Name is filled
     */
    private String principal10AddressLine1;

    /**
     * 97 principal 10 Address Line 2 O 60 AN Free form text
     */
    private String principal10AddressLine2;

    /**
     * 98 principal 10 Address Line 3 O 60 AN Free form text
     */
    private String principal10AddressLine3;

    /**
     * 99 principal 10 City C 60 AN Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal10City;

    /**
     * 100 principal 10 State C 3 Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal10State;

    /**
     * 101 principal 10 Postcode C 10 N Must not be more than four digits and 0000 is not
     * valid. Must correspond to the principal 10 City.
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal10Postcode;

    /**
     * 102 principal 10 Country C Australia Drop
     * down
     * Mandatory field – set to Australia only
     * Mandatory if Principal Owner 3
     * First Name is filled
     */
    private String principal10Country;

    /**
     * 103 principal 10 Phone Number O 15 N If populated, must only contain numeric characters
     * i.e. Special characters like ‘+’ sign or spaces are not
     * accepted
     */
    private String principal10PhoneNumber;

    /**
     * 104 principal 10 Passport Number C 15 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal10PassportNumber;

    /**
     * 217 principal 10 Drivers License C 20 AN Either of Passport or Driving license is mandatory if
     * Principal
     * Owner 3 First Name is filled
     */
    private String principal10DriversLicense;

    /**
     * 218 principal 10 Drivers License State C Drop
     * down
     * Drop down menu; Mandatory field – characters only
     * ACT
     * NSW
     * QLD
     * SA
     * TAS
     * VIC
     * WA
     * NT
     * Mandatory if Driving License is filled
     */
    private String principal10DriversLicenseState;


    /**
     * 219 UPI O
     * Drop down
     * Yes or No, this is offered for both ecommerce and POS solution
     */
    private String upi;

    /**
     * 220 Merchant Onsite Contact Name C 35 AN Mandatory only for TPPs where Fiserv provides POS
     * Terminal to
     * sub-merchants to capture onsite IT contact name for
     * terminal installation
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions
     * to sub-merchant
     */
    private String merchantOnsiteContactName;

    /**
     * 221 Merchant Onsite Contact No C 10 N Mandatory only for TPPs where Fiserv provides POS
     * Terminal to
     * sub-merchants to capture onsite
     * IT contact number for terminal installation
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchant
     */
    private String merchantOnsiteContactNo;

    /**
     * 222 Merchant Onsite Alternate Contact No C 10 N Mandatory only for TPPs where Fiserv provides POS
     * Terminal to
     * sub-merchants to capture onsite
     * IT contact number (alternate) for
     * terminal installation
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchant
     */
    private String merchantOnsiteAlternateContactNo;

    /**
     * 223 Merchant Contact Email C 50 AN Mandatory only for TPPs where Fiserv provides POS
     * Terminal to sub-merchants to capture merchant’s email ID to
     * deliver terminal advices
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchants
     */
    private String merchantContactEmail;

    /**
     * 224 Computer Operation System C Drop down
     * Mandatory only for TPPs where Fiserv provides POS
     * Terminal to
     * sub-merchants. This is only needed if terminal type to
     * be offered is integrated. Value values are –
     *  Android
     *  iOS
     *  Mac OS
     *  Windows
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchants
     */
    private String computerOperationSystem;

    /**
     * 225 Port Type C Drop
     * down
     * Mandatory only for TPPs where Fiserv provides POS
     * Terminal to
     * sub-merchants. This is only needed if terminal type to
     * be offered is integrated. Value values are –
     *  Ethernet
     *  Serial
     *  USB
     *  Wi-Fi
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchants
     */
    private String portType;

    /**
     * 226 POS Vendor Name C 35 AN Mandatory only for TPPs where Fiserv provides POS
     * Terminal to sub-merchants. This is only needed if
     * terminal type to be offered is integrated.
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchants
     * Note: POS vendor must be from the list of accredited
     * companies see below.
     * PC-EFTPOS:
     * https://pceftpos.com/media/PC-EFTPOS Accredited
     * Companies.pdf
     * POSGATE: https://apac.ingenico.com/smartterminals/posgate/banks/accredited-posgate-vendors
     */
    private String posVendorName;

    /**
     * 227 POS Vendor Contact Number C 10 AN Mandatory only for TPPs where Fiserv provides POS
     * Terminal to
     * sub-merchants. This is only needed if terminal type to
     * be offered is integrated. This must be filled with POS
     * Vendors contact number
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchants
     */
    private String posVendorContactNumber;

    /**
     * 228 POS Vendor Email C 50 AN Mandatory only for TPPs where Fiserv provides POS
     * Terminal to
     * sub-merchants. This is only needed if terminal type to
     * be offered is integrated. This must be filled with POS
     * Vendors email address
     * Should be left blank for TPPs with CNP/ecommerce
     * merchants and their own POS terminal/Solutions to
     * sub-merchants
     */
    private String posVendorEmail;

    /**
     * 229 Percent Moto M 3 N Percentage of Order placed over Mail
     * Order/Telephone Order. Sum of MOTO, InStore and
     * Internet should not exceed 100
     */
    private String percentMoto;

    /**
     * 230 Percent InStore M 3 N Percentage of Order placed In Store
     * Sum of MOTO, In-Store and Internet should not
     * exceed 100
     */
    private String percentInStore;

    /**
     * 231 Percent Internet M 3 N Percentage of Order placed
     * on Internet
     * Sum of MOTO, InStore and Internet should not
     * exceed 100
     */
    private String percentInternet;

    /**
     * 232 Percent Delivered 0 Days M 3 N Percentage of order delivered in zero days
     * Sum of all delivery days should not exceed 100
     */
    private String percentDelivered0Days;

    /**
     * 233 Percent Delivered 7 Days M 3 N Percentage of Order delivered in
     * 1–7 days
     * Sum of all delivery days should not exceed 100
     */
    private String percentDelivered7Days;

    /**
     * 234 Percent Delivered 14 Days M 3 N Percentage of Order delivered in 8–14 days
     * Sum of all delivery days should not exceed 100
     */
    private String percentDelivered14Days;

    /**
     * 235 Percent Delivered 30 Days M 3 N Percentage of Order delivered in 15–30 days
     * Sum of all delivery days should not exceed 100
     */
    private String percentDelivered30Days;

    /**
     * 236 Percent Delivered More 30 Days M 3 N Percentage of Order delivered in more than 30 days
     * Sum of all delivery days should not exceed 100
     */
    private String percentDeliveredMore30Days;

    /**
     * 237 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture237;

    /**
     * 238 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture238;

    /**
     * 239 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture239;

    /**
     * 240 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture240;

    /**
     * 241 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture241;

    /**
     * 242 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture242;

    /**
     * 243 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture243;

    /**
     * 244 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture244;

    /**
     * 245 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture245;

    /**
     * 246 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String PlaceholderFuture246;

    /**
     * 247 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String Placeholder240;


    /**
     * 248 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String Placeholder241;


    /**
     * 249 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String placeholder242;

    /**
     * 250 Placeholder Future Usage O RFU – Reserved for future use
     */
    private String placeholder243;

    @Override
    public String toString() {
        return new StringJoiner(", ", FileDetail.class.getSimpleName() + "[", "]")
                .add("recordType='" + recordType + "'")
                .add("merchantNumber=" + merchantNumber)
                .add("merchantLevel='" + merchantLevel + "'")
                .add("parentClient='" + parentClient + "'")
                .add("tradingBusinessName='" + tradingBusinessName + "'")
                .add("legalName='" + legalName + "'")
                .add("abn=" + abn)
                .add("acn=" + acn)
                .add("webAddress='" + webAddress + "'")
                .add("dcc='" + dcc + "'")
                .add("typeOfBusiness='" + typeOfBusiness + "'")
                .add("mcc='" + mcc + "'")
                .add("ecommerceEnabled='" + ecommerceEnabled + "'")
                .add("tradingAddress='" + tradingAddressContactName + "'")
                .add("tradingAddressLine1='" + tradingAddressLine1 + "'")
                .add("tradingAddressLine2='" + tradingAddressLine2 + "'")
                .add("tradingAddressLine3='" + tradingAddressLine3 + "'")
                .add("tradingAddressLine4='" + tradingAddressLine4 + "'")
                .add("tradingAddressCity='" + tradingAddressCity + "'")
                .add("tradingAddressState='" + tradingAddressState + "'")
                .add("tradingAddressPostcode='" + tradingAddressPostcode + "'")
                .add("tradingAddressCountry='" + tradingAddressCountry + "'")
                .add("legalAddressContactName='" + legalAddressContactName + "'")
                .add("legalAddressLine1='" + legalAddressLine1 + "'")
                .add("legalAddressLine2='" + legalAddressLine2 + "'")
                .add("legalAddressLine3='" + legalAddressLine3 + "'")
                .add("legalAddressLine4='" + legalAddressLine4 + "'")
                .add("legalAddressCity='" + legalAddressCity + "'")
                .add("legalAddressState='" + legalAddressState + "'")
                .add("legalAddressPostcode='" + legalAddressPostcode + "'")
                .add("legalAddressCountry='" + legalAddressCountry + "'")
                .add("signedDate='" + signedDate + "'")
                .add("totalAnnualCashOrCreditDebitTurnover='" + totalAnnualCashOrCreditDebitTurnover + "'")
                .add("totalAnnualCreditTurnover='" + totalAnnualCreditTurnover + "'")
                .add("averageTicketOrSalesAmount='" + averageTicketOrSalesAmount + "'")
                .add("captureMethod='" + captureMethod + "'")
                .add("depositRequired='" + depositRequired + "'")
                .add("depositPercentage='" + depositPercentage + "'")
                .add("averageDeliveryTimeframe='" + averageDeliveryTimeframe + "'")
                .add("recurringTran='" + recurringTran + "'")
                .add("terminalType='" + terminalType + "'")
                .add("configCode='" + configCode + "'")
                .add("termQty='" + termQty + "'")
                .add("acquiringAusdebit='" + acquiringAusdebit + "'")
                .add("acquiringVisaMasterCard='" + acquiringVisaMasterCard + "'")
                .add("acquiringAmex='" + acquiringAmex + "'")
                .add("amexExisting='" + amexExisting + "'")
                .add("amexSENumber='" + amexSENumber + "'")
                .add("amexNewRequired='" + amexNewRequired + "'")
                .add("acquiringDiners='" + acquiringDiners + "'")
                .add("dinersSENumber='" + dinersSENumber + "'")
                .add("principal1FirstName='" + principal1FirstName + "'")
                .add("principal1MiddleInitial='" + principal1MiddleInitial + "'")
                .add("principal1Surname='" + principal1Surname + "'")
                .add("principal1Type='" + principal1Type + "'")
                .add("principal1DateOfBirth='" + principal1DateOfBirth + "'")
                .add("principal1AddressLine1='" + principal1AddressLine1 + "'")
                .add("principal1AddressLine2='" + principal1AddressLine2 + "'")
                .add("principal1AddressLine3='" + principal1AddressLine3 + "'")
                .add("principal1City='" + principal1City + "'")
                .add("principal1State='" + principal1State + "'")
                .add("principal1PostCode='" + principal1PostCode + "'")
                .add("principal1Country='" + principal1Country + "'")
                .add("principal1PhoneNumber='" + principal1PhoneNumber + "'")
                .add("principal1PassportNumber='" + principal1PassportNumber + "'")
                .add("principal1DriversLicense='" + principal1DriversLicense + "'")
                .add("principal1DriversLicenseState='" + principal1DriversLicenseState + "'")
                .add("principal2FirstName='" + principal2FirstName + "'")
                .add("principal2MiddleInitial='" + principal2MiddleInitial + "'")
                .add("principal2Surname='" + principal2Surname + "'")
                .add("principal2Type='" + principal2Type + "'")
                .add("principal2DateOfBirth='" + principal2DateOfBirth + "'")
                .add("principal2AddressLine1='" + principal2AddressLine1 + "'")
                .add("principal2AddressLine2='" + principal2AddressLine2 + "'")
                .add("principal2AddressLine3='" + principal2AddressLine3 + "'")
                .add("principal2City='" + principal2City + "'")
                .add("principal2State='" + principal2State + "'")
                .add("principal2Postcode='" + principal2Postcode + "'")
                .add("principal2Country='" + principal2Country + "'")
                .add("principal2PhoneNumber='" + principal2PhoneNumber + "'")
                .add("principal2PassportNumber='" + principal2PassportNumber + "'")
                .add("principal2DriversLicense='" + principal2DriversLicense + "'")
                .add("principal2DriversLicenseState='" + principal2DriversLicenseState + "'")
                .add("principal3FirstName='" + principal3FirstName + "'")
                .add("principal3MiddleInitial='" + principal3MiddleInitial + "'")
                .add("principal3Surname='" + principal3Surname + "'")
                .add("principal3Type='" + principal3Type + "'")
                .add("principal3DateOfBirth='" + principal3DateOfBirth + "'")
                .add("principal3AddressLine1='" + principal3AddressLine1 + "'")
                .add("principal3AddressLine2='" + principal3AddressLine2 + "'")
                .add("principal3AddressLine3='" + principal3AddressLine3 + "'")
                .add("principal3City='" + principal3City + "'")
                .add("principal3State='" + principal3State + "'")
                .add("principal3Postcode='" + principal3Postcode + "'")
                .add("principal3Country='" + principal3Country + "'")
                .add("principal3PhoneNumber='" + principal3PhoneNumber + "'")
                .add("principal3PassportNumber='" + principal3PassportNumber + "'")
                .add("principal3DriversLicense='" + principal3DriversLicense + "'")
                .add("principal3DriversLicenseState='" + principal3DriversLicenseState + "'")
                .add("principal4FirstName='" + principal4FirstName + "'")
                .add("principal4MiddleInitial='" + principal4MiddleInitial + "'")
                .add("principal4Surname='" + principal4Surname + "'")
                .add("principal4Type='" + principal4Type + "'")
                .add("principal4DateOfBirth='" + principal4DateOfBirth + "'")
                .add("principal4AddressLine1='" + principal4AddressLine1 + "'")
                .add("principal4AddressLine2='" + principal4AddressLine2 + "'")
                .add("principal4AddressLine3='" + principal4AddressLine3 + "'")
                .add("principal4City='" + principal4City + "'")
                .add("principal4State='" + principal4State + "'")
                .add("principal4Postcode='" + principal4Postcode + "'")
                .add("principal4Country='" + principal4Country + "'")
                .add("principal4PhoneNumber='" + principal4PhoneNumber + "'")
                .add("principal4PassportNumber='" + principal4PassportNumber + "'")
                .add("principal4DriversLicense='" + principal4DriversLicense + "'")
                .add("principal4DriversLicenseState='" + principal4DriversLicenseState + "'")
                .add("principal5FirstName='" + principal5FirstName + "'")
                .add("principal5MiddleInitial='" + principal5MiddleInitial + "'")
                .add("principal5Surname='" + principal5Surname + "'")
                .add("principal5Type='" + principal5Type + "'")
                .add("principal5DateOfBirth='" + principal5DateOfBirth + "'")
                .add("principal5AddressLine1='" + principal5AddressLine1 + "'")
                .add("principal5AddressLine2='" + principal5AddressLine2 + "'")
                .add("principal5AddressLine3='" + principal5AddressLine3 + "'")
                .add("principal5City='" + principal5City + "'")
                .add("principal5State='" + principal5State + "'")
                .add("principal5Postcode='" + principal5Postcode + "'")
                .add("principal5Country='" + principal5Country + "'")
                .add("principal5PhoneNumber='" + principal5PhoneNumber + "'")
                .add("principal5PassportNumber='" + principal5PassportNumber + "'")
                .add("principal5DriversLicense='" + principal5DriversLicense + "'")
                .add("principal5DriversLicenseState='" + principal5DriversLicenseState + "'")
                .add("principal6FirstName='" + principal6FirstName + "'")
                .add("principal6MiddleInitial='" + principal6MiddleInitial + "'")
                .add("principal6Surname='" + principal6Surname + "'")
                .add("principal6Type='" + principal6Type + "'")
                .add("principal6DateOfBirth='" + principal6DateOfBirth + "'")
                .add("principal6AddressLine1='" + principal6AddressLine1 + "'")
                .add("principal6AddressLine2='" + principal6AddressLine2 + "'")
                .add("principal6AddressLine3='" + principal6AddressLine3 + "'")
                .add("principal6City='" + principal6City + "'")
                .add("principal6State='" + principal6State + "'")
                .add("principal6Postcode='" + principal6Postcode + "'")
                .add("principal6Country='" + principal6Country + "'")
                .add("principal6PhoneNumber='" + principal6PhoneNumber + "'")
                .add("principal6PassportNumber='" + principal6PassportNumber + "'")
                .add("principal6DriversLicense='" + principal6DriversLicense + "'")
                .add("principal6DriversLicenseState='" + principal6DriversLicenseState + "'")
                .add("principal7FirstName='" + principal7FirstName + "'")
                .add("principal7MiddleInitial='" + principal7MiddleInitial + "'")
                .add("principal7Surname='" + principal7Surname + "'")
                .add("principal7Type='" + principal7Type + "'")
                .add("principal7DateOfBirth='" + principal7DateOfBirth + "'")
                .add("principal7AddressLine1='" + principal7AddressLine1 + "'")
                .add("principal7AddressLine2='" + principal7AddressLine2 + "'")
                .add("principal7AddressLine3='" + principal7AddressLine3 + "'")
                .add("principal7City='" + principal7City + "'")
                .add("principal7State='" + principal7State + "'")
                .add("principal7Postcode='" + principal7Postcode + "'")
                .add("principal7Country='" + principal7Country + "'")
                .add("principal7PhoneNumber='" + principal7PhoneNumber + "'")
                .add("principal7PassportNumber='" + principal7PassportNumber + "'")
                .add("principal7DriversLicense='" + principal7DriversLicense + "'")
                .add("principal7DriversLicenseState='" + principal7DriversLicenseState + "'")
                .add("principal8FirstName='" + principal8FirstName + "'")
                .add("principal8MiddleInitial='" + principal8MiddleInitial + "'")
                .add("principal8Surname='" + principal8Surname + "'")
                .add("principal8Type='" + principal8Type + "'")
                .add("principal8DateOfBirth='" + principal8DateOfBirth + "'")
                .add("principal8AddressLine1='" + principal8AddressLine1 + "'")
                .add("principal8AddressLine2='" + principal8AddressLine2 + "'")
                .add("principal8AddressLine3='" + principal8AddressLine3 + "'")
                .add("principal8City='" + principal8City + "'")
                .add("principal8State='" + principal8State + "'")
                .add("principal8Postcode='" + principal8Postcode + "'")
                .add("principal8Country='" + principal8Country + "'")
                .add("principal8PhoneNumber='" + principal8PhoneNumber + "'")
                .add("principal8PassportNumber='" + principal8PassportNumber + "'")
                .add("principal8DriversLicense='" + principal8DriversLicense + "'")
                .add("principal8DriversLicenseState='" + principal8DriversLicenseState + "'")
                .add("principal9FirstName='" + principal9FirstName + "'")
                .add("principal9MiddleInitial='" + principal9MiddleInitial + "'")
                .add("principal9Surname='" + principal9Surname + "'")
                .add("principal9Type='" + principal9Type + "'")
                .add("principal9DateOfBirth='" + principal9DateOfBirth + "'")
                .add("principal9AddressLine1='" + principal9AddressLine1 + "'")
                .add("principal9AddressLine2='" + principal9AddressLine2 + "'")
                .add("principal9AddressLine3='" + principal9AddressLine3 + "'")
                .add("principal9City='" + principal9City + "'")
                .add("principal9State='" + principal9State + "'")
                .add("principal9Postcode='" + principal9Postcode + "'")
                .add("principal9Country='" + principal9Country + "'")
                .add("principal9PhoneNumber='" + principal9PhoneNumber + "'")
                .add("principal9PassportNumber='" + principal9PassportNumber + "'")
                .add("principal9DriversLicense='" + principal9DriversLicense + "'")
                .add("principal9DriversLicenseState='" + principal9DriversLicenseState + "'")
                .add("principal10FirstName='" + principal10FirstName + "'")
                .add("principal10MiddleInitial='" + principal10MiddleInitial + "'")
                .add("principal10Surname='" + principal10Surname + "'")
                .add("principal10Type='" + principal10Type + "'")
                .add("principal10DateOfBirth='" + principal10DateOfBirth + "'")
                .add("principal10AddressLine1='" + principal10AddressLine1 + "'")
                .add("principal10AddressLine2='" + principal10AddressLine2 + "'")
                .add("principal10AddressLine3='" + principal10AddressLine3 + "'")
                .add("principal10City='" + principal10City + "'")
                .add("principal10State='" + principal10State + "'")
                .add("principal10Postcode='" + principal10Postcode + "'")
                .add("principal10Country='" + principal10Country + "'")
                .add("principal10PhoneNumber='" + principal10PhoneNumber + "'")
                .add("principal10PassportNumber='" + principal10PassportNumber + "'")
                .add("principal10DriversLicense='" + principal10DriversLicense + "'")
                .add("principal10DriversLicenseState='" + principal10DriversLicenseState + "'")
                .add("upi='" + upi + "'")
                .add("merchantOnsiteContactName='" + merchantOnsiteContactName + "'")
                .add("merchantOnsiteContactNo='" + merchantOnsiteContactNo + "'")
                .add("merchantOnsiteAlternateContactNo='" + merchantOnsiteAlternateContactNo + "'")
                .add("merchantContactEmail='" + merchantContactEmail + "'")
                .add("computerOperationSystem='" + computerOperationSystem + "'")
                .add("portType='" + portType + "'")
                .add("posVendorName='" + posVendorName + "'")
                .add("posVendorContactNumber='" + posVendorContactNumber + "'")
                .add("posVendorEmail='" + posVendorEmail + "'")
                .add("percentMoto='" + percentMoto + "'")
                .add("percentInStore='" + percentInStore + "'")
                .add("percentInternet='" + percentInternet + "'")
                .add("percentDelivered0Days='" + percentDelivered0Days + "'")
                .add("percentDelivered7Days='" + percentDelivered7Days + "'")
                .add("percentDelivered14Days='" + percentDelivered14Days + "'")
                .add("percentDelivered30Days='" + percentDelivered30Days + "'")
                .add("percentDeliveredMore30Days='" + percentDeliveredMore30Days + "'")
                .add("PlaceholderFuture237='" + PlaceholderFuture237 + "'")
                .add("PlaceholderFuture238='" + PlaceholderFuture238 + "'")
                .add("PlaceholderFuture239='" + PlaceholderFuture239 + "'")
                .add("PlaceholderFuture240='" + PlaceholderFuture240 + "'")
                .add("PlaceholderFuture241='" + PlaceholderFuture241 + "'")
                .add("PlaceholderFuture242='" + PlaceholderFuture242 + "'")
                .add("PlaceholderFuture243='" + PlaceholderFuture243 + "'")
                .add("PlaceholderFuture244='" + PlaceholderFuture244 + "'")
                .add("PlaceholderFuture245='" + PlaceholderFuture245 + "'")
                .add("PlaceholderFuture246='" + PlaceholderFuture246 + "'")
                .add("PlaceholderFuture247='" + Placeholder240 + "'")
                .add("PlaceholderFuture248='" + Placeholder241 + "'")
                .add("placeholder242='" + placeholder242 + "'")
                .add("placeholder243='" + placeholder243 + "'")
                .toString();
    }
}
