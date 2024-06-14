function save(event) {
    event.preventDefault();

    cleanError();

    fetch('./save.jsp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'item': document.getElementById('iData').value
        })
    }).then(res => {
        if(res.ok) {
            return res.text();
        }
        return res.text().then(html => {
            throw new Error(html);
        });
    }).then(html => {
        document.getElementById('iList').insertAdjacentHTML('beforeend', html);
        document.getElementById('iData').value = '';
    }).catch(err => {
        showError(err.message);
    });
}

function remove(id) {
    cleanError();

    fetch('./remove.jsp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'removedItem': id
        })
    }).then(res => {
        if(res.ok) {
            document.getElementById(index).remove();
            return;
        }
        return res.text().then(html => {
            throw new Error(html);
        });
    }).catch(err => {
        showError(err.message);
    });
}

function showError(message) {
    document.getElementById('iError').innerHTML = message;
    document.getElementById('iError').classList.remove('d-none');
    executeScripts('iError');
}

function cleanError() {
    document.getElementById('iError').classList.add('d-none');
    document.getElementById('iError').innerHTML = '';
}

function executeScripts(containerId) {
    //Contenedor de los scripts a ejecutar
    const container = document.getElementById(containerId);

    //Obtengo los scripts del contenedor
    const scripts = container.querySelectorAll('script');
    scripts.forEach(script => {
        //Si el script tiene un atributo 'src', crea un nuevo script para cargar y ejecutar
        if (script.src) {
            const newScript = document.createElement('script');
            newScript.src = script.src;
            document.head.appendChild(newScript);
        } else {
            // Si no tiene 'src', es un script inline y se puede ejecutar directamente
            eval(script.innerText);
        }
    });
}