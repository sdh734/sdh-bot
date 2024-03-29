package sdh.qqbot.utils

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sdh.qqbot.utils.pinyinconvert.PinYinUtils

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
        assertTrue(PinYinUtils.getPinYin("妹子图") == "meizitu")
        assertTrue(PinYinUtils.getPinYin("妹纸图") == "meizitu")
    }

    @Test
    fun isEquals() {
        assertTrue(PinYinUtils.isEquals("妹子图", "妹纸图"))
    }
}
