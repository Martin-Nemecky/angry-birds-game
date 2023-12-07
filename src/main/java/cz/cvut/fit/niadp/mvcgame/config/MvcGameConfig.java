package cz.cvut.fit.niadp.mvcgame.config;

public class MvcGameConfig {

    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 200;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final double MAX_ANGLE = Math.PI / 3;
    public static final double MIN_ANGLE = -Math.PI / 3;
    public static final int POWER_STEP = 1;
    public static final int INIT_POWER = 10;
    public static final double INIT_ANGLE = 0;
    public static final int INIT_BATCH_SIZE = 1;
    public static final double GRAVITY = 9.81;
    public static final int MAX_POWER = 50;
    public static final int MIN_POWER = 1;
    public static final int MAX_BATCH_SIZE = 10;
    public static final int MIN_BATCH_SIZE = 1;

    public static final String GAME_TITLE = "The NI-ADP MvcGame";

    public static final String UP_KEY = "UP";
    public static final String DOWN_KEY = "DOWN";
    public static final String EXIT_KEY = "ESCAPE";
    public static final String SHOOT_KEY = "SPACE";
    public static final String AIM_UP_KEY = "A";
    public static final String AIM_DOWN_KEY = "Y";
    public static final String POWER_UP_KEY = "F";
    public static final String POWER_DOWN_KEY = "D";
    public static final String MOVING_STRATEGY_KEY = "M";
    public static final String SHOOTING_MODE_KEY = "N";
    public static final String UNDO_LAST_COMMAND_KEY = "B";
    public static final String INCREASE_BATCH_KEY = "I";
    public static final String DECREASE_BATCH_KEY = "U";

    public static final String CANNON_IMAGE_RESOURCE = "/images/cannon.png";

    public static final String MISSILE_IMAGE_RESOURCE = "/images/missile.png";


    public static final String MISSILE_STRETCH_SOUND_RESOURCE = "/sounds/stretch.wav";
    public static final String MISSILE_FIRED_SOUND_RESOURCE = "/sounds/flying.wav";
    public static final String CANNON_MOVED_SOUND_RESOURCE = "/sounds/move.wav";
}