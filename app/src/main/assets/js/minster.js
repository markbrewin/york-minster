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
            var e = document.getElementById('loadingMessage');
            e.innerHTML = nowLat + "," + nowLong;
        };
    }
};

World.init();