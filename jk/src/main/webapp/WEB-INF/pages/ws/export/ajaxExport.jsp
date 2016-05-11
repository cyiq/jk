<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
    	var xmlHTTPRequest = new ActiveXObject("Microsoft.XMLHTTP");
		alert(xmlHTTPRequest);
		alert("aaa");
    </script>
</head>
<body>
<form method="post">
	<input type="hidden" name="id" value="${obj.id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		出口报运跟踪
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">合同或确认书号：</td>
	            <td class="tableContent">${obj.customerContract}</td>
	            <td class="columnTitle_mustbe">制单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="inputDate" value="<fmt:formatDate value="${obj.inputDate}" pattern="yyyy-MM-dd"/>"
	             			onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">信用证号：</td>
	            <td class="tableContent"><input type="text" name="lcno" value="${obj.lcno}"/></td>
	            <td class="columnTitle_mustbe">收货人及地址：</td>
	            <td class="tableContent"><input type="text" name="consignee" value="${obj.consignee}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">装运港：</td>
	            <td class="tableContent"><input type="text" name="shipmentPort" value="${obj.shipmentPort}"/></td>
	            <td class="columnTitle_mustbe">目的港：</td>
	            <td class="tableContent"><input type="text" name="destinationPort" value="${obj.destinationPort}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">运输方式：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="transportMode" value="SEA" class="input" <c:if test="${obj.transportMode=='SEA'}">checked</c:if>>SEA
	            	<input type="radio" name="transportMode" value="AIR" class="input" <c:if test="${obj.transportMode=='AIR'}">checked</c:if>>AIR
	            </td>
	            <td class="columnTitle_mustbe">价格条件：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="priceCondition" value="FOB" class="input" <c:if test="${obj.priceCondition=='FOB'}">checked</c:if>>FOB
	            	<input type="radio" name="priceCondition" value="CIF" class="input" <c:if test="${obj.priceCondition=='CIF'}">checked</c:if>>CIF
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">唛头：</td>
	            <td class="tableContent"><textarea name="marks" style="height:120px;">${obj.marks}</textarea></td>
	            <td class="columnTitle_mustbe">备注：</td>
	            <td class="tableContent"><textarea name="remark" style="height:120px;">${obj.remark}</textarea></td>
	        </tr>
		</table>
	</div>
</div>


 
</form>
</body>
</html>

