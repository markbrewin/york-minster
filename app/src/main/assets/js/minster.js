var World = {
    loaded: false,
    
    init: function initFn() {
        objectTest();
    },
    
    objectTest : function objectTestFn(){
        var objLocation = new AR.RelativeLocation(null, 5, 0, 0);

        var chest = new AR.Model("assets/models/chest.wt3", {
            scale: {
                x: 1,
                y: 1,
                z: 1
            }
        });

        var obj = new AR.GeoObject(objLocation, {
            drawables: {
                cam: [chest]
            }
        });
    }
};

World.init();