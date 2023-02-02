<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SBAM</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
  <link rel="stylesheet" href="/spsv1/css/style.css">
  <spring:url value="/js/validation.js" var="validationjs" />
<script src="${ validationjs }"></script>

</head>
<body id="mainbody">
<jsp:include page="header.jsp"></jsp:include>

<div class="rightsidediv">

<div class="panel-group cheprocess">
	<div class="panel mainheading">
		<div class="panel-heading" id="refont">:: Reassign Task ::</div>
		<div class="panel-body" id="rstask">
	    <h3 id="err_msg">${ error1 }</h3>
	    
		<form:form action="/spsv1/cheque/savedes" method="post" modelAttribute="cheque">
	
			<div class="panel-group nidiv2">
				<div class="panel panel-primary">
					<div class="panel-heading" align="center">Cheque Reprocessing</div>
						<div class="panel-body">
						    <div class="row readonlydata">
						      	<div class="col-sm-6 col-md-6">
						      		<table class="cncd">
						      			<tr>
						      				<td>Customer Name : </td>
						      				<td><input type="text" name="name" readonly value="${cheque.getCustName()}" onCopy="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      			<!-- clientId in cheque reprocessing UI===custid in CNC file -->
						      				<td>Client Id : </td>
						      				<td><input type="text" name="clientId" readonly value="${cheque.getClientId()}" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Policy No : </td>
						      				<td><input type="text" name="rpolicyno" id="rpolicyno" readonly value="${ cheque.policyno }"></td>
						      			</tr>
						      			<tr>
						      				<td>Cross Ref. Policy : </td>
						      				<td><input type="text" name="refpolicyno" readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Application No.: </td>
						      				<td><input type="text" name="applicationNo" value="${ cheque.applicationNo}" readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Cross Application No. : </td>
						      				<td><input type="text" name="capplicationNo"   readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      
						      			<tr>
						      				<td>Date : </td>
						      				<td><input type="text" name="date" readonly value="" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      	</table>
						      	</div>
						      	<div class="col-sm-6 col-md-6">
						      		<table class="cncd">
						      		<tr>
						      				<td>GMID : </td>
						      				<td><input type="text" name="gmId" readonly value="" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      		
						      			<tr>
						      				<td>PRN No : </td>
						      				<td><input type="text" name="prnNo" readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Payment mode : </td>
						      				<td><input type="text" readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Payment reason : </td>
						      				<td><input type="text" name="paymentReason" readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Bank name : </td>
						      				<td><input type="text" name="bankName"value="${cheque. getBankName()}"  readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Bank code : </td>
						      				<td><input type="text" name="bankCode"  readonly onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<%-- <tr>
						      				<td>SAS Code : </td>
						      				<td><input type="text" name="sasCode" readonly value="${cheque.getSasCode()}" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>SAS Type : </td>
						      				<td><input type="text" name="sasType" readonly value="${cheque.getSasType()}" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			<tr>
						      				<td>Stage : </td>
						      				<td><input type="text" name="stage" readonly value="${cheque.getStage()}" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr> --%>
						      			<tr>
						      				<td>Funding By : </td>
						      				<td><input type="text" name="fundingBy" readonly value="" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      			 
						      		</table>
						      </div>
						 </div> 
					</div>
				</div>
		
				<div class="panel panel-primary">
					<div class="panel-heading" align="center">Validation to EPM details</div>
					<div class="panel-body">
						<div class="row readonlydata">
						    <div class="col-sm-6 col-md-6">
						      	<table class="fdetails">
						      		<tr>
						      			<td>Client Type : </td>
										<td><input type="text" name="clientType" value="${ cheque.getClientType() }" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		 </tr>
						      		
						      		  <tr>
						      			  <td>NRENRO Indication : </td>
						      			  <td>
						      			  	<select name="nrenroind" id="nrenroind">
												 <option value="">---Select---</option>
										         <option value="NRE">NRE</option>
										         <option value="NRO">NRO</option>
										         
										      </select>
						      			  </td>
						      		  </tr>
						      		  <tr>
						      			  <td>Payment Mode : </td>
						      			  <td>
						      			  	  <form:select path="paymentMode" id="paymentMode">
						      			  	  		<option value="">Select</option>
						      			  	  		<form:options items="${ modeOfPayment }"/>
						      			  	  </form:select>
						      			  </td>
						      		  </tr>
						      		  <tr>
						      			<td>M-account No. : </td>
										<td><input type="password" id="mAccountNumber" value="" name="mAccountNumber" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false" autocomplete="off""></td>
						      		 </tr>
						      		 <tr>
						      			<td>V-Account No. : </td>
						      			<td><input type="text" id="vAccountNumber" name="vAccountNumber" value="" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false" autocomplete = "off"></td>
						      		</tr>
						      	    <tr>
						      			<td>M-IFSC code : </td>						   
										<td><input type="text" name="mifscCode" id="mifscCode"  autocomplete="off" value="" onblur="getIfscDetails(this)" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		 </tr>
						      		
						      		 <tr>
						      			<td>Dispatched By : </td>
										<td><input type="text" name="dispatchedBy" readonly  value="${cheque.getDispatchedBy()}" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		 </tr>
						      		 <tr>
						      			<td>Instructions : </td>
										<td colspan=2><input type="text" name="instructions" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		 </tr>
						      	  </table>
						      </div>			
						      <div class="col-sm-6 col-md-6">
						      	<table class="fdetails">
						      	
						      	 <tr>
						      			<td> Assignee Id : </td>
										<td>
											<input type="text" name="assigneeId"  onblur="getAssigneeId(this)" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false">
										</td>
						      		  </tr> 
						      		<tr>
						      			<td>V Policy No. : </td>
						      			<%-- <td><form:input path="policyno"/> </td> --%>
						      			<td><input type="text" name="vPolicyNo" id="vPolicyNo" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false" autocomplete="off"/> </td>
						      		</tr>
						      		<tr>
						      			<td>Cust Name : </td>
						      			<td><input type="text" name="custName" readonly  value="${cheque.getCustName()}" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		</tr>
						      		<tr>
						      				<td>Client Id : </td>
						      				<td><input type="text" name="clientId1" readonly value="${cheque.getClientId()}" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      			</tr>
						      		 <tr>
						      			<td>M-MICR Code : </td>
										<td><input type="text" name="mmicrCode" id="mmicrCode"  value="${cheque.getMmicrCode()}"  onblur="getMicrDetails(this)" readonly  value="" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		 </tr>
						      		<tr>
						      			<td>Bank Name : </td>
						      			<td><input type="text" name="bankName" id="bankName"readonly  value="" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		</tr>
						      		<tr>
						      			<td>M-NM Code : </td>
						      			<td><input type="text" name="mnmCode" id="mnmCode" value="${cheque.getMnmCode()}"  readonly  value="" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		</tr>
						      		<tr>
						      			<td>Application No. : </td>
						      			<td><input type="text"  name="vapplicationno1" readonly autocomplete="off" id="applicationNo3" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false"></td>
						      		</tr>
						      	</table>
						     </div>
						 </div>
					</div>
				</div>
		
				<div class="panel panel-primary">
					<div class="panel-heading" align="center">Discrepancy Details</div>
					<div class="panel-body">
						<div class="row">
						    <div class="col-sm-12 col-md-12">
						      	<table class="discdetails">
						      		<tr>
						      			<td width="18%">Discrepancy Raised Ind <span style="color:red;">*</span> : </td>
						      			<td>
						      				<select id="selectchoice">
											
										         <option value="yes">Yes</option>
										         <option value="no" selected>No</option>
										      </select>
						      			</td>	
						      		</tr>
						      		<tr>
						      			<td>Discrepancy Tag : </td>
						      			<td>
						      			<div class="disctag">
						      			
						      					 <form:checkboxes items="${ discrepancyTags }" path="discrepancyTag"/> </br>
				      							 <input type="checkbox" name="othercheck" id="othercheck"> OTHER
				      							 <input type="text" name="adddiscr" id="adddiscr" autocomplete="off">
						      			</div>
						      		
						      			</td>
						      		</tr>
						      	</table>
						      </div>
						 </div>
					</div>
				</div>
		
				<div class="panel panel-primary">
					<div class="panel-heading" align="center">Other Remarks</div>
					<div class="panel-body">
						<div class="row">
						      	<div class="col-sm-6 col-md-6">
						      		<table class="cncd">
						      			<tr>
						      				<td>Remarks By : </td>
						      				<td class="oremark"><input type="text" name="remarksBy" value="${user.username }"></td>
						      			</tr>
						      			<tr>
						      				<td>Remarks : </td>
						      				<td class="oremark"><input type="text" name="remarks" id="remark1" value=""></td>
						      			</tr>
						      			<tr>
						      				<td>Maker Submitted On : </td>
						      				<td class="oremark"><input type="text" name="makerSubmittedOn"></td>
						      			</tr>
						      		</table>
						      	</div>
						      	<div class="col-sm-6 col-md-6">
						      		<table class="cncd">
						      			<tr>
						      				<td>Remark Date : </td>
						      				<td class="oremark"><input type="text" name="remarkDate"></td>
						      			</tr>
						      			<tr>
						      				<td>Reference : </td>
						      				<td class="oremark"><input type="text" name="referenceno" autocomplete="off"></td>
						      			</tr>
						      		</table>
						      	</div>
						 </div>    
					</div>
				</div>	
			</div> 
			
			<div class="chequebtndiv" align="center">
				<input type="submit" name="submit" value="Save" class="save">
				<!-- <button>Save and Home</button> -->
				<input type="submit" name="submit" value="Save and Next" class="save">			
				<button type="reset">Reset</button>
			</div> 
			
		</form:form>
			   
		</div>
	</div>
</div>				     
</div>
</body>
</html>