import { dateValidator } from './coreBirtUtils.js';


//----------------------------- 4.5 Pharmacy Visit-Officer,Soldier,Civilian,Family START ----------------------------------


document.addEventListener('DOMContentLoaded', function (){
			
		 var form = document.getElementById('phavoscf'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

		e.preventDefault(); // Prevent form submission if validation fails
        var sdate = document.getElementById('sdate15').value;
        var edate = document.getElementById('edate15').value;
        var error = document.getElementById('phavoscf-error-message'); // Ensure you have this element in your HTML
        
 		
 		
 		// ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE START -----------------------------------
        if (!dateValidator(sdate, edate, error))
        {
           return; // Exit the function
        }
 		// ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE END -----------------------------------
       
 		

        // ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
        
        var queryString='?brtSdate=' + encodeURIComponent(sdate) + '&brtEdate=' + encodeURIComponent(edate);
		
		// ----------------------------------- QUERY STRING CONSTRUCTOR END  -----------------------------------
   
        

        // Only change the window location if form validation passes
       /* if (!e.defaultPrevented) {
            window.location.href = form.getAttribute('action') + queryString;
        }*/
        
         window.open(form.getAttribute('action') + queryString, '_blank');
        
        
    });
});

//----------------------------- 4.5 Pharmacy Visit-Officer,Soldier,Civilian,Family END ----------------------------------

