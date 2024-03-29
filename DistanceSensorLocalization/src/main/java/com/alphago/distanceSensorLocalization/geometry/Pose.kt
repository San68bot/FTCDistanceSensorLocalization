package com.alphago.distanceSensorLocalization.geometry

import com.acmerobotics.roadrunner.geometry.Pose2d
import kotlin.math.PI

data class Pose(
    val point: Point,
    val rad: Double
) {
    constructor(x: Double, y: Double, rad: Double) : this(Point(x, y), rad)
    override fun toString(): String = "x: $x, y: $y, rad: $rad"

    val x = point.x
    val y = point.y
}