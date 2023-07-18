<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

    <!-- jQuery CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
    <script type="text/javascript">
     
    /* Address Table */
   /*  
    var itemIndex=1;
    function add() {
    	
    	  
    	  var emptyFields = $('#Tbody tr:last').find('input[type="text"]').filter(function() {
    	    return $(this).val() === '';
    	  });
          
    	  if (emptyFields.length > 0) {
    	    
    	    alert('Please fill all the fields in the current row.');
    	  } else {
    	   
    	    var existingItemCount = $('#Tbody tr').length;
    	    itemIndex = existingItemCount;
    	    var newRow = $('<tr id="Trow"><td><input type="hidden" class="idClass" /></td><td><input id="phone' + itemIndex + '" class="phoneClass" type="text" name="contactdetails[' + itemIndex + '].phone" placeholder="Enter phone number" required="required"><label class="phonemessage"></label></td><td><input id="email' + itemIndex + '" class="emailClass" type="email" name="contactdetails[' + itemIndex + '].email" placeholder="Enter your email" required="required"><label class="emailmessage"></label></td><td><input id="address' + itemIndex + '" type="text" name="contactdetails[' + itemIndex + '].address" placeholder="Enter your address" class="addressClass" required="required"></td><td><input id="pin' + itemIndex + '" type="text" name="contactdetails[' + itemIndex + '].pin" placeholder="Enter your PIN code" class="pinClass" required="required"></td><td><button id="btn1" type="button" onclick="remove(this)">Remove</button><br/></td></tr>');
    	    $('#Tbody').append(newRow);
    	    itemIndex++;
    	  }
    	}
 */
 
 $.fn.add = function(btnId, tableId){
	 let lastTr = $('#'+tableId).find('tbody').find('tr:last');
	 let clonedTr = lastTr.clone();
	 $(clonedTr).find('INPUT,SELECT').each(function(){
		 
		if(!$(this).hasClass('emailClass'))
		 	$(this).val('');
		 $(this).attr('name',function(){
			 var part = this.name.match(/^(\D+)(\d+)(\D+)$/); 
		     return (part[1]+(++part[2])+part[3]);
		 })
	 })
	 
	 $(clonedTr).insertAfter(lastTr);
 }
 
 
    function remove(v) {
        $(v).parent().parent().remove();
        var index = 0;
        
        $("#Tbody tr").each(function() {
        	$(this).find(".idClass").attr("name", "contactdetails[" + index + "].id");
            $(this).find(".phoneClass").attr("name", "contactdetails[" + index + "].phone");
            $(this).find(".emailClass").attr("name", "contactdetails[" + index + "].email");
            $(this).find(".addressClass").attr("name", "contactdetails[" + index + "].address");
            $(this).find(".pinClass").attr("name", "contactdetails[" + index + "].pin");
            index++;
        });
    }


    

     
    /* BankDetails Table */
    
    var index=1;
    function BtnAdd() {
    	  var inputFields = $('#Tbody1 tr:last').find('input[type="text"], select').filter(function() {
    	    return $(this).val() === '';
    	  });

    	  if (inputFields.length > 0) {
    	   
    	    alert('Please fill all the bank details in the current row.');
    	  } else {
    	    var existingItemCount = $('#Tbody1 tr').length;
    	    index = existingItemCount;
     	    var newRow = $('<tr id="Trow"><td><input type="hidden" class="bankidClass" required="required"</td><td><select id="bankname' + index + '" name="bankdetails[' + index + '].bankname" class="banknameClass" required="required"><option value="">Select bank name</option></select></td><td><input id="accNo' + index + '" type="text" name="bankdetails[' + index + '].accNo" placeholder="Enter your accou num" class="accNoClass" required="required"></td><td><input id="branch' + index + '" type="text" name="bankdetails[' + index + '].branch" placeholder="Enter your branch name" class="branchClass" required="required"></td><td><input id="amount' + index + '" type="text" name="bankdetails[' + index + '].amount" placeholder="Enter your amount" class="amountClass" required="required"></td><td><button id="btn3" type="button" onclick="remove1(this)" onchange="updateTotalAmount()">Remove</button><br/></td></tr>');
         /*  var newRow = $('#tlast:last').clone();
          <td><input id="branch' + index + '" type="text" name="bankdetails[' + index + '].branch" placeholder="Enter your branch name" class="branchClass" required="required"></td>
             newRow.find('input').val(''); */
            
 
    	    $('#Tbody1').append(newRow);
             updateBankOptions(index);
    	    index++;
    	  }
    	}

    function remove1(v) {
	        $(v).parent().parent().remove();
	        var bankindex = 0;
	     
	        $("#Tbody1 tr").each(function() {
	       
	        	$(this).find(".bankidClass").attr("name", "bankdetails[" + bankindex + "].id");
	            $(this).find(".banknameClass").attr("name", "bankdetails[" + bankindex + "].bankname");
	            $(this).find(".accNoClass").attr("name", "bankdetails[" + bankindex + "].accNo");
	            $(this).find(".branchClass").attr("name", "bankdetails[" + bankindex + "].branch");
	            $(this).find(".amountClass").attr("name", "bankdetails[" + bankindex + "].amount");
	            bankindex++;
	           
	        });
	        
	       
	     
	}
    
    function updateBankOptions(index) {
    	  $.ajax({
    	    type: "GET",
    	    url: 'http://localhost:8080/customer/getAllCreatedBanks',
    	    dataType: 'json',
    	    success: function(result) {
    	      console.log(result);

    	      var bankNameSelect = $('.banknameClass').eq(index);
    	      var branchInput = $('.branchClass').eq(index);

    	      bankNameSelect.empty(); // Clear existing options

    	      $.each(result, function(i, item) {
    	        var bankNameOption = $('<option>').val(item.nameofbank).text(item.nameofbank);
    	        bankNameSelect.append(bankNameOption);
    	      });

    	      bankNameSelect.on('change', function() {
    	        var selectedBankId = $(this).val();
    	        var selectedBank = result.find(function(bank) {
    	          return bank.id == selectedBankId;
    	        });

    	        branchInput.val(selectedBank.nameofbranch);
    	      });
    	    },
    	    error: function() {
    	      console.log('Error occurred while retrieving bank data.');
    	    }
    	  });
    	}

    
     $(document).ready(function() {
    	 
    	 updateTotalAmount();
    	 
    	 
    	 $('#Tbody1 tr').each(function() {
    	        var index = $(this).index();
    	        updateBankOptions(index);
    	    });
    	 
    	 $('#Tbody').on('blur', '.emailClass', function(){
    		 var email = $(this).val();
     	    var email_regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
     	   if (email_regex.test(email)) {
     	      $(this).removeClass('invalid').addClass('valid');
     	      $(this).closest('tr').find('.emailmessage').text('Email address is valid!').css('color', 'green');
     	    } else {
     	      $(this).removeClass('valid').addClass('invalid');
     	      $(this).closest('tr').find('.emailmessage').text('Please enter a valid email address.').css('color', 'red');
     	    }
    	 }); 
    	  
    	  $('#Tbody').on('blur', '.phoneClass', function(){
    	 		 var phone = $(this).val();
    	  	    var phone_regex = /^\d{10}$/;
    	  	   if (phone_regex.test(phone)) {
    	  	      $(this).removeClass('invalid').addClass('valid');
    	  	    $(this).closest('tr').find('.phonemessage').text('phone num is valid!').css('color', 'green');
    	  	    } else {
    	  	      $(this).removeClass('valid').addClass('invalid');
    	  	    $(this).closest('tr').find('.phonemessage').text('in valid!').css('color', 'red');
    	  	    }
    	 	 }); 
    	
    	 
    	  function updateTotalAmount() {
	      	    var total = 0;
	      	  
	      	    $('#Tbody1 tr').each(function() {
	      	     
	      	      var amount = $(this).find('.amountClass').val();
	      	     
	      	      total += parseFloat(amount) || 0;
	      	    });
	      	    console.log(total);
	      	    $('.totalamount').text(total);
	      	    $('#total').val(total);
	      	  }

	      	  
	      	  $('#Tbody1').on('change', '.amountClass', function() {
	      	    updateTotalAmount();
	      	  });
     
	      	 $('#DOB').on('change', 'input[type="date"]', function() {
	      	      var selectedDate = new Date($(this).val());
	      	      console.log(selectedDate);
	      	      var currentDate = new Date();
	      	       currentDate.setHours(0, 0, 0, 0);
	      	      console.log(currentDate);  
	      	      if (selectedDate < currentDate) {
	      	        $(this).val('');
	      	      $('#dateError').text('Entered date is invalid. Date should be greater than or equal to today.');
	      	      }else{
	      	    	$('#dateError').empty();
	      	    	$('#dateError2').text('');
	      	      }
	      	    });
	      	 
	      	
	      	 $('#DOB2').on('change', 'input[type="date"]', function() {
	      	    var fromDate = $('#DOB input').val();
	      	    var toDate = $(this).val();
	      	    console.log(fromDate);
	      	    console.log(toDate);
	      	    if (fromDate === '' || toDate === '') {
	      	    	$(this).val('');
	      	      $('#dateError2').text('Please select the From Date.');
	      	    } else {
	      	    
	      	      var fromDateObj = new Date(fromDate);
	      	      var toDateObj = new Date(toDate);
	      	      if (toDateObj <= fromDateObj) {
	      	        
	      	        $('#dateError2').text('To Date should be greater than From Date.');
	      	      } else {
	      	        
	      	        $('#dateError2').text('');
	      	      }
	      	    }
	      	  });
	      	 
	      	

	      	
     });
     
    

    	 
    	  
     
    </script>
    
   
    
   
    <style type="text/css">
    
     table {
        border-collapse: collapse;
        width: 100%;
    }
    
    th, td {
        text-align: left;
        padding: 8px;
        border-bottom: 1px solid #ddd;
    }
    
    th {
        background-color: #f2f2f2;
    }
    
    fieldset.scheduler-border {
  border: 2px solid #000;
  padding: 10px;
  margin: 10px;
  border-radius: 5px;
}
   

legend.scheduler-border {
  font-size: 1.2em !important;
  font-weight: bold !important;
  text-align: left !important;
  width: auto;
  padding: 0 10px;
  border-bottom: none;
  margin-top: -15px;
  background-color: white;
  color: black;
}
  .am{
    background: silver;
  }
    </style>
   
</head>
<body>
   
  <form:form action="${pageContext.request.contextPath }/save" class="ml-5 mr-5 mt-5" method="POST" modelAttribute="customerForm">
 <fieldset class="scheduler-border" >
   <legend  class="scheduler-border" "><h5>CustomerDeatils</h5></legend>
  <div class="form-row ml-4 mt-3">
  
    <div class="col-md-3 mb-3">
      <label for="validationDefault01">Name</label>
      <form:input path="name" class="form-control" placeholder="Enter your Name" required="required"/>  
          <form:input  type="hidden" path="id" />
    </div>
    <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault02">Gender</label>
      <form:input path="gender" class="form-control" placeholder="Enter your Gender" required="required"/>
    </div>
   <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault02">Adhar</label>
      <form:input path="adharNo"  class="form-control" placeholder="Enter your Adhar No" required="required"/>
      <!--  id only i am added now -->
    </div>
  </div>
  <div class="form-row ml-4 mt-3">
    <div class="col-md-3 mb-3 ">
      <label for="validationDefault03">Pan No</label>
      <form:input path="panNo" placeholder="Enter your PAN No" class="form-control" required="required"/>  
    </div>
    <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault03">Maritual status</label>
      <form:input path="maritualstatus" class="form-control"  placeholder="Enter your Maritual status" required="required"/>  
      
    </div>
    <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault03">Amount</label>
      <form:input  path="total" id="total" class="form-control totalamount " readonly="true"/>  
      
    </div>
     <div class="col-md-3 mb-3 ml-1">
      <label for="validationDefault03">From Date</label>
               <div class="form-group">
                <div class='input-group date' id='DOB'>
                    <form:input path="dateofbirth" class="form-control" type="date" required="required"/>   
                </div>
                <span id="dateError" style="color: red;"></span>
            </div>
             </div>
             <%--  <div class="col-md-3 mb-3 ml-5">
      <label for="validationDefault03">To Date</label>
               <div class="form-group">
                <div class='input-group date' id='DOB2'>
                    <form:input path="" class="form-control" type="date" required="required"/>   
                </div>
                <span id="dateError2" style="color: red;"></span>
            </div>
             </div> --%>
         </div>
      </fieldset>   
         <br>
         
         <fieldset  class="scheduler-border">
   <legend  class="scheduler-border"><h5>Address Details</h5></legend>
         <div class="container">   
            <table class="table table-bordered" id="addressTbl" style="border-radius: 30px;">
            <thead id="thead">
                <tr id="Thead">  
                	<th scope="col" class="text-end"></th> 
                    <th scope="col" class="text-end">Phone No</th>
                    <th scope="col" class="text-end">Email</th>
                    <th scope="col" class="text-end">Address</th>
                    <th scope="col" class="text-end">Pin</th>              
                    <th><button id="btn2" type="button" onclick="$(this).add('btn2','addressTbl')">Add</button></th>
                </tr>
            </thead>
           <tbody id="Tbody">
           <c:forEach end="${contactdetails.size()==0?0:contactdetails.size()-1 }" begin="0" varStatus="status">
		    <tr class="">
            <td><form:input  type="hidden" id="id" class="idClass"  path="contactdetails[${status.index}].id"/></td>
           
            <td><form:input  type="text" id="phone" class="phoneClass" path="contactdetails[${status.index}].phone"  placeholder="Enter phone number" required="required"/>
           <label class="phonemessage"></label></td> 
            
            <td><form:input  type="text"  id="email"  class="emailClass" path="contactdetails[${status.index}].email" placeholder="Enter your email" required="required"/>
             <label class="emailmessage"></label></td>
            
            <td><form:input  type="text" id="address" class="addressClass" path="contactdetails[${status.index}].address" placeholder="Enter your address" required="required"/></td>
           
           <td><form:input  type="text" id="pin" class="pinClass" path="contactdetails[${status.index}].pin" placeholder="Enter your PIN code" required="required"/></td>
            
            <td><button class="tr" id="btn1" type="button" onclick="remove(this)">Remove</button><br/>
    		</tr>
    		</c:forEach>
			</tbody>
        </table>          </div>
          </fieldset>
          <br><br>
          
          <fieldset  class="scheduler-border" >
        <legend class="scheduler-border" ><h5>Bank Details</h5></legend>
            <div class="container">
             <table class="table table-bordered" id="bankTblId">
            <thead id="thead">
                <tr id="Thead1">  
                	 <th scope="col" class="text-end"></th>  
                    <th scope="col" class="text-end">Bank Name</th>
                    <th scope="col" class="text-end">Act No</th>
                    <th scope="col" class="text-end">Branch</th>
                    <th scope="col" class="text-end">Amount</th>             
                    <th><button id="btn6" type="button" onclick="$(this).add('btn6','bankTblId')">Add</button></th>
                </tr>
            </thead>
           <tbody id="Tbody1">
           <c:forEach end="${bankdetails.size()==0?0:bankdetails.size()-1 }" begin="0" varStatus="status">
		    <tr>
		    <td><form:input  type="hidden" id="id" class="bankidClass"  path="bankdetails[${status.index}].id"/></td>
            
              <td><form:select path="bankdetails[${status.index}].bankname" id="bankname" class="banknameClass" type="text"  >  
              <form:option  items="${bankdetails}" value="">Select Bank Name</form:option> 
             
      </form:select>  
            </td>
              
            <td><form:input  type="text" id="accNo" path="bankdetails[${status.index}].accNo" class="accNoClass" placeholder="Enter your acc num"/></td>
            <td><form:input  type="text" id="branch" path="bankdetails[${status.index}].branch" class="branchClass" placeholder="Enter your branch"/></td>
            
              
            <td><form:input  type="text" id="amount" path="bankdetails[${status.index}].amount" class="amountClass" placeholder="Enter your amount"/></td>
            <td><button class="tr" id="btn3" type="button" onclick="remove1(this)">Remove</button><br/>
    		</tr>
    		</c:forEach>
			</tbody>
			<tfoot>
            <tr>
              <td></td><td></td><td></td>
              <td class="text-center" style="color: black;">Total Anount:</td>
              <td class="totalamount am text-center total"></td>
             
            </tr>
          </tfoot>
        </table>          
              </div>
             </fieldset>
              <br><br>
     <div class="text-right mb-2 mr-5">
     <input  type="submit" id="submit-btn" class="btn btn-primary " value="Submit" /> 
     <a href="/customer/"><button type="button"  class="btn btn-danger" value="Cancel">Cancel</button></a>
     </div>
  </form:form>

    
      <br><br>
</body>
</html>