import {clearElementChildren, 
    createLinkCell, 
    createButtonCell, 
    createTextCell,
    getParameterByName
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayFootballTeams();
});

function fetchAndDisplayFootballTeams() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFootballTeams(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/footballteams', true);
    xhttp.send();
}

function displayFootballTeams(footballTeams) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    footballTeams.footballTeams.forEach(footballteam => {
        tableBody.appendChild(createTableRow(footballteam));
    })

}

function createTableRow(footballteam) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(footballteam));
    tr.appendChild(createLinkCell('edit', '../footballteam_edit/footballteam_edit.html?footballteam='
        + footballteam));
    tr.appendChild(createLinkCell('view', '../footballteam_view/footballteam_view.html?footballteam=' + footballteam));
    tr.appendChild(createButtonCell('delete', () => deleteFootballTeam(footballteam)));
    return tr;
}

function deleteFootballTeam(footballteam) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayFootballTeams()
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/footballteams/' + footballteam, true);
    xhttp.send();
}
