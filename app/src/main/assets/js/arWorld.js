function chest(lat, long){
    this.lat = lat;
    this.long = long;
}

var World = {
    loaded: false,
    
    chestLocationList: [],

    chestModel: null,
    curChest: null,
    keyModel: null,
    keyChest: null,
    
    plyLat: null,
    plyLong: null,
        
    init: function initFn() {
        World.chestLocationList = [];
        
        World.chestModel = new AR.Model("assets/models/chest.wt3", {
            scale: {
                x: 0.05,
                y: 0.05,
                z: 0.05
            }
        });
        World.keyModel = new AR.Model("assets/models/key.wt3", {
            scale: {
                x: 1,
                y: 1,
                z: 1
            }
        });
        
        World.indicatorChestImg = new AR.ImageResource("assets/indiChest.png");     
        World.inidcatorChestDraw = new AR.ImageDrawable(World.indicatorImage, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        }); 
        
        World.indicatorKeyImg = new AR.ImageResource("assets/indiKey.png");     
        World.inidcatorKeyDraw = new AR.ImageDrawable(World.indicatorImage, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        }); 
        
        AR.context.onLocationChanged = function(latitude, longitude, altitude, accuracy){
            World.showToast("Location update.");
            
            World.plyLat = latitude;
            World.plyLong = longitude;
            
            document.getElementById("loadingMessage").innerHTML = World.chestLocationList[0].lat + "," + World.chestLocationList[0].long + "<br>" + World.plyLat + "," + World.plyLong;
                
            World.chestLocationUpdate();
        };
    },
    
    showToast: function showToast(msg){
        console.log(msg);
        'use strict';
        
        var snackbarContainer = document.querySelector('#toast');
        
        var data = {message: "WIKITUDEWIKITUDE - " + msg};
        snackbarContainer.MaterialSnackbar.showSnackbar(data);
    },
    
    addChest: function addChestFn(lat, long) {
        World.chestLocationList.push(new chest(lat, long));
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
    
    rotateChest: function rotateChestFn(){
        World.curChest.drawables.cam[0].rotate.heading += 1;
        setTimeout(World.rotateChest(), 5000);
    },
    
    chestLocationUpdate: function chestLocationUpdateFn(){
        for(i = 0; i < World.chestLocationList.length; i++){
            if((Math.abs(World.plyLat - World.chestLocationList[i].lat) <= 0.000005) && (Math.abs(World.plyLong - World.chestLocationList[i].long) <= 0.000005)){
                if(World.curChest == null){
                    World.loadChest();
                }
                
                World.showToast("Close to chest.");
                
                break;
            }else{
                World.showToast("Far away from chest.");
                if(World.curChest != null){
                    World.showToast("Destroyed chest.");
                    World.curChest.destroy();
                    World.curChest = null;
                }
            }
        }
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.parentElement.removeChild(e);
    }
};

World.init();