package com.mygdx.resources;

public enum ResourceEnum {

//ATLASES
    PLAYER("assets/packed/player.atlas", TypeEnum.ATLAS),
    NPCS("assets/packed/npcs.atlas", TypeEnum.ATLAS),
    WEAPONS("assets/packed/weapons.atlas", TypeEnum.ATLAS),
    BULLETS("assets/packed/bullets.atlas", TypeEnum.ATLAS),
    BUILDINGS("assets/packed/buildings.atlas", TypeEnum.ATLAS),
    COMPONENTS("assets/packed/components.atlas", TypeEnum.ATLAS),
    EFFECTS("assets/packed/effects.atlas", TypeEnum.ATLAS),
    OTHERS("assets/packed/others.atlas", TypeEnum.ATLAS),

//ATLAS REGIONS:

    //PLAYER
    PLAYER_IDLE_DOWN,
    PLAYER_IDLE_UP,
    PLAYER_IDLE_LEFT,
    PLAYER_IDLE_RIGHT,
    PLAYER_WALK_DOWN(3),
    PLAYER_WALK_UP(3),
    PLAYER_WALK_LEFT(1),
    PLAYER_WALK_RIGHT(1),


    //NPCS
    JERKINS_IDLE_DOWN(3),
    JERKINS_IDLE_UP(1),
    JERKINS_IDLE_LEFT(3),
    JERKINS_IDLE_RIGHT(3),
    JERKINS_WALK_DOWN(3),
    JERKINS_WALK_UP(3),
    JERKINS_WALK_LEFT(3),
    JERKINS_WALK_RIGHT(3),


    BLACKMARKETEER_IDLE_DOWN(3),
    BLACKMARKETEER_IDLE_UP(1),
    BLACKMARKETEER_IDLE_LEFT(3),
    BLACKMARKETEER_IDLE_RIGHT(3),
    BLACKMARKETEER_WALK_DOWN(3),
    BLACKMARKETEER_WALK_UP(3),
    BLACKMARKETEER_WALK_LEFT(3),
    BLACKMARKETEER_WALK_RIGHT(3),
    BLACKMARKETEER_SLEEPING(3, 0.01f, 0),

    TEST,


    //PROJECTILES
    STONE,

    BULLET,

    //WEAPONS
    DEFAULT,

    CHAINGUN,

    USBLADE,


    //BUILDINGS
    ABANDONED_TURNED_OFF(4),
    ABANDONED_TURNED_ON(4),
    
    ELECTRO(11),
    
    ENERGYPLANT_TURNED_OFF(2),
    ENERGYPLANT_TURNED_ON(2),

    GLUTTONY_DONUT(2),

    MARMOT_PIZZA(5),
    
    SKYSCRAPER_1_BACK,
    SKYSCRAPER_1_FRONT,
    SKYSCRAPER_1_SIDE,

    SKYSCRAPER_2_BACK,
    SKYSCRAPER_2_FRONT,

    SKYSCRAPER_3_FRONT,

    SKYSCRAPER_HIGH,

    SKYSCRAPER_L,
    
    SKYSCRAPER_MEDIUM,
    
    SKYSCRAPER_SMALL,
    
    SKYSCRAPER_U,
    
    SYNTH_TURNED_OFF(4),
    SYNTH_TURNED_ON(4),
    SKYSCRAPER_PROTOTYPE,
    

    //MAP COMPONENTS
    LAMP(2),
    LONG_LAMP,
    TRAFFIC_LIGHT,
    GLASS_DOOR(9, 0.1f, 3),
    MARCO_LAMP(4),
    SIDE_WALK(1),
    RUBBISH(3),
    GLASS_DOOR1_OPENING(32, 0.02f, 5),
    SAVING_TREE,

    //EFFECTS
    EXPLOSION(8, 0.05f, 0),

    //OTHERS
    MARKER,
    SCOPE,

//MENU
    ITAFLAG("assets/dialogues/images/itaFlag.png", TypeEnum.TEXTURE),
    ENGFLAG("assets/dialogues/images/engFlag.jpg", TypeEnum.TEXTURE),
    PLAY_BUTTON("assets/menu/play.png", TypeEnum.TEXTURE),
    SETTINGS_BUTTON("assets/menu/settings.png", TypeEnum.TEXTURE),
    QUIT_BUTTON("assets/menu/quit.png", TypeEnum.TEXTURE),
    BACKGROUND_1("assets/menu/background.jpg", TypeEnum.TEXTURE),
    BACKGROUND_2("assets/menu/background-2.jpg", TypeEnum.TEXTURE),

//HUD
    HEALTH_BAR("assets/hud/health.png", TypeEnum.TEXTURE),

//TILED MAPS
    SLUMS("assets/map/slums/slums.tmx", TypeEnum.MAP),
    RICH_DISTRICT("assets/map/rich_disctirct/rich_district.tmx", TypeEnum.MAP),
    PARK("assets/map/park/park.tmx", TypeEnum.MAP),
    REFLECTION_ARENA("assets/map/reflection_arena/reflection_arena.tmx", TypeEnum.MAP),
    INSIDE("assets/map/inside/inside.tmx", TypeEnum.MAP),


//DIALOGUE TEXTURES
    COMPLEX_DIALOGUE("assets/dialogues/images/dialogue.png", TypeEnum.TEXTURE),
    CHOICE("assets/dialogues/images/choice.png", TypeEnum.TEXTURE),


//DIALOGUES
    ADEPTUS_1("adeptus1_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_2("adeptus2_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_3("adeptus3_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_4("adeptus4_JSON.json", TypeEnum.DIALOGUE),
    SAVE("save.json", TypeEnum.DIALOGUE),
    
//MUSIC
    REFLECTION_1("assets/audio/reflection_1.mp3", TypeEnum.AUDIO),
    REFLECTION_2("assets/audio/reflection_2.mp3", TypeEnum.AUDIO),
    REFLECTION_3("assets/audio/reflection_3.mp3", TypeEnum.AUDIO),
    REFLECTION_4("assets/audio/reflection_4.mp3", TypeEnum.AUDIO),
    REFLECTION_5("assets/audio/reflection_5.mp3", TypeEnum.AUDIO),



//SCRIPTS
    TEST_SCRIPT("assets/scripts/npcs/reflection/test.gcs", TypeEnum.SCRIPT),
    TEST_2("assets/scripts/npcs/reflection/test2.gcs", TypeEnum.SCRIPT),
    TEST_3("assets/scripts/npcs/reflection/test3.gcs", TypeEnum.SCRIPT),
    MEET_1("assets/scripts/npcs/reflection/meet1.gcs", TypeEnum.SCRIPT),
    MEET_2("assets/scripts/npcs/reflection/meet2.gcs", TypeEnum.SCRIPT),

//SKIN
    SKIN("assets/ui_v2.json", TypeEnum.ATLAS_REGION);


    public String label;
    public TypeEnum type;
    public float animationRate = -1;
    public float delay = -1;
    public int  frameCount = 1;

    /**
     * only for non graphics OR textures not included in any atlas AND single frame
     * AKA use it only for non textures
     * @param label
     * @param type
     */
    ResourceEnum(String label, TypeEnum type) {
        this.label = label;
        this.type = type;
        
    }

    /**
     * texture with default (1) frame count
     * @param label
     * @param type
     * @param animationRate
     * @param delay
     */
    ResourceEnum(String label, TypeEnum type, float animationRate, float delay) {
        this(label, type);
        this.animationRate = animationRate;
        this.delay = delay;
    }

    /**
     * texture with custom frame count
     * @param label
     * @param type
     * @param animationRate
     * @param delay
     */
    ResourceEnum(String label, TypeEnum type, float animationRate, float delay, int frameCount) {
        this(label, type, animationRate, delay);
        this.frameCount = frameCount;
    }

    /**
     * Animation included in a textureatlas
     * Lable is trivial, should always be the same name as the enum
     * TypeEnum is trivial, you are only allowed to not specify the label when you have a ATLAS_REGION
     * @param animationRate
     * @param delay
     * @param frameCount
     */
    ResourceEnum(int frameCount, float animationRate, float delay){
        this.label = name().toLowerCase();
        this.type = TypeEnum.ATLAS_REGION;
        this.animationRate = animationRate;
        this.delay = delay;
        this.frameCount = frameCount;
    }

    /**
     * Animation included in a textureatlas with default rate and delay
     * Lable is trivial, should always be the same name as the enum
     * TypeEnum is trivial, you are only allowed to not specify the label when you have a ATLAS_REGION
     * @param animationRate
     * @param delay
     * @param frameCount
     */
    ResourceEnum(int frameCount){
        this.label = name().toLowerCase();
        this.type = TypeEnum.ATLAS_REGION;
        this.frameCount = frameCount;
    }

    ResourceEnum(){
        this(1);
    }
}
