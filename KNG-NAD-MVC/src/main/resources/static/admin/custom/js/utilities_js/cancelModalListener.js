document.addEventListener("DOMContentLoaded", function ()
 {
    document.querySelectorAll(".cancelButton").forEach(function (button) 
    {
        button.addEventListener("click", function () 
        {
            // Get dynamic cancel URL from button attribute
            const cancelUrl = this.getAttribute("data-cancel-url");

            if (cancelUrl) 
            {
                console.log(`Redirecting to: ${cancelUrl}`);
                window.location.href = cancelUrl;  // Perform the redirect
            } else 
            {
                console.error("Cancel URL not set.");
            }
        });
    });
});