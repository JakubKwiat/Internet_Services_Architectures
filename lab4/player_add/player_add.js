import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //fetchAndDisplayPlayer();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/players/', true);

    const request = {
        'surname': document.getElementById('surname').value,
        'rate': document.getElementById('rate').value,
        'footballteam': document.getElementById('footballteam').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}
