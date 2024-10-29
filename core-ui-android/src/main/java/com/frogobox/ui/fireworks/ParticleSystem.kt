package com.frogobox.ui.fireworks

import android.R
import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.frogobox.ui.fireworks.initializers.*
import com.frogobox.ui.fireworks.modifiers.AlphaModifier
import com.frogobox.ui.fireworks.modifiers.ParticleModifier
import com.frogobox.ui.fireworks.initializers.AccelerationInitializer
import java.lang.ref.WeakReference
import java.util.*

class ParticleSystem private constructor(
    parentView: ViewGroup,
    maxParticles: Int,
    timeToLive: Long
) {
    private var mParentView: ViewGroup? = null
    private val mMaxParticles: Int
    private val mRandom: Random
    private var mDrawingView: ParticleField? = null
    private val mParticles: ArrayList<Particle>
    private val mActiveParticles = ArrayList<Particle>()
    private val mTimeToLive: Long
    private var mCurrentTime: Long = 0
    private var mParticlesPerMillisecond = 0f
    private var mActivatedParticles = 0
    private var mEmittingTime: Long = 0
    private val mModifiers: MutableList<ParticleModifier>
    private val mInitializers: MutableList<ParticleInitializer>
    private var mAnimator: ValueAnimator? = null
    private var mTimer: Timer? = null
    private val mTimerTask = ParticleTimerTask(this)
    private val mDpToPxScale: Float
    private val mParentLocation: IntArray
    private var mEmitterXMin = 0
    private var mEmitterXMax = 0
    private var mEmitterYMin = 0
    private var mEmitterYMax = 0

    private class ParticleTimerTask(ps: ParticleSystem?) : TimerTask() {
        private val mPs: WeakReference<ParticleSystem?>

        init {
            mPs = WeakReference(ps)
        }

        override fun run() {
            if (mPs.get() != null) {
                val ps = mPs.get()
                ps!!.onUpdate(ps.mCurrentTime)
                ps.mCurrentTime += TIMER_TASK_INTERVAL
            }
        }
    }

    init {
        mRandom = Random()
        mParentLocation = IntArray(2)
        setParentViewGroup(parentView)
        mModifiers = ArrayList()
        mInitializers = ArrayList()
        mMaxParticles = maxParticles
        // Create the particles
        mParticles = ArrayList()
        mTimeToLive = timeToLive
        val displayMetrics = parentView.context.resources.displayMetrics
        mDpToPxScale = displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
    }

    /**
     * Creates a particle system with the given parameters
     *
     * @param parentView   The parent view group
     * @param drawable     The drawable to use as a particle
     * @param maxParticles The maximum number of particles
     * @param timeToLive   The time to live for the particles
     */
    constructor(
        parentView: ViewGroup,
        maxParticles: Int,
        drawable: Drawable,
        timeToLive: Long
    ) : this(parentView, maxParticles, timeToLive) {
        if (drawable is AnimationDrawable) {
            for (i in 0 until mMaxParticles) {
                mParticles.add(AnimatedParticle(drawable))
            }
        } else {
            val bitmap: Bitmap?
            if (drawable is BitmapDrawable) {
                bitmap = drawable.bitmap
            } else {
                bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
            }
            for (i in 0 until mMaxParticles) {
                mParticles.add(Particle(bitmap))
            }
        }
    }

    /**
     * Creates a particle system with the given parameters
     *
     * @param a             The parent activity
     * @param maxParticles  The maximum number of particles
     * @param drawableRedId The drawable resource to use as particle (supports Bitmaps and Animations)
     * @param timeToLive    The time to live for the particles
     */
    constructor(a: Activity, maxParticles: Int, drawableRedId: Int, timeToLive: Long) : this(a,
        maxParticles,
        ContextCompat.getDrawable(a, drawableRedId)!!,
        timeToLive,
        R.id.content) {
    }

    /**
     * Creates a particle system with the given parameters
     *
     * @param a             The parent activity
     * @param maxParticles  The maximum number of particles
     * @param drawableRedId The drawable resource to use as particle (supports Bitmaps and Animations)
     * @param timeToLive    The time to live for the particles
     * @param parentViewId  The view Id for the parent of the particle system
     */
    constructor(
        a: Activity,
        maxParticles: Int,
        drawableRedId: Int,
        timeToLive: Long,
        parentViewId: Int
    ) : this(a, maxParticles, ContextCompat.getDrawable(a, drawableRedId)!!, timeToLive, parentViewId) {
    }
    /**
     * Utility constructor that receives a Drawable
     *
     * @param a            The parent activity
     * @param maxParticles The maximum number of particles
     * @param drawable     The drawable to use as particle (supports Bitmaps and Animations)
     * @param timeToLive   The time to live for the particles
     * @param parentViewId The view Id for the parent of the particle system
     */
    /**
     * Utility constructor that receives a Drawable
     *
     * @param a            The parent activity
     * @param maxParticles The maximum number of particles
     * @param drawable     The drawable to use as particle (supports Bitmaps and Animations)
     * @param timeToLive   The time to live for the particles
     */
    @JvmOverloads
    constructor(
        a: Activity,
        maxParticles: Int,
        drawable: Drawable,
        timeToLive: Long,
        parentViewId: Int = R.id.content
    ) : this(a.findViewById<View>(parentViewId) as ViewGroup, maxParticles, drawable, timeToLive) {
    }

    fun dpToPx(dp: Float): Float {
        return dp * mDpToPxScale
    }
    /**
     * Utility constructor that receives a Bitmap
     *
     * @param a            The parent activity
     * @param maxParticles The maximum number of particles
     * @param bitmap       The bitmap to use as particle
     * @param timeToLive   The time to live for the particles
     * @param parentViewId The view Id for the parent of the particle system
     */
    /**
     * Utility constructor that receives a Bitmap
     *
     * @param a            The parent activity
     * @param maxParticles The maximum number of particles
     * @param bitmap       The bitmap to use as particle
     * @param timeToLive   The time to live for the particles
     */
    @JvmOverloads
    constructor(
        a: Activity,
        maxParticles: Int,
        bitmap: Bitmap?,
        timeToLive: Long,
        parentViewId: Int = R.id.content
    ) : this(a.findViewById<View>(parentViewId) as ViewGroup, maxParticles, timeToLive) {
        for (i in 0 until mMaxParticles) {
            mParticles.add(Particle(bitmap))
        }
    }
    /**
     * Utility constructor that receives an AnimationDrawable
     *
     * @param a            The parent activity
     * @param maxParticles The maximum number of particles
     * @param animation    The animation to use as particle
     * @param timeToLive   The time to live for the particles
     * @param parentViewId The view Id for the parent of the particle system
     */
    /**
     * Utility constructor that receives an AnimationDrawable
     *
     * @param a            The parent activity
     * @param maxParticles The maximum number of particles
     * @param animation    The animation to use as particle
     * @param timeToLive   The time to live for the particles
     */
    @JvmOverloads
    constructor(
        a: Activity,
        maxParticles: Int,
        animation: AnimationDrawable?,
        timeToLive: Long,
        parentViewId: Int = R.id.content
    ) : this(a.findViewById<View>(parentViewId) as ViewGroup, maxParticles, timeToLive) {
        // Create the particles
        for (i in 0 until mMaxParticles) {
            mParticles.add(AnimatedParticle(animation!!))
        }
    }

    /**
     * Adds a modifier to the Particle system, it will be executed on each update.
     *
     * @param modifier modifier to be added to the ParticleSystem
     */
    fun addModifier(modifier: ParticleModifier): ParticleSystem {
        mModifiers.add(modifier)
        return this
    }

    fun setSpeedRange(speedMin: Float, speedMax: Float): ParticleSystem {
        mInitializers.add(SpeedModuleAndRangeInitializer(dpToPx(speedMin),
            dpToPx(speedMax),
            0,
            360))
        return this
    }

    /**
     * Initializes the speed range and angle range of emitted particles. Angles are in degrees
     * and non negative:
     * 0 meaning to the right, 90 to the bottom,... in clockwise orientation. Speed is non
     * negative and is described in pixels per millisecond.
     *
     * @param speedMin The minimum speed to emit particles.
     * @param speedMax The maximum speed to emit particles.
     * @param minAngle The minimum angle to emit particles in degrees.
     * @param maxAngle The maximum angle to emit particles in degrees.
     * @return This.
     */
    fun setSpeedModuleAndAngleRange(
        speedMin: Float,
        speedMax: Float,
        minAngle: Int,
        maxAngle: Int
    ): ParticleSystem {
        // else emitting from top (270°) to bottom (90°) range would not be possible if someone
        // entered minAngle = 270 and maxAngle=90 since the module would swap the values
        var maxAngle = maxAngle
        while (maxAngle < minAngle) {
            maxAngle += 360
        }
        mInitializers.add(SpeedModuleAndRangeInitializer(dpToPx(speedMin),
            dpToPx(speedMax),
            minAngle,
            maxAngle))
        return this
    }

    /**
     * Initializes the speed components ranges that particles will be emitted. Speeds are
     * measured in density pixels per millisecond.
     *
     * @param speedMinX The minimum speed in x direction.
     * @param speedMaxX The maximum speed in x direction.
     * @param speedMinY The minimum speed in y direction.
     * @param speedMaxY The maximum speed in y direction.
     * @return This.
     */
    fun setSpeedByComponentsRange(
        speedMinX: Float,
        speedMaxX: Float,
        speedMinY: Float,
        speedMaxY: Float
    ): ParticleSystem {
        mInitializers.add(SpeeddByComponentsInitializer(dpToPx(speedMinX), dpToPx(speedMaxX),
            dpToPx(speedMinY), dpToPx(speedMaxY)))
        return this
    }

    /**
     * Initializes the initial rotation range of emitted particles. The rotation angle is
     * measured in degrees with 0° being no rotation at all and 90° tilting the image to the right.
     *
     * @param minAngle The minimum tilt angle.
     * @param maxAngle The maximum tilt angle.
     * @return This.
     */
    fun setInitialRotationRange(minAngle: Int, maxAngle: Int): ParticleSystem {
        mInitializers.add(RotationInitializer(minAngle, maxAngle))
        return this
    }

    /**
     * Initializes the scale range of emitted particles. Will scale the images around their
     * center multiplied with the given scaling factor.
     *
     * @param minScale The minimum scaling factor
     * @param maxScale The maximum scaling factor.
     * @return This.
     */
    fun setScaleRange(minScale: Float, maxScale: Float): ParticleSystem {
        mInitializers.add(ScaleInitializer(minScale, maxScale))
        return this
    }

    /**
     * Initializes the rotation speed of emitted particles. Rotation speed is measured in degrees
     * per second.
     *
     * @param rotationSpeed The rotation speed.
     * @return This.
     */
    fun setRotationSpeed(rotationSpeed: Float): ParticleSystem {
        mInitializers.add(RotationSpeedInitializer(rotationSpeed, rotationSpeed))
        return this
    }

    /**
     * Initializes the rotation speed range for emitted particles. The rotation speed is measured
     * in degrees per second and can be positive or negative.
     *
     * @param minRotationSpeed The minimum rotation speed.
     * @param maxRotationSpeed The maximum rotation speed.
     * @return This.
     */
    fun setRotationSpeedRange(minRotationSpeed: Float, maxRotationSpeed: Float): ParticleSystem {
        mInitializers.add(RotationSpeedInitializer(minRotationSpeed, maxRotationSpeed))
        return this
    }

    /**
     * Initializes the acceleration range and angle range of emitted particles. The acceleration
     * components in x and y direction are controlled by the acceleration angle. The acceleration
     * is measured in density pixels per square millisecond. The angle is measured in degrees
     * with 0° pointing to the right and going clockwise.
     *
     * @param minAcceleration
     * @param maxAcceleration
     * @param minAngle
     * @param maxAngle
     * @return
     */
    fun setAccelerationModuleAndAndAngleRange(
        minAcceleration: Float,
        maxAcceleration: Float,
        minAngle: Int,
        maxAngle: Int
    ): ParticleSystem {
        mInitializers.add(
            AccelerationInitializer(dpToPx(minAcceleration), dpToPx(maxAcceleration),
            minAngle, maxAngle)
        )
        return this
    }

    /**
     * Adds a custom initializer for emitted particles. The most common use case is the ability to
     * update the initializer in real-time instead of adding new ones ontop of the existing one.
     *
     * @param initializer The non-null initializer to add.
     * @return This.
     */
    fun addInitializer(initializer: ParticleInitializer?): ParticleSystem {
        if (initializer != null) {
            mInitializers.add(initializer)
        }
        return this
    }

    /**
     * Initializes the acceleration for emitted particles with the given angle. Acceleration is
     * measured in pixels per square millisecond. The angle is measured in degrees with 0°
     * meaning to the right and orientation being clockwise. The angle controls the acceleration
     * direction.
     *
     * @param acceleration The acceleration.
     * @param angle        The acceleration direction.
     * @return This.
     */
    fun setAcceleration(acceleration: Float, angle: Int): ParticleSystem {
        mInitializers.add(AccelerationInitializer(acceleration, acceleration, angle, angle))
        return this
    }

    /**
     * Initializes the parent view group. This needs to be done before any other configuration or
     * emitting is done. Drawing will be done to a child that is added to this view. So this view
     * needs to allow displaying an arbitrary sized view on top of its other content.
     *
     * @param viewGroup The view group to use.
     * @return This.
     */
    fun setParentViewGroup(viewGroup: ViewGroup?): ParticleSystem {
        mParentView = viewGroup
        if (mParentView != null) {
            mParentView!!.getLocationInWindow(mParentLocation)
        }
        return this
    }

    fun setStartTime(time: Long): ParticleSystem {
        mCurrentTime = time
        return this
    }

    /**
     * Configures a fade out for the particles when they disappear
     *
     * @param milisecondsBeforeEnd fade out duration in milliseconds
     * @param interpolator         the interpolator for the fade out (default is linear)
     */
    fun setFadeOut(milisecondsBeforeEnd: Long, interpolator: Interpolator?): ParticleSystem {
        mModifiers.add(AlphaModifier(255,
            0,
            mTimeToLive - milisecondsBeforeEnd,
            mTimeToLive,
            interpolator!!))
        return this
    }

    /**
     * Configures a fade out for the particles when they disappear
     *
     * @param duration fade out duration in milliseconds
     */
    fun setFadeOut(duration: Long): ParticleSystem {
        return setFadeOut(duration, LinearInterpolator())
    }

    /**
     * Starts emitting particles from a specific view. If at some point the number goes over the amount of particles availabe on create
     * no new particles will be created
     *
     * @param emitter            View from which center the particles will be emited
     * @param gravity            Which position among the view the emission takes place
     * @param particlesPerSecond Number of particles per second that will be emited (evenly distributed)
     * @param emittingTime       time the emitter will be emitting particles
     */
    fun emitWithGravity(emitter: View, gravity: Int, particlesPerSecond: Int, emittingTime: Int) {
        // Setup emitter
        configureEmitter(emitter, gravity)
        startEmitting(particlesPerSecond, emittingTime)
    }

    /**
     * Starts emitting particles from a specific view. If at some point the number goes over the amount of particles availabe on create
     * no new particles will be created
     *
     * @param emitter            View from which center the particles will be emited
     * @param particlesPerSecond Number of particles per second that will be emited (evenly distributed)
     * @param emittingTime       time the emitter will be emitting particles
     */
    fun emit(emitter: View, particlesPerSecond: Int, emittingTime: Int) {
        emitWithGravity(emitter, Gravity.CENTER, particlesPerSecond, emittingTime)
    }

    /**
     * Starts emitting particles from a specific view. If at some point the number goes over the amount of particles availabe on create
     * no new particles will be created
     *
     * @param emitter            View from which center the particles will be emited
     * @param particlesPerSecond Number of particles per second that will be emited (evenly distributed)
     */
    fun emit(emitter: View, particlesPerSecond: Int) {
        // Setup emitter
        emitWithGravity(emitter, Gravity.CENTER, particlesPerSecond)
    }

    /**
     * Starts emitting particles from a specific view. If at some point the number goes over the amount of particles availabe on create
     * no new particles will be created
     *
     * @param emitter            View from which center the particles will be emited
     * @param gravity            Which position among the view the emission takes place
     * @param particlesPerSecond Number of particles per second that will be emited (evenly distributed)
     */
    fun emitWithGravity(emitter: View, gravity: Int, particlesPerSecond: Int) {
        // Setup emitter
        configureEmitter(emitter, gravity)
        startEmitting(particlesPerSecond)
    }

    private fun startEmitting(particlesPerSecond: Int) {
        mActivatedParticles = 0
        mParticlesPerMillisecond = particlesPerSecond / 1000f
        // Add a full size view to the parent view
        mDrawingView = ParticleField(mParentView!!.context)
        mParentView!!.addView(mDrawingView)
        mEmittingTime = -1 // Meaning infinite
        mDrawingView!!.setParticles(mActiveParticles)
        updateParticlesBeforeStartTime(particlesPerSecond)
        mTimer = Timer()
        mTimer!!.schedule(mTimerTask, 0, TIMER_TASK_INTERVAL)
    }

    fun emit(emitterX: Int, emitterY: Int, particlesPerSecond: Int, emittingTime: Int) {
        configureEmitter(emitterX, emitterY)
        startEmitting(particlesPerSecond, emittingTime)
    }

    private fun configureEmitter(emitterX: Int, emitterY: Int) {
        // We configure the emitter based on the window location to fix the offset of action bar if present
        mEmitterXMin = emitterX - mParentLocation[0]
        mEmitterXMax = mEmitterXMin
        mEmitterYMin = emitterY - mParentLocation[1]
        mEmitterYMax = mEmitterYMin
    }

    private fun startEmitting(particlesPerSecond: Int, emittingTime: Int) {
        mActivatedParticles = 0
        mParticlesPerMillisecond = particlesPerSecond / 1000f
        // Add a full size view to the parent view
        mDrawingView = ParticleField(mParentView!!.context)
        mParentView!!.addView(mDrawingView)
        mDrawingView!!.setParticles(mActiveParticles)
        updateParticlesBeforeStartTime(particlesPerSecond)
        mEmittingTime = emittingTime.toLong()
        startAnimator(LinearInterpolator(), emittingTime + mTimeToLive)
    }

    fun emit(emitterX: Int, emitterY: Int, particlesPerSecond: Int) {
        configureEmitter(emitterX, emitterY)
        startEmitting(particlesPerSecond)
    }

    fun updateEmitPoint(emitterX: Int, emitterY: Int) {
        configureEmitter(emitterX, emitterY)
    }

    fun updateEmitPoint(emitter: View, gravity: Int) {
        configureEmitter(emitter, gravity)
    }
    /**
     * Launches particles in one Shot using a special Interpolator
     *
     * @param emitter      View from which center the particles will be emited
     * @param numParticles number of particles launched on the one shot
     * @param interpolator the interpolator for the time
     */
    /**
     * Launches particles in one Shot
     *
     * @param emitter      View from which center the particles will be emited
     * @param numParticles number of particles launched on the one shot
     */
    @JvmOverloads
    fun oneShot(
        emitter: View,
        numParticles: Int,
        interpolator: Interpolator = LinearInterpolator()
    ) {
        configureEmitter(emitter, Gravity.CENTER)
        mActivatedParticles = 0
        mEmittingTime = mTimeToLive
        // We create particles based in the parameters
        var i = 0
        while (i < numParticles && i < mMaxParticles) {
            activateParticle(0)
            i++
        }
        // Add a full size view to the parent view
        mDrawingView = ParticleField(mParentView!!.context)
        mParentView!!.addView(mDrawingView)
        mDrawingView!!.setParticles(mActiveParticles)
        // We start a property animator that will call us to do the update
        // Animate from 0 to timeToLiveMax
        startAnimator(interpolator, mTimeToLive)
    }

    private fun startAnimator(interpolator: Interpolator, animnationTime: Long) {
        mAnimator = ValueAnimator.ofInt(0, animnationTime.toInt()).apply {
            duration = animnationTime
            addUpdateListener { animation ->
                val miliseconds = animation.animatedValue as Int
                onUpdate(miliseconds.toLong())
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    cleanupAnimation()
                }

                override fun onAnimationCancel(animation: Animator) {
                    cleanupAnimation()
                }
            })
            setInterpolator(interpolator)
        }
        mAnimator!!.start()
    }

    private fun configureEmitter(emitter: View, gravity: Int) {
        // It works with an emision range
        val location = IntArray(2)
        emitter.getLocationInWindow(location)

        // Check horizontal gravity and set range
        if (hasGravity(gravity, Gravity.LEFT)) {
            mEmitterXMin = location[0] - mParentLocation[0]
            mEmitterXMax = mEmitterXMin
        } else if (hasGravity(gravity, Gravity.RIGHT)) {
            mEmitterXMin = location[0] + emitter.width - mParentLocation[0]
            mEmitterXMax = mEmitterXMin
        } else if (hasGravity(gravity, Gravity.CENTER_HORIZONTAL)) {
            mEmitterXMin = location[0] + emitter.width / 2 - mParentLocation[0]
            mEmitterXMax = mEmitterXMin
        } else {
            // All the range
            mEmitterXMin = location[0] - mParentLocation[0]
            mEmitterXMax = location[0] + emitter.width - mParentLocation[0]
        }

        // Now, vertical gravity and range
        if (hasGravity(gravity, Gravity.TOP)) {
            mEmitterYMin = location[1] - mParentLocation[1]
            mEmitterYMax = mEmitterYMin
        } else if (hasGravity(gravity, Gravity.BOTTOM)) {
            mEmitterYMin = location[1] + emitter.height - mParentLocation[1]
            mEmitterYMax = mEmitterYMin
        } else if (hasGravity(gravity, Gravity.CENTER_VERTICAL)) {
            mEmitterYMin = location[1] + emitter.height / 2 - mParentLocation[1]
            mEmitterYMax = mEmitterYMin
        } else {
            // All the range
            mEmitterYMin = location[1] - mParentLocation[1]
            mEmitterYMax = location[1] + emitter.height - mParentLocation[1]
        }
    }

    private fun hasGravity(gravity: Int, gravityToCheck: Int): Boolean {
        return gravity and gravityToCheck == gravityToCheck
    }

    private fun activateParticle(delay: Long) {
        val p = mParticles.removeAt(0)
        p.init()
        // Initialization goes before configuration, scale is required before can be configured properly
        for (i in mInitializers.indices) {
            mInitializers[i].initParticle(p, mRandom)
        }
        val particleX = getFromRange(mEmitterXMin, mEmitterXMax)
        val particleY = getFromRange(mEmitterYMin, mEmitterYMax)
        p.configure(mTimeToLive, particleX.toFloat(), particleY.toFloat())
        p.activate(delay, mModifiers)
        mActiveParticles.add(p)
        mActivatedParticles++
    }

    private fun getFromRange(minValue: Int, maxValue: Int): Int {
        if (minValue == maxValue) {
            return minValue
        }
        return if (minValue < maxValue) {
            mRandom.nextInt(maxValue - minValue) + minValue
        } else {
            mRandom.nextInt(minValue - maxValue) + maxValue
        }
    }

    private fun onUpdate(miliseconds: Long) {
        while ((mEmittingTime > 0 && miliseconds < mEmittingTime || mEmittingTime == -1L) &&  // This point should emit
            mParticles.isNotEmpty() && // We have particles in the pool
            mActivatedParticles < mParticlesPerMillisecond * miliseconds
        ) { // and we are under the number of particles that should be launched
            // Activate a new particle
            activateParticle(miliseconds)
        }
        synchronized(mActiveParticles) {
            var i = 0
            while (i < mActiveParticles.size) {
                val active = mActiveParticles[i].update(miliseconds)
                if (!active) {
                    val p = mActiveParticles.removeAt(i)
                    i-- // Needed to keep the index at the right position
                    mParticles.add(p)
                }
                i++
            }
        }
        mDrawingView!!.postInvalidate()
    }

    private fun cleanupAnimation() {
        mParentView!!.removeView(mDrawingView)
        mDrawingView = null
        mParentView!!.postInvalidate()
        mParticles.addAll(mActiveParticles)
    }

    /**
     * Stops emitting new particles, but will draw the existing ones until their timeToLive expire
     * For an cancellation and stop drawing of the particles, use cancel instead.
     */
    fun stopEmitting() {
        // The time to be emitting is the current time (as if it was a time-limited emitter
        mEmittingTime = mCurrentTime
    }

    /**
     * Cancels the particle system and all the animations.
     * To stop emitting but animate until the end, use stopEmitting instead.
     */
    fun cancel() {
        if (mAnimator != null && mAnimator!!.isRunning) {
            mAnimator!!.cancel()
        }
        if (mTimer != null) {
            mTimer!!.cancel()
            mTimer!!.purge()
            cleanupAnimation()
        }
    }

    private fun updateParticlesBeforeStartTime(particlesPerSecond: Int) {
        if (particlesPerSecond == 0) {
            return
        }
        val currentTimeInMs = mCurrentTime / 1000
        val framesCount = currentTimeInMs / particlesPerSecond
        if (framesCount == 0L) {
            return
        }
        val frameTimeInMs = mCurrentTime / framesCount
        for (i in 1..framesCount) {
            onUpdate(frameTimeInMs * i + 1)
        }
    }

    companion object {
        private var TIMER_TASK_INTERVAL: Long = 33 // Default 30fps

        /**
         * Sets the frames per second of *ALL* ParticleSystems
         *
         * @param fps the desired frames per second
         */
        fun setFPS(fps: Double) {
            TIMER_TASK_INTERVAL = Math.round(1000 / fps)
        }
    }
}