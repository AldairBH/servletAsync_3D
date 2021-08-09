<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Listado de Videojuegos</title>
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/styles.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
    <thead class="table-dark">
    <tr>
        <th>No.</th>
        <th>Nombre de videojuego</th>
        <th>Imagen</th>
        <th>Categoría</th>
        <th>Fecha de salida</th>
        <th>Estatus</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ listGame }" var="beanGame"  varStatus="status">
        <tr>
            <td>${ status.count }</td><br>
            <td>${ beanGame.setIdGame }</td><br>
            <td>${ beanGame.setNameGame }</td><br>
            <td>${ beanGame.setImg_game }</td><br>
            <td>${ beanGame.setCategory_idCategory }</td><br>
            <td>${ beanGame.setDatePremiere }</td><br>
            <td>${beanGame.setStatus }</td><br>
            <td>
                <c:if test="${ beanGame.status == 1 }">
                    <span class="badge rounded-pill bg-success">En venta</span>
                </c:if>
                <c:if test="${ beanGame.status == 0 }">
                    <span class="badge rounded-pill bg-danger">Inactivo</span>
                </c:if>
            </td>
            <td>
                <c:if test="${ beanGame.status == 1 }">
                    <form action="${context}/create" method="POST" style="display: inline;">
                        <input type="hidden" name="action" value="getUserById">
                        <input type="hidden" name="id" value="${ beanGame.setIdGame }">
                        <button type="submit" class="btn btn-outline-primary"><i class="fas fa-edit"></i> Modificar</button>
                    </form>
                    <button id="btn-delete-${ status.count }" data-code="${ beanGame.setIdGame }" data-text="${ beanGame.setNameGame } ${ beanGame.setImg_game } ${ beanGame.setCategory_idCategory} ${ beanGame.setDatePremiere }" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#delete"><i class="fas fa-trash"></i> Eliminar</button>
                </c:if>
                <c:if test="${ beanGame.setIdGame  == 0 }">
                    <button id="btn-details-${ status.count }" data-code="${ beanGame.setIdGame  }" type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#details"><i class="fas fa-info-circle"></i> Detalles</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="${context}/assets/dist/js/main.js"></script>
</body>
</html>