package jp.gr.java_conf.ktnet.dbpoipoi.util;

/**
 * 引数チェック用のユーティリティクラスです.
 * @author tanabe
 *
 */
public final class ArgumentCheckUtil {
    
    /**
     * コンストラクタ(使用不可).
     */
    private ArgumentCheckUtil() { }

    /**
     * 空文字ではないことをチェックします.
     * @param arg 引数
     */
    public static void checkNotEmpty(String arg) {
        if("".equals(arg)) {
            throw new IllegalArgumentException("空文字不可");
        }
    }
}
