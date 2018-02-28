var World = {
    loaded: false,
        
    init: function init() {
        console.log("hello2");
        this.createMenu();
    },
    
    createMenu: function createMenu() {
    },
    worldLoaded: function worldLoaded() {
        World.loaded = true;
        var e = document.getElementById('loadingMessage');
        e.parentElement.removeChild(e);
    }
};
World.init();