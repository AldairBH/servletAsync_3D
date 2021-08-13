var ver = document.getElementById('btn-registar');
const agregar = document.getElementById('Registrar');
const cerrar = document.getElementById('cerrar');


const fill = (list) => {
    let table = "";

    if(list.length > 0){
        for(let i = 0; i < list.length; i++) {
            table += `
			<tr>
				<td>${ i + 1 }</td>
				<td>${list[i].nameGame}</td>
				<td><img src="data:image/jpeg;base64,${list[i].imgGame}"></td>				
				<td>${list[i].Category_idCategory.nameCategory}</td>
				<td>${list[i].datePremiere}</td>
				<td>${list[i].status ? "Activo" : "Inactivo"}</td>
				<td>
					<button type="button" class="btn btn-info">Ver</button>
					<button type="button" class="btn btn-primary">Modificar</button>
					<button type="button" class="btn btn-danger">Eliminar</button>
				</td>
			</tr>
			`;
        }
    }else{
        table = `
		<tr class="text-center">
			<td colspan="5">No hay registros para mostrar</td>
		</tr>
		`;
    }
    $(`#container > tbody`).html(table);
};

const registrar = (json) =>{
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'POST',
        url: contextPath + '/createGame?action=create',
        data: {cat: json },
        success: function(){
            findAll();
        }
    });
}

const findAll = () => {
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        url: contextPath + '/readGames',
        data: { }
    }).done(function(res){
        fill(res.listGames);
    });
};
findAll();

(function() {
    ver.addEventListener('click', function() {
        agregar.showModal();
    });
    cerrar.addEventListener('click', function() {
        agregar.close();
    });


})();


$('#datos').submit(function () {
    var obj = {
        nameGame: $('nameGame').val(),
        imgGame: $('imgGame').val(),
        idCategory: $('idCategory').val(),
        datePremiere: $('datePremiere').val(),
        status: $('status').val()
    };

    registrar(JSON.stringify(obj));

});

