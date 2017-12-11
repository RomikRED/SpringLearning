var STATE;
var STATE_EDIT = 0;
var STATE_DELETE = 1;
var STATE_VIEW = 2;

var idActorInputDom = '#div-actor-input'
var idActorsListDom = '#div-actors-list';

var idActorInput = 'input-actor';

var idActorsGetButton = 'btn-actors-get';
var labelActorsGetButton = "Find";

var idDeleteActorButton = 'btn-actors-delete';
var labelDeleteActorButton = "Delete";

var idEditActorButton = 'btn-actors-edit';
var labelEditActorButton = "Edit";

var classSaveActorButton = 'btn-actors-save';
var labelSaveActorButton = "Save";

var classDeleteActorCheckbox = 'checkbox-actors-delete';
var classEditActorCheckbox = 'checkbox-actors-edit';

var idTableActors = 'table-actors';
var classTableData = 'table-actor-data';
var firstColumnName = 'First name';
var secondColumnName = 'Last name';
var attrActorId = 'data-actor-id';	
var attrActorFirstName = 'data-actor-first-name';	
var attrActorLastName = 'data-actor-last-name';	
//-------------------------------------LISTENERS
$( document ).ready( afterLoadedInit );
$( document ).on('click', hashSelector(idActorsGetButton), getActorById);

$( document ).on('click', dotSelector(classDeleteActorCheckbox), deleteActorCheckboxHandler);
$( document ).on('click', dotSelector(classEditActorCheckbox), editActorCheckboxHandler);

$( document ).on('click', hashSelector(idDeleteActorButton), deleteActorsButtonHandler);
$( document ).on('click', hashSelector(idEditActorButton), editActorsButtonHandler);
$( document ).on('click', dotSelector(classSaveActorButton), saveActorButtonHandler);
//-------------------------------------HANDLERS
//After page loaded
function afterLoadedInit(){
	STATE =  STATE_VIEW;
	//add input and findButton for search by id
	var input = createInput( idActorInput, 'id' );
	var button = createButton( labelActorsGetButton, idActorsGetButton, 'id' );
	$( idActorInputDom ).html( input + button );
	//Make empty table with headers
	$( idActorsListDom )								.html('<table></table>');
	$( idActorsListDom ).find('table')					.attr('id',idTableActors);
	$( hashSelector(idTableActors) )					.append('<thead></thead><tbody></tbody>');
	$( hashSelector(idTableActors) + ' thead' )			.append('<tr></tr>');
	$( hashSelector(idTableActors) + ' thead tr' )		.append('<th>'+firstColumnName+'</th><th>'+secondColumnName+'</th>');
	//add Delete Edit Btns
	$( hashSelector(idTableActors) +' tr th:last' ).after(
			 '<th>'		+ createButton(labelDeleteActorButton, 	idDeleteActorButton, 	'id') +	'</th>' +
			 '<th>'		+ createButton(labelEditActorButton, 	idEditActorButton, 		'id') +	'</th>'
		);
}

//After find button pressed
function getActorById(){
	var inputedId = $( hashSelector(idActorInput) ).val();
	if( inputedId === ''){
		apprise("Please enter actor ID");
		afterLoadedInit();
	} else if( inputedId > 0 ) {
		doAjaxQuery('actors/'+ inputedId, 'GET', handleActor);
	} else {
		apprise("ID contains only numbers!");
		afterLoadedInit();
	}
}

function handleActor(response){
	STATE = STATE_VIEW;
//	$( idActorsListDom ).html( 
//			createSimpleTable( 
//					idTableActors,							
//					2, 2, 									
//					['FirstName', 'LastName'], 				
//					[response.firstName, response.lastName]	
//			)
//		);
	
//	$( hashSelector(idTableActors) +' tbody' )			.append( '<tr></tr>' );
	
//	$( hashSelector(idTableActors) +' tbody tr:first' ).attr( attrActorId, response.id );
//	$( hashSelector(idTableActors) +' tr td' ).addClass( classTableData );
//	
////	add Delete Edit Btns;
//	$( hashSelector(idTableActors) +' tr th:last' ).after(
//			 '<th>'		+ createButton(labelDeleteActorButton, idDeleteActorButton, 'id') +	'</th>'+
//			 '<th>'		+ createButton(labelEditActorButton, idEditActorButton, 'id') 	  +	'</th>'
//		);
////	add Delete Edit Checkboxes	
//	$( hashSelector(idTableActors) +' tr td:last' ).after(
//		 '<td>'		+ createCheckbox('class', classDeleteActorCheckbox)  +	'</td>'+
//		 '<td>'		+ createCheckbox('class', classEditActorCheckbox) 	 +	'</td>'
//	);
}

function handleError(e){
	switch (e.status) {
	case 404:
		apprise("No such record!");
		afterLoadedInit();
		break;

	default:
		apprise("Server error");
		break;
	}
}

function deleteActorCheckboxHandler(){
	var isCheckedDelete = $(this).prop('checked');
	var editCheckbox = $(this).parent().parent().find(dotSelector(classEditActorCheckbox));
	var isCheckedEdit = $(editCheckbox).prop('checked');
	
	if(isCheckedDelete && isCheckedEdit){
		$(editCheckbox).prop('checked', false);
	}
}

function editActorCheckboxHandler(){
	var isCheckedEdit = $(this).prop('checked');
	var deleteCheckbox = $(this).parent().parent().find(dotSelector(classDeleteActorCheckbox));
	var isCheckedDelete = $(deleteCheckbox).prop('checked');
	
	if(isCheckedDelete && isCheckedEdit){
		$(deleteCheckbox).prop('checked', false);
	}
}

function deleteActorsButtonHandler(){
	STATE = STATE_DELETE;
	var checkboxesDelete = [];
	$(hashSelector(idTableActors)+' tr').each(function(){
		isCheckedDelete = $(this).find(dotSelector(classDeleteActorCheckbox)).prop('checked');
		if(isCheckedDelete){
			checkboxesDelete.push(this);
		}
	});
	
	if(checkboxesDelete.length > 0){
		apprise('Are you really want to delete selected rows?',{verify:true}, function(resp){
			if(resp){
				$.each(checkboxesDelete, function(){
					$(this).css({display:'none'});
				});
				//TODO: Add command to server for delete
			}
		});
	}
}

function editActorsButtonHandler(){
	if(STATE == STATE_EDIT){
		return;
	}
	STATE = STATE_EDIT;
	var checkboxesEdit = [];
	$(hashSelector(idTableActors)+' tr').each(function(){
		isCheckedEdit = $(this).find(dotSelector(classEditActorCheckbox)).prop('checked');
		if(isCheckedEdit){
			checkboxesEdit.push(this);
		}
	});
	
	$.each(checkboxesEdit, function(){
		var input = createInput('', '');
		$(this).find(dotSelector(classTableData)).each(function(){
			var value = $(this).text();
			$(this).html( input )
				.find('input')
				.val( value );
		});
		
		$(this).find('td:last').after(
			'<td>'		+ createButton(labelSaveActorButton, classSaveActorButton, 'class') 	+	'</td>'
		);
	});
}

function saveActorButtonHandler(){
	STATE = STATE_VIEW;
	var currentRow = $(this).parent().parent(),
		dataSave = [];
	$(currentRow).find(dotSelector(classTableData)+' input').each(function(){
//		var key = $(currentRow).attr(attrActorId);
//		var value = $(this).val();
			
		$(this).parent().html( value );
	});
	
	$(hashSelector(idTableActors)+' tr td:last').each(function(){
		$(this).remove();
	});
	console.log(dataSave);
	//TODO: Add command to server for update
}

//-------------------------------------UTILLS FUNCTIONS
function hashSelector(selector){
	return '#'+selector;
}

function dotSelector(selector){
	return '.'+selector;
}

function clearContent(erased){
	$( dotSelector(erased) ).each(function(){
			$(this).html('').val('').text('');
	});
}

function doAjaxQuery(serverUrl, method, successHandler){
	$.ajax(serverUrl, {
		   type: 		method,
		   contentType: "application/json; charset=UTF-8",
		   statusCode: 	{
					      200: function (response) {
//						         alert('200');
						      },
					      201: function (response) {
//						         alert('201');
						      },
					      400: function (response) {
//						         alert('400');
						      },
					      404: function (response) {
//						         alert('404');
						      }
		   				}, 
		   success: 	successHandler,
		   error: 		handleError
	});
}
