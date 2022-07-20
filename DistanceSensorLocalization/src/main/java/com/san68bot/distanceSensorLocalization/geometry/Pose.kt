package com.san68bot.distanceSensorLocalization.geometry

import com.acmerobotics.roadrunner.geometry.Pose2d

data class Pose(
    val point: Point,
    val rad: Double
) {
    constructor(x: Double, y: Double, rad: Double) : this(Point(x, y), rad)
    override fun toString(): String {
        return "x: ${ point.x }, y: ${ point.y }, rad: $rad"
    }

    val x = point.x
    val y = point.y
    fun toPose2d(): Pose2d = Pose2d(x, y, rad)
}