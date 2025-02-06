//----------------------------- KNG SMS Clinics Lens button click START----------------------------------

 $('document').ready(function()
{
	 //$('table #editButton').on('click',function(event)
	   $(document).on('click', 'table .editButton', function(event)
		{
			        event.preventDefault();
			        var href= $(this).attr('href');
			         
		        // Perform AJAX GET request
		        $.get(href, function(smsclinic, status) 
		        {
		            if (status === 'success' && smsclinic)
		            {
												
		                // Populate the modal fields with fetched data
		                $('#idEdit').val(smsclinic.id);
		                $('#clinicidEdit').val(smsclinic.clinicid);
		                $('#cnameEdit').val(smsclinic.cname);
		                $('#cnameaEdit').val(smsclinic.cnamea);
		                $('#statEdit').val(smsclinic.stat);
		
						//	editModal-> to delete the record on the basis of id value assigned dynamically via AJAX.
                		$('#editClinicModal').find('.btn-danger').attr('data-delete-url', '/clinicSetup/deleteById?id=' + smsclinic.id);
									
		
		                // Show the modal only after successful data fetch
		                $('#editClinicModal').modal('show');
		                
		                console.log("After setting up the DELETE URL dynamically. The DELETE URL is as follows: " + $('#editClinicModal').find('.btn-danger').attr('data-delete-url'));
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
//----------------------------- KNG SMS Clinics Lens button click END----------------------------------
 

