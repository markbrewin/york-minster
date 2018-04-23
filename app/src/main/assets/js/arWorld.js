var World = {
    loaded: false,
    
    chestLocationList: [],

    chestModel: null,
    curChest: null,
    
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
        
        World.indicatorImage = new AR.ImageResource("assets/indi.png");
        
        World.inidcatorDrawable = new AR.ImageDrawable(World.indicatorImage, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        }); 
        
        AR.context.onLocationChanged = function(latitude, longitude, altitude, accuracy){
            World.plyLat = latitude;
            World.plyLong = longitude;
            
            console.log("update");
        
            document.getElementById("loadingMessage").innerHTML = World.curChest.locations[0].latitude + "," + World.curChest.locations[0].longitude + "<br>" + World.plyLat + "," + World.plyLong;
        };
    },
    
    addChest: function addChestFn(lat, long, alt) {
        //World.chestList.push(new chest(lat, long, alt));
    },
    
    loadChest: function loadChestFn(){
        var location = new AR.RelativeLocation(null, -50, 0, 0);
        
        World.curChest = new AR.GeoObject(location, {
                drawables: {
                cam: [World.chestModel],
                indicator: [World.inidcatorDrawable]
            } 
        });
        
        World.rotateChest();
    },
    
    rotateChest: function rotateChestFn(){
        console.log("rotate");

        World.curChest.drawables.cam[0].rotate.heading += 1;
        setTimeout(World.rotateChest(), 5000);
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.parentElement.removeChild(e);
    }
};

World.init();