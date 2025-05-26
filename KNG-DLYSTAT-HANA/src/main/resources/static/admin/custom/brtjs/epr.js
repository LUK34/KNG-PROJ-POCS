import { dateValidator ,clinicValidator} from './coreBirtUtils.js';

// ------------------------------------------- 4.2 Medicine by KNG Pharmacy START --------------------------------------------------------------			
document.addEventListener('DOMContentLoaded', function (){
	
		
		 var form = document.getElementById('epr'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

        e.preventDefault(); // Prevent form submission if validation fails
        var sdate = document.getElementById('epr_sdate').value;
        var edate = document.getElementById('epr_edate').value;
        var oid = document.getElementById('epr_oid').value;
        var error = document.getElementById('epr-error-message'); // Ensure you have this element in your HTML
       console.log(sdate,edate,oid);
       
 		// ----------------------------------- BIRT  VALIDATORS CALLED HERE START -----------------------------------
        if (!dateValidator(sdate, edate, error))
        {
            return; // Exit the function
        }
     	
     	if (!clinicValidator(oid, error))
        {
            e.preventDefault(); // Prevent form submission if validation fails
            return; // Exit the function
        }
        
 		// ----------------------------------- BIRT VALIDATORS CALLED HERE END -----------------------------------
       

        // ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
        
			 var queryString='?brtSdate=' + encodeURIComponent(sdate) +'&brtEdate=' + encodeURIComponent(edate)+ '&brtOid=' +encodeURIComponent(oid);
	
		// ----------------------------------- QUERY STRING CONSTRUCTOR END -----------------------------------
        
        
        window.open(form.getAttribute('action') + queryString, '_blank');
        
        
    });
		});
			
// ------------------------------------------- 4.2 Medicine by KNG Pharmacy END --------------------------------------------------------------			
	