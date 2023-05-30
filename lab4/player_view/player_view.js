import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayPlayer();
    fetchAndDisplayPlayers();
});


function fetchAndDisplayPlayers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/footballteams/' + getParameterByName('footballteam') + '/players', true);
    xhttp.send();
}

function displayPlayers(players) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    players.players.forEach(player => {
        tableBody.appendChild(createTableRow(player));
    })
}

function createTableRow(player) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(player.surname));
    tr.appendChild(createTextCell(player.rate));
    tr.appendChild(createLinkCell('edit', '../player_edit/player_edit.html?footballteam='
        + getParameterByName('footballteam') + '&player=' + player.surname));
    tr.appendChild(createButtonCell('delete', () => deletePlayer(player.surname)));
    return tr;
}

function deletePlayer(player) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPlayers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/footballteams/' + getParameterByName('footballteam')
        + '/players/' + player, true);
    xhttp.send();
}


function fetchAndDisplayPlayer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayer(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/players/' + getParameterByName('player'), true);
    xhttp.send();
}

function displayPlayer(player) {
    //setTextNode('id', director.id);
    setTextNode('surname', player.surname);
    setTextNode('rate', player.rate);
}
