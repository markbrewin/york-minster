var World = {
    loaded: false,
    
    chestList: [],
    
    chestModel: null,
        
    init: function initFn() {
        World.chestList = []
        World.chestModel = new AR.Model("assets/models/chest.wt3", {
            scale: {
                x: 1,
                y: 1,
                z: 1
            }
        });
    },
    
    addChest: function addChest(lat, long, alt) {
        var location = new AR.GeoLocation(lat, long, alt);
        var indicatorImage = new AR.ImageResource("assets/indi.png");
    
        var indicatorDrawable = new AR.ImageDrawable(indicatorImage, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        });
        var chestGeoLocation = new AR.GeoObject(location, {
           drawables: {
               cam: [World.chestModel],
               indicator: [indicatorDrawable]
           } 
        });
        World.chestList.push(chestGeoLocation);
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.parentElement.removeChild(e);
    }
};

World.init();