<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reclamos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-3">
    <h3>Reclamos</h3><br>
    <div id="iError" class="d-none alert alert-danger"></div>
    <h4>Quiere hacer un reclamo?</h4>
    <form id="iForm" action="javascript:void(null)" method="post" onsubmit="save(event)">
        <div class="row mb-4">
            <label for="chasis" class="col-sm-4 col-form-label">Conoces tu numero de chasis? *</label>
            <div class="col-sm-8 d-flex align-items-center">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" autofocus>
                    <label class="form-check-label" for="flexRadioDefault1">
                        Si
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
                    <label class="form-check-label" for="flexRadioDefault2">
                        No
                    </label>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <label for="chasis" class="col-sm-2 col-form-label">N de chasis *</label>
            <div class="col-sm-9">
                <input type="text" id="chasis" name="chasis" class="form-control" onblur="consultarVehiculo()" required>
            </div>
            <div class="col-sm-1" id="responseChasis">
            </div>
        </div>
        <div class="row mb-3">
            <label for="patente" class="col-sm-2 col-form-label">Patente</label>
            <div class="col-sm-9">
                <input type="text" id="patente" name="patente" class="form-control" onblur="consultarVehiculo()" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="kilometros" class="col-sm-2 col-form-label">Kilometros</label>
            <div class="col-sm-9">
                <input type="text" id="kilometros" name="kilometros" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="apellido" class="col-sm-2 col-form-label">Apellido *</label>
            <div class="col-sm-9">
                <input type="text" id="apellido" name="apellido" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="nombre" class="col-sm-2 col-form-label">Nombre *</label>
            <div class="col-sm-9">
                <input type="text" id="nombre" name="nombre" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="email" class="col-sm-2 col-form-label">E-mail *</label>
            <div class="col-sm-9">
                <input type="email" id="email" name="email" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="telefono" class="col-sm-2 col-form-label">Telefono *</label>
            <div class="col-sm-9">
                <input type="text" id="telefono" name="telefono" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-4 col-form-label">Desea ser contactado por un vendedor? *</label>
            <div class="col-sm-8 d-flex align-items-center">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="contactar" id="contactarSi" value="S" required>
                    <label class="form-check-label" for="contactarSi">Si</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="contactar" id="contactarNo" value="N" required>
                    <label class="form-check-label" for="contactarNo">No</label>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <label for="reclamo" class="col-sm-2 col-form-label">Reclamo (4000 caracteres max.) *</label>
            <div class="col-sm-9">
                <textarea id="reclamo" name="reclamo" class="form-control" maxlength="4000" required></textarea>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Registrar</button>
    </form>
</div>
<!-- Bootstrap JS (Optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="./reclamos/js/reclamos.js"></script>
</body>
</html>