package com.san68bot.distanceSensorLocalization.geometry

data class Pose(
    val point: Point,
    val rad: Double
) {
    constructor(x: Double, y: Double, rad: Double) : this(Point(x, y), rad)
    override fun toString(): String = "x: $x, y: $y, rad: $rad"

    val x = point.x
    val y = point.y
}