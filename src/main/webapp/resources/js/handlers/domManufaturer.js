var ERROR_MESSAGE = 'syntax error';

function createInput(identifier, type){
	if(identifier === undefined){
		identifier='undef';
	}
	if(type === undefined){
		type='class';
	}
	
	return 	'<input type="text" '+type+'="'+identifier+'">';
}

function createButton(label, identifier, type){
	return '<input type="button"  '+type+'="'+identifier+'" value="'+label+'">';
}

function createUnorderedList(itemsArray){
	if(itemsArray instanceof Array) {
		var ul = '<ul>';
		for (var i = 0; i < itemsArray.length; i++) {
			ul += '<li>'+itemsArray[i]+'</li>';
		}
		ul += '</ul>';
		return ul;
	} else {
		return ERROR_MESSAGE + ": createUnorderedList()";
	}
}

function createSimpleTable(tableId, rows, cols, colNames, cellDatas){
	var table = '<table id="'+tableId+'"><thead>';
	var th = '', td = '';
	for (var row = 0; row < rows; row++) {
		table += '<tr>';
		for (var col = 0; col < cols; col++) {
			if(row == 0) {
				th += '<th>';
				if(colNames instanceof Array && colNames.length == cols) {
					th += colNames[col];
				} else {
					th += 'defaultName';
				}
				th += '</th>';
			} else {
				td += '<td>';
				if(cellDatas instanceof Array && cellDatas.length == cols){
					td += cellDatas[col];
				} else {
					td += 'defaultContent';
				}
				td += '</td>';
			}
		}
		
		if(row == 0) {
			table += th;
			table += '</tr>'
			table += '</thead><tboby>';
		} else {
			table += td;
			table += '</tr>';
		}
	}
	table += '</tboby>'
//		console.log(table)
	return table;	
}


function createCheckbox(identifierType, identifier){
	return 	'<input type="checkbox" '+identifierType+'="'+identifier+'">';
}














