import { dateValidator } from './coreBirtUtils.js';

//----------------------------- SPECIALITY VISIT Report FORM SUBMIT LOGIC START----------------------------------
			
document.addEventListener('DOMContentLoaded', function (){
			
		
		 var form = document.getElementById('mrt'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

        e.preventDefault(); // Prevent form submission if validation fails
        var sdate = document.getElementById('mrt_sdate').value;
        var edate = document.getElementById('mrt_edate').value;
        var error = document.getElementById('mrt-error-message'); // Ensure you have this element in your HTML
        
 		
 			
 		// ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE START -----------------------------------
        if (!dateValidator(sdate, edate, error))
        {
            return; // Exit the function
        }
 		// ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE END -----------------------------------
       
		console.log(sdate,edate);
        // ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
       
        var queryString='?brtSdate=' + encodeURIComponent(sdate) + '&brtEdate=' + encodeURIComponent(edate);
		
		// ----------------------------------- QUERY STRING CONSTRUCTOR END -----------------------------------
        
        // Only change the window location if form validation passes
         window.open(form.getAttribute('action') + queryString, '_blank');
    });

});
		
//----------------------------- SPECIALITY VISIT Report FORM SUBMIT LOGIC END----------------------------------
