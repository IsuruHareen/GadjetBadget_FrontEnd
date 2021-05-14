
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidItemIDSave").val("");
	$("#formEmployee")[0].reset();
});
 
 $(document).on("click", "#btnSave", function(event)
{ 
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

var status = validateItemForm(); 
if (status != true) 
 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
 } 
 

var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( { 
		 url : "EmployeeAPI", 
		 type : type, 
		 data : $("#formEmployee").serialize(), 
		 dataType : "text", 
		 complete : function(response, status)  { 
		 	onItemSaveComplete(response.responseText, status); 
		 } 
 }); 
 
 
	 function onItemSaveComplete(response, status)
	 { 
		if (status == "success")  { 
			 var resultSet = JSON.parse(response); 
			 if (resultSet.status.trim() == "success")  { 
				 $("#alertSuccess").text("Successfully saved."); 
				 $("#alertSuccess").show(); 
				 $("#divItemsGrid").html(resultSet.data); 
			 } else if (resultSet.status.trim() == "error")  { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
		 } else if (status == "error") { 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show(); 
		 } else { 
			 $("#alertError").text("Unknown error while saving.."); 
			 $("#alertError").show(); 
		 }
		 	 $("#hidItemIDSave").val(""); 
	 		 $("#formEmployee")[0].reset(); 
	 }
 
 
 
	 $(document).on("click", ".btnUpdate", function(event) { 
		 $("#hidItemIDSave").val($(this).data("empID")); 
		 $("#empCode").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#empName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#empEmail").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#empAge").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#empAddress").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#empRole").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#date").val($(this).closest("tr").find('td:eq(6)').text()); 
	 });
  
	 $(document).on("click", ".btnRemove", function(event) { 
		 $.ajax({ 
			 url : "EmployeeAPI", 
			 type : "DELETE", 
			 data : "empID=" + $(this).data("empID"),
			 dataType : "text", 
			 complete : function(response, status){ 
			 	onItemDeleteComplete(response.responseText, status); 
			 } 
		 }); 
	 });
 
 
 
 
	 function onItemDeleteComplete(response, status)
	 { 
		 if (status == "success") 
		 { 
		 	var resultSet = JSON.parse(response); 
			 if (resultSet.status.trim() == "success") 
			 { 
				 $("#alertSuccess").text("Successfully deleted."); 
				 $("#alertSuccess").show(); 
				 $("#divItemsGrid").html(resultSet.data); 
			 } 
			 else if (resultSet.status.trim() == "error") 
			 { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
			 } 
			 else if (status == "error") 
			 { 
				 $("#alertError").text("Error while deleting."); 
				 $("#alertError").show(); 
			 } 
			 else
			 { 
				 $("#alertError").text("Unknown error while deleting.."); 
				 $("#alertError").show(); 
			 } 
	 }
 
 
	function validateItemForm() 
	{ 
		if ($("#empCode").val().trim() == "") 
		 { 
		 	return "Insert the Employee's Code."; 
		 } 
	
		if ($("#empName").val().trim() == "") 
		 { 
		 	return "Insert the Employee Name."; 
		 } 
	
		if ($("#empEmail").val().trim() == "") 
		 { 
		 	return "Insert the Employee's E-mail Address."; 
		 } 
	
		if ($("#empAge").val().trim() == "") 
		 { 
		 	return "Insert the Employee's Age."; 
		 }
		 if ($("#empAddress").val().trim() == "") 
		 { 
		 	return "Insert Address"; 
		 } 
		 if ($("#empRole").val().trim() == "") 
		 { 
		 	return "Insert the Role"; 
		 }
		 if ($("#date").val().trim() == "") 
		 { 
		 	return "Insert the Date"; 
		 }
		return true; 
	}

});