package com.k300.graphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.BooleanSupplier;

public class MenuBackground {

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int UP_ANGLE = 90;
    private static final int DOWN_ANGLE = 270;
    private final Point redCarPosition;
    private final Point blueCarPosition;
    private final Point yellowCarPosition;
    private int yellowAngle;

    public MenuBackground() {
        final int blueCarY = SCREEN_HEIGHT / 5;
        final int redCarY = SCREEN_HEIGHT / 5 * 4;
        final int yellowCarX = SCREEN_WIDTH / 5;
        blueCarPosition = new Point(SCREEN_WIDTH - Assets.getImage(Assets.BLUE_CAR_KEY).getWidth(), blueCarY);
        redCarPosition = new Point(SCREEN_WIDTH / 20, redCarY);
        yellowCarPosition = new Point(yellowCarX, -Assets.getImage(Assets.YELLOW_CAR_KEY).getHeight());
        yellowAngle = 270;
    }

    public void tick() {
        final int speed = 5;
        blueCarPosition.x -= speed;
        blueCarPosition.x = clamp(blueCarPosition.x,
                SCREEN_WIDTH - Assets.getImage(Assets.BLUE_CAR_KEY).getWidth(),
                () -> blueCarPosition.x <= -Assets.getImage(Assets.BLUE_CAR_KEY).getWidth());
        redCarPosition.x += speed;
        redCarPosition.x = clamp(redCarPosition.x,
                SCREEN_WIDTH / 20,
                () -> redCarPosition.x >= SCREEN_WIDTH);
        yellowCarPosition.y += getYellowMultiplier() * speed;
        resetYellowPosition();
    }

    private void resetYellowPosition() {
        if(yellowAngle == UP_ANGLE && yellowCarPosition.y <= 0){
            yellowAngle = DOWN_ANGLE;
            yellowCarPosition.x = SCREEN_WIDTH / 5 * 4;
        } else if(yellowAngle == DOWN_ANGLE && yellowCarPosition.y >= SCREEN_HEIGHT) {
            yellowAngle = UP_ANGLE;
            yellowCarPosition.x = SCREEN_WIDTH / 5;
        }
    }

    private int getYellowMultiplier() {
        return (yellowAngle == UP_ANGLE)? -1: 1;
    }

    private int clamp(int toClamp, int fallBack, BooleanSupplier isOutOfBounds) {
        if(isOutOfBounds.getAsBoolean()) {
            return fallBack;
        } else {
            return toClamp;
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(Assets.getImage(Assets.RED_CAR_KEY), redCarPosition.x, redCarPosition.y, null);

        AffineTransform blueCarAngle = AffineTransform.getTranslateInstance(blueCarPosition.x - Assets.getImage(Assets.BLUE_CAR_KEY).getWidth() / 2f,
                blueCarPosition.y -  Assets.getImage(Assets.BLUE_CAR_KEY).getHeight() / 2f);
        blueCarAngle.rotate(Math.toRadians(-180),
                Assets.getImage(Assets.BLUE_CAR_KEY).getWidth() / 2f,
                Assets.getImage(Assets.BLUE_CAR_KEY).getHeight() / 2f);
        ((Graphics2D) graphics).drawImage(Assets.getImage(Assets.BLUE_CAR_KEY), blueCarAngle, null);

        AffineTransform carAngle = AffineTransform.getTranslateInstance(yellowCarPosition.x - Assets.getImage(Assets.YELLOW_CAR_KEY).getWidth() / 2f,
                yellowCarPosition.y -  Assets.getImage(Assets.YELLOW_CAR_KEY).getHeight() / 2f);
        carAngle.rotate(Math.toRadians(-yellowAngle),
                Assets.getImage(Assets.YELLOW_CAR_KEY).getWidth() / 2f,
                Assets.getImage(Assets.YELLOW_CAR_KEY).getHeight() / 2f);
        graphics.setColor(Color.red);
        graphics.drawString("" + yellowAngle, 40, 40);
        graphics.drawString("" + yellowCarPosition, 40, 50);
        ((Graphics2D)graphics).drawImage(Assets.getImage(Assets.YELLOW_CAR_KEY), carAngle, null);
    }

}
