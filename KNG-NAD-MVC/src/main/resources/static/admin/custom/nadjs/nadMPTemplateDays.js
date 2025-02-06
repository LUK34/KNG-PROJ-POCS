$(document).on('click', 'table .editButton', function(event) {
    event.preventDefault();
    var href = $(this).attr('href');
    
    // Perform AJAX GET request
    $.get(href, function(nadMPTD, status) {
        if (status === 'success' && nadMPTD) {
            // Populate the modal fields with fetched data
            $('#idEdit').val(nadMPTD.id);
            $('#tplIdEdit').val(nadMPTD.tplId);
            $('#tplCodeEdit').val(nadMPTD.tplCode);
            $('#tpleCrsFreEdit').val(nadMPTD.tpleCrsFre);
            $('#tplAvlEdit').val(nadMPTD.tplAvl);
            $('#tplPubEdit').val(nadMPTD.tplPub);
            $('#tplDayEdit').val(nadMPTD.tplDay);
            $('#tplDaysEdit').val(nadMPTD.tplDays);
            $('#tplTEnEdit').val(nadMPTD.tplTEn);
            $('#tplTFaEdit').val(nadMPTD.tplTFa);
            $('#tplTChEdit').val(nadMPTD.tplTCh);
            $('#tplTPrEdit').val(nadMPTD.tplTPr);
            $('#tplTCarbEdit').val(nadMPTD.tplTCarb);
            $('#tplTFibEdit').val(nadMPTD.tplTFib);
            $('#tplTSodEdit').val(nadMPTD.tplTSod);
            $('#tplTSugEdit').val(nadMPTD.tplTSug);
            
            // Retrieve the parentId from the modal's data attribute
            var parentId = $('#editNadMPTemplateDayModal').data('parent-id');
            console.log("Parent ID: " + parentId);

            // Set the DELETE URL dynamically with parentId and childId (nadMPTD.id)
            var deleteUrl = '/nadMPTemplateDetails/' + parentId + '/deleteById?id=' + nadMPTD.id;
            $('#editNadMPTemplateDayModal').find('.btn-danger').attr('data-delete-url', deleteUrl);

            console.log("DELETE URL: " + deleteUrl);

            // Show the modal after successfully setting up the data
            $('#editNadMPTemplateDayModal').modal('show');
        } else {
            alert("Failed to fetch Meal Plan Template data. Please try again.");
        }
    }).fail(function() {
        alert("Error occurred while fetching clinic data.");
    });
});
