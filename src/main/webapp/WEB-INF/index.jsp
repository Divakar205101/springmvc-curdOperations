<html>
<head>
<meta charset="ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"%>
<%@include file="./base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
 <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>     
<script>
  



       
     

  
$(document).ready(function() {
	

	/*   $('.check-box').change(function() {
	    if ($(this).is(':checked')) {
	      $('.check-box').not(this).prop('checked', false);
	      var index = $(this).closest('tr').index();
	      var value = $('tbody tr').eq(index).find('td:nth-child(' + (index + 2) + ')').text();
	      
	      var label = "";
	      switch(index) {
	        case 0:
	          label = "Name";
	          break;
	        case 1:
	          label = "Date of Birth";
	          break;
	        case 2:
	          label = "Adhar Number";
	          break;
	        case 3:
	          label = "PAN Number";
	          break;
	        case 4:
	          label = "Gender";
	          break;
	        case 5:
	          label = "Marital Status";
	          break;
	        default:
	          label = "";
	      }
	      
	      alert('Selected ' + label + ': ' + value);
	    }
	  }); */
	  
	  $('.check-box').change(function() {
		  
		let index = $(this).data('index');
		let text = $(this).closest('tr').find('td').eq(index).text();
		alert(text);
	  })
	  
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
	
  
 function showBankDetails(id, icon) {
	 
	 var row = $(icon).closest('tr');
	
	  var bankDetailsRow = row.next('.hidden-row');
      
	  $('.hidden-row').not(bankDetailsRow).hide();
	  
	  if (bankDetailsRow.css('display') === 'none') {
	   
	    $.ajax({
	      type: "GET",
	      url: window.location + 'getBankDetails/' + id,
	      dataType: 'json',
	      success: function(result) {
	        var bankDetailsTableBody = bankDetailsRow.find('#bankDetailsTableBody');
	        bankDetailsTableBody.empty();

	        $.each(result, function(index, bankDetail) {
	          var row = '<tr>' +
	            '<td>' + bankDetail.bankname + '</td>' +
	            '<td>' + bankDetail.accNo + '</td>' +
	            '<td>' + bankDetail.branch + '</td>' +
	            '<td>' + bankDetail.amount + '</td>' +
	            '</tr>';
	          bankDetailsTableBody.append(row);
	        });
	        /* bankDetailsRow.css('display', 'table-row');; */
	        bankDetailsRow.show();
	      },
	      error: function() {
	        console.log('Error occurred while retrieving bank details.');
	      }
	    });
	  } else {
	   /*  bankDetailsRow.css('display', 'none'); */
	   bankDetailsRow.hide();
	  }
	}
 
 function downloadBankDetails(id){
     
    
     var url = window.location + 'download/bankdetails/' + id;

     
     $.ajax({
       url: url,
       method: 'GET',
       xhrFields: {
         responseType: 'arraybuffer' // Set the response type to arraybuffer
       },
       success: function(data) {
         // Create a Blob from the received data
         var blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

         // Create a temporary anchor element and click it to initiate the download
         var tempLink = document.createElement('a');
         tempLink.href = URL.createObjectURL(blob);
         tempLink.setAttribute('download', 'bank_details.xlsx');
         tempLink.click();
       },
       error: function() {
         
         console.log('Error occurred during download');
       }
    
   });
 }

</script>

<style>

</style>
 
</head>
<body>  
       <h2 style="text-align: center" class="mt-1">Customer Search</h2>
       
      
      
      <a type="button" class="btn btn-primary mr-4 ml-3 " href="${pageContext.request.contextPath }/add" style="float:right;">Create</a>
      
      <a type="button" class="btn btn-primary mr-4 ml-3 " href="${pageContext.request.contextPath }/addbank" style="float:right;">Create Bank</a>
     
 <div class="container mt-4 " >
  <form:form action="search" method="GET" modelAttribute="bean" class="mr-2" >
    <div class="row" style="background-color:C0C0C0; border-radius: 25px;">
      <div class="col ">
        <label for="name">Name</label>
        <form:input path="name" class="form-control" placeholder="Enter Name"/> &nbsp; 
      </div>
      <div class="col">
        <label for="adhar">Adhar</label>
         <form:input path="adharNo" class="form-control" placeholder="Enter Adhar Number"/> &nbsp; 
      </div>
      <div class="col">
        <label for="pan">Pan</label>
        <form:input path="panNo" class="form-control" placeholder="Enter Pan Number"/> &nbsp;
      </div>
       <div class="col mt-3">
         <input type="submit" class="btn btn-primary mt-3" value="Search">
         <a href="/customer/"><button type="button"  class="btn btn-danger mt-3" value="Reset">Reset</button></a>	
          
     </div>
    </div>
    </form:form>
</div>

   

   	
      <div class="container mt-3 ml-1 mr-1">
       
       <div class="row">
       
       <div class="col-md-11">
       
       <h3 class="text-center mb-3">Customers Data</h3>
       
       <table class = "table table-hover table-bordered table-striped text-center mr-1" style="width:100%">
  <thead>
    <tr>
      <th scope="col">S.No</th>
      <th scope="col">Name</th>
      <th scope="col">DOB</th>
      <th scope="col">Adhar</th>
      <th scope="col">PAN</th>
       <th scope="col">Gender</th>
      <th scope="col">M.S</th>
      <th scope="col">Amount</th>
       <th scope="col">Action</th>
       <th scope="col">Check Box</th>
       <th scope="col">Bank Details</th>
       <th scope="col">DownLoad</th>
       <th scope="col">Transfer Money</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items ="${list}" var="employee"  begin="0" varStatus="i">
   <tr>
     <td>${employee.id }</td>
    <td>${employee.name }</td>
    <td>${employee.dateofbirth }</td>
    <td>${employee.adharNo }</td>
    <td>${employee.panNo }</td>
     <td>${employee.gender }</td>
    <td>${employee.maritualstatus }</td>
    <td>${employee.total }</td>
    <td>
     <spring:url value="update/${employee.id }" var="updateURL" />
     <a  href="${updateURL }"><i class="fas fa-edit"></i></a>
     <spring:url value="delete/${employee.id }" var="deleteURL" />
     <a type="button"  href="${deleteURL }" ><i class='fa fa-trash red-color'></i></a>
    </td>
     <td>
       <input type="checkbox" data-index="${i.index+1 }" type="radio" class="check-box">
     </td>
     <td>     
     <a><i class="fa fa-bank" onclick="showBankDetails(${employee.id},this)"></i></a>
     </td>
     <td>     
     <a><i class="fa-solid fa-download" onclick="downloadBankDetails(${employee.id})"></i></a>
     </td>
      <td>
      <spring:url value="moneytransfer/${employee.id }" var="updateURL" />
      <a href="${updateURL }"><i class="fa-solid fa-money-bill-transfer"></i></a>         
     </td>
   </tr>
   <tr class="hidden-row" style="display: none; background-color: #87CEEB">
    <td class="bank-details" colspan="100%">
    <fieldset  class="scheduler-border" style="" >
        <legend class="scheduler-border" ><h5>Bank Details</h5></legend>
            <div class="container">
             <table class="table table-bordered">
            <thead id="thead">
                <tr id="Thead1">  
                	 <th scope="col" class="text-end">Bank Name</th>  
                    <th scope="col" class="text-end">Act No</th>
                    <th scope="col" class="text-end">Branch</th>
                    <th scope="col" class="text-end">Amount</th>                                                  
                </tr>
            </thead>
           <tbody id="bankDetailsTableBody">
		</tbody>
       </table>          
     </div>
</fieldset>
  </td>
   </tr>
  </c:forEach>
  </tbody>
</table>
</div>
</div>
</div>
 
</body>
</html>
