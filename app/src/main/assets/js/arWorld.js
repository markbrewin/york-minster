var World = {
    loaded: false,
        
    init: function initFn() {
        
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.parentElement.removeChild(e);
    }
};

World.init();

function addChest(lat, long, alt){
    var location = new AR.GeoLocation(lat, long, alt);
    
    var chest = new AR.Model("assets/models/chest.wt3", {
        scale: {
           x: 1,
           y: 1,
           z: 1
        },
        onLoaded: World.worldLoaded
    });
    
    var indicatorImage = new AR.ImageResource("assets/indi.png");
    
    var indicatorDrawable = new AR.ImageDrawable(indicatorImage, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        });
     
    var chestGeoLocation = new AR.GeoObject(location, {
       drawables: {
           cam: [chest],
           indicator: [indicatorDrawable]
       } 
    });
}