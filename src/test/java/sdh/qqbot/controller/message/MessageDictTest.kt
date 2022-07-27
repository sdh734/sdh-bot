package sdh.qqbot.controller.message

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sdh.qqbot.entity.message.MessageEntity

/**
 * @author jj_wen
 * @date 2022/7/27 15:38
 */
internal class MessageDictTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun matchAndRunTest() {

        // 参数
        val messageEntity = MessageEntity()
        messageEntity.message = "妹zi图"


        // 测试匹配方法
        val matchDict = MessageDict.matchDict(messageEntity)

        // 测试匹配后执行方法
        // 这个方法能进到对应的函数
        // dict[arrayOf("妹纸图", "妹子图", "妹纸图片", "妹子图片")] = { message ->
        //     TheGirlImg.theGirlImgManager(message)
        // }
        MessageDict.matchAndRun(messageEntity)

        assertNotNull(matchDict)

        // 测试复读分支
        messageEntity.message = ""
        messageEntity.messageType = "group"
        MessageDict.matchAndRun(messageEntity)
    }
}
