##QR Code Generation and Scanning.
An Android library for QR Code Generation and Scanning.

========
##Set up
There are two ways to import this library into your android project: you can either directly import qrcodeLib.aar file or import the whole QRScannerNgenerator package (with source code) from this module. This module is also dependant on the Google vision api and thus we should include it in our dependencies.

####Import .aar file
* Download [qrcodeLib.aar](https://github.com/visa-innovation-sf/android-components/blob/dev/Libraries/qrcodeLib.aar)
* In Android Studio, choose File->New->New Module, then choose import new .JAR/.AAR package and select/import qrcodeLib.aar 
* In your app Gradle file, add following line to the dependencies
```gradle
compile project(':qrcodeLib')
compile 'com.google.android.gms:play-services-vision:9.4.0'
```

####Import QRScannerNGenerator module with source code
* checkout this repo
* In Android Studio, choose File->New->Import Module and select authLib folder to import
* In your app Gradle file, add following line to the dependencies
```gradle
compile project(':qrcodeLib')
compile 'com.google.android.gms:play-services-vision:9.4.0'
```
========
####Sample App
An example of integration with this library can be found at SampleApp module in this repo

=======
##Usage

####QR Code Generator
The call to```QRGenerator.generateQR()``` will return a bitmap. The user needs to provide the text , width and height of the bitmap the library should return in the call to ```generateQR()```
```Java
Bitmap bmp = QRGenerator.generateQr(<your text>,width,height);

Example :
Bitmap bmp = QRGenerator.generateQr(editText.getText().toString(),width,height);

```
========
####QR Code Scanning
The QR Code scanner starts a camera preview . Once the camera preview starts it will autofocus on the QR codes and will return the QR code scan result as a string. The user has an option to select two features i.e. auto focus and use flash while scanning QR. These have to be provided with the intent being sent for the ```BarCodeCaptureActivity```. By default auto focus would be on/true and use flash would off/false.

```Java
Intent intent = new Intent(getApplication().getBaseContext(), BarcodeCaptureActivity.class);
// You can always take user input to change the values for autocapture and use flash
// By default autofocus is true and use flash is false.
intent.putExtra(QRUtil.AutoFocus, true/false);
intent.putExtra(QRUtil.UseFlash, false/true);
startActivityForResult(intent, QRUtil.RC_BARCODE_CAPTURE);
```


Second step is to override the ```onActivityResult()``` method in the activity which is calling the ```BarCodeCaptureActivity```. Inside ```onActivityResult()``` the user needs to verify whether the request is corresponding to the ```QRUtil.RC_BARCODE_CAPTURE``` and then proceed to getting the QRCode value from the Intent returned to the method.The cases where the intent is null and cases where QR Code was not captured have been readily handled and error codes are provided wherever necesseary.

```Java
 @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == QRUtil.RC_BARCODE_CAPTURE) {
            if (resultCode == QRUtil.SUCCESS) {
                if (data != null) {
                    final String bval = data.getStringExtra(QRUtil.BARCODE_VALUE);
                    Log.d("ExampleActivity",bval);                    
                } else {
                    Log.d("ExampleActivity", "No barcode captured, intent data is null");
                }
            } else {
                Log.d("ExampleActivity",(String.format(getString(R.string.barcode_error),
                        QRUtil.getStatusCodeString(resultCode))));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
```

