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
    curChest: null,
    curChestID: null,
    keyModel: null,
    curKey: null,
    curKeyID: null,
    
    plyLat: null,
    plyLong: null,
        
    init: function initFn() {
        if(!World.loaded){            
            World.chestModel = new AR.Model("assets/models/chest.wt3", {
                scale: {
                    x: 0.05,
                    y: 0.05,
                    z: 0.05
                },
                onClick: function(){
                    if(keysFound.includes(World.curChestID)){
                        openInfoCard(World.curChestID, "Chest Found!", "Well done! You opened the " + chestLocations[World.curChestID].title + " chest and gained its treasure!");
                        chestsOpened.push(World.curChestID);
                        
                        store.set('chestsOpened', getDataString(chestsOpened));
                    } else {
                        openInfoCard(World.curChestID, "Chest Found!", "Well done! You found the " + chestLocations[World.curChestID].title + " chest but you need its key first!");
                        chestsFound.push(World.curChestID);
                        
                        store.set('chestsFound', getDataString(chestsFound));
                    }
                }
            });

            World.keyModel = new AR.Model("assets/models/padlock.wt3", {
                scale: {
                    x: 0.1,
                    y: 0.1,
                    z: 0.1
                },
                onClick: function(){
                    openInfoCard("Key Found!", "Well done! You found a key, can you find the chest it opens?!");
                    keysFound.push(World.curKeyID);

                    showToast("Destroyed key.");
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

                World.plyLat = latitude;
                World.plyLong = longitude;

                document.getElementById("loadingMessage").innerHTML = chestLocations[0].lat + "," + chestLocations[0].long + "<br>" + World.plyLat + "," + World.plyLong;

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
            if((Math.abs(World.plyLat - chestLocations[i].lat) <= 0.00005) && (Math.abs(World.plyLong - chestLocations[i].long) <= 0.00005)){
                if(World.curChest == null){
                    World.loadChest();
                    World.curChestID = chestLocations[i].id;
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
            if((Math.abs(World.plyLat - keyLocations[i].lat) <= 0.00005) && (Math.abs(World.plyLong - keyLocations[i].long) <= 0.00005) && !keysFound.includes(World.curChestID)){
                if(World.curKey == null){
                    World.loadKey();
                    World.curKeyID = keyLocations[i].id;
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
    
    setLocation: function setLocationFn() {
        keyLocations[0].lat = World.plyLat;
        keyLocations[0].long = World.plyLong;
        chestLocations[0].lat = World.plyLat;
        chestLocations[0].long = World.plyLong;
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        readData = false;
        
        //var e = document.getElementById('loadingMessage');
        //e.parentElement.removeChild(e);
        
        AR.hardware.camera.enabled = true;
        AR.hardware.sensors.enabled = true;
    }
};