function coord(id, lat, long){
    this.id = id;
    this.lat = lat;
    this.long = long;
}

var World = {
    loaded: false,
    
    chestLocationList: [],
    keyLocationList: [],
    
    keysFound: [],
    chestsFound: [],

    chestModel: null,
    curChest: null,
    curChestID: null,
    keyModel: null,
    curKey: null,
    curKeyID: null,
    
    plyLat: null,
    plyLong: null,
        
    init: function initFn() {
        World.chestLocationList = [];
        World.keyLocationList = [];
        World.keysFound = [];
        World.chestsFound = [];
        
        World.chestModel = new AR.Model("assets/models/chest.wt3", {
            scale: {
                x: 0.05,
                y: 0.05,
                z: 0.05
            },
            onClick: function(){
                if(World.keysFound.includes(World.curChestID)){
                    openInfoCard("Chest Found!", "Well done! You opened the chest and gained its treasure!");
                    World.chestsFound.push(World.curChestID);
                } else {
                    openInfoCard("Chest Found!", "Well done! You found the chest but you need its key first!");
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
                openInfoCard("Key Found!", "Well done! You found a key, can you find the chest it opens?!")
                World.keysFound.push(World.curKeyID);
                
                showToast("Destroyed key.");
                World.curKey.destroy();
                World.curKey = null;
                World.curKeyID = null;
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
            
            document.getElementById("loadingMessage").innerHTML = World.chestLocationList[0].lat + "," + World.chestLocationList[0].long + "<br>" + World.plyLat + "," + World.plyLong;
                
            World.chestLocationUpdate();
            World.keyLocationUpdate();
        };
    },
    
    addChest: function addChestFn(id, lat, long) {
        World.chestLocationList.push(new coord(id, lat, long));
    },
    
    addKey: function addKeyFn(id, lat, long) {
        World.keyLocationList.push(new coord(id, lat, long));
    },
    
    loadChest: function loadChestFn(){
        var location = new AR.RelativeLocation(null, -50, 0, 0);
        
        World.curChest = new AR.GeoObject(location, {
                drawables: {
                cam: [World.chestModel],
                indicator: [World.inidcatorChestDraw]
            } 
        });
        
        //World.rotateChest();
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
        for(var i = 0; i < World.chestLocationList.length; i++){
            if((Math.abs(World.plyLat - World.chestLocationList[i].lat) <= 0.00005) && (Math.abs(World.plyLong - World.chestLocationList[i].long) <= 0.00005)){
                if(World.curChest == null){
                    World.loadChest();
                    World.curChestID = World.chestLocationList[i].id;
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
        for(var i = 0; i < World.keyLocationList.length; i++){
            if((Math.abs(World.plyLat - World.keyLocationList[i].lat) <= 0.00005) && (Math.abs(World.plyLong - World.keyLocationList[i].long) <= 0.00005) && !World.keysFound.includes(World.curChestID)){
                if(World.curKey == null){
                    World.loadKey();
                    World.curKeyID = World.keyLocationList[i].id;
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
        World.keyLocationList[0].lat = World.plyLat;
        World.keyLocationList[0].long = World.plyLong;
        World.chestLocationList[0].lat = World.plyLat;
        World.chestLocationList[0].long = World.plyLong;
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.parentElement.removeChild(e);
    }
};

World.init();

function showToast(msg){
    console.log(msg);
    'use strict';

    var snackbarContainer = document.querySelector('#toast');

    var data = {message: "WIKITUDEWIKITUDE - " + msg};
    snackbarContainer.MaterialSnackbar.showSnackbar(data);
}

function openInfoCard(title, info) {
    document.getElementById('infoCard').style.display = "inherit";
    document.getElementById('infoCardTitle').innerHTML = title;
    document.getElementById('infoCardInfo').innerHTML = info;
}

function openProgCard() {
    document.getElementById('progressCard').style.display = "inherit";
    document.getElementById('progressCardInfo').innerHTML = "You currently have found the following chests: " + World.chestsFound;
}

function closeInfoCard() {
    document.getElementById('infoCard').style.display = "none";
}

function closeProgCard() {
    document.getElementById('progressCard').style.display = "none";
}