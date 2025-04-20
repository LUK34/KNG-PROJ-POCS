import { dateValidator ,clinicValidator } from './coreBirtUtils.js';
import { phaTransacTypeFilter } from './coreBirtUtils.js';
import {medicineDynaSingleFilter}  from './coreBirtUtils.js';

// ------------------------------------------- KNG Lab Patient Count Accessed Report Tests Taken START --------------------------------------------------------------			
document.addEventListener('DOMContentLoaded', function ()
{
	
	 // ----------------------------------- SEARCH Medicine using SELECT2 -----------------------------------
	    $('#mid_ptit').select2({
	        placeholder: "Select a medicine",
	        allowClear: true,
	    });
	    
	 // ------------------------------ SEARCH Medicine using SELECT2 -----------------------------------	
	
	// ----------------------------------- Medicine select by OID FILTER START -----------------------------------
	     	medicineDynaSingleFilter('oid_ptit','mid_ptit');	 	
	// ----------------------------------- Medicine select by OID FILTER END -----------------------------------
	
	
	// ----------------------------------- Pharmacy Transaction Type FILTER START -----------------------------------
	 		phaTransacTypeFilter('phaTransacType_ptit');	
	// ----------------------------------- Pharmacy Transaction Type FILTER START -----------------------------------
	
	 
	 var form = document.getElementById('ptit');	
	 
	 form.addEventListener('submit', function (e)
     {

        e.preventDefault(); // Prevent form submission if validation fails
        var sdate = document.getElementById('sdate_ptit').value;
        var edate = document.getElementById('edate_ptit').value;
        var oid = document.getElementById('oid_ptit').value;
        var mid= document.getElementById('mid_ptit').value;
        var typeid= document.getElementById('phaTransacType_ptit').value;
        
        var error = document.getElementById('ptit-error-message'); // Ensure you have this element in your HTML
        
        // ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE START -----------------------------------
        if (!dateValidator(sdate, edate, error))
        {
			 e.preventDefault(); // Prevent form submission if validation fails
            return; // Exit the function
        }
          if (!clinicValidator(oid, error))
        {
            e.preventDefault(); // Prevent form submission if validation fails
            return; // Exit the function
        }
 		// ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE END -----------------------------------
 		
 		// ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
       
        var queryString='?brtSdate=' + encodeURIComponent(sdate) + '&brtEdate=' + encodeURIComponent(edate)+ '&brtOid=' +encodeURIComponent(oid)+ '&brtPharmItemId=' +encodeURIComponent(mid)+'&brtTranscTypeId='+encodeURIComponent(typeid);
		
		// ----------------------------------- QUERY STRING CONSTRUCTOR END -----------------------------------
        
        // Open a new tab with the form's action URL and the query string
       	window.open(form.getAttribute('action') + queryString, '_blank');
 		
       
	});
	 
});