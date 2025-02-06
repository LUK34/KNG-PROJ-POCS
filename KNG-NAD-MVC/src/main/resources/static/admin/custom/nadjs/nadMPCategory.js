//----------------------------- KNG NAD Consultations Lens button click START----------------------------------

 $('document').ready(function()
{
	 //$('table #editButton').on('click',function(event)
	   $(document).on('click', 'table .editButton', function(event)
		{
			        event.preventDefault();
			        var href= $(this).attr('href');
			         
		        // Perform AJAX GET request
		        $.get(href, function(nadMPC, status) 
		        {
		            if (status === 'success' && nadMPC)
		            {
												
		                // Populate the modal fields with fetched data
		                $('#idEdit').val(nadMPC.id);
		                $('#ctgEdit').val(nadMPC.ctg);
		                $('#ctgaEdit').val(nadMPC.ctga);
		                $('#statEdit').val(nadMPC.stat);
		
						//	editModal-> to delete the record on the basis of id value assigned dynamically via AJAX.
                		$('#editNadMPCategoryModal').find('.btn-danger').attr('data-delete-url', '/nadMPCategory/deleteById?id=' + nadMPC.id);
									
		
		                // Show the modal only after successful data fetch
		                $('#editNadMPCategoryModal').modal('show');
		                
		                console.log("After setting up the DELETE URL dynamically. The DELETE URL is as follows: " + $('#editNadMPCategoryModal').find('.btn-danger').attr('data-delete-url'));
		            } 
		            else 
		            {
		                alert("Failed to fetch Meal Plan Category data. Please try again.");
		            }
		        }).fail(function() 
		        {
		            alert("Error occurred while fetching clinic data.");
		        });
		}); 
});
//----------------------------- KNG NAD Consultations Lens button click END----------------------------------
 

