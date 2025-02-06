//----------------------------- KNG NAD Consultations Lens button click START----------------------------------

 $('document').ready(function()
{
	 //$('table #editButton').on('click',function(event)
	   $(document).on('click', 'table .editButton', function(event)
		{
			        event.preventDefault();
			        var href= $(this).attr('href');
			         
		        // Perform AJAX GET request
		        $.get(href, function(nadclinic, status) 
		        {
		            if (status === 'success' && nadclinic)
		            {
												
		                // Populate the modal fields with fetched data
		                $('#idEdit').val(nadclinic.id);
		                $('#clinicidEdit').val(nadclinic.clinicid);
		                $('#cnameEdit').val(nadclinic.cname);
		                $('#cnameaEdit').val(nadclinic.cnamea);
		                $('#statEdit').val(nadclinic.stat);
		
						//	editModal-> to delete the record on the basis of id value assigned dynamically via AJAX.
                		$('#editNadKngClinicModal').find('.btn-danger').attr('data-delete-url', '/nadClinics/deleteById?id=' + nadclinic.id);
									
		
		                // Show the modal only after successful data fetch
		                $('#editNadKngClinicModal').modal('show');
		                
		                console.log("After setting up the DELETE URL dynamically. The DELETE URL is as follows: " + $('#editNadKngClinicModal').find('.btn-danger').attr('data-delete-url'));
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
 

