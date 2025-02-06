


$(document).ready(function(){
    $(document).on('click', 'tr:has(td)', function() {
        $('tr:has(td)').removeClass('highlight'); // Remove highlight from all rows
        $(this).addClass('highlight'); // Add highlight to clicked row
    });
});





/*

//01 DEC 2023 OLD CODE:
$(document).ready(function(){
    $("tr:has(td)").click(function(){
       $("tr:has(td)").removeClass("highlight"); // remove highlight from all rows
        $(this).addClass("highlight"); // add highlight to clicked row
    });
});


*/


