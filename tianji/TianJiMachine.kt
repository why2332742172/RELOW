package com.playtheworld.youlongmachine.machine.tianji

import ink.ptms.zaphkiel.ZaphkielAPI
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Schedule
import taboolib.common.platform.function.onlinePlayers
import taboolib.platform.util.giveItem
import java.time.LocalTime

/**
 * @Author StrawberryYu
 * @Date 2021/10/5 14:27
 * @Description Every day from 12 noon to 14 pm, from 18 pm to 20 pm, a random selection of online players to distribute props "道具_养心石".
 * @Param
 * @Return
 * @Since version-1.0
*/
object TianJiMachine {
    @Awake(LifeCycle.INIT)
    fun init() {
        // machine init
        // 在插件启动时 创建一个异步线程 然后每隔10分钟检测一次当前时间是否处于“12点到14点，晚上18点到20点” 如果是则执行行为

        @Schedule(async = true,0,12000)
        fun task(){
            if(onlinePlayers().isNotEmpty()){
                if(LocalTime.now().hour in 12..14 || LocalTime.now().hour in 18..20){
                    val player = onlinePlayers().random().cast<Player>()
                    ZaphkielAPI.getItemStack("道具_养心石")?.let { player.giveItem(it) }
                }
            }
        }

    }
}