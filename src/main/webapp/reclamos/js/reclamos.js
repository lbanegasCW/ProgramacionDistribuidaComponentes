function save(event) {
    event.preventDefault();

    var form = document.getElementById('iForm');
    var formData = new FormData(form);
    var params = new URLSearchParams(formData);

    fetch('./saveReclamo.jsp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: params
    }).then(res => {
        if(res.ok) {
            return res.text();
        } else {
            return res.text().then(html => {
                throw new Error(html);
            });
        }
    }).then(url => {
        window.location.href = url.trim();
    }).catch(err => {
        showError(err.message);
    });
}

function consultarVehiculo() {
    fetch('./consulta.jsp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'chasis': document.getElementById('chasis').value,
            'patente': document.getElementById('patente').value,
        })
    }).then(res => {
        if (res.status === 200) {
            document.getElementById('chasis').classList.remove('border', 'border-danger');
            return res.text();
        } else if (res.status === 206) {
            document.getElementById('chasis').classList.add('border', 'border-danger');
            return res.text();
        } else {
            throw new Error('Error en la solicitud: ' + res.status);
        }
    }).then(html => {
        document.getElementById('responseChasis').innerHTML = html;
    }).catch(err => {
        showError(err.message);
    });
}