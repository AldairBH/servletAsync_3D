var abrir = document.getElementById('btn-registar');
var abrir2 = document.getElementsByClassName('btn-modificar');
var abrir3 = document.getElementsByClassName('btn-eliminar');
const cancelButton2 = document.getElementById('cerrar1');
const cancelButton3 = document.getElementById('cerrar2');
const cancelButton = document.getElementById('cerrar');
const dialogoregistrar = document.getElementById('Registrar');
const dialogomodificar = document.getElementById('Modificar');
const dialogoeliminar = document.getElementById('Eliminar');


(function() {


    abrir.addEventListener('click', function() {
        dialogoregistrar.showModal();
    });

    for(let i=0; i < abrir2.length; i++){

        cargarmodal(abrir2[i]);
    }

    for(let x=0; x< abrir3.length; x++){
        cargarmodal2(abrir3[x]);
    }
    cancelButton.addEventListener('click', function() {
        dialogoregistrar.close();
    });

    cancelButton2.addEventListener('click', function() {
        dialogomodificar.close();
    });
    cancelButton3.addEventListener('click', function() {
        dialogoeliminar.close();
    });

})();

const fill = (list) => {
    let table = "";

    if(list.length > 0){
        for(let i = 0; i < list.length; i++) {
            table += `
			<tr>
				<td>${ i + 1 }</td>
				<td>${list[i].nameGame}</td>
				<td>${list[i].datePremiere}</td>
				<td><img src="data:image/jpeg;base64,${list[i].imgGame}"></td>
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

function cargarmodal(boton){

    boton.onclick = function(){

        let id = $(this).attr('data-id');
        let name = $(this).attr('data-name');
        let image = $(this).attr('data-image');
        let Idcategory = $(this).attr('data-category');
        let date = $(this).attr('data-date');
        let status = $(this).attr('data-status');

        $('#IdGame').val(id);
        $('#name').val(name);
        $('#image').val(image);
        $('#Idcategory').val(Idcategory);
        $('#date').val(date);
        $('#status').val(status);

        dialogomodificar.showModal();


    }
}

function  cargarmodal2(boton2) {
    boton2.onclick = function(){
        let id = $(this).attr('data-id');
        let name = $(this).attr('data-name');;

        $('#IdGame').val(id);
        $('#name').val(name);
        dialogoeliminar.showModal();
    }
}
