package com.san68bot.distanceSensorLocalization.roadrunner

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.san68bot.distanceSensorLocalization.geometry.Pose
import kotlin.math.PI

fun Pose.asRoadRunnerCoords(): Pose2d {
    return Pose2d(this.y - 72.0, 72.0 - this.x, PI/2 + this.rad)
}

fun Double.asUnitCircle(): Double {
    var theta = this
    if (theta >= PI * 2.0) theta -= PI * 2.0
    if (theta < 0.0) theta += PI * 2.0
    return theta
}