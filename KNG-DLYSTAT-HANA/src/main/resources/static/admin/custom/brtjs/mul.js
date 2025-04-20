import { kwtNkwtFilter } from './coreBirtUtils.js';
import { userTypeFilter } from './coreBirtUtils.js';
import { kngMohFilter } from './coreBirtUtils.js';
import { statusFilter } from './coreBirtUtils.js';


document.addEventListener('DOMContentLoaded', function (){
			
		// ----------------------------------- UTILITIES START -----------------------------------
		 	
		 	kwtNkwtFilter('mulDemog');
		 	kngMohFilter('mulKngMoh');
		 	userTypeFilter('mulUserType');
		 	statusFilter('mulStatus');
		 	
		// ----------------------------------- UTILITIES  END -----------------------------------

		 var form = document.getElementById('mul'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

		e.preventDefault(); // Prevent form submission if validation fails
        var mulDemogid = document.getElementById('mulDemog').value;
        var mulKngMohid = document.getElementById('mulKngMoh').value;
        var mulUserTypeid = document.getElementById('mulUserType').value;
        var mulStatusid = document.getElementById('mulStatus').value;
        
        console.log(mulDemogid,mulKngMohid,mulUserTypeid,mulStatusid)
      
		var error = document.getElementById('mul-error-message'); // Ensure you have this element in your HTML


		 // ----------------------------------- BIRT FILTER VALIDATOR CALLED HERE START -----------------------------------
       
	       
 		// ----------------------------------- BIRT FILTER VALIDATOR CALLED HERE END -----------------------------------
       
	
        // ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
       
        var queryString='?brtKwtNkwtid=' + encodeURIComponent(mulDemogid) +'&brtKngMoh=' +encodeURIComponent(mulKngMohid) + '&brtUserType=' +encodeURIComponent(mulUserTypeid) + '&brtStatus=' +encodeURIComponent(mulStatusid);
		
		// ----------------------------------- QUERY STRING CONSTRUCTOR END -----------------------------------
        
        // Only change the window location if form validation passes
         window.open(form.getAttribute('action') + queryString, '_blank');
    });

});