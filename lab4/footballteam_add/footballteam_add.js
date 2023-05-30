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
    xhttp.open("POST", getBackendUrl() + '/api/footballteams/', true);

    const request = {
        'name': document.getElementById('name').value,
        'attack': parseInt(document.getElementById('attack').value),
        'defence': parseInt(document.getElementById('defence').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}
