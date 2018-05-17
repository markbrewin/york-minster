function treasure(id, lat, long, title, info){
    this.id = id;
    this.lat = lat;
    this.long = long;
    this.title = title;
    this.info = info;
}

var World = {
    loaded: false,
    firstChest: true,
    firstKey: true,

    chestModel: null,
    chestAnim: null,
    curChest: null,
    curChestID: null,
    keyModel: null,
    keyAnim: null,
    curKey: null,
    curKeyID: null,
    
    plyLat: null,
    plyLong: null,
        
    init: function initFn() {
        if(!World.loaded){
            World.chestModel = new AR.Model("assets/models/chest-1.wt3", {
                scale: {
                    x: 0.05,
                    y: 0.05,
                    z: 0.05
                },
                onClick: function(){
                    if(!chestsFound.includes(World.curChestID)){
                        chestsFound.push(World.curChestID);
                        store.set('chestsFound', getDataString(chestsFound));
                    }
                    
                    if(keysFound.includes(World.curChestID)){                
                        openInfoCard(World.curChestID, "Chest Found!", "Well done! You opened the " + chestLocations[World.curChestID].title + " chest and gained its treasure!");
                        
                        if(!chestsOpened.includes(World.curChestID)){
                            chestsOpened.push(World.curChestID);
                            store.set('chestsOpened', getDataString(chestsOpened));
                        }
                    } else {
                        openInfoCard(World.curChestID, "Chest Found!", "Well done! You found the " + chestLocations[World.curChestID].title + " chest but you need its key first!");     
                    }
                }
            });
            //World.chestAnim = new AR.PropertyAnimation(World.chestModel, "rotate.heading", );

            World.keyModel = new AR.Model("assets/models/key-1.wt3", {
                scale: {
                    x: 0.1,
                    y: 0.1,
                    z: 0.1
                },
                onClick: function(){
                    openInfoCard(World.curKeyID, "Key Found!", "Well done! You found the " + keyLocations[World.curKeyID].title + ", can you find the chest it opens?!");
                    keysFound.push(World.curKeyID);

                    World.curKey.destroy();
                    World.curKey = null;
                    World.curKeyID = null;
                    
                    store.set('keysFound', getDataString(keysFound));
                }
            });

            World.indicatorChestImg = new AR.ImageResource("assets/indiChest.png");     
            World.inidcatorChestDraw = new AR.ImageDrawable(World.indicatorChestImg, 0.1, {
                verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
            }); 

            World.indicatorKeyImg = new AR.ImageResource("assets/indiKey.png");     
            World.inidcatorKeyDraw = new AR.ImageDrawable(World.indicatorKeyImg, 0.1, {
                verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
            }); 

            AR.context.onLocationChanged = function(latitude, longitude, altitude, accuracy){
                showToast("Location update.");

                lastLocation[0] = latitude;
                lastLocation[1] = longitude;
                store.set('lastLocation', getDataString(lastLocation));

                document.getElementById("loadingMessage").innerHTML = keyLocations[0].lat + "," + keyLocations[0].long + "<br>" + lastLocation[0] + "," + lastLocation[1] + "<br>" + distance(lastLocation[0], lastLocation[1], keyLocations[0].lat, keyLocations[0].long, "K") + "km";

                World.chestLocationUpdate();
                World.keyLocationUpdate();
            };
        }
        
        World.worldLoaded();
    },
    
    addChest: function addChestFn(id, lat, long, title, info) {
        if(World.firstChest){
            chestLocations = [];
            
            World.firstChest = false;
        }
        
        chestLocations.push(new treasure(id, lat, long, title, info));
        
        store.set('chestLocations', getDataString(chestLocations));
    },
    
    addKey: function addKeyFn(id, lat, long, title) {
        if(World.firstKey){
            keyLocations = [];
            
            World.firstKey = false;
        }
        
        keyLocations.push(new treasure(id, lat, long, title, "n/a"));
        
        store.set('keyLocations', getDataString(keyLocations));
    },
    
    loadChest: function loadChestFn(){
        var location = new AR.RelativeLocation(null, -50, 0, 0);
        
        World.curChest = new AR.GeoObject(location, {
                drawables: {
                cam: [World.chestModel],
                indicator: [World.inidcatorChestDraw]
            } 
        });
        
        //World.rotateChest(); //Broken - freezes app.
    },
    
    loadKey: function loadKeyFn(){
        var location = new AR.RelativeLocation(null, 0, 50, 0);
        
        World.curKey = new AR.GeoObject(location, {
                drawables: {
                cam: [World.keyModel],
                indicator: [World.inidcatorKeyDraw]
            } 
        });
    },
    
    rotateChest: function rotateChestFn(){
        World.curChest.drawables.cam[0].rotate.heading += 1;
        setTimeout(World.rotateChest(), 5000);
    },
    
    chestLocationUpdate: function chestLocationUpdateFn(){
        for(var i = 0; i < chestLocations.length; i++){
            if(distance(lastLocation[0], lastLocation[1], chestLocations[i].lat, chestLocations[i].long, "K") <= 0.015){
                if(World.curChest == null){
                    World.loadChest();
                    World.curChestID = i;
                }
                
                showToast("Close to chest.");
                
                break;
            }else{
                console.log("Far away from chest.");
                if(World.curChest != null){
                    showToast("Destroyed chest.");
                    World.curChest.destroy();
                    World.curChest = null;
                    World.curChestID = null;
                }
            }
        }
    },
    
    keyLocationUpdate: function keyLocationUpdateFn(){
        for(var i = 0; i < keyLocations.length; i++){
            if((distance(lastLocation[0], lastLocation[1], keyLocations[i].lat, keyLocations[i].long, "K") <= 0.015) && !keysFound.includes(i)){
                if(World.curKey == null){
                    World.loadKey();
                    World.curKeyID = i;
                }
                
                showToast("Close to key.");
                
                break;
            }else{
                console.log("Far away from key.");
                if(World.curKey != null){
                    showToast("Destroyed key.");
                    World.curKey.destroy();
                    World.curKey = null;
                    World.curKeyID = null;
                }
            }
        }
    },
    
    setLocation: function setLocationFn(type) {
        World.firstChest = false;
        World.firstKey = false;
        
        if(type === 'c'){
            chestLocations[0].lat = lastLocation[0];
            chestLocations[0].long = lastLocation[1];
            //World.addChest(999, lastLocation[0], lastLocation[1], "Dev Chest", "Testing chest.");
        }else if(type === 'k'){
            keyLocations[0].lat = lastLocation[0];
            keyLocations[0].long = lastLocation[1];
            //World.addKey(999, lastLocation[0], lastLocation[1], "Dev Key", "The key to being a muscly boi.");
        }else{
            World.setLocation('c');
            World.setLocation('k');
        }
        
        store.set('chestsFound', getDataString(chestsFound));
        store.set('keysFound', getDataString(keysFound));
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        readData = false;
        
        if(!dev){
            var e = document.getElementById('loadingMessage');
            e.parentElement.removeChild(e);
        }
        
        //AR.hardware.camera.enabled = true;
        //AR.hardware.sensors.enabled = true;
    }
};