var nowLat;
var nowLong;

var World = {
    loaded: false,
        
    init: function initFn() {
        AR.context.onLocationChanged = function(latitude, longitude, altitude, accuracy){
            nowLat = latitude;
            nowLong = longitude;
        };
        
        AR.context.onScreenClick = function(){
            World.objectTest(nowLat, nowLong);
        };
    },
  
    objectTest : function objectTestFn(lat, long){
        var objLocation = new AR.GeoLocation(lat + 0.0005, long + 0.0001, 1);

        var chest = new AR.Model("assets/models/chest.wt3", {
            scale: {
                x: 1,
                y: 1,
                z: 1
            },
            onLoaded: this.worldLoaded
        });
        
        var obj = new AR.GeoObject(objLocation, {
            drawables: {
                cam: [chest]
            }
        });
    },  
            
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.innerHTML = nowLat + "," + nowLong;
    }
};

World.init();