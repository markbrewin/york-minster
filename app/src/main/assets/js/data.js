var store;

var chestLocations = [];
var keyLocations = [];
var chestsFound = [];
var chestsOpened = [];
var keysFound = [];
var lastLocation = [];
var dev = false;

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
    
    if(store.get('lastLocation') != null){
        lastLocation = getData(store.get('lastLocation'));
    }else{
        store.set('lastLocation', getDataString(lastLocation));
    }
    
    if(store.get('dev') != null){
        dev = getData(store.get('dev'));
    }else{
        store.set('dev', getDataString(dev));
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
    store.remove('lastLocation');
    store.remove('dev');
}

function resetSaveData() {
    store.remove('chestsFound');
    store.remove('chestsOpened');
    store.remove('keysFound');
}