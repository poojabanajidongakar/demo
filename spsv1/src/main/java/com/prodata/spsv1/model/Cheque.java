package com.prodata.spsv1.model;

import java.util.List;

public class Cheque 
{
	//@Size(min=11,max=11,message="IFSC Code IS not correct")
	private String mifscCode,call_id; 
	int chequeid;
	String factoryHouse,bankBranchCode,ifscCode,accountNo,accountType,name,indemnity,
	type,dispatchedBy,branchName,instructions,gmId,sasCode,sasType,clientId,stage,fundingBy,
	date,remarks,chRemarks,subtypeteam,pendingwith,
	/*------------------epm--------------------------*/
	nrenroIndication,vPolicyNo,paymentMode,custName,mAccountNumber,
	vAccountNumber,bankName,mmicrCode,mnmCode,applicationNo,crossApplicationNo,referenceno,oldprn,crossrefapplicationno,tfaccountno,tfifsccode,tfmicrcode,tfSparcAcno,
	/*--------discrepancy----------*/
	discrepancyInd,discrepancyTag,username,srNo,
	/*-------paymentqueriesdetails--------*/
	paymentHoldInd,holdIndication,tofInd,navInd,repatInd,clientIndicator,payinPayoutInd,
	ombudsmentInd,legalnoticeInd,courtInd,duplicateInd,vendorName,descr,checkName,oneRsName, reason,FinalRemarkOners,
	/*--------otherremarks---------*/
	remarksBy,remarkDate,makerSubmittedOn,policyno,assigneeId,custId,clientType,crossRefPolicy,status,reference,prnNo,paymentType,mos,bankCode,crossrefPolicy2
	
	/*sampling MIs */
	 ,needcheckok,needCheckreverse,partialOk,partialReverse,currentStatus,subtype,id,makerBy,checkerBy,finalRemark,finalReason,
	 checkerName,checkerRemark,makerName,makerRemark,checkerReason;
	double okntc,revntc,parok, parrev;
	float amount;
	
	public String getCheckerReason() {
		return checkerReason;
	}
	
	public String getIndemnity() {
		return indemnity;
	}

	public void setIndemnity(String indemnity) {
		this.indemnity = indemnity;
	}

	public void setCheckerReason(String checkerReason) {
		this.checkerReason = checkerReason;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public String getCheckerRemark() {
		return checkerRemark;
	}
	public void setCheckerRemark(String checkerRemark) {
		this.checkerRemark = checkerRemark;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getMakerRemark() {
		return makerRemark;
	}
	public void setMakerRemark(String makerRemark) {
		this.makerRemark = makerRemark;
	}
	public String getMakerBy() {
		return makerBy;
	}
	public void setMakerBy(String makerBy) {
		this.makerBy = makerBy;
	}
	public String getCheckerBy() {
		return checkerBy;
	}
	public void setCheckerBy(String checkerBy) {
		this.checkerBy = checkerBy;
	}
	public String getFinalRemark() {
		return finalRemark;
	}
	public void setFinalRemark(String finalRemark) {
		this.finalRemark = finalRemark;
	}
	public String getFinalReason() {
		return finalReason;
	}
	public void setFinalReason(String finalReason) {
		this.finalReason = finalReason;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/*presampling*/
	String sapName,sapPolicy,processingDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getProcessingDate() {
		return processingDate;
	}
	public void setProcessingDate(String processingDate) {
		this.processingDate = processingDate;
	}
	public String getSapName() {
		return sapName;
	}
	public void setSapName(String sapName) {
		this.sapName = sapName;
	}
	public String getSapPolicy() {
		return sapPolicy;
	}
	public void setSapPolicy(String sapPolicy) {
		this.sapPolicy = sapPolicy;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getPartialReverse() {
		return partialReverse;
	}
	public void setPartialReverse(String partialReverse) {
		this.partialReverse = partialReverse;
	}
	public String getNeedcheckok() {
		return needcheckok;
	}
	public void setNeedcheckok(String needcheckok) {
		this.needcheckok = needcheckok;
	}
	public String getNeedCheckreverse() {
		return needCheckreverse;
	}
	public void setNeedCheckreverse(String needCheckreverse) {
		this.needCheckreverse = needCheckreverse;
	}
	public String getPartialOk() {
		return partialOk;
	}
	public void setPartialOk(String partialOk) {
		this.partialOk = partialOk;
	}
	
	
	
	public double getOkntc() {
		return okntc;
	}
	public void setOkntc(double okntc) {
		this.okntc = okntc;
	}
	public double getRevntc() {
		return revntc;
	}
	public void setRevntc(double revntc) {
		this.revntc = revntc;
	}
	public double getParok() {
		return parok;
	}
	public void setParok(double parok) {
		this.parok = parok;
	}
	public double getParrev() {
		return parrev;
	}
	public void setParrev(double parrev) {
		this.parrev = parrev;
	}
	public void setParrev(long parrev) {
		this.parrev = parrev;
	}
	public String getFinalRemarkOners() {
		return FinalRemarkOners;
	}
	public void setFinalRemarkOners(String finalRemarkOners) {
		FinalRemarkOners = finalRemarkOners;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOneRsName() {
		return oneRsName;
	}
	public void setOneRsName(String oneRsName) {
		this.oneRsName = oneRsName;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getCrossrefPolicy2() {
		return crossrefPolicy2;
	}
	public void setCrossrefPolicy2(String crossrefPolicy2) {
		this.crossrefPolicy2 = crossrefPolicy2;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMos() {
		return mos;
	}
	public String getTfaccountno() {
		return tfaccountno;
	}
	public void setTfaccountno(String tfaccountno) {
		this.tfaccountno = tfaccountno;
	}
	public String getTfifsccode() {
		return tfifsccode;
	}
	public void setTfifsccode(String tfifsccode) {
		this.tfifsccode = tfifsccode;
	}
	public String getTfmicrcode() {
		return tfmicrcode;
	}
	public void setTfmicrcode(String tfmicrcode) {
		this.tfmicrcode = tfmicrcode;
	}
	public String getTfSparcAcno() {
		return tfSparcAcno;
	}
	public void setTfSparcAcno(String tfSparcAcno) {
		this.tfSparcAcno = tfSparcAcno;
	}
	public void setMos(String mos) {
		this.mos = mos;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPrnNo() {
		return prnNo;
	}
	public void setPrnNo(String prnNo) {
		this.prnNo = prnNo;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getOldprn() {
		return oldprn;
	}
	public void setOldprn(String oldprn) {
		this.oldprn = oldprn;
	}
	public String getCrossrefapplicationno() {
		return crossrefapplicationno;
	}
	public void setCrossrefapplicationno(String crossrefapplicationno) {
		this.crossrefapplicationno = crossrefapplicationno;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public String getCrossApplicationNo() {
		return crossApplicationNo;
	}
	public void setCrossApplicationNo(String crossApplicationNo) {
		this.crossApplicationNo = crossApplicationNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private List<String> discrepancyTags ;
	
	public int getChequeid() {
		return chequeid;
	}
	public void setChequeid(int chequeid)
	{
		this.chequeid = chequeid;
	}
	public String getCall_id() {
		return call_id;
	}
	public void setCall_id(String call_id) 
	{
		this.call_id = call_id;
	}
	public String getCrossRefPolicy() {
		return crossRefPolicy;
	}
	public void setCrossRefPolicy(String crossRefPolicy) {
		this.crossRefPolicy = crossRefPolicy;
	}
	public String getPolicyno() {
		return policyno;
	}
	public String getNrenroIndication() {
		return nrenroIndication;
	}
	public void setNrenroIndication(String nrenroIndication) {
		this.nrenroIndication = nrenroIndication;
	}
	public void setPolicyno(String policyno) {
		//System.out.println(policyno+" in setter");
		this.policyno = policyno;
	}
	
	public String getFactoryHouse() {
		return factoryHouse;
	}
	public void setFactoryHouse(String factoryHouse) {
		this.factoryHouse = factoryHouse;
	}
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}*/
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDispatchedBy() {
		return dispatchedBy;
	}
	public void setDispatchedBy(String dispatchedBy) {
		this.dispatchedBy = dispatchedBy;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getGmId() {
		return gmId;
	}
	public void setGmId(String gmId) {
		this.gmId = gmId;
	}
	public String getSasCode() {
		return sasCode;
	}
	public void setSasCode(String sasCode) {
		this.sasCode = sasCode;
	}
	public String getSasType() {
		return sasType;
	}
	public void setSasType(String sasType) {
		this.sasType = sasType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getFundingBy() {
		return fundingBy;
	}
	public void setFundingBy(String fundingBy) {
		this.fundingBy = fundingBy;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getChRemarks() {
		return chRemarks;
	}
	public void setChRemarks(String chRemarks) {
		this.chRemarks = chRemarks;
	}
	public String getSubtypeteam() {
		return subtypeteam;
	}
	public void setSubtypeteam(String subtypeteam) {
		this.subtypeteam = subtypeteam;
	}
	public String getPendingwith() {
		return pendingwith;
	}
	public void setPendingwith(String pendingwith) {
		this.pendingwith = pendingwith;
	}

	public String getvPolicyNo() {
		return vPolicyNo;
	}
	public void setvPolicyNo(String vPolicyNo) {
		this.vPolicyNo = vPolicyNo;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDiscrepancyInd() {
		return discrepancyInd;
	}
	public void setDiscrepancyInd(String discrepancyInd) {
		this.discrepancyInd = discrepancyInd;
	}
	public String getDiscrepancyTag() {
		return discrepancyTag;
	}
	public void setDiscrepancyTag(String discrepancyTag) {
		this.discrepancyTag = discrepancyTag;
	}
	public String getPaymentHoldInd() {
		return paymentHoldInd;
	}
	public void setPaymentHoldInd(String paymentHoldInd) {
		this.paymentHoldInd = paymentHoldInd;
	}
	public String getHoldIndication() {
		return holdIndication;
	}
	public void setHoldIndication(String holdIndication) {
		this.holdIndication = holdIndication;
	}
	public String getTofInd() {
		return tofInd;
	}
	public void setTofInd(String tofInd) {
		this.tofInd = tofInd;
	}
	public String getNavInd() {
		return navInd;
	}
	public void setNavInd(String navInd) {
		this.navInd = navInd;
	}
	public String getRepatInd() {
		return repatInd;
	}
	public void setRepatInd(String repatInd) {
		this.repatInd = repatInd;
	}
	public String getClientIndicator() {
		return clientIndicator;
	}
	public void setClientIndicator(String clientIndicator) {
		this.clientIndicator = clientIndicator;
	}
	public String getPayinPayoutInd() {
		return payinPayoutInd;
	}
	public void setPayinPayoutInd(String payinPayoutInd) {
		this.payinPayoutInd = payinPayoutInd;
	}
	public String getOmbudsmentInd() {
		return ombudsmentInd;
	}
	public void setOmbudsmentInd(String ombudsmentInd) {
		this.ombudsmentInd = ombudsmentInd;
	}
	public String getLegalnoticeInd() {
		return legalnoticeInd;
	}
	public void setLegalnoticeInd(String legalnoticeInd) {
		this.legalnoticeInd = legalnoticeInd;
	}
	public String getCourtInd() {
		return courtInd;
	}
	public void setCourtInd(String courtInd) {
		this.courtInd = courtInd;
	}
	public String getDuplicateInd() {
		return duplicateInd;
	}
	public void setDuplicateInd(String duplicateInd) {
		this.duplicateInd = duplicateInd;
	}
	public String getRemarksBy() {
		return remarksBy;
	}
	public void setRemarksBy(String remarksBy) {
		this.remarksBy = remarksBy;
	}
	public String getRemarkDate() {
		return remarkDate;
	}
	public void setRemarkDate(String remarkDate) {
		this.remarkDate = remarkDate;
	}
	public String getMakerSubmittedOn() {
		return makerSubmittedOn;
	}
	public void setMakerSubmittedOn(String makerSubmittedOn) {
		this.makerSubmittedOn = makerSubmittedOn;
	}
	public String getmAccountNumber() {
		return mAccountNumber;
	}
	public void setmAccountNumber(String mAccountNumber) {
		this.mAccountNumber = mAccountNumber;
	}
	public String getvAccountNumber() {
		return vAccountNumber;
	}
	public void setvAccountNumber(String vAccountNumber) {
		this.vAccountNumber = vAccountNumber;
	}
	public String getMifscCode() {
		return mifscCode;
	}
	public void setMifscCode(String mifscCode) {
		this.mifscCode = mifscCode;
	}
	public String getMmicrCode() {
		return mmicrCode;
	}
	public void setMmicrCode(String mmicrCode) {
		this.mmicrCode = mmicrCode;
	}
	public String getMnmCode() {
		return mnmCode;
	}
	public void setMnmCode(String mnmCode) {
		this.mnmCode = mnmCode;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	
	public String getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}
	
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	
	public List<String> getDiscrepancyTags() {
		return discrepancyTags;
	}
	public void setDiscrepancyTags(List<String> discrepancyTags) {
		this.discrepancyTags = discrepancyTags;
	}

	@Override
	public String toString() {
		return "Cheque [mifscCode=" + mifscCode + ", call_id=" + call_id + ", chequeid=" + chequeid + ", factoryHouse="
				+ factoryHouse + ", bankBranchCode=" + bankBranchCode + ", ifscCode=" + ifscCode + ", accountNo="
				+ accountNo + ", accountType=" + accountType + ", name=" + name + ", indemnity=" + indemnity + ", type="
				+ type + ", dispatchedBy=" + dispatchedBy + ", branchName=" + branchName + ", instructions="
				+ instructions + ", gmId=" + gmId + ", sasCode=" + sasCode + ", sasType=" + sasType + ", clientId="
				+ clientId + ", stage=" + stage + ", fundingBy=" + fundingBy + ", date=" + date + ", remarks=" + remarks
				+ ", chRemarks=" + chRemarks + ", subtypeteam=" + subtypeteam + ", pendingwith=" + pendingwith
				+ ", nrenroIndication=" + nrenroIndication + ", vPolicyNo=" + vPolicyNo + ", paymentMode=" + paymentMode
				+ ", custName=" + custName + ", mAccountNumber=" + mAccountNumber + ", vAccountNumber=" + vAccountNumber
				+ ", bankName=" + bankName + ", mmicrCode=" + mmicrCode + ", mnmCode=" + mnmCode + ", applicationNo="
				+ applicationNo + ", crossApplicationNo=" + crossApplicationNo + ", referenceno=" + referenceno
				+ ", oldprn=" + oldprn + ", crossrefapplicationno=" + crossrefapplicationno + ", tfaccountno="
				+ tfaccountno + ", tfifsccode=" + tfifsccode + ", tfmicrcode=" + tfmicrcode + ", tfSparcAcno="
				+ tfSparcAcno + ", discrepancyInd=" + discrepancyInd + ", discrepancyTag=" + discrepancyTag
				+ ", username=" + username + ", srNo=" + srNo + ", paymentHoldInd=" + paymentHoldInd
				+ ", holdIndication=" + holdIndication + ", tofInd=" + tofInd + ", navInd=" + navInd + ", repatInd="
				+ repatInd + ", clientIndicator=" + clientIndicator + ", payinPayoutInd=" + payinPayoutInd
				+ ", ombudsmentInd=" + ombudsmentInd + ", legalnoticeInd=" + legalnoticeInd + ", courtInd=" + courtInd
				+ ", duplicateInd=" + duplicateInd + ", vendorName=" + vendorName + ", descr=" + descr + ", checkName="
				+ checkName + ", oneRsName=" + oneRsName + ", reason=" + reason + ", FinalRemarkOners="
				+ FinalRemarkOners + ", remarksBy=" + remarksBy + ", remarkDate=" + remarkDate + ", makerSubmittedOn="
				+ makerSubmittedOn + ", policyno=" + policyno + ", assigneeId=" + assigneeId + ", custId=" + custId
				+ ", clientType=" + clientType + ", crossRefPolicy=" + crossRefPolicy + ", status=" + status
				+ ", reference=" + reference + ", prnNo=" + prnNo + ", paymentType=" + paymentType + ", mos=" + mos
				+ ", bankCode=" + bankCode + ", crossrefPolicy2=" + crossrefPolicy2 + ", needcheckok=" + needcheckok
				+ ", needCheckreverse=" + needCheckreverse + ", partialOk=" + partialOk + ", partialReverse="
				+ partialReverse + ", currentStatus=" + currentStatus + ", subtype=" + subtype + ", id=" + id
				+ ", makerBy=" + makerBy + ", checkerBy=" + checkerBy + ", finalRemark=" + finalRemark
				+ ", finalReason=" + finalReason + ", checkerName=" + checkerName + ", checkerRemark=" + checkerRemark
				+ ", makerName=" + makerName + ", makerRemark=" + makerRemark + ", checkerReason=" + checkerReason
				+ ", okntc=" + okntc + ", revntc=" + revntc + ", parok=" + parok + ", parrev=" + parrev + ", amount="
				+ amount + ", sapName=" + sapName + ", sapPolicy=" + sapPolicy + ", processingDate=" + processingDate
				+ ", discrepancyTags=" + discrepancyTags + "]";
	}
	
	
	

}
