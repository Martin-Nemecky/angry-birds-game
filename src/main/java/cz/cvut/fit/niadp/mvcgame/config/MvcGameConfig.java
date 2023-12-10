package cz.cvut.fit.niadp.mvcgame.config;

public class MvcGameConfig {

    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 100;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int CANNON_MIN_Y = 100;
    public static final int CANNON_MAX_Y = 900;
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
    public static final int MAX_ENEMIES_COUNT = 8;

    public static final int GAME_OBJECT_SIZE = 30;
    public static final int LEVEL_COUNT = 3;

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
    public static final String BACK_IMAGE_RESOURCE = "/images/resized_back.jpg";
    public static final String ENEMY1_IMAGE_RESOURCE = "/images/enemy1.png";
    public static final String HURT_ENEMY1_IMAGE_RESOURCE = "/images/collision.png";
    public static final String ENEMY2_IMAGE_RESOURCE = "/images/enemy2.png";
    public static final String BOUND_IMAGE_RESOURCE = "/images/bound.png";

    public static final String MISSILE_COLLISION_SOUND_RESOURCE = "/sounds/rock_damage.wav";
    public static final String ENEMY_ATTACKED_SOUND_RESOURCE = "/sounds/hit2.wav";
    public static final String ENEMY_DESTROYED_SOUND_RESOURCE = "/sounds/destroyed.wav";
    public static final String NEXT_LEVEL_SOUND_RESOURCE = "/sounds/challenger_beaten.wav";
    public static final String MISSILE_FIRED_SOUND_RESOURCE = "/sounds/flying.wav";
    public static final String CANNON_MOVED_SOUND_RESOURCE = "/sounds/move.wav";
}