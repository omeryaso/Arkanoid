import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * GameLevel.
 */
public class GameLevel implements Animation {

    private AnimationRunner animationRunner;
    private boolean running;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter blocksCounter = new Counter();
    private Counter ballsCounter = new Counter();
    private BlockRemover remover = new BlockRemover(this, blocksCounter);
    private BallRemover ballRemover = new BallRemover(this, ballsCounter);
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private LevelNameIndicator levelNameIndicator;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private ScoreTrackingListener scoreTrackingListener;

    /**
     *
     * @param levelInformation level Information.
     * @param keyboard keyboard
     * @param animationRunner animationRunner
     * @param livesIndicator lives indicator
     * @param scoreIndicator score indicator
     * @param scoreTrackingListener score trackingListener
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner animationRunner,
                     LivesIndicator livesIndicator, ScoreIndicator scoreIndicator,
                     ScoreTrackingListener scoreTrackingListener) {
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.animationRunner = animationRunner;
        this.livesIndicator = livesIndicator;
        this.scoreIndicator = scoreIndicator;
        this.scoreTrackingListener = scoreTrackingListener;
        this.levelNameIndicator = new LevelNameIndicator(this.levelInformation.levelName());
        blocksCounter.increase(levelInformation.numberOfBlocksToRemove());
    }

    /**
     * add the given collidable to the environment.
     * @param c Collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        sprites.addSprite(this.levelInformation.getBackground());
        sprites.addSprite(scoreIndicator);
        sprites.addSprite(livesIndicator);
        sprites.addSprite(levelNameIndicator);
        this.createBorders();
        this.ballsCreator();
        this.paddleCreator();
        this.createBlocks();

    }

    /**
     * plays One Turn.
     */
    public void playOneTurn() {

        this.running = true;
        this.animationRunner.run(new CountdownAnimation(3, 3, this.sprites));

        // use our runner to run the current animation -- which is one turn of the game.
        this.animationRunner.run(this);

    }


    /**
     * @param d  Draw surface.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        this.levelInformation.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        if (this.ballsCounter.getValue() == 0 && this.livesIndicator.getLives().getValue() > 0) {
            this.livesIndicator.getLives().decrease(1);
            this.ballsCreator();
            this.paddle.removeFromGame(this);
            this.paddleCreator();
            if (this.livesIndicator.getLives().getValue() != 0) {
                this.animationRunner.run(new CountdownAnimation(3, 3, this.sprites));
            }
        }

        if (livesIndicator.getLives().getValue() == 0 || blocksCounter.getValue() == 0) {
            this.running = false;
        }

        if (blocksCounter.getValue() == 0) {
            this.scoreIndicator.getScore().increase(100);
        }

        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation pause = new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen());
            this.animationRunner.run(pause);
        }
    }

    /**
     *
     * @param c collidable to be Removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes sprite.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * creates balls.
     */
    public void ballsCreator() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 545, 4, Color.white, environment);
            ball.addToGame(this);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
        }
        ballsCounter.increase(this.levelInformation.numberOfBalls());
    }

    /**
     * @return stopping condition.
     */
    public boolean shouldStop() {
         return !this.running;
    }

    /**
     * creates a paddle.
     */
    public void paddleCreator() {

        this.paddle = new Paddle(new Rectangle(new Point(400 - this.levelInformation.paddleWidth() / 2,
                570), this.levelInformation.paddleWidth(), 15),
                keyboard, this.levelInformation.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * create borders.
     */
    public void createBorders() {
        Block leftBorder = new Block(new Point(0, 20), 20, 600, Color.gray, 0);
        Block rightBorder = new Block(new Point(780, 20), 20, 600, Color.gray, 0);
        Block upBorder = new Block(new Point(0, 20), 800, 20, Color.gray, 0);
        Block deathRegion = new Block(new Point(0, 599), 800, 20, Color.gray, 0);
        deathRegion.addHitListener(ballRemover);

        environment.addCollidable(deathRegion);
        environment.addCollidable(leftBorder);
        environment.addCollidable(rightBorder);
        environment.addCollidable(upBorder);

        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        upBorder.addToGame(this);
    }

    /**
     * create Blocks.
     */
    public void createBlocks() {
        for (Block block: this.levelInformation.blocks()) {
            block.addHitListener(this.remover);
            block.addHitListener(this.scoreTrackingListener);
            block.addToGame(this);
        }
    }

    /**
     * @return the number of blocks left in he game.
     */
    public int getBlocksLeft() {
        return this.blocksCounter.getValue();
    }

}