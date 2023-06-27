package IA1jun23;
import java.awt.Color;
import java.util.Random;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.*;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.RateControlRobot;
import robocode.ScannedRobotEvent;
import robocode.Robot;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * R201807160 - a robot by (your name here)
 */
public class R201807160 extends RateControlRobot
{
	int turnCounter;
	public void run() {
		setBodyColor(new Color(255, 255, 69));
		setGunColor(new Color(255, 69, 255));
		setRadarColor(new Color(69, 255, 255));

		turnCounter = 0;
		setGunRotationRate(15);
		
		while (true) {
			turnGunRight(10); // Scans automatically
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// Calculate exact location of the robot
		double rotacionCompleta = getHeading() + e.getBearing();
		double rotacionCanon = normalRelativeAngleDegrees(rotacionCompleta - getGunHeading());

		if (Math.abs(rotacionCanon) <= 3) {
			turnGunRight(rotacionCanon);
			if (getGunHeat() == 0) {
				fire(1);
			}
		}
		else {
			turnGunRight(rotacionCanon);
		}
		if (rotacionCanon == 0) {
			scan();
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
			if (turnCounter % 64 == 0) {
				setTurnRate(0);
				setVelocityRate(4);
			}
			if (turnCounter % 64 == 32) {
				setVelocityRate(-6);
			}
			turnCounter++;
			execute();
	}
	
	public void onHitWall(HitWallEvent e) {
			if (turnCounter % 64 == 0) {
				setTurnRate(0);
				setVelocityRate(4);
			}
			if (turnCounter % 64 == 32) {
				setVelocityRate(-6);
			}
			turnCounter++;
			execute();
	}
}
