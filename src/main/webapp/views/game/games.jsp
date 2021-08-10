<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Listado de videojuegos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/main.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/dialogos.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/games.css">
    <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
<div class="fondito">
<div class="main-content">
    <div class="d-flex">
        <div class="col-sm-12">
            <button type="button" class="btn btn-success" id="btn-registar"><i class="fas fa-plus"></i>Agregar</button>
            <table id="container" class="table table-hover" class="table" class="list" id="datostabla">
                <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Fecha</th>
                    <th>Imagen</th>
                    <th>Estado</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listGame}" var="beanGame" >
                <tr>
                    <td>${beanGame.getIdGame()}</td>
                    <td>${beanGame.getNameGame()}}</td>
                    <td>${beanGame.getImg_game()}</td>
                    <td>${beanGame.getIdCategory()}</td>
                    <td>${beanGame.getDatePremiere()}</td>
                    <td>${beanGame.getStatus()}</td>
                    <td>
                        <a class="btn btn-primary btn-sm btn-modificar" data-id="${beanGame.getIdGame()}" data-name="${beanGame.getNameGame()} " data-image="${beanGame.getImg_game()}" data-category="${beanGame.getIdCategory()}" data-date="${beanGame.getDatePremiere()}" data-status="${beanGame.getStatus()}"><i class="fas fa-edit"></i></a>
                        <a class="btn btn-danger btn-sm btn-eliminar" data-id="${beanGame.getIdGame()}" data-name="${beanGame.getNameGame()} "><i class="fas fa-trash-alt"></i></a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>

<dialog id="Registrar" class="col-sm-5 dialogo">
    <div class="d-flex">
        <div class="card col-sm-12 border-0">
            <div class="card-header align-content-end">
                <button class="btn btn-light" id="cerrar" type="reset"><i class="fas fa-times"></i></button>
            </div>
            <div class="card-body">
                <form action="ServletGame?action=create" method="POST" class="row g-3" >
                    <input type="hidden" name="action" value="create">
                    <div class="form-group col-md-6">
                        <label>Nombre:</label>
                        <input type="text" class="form-control campo" name="name" onkeyup="" pattern="^[a-zA-ZáéíóúÁÉÍÓÚÑñüÜ ]+" title="Sólo letras" value="${beanGameselected.getIdGame()}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Fecha:</label>
                        <input type="date" class="form-control campo" name="date" onkeyup=""/>
                    </div>
                    <div class="form-group col-md-12">
                        <label>Imagen:</label>
                        <input type="file" class="form-control" name="image" onkeyup="" />
                    </div>
                    <div class="col-md-6">
                        <label>Estado (0=Expirado - 1=En venta): </label>
                        <div class="row">
                            <div class="">
                                <select class="form-select fecha" name="beanCategory" onchange="">
                                    <option value="Cajero">0</option>
                                    <option value="Coordinador">1</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <menu>
                        <button type="submit" class="btn btn-success"><i class="fas fa-plus"></i>Registrar</button>
                    </menu>
                </form>
            </div>
        </div>
    </div>
</dialog>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="${context}/assets/dist/js/funciones.js"></script>
<script src="${context}/assets/dist/js/games.js"></script>
<script src="${context}/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${context}/assets/plugins/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
