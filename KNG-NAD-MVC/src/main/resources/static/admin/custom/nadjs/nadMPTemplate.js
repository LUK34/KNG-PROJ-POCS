//----------------------------- KNG NAD Consultations Lens button click START----------------------------------

 $('document').ready(function()
{
	 //$('table #editButton').on('click',function(event)
	   $(document).on('click', 'table .editButton', function(event)
		{
			        event.preventDefault();
			        var href= $(this).attr('href');
			         
		        // Perform AJAX GET request
		        $.get(href, function(nadMPT, status) 
		        {
		            if (status === 'success' && nadMPT)
		            {
												
		                // Populate the modal fields with fetched data
		                $('#idEdit').val(nadMPT.id);
		                $('#tplNameEdit').val(nadMPT.tplName);
		                $('#tplCodeEdit').val(nadMPT.tplCode);
		                $('#tpleCrsFreEdit').val(nadMPT.tpleCrsFre);
		                $('#tplAvlEdit').val(nadMPT.tplAvl);
		                $('#tplPubEdit').val(nadMPT.tplPub);
		                $('#tplObjEdit').val(nadMPT.tplObj);
		                $('#tplPlanEdit').val(nadMPT.tplPlan);
		                $('#tplCatEdit').val(nadMPT.tplCat);
		                $('#tplTEnEdit').val(nadMPT.tplTEn);
		                $('#tplTFaEdit').val(nadMPT.tplTFa);
		                $('#tplTChEdit').val(nadMPT.tplTCh);
		                $('#tplTPrEdit').val(nadMPT.tplTPr);
		                $('#tplTCarbEdit').val(nadMPT.tplTCarb);
		                $('#tplTFibEdit').val(nadMPT.tplTFib);
		                $('#tplTSodEdit').val(nadMPT.tplTSod);
		                $('#tplTSugEdit').val(nadMPT.tplTSug);
		
						//	editModal-> to delete the record on the basis of id value assigned dynamically via AJAX.
                		$('#editNadMPTemplateModal').find('.btn-danger').attr('data-delete-url', '/nadMPTemplate/deleteById?id=' + nadMPT.id);
									
		
		                // Show the modal only after successful data fetch
		                $('#editNadMPTemplateModal').modal('show');
		                
		                console.log("After setting up the DELETE URL dynamically. The DELETE URL is as follows: " + $('#editNadMPTemplateModal').find('.btn-danger').attr('data-delete-url'));
		            } 
		            else 
		            {
		                alert("Failed to fetch Meal Plan Template data. Please try again.");
		            }
		        }).fail(function() 
		        {
		            alert("Error occurred while fetching clinic data.");
		        });
		}); 
});
//----------------------------- KNG NAD Consultations Lens button click END----------------------------------
 

