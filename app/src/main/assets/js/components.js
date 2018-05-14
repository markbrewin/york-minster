function showToast(msg){
    console.log(msg);
    'use strict';

    var snackbarContainer = document.querySelector('#toast');

    var data = {message: "WIKITUDEWIKITUDE - " + msg};
    snackbarContainer.MaterialSnackbar.showSnackbar(data);
}

function openInfoCard(id, title, info) {
    document.getElementById('infoCard').style.display = "inherit";
    document.getElementById('infoCardTitle').innerHTML = title;
    document.getElementById('infoCardInfo').innerHTML = info;
    document.getElementById('infoCardLink').setAttribute('href', 'treasure.html#' + id);
}

function closeInfoCard() {
    document.getElementById('infoCard').style.display = "none";
}

function addTreasureCard(id) {
    var title;
    var info;
    
    if(chestsOpened.includes(id)){
        title = chestLocations[id].title;
        info = chestLocations[id].info;
    }else if(chestsFound.includes(id)){
        title = chestLocations[id].title;
        info = "You found the chest, but now you need to find the key!";
    }else{
        title = "???";
        info = "You've not found this chest yet...";
    }
    
    var card = '<div class="mdl-grid" id="' + id + '">';
    card += '<div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-phone">';
    card += '<div class="mdl-card mdl-shadow--2dp">';
    card += '<div class="mdl-card__title mdl-card--expand">';
    card += '<h2 class="mdl-card__title-text">';
    card += title;
    card += '</h2></div>';
    card += '<div class="mdl-card__supporting-text">';
    card += info;
    card += '</div>';
    card += '<div class="mdl-card__actions mdl-card--border">';
    card += '<a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">';
    card += 'View More Information';
    card += '</a></div></div></div></div>';
    
    document.getElementById('treasure').innerHTML += card;
}