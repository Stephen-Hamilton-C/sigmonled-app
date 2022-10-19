package app.shamilton.common.core.color

import app.shamilton.common.core.Util
import kotlin.math.max
import kotlin.math.min

/**
 * A representation of a color in red, green, and blue
 * @param r Red, 0 - 255
 * @param g Green, 0 - 255
 * @param b Blue, 0 - 255
 */
data class RGBColor(var r: Int = 0, var g: Int = 0, var b: Int = 0) {

    constructor(rgb: RGBColor): this(rgb.r, rgb.g, rgb.b)

    /**
     * Converts this RGBColor to HSVColor
     * Source: https://stackoverflow.com/a/6930407
     * @return An HSVColor
     */
    fun toHSV(): HSVColor {
        // This code will be expecting r, g, and b to be 0 - 1
        val out = HSVColor()
        val rFrac: Double = r / 255.0
        val gFrac: Double = g / 255.0
        val bFrac: Double = b / 255.0

        var min = min(rFrac, gFrac)
        min = min(min, bFrac)

        var max = max(rFrac, gFrac)
        max = max(max, bFrac)

        out.v = max                                // v
        val delta: Double = max - min
        if (delta < 0.00001)
        {
            out.s = 0.0
            out.h = 0.0 // undefined, maybe nan?
            return out
        }
        if( max > 0.0 ) { // NOTE: if Max is == 0, this divide would cause a crash
            out.s = (delta / max)                  // s
        } else {
            // if max is 0, then r = g = b = 0
            // s = 0, h is undefined
            out.s = 0.0
            out.h = 0.0                            // its now undefined
            return out
        }
        if(rFrac >= max) {
            out.h = (gFrac - bFrac) / delta
        } else if(gFrac >= max) {
            out.h = 2.0 + ( bFrac - rFrac ) / delta  // between cyan & yellow
        } else {
            out.h = 4.0 + ( rFrac - gFrac ) / delta  // between magenta & cyan
        }

        out.h *= 60.0                              // degrees

        if( out.h < 0.0 )
            out.h += 360.0

        // This function sets Hue in degrees, but we want 0 - 1
        out.h /= 360
        return out
    }

    /**
     * Converts a decimal number to a two digit hexadecimal
     * @param decimal The decimal number from 0 to 255 inclusive
     * @throws IllegalArgumentException if decimal is not within the inclusive range 0 - 255
     */
    private fun decimalToHex(decimal: Int): String {
        if(decimal > 255 || decimal < 0) throw IllegalArgumentException()

        var hex = ""
        hex += Util.decimalToHex[decimal / 16 % 16]
        hex += Util.decimalToHex[decimal % 16]
        return hex
    }

    /**
     * Converts this RGBColor to a hexadecimal string
     * @return A hexadecimal string starting with #
     */
    fun toHEX(): HEXColor {
        val rHex = decimalToHex(r)
        val gHex = decimalToHex(g)
        val bHex = decimalToHex(b)
        return HEXColor(rHex, gHex, bHex)
    }
}
