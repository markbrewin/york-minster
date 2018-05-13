var store;

var chestLocations = [];
var keyLocations = [];
var chestsFound = [];
var keysFound = [];

function loadData(){
    store = new Persist.Store('YorkCathedral');
    
    if(store.get('chestLocations') != null){
        chestLocations = getData(store.get('chestLocations'));
        store.remove('chestLocations'); 
    }
    store.set('chestLocations', getDataString(chestLocations));
    
    if(store.get('keyLocations') != null){
        chestLocations = getData(store.get('keyLocations'));
        store.remove('keyLocations'); 
    }
    store.set('keyLocations', getDataString(keyLocations));
    
    if(store.get('chestsFound') != null){
        chestLocations = getData(store.get('chestsFound'));
    }else{
        store.set('chestsFound', getDataString(chestsFound));
    }
    
    if(store.get('keysFound') != null){
        chestLocations = getData(store.get('keysFound'));
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