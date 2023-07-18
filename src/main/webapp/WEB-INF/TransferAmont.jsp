<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Money</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<%@include file="./base.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
  
  
    <script type="text/javascript">
    $(document).ready(function() {
       
    	$('.debiteamount').on('input', function() {
            var debiteAmount = parseFloat($(this).val());
            var saveAmount = parseFloat($('.saveamount').val());

            if (debiteAmount > saveAmount) {
                alert('Insufficient funds');
                $(this).val('');
            } else {
            	var newSaveAmount = (saveAmount - debiteAmount).toFixed(4);
                $('.saveamount').val(newSaveAmount);
            }
        });

    	
    	$('.selectedbank').on('change', function() {
            var selectedOption = $(this).find('option:selected');
            var bankId = selectedOption.val();
            var bankName = selectedOption.text();
            var baseUrl = 'http://localhost:8080/customer';
            var urll = baseUrl + '/getBankById/' + bankId;
            $.ajax({
  			  type: "GET",
  			  url: urll,
  			  dataType: 'json',
  			  success : function(result){
  				  console.log(result);
  				
  				 $('.bankamount').text(result.amount);
  				 $('.bankamount').val(result.amount);
  			  },
  			  error: function() {
  			         console.log('Error occurred while retrieving employee data.');
  			      }
  		  });

            
        });
    	
    	
    	
    });
    
    function showBanks(id) {
    	  var baseUrl = 'http://localhost:8080/customer';
    	  var url = baseUrl + '/getBankToId/' + id;
    	  
    	  $.ajax({
    	    type: "GET",
    	    url: url,
    	    dataType: 'json',
    	    success: function(result) {
    	      console.log(result);

    	      var selectElement = $('.banks');
    	      selectElement.empty(); 
    	      
    	      $.each(result, function(index, item) {
    	        var option = $('<option>').val(item.id).text(item.bankname);
    	        selectElement.append(option);
    	      });

    	      $('.hidden-row').show();
    	    },
    	    error: function() {
    	      console.log('Error occurred while retrieving employee data.');
    	    }
    	  });
    	}


   

    </script>
</head>
<body class="form-center">
  <form:form action="${pageContext.request.contextPath }/savetransaction" class="ml-5 mr-5 mt-5" method="POST" modelAttribute="transaction"> 
  <div class="form-row ml-4 mt-3">
    <div class="col-md-3 mb-3">
      <label for="validationDefault01">Name</label>
      <form:input path="fromname"  class="form-control" placeholder="Enter your Name" required="required"/>  
          <form:input  type="hidden" path="fromid" />
    </div>
   <%--   <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault03">Amount</label>
      <form:input  path="fromamount"  type="number" class="form-control saveamount " readonly="true"/>       
    </div> --%>
     <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault03">Banks</label>
      <select class="form-control selectedbank" name="frombankId">
            <c:forEach var="n" items="${b}">              
             <option  value="${n.id}">${n.bankname}</option>
             </c:forEach>
        </select>          
    </div>
     <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault03">Amount</label>
      <form:input  path="fromamount"  type="number" class="form-control saveamount bankamount" readonly="true"/>       
    </div>  
  </div>
    To
    <div class="form-row ml-4 mt-3">
    <div class="col-md-3 mb-3">
      <label for="validationDefault01" >Name</label>
        <select class="form-control selectedname" name="toid" onchange="showBanks(this.value)">
            <c:forEach var="n" items="${c}">              
             <option  value="${n.id}">${n.name}</option>
             </c:forEach>
        </select>                       
    </div>
    <div class="col-md-3 mb-3 ml-5 hidden-row" style="display: none">
      <label for="validationDefault01" >Banks</label >
       <select class="form-control selectedname tobank banks"  name="tobankId"></select>
                            
    </div>
     <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault03">Amount</label>
      <form:input id="toamount" path="toamount" class="form-control debiteamount" type="number"/> 
     <!--  id="toamount"  -->     
    </div>
  </div>
  <br>
  <input  type="submit" id="submit-btn" class="btn btn-primary " value="Submit" /> 
   <a href="/customer/"><button type="button"  class="btn btn-danger" value="Cancel">Cancel</button></a>
</form:form>
</body>
</html>