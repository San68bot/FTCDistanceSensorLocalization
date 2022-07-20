package com.san68bot.distanceSensorLocalization

import com.san68bot.distanceSensorLocalization.geometry.Point
import com.san68bot.distanceSensorLocalization.geometry.Pose
import kotlin.math.*

/**
 * Distance Sensor Localization Class,
 * made to calculate / re-calculate the robot's position in the field fully based on the distance sensor data.
 * "NormalRange" Functions are 100% tested, with AlphaGo 16439's 2021-2022 season Robot.
 * To be used with a format of one sensor on the left, front, and right sides of the robot.
 */
class DistanceLocalization(leftSensor: Point, frontSensor: Point, rightSensor: Point, private val red: Boolean) {
    private val lt = SensorTrig(leftSensor)
    private val ft = SensorTrig(frontSensor)
    private val rt = SensorTrig(rightSensor)

    private var left = 0.0
    private var front = 0.0
    private var right = 0.0
    
    private var theta = 0.0
    private var deltaTheta = 0.0
    
    private val quadrantOneMax = 144.0

    /**
     * Normal range update function
     * @param left left sensor distance
     * @param front front sensor distance
     * @param right right sensor distance
     * @param theta robot's angle in radians
     * @return robot's estimated position (in Quadrant I & Unit circle radians!!!)
     */
    fun update(left: Double, front: Double, right: Double, theta: Double): Pose {
        this.left = left; this.front = front; this.right = right; this.theta = theta; deltaTheta = PI/2.0 - theta
        return Pose((if(red) quadrantOneMax - normalRight() else normalLeft()), quadrantOneMax - normalFront(), theta)
    }

    /**
     * Calculates x distance from left sensor
     */
    fun normalLeft(): Double {
        return ((lt.hypot * cos((asin(lt.y / lt.hypot)) +
                deltaTheta)) + (left * cos(deltaTheta)))
    }

    /**
     * Calculates y distance from front sensor
     */
    fun normalFront(): Double {
        return ((ft.hypot * cos(PI/2.0 + (-ft.beta) -
                deltaTheta)) + (front * cos(deltaTheta)))
    }

    /**
     * Calculates x distance from right sensor
     */
    fun normalRight(): Double {
        return ((rt.hypot * cos((asin(rt.y / rt.hypot)) -
                deltaTheta)) + (right * cos(deltaTheta)))
    }

    /**
     * DO NOT USE
     * Calculates y distance from left sensor
     */
    private fun leftFront(): Double {
        return ((lt.hypot * cos(PI/2.0 + lt.alpha -
                theta)) + (left * sin(deltaTheta)))
    }

    /**
     * DO NOT USE
     * Calculates x distance from front sensor on the left side (blue alliance)
     */
    private fun frontLeft(): Double {
        return ((ft.hypot * cos(PI/2.0 - ft.alpha -
                deltaTheta)) - (front * sin(deltaTheta)))
    }

    /**
     * DO NOT USE
     * Calculates x distance from front sensor on the right side (red alliance)
     */
    private fun frontRight(): Double {
        return ((ft.hypot * cos(PI/2.0 - ft.alpha -
                deltaTheta)) + (front * sin(deltaTheta)))
    }

    /**
     * DO NOT USE
     * Calculates y distance from right sensor
     */
    private fun rightFront(): Double {
        return ((rt.hypot * cos(PI/2.0 + rt.alpha -
                theta)) - (right * sin(deltaTheta)))
    }

    /**
     * Log data
     */
    override fun toString(): String {
        return "x distance from left: ${ normalLeft() }, y distance ${ normalFront() }, x distance from right ${ normalRight() }"
    }

    /**
     * Data class for easy to use sensor trigonometry
     */
    private data class SensorTrig(val s: Point) {
        val x = s.x; val y = s.y
        val hypot = hypot(x, y)
        val alpha = atan(x / y)
        val beta = atan(y / x)
    }
}