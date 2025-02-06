document.addEventListener("DOMContentLoaded", function () 
{
    document.querySelectorAll("[data-target='#deleteModal']").forEach(function (button) 
    {
        button.addEventListener("click", function () 
        {
			console.log("deleteModalListener START ====")
			// Get the `data-delete-url` value
            const deleteUrl = this.getAttribute("data-delete-url");
            console.log("deleteUrl value: " + deleteUrl);

            // Find the Yes button inside the delete modal
            const yesButton = document.querySelector("#deleteModal button.btn-danger");

            // Update the Yes button click action
            //When the user clicks on the button -> the deletUrl which contains the link will be executed.
            //The link will contain teh id value -> the link will hit the controller layer containing the id value.
            // and the controller will delete the id value.
            yesButton.onclick = function ()
            {
                if (deleteUrl) 
                {
                    fetch(deleteUrl, 
                    {
                        method: 'GET'
                    })
                    .then(response =>
                    {
                        if (response.redirected)
                        {
                            window.location.href = response.url; // Redirect after success
                        } 
                        else 
                        {
                            alert("Failed to delete the record.");
                        }
                    })
                    .catch(error => console.error("Error:", error));
               }
               else 
               {
                    console.error("Delete URL is not set.");
               }
            };
            console.log("deleteModalListener END ====")
        });
    });
});

/*

Objective of this code:
1. This code is used to handle deleteModal.html as generic whenever the user want to delete the record.
2. The delete modal fragment is only defined once in deleteModal.html.
3. The delete button triggers the modal and dynamically sets the URL using JavaScript.
4. You can reuse this modal across multiple pages by changing the th:attr="data-delete-url" value.

 */