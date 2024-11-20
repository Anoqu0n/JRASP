package Agent;

import config.Log;

import java.lang.instrument.Instrumentation;

/**
 * @author 0x安权
 * 2024/11/20 11:13
 */
public class agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        try {

            Log.logInfo("JRASP防御启动，Author:安权");
            inst.addTransformer(new ClassTransformer());

        } catch (Exception e) {
            Log.logError("JRASP防御启动失败", e);
        }
    }

}
