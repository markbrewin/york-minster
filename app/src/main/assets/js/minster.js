var World = {
    loaded: false,
        
    init: function initFn() {
        menu.init();
    },
    
    worldLoaded: function worldLoadedFn() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.parentElement.removeChild(e);
    }
};

var menu = {
    init: function initFn() {
        var menuLocation = new AR.RelativeLocation(null, 3, 0, 0);
        
        var htmlDrawable = new AR.HtmlDrawable({
            uri:"menu.html"}, 1, {
            onLoaded : World.worldLoaded
        });
        
        var menu = new AR.GeoObject(menuLocation, {
            drawables: {
                cam : [htmlDrawable]
            }
        });
    }
}

World.init();