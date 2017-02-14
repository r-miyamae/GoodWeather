package GoodWeather.logic;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class passwordEncode {
    /**
     * 暗号化メソッド
     *
     * @param text 暗号化する文字列
     * @return 暗号化文字列
     */
    public static final String ENCRYPT_KEY = "megane";
    public static final String ENCRYPT_IV = "mediafrontia";
    public static String encrypt(String text) {

        // 変数初期化
        String strResult = null;

        try {
            // 文字列をバイト配列へ変換
            byte[] byteText = text.getBytes("UTF-8");

            // 暗号化キーと初期化ベクトルをバイト配列へ変換
            byte[] byteKey = ENCRYPT_KEY.getBytes("UTF-8");
            byte[] byteIv = ENCRYPT_IV.getBytes("UTF-8");

            // 暗号化キーと初期化ベクトルのオブジェクト生成
            SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(byteIv);

            // Cipherオブジェクト生成
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Cipherオブジェクトの初期化
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            // 暗号化の結果格納
            byte[] byteResult = cipher.doFinal(byteText);

            // Base64へエンコード
            strResult = Base64.getEncoder()
                    .encodeToString(byteResult);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        // 暗号化文字列を返却
        return strResult;
    }
}
