package com.san68bot.distanceSensorLocalization.geometry

import com.acmerobotics.roadrunner.geometry.Vector2d

data class Point(
    @JvmField var x: Double,
    @JvmField var y: Double
) {
    constructor(vec2d: Vector2d) : this(vec2d.x, vec2d.y)
    fun toVector2d() = Vector2d(x, y)
}