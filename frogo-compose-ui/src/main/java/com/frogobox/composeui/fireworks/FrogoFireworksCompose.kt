package com.frogobox.composeui.fireworks

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import java.util.Random

/**
 * Data class representing a single particle in the native Compose fireworks system.
 */
data class FrogoParticleCompose(
    var x: Float,
    var y: Float,
    var vx: Float,
    var vy: Float,
    var alpha: Float = 1f,
    val color: Color,
    val radius: Float,
    val lifeTime: Long,
    var age: Long = 0L
)

/**
 * State class to hold the active particles and manage updates for the fireworks.
 */
class FrogoFireworksStateCompose {
    val particles = mutableStateListOf<FrogoParticleCompose>()
    private val random = Random()

    private var emitterX = 0f
    private var emitterY = 0f
    private var emitterColors = listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue, Color.Magenta, Color.Cyan)
    private var isEmitting = false
    private var emitRateMillis = 50L
    private var lastEmitTime = 0L

    /**
     * Triggers a single explosion (one-shot) at the given coordinates.
     */
    fun explode(x: Float, y: Float, colors: List<Color> = emitterColors, count: Int = 60) {
        for (i in 0 until count) {
            val angle = random.nextDouble() * 2 * Math.PI
            val speed = 2f + random.nextFloat() * 10f
            val vx = (Math.cos(angle) * speed).toFloat()
            val vy = (Math.sin(angle) * speed).toFloat()
            val color = colors[random.nextInt(colors.size)]
            particles.add(
                FrogoParticleCompose(
                    x = x,
                    y = y,
                    vx = vx,
                    vy = vy,
                    color = color,
                    radius = 4f + random.nextFloat() * 8f,
                    lifeTime = 600L + random.nextInt(800)
                )
            )
        }
    }

    /**
     * Starts emitting a stream of particles from the specified coordinates.
     */
    fun startEmit(x: Float, y: Float, colors: List<Color> = emitterColors, rateMillis: Long = 40L) {
        emitterX = x
        emitterY = y
        emitterColors = colors
        emitRateMillis = rateMillis
        isEmitting = true
    }

    /**
     * Stops the continuous emission of particles.
     */
    fun stopEmit() {
        isEmitting = false
    }

    /**
     * Updates the position, velocity, gravity, and lifetimes of active particles.
     */
    fun update(elapsedMillis: Long) {
        // Handle emitter
        if (isEmitting) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmitTime >= emitRateMillis) {
                lastEmitTime = currentTime
                // Emit a few particles per frame
                for (i in 0..3) {
                    val angle = random.nextDouble() * 2 * Math.PI
                    val speed = 1f + random.nextFloat() * 4f
                    val vx = (Math.cos(angle) * speed).toFloat()
                    val vy = (Math.sin(angle) * speed).toFloat()
                    val color = emitterColors[random.nextInt(emitterColors.size)]
                    particles.add(
                        FrogoParticleCompose(
                            x = emitterX,
                            y = emitterY,
                            vx = vx,
                            vy = vy,
                            color = color,
                            radius = 3f + random.nextFloat() * 6f,
                            lifeTime = 800L + random.nextInt(600)
                        )
                    )
                }
            }
        }

        // Update active particles
        val iterator = particles.iterator()
        while (iterator.hasNext()) {
            val particle = iterator.next()
            particle.age += elapsedMillis
            if (particle.age >= particle.lifeTime) {
                iterator.remove()
            } else {
                particle.x += particle.vx
                particle.y += particle.vy
                // Add gravity drag
                particle.vy += 0.15f
                // Decay alpha over lifetime
                particle.alpha = 1f - (particle.age.toFloat() / particle.lifeTime)
            }
        }
    }
}

/**
 * Creates and remembers a [FrogoFireworksStateCompose], setting up a frame ticker
 * loop to drive particle physics simulation automatically.
 */
@Composable
fun rememberFrogoFireworksStateCompose(): FrogoFireworksStateCompose {
    val state = remember { FrogoFireworksStateCompose() }
    LaunchedEffect(state) {
        var lastTime = withFrameMillis { it }
        while (true) {
            withFrameMillis { time ->
                val elapsed = time - lastTime
                lastTime = time
                if (elapsed > 0) {
                    // Cap elapsed at 50ms to prevent huge physics jumps on frame drop
                    state.update(elapsed.coerceAtMost(50L))
                }
            }
        }
    }
    return state
}

/**
 * A native Composable canvas that renders the active particles of a [FrogoFireworksStateCompose].
 */
@Composable
fun FrogoFireworksCompose(
    state: FrogoFireworksStateCompose,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        state.particles.forEach { particle ->
            drawCircle(
                color = particle.color.copy(alpha = particle.alpha),
                radius = particle.radius,
                center = Offset(particle.x, particle.y)
            )
        }
    }
}
