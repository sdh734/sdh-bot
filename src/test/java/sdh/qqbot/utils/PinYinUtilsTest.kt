package sdh.qqbot.utils

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author jj_wen
 * @date 2022/7/26 16:06
 */
internal class PinYinUtilsTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun getPinYin() {
        println(PinYinUtils.getPinYin("妹子图"))
        println(PinYinUtils.getPinYin("妹纸图"))
        assertTrue(PinYinUtils.getPinYin("妹子图") == "MEIZITU")
        assertTrue(PinYinUtils.getPinYin("妹纸图") == "MEIZITU")
    }

    @Test
    fun isEquals() {
        assertTrue(PinYinUtils.isEquals("妹子图", "妹纸图"))
    }
}
