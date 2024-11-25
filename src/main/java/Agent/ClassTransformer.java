package Agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.io.IOException;
import javassist.*;

/**
 * @author 0x安权
 */
public class ClassTransformer implements ClassFileTransformer {
    private Instrumentation inst;
    private ClassPool classPool;

    public ClassTransformer(Instrumentation inst) {
        this.inst = inst;
        this.classPool = new ClassPool(true);
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.equals("java/lang/ProcessBuilder")) {
            CtClass ctClass = null;
            try {
                // 找到ProcessBuilder对应的字节码
                ctClass = this.classPool.get("java.lang.ProcessBuilder");
                // 获取所有方法
                CtMethod[] methods = ctClass.getMethods();
                // $0代表this，this指的是用户创建的ProcessBuilder实例对象
                String src =
                        "if ($0.command.contains(\"ifconfig\")) {" +  // 如果命令包含 ifconfig
                                "    System.out.println(\"RASPblock\");" +  // 打印 RASPblock
                                "    return null;" +  // 阻止执行命令
                                "}";

                for (CtMethod method : methods) {
                    // 找到start方法，并插入拦截代码
                    if (method.getName().equals("start")) {
                        method.insertBefore(src);  // 在start方法执行前插入src代码
                        break;
                    }
                }
                // 获取修改后的字节码
                classfileBuffer = ctClass.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            } finally {
                if (ctClass != null) {
                    ctClass.detach();  // 清理CtClass对象
                }
            }
        }
        return classfileBuffer;
    }
}
