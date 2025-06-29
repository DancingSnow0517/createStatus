package cn.dancingsnow.createstates;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FastColor;

import java.util.function.UnaryOperator;

public class GradientUtil {

    public static final UnaryOperator<Style> RAINBOW = style -> style.withColor(rainbowColor(2.5f));

    public static TextColor rainbowColor(float speed) {
        return TextColor.fromRgb(GradientUtil.toRGB((CreateStates.CLIENT_TICK & ((1 << 20) - 1)) * speed, 95f, 60f));
    }

    private GradientUtil() {}

    public static int toRGB(float h, float s, float l) {
        // Formula needs all values between 0 - 1
        h = h % 360.0F;
        h /= 360.0F;
        s /= 100.0F;
        l /= 100.0F;

        float q;
        if (l < 0.5F) {
            q = l * (1 + s);
        } else {
            q = (l + s) - (s * l);
        }

        float p = 2 * l - q;

        int r = (int) (Math.max(0, hueToRGB(p, q, h + (1.0F / 3.0F))) * 255);
        int g = (int) (Math.max(0, hueToRGB(p, q, h)) * 255);
        int b = (int) (Math.max(0, hueToRGB(p, q, h - (1.0F / 3.0F))) * 255);

        return FastColor.ARGB32.color(255, r, g, b);
    }

    private static float hueToRGB(float p, float q, float h) {
        if (h < 0) {
            h += 1;
        }
        if (h > 1) {
            h -= 1;
        }
        if (6 * h < 1) {
            return p + ((q - p) * 6 * h);
        }
        if (2 * h < 1) {
            return q;
        }
        if (3 * h < 2) {
            return p + ((q - p) * 6 * ((2.0F / 3.0F) - h));
        }
        return p;
    }
}