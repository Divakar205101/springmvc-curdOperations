<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <%@include file="./base.jsp" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  
    <script type="text/javascript">
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
                var newRow = $('<tr id="Trow"><td><input type="hidden" class="bankidClass" name="createBanks[' + index + '].id" required="required"></td><td><input id="nameofbank' + index + '" type="text" name="createBanks[' + index + '].nameofbank" placeholder="Enter nameofbank" class="accNoClass" required="required"></td><td><input id="nameofbank' + index + '" type="text" name="createBanks[' + index + '].nameofbranch" placeholder="Enter branch name" class="branchClass" required="required"></td><td><button id="btn3" type="button" onclick="remove1(this)">Remove</button><br/></td></tr>');
                $('#Tbody1').append(newRow);
                index++;
            }
        }

        function remove1(v) {
            $(v).parent().parent().remove();
            var bankindex = 0;
         
            $("#Tbody1 tr").each(function() {
                $(this).find(".bankidClass").attr("name", "bank[" + bankindex + "].id");
                $(this).find(".bankClass").attr("name", "bank[" + bankindex + "].nameofbank");
                $(this).find(".branchClass").attr("name", "bank[" + bankindex + "].nameofbranch");
                bankindex++;
            });
        }
    </script>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/savebank" class="ml-5 mr-5 mt-5" method="POST" modelAttribute="createBankMo">
        <div class="container">
            <table class="table table-bordered">
                <thead id="thead">
                    <tr id="Thead1">  
                        <th scope="col" class="text-end"></th>  
                        <th scope="col" class="text-end">Bank Name</th>
                        <th scope="col" class="text-end">Branch</th>             
                        <th><button id="btn6" type="button" onclick="BtnAdd()">Add</button></th>
                    </tr>
                </thead>
                <tbody id="Tbody1">
                    <c:forEach   end="${createBanks.size()==0?0:createBanks.size()-1 }" begin="0" varStatus="status">
                        <tr id="Trow">
                            <td><form:input type="hidden" id="id" class="bankidClass" path="createBanks[${ status.index}].id"/></td>
                            <td><form:input type="text" id="nameofbank" path="createBanks[${ status.index}].nameofbank" class="bankClass" placeholder="Enter bank name" /></td>
                            <td><form:input type="text" id="nameofbranch" path="createBanks[${ status.index}].nameofbranch" class="branchClass" placeholder="Enter branch name"/></td>
                            <td><button class="tr" id="btn3" type="button" onclick="remove1(this)">Remove</button><br/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>          
        </div>
         <input  type="submit" id="submit-btn" class="btn btn-primary " value="Submit" /> 
    </form:form>
</body>
</html>
