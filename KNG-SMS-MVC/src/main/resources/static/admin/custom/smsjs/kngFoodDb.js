//----------------------------- KNG NAD Consultations Lens button click START----------------------------------

 $('document').ready(function()
{
	 //$('table #editButton').on('click',function(event)
	   $(document).on('click', 'table .editButton', function(event)
		{
			        event.preventDefault();
			        var href= $(this).attr('href');
			         
		        // Perform AJAX GET request
		        $.get(href, function(kngfdbs, status) 
		        {
		            if (status === 'success' && kngfdbs)
		            {
												
		                // Populate the modal fields with fetched data
		                $('#idEdit').val(kngfdbs.id);
		                $('#fcodeEdit').val(kngfdbs.fcode);
		                $('#foodNameEdit').val(kngfdbs.foodName);
		                $('#foodTypeEdit').val(kngfdbs.foodType);
		                $('#portionsEdit').val(kngfdbs.portions);
		                $('#measurementEdit').val(kngfdbs.measurement);
		                $('#caloriesEdit').val(kngfdbs.calories);
		                $('#carbohydratesEdit').val(kngfdbs.carbohydrates);
		              	$('#fatEdit').val(kngfdbs.fat);
		                $('#proteinEdit').val(kngfdbs.protein);
		                $('#fiberEdit').val(kngfdbs.fiber);		         
		                $('#sodiumEdit').val(kngfdbs.sodium);
		                $('#statEdit').val(kngfdbs.stat);
		
						//	editModal-> to delete the record on the basis of id value assigned dynamically via AJAX.
                		$('#editNadKngFoodDbModal').find('.btn-danger').attr('data-delete-url', '/kngFoodDb/deleteById?id=' + kngfdbs.id);
									
		
		                // Show the modal only after successful data fetch
		                $('#editNadKngFoodDbModal').modal('show');
		                
		                console.log("After setting up the DELETE URL dynamically. The DELETE URL is as follows: " + $('#editNadKngFoodDbModal').find('.btn-danger').attr('data-delete-url'));
		            } 
		            else 
		            {
		                alert("Failed to fetch clinic data. Please try again.");
		            }
		        }).fail(function() 
		        {
		            alert("Error occurred while fetching clinic data.");
		        });
		}); 
});
//----------------------------- KNG NAD Consultations Lens button click END----------------------------------
 

