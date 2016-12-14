package com.visa.qrscannerngenerator;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by ntelukun on 10/12/16.
 */
public class QRGenerator {

    @Nullable
    public static Bitmap generateQr(final String txt, int width, int height) {

        Bitmap bitmap = null;

        if (txt == null){
            return bitmap;
        }
        //Encode with a QR Code image
        QRCodeWriter qrCodeEncoder = new QRCodeWriter();
        try {
            BitMatrix bitmatrix = qrCodeEncoder.encode(txt, BarcodeFormat.QR_CODE, width, height);
            bitmap = toBitmap(bitmatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Writes the given Matrix on a new Bitmap object.
     *
     * @param matrix the matrix to write.
     * @return the new {@link Bitmap}-object.
     */
    private static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        int[] pixels = new int[width * height];
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[(y * width) + x] = matrix.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);
        return bmp;
    }
}
