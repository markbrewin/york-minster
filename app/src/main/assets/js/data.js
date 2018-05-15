var store;

var chestLocations = [];
var keyLocations = [];
var chestsFound = [];
var chestsOpened = [];
var keysFound = [];

function loadData(){
    store = new Persist.Store('YorkCathedral');
    
    if(store.get('chestLocations') != null){
        chestLocations = getData(store.get('chestLocations'));   
    }else{
        store.set('chestLocations', getDataString(chestLocations));
    }
       
    if(store.get('keyLocations') != null){
        keyLocations = getData(store.get('keyLocations'));
    }else{
        store.set('keyLocations', getDataString(keyLocations));       
    }    
    
    if(store.get('chestsFound') != null){
        chestsFound = getData(store.get('chestsFound'));
    }else{
        store.set('chestsFound', getDataString(chestsFound));
    }
    
    if(store.get('chestsOpened') != null){
        chestsOpened = getData(store.get('chestsOpened'));
    }else{
        store.set('chestsOpened', getDataString(chestsOpened));
    }
    
    if(store.get('keysFound') != null){
        keysFound = getData(store.get('keysFound'));
    }else{
        store.set('keysFound', getDataString(keysFound));
    }
}

function getDataString(str){
    return JSON.stringify(str);
}
    
function getData(str){
    return JSON.parse(str);
}

function resetData() {
    store.remove('chestLocations');
    store.remove('keyLocations');
    store.remove('chestsFound');
    store.remove('chestsOpened');
    store.remove('keysFound');
}

function resetSaveData() {
    store.remove('chestsFound');
    store.remove('chestsOpened');
    store.remove('keysFound');
}