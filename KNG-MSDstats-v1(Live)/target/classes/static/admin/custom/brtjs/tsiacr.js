document.addEventListener('DOMContentLoaded', function ()
{
			
		 var form = document.getElementById('tsiacr'); // Get the form by its ID

    	form.addEventListener('submit', function (e) 
    	{
   			e.preventDefault(); 
        window.open(form.getAttribute('action'), '_blank');

    	});
});