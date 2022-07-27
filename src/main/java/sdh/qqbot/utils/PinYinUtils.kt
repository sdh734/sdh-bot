package sdh.qqbot.utils

import com.github.promeg.pinyinhelper.Pinyin
import com.github.promeg.pinyinhelper.PinyinMapDict

/**
 * 中文转拼音
 * {@link https://github.com/hellokaton/TinyPinyin}
 *
 * @author jj_wen
 * @date 2022/7/26 15:56
 **/
object PinYinUtils {

    /**
     * 初始化字典，一些多音字之类的可以放在这
     */
    init {
        Pinyin.init(
            Pinyin.newConfig()
                .with(object : PinyinMapDict() {
                    override fun mapping(): MutableMap<String, Array<String>> {
                        val map = mutableMapOf<String, Array<String>>()

                        // 插入自定义字典
                        map["妹子图"] = arrayOf("MEI", "ZI", "TU")
                        map["妹纸图"] = arrayOf("MEI", "ZI", "TU")

                        return map
                    }
                })
        )
    }

    /**
     * 获取拼音，拼音之间没有分隔符
     */
    @JvmStatic
    fun getPinYin(text: String): String {
        return Pinyin.toPinyin(text, "").lowercase()
    }

    /**
     * 判断两个文字的拼音是否相等
     */
    @JvmStatic
    fun isEquals(source: String, input: String): Boolean =
        getPinYin(source) == getPinYin(input)

}
