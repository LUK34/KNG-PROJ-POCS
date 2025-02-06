

//VERSION 3: Created on 18 Dec 2024
export function dataTableCreator(tableName, rowsPerPage, recordOrder, tableUtils)
{
	$(function () 
	{
		var tableSelector = "#" + tableName;
		$(tableSelector).DataTable({
			"responsive": true, "lengthChange": false, "autoWidth": false,"pageLength":rowsPerPage,
		   //"buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
		  //"buttons": ["excel","colvis"]  
		  	"order": [[0, recordOrder]], // Ordering in the basis of first column -> "desc" ,"asc""
		  	"buttons": [...tableUtils]
		}).buttons().container().appendTo(tableSelector + '_wrapper .col-md-6:eq(0)');
		$('#example2').DataTable({
		   "paging": true,
		   "lengthChange": false,
		   "searching": false,
		   "ordering": true,
		   "order": [[0, "desc"]],  // Order by the first column in descending order
		   "info": true,
		   "autoWidth": false,
		   "responsive": true,
		   "pageLength":rowsPerPage,
		});
	});
}

//VERSION 2:
/*
export function dataTableCreator(tableName, rowsPerPage, tableUtils)
{
	$(function () 
	{
		var tableSelector = "#" + tableName;
		$(tableSelector).DataTable({
			"responsive": true, "lengthChange": false, "autoWidth": false,"pageLength":rowsPerPage,
		   //"buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
		  //"buttons": ["excel","colvis"]  
		  	"order": [[0, "desc"]], // Order by the first column in descending order
		  	"buttons": [...tableUtils]
		}).buttons().container().appendTo(tableSelector + '_wrapper .col-md-6:eq(0)');
		$('#example2').DataTable({
		   "paging": true,
		   "lengthChange": false,
		   "searching": false,
		   "ordering": true,
		   "order": [[0, "desc"]],  // Order by the first column in descending order
		   "info": true,
		   "autoWidth": false,
		   "responsive": true,
		   "pageLength":rowsPerPage,
		});
	});
}
*/


//VERSION 1:
/*
export function dataTableCreator(tableName, rowsPerPage)
{
	$(function () 
	{
		var tableSelector = "#" + tableName;
		$(tableSelector).DataTable({
			"responsive": true, "lengthChange": false, "autoWidth": false,"pageLength":rowsPerPage,
		   //"buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
		  "buttons": ["excel","colvis"]  
		}).buttons().container().appendTo(tableSelector + '_wrapper .col-md-6:eq(0)');
		$('#example2').DataTable({
		   "paging": true,
		   "lengthChange": false,
		   "searching": false,
		   "ordering": true,
		   "info": true,
		   "autoWidth": false,
		   "responsive": true,
		   "pageLength":rowsPerPage,
		});
	});
}

*/



/*
// Original code for reference:

  $(function () {
					$("#example1").DataTable({
					  "responsive": true, "lengthChange": false, "autoWidth": false,"pageLength":10,
					   //"buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
					  "buttons": ["excel","colvis"]
					  
					}).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
					$('#example2').DataTable({
					  "paging": true,
					  "lengthChange": false,
					  "searching": false,
					  "ordering": true,
					  "info": true,
					  "autoWidth": false,
					  "responsive": true,
					  "pageLength":10,
					});
				  });

 */